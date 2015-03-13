package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AvaliacaoReferenciaFornecedorDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class AvaliacaoReferenciaFornecedorDao extends CrudDaoDefaultImpl {

    public AvaliacaoReferenciaFornecedorDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idAvaliacaoFornecedor", "idAvaliacaoFornecedor", true, false, false, false));
        listFields.add(new Field("idEmpregado", "idEmpregado", true, false, false, false));
        listFields.add(new Field("decisao", "decisao", false, false, false, false));
        listFields.add(new Field("observacoes", "observacoes", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "avaliacaoReferenciaFornecedor";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return AvaliacaoReferenciaFornecedorDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public void deleteByIdAvaliacaoFornecedor(final Integer idAvaliacaoFornecedor) throws PersistenceException {
        final List lstCondicao = new ArrayList<>();
        lstCondicao.add(new Condition("idAvaliacaoFornecedor", "=", idAvaliacaoFornecedor));
        super.deleteByCondition(lstCondicao);
    }

    /**
     * Lista AvaliacaoReferenciaFornecedorDTO por idAvaliacaoFornecedor.
     *
     * @param idBaseConhecimento
     * @return Collection<AvaliacaoReferenciaFornecedorDTO>
     * @throws Exception
     * @author thays.araujo
     */
    public Collection<AvaliacaoReferenciaFornecedorDTO> listByIdAvaliacaoFornecedor(final Integer idAvaliacaoFornecedor) throws PersistenceException {
        final List parametros = new ArrayList<>();
        final List listRetorno = new ArrayList<>();

        List list = new ArrayList<>();

        final StringBuilder sql = new StringBuilder();

        sql.append("select empregados.idempregado,avaliacaoreferenciafornecedor.idavaliacaofornecedor,empregados.nome,empregados.telefone,avaliacaoreferenciafornecedor.decisao,avaliacaoreferenciafornecedor.observacoes ");
        sql.append("from avaliacaoreferenciafornecedor ");
        sql.append("inner join empregados on empregados.idempregado = avaliacaoreferenciafornecedor.idempregado ");

        if (idAvaliacaoFornecedor != null) {
            sql.append("where idavaliacaofornecedor = ?");
            parametros.add(idAvaliacaoFornecedor);
        }

        list = this.execSQL(sql.toString(), parametros.toArray());
        listRetorno.add("idEmpregado");
        listRetorno.add("idAvaliacaoFornecedor");
        listRetorno.add("nome");
        listRetorno.add("telefone");
        listRetorno.add("decisao");
        listRetorno.add("observacoes");

        if (list != null && !list.isEmpty()) {
            final Collection<AvaliacaoReferenciaFornecedorDTO> listAvaliacaoReferenciaFornecedor = this.listConvertion(AvaliacaoReferenciaFornecedorDTO.class,
                    list, listRetorno);
            return listAvaliacaoReferenciaFornecedor;
        }

        return null;
    }

}
