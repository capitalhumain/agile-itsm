package br.com.centralit.citcorpore.util;

public class ImportInfoField {

    private String nameField;
    private boolean key;
    private boolean sequence;
    private boolean check;
    private boolean exclusion;
    private String type;
    private String valueField;

    public String getNameField() {
        return nameField;
    }

    public void setNameField(final String nameField) {
        this.nameField = nameField;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(final String valueField) {
        this.valueField = valueField;
    }

    public boolean isKey() {
        return key;
    }

    public void setKey(final boolean key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public boolean isSequence() {
        return sequence;
    }

    public void setSequence(final boolean sequence) {
        this.sequence = sequence;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(final boolean check) {
        this.check = check;
    }

    public boolean isExclusion() {
        return exclusion;
    }

    public void setExclusion(final boolean exclusion) {
        this.exclusion = exclusion;
    }

}
