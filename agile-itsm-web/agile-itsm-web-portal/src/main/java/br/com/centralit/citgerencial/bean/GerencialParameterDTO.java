package br.com.centralit.citgerencial.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class GerencialParameterDTO extends BaseEntity {

    private static final long serialVersionUID = -360825560015299550L;

    private String name;
    private String type;
    private String typeHTML;
    private boolean fix;
    private String value;
    private String description;
    private boolean mandatory;
    private Integer size;
    private boolean reload;
    private String defaultValue;

    private Collection colOptions;

    public boolean isFix() {
        return fix;
    }

    public void setFix(final boolean fix) {
        this.fix = fix;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(final boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(final Integer size) {
        this.size = size;
    }

    public String getTypeHTML() {
        return typeHTML;
    }

    public void setTypeHTML(final String typeHTML) {
        this.typeHTML = typeHTML;
    }

    public Collection getColOptions() {
        return colOptions;
    }

    public void setColOptions(final Collection colOptions) {
        this.colOptions = colOptions;
    }

    public boolean isReload() {
        return reload;
    }

    public void setReload(final boolean reload) {
        this.reload = reload;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }

}
