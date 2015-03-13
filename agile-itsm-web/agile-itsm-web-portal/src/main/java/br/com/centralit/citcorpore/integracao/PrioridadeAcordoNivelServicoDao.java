package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.PrioridadeAcordoNivelServicoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class PrioridadeAcordoNivelServicoDao extends CrudDaoDefaultImpl {

    public PrioridadeAcordoNivelServicoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idUnidade", "idUnidade", true, false, false, false));
        listFields.add(new Field("idAcordoNivelServico", "idAcordoNivelServico", true, false, false, false));
        listFields.add(new Field("idPrioridade", "idPrioridade", false, false, false, false));
        listFields.add(new Field("dataInicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("dataFim", "dataFim", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "PrioridadeAcordoNivelServico";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return PrioridadeAcordoNivelServicoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdUnidade(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idUnidade", "=", parm));
        ordenacao.add(new Order("idAcordoNivelServico"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdUnidade(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idUnidade", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdAcordoNivelServico(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServico", "=", parm));
        ordenacao.add(new Order("idUnidade"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdAcordoNivelServico(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServico", "=", parm));
        super.deleteByCondition(condicao);
    }

    public PrioridadeAcordoNivelServicoDTO findByIdAcordoNivelServicoAndIdUnidade(final Integer idAcordoNivelServico, final Integer idUnidade)
            throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServico", "=", idAcordoNivelServico));
        condicao.add(new Condition("idUnidade", "=", idUnidade));
        condicao.add(new Condition("dataFim", "is", null));

        final Collection col = super.findByCondition(condicao, null);
        if (col == null || col.size() == 0) {
            return null;
        }
        return (PrioridadeAcordoNivelServicoDTO) ((List) col).get(0);
    }

}
