/**
 * CentralIT - CITSmart.
 */
package br.com.centralit.citcorpore.negocio;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citcorpore.bean.CaracteristicaDTO;
import br.com.centralit.citcorpore.bean.CaracteristicaTipoItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.TipoItemConfiguracaoDTO;
import br.com.centralit.citcorpore.integracao.CaracteristicaTipoItemConfiguracaoDAO;
import br.com.centralit.citcorpore.integracao.TipoItemConfiguracaoDAO;
import br.com.centralit.citcorpore.util.WebUtil;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.integracao.TransactionControlerImpl;
import br.com.citframework.service.CrudServiceImpl;
import br.com.citframework.util.UtilDatas;

/**
 * EJB de Tipo Item Configuração.
 *
 * @author valdoilo.damasceno
 */
public class TipoItemConfiguracaoServiceEjb extends CrudServiceImpl implements TipoItemConfiguracaoService {

    private TipoItemConfiguracaoDAO dao;

    @Override
    protected TipoItemConfiguracaoDAO getDao() {
        if (dao == null) {
            dao = new TipoItemConfiguracaoDAO();
        }
        return dao;
    }

    private CaracteristicaTipoItemConfiguracaoDAO caracteristicaTipoItemConfiguracaoDao;

    private TipoItemConfiguracaoDTO tipoItemConfiguracaoBean;

    @Override
    public BaseEntity create(final BaseEntity tipoItemConfiguracao, final HttpServletRequest request) throws ServiceException, LogicException {
        setTipoItemConfiguracaoBean(tipoItemConfiguracao);

        final TransactionControler tc = new TransactionControlerImpl(getDao().getAliasDB());
        try {
            validaCreate(getTipoItemConfiguracaoBean());
            getDao().setTransactionControler(tc);
            getCaracteristicaTipoItemConfiguracaoDao().setTransactionControler(tc);

            tc.start();

            getTipoItemConfiguracaoBean().setDataInicio(UtilDatas.getDataAtual());
            getTipoItemConfiguracaoBean().setIdEmpresa(WebUtil.getIdEmpresa(request));
            getDao().create(tipoItemConfiguracao);

            criarEAssociarCaracteristicaAoTipoItemConfiguracao();

            tc.commit();
            tc.close();
        } catch (final Exception e) {
            e.printStackTrace();
            rollbackTransaction(tc, e);
        }
        return getTipoItemConfiguracaoBean();
    }

    @Override
    public void update(final BaseEntity tipoItemConfiguracao) throws ServiceException, LogicException {
        setTipoItemConfiguracaoBean(tipoItemConfiguracao);
        try {
            criarEAssociarCaracteristicaAoTipoItemConfiguracao();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        super.update(getTipoItemConfiguracaoBean());
    }

    /**
     * Associa Característica ao Tipo Item Configuração.
     *
     * @throws Exception
     * @author valdoilo.damasceno
     */
    private void criarEAssociarCaracteristicaAoTipoItemConfiguracao() throws Exception {
        if (getTipoItemConfiguracaoBean().getCaracteristicas() != null && !getTipoItemConfiguracaoBean().getCaracteristicas().isEmpty()) {
            for (int i = 0; i < getTipoItemConfiguracaoBean().getCaracteristicas().size(); i++) {
                final Integer idCaracteristica = ((CaracteristicaDTO) getTipoItemConfiguracaoBean().getCaracteristicas().get(i)).getIdCaracteristica();

                final CaracteristicaTipoItemConfiguracaoDTO caracteristicaTipoItemConfiguracaoBean = new CaracteristicaTipoItemConfiguracaoDTO();

                if (!getCaracteristicaTipoItemConfiguracaoDao().existeAssociacaoComCaracteristica(idCaracteristica, getTipoItemConfiguracaoBean().getId())) {
                    caracteristicaTipoItemConfiguracaoBean.setIdTipoItemConfiguracao(getTipoItemConfiguracaoBean().getId());
                    caracteristicaTipoItemConfiguracaoBean.setIdCaracteristica(idCaracteristica);
                    caracteristicaTipoItemConfiguracaoBean.setDataInicio(UtilDatas.getDataAtual());
                    getCaracteristicaTipoItemConfiguracaoDao().create(caracteristicaTipoItemConfiguracaoBean);
                    // throw new LogicException("teste");
                }
            }
        }
    }

    @Override
    public void restaurarGridCaracteristicas(final DocumentHTML document, final Collection<CaracteristicaDTO> caracteristicas) {
        if (caracteristicas != null && !caracteristicas.isEmpty()) {
            int count = 0;
            document.executeScript("countCaracteristica = 0");
            for (final CaracteristicaDTO caracteristicaBean : caracteristicas) {
                count++;

                document.executeScript("restoreRow()");
                document.executeScript("seqSelecionada = " + count);

                String caracteristica = caracteristicaBean.getNome() != null ? caracteristicaBean.getNome() : "";
                String tag = caracteristicaBean.getTag() != null ? caracteristicaBean.getTag() : "";
                String valor = caracteristicaBean.getValorString() != null ? caracteristicaBean.getValorString() : "";
                String descricao = caracteristicaBean.getDescricao() != null ? caracteristicaBean.getDescricao() : "";
                final String idEmpresa = caracteristicaBean.getIdEmpresa().toString() != null ? caracteristicaBean.getIdEmpresa().toString() : "";
                final String dataInicio = caracteristicaBean.getDataInicio().toString() != null ? caracteristicaBean.getDataInicio().toString() : "";
                final String dataFim = caracteristicaBean.getDataFim() != null ? caracteristicaBean.getDataFim().toString() : "";

                if (caracteristica != null) {
                    caracteristica = caracteristica.replaceAll("'", "");
                }
                if (tag != null) {
                    tag = tag.replaceAll("'", "");
                }
                if (valor != null) {
                    valor = valor.replaceAll("'", "");
                }
                if (descricao != null) {
                    descricao = descricao.replaceAll("'", "");
                }

                document.executeScript("setRestoreCaracteristica('" + caracteristicaBean.getIdCaracteristica() + "'," + "'"
                        + br.com.citframework.util.WebUtil.codificaEnter(caracteristica) + "'," + "'" + br.com.citframework.util.WebUtil.codificaEnter(tag)
                        + "'," + "'" + br.com.citframework.util.WebUtil.codificaEnter(StringEscapeUtils.escapeJavaScript(valor)) + "'," + "'"
                        + br.com.citframework.util.WebUtil.codificaEnter(descricao) + "'," + "'" + br.com.citframework.util.WebUtil.codificaEnter(idEmpresa)
                        + "'," + "'" + br.com.citframework.util.WebUtil.codificaEnter(dataInicio) + "'," + "'"
                        + br.com.citframework.util.WebUtil.codificaEnter(dataFim) + "')");
            }
            document.executeScript("exibeGrid()");
        } else {
            document.executeScript("ocultaGrid()");
        }
    }

    @Override
    public void restaurarGridCaracteristicasItemConfiguracaoFilho(final DocumentHTML document, final Collection<CaracteristicaDTO> caracteristicas) {
        if (caracteristicas != null && !caracteristicas.isEmpty()) {
            int count = 0;
            document.executeScript("countCaracteristica = 0");
            for (final CaracteristicaDTO caracteristicaBean : caracteristicas) {
                count++;

                document.executeScript("restoreRowCaracteristicaItemFilho()");
                document.executeScript("seqSelecionada = " + count);

                String caracteristica = caracteristicaBean.getNome() != null ? caracteristicaBean.getNome() : "";
                String tag = caracteristicaBean.getTag() != null ? caracteristicaBean.getTag() : "";
                String valor = caracteristicaBean.getValorString() != null ? caracteristicaBean.getValorString() : "";
                String descricao = caracteristicaBean.getDescricao() != null ? caracteristicaBean.getDescricao() : "";
                final String idEmpresa = caracteristicaBean.getIdEmpresa().toString() != null ? caracteristicaBean.getIdEmpresa().toString() : "";
                final String dataInicio = caracteristicaBean.getDataInicio().toString() != null ? caracteristicaBean.getDataInicio().toString() : "";
                final String dataFim = caracteristicaBean.getDataFim() != null ? caracteristicaBean.getDataFim().toString() : "";

                if (caracteristica != null) {
                    caracteristica = caracteristica.replaceAll("'", "");
                }
                if (tag != null) {
                    tag = tag.replaceAll("'", "");
                }
                if (valor != null) {
                    valor = valor.replaceAll("'", "");
                }
                if (descricao != null) {
                    descricao = descricao.replaceAll("'", "");
                }

                document.executeScript("setRestoreCaracteristicaItemConfiguracaoFilho('" + caracteristicaBean.getIdCaracteristica() + "'," + "'"
                        + br.com.citframework.util.WebUtil.codificaEnter(caracteristica) + "'," + "'" + br.com.citframework.util.WebUtil.codificaEnter(tag)
                        + "'," + "'" + br.com.citframework.util.WebUtil.codificaEnter(valor) + "'," + "'"
                        + br.com.citframework.util.WebUtil.codificaEnter(descricao) + "'," + "'" + br.com.citframework.util.WebUtil.codificaEnter(idEmpresa)
                        + "'," + "'" + br.com.citframework.util.WebUtil.codificaEnter(dataInicio) + "'," + "'"
                        + br.com.citframework.util.WebUtil.codificaEnter(dataFim) + "')");
            }
            document.executeScript("exibeGridPatrimonioItemFilho()");
        } else {
            document.executeScript("ocultaGridPatrimonioItemFilho()");
        }
    }

    /**
     * @return valor do atributo caracteristicaTipoItemConfiguracaoDao.
     * @author valdoilo.damasceno
     */
    private CaracteristicaTipoItemConfiguracaoDAO getCaracteristicaTipoItemConfiguracaoDao() {
        if (caracteristicaTipoItemConfiguracaoDao == null) {
            caracteristicaTipoItemConfiguracaoDao = new CaracteristicaTipoItemConfiguracaoDAO();
        }
        return caracteristicaTipoItemConfiguracaoDao;
    }

    /**
     * Retorna Tipo Item Configuração.
     *
     * @return TipoItemConfiguracaoDTO
     * @author VMD
     */
    private TipoItemConfiguracaoDTO getTipoItemConfiguracaoBean() {
        return tipoItemConfiguracaoBean;
    }

    /**
     * Configura Tipo Item Configuração.
     *
     * @param tipoItemConfiguracao
     * @author VMD
     */
    private void setTipoItemConfiguracaoBean(final BaseEntity tipoItemConfiguracao) {
        tipoItemConfiguracaoBean = (TipoItemConfiguracaoDTO) tipoItemConfiguracao;
    }

    @Override
    public boolean verificarSeTipoItemConfiguracaoExiste(final TipoItemConfiguracaoDTO tipoItemConfiguracao) throws PersistenceException {
        final TipoItemConfiguracaoDAO tipoItemConfiguracaoDao = new TipoItemConfiguracaoDAO();
        return tipoItemConfiguracaoDao.verificarSeTipoItemConfiguracaoExiste(tipoItemConfiguracao);
    }

    @Override
    public boolean verificaAssociacaoItemConfiguracao(final TipoItemConfiguracaoDTO tipoItemConfiguracao) throws PersistenceException {
        final TipoItemConfiguracaoDAO tipoItemConfiguracaoDAO = new TipoItemConfiguracaoDAO();
        return tipoItemConfiguracaoDAO.verificaAssociacaoTipoConfiguracao(tipoItemConfiguracao);
    }

}
