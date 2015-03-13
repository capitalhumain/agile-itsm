package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.RoteiroViagemDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class RoteiroViagemDAO extends CrudDaoDefaultImpl {

    public RoteiroViagemDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "roteiroviagem";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return RoteiroViagemDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idroteiroviagem", "idRoteiroViagem", true, true, false, false));
        listFields.add(new Field("datainicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("datafim", "dataFim", false, false, false, false));
        listFields.add(new Field("idsolicitacaoservico", "idSolicitacaoServico", false, false, false, false));
        listFields.add(new Field("idintegrante", "idIntegrante", false, false, false, false));
        listFields.add(new Field("origem", "origem", false, false, false, false));
        listFields.add(new Field("destino", "destino", false, false, false, false));
        listFields.add(new Field("ida", "ida", false, false, false, false));
        listFields.add(new Field("volta", "volta", false, false, false, false));

        return listFields;
    }

    /**
     * Retorna o roteiro atual ligado ao idintegrante passado
     *
     * @param idIntegrante
     * @return
     * @throws Exception
     */
    public RoteiroViagemDTO findByIdIntegrante(final Integer idIntegrante) throws PersistenceException {
        List result = new ArrayList<RoteiroViagemDTO>();

        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idIntegrante", "=", idIntegrante));
        condicao.add(new Condition("dataFim", "is", null));
        ordenacao.add(new Order("idRoteiroViagem"));

        result = (List) super.findByCondition(condicao, ordenacao);

        if (result != null && !result.isEmpty()) {
            return (RoteiroViagemDTO) result.get(0);
        } else {
            return null;
        }
    }

    /**
     * Retorna uma coleção de roteiros passados ligados ao idintegrante passado
     *
     * @param idIntegrante
     * @return
     * @throws Exception
     */
    public Collection<RoteiroViagemDTO> findByIdIntegranteHistorico(final Integer idIntegrante) throws PersistenceException {
        List result = new ArrayList<RoteiroViagemDTO>();

        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idIntegrante", "=", idIntegrante));
        condicao.add(new Condition("dataFim", "is not", null));
        ordenacao.add(new Order("idRoteiroViagem"));

        result = (List) super.findByCondition(condicao, ordenacao);

        if (result != null && !result.isEmpty()) {
            return result;
        } else {
            return null;
        }
    }

    /**
     * TODO Este metodo esta em desuso, pode ser removido na proxima versão
     */
    public Collection<RoteiroViagemDTO> findByIdSolicitacaoServico(final Integer idSolicitacaoServico) throws PersistenceException {
        List result = new ArrayList<RoteiroViagemDTO>();

        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        condicao.add(new Condition("dataFim", "is", null));
        ordenacao.add(new Order("idRoteiroViagem"));

        result = (List) super.findByCondition(condicao, ordenacao);

        if (result != null && !result.isEmpty()) {
            return result;
        }
        return null;
    }

    /**
     * TODO Este metodo esta em desuso, pode ser removido na proxima versão
     */
    public Collection<RoteiroViagemDTO> findByIdSolicitacaoServicoTodos(final Integer idSolicitacaoServico) throws PersistenceException {
        List result = new ArrayList<RoteiroViagemDTO>();

        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", idSolicitacaoServico));
        ordenacao.add(new Order("idRoteiroViagem"));

        result = (List) super.findByCondition(condicao, ordenacao);

        if (result != null && !result.isEmpty()) {
            return result;
        }
        return null;
    }

    /**
     * Retorna uma coleção de roteiros atuais ligados ao idintegrante passado
     *
     * @param idIntegrante
     * @return
     * @throws Exception
     */
    public Collection<RoteiroViagemDTO> findByIdIntegranteOriginal(final Integer idIntegrante) throws PersistenceException {
        List result = new ArrayList<RoteiroViagemDTO>();

        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idIntegrante", "=", idIntegrante));
        condicao.add(new Condition("dataFim", "is", null));
        ordenacao.add(new Order("idRoteiroViagem"));

        result = (List) super.findByCondition(condicao, ordenacao);

        if (result != null && !result.isEmpty()) {
            return result;
        }
        return null;
    }

    /**
     * Retorna uma coleção com todos os roteiros ligados ao idintegrante passado
     *
     * @param idIntegrante
     * @return
     * @throws Exception
     */
    public Collection<RoteiroViagemDTO> findByIdIntegranteTodos(final Integer idIntegrante) throws PersistenceException {
        List result = new ArrayList<RoteiroViagemDTO>();

        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idIntegrante", "=", idIntegrante));
        ordenacao.add(new Order("idRoteiroViagem"));

        result = (List) super.findByCondition(condicao, ordenacao);

        if (result != null && !result.isEmpty()) {
            return result;
        }
        return null;
    }

}