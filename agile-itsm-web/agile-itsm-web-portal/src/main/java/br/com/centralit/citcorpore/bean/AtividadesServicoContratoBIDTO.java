package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class AtividadesServicoContratoBIDTO extends BaseEntity {

    private Integer idAtividadeServicoContrato;
    private Integer idServicoContrato;
    private String descricaoAtividade;
    private String obsAtividade;
    private String complexidade;
    private Double custoAtividade;
    private String deleted;
    private Double hora;
    private Integer quantidade;
    private String periodo;
    private String formula;
    private String contabilizar;
    private Integer idServicoContratoContabil;
    private String nomeServico;
    private String tipoCusto;
    private Integer idConexaoBI;

    public Integer getIdAtividadeServicoContrato() {
        return idAtividadeServicoContrato;
    }

    public void setIdAtividadeServicoContrato(final Integer parm) {
        idAtividadeServicoContrato = parm;
    }

    public Integer getIdServicoContrato() {
        return idServicoContrato;
    }

    public void setIdServicoContrato(final Integer parm) {
        idServicoContrato = parm;
    }

    public String getDescricaoAtividade() {
        return descricaoAtividade;
    }

    public void setDescricaoAtividade(final String parm) {
        descricaoAtividade = parm;
    }

    public Double getCustoAtividade() {
        return custoAtividade;
    }

    public void setCustoAtividade(final Double parm) {
        custoAtividade = parm;
    }

    public String getObsAtividade() {
        return obsAtividade;
    }

    public void setObsAtividade(final String parm) {
        obsAtividade = parm;
    }

    public String getDeleted() {
        if (deleted == null || deleted.trim().equalsIgnoreCase("")) {
            return "N";
        }
        return deleted;
    }

    public void setDeleted(final String parm) {
        deleted = parm;
    }

    public String getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(final String complexidade) {
        this.complexidade = complexidade;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(final String formula) {
        this.formula = formula;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(final String periodo) {
        this.periodo = periodo;
    }

    public Double getHora() {
        return hora;
    }

    public void setHora(final Double hora) {
        this.hora = hora;
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

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getTipoCusto() {
        return tipoCusto;
    }

    public void setTipoCusto(final String tipoCusto) {
        this.tipoCusto = tipoCusto;
    }

    public Integer getIdConexaoBI() {
        return idConexaoBI;
    }

    public void setIdConexaoBI(final Integer idConexaoBI) {
        this.idConexaoBI = idConexaoBI;
    }

}
