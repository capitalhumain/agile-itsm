package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class GerenciamentoProblemasDTO extends BaseEntity {

    private Integer idFluxo;
    private Integer idTarefa;
    private Integer idProblema;
    private String acaoFluxo;

    private String numeroContratoSel;
    private String idSolicitacaoSel;
    private String atribuidaCompartilhada;
    private String idProblemaSel;

    public Integer getIdFluxo() {
        return idFluxo;
    }

    public void setIdFluxo(final Integer idFluxo) {
        this.idFluxo = idFluxo;
    }

    public Integer getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(final Integer idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getAcaoFluxo() {
        return acaoFluxo;
    }

    public void setAcaoFluxo(final String acaoFluxo) {
        this.acaoFluxo = acaoFluxo;
    }

    public String getNumeroContratoSel() {
        return numeroContratoSel;
    }

    public void setNumeroContratoSel(final String numeroContratoSel) {
        this.numeroContratoSel = numeroContratoSel;
    }

    public String getIdSolicitacaoSel() {
        return idSolicitacaoSel;
    }

    public void setIdSolicitacaoSel(final String idSolicitacaoSel) {
        this.idSolicitacaoSel = idSolicitacaoSel;
    }

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(final Integer idProblema) {
        this.idProblema = idProblema;
    }

    public String getAtribuidaCompartilhada() {
        return atribuidaCompartilhada;
    }

    public void setAtribuidaCompartilhada(final String atribuidaCompartilhada) {
        this.atribuidaCompartilhada = atribuidaCompartilhada;
    }

    /**
     * @return the idRequisicaoSel
     */
    public String getIdProblemaSel() {
        return idProblemaSel;
    }

    /**
     * @param idRequisicaoSel
     *            the idRequisicaoSel to set
     */
    public void setIdProblemaSel(final String idProblemaSel) {
        this.idProblemaSel = idProblemaSel;
    }

}
