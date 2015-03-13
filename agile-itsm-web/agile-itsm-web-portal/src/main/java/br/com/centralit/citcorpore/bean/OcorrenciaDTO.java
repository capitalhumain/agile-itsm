package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citajax.util.CitAjaxWebUtil;
import br.com.centralit.citajax.util.JavaScriptUtil;
import br.com.citframework.util.UtilDatas;

public class OcorrenciaDTO extends BaseEntity {

    private static final long serialVersionUID = -682996086218596048L;

    private Integer idOcorrencia;
    private Integer idDemanda;
    private String ocorrencia;
    private String tipoOcorrencia;
    private String respostaOcorrencia;
    private Date data;
    private Integer idEmpregado;

    // campo estrangeiro da tabela grupo
    private String sigla;

    private String nomeEmpregado;

    public String getDataStr() {
        return UtilDatas.dateToSTR(data);
    }

    public Date getData() {
        return data;
    }

    public void setData(final Date data) {
        this.data = data;
    }

    public Integer getIdDemanda() {
        return idDemanda;
    }

    public void setIdDemanda(final Integer idDemanda) {
        this.idDemanda = idDemanda;
    }

    public Integer getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(final Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public String getOcorrenciaConv() {
        if (ocorrencia == null) {
            return "";
        }
        final String ret = JavaScriptUtil.escapeJavaScript(CitAjaxWebUtil.codificaEnter(ocorrencia));
        return ret;
    }

    public String getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(final String ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public String getRespostaOcorrenciaConv() {
        if (respostaOcorrencia == null) {
            return "";
        }
        final String ret = JavaScriptUtil.escapeJavaScript(CitAjaxWebUtil.codificaEnter(respostaOcorrencia));
        return ret;
    }

    public String getRespostaOcorrencia() {
        return respostaOcorrencia;
    }

    public void setRespostaOcorrencia(final String respostaOcorrencia) {
        this.respostaOcorrencia = respostaOcorrencia;
    }

    public String getTipoOcorrenciaStr() {
        if (tipoOcorrencia == null) {
            return "";
        }
        if (tipoOcorrencia.equalsIgnoreCase("D")) {
            return "Dúvida";
        }
        if (tipoOcorrencia.equalsIgnoreCase("O")) {
            return "Diversos";
        }
        return tipoOcorrencia;
    }

    public String getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    public void setTipoOcorrencia(final String tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    public String getNomeEmpregado() {
        return nomeEmpregado;
    }

    public void setNomeEmpregado(final String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(final String sigla) {
        this.sigla = sigla;
    }

}
