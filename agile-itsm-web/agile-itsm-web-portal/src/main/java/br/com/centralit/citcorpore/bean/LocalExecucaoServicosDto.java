package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class LocalExecucaoServicosDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idLocalExecucaoServico;
    private String nomeLocalExecucaoServico;
    private String deleted;

    public Integer getIdLocalExecucaoServico() {
        return idLocalExecucaoServico;
    }

    public void setIdLocalExecucaoServico(final Integer idLocalExecucaoServico) {
        this.idLocalExecucaoServico = idLocalExecucaoServico;
    }

    public String getNomeLocalExecucaoServico() {
        return nomeLocalExecucaoServico;
    }

    public void setNomeLocalExecucaoServico(final String nomeLocalExecucaoServico) {
        this.nomeLocalExecucaoServico = nomeLocalExecucaoServico;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

}
