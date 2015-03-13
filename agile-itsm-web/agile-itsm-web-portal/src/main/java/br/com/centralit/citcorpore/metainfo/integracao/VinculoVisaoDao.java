package br.com.centralit.citcorpore.metainfo.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.metainfo.bean.VinculoVisaoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class VinculoVisaoDao extends CrudDaoDefaultImpl {

    public VinculoVisaoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final List<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idVisaoRelacionada", "idVisaoRelacionada", true, false, false, false));
        listFields.add(new Field("seq", "seq", true, false, false, false));
        listFields.add(new Field("tipoVinculo", "tipoVinculo", false, false, false, false));
        listFields.add(new Field("idGrupoVisaoPai", "idGrupoVisaoPai", false, false, false, false));
        listFields.add(new Field("idCamposObjetoNegocioPai", "idCamposObjetoNegocioPai", false, false, false, false));
        listFields.add(new Field("idGrupoVisaoFilho", "idGrupoVisaoFilho", false, false, false, false));
        listFields.add(new Field("idCamposObjetoNegocioFilho", "idCamposObjetoNegocioFilho", false, false, false, false));
        listFields.add(new Field("idCamposObjetoNegocioPaiNN", "idCamposObjetoNegocioPaiNN", false, false, false, false));
        listFields.add(new Field("idCamposObjetoNegocioFilhoNN", "idCamposObjetoNegocioFilhoNN", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "VinculoVisao";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return VinculoVisaoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdVisaoRelacionada(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idVisaoRelacionada", "=", parm));
        ordenacao.add(new Order("seq"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdGrupoVisaoPai(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idGrupoVisaoPai", "=", parm));
        ordenacao.add(new Order("seq"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdGrupoVisaoPai(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idGrupoVisaoPai", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdCamposObjetoNegocioPai(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCamposObjetoNegocioPai", "=", parm));
        ordenacao.add(new Order("seq"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdCamposObjetoNegocioPai(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idCamposObjetoNegocioPai", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdGrupoVisaoFilho(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idGrupoVisaoFilho", "=", parm));
        ordenacao.add(new Order("seq"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdGrupoVisaoFilho(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idGrupoVisaoFilho", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdCamposObjetoNegocioFilho(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCamposObjetoNegocioFilho", "=", parm));
        ordenacao.add(new Order("seq"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdCamposObjetoNegocioFilho(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idCamposObjetoNegocioFilho", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdCamposObjetoNegocioPaiNN(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCamposObjetoNegocioPaiNN", "=", parm));
        ordenacao.add(new Order("seq"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdCamposObjetoNegocioPaiNN(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idCamposObjetoNegocioPaiNN", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdCamposObjetoNegocioFilhoNN(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCamposObjetoNegocioFilhoNN", "=", parm));
        ordenacao.add(new Order("seq"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdCamposObjetoNegocioFilhoNN(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idCamposObjetoNegocioFilhoNN", "=", parm));
        super.deleteByCondition(condicao);
    }

    public void deleteByIdVisaoPai(final Integer idVisaoParm) throws Exception {
        final VisaoRelacionadaDao visaoRelacionadaDao = new VisaoRelacionadaDao();
        String sql = "DELETE FROM " + this.getTableName() + " WHERE idVisaoRelacionada IN (";
        sql += "SELECT idVisaoRelacionada FROM " + visaoRelacionadaDao.getTableName() + " WHERE idVisaoPai = ?) and idVisaoRelacionada > 0 ";
        final List parametros = new ArrayList<>();
        parametros.add(idVisaoParm);
        this.execUpdate(sql, parametros.toArray());
    }

}
