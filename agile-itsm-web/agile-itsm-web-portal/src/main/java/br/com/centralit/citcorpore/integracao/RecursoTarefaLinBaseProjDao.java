package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.RecursoTarefaLinBaseProjDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class RecursoTarefaLinBaseProjDao extends CrudDaoDefaultImpl {

    public RecursoTarefaLinBaseProjDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idRecursoTarefaLinBaseProj", "idRecursoTarefaLinBaseProj", true, true, false, false));
        listFields.add(new Field("idTarefaLinhaBaseProjeto", "idTarefaLinhaBaseProjeto", false, false, false, false));
        listFields.add(new Field("idPerfilContrato", "idPerfilContrato", false, false, false, false));
        listFields.add(new Field("idEmpregado", "idEmpregado", false, false, false, false));
        listFields.add(new Field("percentualAloc", "percentualAloc", false, false, false, false));
        listFields.add(new Field("tempoAloc", "tempoAloc", false, false, false, false));
        listFields.add(new Field("percentualExec", "percentualExec", false, false, false, false));
        listFields.add(new Field("tempoAlocMinutos", "tempoAlocMinutos", false, false, false, false));
        listFields.add(new Field("custo", "custo", false, false, false, false));
        listFields.add(new Field("custoPerfil", "custoPerfil", false, false, false, false));
        listFields.add(new Field("esforcoPorOS", "esforcoPorOS", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "RecursoTarefaLinBaseProj";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return RecursoTarefaLinBaseProjDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdTarefaLinhaBaseProjeto(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idTarefaLinhaBaseProjeto", "=", parm));
        ordenacao.add(new Order("idEmpregado"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdTarefaLinhaBaseProjetoAndIdEmpregado(final Integer parm, final Integer idEmpregado) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idTarefaLinhaBaseProjeto", "=", parm));
        condicao.add(new Condition("idEmpregado", "=", idEmpregado));
        ordenacao.add(new Order("idEmpregado"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdLinhaBaseProjeto(final Integer idLinhaBaseProjeto) throws PersistenceException {
        final List parametros = new ArrayList<>();
        final String sql = "DELETE FROM RecursoTarefaLinBaseProj WHERE idTarefaLinhaBaseProjeto IN (Select idTarefaLinhaBaseProjeto FROM TarefaLinhaBaseProjeto WHERE idLinhaBaseProjeto = ?)";
        parametros.add(idLinhaBaseProjeto);
        this.execUpdate(sql, parametros.toArray());
    }

    public void deleteByIdTarefaLinhaBaseProjeto(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idTarefaLinhaBaseProjeto", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdEmpregado(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idEmpregado", "=", parm));
        ordenacao.add(new Order("idTarefaLinhaBaseProjeto"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdEmpregado(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idEmpregado", "=", parm));
        super.deleteByCondition(condicao);
    }

}
