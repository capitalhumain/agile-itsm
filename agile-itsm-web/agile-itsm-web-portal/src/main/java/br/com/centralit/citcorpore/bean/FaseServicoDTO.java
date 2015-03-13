package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class FaseServicoDTO extends BaseEntity {

    private Integer idFase;
    private String nomeFase;
    private String faseCaptura;

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(final Integer parm) {
        idFase = parm;
    }

    public String getNomeFase() {
        return nomeFase;
    }

    public void setNomeFase(final String parm) {
        nomeFase = parm;
    }

    public String getFaseCaptura() {
        return faseCaptura;
    }

    public void setFaseCaptura(final String parm) {
        faseCaptura = parm;
    }

}
