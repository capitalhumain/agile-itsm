package br.com.centralit.citcorpore.negocio;

import br.com.centralit.citcorpore.integracao.PagamentoDao;
import br.com.citframework.service.CrudServiceImpl;

/**
 * @author Pedro
 *
 */
public class PagamentoServiceEjb extends CrudServiceImpl implements PagamentoService {

    private PagamentoDao dao;

    @Override
    protected PagamentoDao getDao() {
        if (dao == null) {
            dao = new PagamentoDao();
        }
        return dao;
    }

}
