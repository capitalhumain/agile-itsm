package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author david.silva
 *
 */
public class ParceiroDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idParceiro;
    private String nome;
    private String razaoSocial;
    private String tipoPessoa;
    private Date dataAlteracao;
    private String ativo;
    private String situacao;
    private String fornecedor;

    public Integer getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(final Integer idParceiro) {
        this.idParceiro = idParceiro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(final String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(final String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(final Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(final String ativo) {
        this.ativo = ativo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(final String fornecedor) {
        this.fornecedor = fornecedor;
    }

}
