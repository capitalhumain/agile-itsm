package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.TipoMovimFinanceiraViagemDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * @author ronnie.lopes
 *
 */
public class TipoMovimFinanceiraViagemDao extends CrudDaoDefaultImpl {

    public TipoMovimFinanceiraViagemDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idtipoMovimFinanceiraViagem", "idtipoMovimFinanceiraViagem", true, true, false, false));
        listFields.add(new Field("nome", "nome", false, false, false, false));
        listFields.add(new Field("descricao", "descricao", false, false, false, false));
        listFields.add(new Field("classificacao", "classificacao", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        listFields.add(new Field("exigePrestacaoConta", "exigePrestacaoConta", false, false, false, false));
        listFields.add(new Field("exigeDataHoraCotacao", "exigeDataHoraCotacao", false, false, false, false));
        listFields.add(new Field("permiteAdiantamento", "permiteAdiantamento", false, false, false, false));
        listFields.add(new Field("valorPadrao", "valorPadrao", false, false, false, false));
        listFields.add(new Field("tipo", "tipo", false, false, false, false));
        listFields.add(new Field("imagem", "imagem", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "tipoMovimFinanceiraViagem";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List ordem = new ArrayList<>();
        ordem.add(new Order("nome"));
        return super.find(obj, ordem);
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("nome"));
        return super.list(list);
    }

    @Override
    public Class getBean() {
        return TipoMovimFinanceiraViagemDTO.class;
    }

    /**
     *
     * @param classificacao
     * @return
     * @throws Exception
     *             geber.costa
     *
     *             Método que traz todos os nomes da classificação escolhida(passada por parâmetro)
     */
    public List<TipoMovimFinanceiraViagemDTO> listByClassificacao(final String classificacao) throws PersistenceException {
        final String sql = "select nome,idtipoMovimFinanceiraViagem from " + this.getTableName() + " where classificacao = ? and situacao like 'A'";
        final List parametro = new ArrayList<>();
        final List<String> listRetorno = new ArrayList<String>();
        parametro.add(classificacao);
        listRetorno.add("nome");
        listRetorno.add("idtipoMovimFinanceiraViagem");
        final List lista = this.execSQL(sql, parametro.toArray());
        return engine.listConvertion(TipoMovimFinanceiraViagemDTO.class, lista, listRetorno);
    }

    public TipoMovimFinanceiraViagemDTO findByMovimentacao(final Long idtipoMovimFinanceiraViagem) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        ArrayList<TipoMovimFinanceiraViagemDTO> result = new ArrayList<TipoMovimFinanceiraViagemDTO>();

        condicao.add(new Condition("idtipoMovimFinanceiraViagem", "=", idtipoMovimFinanceiraViagem));
        condicao.add(new Condition("situacao", "=", "A"));

        result = (ArrayList<TipoMovimFinanceiraViagemDTO>) super.findByCondition(condicao, null);

        if (result != null && !result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }

    public TipoMovimFinanceiraViagemDTO findByMovimentacaoEstadoAdiantamento(final Long idtipoMovimFinanceiraViagem, final String adiantamento)
            throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        ArrayList<TipoMovimFinanceiraViagemDTO> result = new ArrayList<TipoMovimFinanceiraViagemDTO>();

        condicao.add(new Condition("idtipoMovimFinanceiraViagem", "=", idtipoMovimFinanceiraViagem));
        condicao.add(new Condition("permiteAdiantamento", "=", adiantamento));
        condicao.add(new Condition("situacao", "=", "A"));

        result = (ArrayList<TipoMovimFinanceiraViagemDTO>) super.findByCondition(condicao, null);

        if (result != null && !result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }

    public List<TipoMovimFinanceiraViagemDTO> recuperaTiposAtivos() throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("situacao", "=", "A"));
        return (List<TipoMovimFinanceiraViagemDTO>) super.findByCondition(condicao, null);
    }

}
