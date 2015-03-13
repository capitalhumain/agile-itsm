package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.EventoGrupoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class EventoGrupoDao extends CrudDaoDefaultImpl {

    public EventoGrupoDao() {
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
        listFields.add(new Field("IDGRUPO", "idGrupo", true, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return "EVENTOGRUPO";
    }

    @Override
    public Collection<?> list() throws PersistenceException {
        return null;
    }

    @Override
    public Class<?> getBean() {
        return EventoGrupoDTO.class;
    }

    public void deleteByIdEvento(final Integer idEvento) throws PersistenceException {
        final List<Condition> lstCondicao = new ArrayList<Condition>();
        lstCondicao.add(new Condition(Condition.AND, "idEvento", "=", idEvento));
        super.deleteByCondition(lstCondicao);
    }

    @SuppressWarnings("unchecked")
    public Collection<EventoGrupoDTO> listByEvento(final Integer idEvento) throws PersistenceException {
        final String sql = "SELECT idevento, idgrupo FROM " + this.getTableName() + " WHERE idevento = ?";
        final List<?> dados = this.execSQL(sql, new Object[] {idEvento});
        final List<String> fields = new ArrayList<String>();
        fields.add("idEvento");
        fields.add("idGrupo");
        return this.listConvertion(this.getBean(), dados, fields);
    }

    /**
     *
     * @param idGrupo
     * @return Collection de Contrato
     * @throws Exception
     */
    public Collection findByIdGrupo(final Integer idGrupo) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idGrupo", "=", idGrupo));
        ordenacao.add(new Order("idGrupo"));
        return super.findByCondition(condicao, ordenacao);
    }

}
