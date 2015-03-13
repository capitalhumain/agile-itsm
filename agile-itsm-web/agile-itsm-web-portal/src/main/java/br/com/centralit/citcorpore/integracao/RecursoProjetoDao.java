package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.RecursoProjetoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class RecursoProjetoDao extends CrudDaoDefaultImpl {

    public RecursoProjetoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idProjeto", "idProjeto", true, false, false, false));
        listFields.add(new Field("idEmpregado", "idEmpregado", true, false, false, false));
        listFields.add(new Field("custoHora", "custoHora", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "RecursoProjeto";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return RecursoProjetoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdProjeto(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idProjeto", "=", parm));
        ordenacao.add(new Order("idEmpregado"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdProjeto(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idProjeto", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdEmpregado(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idEmpregado", "=", parm));
        ordenacao.add(new Order("idProjeto"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdEmpregado(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idEmpregado", "=", parm));
        super.deleteByCondition(condicao);
    }

}
