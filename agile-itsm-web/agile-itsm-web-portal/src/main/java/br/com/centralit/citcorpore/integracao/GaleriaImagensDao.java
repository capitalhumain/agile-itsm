package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.GaleriaImagensDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class GaleriaImagensDao extends CrudDaoDefaultImpl {

    public GaleriaImagensDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    public Collection findByCategoria(final Integer idCategoria) throws PersistenceException {
        final List lstCondicao = new ArrayList<>();
        final List lstOrder = new ArrayList<>();

        lstCondicao.add(new Condition("idCategoriaGaleriaImagem", "=", idCategoria));

        lstOrder.add(new Order("descricaoImagem"));

        return super.findByCondition(lstCondicao, lstOrder);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idImagem", "idImagem", true, true, false, false));
        listFields.add(new Field("idCategoriaGaleriaImagem", "idCategoriaGaleriaImagem", false, false, false, false));
        listFields.add(new Field("nomeImagem", "nomeImagem", false, false, false, false));
        listFields.add(new Field("descricaoImagem", "descricaoImagem", false, false, false, false));
        listFields.add(new Field("detalhamento", "detalhamento", false, false, false, false));
        listFields.add(new Field("extensao", "extensao", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "GaleriaImagens";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    public Collection listOrderByCategoria() throws PersistenceException {
        new ArrayList<>();
        final Collection col = this.getFields();
        final List lstCamposRetorno = new ArrayList<>();
        String sql = "";
        if (col != null) {
            for (final Iterator it = col.iterator(); it.hasNext();) {
                final Field field = (Field) it.next();
                if (!sql.equalsIgnoreCase("")) {
                    sql += ",";
                }
                sql += "A." + field.getFieldDB();

                lstCamposRetorno.add(field.getFieldClass());
            }
        }
        sql = "SELECT " + sql + ", B.nomeCategoria FROM " + this.getTableName() + " A INNER JOIN CategoriaGaleriaImagem B "
                + "ON B.idCategoriaGaleriaImagem = A.idCategoriaGaleriaImagem ";
        sql += " ORDER BY B.nomeCategoria, A.descricaoImagem";

        lstCamposRetorno.add("nomeCategoria");

        final List lista = this.execSQL(sql, null);
        final List result = engine.listConvertion(this.getBean(), lista, lstCamposRetorno);
        return result;
    }

    @Override
    public Class getBean() {
        return GaleriaImagensDTO.class;
    }

}
