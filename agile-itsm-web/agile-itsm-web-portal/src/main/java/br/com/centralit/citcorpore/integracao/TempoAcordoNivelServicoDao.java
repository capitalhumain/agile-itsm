package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.TempoAcordoNivelServicoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class TempoAcordoNivelServicoDao extends CrudDaoDefaultImpl {

    public TempoAcordoNivelServicoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idAcordoNivelServico", "idAcordoNivelServico", true, false, false, false));
        listFields.add(new Field("idPrioridade", "idPrioridade", true, false, false, false));
        listFields.add(new Field("idFase", "idFase", true, false, false, false));
        listFields.add(new Field("tempoHH", "tempoHH", false, false, false, false));
        listFields.add(new Field("tempoMM", "tempoMM", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "TempoAcordoNivelServico";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return TempoAcordoNivelServicoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public void deleteByIdAcordo(final Integer idAcordoNivelServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServico", "=", idAcordoNivelServico));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdAcordo(final Integer idAcordoNivelServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServico", "=", idAcordoNivelServico));
        ordenacao.add(new Order("idFase"));
        ordenacao.add(new Order("idPrioridade"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdAcordoAndIdPrioridade(final Integer idAcordoNivelServico, final Integer idPrioridade) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServico", "=", idAcordoNivelServico));
        condicao.add(new Condition("idPrioridade", "=", idPrioridade));
        ordenacao.add(new Order("idFase"));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdAcordoAndFaseAndIdPrioridade(final Integer idAcordoNivelServico, final Integer idFase, final Integer idPrioridade)
            throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idAcordoNivelServico", "=", idAcordoNivelServico));
        condicao.add(new Condition("idFase", "=", idFase));
        condicao.add(new Condition("idPrioridade", "=", idPrioridade));
        ordenacao.add(new Order("idPrioridade"));
        return super.findByCondition(condicao, ordenacao);
    }

}
