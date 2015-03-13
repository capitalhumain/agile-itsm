package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.EmailSolicitacaoServicoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class EmailSolicitacaoServicoDao extends CrudDaoDefaultImpl {

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idemail", "idEmail", true, true, false, true));
        listFields.add(new Field("idmessage", "idMessage", false, false, false, false));
        listFields.add(new Field("idsolicitacao", "idSolicitacao", false, false, false, false));
        listFields.add(new Field("origem", "origem", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "emailsolicitacao";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("idEmail"));
        return super.list(ordenacao);
    }

    public EmailSolicitacaoServicoDTO listSituacao(final String messageid) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final StringBuilder sb = new StringBuilder();
        sb.append("select idEmail, idMessage, idSolicitacao, origem from emailsolicitacao where idmessage = ? ");
        parametro.add(messageid);

        final List lista = this.execSQL(sb.toString(), parametro.toArray());

        final List<String> listRetorno = new ArrayList<String>();
        listRetorno.add("idEmail");
        listRetorno.add("idMessage");
        listRetorno.add("idSolicitacao");
        listRetorno.add("origem");

        final List result = engine.listConvertion(EmailSolicitacaoServicoDTO.class, lista, listRetorno);

        if (result.size() > 0) {
            return (EmailSolicitacaoServicoDTO) result.get(0);
        } else {
            return null;
        }
    }

    public Collection<EmailSolicitacaoServicoDTO> getEmailByOrigem(final String origem) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final StringBuilder sb = new StringBuilder();
        sb.append("select idEmail, idMessage, idSolicitacao, origem from emailsolicitacao where origem = ? ");
        parametro.add(origem);

        final List lista = this.execSQL(sb.toString(), parametro.toArray());

        final List<String> listRetorno = new ArrayList<String>();
        listRetorno.add("idEmail");
        listRetorno.add("idMessage");
        listRetorno.add("idSolicitacao");
        listRetorno.add("origem");

        final List result = engine.listConvertion(EmailSolicitacaoServicoDTO.class, lista, listRetorno);

        if (result.size() > 0) {
            return result;
        } else {
            return null;
        }
    }

    public EmailSolicitacaoServicoDTO getEmailByIdSolicitacaoAndOrigem(final Integer idSolicitacao, final String origem) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final StringBuilder sb = new StringBuilder();
        sb.append("select idEmail, idMessage, idSolicitacao, origem from emailsolicitacao where idsolicitacao = ? and origem = ? ");
        parametro.add(idSolicitacao);
        parametro.add(origem);

        final List lista = this.execSQL(sb.toString(), parametro.toArray());

        final List<String> listRetorno = new ArrayList<String>();
        listRetorno.add("idEmail");
        listRetorno.add("idMessage");
        listRetorno.add("idSolicitacao");
        listRetorno.add("origem");

        final List result = engine.listConvertion(EmailSolicitacaoServicoDTO.class, lista, listRetorno);

        if (result.size() > 0) {
            return (EmailSolicitacaoServicoDTO) result.get(0);
        } else {
            return null;
        }
    }

    public EmailSolicitacaoServicoDTO getEmailByIdMessageAndOrigem(final String idMessage, final String origem) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final StringBuilder sb = new StringBuilder();
        sb.append("select idEmail, idMessage, idSolicitacao, origem from emailsolicitacao where idmessage = ? and origem = ? ");
        parametro.add(idMessage);
        parametro.add(origem);

        final List lista = this.execSQL(sb.toString(), parametro.toArray());

        final List<String> listRetorno = new ArrayList<String>();
        listRetorno.add("idEmail");
        listRetorno.add("idMessage");
        listRetorno.add("idSolicitacao");
        listRetorno.add("origem");

        final List result = engine.listConvertion(EmailSolicitacaoServicoDTO.class, lista, listRetorno);

        if (result.size() > 0) {
            return (EmailSolicitacaoServicoDTO) result.get(0);
        } else {
            return null;
        }
    }

    public EmailSolicitacaoServicoDTO getEmailByIdMessage(final String idMessage) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final StringBuilder sb = new StringBuilder();
        sb.append("select idEmail, idMessage, idSolicitacao, origem from emailsolicitacao where idmessage = ? ");
        parametro.add(idMessage);

        final List lista = this.execSQL(sb.toString(), parametro.toArray());

        final List<String> listRetorno = new ArrayList<String>();
        listRetorno.add("idEmail");
        listRetorno.add("idMessage");
        listRetorno.add("idSolicitacao");
        listRetorno.add("origem");

        final List result = engine.listConvertion(EmailSolicitacaoServicoDTO.class, lista, listRetorno);

        if (result.size() > 0) {
            return (EmailSolicitacaoServicoDTO) result.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Class getBean() {
        return EmailSolicitacaoServicoDTO.class;
    }

    public EmailSolicitacaoServicoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

}
