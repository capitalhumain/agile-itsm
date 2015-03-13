package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Maycon.Fernandes
 *
 */
public class InventarioXMLDTO extends BaseEntity {

    private Integer idInventarioxml;
    private Integer idNetMap;
    private String nome;
    private String conteudo;
    private java.sql.Date datainicial;
    private java.sql.Date datafinal;

    /**
     * Define valor do atributo idInventarioxml.
     *
     * @param idInventarioxml
     */
    public void setIdInventarioxml(final Integer idInventarioxml) {
        this.idInventarioxml = idInventarioxml;
    }

    /**
     * @return valor do atributo idInventarioxml.
     */
    public Integer getIdInventarioxml() {
        return idInventarioxml;
    }

    /**
     * Define valor do atributo idNetMap.
     *
     * @param idNetMap
     */
    public void setIdNetMap(final Integer idNetMap) {
        this.idNetMap = idNetMap;
    }

    /**
     * @return valor do atributo idNetMap.
     */
    public Integer getIdNetMap() {
        return idNetMap;
    }

    /**
     * Define valor do atributo nome.
     *
     * @param nome
     */
    public void setNome(final String nome) {
        this.nome = nome;
    }

    /**
     * @return valor do atributo nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define valor do atributo conteudo.
     *
     * @param conteudo
     */
    public void setConteudo(final String conteudo) {
        this.conteudo = conteudo;
    }

    /**
     * @return valor do atributo conteudo.
     */
    public String getConteudo() {
        return conteudo;
    }

    /**
     * Define valor do atributo dataInicio.
     *
     * @param dataInicio
     */
    /**
     * Define valor do atributo datainicial.
     *
     * @param datainicial
     */
    public void setDatainicial(final java.sql.Date datainicial) {
        this.datainicial = datainicial;
    }

    /**
     * @return valor do atributo datainicial.
     */
    public java.sql.Date getDatainicial() {
        return datainicial;
    }

    /**
     * Define valor do atributo datafinal.
     *
     * @param datafinal
     */
    public void setDatafinal(final java.sql.Date datafinal) {
        this.datafinal = datafinal;
    }

    /**
     * @return valor do atributo datafinal.
     */
    public java.sql.Date getDatafinal() {
        return datafinal;
    }

}
