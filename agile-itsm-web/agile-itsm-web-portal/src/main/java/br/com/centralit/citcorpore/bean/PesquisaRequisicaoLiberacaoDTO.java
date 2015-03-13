package br.com.centralit.citcorpore.bean;

import java.sql.Date;

public class PesquisaRequisicaoLiberacaoDTO {

    private Date dataInicio;

    private Date dataFim;

    private Date dataInicioFechamento;

    private Date dataFimFechamento;

    private Integer idRequisicaoLiberacaoPesquisa;

    private Integer idRequisicaoLiberacao;

    private Integer idContrato;

    private Integer idItemConfiguracao;

    private Integer idSolicitante;

    private Integer idResponsavel;

    private Integer idUnidade;

    private Integer idServico;

    private String situacao;

    private Integer idPrioridade;

    private Integer idOrigem;

    private Integer idFaseAtual;

    private String ordenacao;

    private Integer idGrupoAtual;

    private String palavraChave;

    private Integer idTipoDemandaServico;

    private String exibirCampoDescricao;

    private String nomeItemConfiguracao;

    private String nomeSolicitante;

    private String nomeTipoDemandaServico;

    private String faseAtual;

    private String prioridade;

    private String grupoAtual;

    private String origem;

    private Integer idContato;

    private String nomeContato;

    /**
     * @return the nomeContato
     */
    public String getNomeContato() {
        return nomeContato;
    }

    /**
     * @param nomeContato
     */
    public void setNomeContato(final String nomeContato) {
        this.nomeContato = nomeContato;
    }

    /**
     * @return the idContato
     */
    public Integer getIdContato() {
        return idContato;
    }

    /**
     * @param idContato
     */
    public void setIdContato(final Integer idContato) {
        this.idContato = idContato;
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio
     *            the dataInicio to set
     */
    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim
     *            the dataFim to set
     */
    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the dataInicioFechamento
     */
    public Date getDataInicioFechamento() {
        return dataInicioFechamento;
    }

    /**
     * @param dataInicioFechamento
     *            the dataInicioFechamento to set
     */
    public void setDataInicioFechamento(final Date dataInicioFechamento) {
        this.dataInicioFechamento = dataInicioFechamento;
    }

    /**
     * @return the dataFimFechamento
     */
    public Date getDataFimFechamento() {
        return dataFimFechamento;
    }

    /**
     * @param dataFimFechamento
     *            the dataFimFechamento to set
     */
    public void setDataFimFechamento(final Date dataFimFechamento) {
        this.dataFimFechamento = dataFimFechamento;
    }

    /**
     * @return the idSolicitacaoServicoPesquisa
     */
    public Integer getIdRequisicaoLiberacaoPesquisa() {
        return idRequisicaoLiberacaoPesquisa;
    }

    /**
     * @param idRequisicaoLiberacaoPesquisa
     *            the idSolicitacaoServicoPesquisa to set
     */
    public void setIdRequisicaoLiberacaoPesquisa(final Integer idRequisicaoLiberacaoPesquisa) {
        this.idRequisicaoLiberacaoPesquisa = idRequisicaoLiberacaoPesquisa;
    }

    /**
     * @return the idContrato
     */
    public Integer getIdContrato() {
        return idContrato;
    }

    /**
     * @param idContrato
     *            the idContrato to set
     */
    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    /**
     * @return the idItemConfiguracao
     */
    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    /**
     * @param idItemConfiguracao
     *            the idItemConfiguracao to set
     */
    public void setIdItemConfiguracao(final Integer idItemConfiguracao) {
        this.idItemConfiguracao = idItemConfiguracao;
    }

    /**
     * @return the idSolicitante
     */
    public Integer getIdSolicitante() {
        return idSolicitante;
    }

    /**
     * @param idSolicitante
     *            the idSolicitante to set
     */
    public void setIdSolicitante(final Integer idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    /**
     * @return the idResponsavel
     */
    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    /**
     * @param idResponsavel
     *            the idResponsavel to set
     */
    public void setIdResponsavel(final Integer idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    /**
     * @return the idUnidade
     */
    public Integer getIdUnidade() {
        return idUnidade;
    }

    /**
     * @param idUnidade
     *            the idUnidade to set
     */
    public void setIdUnidade(final Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    /**
     * @return the idServico
     */
    public Integer getIdServico() {
        return idServico;
    }

    /**
     * @param idServico
     *            the idServico to set
     */
    public void setIdServico(final Integer idServico) {
        this.idServico = idServico;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao
     *            the situacao to set
     */
    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the idPrioridade
     */
    public Integer getIdPrioridade() {
        return idPrioridade;
    }

    /**
     * @param idPrioridade
     *            the idPrioridade to set
     */
    public void setIdPrioridade(final Integer idPrioridade) {
        this.idPrioridade = idPrioridade;
    }

    /**
     * @return the idOrigem
     */
    public Integer getIdOrigem() {
        return idOrigem;
    }

    /**
     * @param idOrigem
     *            the idOrigem to set
     */
    public void setIdOrigem(final Integer idOrigem) {
        this.idOrigem = idOrigem;
    }

    /**
     * @return the idFaseAtual
     */
    public Integer getIdFaseAtual() {
        return idFaseAtual;
    }

    /**
     * @param idFaseAtual
     *            the idFaseAtual to set
     */
    public void setIdFaseAtual(final Integer idFaseAtual) {
        this.idFaseAtual = idFaseAtual;
    }

    /**
     * @return the ordenacao
     */
    public String getOrdenacao() {
        return ordenacao;
    }

    /**
     * @param ordenacao
     *            the ordenacao to set
     */
    public void setOrdenacao(final String ordenacao) {
        this.ordenacao = ordenacao;
    }

    /**
     * @return the idGrupoAtual
     */
    public Integer getIdGrupoAtual() {
        return idGrupoAtual;
    }

    /**
     * @param idGrupoAtual
     *            the idGrupoAtual to set
     */
    public void setIdGrupoAtual(final Integer idGrupoAtual) {
        this.idGrupoAtual = idGrupoAtual;
    }

    /**
     * @return the palavraChave
     */
    public String getPalavraChave() {
        return palavraChave;
    }

    /**
     * @param palavraChave
     *            the palavraChave to set
     */
    public void setPalavraChave(final String palavraChave) {
        this.palavraChave = palavraChave;
    }

    /**
     * @return the idTipoDemandaServico
     */
    public Integer getIdTipoDemandaServico() {
        return idTipoDemandaServico;
    }

    /**
     * @param idTipoDemandaServico
     *            the idTipoDemandaServico to set
     */
    public void setIdTipoDemandaServico(final Integer idTipoDemandaServico) {
        this.idTipoDemandaServico = idTipoDemandaServico;
    }

    /**
     * @return the exibirCampoDescricao
     */
    public String getExibirCampoDescricao() {
        return exibirCampoDescricao;
    }

    /**
     * @param exibirCampoDescricao
     *            the exibirCampoDescricao to set
     */
    public void setExibirCampoDescricao(final String exibirCampoDescricao) {
        this.exibirCampoDescricao = exibirCampoDescricao;
    }

    public String getNomeItemConfiguracao() {
        return nomeItemConfiguracao;
    }

    public void setNomeItemConfiguracao(final String nomeItemConfiguracao) {
        this.nomeItemConfiguracao = nomeItemConfiguracao;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(final String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public String getNomeTipoDemandaServico() {
        return nomeTipoDemandaServico;
    }

    public void setNomeTipoDemandaServico(final String nomeTipoDemandaServico) {
        this.nomeTipoDemandaServico = nomeTipoDemandaServico;
    }

    public String getFaseAtual() {
        return faseAtual;
    }

    public void setFaseAtual(final String faseAtual) {
        this.faseAtual = faseAtual;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(final String prioridade) {
        this.prioridade = prioridade;
    }

    public String getGrupoAtual() {
        return grupoAtual;
    }

    public void setGrupoAtual(final String grupoAtual) {
        this.grupoAtual = grupoAtual;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(final String origem) {
        this.origem = origem;
    }

    public Integer getIdRequisicaoLiberacao() {
        return idRequisicaoLiberacao;
    }

    public void setIdRequisicaoLiberacao(final Integer idRequisicaoLiberacao) {
        this.idRequisicaoLiberacao = idRequisicaoLiberacao;
    }

}
