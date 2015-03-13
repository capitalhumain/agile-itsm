package br.com.centralit.citcorpore.negocio;

import br.com.centralit.citcorpore.bean.ControleContratoDTO;
import br.com.centralit.citcorpore.integracao.ControleContratoOcorrenciaDao;
import br.com.citframework.service.CrudServiceImpl;

/**
 * @author Pedro
 *
 */
public class ControleContratoOcorrenciaServiceEjb extends CrudServiceImpl implements ControleContratoOcorrenciaService {

    private ControleContratoOcorrenciaDao dao;

    @Override
    protected ControleContratoOcorrenciaDao getDao() {
        if (dao == null) {
            dao = new ControleContratoOcorrenciaDao();
        }
        return dao;
    }

    @Override
    public void deleteByIdControleContrato(final ControleContratoDTO controleContrato) throws Exception {
        this.getDao().deleteByIdControleContrato(controleContrato);
    }

}
