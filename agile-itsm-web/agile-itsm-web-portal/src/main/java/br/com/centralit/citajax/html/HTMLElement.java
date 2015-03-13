package br.com.centralit.citajax.html;

import br.com.centralit.citajax.util.CitAjaxWebUtil;
import br.com.centralit.citajax.util.JavaScriptUtil;

/**
 * Para utilizar o Framework CITAjax, deve-se acrescentar os seguintes arquivos de Javascript na pagina:
 * HTMLUtils.js
 * ObjectUtils.js
 *
 * @author emauri
 *
 */
public class HTMLElement {

    protected String id;
    protected boolean disabled;
    protected boolean readonly;
    protected boolean visible;
    protected String value;
    protected String innerHTML;
    protected String style;
    protected String className;
    protected DocumentHTML document; // Documento ao qual o elemento esta vinculado.

    // Definicao de tipos de objetos
    protected static final String UNDEFINED = "undefined";
    protected static final String TEXTBOX = "text";
    protected static final String TEXTAREA = "textarea";
    protected static final String CHECKBOX = "checkbox";
    protected static final String RADIO = "radio";
    protected static final String TABLE = "table";
    protected static final String SELECT = "select";
    protected static final String TREEVIEW = "treeview";
    protected static final String JANELAPOPUP = "janelapopup";

    public HTMLElement(final String idParm, final DocumentHTML documentParm) {
        this.setId(idParm);
        this.setDocument(documentParm);
        documentParm.setElement(idParm, this); // Atribui informacoes dele no document (Se nao existir).
    }

    public String getType() {
        return UNDEFINED;
    }

    public void setFocus() {
        this.setCommandExecute("HTMLUtils.setFocus('" + id + "')");
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(final boolean disabled) {
        this.disabled = disabled;
        this.setCommandExecute("document.getElementById('" + id + "').disabled=" + (disabled ? "true" : "false"));
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getInnerHTML() {
        return innerHTML;
    }

    public void setInnerHTML(final String innerHTML) {
        this.innerHTML = innerHTML;
        this.setCommandExecute("document.getElementById('" + id + "').innerHTML=ObjectUtils.decodificaAspasApostrofe(ObjectUtils.decodificaEnter('"
                + CitAjaxWebUtil.codificaAspasApostrofe(CitAjaxWebUtil.codificaEnter(JavaScriptUtil.escapeJavaScript(innerHTML))) + "'))");
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(final boolean readonly) {
        this.readonly = readonly;
        this.setCommandExecute("document.getElementById('" + id + "').readOnly=" + (readonly ? "true" : "false"));
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
        this.setCommandExecute("HTMLUtils.setValue('" + id + "','" + value + "')");
    }

    public DocumentHTML getDocument() {
        return document;
    }

    public void setDocument(final DocumentHTML document) {
        this.document = document;
    }

    protected void setCommandExecute(final String comand) {
        document.getComandsExecute().add(comand);
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(final String style) {
        this.style = style;
        this.setCommandExecute("document.getElementById('" + id + "').style='" + style + "'");
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(final String className) {
        this.className = className;
        this.setCommandExecute("document.getElementById('" + id + "').className='" + className + "'");
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(final boolean visible) {
        this.visible = visible;
        this.setCommandExecute("HTMLUtils.setVisible('" + id + "', " + (visible ? "true" : "false") + ")");
    }

}
