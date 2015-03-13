package br.com.centralit.citcorpore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.pdfbox.cos.COSDocument;
import org.pdfbox.pdfparser.PDFParser;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

import br.com.centralit.citcorpore.bean.UploadDTO;
import br.com.centralit.citged.bean.ControleGEDDTO;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilStrings;

/**
 * Precisei criar esta classe, para utilizar este código tanto na indexação Lucene como na tela Base de Conhecimento...
 * 
 * @author euler.ramos
 *
 */
public class Arquivo {

    private String link;
    private String nome;
    private String extensao;
    private String conteudo;

    public Arquivo(final ControleGEDDTO controleGEDDTO) {
        super();
        nome = controleGEDDTO.getNomeArquivo();
        extensao = controleGEDDTO.getExtensaoArquivo();
        try {
            link = this.getFromGed(controleGEDDTO);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        conteudo = this.buscaConteudo();
    }

    public Arquivo() {

    }

    public String getLink() {
        return link;
    }

    public String getNome() {
        return nome;
    }

    public String getExtensao() {
        return extensao;
    }

    public String getConteudo() {
        return conteudo;
    }

    private String buscaConteudo() {
        if (link != null) {
            final StringBuilder textoDocumento = new StringBuilder();

            String[] arrayMicrosoftWord;

            final File arquivo = new File(link);
            if (arquivo.exists()) {
                if (this.getExtensao().equals("pdf")) {
                    try {
                        textoDocumento.append(this.extrairFormatoPDF(link));
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                } else if (this.getExtensao().equals("doc")) {
                    arrayMicrosoftWord = this.extrairFormatoMicrosoftWord(link);
                    for (final String element : arrayMicrosoftWord) {
                        textoDocumento.append(element);
                    }

                } else if (this.getExtensao().equals("docx")) {
                    textoDocumento.append(this.extrairFormatoDOCX(link));

                } else if (this.getExtensao().equals("xls")) {
                    textoDocumento.append(this.extrairFormatoXLS(link));

                } else if (this.getExtensao().equals("xlsx")) {
                    textoDocumento.append(this.extrairFormatoXLSX(link));
                } else if (this.getExtensao().equals("txt")) {
                    textoDocumento.append(this.extrairFormatoTxt(link));
                }
            }

            return textoDocumento.toString();
        } else {
            return "";
        }
    }

    /**
     * Extrai o texto nos formatos suportados pelo Microsoft Word. Na necessidade presente só será necessários os formatos DOC e DOCX.
     *
     * @param caminhoDocumento
     * @param nomeArquivo
     * @return String
     */
    private String[] extrairFormatoMicrosoftWord(final String caminhoDocumento) {

        File file = null;
        FileInputStream fis = null;
        WordExtractor extractor = null;
        String[] fileData = null;

        try {

            file = new File(caminhoDocumento);

            fis = new FileInputStream(file.getAbsolutePath());

            final HWPFDocument document = new HWPFDocument(fis);

            extractor = new WordExtractor(document);

            fileData = extractor.getParagraphText();

        } catch (final Exception ex) {

            ex.printStackTrace();

        } finally {

            try {
                if (fis != null) {
                    fis.close();
                }

                if (extractor != null) {
                    extractor.close();
                }

            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        return fileData;
    }

    /**
     * Extrai texto dos documentos do formato .docx
     *
     * @param caminhoDocumento
     * @return StringBuilder
     * @author Vadoilo Damasceno
     */
    private StringBuilder extrairFormatoDOCX(final String caminhoDocumento) {

        File file = null;
        FileInputStream fis = null;
        XWPFWordExtractor extractor = null;
        final StringBuilder texto = new StringBuilder();

        try {

            file = new File(caminhoDocumento);

            fis = new FileInputStream(file.getAbsolutePath());

            final XWPFDocument document = new XWPFDocument(fis);
            extractor = new XWPFWordExtractor(document);

            texto.append(extractor.getText());

        } catch (final Exception ex) {

            ex.printStackTrace();

        } finally {

            try {
                if (fis != null) {
                    fis.close();
                }

                if (extractor != null) {
                    extractor.close();
                }

            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        return texto;
    }

    /**
     * Extrai texto de documentos no formato .xlsx
     *
     * @param caminhoDocumento
     * @return StringBuilder
     * @author Vadoilo Damasceno
     */
    private StringBuilder extrairFormatoXLSX(final String caminhoDocumento) {

        final StringBuilder texto = new StringBuilder();
        FileInputStream fileInputStream = null;
        XSSFExcelExtractor extractor = null;
        try {

            final File file = new File(caminhoDocumento);

            fileInputStream = new FileInputStream(file.getAbsolutePath());

            final XSSFWorkbook document = new XSSFWorkbook(fileInputStream);

            extractor = new XSSFExcelExtractor(document);

            extractor.setFormulasNotResults(true);
            extractor.setIncludeSheetNames(true);

            texto.append(extractor.getText());

        } catch (final Exception ex) {

            ex.printStackTrace();

        } finally {

            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }

                if (extractor != null) {
                    extractor.close();
                }

            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        return texto;
    }

    /**
     * Extrai texto de documentos no formato .xls
     *
     * @param caminhoDocumento
     * @return StringBuilder
     * @author Vadoilo Damasceno
     */
    private StringBuilder extrairFormatoXLS(final String caminhoDocumento) {

        final StringBuilder texto = new StringBuilder();
        FileInputStream fileInputStream = null;
        ExcelExtractor extractor = null;
        try {

            final File file = new File(caminhoDocumento);

            fileInputStream = new FileInputStream(file.getAbsolutePath());

            final HSSFWorkbook document = new HSSFWorkbook(fileInputStream);

            extractor = new ExcelExtractor(document);

            extractor.setFormulasNotResults(true);
            extractor.setIncludeSheetNames(true);

            texto.append(extractor.getText());

        } catch (final Exception ex) {

            ex.printStackTrace();

        } finally {

            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }

                if (extractor != null) {
                    extractor.close();
                }

            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        return texto;
    }

    /**
     * Extrai o texto de um documento no formato PDF.
     *
     * @param caminhoDocumento
     * @param nomeArquivo
     * @return StringBuilder
     * @throws IOException
     */
    private StringBuilder extrairFormatoPDF(final String caminhoDocumento) throws IOException {

        final StringBuilder texto = new StringBuilder();
        PDFParser parser;
        FileInputStream fi = null;
        COSDocument cd = null;
        try {

            fi = new FileInputStream(new File(caminhoDocumento));

            parser = new PDFParser(fi);

            parser.parse();

            cd = parser.getDocument();

            texto.append(new PDFTextStripper().getText(new PDDocument(cd)));

        } catch (final FileNotFoundException e1) {

            e1.printStackTrace();

        } catch (final IOException e1) {

            e1.printStackTrace();

        } finally {

            if (cd != null) {
                cd.close();
            }

            if (fi != null) {
                fi.close();
            }
        }

        return texto;
    }

    /**
     * Extrai o texto de um arquivo no formato txt.
     *
     * @param caminhoDocumento
     * @param nomeArquivo
     * @return StringBuilder
     */
    private StringBuilder extrairFormatoTxt(final String caminhoDocumento) {

        File file = null;
        FileInputStream fis = null;
        final StringBuilder texto = new StringBuilder("");

        try {

            file = new File(caminhoDocumento);

            fis = new FileInputStream(file);

            int ln;

            while ((ln = fis.read()) != -1) {

                texto.append((char) ln);

            }

        } catch (final FileNotFoundException e) {

            e.printStackTrace();

        } catch (final IOException e) {

            e.printStackTrace();

        } finally {

            try {
                if (fis != null) {
                    fis.close();
                }

            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        return texto;
    }

    /**
     * Retorna Link do arquivo Decriptografado do GED.
     *
     * @param idControleGed
     * @return
     * @throws Exception
     */
    public String getFromGed(final ControleGEDDTO controleGEDDTO) throws Exception {
        final Integer idEmpresa = 1;

        String pasta = "";
        if (controleGEDDTO != null) {
            pasta = controleGEDDTO.getPasta();
        }

        String PRONTUARIO_GED_DIRETORIO = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.GedDiretorio, "/usr/local/gedCitsmart/");
        if (PRONTUARIO_GED_DIRETORIO == null || PRONTUARIO_GED_DIRETORIO.trim().equalsIgnoreCase("")) {
            PRONTUARIO_GED_DIRETORIO = "";
        }

        if (PRONTUARIO_GED_DIRETORIO.equalsIgnoreCase("")) {
            PRONTUARIO_GED_DIRETORIO = Constantes.getValue("DIRETORIO_GED");
        }

        if (PRONTUARIO_GED_DIRETORIO == null || PRONTUARIO_GED_DIRETORIO.equalsIgnoreCase("")) {
            PRONTUARIO_GED_DIRETORIO = "/ged";
        }
        String PRONTUARIO_GED_INTERNO = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.GedInterno, "S");
        if (PRONTUARIO_GED_INTERNO == null) {
            PRONTUARIO_GED_INTERNO = "S";
        }
        String prontuarioGedInternoBancoDados = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.GedInternoBD, "N");
        if (!UtilStrings.isNotVazio(prontuarioGedInternoBancoDados)) {
            prontuarioGedInternoBancoDados = "N";
        }

        if (PRONTUARIO_GED_INTERNO.equalsIgnoreCase("S")) {

            final String fileRec = CITCorporeUtil.CAMINHO_REAL_APP + "tempUpload/REC_FROM_GED_" + controleGEDDTO.getIdControleGED() + "."
                    + controleGEDDTO.getExtensaoArquivo();

            final File arquivo = new File(PRONTUARIO_GED_DIRETORIO + "/" + idEmpresa + "/" + pasta + "/" + controleGEDDTO.getIdControleGED() + ".ged");
            if (arquivo.exists()) {
                CriptoUtils.decryptFile(PRONTUARIO_GED_DIRETORIO + "/" + idEmpresa + "/" + pasta + "/" + controleGEDDTO.getIdControleGED() + ".ged", fileRec,
                        System.getProperties().get("user.dir") + Constantes.getValue("CAMINHO_CHAVE_PRIVADA"));
            } else {
                System.out.println("Arquivo : " + arquivo.getPath() + " Não Encontrado!");
            }

            return fileRec;
        }
        return null;
    }

    public static boolean verificaSeArquivoExiste(final String caminhoDocumento) {
        File arquivo = null;
        try {
            arquivo = new File(caminhoDocumento);
            return arquivo.exists();
        } catch (final Exception e) {}
        return false;
    }

    public static String getDirUploadImagem(final UploadDTO uploadDTO) {
        return "../../tempUpload/" + uploadDTO.getNameFile();
    }

}
