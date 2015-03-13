package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class AcaoPlanoMelhoriaDTO extends BaseEntity {

    private Integer idAcaoPlanoMelhoria;
    private Integer idPlanoMelhoria;
    private Integer idObjetivoPlanoMelhoria;
    private String tituloAcao;
    private String detalhamentoAcao;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;
    private String responsavel;
    private java.sql.Date dataConclusao;
    private String criadoPor;
    private String modificadoPor;
    private java.sql.Date dataCriacao;
    private java.sql.Date ultModificacao;

    private String tituloObjetivoPlanoMelhoria;

    private String resultadoEsperadoPlanoMelhoria;

    private String medicaoPlanoMelhoria;

    private Integer sequencialObjetivo;

    private Integer sequencialAcao;

    public Integer getIdAcaoPlanoMelhoria() {
        return idAcaoPlanoMelhoria;
    }

    public void setIdAcaoPlanoMelhoria(final Integer parm) {
        idAcaoPlanoMelhoria = parm;
    }

    public Integer getIdPlanoMelhoria() {
        return idPlanoMelhoria;
    }

    public void setIdPlanoMelhoria(final Integer parm) {
        idPlanoMelhoria = parm;
    }

    public Integer getIdObjetivoPlanoMelhoria() {
        return idObjetivoPlanoMelhoria;
    }

    public void setIdObjetivoPlanoMelhoria(final Integer parm) {
        idObjetivoPlanoMelhoria = parm;
    }

    public String getTituloAcao() {
        return tituloAcao;
    }

    public void setTituloAcao(final String parm) {
        tituloAcao = parm;
    }

    public String getDetalhamentoAcao() {
        return detalhamentoAcao;
    }

    public void setDetalhamentoAcao(final String parm) {
        detalhamentoAcao = parm;
    }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final java.sql.Date parm) {
        dataInicio = parm;
    }

    public java.sql.Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final java.sql.Date parm) {
        dataFim = parm;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(final String parm) {
        responsavel = parm;
    }

    public java.sql.Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(final java.sql.Date parm) {
        dataConclusao = parm;
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
     * @return the sequencialObjetivo
     */
    public Integer getSequencialObjetivo() {
        return sequencialObjetivo;
    }

    /**
     * @param sequencialObjetivo
     *            the sequencialObjetivo to set
     */
    public void setSequencialObjetivo(final Integer sequencialObjetivo) {
        this.sequencialObjetivo = sequencialObjetivo;
    }

    /**
     * @return the sequencialAcao
     */
    public Integer getSequencialAcao() {
        return sequencialAcao;
    }

    /**
     * @param sequencialAcao
     *            the sequencialAcao to set
     */
    public void setSequencialAcao(final Integer sequencialAcao) {
        this.sequencialAcao = sequencialAcao;
    }

    /**
     * @return the resultadoEsperadoPlanoMelhoria
     */
    public String getResultadoEsperadoPlanoMelhoria() {
        return resultadoEsperadoPlanoMelhoria;
    }

    /**
     * @param resultadoEsperadoPlanoMelhoria
     *            the resultadoEsperadoPlanoMelhoria to set
     */
    public void setResultadoEsperadoPlanoMelhoria(final String resultadoEsperadoPlanoMelhoria) {
        this.resultadoEsperadoPlanoMelhoria = resultadoEsperadoPlanoMelhoria;
    }

    /**
     * @return the medicaoPlanoMelhoria
     */
    public String getMedicaoPlanoMelhoria() {
        return medicaoPlanoMelhoria;
    }

    /**
     * @param medicaoPlanoMelhoria
     *            the medicaoPlanoMelhoria to set
     */
    public void setMedicaoPlanoMelhoria(final String medicaoPlanoMelhoria) {
        this.medicaoPlanoMelhoria = medicaoPlanoMelhoria;
    }

}
