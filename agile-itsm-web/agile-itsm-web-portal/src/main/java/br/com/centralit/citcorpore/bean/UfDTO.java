package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class UfDTO extends BaseEntity {

    private static final long serialVersionUID = -7955015000680151852L;

    private Integer idUf;

    private Integer idRegioes;

    private String nomeUf;

    private String siglaUf;

    private Integer idPais;

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

    public Integer getIdUf() {
        return idUf;
    }

    public void setIdUf(final Integer idUf) {
        this.idUf = idUf;
    }

    public String getNomeUf() {
        return nomeUf;
    }

    public void setNomeUf(final String nomeUf) {
        this.nomeUf = nomeUf;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(final String siglaUf) {
        this.siglaUf = siglaUf;
    }

    /**
     * @return the idPais
     */
    public Integer getIdPais() {
        return idPais;
    }

    /**
     * @param idPais
     *            the idPais to set
     */
    public void setIdPais(final Integer idPais) {
        this.idPais = idPais;
    }

}
