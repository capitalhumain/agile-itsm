package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class ExecucaoAtividadePeriodicaDTO extends BaseEntity {

    private Integer idExecucaoAtividadePeriodica;
    private Integer idAtividadePeriodica;
    private Integer idProgramacaoAtividade;
    private java.sql.Date dataProgramada;
    private String horaProgramada;
    private String situacao;
    private String detalhamento;
    private String usuario;
    private Integer idEmpregado;
    private java.sql.Date dataExecucao;
    private String horaExecucao;
    private java.sql.Date dataRegistro;
    private String horaRegistro;
    private Integer idMotivoSuspensao;
    private String complementoMotivoSuspensao;

    private Collection colArquivosUpload;

    private Collection colAnexos;

    public Integer getIdExecucaoAtividadePeriodica() {
        return idExecucaoAtividadePeriodica;
    }

    public void setIdExecucaoAtividadePeriodica(final Integer parm) {
        idExecucaoAtividadePeriodica = parm;
    }

    public Integer getIdAtividadePeriodica() {
        return idAtividadePeriodica;
    }

    public void setIdAtividadePeriodica(final Integer parm) {
        idAtividadePeriodica = parm;
    }

    public java.sql.Date getDataProgramada() {
        return dataProgramada;
    }

    public void setDataProgramada(final java.sql.Date parm) {
        dataProgramada = parm;
    }

    public String getHoraProgramada() {
        return horaProgramada;
    }

    public void setHoraProgramada(final String parm) {
        horaProgramada = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public String getSituacaoDescr() {
        if (situacao == null) {
            return "";
        }
        if (situacao.equalsIgnoreCase("E")) {
            return "Em Execução";
        }
        if (situacao.equalsIgnoreCase("S")) {
            return "Suspenso";
        }
        if (situacao.equalsIgnoreCase("F")) {
            return "Executado";
        }
        return situacao;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(final String parm) {
        detalhamento = parm;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(final String parm) {
        usuario = parm;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer parm) {
        idEmpregado = parm;
    }

    public java.sql.Date getDataExecucao() {
        return dataExecucao;
    }

    public void setDataExecucao(final java.sql.Date parm) {
        dataExecucao = parm;
    }

    public String getHoraExecucao() {
        return horaExecucao;
    }

    public void setHoraExecucao(final String parm) {
        horaExecucao = parm;
    }

    public java.sql.Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(final java.sql.Date parm) {
        dataRegistro = parm;
    }

    public String getHoraRegistro() {
        return horaRegistro;
    }

    public void setHoraRegistro(final String parm) {
        horaRegistro = parm;
    }

    public Integer getIdProgramacaoAtividade() {
        return idProgramacaoAtividade;
    }

    public void setIdProgramacaoAtividade(final Integer idProgramacaoAtividade) {
        this.idProgramacaoAtividade = idProgramacaoAtividade;
    }

    public Collection getColAnexos() {
        return colAnexos;
    }

    public void setColAnexos(final Collection colAnexos) {
        this.colAnexos = colAnexos;
    }

    public Integer getIdMotivoSuspensao() {
        return idMotivoSuspensao;
    }

    public void setIdMotivoSuspensao(final Integer idMotivoSuspensao) {
        this.idMotivoSuspensao = idMotivoSuspensao;
    }

    public String getComplementoMotivoSuspensao() {
        return complementoMotivoSuspensao;
    }

    public void setComplementoMotivoSuspensao(final String complementoMotivoSuspensao) {
        this.complementoMotivoSuspensao = complementoMotivoSuspensao;
    }

    public Collection getColArquivosUpload() {
        return colArquivosUpload;
    }

    public void setColArquivosUpload(final Collection colArquivosUpload) {
        this.colArquivosUpload = colArquivosUpload;
    }

}
