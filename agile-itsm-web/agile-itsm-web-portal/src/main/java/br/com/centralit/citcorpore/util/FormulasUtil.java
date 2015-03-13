package br.com.centralit.citcorpore.util;

import javax.servlet.http.HttpServletRequest;

import br.com.centralit.citajax.html.DocumentHTML;
import br.com.citframework.util.UtilFormatacao;

public class FormulasUtil {

    public static double getDoubleValueFromGrid(final HttpServletRequest request, final int seq, final String name) {
        String value = request.getParameter(name + UtilFormatacao.formatInt(seq, "00000"));
        if (value == null) {
            return 0;
        }
        value = value.replaceAll("\\.", "");
        value = value.replaceAll("\\,", ".");
        double v = 0;
        try {
            v = Double.parseDouble(value);
        } catch (final Exception e) {}
        return v;
    }

    public static void setDoubleValueFromGrid(final DocumentHTML document, final int seq, final String name, final double value) {
        final String valueStr = UtilFormatacao.formatDouble(value, 2);
        document.executeScript("document.getElementById('" + name + UtilFormatacao.formatInt(seq, "00000") + "').value = '" + valueStr + "'");
    }

    public static void lockObjectFromGrid(final DocumentHTML document, final int seq, final String name) {
        document.executeScript("document.getElementById('" + name + UtilFormatacao.formatInt(seq, "00000") + "').disabled = 'true'");
    }

}
