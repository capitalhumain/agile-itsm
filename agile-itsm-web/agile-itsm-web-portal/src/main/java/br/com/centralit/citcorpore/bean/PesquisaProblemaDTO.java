package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.centralit.bpm.dto.ObjetoNegocioFluxoDTO;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoProblema;
import br.com.centralit.citcorpore.util.Util;

/**
 *
 * @author geber.costa
 *
 */
public class PesquisaProblemaDTO extends ObjetoNegocioFluxoDTO {

    private static final long serialVersionUID = 1L;
    private String serializados;
    private String itensConfiguracaoRelacionadosSerializado;
    private String servicosRelacionadosSerializado;
    private Integer idProblema;
    private Integer idProprietario;
    private String nomeProprietario;
    private Integer idSolicitante;
    private String nomeSolicitante;
    private String tipo;
    private Integer idCategoriaProblema;
    private String categoria;
    private String motivo;
    private String nivelImportanciaNegocio;
    private String classificacao;
    private String nivelImpacto;
    private String analiseImpacto;
    private Timestamp dataHoraConclusao;
    private Date dataAceitacao;
    private Date dataVotacao;
    private Timestamp dataHoraInicio;
    private Timestamp dataHoraTermino;
    private String titulo;
    private String descricao;
    private String risco;
    private Double estimativaCusto;
    private String planoReversao;
    private String status;
    private Integer prioridade;
    private Integer tempoDecorridoHH;
    private Integer tempoDecorridoMM;
    private Timestamp dataHoraSuspensao;
    private Timestamp dataHoraReativacao;
    private Timestamp dataHoraCaptura;
    private Integer prazoHH;
    private Integer prazoMM;
    private Integer idCalendario;
    private Integer tempoAtendimentoHH;
    private Integer tempoAtendimentoMM;
    private Integer tempoAtrasoHH;
    private Integer tempoAtrasoMM;
    private Integer tempoCapturaHH;
    private Integer tempoCapturaMM;
    private String fase;
    private Integer sequenciaProblema;
    private Integer idSolicitacaoServico;
    private String solicitacaoServicoSerializado;
    private List<SolicitacaoServicoDTO> listIdSolicitacaoServico;
    private String nomeServico;

    private String enviaEmailCriacao;
    private String enviaEmailFinalizacao;
    private String enviaEmailAcoes;
    private String exibirQuadroProblemas;

    private Integer seqReabertura;
    private UsuarioDTO usuarioDto;

    private String nomeTarefa;
    private String nomeGrupoAtual;
    private String nomeGrupoNivel1;
    private String emailSolicitante;

    private Integer idJustificativa;
    private String complementoJustificativa;

    private Integer idTarefa;
    private String acaoFluxo;
    private String escalar;
    private String alterarSituacao;
    private String dataHoraLimiteToString;
    private String dataHoraInicioStr;
    private String dataHoraTerminoStr;
    private String dataHoraInicioToString;
    private String dataHoraSolicitacaoStr;
    private Timestamp dataHoraSolicitacao;

    private String descrSituacao;
    private double atraso;
    private String atrasoStr;

    private String nivelUrgencia;
    private Integer idGrupoNivel;
    private Integer idBaseConhecimento;
    private Integer quantidade;

    private Date dataInicio;
    private Date dataFim;
    private String nomeItemConfiguracao;
    private String exibirCampoDescricao;
    private String formatoArquivoRelatorio;
    private String ordenacao;
    private Integer idItemConfiguracao;
    private Integer idTipoProblema;
    private String nomeCategoriaProblema;

    private Timestamp dataHoraFim;

    private String dataHoraConclusaoStr;

    public Integer getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(final Integer idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getEscalar() {
        return escalar;
    }

    public void setEscalar(final String escalar) {
        this.escalar = escalar;
    }

    public String getAlterarSituacao() {
        return alterarSituacao;
    }

    public void setAlterarSituacao(final String alterarSituacao) {
        this.alterarSituacao = alterarSituacao;
    }

    public Integer getIdCategoriaProblema() {
        return idCategoriaProblema;
    }

    public void setIdCategoriaProblema(final Integer idCategoriaProblema) {
        this.idCategoriaProblema = idCategoriaProblema;
    }

    public String getItensConfiguracaoRelacionadosSerializado() {
        return itensConfiguracaoRelacionadosSerializado;
    }

    public void setItensConfiguracaoRelacionadosSerializado(final String itensConfiguracaoRelacionadosSerializado) {
        this.itensConfiguracaoRelacionadosSerializado = itensConfiguracaoRelacionadosSerializado;
    }

    public String getServicosRelacionadosSerializado() {
        return servicosRelacionadosSerializado;
    }

    public void setServicosRelacionadosSerializado(final String servicosRelacionadosSerializado) {
        this.servicosRelacionadosSerializado = servicosRelacionadosSerializado;
    }

    public String getEnviaEmailCriacao() {
        return enviaEmailCriacao;
    }

    public void setEnviaEmailCriacao(final String enviaEmailCriacao) {
        this.enviaEmailCriacao = enviaEmailCriacao;
    }

    public String getEnviaEmailFinalizacao() {
        return enviaEmailFinalizacao;
    }

    public void setEnviaEmailFinalizacao(final String enviaEmailFinalizacao) {
        this.enviaEmailFinalizacao = enviaEmailFinalizacao;
    }

    public String getEnviaEmailAcoes() {
        return enviaEmailAcoes;
    }

    public void setEnviaEmailAcoes(final String enviaEmailAcoes) {
        this.enviaEmailAcoes = enviaEmailAcoes;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(final String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(final String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public String getSerializados() {
        return serializados;
    }

    public void setSerializados(final String serializados) {
        this.serializados = serializados;
    }

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(final Integer idProblema) {
        this.idProblema = idProblema;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(final String categoria) {
        this.categoria = categoria;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(final String motivo) {
        this.motivo = motivo;
    }

    public String getNivelImportanciaNegocio() {
        return nivelImportanciaNegocio;
    }

    public void setNivelImportanciaNegocio(final String nivelImportanciaNegocio) {
        this.nivelImportanciaNegocio = nivelImportanciaNegocio;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(final String classificacao) {
        this.classificacao = classificacao;
    }

    public String getNivelImpacto() {
        return nivelImpacto;
    }

    public void setNivelImpacto(final String nivelImpacto) {
        this.nivelImpacto = nivelImpacto;
    }

    public String getAnaliseImpacto() {
        return analiseImpacto;
    }

    public void setAnaliseImpacto(final String analiseImpacto) {
        this.analiseImpacto = analiseImpacto;
    }

    public Integer getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(final Integer idProprietario) {
        this.idProprietario = idProprietario;
    }

    public Integer getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(final Integer idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public Date getDataAceitacao() {
        return dataAceitacao;
    }

    public void setDataAceitacao(final Date dataAceitacao) {
        this.dataAceitacao = dataAceitacao;
    }

    public Date getDataVotacao() {
        return dataVotacao;
    }

    public void setDataVotacao(final Date dataVotacao) {
        this.dataVotacao = dataVotacao;
    }

    public String getNumberAndTitulo() {
        return "#" + idProblema + " - " + titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getRisco() {
        return risco;
    }

    public void setRisco(final String risco) {
        this.risco = risco;
    }

    public Double getEstimativaCusto() {
        return estimativaCusto;
    }

    public void setEstimativaCusto(final Double estimativaCusto) {
        this.estimativaCusto = estimativaCusto;
    }

    public String getPlanoReversao() {
        return planoReversao;
    }

    public void setPlanoReversao(final String planoReversao) {
        this.planoReversao = planoReversao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
        try {
            if (this.status != null) {
                descrSituacao = SituacaoProblema.valueOf(this.status.trim()).getDescricao();
            }
        } catch (final Exception e) {}
    }

    public String getDataHoraInicioStr() {
        return dataHoraInicioStr;
    }

    public void setDataHoraInicioStr(final String dataHoraInicioStr) {
        this.dataHoraInicioStr = dataHoraInicioStr;
    }

    public String getDescrSituacao() {
        return descrSituacao;
    }

    public void setDescrSituacao(final String descrSituacao) {
        this.descrSituacao = descrSituacao;
    }

    public String getAtrasoStr() {
        return atrasoStr;
    }

    public void setAtrasoStr(final String atrasoStr) {
        this.atrasoStr = atrasoStr;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(final Integer prioridade) {
        this.prioridade = prioridade;
    }

    public String getExibirQuadroProblemas() {
        return exibirQuadroProblemas;
    }

    public void setExibirQuadroProblemas(final String exibirQuadroProblemas) {
        this.exibirQuadroProblemas = exibirQuadroProblemas;
    }

    public UsuarioDTO getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(final UsuarioDTO usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public Integer getSeqReabertura() {
        return seqReabertura;
    }

    public void setSeqReabertura(final Integer seqReabertura) {
        this.seqReabertura = seqReabertura;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(final String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public String getNomeGrupoAtual() {
        return nomeGrupoAtual;
    }

    public void setNomeGrupoAtual(final String nomeGrupoAtual) {
        this.nomeGrupoAtual = nomeGrupoAtual;
    }

    public String getNomeGrupoNivel1() {
        return nomeGrupoNivel1;
    }

    public void setNomeGrupoNivel1(final String nomeGrupoNivel1) {
        this.nomeGrupoNivel1 = nomeGrupoNivel1;
    }

    public Timestamp getDataHoraConclusao() {
        return dataHoraConclusao;
    }

    public void setDataHoraConclusao(final Timestamp dataHoraConclusao) {
        this.dataHoraConclusao = dataHoraConclusao;
    }

    public Timestamp getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(final Timestamp dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
        dataHoraSolicitacao = dataHoraInicio;
        if (dataHoraInicio != null) {
            final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dataHoraInicioStr = format.format(dataHoraInicio);
            dataHoraSolicitacaoStr = format.format(dataHoraInicio);
        }
    }

    public Timestamp getDataHoraTermino() {
        return dataHoraTermino;
    }

    public void setDataHoraTermino(final Timestamp dataHoraTermino) {
        this.dataHoraTermino = dataHoraTermino;
        if (dataHoraTermino != null) {
            final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dataHoraTerminoStr = format.format(dataHoraTermino);
        }
    }

    public String getEmailSolicitante() {
        return emailSolicitante;
    }

    public void setEmailSolicitante(final String emailSolicitante) {
        this.emailSolicitante = emailSolicitante;
    }

    /**
     *
     * @geber.costa
     *              em PesquisaRequisicaoMudanca a SituacaoMudanca é concluida, no caso do problema é resolvida
     */

    public boolean atendida() {
        return status != null
                && (status.equalsIgnoreCase(Enumerados.SituacaoProblema.Resolvida.name())
                        || status.equalsIgnoreCase(Enumerados.SituacaoProblema.Cancelada.name()) || status
                        .equalsIgnoreCase(Enumerados.SituacaoProblema.Resolvida.name()));
    }

    public boolean encerrada() {
        return status != null && status.equalsIgnoreCase(Enumerados.SituacaoProblema.Resolvida.name());
    }

    public boolean emAtendimento() {

        if (status != null) {
            return status != null
                    // && (this.status.equalsIgnoreCase(Enumerados.SituacaoProblema.Registrada.name())
                    // || this.status.equalsIgnoreCase(Enumerados.SituacaoProblema.Aprovada.name())
                    || status.equalsIgnoreCase(Enumerados.SituacaoProblema.EmAndamento.name())
                    || status.equalsIgnoreCase(Enumerados.SituacaoProblema.Reaberta.name());
            // );
        } else {
            return false;
        }
    }

    public boolean escalada() {
        return this.getIdGrupoAtual() != null;
    }

    public boolean suspensa() {
        return status != null && status.equalsIgnoreCase(Enumerados.SituacaoRequisicaoMudanca.Suspensa.name());
    }

    public Integer getTempoDecorridoHH() {
        return tempoDecorridoHH;
    }

    public void setTempoDecorridoHH(final Integer tempoDecorridoHH) {
        this.tempoDecorridoHH = tempoDecorridoHH;
    }

    public Integer getTempoDecorridoMM() {
        return tempoDecorridoMM;
    }

    public void setTempoDecorridoMM(final Integer tempoDecorridoMM) {
        this.tempoDecorridoMM = tempoDecorridoMM;
    }

    public Timestamp getDataHoraSuspensao() {
        return dataHoraSuspensao;
    }

    public void setDataHoraSuspensao(final Timestamp dataHoraSuspensao) {
        this.dataHoraSuspensao = dataHoraSuspensao;
    }

    public Timestamp getDataHoraReativacao() {
        return dataHoraReativacao;
    }

    public void setDataHoraReativacao(final Timestamp dataHoraReativacao) {
        this.dataHoraReativacao = dataHoraReativacao;
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

    public Integer getPrazoHH() {
        return prazoHH;
    }

    public void setPrazoHH(final Integer prazoHH) {
        this.prazoHH = prazoHH;
    }

    public Integer getPrazoMM() {
        return prazoMM;
    }

    public void setPrazoMM(final Integer prazoMM) {
        this.prazoMM = prazoMM;
    }

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(final Integer idCalendario) {
        this.idCalendario = idCalendario;
    }

    public Integer getTempoAtendimentoHH() {
        return tempoAtendimentoHH;
    }

    public void setTempoAtendimentoHH(final Integer tempoAtendimentoHH) {
        this.tempoAtendimentoHH = tempoAtendimentoHH;
    }

    public Integer getTempoAtendimentoMM() {
        return tempoAtendimentoMM;
    }

    public void setTempoAtendimentoMM(final Integer tempoAtendimentoMM) {
        this.tempoAtendimentoMM = tempoAtendimentoMM;
    }

    public Integer getTempoAtrasoHH() {
        return tempoAtrasoHH;
    }

    public void setTempoAtrasoHH(final Integer tempoAtrasoHH) {
        this.tempoAtrasoHH = tempoAtrasoHH;
    }

    public Integer getTempoAtrasoMM() {
        return tempoAtrasoMM;
    }

    public void setTempoAtrasoMM(final Integer tempoAtrasoMM) {
        this.tempoAtrasoMM = tempoAtrasoMM;
    }

    public Timestamp getDataHoraCaptura() {
        return dataHoraCaptura;
    }

    public void setDataHoraCaptura(final Timestamp dataHoraCaptura) {
        this.dataHoraCaptura = dataHoraCaptura;
    }

    public Integer getTempoCapturaHH() {
        return tempoCapturaHH;
    }

    public void setTempoCapturaHH(final Integer tempoCapturaHH) {
        this.tempoCapturaHH = tempoCapturaHH;
    }

    public Integer getTempoCapturaMM() {
        return tempoCapturaMM;
    }

    public void setTempoCapturaMM(final Integer tempoCapturaMM) {
        this.tempoCapturaMM = tempoCapturaMM;
    }

    public String getAcaoFluxo() {
        return acaoFluxo;
    }

    public void setAcaoFluxo(final String acaoFluxo) {
        this.acaoFluxo = acaoFluxo;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(final String fase) {
        this.fase = fase;
    }

    public String getDataHoraLimiteToString() {
        if (dataHoraTermino == null) {
            return "";
        }
        return dataHoraTermino.toString();
    }

    public void setDataHoraLimiteToString(final String dataHoraLimiteToString) {
        this.dataHoraLimiteToString = this.getDataHoraLimiteToString();
    }

    public String getDataHoraInicioToString() {
        if (dataHoraInicioStr == null) {
            return "";
        }
        return dataHoraInicioStr.toString();
    }

    public void setDataHoraInicioToString(final String dataHoraInicioToString) {
        this.dataHoraInicioToString = this.getDataHoraInicioToString();
    }

    public String getDataHoraLimiteStr() {
        return dataHoraTerminoStr;
    }

    public String getDataHoraTerminoStr() {
        return dataHoraTerminoStr;
    }

    public void setDataHoraTerminoStr(final String dataHoraTerminoStr) {
        this.dataHoraTerminoStr = dataHoraTerminoStr;
    }

    public double getAtraso() {
        return atraso;
    }

    public void setAtraso(final double atraso) {
        this.atraso = atraso;
        atrasoStr = Util.getHoraFmtStr(atraso / 3600);
    }

    public String getDataHoraSolicitacaoStr() {
        return dataHoraSolicitacaoStr;
    }

    public void setDataHoraSolicitacaoStr(final String dataHoraSolicitacaoStr) {
        this.dataHoraSolicitacaoStr = dataHoraSolicitacaoStr;
    }

    public Timestamp getDataHoraSolicitacao() {
        return dataHoraSolicitacao;
    }

    public void setDataHoraSolicitacao(final Timestamp dataHoraSolicitacao) {
        this.dataHoraSolicitacao = dataHoraSolicitacao;
    }

    /**
     * @return the nivelUrgencia
     */
    public String getNivelUrgencia() {
        return nivelUrgencia;
    }

    /**
     * @param nivelUrgencia
     *            the nivelUrgencia to set
     */
    public void setNivelUrgencia(final String nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }

    /**
     * @return the idGrupoNivel
     */
    public Integer getIdGrupoNivel() {
        return idGrupoNivel;
    }

    /**
     * @param idGrupoNivel
     *            the idGrupoNivel to set
     */
    public void setIdGrupoNivel(final Integer idGrupoNivel) {
        this.idGrupoNivel = idGrupoNivel;
    }

    /**
     * @return the sequenciaProblema
     */
    public Integer getSequenciaProblema() {
        return sequenciaProblema;
    }

    /**
     * @param sequenciaProblema
     *            the sequenciaProblema to set
     */
    public void setSequenciaProblema(final Integer sequenciaProblema) {
        this.sequenciaProblema = sequenciaProblema;
    }

    /**
     * @return the idSolicitacaoServico
     */
    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    /**
     * @param idSolicitacaoServico
     *            the idSolicitacaoServico to set
     */
    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    /**
     * @return the solicitacaoServicoSerializado
     */
    public String getSolicitacaoServicoSerializado() {
        return solicitacaoServicoSerializado;
    }

    /**
     * @param solicitacaoServicoSerializado
     *            the solicitacaoServicoSerializado to set
     */
    public void setSolicitacaoServicoSerializado(final String solicitacaoServicoSerializado) {
        this.solicitacaoServicoSerializado = solicitacaoServicoSerializado;
    }

    /**
     * @return the listIdSolicitacaoServico
     */
    public List<SolicitacaoServicoDTO> getListIdSolicitacaoServico() {
        return listIdSolicitacaoServico;
    }

    /**
     * @param listIdSolicitacaoServico
     *            the listIdSolicitacaoServico to set
     */
    public void setListIdSolicitacaoServico(final List<SolicitacaoServicoDTO> listIdSolicitacaoServico) {
        this.listIdSolicitacaoServico = listIdSolicitacaoServico;
    }

    /**
     * @return the nomeServico
     */
    public String getNomeServico() {
        return nomeServico;
    }

    /**
     * @param nomeServico
     *            the nomeServico to set
     */
    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public Integer getIdBaseConhecimento() {
        return idBaseConhecimento;
    }

    public void setIdBaseConhecimento(final Integer idBaseConhecimento) {
        this.idBaseConhecimento = idBaseConhecimento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final Integer quantidade) {
        this.quantidade = quantidade;
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

    public String getNomeItemConfiguracao() {
        return nomeItemConfiguracao;
    }

    public void setNomeItemConfiguracao(final String nomeItemConfiguracao) {
        this.nomeItemConfiguracao = nomeItemConfiguracao;
    }

    /**
     * @return exibirCampoDescricao
     */
    public String getExibirCampoDescricao() {
        return exibirCampoDescricao;
    }

    /**
     * @param exibirCampoDescricao
     *            the exibirCampoDescricao to set
     */
    public void setExibirCampoDescricao(final String exibirCampoDescricao) {
        this.exibirCampoDescricao = exibirCampoDescricao;
    }

    public String getFormatoArquivoRelatorio() {
        return formatoArquivoRelatorio;
    }

    public void setFormatoArquivoRelatorio(final String formatoArquivoRelatorio) {
        this.formatoArquivoRelatorio = formatoArquivoRelatorio;
    }

    public String getOrdenacao() {
        return ordenacao;
    }

    public void setOrdenacao(final String ordenacao) {
        this.ordenacao = ordenacao;
    }

    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    public void setIdItemConfiguracao(final Integer idItemConfiguracao) {
        this.idItemConfiguracao = idItemConfiguracao;
    }

    public String getNomeCategoriaProblema() {
        return nomeCategoriaProblema;
    }

    public void setNomeCategoriaProblema(final String nomeCategoriaProblema) {
        this.nomeCategoriaProblema = nomeCategoriaProblema;
    }

    public Integer getIdTipoProblema() {
        return idTipoProblema;
    }

    public void setIdTipoProblema(final Integer idTipoProblema) {
        this.idTipoProblema = idTipoProblema;
    }

    public Timestamp getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(final Timestamp dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    /**
     * @return the dataHoraConclusaoStr
     */
    public String getDataHoraConclusaoStr() {
        return dataHoraConclusaoStr;
    }

    /**
     * @param dataHoraConclusaoStr
     *            the dataHoraConclusaoStr to set
     */
    public void setDataHoraConclusaoStr(final String dataHoraConclusaoStr) {
        this.dataHoraConclusaoStr = dataHoraConclusaoStr;
    }

}
