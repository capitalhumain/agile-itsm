package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class DicionarioDTO extends BaseEntity {

    private Integer idDicionario;

    private Integer idLingua;

    private Integer idLingua2;

    private Integer idLingua3;

    private String sigla;

    private String nome;

    private String valor;

    private String keyIdioma;

    private String keyDescricao;

    private String dicionarioSerializados;

    private String personalizado;

    private Integer qtdCustomizados;

    private String existemItensCustomizados;

    public String getExistemItensCustomizados() {
        return existemItensCustomizados;
    }

    public void setExistemItensCustomizados(final String existemItensCustomizados) {
        this.existemItensCustomizados = existemItensCustomizados;
    }

    public Integer getQtdCustomizados() {
        return qtdCustomizados;
    }

    public void setQtdCustomizados(final Integer qtdCustomizados) {
        this.qtdCustomizados = qtdCustomizados;
    }

    public String getPersonalizado() {
        return personalizado;
    }

    public void setPersonalizado(final String personalizado) {
        this.personalizado = personalizado;
    }

    public String getDicionarioSerializados() {
        return dicionarioSerializados;
    }

    public void setDicionarioSerializados(final String dicionarioSerializados) {
        this.dicionarioSerializados = dicionarioSerializados;
    }

    public String getKeyIdioma() {
        return keyIdioma;
    }

    public void setKeyIdioma(final String keyIdioma) {
        this.keyIdioma = keyIdioma;
    }

    public String getKeyDescricao() {
        return keyDescricao;
    }

    public void setKeyDescricao(final String keyDescricao) {
        this.keyDescricao = keyDescricao;
    }

    public Integer getIdLingua3() {
        return idLingua3;
    }

    public void setIdLingua3(final Integer idLingua3) {
        this.idLingua3 = idLingua3;
    }

    public Integer getIdLingua2() {
        return idLingua2;
    }

    public void setIdLingua2(final Integer idLingua2) {
        this.idLingua2 = idLingua2;
    }

    private Collection<DicionarioDTO> listDicionario;

    /**
     * @return the idDicionario
     */
    public Integer getIdDicionario() {
        return idDicionario;
    }

    /**
     * @param idDicionario
     *            the idDicionario to set
     */
    public void setIdDicionario(final Integer idDicionario) {
        this.idDicionario = idDicionario;
    }

    /**
     * @return the idLingua
     */
    public Integer getIdLingua() {
        return idLingua;
    }

    /**
     * @param idLingua
     *            the idLingua to set
     */
    public void setIdLingua(final Integer idLingua) {
        this.idLingua = idLingua;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome
     *            the nome to set
     */
    public void setNome(final String nome) {
        this.nome = nome;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor
     *            the valor to set
     */
    public void setValor(final String valor) {
        this.valor = valor;
    }

    /**
     * @return the listDicionario
     */
    public Collection<DicionarioDTO> getListDicionario() {
        return listDicionario;
    }

    /**
     * @param listDicionario
     *            the listDicionario to set
     */
    public void setListDicionario(final Collection<DicionarioDTO> listDicionario) {
        this.listDicionario = listDicionario;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(final String sigla) {
        this.sigla = sigla;
    }

}
