package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ItemPedidoCompraDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ItemPedidoCompraDao extends CrudDaoDefaultImpl {

    public ItemPedidoCompraDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idItemPedido", "idItemPedido", true, true, false, false));
        listFields.add(new Field("idPedido", "idPedido", false, false, false, false));
        listFields.add(new Field("idColetaPreco", "idColetaPreco", false, false, false, false));
        listFields.add(new Field("idProduto", "idProduto", false, false, false, false));
        listFields.add(new Field("quantidade", "quantidade", false, false, false, false));
        listFields.add(new Field("valorTotal", "valorTotal", false, false, false, false));
        listFields.add(new Field("valorDesconto", "valorDesconto", false, false, false, false));
        listFields.add(new Field("valorAcrescimo", "valorAcrescimo", false, false, false, false));
        listFields.add(new Field("baseCalculoIcms", "baseCalculoIcms", false, false, false, false));
        listFields.add(new Field("aliquotaIcms", "aliquotaIcms", false, false, false, false));
        listFields.add(new Field("aliquotaIpi", "aliquotaIpi", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "ItemPedidoCompra";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ItemPedidoCompraDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdPedido(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idPedido", "=", parm));
        ordenacao.add(new Order("idItemPedido"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdColetaPreco(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idColetaPreco", "=", parm));
        ordenacao.add(new Order("idItemPedido"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdPedido(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idPedido", "=", parm));
        super.deleteByCondition(condicao);
    }

    public void deleteByIdColetaPreco(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idColetaPreco", "=", parm));
        super.deleteByCondition(condicao);
    }

    private StringBuilder getSQLRestoreAll() {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT ip.idItemPedido, ip.idPedido, ip.idColetaPreco, ip.idProduto, ip.quantidade, ip.valorTotal, ip.valorDesconto, ip.valorAcrescimo, ");
        sql.append("       item.descricaoItem, item.idSolicitacaoServico, item.idParecerValidacao, item.idParecerAutorizacao, cp.idParecer, e1.nome, e2.nome, e3.nome ");
        sql.append("  FROM itempedidocompra ip ");
        sql.append("       INNER JOIN cotacaoitemrequisicao cp ON cp.idColetaPreco = ip.idColetaPreco ");
        sql.append("       INNER JOIN itemrequisicaoproduto item ON item.idItemRequisicaoProduto = cp.idItemRequisicaoProduto ");
        sql.append("        LEFT JOIN parecer p1 ON item.idParecerValidacao = p1.idParecer ");
        sql.append("        LEFT JOIN parecer p2 ON item.idParecerAutorizacao = p2.idParecer ");
        sql.append("        LEFT JOIN parecer p3 ON cp.idParecer = p3.idParecer ");
        sql.append("        LEFT JOIN empregados e1 ON p1.idResponsavel = e1.idEmpregado ");
        sql.append("        LEFT JOIN empregados e2 ON p2.idResponsavel = e2.idEmpregado ");
        sql.append("        LEFT JOIN empregados e3 ON p3.idResponsavel = e3.idEmpregado ");

        return sql;
    }

    private List getColunasRestoreAll() {
        final List listRetorno = new ArrayList<>();
        listRetorno.add("idItemPedido");
        listRetorno.add("idPedido");
        listRetorno.add("idColetaPreco");
        listRetorno.add("idProduto");
        listRetorno.add("quantidade");
        listRetorno.add("valorTotal");
        listRetorno.add("valorDesconto");
        listRetorno.add("valorAcrescimo");
        listRetorno.add("descricaoItem");
        listRetorno.add("idSolicitacaoServico");
        listRetorno.add("idParecerValidacao");
        listRetorno.add("idParecerAutorizacao");
        listRetorno.add("idParecerCotacao");
        listRetorno.add("autoridadeValidacao");
        listRetorno.add("autoridadeAprovacao");
        listRetorno.add("autoridadeCotacao");
        return listRetorno;
    }

    public Collection findByIdPedidoOrderByIdSolicitacao(final Integer idPedido) throws PersistenceException {
        final List parametro = new ArrayList<>();

        final StringBuilder sql = this.getSQLRestoreAll();
        sql.append("  WHERE ip.idPedido = ? ");

        parametro.add(idPedido);
        sql.append("ORDER BY item.idSolicitacaoServico, ip.idItemPedido");

        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        return engine.listConvertion(this.getBean(), lista, this.getColunasRestoreAll());
    }

}
