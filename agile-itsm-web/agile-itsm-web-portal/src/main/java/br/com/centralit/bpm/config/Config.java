package br.com.centralit.bpm.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.centralit.citajax.util.CitAjaxUtil;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilTratamentoArquivos;

import com.google.gson.Gson;

public class Config {

    private static Properties props = null;
    private static final String fileName = "bpm.properties";
    private static final String designJson = "bpm_design.json";

    private static final String prefixoClasseDto = "br.com.centralit.bpm.dto.ElementoFluxo";
    private static final String prefixoClasseNegocio = "br.com.centralit.bpm.negocio.";

    public static InputStream inputStreamSettedInLoad = null;

    private static Design design = null;

    static {
        props = new Properties();
        final ClassLoader load = Constantes.class.getClassLoader();

        InputStream is = load.getResourceAsStream(fileName);
        if (is == null) {
            is = ClassLoader.getSystemResourceAsStream(fileName);
        }
        if (is == null) {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
        }

        // InputStream is = ClassLoader.getSystemResourceAsStream(fileName);
        try {
            if (is == null) {
                is = inputStreamSettedInLoad;
            }
            if (is == null) {
                throw new Exception("Arquivo de recursos nao encontrado: " + fileName);
            }
            props.load(is);
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    private static Properties getProps() {
        if (props == null) {
            return null;
        }

        return props;
    }

    public static String getPropriedade(final String chave) {
        return getProps().getProperty(chave);
    }

    public static String getClasseDtoElemento(final String tipoElemento) {
        String result = getProps().getProperty("classe.dto.elemento." + tipoElemento.toLowerCase());
        if (result == null) {
            result = prefixoClasseDto + tipoElemento + "DTO";
        }
        return result;
    }

    public static String getClasseNegocioElemento(final String tipoElemento) {
        String result = getProps().getProperty("classe.negocio.elemento." + tipoElemento.toLowerCase());
        if (result == null) {
            result = prefixoClasseNegocio + tipoElemento;
        }
        return result;
    }

    public static Design getRender() {
        try {
            final String propriedades = UtilTratamentoArquivos.getStringTextFromFileTxt(CitAjaxUtil.CAMINHO_REAL_APP + "/WEB-INF/" + designJson);
            design = new Gson().fromJson(propriedades, Design.class);
            design.configuraElementos();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return design;
    }

}
