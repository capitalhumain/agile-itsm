package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class JustificativaRequisicaoLiberacaoDTO extends BaseEntity {

    private Integer idJustificativaLiberacao;

    private String descricaoJustificativa;

    private String suspensao;

    private String situacao;

    private String aprovacao;

    private String deleted;

    /**
     * @return the suspensao
     */
    public String getSuspensao() {
        return suspensao;
    }

    /**
     * @param suspensao
     *            the suspensao to set
     */
    public void setSuspensao(final String suspensao) {
        this.suspensao = suspensao;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao
     *            the situacao to set
     */
    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the aprovacao
     */
    public String getAprovacao() {
        return aprovacao;
    }

    /**
     * @param aprovacao
     *            the aprovacao to set
     */
    public void setAprovacao(final String aprovacao) {
        this.aprovacao = aprovacao;
    }

    /**
     * @return the deleted
     */
    public String getDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     *            the deleted to set
     */
    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

    /**
     * @return the descricaoJustificativa
     */
    public String getDescricaoJustificativa() {
        return descricaoJustificativa;
    }

    /**
     * @param descricaoJustificativa
     *            the descricaoJustificativa to set
     */
    public void setDescricaoJustificativa(final String descricaoJustificativa) {
        this.descricaoJustificativa = descricaoJustificativa;
    }

    /**
     * @return the idJustificativaLiberacao
     */
    public Integer getIdJustificativaLiberacao() {
        return idJustificativaLiberacao;
    }

    /**
     * @param idJustificativaLiberacao
     *            the idJustificativaLiberacao to set
     */
    public void setIdJustificativaLiberacao(final Integer idJustificativaLiberacao) {
        this.idJustificativaLiberacao = idJustificativaLiberacao;
    }

}
