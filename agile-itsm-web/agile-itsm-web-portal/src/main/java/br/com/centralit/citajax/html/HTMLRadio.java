package br.com.centralit.citajax.html;

public class HTMLRadio extends HTMLElement {

    private boolean checked;

    public HTMLRadio(final String idParm, final DocumentHTML documentParm) {
        super(idParm, documentParm);
    }

    public HTMLRadio(final String idParm, final DocumentHTML documentParm, final boolean checkedParm) {
        super(idParm, documentParm);
        this.setChecked(checkedParm);
    }

    @Override
    public String getType() {
        return RADIO;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(final boolean checkedParm) {
        checked = checkedParm;
        this.setCommandExecute("document.getElementById('" + this.getId() + "').checked = " + (checkedParm ? "true" : "false"));
    }

}
