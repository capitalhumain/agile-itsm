package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class ControleImportarDadosDTO extends BaseEntity {

    private static final long serialVersionUID = 4942283426115769824L;

    private Integer idControleImportarDados;
    private Integer idImportarDados;
    private Timestamp dataExecucao;

    public Integer getIdControleImportarDados() {
        return idControleImportarDados;
    }

    public void setIdControleImportarDados(final Integer idControleImportarDados) {
        this.idControleImportarDados = idControleImportarDados;
    }

    public Integer getIdImportarDados() {
        return idImportarDados;
    }

    public void setIdImportarDados(final Integer idImportarDados) {
        this.idImportarDados = idImportarDados;
    }

    public Timestamp getDataExecucao() {
        return dataExecucao;
    }

    public void setDataExecucao(final Timestamp dataExecucao) {
        this.dataExecucao = dataExecucao;
    }

}
