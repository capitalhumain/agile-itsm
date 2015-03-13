package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ProcessoNivelAutoridadeDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ProcessoNivelAutoridadeDao extends CrudDaoDefaultImpl {

    public ProcessoNivelAutoridadeDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idProcessoNegocio", "idProcessoNegocio", true, false, false, false));
        listFields.add(new Field("idNivelAutoridade", "idNivelAutoridade", true, false, false, false));
        listFields.add(new Field("permiteAprovacaoPropria", "permiteAprovacaoPropria", false, false, false, false));
        listFields.add(new Field("permiteSolicitacao", "permiteSolicitacao", false, false, false, false));
        listFields.add(new Field("antecedenciaMinimaAprovacao", "antecedenciaMinimaAprovacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "ProcessoNivelAutoridade";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ProcessoNivelAutoridadeDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdProcessoNegocio(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idProcessoNegocio", "=", parm));
        ordenacao.add(new Order("idNivelAutoridade"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdProcessoNegocio(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idProcessoNegocio", "=", parm));
        super.deleteByCondition(condicao);
    }

}
