package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RegiaoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idRegioes;
    private String nome;

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

}
