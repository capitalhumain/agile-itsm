package br.com.centralit.citajax.html;

public class HTMLTextBox extends HTMLElement {

    public HTMLTextBox(final String idParm, final DocumentHTML documentParm) {
        super(idParm, documentParm);
    }

    @Override
    public String getType() {
        return TEXTBOX;
    }

    public void select() {
        this.setCommandExecute("document.getElementById('" + this.getId() + "').select()");
    }

}
