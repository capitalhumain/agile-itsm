package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.GrupoAtvPeriodicaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class GrupoAtvPeriodicaDao extends CrudDaoDefaultImpl {

    public GrupoAtvPeriodicaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idGrupoAtvPeriodica", "idGrupoAtvPeriodica", true, false, false, false));
        listFields.add(new Field("nomeGrupoAtvPeriodica", "nomeGrupoAtvPeriodica", false, false, false, false));
        listFields.add(new Field("descGrupoAtvPeriodica", "descGrupoAtvPeriodica", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "GrupoAtvPeriodica";
    }

    @Override
    public Collection list() throws PersistenceException {
        return super.list("nomeGrupoAtvPeriodica");
    }

    @Override
    public Class getBean() {
        return GrupoAtvPeriodicaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByDescGrupoAtvPeriodica(final String parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("descGrupoAtvPeriodica", "=", parm));
        ordenacao.add(new Order(""));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByDescGrupoAtvPeriodica(final String parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("descGrupoAtvPeriodica", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection listGrupoAtividadePeriodicaAtiva() throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("deleted", "<>", "Y"));
        condicao.add(new Condition(Condition.OR, "deleted", "is", null));
        ordenacao.add(new Order("nomeGrupoAtvPeriodica"));
        return super.findByCondition(condicao, ordenacao);
    }

}
