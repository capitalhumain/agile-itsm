package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaResponsavelDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class RequisicaoMudancaResponsavelDao extends CrudDaoDefaultImpl {

    public RequisicaoMudancaResponsavelDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idrequisicaomudancaresp", "idRequisicaoMudancaResp", true, true, false, false));
        listFields.add(new Field("idRequisicaoMudanca", "idRequisicaoMudanca", false, false, false, false));
        listFields.add(new Field("idResponsavel", "idResponsavel", false, false, false, false));
        listFields.add(new Field("papelResponsavel", "papelResponsavel", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        return listFields;

    }

    @Override
    public String getTableName() {
        return this.getOwner() + "requisicaoMudancaResponsavel";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return RequisicaoMudancaResponsavelDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdMudanca(final Integer idRequisicaoMudanca) throws Exception {
        new ArrayList<>();
        final List fields = new ArrayList<>();

        final String sql = " select rqResponsavel.idResponsavel, rqResponsavel.idRequisicaoMudanca, responsavel.nome, responsavel.telefone, responsavel.email, cargo.nomecargo , rqResponsavel.papelresponsavel "
                + " from requisicaomudancaresponsavel rqResponsavel "
                + " inner join requisicaomudanca lib on rqResponsavel.idRequisicaoMudanca = lib.idrequisicaomudanca "
                + " inner join empregados responsavel on rqResponsavel.idResponsavel = responsavel.idempregado "
                + " inner join cargos cargo on responsavel.idcargo = cargo.idcargo" + " where rqResponsavel.idRequisicaoMudanca = ? ";

        final List resultado = this.execSQL(sql, new Object[] {idRequisicaoMudanca});

        fields.add("idResponsavel");
        fields.add("idRequisicaoMudanca");
        fields.add("nomeResponsavel");
        fields.add("telResponsavel");
        fields.add("emailResponsavel");
        fields.add("nomeCargo");
        fields.add("papelResponsavel");

        return this.listConvertion(this.getBean(), resultado, fields);
    }

    public Collection listByIdHistoricoMudanca(final Integer idHistoricoMudanca) throws Exception {
        new ArrayList<>();
        final List fields = new ArrayList<>();

        final String sql = " select rqResponsavel.idResponsavel, rqResponsavel.idRequisicaoMudanca, responsavel.nome, responsavel.telefone, responsavel.email, cargo.nomecargo , rqResponsavel.papelresponsavel "
                + "from requisicaomudancaresponsavel rqResponsavel "
                + "inner join requisicaomudanca lib on rqResponsavel.idRequisicaoMudanca = lib.idrequisicaomudanca "
                + "inner join empregados responsavel on rqResponsavel.idResponsavel = responsavel.idempregado "
                + "inner join cargos cargo on responsavel.idcargo = cargo.idcargo "
                + "inner join ligacao_mud_hist_resp ligresp on ligresp.idrequisicaomudancaresp = rqResponsavel.idrequisicaomudancaresp "
                + "where ligresp.idhistoricomudanca = ? ";

        final List resultado = this.execSQL(sql, new Object[] {idHistoricoMudanca});

        fields.add("idResponsavel");
        fields.add("idRequisicaoMudanca");
        fields.add("nomeResponsavel");
        fields.add("telResponsavel");
        fields.add("emailResponsavel");
        fields.add("nomeCargo");
        fields.add("papelResponsavel");

        return this.listConvertion(this.getBean(), resultado, fields);
    }

    public void deleteByIdRequisicaoMudanca(final Integer idRequisicaoMudanca) throws ServiceException, Exception {
        final ArrayList<Condition> condicoes = new ArrayList<Condition>();
        condicoes.add(new Condition("idRequisicaoMudanca", "=", idRequisicaoMudanca));

        super.deleteByCondition(condicoes);
    }

    public Collection findByIdMudancaEDataFim(final Integer idRequisicaoMudanca) throws Exception {
        new ArrayList<>();
        final List fields = new ArrayList<>();

        final String sql = " select rqResponsavel.idRequisicaoMudancaResp, rqResponsavel.idResponsavel, rqResponsavel.idRequisicaoMudanca, responsavel.nome, responsavel.telefone, responsavel.email, cargo.nomecargo , rqResponsavel.papelresponsavel "
                + " from requisicaomudancaresponsavel rqResponsavel "
                + " inner join requisicaomudanca lib on rqResponsavel.idRequisicaoMudanca = lib.idrequisicaomudanca "
                + " inner join empregados responsavel on rqResponsavel.idResponsavel = responsavel.idempregado "
                + " inner join cargos cargo on responsavel.idcargo = cargo.idcargo"
                + " where rqResponsavel.idRequisicaoMudanca = ? and rqResponsavel.datafim is null";

        final List resultado = this.execSQL(sql, new Object[] {idRequisicaoMudanca});

        fields.add("idRequisicaoMudancaResp");
        fields.add("idResponsavel");
        fields.add("idRequisicaoMudanca");
        fields.add("nomeResponsavel");
        fields.add("telResponsavel");
        fields.add("emailResponsavel");
        fields.add("nomeCargo");
        fields.add("papelResponsavel");

        return this.listConvertion(this.getBean(), resultado, fields);
    }

}
