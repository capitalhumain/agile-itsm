package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class AvaliacaoReferenciaFornecedorDTO extends BaseEntity {

    private Integer idAvaliacaoFornecedor;

    private Integer idEmpregado;

    private String decisao;

    private String observacoes;

    private Integer sequencia;

    private String nome;

    private String telefone;

    public Integer getIdAvaliacaoFornecedor() {
        return idAvaliacaoFornecedor;
    }

    public void setIdAvaliacaoFornecedor(final Integer parm) {
        idAvaliacaoFornecedor = parm;
    }

    public String getDecisao() {
        return decisao;
    }

    public void setDecisao(final String parm) {
        decisao = parm;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(final String parm) {
        observacoes = parm;
    }

    /**
     * @return the sequencia
     */
    public Integer getSequencia() {
        return sequencia;
    }

    /**
     * @param sequencia
     *            the sequencia to set
     */
    public void setSequencia(final Integer sequencia) {
        this.sequencia = sequencia;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome
     *            the nome to set
     */
    public void setNome(final String nome) {
        this.nome = nome;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone
     *            the telefone to set
     */
    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

}
