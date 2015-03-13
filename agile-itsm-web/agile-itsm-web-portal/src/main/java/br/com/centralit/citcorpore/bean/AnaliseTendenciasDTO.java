package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class AnaliseTendenciasDTO extends BaseEntity {

    private Date dataInicio;

    private Date dataFim;

    private Integer idContrato;

    private Integer idServico;

    private Integer idGrupoExecutor;

    private Integer idEmpregado;

    private Integer idTipoDemandaServico;

    private String urgencia;

    private String impacto;

    private Integer idItemConfiguracao;

    private Integer idCausaIncidente;

    private Integer qtdeCritica;

    private Integer idRelatorio;

    private String tipoRelatorio;

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

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(final Integer idServico) {
        this.idServico = idServico;
    }

    public Integer getIdGrupoExecutor() {
        return idGrupoExecutor;
    }

    public void setIdGrupoExecutor(final Integer idGrupoExecutor) {
        this.idGrupoExecutor = idGrupoExecutor;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    public Integer getIdTipoDemandaServico() {
        return idTipoDemandaServico;
    }

    public void setIdTipoDemandaServico(final Integer idTipoDemandaServico) {
        this.idTipoDemandaServico = idTipoDemandaServico;
    }

    public String getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(final String urgencia) {
        this.urgencia = urgencia;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(final String impacto) {
        this.impacto = impacto;
    }

    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    public void setIdItemConfiguracao(final Integer idItemConfiguracao) {
        this.idItemConfiguracao = idItemConfiguracao;
    }

    public Integer getIdCausaIncidente() {
        return idCausaIncidente;
    }

    public void setIdCausaIncidente(final Integer idCausaIncidente) {
        this.idCausaIncidente = idCausaIncidente;
    }

    public Integer getQtdeCritica() {
        return qtdeCritica;
    }

    public void setQtdeCritica(final Integer qtdeCritica) {
        this.qtdeCritica = qtdeCritica;
    }

    public Integer getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(final Integer idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public String getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(final String tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }

}
