package br.com.centralit.citcorpore.util;

import java.util.Collection;

public class ImportInfoRecord {

    private String tableName;
    private String origem;
    private String filter;
    private String type;
    private Collection colFields;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    public Collection getColFields() {
        return colFields;
    }

    public void setColFields(final Collection colFields) {
        this.colFields = colFields;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(final String origem) {
        this.origem = origem;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(final String filter) {
        this.filter = filter;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

}
