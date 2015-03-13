package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class CriterioAvaliacaoFornecedorDTO extends BaseEntity {

    private Integer idAvaliacaoFornecedor;

    private Integer idCriterio;

    private String valor;

    private Integer valorInteger;

    private String obs;

    private String descricao;

    public Integer getIdAvaliacaoFornecedor() {
        return idAvaliacaoFornecedor;
    }

    public void setIdAvaliacaoFornecedor(final Integer parm) {
        idAvaliacaoFornecedor = parm;
    }

    public Integer getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(final Integer parm) {
        idCriterio = parm;
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param obs
     *            the obs to set
     */
    public void setObs(final String obs) {
        this.obs = obs;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor
     *            the valor to set
     */
    public void setValor(final String valor) {
        this.valor = valor;
    }

    /**
     * @return the valorInteger
     */
    public Integer getValorInteger() {
        return valorInteger;
    }

    /**
     * @param valorInteger
     *            the valorInteger to set
     */
    public void setValorInteger(final Integer valorInteger) {
        this.valorInteger = valorInteger;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao
     *            the descricao to set
     */
    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

}
