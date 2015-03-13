package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ReaberturaMudancaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ReaberturaMudancaDao extends CrudDaoDefaultImpl {

    public ReaberturaMudancaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idrequisicaomudanca", "idRequisicaoMudanca", true, false, false, false));
        listFields.add(new Field("seqreabertura", "seqReabertura", true, false, false, false));
        listFields.add(new Field("idresponsavel", "idResponsavel", false, false, false, false));
        listFields.add(new Field("datahora", "dataHora", false, false, false, false));
        listFields.add(new Field("observacoes", "observacoes", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "ReaberturaMudanca";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ReaberturaMudancaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdRequisicaoMudanca(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idRequisicaoMudanca", "=", parm));
        ordenacao.add(new Order("seqReabertura"));
        return super.findByCondition(condicao, ordenacao);
    }

}
