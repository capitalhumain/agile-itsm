package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class CidadesDTO extends BaseEntity {

    private static final long serialVersionUID = -8199400906988594799L;

    private Integer idCidade;
    private Integer IdUf;
    private String nomeCidade;
    private String nomeUf;

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(final Integer idCidade) {
        this.idCidade = idCidade;
    }

    public Integer getIdUf() {
        return IdUf;
    }

    public void setIdUf(final Integer idUf) {
        IdUf = idUf;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(final String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getNomeUf() {
        return nomeUf;
    }

    public void setNomeUf(final String nomeUf) {
        this.nomeUf = nomeUf;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
