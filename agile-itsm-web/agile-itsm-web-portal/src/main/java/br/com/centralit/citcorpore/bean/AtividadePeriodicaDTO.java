package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class AtividadePeriodicaDTO extends BaseEntity {

    private Integer idAtividadePeriodica;
    private Integer idContrato;
    private Integer idProcedimentoTecnico;
    private Integer idGrupoAtvPeriodica;
    private String tituloAtividade;
    private String descricao;
    private String orientacaoTecnica;
    private String criadoPor;
    private String alteradoPor;
    private java.sql.Date dataCriacao;
    private java.sql.Date dataUltAlteracao;

    private Integer idSolicitacaoServico;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;
    private String horaInicio;
    private Integer duracaoEstimada;

    private Integer idRequisicaoMudanca;
    private String blackout;
    private String identMudanca;
    private String nomeTipoMudanca;

    private Collection colItens;
    private Collection colItensOS;

    private Integer idProblema;

    private Integer idRequisicaoLiberacao;

    public Integer getIdAtividadePeriodica() {
        return idAtividadePeriodica;
    }

    public void setIdAtividadePeriodica(final Integer parm) {
        idAtividadePeriodica = parm;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer parm) {
        idContrato = parm;
    }

    public Integer getIdProcedimentoTecnico() {
        return idProcedimentoTecnico;
    }

    public void setIdProcedimentoTecnico(final Integer parm) {
        idProcedimentoTecnico = parm;
    }

    public Integer getIdGrupoAtvPeriodica() {
        return idGrupoAtvPeriodica;
    }

    public void setIdGrupoAtvPeriodica(final Integer parm) {
        idGrupoAtvPeriodica = parm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String parm) {
        descricao = parm;
    }

    public String getOrientacaoTecnica() {
        return orientacaoTecnica;
    }

    public void setOrientacaoTecnica(final String parm) {
        orientacaoTecnica = parm;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(final String parm) {
        criadoPor = parm;
    }

    public String getAlteradoPor() {
        return alteradoPor;
    }

    public void setAlteradoPor(final String parm) {
        alteradoPor = parm;
    }

    public java.sql.Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(final java.sql.Date parm) {
        dataCriacao = parm;
    }

    public java.sql.Date getDataUltAlteracao() {
        return dataUltAlteracao;
    }

    public void setDataUltAlteracao(final java.sql.Date parm) {
        dataUltAlteracao = parm;
    }

    public Collection getColItens() {
        return colItens;
    }

    public void setColItens(final Collection colItens) {
        this.colItens = colItens;
    }

    public Collection getColItensOS() {
        return colItensOS;
    }

    public void setColItensOS(final Collection colItensOS) {
        this.colItensOS = colItensOS;
    }

    public String getTituloAtividade() {
        return tituloAtividade;
    }

    public void setTituloAtividade(final String tituloAtividade) {
        this.tituloAtividade = tituloAtividade;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final java.sql.Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(final String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Integer getDuracaoEstimada() {
        return duracaoEstimada;
    }

    public void setDuracaoEstimada(final Integer duracaoEstimada) {
        this.duracaoEstimada = duracaoEstimada;
    }

    public Integer getIdRequisicaoMudanca() {
        return idRequisicaoMudanca;
    }

    public void setIdRequisicaoMudanca(final Integer idRequisicaoMudanca) {
        this.idRequisicaoMudanca = idRequisicaoMudanca;
    }

    public String getBlackout() {
        return blackout;
    }

    public void setBlackout(final String blackout) {
        this.blackout = blackout;
    }

    public String getIdentMudanca() {
        return identMudanca;
    }

    public void setIdentMudanca(final String identMudanca) {
        this.identMudanca = identMudanca;
    }

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(final Integer idProblema) {
        this.idProblema = idProblema;
    }

    public String getNomeTipoMudanca() {
        return nomeTipoMudanca;
    }

    public void setNomeTipoMudanca(final String nomeTipoMudanca) {
        this.nomeTipoMudanca = nomeTipoMudanca;
    }

    /**
     * @return the idRequisicaoLiberacao
     */
    public Integer getIdRequisicaoLiberacao() {
        return idRequisicaoLiberacao;
    }

    /**
     * @param idRequisicaoLiberacao
     *            the idRequisicaoLiberacao to set
     */
    public void setIdRequisicaoLiberacao(final Integer idRequisicaoLiberacao) {
        this.idRequisicaoLiberacao = idRequisicaoLiberacao;
    }

    public java.sql.Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final java.sql.Date dataFim) {
        this.dataFim = dataFim;
    }

}
