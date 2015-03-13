/**
 * CentralIT - CITSmart
 */
package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AnexoBaseConhecimentoDTO;
import br.com.centralit.citcorpore.bean.BaseConhecimentoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * DAO de AnexoBaseConhecimento.
 *
 * @author valdoilo.damasceno
 */
public class AnexoBaseConhecimentoDAO extends CrudDaoDefaultImpl {

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDANEXOBASECONHECIMENTO", "idAnexoBaseConhecimento", true, true, false, false));
        listFields.add(new Field("IDBASECONHECIMENTO", "idBaseConhecimento", false, false, false, false));
        listFields.add(new Field("DATAINICIO", "dataInicio", false, false, false, false));
        listFields.add(new Field("DATAFIM", "dataFim", false, false, false, false));
        listFields.add(new Field("NOME", "nomeAnexo", false, false, false, false));
        listFields.add(new Field("DESCRICAO", "descricao", false, false, false, false));
        listFields.add(new Field("LINK", "link", false, false, false, false));
        listFields.add(new Field("EXTENSAO", "extensao", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "ANEXOBASECONHECIMENTO";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("dataInicio"));
        return super.list(ordenacao);
    }

    /**
     * Retorna os Anexos da Base de Conhecimento informada.
     *
     * @param baseConhecimentoBean
     * @return listaDeAnexos
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public Collection<AnexoBaseConhecimentoDTO> consultarAnexosDaBaseConhecimento(final BaseConhecimentoDTO baseConhecimentoBean) throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        final List<Condition> condicao = new ArrayList<>();

        ordenacao.add(new Order("nomeAnexo"));
        condicao.add(new Condition("dataFim", "is", null));
        condicao.add(new Condition("idBaseConhecimento", "=", baseConhecimentoBean.getIdBaseConhecimento()));

        return super.findByCondition(condicao, ordenacao);
    }

    @Override
    public Class getBean() {
        return AnexoBaseConhecimentoDTO.class;
    }

    public AnexoBaseConhecimentoDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

}
