package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class ObjetivoPlanoMelhoriaDTO extends BaseEntity {

    private Integer idObjetivoPlanoMelhoria;
    private Integer idPlanoMelhoria;
    private Integer idPlanoMelhoriaAux1;
    private String tituloObjetivo;
    private String detalhamento;
    private String resultadoEsperado;
    private String medicao;
    private String responsavel;
    private String criadoPor;
    private String modificadoPor;
    private java.sql.Date dataCriacao;
    private java.sql.Date ultModificacao;

    private Collection<AcaoPlanoMelhoriaDTO> listAcaoPlanoMelhoria;

    private Collection<ObjetivoPlanoMelhoriaDTO> listObjetivosPlanoMelhoria;

    private Collection<ObjetivoMonitoramentoDTO> listObjetivosMonitoramento;

    private Integer sequencialObjetivo;

    public Integer getIdObjetivoPlanoMelhoria() {
        return idObjetivoPlanoMelhoria;
    }

    public void setIdObjetivoPlanoMelhoria(final Integer parm) {
        idObjetivoPlanoMelhoria = parm;
    }

    public Integer getIdPlanoMelhoria() {
        return idPlanoMelhoria;
    }

    public void setIdPlanoMelhoria(final Integer parm) {
        idPlanoMelhoria = parm;
    }

    public String getTituloObjetivo() {
        return tituloObjetivo;
    }

    public void setTituloObjetivo(final String parm) {
        tituloObjetivo = parm;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(final String parm) {
        detalhamento = parm;
    }

    public String getResultadoEsperado() {
        return resultadoEsperado;
    }

    public void setResultadoEsperado(final String parm) {
        resultadoEsperado = parm;
    }

    public String getMedicao() {
        return medicao;
    }

    public void setMedicao(final String parm) {
        medicao = parm;
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
     * @return the listAcaoPlanoMelhoria
     */
    public Collection<AcaoPlanoMelhoriaDTO> getListAcaoPlanoMelhoria() {
        return listAcaoPlanoMelhoria;
    }

    /**
     * @param listAcaoPlanoMelhoria
     *            the listAcaoPlanoMelhoria to set
     */
    public void setListAcaoPlanoMelhoria(final Collection<AcaoPlanoMelhoriaDTO> listAcaoPlanoMelhoria) {
        this.listAcaoPlanoMelhoria = listAcaoPlanoMelhoria;
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
     * @return the listObjetivosPlanoMelhoria
     */
    public Collection<ObjetivoPlanoMelhoriaDTO> getListObjetivosPlanoMelhoria() {
        return listObjetivosPlanoMelhoria;
    }

    /**
     * @param listObjetivosPlanoMelhoria
     *            the listObjetivosPlanoMelhoria to set
     */
    public void setListObjetivosPlanoMelhoria(final Collection<ObjetivoPlanoMelhoriaDTO> listObjetivosPlanoMelhoria) {
        this.listObjetivosPlanoMelhoria = listObjetivosPlanoMelhoria;
    }

    public Integer getIdPlanoMelhoriaAux1() {
        return idPlanoMelhoriaAux1;
    }

    public void setIdPlanoMelhoriaAux1(final Integer idPlanoMelhoriaAux1) {
        this.idPlanoMelhoriaAux1 = idPlanoMelhoriaAux1;
    }

    /**
     * @return the listObjetivosMonitoramento
     */
    public Collection<ObjetivoMonitoramentoDTO> getListObjetivosMonitoramento() {
        return listObjetivosMonitoramento;
    }

    /**
     * @param listObjetivosMonitoramento
     *            the listObjetivosMonitoramento to set
     */
    public void setListObjetivosMonitoramento(final Collection<ObjetivoMonitoramentoDTO> listObjetivosMonitoramento) {
        this.listObjetivosMonitoramento = listObjetivosMonitoramento;
    }

}
