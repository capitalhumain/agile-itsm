package br.com.centralit.citcorpore.negocio;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.integracao.ContatoSolicitacaoServicoDao;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.CrudServiceImpl;

public class ContatoSolicitacaoServicoServiceEjb extends CrudServiceImpl implements ContatoSolicitacaoServicoService {

    private ContatoSolicitacaoServicoDao dao;

    @Override
    protected ContatoSolicitacaoServicoDao getDao() {
        if (dao == null) {
            dao = new ContatoSolicitacaoServicoDao();
        }
        return dao;
    }

    @Override
    public synchronized BaseEntity create(final BaseEntity model) throws ServiceException, LogicException {
        return super.create(model);
    }

}
