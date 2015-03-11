package br.com.centralit.citcorpore.negocio;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.CrudService;

public interface ExecucaoDemandaService extends CrudService {
	public Collection getAtividadesByGrupoAndPessoa(Integer idEmpregado, String[] grupo) throws LogicException, ServiceException;
	public void updateAtribuir(BaseEntity bean) throws LogicException, ServiceException;
	public void updateStatus(BaseEntity bean) throws LogicException, ServiceException;
	public void updateFinalizar(BaseEntity bean) throws LogicException, ServiceException;
	public boolean temAtividadeNaSequencia(BaseEntity bean) throws LogicException, ServiceException;
}
