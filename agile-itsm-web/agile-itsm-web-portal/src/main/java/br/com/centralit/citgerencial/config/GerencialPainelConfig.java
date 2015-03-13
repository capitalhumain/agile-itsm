package br.com.centralit.citgerencial.config;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import br.com.centralit.citgerencial.bean.GerencialItemPainelDTO;
import br.com.centralit.citgerencial.bean.GerencialOptionDTO;
import br.com.centralit.citgerencial.bean.GerencialOptionsDTO;
import br.com.centralit.citgerencial.bean.GerencialPainelDTO;
import br.com.centralit.citgerencial.bean.GerencialParameterDTO;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilStrings;

public class GerencialPainelConfig {

    private static final Logger LOGGER = Logger.getLogger(GerencialPainelConfig.class);
    private Document doc = null;

    public static GerencialPainelDTO getInstance(final String fileNameConfig) throws Exception {
        InputStream painelConfigFile = GerencialPainelConfig.class.getClassLoader().getResourceAsStream(fileNameConfig);
        if (painelConfigFile == null) {
            painelConfigFile = ClassLoader.getSystemResourceAsStream(fileNameConfig);
        }
        if (painelConfigFile == null) {
            painelConfigFile = ClassLoader.getSystemClassLoader().getResourceAsStream(fileNameConfig);
        }
        if (painelConfigFile == null) {
            // Tenta pelo ClassLoader do Log4j.
            painelConfigFile = LOGGER.getClass().getClassLoader().getResourceAsStream(fileNameConfig);
        }

        LOGGER.info("CITAJAX_CONFIG: " + fileNameConfig);
        final GerencialPainelConfig gerencialConfig = new GerencialPainelConfig();
        return gerencialConfig.getPainel(painelConfigFile, fileNameConfig);
    }

    public GerencialPainelDTO getPainel(final InputStream ioos, final String fileNameConfig) {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            if (ioos == null) {
                throw new Exception("ARQUIVO (ITEM_INFORMACAO_CONFIG): " + fileNameConfig + " NAO ENCONTRADO!!!!!!!!!");
            }
            doc = builder.parse(ioos);
            return this.load();
        } catch (final Exception e) {
            e.printStackTrace();
            doc = null;
            return null;
        }
    }

    public GerencialPainelDTO getPainelString(final String textoXml, final String fileNameConfig) {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            if (textoXml == null) {
                throw new Exception(" (ITEM_INFORMACAO_CONFIG): " + fileNameConfig + " NAO ENCONTRADO!!!!!!!!!");
            }
            final InputStream is = new ByteArrayInputStream(textoXml.getBytes());
            doc = builder.parse(is);
            return this.load();
        } catch (final Exception e) {
            e.printStackTrace();
            doc = null;
            return null;
        }
    }

    public GerencialPainelDTO load() {
        if (doc == null) {
            return null;
        }
        GerencialPainelDTO gerencialPainelDto = new GerencialPainelDTO();

        final Node noRoot = doc.getChildNodes().item(0);
        if (noRoot != null) {
            final NamedNodeMap map = noRoot.getAttributes();

            gerencialPainelDto.setDescription(map.getNamedItem("description").getNodeValue());
            if (map.getNamedItem("classNameProcessParameters") != null) {
                gerencialPainelDto.setClassNameProcessParameters(map.getNamedItem("classNameProcessParameters").getNodeValue());
            }

            gerencialPainelDto = this.getSubTree(gerencialPainelDto, noRoot);
        }

        return gerencialPainelDto;
    }

    public GerencialPainelDTO getSubTree(final GerencialPainelDTO painel, final Node noItem) {
        if (noItem == null) {
            return painel;
        }

        final Collection colItensPaineis = new ArrayList<>();
        GerencialItemPainelDTO gerencialItemPainel;
        if (noItem.getChildNodes() != null) {
            for (int i = 0; i < noItem.getChildNodes().getLength(); i++) {
                final Node noSubItem = noItem.getChildNodes().item(i);
                if (noSubItem.getNodeName().equals("#text")) {
                    continue;
                }
                if (noSubItem.getNodeName().equals("#comment")) {
                    continue;
                }

                if (noSubItem.getNodeName().equalsIgnoreCase("ITEM")) {
                    gerencialItemPainel = new GerencialItemPainelDTO();

                    final NamedNodeMap map = noSubItem.getAttributes();

                    gerencialItemPainel.setFile(map.getNamedItem("file").getNodeValue());
                    gerencialItemPainel.setName(map.getNamedItem("name").getNodeValue());

                    gerencialItemPainel.setTop(map.getNamedItem("top").getNodeValue());
                    gerencialItemPainel.setLeft(map.getNamedItem("left").getNodeValue());
                    gerencialItemPainel.setHeigth(map.getNamedItem("heigth").getNodeValue());
                    gerencialItemPainel.setWidth(map.getNamedItem("width").getNodeValue());

                    if (map.getNamedItem("total") != null) {
                        gerencialItemPainel.setTotal(map.getNamedItem("total").getNodeValue());
                    }

                    colItensPaineis.add(gerencialItemPainel);
                }
                if (noSubItem.getNodeName().equalsIgnoreCase("PARAMETERS")) {
                    painel.setListParameters(this.getSubTreeParameters(noSubItem));
                }
            }
        }
        painel.setListItens(colItensPaineis);

        return painel;
    }

    public Collection getSubTreeParameters(final Node noItem) {
        final Collection colParameters = new ArrayList<>();
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

        final Collection colRetorno = new ArrayList<>();
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

}
