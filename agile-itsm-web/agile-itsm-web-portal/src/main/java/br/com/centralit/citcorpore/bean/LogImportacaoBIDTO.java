package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class LogImportacaoBIDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idLogImportacao;
    private Timestamp dataHoraInicio;
    private Timestamp dataHoraFim;
    private String status;
    private final StringBuilder detalhamento;
    private String tipo;
    private Integer idConexaoBI;
    private Integer paginaSelecionada;

    public LogImportacaoBIDTO() {
        super();
        detalhamento = new StringBuilder();
    }

    public Integer getIdLogImportacao() {
        return idLogImportacao;
    }

    public void setIdLogImportacao(final Integer idLogImportacao) {
        this.idLogImportacao = idLogImportacao;
    }

    public Timestamp getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(final Timestamp dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Timestamp getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(final Timestamp dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getDetalhamento() {
        return detalhamento.toString();
    }

    public void setDetalhamento(final String detalhamento) {
        this.clear();
        this.concatDetalhamento(detalhamento);
    }

    public void concatDetalhamento(final String detalhamento) {
        if (this.detalhamento.length() > 0) {
            this.detalhamento.append("\r\n");
        }
        this.detalhamento.append(detalhamento);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdConexaoBI() {
        return idConexaoBI;
    }

    public void setIdConexaoBI(final Integer idConexaoBI) {
        this.idConexaoBI = idConexaoBI;
    }

    public Integer getPaginaSelecionada() {
        return paginaSelecionada;
    }

    public void setPaginaSelecionada(final Integer paginaSelecionada) {
        this.paginaSelecionada = paginaSelecionada;
    }

    public void clear() {
        detalhamento.delete(0, detalhamento.length());
    }

}
