package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Bruno Rodrigues
 */
public class RiscoDTO extends BaseEntity {

    private Integer idRisco;
    private String nomeRisco;
    private String detalhamento;
    private Integer nivelRisco;
    private Date dataInicio;
    private Date dataFim;

    public Integer getIdRisco() {
        return idRisco;
    }

    public void setIdRisco(final Integer idRisco) {
        this.idRisco = idRisco;
    }

    public String getNomeRisco() {
        return nomeRisco;
    }

    public void setNomeRisco(final String nomeRisco) {
        this.nomeRisco = nomeRisco;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(final String detalhamento) {
        this.detalhamento = detalhamento;
    }

    public Integer getNivelRisco() {
        return nivelRisco;
    }

    public void setNivelRisco(final Integer nivelRisco) {
        this.nivelRisco = nivelRisco;
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
