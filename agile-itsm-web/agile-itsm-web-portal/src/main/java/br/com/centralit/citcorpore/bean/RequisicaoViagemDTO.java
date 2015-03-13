package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.Collection;

public class RequisicaoViagemDTO extends SolicitacaoServicoDTO {

    public static final String AGUARDANDO_PLANEJAMENTO = "Aguardando Planejamento";
    public static final String EM_PLANEJAMENTO = "Em Planejamento";
    public static final String REJEITADA_PLANEJAMENTO = "Rejeitada Planejamento";
    public static final String AGUARDANDO_APROVACAO = "Aguardando Aprovacao";
    public static final String NAO_APROVADA = "Não Aprovada";
    public static final String AGUARDANDO_FINANCEIRO = "Aguardando Financeiro";
    public static final String AGUARDANDO_COMPRAS = "Aguardando Compras";
    public static final String AGUARDANDO_ADIANTAMENTO = "Aguardando Adiantamento";
    public static final String AGUARDANDO_PRESTACAOCONTAS = "Aguardando Prestação de Contas";
    public static final String EM_PRESTACAOCONTAS = "Em Prestação de Contas";
    public static final String EM_AUTORIZACAO = "Em Autorização";
    public static final String EM_CONFERENCIA = "Em Conferência";
    public static final String AGUARDANDO_CONFERENCIA = "Aguardando Conferência";
    public static final String AGUARDANDO_CORRECAO = "Aguardando Correção";
    public static final String REMARCADO = "Viagem Remarcada";
    public static final String FINALIZADA = "Finalizada";

    private static final long serialVersionUID = 1L;

    private Integer idCidadeOrigem;

    private Integer idCidadeDestino;

    private Integer idProjeto;

    private Integer idCentroCusto;

    private Integer idMotivoViagem;

    private Integer idAprovacao;

    private String descricaoMotivo;

    private Date dataInicioViagem;

    private Date dataInicioViagemAux;

    private Date dataFimViagem;

    private Date dataFimViagemAux;

    private String rejeitada;

    private Integer qtdeDias;

    private Double valorTotalSolicitado;

    private String estado;
    private String tarefaIniciada;

    private Collection<IntegranteViagemDTO> integranteViagem;

    private String integranteViagemSerialize;

    // Tarefa Autorização
    private String autorizado;

    private Integer idJustificativaAutorizacao;

    private String complemJustificativaAutorizacao;

    private String nomeCidadeOrigem;

    private String nomeCidadeDestino;

    // Para ControleFinanceiroViagem

    private String observacoes;
    private Integer idControleFinanceiroViagem;
    private Integer idMoeda;

    private Integer idEmpregado;

    private String nomeEmpregado;

    private String nomeNaoFuncionario;

    private String infoNaoFuncionario;

    private Integer idNaoFuncionario;

    private String nomeMoeda;

    private Integer idRespPrestacaoContasInteger;
    private String respPrestacaoContas;

    private Integer idIntegranteAux;

    private String justificativa;

    private String remarcacao;

    private Integer idItemTrabalho;

    private String cancelarRequisicao;

    private String stringIntegrantes;

    public Integer getIdCidadeOrigem() {
        return idCidadeOrigem;
    }

    public void setIdCidadeOrigem(final Integer idCidadeOrigem) {
        this.idCidadeOrigem = idCidadeOrigem;
    }

    public Integer getIdCidadeDestino() {
        return idCidadeDestino;
    }

    public void setIdCidadeDestino(final Integer idCidadeDestino) {
        this.idCidadeDestino = idCidadeDestino;
    }

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(final Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Integer getIdCentroCusto() {
        return idCentroCusto;
    }

    public void setIdCentroCusto(final Integer idCentroCusto) {
        this.idCentroCusto = idCentroCusto;
    }

    public Integer getIdMotivoViagem() {
        return idMotivoViagem;
    }

    public void setIdMotivoViagem(final Integer idMotivoViagem) {
        this.idMotivoViagem = idMotivoViagem;
    }

    public Integer getIdAprovacao() {
        return idAprovacao;
    }

    public void setIdAprovacao(final Integer idAprovacao) {
        this.idAprovacao = idAprovacao;
    }

    public String getDescricaoMotivo() {
        return descricaoMotivo;
    }

    public void setDescricaoMotivo(final String descricaoMotivo) {
        this.descricaoMotivo = descricaoMotivo;
    }

    public Date getDataInicioViagem() {
        return dataInicioViagem;
    }

    public void setDataInicioViagem(final Date dataInicioViagem) {
        this.dataInicioViagem = dataInicioViagem;
    }

    public Date getDataFimViagem() {
        return dataFimViagem;
    }

    public void setDataFimViagem(final Date dataFimViagem) {
        this.dataFimViagem = dataFimViagem;
    }

    public String getRejeitada() {
        return rejeitada;
    }

    public void setRejeitada(final String rejeitada) {
        this.rejeitada = rejeitada;
    }

    public Integer getQtdeDias() {
        return qtdeDias;
    }

    public void setQtdeDias(final Integer qtdeDias) {
        this.qtdeDias = qtdeDias;
    }

    public Double getValorTotalSolicitado() {
        return valorTotalSolicitado;
    }

    public void setValorTotalSolicitado(final Double valorTotalSolicitado) {
        this.valorTotalSolicitado = valorTotalSolicitado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(final String estado) {
        this.estado = estado;
    }

    public String getTarefaIniciada() {
        return tarefaIniciada;
    }

    public void setTarefaIniciada(final String tarefaIniciada) {
        this.tarefaIniciada = tarefaIniciada;
    }

    public Collection<IntegranteViagemDTO> getIntegranteViagem() {
        return integranteViagem;
    }

    public void setIntegranteViagem(final Collection<IntegranteViagemDTO> integranteViagem) {
        this.integranteViagem = integranteViagem;
    }

    public String getIntegranteViagemSerialize() {
        return integranteViagemSerialize;
    }

    public void setIntegranteViagemSerialize(final String integranteViagemSerialize) {
        this.integranteViagemSerialize = integranteViagemSerialize;
    }

    public String getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(final String autorizado) {
        this.autorizado = autorizado;
    }

    public Integer getIdJustificativaAutorizacao() {
        return idJustificativaAutorizacao;
    }

    public void setIdJustificativaAutorizacao(final Integer idJustificativaAutorizacao) {
        this.idJustificativaAutorizacao = idJustificativaAutorizacao;
    }

    public String getComplemJustificativaAutorizacao() {
        return complemJustificativaAutorizacao;
    }

    public void setComplemJustificativaAutorizacao(final String complemJustificativaAutorizacao) {
        this.complemJustificativaAutorizacao = complemJustificativaAutorizacao;
        this.setJustificativa(complemJustificativaAutorizacao);
    }

    public String getNomeCidadeOrigem() {
        return nomeCidadeOrigem;
    }

    public void setNomeCidadeOrigem(final String nomeCidadeOrigem) {
        this.nomeCidadeOrigem = nomeCidadeOrigem;
    }

    public String getNomeCidadeDestino() {
        return nomeCidadeDestino;
    }

    public void setNomeCidadeDestino(final String nomeCidadeDestino) {
        this.nomeCidadeDestino = nomeCidadeDestino;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(final String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getIdControleFinanceiroViagem() {
        return idControleFinanceiroViagem;
    }

    public void setIdControleFinanceiroViagem(final Integer idControleFinanceiroViagem) {
        this.idControleFinanceiroViagem = idControleFinanceiroViagem;
    }

    public Integer getIdMoeda() {
        return idMoeda;
    }

    public void setIdMoeda(final Integer idMoeda) {
        this.idMoeda = idMoeda;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    public String getNomeMoeda() {
        return nomeMoeda;
    }

    public void setNomeMoeda(final String nomeMoeda) {
        this.nomeMoeda = nomeMoeda;
    }

    public Integer getIdRespPrestacaoContasInteger() {
        return idRespPrestacaoContasInteger;
    }

    public void setIdRespPrestacaoContasInteger(final Integer idRespPrestacaoContasInteger) {
        this.idRespPrestacaoContasInteger = idRespPrestacaoContasInteger;
    }

    public String getRespPrestacaoContas() {
        return respPrestacaoContas;
    }

    public void setRespPrestacaoContas(final String respPrestacaoContas) {
        this.respPrestacaoContas = respPrestacaoContas;
    }

    public Integer getIdIntegranteAux() {
        return idIntegranteAux;
    }

    public void setIdIntegranteAux(final Integer idIntegranteAux) {
        this.idIntegranteAux = idIntegranteAux;
    }

    public static String getAguardandoPlanejamento() {
        return AGUARDANDO_PLANEJAMENTO;
    }

    public static String getRejeitadaPlanejamento() {
        return REJEITADA_PLANEJAMENTO;
    }

    public static String getAguardandoAprovacao() {
        return AGUARDANDO_APROVACAO;
    }

    public static String getNaoAprovada() {
        return NAO_APROVADA;
    }

    public static String getAguardandoFinanceiro() {
        return AGUARDANDO_FINANCEIRO;
    }

    public static String getAguardandoPrestacaocontas() {
        return AGUARDANDO_PRESTACAOCONTAS;
    }

    public static String getAguardandoConferencia() {
        return AGUARDANDO_CONFERENCIA;
    }

    public static String getAguardandoCorrecao() {
        return AGUARDANDO_CORRECAO;
    }

    public static String getFinalizada() {
        return FINALIZADA;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(final String justificativa) {
        this.justificativa = justificativa;
    }

    public String getRemarcacao() {
        return remarcacao;
    }

    public void setRemarcacao(final String remarcacao) {
        this.remarcacao = remarcacao;
    }

    public static String getRemarcado() {
        return REMARCADO;
    }

    /**
     * @return the idItemTrabalho
     */
    public Integer getIdItemTrabalho() {
        return idItemTrabalho;
    }

    /**
     * @param idItemTrabalho
     *            the idItemTrabalho to set
     */
    public void setIdItemTrabalho(final Integer idItemTrabalho) {
        this.idItemTrabalho = idItemTrabalho;
    }

    /**
     * @return the cancelarRequisicao
     */
    public String getCancelarRequisicao() {
        return cancelarRequisicao;
    }

    /**
     * @param cancelarRequisicao
     *            the cancelarRequisicao to set
     */
    public void setCancelarRequisicao(final String cancelarRequisicao) {
        this.cancelarRequisicao = cancelarRequisicao;
    }

    public Integer getIdNaoFuncionario() {
        return idNaoFuncionario;
    }

    public void setIdNaoFuncionario(final Integer idNaoFuncionario) {
        this.idNaoFuncionario = idNaoFuncionario;
    }

    public Date getDataInicioViagemAux() {
        return dataInicioViagemAux;
    }

    public void setDataInicioViagemAux(final Date dataInicioViagemAux) {
        this.dataInicioViagemAux = dataInicioViagemAux;
    }

    public Date getDataFimViagemAux() {
        return dataFimViagemAux;
    }

    public void setDataFimViagemAux(final Date dataFimViagemAux) {
        this.dataFimViagemAux = dataFimViagemAux;
    }

    public String getNomeEmpregado() {
        return nomeEmpregado;
    }

    public void setNomeEmpregado(final String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

    public String getStringIntegrantes() {
        return stringIntegrantes;
    }

    public void setStringIntegrantes(final String stringIntegrantes) {
        this.stringIntegrantes = stringIntegrantes;
    }

    public String getNomeNaoFuncionario() {
        return nomeNaoFuncionario;
    }

    public void setNomeNaoFuncionario(final String nomeNaoFuncionario) {
        this.nomeNaoFuncionario = nomeNaoFuncionario;
    }

    public String getInfoNaoFuncionario() {
        return infoNaoFuncionario;
    }

    public void setInfoNaoFuncionario(final String infoNaoFuncionario) {
        this.infoNaoFuncionario = infoNaoFuncionario;
    }

    public static String getAguardandoCompras() {
        return AGUARDANDO_COMPRAS;
    }

    public static String getAguardandoAdiantamento() {
        return AGUARDANDO_ADIANTAMENTO;
    }

    public static String getEmPrestacaocontas() {
        return EM_PRESTACAOCONTAS;
    }

    public static String getEmPlanejamento() {
        return EM_PLANEJAMENTO;
    }

}
