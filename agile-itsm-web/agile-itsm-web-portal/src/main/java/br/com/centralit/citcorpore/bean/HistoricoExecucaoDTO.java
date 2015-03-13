package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citajax.util.JavaScriptUtil;
import br.com.citframework.util.UtilDatas;

public class HistoricoExecucaoDTO extends BaseEntity {

    private static final long serialVersionUID = 175188654082235948L;

    private Integer idHistorico;
    private Integer idExecucao;
    private Date data;
    private String situacao;
    private Integer idEmpregadoExecutor;
    private String detalhamento;
    private Long hora;
    private String nomeEmpregado;

    private Integer idDemanda;

    public String getDataStr() {
        return UtilDatas.dateToSTR(data);
    }

    public Date getData() {
        return data;
    }

    public void setData(final Date data) {
        this.data = data;
    }

    public Integer getIdEmpregadoExecutor() {
        return idEmpregadoExecutor;
    }

    public void setIdEmpregadoExecutor(final Integer idEmpregadoExecutor) {
        this.idEmpregadoExecutor = idEmpregadoExecutor;
    }

    public Integer getIdExecucao() {
        return idExecucao;
    }

    public void setIdExecucao(final Integer idExecucao) {
        this.idExecucao = idExecucao;
    }

    public Integer getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(final Integer idHistorico) {
        this.idHistorico = idHistorico;
    }

    public String getSituacaoDesc() {
        if (situacao == null) {
            return "";
        }
        if (situacao.equalsIgnoreCase("N")) {
            return "Não Iniciada";
        }
        if (situacao.equalsIgnoreCase("I")) {
            return "Em Execução";
        }
        if (situacao.equalsIgnoreCase("F")) {
            return "Finalizada";
        }
        if (situacao.equalsIgnoreCase("C")) {
            return "Paralisada - Aguard. Cliente";
        }
        if (situacao.equalsIgnoreCase("P")) {
            return "Paralisada - Interno";
        }
        if (situacao.equalsIgnoreCase("T")) {
            return "Transferida";
        }
        return situacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public String getDetalhamentoConv() {
        return JavaScriptUtil.escapeJavaScript(detalhamento);
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(final String detalhamento) {
        this.detalhamento = detalhamento;
    }

    public String getHoraStr() {
        if (hora == null) {
            return "";
        }
        final Timestamp t = new Timestamp(hora.longValue());

        final SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        final String s = format.format(t);
        return s;
    }

    public String getNomeEmpregado() {
        return nomeEmpregado;
    }

    public void setNomeEmpregado(final String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

    public Integer getIdDemanda() {
        return idDemanda;
    }

    public void setIdDemanda(final Integer idDemanda) {
        this.idDemanda = idDemanda;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(final Long hora) {
        this.hora = hora;
    }

}
