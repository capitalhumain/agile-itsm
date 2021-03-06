package br.com.centralit.citgerencial.config;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import br.com.centralit.citgerencial.bean.GerencialFieldDTO;
import br.com.centralit.citgerencial.bean.GerencialGraphInformationDTO;
import br.com.centralit.citgerencial.bean.GerencialGroupDTO;
import br.com.centralit.citgerencial.bean.GerencialItemInformationDTO;
import br.com.centralit.citgerencial.bean.GerencialSQLDTO;
import br.com.centralit.citgerencial.bean.GerencialSummaryInformationDTO;
import br.com.centralit.citgerencial.util.CITGerencialUtil;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilI18N;

public class GerencialItemInformationConfig {

    private static final Logger LOGGER = Logger.getLogger(GerencialItemInformationConfig.class);
    private Document doc = null;

    public static GerencialItemInformationDTO getInstance(final String fileNameConfig, final HttpServletRequest request) throws Exception {
        InputStream itemInformationConfigFile = GerencialItemInformationConfig.class.getClassLoader().getResourceAsStream("/" + fileNameConfig);
        if (itemInformationConfigFile == null) {
            itemInformationConfigFile = GerencialItemInformationConfig.class.getResourceAsStream(fileNameConfig);
        }
        if (itemInformationConfigFile == null) {
            itemInformationConfigFile = GerencialItemInformationConfig.class.getResourceAsStream("/WEB-INF/classes/" + fileNameConfig);
        }
        if (itemInformationConfigFile == null) {
            itemInformationConfigFile = ClassLoader.getSystemResourceAsStream(fileNameConfig);
        }
        if (itemInformationConfigFile == null) {
            itemInformationConfigFile = ClassLoader.getSystemClassLoader().getResourceAsStream(fileNameConfig);
        }
        if (itemInformationConfigFile == null) {
            // Tenta pelo ClassLoader do Log4j.
            itemInformationConfigFile = LOGGER.getClass().getClassLoader().getResourceAsStream(fileNameConfig);
        }
        if (itemInformationConfigFile == null) {
            LOGGER.info("Tentando abrir arquivo de Relatorio: " + CITGerencialUtil.CAMINHO_REAL_APP + fileNameConfig);
            itemInformationConfigFile = new FileInputStream(CITGerencialUtil.CAMINHO_REAL_APP + fileNameConfig);
        }
        if (itemInformationConfigFile == null) {
            LOGGER.info("Tentando abrir arquivo de Relatorio: " + CITGerencialUtil.CAMINHO_REAL_APP + "WEB-INF/classes/" + fileNameConfig);
            itemInformationConfigFile = new FileInputStream(CITGerencialUtil.CAMINHO_REAL_APP + "WEB-INF/classes/" + fileNameConfig);
        }

        LOGGER.info("Arquivo de Relatorio: " + fileNameConfig);
        final GerencialItemInformationConfig gerencialConfig = new GerencialItemInformationConfig();
        return gerencialConfig.getItemInformation(itemInformationConfigFile, fileNameConfig, request);
    }

    public GerencialItemInformationDTO getItemInformation(final InputStream ioos, final String fileNameConfig, final HttpServletRequest request) {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            if (ioos == null) {
                throw new Exception("ARQUIVO (ITEM_INFORMACAO_CONFIG): " + fileNameConfig + " NAO ENCONTRADO!!!!!!!!!");
            }
            doc = builder.parse(ioos);
            return this.load(request);
        } catch (final Exception e) {
            e.printStackTrace();
            doc = null;
            return null;
        }
    }

    public GerencialItemInformationDTO getItemInformationString(final String textoItem, final String fileNameConfig, final HttpServletRequest request) {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            if (textoItem == null) {
                throw new Exception(" (ITEM_INFORMACAO_CONFIG): " + fileNameConfig + " NAO ENCONTRADO!!!!!!!!!");
            }
            final InputStream is = new ByteArrayInputStream(textoItem.getBytes());
            doc = builder.parse(is);
            return this.load(request);
        } catch (final Exception e) {
            e.printStackTrace();
            doc = null;
            return null;
        }
    }

    public GerencialItemInformationDTO load(final HttpServletRequest request) {
        if (doc == null) {
            return null;
        }
        GerencialItemInformationDTO gerencialItemDto = new GerencialItemInformationDTO();

        final Node noRoot = doc.getChildNodes().item(0);
        if (noRoot != null) {
            final NamedNodeMap map = noRoot.getAttributes();

            gerencialItemDto.setType(map.getNamedItem("type").getNodeValue());
            gerencialItemDto.setDescription(map.getNamedItem("description").getNodeValue());
            gerencialItemDto.setTitle(map.getNamedItem("title").getNodeValue());
            gerencialItemDto.setHDetailSpacing(map.getNamedItem("hDetailSpacing").getNodeValue());

            gerencialItemDto.setReport(Boolean.valueOf(map.getNamedItem("report").getNodeValue()).booleanValue());
            gerencialItemDto.setGraph(Boolean.valueOf(map.getNamedItem("graph").getNodeValue()).booleanValue());
            if (map.getNamedItem("porcentagem") != null) {
                gerencialItemDto.setPorcentagem(Boolean.valueOf(map.getNamedItem("porcentagem").getNodeValue()).booleanValue());
            }

            gerencialItemDto.setReportPageLayout(map.getNamedItem("reportPageLayout").getNodeValue());
            gerencialItemDto.setDefaultVisualization(map.getNamedItem("defaultVisualization").getNodeValue());

            gerencialItemDto = this.getSubTree(gerencialItemDto, noRoot, request);
        }

        return gerencialItemDto;
    }

    public GerencialItemInformationDTO getSubTree(final GerencialItemInformationDTO item, final Node noItem, final HttpServletRequest request) {
        if (noItem == null) {
            return item;
        }

        if (noItem.getChildNodes() != null) {
            for (int i = 0; i < noItem.getChildNodes().getLength(); i++) {
                final Node noSubItem = noItem.getChildNodes().item(i);
                if (noSubItem.getNodeName().equals("#text")) {
                    continue;
                }

                if (noSubItem.getNodeName().equalsIgnoreCase("SQL")) {
                    item.setGerencialSQL(this.getItemSQL(noSubItem));
                }
                if (noSubItem.getNodeName().equalsIgnoreCase("CLASS")) {
                    final NamedNodeMap map = noSubItem.getAttributes();
                    item.setClassExecute(map.getNamedItem("class").getNodeValue());
                }
                if (noSubItem.getNodeName().equalsIgnoreCase("SERVICE")) {
                    final NamedNodeMap map = noSubItem.getAttributes();
                    item.setClassExecute(map.getNamedItem("class").getNodeValue());
                }
                if (noSubItem.getNodeName().equalsIgnoreCase("JSP")) {
                    final NamedNodeMap map = noSubItem.getAttributes();
                    item.setClassExecute(map.getNamedItem("path").getNodeValue());
                }
                if (noSubItem.getNodeName().equalsIgnoreCase("FIELDS")) {
                    item.setListFields(this.getSubTreeFields(noSubItem, request));
                }
                if (noSubItem.getNodeName().equalsIgnoreCase("GRAPHS")) {
                    item.setListGraphs(this.getSubTreeGraphs(noSubItem));
                }
                if (noSubItem.getNodeName().equalsIgnoreCase("GROUPS")) {
                    item.setListGroups(this.getSubTreeGroups(noSubItem));
                }
                if (noSubItem.getNodeName().equalsIgnoreCase("SUMMARY")) {
                    final GerencialSummaryInformationDTO gerencialSummary = new GerencialSummaryInformationDTO();
                    item.setGerencialSummary(gerencialSummary);
                }
            }
        }
        return item;
    }

    public Collection getSubTreeFields(final Node noItem, final HttpServletRequest request) {
        final Collection colFields = new ArrayList<>();
        GerencialFieldDTO gerencialField;
        if (noItem.getChildNodes() != null) {
            for (int i = 0; i < noItem.getChildNodes().getLength(); i++) {
                final Node noSubItem = noItem.getChildNodes().item(i);
                if (noSubItem.getNodeName().equals("#text")) {
                    continue;
                }

                if (noSubItem.getNodeName().equalsIgnoreCase("FIELD")) {
                    gerencialField = new GerencialFieldDTO();

                    final NamedNodeMap map = noSubItem.getAttributes();

                    gerencialField.setType(map.getNamedItem("type").getNodeValue());
                    gerencialField.setWidth(map.getNamedItem("width").getNodeValue());
                    gerencialField.setTitle(UtilI18N.internacionaliza(request, map.getNamedItem("title").getNodeValue()));
                    gerencialField.setName(map.getNamedItem("name").getNodeValue());

                    gerencialField.setTotals(Boolean.valueOf(map.getNamedItem("totals").getNodeValue()).booleanValue());
                    gerencialField.setCount(Boolean.valueOf(map.getNamedItem("count").getNodeValue()).booleanValue());

                    gerencialField.setDecimals(Integer.valueOf(map.getNamedItem("decimals").getNodeValue()));
                    gerencialField.setMask(map.getNamedItem("mask").getNodeValue());

                    try {
                        gerencialField.setClassField(Class.forName(gerencialField.getType()));
                    } catch (final ClassNotFoundException e) {
                        e.printStackTrace();
                        gerencialField.setClassField(null);
                    }

                    colFields.add(gerencialField);
                }
            }
        }
        return colFields;
    }

    public Collection getSubTreeGroups(final Node noItem) {
        final Collection colGroups = new ArrayList<>();
        GerencialGroupDTO gerencialGroup;
        if (noItem.getChildNodes() != null) {
            for (int i = 0; i < noItem.getChildNodes().getLength(); i++) {
                final Node noSubItem = noItem.getChildNodes().item(i);
                if (noSubItem.getNodeName().equals("#text")) {
                    continue;
                }

                if (noSubItem.getNodeName().equalsIgnoreCase("GROUP")) {
                    gerencialGroup = new GerencialGroupDTO();

                    final NamedNodeMap map = noSubItem.getAttributes();

                    gerencialGroup.setSpacingLeft(map.getNamedItem("spacingLeft").getNodeValue());
                    gerencialGroup.setFieldName(map.getNamedItem("fieldName").getNodeValue());

                    colGroups.add(gerencialGroup);
                }
            }
        }
        return colGroups;
    }

    public Collection getSubTreeGraphs(final Node noItem) {
        final Collection colGraphs = new ArrayList<>();
        GerencialGraphInformationDTO gerencialGraph;
        if (noItem.getChildNodes() != null) {
            for (int i = 0; i < noItem.getChildNodes().getLength(); i++) {
                final Node noSubItem = noItem.getChildNodes().item(i);
                if (noSubItem.getNodeName().equals("#text")) {
                    continue;
                }

                if (noSubItem.getNodeName().equalsIgnoreCase("GRAPH")) {
                    gerencialGraph = new GerencialGraphInformationDTO();

                    final NamedNodeMap map = noSubItem.getAttributes();

                    gerencialGraph.setType(map.getNamedItem("type").getNodeValue());

                    colGraphs.add(gerencialGraph);
                }
            }
        }
        return colGraphs;
    }

    public GerencialSQLDTO getItemSQL(final Node noItem) {
        final GerencialSQLDTO gerencialSQL = new GerencialSQLDTO();

        String strOwner = Constantes.getValue("OWNER_DB");
        if (strOwner == null) {
            strOwner = "";
        }
        strOwner = strOwner.replaceAll("\\.", ""); // Retira o ponto, pois o owner, na lookup.xml ja esta com .

        String strTratar = noItem.getChildNodes().item(0).getNodeValue();
        strTratar = strTratar.replaceAll("\\{OWNER_BD\\}", strOwner);

        gerencialSQL.setSql(strTratar);

        return gerencialSQL;
    }

}
