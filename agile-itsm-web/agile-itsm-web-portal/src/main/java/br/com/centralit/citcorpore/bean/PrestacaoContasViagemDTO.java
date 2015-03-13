package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class PrestacaoContasViagemDTO extends BaseEntity {

    public static final String APROVADA = "Aprovada";
    public static final String NAO_APROVADA = "Não Aprovada";
    public static final String AGUARDANDO_CONFERENCIA = "Aguardando Conferência";
    public static final String EM_CONFERENCIA = "Em Conferência";
    public static final String EM_CORRECAO = "Em Correção";
    public static final String AGUARDANDO_CORRECAO = "Aguardando Correção";

    private Integer idPrestacaoContasViagem;
    private Integer idResponsavel;
    private Integer idAprovacao;
    private Integer idSolicitacaoServico;
    private Integer idEmpregado;
    private Timestamp dataHora;
    private Date data;
    private String situacao;

    private String listItens;
    private String itensPrestacaoContasViagemSerialize;

    private String complemJustificativaAutorizacao;
    private Integer idJustificativaAutorizacao;
    private String aprovado;

    private Integer idItemTrabalho;
    private Integer idTarefa;
    private Integer idContrato;

    private String corrigir;
    private String integranteSerialize;
    private String correcao;
    private String remarcacao;
    private Integer idRespPrestacaoContas;

    private IntegranteViagemDTO integranteViagemDto;

    // Define se o Integrante da Viagem é um funcionario(S) ou não(N)
    private String integranteFuncionario;

    private Integer idIntegrante;

    private String nomeNaoFuncionario;

    Collection<ItemPrestacaoContasViagemDTO> listaItemPrestacaoContasViagemDTO;

    // Controle prestacao de contas
    private Double valorDiferenca;
    private String valorDiferencaAux;
    private Double totalLancamentos;
    private String totalLancamentosAux;
    private Double totalPrestacaoContas;
    private String totalPrestacaoContasAux;
    private Double valor;
    private String valorAux;

    public Collection<ItemPrestacaoContasViagemDTO> getListaItemPrestacaoContasViagemDTO() {
        return listaItemPrestacaoContasViagemDTO;
    }

    public void setListaItemPrestacaoContasViagemDTO(final Collection<ItemPrestacaoContasViagemDTO> listaItemPrestacaoContasViagemDTO) {
        this.listaItemPrestacaoContasViagemDTO = listaItemPrestacaoContasViagemDTO;
    }

    private static final long serialVersionUID = 1L;

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public Integer getIdAprovacao() {
        return idAprovacao;
    }

    public void setIdAprovacao(final Integer idAprovacao) {
        this.idAprovacao = idAprovacao;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(final Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public String getListItens() {
        return listItens;
    }

    public void setListItens(final String listItens) {
        this.listItens = listItens;
    }

    public Integer getIdPrestacaoContasViagem() {
        return idPrestacaoContasViagem;
    }

    public void setIdPrestacaoContasViagem(final Integer idPrestacaoContasViagem) {
        this.idPrestacaoContasViagem = idPrestacaoContasViagem;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public String getItensPrestacaoContasViagemSerialize() {
        return itensPrestacaoContasViagemSerialize;
    }

    public void setItensPrestacaoContasViagemSerialize(final String itensPrestacaoContasViagemSerialize) {
        this.itensPrestacaoContasViagemSerialize = itensPrestacaoContasViagemSerialize;
    }

    public String getComplemJustificativaAutorizacao() {
        return complemJustificativaAutorizacao;
    }

    public void setComplemJustificativaAutorizacao(final String complemJustificativaAutorizacao) {
        this.complemJustificativaAutorizacao = complemJustificativaAutorizacao;
    }

    public Integer getIdJustificativaAutorizacao() {
        return idJustificativaAutorizacao;
    }

    public void setIdJustificativaAutorizacao(final Integer idJustificativaAutorizacao) {
        this.idJustificativaAutorizacao = idJustificativaAutorizacao;
    }

    public String getAprovado() {
        return aprovado;
    }

    public void setAprovado(final String aprovado) {
        this.aprovado = aprovado;
    }

    public Integer getIdItemTrabalho() {
        return idItemTrabalho;
    }

    public void setIdItemTrabalho(final Integer idItemTrabalho) {
        this.idItemTrabalho = idItemTrabalho;
    }

    public Integer getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(final Integer idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getCorrigir() {
        return corrigir;
    }

    public void setCorrigir(final String corrigir) {
        this.corrigir = corrigir;
    }

    public Integer getIdRespPrestacaoContas() {
        return idRespPrestacaoContas;
    }

    public void setIdRespPrestacaoContas(final Integer idRespPrestacaoContas) {
        this.idRespPrestacaoContas = idRespPrestacaoContas;
    }

    public IntegranteViagemDTO getIntegranteViagemDto() {
        return integranteViagemDto;
    }

    public void setIntegranteViagemDto(final IntegranteViagemDTO integranteViagemDto) {
        this.integranteViagemDto = integranteViagemDto;
    }

    public String getIntegranteSerialize() {
        return integranteSerialize;
    }

    public void setIntegranteSerialize(final String integranteSerialize) {
        this.integranteSerialize = integranteSerialize;
    }

    public String getCorrecao() {
        return correcao;
    }

    public void setCorrecao(final String correcao) {
        this.correcao = correcao;
    }

    public String getIntegranteFuncionario() {
        return integranteFuncionario;
    }

    public void setIntegranteFuncionario(final String integranteFuncionario) {
        this.integranteFuncionario = integranteFuncionario;
    }

    public String getNomeNaoFuncionario() {
        return nomeNaoFuncionario;
    }

    public void setNomeNaoFuncionario(final String nomeNaoFuncionario) {
        this.nomeNaoFuncionario = nomeNaoFuncionario;
    }

    public static String getAprovada() {
        return APROVADA;
    }

    public static String getNaoAprovada() {
        return NAO_APROVADA;
    }

    public static String getAguardandoConferencia() {
        return AGUARDANDO_CONFERENCIA;
    }

    public static String getEmConferencia() {
        return EM_CONFERENCIA;
    }

    public static String getEmCorrecao() {
        return EM_CORRECAO;
    }

    public static String getAguardandoCorrecao() {
        return AGUARDANDO_CORRECAO;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getRemarcacao() {
        return remarcacao;
    }

    public void setRemarcacao(final String remarcacao) {
        this.remarcacao = remarcacao;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Double getValorDiferenca() {
        return valorDiferenca;
    }

    public void setValorDiferenca(final Double valorDiferenca) {
        this.valorDiferenca = valorDiferenca;
        valorDiferencaAux = valorDiferenca.toString();
    }

    public String getValorDiferencaAux() {
        return valorDiferencaAux;
    }

    public void setValorDiferencaAux(final String valorDiferencaAux) {
        this.valorDiferencaAux = valorDiferencaAux;
    }

    public Double getTotalLancamentos() {
        return totalLancamentos;
    }

    public void setTotalLancamentos(final Double totalLancamentos) {
        this.totalLancamentos = totalLancamentos;
        totalLancamentosAux = totalLancamentos.toString();
    }

    public String getTotalLancamentosAux() {
        return totalLancamentosAux;
    }

    public void setTotalLancamentosAux(final String totalLancamentosAux) {
        this.totalLancamentosAux = totalLancamentosAux;
    }

    public Double getTotalPrestacaoContas() {
        return totalPrestacaoContas;
    }

    public void setTotalPrestacaoContas(final Double totalPrestacaoContas) {
        this.totalPrestacaoContas = totalPrestacaoContas;
        totalPrestacaoContasAux = totalPrestacaoContas.toString();
    }

    public String getTotalPrestacaoContasAux() {
        return totalPrestacaoContasAux;
    }

    public void setTotalPrestacaoContasAux(final String totalPrestacaoContasAux) {
        this.totalPrestacaoContasAux = totalPrestacaoContasAux;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(final Double valor) {
        this.valor = valor;
        if (valor != null) {
            valorAux = valor.toString();
        }
    }

    public String getValorAux() {
        return valorAux;
    }

    public void setValorAux(final String valorAux) {
        this.valorAux = valorAux;
    }

    public Integer getIdIntegrante() {
        return idIntegrante;
    }

    public void setIdIntegrante(final Integer idIntegrante) {
        this.idIntegrante = idIntegrante;
    }

    public Date getData() {
        return data;
    }

    public void setData(final Date data) {
        this.data = data;
    }

}
