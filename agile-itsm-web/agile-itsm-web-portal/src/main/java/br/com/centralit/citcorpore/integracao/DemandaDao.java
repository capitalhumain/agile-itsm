package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.DemandaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class DemandaDao extends CrudDaoDefaultImpl {

    public DemandaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return DemandaDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idDemanda", "idDemanda", true, true, false, false));
        listFields.add(new Field("idContrato", "idContrato", false, false, false, false));
        listFields.add(new Field("idSituacaoDemanda", "idSituacaoDemanda", false, false, false, false));
        listFields.add(new Field("idTipoDemanda", "idTipoDemanda", false, false, false, false));
        listFields.add(new Field("idProjeto", "idProjeto", false, false, false, false));
        listFields.add(new Field("idDemandaPai", "idDemandaPai", false, false, false, false));
        listFields.add(new Field("idFluxo", "idFluxo", false, false, false, false));
        listFields.add(new Field("previsaoInicio", "previsaoInicio", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("previsaoFim", "previsaoFim", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        listFields.add(new Field("detalhamento", "detalhamento", false, false, false, false));
        listFields.add(new Field("prioridade", "prioridade", false, false, false, false));
        listFields.add(new Field("expectativaFim", "expectativaFim", false, false, false, false));
        listFields.add(new Field("complexidade", "complexidade", false, false, false, false));
        listFields.add(new Field("custoTotal", "custoTotal", false, false, false, false));
        listFields.add(new Field("observacao", "observacao", false, false, false, false));
        listFields.add(new Field("idOS", "idOS", false, false, false, false));
        listFields.add(new Field("glosa", "glosa", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "DEMANDAS";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("previsaoFim"));
        return super.list(list);
    }

    public void updateStatus(final BaseEntity obj) throws PersistenceException {
        final DemandaDTO demanda = (DemandaDTO) obj;
        final DemandaDTO demandaUpdate = new DemandaDTO();

        demandaUpdate.setIdDemanda(demanda.getIdDemanda());
        demandaUpdate.setIdSituacaoDemanda(demanda.getIdSituacaoDemanda());

        super.updateNotNull(demandaUpdate);
    }

    public void deleteByIdOS(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idOS", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdOS(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idOS", "=", parm));
        ordenacao.add(new Order("idDemanda"));
        return super.findByCondition(condicao, ordenacao);
    }

}
