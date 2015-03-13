/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Thays
 *
 */
public class RelatorioQuantitativoSolicitacaoDTO extends BaseEntity {

    private static final long serialVersionUID = 5769173299912237423L;
    private String fase;
    private String grupo;
    private String grupoPesquisaSatisfacao;
    private Integer horaAbertura;
    private Integer idServico;
    private String itemConfiguracao;
    private Object listaPorFase;
    private Object listaPorGrupo;
    private Object listaPorHoraAbertura;
    private Object listaPorItemConfiguracao;
    private Object listaPorOrigem;
    private Object listaPorPesquisaSatisfacao;
    private Object listaPorPrioridade;
    private Object listaPorResponsavel;
    private Object listaPorServico;
    private Object listaPorSituacao;
    private Object listaPorSituacaoSLA;
    private Object listaPorTipo;
    private Object listaPorTipoServico;
    private String nomeServico;
    private String nometiposervico;
    private String origem;
    private String prioridade;
    private Integer quantidadeFase;
    private Integer quantidadeHoraAbertura;
    private Integer quantidadeItemConfiguracao;
    private Integer quantidadeOrigem;
    private Integer quantidadePesquisaSatisfacao;
    private Integer quantidadePrioridade;
    private Integer quantidadeResponsavel;
    private Integer quantidadeServico;
    private Integer quantidadeSituacao;
    private Integer quantidadeSituacaoSLA;
    private Integer quantidadeSolicitante;
    private Integer quantidadeTipo;
    private Integer quantidadeTipoServico;
    private String responsavel;
    private String servico;
    private String servicoPesquisaSatisfacao;
    private String situacao;
    private String situacaoSLA;
    private String solicitante;
    private String tipo;
    private String tipoServico;
    private String nome;
    private Integer idSolicitacaoServico;
    private Date dataHoraSolicitacao;
    private String numero;;

    public String getFase() {
        return fase;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getGrupoPesquisaSatisfacao() {
        return grupoPesquisaSatisfacao;
    }

    public Integer getHoraAbertura() {
        return horaAbertura;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public String getItemConfiguracao() {
        return itemConfiguracao;
    }

    /**
     * @return the listaPorFase
     */
    public Object getListaPorFase() {
        return listaPorFase;
    }

    /**
     * @return the listaPorGrupo
     */
    public Object getListaPorGrupo() {
        return listaPorGrupo;
    }

    public Object getListaPorHoraAbertura() {
        return listaPorHoraAbertura;
    }

    /**
     * @return the listaPorItemConfiguracao
     */
    public Object getListaPorItemConfiguracao() {
        return listaPorItemConfiguracao;
    }

    /**
     * @return the listaPorOrigem
     */
    public Object getListaPorOrigem() {
        return listaPorOrigem;
    }

    public Object getListaPorPesquisaSatisfacao() {
        return listaPorPesquisaSatisfacao;
    }

    /**
     * @return the listaPorPrioridade
     */
    public Object getListaPorPrioridade() {
        return listaPorPrioridade;
    }

    public Object getListaPorResponsavel() {
        return listaPorResponsavel;
    }

    public Object getListaPorServico() {
        return listaPorServico;
    }

    /**
     * @return the listaPorSituacao
     */
    public Object getListaPorSituacao() {
        return listaPorSituacao;
    }

    public Object getListaPorSituacaoSLA() {
        return listaPorSituacaoSLA;
    }

    /**
     * @return the listaPorTipo
     */
    public Object getListaPorTipo() {
        return listaPorTipo;
    }

    /**
     * @return the listaPorTipoServico
     */
    public Object getListaPorTipoServico() {
        return listaPorTipoServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    /**
     * @return the nometiposervico
     */
    public String getNometiposervico() {
        return nometiposervico;
    }

    public String getOrigem() {
        return origem;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public Integer getQuantidadeFase() {
        return quantidadeFase;
    }

    public Integer getQuantidadeHoraAbertura() {
        return quantidadeHoraAbertura;
    }

    public Integer getQuantidadeItemConfiguracao() {
        return quantidadeItemConfiguracao;
    }

    public Integer getQuantidadeOrigem() {
        return quantidadeOrigem;
    }

    public Integer getQuantidadePesquisaSatisfacao() {
        return quantidadePesquisaSatisfacao;
    }

    public Integer getQuantidadePrioridade() {
        return quantidadePrioridade;
    }

    public Integer getQuantidadeResponsavel() {
        return quantidadeResponsavel;
    }

    public Integer getQuantidadeServico() {
        return quantidadeServico;
    }

    public Integer getQuantidadeSituacao() {
        return quantidadeSituacao;
    }

    public Integer getQuantidadeSituacaoSLA() {
        return quantidadeSituacaoSLA;
    }

    public Integer getQuantidadeSolicitante() {
        return quantidadeSolicitante;
    }

    public Integer getQuantidadeTipo() {
        return quantidadeTipo;
    }

    /**
     * @return the quantidadeTipoServico
     */
    public Integer getQuantidadeTipoServico() {
        return quantidadeTipoServico;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public String getServico() {
        return servico;
    }

    public String getServicoPesquisaSatisfacao() {
        return servicoPesquisaSatisfacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public String getSituacaoSLA() {
        return situacaoSLA;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public String getTipo() {
        return tipo;
    }

    /**
     * @return the tipoServico
     */
    public String getTipoServico() {
        return tipoServico;
    }

    public void setFase(final String fase) {
        this.fase = fase;
    }

    public void setGrupo(final String grupo) {
        this.grupo = grupo;
    }

    public void setGrupoPesquisaSatisfacao(final String grupoPesquisaSatisfacao) {
        this.grupoPesquisaSatisfacao = grupoPesquisaSatisfacao;
    }

    public void setHoraAbertura(final Integer horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public void setIdServico(final Integer idServico) {
        this.idServico = idServico;
    }

    public void setItemConfiguracao(final String itemConfiguracao) {
        this.itemConfiguracao = itemConfiguracao;
    }

    /**
     * @param listaPorFase
     *            the listaPorFase to set
     */
    public void setListaPorFase(final Object listaPorFase) {
        this.listaPorFase = listaPorFase;
    }

    /**
     * @param listaPorGrupo
     *            the listaPorGrupo to set
     */
    public void setListaPorGrupo(final Object listaPorGrupo) {
        this.listaPorGrupo = listaPorGrupo;
    }

    public void setListaPorHoraAbertura(final Object listaPorHoraAbertura) {
        this.listaPorHoraAbertura = listaPorHoraAbertura;
    }

    /**
     * @param listaPorItemConfiguracao
     *            the listaPorItemConfiguracao to set
     */
    public void setListaPorItemConfiguracao(final Object listaPorItemConfiguracao) {
        this.listaPorItemConfiguracao = listaPorItemConfiguracao;
    }

    /**
     * @param listaPorOrigem
     *            the listaPorOrigem to set
     */
    public void setListaPorOrigem(final Object listaPorOrigem) {
        this.listaPorOrigem = listaPorOrigem;
    }

    public void setListaPorPesquisaSatisfacao(final Object listaPorPesquisaSatisfacao) {
        this.listaPorPesquisaSatisfacao = listaPorPesquisaSatisfacao;
    }

    /**
     * @param listaPorPrioridade
     *            the listaPorPrioridade to set
     */
    public void setListaPorPrioridade(final Object listaPorPrioridade) {
        this.listaPorPrioridade = listaPorPrioridade;
    }

    public void setListaPorResponsavel(final Object listaPorResponsavel) {
        this.listaPorResponsavel = listaPorResponsavel;
    }

    public void setListaPorServico(final Object listaPorServico) {
        this.listaPorServico = listaPorServico;
    }

    /**
     * @param listaPorSituacao
     *            the listaPorSituacao to set
     */
    public void setListaPorSituacao(final Object listaPorSituacao) {
        this.listaPorSituacao = listaPorSituacao;
    }

    public void setListaPorSituacaoSLA(final Object listaPorSituacaoSLA) {
        this.listaPorSituacaoSLA = listaPorSituacaoSLA;
    }

    /**
     * @param listaPorTipo
     *            the listaPorTipo to set
     */
    public void setListaPorTipo(final Object listaPorTipo) {
        this.listaPorTipo = listaPorTipo;
    }

    /**
     * @param listaPorTipoServico
     *            the listaPorTipoServico to set
     */
    public void setListaPorTipoServico(final Object listaPorTipoServico) {
        this.listaPorTipoServico = listaPorTipoServico;
    }

    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

    /**
     * @param nometiposervico
     *            the nometiposervico to set
     */
    public void setNometiposervico(final String nometiposervico) {
        this.nometiposervico = nometiposervico;
    }

    public void setOrigem(final String origem) {
        this.origem = origem;
    }

    public void setPrioridade(final String prioridade) {
        this.prioridade = prioridade;
    }

    public void setQuantidadeFase(final Integer quantidadeFase) {
        this.quantidadeFase = quantidadeFase;
    }

    public void setQuantidadeHoraAbertura(final Integer quantidadeHoraAbertura) {
        this.quantidadeHoraAbertura = quantidadeHoraAbertura;
    }

    public void setQuantidadeItemConfiguracao(final Integer quantidadeItemConfiguracao) {
        this.quantidadeItemConfiguracao = quantidadeItemConfiguracao;
    }

    public void setQuantidadeOrigem(final Integer quantidadeOrigem) {
        this.quantidadeOrigem = quantidadeOrigem;
    }

    public void setQuantidadePesquisaSatisfacao(final Integer quantidadePesquisaSatisfacao) {
        this.quantidadePesquisaSatisfacao = quantidadePesquisaSatisfacao;
    }

    public void setQuantidadePrioridade(final Integer quantidadePrioridade) {
        this.quantidadePrioridade = quantidadePrioridade;
    }

    public void setQuantidadeResponsavel(final Integer quantidadeResponsavel) {
        this.quantidadeResponsavel = quantidadeResponsavel;
    }

    public void setQuantidadeServico(final Integer quantidadeServico) {
        this.quantidadeServico = quantidadeServico;
    }

    public void setQuantidadeSituacao(final Integer quantidadeSituacao) {
        this.quantidadeSituacao = quantidadeSituacao;
    }

    public void setQuantidadeSituacaoSLA(final Integer quantidadeSituacaoSLA) {
        this.quantidadeSituacaoSLA = quantidadeSituacaoSLA;
    }

    public void setQuantidadeSolicitante(final Integer quantidadeSolicitante) {
        this.quantidadeSolicitante = quantidadeSolicitante;
    }

    public void setQuantidadeTipo(final Integer quantidadeTipo) {
        this.quantidadeTipo = quantidadeTipo;
    }

    /**
     * @param quantidadeTipoServico
     *            the quantidadeTipoServico to set
     */
    public void setQuantidadeTipoServico(final Integer quantidadeTipoServico) {
        this.quantidadeTipoServico = quantidadeTipoServico;
    }

    public void setResponsavel(final String responsavel) {
        this.responsavel = responsavel;
    }

    public void setServico(final String servico) {
        this.servico = servico;
    }

    public void setServicoPesquisaSatisfacao(final String servicoPesquisaSatisfacao) {
        this.servicoPesquisaSatisfacao = servicoPesquisaSatisfacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public void setSituacaoSLA(final String situacaoSLA) {
        this.situacaoSLA = situacaoSLA;
    }

    public void setSolicitante(final String solicitante) {
        this.solicitante = solicitante;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    /**
     * @param tipoServico
     *            the tipoServico to set
     */
    public void setTipoServico(final String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public Date getDataHoraSolicitacao() {
        return dataHoraSolicitacao;
    }

    public void setDataHoraSolicitacao(final Date dataHoraSolicitacao) {
        this.dataHoraSolicitacao = dataHoraSolicitacao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(final String numero) {
        this.numero = numero;
    }

}
