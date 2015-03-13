package br.com.centralit.citcorpore.negocio;

import br.com.centralit.citcorpore.bean.ComandoDTO;
import br.com.centralit.citcorpore.integracao.ComandoDao;
import br.com.citframework.service.CrudServiceImpl;

/**
 * @author ygor.magalhaes
 *
 */
public class ComandoServiceEjb extends CrudServiceImpl implements ComandoService {

    private ComandoDao dao;

    @Override
    protected ComandoDao getDao() {
        if (dao == null) {
            dao = new ComandoDao();
        }
        return dao;
    }

    @Override
    public ComandoDTO listItemCadastrado(final String descricao) throws Exception {
        return this.getDao().listItemCadastrado(descricao);
    }

}
