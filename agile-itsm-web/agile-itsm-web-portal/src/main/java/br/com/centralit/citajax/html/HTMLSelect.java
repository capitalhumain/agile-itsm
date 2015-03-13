package br.com.centralit.citajax.html;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang3.StringEscapeUtils;

import br.com.centralit.citajax.reflexao.CitAjaxReflexao;

public class HTMLSelect extends HTMLElement {

    private int iIndice = 0;

    public HTMLSelect(final String idParm, final DocumentHTML documentParm) {
        super(idParm, documentParm);
        iIndice = 0;
    }

    @Override
    public String getType() {
        return SELECT;
    }

    public void addOption(final String value, final String text) {
        this.setCommandExecute("HTMLUtils.addOption('" + this.getId() + "', '" + text + "', '" + value + "')");
        iIndice++;
    }

    public void addOptionIfNotExists(final String value, final String text) {
        this.setCommandExecute("HTMLUtils.addOptionIfNotExists('" + this.getId() + "', '" + text + "', '" + value + "')");
    }

    public void addOptions(final Collection colOptions, final String namePropertyValue, final String namePropertyText, final String valueSelected)
            throws Exception {
        if (colOptions == null) {
            return;
        }
        if (namePropertyValue == null) {
            return;
        }
        if (namePropertyText == null) {
            return;
        }

        Object obj;
        Object value, text;
        int i = iIndice;
        for (final Iterator it = colOptions.iterator(); it.hasNext();) {
            obj = it.next();
            value = CitAjaxReflexao.getPropertyValue(obj, namePropertyValue);
            text = CitAjaxReflexao.getPropertyValue(obj, namePropertyText);

            if (value == null) {
                value = "";
            }
            if (text == null) {
                text = "";
            }
            this.addOption(value.toString(), StringEscapeUtils.escapeEcmaScript(text.toString()));

            if (valueSelected != null) {
                if (valueSelected.equalsIgnoreCase(value.toString())) {
                    this.setSelectedIndex(i);
                }
            }
            i++;
        }
    }

    public boolean addOptions(final Collection colOptions, final String namePropertyValue, final String namePropertyText, final String valueSelected,
            final int numberLines) throws Exception {
        if (colOptions == null) {
            return false;
        }
        if (namePropertyValue == null) {
            return false;
        }
        if (namePropertyText == null) {
            return false;
        }

        Object obj;
        Object value, text;
        int i = iIndice;
        for (final Iterator it = colOptions.iterator(); it.hasNext();) {
            if (i > numberLines) {
                return true;
            }
            obj = it.next();
            value = CitAjaxReflexao.getPropertyValue(obj, namePropertyValue);
            text = CitAjaxReflexao.getPropertyValue(obj, namePropertyText);

            if (value == null) {
                value = "";
            }
            if (text == null) {
                text = "";
            }
            this.addOption(value.toString(), text.toString());

            if (valueSelected != null) {
                if (valueSelected.equalsIgnoreCase(value.toString())) {
                    this.setSelectedIndex(i);
                }
            }
            i++;
        }
        return false;
    }

    public void removeOption(final int indice) {
        this.setCommandExecute("HTMLUtils.removeOption('" + this.getId() + "', " + indice + ")");
    }

    public void removeAllOptions() {
        this.setCommandExecute("HTMLUtils.removeAllOptions('" + this.getId() + "')");
        iIndice = 0;
    }

    public void removeOptionSelected() {
        this.setCommandExecute("HTMLUtils.removeOptionSelected('" + this.getId() + "')");
    }

    public void setSelectedIndex(final int indice) {
        this.setCommandExecute("HTMLUtils.setOptionSelected('" + this.getId() + "', " + indice + ")");
    }

}
