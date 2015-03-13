package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.FaturaOSDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class OSAtividadePeriodicaDao extends CrudDaoDefaultImpl {

    public OSAtividadePeriodicaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idAtividadePeriodica", "idAtividadePeriodica", true, false, false, false));
        listFields.add(new Field("idOs", "idOs", true, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "OSAtividadePeriodica";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return FaturaOSDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdAtividadePeriodica(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idAtividadePeriodica", "=", parm));
        ordenacao.add(new Order("idOs"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdAtividadePeriodica(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idAtividadePeriodica", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdOs(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idOs", "=", parm));
        ordenacao.add(new Order("idAtividadePeriodica"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdOs(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idOs", "=", parm));
        super.deleteByCondition(condicao);
    }

}
