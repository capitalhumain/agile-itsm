package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.PedidoCompraDTO;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoPedidoCompra;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class PedidoCompraDao extends CrudDaoDefaultImpl {

    public PedidoCompraDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idPedido", "idPedido", true, true, false, false));
        listFields.add(new Field("idCotacao", "idCotacao", false, false, false, false));
        listFields.add(new Field("idEmpresa", "idEmpresa", false, false, false, false));
        listFields.add(new Field("idFornecedor", "idFornecedor", false, false, false, false));
        listFields.add(new Field("dataPedido", "dataPedido", false, false, false, false));
        listFields.add(new Field("dataPrevistaEntrega", "dataPrevistaEntrega", false, false, false, false));
        listFields.add(new Field("numeroPedido", "numeroPedido", false, false, false, false));
        listFields.add(new Field("identificacaoEntrega", "identificacaoEntrega", false, false, false, false));
        listFields.add(new Field("valorFrete", "valorFrete", false, false, false, false));
        listFields.add(new Field("valorSeguro", "valorSeguro", false, false, false, false));
        listFields.add(new Field("outrasDespesas", "outrasDespesas", false, false, false, false));
        listFields.add(new Field("numeroNF", "numeroNF", false, false, false, false));
        listFields.add(new Field("idEnderecoEntrega", "idEnderecoEntrega", false, false, false, false));
        listFields.add(new Field("dataEntrega", "dataEntrega", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        listFields.add(new Field("observacoes", "observacoes", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "PedidoCompra";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return PedidoCompraDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdCotacao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCotacao", "=", parm));
        ordenacao.add(new Order("idPedido"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findEntreguesByIdCotacao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCotacao", "=", parm));
        condicao.add(new Condition("situacao", "=", SituacaoPedidoCompra.Entregue.name()));
        ordenacao.add(new Order("idPedido"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void excluiRelacionamentos(final Collection<PedidoCompraDTO> col) throws PersistenceException {
        if (col == null) {
            return;
        }
        final EntregaItemRequisicaoDao inspecaoEntregaDao = new EntregaItemRequisicaoDao();
        inspecaoEntregaDao.setTransactionControler(this.getTransactionControler());
        final InspecaoPedidoCompraDao inspecaoPedidoDao = new InspecaoPedidoCompraDao();
        inspecaoPedidoDao.setTransactionControler(this.getTransactionControler());
        for (final PedidoCompraDTO pedidoCompraDto : col) {
            inspecaoEntregaDao.deleteByIdPedido(pedidoCompraDto.getIdPedido());
            inspecaoPedidoDao.deleteByIdPedido(pedidoCompraDto.getIdPedido());
        }
    }

    public void deleteByIdCotacao(final Integer parm) throws PersistenceException {
        final Collection<PedidoCompraDTO> col = this.findByIdCotacao(parm);
        this.excluiRelacionamentos(col);
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idCotacao", "=", parm));
        super.deleteByCondition(condicao);
    }

}
