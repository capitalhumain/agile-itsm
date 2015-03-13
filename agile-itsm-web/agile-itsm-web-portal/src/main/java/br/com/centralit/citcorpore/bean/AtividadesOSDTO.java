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

public class AtividadesOSDTO extends BaseEntity {

    private Integer idAtividadesOS;
    private Integer idOS;
    private Integer sequencia;
    private Integer idAtividadeServicoContrato;
    private String descricaoAtividade;
    private String obsAtividade;
    private Double custoAtividade;
    private Double glosaAtividade;
    private Double qtdeExecutada;
    private String complexidade;
    private String numeroOS;
    private String deleted;
    private Double custoRealizado;
    private Double custos;
    private Object listaAcordoNivelServico;
    private Object listaAtividadeOs;
    private Object listaGlosasOs;
    private String formula;
    private String contabilizar;
    private Integer idServicoContratoContabil;

    public Object getListaGlosasOs() {
        return listaGlosasOs;
    }

    public void setListaGlosasOs(final Object listaGlosasOs) {
        this.listaGlosasOs = listaGlosasOs;
    }

    public Double getCustos() {
        return custos;
    }

    public void setCustos(final Double custos) {
        this.custos = custos;
    }

    public Double getCustoRealizado() {
        return custoRealizado;
    }

    public void setCustoRealizado(final Double custoRealizado) {
        this.custoRealizado = custoRealizado;
    }

    public Object getListaAtividadeOs() {
        return listaAtividadeOs;
    }

    public void setListaAtividadeOs(final Object listaAtividadeOs) {
        this.listaAtividadeOs = listaAtividadeOs;
    }

    public Object getListaAcordoNivelServico() {
        return listaAcordoNivelServico;
    }

    public void setListaAcordoNivelServico(final Object listaAcordoNivelServico) {
        this.listaAcordoNivelServico = listaAcordoNivelServico;
    }

    public Integer getIdAtividadesOS() {
        return idAtividadesOS;
    }

    public void setIdAtividadesOS(final Integer parm) {
        idAtividadesOS = parm;
    }

    public Integer getIdOS() {
        return idOS;
    }

    public void setIdOS(final Integer parm) {
        idOS = parm;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(final Integer parm) {
        sequencia = parm;
    }

    public Integer getIdAtividadeServicoContrato() {
        return idAtividadeServicoContrato;
    }

    public void setIdAtividadeServicoContrato(final Integer parm) {
        idAtividadeServicoContrato = parm;
    }

    public String getDescricaoAtividade() {
        return descricaoAtividade;
    }

    public void setDescricaoAtividade(final String parm) {
        descricaoAtividade = parm;
    }

    public String getObsAtividade() {
        return obsAtividade;
    }

    public void setObsAtividade(final String parm) {
        obsAtividade = parm;
    }

    public Double getCustoAtividade() {
        return custoAtividade;
    }

    public void setCustoAtividade(final Double parm) {
        custoAtividade = parm;
    }

    public String getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(final String parm) {
        complexidade = parm;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String parm) {
        deleted = parm;
    }

    public Double getGlosaAtividade() {
        return glosaAtividade;
    }

    public void setGlosaAtividade(final Double glosaAtividade) {
        this.glosaAtividade = glosaAtividade;
    }

    public Double getQtdeExecutada() {
        return qtdeExecutada;
    }

    public void setQtdeExecutada(final Double qtdeExecutada) {
        this.qtdeExecutada = qtdeExecutada;
    }

    public String getNumeroOS() {
        return numeroOS;
    }

    public void setNumeroOS(final String numeroOS) {
        this.numeroOS = numeroOS;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(final String formula) {
        this.formula = formula;
    }

    public String getContabilizar() {
        return contabilizar;
    }

    public void setContabilizar(final String contabilizar) {
        this.contabilizar = contabilizar;
    }

    public Integer getIdServicoContratoContabil() {
        return idServicoContratoContabil;
    }

    public void setIdServicoContratoContabil(final Integer idServicoContratoContabil) {
        this.idServicoContratoContabil = idServicoContratoContabil;
    }

}
