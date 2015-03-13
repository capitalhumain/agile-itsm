package br.com.centralit.citcorpore.integracao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ExcecaoCalendarioDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ExcecaoCalendarioDao extends CrudDaoDefaultImpl {

    public ExcecaoCalendarioDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idExcecaoCalendario", "idExcecaoCalendario", true, true, false, false));
        listFields.add(new Field("idCalendario", "idCalendario", false, false, false, false));
        listFields.add(new Field("idJornada", "idJornada", false, false, false, false));
        listFields.add(new Field("tipo", "tipo", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataTermino", "dataTermino", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "ExcecaoCalendario";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ExcecaoCalendarioDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdCalendario(final Integer idCalendario) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCalendario", "=", idCalendario));
        ordenacao.add(new Order("dataInicio"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdCalendario(final Integer idCalendario) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idCalendario", "=", idCalendario));
        super.deleteByCondition(condicao);
    }

    public ExcecaoCalendarioDTO findByIdCalendarioAndData(final Integer idCalendario, final Date data) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCalendario", "=", idCalendario));
        condicao.add(new Condition("dataInicio", "<=", data));
        condicao.add(new Condition("dataTermino", ">=", data));
        ordenacao.add(new Order("dataInicio"));
        final List result = (List) super.findByCondition(condicao, ordenacao);
        if (result != null && !result.isEmpty()) {
            return (ExcecaoCalendarioDTO) result.get(0);
        }
        return null;
    }

}
