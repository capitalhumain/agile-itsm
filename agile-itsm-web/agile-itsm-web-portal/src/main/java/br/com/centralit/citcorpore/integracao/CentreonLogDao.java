package br.com.centralit.citcorpore.integracao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.CentreonLogDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;

public class CentreonLogDao extends CrudDaoDefaultImpl {

    public CentreonLogDao(final String nameJNDI) {
        super(nameJNDI, null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("log_id", "log_id", true, true, false, false));
        listFields.add(new Field("ctime", "ctime", false, false, false, false));
        listFields.add(new Field("host_name", "host_name", false, false, false, false));
        listFields.add(new Field("service_description", "service_description", false, false, false, false));
        listFields.add(new Field("status", "status", false, false, false, false));
        listFields.add(new Field("output", "output", false, false, false, false));
        listFields.add(new Field("notification_cmd", "notification_cmd", false, false, false, false));
        listFields.add(new Field("notification_contact", "notification_contact", false, false, false, false));
        listFields.add(new Field("type", "type", false, false, false, false));
        listFields.add(new Field("retry", "retry", false, false, false, false));
        listFields.add(new Field("msg_type", "msg_type", false, false, false, false));
        listFields.add(new Field("instance", "instance", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "log";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    public Collection findByHostServiceStatus(final String hostName, final String serviceName, final String status, final Date dataInicial, final Date dataFinal)
            throws PersistenceException {
        final List parametros = new ArrayList<>();
        String sql = "select " + this.getNamesFieldsStr() + " from " + this.getTableName() + " ";
        sql = sql + "where 1 = 1 ";

        if (hostName != null && !hostName.trim().equalsIgnoreCase("")) {
            sql = sql + " and host_name = ? ";
            parametros.add(hostName);
        }
        if (serviceName != null && !serviceName.trim().equalsIgnoreCase("")) {
            sql = sql + " and service_description = ? ";
            parametros.add(serviceName);
        }
        if (status != null && !status.trim().equalsIgnoreCase("")) {
            sql = sql + " and status = ? ";
            parametros.add(status);
        }

        long tsNum1 = dataInicial.getTime() / 1000;
        tsNum1 = tsNum1 - 1;
        long tsNum2 = dataFinal.getTime() / 1000;
        tsNum2 = tsNum2 + 1;

        sql = sql + " and ctime between ? and ?";
        parametros.add(tsNum1);
        parametros.add(tsNum2);

        sql = sql + " order by ctime";
        final List lst = this.execSQL(sql, parametros.toArray());
        return this.listConvertion(CentreonLogDTO.class, lst, this.getListNamesFieldClass());
    }

    public Collection findByHostServiceStatusAndServiceNull(final String hostName, final String status, final Date dataInicial, final Date dataFinal)
            throws PersistenceException {
        final List parametros = new ArrayList<>();
        String sql = "select " + this.getNamesFieldsStr() + " from " + this.getTableName() + " ";
        sql = sql + "where 1 = 1 ";

        if (hostName != null && !hostName.trim().equalsIgnoreCase("")) {
            sql = sql + " and host_name = ? ";
            parametros.add(hostName);
        }
        if (status != null && !status.trim().equalsIgnoreCase("")) {
            sql = sql + " and status = ? ";
            parametros.add(status);
        }
        sql = sql + " and service_description is null ";

        long tsNum1 = dataInicial.getTime() / 1000;
        tsNum1 = tsNum1 - 1;
        long tsNum2 = dataFinal.getTime() / 1000;
        tsNum2 = tsNum2 + 1;

        sql = sql + " and ctime between ? and ?";
        parametros.add(tsNum1);
        parametros.add(tsNum2);

        sql = sql + " order by ctime";
        final List lst = this.execSQL(sql, parametros.toArray());
        return this.listConvertion(CentreonLogDTO.class, lst, this.getListNamesFieldClass());
    }

    @Override
    public Class getBean() {
        return CentreonLogDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

}
