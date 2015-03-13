package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class CategoriaSolucaoDTO extends BaseEntity {

    private Integer idCategoriaSolucao;
    private Integer idCategoriaSolucaoPai;
    private String descricaoCategoriaSolucao;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;

    private Integer nivel;

    public Integer getIdCategoriaSolucao() {
        return idCategoriaSolucao;
    }

    public void setIdCategoriaSolucao(final Integer parm) {
        idCategoriaSolucao = parm;
    }

    public Integer getIdCategoriaSolucaoPai() {
        return idCategoriaSolucaoPai;
    }

    public void setIdCategoriaSolucaoPai(final Integer parm) {
        idCategoriaSolucaoPai = parm;
    }

    public String getDescricaoCategoriaSolucao() {
        return descricaoCategoriaSolucao;
    }

    public void setDescricaoCategoriaSolucao(final String parm) {
        descricaoCategoriaSolucao = parm;
    }

    public String getDescricaoCategoriaNivel() {
        if (this.getNivel() == null) {
            return descricaoCategoriaSolucao;
        }
        String str = "";
        for (int i = 0; i < this.getNivel().intValue(); i++) {
            str += "....";
        }
        return str + descricaoCategoriaSolucao;
    }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final java.sql.Date parm) {
        dataInicio = parm;
    }

    public java.sql.Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final java.sql.Date parm) {
        dataFim = parm;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(final Integer nivel) {
        this.nivel = nivel;
    }

}
