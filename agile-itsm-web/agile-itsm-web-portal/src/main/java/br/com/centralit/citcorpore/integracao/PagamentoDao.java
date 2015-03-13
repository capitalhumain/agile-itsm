package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.TreinamentoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

/**
 * @author Pedro
 *
 */
public class PagamentoDao extends CrudDaoDefaultImpl {

    public PagamentoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Class getBean() {
        return TreinamentoDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idpagamento", "idPagamento", true, true, false, false));
        listFields.add(new Field("datapagamento", "descPagamento", false, false, false, false));
        listFields.add(new Field("parcela", "parcela", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "PAGAMENTO";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List ordem = new ArrayList<>();
        return super.find(obj, ordem);
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        return super.list(list);
    }

}
