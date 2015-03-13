package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ProdutoContratoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ProdutoContratoDao extends CrudDaoDefaultImpl {

    public ProdutoContratoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idProdutoContrato", "idProdutoContrato", true, true, false, false));
        listFields.add(new Field("idContrato", "idContrato", false, false, false, false));
        listFields.add(new Field("nomeProduto", "nomeProduto", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "ProdutoContrato";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ProdutoContratoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdContrato(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idContrato", "=", parm));
        ordenacao.add(new Order("nomeProduto"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdContrato(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idContrato", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection getProdutosByIdMarcoPagamentoPrj(final Integer idMarcoPagamentoPrjParm, final Integer idLinhaBaseProjetoParm) throws PersistenceException {
        final String sql = "SELECT ProdutoContrato.idProdutoContrato, nomeProduto, count(*) FROM ProdutoContrato "
                + "INNER JOIN ProdutoTarefaLinBaseProj ON ProdutoTarefaLinBaseProj.idProdutoContrato = ProdutoContrato.idProdutoContrato "
                + "INNER JOIN TarefaLinhaBaseProjeto ON TarefaLinhaBaseProjeto.idTarefaLinhaBaseProjeto = ProdutoTarefaLinBaseProj.idTarefaLinhaBaseProjeto "
                + "WHERE TarefaLinhaBaseProjeto.idMarcoPagamentoPrj = ? AND TarefaLinhaBaseProjeto.idLinhaBaseProjeto = ? AND (deleted IS NULL or deleted = 'N') "
                + "GROUP BY ProdutoContrato.idProdutoContrato, nomeProduto " + "ORDER BY nomeProduto";
        final List lstDados = this.execSQL(sql, new Object[] {idMarcoPagamentoPrjParm, idLinhaBaseProjetoParm});
        final List fields = new ArrayList<>();
        fields.add("idProdutoContrato");
        fields.add("nomeProduto");
        fields.add("qtde");
        return this.listConvertion(this.getBean(), lstDados, fields);
    }

}
