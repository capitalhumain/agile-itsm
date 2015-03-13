/**
 *
 * ************************************************************************************************************
 *
 * Dependentes: BI Citsmart
 *
 * Obs:
 * Qualquer alteração nesta tabela deverá ser informada aos responsáveis pelo desenvolvimento do BI Citsmart.
 * O database do BI Citsmart precisa ter suas tabelas atualizadas de acordo com as mudanças nesta tabela.
 *
 * ************************************************************************************************************
 *
 */

package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.UtilI18N;

public class FaturaDTO extends BaseEntity {

    public static String EM_CRIACAO = "1";
    public static String AGUARDANDO_APROVACAO = "2";
    public static String APROVADAS = "3";
    public static String EM_RECEBIMENTO = "4";
    public static String RECEBIDAS = "5";
    public static String REJEITADAS = "6";
    public static String CANCELADA = "7";

    private Integer idFatura;
    private Integer idContrato;
    private java.sql.Date dataInicial;
    private java.sql.Date dataFinal;
    private String descricaoFatura;
    private Double valorCotacaoMoeda;
    private java.sql.Date dataCriacao;
    private java.sql.Date dataUltModificacao;
    private Double valorPrevistoSomaOS;
    private Double valorSomaGlosasOS;
    private Double valorExecutadoSomaOS;
    private String observacao;
    private String aprovacaoGestor;
    private String aprovacaoFiscal;
    private Double saldoPrevisto;
    private String situacaoFatura;

    private Integer qtdeOS;

    private Collection colItens;

    private Integer[] idOSFatura;
    private Integer idOSExcluir;

    private Integer idANS;
    private Integer seqANS;
    private String fieldANS;

    public Integer getIdFatura() {
        return idFatura;
    }

    public void setIdFatura(final Integer parm) {
        idFatura = parm;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer parm) {
        idContrato = parm;
    }

    public java.sql.Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(final java.sql.Date parm) {
        dataInicial = parm;
    }

    public java.sql.Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(final java.sql.Date parm) {
        dataFinal = parm;
    }

    public String getDescricaoFatura() {
        return descricaoFatura;
    }

    public void setDescricaoFatura(final String parm) {
        descricaoFatura = parm;
    }

    public Double getValorCotacaoMoeda() {
        return valorCotacaoMoeda;
    }

    public void setValorCotacaoMoeda(final Double parm) {
        valorCotacaoMoeda = parm;
    }

    public java.sql.Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(final java.sql.Date parm) {
        dataCriacao = parm;
    }

    public java.sql.Date getDataUltModificacao() {
        return dataUltModificacao;
    }

    public void setDataUltModificacao(final java.sql.Date parm) {
        dataUltModificacao = parm;
    }

    public Double getValorReceberOS() {
        double valorexec = 0;
        double valorglosas = 0;
        if (this.getValorExecutadoSomaOS() != null) {
            valorexec = this.getValorExecutadoSomaOS();
        }
        if (this.getValorSomaGlosasOS() != null) {
            valorglosas = this.getValorSomaGlosasOS();
        }
        return valorexec - valorglosas;
    }

    public Double getValorPrevistoSomaOS() {
        return valorPrevistoSomaOS;
    }

    public void setValorPrevistoSomaOS(final Double parm) {
        valorPrevistoSomaOS = parm;
    }

    public Double getValorSomaGlosasOS() {
        return valorSomaGlosasOS;
    }

    public void setValorSomaGlosasOS(final Double parm) {
        valorSomaGlosasOS = parm;
    }

    public Double getValorExecutadoSomaOS() {
        return valorExecutadoSomaOS;
    }

    public void setValorExecutadoSomaOS(final Double parm) {
        valorExecutadoSomaOS = parm;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(final String parm) {
        observacao = parm;
    }

    public String getAprovacaoGestor() {
        return aprovacaoGestor;
    }

    public void setAprovacaoGestor(final String parm) {
        aprovacaoGestor = parm;
    }

    public String getAprovacaoFiscal() {
        return aprovacaoFiscal;
    }

    public void setAprovacaoFiscal(final String parm) {
        aprovacaoFiscal = parm;
    }

    public Double getSaldoPrevisto() {
        return saldoPrevisto;
    }

    public void setSaldoPrevisto(final Double parm) {
        saldoPrevisto = parm;
    }

    public String getSituacaoFatura() {
        return situacaoFatura;
    }

    public void setSituacaoFatura(final String situacaoFatura) {
        this.situacaoFatura = situacaoFatura;
    }

    public String getDescricaoSituacaoFatura(final HttpServletRequest request) {
        if (situacaoFatura == null) {
            return "";
        }
        if (situacaoFatura.equalsIgnoreCase("1")) {
            return UtilI18N.internacionaliza(request, "perfil.criacao");
        }
        if (situacaoFatura.equalsIgnoreCase("2")) {
            return UtilI18N.internacionaliza(request, "perfil.aguardandoAprovacao");
        }
        if (situacaoFatura.equalsIgnoreCase("3")) {
            return UtilI18N.internacionaliza(request, "perfil.aprovada");
        }
        if (situacaoFatura.equalsIgnoreCase("4")) {
            return UtilI18N.internacionaliza(request, "perfil.rejeitada");
        }
        if (situacaoFatura.equalsIgnoreCase("5")) {
            return UtilI18N.internacionaliza(request, "perfil.recebimento");
        }
        if (situacaoFatura.equalsIgnoreCase("6")) {
            return UtilI18N.internacionaliza(request, "perfil.recebida");
        }
        if (situacaoFatura.equalsIgnoreCase("7")) {
            return UtilI18N.internacionaliza(request, "perfil.cancelada");
        }
        return "";
    }

    public Integer getQtdeOS() {
        return qtdeOS;
    }

    public void setQtdeOS(final Integer qtdeOS) {
        this.qtdeOS = qtdeOS;
    }

    public Collection getColItens() {
        return colItens;
    }

    public void setColItens(final Collection colItens) {
        this.colItens = colItens;
    }

    public Integer[] getIdOSFatura() {
        return idOSFatura;
    }

    public void setIdOSFatura(final Integer[] idOSFatura) {
        this.idOSFatura = idOSFatura;
    }

    public Integer getIdOSExcluir() {
        return idOSExcluir;
    }

    public void setIdOSExcluir(final Integer idOSExcluir) {
        this.idOSExcluir = idOSExcluir;
    }

    public Integer getIdANS() {
        return idANS;
    }

    public void setIdANS(final Integer idANS) {
        this.idANS = idANS;
    }

    public Integer getSeqANS() {
        return seqANS;
    }

    public void setSeqANS(final Integer seqANS) {
        this.seqANS = seqANS;
    }

    public String getFieldANS() {
        return fieldANS;
    }

    public void setFieldANS(final String fieldANS) {
        this.fieldANS = fieldANS;
    }

}
