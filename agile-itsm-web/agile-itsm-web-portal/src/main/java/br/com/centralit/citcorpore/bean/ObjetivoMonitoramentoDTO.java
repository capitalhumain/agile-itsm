package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ObjetivoMonitoramentoDTO extends BaseEntity {

    private Integer idObjetivoMonitoramento;
    private Integer idObjetivoPlanoMelhoria;
    private String tituloMonitoramento;
    private String fatorCriticoSucesso;
    private String kpi;
    private String metrica;
    private String medicao;
    private String relatorios;
    private String responsavel;
    private String criadoPor;
    private String modificadoPor;
    private java.sql.Date dataCriacao;
    private java.sql.Date ultModificacao;

    private String tituloObjetivoPlanoMelhoria;

    private Integer sequecialObjetivoMonitoramento;

    public Integer getIdObjetivoMonitoramento() {
        return idObjetivoMonitoramento;
    }

    public void setIdObjetivoMonitoramento(final Integer parm) {
        idObjetivoMonitoramento = parm;
    }

    public Integer getIdObjetivoPlanoMelhoria() {
        return idObjetivoPlanoMelhoria;
    }

    public void setIdObjetivoPlanoMelhoria(final Integer parm) {
        idObjetivoPlanoMelhoria = parm;
    }

    public String getTituloMonitoramento() {
        return tituloMonitoramento;
    }

    public void setTituloMonitoramento(final String parm) {
        tituloMonitoramento = parm;
    }

    public String getFatorCriticoSucesso() {
        return fatorCriticoSucesso;
    }

    public void setFatorCriticoSucesso(final String parm) {
        fatorCriticoSucesso = parm;
    }

    public String getKpi() {
        return kpi;
    }

    public void setKpi(final String parm) {
        kpi = parm;
    }

    public String getMetrica() {
        return metrica;
    }

    public void setMetrica(final String parm) {
        metrica = parm;
    }

    public String getMedicao() {
        return medicao;
    }

    public void setMedicao(final String parm) {
        medicao = parm;
    }

    public String getRelatorios() {
        return relatorios;
    }

    public void setRelatorios(final String parm) {
        relatorios = parm;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(final String parm) {
        responsavel = parm;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(final String parm) {
        criadoPor = parm;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(final String parm) {
        modificadoPor = parm;
    }

    public java.sql.Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(final java.sql.Date parm) {
        dataCriacao = parm;
    }

    public java.sql.Date getUltModificacao() {
        return ultModificacao;
    }

    public void setUltModificacao(final java.sql.Date parm) {
        ultModificacao = parm;
    }

    /**
     * @return the tituloObjetivoPlanoMelhoria
     */
    public String getTituloObjetivoPlanoMelhoria() {
        return tituloObjetivoPlanoMelhoria;
    }

    /**
     * @param tituloObjetivoPlanoMelhoria
     *            the tituloObjetivoPlanoMelhoria to set
     */
    public void setTituloObjetivoPlanoMelhoria(final String tituloObjetivoPlanoMelhoria) {
        this.tituloObjetivoPlanoMelhoria = tituloObjetivoPlanoMelhoria;
    }

    /**
     * @return the sequecialObjetivoMonitoramento
     */
    public Integer getSequecialObjetivoMonitoramento() {
        return sequecialObjetivoMonitoramento;
    }

    /**
     * @param sequecialObjetivoMonitoramento
     *            the sequecialObjetivoMonitoramento to set
     */
    public void setSequecialObjetivoMonitoramento(final Integer sequecialObjetivoMonitoramento) {
        this.sequecialObjetivoMonitoramento = sequecialObjetivoMonitoramento;
    }

}
