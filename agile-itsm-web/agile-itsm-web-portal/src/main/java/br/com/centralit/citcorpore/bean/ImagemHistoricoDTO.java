package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class ImagemHistoricoDTO extends BaseEntity {

    private static final long serialVersionUID = 6113478116221550410L;

    private Integer idImagem;
    private Date data;
    private String nomeArquivo;
    private String observacao;
    private Integer idContrato;
    private Integer idProfissional;
    private Integer idEmpresa;
    private String aba;

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(final Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(final Integer idImagem) {
        this.idImagem = idImagem;
    }

    public Integer getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(final Integer idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(final String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(final String observacao) {
        this.observacao = observacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(final Date data) {
        this.data = data;
    }

    public String getAba() {
        return aba;
    }

    public void setAba(final String aba) {
        this.aba = aba;
    }

    public String getKey() {
        if (this.getIdImagem() == null) {
            return "NULL";
        }
        return this.getIdImagem().toString();
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

}
