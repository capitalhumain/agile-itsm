package br.com.centralit.citgerencial.ajaxForms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;

import br.com.centralit.citajax.html.AjaxFormAction;
import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citajax.html.HTMLElement;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.centralit.citcorpore.util.WebUtil;
import br.com.centralit.citgerencial.bean.GerencialGraphInformationDTO;
import br.com.centralit.citgerencial.bean.GerencialInfoGenerateDTO;
import br.com.centralit.citgerencial.bean.GerencialItemInformationDTO;
import br.com.centralit.citgerencial.bean.GerencialItemPainelDTO;
import br.com.centralit.citgerencial.bean.GerencialOptionDTO;
import br.com.centralit.citgerencial.bean.GerencialOptionsDTO;
import br.com.centralit.citgerencial.bean.GerencialPainelDTO;
import br.com.centralit.citgerencial.bean.GerencialParameterDTO;
import br.com.centralit.citgerencial.config.GerencialGrupoPainelConfig;
import br.com.centralit.citgerencial.config.GerencialItemInformationConfig;
import br.com.centralit.citgerencial.config.GerencialPainelConfig;
import br.com.centralit.citgerencial.negocio.GerencialGenerate;
import br.com.centralit.citgerencial.util.WebUtilGerencial;
import br.com.citframework.dto.Usuario;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilI18N;
import br.com.citframework.util.UtilStrings;
import br.com.citframework.util.UtilTratamentoArquivos;

public class Painel extends AjaxFormAction {

    private static final Logger LOGGER = Logger.getLogger(Painel.class);

    @Override
    public Class<GerencialPainelDTO> getBeanClass() {
        return GerencialPainelDTO.class;
    }

    @Override
    public void load(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usrDto = WebUtil.getUsuario(request);
        if (usrDto == null) {
            return;
        }
        final HTMLElement divUser = document.getElementById("usuarioRel");
        divUser.setInnerHTML(usrDto.getNomeUsuario());

    }

    /*
     * Faz o carregamento do menu no event de mudança da combo
     */
    public void changeCombo(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        LOGGER.debug(UtilI18N.internacionaliza(request, "painel.loadPainelGerencial"));

        final GerencialPainelDTO gerencialPainel = (GerencialPainelDTO) document.getBean();

        Collection colPaineis = null;
        if (gerencialPainel.getFileNameGrupo() == null || gerencialPainel.getFileNameGrupo().trim().equalsIgnoreCase("")) {
            colPaineis = new ArrayList<>();
        } else {
            colPaineis = GerencialGrupoPainelConfig.getItensGrupo(gerencialPainel.getFileNameGrupo());
        }

        final StringBuilder html = new StringBuilder();
        html.append("<div id='tblPaineis'>");
        if (!colPaineis.isEmpty()) {
            int i = 1;
            for (final Iterator it = colPaineis.iterator(); it.hasNext();) {
                final GerencialPainelDTO painel = (GerencialPainelDTO) it.next();
                html.append("<a class='sJcFJd' id='nav-0" + i + "' onclick='atualizaPainel(\"" + painel.getFileName() + "\", this.id)'"
                        + " href='javascript:;'><div class='CpzCDd'>" + UtilI18N.internacionaliza(request, painel.getDescription()) + "</div></a>");

                document.executeScript("atualizaPainel(" + painel.getFileName() + ", this)");
                i++;
            }
        }
        html.append("</div>");

        final HTMLElement divUser = document.getElementById("divLista");
        divUser.setInnerHTML(html + "");
    }

    public void geraPainel(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final Usuario user = WebUtilGerencial.getUsuario(request);
        if (user == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
            return;
        }

        if (user.getIdUsuario() == null) {
            user.setIdUsuario("1");
        }

        final GerencialPainelDTO gerencialDto = (GerencialPainelDTO) document.getBean();
        GerencialPainelDTO gerencialPainelDto = null;
        gerencialPainelDto = GerencialPainelConfig.getInstance(gerencialDto.getFileName());

        final HashMap hashParametros = this.getParametrosInformados(request);

        final String dataInicial = (String) hashParametros.get("PARAM.dataInicial");
        final String dataFinal = (String) hashParametros.get("PARAM.dataFinal");

        /* Visualização da data no painel principal */
        if (dataInicial != null || dataFinal != null) {
            final HTMLElement parametros = document.getElementById("parametros");
            parametros.setInnerHTML("<h2>" + UtilI18N.internacionaliza(request, "citcorpore.comum.datainicio") + " - "
                    + UtilI18N.internacionaliza(request, "citcorpore.comum.datafim") + "</h2>");
        }

        if (!gerencialDto.isParametersPreenchidos() && gerencialPainelDto.getListParameters() != null && gerencialPainelDto.getListParameters().size() > 0) {
            final HTMLElement divParametros = document.getElementById("divParametros");
            divParametros.setInnerHTML(this.geraParametrosPainel(gerencialPainelDto, hashParametros, user, true, request));
            document.executeScript("DEFINEALLPAGES_atribuiCaracteristicasCitAjax()");
            document.executeScript("HTMLUtils.focusInFirstActivateField(document.formParametros)");
            document.executeScript("pageLoad();");
            document.executeScript("$('#POPUP_PARAM').dialog('open')");
            return;
        }

        // Limpando a estrutura
        document.executeScript("document.getElementById('divPainel').innerHTML = '';");

        final String caminhoGraficos = request.getSession().getServletContext().getRealPath("/tempFiles");
        final String caminhoPdfs = request.getSession().getServletContext().getRealPath("/tempFiles");

        if (gerencialPainelDto.getListItens() != null) {
            final Collection<GerencialItemPainelDTO> colItens = gerencialPainelDto.getListItens();
            int i = 0;
            for (final GerencialItemPainelDTO gerencialItemPainelAuxDto : colItens) {
                document.executeScript("var divPainel = document.getElementById('divPainel')");

                document.executeScript("var div" + i + " = document.createElement('div')");

                /* Ferramentas de opções */
                document.executeScript("var internaTools" + i + " = document.createElement('div')");
                document.executeScript("internaTools" + i + ".id = 'internaTools" + i + "'");
                document.executeScript("internaTools" + i + ".className = 'internaTools'");
                /* Titulo */
                document.executeScript("var internaTitle" + i + " = document.createElement('div')");
                document.executeScript("internaTitle" + i + ".id = 'internaTitle" + i + "'");
                document.executeScript("internaTitle" + i + ".className = 'internaTitle'");
                /* Conteudo */
                document.executeScript("var interna" + i + " = document.createElement('div')");
                document.executeScript("interna" + i + ".id = 'interna" + i + "'");
                document.executeScript("interna" + i + ".className = 'interna'");

                GerencialItemInformationDTO gerencialItemInfDto = null;

                gerencialItemInfDto = GerencialItemInformationConfig.getInstance(gerencialItemPainelAuxDto.getFile(), request);
                if (gerencialItemInfDto != null && gerencialItemInfDto.getType().equalsIgnoreCase("JSP")) {
                    this.geraBotoesBarraFerramentasJSP(document, gerencialItemPainelAuxDto, request, "TABLE", i, gerencialItemInfDto, "");
                    document.executeScript("submitJSP('" + Constantes.getValue("SERVER_ADDRESS") + Constantes.getValue("CONTEXTO_APLICACAO")
                            + gerencialItemInfDto.getClassExecute() + "')");
                    return;
                }

                final GerencialGenerate gerencialGenerateService = (GerencialGenerate) ServiceLocator.getInstance().getService(GerencialGenerate.class,
                        WebUtil.getUsuarioSistema(request));

                final GerencialInfoGenerateDTO infoGenerate = new GerencialInfoGenerateDTO();
                infoGenerate.setHashParametros(hashParametros);

                infoGenerate.setCaminhoArquivosGraficos(caminhoGraficos);
                infoGenerate.setCaminhoArquivosPdfs(caminhoPdfs);

                String tipoSaida = (String) request.getSession(true).getAttribute(gerencialItemPainelAuxDto.getFile());
                if (gerencialDto.getGerarExcel() != null && gerencialDto.getGerarExcel().equalsIgnoreCase("S")) {
                    tipoSaida = "EXCEL";
                }

                String graphType = "";
                if (gerencialDto.getGerarExcel() == null || gerencialDto.getGerarExcel().equalsIgnoreCase("")
                        || gerencialDto.getGerarExcel().equalsIgnoreCase("N")) {
                    if (tipoSaida == null || tipoSaida.trim().equalsIgnoreCase("")) {
                        if (gerencialItemInfDto.getDefaultVisualization().equalsIgnoreCase("T")) {
                            tipoSaida = "TABLE";
                        }
                        if (gerencialItemInfDto.getDefaultVisualization().equalsIgnoreCase("P")) {
                            tipoSaida = "PDF";
                        } else if (gerencialItemInfDto.getDefaultVisualization().substring(0, 1).equalsIgnoreCase("G")) {
                            tipoSaida = "GRAPH";
                            try {
                                graphType = gerencialItemInfDto.getDefaultVisualization().substring(2);
                            } catch (final Exception e) {
                                //
                            }
                        }
                    } else {
                        if (tipoSaida.equalsIgnoreCase("T")) {
                            tipoSaida = "TABLE";
                        }
                        if (tipoSaida.equalsIgnoreCase("P")) {
                            tipoSaida = "PDF";
                        } else if (tipoSaida.equalsIgnoreCase("G")) {
                            try {
                                graphType = gerencialItemInfDto.getDefaultVisualization().substring(2);
                            } catch (final Exception e) {}
                            tipoSaida = "GRAPH";
                        } else if (tipoSaida.substring(0, 1).equalsIgnoreCase("G")) {
                            try {
                                graphType = tipoSaida.substring(2);
                            } catch (final Exception e) {}
                            tipoSaida = "GRAPH";
                        }
                    }
                }

                // ACRESCENTADO POR EMAURI - 02/12
                request.getSession(true).setAttribute("FILE_NAME_GERENCIAL_PDF", gerencialItemPainelAuxDto.getFile());
                // fim - ACRESCENTADO POR EMAURI - 02/12

                infoGenerate.setSaida(tipoSaida);
                if (tipoSaida.equalsIgnoreCase("GRAPH")) {
                    if (gerencialItemInfDto.getDefaultVisualization().length() <= 1) {
                        document.alert(UtilI18N.internacionaliza(request, "painel.estaConsultaNaoPodeterGraficoGerado"));
                        tipoSaida = "TABLE";
                        infoGenerate.setSaida(tipoSaida);

                        request.getSession(true).setAttribute(gerencialDto.getFileNameItem(), tipoSaida);
                    } else {
                        if (graphType.equalsIgnoreCase("")) { // Se nao foi especificado o Grafico, entao pega do configuracao.
                            infoGenerate.setGraphType(gerencialItemInfDto.getDefaultVisualization().substring(2));
                        } else {
                            infoGenerate.setGraphType(graphType);
                        }
                    }
                }

                String retorno = "";
                if (tipoSaida.equalsIgnoreCase("PDF")) {
                    request.getSession(true).setAttribute("FILE_NAME_GERENCIAL_PDF", gerencialItemPainelAuxDto.getFile());
                    this.geraPDF(document, request, response);
                } else if (tipoSaida.equalsIgnoreCase("EXCEL")) {
                    this.geraExcel(document, request, response);
                    document.executeScript("hideAguarde()");
                    return;
                } else {
                    retorno = (String) gerencialGenerateService.generate(gerencialItemInfDto, user, infoGenerate, gerencialItemPainelAuxDto,
                            gerencialPainelDto, request);
                    if (retorno == null) {
                        retorno = (String) gerencialGenerateService.geraTabelaVazia(infoGenerate, request);
                    }
                }

                this.geraBotoesBarraFerramentas(document, gerencialItemPainelAuxDto, request, tipoSaida, i, gerencialItemInfDto, retorno);

                i++;
            }
        }

        document.executeScript("document.getElementById('Throbber_2').style.visibility ='hidden';");
        document.executeScript("$('#POPUP_GRAFICO_OPC').dialog('close');");
        document.executeScript("JANELA_AGUARDE_MENU.hide()");
    }

    public void geraBotoesBarraFerramentas(final DocumentHTML document, final GerencialItemPainelDTO gerencialItemPainelAuxDto,
            final HttpServletRequest request, final String tipoSaida, final int i, final GerencialItemInformationDTO gerencialItemInfDto, final String retorno)
            throws Exception {

        /*
         * Desenvolvedor: Rodrigo Pecci - Data: 31/10/2013 - Horário: 11h07min - ID Citsmart: 120770
         * Motivo/Comentário: A largura do gráfico gerado estava ultrapassando a largura da tabela. Foi corrigido para pegar a largura correta.
         */
        document.executeScript("interna" + i + ".style.cssText  = 'width:" + gerencialItemPainelAuxDto.getWidth() + "px !important;height:"
                + gerencialItemPainelAuxDto.getHeigth() + "px !important;'");

        if ("TABLE".equalsIgnoreCase(tipoSaida)) {
            document.executeScript("interna" + i + ".style.overflow = 'auto'");
        }

        document.executeScript("div" + i + ".appendChild ( internaTools" + i + " )");
        document.executeScript("div" + i + ".appendChild ( internaTitle" + i + " )");
        document.executeScript("div" + i + ".appendChild ( interna" + i + " )");
        document.executeScript("div" + i + ".id ='miniPainel'");

        document.executeScript("divPainel.appendChild ( div" + i + " )");

        final HTMLElement internaTitle = document.getElementById("internaTitle" + i + "");
        internaTitle.setInnerHTML("<h2>" + UtilI18N.internacionaliza(request, gerencialItemInfDto.getTitle()) + "</h2>");

        final HTMLElement internaTools = document.getElementById("internaTools" + i + "");
        final String strBarraFerramentas = this.geraBarraFerramentas(gerencialItemInfDto, gerencialItemPainelAuxDto, tipoSaida, request);
        internaTools.setInnerHTML(strBarraFerramentas);

        final HTMLElement interna = document.getElementById("interna" + i + "");
        if (!gerencialItemInfDto.getType().equalsIgnoreCase("SERVICE_BUFFER")) {
            interna.setInnerHTML(StringEscapeUtils.unescapeHtml4(retorno));
        } else {
            interna.setInnerHTML(retorno);
        }
    }

    public void geraBotoesBarraFerramentasJSP(final DocumentHTML document, final GerencialItemPainelDTO gerencialItemPainelAuxDto,
            final HttpServletRequest request, final String tipoSaida, final int i, final GerencialItemInformationDTO gerencialItemInfDto, final String retorno)
            throws Exception {
        document.executeScript("interna" + i + ".style.width  = '" + gerencialItemPainelAuxDto.getWidth() + "px'");
        document.executeScript("interna" + i + ".style.height = '" + gerencialItemPainelAuxDto.getHeigth() + "px'");

        if ("TABLE".equalsIgnoreCase(tipoSaida)) {
            document.executeScript("interna" + i + ".style.overflow = 'auto'");
        }

        document.executeScript("div" + i + ".appendChild ( internaTools" + i + " )");
        document.executeScript("div" + i + ".appendChild ( internaTitle" + i + " )");
        document.executeScript("div" + i + ".appendChild ( interna" + i + " )");
        document.executeScript("div" + i + ".id ='miniPainel'");

        document.executeScript("divPainel.appendChild ( div" + i + " )");

        final HTMLElement internaTitle = document.getElementById("internaTitle" + i + "");
        internaTitle.setInnerHTML("<h2>" + gerencialItemInfDto.getTitle() + "</h2>");

        final HTMLElement internaTools = document.getElementById("internaTools" + i + "");
        final String strBarraFerramentas = this.geraBarraFerramentas(gerencialItemInfDto, gerencialItemPainelAuxDto, tipoSaida, request);
        internaTools.setInnerHTML(strBarraFerramentas);

        final HTMLElement interna = document.getElementById("interna" + i + "");
        if (!gerencialItemInfDto.getType().equalsIgnoreCase("SERVICE_BUFFER")) {
            interna.setInnerHTML(StringEscapeUtils.unescapeHtml4(retorno));
        } else {
            interna.setInnerHTML(retorno);
        }
    }

    public void geraPDF(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final Usuario user = WebUtilGerencial.getUsuario(request);
        if (user == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
            return;
        }
        final GerencialPainelDTO gerencialDto = (GerencialPainelDTO) document.getBean();
        GerencialPainelDTO gerencialPainelDto = null;
        gerencialPainelDto = GerencialPainelConfig.getInstance(gerencialDto.getFileName());

        String fileNameItem = gerencialDto.getFileNameItem();
        if (fileNameItem == null || fileNameItem.trim().equalsIgnoreCase("")) {
            fileNameItem = (String) request.getSession(true).getAttribute("FILE_NAME_GERENCIAL_PDF");
        }

        GerencialItemInformationDTO gerencialItemInfDto = null;
        gerencialItemInfDto = GerencialItemInformationConfig.getInstance(fileNameItem, request);

        final HashMap hashParametros = this.getParametrosInformados(request);

        final GerencialGenerate gerencialGenerateService = (GerencialGenerate) ServiceLocator.getInstance().getService(GerencialGenerate.class,
                WebUtil.getUsuarioSistema(request));

        final GerencialInfoGenerateDTO infoGenerate = new GerencialInfoGenerateDTO();
        infoGenerate.setTipoSaidaApresentada(gerencialDto.getTipoSaidaApresentada());
        infoGenerate.setSaida("PDF");

        final String caminhoGraficos = request.getSession().getServletContext().getRealPath("/tempFiles");
        final String caminhoPdfs = request.getSession().getServletContext().getRealPath("/tempFiles");

        infoGenerate.setCaminhoArquivosGraficos(caminhoGraficos);
        infoGenerate.setCaminhoArquivosPdfs(caminhoPdfs);

        if (gerencialDto.getTipoSaidaApresentada().equalsIgnoreCase("GRAPH")) {
            try {
                infoGenerate.setGraphType(gerencialItemInfDto.getDefaultVisualization().substring(2));
            } catch (final Exception e) {}

            final String tipoSaida = (String) request.getSession(true).getAttribute(gerencialDto.getFileNameItem());

            String graphType = "";
            if (tipoSaida != null && !tipoSaida.trim().equalsIgnoreCase("")) {
                if (tipoSaida.substring(0, 1).equalsIgnoreCase("G")) {
                    try {
                        graphType = tipoSaida.substring(2);
                    } catch (final Exception e) {}

                    infoGenerate.setGraphType(graphType);
                }
            }
        }

        GerencialItemPainelDTO gerencialItemPainel = null;
        if (gerencialPainelDto.getListItens() != null) {
            // int i = 0;
            for (final Iterator it = gerencialPainelDto.getListItens().iterator(); it.hasNext();) {
                final GerencialItemPainelDTO gerencialItemPainelAuxDto = (GerencialItemPainelDTO) it.next();
                if (gerencialItemPainelAuxDto.getFile().equalsIgnoreCase(gerencialDto.getFileNameItem())) {
                    gerencialItemPainel = gerencialItemPainelAuxDto;
                }
            }
        }

        infoGenerate.setHashParametros(hashParametros);

        // Internacionaliza os GerencialOptionDTO nos parâmentros
        for (final Object parameter : gerencialPainelDto.getListParameters()) {
            if (parameter instanceof GerencialParameterDTO && ((GerencialParameterDTO) parameter).getColOptions() != null) {
                for (final Object objeto : ((GerencialParameterDTO) parameter).getColOptions()) {
                    if (objeto instanceof GerencialOptionDTO) {
                        final GerencialOptionDTO option = (GerencialOptionDTO) objeto;
                        option.setText(UtilI18N.internacionaliza(request, option.getText()));
                    }
                }
            }
        }

        final String retorno = (String) gerencialGenerateService.generate(gerencialItemInfDto, user, infoGenerate, gerencialItemPainel, gerencialPainelDto,
                request);
        if (retorno != null && retorno != "") {
            document.executeScript("window.open('" + retorno + "')");
        } else {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.relatorioVazio"));
        }
        document.executeScript("JANELA_AGUARDE_MENU.hide()");
    }

    public void getFileExcel(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final GerencialPainelDTO gerencialDto = (GerencialPainelDTO) document.getBean();
        if (gerencialDto.getFileNameExcel() != null) {
            System.out.println(">>> Arquivo Excel: " + gerencialDto.getFileNameExcel());
            System.out.println(">>> Arquivo Excel (curto): " + gerencialDto.getFileNameExcelShort());
            final File arquivo = new File(gerencialDto.getFileNameExcel());

            final byte[] buffer = UtilTratamentoArquivos.getBytesFromFile(arquivo);
            response.setContentType("application/vnd.ms-excel");
            if (gerencialDto.getFileNameExcelShort() == null || gerencialDto.getFileNameExcelShort().trim().equalsIgnoreCase("")) {
                gerencialDto.setFileNameExcelShort("relatorio-excel.xls");
            }
            response.setHeader("Content-Disposition", "attachment; filename=" + gerencialDto.getFileNameExcelShort());
            final ServletOutputStream outputStream = response.getOutputStream();
            if (buffer != null) {
                response.setContentLength(buffer.length);
            } else {
                System.out.println(">>> CITGERENCIAL -> Buffer null ");
                System.out.println(">>> Arquivo Excel: " + gerencialDto.getFileNameExcel());
                System.out.println(">>> Arquivo Excel (curto): " + gerencialDto.getFileNameExcelShort());
            }
            if (buffer != null) {
                outputStream.write(buffer);
            }
            outputStream.flush();
            outputStream.close();
        }
    }

    public void geraExcel(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final Usuario user = WebUtilGerencial.getUsuario(request);
        if (user == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
            return;
        }
        final GerencialPainelDTO gerencialDto = (GerencialPainelDTO) document.getBean();
        GerencialPainelDTO gerencialPainelDto = null;
        gerencialPainelDto = GerencialPainelConfig.getInstance(gerencialDto.getFileName());

        String fileNameItem = gerencialDto.getFileNameItem();
        if (fileNameItem == null || fileNameItem.trim().equalsIgnoreCase("")) {
            fileNameItem = (String) request.getSession(true).getAttribute("FILE_NAME_GERENCIAL_PDF");
        }

        GerencialItemInformationDTO gerencialItemInfDto = null;
        gerencialItemInfDto = GerencialItemInformationConfig.getInstance(fileNameItem, request);

        final HashMap hashParametros = this.getParametrosInformados(request);

        final GerencialGenerate gerencialGenerateService = (GerencialGenerate) ServiceLocator.getInstance().getService(GerencialGenerate.class,
                WebUtil.getUsuarioSistema(request));

        final GerencialInfoGenerateDTO infoGenerate = new GerencialInfoGenerateDTO();
        infoGenerate.setTipoSaidaApresentada(gerencialDto.getTipoSaidaApresentada());
        infoGenerate.setSaida("TABLE");

        ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.URL_Sistema, "");
        final String caminhoGraficos = request.getSession().getServletContext().getRealPath("/tempFiles");
        final String caminhoPdfs = request.getSession().getServletContext().getRealPath("/tempFiles");

        infoGenerate.setCaminhoArquivosGraficos(caminhoGraficos);
        infoGenerate.setCaminhoArquivosPdfs(caminhoPdfs);

        if (gerencialDto.getTipoSaidaApresentada().equalsIgnoreCase("GRAPH")) {
            try {
                infoGenerate.setGraphType(gerencialItemInfDto.getDefaultVisualization().substring(2));
            } catch (final Exception e) {}

            final String tipoSaida = (String) request.getSession(true).getAttribute(gerencialDto.getFileNameItem());

            String graphType = "";
            if (tipoSaida != null && !tipoSaida.trim().equalsIgnoreCase("")) {
                if (tipoSaida.substring(0, 1).equalsIgnoreCase("G")) {
                    try {
                        graphType = tipoSaida.substring(2);
                    } catch (final Exception e) {}

                    infoGenerate.setGraphType(graphType);
                }
            }
        }

        GerencialItemPainelDTO gerencialItemPainel = null;
        if (gerencialPainelDto.getListItens() != null) {
            // int i = 0;
            for (final Iterator it = gerencialPainelDto.getListItens().iterator(); it.hasNext();) {
                final GerencialItemPainelDTO gerencialItemPainelAuxDto = (GerencialItemPainelDTO) it.next();
                if (gerencialItemPainelAuxDto.getFile().equalsIgnoreCase(gerencialDto.getFileNameItem())) {
                    gerencialItemPainel = gerencialItemPainelAuxDto;
                }
            }
        }

        infoGenerate.setHashParametros(hashParametros);

        final String retorno = (String) gerencialGenerateService.generate(gerencialItemInfDto, user, infoGenerate, gerencialItemPainel, gerencialPainelDto,
                request);
        if (retorno != null && retorno != "") {
            String caminho = "";
            try {
                File arquivo = new File(caminhoGraficos);
                if (!arquivo.exists()) {
                    arquivo.mkdirs();
                }
                final File arquivoVer = new File(caminhoGraficos + "/" + user.getIdUsuario());
                if (!arquivoVer.exists()) {
                    arquivoVer.mkdirs();
                }
                caminho = caminhoGraficos + "/" + user.getIdUsuario() + "/"
                        + UtilStrings.generateNomeBusca(UtilStrings.removeCaracteresEspeciais(gerencialItemInfDto.getDescription())) + ".xls";
                caminho = caminho.replaceAll("\\\\", "/");
                arquivo = new File(caminho);
                if (arquivo.exists()) {
                    arquivo.delete();
                }

                BufferedWriter out = null;
                try {
                    /**
                     * Alterado codificação do Writer para ANSI
                     * 
                     * @author thyen.chang
                     *         17/12/2014
                     */
                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "Cp1252"));
                } catch (final Exception e) {
                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo)));
                    e.printStackTrace();
                }

                if (!gerencialItemInfDto.getType().equalsIgnoreCase("SERVICE_BUFFER")) {
                    try {
                        out.write(retorno);
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    out.write(retorno);
                }
                out.flush();
                out.close();
                out = null;
                arquivo = null;

                document.executeScript("getFileExcel('" + caminho + "','"
                        + UtilStrings.generateNomeBusca(UtilStrings.removeCaracteresEspeciais(gerencialItemInfDto.getDescription())) + ".xls')");
                document.executeScript("JANELA_AGUARDE_MENU.hide()");
            } catch (final Exception e) {
                e.printStackTrace();
                document.executeScript("hideAguarde()");
            }
        } else {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.relatorioVazio"));
            document.executeScript("JANELA_AGUARDE_MENU.hide()");
        }
    }

    private String geraBarraFerramentas(final GerencialItemInformationDTO gerencialItemInfDto, final GerencialItemPainelDTO gerencialItemPainelAuxDto,
            final String tipoSaidaApresentada, final HttpServletRequest request) {
        String strOpcoes = "";

        strOpcoes += "<table>";
        strOpcoes += "<tr>";
        if (gerencialItemInfDto.isReport()) {
            strOpcoes += "<td>";
            strOpcoes += "<img src=\"" + Constantes.getValue("SERVER_ADDRESS") + Constantes.getValue("CONTEXTO_APLICACAO")
                    + "/produtos/citgerencial/imagens/grid.gif\" style=\"cursor:pointer\" title='"
                    + UtilI18N.internacionaliza(request, "citcorpore.comum.tabela") + "' onclick='setaTipoSaida(\"" + gerencialItemPainelAuxDto.getFile()
                    + "\", \"T\")' />";
            strOpcoes += "</td>";
        }
        if (gerencialItemInfDto.isGraph()) {
            String graficosTipos = "";
            if (gerencialItemInfDto.getListGraphs() != null) {
                for (final Iterator it = gerencialItemInfDto.getListGraphs().iterator(); it.hasNext();) {
                    final GerencialGraphInformationDTO gerencialGraph = (GerencialGraphInformationDTO) it.next();
                    if (!graficosTipos.equalsIgnoreCase("")) {
                        graficosTipos += ";";
                    }
                    graficosTipos += gerencialGraph.getType();
                }
            }

            strOpcoes += "<td>";
            strOpcoes += "<img src=\"" + Constantes.getValue("SERVER_ADDRESS") + Constantes.getValue("CONTEXTO_APLICACAO")
                    + "/produtos/citgerencial/imagens/grafico.gif\" style=\"cursor:pointer\" title='"
                    + UtilI18N.internacionaliza(request, "citcorpore.comum.graficos") + "'  onclick='setaTipoGrafico(\"" + gerencialItemPainelAuxDto.getFile()
                    + "\", \"G\", \"" + graficosTipos + "\", \"" + UtilI18N.internacionaliza(request, gerencialItemInfDto.getTitle()) + "\")' />";
            strOpcoes += "</td>";
        }
        if (gerencialItemInfDto.isReport()) {
            strOpcoes += "<td>";
            strOpcoes += "<img src=\"" + Constantes.getValue("SERVER_ADDRESS") + Constantes.getValue("CONTEXTO_APLICACAO")
                    + "/produtos/citgerencial/imagens/documents.gif\" style=\"cursor:pointer\" title='"
                    + UtilI18N.internacionaliza(request, "painel.downloadDocumentoPDF") + "' onclick='geraPDF(\"" + gerencialItemPainelAuxDto.getFile()
                    + "\", \"" + tipoSaidaApresentada + "\")' />";
            strOpcoes += "</td>";
            strOpcoes += "<td>";
            strOpcoes += "<img src=\"" + Constantes.getValue("SERVER_ADDRESS") + Constantes.getValue("CONTEXTO_APLICACAO")
                    + "/produtos/citgerencial/imagens/excel.gif\" style=\"cursor:pointer\" title='"
                    + UtilI18N.internacionaliza(request, "painel.downloadDocumentoXLS") + "' onclick='geraExcel(\"" + gerencialItemPainelAuxDto.getFile()
                    + "\", \"" + tipoSaidaApresentada + "\")' />";
            strOpcoes += "</td>";
        }
        strOpcoes += "</tr>";
        strOpcoes += "<table>";

        return strOpcoes;
    }

    private String geraParametrosPainel(final GerencialPainelDTO gerencialPainelDto, final HashMap hashParametros, final Usuario user, final boolean reload,
            final HttpServletRequest request) throws ServiceException, Exception {
        String strRetorno = "<table>";
        for (final Iterator it = gerencialPainelDto.getListParameters().iterator(); it.hasNext();) {
            final GerencialParameterDTO gerencialParameterDto = (GerencialParameterDTO) it.next();

            strRetorno += "<tr>";
            strRetorno += "<td>";
            strRetorno += UtilI18N.internacionaliza(request, gerencialParameterDto.getDescription());
            if (gerencialParameterDto.isMandatory()) {
                strRetorno += "*";
            }
            strRetorno += "</td>";
            strRetorno += "<td>";
            strRetorno += this.geraCampo(gerencialParameterDto, gerencialPainelDto, hashParametros, user, reload, request);
            strRetorno += "</td>";
            strRetorno += "</tr>";
        }
        strRetorno += "</table>";

        return strRetorno;
    }

    private String geraCampo(final GerencialParameterDTO gerencialParameterDto, final GerencialPainelDTO gerencialPainelDto, final HashMap hashParametros,
            final Usuario user, final boolean reload, final HttpServletRequest request) throws ServiceException, Exception {
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

            if (gerencialParameterDto.isReload()) {}

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
                            final Collection col = gerencialGenerateService.executaSQLOptions(options, gerencialPainelDto, hashParametros, user);
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
                        strRetornoAux += "/>" + option.getText() + "<br/>";
                    }
                    if (GerencialOptionsDTO.class.isInstance(obj)) {
                        final GerencialOptionsDTO options = (GerencialOptionsDTO) obj;
                        if (options.isOnload() || reload) {
                            final GerencialGenerate gerencialGenerateService = (GerencialGenerate) ServiceLocator.getInstance().getService(
                                    GerencialGenerate.class, WebUtil.getUsuarioSistema(request));
                            final Collection col = gerencialGenerateService.executaSQLOptions(options, gerencialPainelDto, hashParametros, user);
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

    public HashMap getParametrosInformados(final HttpServletRequest request) {
        final Enumeration x = request.getParameterNames();
        final HashMap hashRetorno = new HashMap<>();
        String[] aux;
        while (x.hasMoreElements()) {
            final String nameElement = (String) x.nextElement();

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
        hashRetorno.put("citcorpore.comum.emissao", UtilI18N.internacionaliza(request, "citcorpore.comum.emissao"));
        hashRetorno.put("citcorpore.comum.pagina", UtilI18N.internacionaliza(request, "citcorpore.comum.pagina"));
        hashRetorno.put("grupovisao.contratos", UtilI18N.internacionaliza(request, "grupovisao.contratos"));
        hashRetorno.put("citcorpore.comum.temSLA", UtilI18N.internacionaliza(request, "citcorpore.comum.temSLA"));
        hashRetorno.put("citcorpore.comum.naoTemSLA", UtilI18N.internacionaliza(request, "citcorpore.comum.naoTemSLA"));
        hashRetorno.put("citcorpore.comum.numeroSolicitacoesIncidentes", UtilI18N.internacionaliza(request, "citcorpore.comum.numeroSolicitacoesIncidentes"));
        hashRetorno.put("citcorpore.comum.quantidadeDeOrigens", UtilI18N.internacionaliza(request, "citcorpore.comum.quantidadeDeOrigens"));
        hashRetorno.put("citcorpore.comum.quantidadeRegistros", UtilI18N.internacionaliza(request, "citcorpore.comum.quantidadeRegistros"));
        hashRetorno.put("citcorpore.comum.naoInformado", UtilI18N.internacionaliza(request, "citcorpore.comum.naoInformado"));
        hashRetorno.put("citcorpore.comum.todos", UtilI18N.internacionaliza(request, "citcorpore.comum.todos"));
        hashRetorno.put("citcorpore.comum.selecione", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));

        return hashRetorno;
    }

    public void atualizaTipoSaida(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final Usuario user = WebUtilGerencial.getUsuario(request);
        if (user == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
            return;
        }
        final GerencialPainelDTO gerencialDto = (GerencialPainelDTO) document.getBean();

        request.getSession(true).setAttribute(gerencialDto.getFileNameItem(), gerencialDto.getTipoSaida());

        document.executeScript("atualizaPainel(null,null)");
    }

    public void reloadParameters(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final Usuario user = WebUtilGerencial.getUsuario(request);
        if (user == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
            return;
        }
        final GerencialPainelDTO gerencialDto = (GerencialPainelDTO) document.getBean();
        GerencialPainelDTO gerencialPainelDto = null;
        gerencialPainelDto = GerencialPainelConfig.getInstance(gerencialDto.getFileName());

        final HashMap hashParametros = this.getParametrosInformados(request);

        if (!gerencialDto.isParametersPreenchidos() && gerencialPainelDto.getListParameters() != null && gerencialPainelDto.getListParameters().size() > 0) {
            final HTMLElement divParametros = document.getElementById("divParametros");
            divParametros.setInnerHTML(this.geraParametrosPainel(gerencialPainelDto, hashParametros, user, true, request));
            document.executeScript("DEFINEALLPAGES_atribuiCaracteristicasCitAjax()");
            document.executeScript("window.setTimeout(\"document.formParametros['" + gerencialDto.getCampo() + "'].focus()\", 3000)");

            return;
        }
    }

}
