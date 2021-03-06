package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.BICategoriasDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class BICategoriasDao extends CrudDaoDefaultImpl {

    public BICategoriasDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idCategoria", "idCategoria", true, true, false, false));
        listFields.add(new Field("nomeCategoria", "nomeCategoria", false, false, false, false));
        listFields.add(new Field("identificacao", "identificacao", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        listFields.add(new Field("idCategoriaPai", "idCategoriaPai", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "BI_Categorias";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return BICategoriasDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdCategoriaPai(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCategoriaPai", "=", parm));
        ordenacao.add(new Order("nomeCategoria"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findSemPai() throws PersistenceException {
        final String sql = "SELECT " + this.getNamesFieldsStr() + " FROM " + this.getTableName() + " WHERE idCategoriaPai IS NULL ORDER by nomeCategoria";
        final List lst = this.execSQL(sql, null);
        return this.listConvertion(this.getBean(), lst, this.getListNamesFieldClass());
    }

}
