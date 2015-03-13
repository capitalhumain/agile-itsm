package br.com.centralit.citcorpore.util;

import java.io.File;

import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;

public class LogoRel {

    public static String getDirLogoPadraoRel() {
        final String realPath = CITCorporeUtil.CAMINHO_REAL_APP;
        final String pLogo = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.URL_LOGO_PADRAO_RELATORIO, "");
        String resultado = "";
        final int pos = pLogo.indexOf("galeriaImagens");
        if (pos > -1) {
            resultado = realPath + pLogo.substring(pos);
        } else {
            resultado = retornaCaminhoLogoSistema();
        }
        return resultado;
    }

    public static File getFile() {
        final File logo = new File(getDirLogoPadraoRel());
        return logo.exists() ? logo : retornaLogoSistema();
    }

    public static String retornaCaminhoLogoSistema() {
        return CITCorporeUtil.CAMINHO_REAL_APP + "imagens" + File.separator + "logo" + File.separator + "logo.png";
    }

    public static File retornaLogoSistema() {
        final File logoSistema = new File(retornaCaminhoLogoSistema());
        return logoSistema.exists() ? logoSistema : null;
    }

}
