package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class MotivoSuspensaoAtividadeDTO extends BaseEntity {

    private static final long serialVersionUID = 3874037901517329598L;

    private Integer idMotivo;
    private String descricao;
    private Date dataFim;

    public Integer getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(final Integer idMotivo) {
        this.idMotivo = idMotivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

}
