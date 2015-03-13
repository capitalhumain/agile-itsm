package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class AssinaturaDTO extends BaseEntity {

    private static final long serialVersionUID = 3430643183983341226L;

    private Integer idAssinatura;
    private Integer idEmpregado;
    private String nomeResponsavel;
    private String papel;
    private String fase;
    private Date dataInicio;
    private Date dataFim;

    public Integer getIdAssinatura() {
        return idAssinatura;
    }

    public void setIdAssinatura(final Integer idAssinatura) {
        this.idAssinatura = idAssinatura;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(final String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(final String papel) {
        this.papel = papel;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(final String fase) {
        this.fase = fase;
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
