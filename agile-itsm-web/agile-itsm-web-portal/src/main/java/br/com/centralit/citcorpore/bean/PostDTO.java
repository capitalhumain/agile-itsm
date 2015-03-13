package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class PostDTO extends BaseEntity {

    private static final long serialVersionUID = 638687400065001805L;

    private Integer idPost;
    private String titulo;
    private String descricao;
    private String conteudo;
    private String imagem;
    private Integer idCategoriaPost;
    private Date dataInicio;
    private Date dataFim;

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(final Integer idPost) {
        this.idPost = idPost;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(final String conteudo) {
        this.conteudo = conteudo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(final String imagem) {
        this.imagem = imagem;
    }

    public Integer getIdCategoriaPost() {
        return idCategoriaPost;
    }

    public void setIdCategoriaPost(final Integer idCategoriaPost) {
        this.idCategoriaPost = idCategoriaPost;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

}
