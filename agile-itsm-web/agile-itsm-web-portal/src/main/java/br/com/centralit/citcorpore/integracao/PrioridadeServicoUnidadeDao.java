package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.PrioridadeServicoUnidadeDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class PrioridadeServicoUnidadeDao extends CrudDaoDefaultImpl {

    public PrioridadeServicoUnidadeDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idUnidade", "idUnidade", true, false, false, false));
        listFields.add(new Field("idServicoContrato", "idServicoContrato", true, false, false, false));
        listFields.add(new Field("idPrioridade", "idPrioridade", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "PrioridadeServicoUnidade";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return PrioridadeServicoUnidadeDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdServicoContrato(final Integer idServicoContrato) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idServicoContrato", "=", idServicoContrato));
        ordenacao.add(new Order("idUnidade"));

        return super.findByCondition(condicao, ordenacao);
    }

    public PrioridadeServicoUnidadeDTO restore(final Integer idServicoContrato, final Integer idUnidade) throws PersistenceException {

        final List parametro = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append(" select idunidade,idservicocontrato,idprioridade,datainicio from prioridadeservicounidade where  idservicocontrato = ?  and idunidade = ? ");
        parametro.add(idServicoContrato);
        parametro.add(idUnidade);
        final List lista = this.execSQL(sql.toString(), parametro.toArray());
        final List<String> listRetorno = new ArrayList<String>();
        listRetorno.add("idUnidade");
        listRetorno.add("idServicoContrato");
        listRetorno.add("idPrioridade");
        listRetorno.add("dataInicio");
        final List result = engine.listConvertion(PrioridadeServicoUnidadeDTO.class, lista, listRetorno);
        if (result != null && !result.isEmpty()) {
            return (PrioridadeServicoUnidadeDTO) result.get(0);
        }
        return null;
    }

}
