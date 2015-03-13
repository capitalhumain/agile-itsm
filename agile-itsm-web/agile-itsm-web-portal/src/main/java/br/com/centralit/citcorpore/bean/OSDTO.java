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

import java.sql.Date;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class OSDTO extends BaseEntity {

    private static final long serialVersionUID = 4291019354197500491L;

    public static Integer TODAS = 0;
    public static Integer EM_CRIACAO = 1;
    public static Integer SOLICITADA = 2;
    public static Integer AUTORIZADA = 3;
    public static Integer APROVADA = 4;
    public static Integer EM_EXECUCAO = 5;
    public static Integer EXECUTADA = 6;
    public static Integer CANCELADA = 7;

    private Integer idOS;
    private Integer idContrato;
    private Integer idClassificacaoOS;
    private Integer ano;
    private String numero;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;
    private String demanda;
    private String objetivo;
    private Integer situacaoOS;

    private Integer idServicoContrato;
    private String nomeServico;

    private Double custoOS;
    private Double glosaOS;
    private Double executadoOS;

    private String nomeAreaRequisitante;
    private String obsFinalizacao;

    private Integer sequenciaOS;
    private Integer quantidadeGlosasAnterior;
    private Collection colItens;
    private String tituloAtividade;
    private Integer idAtividadePeriodica;

    private Integer idANS;
    private Integer seqANS;
    private String fieldANS;

    private Double percentualGlosa;
    private Double valorGlosa;
    private Double totalQuantidadeDemanda;
    private Double totalUstPrevista;
    private Double totalUstRealizadas;
    private Double totalUstGlosadas;
    private Double totalUstTotal;
    private Double totalValorAutorizado;
    private Double ustRealizada;
    private Double realizado;
    private Double ustTotal;
    private Double valorAutorizado;
    private Double cotacaoMoeda;
    private Double totalUstPrevistoPerido;

    private String servico;
    private Double quantidadeDemanda;
    private Double glosaAtividade;
    private Double custoGlosa;
    private Double totalglosasAtividades;

    private Object listaFaturaApuracaoANS;

    private Integer idOSPai;
    private Date dataInicioExecucao;
    private Date dataFimExecucao;
    private Integer quantidade;
    private String flagGlosa;

    private Integer idServicoContratoContabil;
    private Integer idSolicitacaoServico;

    private Date dataEmissao;

    private Integer idGrupoAssinatura;

    public Integer getIdOS() {
        return idOS;
    }

    public void setIdOS(final Integer parm) {
        idOS = parm;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer parm) {
        idContrato = parm;
    }

    public Integer getIdClassificacaoOS() {
        return idClassificacaoOS;
    }

    public void setIdClassificacaoOS(final Integer parm) {
        idClassificacaoOS = parm;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(final Integer parm) {
        ano = parm;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(final String parm) {
        numero = parm;
    }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final java.sql.Date parm) {
        dataInicio = parm;
    }

    public java.sql.Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final java.sql.Date parm) {
        dataFim = parm;
    }

    public String getDemanda() {
        return demanda;
    }

    public void setDemanda(final String parm) {
        demanda = parm;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(final String parm) {
        objetivo = parm;
    }

    public Collection getColItens() {
        return colItens;
    }

    public void setColItens(final Collection colItens) {
        this.colItens = colItens;
    }

    public Integer getSituacaoOS() {
        return situacaoOS;
    }

    public String getDescricaoSituacaoOS() {
        if (this.getSituacaoOS() == null) {
            return "";
        }
        if (this.getSituacaoOS().intValue() == 1) {
            return "Em criação";
        }
        if (this.getSituacaoOS().intValue() == 2) {
            return "Solicitada";
        }
        if (this.getSituacaoOS().intValue() == 3) {
            return "Autorizada";
        }
        if (this.getSituacaoOS().intValue() == 4) {
            return "Aprovada";
        }
        if (this.getSituacaoOS().intValue() == 5) {
            return "Em execução";
        }
        if (this.getSituacaoOS().intValue() == 6) {
            return "Executada";
        }
        if (this.getSituacaoOS().intValue() == 7) {
            return "Cancelada";
        }
        return "";
    }

    public void setSituacaoOS(final Integer situacaoOS) {
        this.situacaoOS = situacaoOS;
    }

    public Integer getIdServicoContrato() {
        return idServicoContrato;
    }

    public void setIdServicoContrato(final Integer idServicoContrato) {
        this.idServicoContrato = idServicoContrato;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public Double getCustoOS() {
        return custoOS;
    }

    public void setCustoOS(final Double custoOS) {
        this.custoOS = custoOS;
    }

    public Double getGlosaOS() {
        return glosaOS;
    }

    public void setGlosaOS(final Double glosaOS) {
        this.glosaOS = glosaOS;
    }

    public String getNomeAreaRequisitante() {
        return nomeAreaRequisitante;
    }

    public void setNomeAreaRequisitante(final String nomeAreaRequisitante) {
        this.nomeAreaRequisitante = nomeAreaRequisitante;
    }

    public String getObsFinalizacao() {
        return obsFinalizacao;
    }

    public void setObsFinalizacao(final String obsFinalizacao) {
        this.obsFinalizacao = obsFinalizacao;
    }

    public Double getExecutadoOS() {
        return executadoOS;
    }

    public void setExecutadoOS(final Double executadoOS) {
        this.executadoOS = executadoOS;
    }

    /**
     * @return the sequenciaOS
     */
    public Integer getSequenciaOS() {
        return sequenciaOS;
    }

    /**
     * @param sequenciaOS
     *            the sequenciaOS to set
     */
    public void setSequenciaOS(final Integer sequenciaOS) {
        this.sequenciaOS = sequenciaOS;
    }

    public Integer getQuantidadeGlosasAnterior() {
        return quantidadeGlosasAnterior;
    }

    public void setQuantidadeGlosasAnterior(final Integer quantidadeGlosasAnterior) {
        this.quantidadeGlosasAnterior = quantidadeGlosasAnterior;
    }

    /**
     * @return the tituloAtividade
     */
    public String getTituloAtividade() {
        return tituloAtividade;
    }

    /**
     * @param tituloAtividade
     *            the tituloAtividade to set
     */
    public void setTituloAtividade(final String tituloAtividade) {
        this.tituloAtividade = tituloAtividade;
    }

    /**
     * @return the idAtividadePeriodica
     */
    public Integer getIdAtividadePeriodica() {
        return idAtividadePeriodica;
    }

    /**
     * @param idAtividadePeriodica
     *            the idAtividadePeriodica to set
     */
    public void setIdAtividadePeriodica(final Integer idAtividadePeriodica) {
        this.idAtividadePeriodica = idAtividadePeriodica;
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

    /**
     * @return the percentualGlosa
     */
    public Double getPercentualGlosa() {
        return percentualGlosa;
    }

    /**
     * @param percentualGlosa
     *            the percentualGlosa to set
     */
    public void setPercentualGlosa(final Double percentualGlosa) {
        this.percentualGlosa = percentualGlosa;
    }

    /**
     * @return the valorGlosa
     */
    public Double getValorGlosa() {
        return valorGlosa;
    }

    /**
     * @param valorGlosa
     *            the valorGlosa to set
     */
    public void setValorGlosa(final Double valorGlosa) {
        this.valorGlosa = valorGlosa;
    }

    /**
     * @return the totalQuantidadeDemanda
     */
    public Double getTotalQuantidadeDemanda() {
        return totalQuantidadeDemanda;
    }

    /**
     * @param totalQuantidadeDemanda
     *            the totalQuantidadeDemanda to set
     */
    public void setTotalQuantidadeDemanda(final Double totalQuantidadeDemanda) {
        this.totalQuantidadeDemanda = totalQuantidadeDemanda;
    }

    /**
     * @return the totalUstPrevista
     */
    public Double getTotalUstPrevista() {
        return totalUstPrevista;
    }

    /**
     * @param totalUstPrevista
     *            the totalUstPrevista to set
     */
    public void setTotalUstPrevista(final Double totalUstPrevista) {
        this.totalUstPrevista = totalUstPrevista;
    }

    /**
     * @return the totalUstRealizadas
     */
    public Double getTotalUstRealizadas() {
        return totalUstRealizadas;
    }

    /**
     * @param totalUstRealizadas
     *            the totalUstRealizadas to set
     */
    public void setTotalUstRealizadas(final Double totalUstRealizadas) {
        this.totalUstRealizadas = totalUstRealizadas;
    }

    /**
     * @return the totalUstGlosadas
     */
    public Double getTotalUstGlosadas() {
        return totalUstGlosadas;
    }

    /**
     * @param totalUstGlosadas
     *            the totalUstGlosadas to set
     */
    public void setTotalUstGlosadas(final Double totalUstGlosadas) {
        this.totalUstGlosadas = totalUstGlosadas;
    }

    /**
     * @return the totalUstTotal
     */
    public Double getTotalUstTotal() {
        return totalUstTotal;
    }

    /**
     * @param totalUstTotal
     *            the totalUstTotal to set
     */
    public void setTotalUstTotal(final Double totalUstTotal) {
        this.totalUstTotal = totalUstTotal;
    }

    /**
     * @return the totalValorAutorizado
     */
    public Double getTotalValorAutorizado() {
        return totalValorAutorizado;
    }

    /**
     * @param totalValorAutorizado
     *            the totalValorAutorizado to set
     */
    public void setTotalValorAutorizado(final Double totalValorAutorizado) {
        this.totalValorAutorizado = totalValorAutorizado;
    }

    /**
     * @return the ustRealizada
     */
    public Double getUstRealizada() {
        return ustRealizada;
    }

    /**
     * @param ustRealizada
     *            the ustRealizada to set
     */
    public void setUstRealizada(final Double ustRealizada) {
        this.ustRealizada = ustRealizada;
    }

    /**
     * @return the realizado
     */
    public Double getRealizado() {
        return realizado;
    }

    /**
     * @param realizado
     *            the realizado to set
     */
    public void setRealizado(final Double realizado) {
        this.realizado = realizado;
    }

    /**
     * @return the ustTotal
     */
    public Double getUstTotal() {
        return ustTotal;
    }

    /**
     * @param ustTotal
     *            the ustTotal to set
     */
    public void setUstTotal(final Double ustTotal) {
        this.ustTotal = ustTotal;
    }

    /**
     * @return the valorAutorizado
     */
    public Double getValorAutorizado() {
        return valorAutorizado;
    }

    /**
     * @param valorAutorizado
     *            the valorAutorizado to set
     */
    public void setValorAutorizado(final Double valorAutorizado) {
        this.valorAutorizado = valorAutorizado;
    }

    /**
     * @return the cotacaoMoeda
     */
    public Double getCotacaoMoeda() {
        return cotacaoMoeda;
    }

    /**
     * @param cotacaoMoeda
     *            the cotacaoMoeda to set
     */
    public void setCotacaoMoeda(final Double cotacaoMoeda) {
        this.cotacaoMoeda = cotacaoMoeda;
    }

    /**
     * @return the totalUstPrevistoPerido
     */
    public Double getTotalUstPrevistoPerido() {
        return totalUstPrevistoPerido;
    }

    /**
     * @param totalUstPrevistoPerido
     *            the totalUstPrevistoPerido to set
     */
    public void setTotalUstPrevistoPerido(final Double totalUstPrevistoPerido) {
        this.totalUstPrevistoPerido = totalUstPrevistoPerido;
    }

    /**
     * @return the servico
     */
    public String getServico() {
        return servico;
    }

    /**
     * @param servico
     *            the servico to set
     */
    public void setServico(final String servico) {
        this.servico = servico;
    }

    /**
     * @return the quantidadeDemanda
     */
    public Double getQuantidadeDemanda() {
        return quantidadeDemanda;
    }

    /**
     * @param quantidadeDemanda
     *            the quantidadeDemanda to set
     */
    public void setQuantidadeDemanda(final Double quantidadeDemanda) {
        this.quantidadeDemanda = quantidadeDemanda;
    }

    /**
     * @return the glosaAtividade
     */
    public Double getGlosaAtividade() {
        return glosaAtividade;
    }

    /**
     * @param glosaAtividade
     *            the glosaAtividade to set
     */
    public void setGlosaAtividade(final Double glosaAtividade) {
        this.glosaAtividade = glosaAtividade;
    }

    /**
     * @return the custoGlosa
     */
    public Double getCustoGlosa() {
        return custoGlosa;
    }

    /**
     * @param custoGlosa
     *            the custoGlosa to set
     */
    public void setCustoGlosa(final Double custoGlosa) {
        this.custoGlosa = custoGlosa;
    }

    /**
     * @return the totalglosasAtividades
     */
    public Double getTotalglosasAtividades() {
        return totalglosasAtividades;
    }

    /**
     * @param totalglosasAtividades
     *            the totalglosasAtividades to set
     */
    public void setTotalglosasAtividades(final Double totalglosasAtividades) {
        this.totalglosasAtividades = totalglosasAtividades;
    }

    /**
     * @return the listaFaturaApuracaoANS
     */
    public Object getListaFaturaApuracaoANS() {
        return listaFaturaApuracaoANS;
    }

    /**
     * @param listaFaturaApuracaoANS
     *            the listaFaturaApuracaoANS to set
     */
    public void setListaFaturaApuracaoANS(final Object listaFaturaApuracaoANS) {
        this.listaFaturaApuracaoANS = listaFaturaApuracaoANS;
    }

    public Integer getIdOSPai() {
        return idOSPai;
    }

    public void setIdOSPai(final Integer idOSPai) {
        this.idOSPai = idOSPai;
    }

    public Date getDataInicioExecucao() {
        return dataInicioExecucao;
    }

    public void setDataInicioExecucao(final Date dataInicioExecucao) {
        this.dataInicioExecucao = dataInicioExecucao;
    }

    public Date getDataFimExecucao() {
        return dataFimExecucao;
    }

    public void setDataFimExecucao(final Date dataFimExecucao) {
        this.dataFimExecucao = dataFimExecucao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getFlagGlosa() {
        return flagGlosa;
    }

    public void setFlagGlosa(final String flagGlosa) {
        this.flagGlosa = flagGlosa;
    }

    public Integer getIdServicoContratoContabil() {
        return idServicoContratoContabil;
    }

    public void setIdServicoContratoContabil(final Integer idServicoContratoContabil) {
        this.idServicoContratoContabil = idServicoContratoContabil;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(final Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Integer getIdGrupoAssinatura() {
        return idGrupoAssinatura;
    }

    public void setIdGrupoAssinatura(final Integer idGrupoAssinatura) {
        this.idGrupoAssinatura = idGrupoAssinatura;
    }

}
