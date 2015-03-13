package br.com.centralit.citgerencial.pdf;

import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.Enumerados.TipoDate;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.centralit.citcorpore.util.UtilImagem;
import br.com.centralit.citcorpore.util.WebUtil;
import br.com.centralit.citgerencial.bean.GerencialFieldDTO;
import br.com.centralit.citgerencial.bean.GerencialGroupDTO;
import br.com.centralit.citgerencial.bean.GerencialItemInformationDTO;
import br.com.centralit.citgerencial.bean.GerencialPainelDTO;
import br.com.centralit.citgerencial.bean.GerencialParameterDTO;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.Reflexao;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilI18N;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class EndPageGerencialControl extends PdfPageEventHelper {

    private final String titleReport;
    private final Collection colParmsUtilizadosNoSQL;
    private final HashMap hshParameters;
    private final Collection colDefinicaoParametros;
    private final GerencialPainelDTO gerencialPainelDto;
    private final GerencialItemInformationDTO gerencialItemDto;
    private final List listRetorno;
    private final int tamTabela;
    private final int[] tamanhoColunasReal;
    private final boolean existeAgrupador;
    private final HttpServletRequest request;

    public EndPageGerencialControl(final boolean existeAgrupadorParm, final GerencialItemInformationDTO gerencialItemDtoParm, final PdfPTable tableParm,
            final List listRetornoParm, final int tamTabelaParm, final int[] tamanhoColunasRealParm, final String titleReportParm,
            final Collection colParmsUtilizadosNoSQLParm, final HashMap hshParametersParm, final Collection colDefinicaoParametrosParm,
            final GerencialPainelDTO gerencialPainelDtoParm, final HttpServletRequest request) {
        titleReport = UtilI18N.internacionaliza(request, titleReportParm);
        colParmsUtilizadosNoSQL = colParmsUtilizadosNoSQLParm;
        hshParameters = hshParametersParm;
        colDefinicaoParametros = colDefinicaoParametrosParm;
        gerencialPainelDto = gerencialPainelDtoParm;
        gerencialItemDto = gerencialItemDtoParm;
        listRetorno = listRetornoParm;
        tamTabela = tamTabelaParm;
        tamanhoColunasReal = tamanhoColunasRealParm;
        existeAgrupador = existeAgrupadorParm;
        this.request = request;
    }

    @Override
    public void onStartPage(final PdfWriter writer, final Document document) {
        super.onStartPage(writer, document);
    }

    @Override
    public void onEndPage(final PdfWriter writer, final Document document) {
        try {
            /* Adicionado o header */
            final Rectangle page = document.getPageSize();
            final PdfPTable header = new PdfPTable(1);
            header.setTotalWidth(550);
            header.setLockedWidth(true);
            header.getDefaultCell().setFixedHeight(55);
            header.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);

            final PdfPCell cellC = new PdfPCell();
            cellC.setBorder(1);

            final PdfPTable tableContent = new PdfPTable(3);

            /* Adicionando a LogoMarca */
            URL url = null;
            String caminho = "";
            String urlInicial = "";
            Image image = null;
            caminho = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.URL_LOGO_PADRAO_RELATORIO, "");

            if ("".equals(caminho.trim()) || !UtilImagem.verificaSeImagemExiste(caminho)) {
                urlInicial = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.URL_Sistema, "");
                caminho = urlInicial + "/imagens/logo/logo.png";
            }

            try {
                url = new URL(caminho);
                final URLConnection conn = url.openConnection();
                conn.connect();
            } catch (final MalformedURLException e) {
                // the URL is not in a valid form
                e.printStackTrace();
                url = null;
                // throw new LogicException("Falha no estabelecimento de conexão com a url");
            } catch (final IOException e) {
                // the connection couldn't be established
                e.printStackTrace();
                url = null;
                // throw new LogicException("Falha no estabelecimento de conexão com a url");
            }

            if (url == null) {
                if (Constantes.getValue("CAMINHO_LOGO_CITGERENCIAL") != null) {
                    try {
                        url = new URL(Constantes.getValue("CAMINHO_LOGO_CITGERENCIAL"));
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if (url == null) {
                caminho = Constantes.getValue("SERVER_ADDRESS") + Constantes.getValue("CONTEXTO_APLICACAO") + "/imagens/logoPadraoRelatorio.png";
                try {
                    url = new URL(caminho);
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }

            if (url != null) {
                try {
                    image = Image.getInstance(url);
                } catch (final BadElementException e) {
                    e.printStackTrace();
                }
            }

            if (image != null) {
                image.scaleAbsolute(150, 50);
                image.setAlignment(Image.RIGHT);
                final Chunk ck = new Chunk(image, -3, -25);
                final PdfPCell cell = new PdfPCell();
                cell.addElement(ck);
                cell.setBorderWidth(0);
                cell.setRowspan(2);
                tableContent.addCell(cell);
            } else {
                tableContent.addCell("Citsmart");
            }

            final String strCab = Constantes.getValue("TEXTO_1a_LINHA_CABECALHO_CITGERENCIAL");
            if (strCab != null && !strCab.equalsIgnoreCase("")) {
                final PdfPCell cAux = new PdfPCell(new Phrase(strCab, new Font(Font.HELVETICA, 12, Font.BOLD, new Color(0, 0, 0))));
                cAux.setColspan(2);
                cAux.setBorderWidth(1);
                cAux.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                tableContent.addCell(cAux);
            }

            /* Adicionado o Titulo do relatório */
            final PdfPCell titulo = new PdfPCell(new Phrase(titleReport, new Font(Font.HELVETICA, 14, Font.BOLD, new Color(0, 0, 0))));
            titulo.setColspan(2);
            titulo.setRowspan(1);
            titulo.setBorderWidth(0);
            titulo.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            tableContent.addCell(titulo);

            /* Adicionado o filtro */
            String strFiltro = this.trataParameters(hshParameters, colParmsUtilizadosNoSQL, colDefinicaoParametros);
            if (strFiltro == null) {
                strFiltro = "";
            }
            final PdfPCell cFiltro = new PdfPCell(new Phrase(strFiltro, new Font(Font.HELVETICA, 8, Font.NORMAL, new Color(0, 0, 0))));
            cFiltro.setBorderWidth(0);
            cFiltro.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cFiltro.setColspan(2);
            tableContent.addCell(cFiltro);
            cellC.addElement(tableContent);
            header.addCell(tableContent);
            // header.writeSelectedRows(0, -1, 20, 805, writer.getDirectContent());

            // Fim - Trata parametros
            if (!existeAgrupador) {
                if (listRetorno != null && listRetorno.size() > 0) {
                    final Object[] row = (Object[]) listRetorno.get(0);
                    // if (!existeAgrupador){
                    this.geraCabecalhoPDF(row.length, gerencialItemDto, header, writer, document, page);
                    // }
                }
            }

            if (page.getWidth() > 650) {
                if (!existeAgrupador) {
                    // header.writeSelectedRows(0, -1, 20, page.getHeight() - document.topMargin() + header.getTotalHeight(), writer.getDirectContent());
                    header.writeSelectedRows(0, -1, 20, 565, writer.getDirectContent());
                } else {
                    header.writeSelectedRows(0, -1, 20, 585, writer.getDirectContent());
                }
            } else {
                if (!existeAgrupador) {
                    header.writeSelectedRows(0, -1, 20, page.getHeight() - document.topMargin() + header.getTotalHeight(), writer.getDirectContent());
                } else {
                    header.writeSelectedRows(0, -1, 20, 805, writer.getDirectContent());
                }
            }

            /* Adicionado o footer */
            final PdfPTable footer = new PdfPTable(2);
            final String emissao = (String) hshParameters.get("citcorpore.comum.emissao");
            final String pagina = (String) hshParameters.get("citcorpore.comum.pagina");

            PdfPCell cAuxPageNumber = new PdfPCell(new Phrase(emissao + ": "
                    + UtilDatas.convertDateToString(TipoDate.DATE_DEFAULT, UtilDatas.getDataAtual(), WebUtil.getLanguage(request)) + " "
                    + UtilDatas.formatHoraFormatadaStr(UtilDatas.getHoraAtual()), new Font(Font.HELVETICA, 8, Font.NORMAL, new Color(0, 0, 0))));
            cAuxPageNumber.setBorder(0);
            footer.addCell(cAuxPageNumber);

            cAuxPageNumber = new PdfPCell(new Phrase(pagina + ": " + writer.getPageNumber(), new Font(Font.HELVETICA, 8, Font.NORMAL, new Color(0, 0, 0))));
            cAuxPageNumber.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cAuxPageNumber.setBorder(0);
            footer.addCell(cAuxPageNumber);
            footer.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
            footer.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
        } catch (final Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public String trataParameters(final HashMap hsmParms, final Collection colParmsUtilizadosNoSQL, final Collection colDefinicaoParametros) {
        if (gerencialPainelDto.getClassNameProcessParameters() != null && !gerencialPainelDto.getClassNameProcessParameters().equalsIgnoreCase("")) {
            Class classe = null;
            try {
                classe = Class.forName(gerencialPainelDto.getClassNameProcessParameters());
            } catch (final ClassNotFoundException e) {
                e.printStackTrace();
                // Deixa Passar
            }
            if (classe != null) {
                Object objeto = null;
                try {
                    objeto = classe.newInstance();
                } catch (final InstantiationException e) {
                    e.printStackTrace();
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (objeto != null) {
                    final Method metodo = Reflexao.findMethod("processParameters", objeto);
                    if (metodo != null) {
                        final Object[] param = new Object[] {hsmParms, colParmsUtilizadosNoSQL, colDefinicaoParametros};
                        Object retorno = null;
                        try {
                            retorno = metodo.invoke(objeto, param);
                        } catch (final IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (final IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (final InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        if (retorno == null) {
                            return "";
                        }
                        return (String) retorno;
                    }
                }
            }
            return "";
        } else {
            if (colParmsUtilizadosNoSQL == null || colParmsUtilizadosNoSQL.size() == 0) {
                return "";
            }
            String strRetorno = "";
            for (final Iterator it = colParmsUtilizadosNoSQL.iterator(); it.hasNext();) {
                final String nameParm = (String) it.next();
                final String valor = (String) hsmParms.get(nameParm);

                strRetorno += this.getDescricaoParametro(colDefinicaoParametros, nameParm) + ": " + valor;
                strRetorno += "  ";
            }
            return strRetorno;
        }
    }

    private String getDescricaoParametro(final Collection colDefinicaoParametros, final String nameParm) {
        if (colDefinicaoParametros == null) {
            return "";
        }
        for (final Iterator it = colDefinicaoParametros.iterator(); it.hasNext();) {
            final GerencialParameterDTO gerencialParameterDTO = (GerencialParameterDTO) it.next();
            final String nomeParmAux = "PARAM." + gerencialParameterDTO.getName().trim();
            if (nomeParmAux.equalsIgnoreCase(nameParm)) {
                return gerencialParameterDTO.getDescription();
            }
        }
        return "";
    }

    public void geraCabecalhoPDF(final int tam, final GerencialItemInformationDTO gerencialItemDto, final PdfPTable tableParm, final PdfWriter writer,
            final Document document, final Rectangle page) {
        final PdfPTable table = new PdfPTable(tamTabela);
        table.setWidthPercentage(100);
        try {
            table.setWidths(tamanhoColunasReal);
        } catch (final DocumentException e1) {
            e1.printStackTrace();
        }

        for (int j = 0; j < tam; j++) {
            final PdfPCell cell = new PdfPCell();
            final GerencialFieldDTO fieldDto = (GerencialFieldDTO) ((List) gerencialItemDto.getListFields()).get(j);

            final GerencialGroupDTO grupoDefinicaoDto = this.fieldInGroupDefinition(fieldDto.getName(), gerencialItemDto.getListGroups());
            if (grupoDefinicaoDto == null) { // So mostra se nao for um agrupador
                if (fieldDto.getClassField().getName().equalsIgnoreCase("java.lang.String")) {
                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                }
                if (fieldDto.getClassField().getName().equalsIgnoreCase("java.lang.Double")) {
                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                }
                if (fieldDto.getClassField().getName().equalsIgnoreCase("java.lang.Integer")) {
                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                }
                if (fieldDto.getClassField().getName().equalsIgnoreCase("java.sql.Date")) {
                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                }
                cell.setBackgroundColor(Color.GRAY);
                cell.setPhrase(new Phrase(fieldDto.getTitle()));
                table.addCell(cell);
            }
        }

        final PdfPCell celula0 = new PdfPCell(new Phrase(" "));
        celula0.setColspan(2);
        celula0.setBorder(0);
        tableParm.addCell(celula0);

        final PdfPCell celula = new PdfPCell(table);
        celula.setColspan(2);
        tableParm.addCell(celula);
    }

    private GerencialGroupDTO fieldInGroupDefinition(final String fieldName, final Collection colGrupos) {
        if (colGrupos == null) {
            return null;
        }

        for (final Iterator it = colGrupos.iterator(); it.hasNext();) {
            final GerencialGroupDTO gerencialGroup = (GerencialGroupDTO) it.next();
            if (gerencialGroup.getFieldName().trim().equalsIgnoreCase(fieldName)) {
                return gerencialGroup;
            }
        }
        return null;
    }

}
