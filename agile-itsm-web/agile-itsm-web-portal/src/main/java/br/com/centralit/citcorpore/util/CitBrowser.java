package br.com.centralit.citcorpore.util;

import javax.servlet.http.HttpServletRequest;

import nl.bitwalker.useragentutils.UserAgent;
import nl.bitwalker.useragentutils.Version;

public class CitBrowser {

    String nome;
    int versao;

    public CitBrowser(final HttpServletRequest request) {
        final String userAgent = request.getHeader("user-agent");
        final UserAgent ua = UserAgent.parseUserAgentString(userAgent);
        final Version browserVersion = ua.getBrowserVersion();
        nome = ua.getBrowser().toString();
        if (browserVersion != null) {
            versao = Integer.parseInt(browserVersion.getMajorVersion());
        }
    }

    public String getNome() {
        return nome;
    }

    public int getVersao() {
        return versao;
    }

    public int valido() {
        if (nome.contains("IE") || nome.contains("Explorer")) {
            if (versao < 10) {
                return 0;
            }
        }
        return 1;
    }

}
