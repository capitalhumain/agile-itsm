package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.UfDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class UfDao extends CrudDaoDefaultImpl {

    public UfDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return UfDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idUF", "idUf", true, false, false, true));
        listFields.add(new Field("idregioes", "idRegioes", true, false, false, true));
        listFields.add(new Field("NomeUF", "nomeUf", false, false, false, false));
        listFields.add(new Field("SiglaUF", "siglaUf", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "UFS";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("nomeUf"));
        return super.list(list);
    }

    public Collection<UfDTO> listByIdRegioes(final UfDTO obj) throws PersistenceException {
        List list = new ArrayList<>();
        final List fields = new ArrayList<>();
        final String sql = "select iduf,nomeuf from " + this.getTableName() + " where idregioes = ? ";
        fields.add("idUf");
        fields.add("nomeUf");
        list = this.execSQL(sql, new Object[] {obj.getIdRegioes()});
        final Collection<UfDTO> result = engine.listConvertion(this.getBean(), list, fields);
        return result;
    }

    public Collection<UfDTO> listByIdPais(final UfDTO obj) throws PersistenceException {
        List list = new ArrayList<>();
        final List fields = new ArrayList<>();
        final String sql = "select iduf,nomeuf from " + this.getTableName() + " where idpais = ? ";

        fields.add("idUf");
        fields.add("nomeUf");
        list = this.execSQL(sql, new Object[] {obj.getIdPais()});
        if (list != null && !list.isEmpty()) {
            final Collection<UfDTO> result = engine.listConvertion(this.getBean(), list, fields);
            return result;
        }
        return null;
    }

    public UfDTO findByIdUf(final Integer idUf) throws PersistenceException {
        List list = new ArrayList<>();
        final List fields = new ArrayList<>();
        final String sql = "select iduf,nomeuf,idPais from " + this.getTableName() + " where idUf = ? ";
        fields.add("idUf");
        fields.add("nomeUf");
        fields.add("idPais");

        list = this.execSQL(sql, new Object[] {idUf});
        final List<UfDTO> result = engine.listConvertion(this.getBean(), list, fields);
        if (result != null && !result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

}
