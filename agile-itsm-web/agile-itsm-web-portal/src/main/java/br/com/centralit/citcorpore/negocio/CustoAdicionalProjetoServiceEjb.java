package br.com.centralit.citcorpore.negocio;

import br.com.centralit.citcorpore.integracao.CustoAdicionalProjetoDao;
import br.com.citframework.service.CrudServiceImpl;

public class CustoAdicionalProjetoServiceEjb extends CrudServiceImpl implements CustoAdicionalProjetoService {

    private CustoAdicionalProjetoDao dao;

    @Override
    protected CustoAdicionalProjetoDao getDao() {
        if (dao == null) {
            dao = new CustoAdicionalProjetoDao();
        }
        return dao;
    }

}
