package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.InspecaoPedidoCompraDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class InspecaoPedidoCompraDao extends CrudDaoDefaultImpl {

    public InspecaoPedidoCompraDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idPedido", "idPedido", true, false, false, false));
        listFields.add(new Field("idCriterio", "idCriterio", true, false, false, false));
        listFields.add(new Field("dataHoraInspecao", "dataHoraInspecao", false, false, false, false));
        listFields.add(new Field("idResponsavel", "idResponsavel", false, false, false, false));
        listFields.add(new Field("avaliacao", "avaliacao", false, false, false, false));
        listFields.add(new Field("observacoes", "observacoes", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "InspecaoPedidoCompra";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return InspecaoPedidoCompraDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdPedido(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idPedido", "=", parm));
        ordenacao.add(new Order("idCriterio"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdPedido(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idPedido", "=", parm));
        super.deleteByCondition(condicao);
    }

}
