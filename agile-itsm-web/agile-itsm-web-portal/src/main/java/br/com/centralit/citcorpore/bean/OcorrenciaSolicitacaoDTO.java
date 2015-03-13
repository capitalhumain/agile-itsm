package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.Enumerados;

public class OcorrenciaSolicitacaoDTO extends BaseEntity {

    private static final long serialVersionUID = -3055161845325828805L;

    private Integer idOcorrencia;

    private Integer idSolicitacaoServico;

    private Date dataInicio;

    private Date dataFim;

    private String categoria;

    private String origem;

    private String descricao;

    private long totalOcorrenciasAlterarcaoSlaPorSolicitacao;// campo auxiliar, não é salvo em banco

    private String ocorrencia;

    private String informacoesContato;

    private Integer tempoGasto;

    private Date dataregistro;

    private String horaregistro;

    private String registradopor;

    private Integer idItemTrabalho;

    private String dadosSolicitacao;

    private Integer idCategoriaOcorrencia;

    private Integer idOrigemOcorrencia;

    private Integer idSolicitacaoOcorrencia;

    private Integer idJustificativa;

    private String complementoJustificativa;

    private String notificarSolicitante; // Define se o solicitante será ou foi notificado no lançamento da ocorrência - [S/N]

    private String notificarResponsavel;

    // campo auxiliar, não é salvo no banco
    private Integer idUsuario;
    private String nome;
    private Integer idResponsavelAtual;
    private String urgencia;
    private Timestamp dataHoraLimite;
    private Timestamp dataHoraFim;
    private String isPortal;

    public long getTotalOcorrenciasAlterarcaoSlaPorSolicitacao() {
        return totalOcorrenciasAlterarcaoSlaPorSolicitacao;
    }

    public void setTotalOcorrenciasAlterarcaoSlaPorSolicitacao(final long totalOcorrenciasAlterarcaoSlaPorSolicitacao) {
        this.totalOcorrenciasAlterarcaoSlaPorSolicitacao = totalOcorrenciasAlterarcaoSlaPorSolicitacao;
    }

    public Integer getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(final Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCategoriaDescricao() {
        if (categoria == null) {
            return "";
        }
        for (final Enumerados.CategoriaOcorrencia c : Enumerados.CategoriaOcorrencia.values()) {
            if (categoria.equalsIgnoreCase(c.getSigla().toString())) {
                return c.getDescricao();
            }
        }
        return "";
    }

    public void setCategoria(final String categoria) {
        this.categoria = categoria;
    }

    public String getOrigem() {
        return origem;
    }

    public String getOrigemDescricao() {
        if (origem == null) {
            return "";
        }
        for (final Enumerados.OrigemOcorrencia o : Enumerados.OrigemOcorrencia.values()) {
            if (origem.equalsIgnoreCase(o.getSigla().toString())) {
                return o.getDescricao();
            }
        }
        return "";
    }

    public void setOrigem(final String origem) {
        this.origem = origem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(final String ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public String getInformacoesContato() {
        return informacoesContato;
    }

    public void setInformacoesContato(final String informacoesContato) {
        this.informacoesContato = informacoesContato;
    }

    public Integer getTempoGasto() {
        return tempoGasto;
    }

    public void setTempoGasto(final Integer tempoGasto) {
        this.tempoGasto = tempoGasto;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
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

    public Date getDataregistro() {
        return dataregistro;
    }

    public void setDataregistro(final Date dataregistro) {
        this.dataregistro = dataregistro;
    }

    public String getHoraregistro() {
        return horaregistro;
    }

    public void setHoraregistro(final String horaregistro) {
        this.horaregistro = horaregistro;
    }

    public String getRegistradopor() {
        return registradopor;
    }

    public void setRegistradopor(final String registradopor) {
        this.registradopor = registradopor;
    }

    public Integer getIdSolicitacaoOcorrencia() {
        return idSolicitacaoOcorrencia;
    }

    public void setIdSolicitacaoOcorrencia(final Integer idSolicitacaoOcorrencia) {
        this.idSolicitacaoOcorrencia = idSolicitacaoOcorrencia;
    }

    public Integer getIdItemTrabalho() {
        return idItemTrabalho;
    }

    public void setIdItemTrabalho(final Integer idItemTrabalho) {
        this.idItemTrabalho = idItemTrabalho;
    }

    public Integer getIdJustificativa() {
        return idJustificativa;
    }

    public void setIdJustificativa(final Integer idJustificativa) {
        this.idJustificativa = idJustificativa;
    }

    public String getComplementoJustificativa() {
        return complementoJustificativa;
    }

    public void setComplementoJustificativa(final String complementoJustificativa) {
        this.complementoJustificativa = complementoJustificativa;
    }

    public String getNotificarSolicitante() {
        return notificarSolicitante;
    }

    public void setNotificarSolicitante(final String notificarSolicitante) {
        this.notificarSolicitante = notificarSolicitante;
    }

    public String getDadosSolicitacao() {
        return dadosSolicitacao;
    }

    public void setDadosSolicitacao(final String dadosSolicitacao) {
        this.dadosSolicitacao = dadosSolicitacao;
    }

    public Integer getIdCategoriaOcorrencia() {
        return idCategoriaOcorrencia;
    }

    public void setIdCategoriaOcorrencia(final Integer idCategoriaOcorrencia) {
        this.idCategoriaOcorrencia = idCategoriaOcorrencia;
    }

    public Integer getIdOrigemOcorrencia() {
        return idOrigemOcorrencia;
    }

    public void setIdOrigemOcorrencia(final Integer idOrigemOcorrencia) {
        this.idOrigemOcorrencia = idOrigemOcorrencia;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(final Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public Integer getIdResponsavelAtual() {
        return idResponsavelAtual;
    }

    public void setIdResponsavelAtual(final Integer idResponsavelAtual) {
        this.idResponsavelAtual = idResponsavelAtual;
    }

    public String getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(final String urgencia) {
        this.urgencia = urgencia;
    }

    public Timestamp getDataHoraLimite() {
        return dataHoraLimite;
    }

    public void setDataHoraLimite(final Timestamp dataHoraLimite) {
        this.dataHoraLimite = dataHoraLimite;
    }

    public Timestamp getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(final Timestamp dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    /**
     * @return the notificarResponsavel
     */
    public String getNotificarResponsavel() {
        return notificarResponsavel;
    }

    /**
     * @param notificarResponsavel
     *            the notificarResponsavel to set
     */
    public void setNotificarResponsavel(final String notificarResponsavel) {
        this.notificarResponsavel = notificarResponsavel;
    }

    public String getIsPortal() {
        return isPortal;
    }

    public void setIsPortal(final String isPortal) {
        this.isPortal = isPortal;
    }

}
