package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.TreinamentoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * @author Pedro
 *
 */
public class TreinamentoDao extends CrudDaoDefaultImpl {

    public TreinamentoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return TreinamentoDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idtreinamento", "idTreinamento", true, true, false, false));
        listFields.add(new Field("desctreinamento", "descTreinamento", false, false, false, false));
        listFields.add(new Field("datatreinamento", "dataTreinamento", false, false, false, false));
        listFields.add(new Field("idinstrutortreinamento", "idInstrutorTreinamento", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "treinamento";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List ordem = new ArrayList<>();
        ordem.add(new Order("descTreinamento"));
        return super.find(obj, ordem);
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("descTreinamento"));
        return super.list(list);
    }

}
