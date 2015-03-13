package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class CtrlAsteriskDTO extends BaseEntity {

    private static final long serialVersionUID = 4964912748878635243L;

    private String ramalTelefone;
    private String listaChamadas;

    public String getRamalTelefone() {
        return ramalTelefone;
    }

    public void setRamalTelefone(final String ramalTelefone) {
        this.ramalTelefone = ramalTelefone;
    }

    public String getListaChamadas() {
        return listaChamadas;
    }

    public void setListaChamadas(final String listaChamadas) {
        this.listaChamadas = listaChamadas;
    }

}
