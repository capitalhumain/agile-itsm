package br.com.centralit.citcorpore.bean;

//Criado por Bruno.Aquino

import java.sql.Date;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioEficaciaNasEstimativasDasRequisicaoDeServicoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Date dataInicio;
    private Date dataFim;
    private String formatoArquivoRelatorio;
    private Integer idContrato;
    private String contrato;
    private String funcionario;
    private String grupo;
    private String listaUsuarios;
    private String checkMostrarRequisicoes;
    private String checkMostrarIncidentes;
    private String listaGrupoUnidade;
    private Collection<SolicitacaoServicoDTO> listaSolicitacoesUsuario;
    private Collection<RelatorioEficaciaNasEstimativasDasRequisicaoDeServicoDTO> listaGeral;
    private int qtdeExecutada;
    private int qtdReestimada;
    private int numeroSolicitacao;
    private String NomeServico;
    private double totalGrupoReestimada;
    private double totalGrupoExecutadas;
    private int totalPorExecutante;
    private int totalPorExecutanteReestimada;
    private String totalPorExecutanteReestimadaPorcentagem;
    private String totalPorExecutantePorcentagem;
    private String totalPorServicoPorcentagem;
    private String selecionarGrupoUnidade;
    private String situacao;

    public int getQtdeExecutada() {
        return qtdeExecutada;
    }

    public void setQtdeExecutada(final int qtdeExecutada) {
        this.qtdeExecutada = qtdeExecutada;
    }

    public int getQtdReestimada() {
        return qtdReestimada;
    }

    public void setQtdReestimada(final int qtdReestimada) {
        this.qtdReestimada = qtdReestimada;
    }

    public String getListaGrupoUnidade() {
        return listaGrupoUnidade;
    }

    public void setListaGrupoUnidade(final String listaGrupoUnidade) {
        this.listaGrupoUnidade = listaGrupoUnidade;
    }

    public String getSelecionarGrupoUnidade() {
        return selecionarGrupoUnidade;
    }

    public void setSelecionarGrupoUnidade(final String selecionarGrupoUnidade) {
        this.selecionarGrupoUnidade = selecionarGrupoUnidade;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public double getTotalGrupoReestimada() {
        return totalGrupoReestimada;
    }

    public void setTotalGrupoReestimada(final double totalGrupoReestimada) {
        this.totalGrupoReestimada = totalGrupoReestimada;
    }

    public int getTotalPorExecutanteReestimada() {
        return totalPorExecutanteReestimada;
    }

    public void setTotalPorExecutanteReestimada(final int totalPorExecutanteReestimada) {
        this.totalPorExecutanteReestimada = totalPorExecutanteReestimada;
    }

    public String getTotalPorExecutanteReestimadaPorcentagem() {
        return totalPorExecutanteReestimadaPorcentagem;
    }

    public void setTotalPorExecutanteReestimadaPorcentagem(final String totalPorExecutanteReestimadaPorcentagem) {
        this.totalPorExecutanteReestimadaPorcentagem = totalPorExecutanteReestimadaPorcentagem;
    }

    public String getTotalPorServicoPorcentagem() {
        return totalPorServicoPorcentagem;
    }

    public void setTotalPorServicoPorcentagem(final String totalPorServicoPorcentagem) {
        this.totalPorServicoPorcentagem = totalPorServicoPorcentagem;
    }

    public String getTotalPorExecutantePorcentagem() {
        return totalPorExecutantePorcentagem;
    }

    public void setTotalPorExecutantePorcentagem(final String totalPorExecutantePorcentagem) {
        this.totalPorExecutantePorcentagem = totalPorExecutantePorcentagem;
    }

    public double getTotalGrupoExecutadas() {
        return totalGrupoExecutadas;
    }

    public void setTotalGrupoExecutadas(final double totalGrupoExecutadas) {
        this.totalGrupoExecutadas = totalGrupoExecutadas;
    }

    public int getTotalPorExecutante() {
        return totalPorExecutante;
    }

    public void setTotalPorExecutante(final int totalPorExecutante) {
        this.totalPorExecutante = totalPorExecutante;
    }

    public String getNomeServico() {
        return NomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        NomeServico = nomeServico;
    }

    public int getNumeroSolicitacao() {
        return numeroSolicitacao;
    }

    public void setNumeroSolicitacao(final int numeroSolicitacao) {
        this.numeroSolicitacao = numeroSolicitacao;
    }

    public Collection<SolicitacaoServicoDTO> getListaSolicitacoesUsuario() {
        return listaSolicitacoesUsuario;
    }

    public void setListaSolicitacoesUsuario(final Collection<SolicitacaoServicoDTO> listaSolicitacoesUsuario) {
        this.listaSolicitacoesUsuario = listaSolicitacoesUsuario;
    }

    public Collection<RelatorioEficaciaNasEstimativasDasRequisicaoDeServicoDTO> getListaGeral() {
        return listaGeral;
    }

    public void setListaGeral(final Collection<RelatorioEficaciaNasEstimativasDasRequisicaoDeServicoDTO> listaGeral) {
        this.listaGeral = listaGeral;
    }

    public String getCheckMostrarRequisicoes() {
        return checkMostrarRequisicoes;
    }

    public void setCheckMostrarRequisicoes(final String checkMostrarRequisicoes) {
        this.checkMostrarRequisicoes = checkMostrarRequisicoes;
    }

    public String getCheckMostrarIncidentes() {
        return checkMostrarIncidentes;
    }

    public void setCheckMostrarIncidentes(final String checkMostrarIncidentes) {
        this.checkMostrarIncidentes = checkMostrarIncidentes;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getFormatoArquivoRelatorio() {
        return formatoArquivoRelatorio;
    }

    public void setFormatoArquivoRelatorio(final String formatoArquivoRelatorio) {
        this.formatoArquivoRelatorio = formatoArquivoRelatorio;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(final String contrato) {
        this.contrato = contrato;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(final String funcionario) {
        this.funcionario = funcionario;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(final String grupo) {
        this.grupo = grupo;
    }

    public String getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(final String listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
