package br.com.centralit.citcorpore.negocio;

import br.com.centralit.citcorpore.integracao.ControleContratoVersaoDao;
import br.com.citframework.service.CrudServiceImpl;

/**
 * @author Pedro
 *
 */
public class ControleContratoVersaoServiceEjb extends CrudServiceImpl implements ControleContratoVersaoService {

    private ControleContratoVersaoDao dao;

    @Override
    protected ControleContratoVersaoDao getDao() {
        if (dao == null) {
            dao = new ControleContratoVersaoDao();
        }
        return dao;
    }

}
