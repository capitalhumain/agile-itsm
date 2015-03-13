package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoMudancaDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoProblemaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class SolicitacaoServicoMudancaDao extends CrudDaoDefaultImpl {

    public SolicitacaoServicoMudancaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);

    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    // corrigir, foi retirado às pressas, precisa de reavaliação
    // "delete from  SOLICITACAOSERVICOMUDANCA where idRequisicaoMudanca = ? and idhistoricomudanca is null";
    private static final String SQL_DELETE_BY_ID_MUDANCA = "delete from  SOLICITACAOSERVICOMUDANCA where idRequisicaoMudanca = ? ";

    @Override
    public Collection<Field> getFields() {

        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDREQUISICAOMUDANCA", "idRequisicaoMudanca", true, false, false, false));
        listFields.add(new Field("IDSOLICITACAOSERVICO", "idSolicitacaoServico", true, false, false, false));
        listFields.add(new Field("idhistoricomudanca", "idHistoricoMudanca", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "solicitacaoservicomudanca";
    }

    @Override
    public Class<SolicitacaoServicoProblemaDTO> getBean() {
        return SolicitacaoServicoProblemaDTO.class;
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    public void deleteByIdMudanca(final Integer parm) throws PersistenceException {
        super.execUpdate(SQL_DELETE_BY_ID_MUDANCA, new Object[] {parm});
    }

    public void deleteByIdSolictacaoServico(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdSolictacaoServico(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", parm));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection listByIdHistoricoMudanca(final Integer idRequisicaoMudanca) throws PersistenceException {
        final List fields = new ArrayList<>();

        final String sql = "select IDREQUISICAOMUDANCA,IDSOLICITACAOSERVICO,idhistoricomudanca from SOLICITACAOSERVICOMUDANCA where idhistoricomudanca = ?";

        final List resultado = this.execSQL(sql, new Object[] {idRequisicaoMudanca});

        fields.add("idRequisicaoMudanca");
        fields.add("idSolicitacaoServico");
        fields.add("idHistoricoMudanca");

        return this.listConvertion(SolicitacaoServicoMudancaDTO.class, resultado, fields);
    }

    public Collection findByIdProblema(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idRequisicaoMudanca", "=", parm));
        return super.findByCondition(condicao, ordenacao);
    }

    public List<SolicitacaoServicoMudancaDTO> listByIdMudanca(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idRequisicaoMudanca", "=", parm));
        return (List<SolicitacaoServicoMudancaDTO>) super.findByCondition(condicao, ordenacao);
    }

    public List<SolicitacaoServicoDTO> listSolicitacaoByIdMudanca(final Integer idMudanca) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List fields = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();

        sql.append("select sm.idsolicitacaoservico ");
        sql.append(" from solicitacaoservico ss ");
        sql.append(" inner join solicitacaoservicomudanca sm ");
        sql.append(" on ss.idsolicitacaoservico = sm.idsolicitacaoservico ");
        sql.append(" where sm.idrequisicaomudanca = ? and sm.idhistoricomudanca is null ");

        parametro.add(idMudanca);

        fields.add("idSolicitacaoServico");

        final List dados = this.execSQL(sql.toString(), parametro.toArray());

        return this.listConvertion(SolicitacaoServicoDTO.class, dados, fields);
    }

}
