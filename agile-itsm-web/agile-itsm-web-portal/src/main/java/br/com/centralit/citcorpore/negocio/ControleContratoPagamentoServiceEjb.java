package br.com.centralit.citcorpore.negocio;

import br.com.centralit.citcorpore.bean.ControleContratoDTO;
import br.com.centralit.citcorpore.integracao.ControleContratoPagamentoDao;
import br.com.citframework.service.CrudServiceImpl;

/**
 * @author Pedro
 *
 */
public class ControleContratoPagamentoServiceEjb extends CrudServiceImpl implements ControleContratoPagamentoService {

    private ControleContratoPagamentoDao dao;

    @Override
    protected ControleContratoPagamentoDao getDao() {
        if (dao == null) {
            dao = new ControleContratoPagamentoDao();
        }
        return dao;
    }

    @Override
    public void deleteByIdControleContrato(final ControleContratoDTO controleContrato) throws Exception {
        this.getDao().deleteByIdControleContrato(controleContrato);
    }

}
