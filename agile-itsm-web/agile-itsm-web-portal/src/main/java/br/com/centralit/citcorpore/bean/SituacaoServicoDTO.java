package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class SituacaoServicoDTO extends BaseEntity {

    private Integer idSituacaoServico;
    private Integer idEmpresa;
    private String nomeSituacaoServico;
    private Date dataInicio;
    private Date dataFim;

    public Integer getIdSituacaoServico() {
        return idSituacaoServico;
    }

    public void setIdSituacaoServico(final Integer idSituacaoServico) {
        this.idSituacaoServico = idSituacaoServico;
    }

    public String getNomeSituacaoServico() {
        return nomeSituacaoServico;
    }

    public void setNomeSituacaoServico(final String nomeSituacaoServico) {
        this.nomeSituacaoServico = nomeSituacaoServico;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(final Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
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
