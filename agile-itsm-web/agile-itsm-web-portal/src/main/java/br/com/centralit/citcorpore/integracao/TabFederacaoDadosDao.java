package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.TabFederacaoDadosDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class TabFederacaoDadosDao extends CrudDaoDefaultImpl {

    public TabFederacaoDadosDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("nomeTabela", "nomeTabela", true, false, false, false));
        listFields.add(new Field("chaveFinal", "chaveFinal", true, false, false, false));
        listFields.add(new Field("chaveOriginal", "chaveOriginal", false, false, false, false));
        listFields.add(new Field("origem", "origem", false, false, false, false));
        listFields.add(new Field("criacao", "criacao", false, false, false, false));
        listFields.add(new Field("ultAtualiz", "ultAtualiz", false, false, false, false));
        return listFields;
    }

    public Collection findByOrigemTabelaChaveOriginal(final String origem, final String nomeTab, final String chaveOrig) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("origem", "=", origem));
        condicao.add(new Condition("nomeTabela", "=", nomeTab));
        condicao.add(new Condition("chaveOriginal", "=", chaveOrig));
        ordenacao.add(new Order("chaveFinal"));
        return super.findByCondition(condicao, ordenacao);
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "TabFederacaoDados";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return TabFederacaoDadosDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByNomeTabela(final String parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("nomeTabela", "=", parm));
        ordenacao.add(new Order("chaveFinal"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByNomeTabela(final String parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("nomeTabela", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByChaveFinal(final String parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("chaveFinal", "=", parm));
        ordenacao.add(new Order("chaveOriginal"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByChaveFinal(final String parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("chaveFinal", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByChaveOriginal(final String parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("chaveOriginal", "=", parm));
        ordenacao.add(new Order("chaveFinal"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByChaveOriginal(final String parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("chaveOriginal", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByOrigem(final String parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("origem", "=", parm));
        ordenacao.add(new Order("chaveFinal"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByOrigem(final String parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("origem", "=", parm));
        super.deleteByCondition(condicao);
    }

}
