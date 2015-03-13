package br.com.centralit.citcorpore.versao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import br.com.centralit.citcorpore.util.CITCorporeUtil;

public class Versao {

    public static String VERSAO_DATA_GERACAO = "";

    public static String getVersao() {
        return lerXmlDeVersoes();
    }

    public static String getDataAndVersao() {
        return Versao.VERSAO_DATA_GERACAO + " " + lerXmlDeVersoes();
    }

    private static String lerXmlDeVersoes() {
        String versaoStr = "1.0.0";

        final String separator = System.getProperty("file.separator");
        final String diretorio = CITCorporeUtil.CAMINHO_REAL_APP + "XMLs" + separator;
        final File file = new File(diretorio + "historicoDeVersoes.xml");
        final SAXBuilder sb = new SAXBuilder();
        Document doc = new Document();

        try {
            doc = sb.build(file);
        } catch (final JDOMException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        final Element historicoDeVersoes = doc.getRootElement();

        final List<Element> versoes = historicoDeVersoes.getChildren();

        if (versoes != null && versoes.size() > 0) {
            return versaoStr = versoes.get(versoes.size() - 1).getText();
        } else {
            return versaoStr;
        }
    }

}
