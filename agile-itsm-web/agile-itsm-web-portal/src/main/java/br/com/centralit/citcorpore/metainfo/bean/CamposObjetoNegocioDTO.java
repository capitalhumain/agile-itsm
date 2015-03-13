package br.com.centralit.citcorpore.metainfo.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class CamposObjetoNegocioDTO extends BaseEntity {

    private Integer idCamposObjetoNegocio;
    private Integer idObjetoNegocio;
    private String nome;
    private String nomeDB;
    private String pk;
    private String sequence;
    private String unico;
    private String tipoDB;
    private Integer precisionDB;
    private String tipoNegocio;
    private String obrigatorio;
    private String situacao;
    private String filtro;
    private String descricao;
    private String order;
    private String formula;
    private String nomeTabelaDB;
    private ReturnLookupDTO returnLookupDTO;
    private Object value;

    public Integer getIdCamposObjetoNegocio() {
        return idCamposObjetoNegocio;
    }

    public void setIdCamposObjetoNegocio(final Integer parm) {
        idCamposObjetoNegocio = parm;
    }

    public Integer getIdObjetoNegocio() {
        return idObjetoNegocio;
    }

    public void setIdObjetoNegocio(final Integer parm) {
        idObjetoNegocio = parm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String parm) {
        nome = parm;
    }

    public String getNomeDB() {
        return nomeDB;
    }

    public void setNomeDB(final String parm) {
        nomeDB = parm;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(final String parm) {
        pk = parm;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(final String parm) {
        sequence = parm;
    }

    public String getTipoDB() {
        return tipoDB;
    }

    public void setTipoDB(final String parm) {
        tipoDB = parm;
    }

    public String getObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(final String parm) {
        obrigatorio = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public String getUnico() {
        return unico;
    }

    public void setUnico(final String unico) {
        this.unico = unico;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(final String filtro) {
        this.filtro = filtro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(final String order) {
        this.order = order;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(final String formula) {
        this.formula = formula;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(final Object value) {
        this.value = value;
    }

    public ReturnLookupDTO getReturnLookupDTO() {
        return returnLookupDTO;
    }

    public void setReturnLookupDTO(final ReturnLookupDTO returnLookupDTO) {
        this.returnLookupDTO = returnLookupDTO;
    }

    public String getNomeTabelaDB() {
        return nomeTabelaDB;
    }

    public void setNomeTabelaDB(final String nomeTabelaDB) {
        this.nomeTabelaDB = nomeTabelaDB;
    }

    public String getTipoNegocio() {
        return tipoNegocio;
    }

    public void setTipoNegocio(final String tipoNegocio) {
        this.tipoNegocio = tipoNegocio;
    }

    /**
     * @return the precisionDB
     */
    public Integer getPrecisionDB() {
        return precisionDB;
    }

    /**
     * @param precisionDB
     *            the precisionDB to set
     */
    public void setPrecisionDB(final Integer precisionDB) {
        this.precisionDB = precisionDB;
    }

}
