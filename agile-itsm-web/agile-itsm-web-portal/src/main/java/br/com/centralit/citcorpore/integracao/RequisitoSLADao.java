package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.RequisitoSLADTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class RequisitoSLADao extends CrudDaoDefaultImpl {

    public RequisitoSLADao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idRequisitoSLA", "idRequisitoSLA", true, true, false, false));
        listFields.add(new Field("idEmpregado", "idEmpregado", false, false, false, false));
        listFields.add(new Field("assunto", "assunto", false, false, false, false));
        listFields.add(new Field("detalhamento", "detalhamento", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        listFields.add(new Field("requisitadoEm", "requisitadoEm", false, false, false, false));
        listFields.add(new Field("criadoEm", "criadoEm", false, false, false, false));
        listFields.add(new Field("modificadoEm", "modificadoEm", false, false, false, false));
        listFields.add(new Field("criadoPor", "criadoPor", false, false, false, false));
        listFields.add(new Field("modificadoPor", "modificadoPor", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "RequisitoSLA";
    }

    @Override
    public Collection list() throws PersistenceException {
        return super.list("assunto");
    }

    @Override
    public Class getBean() {
        return RequisitoSLADTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdEmpregado(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idEmpregado", "=", parm));
        ordenacao.add(new Order("assunto"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdEmpregado(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idEmpregado", "=", parm));
        super.deleteByCondition(condicao);
    }

    /**
     * Filtra unidade pelo id
     *
     * @param idRequisitoSla
     * @return Collection
     * @throws Exception
     */
    public Collection findById(final Integer idRequisitoSla) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();
        sql.append("SELECT idRequisitoSla,assunto FROM " + this.getTableName() + " where idRequisitoSla = ? and (deleted is null or deleted = 'n')");
        parametro.add(idRequisitoSla);
        final List list = this.execSQL(sql.toString(), parametro.toArray());
        final List listRetorno = new ArrayList<>();
        listRetorno.add("idRequisitoSla");
        listRetorno.add("assunto");
        final List result = engine.listConvertion(this.getBean(), list, listRetorno);
        return result;
    }

}
