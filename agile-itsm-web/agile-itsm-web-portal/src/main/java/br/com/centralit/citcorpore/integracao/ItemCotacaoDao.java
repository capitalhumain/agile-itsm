package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ItemCotacaoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ItemCotacaoDao extends CrudDaoDefaultImpl {

    public ItemCotacaoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idItemCotacao", "idItemCotacao", true, true, false, false));
        listFields.add(new Field("idCotacao", "idCotacao", false, false, false, false));
        listFields.add(new Field("idProduto", "idProduto", false, false, false, false));
        listFields.add(new Field("quantidade", "quantidade", false, false, false, false));
        listFields.add(new Field("dataHoraLimite", "dataHoraLimite", false, false, false, false));
        listFields.add(new Field("idCategoriaProduto", "idCategoriaProduto", false, false, false, false));
        listFields.add(new Field("idUnidadeMedida", "idUnidadeMedida", false, false, false, false));
        listFields.add(new Field("descricaoItem", "descricaoItem", false, false, false, false));
        listFields.add(new Field("especificacoes", "especificacoes", false, false, false, false));
        listFields.add(new Field("marcaPreferencial", "marcaPreferencial", false, false, false, false));
        listFields.add(new Field("precoAproximado", "precoAproximado", false, false, false, false));
        listFields.add(new Field("tipoIdentificacao", "tipoIdentificacao", false, false, false, false));
        listFields.add(new Field("resultadoValidacao", "resultadoValidacao", false, false, false, false));
        listFields.add(new Field("mensagensValidacao", "mensagensValidacao", false, false, false, false));
        listFields.add(new Field("pesoPreco", "pesoPreco", false, false, false, false));
        listFields.add(new Field("pesoPrazoEntrega", "pesoPrazoEntrega", false, false, false, false));
        listFields.add(new Field("pesoPrazoPagto", "pesoPrazoPagto", false, false, false, false));
        listFields.add(new Field("pesoTaxaJuros", "pesoTaxaJuros", false, false, false, false));
        listFields.add(new Field("pesoPrazoGarantia", "pesoPrazoGarantia", false, false, false, false));
        listFields.add(new Field("exigeFornecedorQualificado", "exigeFornecedorQualificado", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "ItemCotacao";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ItemCotacaoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    private StringBuilder getSQLRestoreAll() {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT item.idItemCotacao, item.idCotacao, item.idProduto, item.descricaoItem, item.especificacoes, item.idCategoriaProduto,");
        sql.append("       item.precoAproximado, item.idUnidadeMedida, item.tipoIdentificacao, item.quantidade, item.marcaPreferencial, ");
        sql.append("       item.dataHoraLimite, item.resultadoValidacao, item.mensagensValidacao, item.exigeFornecedorQualificado, ");
        sql.append("       item.pesoPreco, item.pesoPrazoEntrega, item.pesoPrazoPagto, item.pesoTaxaJuros, item.pesoPrazoGarantia, ");
        sql.append("       prod.codigoProduto, tp.nomeProduto, prod.complemento, prod.modelo, m.nomeMarca ");
        sql.append("  FROM itemcotacao item ");
        sql.append("        LEFT JOIN produto prod ON prod.idproduto = item.idproduto ");
        sql.append("        LEFT JOIN tipoproduto tp ON tp.idtipoproduto = prod.idtipoproduto ");
        sql.append("        LEFT JOIN marca m ON m.idmarca = prod.idmarca ");
        return sql;
    }

    private List getColunasRestoreAll() {

        final List listRetorno = new ArrayList<>();

        listRetorno.add("idItemCotacao");
        listRetorno.add("idCotacao");
        listRetorno.add("idProduto");
        listRetorno.add("descricaoItem");
        listRetorno.add("especificacoes");
        listRetorno.add("idCategoriaProduto");
        listRetorno.add("precoAproximado");
        listRetorno.add("idUnidadeMedida");
        listRetorno.add("tipoIdentificacao");
        listRetorno.add("quantidade");
        listRetorno.add("marcaPreferencial");
        listRetorno.add("dataHoraLimite");
        listRetorno.add("resultadoValidacao");
        listRetorno.add("mensagensValidacao");
        listRetorno.add("exigeFornecedorQualificado");
        listRetorno.add("pesoPreco");
        listRetorno.add("pesoPrazoEntrega");
        listRetorno.add("pesoPrazoPagto");
        listRetorno.add("pesoTaxaJuros");
        listRetorno.add("pesoPrazoGarantia");
        listRetorno.add("codigoProduto");
        listRetorno.add("nomeProduto");
        listRetorno.add("complemento");
        listRetorno.add("modelo");
        listRetorno.add("nomeMarca");
        return listRetorno;
    }

    public Collection findByIdCotacao(final Integer parm) throws PersistenceException {
        final List parametro = new ArrayList<>();

        final StringBuilder sql = this.getSQLRestoreAll();
        sql.append("  WHERE item.idCotacao = ? ");

        parametro.add(parm);
        sql.append("ORDER BY tp.nomeProduto");

        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        return engine.listConvertion(this.getBean(), lista, this.getColunasRestoreAll());
    }

    public void deleteByIdCotacao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idCotacao", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdItemRequisicaoProduto(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idItemRequisicaoProduto", "=", parm));
        ordenacao.add(new Order("idItemCotacao"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdItemRequisicaoProduto(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idItemRequisicaoProduto", "=", parm));
        super.deleteByCondition(condicao);
    }

    public void limpaMensagensValidacao(final ItemCotacaoDTO itemCotacaoDto) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        sql.append("UPDATE " + this.getTableName() + " SET resultadoValidacao = null, mensagensValidacao = null WHERE idItemCotacao = ?");
        final Object[] params = {itemCotacaoDto.getIdItemCotacao()};
        try {
            this.execUpdate(sql.toString(), params);
        } catch (final PersistenceException e) {
            System.out.println("Problemas com atualização do item de cotação.");
            e.printStackTrace();
        }
    }

}
