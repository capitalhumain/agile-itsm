package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.LocalidadeUnidadeDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;

public class LocalidadeUnidadeDAO extends CrudDaoDefaultImpl {

    public LocalidadeUnidadeDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idlocalidadeunidade", "idLocalidadeUnidade", true, true, false, false));
        listFields.add(new Field("idlocalidade", "idLocalidade", false, false, false, false));
        listFields.add(new Field("idunidade", "idUnidade", false, false, false, false));
        listFields.add(new Field("datainicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("datafim", "dataFim", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "localidadeunidade";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return LocalidadeUnidadeDTO.class;

    }

    public Collection<LocalidadeUnidadeDTO> listaIdLocalidades(final Integer idUnidade) throws PersistenceException {
        final Object[] objs = new Object[] {idUnidade};
        final String sql = "SELECT  idlocalidade FROM " + this.getTableName() + " WHERE idunidade = ? AND datafim is null ";
        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idLocalidade");
        if (lista != null && !lista.isEmpty()) {
            return engine.listConvertion(this.getBean(), lista, listRetorno);
        } else {
            return null;
        }
    }

    public boolean verificarExistenciaDeLocalidadeEmUnidade(final Integer idLocalidade) throws PersistenceException {
        final Object[] objs = new Object[] {idLocalidade};
        final String sql = "SELECT distinct idlocalidade FROM " + this.getTableName() + " WHERE idLocalidade = ? AND datafim is null ";
        final List lista = this.execSQL(sql, objs);

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idLocalidade");
        if (lista != null && !lista.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void updateDataFim(final LocalidadeUnidadeDTO obj) throws PersistenceException {
        final List parametros = new ArrayList<>();
        parametros.add(UtilDatas.getDataAtual());
        parametros.add(obj.getIdLocalidade());

        final String sql = "UPDATE " + this.getTableName() + " SET DATAFIM = ? WHERE IDLOCALIDADE = ? ";

        this.execUpdate(sql, parametros.toArray());
    }

    public void deleteByIdUnidade(final Integer idUnidade) throws PersistenceException {
        final List lstCondicao = new ArrayList<>();
        lstCondicao.add(new Condition("idUnidade", "=", idUnidade));
        super.deleteByCondition(lstCondicao);
    }

}
