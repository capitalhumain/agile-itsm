package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.UrgenciaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 *
 * @author rodrigo.oliveira
 *
 */
public class UrgenciaDAO extends CrudDaoDefaultImpl {

    public UrgenciaDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("idUrgencia"));
        return super.find(obj, list);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idUrgencia", "idUrgencia", true, true, false, false));
        listFields.add(new Field("nivelUrgencia", "nivelUrgencia", false, false, false, false));
        listFields.add(new Field("siglaUrgencia", "siglaUrgencia", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return "Urgencia";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("idUrgencia"));
        return super.list(list);
    }

    @Override
    public Class getBean() {
        return UrgenciaDTO.class;
    }

    public void deleteUrgencia() throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List parametros = new ArrayList<>();
        parametros.add(0);
        condicao.add(new Condition("idUrgencia", ">", parametros));
        super.deleteByCondition(condicao);
    }

    public List<UrgenciaDTO> restoreBySigla(final String siglaUrgencia) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("siglaUrgencia", "=", siglaUrgencia));
        return (List<UrgenciaDTO>) super.findByCondition(condicao, null);
    }

    public List<UrgenciaDTO> restoreByNivel(final String nivelUrgencia) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("nivelUrgencia", "=", nivelUrgencia));
        return (List<UrgenciaDTO>) super.findByCondition(condicao, null);
    }

}
