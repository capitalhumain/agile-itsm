package br.com.centralit.citcorpore.negocio;

import java.util.Collection;

import br.com.centralit.citcorpore.integracao.PostDAO;
import br.com.citframework.service.CrudServiceImpl;

/**
 * @author Flávio.santana
 *
 */
public class PostServiceEjb extends CrudServiceImpl implements PostService {

    private PostDAO dao;

    @Override
    protected PostDAO getDao() {
        if (dao == null) {
            dao = new PostDAO();
        }
        return dao;
    }

    @Override
    public Collection listNotNull() throws Exception {
        return this.getDao().listNotNull();
    }

}
