package br.com.centralit.citgerencial.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class GerencialGroupDTO extends BaseEntity {

    private static final long serialVersionUID = 4933718908367971403L;
    private GerencialFieldDTO gerencialField;
    private String spacingLeft;
    private String fieldName;

    public GerencialFieldDTO getGerencialField() {
        return gerencialField;
    }

    public void setGerencialField(final GerencialFieldDTO gerencialField) {
        this.gerencialField = gerencialField;
    }

    public String getSpacingLeft() {
        return spacingLeft;
    }

    public void setSpacingLeft(final String spacingLeft) {
        this.spacingLeft = spacingLeft;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
    }

}
