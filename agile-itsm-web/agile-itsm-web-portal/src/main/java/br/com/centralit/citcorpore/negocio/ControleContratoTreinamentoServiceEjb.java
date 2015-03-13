package br.com.centralit.citcorpore.negocio;

import br.com.centralit.citcorpore.bean.ControleContratoDTO;
import br.com.centralit.citcorpore.integracao.ControleContratoTreinamentoDao;
import br.com.citframework.service.CrudServiceImpl;

/**
 * @author Pedro
 *
 */
public class ControleContratoTreinamentoServiceEjb extends CrudServiceImpl implements ControleContratoTreinamentoService {

    private ControleContratoTreinamentoDao dao;

    @Override
    protected ControleContratoTreinamentoDao getDao() {
        if (dao == null) {
            dao = new ControleContratoTreinamentoDao();
        }
        return dao;
    }

    @Override
    public void deleteByIdControleContrato(final ControleContratoDTO controleContrato) throws Exception {
        this.getDao().deleteByIdControleContrato(controleContrato);
    }

}
