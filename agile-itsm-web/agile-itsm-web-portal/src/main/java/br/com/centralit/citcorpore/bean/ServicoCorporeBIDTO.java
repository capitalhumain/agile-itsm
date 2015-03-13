package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ServicoCorporeBIDTO extends BaseEntity {

    private static final long serialVersionUID = -3079183232889920998L;

    private Integer idServicoCorpore;
    private String nomeServico;

    public Integer getIdServicoCorpore() {
        return idServicoCorpore;
    }

    public void setIdServicoCorpore(final Integer idServicoCorpore) {
        this.idServicoCorpore = idServicoCorpore;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

}
