package br.com.centralit.citcorpore.negocio;

import br.com.centralit.citcorpore.integracao.ModuloSistemaDao;
import br.com.citframework.service.CrudServiceImpl;

/**
 * @author Pedro
 *
 */
public class ModuloSistemaServiceEjb extends CrudServiceImpl implements ModuloSistemaService {

    private ModuloSistemaDao dao;

    @Override
    protected ModuloSistemaDao getDao() {
        if (dao == null) {
            dao = new ModuloSistemaDao();
        }
        return dao;
    }

}
