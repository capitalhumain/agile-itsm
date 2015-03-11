package br.com.centralit.citcorpore.negocio;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ProblemaDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.service.CrudService;

public interface ComplemInfProblemaServicoService extends CrudService {
	
	
	public BaseEntity deserializaObjeto(String serialize) throws Exception;
    public void validaCreate(SolicitacaoServicoDTO solicitacaoServicoDto, BaseEntity model) throws Exception;
    public void validaDelete(SolicitacaoServicoDTO solicitacaoServicoDto, BaseEntity model) throws Exception;
    public void validaUpdate(SolicitacaoServicoDTO solicitacaoServicoDto, BaseEntity model) throws Exception;
	
	 /**
	  * Metodo de criação da entidade.
	 * @param tc
	 * @param problemaDto
	 * @param model
	 * @return
	 * @throws Exception
	 * @author thays.araujo	
	 */
	public BaseEntity create(TransactionControler tc, ProblemaDTO problemaDto, BaseEntity model) throws Exception;
	 
	 /**
	  * Metodo de alteração da entidade
	 * @param tc
	 * @param problemaDto
	 * @param model
	 * @return
	 * @throws Exception
	 * @author thays.araujo
	 */
	public void update(TransactionControler tc, ProblemaDTO problemaDto, BaseEntity model) throws Exception;

}
