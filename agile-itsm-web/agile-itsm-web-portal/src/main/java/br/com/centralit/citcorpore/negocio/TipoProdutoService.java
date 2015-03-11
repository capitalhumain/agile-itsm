package br.com.centralit.citcorpore.negocio;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.TipoProdutoDTO;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.CrudService;
public interface TipoProdutoService extends CrudService {
    public List<TipoProdutoDTO> findByIdCategoria(Integer idCategoria) throws Exception;
    
    public boolean consultarTiposProdutos(TipoProdutoDTO tipoProdutoDTO) throws Exception;
    
    public BaseEntity create(BaseEntity model, HttpServletRequest request) throws ServiceException, LogicException, Exception;
    
    public void update(BaseEntity model, HttpServletRequest request) throws ServiceException, LogicException;
}
