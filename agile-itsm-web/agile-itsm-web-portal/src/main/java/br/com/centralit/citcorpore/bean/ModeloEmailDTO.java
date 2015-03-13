package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ModeloEmailDTO extends BaseEntity {

    private static final long serialVersionUID = 3459262625002482637L;

    private Integer idModeloEmail;
    private String titulo;
    private String texto;
    private String situacao;
    private String campoSelecaoModeloTextual;
    private String div;
    private String tipoCampo;
    private String metodoExecutarVolta;
    private String identificador;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(final String texto) {
        this.texto = texto;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public String getCampoSelecaoModeloTextual() {
        return campoSelecaoModeloTextual;
    }

    public void setCampoSelecaoModeloTextual(final String campoSelecaoModeloTextual) {
        this.campoSelecaoModeloTextual = campoSelecaoModeloTextual;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(final String div) {
        this.div = div;
    }

    public String getTipoCampo() {
        return tipoCampo;
    }

    public void setTipoCampo(final String tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    public String getMetodoExecutarVolta() {
        return metodoExecutarVolta;
    }

    public void setMetodoExecutarVolta(final String metodoExecutarVolta) {
        this.metodoExecutarVolta = metodoExecutarVolta;
    }

    public Integer getIdModeloEmail() {
        return idModeloEmail;
    }

    public void setIdModeloEmail(final Integer idModeloEmail) {
        this.idModeloEmail = idModeloEmail;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(final String identificador) {
        this.identificador = identificador;
    }

}
