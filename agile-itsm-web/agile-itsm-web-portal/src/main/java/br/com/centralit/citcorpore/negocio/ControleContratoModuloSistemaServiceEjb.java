package br.com.centralit.citcorpore.negocio;

import br.com.centralit.citcorpore.bean.ControleContratoDTO;
import br.com.centralit.citcorpore.integracao.ControleContratoModuloSistemaDao;
import br.com.citframework.service.CrudServiceImpl;

/**
 * @author Pedro
 *
 */
public class ControleContratoModuloSistemaServiceEjb extends CrudServiceImpl implements ControleContratoModuloSistemaService {

    private ControleContratoModuloSistemaDao dao;

    @Override
    protected ControleContratoModuloSistemaDao getDao() {
        if (dao == null) {
            dao = new ControleContratoModuloSistemaDao();
        }
        return dao;
    }

    @Override
    public void deleteByIdControleContrato(final ControleContratoDTO controleContrato) throws Exception {
        this.getDao().deleteByIdControleContrato(controleContrato);
    }

}
