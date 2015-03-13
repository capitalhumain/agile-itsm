package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ResponsavelCentroResultadoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ResponsavelCentroResultadoDao extends CrudDaoDefaultImpl {

    public ResponsavelCentroResultadoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idResponsavel", "idResponsavel", true, false, false, false));
        listFields.add(new Field("idCentroResultado", "idCentroResultado", true, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "ResponsavelCentroResultado";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ResponsavelCentroResultadoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdCentroResultado(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCentroResultado", "=", parm));
        ordenacao.add(new Order("idResponsavel"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdCentroResultado(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idCentroResultado", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdResponsavel(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idResponsavel", "=", parm));
        ordenacao.add(new Order("idCentroResultado"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdCentroResultadoAndIdResponsavel(final Integer idCentroResultado, final Integer idResponsavel) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idCentroResultado", "=", idCentroResultado));
        condicao.add(new Condition("idResponsavel", "=", idResponsavel));
        ordenacao.add(new Order("idResponsavel"));
        return super.findByCondition(condicao, ordenacao);
    }

}
