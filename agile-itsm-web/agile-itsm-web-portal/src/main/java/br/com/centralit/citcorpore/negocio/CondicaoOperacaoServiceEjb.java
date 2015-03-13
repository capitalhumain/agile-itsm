package br.com.centralit.citcorpore.negocio;

import java.util.ArrayList;
import java.util.Collection;

import br.com.centralit.citcorpore.integracao.CondicaoOperacaoDao;
import br.com.citframework.integracao.Condition;
import br.com.citframework.service.CrudServiceImpl;

/**
 * @author ygor.magalhaes
 *
 */
public class CondicaoOperacaoServiceEjb extends CrudServiceImpl implements CondicaoOperacaoService {

    private CondicaoOperacaoDao dao;

    @Override
    protected CondicaoOperacaoDao getDao() {
        if (dao == null) {
            dao = new CondicaoOperacaoDao();
        }
        return dao;
    }

    @Override
    public boolean jaExisteCondicaoComMesmoNome(final String nome) {
        final ArrayList<Condition> condicoes = new ArrayList<Condition>();
        condicoes.add(new Condition("nomeCondicaoOperacao", "=", nome));
        condicoes.add(new Condition("dataFim", "is", null));
        Collection retorno = null;
        try {
            retorno = this.getDao().findByCondition(condicoes, null);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return retorno == null ? false : true;
    }

}
