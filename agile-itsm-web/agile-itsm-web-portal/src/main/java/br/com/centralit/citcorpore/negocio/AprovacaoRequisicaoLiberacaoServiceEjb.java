package br.com.centralit.citcorpore.negocio;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.citframework.integracao.CrudDAO;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.service.CrudServiceImpl;

public class AprovacaoRequisicaoLiberacaoServiceEjb extends CrudServiceImpl implements AprovacaoRequisicaoLiberacaoService {

    @Override
    public BaseEntity deserializaObjeto(final String serialize) throws Exception {
        return null;
    }

    @Override
    public void validaCreate(final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception {

    }

    @Override
    public void validaDelete(final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception {

    }

    @Override
    public void validaUpdate(final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception {

    }

    @Override
    public BaseEntity create(final TransactionControler tc, final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception {
        return null;
    }

    @Override
    public void update(final TransactionControler tc, final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception {

    }

    @Override
    public void delete(final TransactionControler tc, final SolicitacaoServicoDTO solicitacaoServicoDto, final BaseEntity model) throws Exception {
        

    }

    @SuppressWarnings("rawtypes")
    @Override
    public Collection findByIdSolicitacaoServico(final Integer parm) throws Exception {
        
        return null;
    }

    @Override
    public void deleteByIdSolicitacaoServico(final Integer parm) throws Exception {
        

    }

    @Override
    protected CrudDAO getDao() {
        return null;
    }

}
