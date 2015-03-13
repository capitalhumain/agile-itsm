package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.GrupoEmailDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class GrupoEmailDao extends CrudDaoDefaultImpl {

    public GrupoEmailDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return GrupoEmailDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idGrupo", "idGrupo", true, false, false, false));
        listFields.add(new Field("idEmpregado", "idEmpregado", false, false, false, false));
        listFields.add(new Field("nome", "nome", false, false, false, false));
        listFields.add(new Field("email", "email", true, false, false, false));
        return listFields;
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {

        return null;
    }

    @Override
    public String getTableName() {
        return "GRUPOSEMAILS";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    public Collection<GrupoEmailDTO> findByIdGrupo(final Integer idGrupo) throws PersistenceException {
        final Object[] objs = new Object[] {idGrupo};
        final String sql = "SELECT G.idGrupo, GE.idEmpregado, GE.email, GE.nome " + "  FROM " + this.getTableName()
                + " GE INNER JOIN GRUPO G ON G.idGrupo = GE.idGrupo " + " WHERE GE.idGrupo = ? ";
        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idGrupo");
        listRetorno.add("idEmpregado");
        listRetorno.add("email");
        listRetorno.add("nome");
        if (lista != null && !lista.isEmpty()) {
            return engine.listConvertion(this.getBean(), lista, listRetorno);
        } else {
            return null;
        }
    }

    public void deleteByIdGrupoAndEmail(final Integer idGrupo, final String email) throws PersistenceException {
        final List lstCondicao = new ArrayList<>();
        lstCondicao.add(new Condition("idGrupo", "=", idGrupo));
        lstCondicao.add(new Condition(Condition.AND, "email", "=", email));
        super.deleteByCondition(lstCondicao);

    }

    public void deleteByIdGrupo(final Integer idGrupo) throws PersistenceException {
        final List lstCondicao = new ArrayList<>();
        lstCondicao.add(new Condition("idGrupo", "=", idGrupo));
        super.deleteByCondition(lstCondicao);
    }

    public Collection<GrupoEmailDTO> obterEmailsGrupo(final Integer idGrupoAtual) throws PersistenceException {

        final Object[] objs = new Object[] {idGrupoAtual};
        final String sql = "SELECT GE.email" + "  FROM " + this.getTableName() + " GE INNER JOIN GRUPO G ON G.idGrupo = GE.idGrupo " + " WHERE GE.idGrupo = ? ";
        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("email");
        if (lista != null && !lista.isEmpty()) {
            return engine.listConvertion(GrupoEmailDTO.class, lista, listRetorno);
        }
        return null;
    }

}
