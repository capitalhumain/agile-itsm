/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author valdoilo.damasceno
 *
 */
public class PesquisaItemConfiguracaoDTO extends BaseEntity {

    private static final long serialVersionUID = 4955623053777469110L;

    private Integer idPesquisaItemConfiguracao;

    private Integer idItemConfiguracao;

    private Integer idGrupoItemConfiguracao;

    private String ip;

    private Date dataInicio;

    private Date dataFim;

    private String instalacao;

    private String desinstalacao;

    private String inventario;

    private String nomeGrupoItemConfiguracao;

    private Integer idItemConfiguracaoFilho;

    private String itemRelacionado;

    public String getInventario() {
        return inventario;
    }

    public void setInventario(final String inventario) {
        this.inventario = inventario;
    }

    /**
     * @return valor do atributo idPesquisaItemConfiguracao.
     */
    public Integer getIdPesquisaItemConfiguracao() {
        return idPesquisaItemConfiguracao;
    }

    /**
     * Define valor do atributo idPesquisaItemConfiguracao.
     *
     * @param idPesquisaItemConfiguracao
     */
    public void setIdPesquisaItemConfiguracao(final Integer idPesquisaItemConfiguracao) {
        this.idPesquisaItemConfiguracao = idPesquisaItemConfiguracao;
    }

    /**
     * @return valor do atributo idItemConfiguracao.
     */
    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    /**
     * Define valor do atributo idItemConfiguracao.
     *
     * @param idItemConfiguracao
     */
    public void setIdItemConfiguracao(final Integer idItemConfiguracao) {
        this.idItemConfiguracao = idItemConfiguracao;
    }

    /**
     * @return Retorna o valor do atributo idGrupoItemConfiguracao.
     */
    public Integer getIdGrupoItemConfiguracao() {
        return idGrupoItemConfiguracao;
    }

    /**
     * @param pIdGrupoItemConfiguracao
     *            modifica o atributo idGrupoItemConfiguracao.
     */
    public void setIdGrupoItemConfiguracao(final Integer pIdGrupoItemConfiguracao) {
        idGrupoItemConfiguracao = pIdGrupoItemConfiguracao;
    }

    /**
     * @return valor do atributo ip.
     */
    public String getIp() {
        return ip;
    }

    /**
     * Define valor do atributo ip.
     *
     * @param ip
     */
    public void setIp(final String ip) {
        this.ip = ip;
    }

    /**
     * @return valor do atributo dataInicio.
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * Define valor do atributo dataInicio.
     *
     * @param dataInicio
     */
    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return valor do atributo dataFim.
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * Define valor do atributo dataFim.
     *
     * @param dataFim
     */
    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return valor do atributo instalacao.
     */
    public String getInstalacao() {
        return instalacao;
    }

    /**
     * Define valor do atributo instalacao.
     *
     * @param instalacao
     */
    public void setInstalacao(final String instalacao) {
        this.instalacao = instalacao;
    }

    /**
     * @return valor do atributo desinstalacao.
     */
    public String getDesinstalacao() {
        return desinstalacao;
    }

    /**
     * Define valor do atributo desinstalacao.
     *
     * @param desinstalacao
     */
    public void setDesinstalacao(final String desinstalacao) {
        this.desinstalacao = desinstalacao;
    }

    /**
     * @return valor do atributo nomeGrupo.
     */
    public String getNomeGrupoItemConfiguracao() {
        return nomeGrupoItemConfiguracao;
    }

    /**
     * @param nomeGrupo
     */
    public void setNomeGrupoItemConfiguracao(final String pNomeGrupoItemConfiguracao) {
        nomeGrupoItemConfiguracao = pNomeGrupoItemConfiguracao;
    }

    /**
     * @return the idItemConfiguracaoFilho
     */
    public Integer getIdItemConfiguracaoFilho() {
        return idItemConfiguracaoFilho;
    }

    /**
     * @param idItemConfiguracaoFilho
     *            the idItemConfiguracaoFilho to set
     */
    public void setIdItemConfiguracaoFilho(final Integer idItemConfiguracaoFilho) {
        this.idItemConfiguracaoFilho = idItemConfiguracaoFilho;
    }

    /**
     * @return the itemRelacionado
     */
    public String getItemRelacionado() {
        return itemRelacionado;
    }

    /**
     * @param itemRelacionado
     *            the itemRelacionado to set
     */
    public void setItemRelacionado(final String itemRelacionado) {
        this.itemRelacionado = itemRelacionado;
    }

}
