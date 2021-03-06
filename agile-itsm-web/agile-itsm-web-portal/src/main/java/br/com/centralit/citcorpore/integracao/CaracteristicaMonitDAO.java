/**
 *
 */
package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.CaracteristicaMonitDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

/**
 * @author valdoilo.damasceno
 *
 */
public class CaracteristicaMonitDAO extends CrudDaoDefaultImpl {

    public CaracteristicaMonitDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {

        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDCARACTERISTICAMONIT", "idCaracteristicaMonit", true, true, false, false));
        listFields.add(new Field("IDCARACTERISTICA", "idCaracteristica", false, false, false, false));
        listFields.add(new Field("IDMONITORAMENTOATIVOS", "idMonitoramentoAtivos", false, false, false, false));
        listFields.add(new Field("DATAINICIO", "dataInicio", false, false, false, false));
        listFields.add(new Field("DATAFIM", "dataFim", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "caracteristicamonit";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return CaracteristicaMonitDTO.class;
    }

    public Collection<CaracteristicaMonitDTO> restoreByIdMonitoramentoAtivos(final Integer idMonitoramentoAtivos) throws PersistenceException {
        final List codicao = new ArrayList<>();

        codicao.add(new Condition("idMonitoramentoAtivos", "=", idMonitoramentoAtivos));

        return this.findByCondition(codicao, null);
    }

    public boolean deleteByIdMonitoramentoAtivos(final Integer idMonitoramentoAtivos) throws PersistenceException {
        final List codicao = new ArrayList<>();

        codicao.add(new Condition("idMonitoramentoAtivos", "=", idMonitoramentoAtivos));

        return this.deleteByCondition(codicao) != 0;

    }

}
