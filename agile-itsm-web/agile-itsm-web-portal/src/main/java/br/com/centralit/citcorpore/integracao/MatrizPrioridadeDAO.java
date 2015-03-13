package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.MatrizPrioridadeDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 *
 * @author rodrigo.oliveira
 *
 */
public class MatrizPrioridadeDAO extends CrudDaoDefaultImpl {

    public MatrizPrioridadeDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("idMatrizPrioridade"));
        return super.find(obj, list);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idMatrizPrioridade", "idMatrizPrioridade", true, true, false, false));
        listFields.add(new Field("siglaImpacto", "siglaImpacto", false, false, false, false));
        listFields.add(new Field("siglaUrgencia", "siglaUrgencia", false, false, false, false));
        listFields.add(new Field("valorPrioridade", "valorPrioridade", false, false, false, false));
        listFields.add(new Field("idContrato", "idContrato", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "MatrizPrioridade";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("idMatrizPrioridade"));
        return super.list(list);
    }

    @Override
    public Class getBean() {
        return MatrizPrioridadeDTO.class;
    }

    public Integer consultaValorPrioridade(final String siglaImpacto, final String siglaUrgencia) throws PersistenceException {
        final List parametros = new ArrayList<>();
        parametros.add(siglaImpacto);
        parametros.add(siglaUrgencia);

        final String sql = "SELECT * FROM " + this.getTableName() + " WHERE siglaImpacto = ? and siglaUrgencia = ? ";
        final List dados = this.execSQL(sql, parametros.toArray());

        final List fields = new ArrayList<>();
        fields.add("idMatrizPrioridade");
        fields.add("siglaImpacto");
        fields.add("siglaUrgencia");
        fields.add("valorPrioridade");
        fields.add("idContrato");

        final Integer valorResp = new Integer(0);

        final Collection<MatrizPrioridadeDTO> listaMatriz = this.listConvertion(this.getBean(), dados, fields);

        if (listaMatriz != null && !listaMatriz.isEmpty()) {
            for (final MatrizPrioridadeDTO matrizPrioridadeDTO : listaMatriz) {
                return matrizPrioridadeDTO.getValorPrioridade();
            }
        }
        return valorResp;

    }

    public void deleteMatriz() throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List parametros = new ArrayList<>();
        parametros.add(0);
        condicao.add(new Condition("idMatrizPrioridade", ">", parametros));
        super.deleteByCondition(condicao);
    }

}
