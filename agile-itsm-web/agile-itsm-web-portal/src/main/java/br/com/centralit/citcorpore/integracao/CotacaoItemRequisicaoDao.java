package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.CotacaoItemRequisicaoDTO;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoCotacaoItemRequisicao;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoItemRequisicaoProduto;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoSolicitacaoServico;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class CotacaoItemRequisicaoDao extends CrudDaoDefaultImpl {

    public CotacaoItemRequisicaoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idColetaPreco", "idColetaPreco", true, false, false, false));
        listFields.add(new Field("idItemRequisicaoProduto", "idItemRequisicaoProduto", true, false, false, false));
        listFields.add(new Field("idParecer", "idParecer", false, false, false, false));
        listFields.add(new Field("idItemTrabalho", "idItemTrabalho", false, false, false, false));
        listFields.add(new Field("idSolicitacaoServico", "idSolicitacaoServico", false, false, false, false));
        listFields.add(new Field("idCotacao", "idCotacao", false, false, false, false));
        listFields.add(new Field("quantidade", "quantidade", false, false, false, false));
        listFields.add(new Field("quantidadeEntregue", "quantidadeEntregue", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "CotacaoItemRequisicao";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return CotacaoItemRequisicaoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findDisponiveisAprovacaoByIdSolicitacaoServico(final Integer idSolicitacaoServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition("situacao", "=", SituacaoCotacaoItemRequisicao.AguardaAprovacao.name()));
        condicao.add(new Condition("idItemTrabalho", "IS", null));
        ordenacao.add(new Order("idItemRequisicaoProduto"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdCotacao(final Integer idCotacao) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCotacao", "=", idCotacao));
        ordenacao.add(new Order("idItemRequisicaoProduto"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findPendentesByIdCotacao(final Integer idCotacao) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCotacao", "=", idCotacao));
        condicao.add(new Condition("situacao", "=", SituacaoCotacaoItemRequisicao.AguardaAprovacao.name()));
        ordenacao.add(new Order("idItemRequisicaoProduto"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdRequisicaoProduto(final Integer parm) throws PersistenceException {
        final List parametro = new ArrayList<>();

        final StringBuilder sql = this.getSQLRestoreAll();
        sql.append("  WHERE ci.idSolicitacaoServico = ? ");

        parametro.add(parm);
        sql.append("ORDER BY ci.idItemRequisicaoProduto");

        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        return engine.listConvertion(this.getBean(), lista, this.getColunasRestoreAll());
    }

    public Collection findAprovadasByIdCentroResultado(final Integer idCentroResultado) throws PersistenceException {
        final List parametro = new ArrayList<>();

        final StringBuilder sql = this.getSQLRestoreAll();
        sql.append("  INNER JOIN solicitacaoservico sol ON sol.idSolicitacaoServico = ci.idSolicitacaoServico ");
        sql.append("  WHERE rp.idCentroCusto = ? ");
        sql.append("    AND sol.situacao <> ? ");
        sql.append("    AND item.situacao <> ? ");
        sql.append("    AND (ci.situacao = ? or ci.situacao = ?) ");

        parametro.add(idCentroResultado);
        parametro.add(SituacaoSolicitacaoServico.Cancelada.name());
        parametro.add(SituacaoItemRequisicaoProduto.Cancelado.name());
        parametro.add(SituacaoCotacaoItemRequisicao.PreAprovado.name());
        parametro.add(SituacaoCotacaoItemRequisicao.Aprovado.name());
        sql.append("ORDER BY ci.idItemRequisicaoProduto");

        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        return engine.listConvertion(this.getBean(), lista, this.getColunasRestoreAll());
    }

    public Collection findByIdColetaPreco(final Integer parm) throws PersistenceException {
        final List parametro = new ArrayList<>();

        final StringBuilder sql = this.getSQLRestoreAll();
        sql.append("  WHERE ci.idColetaPreco = ? ");

        parametro.add(parm);
        sql.append("ORDER BY item.descricaoItem");

        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        return engine.listConvertion(this.getBean(), lista, this.getColunasRestoreAll());
    }

    public Collection findByIdColetaPrecoOrderQtde(final Integer parm) throws PersistenceException {
        final List parametro = new ArrayList<>();

        final StringBuilder sql = this.getSQLRestoreAll();
        sql.append("  WHERE ci.idColetaPreco = ? ");

        parametro.add(parm);
        sql.append("ORDER BY item.quantidade desc");

        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        return engine.listConvertion(this.getBean(), lista, this.getColunasRestoreAll());
    }

    public void excluiRelacionamentos(final Collection<CotacaoItemRequisicaoDTO> col) throws PersistenceException {
        if (col == null) {
            return;
        }
        final EntregaItemRequisicaoDao inspecaoEntregaDao = new EntregaItemRequisicaoDao();
        inspecaoEntregaDao.setTransactionControler(this.getTransactionControler());
        for (final CotacaoItemRequisicaoDTO itemDto : col) {
            inspecaoEntregaDao.deleteByIdItemRequisicaoAndIdColetaPreco(itemDto.getIdItemRequisicaoProduto(), itemDto.getIdColetaPreco());
        }
    }

    public void deleteByIdColetaPreco(final Integer parm) throws PersistenceException {
        final Collection<CotacaoItemRequisicaoDTO> col = this.findByIdColetaPreco(parm);
        this.excluiRelacionamentos(col);
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idColetaPreco", "=", parm));
        super.deleteByCondition(condicao);
    }

    public void deleteByIdCotacao(final Integer parm) throws PersistenceException {
        final Collection<CotacaoItemRequisicaoDTO> col = this.findByIdCotacao(parm);
        this.excluiRelacionamentos(col);
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idCotacao", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdItemRequisicaoProduto(final Integer parm) throws PersistenceException {
        final List parametro = new ArrayList<>();

        final StringBuilder sql = this.getSQLRestoreAll();
        sql.append("  WHERE ci.idItemRequisicaoProduto = ? ");

        parametro.add(parm);
        sql.append("ORDER BY ci.idColetaPreco");

        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        return engine.listConvertion(this.getBean(), lista, this.getColunasRestoreAll());
    }

    public void deleteByIdItemRequisicaoProduto(final Integer parm) throws PersistenceException {
        final Collection<CotacaoItemRequisicaoDTO> col = this.findByIdItemRequisicaoProduto(parm);
        this.excluiRelacionamentos(col);
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idItemRequisicaoProduto", "=", parm));
        super.deleteByCondition(condicao);
    }

    private StringBuilder getSQLRestoreAll() {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT ci.idColetaPreco, ci.idItemRequisicaoProduto, ci.idParecer, ci.idItemTrabalho,  ");
        sql.append("       ci.idSolicitacaoServico, ci.quantidade, ci.quantidadeEntregue, ci.situacao, ci.idCotacao, ");
        sql.append("       par.idJustificativa, par.complementoJustificativa, par.aprovado, item.idProduto, prod.idMarca, item.idUnidadeMedida, ");
        sql.append("       item.descricaoItem, item.idCategoriaProduto, item.percVariacaoPreco, prod.codigoProduto, tprod.nomeProduto, forn.cnpj, forn.nomeFantasia, ");
        sql.append("       c.nomeCategoria, u.siglaUnidadeMedida, cp.especificacoes, ((cp.preco - cp.valorDesconto + cp.valorAcrescimo) / cp.quantidadeCotada), ");
        sql.append("       ((cp.preco - cp.valorDesconto + cp.valorAcrescimo) / cp.quantidadeCotada) * ci.quantidade, cp.prazoEntrega, cp.taxaJuros, forn.tipoPessoa, cp.idItemCotacao, item.idParecerAutorizacao, rp.finalidade   ");
        sql.append("  FROM cotacaoitemrequisicao ci ");
        sql.append("       INNER JOIN itemrequisicaoproduto item ON item.idItemRequisicaoProduto = ci.idItemRequisicaoProduto ");
        sql.append("       INNER JOIN coletapreco cp ON cp.idColetaPreco = ci.idColetaPreco ");
        sql.append("       INNER JOIN itemcotacao ic ON ic.idItemCotacao = cp.idItemCotacao ");
        sql.append("       INNER JOIN fornecedor forn ON forn.idfornecedor = cp.idfornecedor ");
        sql.append("       INNER JOIN requisicaoproduto rp ON rp.idSolicitacaoServico = ci.idSolicitacaoServico ");
        sql.append("        LEFT JOIN parecer par ON par.idParecer = ci.idParecer ");
        sql.append("        LEFT JOIN produto prod ON prod.idProduto = item.idProduto ");
        sql.append("        LEFT JOIN tipoproduto tprod  ON tprod.idTipoProduto = prod.idTipoProduto ");
        sql.append("        LEFT JOIN categoriaproduto c ON c.idCategoria = tprod.idCategoria ");
        sql.append("        LEFT JOIN unidademedida u ON u.idunidademedida = item.idunidademedida ");

        return sql;
    }

    private List getColunasRestoreAll() {
        final List listRetorno = new ArrayList<>();
        listRetorno.add("idColetaPreco");
        listRetorno.add("idItemRequisicaoProduto");
        listRetorno.add("idParecer");
        listRetorno.add("idItemTrabalho");
        listRetorno.add("idSolicitacaoServico");
        listRetorno.add("quantidade");
        listRetorno.add("quantidadeEntregue");
        listRetorno.add("situacao");
        listRetorno.add("idCotacao");
        listRetorno.add("idJustificativa");
        listRetorno.add("complementoJustificativa");
        listRetorno.add("aprovado");
        listRetorno.add("idProduto");
        listRetorno.add("idMarca");
        listRetorno.add("idUnidadeMedida");
        listRetorno.add("descricaoItem");
        listRetorno.add("idCategoriaProduto");
        listRetorno.add("percVariacaoPreco");
        listRetorno.add("codigoProduto");
        listRetorno.add("nomeProduto");
        listRetorno.add("cpfCnpjFornecedor");
        listRetorno.add("nomeFornecedor");
        listRetorno.add("nomeCategoria");
        listRetorno.add("siglaUnidadeMedida");
        listRetorno.add("especificacoes");
        listRetorno.add("preco");
        listRetorno.add("valorTotal");
        listRetorno.add("prazoEntrega");
        listRetorno.add("taxaJuros");
        listRetorno.add("tipoFornecedor");
        listRetorno.add("idItemCotacao");
        listRetorno.add("idParecerAutorizacao");
        listRetorno.add("finalidade");
        return listRetorno;
    }

    public Collection findByIdItemTrabalho(final Integer parm) throws PersistenceException {
        final List parametro = new ArrayList<>();

        final StringBuilder sql = this.getSQLRestoreAll();
        sql.append("  WHERE ci.idItemTrabalho = ? ");

        parametro.add(parm);
        sql.append("ORDER BY item.descricaoItem");

        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        return engine.listConvertion(this.getBean(), lista, this.getColunasRestoreAll());
    }

}
