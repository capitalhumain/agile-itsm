package br.com.centralit.citgerencial.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class GerencialOptionsDTO extends BaseEntity {

    private static final long serialVersionUID = -2827106057596036652L;

    private String sql;
    private String type;
    private String classExecute;
    private boolean onload;

    public String getSql() {
        return sql;
    }

    public void setSql(final String sql) {
        this.sql = sql;
    }

    public boolean isOnload() {
        return onload;
    }

    public void setOnload(final boolean onload) {
        this.onload = onload;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getClassExecute() {
        return classExecute;
    }

    public void setClassExecute(final String classExecute) {
        this.classExecute = classExecute;
    }

}
