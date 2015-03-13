package br.com.centralit.citgerencial.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ParametroDTO extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -7556822228652688359L;
    private String modulo;
    private String nomeParametro;
    private Integer idEmpresa;
    private String valor;
    private String detalhamento;

    private String gravar;

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(final String detalhamento) {
        this.detalhamento = detalhamento;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(final Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(final String modulo) {
        this.modulo = modulo;
    }

    public String getNomeParametro() {
        return nomeParametro;
    }

    public void setNomeParametro(final String nomeParametro) {
        this.nomeParametro = nomeParametro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(final String valor) {
        this.valor = valor;
    }

    public String getGravar() {
        return gravar;
    }

    public void setGravar(final String gravar) {
        this.gravar = gravar;
    }

}
