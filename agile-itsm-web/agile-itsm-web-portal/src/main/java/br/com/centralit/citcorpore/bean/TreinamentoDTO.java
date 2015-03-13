package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Pedro
 *
 */
public class TreinamentoDTO extends BaseEntity {

    private Integer idTreinamento;
    private String descTreinamento;
    private Date dataTreinamento;

    private Integer idInstrutorTreinamento;

    public Integer getIdTreinamento() {
        return idTreinamento;
    }

    public void setIdTreinamento(final Integer idTreinamento) {
        this.idTreinamento = idTreinamento;
    }

    public String getDescTreinamento() {
        return descTreinamento;
    }

    public void setDescTreinamento(final String descTreinamento) {
        this.descTreinamento = descTreinamento;
    }

    public Date getDataTreinamento() {
        return dataTreinamento;
    }

    public void setDataTreinamento(final Date dataTreinamento) {
        this.dataTreinamento = dataTreinamento;
    }

    public Integer getIdInstrutorTreinamento() {
        return idInstrutorTreinamento;
    }

    public void setIdInstrutorTreinamento(final Integer idInstrutorTreinamento) {
        this.idInstrutorTreinamento = idInstrutorTreinamento;
    }

}
