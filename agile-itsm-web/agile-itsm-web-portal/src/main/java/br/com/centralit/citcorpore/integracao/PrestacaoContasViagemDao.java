package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.PrestacaoContasViagemDTO;
import br.com.centralit.citcorpore.bean.RequisicaoViagemDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * @author ronnie.lopes
 *
 */
public class PrestacaoContasViagemDao extends CrudDaoDefaultImpl {

    public PrestacaoContasViagemDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idprestacaocontasviagem", "idPrestacaoContasViagem", true, true, false, false));
        listFields.add(new Field("idresponsavel", "idResponsavel", false, false, false, false));
        listFields.add(new Field("idaprovacao", "idAprovacao", false, false, false, false));
        listFields.add(new Field("idsolicitacaoservico", "idSolicitacaoServico", false, false, false, false));
        listFields.add(new Field("idempregado", "idEmpregado", false, false, false, false));
        listFields.add(new Field("datahora", "dataHora", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        listFields.add(new Field("iditemtrabalho", "idItemTrabalho", false, false, false, false));
        listFields.add(new Field("integrantefuncionario", "integranteFuncionario", false, false, false, false));

        // TODO Este campo esta em desuso, pode ser removido na proxima versão
        listFields.add(new Field("nomenaofuncionario", "nomeNaoFuncionario", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "prestacaocontasviagem";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List ordem = new ArrayList<>();
        ordem.add(new Order("idPrestacaoContasViagem"));
        return super.find(obj, ordem);
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("idPrestacaoContasViagem"));
        return super.list(list);
    }

    @Override
    public Class getBean() {
        return PrestacaoContasViagemDTO.class;
    }

    /**
     * Retorna um objeto PrestacaoContasViagemDTO por SolicitacaoServico e Empregado
     *
     * @param idSolicitacaoServico
     * @param idEmpregado
     * @return
     * @throws Exception
     */
    public PrestacaoContasViagemDTO findBySolicitacaoAndEmpregado(final Integer idSolicitacaoServico, final Integer idEmpregado) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition(Condition.AND, "idEmpregado", "=", idEmpregado));
        final List result = (List) super.findByCondition(condicao, null);
        if (result != null) {
            return (PrestacaoContasViagemDTO) result.get(0);
        }
        return null;
    }

    /**
     * TODO Este metodo esta em desuso, pode ser removido na proxima versão
     */
    public PrestacaoContasViagemDTO findBySolicitacaoAndNomeNaoFuncionario(final Integer idSolicitacaoServico, final String nomeNaoFunc)
            throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition(Condition.AND, "nomeNaoFuncionario", "=", nomeNaoFunc));
        final List result = (List) super.findByCondition(condicao, null);
        if (result != null) {
            return (PrestacaoContasViagemDTO) result.get(0);
        }
        return null;
    }

    /**
     * TODO Este metodo esta em desuso, pode ser removido na proxima versão
     */
    public List findBySolicitacao(final Integer idSolicitacaoServico, final Integer idEmpregado) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        return (List) super.findByCondition(condicao, null);
    }

    /**
     * Retorna uma lista de prestação de contas conforme idsolicitacaoservico passados
     *
     * @param idSolicitacaoServico
     * @return
     * @throws Exception
     */
    public List findBySolicitacao(final Integer idSolicitacaoServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition("idItemTrabalho", "is", null));
        return (List) super.findByCondition(condicao, null);
    }

    /**
     * TODO Este metodo esta em desuso, pode ser removido na proxima versão
     */
    public Collection findBySolicitacaoAndTafera(final Integer idSolicitacaoServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition("situacao", "=", PrestacaoContasViagemDTO.AGUARDANDO_CONFERENCIA));
        condicao.add(new Condition("idItemTrabalho", "IS", null));
        ordenacao.add(new Order("idPrestacaoContasViagem"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * TODO Este metodo esta em desuso, pode ser removido na proxima versão
     */
    public Collection findBySolicitacaoAndConferencia(final Integer idSolicitacaoServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition("situacao", "=", PrestacaoContasViagemDTO.AGUARDANDO_CONFERENCIA));
        condicao.add(new Condition("idItemTrabalho", "is", null));
        ordenacao.add(new Order("idPrestacaoContasViagem"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * Verifica se a requisição esta na etapa de prestação de contas
     *
     * @param requisicaoViagemDto
     * @return
     * @throws Exception
     */
    public boolean isEstadoPrestacaoContas(final RequisicaoViagemDTO requisicaoViagemDto) throws PersistenceException {
        List result = null;
        if (requisicaoViagemDto.getEstado().equalsIgnoreCase(RequisicaoViagemDTO.AGUARDANDO_PRESTACAOCONTAS)) {
            if (requisicaoViagemDto.getTarefaIniciada() != null && requisicaoViagemDto.getTarefaIniciada().equalsIgnoreCase("S")) {
                return false;
            }
            final List<Condition> condicao = new ArrayList<>();
            final List<Order> ordenacao = new ArrayList<>();
            condicao.add(new Condition("idSolicitacaoServico", "=", requisicaoViagemDto.getIdSolicitacaoServico()));
            condicao.add(new Condition("idItemTrabalho", "IS", null));
            ordenacao.add(new Order("idPrestacaoContasViagem"));

            result = (List) super.findByCondition(condicao, ordenacao);
            if (result == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * TODO Este metodo esta em desuso, pode ser removido na proxima versão
     */
    public boolean isEstadoAutorizacao(final RequisicaoViagemDTO requisicaoViagemDto) throws PersistenceException {
        List result = null;
        if (requisicaoViagemDto.getEstado().equalsIgnoreCase(RequisicaoViagemDTO.AGUARDANDO_APROVACAO)) {
            if (requisicaoViagemDto.getTarefaIniciada() != null && requisicaoViagemDto.getTarefaIniciada().equalsIgnoreCase("S")) {
                return false;
            }
            final List<Condition> condicao = new ArrayList<>();
            final List<Order> ordenacao = new ArrayList<>();
            condicao.add(new Condition("idSolicitacaoServico", "=", requisicaoViagemDto.getIdSolicitacaoServico()));
            condicao.add(new Condition("idItemTrabalho", "IS", null));
            ordenacao.add(new Order("idPrestacaoContasViagem"));

            result = (List) super.findByCondition(condicao, ordenacao);
            if (result == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * TODO Este metodo esta em desuso, pode ser removido na proxima versão
     */
    public boolean isViagemRemarcado(final RequisicaoViagemDTO requisicaoViagemDto) throws PersistenceException {
        List result = null;
        if (requisicaoViagemDto.getEstado().equalsIgnoreCase(RequisicaoViagemDTO.REMARCADO)) {
            final List<Condition> condicao = new ArrayList<>();
            final List<Order> ordenacao = new ArrayList<>();
            condicao.add(new Condition("idSolicitacaoServico", "=", requisicaoViagemDto.getIdSolicitacaoServico()));
            condicao.add(new Condition("idItemTrabalho", "IS", null));
            ordenacao.add(new Order("idPrestacaoContasViagem"));

            result = (List) super.findByCondition(condicao, ordenacao);
            if (result == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * TODO Este metodo esta em desuso, pode ser removido na proxima versão
     */
    public Collection findByCorrigirAndSolicitacao(final Integer idSolicitacaoServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition("situacao", "=", PrestacaoContasViagemDTO.EM_CORRECAO));
        condicao.add(new Condition("idItemTrabalho", "is", null));
        ordenacao.add(new Order("idPrestacaoContasViagem"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * TODO Este metodo esta em desuso, pode ser removido na proxima versão
     */
    public Collection findBySolicitacaoEmConferencia(final Integer idSolicitacaoServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition("situacao", "=", PrestacaoContasViagemDTO.EM_CONFERENCIA));
        condicao.add(new Condition("idItemTrabalho", "is not", null));
        ordenacao.add(new Order("idPrestacaoContasViagem"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * TODO Este metodo esta em desuso, pode ser removido na proxima versão
     */
    public PrestacaoContasViagemDTO findBySolicitacaoAndTarefa(final Integer idSolicitacaoServico, final Integer idTarefa) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        List result = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition("idItemTrabalho", "=", idTarefa));
        result = (List) this.findByCondition(condicao, ordenacao);
        if (result != null) {
            return (PrestacaoContasViagemDTO) result.get(0);
        } else {
            return null;
        }
    }

    /**
     * TODO Este metodo esta em desuso, pode ser removido na proxima versão
     */
    public PrestacaoContasViagemDTO findNaoAprovados(final Integer idSolicitacaoServico, final Integer idTarefa) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        List result = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition("idItemTrabalho", "is", null));
        condicao.add(new Condition("situacao", "=", PrestacaoContasViagemDTO.EM_CORRECAO));
        result = (List) this.findByCondition(condicao, ordenacao);
        if (result != null) {
            return (PrestacaoContasViagemDTO) result.get(0);
        }
        return null;
    }

    /**
     * Recupera a prestação de contas conforme idtarefa e idsolicitacaoservico passados
     *
     * @param idTarefa
     * @param idSolicitacaoServico
     * @return
     * @throws Exception
     */
    public PrestacaoContasViagemDTO findByTarefaAndSolicitacao(final Integer idTarefa, final Integer idSolicitacaoServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        List result = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition("idItemTrabalho", "=", idTarefa));
        ordenacao.add(new Order("idPrestacaoContasViagem"));
        result = (List) super.findByCondition(condicao, ordenacao);

        if (result != null) {
            return (PrestacaoContasViagemDTO) result.get(0);
        } else {
            return null;
        }
    }

    /**
     * TODO Este metodo esta em desuso, pode ser removido na proxima versão
     */
    public boolean verificaSeTodasPrestacaoAprovadas(final Integer idSolicitacao) throws PersistenceException {
        final IntegranteViagemDao integrantesDao = new IntegranteViagemDao();
        final Integer totalIntegrantes = integrantesDao.retornaQtdeIntegrantes(idSolicitacao);

        final Collection<PrestacaoContasViagemDTO> colPrestacao = this.findBySolicitacao(idSolicitacao, null);
        if (colPrestacao != null) {
            if (colPrestacao.size() != totalIntegrantes) {
                return false;
            }
            for (final PrestacaoContasViagemDTO prestacaoDto : colPrestacao) {
                if (!prestacaoDto.getSituacao().equalsIgnoreCase(PrestacaoContasViagemDTO.APROVADA)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Retorna uma coleção de prestação de contas que esta na etapa de conferencia
     *
     * @param idSolicitacaoServico
     * @return
     * @throws Exception
     */
    public Collection findBySolicitacaoAndTaferaConferencia(final Integer idSolicitacaoServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition("situacao", "=", PrestacaoContasViagemDTO.AGUARDANDO_CONFERENCIA));
        condicao.add(new Condition("idItemTrabalho", "IS", null));
        ordenacao.add(new Order("idPrestacaoContasViagem"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * TODO Este metodo esta em desuso, pode ser removido na proxima versão
     */
    public Collection findBySolicitacaoAndTaferaCorrecao(final Integer idSolicitacaoServico) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition("situacao", "=", PrestacaoContasViagemDTO.EM_CORRECAO));
        condicao.add(new Condition("idItemTrabalho", "IS", null));
        ordenacao.add(new Order("idPrestacaoContasViagem"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * TODO Este metodo esta em desuso, pode ser removido na proxima versão
     */
    public Collection findBySolicitacaoEmpregadoSeCorrecao(final Integer idSolicitacaoServico, final Integer idEmpregado) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition("situacao", "=", PrestacaoContasViagemDTO.EM_CORRECAO));
        condicao.add(new Condition("idEmpregado", "=", idEmpregado));
        ordenacao.add(new Order("idPrestacaoContasViagem"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * Retorna uma lista de prestação de contas conforme idtarefa passados
     *
     * @param idTarefa
     * @return
     * @throws Exception
     */
    public Collection findByTarefa(final Integer idTarefa) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idItemTrabalho", "=", idTarefa));
        ordenacao.add(new Order("idPrestacaoContasViagem"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * Retorna uma lista de prestação de contas conforme idsolicitacaoservico e situacao passados, mas que não tenha iditemtrabalho associado a ele
     *
     * @param idSolicitacaoServico
     * @param situacao
     * @return
     * @throws Exception
     */
    public Collection findBySituacaoAndNull(final Integer idSolicitacaoServico, final String situacao) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition("situacao", "=", situacao));
        condicao.add(new Condition("idItemTrabalho", "is", null));
        ordenacao.add(new Order("idPrestacaoContasViagem"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * TODO Este metodo esta em desuso, pode ser removido na proxima versão
     */
    public Collection findBySituacaoAndNotNull(final Integer idSolicitacaoServico, final String situacao) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition("situacao", "=", situacao));
        condicao.add(new Condition("idItemTrabalho", "is not", null));
        ordenacao.add(new Order("idPrestacaoContasViagem"));
        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * Retorna uma lista de prestação de contas conforme idsolicitacaoservico e situacao passados
     *
     * @param idSolicitacaoServico
     * @param situacao
     * @return
     * @throws Exception
     */
    public Collection findBySituacao(final Integer idSolicitacaoServico, final String situacao) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition("situacao", "=", situacao));
        ordenacao.add(new Order("idPrestacaoContasViagem"));
        return super.findByCondition(condicao, ordenacao);
    }

}
