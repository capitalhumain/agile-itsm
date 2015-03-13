package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.UtilStrings;

public class GaleriaImagensDTO extends BaseEntity {

    private Integer idImagem;
    private Integer idCategoriaGaleriaImagem;
    private String nomeImagem;
    private String descricaoImagem;
    private String detalhamento;
    private String extensao;
    private String nomeCategoria;
    private String selecaoImagemEdicao;

    public Integer getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(final Integer idImagem) {
        this.idImagem = idImagem;
    }

    public Integer getIdCategoriaGaleriaImagem() {
        return idCategoriaGaleriaImagem;
    }

    public void setIdCategoriaGaleriaImagem(final Integer idCategoriaGaleriaImagem) {
        this.idCategoriaGaleriaImagem = idCategoriaGaleriaImagem;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(final String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public void setDescricaoAndCategoriaImagem(final String str) {

    }

    public String getDescricaoAndCategoriaImagem() {
        return UtilStrings.nullToVazio(this.getNomeCategoria()) + " - " + UtilStrings.nullToVazio(this.getDescricaoImagem());
    }

    public String getDescricaoImagem() {
        return descricaoImagem;
    }

    public void setDescricaoImagem(final String descricaoImagem) {
        this.descricaoImagem = descricaoImagem;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(final String detalhamento) {
        this.detalhamento = detalhamento;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(final String extensao) {
        this.extensao = extensao;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(final String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getSelecaoImagemEdicao() {
        return selecaoImagemEdicao;
    }

    public void setSelecaoImagemEdicao(final String selecaoImagemEdicao) {
        this.selecaoImagemEdicao = selecaoImagemEdicao;
    }

}
