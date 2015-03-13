package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ExecucaoSolicitacaoDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ExecucaoSolicitacaoDao extends CrudDaoDefaultImpl {

    private static final String TABLE_NAME = "execucaosolicitacao";

    public ExecucaoSolicitacaoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idExecucao", "idExecucao", true, true, false, false));
        listFields.add(new Field("idSolicitacaoServico", "idSolicitacaoServico", false, false, false, false));
        listFields.add(new Field("idFase", "idFase", false, false, false, false));
        listFields.add(new Field("idFluxo", "idFluxo", false, false, false, false));
        listFields.add(new Field("prazoHH", "prazoHH", false, false, false, false));
        listFields.add(new Field("prazoMM", "prazoMM", false, false, false, false));
        listFields.add(new Field("idInstanciaFluxo", "idInstanciaFluxo", false, false, false, false));
        listFields.add(new Field("seqReabertura", "seqReabertura", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ExecucaoSolicitacaoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public ExecucaoSolicitacaoDTO findByIdInstanciaFluxo(final Integer idInstanciaFluxo) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idInstanciaFluxo", "=", idInstanciaFluxo));

        final Collection col = super.findByCondition(condicao, null);
        if (col == null || col.size() == 0) {
            return null;
        }
        return (ExecucaoSolicitacaoDTO) ((List) col).get(0);
    }

    public Collection<ExecucaoSolicitacaoDTO> listByIdSolicitacao(final Integer idSolicitacaoServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        ordenacao.add(new Order("idExecucao", Order.DESC));
        return super.findByCondition(condicao, ordenacao);
    }

    public ExecucaoSolicitacaoDTO findBySolicitacaoServico(final SolicitacaoServicoDTO solicitacaoServicoDto) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", solicitacaoServicoDto.getIdSolicitacaoServico()));
        if (solicitacaoServicoDto.getSeqReabertura() != null && solicitacaoServicoDto.getSeqReabertura().intValue() > 0) {
            condicao.add(new Condition("seqReabertura", "=", solicitacaoServicoDto.getSeqReabertura()));
        }
        ordenacao.add(new Order("idExecucao", Order.DESC));
        final List<ExecucaoSolicitacaoDTO> result = (List<ExecucaoSolicitacaoDTO>) super.findByCondition(condicao, ordenacao);
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

}
