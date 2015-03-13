package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AprovacaoSolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class AprovacaoSolicitacaoServicoDao extends CrudDaoDefaultImpl {

    public AprovacaoSolicitacaoServicoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idAprovacaoSolicitacaoServico", "idAprovacaoSolicitacaoServico", true, true, false, false));
        listFields.add(new Field("idSolicitacaoServico", "idSolicitacaoServico", false, false, false, false));
        listFields.add(new Field("idResponsavel", "idResponsavel", false, false, false, false));
        listFields.add(new Field("idTarefa", "idTarefa", false, false, false, false));
        listFields.add(new Field("idJustificativa", "idJustificativa", false, false, false, false));
        listFields.add(new Field("dataHora", "dataHora", false, false, false, false));
        listFields.add(new Field("complementoJustificativa", "complementoJustificativa", false, false, false, false));
        listFields.add(new Field("observacoes", "observacoes", false, false, false, false));
        listFields.add(new Field("aprovacao", "aprovacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "AprovacaoSolicitacaoServico";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return AprovacaoSolicitacaoServicoDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByIdSolicitacaoServico(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", parm));
        ordenacao.add(new Order("idParecer"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByIdSolicitacaoServico(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", parm));
        super.deleteByCondition(condicao);
    }

    /**
     * Retorna AprovacaoSolicitacaoServicoNaoAprovada se existir para a Solicitação de Serviço informada.
     *
     * @param solicitacaoServicoDto
     * @return AprovacaoSolicitacaoServicoDTO
     * @author valdoilo.damasceno
     */
    public AprovacaoSolicitacaoServicoDTO findNaoAprovadaBySolicitacaoServico(final SolicitacaoServicoDTO solicitacaoServicoDto) {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idSolicitacaoServico", "=", solicitacaoServicoDto.getIdSolicitacaoServico()));
        condicao.add(new Condition("aprovacao", "=", "N"));

        ordenacao.add(new Order("idAprovacaoSolicitacaoServico", "DESC"));

        List listAprovacaoSolicitacaoServicoNaoAprovada = null;

        try {
            listAprovacaoSolicitacaoServicoNaoAprovada = (List) super.findByCondition(condicao, ordenacao);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        if (listAprovacaoSolicitacaoServicoNaoAprovada != null && !listAprovacaoSolicitacaoServicoNaoAprovada.isEmpty()) {

            return (AprovacaoSolicitacaoServicoDTO) listAprovacaoSolicitacaoServicoNaoAprovada.get(0);

        } else {
            return null;
        }
    }

}
