package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RelEscalonamentoProblemaDto extends BaseEntity {

    /**
     * Relaciona as solicitações de serviço ao escalonamento
     */
    private static final long serialVersionUID = 1L;

    private Integer idProblema;
    private Integer idEscalonamento;

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(final Integer idProblema) {
        this.idProblema = idProblema;
    }

    public Integer getIdEscalonamento() {
        return idEscalonamento;
    }

    public void setIdEscalonamento(final Integer idEscalonamento) {
        this.idEscalonamento = idEscalonamento;
    }

}
