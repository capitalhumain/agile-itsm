package br.com.centralit.citcorpore.integracao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.NagiosNDOStateHistoryDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;

public class NagiosNDOStateHistoryDao extends CrudDaoDefaultImpl {

    public NagiosNDOStateHistoryDao(final String nameJNDI) {
        super(nameJNDI, null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("statehistory_id", "statehistory_id", true, true, false, false));
        listFields.add(new Field("instance_id", "instance_id", false, false, false, false));
        listFields.add(new Field("state_time", "state_time", false, false, false, false));
        listFields.add(new Field("state_time_usec", "state_time_usec", false, false, false, false));
        listFields.add(new Field("object_id", "object_id", false, false, false, false));
        listFields.add(new Field("state_change", "state_change", false, false, false, false));
        listFields.add(new Field("state", "state", false, false, false, false));
        listFields.add(new Field("state_type", "state_type", false, false, false, false));
        listFields.add(new Field("current_check_attempt", "current_check_attempt", false, false, false, false));
        listFields.add(new Field("max_check_attempts", "max_check_attempts", false, false, false, false));
        listFields.add(new Field("last_state", "last_state", false, false, false, false));
        listFields.add(new Field("last_hard_state", "last_hard_state", false, false, false, false));
        listFields.add(new Field("last_hard_state", "last_hard_state", false, false, false, false));
        listFields.add(new Field("output", "output", false, false, false, false));
        listFields.add(new Field("long_output", "long_output", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "nagios_statehistory";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return NagiosNDOStateHistoryDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public Collection findByObject_id(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("object_id", "=", parm));
        ordenacao.add(new Order("statehistory_id"));
        return super.findByCondition(condicao, ordenacao);
    }

    public void deleteByObject_id(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("object_id", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByHostServiceStatus(final String hostName, final String serviceName, final String status, final Date dataInicial, final Date dataFinal)
            throws PersistenceException {
        final List parametros = new ArrayList<>();
        String sql = "select " + this.getNamesFieldsStr() + " from " + this.getTableName() + " ";
        sql = sql + "where 1 = 1 and (state_type = 1 or state = 0) "; // state_type = 1 --> HARD TYPE

        if (hostName != null && !hostName.trim().equalsIgnoreCase("")) {
            if (serviceName != null && !serviceName.trim().equalsIgnoreCase("")) {
                sql = sql + " and object_id = (select object_id from nagios_objects where name1 = ? and name2 = ?) ";
                parametros.add(hostName);
                parametros.add(serviceName);
            } else {
                sql = sql + " and object_id = (select object_id from nagios_objects where name1 = ? and name2 is null) ";
                parametros.add(hostName);
            }
        }
        if (status != null && !status.trim().equalsIgnoreCase("")) {
            Integer idStatus = 0;
            if (status.equalsIgnoreCase("DOWN") || status.equalsIgnoreCase("CRITICAL")) {
                idStatus = 2;
            }
            if (status.equalsIgnoreCase("WARNING") || status.equalsIgnoreCase("WARN")) {
                idStatus = 1;
            }
            sql = sql + " and state = ? ";
            parametros.add(idStatus);
        }

        sql = sql + " and state_time between ? and ?";
        parametros.add(dataInicial);
        parametros.add(dataFinal);

        sql = sql + " order by state_time";
        final List lst = this.execSQL(sql, parametros.toArray());
        return this.listConvertion(NagiosNDOStateHistoryDTO.class, lst, this.getListNamesFieldClass());
    }

    public Collection findByHostServiceStatusAndServiceNull(final String hostName, final String status, final Date dataInicial, final Date dataFinal)
            throws PersistenceException {
        final List parametros = new ArrayList<>();
        String sql = "select " + this.getNamesFieldsStr() + " from " + this.getTableName() + " ";
        sql = sql + "where 1 = 1 and (state_type = 1 or state = 0) "; // state_type = 1 --> HARD TYPE

        if (hostName != null && !hostName.trim().equalsIgnoreCase("")) {
            sql = sql + " and object_id = (select object_id from nagios_objects where name1 = ? and name2 is null) ";
            parametros.add(hostName);
        }

        if (status != null && !status.trim().equalsIgnoreCase("")) {
            Integer idStatus = 0;
            if (status.equalsIgnoreCase("DOWN") || status.equalsIgnoreCase("CRITICAL")) {
                idStatus = 2;
            }
            if (status.equalsIgnoreCase("WARNING") || status.equalsIgnoreCase("WARN")) {
                idStatus = 1;
            }
            sql = sql + " and state = ? ";
            parametros.add(idStatus);
        }

        sql = sql + " and state_time between ? and ?";
        parametros.add(dataInicial);
        parametros.add(dataFinal);

        sql = sql + " order by state_time";
        final List lst = this.execSQL(sql, parametros.toArray());
        return this.listConvertion(NagiosNDOStateHistoryDTO.class, lst, this.getListNamesFieldClass());
    }

}
