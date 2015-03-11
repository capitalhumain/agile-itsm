package br.com.centralit.bpm.dto;

import br.com.agileitsm.model.support.BaseEntity;

public class ExecucaoFluxoDTO extends BaseEntity {

    private static final long serialVersionUID = -7927825151260462324L;

    private Integer idExecucao;
    private Integer idFluxo;
    private Integer idInstanciaFluxo;

    public Integer getIdExecucao() {
        return idExecucao;
    }

    public void setIdExecucao(final Integer idExecucao) {
        this.idExecucao = idExecucao;
    }

    public Integer getIdFluxo() {
        return idFluxo;
    }

    public void setIdFluxo(final Integer idFluxo) {
        this.idFluxo = idFluxo;
    }

    public Integer getIdInstanciaFluxo() {
        return idInstanciaFluxo;
    }

    public void setIdInstanciaFluxo(final Integer idInstanciaFluxo) {
        this.idInstanciaFluxo = idInstanciaFluxo;
    }

}
