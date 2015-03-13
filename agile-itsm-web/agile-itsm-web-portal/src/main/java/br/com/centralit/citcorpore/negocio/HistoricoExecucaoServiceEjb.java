package br.com.centralit.citcorpore.negocio;

import java.util.Collection;

import br.com.centralit.citcorpore.integracao.HistoricoExecucaoDao;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.CrudServiceImpl;

public class HistoricoExecucaoServiceEjb extends CrudServiceImpl implements HistoricoExecucaoService {

    private HistoricoExecucaoDao dao;

    @Override
    protected HistoricoExecucaoDao getDao() {
        if (dao == null) {
            dao = new HistoricoExecucaoDao();
        }
        return dao;
    }

    @Override
    public Collection findByDemanda(final Integer idDemanda) throws LogicException, ServiceException {
        try {
            return this.getDao().findByDemanda(idDemanda);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

}
