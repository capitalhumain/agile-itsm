package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class ControleRendimentoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idControleRendimento;
    private Integer idGrupo;
    private Integer idPessoa;
    private String mesApuracao;
    private String anoApuracao;
    private Timestamp dataHoraExecucao;
    private String aprovado;
    private Integer qtdPontosPositivos;
    private Integer qtdPontosNegativos;
    private Integer qtdSolicitacoes;
    private Double qtdPontos;
    private String mediaRelativa;

    // campos auxiliares para o relatório
    private String nomeGrupo;
    private String qtdItensEntreguesNoPrazo;
    private String qtdItensAtrasados;
    private String numeroSolicitacoes;
    private String mediaAtraso;
    private String qtdItensSuspensos;

    public Integer getIdControleRendimento() {
        return idControleRendimento;
    }

    public void setIdControleRendimento(final Integer idControleRendimento) {
        this.idControleRendimento = idControleRendimento;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(final Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(final Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getMesApuracao() {
        return mesApuracao;
    }

    public void setMesApuracao(final String mesApuracao) {
        this.mesApuracao = mesApuracao;
    }

    public String getAnoApuracao() {
        return anoApuracao;
    }

    public void setAnoApuracao(final String anoApuracao) {
        this.anoApuracao = anoApuracao;
    }

    public Timestamp getDataHoraExecucao() {
        return dataHoraExecucao;
    }

    public void setDataHoraExecucao(final Timestamp dataHoraExecucao) {
        this.dataHoraExecucao = dataHoraExecucao;
    }

    public String getAprovado() {
        return aprovado;
    }

    public void setAprovado(final String aprovado) {
        this.aprovado = aprovado;
    }

    public Integer getQtdPontosPositivos() {
        return qtdPontosPositivos;
    }

    public void setQtdPontosPositivos(final Integer qtdPontosPositivos) {
        this.qtdPontosPositivos = qtdPontosPositivos;
    }

    public Integer getQtdPontosNegativos() {
        return qtdPontosNegativos;
    }

    public void setQtdPontosNegativos(final Integer qtdPontosNegativos) {
        this.qtdPontosNegativos = qtdPontosNegativos;
    }

    public Double getQtdPontos() {
        return qtdPontos;
    }

    public void setQtdPontos(final Double qtdPontos) {
        this.qtdPontos = qtdPontos;
    }

    public String getMediaRelativa() {
        return mediaRelativa;
    }

    public void setMediaRelativa(final String mediaRelativa) {
        this.mediaRelativa = mediaRelativa;
    }

    public Integer getQtdSolicitacoes() {
        return qtdSolicitacoes;
    }

    public void setQtdSolicitacoes(final Integer qtdSolicitacoes) {
        this.qtdSolicitacoes = qtdSolicitacoes;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(final String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public String getQtdItensEntreguesNoPrazo() {
        return qtdItensEntreguesNoPrazo;
    }

    public void setQtdItensEntreguesNoPrazo(final String qtdItensEntreguesNoPrazo) {
        this.qtdItensEntreguesNoPrazo = qtdItensEntreguesNoPrazo;
    }

    public String getQtdItensAtrasados() {
        return qtdItensAtrasados;
    }

    public void setQtdItensAtrasados(final String qtdItensAtrasados) {
        this.qtdItensAtrasados = qtdItensAtrasados;
    }

    public String getNumeroSolicitacoes() {
        return numeroSolicitacoes;
    }

    public void setNumeroSolicitacoes(final String numeroSolicitacoes) {
        this.numeroSolicitacoes = numeroSolicitacoes;
    }

    public String getMediaAtraso() {
        return mediaAtraso;
    }

    public void setMediaAtraso(final String mediaAtraso) {
        this.mediaAtraso = mediaAtraso;
    }

    public String getQtdItensSuspensos() {
        return qtdItensSuspensos;
    }

    public void setQtdItensSuspensos(final String qtdItensSuspensos) {
        this.qtdItensSuspensos = qtdItensSuspensos;
    }

}
