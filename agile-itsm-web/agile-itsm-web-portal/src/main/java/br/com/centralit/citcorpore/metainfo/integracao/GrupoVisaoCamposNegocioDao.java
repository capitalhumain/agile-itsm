package br.com.centralit.citcorpore.metainfo.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.metainfo.bean.GrupoVisaoCamposNegocioDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class GrupoVisaoCamposNegocioDao extends CrudDaoDefaultImpl {

    public GrupoVisaoCamposNegocioDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final List<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idGrupoVisao", "idGrupoVisao", true, false, false, false));
        listFields.add(new Field("idCamposObjetoNegocio", "idCamposObjetoNegocio", true, false, false, false));
        listFields.add(new Field("descricaoNegocio", "descricaoNegocio", false, false, false, false));
        listFields.add(new Field("tipoNegocio", "tipoNegocio", false, false, false, false));
        listFields.add(new Field("ordem", "ordem", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        listFields.add(new Field("obrigatorio", "obrigatorio", false, false, false, false));
        listFields.add(new Field("tamanho", "tamanho", false, false, false, false));
        listFields.add(new Field("decimais", "decimais", false, false, false, false));
        listFields.add(new Field("tipoLigacao", "tipoLigacao", false, false, false, false));
        listFields.add(new Field("textosql", "sql", false, false, false, false));
        listFields.add(new Field("tamanhoParaPesq", "tamanhoParaPesq", false, false, false, false));
        listFields.add(new Field("formula", "formula", false, false, false, false));
        listFields.add(new Field("visivel", "visivel", false, false, false, false));
        listFields.add(new Field("htmlCode", "htmlCode", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "GrupoVisaoCamposNegocio";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return GrupoVisaoCamposNegocioDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public void deleteByIdVisao(final Integer idVisaoParm) throws Exception {
        final GrupoVisaoDao grupoVisaoDao = new GrupoVisaoDao();
        String sql = "DELETE FROM " + this.getTableName() + " WHERE idGrupoVisao IN (";
        sql += "SELECT idGrupoVisao FROM " + grupoVisaoDao.getTableName() + " WHERE idVisao = ?) and idGrupoVisao > 0 ";
        final List parametros = new ArrayList<>();
        parametros.add(idVisaoParm);
        this.execUpdate(sql, parametros.toArray());
    }

    public Collection findByIdGrupoVisao(final Integer idGrupoVisao) throws Exception {
        final List lstCond = new ArrayList<>();
        final List lstOrder = new ArrayList<>();

        lstCond.add(new Condition("idGrupoVisao", "=", idGrupoVisao));
        lstOrder.add(new Order("ordem"));

        return super.findByCondition(lstCond, lstOrder);
    }

    public Collection findByIdGrupoVisaoAtivos(final Integer idGrupoVisao) throws Exception {
        final List lstCond = new ArrayList<>();
        final List lstOrder = new ArrayList<>();

        lstCond.add(new Condition("idGrupoVisao", "=", idGrupoVisao));
        lstCond.add(new Condition("situacao", "=", "A"));
        lstOrder.add(new Order("ordem"));

        return super.findByCondition(lstCond, lstOrder);
    }

}
