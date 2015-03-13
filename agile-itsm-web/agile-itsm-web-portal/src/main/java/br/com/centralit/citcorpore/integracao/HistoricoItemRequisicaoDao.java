package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.HistoricoItemRequisicaoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class HistoricoItemRequisicaoDao extends CrudDaoDefaultImpl {

    public HistoricoItemRequisicaoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idHistorico", "idHistorico", true, true, false, false));
        listFields.add(new Field("idItemRequisicao", "idItemRequisicao", false, false, false, false));
        listFields.add(new Field("acao", "acao", false, false, false, false));
        listFields.add(new Field("idResponsavel", "idResponsavel", false, false, false, false));
        listFields.add(new Field("dataHora", "dataHora", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        listFields.add(new Field("complemento", "complemento", false, false, false, false));
        listFields.add(new Field("atributosAnteriores", "atributosAnteriores", false, false, false, false));
        listFields.add(new Field("atributosAtuais", "atributosAtuais", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "historicoitemrequisicao";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return HistoricoItemRequisicaoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdItemRequisicao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idItemRequisicao", "=", parm));
        ordenacao.add(new Order("dataHora", Order.DESC));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdItemRequisicao(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idItemRequisicao", "=", parm));
        super.deleteByCondition(condicao);
    }

}
