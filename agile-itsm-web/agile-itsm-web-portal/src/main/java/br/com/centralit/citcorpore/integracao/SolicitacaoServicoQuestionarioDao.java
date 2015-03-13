package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ContratoQuestionariosDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoQuestionarioDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class SolicitacaoServicoQuestionarioDao extends CrudDaoDefaultImpl {

    public SolicitacaoServicoQuestionarioDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);

    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final List<Field> lista = new ArrayList<>();

        lista.add(new Field("idSolicitacaoQuestionario", "idSolicitacaoQuestionario", true, false, false, false));
        lista.add(new Field("idQuestionario", "idQuestionario", false, false, false, false));
        lista.add(new Field("idSolicitacaoServico", "idSolicitacaoServico", false, false, false, false));
        lista.add(new Field("dataQuestionario", "dataQuestionario", false, false, false, false));
        lista.add(new Field("idResponsavel", "idResponsavel", false, false, false, false));
        lista.add(new Field("idTarefa", "idTarefa", false, false, false, false));
        lista.add(new Field("aba", "aba", false, false, false, false));
        lista.add(new Field("situacao", "situacao", false, false, false, false));
        lista.add(new Field("dataHoraGrav", "dataHoraGrav", false, false, false, false));
        lista.add(new Field("conteudoImpresso", "conteudoImpresso", false, false, false, false));

        return lista;
    }

    @Override
    public String getTableName() {
        return "solicitacaoservicoquestionario";
    }

    public static String getTableNameAssDigital() {
        return "solicitacaoservicoquestionario";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return SolicitacaoServicoQuestionarioDTO.class;
    }

    public Collection listByIdSolicitacaoServico(final Integer idSolicitacaoServico) throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("dataQuestionario"));
        list.add(new Order("aba"));
        final SolicitacaoServicoQuestionarioDTO obj = new SolicitacaoServicoQuestionarioDTO();
        obj.setIdSolicitacaoServico(idSolicitacaoServico);
        return super.find(obj, list);
    }

    public SolicitacaoServicoQuestionarioDTO findByIdSolicitacaoServico(final Integer idSolicitacaoServico) throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("dataHoraGrav", Order.DESC));
        list.add(new Order("idSolicitacaoQuestionario", Order.DESC));
        final SolicitacaoServicoQuestionarioDTO obj = new SolicitacaoServicoQuestionarioDTO();
        obj.setIdSolicitacaoServico(idSolicitacaoServico);
        final List<SolicitacaoServicoQuestionarioDTO> result = (List<SolicitacaoServicoQuestionarioDTO>) super.find(obj, list);
        if (result != null && result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void update(final BaseEntity obj) throws PersistenceException {
        super.updateNotNull(obj);
    }

    public void updateSituacaoComplemento(final Integer idPessQuest, final String situacaoComplemento) throws PersistenceException {
        final ContratoQuestionariosDTO obj = new ContratoQuestionariosDTO();
        obj.setIdContratoQuestionario(idPessQuest);
        obj.setSituacaoComplemento(situacaoComplemento);
        super.updateNotNull(obj);
    }

    public void updateConteudoImpresso(final Integer idPessQuest, final String conteudoImpresso) throws PersistenceException {
        final ContratoQuestionariosDTO obj = new ContratoQuestionariosDTO();
        obj.setIdContratoQuestionario(idPessQuest);
        obj.setConteudoImpresso(conteudoImpresso);
        super.updateNotNull(obj);
    }

}
