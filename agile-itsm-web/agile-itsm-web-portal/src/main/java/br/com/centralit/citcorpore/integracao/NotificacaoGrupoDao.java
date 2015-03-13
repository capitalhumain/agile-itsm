package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.NotificacaoGrupoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class NotificacaoGrupoDao extends CrudDaoDefaultImpl {

    public NotificacaoGrupoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idnotificacao", "idNotificacao", true, false, false, false));
        listFields.add(new Field("idgrupo", "idGrupo", true, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "NOTIFICACAOGRUPO";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return NotificacaoGrupoDTO.class;
    }

    public Collection<NotificacaoGrupoDTO> listaIdGrupo(final Integer idNotificacao) throws PersistenceException {
        final Object[] objs = new Object[] {idNotificacao};
        final String sql = "SELECT  idgrupo FROM " + this.getTableName() + " WHERE idnotificacao = ?  ";
        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idGrupo");
        if (lista != null && !lista.isEmpty()) {
            return engine.listConvertion(this.getBean(), lista, listRetorno);
        } else {
            return null;
        }
    }

    public void deleteByIdNotificacaoGrupo(final Integer idNotificacao) throws PersistenceException {
        final List lstCondicao = new ArrayList<>();
        lstCondicao.add(new Condition("idNotificacao", "=", idNotificacao));
        super.deleteByCondition(lstCondicao);
    }

    public void deleteByIdGrupo(final Integer idGrupo) throws PersistenceException {
        final List lstCondicao = new ArrayList<>();
        lstCondicao.add(new Condition("idGrupo", "=", idGrupo));
        super.deleteByCondition(lstCondicao);
    }

}
