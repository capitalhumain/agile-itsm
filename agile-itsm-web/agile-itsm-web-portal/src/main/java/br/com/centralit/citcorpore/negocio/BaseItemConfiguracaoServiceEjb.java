package br.com.centralit.citcorpore.negocio;

import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.BaseItemConfiguracaoDTO;
import br.com.centralit.citcorpore.integracao.BaseItemConfiguracaoDAO;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.integracao.TransactionControlerImpl;
import br.com.citframework.service.CrudServiceImpl;
import br.com.citframework.util.UtilDatas;

/**
 * Service EJB de BaseItemConfiguracao.
 *
 * @author valdoilo.damasceno
 *
 */
public class BaseItemConfiguracaoServiceEjb extends CrudServiceImpl implements BaseItemConfiguracaoService {

    private BaseItemConfiguracaoDAO dao;

    @Override
    protected BaseItemConfiguracaoDAO getDao() {
        if (dao == null) {
            dao = new BaseItemConfiguracaoDAO();
        }
        return dao;
    }

    @Override
    public BaseEntity[] create(final BaseItemConfiguracaoDTO[] baseItemConfiguracao) throws ServiceException, LogicException {

        final TransactionControler transactionControler = new TransactionControlerImpl(getDao().getAliasDB());
        try {

            getDao().setTransactionControler(transactionControler);

            transactionControler.start();

            baseItemConfiguracao[0].setDataInicio(UtilDatas.getDataAtual());
            baseItemConfiguracao[0] = (BaseItemConfiguracaoDTO) getDao().create(baseItemConfiguracao[0]);

            for (int i = 1; i < baseItemConfiguracao.length; i++) {
                if (baseItemConfiguracao[i] != null) {
                    baseItemConfiguracao[i].setIdBaseItemConfiguracaoPai(baseItemConfiguracao[0].getId());
                    baseItemConfiguracao[i] = (BaseItemConfiguracaoDTO) getDao().create(baseItemConfiguracao[i]);
                }
            }

            transactionControler.commit();

        } catch (final Exception e) {
            e.printStackTrace();
            rollbackTransaction(transactionControler, e);
        } finally {
            try {
                transactionControler.close();
            } catch (final PersistenceException e) {
                e.printStackTrace();
            }
        }

        return baseItemConfiguracao;
    }

    @Override
    public void update(final BaseItemConfiguracaoDTO[] vetorBaseItemConfiguracao) throws ServiceException, LogicException {

        final TransactionControler transactionControler = new TransactionControlerImpl(getDao().getAliasDB());
        try {

            getDao().setTransactionControler(transactionControler);

            transactionControler.start();

            final List<BaseEntity> filhos = restoreChildren(vetorBaseItemConfiguracao[0]);

            getDao().update(vetorBaseItemConfiguracao[0]);

            for (int i = 1; i < vetorBaseItemConfiguracao.length; i++) {
                if (vetorBaseItemConfiguracao[i] != null) {
                    vetorBaseItemConfiguracao[i].setIdBaseItemConfiguracaoPai(vetorBaseItemConfiguracao[0].getId());
                    for (final BaseEntity iDto : filhos) {
                        final BaseItemConfiguracaoDTO dto = (BaseItemConfiguracaoDTO) iDto;
                        if (dto.getTipoexecucao().equals(vetorBaseItemConfiguracao[i].getTipoexecucao())) {
                            vetorBaseItemConfiguracao[i].setId(dto.getId());
                            break;
                        }
                    }
                    if (vetorBaseItemConfiguracao[i].getId() != null) {
                        getDao().update(vetorBaseItemConfiguracao[i]);
                    } else {
                        getDao().create(vetorBaseItemConfiguracao[i]);
                    }
                }
            }

            transactionControler.commit();

        } catch (final Exception e) {
            e.printStackTrace();
            rollbackTransaction(transactionControler, e);
        } finally {
            transactionControler.closeQuietly();
        }
    }

    @Override
    public List<BaseEntity> restoreChildren(final BaseItemConfiguracaoDTO baseItemConfiguracaoBean) throws Exception {
        return getDao().restoreFilhos(baseItemConfiguracaoBean);
    }

    @Override
    public boolean existBaseItemConfiguracao(final BaseItemConfiguracaoDTO dto) throws Exception {
        return getDao().existBaseItemConfiguracao(dto);
    }

}
