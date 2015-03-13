/**
 *
 * ************************************************************************************************************
 *
 * Dependentes: BI Citsmart
 *
 * Obs:
 * Qualquer alteração nesta tabela deverá ser informada aos responsáveis pelo desenvolvimento do BI Citsmart.
 * O database do BI Citsmart precisa ter suas tabelas atualizadas de acordo com as mudanças nesta tabela.
 *
 * ************************************************************************************************************
 *
 */

package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class GlosaOSDTO extends BaseEntity {

    private Integer idGlosaOS;
    private Integer idOs;
    private java.sql.Date dataCriacao;
    private java.sql.Date dataUltModificacao;
    private String descricaoGlosa;
    private String ocorrencias;
    private Double percAplicado;
    private Double custoGlosa;
    private Double numeroOcorrencias;
    private Integer sequencia;

    public Integer getIdGlosaOS() {
        return idGlosaOS;
    }

    public void setIdGlosaOS(final Integer parm) {
        idGlosaOS = parm;
    }

    public Integer getIdOs() {
        return idOs;
    }

    public void setIdOs(final Integer parm) {
        idOs = parm;
    }

    public java.sql.Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(final java.sql.Date parm) {
        dataCriacao = parm;
    }

    public java.sql.Date getDataUltModificacao() {
        return dataUltModificacao;
    }

    public void setDataUltModificacao(final java.sql.Date parm) {
        dataUltModificacao = parm;
    }

    public String getDescricaoGlosa() {
        return descricaoGlosa;
    }

    public void setDescricaoGlosa(final String parm) {
        descricaoGlosa = parm;
    }

    public String getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(final String parm) {
        ocorrencias = parm;
    }

    public Double getPercAplicado() {
        return percAplicado;
    }

    public void setPercAplicado(final Double parm) {
        percAplicado = parm;
    }

    public Double getCustoGlosa() {
        return custoGlosa;
    }

    public void setCustoGlosa(final Double parm) {
        custoGlosa = parm;
    }

    public Double getNumeroOcorrencias() {
        return numeroOcorrencias;
    }

    public void setNumeroOcorrencias(final Double parm) {
        numeroOcorrencias = parm;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(final Integer sequencia) {
        this.sequencia = sequencia;
    }

}
