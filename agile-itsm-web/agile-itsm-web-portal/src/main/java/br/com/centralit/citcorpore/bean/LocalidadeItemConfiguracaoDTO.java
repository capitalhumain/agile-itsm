package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class LocalidadeItemConfiguracaoDTO extends BaseEntity {

    private Integer idLocalidade;
    private Integer idItemConfiguracao;
    private Integer idRegioes;
    private Integer idUnidade;
    private String itemConfiguracao;
    private Integer idUf;
    private String cidade;
    private String endereco;
    private String complemento;
    private String numero;
    private String bairro;
    private String edificio;
    private String sala;
    private String divisao;
    private String subdivisao;
    private String secao;
    private String departamento;
    private Date dataInicio;
    private Date dataFim;

    /**
     * @return the idUf
     */
    public Integer getIdUf() {
        return idUf;
    }

    /**
     * @param idUf
     *            the idUf to set
     */
    public void setIdUf(final Integer idUf) {
        this.idUf = idUf;
    }

    /**
     * @return the idLocalidade
     */
    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    /**
     * @param idLocalidade
     *            the idLocalidade to set
     */
    public void setIdLocalidade(final Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
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
     * @return the idRegioes
     */
    public Integer getIdRegioes() {
        return idRegioes;
    }

    /**
     * @param idRegioes
     *            the idRegioes to set
     */
    public void setIdRegioes(final Integer idRegioes) {
        this.idRegioes = idRegioes;
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
     * @return the itemConfiguracao
     */
    public String getItemConfiguracao() {
        return itemConfiguracao;
    }

    /**
     * @param itemConfiguracao
     *            the itemConfiguracao to set
     */
    public void setItemConfiguracao(final String itemConfiguracao) {
        this.itemConfiguracao = itemConfiguracao;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade
     *            the cidade to set
     */
    public void setCidade(final String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco
     *            the endereco to set
     */
    public void setEndereco(final String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento
     *            the complemento to set
     */
    public void setComplemento(final String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero
     *            the numero to set
     */
    public void setNumero(final String numero) {
        this.numero = numero;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro
     *            the bairro to set
     */
    public void setBairro(final String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the edificio
     */
    public String getEdificio() {
        return edificio;
    }

    /**
     * @param edificio
     *            the edificio to set
     */
    public void setEdificio(final String edificio) {
        this.edificio = edificio;
    }

    /**
     * @return the sala
     */
    public String getSala() {
        return sala;
    }

    /**
     * @param sala
     *            the sala to set
     */
    public void setSala(final String sala) {
        this.sala = sala;
    }

    /**
     * @return the divisao
     */
    public String getDivisao() {
        return divisao;
    }

    /**
     * @param divisao
     *            the divisao to set
     */
    public void setDivisao(final String divisao) {
        this.divisao = divisao;
    }

    /**
     * @return the subdivisao
     */
    public String getSubdivisao() {
        return subdivisao;
    }

    /**
     * @param subdivisao
     *            the subdivisao to set
     */
    public void setSubdivisao(final String subdivisao) {
        this.subdivisao = subdivisao;
    }

    /**
     * @return the secao
     */
    public String getSecao() {
        return secao;
    }

    /**
     * @param secao
     *            the secao to set
     */
    public void setSecao(final String secao) {
        this.secao = secao;
    }

    /**
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento
     *            the departamento to set
     */
    public void setDepartamento(final String departamento) {
        this.departamento = departamento;
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

}
