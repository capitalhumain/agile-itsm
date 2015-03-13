package br.com.centralit.citcorpore.comm.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import br.com.centralit.citcorpore.bean.CaracteristicaDTO;
import br.com.centralit.citcorpore.bean.ItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.TipoItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.ValorDTO;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;

/**
 * @author Maycon.Fernandes
 *
 */
public class XmlReadDtdAgente {

    private static final String VALUE = "HARDWARE";

    private Document doc = null;
    private final List<ItemConfiguracaoDTO> listElementos = new ArrayList<ItemConfiguracaoDTO>();

    /**
     * @param ioos
     *            InputStream
     * @throws Exception
     * @throws ServiceException
     */
    public XmlReadDtdAgente(final InputStream ioos) throws ServiceException, Exception, LogicException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(ioos);
            listElementos.add(this.gravaDados());
        } catch (final ParserConfigurationException e) {
            e.printStackTrace();
        } catch (final SAXException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param ioos
     * @throws Exception
     * @throws ServiceException
     */
    public List<ItemConfiguracaoDTO> XmlReadDtdAgent(final String string) throws ServiceException, Exception, LogicException {
        try {
            final DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            final InputSource inStream = new InputSource();
            // inStream.setEncoding("UTF-8");
            inStream.setCharacterStream(new StringReader(string));
            doc = db.parse(inStream);
            listElementos.add(this.gravaDados());
        } catch (final ParserConfigurationException e) {
            e.printStackTrace();
        } catch (final SAXParseException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return listElementos;
    }

    /**
     *
     * Caminho onde encotra os xml Ex(d:\\nomeDiretorio);
     *
     * @param file
     *            File
     * @throws Exception
     * @throws ServiceException
     */
    public XmlReadDtdAgente(final File file) throws ServiceException, Exception, LogicException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            for (final File string : file.listFiles()) {
                final DocumentBuilder builder = factory.newDocumentBuilder();
                doc = builder.parse(string.getPath());
                listElementos.add(this.gravaDados());
            }
        } catch (final ParserConfigurationException e) {
            e.printStackTrace();
        } catch (final SAXException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Caminho do XML (Ex: C:\\nomexml.xml)
     *
     * @param file
     *            String
     * @throws Exception
     * @throws ServiceException
     */
    public XmlReadDtdAgente(final String file) throws ServiceException, Exception, LogicException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(new File(file));
            listElementos.add(this.gravaDados());
        } catch (final ParserConfigurationException e) {
            e.printStackTrace();
        } catch (final SAXException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public XmlReadDtdAgente() {}

    /**
     * @return ItemConfiguracaoDTO
     * @throws Exception
     * @throws ServiceException
     */
    private ItemConfiguracaoDTO gravaDados() throws ServiceException, Exception, LogicException {
        Node noRoot = null;
        final ItemConfiguracaoDTO beanItem = new ItemConfiguracaoDTO();
        CaracteristicaDTO beanCaracteristica = new CaracteristicaDTO();
        final List<TipoItemConfiguracaoDTO> lstTipoItemConfi = new ArrayList<TipoItemConfiguracaoDTO>();

        String atributos = "";
        String noPesquisa = "";

        noPesquisa = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.NoPesquisa, null);
        // List<ParametroCorporeDTO> listParametroNoPesquisa = paramentroService.pesquisarParamentro(Enumerados.ParametroSistema.NoPesquisa.id(),
        // Enumerados.ParametroSistema.NoPesquisa.campo());
        // noPesquisa = ((ParametroCorporeDTO) listParametroNoPesquisa.get(0)).getValor();

        if (!VALUE.equalsIgnoreCase(noPesquisa == null ? "" : noPesquisa.trim())) {
            noPesquisa = "HARDWARE";
        }

        atributos = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.Atributo, null);

        // Euler.Ramos - Correção do incidente 163237 - os itens de configuração
        // estavam duplicando numa rede com ip dinâmico.
        // O código antigo seria apenas uma validação e não deveria acontecer na
        // hora de utilizar o parâmetro e sim na hora de gravá-lo;
        // o valor estava sendo alterado para "IPADDR,NAME,USERID" contra a
        // vontade do usuário, ele devia ficar sabendo da incompatibilidade
        // de valores antes de gravar.
        if (atributos == null || atributos.trim().equalsIgnoreCase("")) {
            atributos = "NAME";
        }

        atributos = atributos.trim();
        noPesquisa = noPesquisa.trim();

        try {
            noRoot = doc.getChildNodes().item(0);

            final NodeList listNoPesquisa = doc.getElementsByTagName(noPesquisa);

            final String[] listAtributos = atributos.split(",");
            final Node firstPersonNode = listNoPesquisa.item(0);
            String atributoAux = "";

            for (final String string : listAtributos) {
                if (this.percorreListaXml(firstPersonNode, string.trim()) != null) {
                    lstTipoItemConfi.add(this.percorreListaXml(firstPersonNode, string.trim()));
                }
            }

            for (final TipoItemConfiguracaoDTO tipoItemCfg : lstTipoItemConfi) {
                beanCaracteristica = (CaracteristicaDTO) tipoItemCfg.getCaracteristicas().get(0);

                if (atributoAux.trim().equalsIgnoreCase("")) {
                    atributoAux += beanCaracteristica.getValor().getValorStr();
                } else {
                    atributoAux += " - " + beanCaracteristica.getValor().getValorStr();
                }
            }

            beanItem.setIdentificacao(atributoAux.trim());
            /**
             * Setando a identificação padrão pelo ip
             */
            if (atributos.startsWith("NAME")) {
                beanItem.setIdentificacaoPadrao(doc.getElementsByTagName("NAME").item(0).getTextContent());
            } else {
                beanItem.setIdentificacaoPadrao(doc.getElementsByTagName("IPADDR").item(0).getTextContent());
            }

            lstTipoItemConfi.clear();

            for (int j = 0; j < noRoot.getChildNodes().getLength(); j++) {
                final Node noAgente = noRoot.getChildNodes().item(j);

                if (noAgente == null || noAgente.getNodeName() == null) {
                    continue;
                }
                if (noAgente.getNodeName().equals("#text")) {
                    continue;
                }

                if (noAgente.hasChildNodes() && noAgente.getChildNodes().getLength() > 0) {
                    for (int i = 0; i < noAgente.getChildNodes().getLength(); i++) {
                        final Node noAgenteAux = noAgente.getChildNodes().item(i);
                        if (noAgenteAux == null || noAgenteAux.getNodeName() == null) {
                            continue;
                        }
                        if (noAgenteAux.getNodeName().equals("#text")) {
                            continue;
                        }

                        if (noAgenteAux.hasChildNodes() && noAgenteAux.getChildNodes().getLength() > 0) {
                            this.procurarProximoNode(noAgenteAux, true, lstTipoItemConfi);
                        } else {
                            lstTipoItemConfi.add(this.percorreListaXml(noAgenteAux.getParentNode(), ""));
                            break;
                        }
                    }
                } else {
                    lstTipoItemConfi.add(this.percorreListaXml(noAgente, ""));
                }
            }
            beanItem.setTipoItemConfiguracao(lstTipoItemConfi);
            return beanItem;
        } catch (final Exception e) {
            System.out.println("Erro ao fazer a leitura do XML: " + e.getMessage());
            throw new LogicException("Parâmentros de Configuração Atributo Pesquisa/No Pesquisa inválidos");
        }
    }

    /**
     * Procura próximo Node.
     *
     * @param noAgente
     *            Node
     * @param existNoFilho
     *            Boolean
     * @param lstTipoItem
     *            TipoItemConfiguracaoDTO
     * @return boolean true se existir próximo node.
     */

    private Boolean procurarProximoNode(final Node noAgente, final Boolean existNoFilho, final List<TipoItemConfiguracaoDTO> lstTipoItem) {
        for (int j = 0; j < noAgente.getChildNodes().getLength(); j++) {
            final Node noAgente2 = noAgente.getChildNodes().item(j);

            if (noAgente2 != null && (!existNoFilho || noAgente2.getChildNodes().getLength() == 1)) {
                lstTipoItem.add(this.percorreListaXml(noAgente2.getParentNode(), ""));
                break;
            }
            if (noAgente2 == null || noAgente2.getNodeName() == null) {
                continue;
            }
            if (noAgente2.getNodeName().equals("#text")) {
                continue;
            }

            if (noAgente2.hasChildNodes()) {
                this.procurarProximoNode(noAgente2, true, lstTipoItem);
                break;
            } else {
                this.procurarProximoNode(noAgente2, false, lstTipoItem);
            }
        }
        return true;
    }

    /**
     * Percorre Atributos do Node
     *
     * OBS: String Identifica atributo atraves desse parametro (Caso queira percorre o node por completo deixar vazio)
     *
     * @param noAtual
     *            Node
     * @param campoIdentificacao
     *            String
     * @return TipoItemConfiguracaoDTO
     *
     */
    private TipoItemConfiguracaoDTO percorreListaXml(final Node noAtual, final String campoIdentificacao) {
        final TipoItemConfiguracaoDTO tpItemConfigbean = new TipoItemConfiguracaoDTO();
        ValorDTO valorbean = null;
        CaracteristicaDTO caracteristicabean = null;

        tpItemConfigbean.setTag(noAtual.getNodeName());
        tpItemConfigbean.setNome(noAtual.getNodeName());

        final List<CaracteristicaDTO> lstCaracteristica = new ArrayList<CaracteristicaDTO>();
        if (noAtual.getChildNodes().getLength() > 0) {
            for (int j = 0; j < noAtual.getChildNodes().getLength(); j++) {
                caracteristicabean = new CaracteristicaDTO();
                valorbean = new ValorDTO();

                final Node noAgente2 = noAtual.getChildNodes().item(j);

                if (noAgente2 == null || noAgente2.getNodeName() == null) {
                    continue;
                }
                if (!noAgente2.getNodeName().equals("#text")) {
                    if (!campoIdentificacao.equalsIgnoreCase("")) {
                        if (!noAgente2.getNodeName().equalsIgnoreCase(campoIdentificacao)) {
                            continue;
                        }
                    }
                    caracteristicabean.setNome(noAgente2.getNodeName());
                    caracteristicabean.setTipo("A");
                    caracteristicabean.setTag(noAgente2.getNodeName());
                    valorbean.setValorStr(noAgente2.getTextContent());
                    caracteristicabean.setValor(valorbean);
                    lstCaracteristica.add(caracteristicabean);
                } else {
                    continue;
                }
            }
        } else {
            valorbean = new ValorDTO();
            caracteristicabean = new CaracteristicaDTO();
            caracteristicabean.setNome(noAtual.getNodeName());
            caracteristicabean.setTipo("A");
            caracteristicabean.setTag(noAtual.getNodeName());
            valorbean.setValorStr(noAtual.getTextContent());
            caracteristicabean.setValor(valorbean);
            lstCaracteristica.add(caracteristicabean);
        }
        if (lstCaracteristica.size() > 0) {
            tpItemConfigbean.setCaracteristicas(lstCaracteristica);
            return tpItemConfigbean;
        } return null;
    }

    /**
     * Retorna Lista Preenchida pelos Node do xml
     *
     * @return Lista Item Configuracao
     */
    public List<ItemConfiguracaoDTO> getRetornaListaItemConfiguracao() {
        return listElementos;
    }

}
