package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.RequisicaoViagemDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

@SuppressWarnings({"rawtypes", "unchecked"})
public class RequisicaoViagemDAO extends CrudDaoDefaultImpl {

    public RequisicaoViagemDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idsolicitacaoservico", "idSolicitacaoServico", true, false, false, false));
        listFields.add(new Field("idcidadeorigem", "idCidadeOrigem", false, false, false, false));
        listFields.add(new Field("idcidadedestino", "idCidadeDestino", false, false, false, false));
        listFields.add(new Field("idprojeto", "idProjeto", false, false, false, false));
        listFields.add(new Field("idcentroresultado", "idCentroCusto", false, false, false, false));
        listFields.add(new Field("idmotivoviagem", "idMotivoViagem", false, false, false, false));
        listFields.add(new Field("idaprovacao", "idAprovacao", false, false, false, false));
        listFields.add(new Field("descricaomotivo", "descricaoMotivo", false, false, false, false));
        listFields.add(new Field("datainicio", "dataInicioViagem", false, false, false, false));
        listFields.add(new Field("datafim", "dataFimViagem", false, false, false, false));
        listFields.add(new Field("qtdedias", "qtdeDias", false, false, false, false));
        listFields.add(new Field("estado", "estado", false, false, false, false));
        listFields.add(new Field("tarefainiciada", "tarefaIniciada", false, false, false, false));
        listFields.add(new Field("remarcacao", "remarcacao", false, false, false, false));
        listFields.add(new Field("iditemtrabalho", "idItemTrabalho", false, false, false, false));
        listFields.add(new Field("cancelarrequisicao", "cancelarRequisicao", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return getOwner() + "requisicaoviagem";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return RequisicaoViagemDTO.class;
    }

    @Override
    public void updateNotNull(final BaseEntity obj) throws PersistenceException {
        super.updateNotNull(obj);
    }

    /**
     * Busca uma coleção de requisicao de viagem pelo idCentroCusto
     *
     * @param idCentroCusto
     * @return
     * @throws Exception
     */
    public Collection findByIdCentroCusto(final Integer parm) throws PersistenceException {
        final List condicao = new ArrayList();
        final List ordenacao = new ArrayList();
        condicao.add(new Condition("idCentroCusto", "=", parm));
        ordenacao.add(new Order("idSolicitacaoServico"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * Retorna uma requisicao de viagem pelo idsolicitacaoservico
     *
     * @param idSolicitacaoServico
     * @return
     * @throws Exception
     */
    public RequisicaoViagemDTO findByIdSolicitacao(final Integer idSolicitacaoServico) throws PersistenceException {

        final List condicao = new ArrayList();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));

        List result = new ArrayList<RequisicaoViagemDTO>();
        result = (List) super.findByCondition(condicao, null);

        if (result != null && !result.isEmpty()) {
            return (RequisicaoViagemDTO) result.get(0);
        } else {
            return null;
        }

    }

    /**
     * Retorna uma lista de requisicao viagem conforme idsolicitacaoservico e template passados
     *
     * @param idSolicitacaoServico
     * @param template
     * @return
     * @throws Exception
     */
    public List<RequisicaoViagemDTO> retornaRequisicaoByTemplateAndIdsolicitacao(final Integer idSolicitacaoServico, final String template)
            throws PersistenceException {
        final List parametro = new ArrayList();
        final List listRetorno = new ArrayList();
        List lista = new ArrayList();
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT req.idsolicitacaoservico, ");
        sql.append("                req.idprojeto, ");
        sql.append("                req.idcentroresultado, ");
        sql.append("                req.idcidadeorigem, ");
        sql.append("                req.idcidadedestino, ");
        sql.append("                req.idmotivoviagem, ");
        sql.append("                req.idaprovacao, ");
        sql.append("                req.descricaomotivo, ");
        sql.append("                req.datainicio, ");
        sql.append("                req.datafim, ");
        sql.append("                req.qtdedias, ");
        sql.append("                req.estado, ");
        sql.append("                req.tarefainiciada, ");
        sql.append("                req.remarcacao ");
        sql.append("FROM   bpm_itemtrabalhofluxo i ");
        sql.append("       INNER JOIN bpm_instanciafluxo ins ");
        sql.append("               ON i.idinstancia = ins.idinstancia ");
        sql.append("       INNER JOIN bpm_elementofluxo el ");
        sql.append("               ON el.idelemento = i.idelemento ");
        sql.append("       INNER JOIN execucaosolicitacao ex ");
        sql.append("               ON ex.idinstanciafluxo = i.idinstancia ");
        sql.append("       INNER JOIN requisicaoviagem req ");
        sql.append("               ON ex.idsolicitacaoservico = req.idsolicitacaoservico ");
        sql.append("WHERE  el.template = ? ");
        sql.append("       AND ex.idsolicitacaoservico = ? ");
        sql.append("       AND ( i.situacao = 'Disponivel' ");
        sql.append("              OR i.situacao = 'EmAndamento' ) ");
        sql.append("       AND ins.situacao = 'Aberta'");

        parametro.add(idSolicitacaoServico);
        parametro.add(template);

        listRetorno.add("idSolicitacaoServico");
        listRetorno.add("idProjeto");
        listRetorno.add("idCentroCusto");
        listRetorno.add("idCidadeOrigem");
        listRetorno.add("idCidadeDestino");
        listRetorno.add("idMotivoViagem");
        listRetorno.add("idAprovacao");
        listRetorno.add("descricaoMotivo");
        listRetorno.add("dataInicioViagem");
        listRetorno.add("dataFimViagem");
        listRetorno.add("qtdeDias");
        listRetorno.add("estado");
        listRetorno.add("tarefaIniciada");
        listRetorno.add("remarcacao");

        lista = execSQL(sql.toString(), parametro.toArray());
        return listConvertion(getBean(), lista, listRetorno);
    }

}
