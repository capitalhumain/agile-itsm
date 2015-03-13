package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.CategoriaProdutoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class CategoriaProdutoDao extends CrudDaoDefaultImpl {

    private static final String SQL_GET_LISTA_SEM_PAI = "SELECT idCategoria, idCategoriaPai, nomeCategoria, situacao " + "  FROM categoriaproduto "
            + " WHERE idCategoriaPai IS NULL " + " ORDER BY nomeCategoria";

    public CategoriaProdutoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idCategoria", "idCategoria", true, true, false, false));
        listFields.add(new Field("idCategoriaPai", "idCategoriaPai", false, false, false, false));
        listFields.add(new Field("nomeCategoria", "nomeCategoria", false, false, false, false));
        listFields.add(new Field("pesoCotacaoPreco", "pesoCotacaoPreco", false, false, false, false));
        listFields.add(new Field("pesoCotacaoPrazoEntrega", "pesoCotacaoPrazoEntrega", false, false, false, false));
        listFields.add(new Field("pesoCotacaoPrazoPagto", "pesoCotacaoPrazoPagto", false, false, false, false));
        listFields.add(new Field("pesoCotacaoTaxaJuros", "pesoCotacaoTaxaJuros", false, false, false, false));
        listFields.add(new Field("pesoCotacaoPrazoGarantia", "pesoCotacaoPrazoGarantia", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "categoriaproduto";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return CategoriaProdutoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection<CategoriaProdutoDTO> findSemPai() throws PersistenceException {
        final Object[] objs = new Object[] {};
        final List lista = this.execSQL(SQL_GET_LISTA_SEM_PAI, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idCategoria");
        listRetorno.add("idCategoriaPai");
        listRetorno.add("nomeCategoria");
        listRetorno.add("situacao");

        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        return result;
    }

    public Collection<CategoriaProdutoDTO> findByIdPai(final Integer idPai) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCategoriaPai", "=", idPai));
        ordenacao.add(new Order("nomeCategoria"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * Se existir igual retorna true.
     */
    public boolean existeIgual(final CategoriaProdutoDTO categoriaProduto) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("nomeCategoria", "=", categoriaProduto.getNomeCategoria()));
        if (categoriaProduto.getIdCategoria() != null) {
            condicao.add(new Condition("idCategoria", "<>", categoriaProduto.getIdCategoria()));
        }
        ordenacao.add(new Order("nomeCategoria"));

        final List result = (List) super.findByCondition(condicao, ordenacao);

        return result != null && !result.isEmpty();
    }

}
