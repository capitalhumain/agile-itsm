package br.com.centralit.citcorpore.negocio;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.CatalogoServicoDTO;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.CrudService;

/**
 * 
 * @author pedro
 *
 */
public interface CatalogoServicoService extends CrudService {
	
	public Collection<CatalogoServicoDTO> listAllCatalogos() throws ServiceException, Exception;
	
	public void update(BaseEntity model) throws ServiceException, LogicException;
	
	public BaseEntity create(BaseEntity model) throws ServiceException, LogicException;
	 
	public boolean verificaSeCatalogoExiste(CatalogoServicoDTO catalogoServicoDTO) throws PersistenceException, ServiceException;
	 
	public Collection<CatalogoServicoDTO> listByIdContrato(Integer idContrato) throws ServiceException, Exception;
}
