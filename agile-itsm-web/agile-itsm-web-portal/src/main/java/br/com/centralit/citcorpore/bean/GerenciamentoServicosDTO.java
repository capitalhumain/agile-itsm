package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class GerenciamentoServicosDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idFluxo;
    private Integer idTarefa;
    private Integer idSolicitacao;
    private String acaoFluxo;
    private String erroGrid;
    private String numeroContratoSel;
    private String idSolicitacaoSel;
    private String idTipoDemandaServico;
    private String responsavelAtual;
    private String nomeCampoOrdenacao;
    private String ordenacaoAsc;
    private String descricaoSolicitacao;
    private String grupoAtual;
    private String solicitanteUnidade;
    private Integer quantidadeAtrasadas;
    private Integer quantidadeTotal;

    private Integer idContrato;
    private Integer idSolicitante;
    private Integer idResponsavelAtual;
    private Integer idGrupoAtual;
    private Integer idTipo;
    private Integer itensPorPagina;
    private Integer paginaSelecionada;
    private Integer totalPaginas;
    private Integer tipoLista;
    private String palavraChave;
    private String situacao;
    private String tarefaAtual;

    private String descricaoSolicitacaoVisualizar;
    private Integer idSolicitacaoServicoDescricao;

    private String TipoVisualizacao;
    private String situacaoSla;
    private String ordenarPor;
    private String direcaoOrdenacao;

    public String getTipoVisualizacao() {
        return TipoVisualizacao;
    }

    public void setTipoVisualizacao(final String tipoVisualizacao) {
        TipoVisualizacao = tipoVisualizacao;
    }

    public String getSituacaoSla() {
        return situacaoSla;
    }

    public void setSituacaoSla(final String situacaoSla) {
        this.situacaoSla = situacaoSla;
    }

    public String getOrdenarPor() {
        return ordenarPor;
    }

    public void setOrdenarPor(final String ordenarPor) {
        this.ordenarPor = ordenarPor;
    }

    public Integer getIdFluxo() {
        return idFluxo;
    }

    public void setIdFluxo(final Integer idFluxo) {
        this.idFluxo = idFluxo;
    }

    public Integer getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(final Integer idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getAcaoFluxo() {
        return acaoFluxo;
    }

    public void setAcaoFluxo(final String acaoFluxo) {
        this.acaoFluxo = acaoFluxo;
    }

    public String getNumeroContratoSel() {
        return numeroContratoSel;
    }

    public void setNumeroContratoSel(final String numeroContratoSel) {
        this.numeroContratoSel = numeroContratoSel;
    }

    public String getIdSolicitacaoSel() {
        return idSolicitacaoSel;
    }

    public void setIdSolicitacaoSel(final String idSolicitacaoSel) {
        this.idSolicitacaoSel = idSolicitacaoSel;
    }

    public Integer getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(final Integer idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public String getResponsavelAtual() {
        return responsavelAtual;
    }

    public void setResponsavelAtual(final String responsavelAtual) {
        this.responsavelAtual = responsavelAtual;
    }

    public String getNomeCampoOrdenacao() {
        return nomeCampoOrdenacao;
    }

    public void setNomeCampoOrdenacao(final String nomeCampoOrdenacao) {
        this.nomeCampoOrdenacao = nomeCampoOrdenacao;
    }

    public String getOrdenacaoAsc() {
        return ordenacaoAsc;
    }

    public void setOrdenacaoAsc(final String ordenacaoAsc) {
        this.ordenacaoAsc = ordenacaoAsc;
    }

    public String getDescricaoSolicitacao() {
        return descricaoSolicitacao;
    }

    public void setDescricaoSolicitacao(final String descricaoSolicitacao) {
        this.descricaoSolicitacao = descricaoSolicitacao;
    }

    public String getErroGrid() {
        return erroGrid;
    }

    public void setErroGrid(final String erroGrid) {
        this.erroGrid = erroGrid;
    }

    public String getIdTipoDemandaServico() {
        return idTipoDemandaServico;
    }

    public void setIdTipoDemandaServico(final String idTipoDemandaServico) {
        this.idTipoDemandaServico = idTipoDemandaServico;
    }

    public String getGrupoAtual() {
        return grupoAtual;
    }

    public void setGrupoAtual(final String grupoAtual) {
        this.grupoAtual = grupoAtual;
    }

    public String getSolicitanteUnidade() {
        return solicitanteUnidade;
    }

    public void setSolicitanteUnidade(final String solicitanteUnidade) {
        this.solicitanteUnidade = solicitanteUnidade;
    }

    public Integer getQuantidadeAtrasadas() {
        return quantidadeAtrasadas;
    }

    public void setQuantidadeAtrasadas(final Integer quantidadeAtrasadas) {
        this.quantidadeAtrasadas = quantidadeAtrasadas;
    }

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(final Integer quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public Integer getPaginaSelecionada() {
        return paginaSelecionada;
    }

    public void setPaginaSelecionada(final Integer paginaSelecionada) {
        this.paginaSelecionada = paginaSelecionada;
    }

    public Integer getItensPorPagina() {
        return itensPorPagina;
    }

    public void setItensPorPagina(final Integer itensPorPagina) {
        this.itensPorPagina = itensPorPagina;
    }

    public Integer getTipoLista() {
        return tipoLista;
    }

    public void setTipoLista(final Integer tipoLista) {
        this.tipoLista = tipoLista;
    }

    public Integer getIdResponsavelAtual() {
        return idResponsavelAtual;
    }

    public void setIdResponsavelAtual(final Integer idResponsavelAtual) {
        this.idResponsavelAtual = idResponsavelAtual;
    }

    public Integer getIdGrupoAtual() {
        return idGrupoAtual;
    }

    public void setIdGrupoAtual(final Integer idGrupoAtual) {
        this.idGrupoAtual = idGrupoAtual;
    }

    public String getPalavraChave() {
        return palavraChave;
    }

    public void setPalavraChave(final String palavraChave) {
        this.palavraChave = palavraChave;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public Integer getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(final Integer idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(final Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(final Integer totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public String getTarefaAtual() {
        return tarefaAtual;
    }

    public void setTarefaAtual(final String tarefaAtual) {
        this.tarefaAtual = tarefaAtual;
    }

    public String getDescricaoSolicitacaoVisualizar() {
        return descricaoSolicitacaoVisualizar;
    }

    public void setDescricaoSolicitacaoVisualizar(final String descricaoSolicitacaoVisualizar) {
        this.descricaoSolicitacaoVisualizar = descricaoSolicitacaoVisualizar;
    }

    public Integer getIdSolicitacaoServicoDescricao() {
        return idSolicitacaoServicoDescricao;
    }

    public void setIdSolicitacaoServicoDescricao(final Integer idSolicitacaoServicoDescricao) {
        this.idSolicitacaoServicoDescricao = idSolicitacaoServicoDescricao;
    }

    public String getDirecaoOrdenacao() {
        return direcaoOrdenacao;
    }

    public void setDirecaoOrdenacao(final String direcaoOrdenacao) {
        this.direcaoOrdenacao = direcaoOrdenacao;
    }

}
