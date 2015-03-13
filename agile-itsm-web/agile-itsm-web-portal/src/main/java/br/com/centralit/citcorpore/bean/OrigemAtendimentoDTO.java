package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class OrigemAtendimentoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idOrigem;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;

    public Integer getIdOrigem() {
        return idOrigem;
    }

    public void setIdOrigem(final Integer parm) {
        idOrigem = parm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String parm) {
        descricao = parm;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

}
