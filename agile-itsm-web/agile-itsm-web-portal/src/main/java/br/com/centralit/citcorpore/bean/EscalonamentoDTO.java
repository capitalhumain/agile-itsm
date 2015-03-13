package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class EscalonamentoDTO extends BaseEntity {

    private static final long serialVersionUID = -6560486489318894832L;

    private Integer idEscalonamento;
    private Integer idRegraEscalonamento;
    private Integer idGrupoExecutor;
    private Integer prazoExecucao;
    private Integer idPrioridade;
    private Date dataInicio;
    private Date dataFim;

    private String descricao;
    private String descrPrioridade;

    public Integer getIdGrupoExecutor() {
        return idGrupoExecutor;
    }

    public void setIdGrupoExecutor(final Integer idGrupoExecutor) {
        this.idGrupoExecutor = idGrupoExecutor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescrPrioridade() {
        return descrPrioridade;
    }

    public void setDescrPrioridade(final String descrPrioridade) {
        this.descrPrioridade = descrPrioridade;
    }

    public Integer getIdEscalonamento() {
        return idEscalonamento;
    }

    public void setIdEscalonamento(final Integer idEscalonamento) {
        this.idEscalonamento = idEscalonamento;
    }

    public Integer getIdRegraEscalonamento() {
        return idRegraEscalonamento;
    }

    public void setIdRegraEscalonamento(final Integer idRegraEscalonamento) {
        this.idRegraEscalonamento = idRegraEscalonamento;
    }

    public Integer getPrazoExecucao() {
        return prazoExecucao;
    }

    public void setPrazoExecucao(final Integer prazoExecucao) {
        this.prazoExecucao = prazoExecucao;
    }

    public Integer getIdPrioridade() {
        return idPrioridade;
    }

    public void setIdPrioridade(final Integer idPrioridade) {
        this.idPrioridade = idPrioridade;
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
