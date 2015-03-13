package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class RequisicaoLiberacaoMidiaDTO extends BaseEntity {

    private Integer idRequisicaoLiberacaoMidia;
    private Integer idMidiaSoftware;
    private Integer idRequisicaoLiberacao;
    private String nomeMidia;
    private Date dataFim;

    private static final long serialVersionUID = 1L;

    /**
     * @return the idRequisicaoLiberacaoMidia
     */
    public Integer getIdRequisicaoLiberacaoMidia() {
        return idRequisicaoLiberacaoMidia;
    }

    /**
     * @param idRequisicaoLiberacaoMidia
     *            the idRequisicaoLiberacaoMidia to set
     */
    public void setIdRequisicaoLiberacaoMidia(final Integer idRequisicaoLiberacaoMidia) {
        this.idRequisicaoLiberacaoMidia = idRequisicaoLiberacaoMidia;
    }

    /**
     * @return the idRequisicaoLiberacao
     */
    public Integer getIdRequisicaoLiberacao() {
        return idRequisicaoLiberacao;
    }

    /**
     * @param idRequisicaoLiberacao
     *            the idRequisicaoLiberacao to set
     */
    public void setIdRequisicaoLiberacao(final Integer idRequisicaoLiberacao) {
        this.idRequisicaoLiberacao = idRequisicaoLiberacao;
    }

    /**
     * @return the idMidiaSoftware
     */
    public Integer getIdMidiaSoftware() {
        return idMidiaSoftware;
    }

    /**
     * @param idMidiaSoftware
     *            the idMidiaSoftware to set
     */
    public void setIdMidiaSoftware(final Integer idMidiaSoftware) {
        this.idMidiaSoftware = idMidiaSoftware;
    }

    /**
     * @return the nomeMidia
     */
    public String getNomeMidia() {
        return nomeMidia;
    }

    /**
     * @param nomeMidia
     *            the nomeMidia to set
     */
    public void setNomeMidia(final String nomeMidia) {
        this.nomeMidia = nomeMidia;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

}
