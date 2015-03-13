package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ComandoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * @author ygor.magalhaes
 *
 */
public class ComandoDao extends CrudDaoDefaultImpl {

    public ComandoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return ComandoDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("ID", "id", true, true, false, false));
        listFields.add(new Field("DESCRICAO", "descricao", false, false, false, true, "Descrição!"));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "COMANDO";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List ordem = new ArrayList<>();
        ordem.add(new Order("descricao"));
        return super.find(obj, ordem);
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("descricao"));
        return super.list(list);
    }

    public ComandoDTO listItemCadastrado(final String descricao) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List fields = new ArrayList<>();
        List list = new ArrayList<>();
        final String sql = "select descricao from " + this.getTableName() + " where descricao LIKE ? ";
        parametro.add(descricao);
        list = this.execSQL(sql, parametro.toArray());
        fields.add("descricao");
        if (list.isEmpty()) {
            return null;
        }
        return (ComandoDTO) this.listConvertion(this.getBean(), list, fields).get(0);
    }

}
