package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.UtilFormatacao;
import br.com.citframework.util.UtilHTML;
import br.com.citframework.util.UtilStrings;

public class PerfilContratoDTO extends BaseEntity {

    private Integer idPerfilContrato;
    private Integer idContrato;
    private String nomePerfilContrato;
    private Double custoHora;
    private Double tempoAlocMinutosTotal;
    private Double custoTotal;
    private Double custoTotalPerfil;
    private String deleted;

    public Integer getIdPerfilContrato() {
        return idPerfilContrato;
    }

    public void setIdPerfilContrato(final Integer parm) {
        idPerfilContrato = parm;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer parm) {
        idContrato = parm;
    }

    public String getNomePerfilContrato() {
        return nomePerfilContrato;
    }

    public String getNomePerfilContratoHTMLEncoded() {
        return UtilHTML.encodeHTML(UtilStrings.nullToVazio(nomePerfilContrato));
    }

    public void setNomePerfilContrato(final String parm) {
        nomePerfilContrato = parm;
    }

    public Double getCustoHora() {
        return custoHora;
    }

    public void setCustoHora(final Double parm) {
        custoHora = parm;
    }

    public Double getTempoAlocMinutosTotal() {
        if (tempoAlocMinutosTotal == null) {
            return new Double(0);
        }
        return tempoAlocMinutosTotal;
    }

    public String getTempoAlocHorasTotalStr() {
        if (tempoAlocMinutosTotal == null) {
            return "";
        }
        final double x = tempoAlocMinutosTotal.doubleValue() / 60;
        return UtilFormatacao.formatDouble(x, 2);
    }

    public String getTempoAlocHorasTotalStrHTMLEncoded() {
        if (tempoAlocMinutosTotal == null) {
            return "";
        }
        final double x = tempoAlocMinutosTotal.doubleValue() / 60;
        return UtilHTML.encodeHTML(UtilStrings.nullToVazio(UtilFormatacao.formatDouble(x, 2)));
    }

    public void setTempoAlocMinutosTotal(final Double tempoAlocMinutosTotal) {
        this.tempoAlocMinutosTotal = tempoAlocMinutosTotal;
    }

    public Double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(final Double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public Double getCustoTotalPerfil() {
        if (custoTotalPerfil == null) {
            return new Double(0);
        }
        return custoTotalPerfil;
    }

    public String getCustoTotalPerfilStr() {
        if (custoTotalPerfil == null) {
            return "";
        }
        return UtilFormatacao.formatDouble(custoTotalPerfil, 2);
    }

    public String getCustoTotalPerfilStrHTMLEncoded() {
        if (custoTotalPerfil == null) {
            return "";
        }
        return UtilHTML.encodeHTML(UtilStrings.nullToVazio(UtilFormatacao.formatDouble(custoTotalPerfil, 2)));
    }

    public void setCustoTotalPerfil(final Double custoTotalPerfil) {
        this.custoTotalPerfil = custoTotalPerfil;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

}
