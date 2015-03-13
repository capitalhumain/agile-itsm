package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.RevisarSlaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class RevisarSlaDao extends CrudDaoDefaultImpl {

    public RevisarSlaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idRevisarSLA", "idRevisarSLA", true, true, false, false));
        listFields.add(new Field("idAcordoNivelServico", "idAcordoNivelServico", true, false, false, false));
        listFields.add(new Field("dataRevisao", "dataRevisao", false, false, false, false));
        listFields.add(new Field("detalheRevisao", "detalheRevisao", false, false, false, false));
        listFields.add(new Field("observacao", "observacao", false, false, false, false));
        listFields.add(new Field("deleted", "deleted", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "RevisarSla";
    }

    @Override
    public Collection list() throws PersistenceException {
        return super.list("idRevisarSLA");
    }

    @Override
    public Class getBean() {
        return RevisarSlaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdAcordoNivelServico(final Integer idAcordoNivelServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServico", "=", idAcordoNivelServico));
        ordenacao.add(new Order("idRevisarSLA"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdAcordoNivelServico(final Integer idAcordoNivelServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServico", "=", idAcordoNivelServico));
        super.deleteByCondition(condicao);
    }

}
