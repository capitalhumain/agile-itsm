package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.EventoItemConfigRelDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

/**
 * DAO da tabela de relacionamento entre item de configuração e evento
 *
 * @author diego.rezende
 *
 */
public class EventoItemConfigRelDao extends CrudDaoDefaultImpl {

    public EventoItemConfigRelDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<?> find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<Field>();
        listFields.add(new Field("IDEVENTO", "idEvento", true, false, false, false));
        listFields.add(new Field("IDITEMCONFIGURACAO", "idItemConfiguracao", true, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return "EVENTOITEMCONFIGURACAO";
    }

    @Override
    public Collection<?> list() throws PersistenceException {
        return null;
    }

    @Override
    public Class<?> getBean() {
        return EventoItemConfigRelDTO.class;
    }

    public void deleteByIdEvento(final Integer idEvento) throws PersistenceException {
        final List<Condition> lstCondicao = new ArrayList<Condition>();
        lstCondicao.add(new Condition(Condition.AND, "idEvento", "=", idEvento));
        super.deleteByCondition(lstCondicao);
    }

    public Collection<EventoItemConfigRelDTO> listByEvento(final Integer idEvento) throws PersistenceException {
        final String sql = "SELECT idevento, iditemconfiguracao FROM " + this.getTableName() + " WHERE idevento = ?";
        final List<?> dados = this.execSQL(sql, new Object[] {idEvento});
        final List<String> fields = new ArrayList<String>();
        fields.add("idEvento");
        fields.add("idItemConfiguracao");
        return this.listConvertion(this.getBean(), dados, fields);
    }

}
