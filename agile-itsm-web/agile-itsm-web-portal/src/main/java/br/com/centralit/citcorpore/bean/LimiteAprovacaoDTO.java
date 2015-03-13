package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class LimiteAprovacaoDTO extends BaseEntity {

    private Integer idLimiteAprovacao;
    private String identificacao;
    private String tipoLimitePorValor;
    private String abrangenciaCentroResultado;

    private Collection<ValorLimiteAprovacaoDTO> colValores;
    private Integer[] idProcessoNegocio;
    private Integer[] idNivelAutoridade;

    protected Double valorMensalUsoInterno = 0.0;
    protected Double valorAnualUsoInterno = 0.0;
    protected Double valorMensalAtendCliente = 0.0;
    protected Double valorAnualAtendCliente = 0.0;

    private Collection<ProcessoNegocioDTO> colProcessos;
    private boolean valido;

    public boolean isValido() {
        return valido;
    }

    public void setValido(final boolean valido) {
        this.valido = valido;
    }

    public Integer getIdLimiteAprovacao() {
        return idLimiteAprovacao;
    }

    public void setIdLimiteAprovacao(final Integer parm) {
        idLimiteAprovacao = parm;
    }

    public String getTipoLimitePorValor() {
        return tipoLimitePorValor;
    }

    public void setTipoLimitePorValor(final String parm) {
        tipoLimitePorValor = parm;
    }

    public String getAbrangenciaCentroResultado() {
        return abrangenciaCentroResultado;
    }

    public void setAbrangenciaCentroResultado(final String parm) {
        abrangenciaCentroResultado = parm;
    }

    public Collection<ValorLimiteAprovacaoDTO> getColValores() {
        return colValores;
    }

    public void setColValores(final Collection<ValorLimiteAprovacaoDTO> colValores) {
        this.colValores = colValores;
    }

    public Integer[] getIdProcessoNegocio() {
        return idProcessoNegocio;
    }

    public void setIdProcessoNegocio(final Integer[] idProcessoNegocio) {
        this.idProcessoNegocio = idProcessoNegocio;
    }

    public Integer[] getIdNivelAutoridade() {
        return idNivelAutoridade;
    }

    public void setIdNivelAutoridade(final Integer[] idNivelAutoridade) {
        this.idNivelAutoridade = idNivelAutoridade;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(final String identificacao) {
        this.identificacao = identificacao;
    }

    public Collection<ProcessoNegocioDTO> getColProcessos() {
        return colProcessos;
    }

    public void setColProcessos(final Collection<ProcessoNegocioDTO> colProcessos) {
        this.colProcessos = colProcessos;
    }

    public Double getValorMensalUsoInterno() {
        return valorMensalUsoInterno;
    }

    public void setValorMensalUsoInterno(final Double valorMensalUsoInterno) {
        this.valorMensalUsoInterno = valorMensalUsoInterno;
    }

    public Double getValorAnualUsoInterno() {
        return valorAnualUsoInterno;
    }

    public void setValorAnualUsoInterno(final Double valorAnualUsoInterno) {
        this.valorAnualUsoInterno = valorAnualUsoInterno;
    }

    public Double getValorMensalAtendCliente() {
        return valorMensalAtendCliente;
    }

    public void setValorMensalAtendCliente(final Double valorMensalAtendCliente) {
        this.valorMensalAtendCliente = valorMensalAtendCliente;
    }

    public Double getValorAnualAtendCliente() {
        return valorAnualAtendCliente;
    }

    public void setValorAnualAtendCliente(final Double valorAnualAtendCliente) {
        this.valorAnualAtendCliente = valorAnualAtendCliente;
    }

    public Double getValorMensal() {
        return valorMensalUsoInterno + valorMensalAtendCliente;
    }

    public Double getValorAnual() {
        return valorAnualUsoInterno + valorAnualAtendCliente;
    }

}
