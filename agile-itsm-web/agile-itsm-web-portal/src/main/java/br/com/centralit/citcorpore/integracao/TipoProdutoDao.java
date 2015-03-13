package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.TipoProdutoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class TipoProdutoDao extends CrudDaoDefaultImpl {

    public TipoProdutoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idTipoProduto", "idTipoProduto", true, true, false, false));
        listFields.add(new Field("idCategoria", "idCategoria", false, false, false, false));
        listFields.add(new Field("idUnidadeMedida", "idUnidadeMedida", false, false, false, false));
        listFields.add(new Field("nomeProduto", "nomeProduto", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        listFields.add(new Field("aceitaRequisicao", "aceitaRequisicao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "TipoProduto";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return TipoProdutoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    private StringBuilder getSQLRestore() {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT t.idTipoProduto, t.idCategoria, t.idUnidadeMedida, t.nomeProduto, t.aceitaRequisicao, ");
        sql.append("       t.situacao, c.nomeCategoria, u.siglaUnidadeMedida  ");
        sql.append("  FROM tipoproduto t  ");
        sql.append("      INNER JOIN categoriaproduto c ON c.idCategoria = t.idCategoria ");
        sql.append("       LEFT JOIN unidademedida u ON u.idUnidadeMedida = t.idUnidadeMedida ");
        return sql;
    }

    private List getColunasRestore() {
        final List listRetorno = new ArrayList<>();
        listRetorno.add("idTipoProduto");
        listRetorno.add("idCategoria");
        listRetorno.add("idUnidadeMedida");
        listRetorno.add("nomeProduto");
        listRetorno.add("aceitaRequisicao");
        listRetorno.add("situacao");
        listRetorno.add("nomeCategoria");
        listRetorno.add("siglaUnidadeMedida");

        return listRetorno;
    }

    @Override
    public BaseEntity restore(final BaseEntity obj) throws PersistenceException {
        final List parametro = new ArrayList<>();

        final StringBuilder sql = this.getSQLRestore();
        sql.append("  WHERE t.idTipoProduto = ? ");

        parametro.add(((TipoProdutoDTO) obj).getIdTipoProduto());

        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        final List<TipoProdutoDTO> result = engine.listConvertion(this.getBean(), lista, this.getColunasRestore());
        if (result != null && !result.isEmpty()) {
            return result.get(0);
        } else {
            return null;
        }
    }

    public List<TipoProdutoDTO> findByIdCategoria(final Integer idCategoria) throws PersistenceException {
        final List parametro = new ArrayList<>();

        final StringBuilder sql = this.getSQLRestore();
        sql.append("  WHERE t.idCategoria = ? ");
        sql.append("  ORDER BY t.nomeProduto ");

        parametro.add(idCategoria);

        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        return engine.listConvertion(this.getBean(), lista, this.getColunasRestore());
    }

    public boolean consultarTiposProdutos(final TipoProdutoDTO tipoProdutoDTO) throws PersistenceException {
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("select idTipoProduto From " + this.getTableName() + "  where  nomeProduto = ?    ");

        parametro.add(tipoProdutoDTO.getNomeProduto());

        if (tipoProdutoDTO.getIdTipoProduto() != null) {
            sql.append("and idTipoProduto <> ?");
            parametro.add(tipoProdutoDTO.getIdTipoProduto());
        }

        list = this.execSQL(sql.toString(), parametro.toArray());
        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

}
