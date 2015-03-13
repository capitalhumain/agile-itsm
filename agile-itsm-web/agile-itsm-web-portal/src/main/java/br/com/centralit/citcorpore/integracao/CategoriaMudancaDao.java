package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.CategoriaMudancaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class CategoriaMudancaDao extends CrudDaoDefaultImpl {

    public CategoriaMudancaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idCategoriaMudanca", "idCategoriaMudanca", true, false, false, false));
        listFields.add(new Field("idCategoriaMudancaPai", "idCategoriaMudancaPai", false, false, false, false));
        listFields.add(new Field("nomeCategoria", "nomeCategoria", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        listFields.add(new Field("idTipoFluxo", "idTipoFluxo", false, false, false, false));
        listFields.add(new Field("idModeloEmailCriacao", "idModeloEmailCriacao", false, false, false, false));
        listFields.add(new Field("idModeloEmailFinalizacao", "idModeloEmailFinalizacao", false, false, false, false));
        listFields.add(new Field("idModeloEmailAcoes", "idModeloEmailAcoes", false, false, false, false));
        listFields.add(new Field("idGrupoNivel1", "idGrupoNivel1", false, false, false, false));
        listFields.add(new Field("idGrupoExecutor", "idGrupoExecutor", false, false, false, false));
        listFields.add(new Field("idCalendario", "idCalendario", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "categoriamudanca";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return CategoriaMudancaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdCategoriaMudanca(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCategoriaMudanca", "=", parm));
        ordenacao.add(new Order("idCategoriaMudanca"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdCategoriaMudanca(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idCategoriaMudanca", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdCategoriaMudancaPai(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCategoriaMudancaPai", "=", parm));
        ordenacao.add(new Order("idCategoriaMudancaPai"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdCategoriaMudancaSemPai(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();

        condicao.add(new Condition("idCategoriaMudanca", "=", parm));
        condicao.add(new Condition("idCategoriaMudancaPai", "IS", null));

        return super.findByCondition(condicao, null);
    }

    public Collection findCategoriaMudancaSemPai() throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();

        condicao.add(new Condition("idCategoriaMudancaPai", "IS", null));

        return super.findByCondition(condicao, null);
    }

    public void deleteByIdCategoriaMudancaPai(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idCategoriaMudancaPai", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByNomeCategoria(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("nomeCategoria", "=", parm));
        ordenacao.add(new Order("nomeCategoria"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByNomeCategoria(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("nomeCategoria", "=", parm));
        super.deleteByCondition(condicao);
    }

}
