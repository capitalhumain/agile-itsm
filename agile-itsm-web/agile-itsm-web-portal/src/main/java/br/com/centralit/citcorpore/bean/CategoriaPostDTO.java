package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Flávio.santana
 *
 */
public class CategoriaPostDTO extends BaseEntity {

    private static final long serialVersionUID = 2984986456849222230L;

    private Integer idCategoriaPost;
    private Integer idCategoriaPostPai;
    private String nomeCategoria;
    private Date dataInicio;
    private Date dataFim;

    public Integer getIdCategoriaPost() {
        return idCategoriaPost;
    }

    public void setIdCategoriaPost(final Integer idCategoriaPost) {
        this.idCategoriaPost = idCategoriaPost;
    }

    public Integer getIdCategoriaPostPai() {
        return idCategoriaPostPai;
    }

    public void setIdCategoriaPostPai(final Integer idCategoriaPostPai) {
        this.idCategoriaPostPai = idCategoriaPostPai;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(final String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
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
