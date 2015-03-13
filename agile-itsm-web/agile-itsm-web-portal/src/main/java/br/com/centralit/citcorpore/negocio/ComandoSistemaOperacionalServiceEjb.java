package br.com.centralit.citcorpore.negocio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.centralit.citcorpore.bean.ComandoSistemaOperacionalDTO;
import br.com.centralit.citcorpore.integracao.ComandoSistemaOperacionalDao;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.service.CrudServiceImpl;

/**
 * @author ygor.magalhaes
 *
 */

public class ComandoSistemaOperacionalServiceEjb extends CrudServiceImpl implements ComandoSistemaOperacionalService {

    private ComandoSistemaOperacionalDao dao;

    @Override
    protected ComandoSistemaOperacionalDao getDao() {
        if (dao == null) {
            dao = new ComandoSistemaOperacionalDao();
        }
        return dao;
    }

    @Override
    public ComandoSistemaOperacionalDTO pegarComandoSO(final String so, final String tipoExecucao) throws ServiceException {
        try {
            return this.getDao().pegarComandoSO(so, tipoExecucao);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean pesquisarExistenciaComandoSO(final ComandoSistemaOperacionalDTO comandoSODTO) throws ServiceException {
        boolean jaExiste = false;

        try {
            final List<Condition> condicoes = new ArrayList<>();

            condicoes.add(new Condition("comando", "=", comandoSODTO.getComando()));
            condicoes.add(new Condition("idComando", "=", comandoSODTO.getIdComando()));
            condicoes.add(new Condition("idSistemaOperacional", "=", comandoSODTO.getIdSistemaOperacional()));

            final Collection colecao = this.getDao().findByCondition(condicoes, null);

            if (colecao != null && !colecao.isEmpty()) {
                jaExiste = true;
            }
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
        return jaExiste;
    }

}
