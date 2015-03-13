package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class FeriadoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idFeriado;
    private Date data;
    private String descricao;
    private String abrangencia;
    private Integer idUf;
    private Integer idCidade;
    private String recorrente;

    public Date getData() {
        return data;
    }

    public void setData(final Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getAbrangencia() {
        return abrangencia;
    }

    public void setAbrangencia(final String abrangencia) {
        this.abrangencia = abrangencia;
    }

    public Integer getIdUf() {
        return idUf;
    }

    public void setIdUf(final Integer idUf) {
        this.idUf = idUf;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(final Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getRecorrente() {
        return recorrente;
    }

    public void setRecorrente(final String recorrente) {
        this.recorrente = recorrente;
    }

    public Integer getIdFeriado() {
        return idFeriado;
    }

    public void setIdFeriado(final Integer idFeriado) {
        this.idFeriado = idFeriado;
    }

}
