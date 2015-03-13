/**
 * CentralIT - CITSmart
 */
package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.SituacaoDemandaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class SituacaoDemandaDao extends CrudDaoDefaultImpl {

    public SituacaoDemandaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return SituacaoDemandaDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDSITUACAODEMANDA", "idSituacaoDemanda", true, false, false, true));
        listFields.add(new Field("NOMESITUACAOSERVICO", "nomeSituacao", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "situacaodemanda";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("nomeSituacao"));
        return super.list(list);
    }

}
