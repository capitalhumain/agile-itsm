/**
 *
 */
package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ImportanciaConhecimentoUsuarioDTO;
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
public class ImportanciaConhecimentoUsuarioDAO extends CrudDaoDefaultImpl {

    public ImportanciaConhecimentoUsuarioDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDBASECONHECIMENTO", "idBaseConhecimento", true, false, false, false));
        listFields.add(new Field("IDUSUARIO", "idUsuario", true, false, false, false));
        listFields.add(new Field("GRAUIMPORTANCIAUSUARIO", "grauImportanciaUsuario", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "IMPORTANCIACONHECIMENTOUSUARIO";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("idBaseConhecimento"));
        return super.list(ordenacao);
    }

    @Override
    public Class getBean() {
        return ImportanciaConhecimentoUsuarioDTO.class;
    }

    /**
     * Deleta ImportanciaConhecimentoUsuário da Base de Conhecimento.
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
     * Lista ImportanciaConhecimentoUsuario por idBaseConhecimento.
     *
     * @param idBaseConhecimento
     * @return Collection<ImportanciaConhecimentoUsuarioDTO>
     * @throws Exception
     * @author Vadoilo Damasceno
     */
    public Collection<ImportanciaConhecimentoUsuarioDTO> listByIdBaseConhecimento(final Integer idBaseConhecimento) throws PersistenceException {

        final List<Condition> condicao = new ArrayList<>();

        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("idBaseConhecimento", "=", idBaseConhecimento));

        ordenacao.add(new Order("idBaseConhecimento", "ASC"));

        return this.findByCondition(condicao, ordenacao);
    }

}
