package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.Util;

/**
 * @author breno.guimaraes
 *
 */
public class JustificativaParecerDTO extends BaseEntity {

    private static final long serialVersionUID = -3055161845325828805L;

    private Integer idJustificativa;
    private String descricaoJustificativa;
    private String aplicavelRequisicao;
    private String aplicavelCotacao;
    private String aplicavelInspecao;
    private String situacao;
    private Date dataInicio;
    private Date dataFim;

    public Integer getIdJustificativa() {
        return idJustificativa;
    }

    public void setIdJustificativa(final Integer idJustificativa) {
        this.idJustificativa = idJustificativa;
    }

    public String getDescricaoJustificativa() {
        return Util.tratarAspasSimples(descricaoJustificativa);
    }

    public void setDescricaoJustificativa(final String descricaoJustificativa) {
        this.descricaoJustificativa = descricaoJustificativa;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public String getAplicavelRequisicao() {
        return aplicavelRequisicao;
    }

    public void setAplicavelRequisicao(final String aplicavelRequisicao) {
        this.aplicavelRequisicao = aplicavelRequisicao;
    }

    public String getAplicavelCotacao() {
        return aplicavelCotacao;
    }

    public void setAplicavelCotacao(final String aplicavelCotacao) {
        this.aplicavelCotacao = aplicavelCotacao;
    }

    public String getAplicavelInspecao() {
        return aplicavelInspecao;
    }

    public void setAplicavelInspecao(final String aplicavelInspecao) {
        this.aplicavelInspecao = aplicavelInspecao;
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
