package br.com.centralit.citcorpore.metainfo.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.metainfo.bean.CamposObjetoNegocioDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class CamposObjetoNegocioDao extends CrudDaoDefaultImpl {

    public CamposObjetoNegocioDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final List<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idCamposObjetoNegocio", "idCamposObjetoNegocio", true, true, false, false));
        listFields.add(new Field("idObjetoNegocio", "idObjetoNegocio", false, false, false, false));
        listFields.add(new Field("nome", "nome", false, false, false, false));
        listFields.add(new Field("nomeDB", "nomeDB", false, false, false, false));
        listFields.add(new Field("pk", "pk", false, false, false, false));
        listFields.add(new Field("sequence", "sequence", false, false, false, false));
        listFields.add(new Field("unico", "unico", false, false, false, false));
        listFields.add(new Field("tipoDB", "tipoDB", false, false, false, false));
        listFields.add(new Field("precisionDB", "precisionDB", false, false, false, false));
        listFields.add(new Field("obrigatorio", "obrigatorio", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "CamposObjetoNegocio";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return CamposObjetoNegocioDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdObjetoNegocio(final Integer idObjetoNegocioParm) throws Exception {
        final List colCondicao = new ArrayList<>();
        final List colOrder = new ArrayList<>();

        colCondicao.add(new Condition("idObjetoNegocio", "=", idObjetoNegocioParm));
        colCondicao.add(new Condition("situacao", "=", "A"));

        colOrder.add(new Order("idCamposObjetoNegocio"));
        return super.findByCondition(colCondicao, colOrder);
    }

    public Collection findByIdObjetoNegocioAndNomeDB(final Integer idObjetoNegocioParm, final String nomeDBParm) throws Exception {
        final List colCondicao = new ArrayList<>();
        final List colOrder = new ArrayList<>();

        final String nomeDBParmAux = nomeDBParm;
        if (nomeDBParmAux == null) {
            return null;
        }

        colCondicao.add(new Condition("idObjetoNegocio", "=", idObjetoNegocioParm));
        colCondicao.add(new Condition("nomeDB", "=", nomeDBParmAux));

        colOrder.add(new Order("idCamposObjetoNegocio"));
        return super.findByCondition(colCondicao, colOrder);
    }

    public void deleteFromNOTRelNomeDB(final String cond, final Integer idObjNegocio) throws PersistenceException {
        final String sqlDelete = "DELETE FROM " + this.getTableName() + " WHERE idObjetoNegocio = ? AND (nomeDB NOT IN (" + cond + "))";
        super.execUpdate(sqlDelete, new Object[] {idObjNegocio});
    }

    public void updateComplexidade(final Integer idCampoObjNegocio1, final Integer idCampoObjNegocio2) throws PersistenceException {
        final String sqlUpdate = "UPDATE " + this.getTableName() + " SET SEQUENCE = 'N' WHERE idCamposObjetoNegocio IN (? , ?)";
        super.execUpdate(sqlUpdate, new Object[] {idCampoObjNegocio1, idCampoObjNegocio2});
    }

    public void updateFluxoServico(final Integer idCampoObjNegocio1, final Integer idCampoObjNegocio2, final Integer idCampoObjNegocio3)
            throws PersistenceException {
        final String sqlUpdate = "UPDATE " + this.getTableName() + " SET SEQUENCE = 'N' WHERE idCamposObjetoNegocio IN (?, ?, ?)";
        super.execUpdate(sqlUpdate, new Object[] {idCampoObjNegocio1, idCampoObjNegocio2, idCampoObjNegocio3});
    }

}
