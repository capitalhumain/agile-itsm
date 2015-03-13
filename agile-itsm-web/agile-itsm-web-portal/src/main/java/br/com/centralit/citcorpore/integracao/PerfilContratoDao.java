package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.PerfilContratoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class PerfilContratoDao extends CrudDaoDefaultImpl {

    public PerfilContratoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idPerfilContrato", "idPerfilContrato", true, true, false, false));
        listFields.add(new Field("idContrato", "idContrato", false, false, false, false));
        listFields.add(new Field("nomePerfilContrato", "nomePerfilContrato", false, false, false, false));
        listFields.add(new Field("custoHora", "custoHora", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "PerfilContrato";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return PerfilContratoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdContrato(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idContrato", "=", parm));
        ordenacao.add(new Order("nomePerfilContrato"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdContrato(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idContrato", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection getTotaisSEMIdMarcoPagamentoPrj(final Integer idLinhaBaseProjetoParm) throws PersistenceException {
        final String sql = "SELECT RecursoTarefaLinBaseProj.idPerfilContrato, nomePerfilContrato, SUM(RecursoTarefaLinBaseProj.tempoAlocMinutos), SUM(RecursoTarefaLinBaseProj.custo), SUM(RecursoTarefaLinBaseProj.custoPerfil) FROM RecursoTarefaLinBaseProj "
                + "INNER JOIN PerfilContrato ON RecursoTarefaLinBaseProj.idPerfilContrato = PerfilContrato.idPerfilContrato "
                + "INNER JOIN TarefaLinhaBaseProjeto ON TarefaLinhaBaseProjeto.idTarefaLinhaBaseProjeto = RecursoTarefaLinBaseProj.idTarefaLinhaBaseProjeto "
                + "WHERE TarefaLinhaBaseProjeto.idMarcoPagamentoPrj IS NULL AND TarefaLinhaBaseProjeto.idLinhaBaseProjeto = ? AND (deleted IS NULL or deleted = 'N') "
                + "GROUP BY RecursoTarefaLinBaseProj.idPerfilContrato, nomePerfilContrato " + "ORDER BY nomePerfilContrato";
        final List lstDados = this.execSQL(sql, new Object[] {idLinhaBaseProjetoParm});
        final List fields = new ArrayList<>();
        fields.add("idPerfilContrato");
        fields.add("nomePerfilContrato");
        fields.add("tempoAlocMinutosTotal");
        fields.add("custoTotal");
        fields.add("custoTotalPerfil");
        return this.listConvertion(this.getBean(), lstDados, fields);
    }

    public Collection getTotaisByIdMarcoPagamentoPrj(final Integer idMarcoPagamentoPrjParm, final Integer idLinhaBaseProjetoParm) throws PersistenceException {
        final String sql = "SELECT RecursoTarefaLinBaseProj.idPerfilContrato, nomePerfilContrato, SUM(RecursoTarefaLinBaseProj.tempoAlocMinutos), SUM(RecursoTarefaLinBaseProj.custo), SUM(RecursoTarefaLinBaseProj.custoPerfil) FROM RecursoTarefaLinBaseProj "
                + "INNER JOIN PerfilContrato ON RecursoTarefaLinBaseProj.idPerfilContrato = PerfilContrato.idPerfilContrato "
                + "INNER JOIN TarefaLinhaBaseProjeto ON TarefaLinhaBaseProjeto.idTarefaLinhaBaseProjeto = RecursoTarefaLinBaseProj.idTarefaLinhaBaseProjeto "
                + "WHERE TarefaLinhaBaseProjeto.idMarcoPagamentoPrj = ? AND TarefaLinhaBaseProjeto.idLinhaBaseProjeto = ? AND (deleted IS NULL or deleted = 'N') "
                + "GROUP BY RecursoTarefaLinBaseProj.idPerfilContrato, nomePerfilContrato " + "ORDER BY nomePerfilContrato";
        final List lstDados = this.execSQL(sql, new Object[] {idMarcoPagamentoPrjParm, idLinhaBaseProjetoParm});
        final List fields = new ArrayList<>();
        fields.add("idPerfilContrato");
        fields.add("nomePerfilContrato");
        fields.add("tempoAlocMinutosTotal");
        fields.add("custoTotal");
        fields.add("custoTotalPerfil");
        return this.listConvertion(this.getBean(), lstDados, fields);
    }

    public Collection getTotaisByIdTarefaLinhaBaseProjeto(final Integer idTarefaLinhaBaseProjetoParm) throws PersistenceException {
        final String sql = "SELECT RecursoTarefaLinBaseProj.idPerfilContrato, nomePerfilContrato, SUM(tempoAlocMinutos) FROM RecursoTarefaLinBaseProj INNER JOIN PerfilContrato ON RecursoTarefaLinBaseProj.idPerfilContrato = PerfilContrato.idPerfilContrato "
                + "WHERE RecursoTarefaLinBaseProj.idTarefaLinhaBaseProjeto = ? "
                + "GROUP BY RecursoTarefaLinBaseProj.idPerfilContrato, nomePerfilContrato "
                + "ORDER BY nomePerfilContrato";
        final List lstDados = this.execSQL(sql, new Object[] {idTarefaLinhaBaseProjetoParm});
        final List fields = new ArrayList<>();
        fields.add("idPerfilContrato");
        fields.add("nomePerfilContrato");
        fields.add("tempoAlocMinutosTotal");
        return this.listConvertion(this.getBean(), lstDados, fields);
    }

    public Collection getTotaisByIdLinhaBaseProjeto(final Integer idLinhaBaseProjetoParm) throws PersistenceException {
        final String sql = "SELECT RecursoTarefaLinBaseProj.idPerfilContrato, nomePerfilContrato, SUM(tempoAlocMinutos), SUM(custo), SUM(custoPerfil) FROM RecursoTarefaLinBaseProj INNER JOIN PerfilContrato ON RecursoTarefaLinBaseProj.idPerfilContrato = PerfilContrato.idPerfilContrato "
                + "WHERE RecursoTarefaLinBaseProj.idTarefaLinhaBaseProjeto IN (SELECT idTarefaLinhaBaseProjeto FROM TarefaLinhaBaseProjeto WHERE idLinhaBaseProjeto = ?) "
                + "GROUP BY RecursoTarefaLinBaseProj.idPerfilContrato, nomePerfilContrato " + "ORDER BY nomePerfilContrato";
        final List lstDados = this.execSQL(sql, new Object[] {idLinhaBaseProjetoParm});
        final List fields = new ArrayList<>();
        fields.add("idPerfilContrato");
        fields.add("nomePerfilContrato");
        fields.add("tempoAlocMinutosTotal");
        fields.add("custoTotal");
        fields.add("custoTotalPerfil");
        return this.listConvertion(this.getBean(), lstDados, fields);
    }

}
