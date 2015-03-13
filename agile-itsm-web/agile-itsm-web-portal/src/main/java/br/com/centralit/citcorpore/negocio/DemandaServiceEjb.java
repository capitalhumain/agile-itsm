package br.com.centralit.citcorpore.negocio;

import java.util.Collection;

import br.com.centralit.citcorpore.integracao.DemandaDao;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.CrudServiceImpl;

public class DemandaServiceEjb extends CrudServiceImpl implements DemandaService {

    private DemandaDao dao;

    @Override
    protected DemandaDao getDao() {
        if (dao == null) {
            dao = new DemandaDao();
        }
        return dao;
    }

    @Override
    public Collection findByIdOS(final Integer parm) throws Exception {
        try {
            return this.getDao().findByIdOS(parm);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

}
