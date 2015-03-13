package br.com.centralit.citcorpore.metainfo.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.metainfo.bean.GrupoVisaoCamposNegocioInfoSQLDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class GrupoVisaoCamposNegocioInfoSQLDao extends CrudDaoDefaultImpl {

    public GrupoVisaoCamposNegocioInfoSQLDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final List<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idGrupoVisaoCamposNegocioInfoSQL", "idGrupoVisaoCamposNegocioInfoSQL", true, true, false, false));
        listFields.add(new Field("idGrupoVisao", "idGrupoVisao", false, false, false, false));
        listFields.add(new Field("idCamposObjetoNegocio", "idCamposObjetoNegocio", false, false, false, false));
        listFields.add(new Field("campo", "campo", false, false, false, false));
        listFields.add(new Field("tipoLigacao", "tipoLigacao", false, false, false, false));
        listFields.add(new Field("filtro", "filtro", false, false, false, false));
        listFields.add(new Field("descricao", "descricao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "GrupoVisaoCamposNegocioInfoSQL";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return GrupoVisaoCamposNegocioInfoSQLDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdGrupoVisao(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idGrupoVisao", "=", parm));
        ordenacao.add(new Order("tipoLigacao"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdGrupoVisao(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idGrupoVisao", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdCamposObjetoNegocio(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCamposObjetoNegocio", "=", parm));
        ordenacao.add(new Order("tipoLigacao"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdCamposObjetoNegocio(final Integer parm) throws Exception {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idCamposObjetoNegocio", "=", parm));
        super.deleteByCondition(condicao);
    }

}
