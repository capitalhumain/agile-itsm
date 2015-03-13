package br.com.citframework.dto;

import java.sql.Date;
import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author karem.ricarte
 *
 */
public class LogDados extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long idlog;
    private Timestamp dtAtualizacao;
    private String operacao;
    private String dados;
    private Integer idUsuario;
    private String localOrigem;
    private String nomeTabela;

    /* Usado apenas para filtro de pesquisa */
    private Date dataInicio;
    private Date dataFim;

    private Timestamp dataLog;

    private String nomeUsuario;

    public Long getIdlog() {
        return idlog;
    }

    public void setIdlog(final Long idlog) {
        this.idlog = idlog;
    }

    public Timestamp getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(final Timestamp dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(final String operacao) {
        this.operacao = operacao;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(final String dados) {
        this.dados = dados;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(final Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLocalOrigem() {
        return localOrigem;
    }

    public void setLocalOrigem(final String localOrigem) {
        this.localOrigem = localOrigem;
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(final String nomeTabela) {
        this.nomeTabela = nomeTabela;
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

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(final String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Timestamp getDataLog() {
        return dataLog;
    }

    public void setDataLog(final Timestamp dataLog) {
        this.dataLog = dataLog;
    }

}
