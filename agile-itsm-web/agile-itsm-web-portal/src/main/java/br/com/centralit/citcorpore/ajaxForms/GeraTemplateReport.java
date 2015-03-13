package br.com.centralit.citcorpore.ajaxForms;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import br.com.centralit.citajax.html.AjaxFormAction;
import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citajax.html.HTMLElement;
import br.com.centralit.citcorpore.bean.BIConsultaDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.metainfo.bean.CamposObjetoNegocioDTO;
import br.com.centralit.citcorpore.metainfo.util.HashMapUtil;
import br.com.centralit.citcorpore.negocio.BIConsultaService;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.centralit.citcorpore.util.Enumerados.TipoDate;
import br.com.centralit.citcorpore.util.WebUtil;
import br.com.centralit.citgerencial.bean.GerencialOptionDTO;
import br.com.centralit.citgerencial.bean.GerencialOptionsDTO;
import br.com.centralit.citgerencial.bean.GerencialParameterDTO;
import br.com.centralit.citgerencial.negocio.GerencialGenerate;
import br.com.centralit.citgerencial.util.WebUtilGerencial;
import br.com.citframework.dto.Usuario;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.JdbcEngine;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilI18N;
import br.com.citframework.util.UtilStrings;
import br.com.citframework.util.UtilTratamentoArquivos;

import com.lowagie.text.Font;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class GeraTemplateReport extends AjaxFormAction {

    private static final Color COR_FUNDO = Color.WHITE;
    private static final Color COR_TITULO = Color.BLACK;

    @Override
    public void load(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final BIConsultaDTO biConsultaParm = (BIConsultaDTO) document.getBean();
        final BIConsultaService biConsultaService = (BIConsultaService) ServiceLocator.getInstance().getService(BIConsultaService.class, null);
        final UsuarioDTO user = WebUtil.getUsuario(request);
        if (user == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }
        final Usuario userGer = WebUtilGerencial.getUsuario(request);

        BIConsultaDTO biConsultaDTO = new BIConsultaDTO();
        biConsultaDTO.setIdConsulta(biConsultaParm.getIdConsulta());
        request.setAttribute("idConsulta", "" + biConsultaParm.getIdConsulta());
        try {
            biConsultaDTO = (BIConsultaDTO) biConsultaService.restore(biConsultaDTO);
        } catch (final Exception e) {
            document.alert("Consulta inexistente!");
            return;
        }
        if (biConsultaDTO == null) {
            document.alert("Consulta inexistente!");
            return;
        }
        HashMap hashParametros = null;
        if (biConsultaDTO.getParametros() != null && !biConsultaDTO.getParametros().trim().equalsIgnoreCase("")) {
            biConsultaDTO.setListParameters(this.getSubTreeParameters(biConsultaDTO.getParametros()));
            hashParametros = this.getParametrosInformados(request);
        }

        if (!biConsultaParm.isParametersPreenchidos() && biConsultaDTO.getListParameters() != null && biConsultaDTO.getListParameters().size() > 0) {
            final HTMLElement divParametros = document.getElementById("divParametros");
            request.setAttribute("htmlProcess", "");
            divParametros.setInnerHTML(this.geraParametrosPainel(biConsultaDTO.getListParameters(), hashParametros, userGer, true, request));
            document.executeScript("DEFINEALLPAGES_atribuiCaracteristicasCitAjax()");
            // document.executeScript("$('#POPUP_PARAM').attr('title','" + gerencialPainelDto.getDescription() + "')");
            document.executeScript("HTMLUtils.focusInFirstActivateField(document.formParametros)");
            document.executeScript("pageLoad();");
            document.executeScript("$('#POPUP_PARAM').dialog('open')");
            return;
        }

        JdbcEngine jdbcEngine = null;
        if (CITCorporeUtil.JDBC_ALIAS_REPORTS != null && !CITCorporeUtil.JDBC_ALIAS_REPORTS.trim().equalsIgnoreCase("")) {
            jdbcEngine = new JdbcEngine(CITCorporeUtil.JDBC_ALIAS_REPORTS, null);
        } else {
            jdbcEngine = new JdbcEngine(Constantes.getValue("DATABASE_ALIAS"), null);
        }
        final Map<String, Object> data = new HashMap<String, Object>();

        final List result = new ArrayList();
        final List result1 = new ArrayList();
        final List result2 = new ArrayList();
        final List result3 = new ArrayList();
        final List result4 = new ArrayList();
        final List result5 = new ArrayList();
        final List result6 = new ArrayList();
        final List result7 = new ArrayList();
        final List result8 = new ArrayList();
        final List result9 = new ArrayList();

        final String strResult = new String("");
        final String strResult1 = new String("");
        final String strResult2 = new String("");
        final String strResult3 = new String("");
        final String strResult4 = new String("");
        final String strResult5 = new String("");
        final String strResult6 = new String("");
        final String strResult7 = new String("");
        final String strResult8 = new String("");
        final String strResult9 = new String("");
        String formula = biConsultaDTO.getScriptExec();
        if (formula != null && !formula.trim().equalsIgnoreCase("")) {
            final org.mozilla.javascript.Context cx = org.mozilla.javascript.Context.enter();
            final org.mozilla.javascript.Scriptable scope = cx.initStandardObjects();

            final String sourceName = this.getClass().getName() + "_Script";

            final String retorno = "";
            formula = formula.replaceAll("TEXTSEARCH", "utilStrings.generateNomeBusca");
            formula = formula.replaceAll("GETFIELD", "hashMapUtil.getFieldInHash");

            scope.put("user", scope, user);
            scope.put("httpRequest", scope, request);

            scope.put("hashParametros", scope, hashParametros);
            scope.put("consultaDTO", scope, biConsultaDTO);

            scope.put("objGeraTemplateReport", scope, this);

            scope.put("retorno", scope, retorno);
            scope.put("utilStrings", scope, new UtilStrings());
            scope.put("hashMapUtil", scope, new HashMapUtil());

            scope.put("jdbcEngine", scope, jdbcEngine);
            scope.put("SGBD_PRINCIPAL", scope, CITCorporeUtil.SGBD_PRINCIPAL);

            scope.put("result", scope, result);
            scope.put("result1", scope, result1);
            scope.put("result2", scope, result2);
            scope.put("result3", scope, result3);
            scope.put("result4", scope, result4);
            scope.put("result5", scope, result5);
            scope.put("result6", scope, result6);
            scope.put("result7", scope, result7);
            scope.put("result8", scope, result8);
            scope.put("result9", scope, result9);

            scope.put("strResult", scope, strResult);
            scope.put("strResult1", scope, strResult1);
            scope.put("strResult2", scope, strResult2);
            scope.put("strResult3", scope, strResult3);
            scope.put("strResult4", scope, strResult4);
            scope.put("strResult5", scope, strResult5);
            scope.put("strResult6", scope, strResult6);
            scope.put("strResult7", scope, strResult7);
            scope.put("strResult8", scope, strResult8);
            scope.put("strResult9", scope, strResult9);

            scope.put("dataToTemplate", scope, data);

            cx.evaluateString(scope, formula, sourceName, 1, null);
        }
        final File f = new File(CITCorporeUtil.CAMINHO_REAL_APP + "/templateFreeMarker/" + user.getIdUsuario());
        if (!f.exists()) {
            f.mkdirs();
        }

        final String strPathTemplate = CITCorporeUtil.CAMINHO_REAL_APP + "/templateFreeMarker/" + user.getIdUsuario() + "/CONS_"
                + biConsultaDTO.getIdConsulta() + "_PROCESS.ftl";
        UtilTratamentoArquivos.geraFileTxtFromString(strPathTemplate, biConsultaDTO.getTemplate());

        final String strPathTemplateCAB = strPathTemplate.replaceAll(".ftl", ".html");
        this.geraTemplate("templateFreeMarker/" + user.getIdUsuario() + "/CONS_" + biConsultaDTO.getIdConsulta() + "_PROCESS.ftl", data, strPathTemplateCAB);

        final String strCabecalho = UtilTratamentoArquivos.getStringTextFromFileTxtTemplate(strPathTemplateCAB);
        final String strFinal = encode(strCabecalho);
        request.setAttribute("htmlProcess", strFinal);
        System.out.println(strFinal);
    }

    private void geraTemplate(final String strPathTemplate, final Map<String, Object> data, final String strPathOutput) throws IOException, TemplateException {
        final Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(CITCorporeUtil.CAMINHO_REAL_APP));
        final Template template = cfg.getTemplate(strPathTemplate, "UTF-8");
        final Writer file = new FileWriter(new File(strPathOutput));
        template.process(data, file);
        file.flush();
        file.close();
    }

    public String generateGrafico_Pizza(final boolean is3D, List listRetorno, final UsuarioDTO usuario, final String titulo, final boolean percentagem,
            final Integer tam, final Integer alt, final HttpServletRequest request) {
        JFreeChart chart;
        final DefaultPieDataset dados = new DefaultPieDataset();
        Double objDouble;
        String objString1;
        if (listRetorno == null) {
            listRetorno = new ArrayList();
        }
        for (int i = 0; i < listRetorno.size(); i++) {
            final Object[] row = (Object[]) listRetorno.get(i);

            objDouble = new Double(0);
            objString1 = "";
            for (final Object element : row) {
                Object obj = element;

                objDouble = null;
                if (Integer.class.isInstance(obj)) {
                    objDouble = new Double(((Integer) obj).intValue());
                } else if (Long.class.isInstance(obj)) {
                    objDouble = new Double(((Long) obj).intValue());
                } else if (Double.class.isInstance(obj)) {
                    objDouble = new Double(((Double) obj).doubleValue());
                } else {
                    if (BigDecimal.class.isInstance(obj)) {
                        objDouble = new Double(((BigDecimal) obj).doubleValue());
                    } else if (Long.class.isInstance(obj)) {
                        objDouble = new Double(((Long) obj).doubleValue());
                    } else if (Integer.class.isInstance(obj)) {
                        objDouble = new Double(((Integer) obj).doubleValue());
                    } else if (BigInteger.class.isInstance(obj)) {
                        objDouble = new Double(((BigInteger) obj).doubleValue());
                        // } else {
                        // objDouble = (Double) obj;
                    }
                }
                if (objDouble == null) {
                    objDouble = new Double(0);
                }
                if (String.class.isInstance(obj)) {
                    if (obj == null) {
                        obj = new String("");
                    }
                    objString1 = (String) obj;
                }
            }

            dados.setValue(objString1, objDouble.doubleValue());
        }
        if (is3D) {
            chart = ChartFactory.createPieChart3D(titulo, dados, true, true, false);
        } else {
            chart = ChartFactory.createPieChart(titulo, dados, true, true, false);
        }

        chart.setBackgroundPaint(COR_FUNDO);
        chart.getTitle().setPaint(COR_TITULO);
        chart.getTitle().setFont(new java.awt.Font("arial", Font.BOLD, 12));
        chart.getPlot().setOutlinePaint(null);
        chart.getPlot().setBackgroundPaint(new Color(221, 227, 213));

        final PiePlot piePlot = (PiePlot) chart.getPlot();
        piePlot.setNoDataMessage(UtilI18N.internacionaliza(request, "citcorpore.comum.naoHaDadosParaApresentar"));
        // piePlot.setNoDataMessage("Não há dados.");

        if (percentagem) {
            piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("({0}) {1} - {2}"));
        } else {
            piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {1}", new DecimalFormat("0"), new DecimalFormat("0")));
        }

        String caminhoRelativo = "";
        String caminho = "";
        try {
            final String strPath = CITCorporeUtil.CAMINHO_REAL_APP + "/tempFiles/" + usuario.getIdUsuario();
            File arquivo = new File(CITCorporeUtil.CAMINHO_REAL_APP + "/tempFiles");
            if (!arquivo.exists()) {
                arquivo.mkdirs();
            }
            final File arquivoVer = new File(strPath);
            if (!arquivoVer.exists()) {
                arquivoVer.mkdirs();
            }

            final String fileName = "GRFTMPL_"
                    + UtilDatas.convertDateToString(TipoDate.TIMESTAMP_WITH_SECONDS, UtilDatas.getDataHoraAtual(), WebUtil.getLanguage(request))
                            .replaceAll("/", "_").replaceAll(":", "_").replaceAll(" ", "_") + ".png";
            caminho = strPath + "/" + fileName;
            String urlInicial = "";
            try {
                // urlInicial = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.URL_Sistema, "");
            } catch (final Exception e) {
                e.printStackTrace();
            }
            if (urlInicial == null || urlInicial.trim().equalsIgnoreCase("")) {
                urlInicial = Constantes.getValue("CONTEXTO_APLICACAO");
            }
            caminhoRelativo = urlInicial + "/tempFiles" + "/" + usuario.getIdUsuario() + "/" + fileName;

            arquivo = new File(caminho);
            if (arquivo.exists()) {
                arquivo.delete();
            }
            ChartUtilities.saveChartAsPNG(arquivo, chart, tam, alt);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return "<img src=\"" + caminhoRelativo + "\"/>";
    }

    public Object generateRetornoGrafico_Barra(final boolean is3D, final List listRetorno, final UsuarioDTO usuario, final String titulo,
            final boolean percentagem, final Integer tam, final Integer alt, final HttpServletRequest request) throws Exception {
        JFreeChart chart;
        final DefaultCategoryDataset dados = new DefaultCategoryDataset();
        Double objDouble;
        String objString1;
        String objString2;
        int posString = 0;

        for (int i = 0; i < listRetorno.size(); i++) {
            final Object[] row = (Object[]) listRetorno.get(i);
            objDouble = new Double(0);
            objString1 = "";
            objString2 = "";
            posString = 0;

            for (final Object element : row) {
                Object obj = element;

                objDouble = null;
                if (Integer.class.isInstance(obj)) {
                    objDouble = new Double(((Integer) obj).intValue());
                } else if (Long.class.isInstance(obj)) {
                    objDouble = new Double(((Long) obj).intValue());
                } else if (Double.class.isInstance(obj)) {
                    objDouble = new Double(((Double) obj).doubleValue());
                } else {
                    if (BigDecimal.class.isInstance(obj)) {
                        objDouble = new Double(((BigDecimal) obj).doubleValue());
                    } else if (Long.class.isInstance(obj)) {
                        objDouble = new Double(((Long) obj).doubleValue());
                    } else if (Integer.class.isInstance(obj)) {
                        objDouble = new Double(((Integer) obj).doubleValue());
                    } else if (BigInteger.class.isInstance(obj)) {
                        objDouble = new Double(((BigInteger) obj).doubleValue());
                        // } else {
                        // objDouble = (Double) obj;
                    }
                }
                if (objDouble == null) {
                    objDouble = new Double(0);
                }
                if (String.class.isInstance(obj)) {
                    if (obj == null) {
                        obj = new String("");
                    }
                    String str = "";
                    str = (String) obj;
                    if (posString == 0) {
                        objString1 = str;
                    } else {
                        objString2 = str;
                    }
                    posString++;
                }
            }

            dados.addValue(objDouble.doubleValue(), objString1, objString2);
        }

        final String t1 = null;
        final String t2 = null;

        if (is3D) {
            chart = ChartFactory.createBarChart3D(titulo, t1, t2, dados, PlotOrientation.VERTICAL, true, true, false);
        } else {
            chart = ChartFactory.createBarChart(titulo, t1, t2, dados, PlotOrientation.VERTICAL, true, true, false);
        }

        // Setando o valor maximo para nunca passar de 100, ja q se trata de
        // porcentagem
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        plot.getRenderer().setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE1, TextAnchor.CENTER));
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setUpperMargin(0.20);
        rangeAxis.setAxisLineVisible(true);
        rangeAxis.setTickLabelsVisible(true);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // Formatando cores de fundo, fonte do titulo, etc...
        chart.setBackgroundPaint(COR_FUNDO); // Cor do fundo do grafico
        chart.getTitle().setPaint(COR_TITULO); // Cor do titulo
        chart.getTitle().setFont(new java.awt.Font("arial", Font.BOLD, 12)); // Fonte do
        chart.getPlot().setBackgroundPaint(new Color(221, 227, 213));// Cor de
        chart.getLegend().setItemFont(new java.awt.Font("arial", Font.BOLD, 8));
        chart.setBorderVisible(false); // Visibilidade da borda do grafico
        final BarRenderer rend = (BarRenderer) plot.getRenderer();
        // CategoryItemRenderer rend = (CategoryItemRenderer) plot.getRenderer();
        rend.setSeriesOutlinePaint(0, Color.BLACK); // Cor da borda das barras do
        rend.setBaseItemLabelFont(new java.awt.Font("SansSerif", Font.BOLD, 10));

        // rend.setSeriesPaint(0, new Color(70 ,130 ,180)); // Cor das barras do
        // rend.setItemMargin(0.10); // Margem entre o eixo Y e a primeira barra do
        rend.setBaseItemLabelsVisible(true);
        // rend.setBaseItemLabelGenerator(new CustomLabelGenerator());
        rend.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        rend.setSeriesItemLabelsVisible(0, new Boolean(true));
        rend.setSeriesItemLabelGenerator(0, new StandardCategoryItemLabelGenerator());
        // rend.setSeriesPositiveItemLabelPosition(1, new ItemLabelPosition(ItemLabelAnchor.OUTSIDE11, TextAnchor.BASELINE_CENTER, TextAnchor.BASELINE_CENTER,
        // 50.0));
        rend.setPositiveItemLabelPositionFallback(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.BASELINE_CENTER, TextAnchor.CENTER, 0.0));

        String caminhoRelativo = "";
        String caminho = "";
        try {
            final String strPath = CITCorporeUtil.CAMINHO_REAL_APP + "/tempFiles/" + usuario.getIdUsuario();
            File arquivo = new File(CITCorporeUtil.CAMINHO_REAL_APP + "/tempFiles");
            if (!arquivo.exists()) {
                arquivo.mkdirs();
            }
            final File arquivoVer = new File(strPath);
            if (!arquivoVer.exists()) {
                arquivoVer.mkdirs();
            }

            final String fileName = "GRFTMPLBAR_"
                    + UtilDatas.convertDateToString(TipoDate.TIMESTAMP_WITH_SECONDS, UtilDatas.getDataHoraAtual(), WebUtil.getLanguage(request))
                            .replaceAll("/", "_").replaceAll(":", "_").replaceAll(" ", "_") + ".png";
            caminho = strPath + "/" + fileName;

            String urlInicial = "";
            try {
                // urlInicial = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.URL_Sistema, "");
            } catch (final Exception e) {
                e.printStackTrace();
            }
            if (urlInicial == null || urlInicial.trim().equalsIgnoreCase("")) {
                urlInicial = Constantes.getValue("CONTEXTO_APLICACAO");
            }
            caminhoRelativo = urlInicial + "/tempFiles" + "/" + usuario.getIdUsuario() + "/" + fileName;

            arquivo = new File(caminho);
            if (arquivo.exists()) {
                arquivo.delete();
            }
            ChartUtilities.saveChartAsPNG(arquivo, chart, tam, alt);

        } catch (final IOException e) {
            e.printStackTrace();
        }

        return "<img src=\"" + caminhoRelativo + "\"/>";
    }

    public String executeScript(String formula, final HashMap map, final CamposObjetoNegocioDTO camposObjetoNegocioDTO) {
        if (formula == null) {
            return null;
        }
        final org.mozilla.javascript.Context cx = org.mozilla.javascript.Context.enter();
        final org.mozilla.javascript.Scriptable scope = cx.initStandardObjects();

        final String sourceName = this.getClass().getName() + "_Script";

        final String retorno = "";
        formula = formula.replaceAll("TEXTSEARCH", "utilStrings.generateNomeBusca");
        formula = formula.replaceAll("GETFIELD", "hashMapUtil.getFieldInHash");
        formula = "retorno = " + formula;

        String compl = "var importNames = JavaImporter();\n";

        compl += "importNames.importPackage(Packages.br.com.citframework.util);\n";
        compl += "importNames.importPackage(Packages.br.com.centralit.citcorpore.metainfo.util);\n";

        formula = compl + "\n" + formula;

        final List result = new ArrayList();
        final List result1 = new ArrayList();
        final List result2 = new ArrayList();
        final List result3 = new ArrayList();
        final List result4 = new ArrayList();
        final List result5 = new ArrayList();
        final List result6 = new ArrayList();
        final List result7 = new ArrayList();
        final List result8 = new ArrayList();
        final List result9 = new ArrayList();

        scope.put("retorno", scope, retorno);
        scope.put("utilStrings", scope, new UtilStrings());
        scope.put("hashMapUtil", scope, new HashMapUtil());

        scope.put("result", scope, result);
        scope.put("result1", scope, result1);
        scope.put("result2", scope, result2);
        scope.put("result3", scope, result3);
        scope.put("result4", scope, result4);
        scope.put("result5", scope, result5);
        scope.put("result6", scope, result6);
        scope.put("result7", scope, result7);
        scope.put("result8", scope, result8);
        scope.put("result9", scope, result9);

        final Object resultEval = cx.evaluateString(scope, formula, sourceName, 1, null);

        // System.out.println(cx.toString(result));

        return org.mozilla.javascript.Context.toString(resultEval);
    }

    @Override
    public Class<BIConsultaDTO> getBeanClass() {
        return BIConsultaDTO.class;
    }

    public String generateSQL(String sql, final UsuarioDTO usuario, final Collection colParmsUtilizadosNoSQL) {
        sql = sql.replaceAll("\\{IDEMPRESA\\}", "" + usuario.getIdEmpresa());
        sql = sql.replaceAll("\\{DATAATUAL\\}", "'" + UtilDatas.dateToSTR(UtilDatas.getDataAtual()) + "'");

        boolean b = true;
        while (b) {
            final int beginIndex = sql.indexOf("{PARAM");
            if (beginIndex >= 0) {
                final int endIndex = sql.indexOf("}");
                String nameParm = sql.substring(beginIndex, endIndex);
                nameParm = nameParm.replaceAll("\\{", "");

                sql = sql.replaceFirst("\\{" + nameParm + "\\}", "?");

                colParmsUtilizadosNoSQL.add(nameParm);
            } else {
                b = false;
            }
        }

        return sql;
    }

    public List generateParameters(final HashMap hsmParms, final UsuarioDTO usuario, final Collection colParmsUtilizadosNoSQL,
            final Collection colDefinicaoParametros) {
        if (colParmsUtilizadosNoSQL == null || colParmsUtilizadosNoSQL.size() == 0) {
            return null;
        }
        final List lstRetorno = new ArrayList();
        for (final Iterator it = colParmsUtilizadosNoSQL.iterator(); it.hasNext();) {
            final String nameParm = (String) it.next();
            final String type = this.getTypeParametro(colDefinicaoParametros, nameParm);

            final String valor = (String) hsmParms.get(nameParm);
            if (type.equalsIgnoreCase("java.sql.Date")) {
                Date data = null;
                try {
                    data = UtilDatas.strToSQLDate(valor);
                } catch (final LogicException e) {
                    e.printStackTrace();
                }
                lstRetorno.add(data);
            }
            if (type.equalsIgnoreCase("java.lang.Integer")) {
                Integer intAux = null;
                if (valor == null) {
                    intAux = new Integer(0);
                } else {
                    intAux = new Integer(valor);
                }
                lstRetorno.add(intAux);
            }
            if (type.equalsIgnoreCase("java.lang.Double")) {
                Double duplo;

                String aux = valor;
                aux = aux.replaceAll("\\.", "");
                aux = aux.replaceAll("\\,", "\\.");

                duplo = new Double(Double.parseDouble(aux));
                lstRetorno.add(duplo);
            }
            if (type.equalsIgnoreCase("java.lang.String")) {
                lstRetorno.add(valor);
            }
        }
        return lstRetorno;
    }

    private String getTypeParametro(final Collection colDefinicaoParametros, final String nameParm) {
        if (colDefinicaoParametros == null) {
            return "";
        }
        for (final Iterator it = colDefinicaoParametros.iterator(); it.hasNext();) {
            final GerencialParameterDTO gerencialParameterDTO = (GerencialParameterDTO) it.next();
            final String nomeParmAux = "PARAM." + gerencialParameterDTO.getName().trim();
            if (nomeParmAux.equalsIgnoreCase(nameParm)) {
                return gerencialParameterDTO.getType();
            }
        }
        return "";
    }

    private String geraParametrosPainel(final List listParameters, final HashMap hashParametros, final Usuario user, final boolean reload,
            final HttpServletRequest request) throws ServiceException, Exception {
        String strRetorno = "<table>";
        for (final Iterator it = listParameters.iterator(); it.hasNext();) {
            final GerencialParameterDTO gerencialParameterDto = (GerencialParameterDTO) it.next();

            strRetorno += "<tr>";
            strRetorno += "<td>";
            strRetorno += UtilI18N.internacionaliza(request, gerencialParameterDto.getDescription());
            if (gerencialParameterDto.isMandatory()) {
                strRetorno += "*";
            }
            strRetorno += "</td>";
            strRetorno += "<td>";
            strRetorno += this.geraCampo(gerencialParameterDto, listParameters, hashParametros, user, reload, request);
            strRetorno += "</td>";
            strRetorno += "</tr>";
        }
        strRetorno += "</table>";

        return strRetorno;
    }

    private String geraCampo(final GerencialParameterDTO gerencialParameterDto, final List listParameters, final HashMap hashParametros, final Usuario user,
            final boolean reload, final HttpServletRequest request) throws ServiceException, Exception {
        String strRetorno = "";
        String strValid = "";

        if (gerencialParameterDto.isMandatory()) {
            strValid = "Required";
        }

        // Verifica se o campo tem o tipo HTML como select.
        if (gerencialParameterDto.getTypeHTML() != null && gerencialParameterDto.getTypeHTML().equalsIgnoreCase("select")) {
            String strValidCompleta = "";

            if (!strValid.trim().equalsIgnoreCase("")) {
                strValidCompleta = " Valid[" + strValid + "]";
            }
            strRetorno += "<select size='" + gerencialParameterDto.getSize() + "' name='PARAM." + gerencialParameterDto.getName() + "' id='PARAM."
                    + gerencialParameterDto.getName() + "' class='Description[" + gerencialParameterDto.getDescription() + "]" + strValidCompleta + "'";

            if (gerencialParameterDto.isReload()) {
                // strRetorno += " onchange='recarrega(this)'";
            }

            String value = "#$#%$#%$#%$#%$#%$#%$#%$#%$#%$#%$#%"; // Valor absurdo!!!! So sera usado se nao foi informado o parametro.
            if (reload) {
                value = (String) hashParametros.get("PARAM." + gerencialParameterDto.getName());
                if (value == null) {
                    value = "#$#%$#%$#%$#%$#%$#%$#%$#%$#%$#%$#%";
                }
            }

            strRetorno += ">";
            if (gerencialParameterDto.getColOptions() != null) {
                for (final Iterator it = gerencialParameterDto.getColOptions().iterator(); it.hasNext();) {
                    final Object obj = it.next();
                    if (GerencialOptionDTO.class.isInstance(obj)) {
                        final GerencialOptionDTO option = (GerencialOptionDTO) obj;
                        strRetorno += "<option value='" + option.getValue() + "'";
                        if (reload) {
                            if (option.getValue().equalsIgnoreCase(value)) {
                                strRetorno += " selected ";
                            }
                        }
                        strRetorno += ">" + UtilI18N.internacionaliza(request, option.getText()) + "</option>";
                    }
                    if (GerencialOptionsDTO.class.isInstance(obj)) {
                        final GerencialOptionsDTO options = (GerencialOptionsDTO) obj;
                        if (options.isOnload() || reload) {
                            final GerencialGenerate gerencialGenerateService = (GerencialGenerate) ServiceLocator.getInstance().getService(
                                    GerencialGenerate.class, WebUtil.getUsuarioSistema(request));
                            final Collection col = gerencialGenerateService.executaSQLOptions(options, listParameters, hashParametros, user);
                            for (final Iterator itOptions = col.iterator(); itOptions.hasNext();) {
                                final GerencialOptionDTO option = (GerencialOptionDTO) itOptions.next();
                                strRetorno += "<option value='" + option.getValue() + "'";
                                if (reload) {
                                    if (option.getValue().equalsIgnoreCase(value)) {
                                        strRetorno += " selected ";
                                    }
                                }
                                strRetorno += ">" + option.getText() + "</option>";
                            }
                        }
                    }
                }
            }
            strRetorno += "</select>";
        }

        // Verifica se o campo tem o tipo HTML como checkbox.
        if (gerencialParameterDto.getTypeHTML() != null && gerencialParameterDto.getTypeHTML().equalsIgnoreCase("checkbox")) {
            String strValidCompleta = "";

            if (!strValid.trim().equalsIgnoreCase("")) {
                strValidCompleta = " Valid[" + strValid + "]";
            }

            String value = "#$#%$#%$#%$#%$#%$#%$#%$#%$#%$#%$#%"; // Valor absurdo!!!! So sera usado se nao foi informado o parametro.
            if (reload) {
                value = (String) hashParametros.get("PARAM." + gerencialParameterDto.getName());
                if (value == null) {
                    value = "#$#%$#%$#%$#%$#%$#%$#%$#%$#%$#%$#%";
                }
            }

            if (gerencialParameterDto.getColOptions() != null) {
                String strRetornoAux = "";
                int qtdeOpcoes = 0;
                for (final Iterator it = gerencialParameterDto.getColOptions().iterator(); it.hasNext();) {
                    final Object obj = it.next();
                    if (GerencialOptionDTO.class.isInstance(obj)) {
                        qtdeOpcoes++;
                        final GerencialOptionDTO option = (GerencialOptionDTO) obj;
                        strRetornoAux += "<input type='checkbox' name='PARAM." + gerencialParameterDto.getName() + "' id='PARAM."
                                + gerencialParameterDto.getName() + "' class='Description[" + gerencialParameterDto.getDescription() + "] " + strValidCompleta
                                + "' value='" + option.getValue() + "'";
                        if (reload) {
                            if (option.getValue().equalsIgnoreCase(value)) {
                                strRetornoAux += " checked='checked' ";
                            }
                        }
                        if (gerencialParameterDto.isReload()) {
                            // strRetornoAux += " onclick='recarrega(this)'";
                        }
                        strRetornoAux += "/>" + option.getText() + "<br/>";
                    }
                    if (GerencialOptionsDTO.class.isInstance(obj)) {
                        final GerencialOptionsDTO options = (GerencialOptionsDTO) obj;
                        if (options.isOnload() || reload) {
                            final GerencialGenerate gerencialGenerateService = (GerencialGenerate) ServiceLocator.getInstance().getService(
                                    GerencialGenerate.class, WebUtil.getUsuarioSistema(request));
                            final Collection col = gerencialGenerateService.executaSQLOptions(options, listParameters, hashParametros, user);
                            for (final Iterator itOptions = col.iterator(); itOptions.hasNext();) {
                                qtdeOpcoes++;
                                final GerencialOptionDTO option = (GerencialOptionDTO) itOptions.next();
                                strRetornoAux += "<input type='checkbox' name='PARAM." + gerencialParameterDto.getName() + "' id='PARAM."
                                        + gerencialParameterDto.getName() + "' class='Description[" + gerencialParameterDto.getDescription() + "] "
                                        + strValidCompleta + "' value='" + option.getValue() + "'";
                                if (reload) {
                                    if (option.getValue().equalsIgnoreCase(value)) {
                                        strRetornoAux += " checked='checked' ";
                                    }
                                }
                                if (gerencialParameterDto.isReload()) {
                                    // strRetornoAux += " onclick='recarrega(this)'";
                                }
                                strRetornoAux += "/>" + option.getText() + "<br/>";
                            }
                        }
                    }
                }
                if (!strRetornoAux.equalsIgnoreCase("")) {
                    if (qtdeOpcoes > 5) {
                        strRetorno += "<div style='height:100px; overflow:auto; border: 1px solid black'>" + strRetornoAux + "</div>";
                    } else {
                        strRetorno += strRetornoAux;
                    }
                }
            }
        }

        // Campo Date
        if (gerencialParameterDto.getType().equalsIgnoreCase("java.sql.Date")) {
            strValid += ",Date";

            strRetorno += "<input type='text' size='" + gerencialParameterDto.getSize() + "' maxlength='" + gerencialParameterDto.getSize() + "' name='PARAM."
                    + gerencialParameterDto.getName() + "' id='PARAM." + gerencialParameterDto.getName() + "'";
            if (!reload) {
                strRetorno += " value='" + gerencialParameterDto.getDefaultValue() + "' ";
            }
            strRetorno += " class='Format[Date] Description[" + gerencialParameterDto.getDescription() + "] Valid[" + strValid + "] datepicker' ";

            if (gerencialParameterDto.isReload()) {
                // strRetorno += " onblur='recarrega(this)'";
            }

            if (reload) {
                final String value = (String) hashParametros.get("PARAM." + gerencialParameterDto.getName());
                if (value != null) {
                    strRetorno += " value='" + value + "'";
                }
            }

            strRetorno += "/>";
        }

        // Campo Inteiro - Nao ha casas decimais
        if (gerencialParameterDto.getType().equalsIgnoreCase("java.lang.Integer")) {
            if (gerencialParameterDto.getTypeHTML() == null || gerencialParameterDto.getTypeHTML().equalsIgnoreCase("")
                    || gerencialParameterDto.getTypeHTML().equalsIgnoreCase("text")) {
                String strValidCompleta = "";

                if (!strValid.trim().equalsIgnoreCase("")) {
                    strValidCompleta = " Valid[" + strValid + "]";
                }
                strRetorno += "<input type='text' size='" + gerencialParameterDto.getSize() + "' maxlength='" + gerencialParameterDto.getSize()
                        + "' name='PARAM." + gerencialParameterDto.getName() + "' id='PARAM." + gerencialParameterDto.getName() + "'";
                if (!reload) {
                    strRetorno += " value='" + gerencialParameterDto.getDefaultValue() + "' ";
                }
                strRetorno += " class='Format[Numero] Description[" + gerencialParameterDto.getDescription() + "]" + strValidCompleta + "'";

                if (gerencialParameterDto.isReload()) {
                    // strRetorno += " onblur='recarrega(this)'";
                }

                if (reload) {
                    final String value = (String) hashParametros.get("PARAM." + gerencialParameterDto.getName());
                    if (value != null) {
                        strRetorno += " value='" + value + "'";
                    }
                }

                strRetorno += "/>";
            }
        }

        // Campo Duplo - Com casas decimais
        if (gerencialParameterDto.getType().equalsIgnoreCase("java.lang.Double")) {
            String strValidCompleta = "";

            if (!strValid.trim().equalsIgnoreCase("")) {
                strValidCompleta = " Valid[" + strValid + "]";
            }
            strRetorno += "<input type='text' size='" + gerencialParameterDto.getSize() + "' maxlength='" + gerencialParameterDto.getSize() + "' name='PARAM."
                    + gerencialParameterDto.getName() + "' id='PARAM." + gerencialParameterDto.getName() + "'";
            if (!reload) {
                strRetorno += " value='" + gerencialParameterDto.getDefaultValue() + "' ";
            }
            strRetorno += " class='Format[Money] Description[" + gerencialParameterDto.getDescription() + "]" + strValidCompleta + "'";

            if (gerencialParameterDto.isReload()) {
                // strRetorno += " onblur='recarrega(this)'";
            }

            if (reload) {
                final String value = (String) hashParametros.get("PARAM." + gerencialParameterDto.getName());
                if (value != null) {
                    strRetorno += " value='" + value + "'";
                }
            }

            strRetorno += "/>";
        }

        // Campo String
        if (gerencialParameterDto.getType().equalsIgnoreCase("java.lang.String")) {
            if (gerencialParameterDto.getTypeHTML() == null || gerencialParameterDto.getTypeHTML().equalsIgnoreCase("")
                    || gerencialParameterDto.getTypeHTML().equalsIgnoreCase("text")) {
                String strValidCompleta = "";

                if (!strValid.trim().equalsIgnoreCase("")) {
                    strValidCompleta = " Valid[" + strValid + "]";
                }
                strRetorno += "<input type='text' size='" + gerencialParameterDto.getSize() + "' maxlength='" + gerencialParameterDto.getSize()
                        + "' name='PARAM." + gerencialParameterDto.getName() + "' id='PARAM." + gerencialParameterDto.getName() + "'";
                if (!reload) {
                    strRetorno += " value='" + gerencialParameterDto.getDefaultValue() + "' ";
                }
                strRetorno += " class='Description[" + gerencialParameterDto.getDescription() + "]" + strValidCompleta + "'";

                if (gerencialParameterDto.isReload()) {
                    // strRetorno += " onblur='recarrega(this)'";
                }

                if (reload) {
                    final String value = (String) hashParametros.get("PARAM." + gerencialParameterDto.getName());
                    if (value != null) {
                        strRetorno += " value='" + value + "'";
                    }
                }

                strRetorno += "/>";
            }
        }
        // Campo StringBuilder
        if (gerencialParameterDto.getType().equalsIgnoreCase("java.lang.StringBuilder")) {
            if (gerencialParameterDto.getTypeHTML() == null || gerencialParameterDto.getTypeHTML().equalsIgnoreCase("")
                    || gerencialParameterDto.getTypeHTML().equalsIgnoreCase("text") || gerencialParameterDto.getTypeHTML().equalsIgnoreCase("textarea")) {
                String strValidCompleta = "";

                if (!strValid.trim().equalsIgnoreCase("")) {
                    strValidCompleta = " Valid[" + strValid + "]";
                }
                strRetorno += "<textarea rows='" + gerencialParameterDto.getSize() + "' cols='70' name='PARAM." + gerencialParameterDto.getName()
                        + "' id='PARAM." + gerencialParameterDto.getName() + "'";
                strRetorno += " class='Description[" + gerencialParameterDto.getDescription() + "]" + strValidCompleta + "'";

                if (gerencialParameterDto.isReload()) {
                    // strRetorno += " onblur='recarrega(this)'";
                }

                String value = "";
                if (reload) {
                    value = (String) hashParametros.get("PARAM." + gerencialParameterDto.getName());
                } else {
                    value = gerencialParameterDto.getDefaultValue();
                }

                strRetorno += ">";
                if (!reload) {
                    if (value == null) {
                        value = "";
                    }
                    strRetorno += value;
                }
                strRetorno += "</textarea>";
            }
        }

        return strRetorno;
    }

    public List getSubTreeParameters(final String parametros) {
        Document doc = null;
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            if (parametros == null) {
                return null;
            }
            final InputStream is = new ByteArrayInputStream(parametros.getBytes());
            doc = builder.parse(is);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
        if (doc == null) {
            return null;
        }
        final Node noItem = doc.getChildNodes().item(0);
        if (noItem == null) {
            return null;
        }
        final List colParameters = new ArrayList();
        GerencialParameterDTO gerencialParameter;
        if (noItem.getChildNodes() != null) {
            for (int i = 0; i < noItem.getChildNodes().getLength(); i++) {
                final Node noSubItem = noItem.getChildNodes().item(i);
                if (noSubItem.getNodeName().equals("#text")) {
                    continue;
                }
                if (noSubItem.getNodeName().equals("#comment")) {
                    continue;
                }

                if (noSubItem.getNodeName().equalsIgnoreCase("PARAM")) {
                    gerencialParameter = new GerencialParameterDTO();

                    final NamedNodeMap map = noSubItem.getAttributes();

                    gerencialParameter.setType(map.getNamedItem("type").getNodeValue());
                    if (map.getNamedItem("typeHTML") != null) {
                        gerencialParameter.setTypeHTML(map.getNamedItem("typeHTML").getNodeValue());
                    }
                    gerencialParameter.setValue(map.getNamedItem("value").getNodeValue());
                    gerencialParameter.setName(map.getNamedItem("name").getNodeValue());
                    gerencialParameter.setDescription(map.getNamedItem("description").getNodeValue());

                    String size = map.getNamedItem("size").getNodeValue();
                    if (size == null || size.trim().equalsIgnoreCase("")) {
                        size = "0";
                    }
                    gerencialParameter.setSize(new Integer(Integer.parseInt(size)));

                    String defaultValue = null;
                    if (map.getNamedItem("default") != null) {
                        defaultValue = map.getNamedItem("default").getNodeValue();
                    }
                    if (defaultValue == null) {
                        defaultValue = "";
                    }
                    if (defaultValue.equalsIgnoreCase("{TODAY}") || defaultValue.equalsIgnoreCase("{DATAATUAL}")) {
                        defaultValue = UtilDatas.dateToSTR(UtilDatas.getDataAtual());
                    }
                    if (defaultValue.equalsIgnoreCase("{MESATUAL}")) {
                        defaultValue = "" + UtilDatas.getMonth(UtilDatas.getDataAtual());
                    }
                    if (defaultValue.equalsIgnoreCase("{ANOATUAL}")) {
                        defaultValue = "" + UtilDatas.getYear(UtilDatas.getDataAtual());
                    }
                    gerencialParameter.setDefaultValue(defaultValue);

                    gerencialParameter.setFix(Boolean.valueOf(map.getNamedItem("fix").getNodeValue()).booleanValue());
                    gerencialParameter.setMandatory(Boolean.valueOf(map.getNamedItem("mandatory").getNodeValue()).booleanValue());
                    if (map.getNamedItem("reload") != null) {
                        if (map.getNamedItem("reload").getNodeValue() != null && !map.getNamedItem("reload").getNodeValue().equalsIgnoreCase("")) {
                            gerencialParameter.setReload(Boolean.valueOf(map.getNamedItem("reload").getNodeValue()).booleanValue());
                        } else {
                            gerencialParameter.setReload(false);
                        }
                    } else {
                        gerencialParameter.setReload(false);
                    }

                    if ("select".equalsIgnoreCase(gerencialParameter.getTypeHTML()) || "checkbox".equalsIgnoreCase(gerencialParameter.getTypeHTML())
                            || "radio".equalsIgnoreCase(gerencialParameter.getTypeHTML())) {
                        gerencialParameter.setColOptions(this.getSubTreeOptions(noSubItem));
                    }

                    colParameters.add(gerencialParameter);
                }
            }
        }
        return colParameters;
    }

    public Collection getSubTreeOptions(final Node noItem) {
        if (noItem == null) {
            return null;
        }

        final Collection colRetorno = new ArrayList();
        if (noItem.getChildNodes() != null) {
            for (int i = 0; i < noItem.getChildNodes().getLength(); i++) {
                final Node noSubItem = noItem.getChildNodes().item(i);
                if (noSubItem.getNodeName().equals("#text")) {
                    continue;
                }
                if (noSubItem.getNodeName().equals("#comment")) {
                    continue;
                }

                if (noSubItem.getNodeName().equalsIgnoreCase("OPTION")) {
                    final NamedNodeMap map = noSubItem.getAttributes();

                    final GerencialOptionDTO gerencialOptionDTO = new GerencialOptionDTO();
                    gerencialOptionDTO.setValue(map.getNamedItem("value").getNodeValue());
                    gerencialOptionDTO.setText(map.getNamedItem("text").getNodeValue());

                    colRetorno.add(gerencialOptionDTO);
                }

                if (noSubItem.getNodeName().equalsIgnoreCase("OPTIONS")) {
                    final NamedNodeMap map = noSubItem.getAttributes();

                    final GerencialOptionsDTO gerencialOptionsDTO = new GerencialOptionsDTO();
                    final String onLoad = UtilStrings.nullToVazio(map.getNamedItem("onload").getNodeValue());
                    if (onLoad.equalsIgnoreCase("true")) {
                        gerencialOptionsDTO.setOnload(true);
                    } else {
                        gerencialOptionsDTO.setOnload(false);
                    }
                    gerencialOptionsDTO.setType(UtilStrings.nullToVazio(map.getNamedItem("type").getNodeValue()));
                    if (gerencialOptionsDTO.getType().equalsIgnoreCase("CLASS_GENERATE_SQL") || gerencialOptionsDTO.getType().equalsIgnoreCase("SERVICE")) {
                        gerencialOptionsDTO.setClassExecute(UtilStrings.nullToVazio(noSubItem.getChildNodes().item(0).getNodeValue()).trim());
                    } else {
                        gerencialOptionsDTO.setType("SQL");
                        gerencialOptionsDTO.setSql(noSubItem.getChildNodes().item(0).getNodeValue());
                    }

                    colRetorno.add(gerencialOptionsDTO);
                }
            }
        }
        return colRetorno;
    }

    public HashMap getParametrosInformados(final HttpServletRequest request) {
        final Enumeration x = request.getParameterNames();
        final HashMap hashRetorno = new HashMap();
        String[] aux;
        while (x.hasMoreElements()) {
            final String nameElement = (String) x.nextElement();

            // System.out.println("Parametro vindo no request: " + nameElement + "    ---> Valor: " + request.getParameter(nameElement));

            if (nameElement.startsWith("PARAM.")) {
                final String[] strValores = request.getParameterValues(nameElement);
                if (strValores.length == 0 || strValores.length == 1) {
                    final String value = request.getParameter(nameElement);
                    hashRetorno.put(nameElement, value);
                } else {
                    aux = new String[strValores.length];
                    for (int i = 0; i < strValores.length; i++) {
                        aux[i] = strValores[i];
                    }
                    hashRetorno.put(nameElement, aux);
                }
            }
        }

        final Usuario user = WebUtilGerencial.getUsuario(request);
        hashRetorno.put("USER", user);

        return hashRetorno;
    }

    public static final String encode(final String string) {
        if (string == null) {
            return "";
        }
        char c;
        final int length = string.length();
        final StringBuffer encoded = new StringBuffer(2 * length);
        for (int i = 0; i < length; i++) {
            c = string.charAt(i);
            switch (c) {
            case '¹':
                encoded.append("&sup1;");
                break;
            case '²':
                encoded.append("&sup2;");
                break;
            case '³':
                encoded.append("&sup3;");
                break;
            case 'º':
                encoded.append("&ordm;");
                break;
            case '°':
                encoded.append("&deg;");
                break;
            case 'ç':
                encoded.append("&ccedil;");
                break;
            case 'Ç':
                encoded.append("&Ccedil;");
                break;
            case 'ª':
                encoded.append("<sup>a</sup>");
                break;
            case 'á':
            case 'Á':
            case 'é':
            case 'É':
            case 'í':
            case 'Í':
            case 'ó':
            case 'Ó':
            case 'ú':
            case 'Ú':
                encoded.append("&" + getLetraCorrespondente(c) + "acute;");
                break;
            case 'â':
            case 'Â':
            case 'ê':
            case 'Ê':
            case 'î':
            case 'Î':
            case 'ô':
            case 'Ô':
            case 'û':
            case 'Û':
                encoded.append("&" + getLetraCorrespondente(c) + "circ;");
                break;
            case 'ã':
            case 'Ã':
            case 'õ':
            case 'Õ':
                encoded.append("&" + getLetraCorrespondente(c) + "tilde;");
                break;
            case 'à':
            case 'À':
            case 'è':
            case 'È':
            case 'ì':
            case 'Ì':
            case 'ò':
            case 'Ò':
            case 'ù':
            case 'Ù':
                encoded.append("&" + getLetraCorrespondente(c) + "grave;");
                break;
            default:
                encoded.append(c);
                break;
            }
        }

        final String strRet = encoded.toString();

        return strRet;
    }

    public static String getLetraCorrespondente(final char c) {
        if (c == 'á' || c == 'â' || c == 'ã') {
            return "a";
        }
        if (c == 'à') {
            return "a";
        } else if (c == 'Á' || c == 'Â' || c == 'Ã') {
            return "A";
        } else if (c == 'À') {
            return "A";
        } else if (c == 'é' || c == 'ê' || c == 'è') {
            return "e";
        } else if (c == 'É' || c == 'Ê' || c == 'È') {
            return "E";
        } else if (c == 'í' || c == 'î' || c == 'ì') {
            return "i";
        } else if (c == 'Í' || c == 'Î' || c == 'Ì') {
            return "I";
        } else if (c == 'ó' || c == 'ô' || c == 'õ' || c == 'ò') {
            return "o";
        } else if (c == 'Ó' || c == 'Ô' || c == 'Õ' || c == 'Ò') {
            return "O";
        } else if (c == 'ú' || c == 'û' || c == 'ù') {
            return "u";
        } else if (c == 'Ú' || c == 'Û' || c == 'Ù') {
            return "U";
        } else {
            final char auxChar[] = new char[1];
            auxChar[0] = c;
            final String aux = new String(auxChar);
            return aux;
        }
    }
}
