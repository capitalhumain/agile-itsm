/**
 *
 */
package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.BaseConhecimentoRelacionadoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * @author Vadoilo Damasceno
 *
 */
public class BaseConhecimentoRelacionadoDAO extends CrudDaoDefaultImpl {

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDBASECONHECIMENTO", "idBaseConhecimento", true, false, false, false));
        listFields.add(new Field("IDBASECONHECIMENTORELACIONADO", "idBaseConhecimentoRelacionado", true, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "BASECONHECIMENTORELACIONADO";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("idBaseConhecimento"));
        return super.list(ordenacao);
    }

    @Override
    public Class getBean() {
        return BaseConhecimentoRelacionadoDTO.class;
    }

    public BaseConhecimentoRelacionadoDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    /**
     * Deleta BaseConhecimentoRelacionado da Base de Conhecimento.
     *
     * @param idBaseConhecimento
     * @throws Exception
     * @author Vadoilo Damasceno
     */
    public void deleteByIdConhecimento(final Integer idBaseConhecimento) throws PersistenceException {

        final List<Condition> condicao = new ArrayList<>();

        condicao.add(new Condition("idBaseConhecimento", "=", idBaseConhecimento));

        this.deleteByCondition(condicao);

    }

    /**
     * Lista BaseConhecimentoRelacionadoDTO por idBaseConhecimento.
     *
     * @param idBaseConhecimento
     * @return Collection<ImportanciaConhecimentoUsuarioDTO>
     * @throws Exception
     * @author Vadoilo Damasceno
     */
    public Collection<BaseConhecimentoRelacionadoDTO> listByIdBaseConhecimento(final Integer idBaseConhecimento) throws PersistenceException {

        final List<Condition> condicao = new ArrayList<>();

        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idBaseConhecimento", "=", idBaseConhecimento));

        ordenacao.add(new Order("idBaseConhecimento", "ASC"));

        return this.findByCondition(condicao, ordenacao);
    }

    public Collection<BaseConhecimentoRelacionadoDTO> listByIdBaseConhecimentoRelacionado(final Integer idBaseConhecimentoRelacionado)
            throws PersistenceException {

        final List<Condition> condicao = new ArrayList<>();

        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idBaseConhecimentoRelacionado", "=", idBaseConhecimentoRelacionado));

        ordenacao.add(new Order("idBaseConhecimento", "ASC"));

        return this.findByCondition(condicao, ordenacao);
    }

}
