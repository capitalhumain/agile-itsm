package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author rodrigo.oliveira
 *
 */
public class ResultadosEsperadosDTO extends BaseEntity {

    private static final long serialVersionUID = 7484677113933240224L;

    private Integer idServicoContrato;
    private Integer idAcordoNivelServico;
    private String descricaoResultados;
    private String limites;
    private String glosa;
    private String limiteGlosa;
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

    public String getLimites() {
        return limites;
    }

    public void setLimites(final String limites) {
        this.limites = limites;
    }

    public String getLimiteGlosa() {
        return limiteGlosa;
    }

    public void setLimiteGlosa(final String limiteGlosa) {
        this.limiteGlosa = limiteGlosa;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(final String glosa) {
        this.glosa = glosa;
    }

    public String getDescricaoResultados() {
        return descricaoResultados;
    }

    public void setDescricaoResultados(final String descricaoResultados) {
        this.descricaoResultados = descricaoResultados;
    }

}
