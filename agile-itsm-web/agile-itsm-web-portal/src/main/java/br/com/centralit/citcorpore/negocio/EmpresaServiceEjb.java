package br.com.centralit.citcorpore.negocio;

import br.com.centralit.citcorpore.bean.EmpresaDTO;
import br.com.centralit.citcorpore.integracao.EmpresaDao;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.service.CrudServiceImpl;

/**
 * @author rosana.godinho
 *
 */
public class EmpresaServiceEjb extends CrudServiceImpl implements EmpresaService {

    private EmpresaDao dao;

    @Override
    protected EmpresaDao getDao() {
        if (dao == null) {
            dao = new EmpresaDao();
        }
        return dao;
    }

    @Override
    protected void validaCreate(final Object obj) throws Exception {
        if (this.jaExisteRegistroComMesmoNome((EmpresaDTO) obj)) {
            throw new LogicException(this.i18nMessage("citcorpore.comum.registroJaCadastrado"));
        }
    }

    @Override
    public boolean jaExisteRegistroComMesmoNome(final EmpresaDTO empresa) {
        try {
            return this.getDao().jaExisteRegistroComMesmoNome(empresa);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
