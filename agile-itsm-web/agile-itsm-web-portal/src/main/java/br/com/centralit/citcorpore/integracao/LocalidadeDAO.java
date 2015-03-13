package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.LocalidadeDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class LocalidadeDAO extends CrudDaoDefaultImpl {

    public LocalidadeDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idlocalidade", "idLocalidade", true, true, false, false));
        listFields.add(new Field("nomelocalidade", "nomeLocalidade", false, false, false, false));
        listFields.add(new Field("datainicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("datafim", "dataFim", false, false, false, false));

        return listFields;
    }

    /**
     * Retorna lista de status de usuário.
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public boolean verificarLocalidadeAtiva(final LocalidadeDTO obj) throws PersistenceException {
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        String sql = "select idlocalidade From " + this.getTableName() + "  where  nomelocalidade = ?   and dataFim is null ";

        if (obj.getIdLocalidade() != null) {
            sql += " and idlocalidade <> " + obj.getIdLocalidade();
        }

        parametro.add(obj.getNomeLocalidade());
        list = this.execSQL(sql, parametro.toArray());
        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public String getTableName() {
        return "localidade";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return LocalidadeDTO.class;
    }

    /**
     * Retorna lista de localidades
     *
     * @param
     * @return Collection
     * @throws Exception
     * @author thays.araujo
     */
    public Collection<LocalidadeDTO> listLocalidade() throws PersistenceException {
        final List listRetorno = new ArrayList<>();
        List list = new ArrayList<>();
        final String sql = "select idlocalidade From " + this.getTableName() + " where dataFim is null ";

        list = this.execSQL(sql, null);

        listRetorno.add("idLocalidade");

        if (list != null && !list.isEmpty()) {

            final Collection<LocalidadeDTO> listLocalidade = this.listConvertion(LocalidadeDTO.class, list, listRetorno);
            return listLocalidade;

        }
        return null;
    }

}
