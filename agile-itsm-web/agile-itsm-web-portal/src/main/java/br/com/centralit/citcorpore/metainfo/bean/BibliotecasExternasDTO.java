package br.com.centralit.citcorpore.metainfo.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class BibliotecasExternasDTO extends BaseEntity {

    private Integer idBibliotecasExterna;
    private String nome;
    private String caminho;

    public Integer getIdBibliotecasExterna() {
        return idBibliotecasExterna;
    }

    public void setIdBibliotecasExterna(final Integer parm) {
        idBibliotecasExterna = parm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String parm) {
        nome = parm;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(final String parm) {
        caminho = parm;
    }

}
