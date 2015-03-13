package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.LimiteAprovacaoAutoridadeDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class LimiteAprovacaoAutoridadeDao extends CrudDaoDefaultImpl {

    public LimiteAprovacaoAutoridadeDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idLimiteAprovacao", "idLimiteAprovacao", true, false, false, false));
        listFields.add(new Field("idNivelAutoridade", "idNivelAutoridade", true, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "LimiteAprovacaoAutoridade";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return LimiteAprovacaoAutoridadeDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdLimiteAprovacao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idLimiteAprovacao", "=", parm));
        ordenacao.add(new Order("idNivelAutoridade"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdNivelAutoridade(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idNivelAutoridade", "=", parm));
        ordenacao.add(new Order("idLimiteAprovacao"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdLimiteAprovacao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idLimiteAprovacao", "=", parm));
        super.deleteByCondition(condicao);
    }

}
