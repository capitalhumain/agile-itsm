package br.com.centralit.citajax.html;

public class HTMLCheckbox extends HTMLElement {

    private boolean checked;

    public HTMLCheckbox(final String idParm, final DocumentHTML documentParm) {
        super(idParm, documentParm);
    }

    public HTMLCheckbox(final String idParm, final DocumentHTML documentParm, final boolean checkedParm) {
        super(idParm, documentParm);
        this.setChecked(checkedParm);
    }

    @Override
    public String getType() {
        return CHECKBOX;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(final boolean checkedParm) {
        checked = checkedParm;
        this.setCommandExecute("document.getElementById('" + this.getId() + "').checked = " + (checkedParm ? "true" : "false"));
    }

    @Override
    public void setValue(final String value) {
        this.value = value;
        this.setCommandExecute("HTMLUtils.setValue('" + id + "','" + value + "')");
    }

    public void setValue(final String[] value) {
        String aux = "";
        for (final String element : value) {
            if (aux.equalsIgnoreCase("")) {
                aux = "[";
            } else {
                aux += ",";
            }
            aux += "'" + element + "'";
        }
        aux += "]";
        this.setCommandExecute("HTMLUtils.setValueCheckBox('" + id + "'," + aux + ")");
    }

}
