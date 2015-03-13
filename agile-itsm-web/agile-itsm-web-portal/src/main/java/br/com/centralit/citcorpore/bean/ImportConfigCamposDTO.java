package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ImportConfigCamposDTO extends BaseEntity {

    private static final long serialVersionUID = 1670709329647476793L;

    private Integer idImportConfigCampo;
    private Integer idImportConfig;
    private String origem;
    private String destino;
    private String script;
    private Integer idImportarDados;

    public Integer getIdImportConfigCampo() {
        return idImportConfigCampo;
    }

    public void setIdImportConfigCampo(final Integer parm) {
        idImportConfigCampo = parm;
    }

    public Integer getIdImportConfig() {
        return idImportConfig;
    }

    public void setIdImportConfig(final Integer parm) {
        idImportConfig = parm;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(final String parm) {
        origem = parm;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(final String parm) {
        destino = parm;
    }

    public String getScript() {
        return script;
    }

    public void setScript(final String parm) {
        script = parm;
    }

    public Integer getIdImportarDados() {
        return idImportarDados;
    }

    public void setIdImportarDados(final Integer idImportarDados) {
        this.idImportarDados = idImportarDados;
    }

}
