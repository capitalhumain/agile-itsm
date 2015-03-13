package br.com.centralit.citgerencial.pdf;

import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import br.com.centralit.citgerencial.bean.GerencialPainelDTO;
import br.com.centralit.citgerencial.bean.GerencialParameterDTO;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.Reflexao;
import br.com.citframework.util.UtilDatas;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class EndPageControlBuffer extends PdfPageEventHelper {

    private final String titleReport;
    private final Collection colParmsUtilizadosNoSQL;
    private final HashMap hshParameters;
    private final Collection colDefinicaoParametros;
    private final GerencialPainelDTO gerencialPainelDto;

    public EndPageControlBuffer(final String titleReportParm, final Collection colParmsUtilizadosNoSQLParm, final HashMap hshParametersParm,
            final Collection colDefinicaoParametrosParm, final GerencialPainelDTO gerencialPainelDtoParm) {
        titleReport = titleReportParm;
        colParmsUtilizadosNoSQL = colParmsUtilizadosNoSQLParm;
        hshParameters = hshParametersParm;
        colDefinicaoParametros = colDefinicaoParametrosParm;
        gerencialPainelDto = gerencialPainelDtoParm;
    }

    @Override
    public void onStartPage(final PdfWriter writer, final Document document) {
        /*
         * try { document.add(new Paragraph(" ")); document.add(new Paragraph(" ")); document.add(new Paragraph(" ")); document.add(new Paragraph(" "));
         * //document.add(new
         * Paragraph(gerencialItemDto.getTitle())); } catch (DocumentException e2) { e2.printStackTrace(); }
         */
        super.onStartPage(writer, document);
    }

    @Override
    public void onEndPage(final PdfWriter writer, final Document document) {
        try {
            final Rectangle page = document.getPageSize();

            final PdfPTable head = new PdfPTable(2);
            try {
                final double tam1 = page.getWidth() * 0.17;
                final double tam2 = page.getWidth() * 0.83;

                final int tamX1 = (int) tam1;
                final int tamX2 = (int) tam2;
                head.setWidths(new int[] {tamX1, tamX2});
            } catch (final DocumentException e1) {
                e1.printStackTrace();
            }
            Image image = null;
            try {
                // URL url = this.servletContext.getContext("/").getResource("/imagens/logo.gif");
                // URL url = this.getClass().getClassLoader().getResource("/imagens/logo.jpg");
                final URL url = new URL(Constantes.getValue("CAMINHO_LOGO_CITGERENCIAL"));
                if (url != null) {
                    try {
                        image = Image.getInstance(url);
                    } catch (final BadElementException e) {
                        e.printStackTrace();
                    }
                }
            } catch (final IOException ioe) {
                ioe.printStackTrace();
            }
            if (image != null) {
                // image.scaleAbsolute(40, 54);
                // image.scaleAbsolute(150, 84);
                image.scaleAbsolute(150, 50);
                image.setAlignment(Image.RIGHT);
                // Chunk ck = new Chunk(image, -3, -60);
                final Chunk ck = new Chunk(image, 40, -25);
                final PdfPCell c1 = new PdfPCell();
                c1.addElement(ck);
                c1.setBorderWidth(0);
                head.addCell(c1);
            } else {
                // PdfPCell c1 = new PdfPCell();
                head.addCell("");
            }

            final String strCab = Constantes.getValue("TEXTO_1a_LINHA_CABECALHO_CITGERENCIAL");
            if (strCab != null && !strCab.equalsIgnoreCase("")) {
                PdfPCell cAux = new PdfPCell(new Phrase(strCab, new Font(Font.HELVETICA, 12, Font.BOLD, new Color(0, 0, 0))));
                cAux.setBorderWidth(0);
                cAux.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                head.addCell(cAux);

                cAux = new PdfPCell(new Phrase(""));
                cAux.setBorderWidth(0);
                cAux.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
                head.addCell(cAux);
            }

            final PdfPCell cAux = new PdfPCell(new Phrase(titleReport, new Font(Font.HELVETICA, 14, Font.BOLD, new Color(0, 0, 0))));
            cAux.setBorderWidth(0);
            cAux.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            head.addCell(cAux);

            // Trata parametros
            final String strFiltro = this.trataParameters(hshParameters, colParmsUtilizadosNoSQL, colDefinicaoParametros);

            PdfPCell cFiltro = new PdfPCell(new Phrase(""));
            cFiltro.setBorderWidth(0);
            cFiltro.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            head.addCell(cFiltro);

            cFiltro = new PdfPCell(new Phrase(strFiltro, new Font(Font.HELVETICA, 8, Font.NORMAL, new Color(0, 0, 0))));
            cFiltro.setBorderWidth(0);
            cFiltro.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            head.addCell(cFiltro);
            // Fim - Trata parametros

            head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
            if (page.getWidth() > 650) {
                /*
                 * head.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - document.topMargin() + head.getTotalHeight(),
                 * writer.getDirectContent());
                 */
                head.writeSelectedRows(0, -1, document.leftMargin(), 585, writer.getDirectContent());
            } else {
                head.writeSelectedRows(0, -1, document.leftMargin(), 825, writer.getDirectContent());
            }

            final PdfPTable foot = new PdfPTable(2);
            final String strSistema = Constantes.getValue("TEXTO_1a_LINHA_RODAPE_CITGERENCIAL");
            if (strSistema != null && !strSistema.equalsIgnoreCase("")) {
                final PdfPCell cAuxSistema = new PdfPCell(new Phrase(strSistema, new Font(Font.HELVETICA, 8, Font.NORMAL, new Color(0, 0, 0))));
                cAuxSistema.setColspan(2);
                foot.addCell(cAuxSistema);
            }

            final String emissao = (String) hshParameters.get("citcorpore.comum.emissao");
            final String pagina = (String) hshParameters.get("citcorpore.comum.pagina");

            PdfPCell cAuxPageNumber = new PdfPCell(new Phrase(emissao + ": " + UtilDatas.dateToSTR(UtilDatas.getDataAtual()) + " "
                    + UtilDatas.formatHoraFormatadaStr(UtilDatas.getHoraAtual()), new Font(Font.HELVETICA, 8, Font.NORMAL, new Color(0, 0, 0))));
            foot.addCell(cAuxPageNumber);

            cAuxPageNumber = new PdfPCell(new Phrase(pagina + ": " + writer.getPageNumber(), new Font(Font.HELVETICA, 8, Font.NORMAL, new Color(0, 0, 0))));
            cAuxPageNumber.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            foot.addCell(cAuxPageNumber);

            foot.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin() - 40);
            foot.writeSelectedRows(0, -1, document.leftMargin() + 40, document.bottomMargin() - 30, writer.getDirectContent());
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

}
