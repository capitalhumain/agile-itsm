package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ExecucaoLiberacaoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ExecucaoLiberacaoDao extends CrudDaoDefaultImpl {

    public ExecucaoLiberacaoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idExecucao", "idExecucao", true, true, false, false));
        listFields.add(new Field("idLiberacao", "idRequisicaoLiberacao", false, false, false, false));
        listFields.add(new Field("idFluxo", "idFluxo", false, false, false, false));
        listFields.add(new Field("idInstanciaFluxo", "idInstanciaFluxo", false, false, false, false));
        listFields.add(new Field("seqReabertura", "seqReabertura", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "ExecucaoLiberacao";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ExecucaoLiberacaoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public ExecucaoLiberacaoDTO findByIdInstanciaFluxo(final Integer idInstanciaFluxo) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idInstanciaFluxo", "=", idInstanciaFluxo));

        final Collection col = super.findByCondition(condicao, null);
        if (col == null || col.size() == 0) {
            return null;
        }
        return (ExecucaoLiberacaoDTO) ((List) col).get(0);
    }

    public Collection<ExecucaoLiberacaoDTO> listByIdRequisicaoLiberacao(final Integer idRequisicaoLiberacao) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idRequisicaoLiberacao", "=", idRequisicaoLiberacao));
        ordenacao.add(new Order("idExecucao", Order.DESC));
        return super.findByCondition(condicao, ordenacao);
    }

}
