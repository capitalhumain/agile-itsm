package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.SlaRequisitoSlaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class SlaRequisitoSLADao extends CrudDaoDefaultImpl {

    public SlaRequisitoSLADao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idRequisitoSLA", "idRequisitoSLA", true, false, false, false));
        listFields.add(new Field("idAcordoNivelServico", "idAcordoNivelServico", true, false, false, false));
        listFields.add(new Field("dataVinculacao", "dataVinculacao", false, false, false, false));
        listFields.add(new Field("dataUltModificacao", "dataUltModificacao", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "slarequisitosla";
    }

    @Override
    public Collection list() throws PersistenceException {
        return super.list("idRequisitoSLA");
    }

    @Override
    public Class getBean() {
        return SlaRequisitoSlaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdAcordoNivelServico(final Integer idAcordoNivelServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServico", "=", idAcordoNivelServico));
        ordenacao.add(new Order("idRequisitoSLA"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdAcordoNivelServico(final Integer idAcordoNivelServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServico", "=", idAcordoNivelServico));
        super.deleteByCondition(condicao);
    }

    public boolean existeAcordoByRequisito(final Integer idRequisito) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();
        sql.append("SELECT * FROM " + this.getTableName());
        sql.append(" WHERE idrequisitosla = ? ");
        parametro.add(idRequisito);
        final List list = this.execSQL(sql.toString(), parametro.toArray());
        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}
