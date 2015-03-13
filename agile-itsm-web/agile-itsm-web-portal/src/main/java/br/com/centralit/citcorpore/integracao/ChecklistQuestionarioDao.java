package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ChecklistQuestionarioDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ChecklistQuestionarioDao extends CrudDaoDefaultImpl {

    public ChecklistQuestionarioDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);

    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {

        return null;
    }

    @Override
    public Collection<Field> getFields() {

        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDCARGO", "idCargo", true, true, false, false));
        listFields.add(new Field("NOMECARGO", "nomeCargo", false, false, false, false));
        listFields.add(new Field("DATAINICIO", "dataInicio", false, false, false, false));
        listFields.add(new Field("DATAFIM", "dataFim", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "CHECKLISTQUESTIONARIO";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("nomeCargo"));
        return super.list(list);
    }

    @Override
    public Class getBean() {
        return ChecklistQuestionarioDTO.class;
    }

}
