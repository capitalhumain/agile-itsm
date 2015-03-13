package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Pedro
 *
 */
public class ControleContratoVersaoDTO extends BaseEntity {

    private Integer idControleContrato;
    private Integer idCcVersao;
    private String nomeCcVersao;

    public Integer getIdControleContrato() {
        return idControleContrato;
    }

    public void setIdControleContrato(final Integer idControleContrato) {
        this.idControleContrato = idControleContrato;
    }

    public Integer getIdCcVersao() {
        return idCcVersao;
    }

    public void setIdCcVersao(final Integer idCcVersao) {
        this.idCcVersao = idCcVersao;
    }

    public String getNomeCcVersao() {
        return nomeCcVersao;
    }

    public void setNomeCcVersao(final String nomeCcVersao) {
        this.nomeCcVersao = nomeCcVersao;
    }

}
