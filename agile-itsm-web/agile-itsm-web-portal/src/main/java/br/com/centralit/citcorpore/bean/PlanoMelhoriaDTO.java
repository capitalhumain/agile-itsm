package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class PlanoMelhoriaDTO extends BaseEntity {

    private Integer idPlanoMelhoria;
    private Integer idFornecedor;
    private Integer idContrato;
    private String titulo;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;
    private String objetivo;
    private String visaoGeral;
    private String escopo;
    private String visao;
    private String missao;
    private String notas;
    private String criadoPor;
    private String modificadoPor;
    private java.sql.Date dataCriacao;
    private java.sql.Date ultModificacao;
    private String situacao;

    // Campos de Objetivos
    private Integer idPlanoMelhoriaAux1;
    private Integer idObjetivoPlanoMelhoria;
    private String tituloObjetivo;
    private String detalhamento;
    private String resultadoEsperado;
    private String medicao;
    private String responsavel;

    // Campos de Acoes
    private Integer idAcaoPlanoMelhoria;
    private String tituloAcao;
    private String detalhamentoAcao;
    private java.sql.Date dataConclusao;

    // Campos referente ao relatorio

    private Object listAcaoPlanoMelhoria;

    // Campos de Monitoramento
    private Integer idObjetivoMonitoramento;
    private String tituloMonitoramento;
    private String fatorCriticoSucesso;
    private String kpi;
    private String metrica;
    private String relatorios;

    public Integer getIdPlanoMelhoria() {
        return idPlanoMelhoria;
    }

    public void setIdPlanoMelhoria(final Integer parm) {
        idPlanoMelhoria = parm;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(final Integer parm) {
        idFornecedor = parm;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer parm) {
        idContrato = parm;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String parm) {
        titulo = parm;
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

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(final String parm) {
        objetivo = parm;
    }

    public String getVisaoGeral() {
        return visaoGeral;
    }

    public void setVisaoGeral(final String parm) {
        visaoGeral = parm;
    }

    public String getEscopo() {
        return escopo;
    }

    public void setEscopo(final String parm) {
        escopo = parm;
    }

    public String getVisao() {
        return visao;
    }

    public void setVisao(final String parm) {
        visao = parm;
    }

    public String getMissao() {
        return missao;
    }

    public void setMissao(final String parm) {
        missao = parm;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(final String parm) {
        notas = parm;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public Integer getIdObjetivoPlanoMelhoria() {
        return idObjetivoPlanoMelhoria;
    }

    public void setIdObjetivoPlanoMelhoria(final Integer idObjetivoPlanoMelhoria) {
        this.idObjetivoPlanoMelhoria = idObjetivoPlanoMelhoria;
    }

    public String getTituloObjetivo() {
        return tituloObjetivo;
    }

    public void setTituloObjetivo(final String tituloObjetivo) {
        this.tituloObjetivo = tituloObjetivo;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(final String detalhamento) {
        this.detalhamento = detalhamento;
    }

    public String getResultadoEsperado() {
        return resultadoEsperado;
    }

    public void setResultadoEsperado(final String resultadoEsperado) {
        this.resultadoEsperado = resultadoEsperado;
    }

    public String getMedicao() {
        return medicao;
    }

    public void setMedicao(final String medicao) {
        this.medicao = medicao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(final String responsavel) {
        this.responsavel = responsavel;
    }

    public Integer getIdPlanoMelhoriaAux1() {
        return idPlanoMelhoriaAux1;
    }

    public void setIdPlanoMelhoriaAux1(final Integer idPlanoMelhoriaAux1) {
        this.idPlanoMelhoriaAux1 = idPlanoMelhoriaAux1;
    }

    public Integer getIdAcaoPlanoMelhoria() {
        return idAcaoPlanoMelhoria;
    }

    public void setIdAcaoPlanoMelhoria(final Integer idAcaoPlanoMelhoria) {
        this.idAcaoPlanoMelhoria = idAcaoPlanoMelhoria;
    }

    public String getTituloAcao() {
        return tituloAcao;
    }

    public void setTituloAcao(final String tituloAcao) {
        this.tituloAcao = tituloAcao;
    }

    public String getDetalhamentoAcao() {
        return detalhamentoAcao;
    }

    public void setDetalhamentoAcao(final String detalhamentoAcao) {
        this.detalhamentoAcao = detalhamentoAcao;
    }

    public java.sql.Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(final java.sql.Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Integer getIdObjetivoMonitoramento() {
        return idObjetivoMonitoramento;
    }

    public void setIdObjetivoMonitoramento(final Integer idObjetivoMonitoramento) {
        this.idObjetivoMonitoramento = idObjetivoMonitoramento;
    }

    public String getTituloMonitoramento() {
        return tituloMonitoramento;
    }

    public void setTituloMonitoramento(final String tituloMonitoramento) {
        this.tituloMonitoramento = tituloMonitoramento;
    }

    public String getFatorCriticoSucesso() {
        return fatorCriticoSucesso;
    }

    public void setFatorCriticoSucesso(final String fatorCriticoSucesso) {
        this.fatorCriticoSucesso = fatorCriticoSucesso;
    }

    public String getKpi() {
        return kpi;
    }

    public void setKpi(final String kpi) {
        this.kpi = kpi;
    }

    public String getMetrica() {
        return metrica;
    }

    public void setMetrica(final String metrica) {
        this.metrica = metrica;
    }

    public String getRelatorios() {
        return relatorios;
    }

    public void setRelatorios(final String relatorios) {
        this.relatorios = relatorios;
    }

    /**
     * @return the listAcaoPlanoMelhoria
     */
    public Object getListAcaoPlanoMelhoria() {
        return listAcaoPlanoMelhoria;
    }

    /**
     * @param listAcaoPlanoMelhoria
     *            the listAcaoPlanoMelhoria to set
     */
    public void setListAcaoPlanoMelhoria(final Object listAcaoPlanoMelhoria) {
        this.listAcaoPlanoMelhoria = listAcaoPlanoMelhoria;
    }

}
