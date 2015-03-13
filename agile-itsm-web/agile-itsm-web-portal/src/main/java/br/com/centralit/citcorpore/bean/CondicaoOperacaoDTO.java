package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author ygor.magalhaes
 *
 */
public class CondicaoOperacaoDTO extends BaseEntity {

    private Integer idCondicaoOperacao;
    private Integer idEmpresa;
    private String nomeCondicaoOperacao;
    private Date dataInicio;
    private Date dataFim;

    public Integer getIdCondicaoOperacao() {
        return idCondicaoOperacao;
    }

    public void setIdCondicaoOperacao(final Integer idCondicaoOperacao) {
        this.idCondicaoOperacao = idCondicaoOperacao;
    }

    public String getNomeCondicaoOperacao() {
        return nomeCondicaoOperacao;
    }

    public void setNomeCondicaoOperacao(final String nomeCondicaoOperacao) {
        this.nomeCondicaoOperacao = nomeCondicaoOperacao;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(final Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

}
