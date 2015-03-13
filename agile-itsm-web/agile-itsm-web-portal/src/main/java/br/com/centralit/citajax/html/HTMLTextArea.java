package br.com.centralit.citajax.html;

import br.com.centralit.citajax.util.CitAjaxWebUtil;
import br.com.centralit.citajax.util.JavaScriptUtil;

public class HTMLTextArea extends HTMLElement {

    public HTMLTextArea(final String idParm, final DocumentHTML documentParm) {
        super(idParm, documentParm);
    }

    @Override
    public String getType() {
        return TEXTAREA;
    }

    public void select() {
        this.setCommandExecute("document.getElementById('" + this.getId() + "').select()");
    }

    @Override
    public void setValue(final String value) {
        this.value = value;
        this.setCommandExecute("HTMLUtils.setValue('" + id + "',ObjectUtils.decodificaAspasApostrofe(ObjectUtils.decodificaEnter('"
                + CitAjaxWebUtil.codificaAspasApostrofe(CitAjaxWebUtil.codificaEnter(JavaScriptUtil.escapeJavaScript(value))) + "')))");
    }

}
