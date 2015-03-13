package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.NotificacaoServicoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class NotificacaoServicoDao extends CrudDaoDefaultImpl {

    public NotificacaoServicoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idNotificacao", "idNotificacao", true, false, false, false));
        listFields.add(new Field("idServico", "idServico", true, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "NOTIFICACAOSERVICO";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return NotificacaoServicoDTO.class;
    }

    public Collection<NotificacaoServicoDTO> listaIdServico(final Integer idservico) throws PersistenceException {
        final Object[] objs = new Object[] {idservico};
        final String sql = "SELECT  idservico, idNotificacao FROM " + this.getTableName() + " WHERE idservico = ?  ";
        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idServico");
        listRetorno.add("idNotificacao");
        if (lista != null && !lista.isEmpty()) {
            return engine.listConvertion(this.getBean(), lista, listRetorno);
        } else {
            return null;
        }
    }

    public Collection<NotificacaoServicoDTO> listaIdNotificacao(final Integer idNotificacao) throws PersistenceException {
        final Object[] objs = new Object[] {idNotificacao};
        final String sql = "SELECT  idservico, idNotificacao FROM " + this.getTableName() + " WHERE idNotificacao = ?  ";
        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idServico");
        listRetorno.add("idNotificacao");
        if (lista != null && !lista.isEmpty()) {
            return engine.listConvertion(this.getBean(), lista, listRetorno);
        } else {
            return null;
        }
    }

    public boolean existeServico(final Integer idNotificacao, final Integer idservico) throws PersistenceException {
        final Object[] objs = new Object[] {idNotificacao, idservico};
        final String sql = "SELECT  idservico, idNotificacao FROM " + this.getTableName() + " WHERE idNotificacao = ? AND idservico = ?  ";
        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idServico");
        listRetorno.add("idNotificacao");
        if (lista != null && !lista.isEmpty()) {
            return true;
        }
        return false;
    }

    public void deleteByIdNotificacaoServico(final Integer idNotificacao) throws PersistenceException {
        final List lstCondicao = new ArrayList<>();
        lstCondicao.add(new Condition("idNotificacao", "=", idNotificacao));
        super.deleteByCondition(lstCondicao);
    }

}
