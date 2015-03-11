/**
 * 
 */
package br.com.centralit.citcorpore.negocio;

import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.HistoricoMudancaDTO;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaDTO;
import br.com.citframework.service.CrudService;

public interface HistoricoMudancaService extends CrudService {
	
	public List<HistoricoMudancaDTO> listHistoricoMudancaByIdRequisicaoMudanca(Integer idRequisicaoMudanca) throws Exception;
	public void updateNotNull(BaseEntity obj) throws Exception ;
	public HistoricoMudancaDTO maxIdHistorico(RequisicaoMudancaDTO requisicaoMudancaDTO) throws Exception;
	
}