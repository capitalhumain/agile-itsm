package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.TipoLiberacaoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class TipoLiberacaoDAO extends CrudDaoDefaultImpl {

    public TipoLiberacaoDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idTipoLiberacao", "idTipoLiberacao", true, true, false, false));
        listFields.add(new Field("nomeTipoLiberacao", "nomeTipoLiberacao", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        listFields.add(new Field("idTipoFluxo", "idTipoFluxo", false, false, false, false));
        listFields.add(new Field("idModeloEmailCriacao", "idModeloEmailCriacao", false, false, false, false));
        listFields.add(new Field("idModeloEmailFinalizacao", "idModeloEmailFinalizacao", false, false, false, false));
        listFields.add(new Field("idModeloEmailAcoes", "idModeloEmailAcoes", false, false, false, false));
        listFields.add(new Field("idGrupoExecutor", "idGrupoExecutor", false, false, false, false));
        listFields.add(new Field("idCalendario", "idCalendario", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "tipoliberacao";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("nomeTipoLiberacao"));
        return super.list(list);
    }

    @Override
    public Class getBean() {

        return TipoLiberacaoDTO.class;
    }

    public Collection findByIdTipoLiberacao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idTipoLiberacao", "=", parm));
        ordenacao.add(new Order("idTipoLiberacao"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdTipoLiberacao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idTipoLiberacao", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByNomeTipoLiberacao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("nomeTipoLiberacao", "=", parm));
        ordenacao.add(new Order("nomeTipoLiberacao"));
        condicao.add(new Condition(Condition.AND, "dataFim", "is", null));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection encontrarPorNomeTipoLiberacao(final TipoLiberacaoDTO tipoLiberacaoDTO) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("nomeTipoLiberacao", "=", tipoLiberacaoDTO.getNomeTipoLiberacao()));
        ordenacao.add(new Order("nomeTipoLiberacao"));
        condicao.add(new Condition(Condition.AND, "dataFim", "is", null));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByNomeTipoLiberacao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("nomeTipoLiberacao", "=", parm));
        super.deleteByCondition(condicao);
    }

    /**
     * Retorna lista de status de tipo mudança.
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public boolean verificarTipoLiberacaoAtivos(final TipoLiberacaoDTO obj) throws PersistenceException {
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        String sql = "select idtipoliberacao From " + this.getTableName() + "  where  nometipoliberacao = ?   and dataFim is null ";

        if (obj.getIdTipoLiberacao() != null) {
            sql += " and idtipoliberacao <> " + obj.getIdTipoLiberacao();
        }

        parametro.add(obj.getNomeTipoLiberacao());
        list = this.execSQL(sql, parametro.toArray());
        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Collection getAtivos() throws PersistenceException {
        final List<Order> order = new ArrayList<>();
        final List<Condition> condition = new ArrayList<>();
        condition.add(new Condition("dataFim", "is", null));
        order.add(new Order("nomeTipoLiberacao"));
        return super.findByCondition(condition, order);

    }

    /**
     * @author euler.ramos
     * @param idCalendario
     * @return
     * @throws Exception
     */
    public ArrayList<TipoLiberacaoDTO> findByIdCalendario(final Integer idCalendario) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCalendario", "=", idCalendario));
        ordenacao.add(new Order("idTipoLiberacao"));
        final ArrayList<TipoLiberacaoDTO> result = (ArrayList<TipoLiberacaoDTO>) super.findByCondition(condicao, ordenacao);
        return result == null ? new ArrayList<TipoLiberacaoDTO>() : result;
    }

}
