package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ProjetoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ProjetoDao extends CrudDaoDefaultImpl {

    private static final String SQL_GET_LISTA_SEM_PAI = "SELECT idProjeto, idProjetoPai, nomeProjeto, situacao " + "  FROM projetos "
            + " WHERE idProjetoPai IS NULL " + "   AND (? = -1 OR ? = idCliente)" + " AND deleted IS NULL " + " ORDER BY idProjeto";

    public ProjetoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return ProjetoDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDPROJETO", "idProjeto", true, true, false, false));
        listFields.add(new Field("IDPROJETOPAI", "idProjetoPai", false, false, false, false));
        listFields.add(new Field("IDCLIENTE", "idCliente", false, false, false, false));
        listFields.add(new Field("idContrato", "idContrato", false, false, false, false));
        listFields.add(new Field("NOMEPROJETO", "nomeProjeto", false, false, false, false));
        listFields.add(new Field("DETALHAMENTO", "detalhamento", false, false, false, false));
        listFields.add(new Field("SITUACAO", "situacao", false, false, false, false));
        listFields.add(new Field("valorEstimado", "valorEstimado", false, false, false, false));
        listFields.add(new Field("idOs", "idOs", false, false, false, false));
        listFields.add(new Field("sigla", "sigla", false, false, false, false));
        listFields.add(new Field("emergencial", "emergencial", false, false, false, false));
        listFields.add(new Field("severidade", "severidade", false, false, false, false));
        listFields.add(new Field("nomeGestor", "nomeGestor", false, false, false, false));
        listFields.add(new Field("idRequisicaoMudanca", "idRequisicaoMudanca", false, false, false, false));
        listFields.add(new Field("idLiberacao", "idLiberacao", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "PROJETOS";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List ordem = new ArrayList<>();
        condicao.add(new Condition("deleted", "is", null));
        ordem.add(new Order("nomeProjeto"));
        return super.findByCondition(condicao, ordem);
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List list = new ArrayList<>();
        condicao.add(new Condition("deleted", "is", null));
        list.add(new Order("nomeProjeto"));
        return super.findByCondition(condicao, list);
    }

    public Collection findByIdCliente(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCliente", "=", parm));
        condicao.add(new Condition("deleted", "is", null));
        ordenacao.add(new Order("nomeProjeto"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection<ProjetoDTO> findSemPai(final Integer idCliente) throws PersistenceException {
        Integer cli = -1;
        if (idCliente != null) {
            cli = idCliente;
        }

        final Object[] objs = new Object[] {cli, cli};
        final List lista = this.execSQL(SQL_GET_LISTA_SEM_PAI, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idProjeto");
        listRetorno.add("idProjetoPai");
        listRetorno.add("nomeProjeto");
        listRetorno.add("situacao");

        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);
        return result;
    }

    public Collection<ProjetoDTO> findByIdPai(final Integer idPai, final Integer idCliente) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idProjetoPai", "=", idPai));
        condicao.add(new Condition("deleted", "is", null));
        if (idCliente != null) {
            condicao.add(new Condition("idCliente", "=", idCliente));
        }
        ordenacao.add(new Order("idProjeto"));
        return super.findByCondition(condicao, ordenacao);
    }

}
