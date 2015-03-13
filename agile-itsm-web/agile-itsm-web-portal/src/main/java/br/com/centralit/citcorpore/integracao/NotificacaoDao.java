package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.NotificacaoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class NotificacaoDao extends CrudDaoDefaultImpl {

    public NotificacaoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idnotificacao", "idNotificacao", true, true, false, false));
        listFields.add(new Field("titulo", "titulo", false, false, false, false));
        listFields.add(new Field("tiponotificacao", "tipoNotificacao", false, false, false, false));
        listFields.add(new Field("datainicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("datafim", "dataFim", false, false, false, false));
        listFields.add(new Field("origemNotificacao", "origemNotificacao", false, false, false, false));
        listFields.add(new Field("idContrato", "idContrato", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "NOTIFICACAO";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return NotificacaoDTO.class;
    }

    /**
     * Retorna true caso titulo ja exista false caso titulo não exista ou esteje excluido.
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public boolean consultarNotificacaoAtivos(final NotificacaoDTO obj) throws PersistenceException {
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        String sql = "select idnotificacao From " + this.getTableName() + "  where  titulo = ?   and dataFim is null ";

        if (obj.getIdNotificacao() != null) {
            sql += " and idnotificacao <> " + obj.getIdNotificacao();
        }
        parametro.add(obj.getTitulo());
        list = this.execSQL(sql, parametro.toArray());
        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Collection<NotificacaoDTO> consultarNotificacaoAtivosOrigemServico(final Integer idContrato) throws PersistenceException {
        final Object[] objs = new Object[] {idContrato};

        final String sql = " select distinct n.idnotificacao, n.titulo, n.tiponotificacao, n.origemnotificacao, n.idContrato, n.datainicio, n.datafim from "
                + this.getTableName() + " n " + " inner join notificacaoservico nsc on n.idnotificacao = nsc.idnotificacao "
                + " inner join servico sc on nsc.idservico = sc.idservico  " + " where n.idcontrato = ? and n.origemnotificacao = 'S' and n.dataFim is null ";
        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idNotificacao");
        listRetorno.add("titulo");
        listRetorno.add("tipoNotificacao");
        listRetorno.add("origemnotificacao");
        listRetorno.add("idContrato");
        listRetorno.add("dataInicio");
        listRetorno.add("datafim");

        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        if (result == null || result.size() == 0) {
            return null;
        }
        return result;

    }

    public Collection<NotificacaoDTO> listaIdContrato(final Integer idContrato) throws PersistenceException {
        final Object[] objs = new Object[] {idContrato};
        final String sql = "SELECT  idContrato, idNotificacao FROM " + this.getTableName() + " WHERE idContrato = ?  AND datafim IS NULL ";
        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idContrato");
        listRetorno.add("idNotificacao");
        if (lista != null && !lista.isEmpty()) {
            return engine.listConvertion(this.getBean(), lista, listRetorno);
        } else {
            return null;
        }
    }

    public Collection<NotificacaoDTO> listaIdServico(final Integer idServico) throws PersistenceException {
        final Object[] objs = new Object[] {idServico};
        final String sql = "SELECT  " + this.getNamesFieldsStr() + " FROM " + this.getTableName()
                + " WHERE idNotificacao in (select idNotificacao from NOTIFICACAOSERVICO where idServico = ?)  AND datafim IS NULL ";
        final List lista = this.execSQL(sql, objs);

        if (lista != null && !lista.isEmpty()) {
            return engine.listConvertion(this.getBean(), lista, this.getListNamesFieldClass());
        } else {
            return null;
        }
    }

}
