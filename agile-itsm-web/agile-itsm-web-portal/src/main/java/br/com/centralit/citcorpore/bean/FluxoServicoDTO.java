package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class FluxoServicoDTO extends BaseEntity {

    private Integer idFluxoServico;
    private Integer idServicoContrato;
    private Integer idTipoFluxo;
    private Integer idFase;
    private String principal;
    private String deleted;

    public Integer getIdFluxoServico() {
        return idFluxoServico;
    }

    public void setIdFluxoServico(final Integer idFluxoServico) {
        this.idFluxoServico = idFluxoServico;
    }

    public Integer getIdServicoContrato() {
        return idServicoContrato;
    }

    public void setIdServicoContrato(final Integer parm) {
        idServicoContrato = parm;
    }

    public Integer getIdTipoFluxo() {
        return idTipoFluxo;
    }

    public void setIdTipoFluxo(final Integer parm) {
        idTipoFluxo = parm;
    }

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(final Integer parm) {
        idFase = parm;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(final String principal) {
        this.principal = principal;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

}
