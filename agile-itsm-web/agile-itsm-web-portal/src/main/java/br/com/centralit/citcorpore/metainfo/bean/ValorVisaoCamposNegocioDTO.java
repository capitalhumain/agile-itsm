package br.com.centralit.citcorpore.metainfo.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ValorVisaoCamposNegocioDTO extends BaseEntity {

    private Integer idValorVisaoCamposNegocio;
    private Integer idGrupoVisao;
    private Integer idCamposObjetoNegocio;
    private String valor;
    private String situacao;
    private String descricao;

    public Integer getIdValorVisaoCamposNegocio() {
        return idValorVisaoCamposNegocio;
    }

    public void setIdValorVisaoCamposNegocio(final Integer parm) {
        idValorVisaoCamposNegocio = parm;
    }

    public Integer getIdGrupoVisao() {
        return idGrupoVisao;
    }

    public void setIdGrupoVisao(final Integer parm) {
        idGrupoVisao = parm;
    }

    public Integer getIdCamposObjetoNegocio() {
        return idCamposObjetoNegocio;
    }

    public void setIdCamposObjetoNegocio(final Integer parm) {
        idCamposObjetoNegocio = parm;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(final String parm) {
        valor = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getValorDescricao() {
        return valor + "#" + descricao;
    }

    public String getValorDescricaoMostrar() {
        return valor + " - " + descricao;
    }

}
