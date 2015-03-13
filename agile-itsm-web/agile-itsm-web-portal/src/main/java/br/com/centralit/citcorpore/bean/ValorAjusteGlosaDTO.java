package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ValorAjusteGlosaDTO extends BaseEntity {

    private static final long serialVersionUID = -645671303268213779L;

    private Integer idServicoContrato;
    private Integer idAcordoNivelServico;
    private Integer quantidadeFalhas;
    private Double valorAjuste;
    private String deleted;

    public Integer getIdServicoContrato() {
        return idServicoContrato;
    }

    public void setIdServicoContrato(final Integer idServicoContrato) {
        this.idServicoContrato = idServicoContrato;
    }

    public Integer getIdAcordoNivelServico() {
        return idAcordoNivelServico;
    }

    public void setIdAcordoNivelServico(final Integer idAcordoNivelServico) {
        this.idAcordoNivelServico = idAcordoNivelServico;
    }

    public Integer getQuantidadeFalhas() {
        return quantidadeFalhas;
    }

    public void setQuantidadeFalhas(final Integer quantidadeFalhas) {
        this.quantidadeFalhas = quantidadeFalhas;
    }

    public Double getValorAjuste() {
        return valorAjuste;
    }

    public void setValorAjuste(final Double valorAjuste) {
        this.valorAjuste = valorAjuste;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

}
