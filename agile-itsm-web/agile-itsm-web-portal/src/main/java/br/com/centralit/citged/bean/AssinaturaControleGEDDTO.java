package br.com.centralit.citged.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class AssinaturaControleGEDDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idAssinatura;
    private Integer idControleGED;
    private String pathAssinatura;

    private String pastaControleGed;

    public Integer getIdAssinatura() {
        return idAssinatura;
    }

    public void setIdAssinatura(final Integer idAssinatura) {
        this.idAssinatura = idAssinatura;
    }

    public Integer getIdControleGED() {
        return idControleGED;
    }

    public void setIdControleGED(final Integer idControleGED) {
        this.idControleGED = idControleGED;
    }

    public String getPathAssinatura() {
        return pathAssinatura;
    }

    public void setPathAssinatura(final String pathAssinatura) {
        this.pathAssinatura = pathAssinatura;
    }

    public String getPastaControleGed() {
        if (pastaControleGed == null) {
            pastaControleGed = "";
        }
        return pastaControleGed;
    }

    public void setPastaControleGed(final String pastaControleGed) {
        this.pastaControleGed = pastaControleGed;
    }

}
