package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class MarcaDTO extends BaseEntity {

    private Integer idMarca;
    private Integer idFabricante;
    private String nomeMarca;
    private String situacao;
    private String nomeFabricante;

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(final Integer parm) {
        idMarca = parm;
    }

    public Integer getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(final Integer parm) {
        idFabricante = parm;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(final String parm) {
        nomeMarca = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public String getNomeFabricante() {
        return nomeFabricante;
    }

    public void setNomeFabricante(final String nomeFabricante) {
        this.nomeFabricante = nomeFabricante;
    }

}
