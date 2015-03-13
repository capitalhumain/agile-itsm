package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ProblemaDTO;
import br.com.centralit.citcorpore.bean.ProblemaMudancaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ProblemaMudancaDAO extends CrudDaoDefaultImpl {

    public ProblemaMudancaDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idProblemaMudanca", "idProblemaMudanca", true, true, false, false));
        listFields.add(new Field("idProblema", "idProblema", false, false, false, false));
        listFields.add(new Field("idRequisicaoMudanca", "idRequisicaoMudanca", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "problemamudanca";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ProblemaMudancaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdProblemaMudanca(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idProblemaMudanca", "=", parm));
        ordenacao.add(new Order("idProblemaMudanca"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdProblemaMudanca(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idProblemaMudanca", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdProblema(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idProblema", "=", parm));
        ordenacao.add(new Order("idProblema"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdProblema(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idProblema", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdRequisicaoMudanca(final Integer parm) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List fields = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct pm.idproblema as idproblema, titulo, status FROM problema pro JOIN problemamudanca pm ON pro.idproblema = pm.idproblema WHERE pm.idrequisicaomudanca = ? AND pm.datafim is null ORDER BY pm.idproblema");
        parametro.add(parm);
        list = this.execSQL(sql.toString(), parametro.toArray());
        fields.add("idProblema");
        fields.add("titulo");
        fields.add("status");
        if (list != null && !list.isEmpty()) {
            return this.listConvertion(this.getBean(), list, fields);
        } else {
            return null;
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Collection findByIdMudancaEDataFim(final Integer idRequisicaoMudanca) throws PersistenceException {
        final List fields = new ArrayList<>();

        final String sql = "select idproblemamudanca, idproblema, idrequisicaomudanca, datafim from problemamudanca WHERE idrequisicaomudanca = ? and datafim is null";

        final List resultado = this.execSQL(sql, new Object[] {idRequisicaoMudanca});

        fields.add("idProblemaMudanca");
        fields.add("idProblema");
        fields.add("idRequisicaoMudanca");
        fields.add("dataFim");

        return this.listConvertion(this.getBean(), resultado, fields);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Collection listByIdHistoricoMudanca(final Integer idHistoricoMudanca) throws PersistenceException {
        final List fields = new ArrayList<>();

        final String sql = "select distinct pr.idproblemamudanca, pr.idproblema, pr.idrequisicaomudanca, pr.datafim " + "from problemamudanca pr "
                + "inner join ligacao_mud_hist_pr ligpr on ligpr.idproblemamudanca = pr.idproblemamudanca " + "WHERE ligpr.idhistoricomudanca = ?";

        final List resultado = this.execSQL(sql, new Object[] {idHistoricoMudanca});

        fields.add("idProblemaMudanca");
        fields.add("idProblema");
        fields.add("idRequisicaoMudanca");
        fields.add("dataFim");

        return this.listConvertion(this.getBean(), resultado, fields);
    }

    public void deleteByIdRequisicaoMudanca(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idRequisicaoMudanca", "=", parm));
        super.deleteByCondition(condicao);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<ProblemaDTO> listProblemaByIdMudanca(final Integer idMudanca) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List fields = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();

        sql.append("select pr.idproblema ");
        sql.append(" from problema pr  ");
        sql.append(" inner join problemamudanca pm ");
        sql.append(" on pr.idproblema = pm.idproblema ");
        sql.append(" where pm.idrequisicaomudanca =  ? ");

        parametro.add(idMudanca);

        fields.add("idProblema");

        final List dados = this.execSQL(sql.toString(), parametro.toArray());

        return this.listConvertion(ProblemaDTO.class, dados, fields);
    }

    public ProblemaMudancaDTO restoreByIdProblema(final Integer idProblema) throws PersistenceException {
        final List parametro = new ArrayList<>();
        parametro.add(idProblema);

        final List<String> listRetorno = new ArrayList<String>();
        listRetorno.add("idProblemaMudanca");
        listRetorno.add("idProblema");
        listRetorno.add("idRequisicaoMudanca");
        listRetorno.add("dataFim");

        final String sql = "  select * from problemamudanca where idproblema = ? and datafim is null ";

        final List lista = this.execSQL(sql.toString(), parametro.toArray());

        if (lista != null && !lista.isEmpty()) {
            final List listaResult = engine.listConvertion(ProblemaMudancaDTO.class, lista, listRetorno);
            return (ProblemaMudancaDTO) listaResult.get(0);
        }
        return null;
    }

}
