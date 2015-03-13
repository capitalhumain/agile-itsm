package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.EventoEmpregadoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class EventoEmpregadoDao extends CrudDaoDefaultImpl {

    public EventoEmpregadoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return EventoEmpregadoDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDEVENTO", "idEvento", true, false, false, false));
        listFields.add(new Field("IDEMPREGADO", "idEmpregado", true, false, false, false));
        listFields.add(new Field("IDUNIDADE", "idUnidade", false, false, false, false));
        listFields.add(new Field("IDGRUPO", "idGrupo", false, false, false, false));
        listFields.add(new Field("IDITEMCONFIGURACAOPAI", "idItemConfiguracaoPai", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "EVENTOEMPREGADO";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    public Collection<EventoEmpregadoDTO> listByIdEvento(final Integer idEvento) throws PersistenceException {
        final String sql = "select distinct idempregado, iditemconfiguracaopai from " + this.getTableName() + " where idevento = ?";
        final List dados = this.execSQL(sql, new Object[] {idEvento});
        final List fields = new ArrayList<>();
        fields.add("idEmpregado");
        fields.add("idItemConfiguracaoPai");
        return this.listConvertion(this.getBean(), dados, fields);
    }

    public Collection<EventoEmpregadoDTO> listByIdEventoGrupo(final Integer idEvento) throws PersistenceException {
        final String sql = "select distinct ee.idgrupo, g.nome from " + this.getTableName() + " ee join grupo g on g.idgrupo = ee.idgrupo "
                + "where ee.idevento = ? and ee.idgrupo is not null";
        final List dados = this.execSQL(sql, new Object[] {idEvento});
        final List fields = new ArrayList<>();
        fields.add("idGrupo");
        fields.add("nome");
        return this.listConvertion(this.getBean(), dados, fields);
    }

    public Collection<EventoEmpregadoDTO> listByIdEventoUnidade(final Integer idEvento) throws PersistenceException {
        final String sql = "select distinct ee.idunidade, u.nome from " + this.getTableName() + " ee join unidade u on u.idunidade = ee.idunidade "
                + "where ee.idevento = ? and ee.idunidade is not null";
        final List dados = this.execSQL(sql, new Object[] {idEvento});
        final List fields = new ArrayList<>();
        fields.add("idUnidade");
        fields.add("nome");
        return this.listConvertion(this.getBean(), dados, fields);
    }

    public Collection<EventoEmpregadoDTO> listByIdEventoEmpregado(final Integer idEvento) throws PersistenceException {
        final String sql = "select e.idempregado, e.nome from " + this.getTableName() + " ee join empregados e on e.idempregado = ee.idempregado "
                + "where ee.idevento = ? and ee.idgrupo is null and ee.idunidade is null";
        final List dados = this.execSQL(sql, new Object[] {idEvento});
        final List fields = new ArrayList<>();
        fields.add("idEmpregado");
        fields.add("nome");
        return this.listConvertion(this.getBean(), dados, fields);
    }

    public void deleteByIdEvento(final Integer idEvento) throws PersistenceException {
        final List lstCondicao = new ArrayList<>();
        lstCondicao.add(new Condition(Condition.AND, "idEvento", "=", idEvento));
        super.deleteByCondition(lstCondicao);
    }

}
