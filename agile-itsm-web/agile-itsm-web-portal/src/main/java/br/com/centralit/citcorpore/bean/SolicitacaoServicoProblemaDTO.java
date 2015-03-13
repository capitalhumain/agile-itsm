package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class SolicitacaoServicoProblemaDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idProblema;
    private Integer idSolicitacaoServico;

    private String nomeServico;

    /**
     * @return the idProblema
     */
    public Integer getIdProblema() {
        return idProblema;
    }

    /**
     * @param idProblema
     *            the idProblema to set
     */
    public void setIdProblema(final Integer idProblema) {
        this.idProblema = idProblema;
    }

    /**
     * @return the idSolicitacaoServico
     */
    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    /**
     * @param idSolicitacaoServico
     *            the idSolicitacaoServico to set
     */
    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

}
