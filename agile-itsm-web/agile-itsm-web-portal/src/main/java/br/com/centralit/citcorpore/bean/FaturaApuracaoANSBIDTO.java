package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class FaturaApuracaoANSBIDTO extends BaseEntity {

    private Integer idFaturaApuracaoANS;
    private Integer idFatura;
    private Integer idAcordoNivelServicoContrato;
    private Double valorApurado;
    private String detalhamento;
    private Double percentualGlosa;
    private Double valorGlosa;
    private java.sql.Date dataApuracao;
    private String descricao;
    private Integer idConexaoBI;

    public Integer getIdFaturaApuracaoANS() {
        return idFaturaApuracaoANS;
    }

    public void setIdFaturaApuracaoANS(final Integer parm) {
        idFaturaApuracaoANS = parm;
    }

    public Integer getIdFatura() {
        return idFatura;
    }

    public void setIdFatura(final Integer parm) {
        idFatura = parm;
    }

    public Integer getIdAcordoNivelServicoContrato() {
        return idAcordoNivelServicoContrato;
    }

    public void setIdAcordoNivelServicoContrato(final Integer parm) {
        idAcordoNivelServicoContrato = parm;
    }

    public Double getValorApurado() {
        return valorApurado;
    }

    public void setValorApurado(final Double parm) {
        valorApurado = parm;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(final String parm) {
        detalhamento = parm;
    }

    public Double getPercentualGlosa() {
        return percentualGlosa;
    }

    public void setPercentualGlosa(final Double parm) {
        percentualGlosa = parm;
    }

    public Double getValorGlosa() {
        return valorGlosa;
    }

    public void setValorGlosa(final Double parm) {
        valorGlosa = parm;
    }

    public java.sql.Date getDataApuracao() {
        return dataApuracao;
    }

    public void setDataApuracao(final java.sql.Date parm) {
        dataApuracao = parm;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao
     *            the descricao to set
     */
    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdConexaoBI() {
        return idConexaoBI;
    }

    public void setIdConexaoBI(final Integer idConexaoBI) {
        this.idConexaoBI = idConexaoBI;
    }

}
