package br.com.centralit.citcorpore.negocio;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.service.CrudService;

public interface AprovacaoRequisicaoLiberacaoService extends CrudService {
	
	public BaseEntity deserializaObjeto(String serialize) throws Exception;
    public void validaCreate(SolicitacaoServicoDTO solicitacaoServicoDto, BaseEntity model) throws Exception;
    public void validaDelete(SolicitacaoServicoDTO solicitacaoServicoDto, BaseEntity model) throws Exception;
    public void validaUpdate(SolicitacaoServicoDTO solicitacaoServicoDto, BaseEntity model) throws Exception;

    public BaseEntity create(TransactionControler tc, SolicitacaoServicoDTO solicitacaoServicoDto, BaseEntity model) throws Exception;
    public void update(TransactionControler tc, SolicitacaoServicoDTO solicitacaoServicoDto, BaseEntity model) throws Exception;
    public void delete(TransactionControler tc, SolicitacaoServicoDTO solicitacaoServicoDto, BaseEntity model) throws Exception;
    
	public Collection findByIdSolicitacaoServico(Integer parm) throws Exception;
	public void deleteByIdSolicitacaoServico(Integer parm) throws Exception;

}
