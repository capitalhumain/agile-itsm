package br.com.centralit.citcorpore.negocio;
import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.service.CrudService;
public interface BIDashBoardService extends CrudService {
	public BaseEntity getByIdentificacao(String ident) throws Exception;
}
