package br.com.centralit.citcorpore.util;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

public class UtilRelatorio implements Serializable {

    public static Map<String, Object> trataInternacionalizacaoLocale(final HttpSession session, final Map<String, Object> parametros) {
        String localeSession = null;
        if (session.getAttribute("locale") != null) {
            localeSession = session.getAttribute("locale").toString();
            if (localeSession.equalsIgnoreCase("pt") || localeSession.trim().equalsIgnoreCase("")) {
                final Locale locale = new Locale("pt", "BR");
                parametros.put("REPORT_LOCALE", locale);
                parametros.put("REPORT_RESOURCE_BUNDLE", ResourceBundle.getBundle("br.com.centralit.citcorpore.Mensagens.Mensagens_relatorio", locale));
            } else if (localeSession.equalsIgnoreCase("en")) {
                final Locale locale = new Locale("en", "US");
                parametros.put("REPORT_LOCALE", locale);
                parametros.put("REPORT_RESOURCE_BUNDLE", ResourceBundle.getBundle("br.com.centralit.citcorpore.Mensagens.Mensagens_relatorio", locale));
            } else {
                final Locale locale = new Locale("es", "ES");
                parametros.put("REPORT_LOCALE", locale);
                parametros.put("REPORT_RESOURCE_BUNDLE", ResourceBundle.getBundle("br.com.centralit.citcorpore.Mensagens.Mensagens_relatorio", locale));
            }
        } else {
            final Locale locale = new Locale("pt", "BR");
            parametros.put("REPORT_LOCALE", locale);
            parametros.put("REPORT_RESOURCE_BUNDLE", ResourceBundle.getBundle("br.com.centralit.citcorpore.Mensagens.Mensagens_relatorio", locale));
        }

        return parametros;

    }

}
