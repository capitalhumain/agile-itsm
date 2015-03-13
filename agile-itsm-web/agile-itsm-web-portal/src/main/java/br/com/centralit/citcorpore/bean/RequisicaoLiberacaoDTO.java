package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import br.com.centralit.bpm.dto.ObjetoNegocioFluxoDTO;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoRequisicaoLiberacao;
import br.com.centralit.citcorpore.util.Util;

public class RequisicaoLiberacaoDTO extends ObjetoNegocioFluxoDTO {

    private static final long serialVersionUID = 1L;

    private Integer idRequisicaoLiberacao;
    private Integer idSolicitante;
    private Integer idLiberacao;
    private String titulo;
    private String descricao;
    private Date dataInicial;
    private Date dataFinal;
    private Date dataLiberacao;
    private String situacao;
    private String risco;
    private String versao;
    private String editar;
    private Integer idAprovador;
    private Timestamp datahoraAprovacao;
    private String datahoraAprovacaoStr;
    private String autorizadoLiberar;
    private Integer idContrato;
    private Integer idGrupoAprovador;
    private String nomeGrupoAprovador;
    private String usuarioSolicitante;
    private Integer idMudanca;
    // Atributos Contato
    private Integer idContatoRequisicaoLiberacao;
    private String telefoneContato;
    private String ramal;
    private String observacao;
    private Integer idUnidade;
    private Integer IdLocalidade;
    private String nomeContato2;
    private String emailContato;

    // Atributos do Questionario
    private Integer idTipoAba;
    private Integer idTipoRequisicao;

    // Atributos Fluxo
    private Integer idFaseAtual;
    private String demanda;
    private Integer idTipoDemandaServico;
    private Integer idUsuarioDestino;
    private Integer idGrupoDestino;
    private String grupoAtual;
    private String aprovacao;
    private String escalar;
    private Integer prioridade;
    private String nivelUrgencia;
    private String nivelImpacto;
    private String complementoJustificativa;
    private Integer idJustificativa;
    private Integer idTipoLiberacao;
    private Integer seqReabertura;
    private String enviaEmailCriacao;
    private String enviaEmailAcoes;
    private String nomeGrupoAtual;
    private String nomeGrupoNivel1;
    private Integer tempoAtrasoHH;
    private Integer tempoAtrasoMM;
    private Integer tempoCapturaHH;
    private Integer tempoCapturaMM;
    private String dataInicioStr;
    private String dataTerminoStr;
    private Timestamp dataHoraTermino;
    private Timestamp dataHoraConclusao;
    private String status;
    private String descrSituacao;
    private Integer tempoDecorridoHH;
    private Integer tempoDecorridoMM;
    private Integer tempoAtendimentoHH;
    private Integer tempoAtendimentoMM;
    private Timestamp dataHoraCaptura;
    private Timestamp dataHoraReativacao;
    private Timestamp dataHoraInicio;
    private String nomeTarefa;
    private UsuarioDTO usuarioDto;
    private Integer idCalendario;
    private Timestamp dataHoraSuspensao;
    private String nomeContato;
    private String enviaEmailFinalizacao;
    private String emailSolicitante;
    private Integer prazoHH;
    private Integer prazoMM;
    private String dataHoraTerminoStr;
    private double atraso;
    private String atrasoStr;
    private String acaoFluxo;
    private String tipo;
    private Timestamp dataHoraSolicitacao;
    private Integer idProprietario;
    private Timestamp dataHoraInicioAgendada;
    private Timestamp dataHoraTerminoAgendada;
    private String dataHoraLimiteToString;
    private String dataHoraInicioToString;
    private String dataHoraInicioStr;
    private String dataHoraSolicitacaoStr;
    private Integer idTarefa;
    private String fase;
    private String fechamento;
    private String alterarSituacao;
    private Integer idCategoriaSolucao;

    private Integer sequenciaLiberacao;

    private String nomeSolicitante;
    private String nomeProprietario;

    private Collection<LiberacaoMudancaDTO> colLiberacoes;

    private Collection<RequisicaoLiberacaoMidiaDTO> colMidia;

    private Collection<LiberacaoProblemaDTO> colProblemas;
    private List<RequisicaoLiberacaoItemConfiguracaoDTO> listRequisicaoLiberacaoItemConfiguracaoDTO;
    private Collection<RequisicaoLiberacaoResponsavelDTO> colResponsaveis;
    private Collection<RequisicaoLiberacaoRequisicaoComprasDTO> colRequisicaoCompras;

    // atributos do historico
    private Collection<UploadDTO> colArquivosUpload;
    private Collection<UploadDTO> colArquivosUploadDocsLegais;

    private Integer idEmpresa;
    private String registroexecucao;
    private String baselinesSerializadas;
    private String itensConfiguracaoRelacionadosSerializado;

    private Integer idICMudanca;

    private Integer idItemConfig;
    private String nomeItemConfig;

    private String horaAgendamentoFinal;
    private String horaAgendamentoInicial;

    // campo para cadastrar agendamento direto da jsp requisicaoMudanca
    private Integer idGrupoAtvPeriodica;

    // campo auxiliar, não é salvo no banco
    private String responsavelAtual;

    public String getHoraAgendamentoFinal() {
        return horaAgendamentoFinal;
    }

    public void setHoraAgendamentoFinal(final String horaAgendamentoFinal) {
        this.horaAgendamentoFinal = horaAgendamentoFinal;
    }

    public String getHoraAgendamentoInicial() {
        return horaAgendamentoInicial;
    }

    public void setHoraAgendamentoInicial(final String horaAgendamentoInicial) {
        this.horaAgendamentoInicial = horaAgendamentoInicial;
    }

    // atributos docs gerais
    private Collection<UploadDTO> colDocsGerais;

    // atributos aprovação
    public Collection<UploadDTO> getColArquivosUploadDocsLegais() {
        return colArquivosUploadDocsLegais;
    }

    public void setColArquivosUploadDocsLegais(final Collection<UploadDTO> colArquivosUploadDocsLegais) {
        this.colArquivosUploadDocsLegais = colArquivosUploadDocsLegais;
    }

    private Integer idUltimaAprovacao;

    private String mudancas_serialize;

    private List<RequisicaoLiberacaoDTO> lstMudancas;

    private String situacaoLiberacao;

    public List getLstMudancas() {
        return lstMudancas;
    }

    public void setLstMudancas(final List lstMudancas) {
        this.lstMudancas = lstMudancas;
    }

    public Integer getIdRequisicaoLiberacao() {
        return idRequisicaoLiberacao;
    }

    public void setIdRequisicaoLiberacao(final Integer parm) {
        idRequisicaoLiberacao = parm;
    }

    public Integer getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(final Integer parm) {
        idSolicitante = parm;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String parm) {
        titulo = parm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String parm) {
        descricao = parm;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(final Date parm) {
        dataInicial = parm;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(final Date parm) {
        dataFinal = parm;
    }

    public Date getDataLiberacao() {
        return dataLiberacao;
    }

    public void setDataLiberacao(final Date parm) {
        dataLiberacao = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public String getRisco() {
        return risco;
    }

    public void setRisco(final String parm) {
        risco = parm;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(final String parm) {
        versao = parm;
    }

    public Collection<LiberacaoMudancaDTO> getColMudancas() {
        return colLiberacoes;
    }

    public void setColMudancas(final Collection<LiberacaoMudancaDTO> colMudancas) {
        colLiberacoes = colMudancas;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(final String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public Integer getIdTipoLiberacao() {
        return idTipoLiberacao;
    }

    public void setIdTipoLiberacao(final Integer idTipoLiberacao) {
        this.idTipoLiberacao = idTipoLiberacao;
    }

    public boolean escalada() {
        return this.getIdGrupoAtual() != null;
    }

    public Integer getSeqReabertura() {
        return seqReabertura;
    }

    public void setSeqReabertura(final Integer seqReabertura) {
        this.seqReabertura = seqReabertura;
    }

    public String getEnviaEmailCriacao() {
        return enviaEmailCriacao;
    }

    public void setEnviaEmailCriacao(final String enviaEmailCriacao) {
        this.enviaEmailCriacao = enviaEmailCriacao;
    }

    public String getEnviaEmailAcoes() {
        return enviaEmailAcoes;
    }

    public void setEnviaEmailAcoes(final String enviaEmailAcoes) {
        this.enviaEmailAcoes = enviaEmailAcoes;
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

    public String getDataInicioStr() {
        return dataInicioStr;
    }

    public void setDataInicioStr(final String dataInicioStr) {
        this.dataInicioStr = dataInicioStr;
    }

    public String getDataTerminoStr() {
        return dataTerminoStr;
    }

    public void setDataTerminoStr(final String dataTerminoStr) {
        this.dataTerminoStr = dataTerminoStr;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
        try {
            if (this.status != null) {
                this.setDescrSituacao(SituacaoRequisicaoLiberacao.valueOf(this.status.trim()).getDescricao());
            }
        } catch (final Exception e) {}
    }

    public Timestamp getDataHoraConclusao() {
        return dataHoraConclusao;
    }

    public void setDataHoraConclusao(final Timestamp dataHoraConclusao) {
        this.dataHoraConclusao = dataHoraConclusao;
    }

    public String getDescrSituacao() {
        return descrSituacao;
    }

    public void setDescrSituacao(final String descrSituacao) {
        this.descrSituacao = descrSituacao;
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

    public Timestamp getDataHoraCaptura() {
        return dataHoraCaptura;
    }

    public void setDataHoraCaptura(final Timestamp dataHoraCaptura) {
        this.dataHoraCaptura = dataHoraCaptura;
    }

    public Timestamp getDataHoraReativacao() {
        return dataHoraReativacao;
    }

    public void setDataHoraReativacao(final Timestamp dataHoraReativacao) {
        this.dataHoraReativacao = dataHoraReativacao;
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

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(final String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public UsuarioDTO getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(final UsuarioDTO usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(final Integer idCalendario) {
        this.idCalendario = idCalendario;
    }

    public Timestamp getDataHoraSuspensao() {
        return dataHoraSuspensao;
    }

    public void setDataHoraSuspensao(final Timestamp dataHoraSuspensao) {
        this.dataHoraSuspensao = dataHoraSuspensao;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(final String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getEnviaEmailFinalizacao() {
        return enviaEmailFinalizacao;
    }

    public void setEnviaEmailFinalizacao(final String enviaEmailFinalizacao) {
        this.enviaEmailFinalizacao = enviaEmailFinalizacao;
    }

    public String getEmailSolicitante() {
        return emailSolicitante;
    }

    public void setEmailSolicitante(final String emailSolicitante) {
        this.emailSolicitante = emailSolicitante;
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

    public boolean atendida() {
        return situacao != null
                && (status.equalsIgnoreCase(Enumerados.SituacaoRequisicaoLiberacao.Resolvida.name())
                        || status.equalsIgnoreCase(Enumerados.SituacaoRequisicaoLiberacao.Cancelada.name()) || status
                            .equalsIgnoreCase(Enumerados.SituacaoRequisicaoLiberacao.Fechada.name()));
    }

    public boolean aprovada() {
        return aprovacao != null && aprovacao.equalsIgnoreCase("A");
    }

    public boolean suspensa() {
        return status != null && status.equalsIgnoreCase(Enumerados.SituacaoRequisicaoLiberacao.Suspensa.name());
    }

    public boolean encerrada() {
        return status != null && status.equalsIgnoreCase(Enumerados.SituacaoRequisicaoLiberacao.Concluida.name());
    }

    public boolean emAtendimento() {
        return status != null
                && (status.equalsIgnoreCase(Enumerados.SituacaoRequisicaoLiberacao.Registrada.name())
                        || status.equalsIgnoreCase(Enumerados.SituacaoRequisicaoLiberacao.Aprovada.name())
                        || status.equalsIgnoreCase(Enumerados.SituacaoRequisicaoLiberacao.EmExecucao.name()) || status
                            .equalsIgnoreCase(Enumerados.SituacaoRequisicaoLiberacao.Reaberta.name()));
    }

    public boolean liberada() {
        return status != null
                && (status.equalsIgnoreCase(Enumerados.SituacaoRequisicaoLiberacao.Resolvida.name())
                        || status.equalsIgnoreCase(Enumerados.SituacaoRequisicaoLiberacao.Cancelada.name()) || status
                            .equalsIgnoreCase(Enumerados.SituacaoRequisicaoLiberacao.NaoResolvida.name()));
    }

    public boolean naoResolvida() {
        return status != null && status.equalsIgnoreCase(Enumerados.SituacaoRequisicaoLiberacao.NaoResolvida.name());
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
        this.setAtrasoStr(Util.getHoraFmtStr(atraso / 3600));
    }

    public String getAtrasoStr() {
        return atrasoStr;
    }

    public void setAtrasoStr(final String atrasoStr) {
        this.atrasoStr = atrasoStr;
    }

    public String getAcaoFluxo() {
        return acaoFluxo;
    }

    public void setAcaoFluxo(final String acaoFluxo) {
        this.acaoFluxo = acaoFluxo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public Timestamp getDataHoraSolicitacao() {
        return dataHoraSolicitacao;
    }

    public void setDataHoraSolicitacao(final Timestamp dataHoraSolicitacao) {
        this.dataHoraSolicitacao = dataHoraSolicitacao;
    }

    public Integer getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(final Integer idProprietario) {
        this.idProprietario = idProprietario;
    }

    public Timestamp getDataHoraInicioAgendada() {
        return dataHoraInicioAgendada;
    }

    public void setDataHoraInicioAgendada(final Timestamp dataHoraInicioAgendada) {
        this.dataHoraInicioAgendada = dataHoraInicioAgendada;
        if (dataHoraInicioAgendada != null) {
            final SimpleDateFormat fData = new SimpleDateFormat("dd/MM/yyyy");
            dataInicioStr = fData.format(dataHoraInicioAgendada);
        }
    }

    public Timestamp getDataHoraTerminoAgendada() {
        return dataHoraTerminoAgendada;
    }

    public void setDataHoraTerminoAgendada(final Timestamp dataHoraTerminoAgendada) {
        this.dataHoraTerminoAgendada = dataHoraTerminoAgendada;
        if (dataHoraTerminoAgendada != null) {
            final SimpleDateFormat fData = new SimpleDateFormat("dd/MM/yyyy");
            dataTerminoStr = fData.format(dataHoraTerminoAgendada);
        }
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

    /**
     * @return the complementoJustificativa
     */
    // inicio contatos
    public Integer getIdContatoRequisicaoLiberacao() {
        return idContatoRequisicaoLiberacao;
    }

    public void setIdContatoRequisicaoLiberacao(final Integer idContatoRequisicaoLiberacao) {
        this.idContatoRequisicaoLiberacao = idContatoRequisicaoLiberacao;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(final String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(final String ramal) {
        this.ramal = ramal;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public void setEmailContato(final String emailContato) {
        this.emailContato = emailContato;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(final String observacao) {
        this.observacao = observacao;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(final Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    public Integer getIdLocalidade() {
        return IdLocalidade;
    }

    public void setIdLocalidade(final Integer idLocalidade) {
        IdLocalidade = idLocalidade;
    }

    public String getNomeContato2() {
        return nomeContato2;
    }

    public void setNomeContato2(final String nomeContato2) {
        this.nomeContato2 = nomeContato2;
    }

    // fim contatos
    public String getComplementoJustificativa() {
        return complementoJustificativa;
    }

    /**
     * @param complementoJustificativa
     *            the complementoJustificativa to set
     */
    public void setComplementoJustificativa(final String complementoJustificativa) {
        this.complementoJustificativa = complementoJustificativa;
    }

    /**
     * @return the idJustificativa
     */
    public Integer getIdJustificativa() {
        return idJustificativa;
    }

    /**
     * @param idJustificativa
     *            the idJustificativa to set
     */
    public void setIdJustificativa(final Integer idJustificativa) {
        this.idJustificativa = idJustificativa;
    }

    /**
     * @return the dataHoraInicioToString
     */
    public String getDataHoraInicioToString() {
        if (dataHoraInicioStr == null) {
            return "";
        }
        return dataHoraInicioStr.toString();
    }

    /**
     * @param dataHoraInicioToString
     *            the dataHoraInicioToString to set
     */
    public void setDataHoraInicioToString(final String dataHoraInicioToString) {
        this.dataHoraInicioToString = this.getDataHoraInicioToString();
    }

    public String getDataHoraInicioStr() {
        return dataHoraInicioStr;
    }

    public void setDataHoraInicioStr(final String dataHoraInicioStr) {
        this.dataHoraInicioStr = dataHoraInicioStr;
    }

    /**
     * @return the dataHoraSolicitacaoStr
     */
    public String getDataHoraSolicitacaoStr() {
        return dataHoraSolicitacaoStr;
    }

    /**
     * @param dataHoraSolicitacaoStr
     *            the dataHoraSolicitacaoStr to set
     */
    public void setDataHoraSolicitacaoStr(final String dataHoraSolicitacaoStr) {
        this.dataHoraSolicitacaoStr = dataHoraSolicitacaoStr;
    }

    /**
     * @return the idTarefa
     */
    public Integer getIdTarefa() {
        return idTarefa;
    }

    /**
     * @param idTarefa
     *            the idTarefa to set
     */
    public void setIdTarefa(final Integer idTarefa) {
        this.idTarefa = idTarefa;
    }

    /**
     * @return the fase
     */
    public String getFase() {
        return fase;
    }

    /**
     * @param fase
     *            the fase to set
     */
    public void setFase(final String fase) {
        this.fase = fase;
    }

    /**
     * @return the fechamento
     */
    public String getFechamento() {
        return fechamento;
    }

    /**
     * @param fechamento
     *            the fechamento to set
     */
    public void setFechamento(final String fechamento) {
        this.fechamento = fechamento;
    }

    /**
     * @return the alterarSituacao
     */
    public String getAlterarSituacao() {
        return alterarSituacao;
    }

    /**
     * @param alterarSituacao
     *            the alterarSituacao to set
     */
    public void setAlterarSituacao(final String alterarSituacao) {
        this.alterarSituacao = alterarSituacao;
    }

    public Collection<UploadDTO> getColArquivosUpload() {
        return colArquivosUpload;
    }

    public void setColArquivosUpload(final Collection<UploadDTO> colArquivosUpload) {
        this.colArquivosUpload = colArquivosUpload;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(final Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getRegistroexecucao() {
        return registroexecucao;
    }

    public void setRegistroexecucao(final String registroexecucao) {
        this.registroexecucao = registroexecucao;
    }

    public String getBaselinesSerializadas() {
        return baselinesSerializadas;
    }

    public void setBaselinesSerializadas(final String baselinesSerializadas) {
        this.baselinesSerializadas = baselinesSerializadas;
    }

    // esse bloco lista o json e seta os dados para as ocorrencias;
    public String getDadosStr() {
        final StringBuilder str = new StringBuilder();
        if (this.getIdRequisicaoLiberacao() != null) {
            str.append("Número da requisição: " + this.getIdRequisicaoLiberacao() + "\n");
            if (this.getDataHoraInicio() != null) {
                str.append("Criada em: " + this.getDataHoraInicio() + "\n");
            }
            if (this.getDescrSituacao() != null) {
                str.append("Situação: " + this.getDescrSituacao() + "\n");
            }
            if (!this.suspensa()) {
                if (this.getPrazoHH() != null) {
                    str.append("Prazo atual: " + this.getPrazoHH() + "\n");
                }
                if (this.getDataHoraLimiteToString() != null) {
                    str.append("Data hora limite: " + this.getDataHoraLimiteToString() + "\n");
                }
                if (this.getNomeGrupoAtual() != null) {
                    str.append("Grupo atual: " + this.getNomeGrupoAtual() + "\n");
                }
            } else {
                str.append("Tempo decorrido: " + this.getTempoCapturaMM() + "\n");
            }
        }
        return str.toString();
    }

    /**
     * @return the prioridade
     */
    public Integer getPrioridade() {
        return prioridade;
    }

    /**
     * @param prioridade
     *            the prioridade to set
     */
    public void setPrioridade(final Integer prioridade) {
        this.prioridade = prioridade;
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
     * @return the nivelImpacto
     */
    public String getNivelImpacto() {
        return nivelImpacto;
    }

    /**
     * @param nivelImpacto
     *            the nivelImpacto to set
     */
    public void setNivelImpacto(final String nivelImpacto) {
        this.nivelImpacto = nivelImpacto;
    }

    /**
     * @return the editar
     */
    public String getEditar() {
        return editar;
    }

    /**
     * @param editar
     *            the editar to set
     */
    public void setEditar(final String editar) {
        this.editar = editar;
    }

    /**
     * @return the idAprovador
     */
    public Integer getIdAprovador() {
        return idAprovador;
    }

    /**
     * @param idAprovador
     *            the idAprovador to set
     */
    public void setIdAprovador(final Integer idAprovador) {
        this.idAprovador = idAprovador;
    }

    /**
     * @return the datahoraAprovacao
     */
    public Timestamp getDatahoraAprovacao() {
        return datahoraAprovacao;
    }

    /**
     * @param datahoraAprovacao
     *            the datahoraAprovacao to set
     */
    public void setDatahoraAprovacao(final Timestamp datahoraAprovacao) {
        this.datahoraAprovacao = datahoraAprovacao;

        if (datahoraAprovacao != null) {
            final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            datahoraAprovacaoStr = format.format(datahoraAprovacao);
        }
    }

    /**
     * @return the autorizadoLiberar
     */
    public String getAutorizadoLiberar() {
        return autorizadoLiberar;
    }

    /**
     * @param autorizadoLiberar
     *            the autorizadoLiberar to set
     */
    public void setAutorizadoLiberar(final String autorizadoLiberar) {
        this.autorizadoLiberar = autorizadoLiberar;
    }

    /**
     * @return the datahoraAprovacaoStr
     */
    public String getDatahoraAprovacaoStr() {
        return datahoraAprovacaoStr;
    }

    /**
     * @param datahoraAprovacaoStr
     *            the datahoraAprovacaoStr to set
     */
    public void setDatahoraAprovacaoStr(final String datahoraAprovacaoStr) {
        this.datahoraAprovacaoStr = datahoraAprovacaoStr;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    /**
     * @return the idUsuarioDestino
     */
    public Integer getIdUsuarioDestino() {
        return idUsuarioDestino;
    }

    /**
     * @param idUsuarioDestino
     *            the idUsuarioDestino to set
     */
    public void setIdUsuarioDestino(final Integer idUsuarioDestino) {
        this.idUsuarioDestino = idUsuarioDestino;
    }

    /**
     * @return the idGrupoDestino
     */
    public Integer getIdGrupoDestino() {
        return idGrupoDestino;
    }

    /**
     * @param idGrupoDestino
     *            the idGrupoDestino to set
     */
    public void setIdGrupoDestino(final Integer idGrupoDestino) {
        this.idGrupoDestino = idGrupoDestino;
    }

    /**
     * @return the idTipoDemandaServico
     */
    public Integer getIdTipoDemandaServico() {
        return idTipoDemandaServico;
    }

    /**
     * @param idTipoDemandaServico
     *            the idTipoDemandaServico to set
     */
    public void setIdTipoDemandaServico(final Integer idTipoDemandaServico) {
        this.idTipoDemandaServico = idTipoDemandaServico;
    }

    /**
     * @return the demanda
     */
    public String getDemanda() {
        return demanda;
    }

    /**
     * @param demanda
     *            the demanda to set
     */
    public void setDemanda(final String demanda) {
        this.demanda = demanda;
    }

    public List<RequisicaoLiberacaoItemConfiguracaoDTO> getListRequisicaoLiberacaoItemConfiguracaoDTO() {
        return listRequisicaoLiberacaoItemConfiguracaoDTO;
    }

    public void setListRequisicaoLiberacaoItemConfiguracaoDTO(final List<RequisicaoLiberacaoItemConfiguracaoDTO> listRequisicaoLiberacaoItemConfiguracaoDTO) {
        this.listRequisicaoLiberacaoItemConfiguracaoDTO = listRequisicaoLiberacaoItemConfiguracaoDTO;
    }

    public String getItensConfiguracaoRelacionadosSerializado() {
        return itensConfiguracaoRelacionadosSerializado;
    }

    public void setItensConfiguracaoRelacionadosSerializado(final String itensConfiguracaoRelacionadosSerializado) {
        this.itensConfiguracaoRelacionadosSerializado = itensConfiguracaoRelacionadosSerializado;
    }

    /**
     * @return the grupoAtual
     */
    public String getGrupoAtual() {
        return grupoAtual;
    }

    /**
     * @param grupoAtual
     *            the grupoAtual to set
     */
    public void setGrupoAtual(final String grupoAtual) {
        this.grupoAtual = grupoAtual;
    }

    public Collection<LiberacaoProblemaDTO> getColProblemas() {
        return colProblemas;
    }

    public void setColProblemas(final Collection<LiberacaoProblemaDTO> colProblemas) {
        this.colProblemas = colProblemas;
    }

    /**
     * @param Responsáveis
     */
    public Collection<RequisicaoLiberacaoResponsavelDTO> getColResponsaveis() {
        return colResponsaveis;
    }

    public void setColResponsaveis(final Collection<RequisicaoLiberacaoResponsavelDTO> colResponsaveis) {
        this.colResponsaveis = colResponsaveis;
    }

    /**
     * @return the escalar
     */
    public String getEscalar() {
        return escalar;
    }

    /**
     * @param escalar
     *            the escalar to set
     */
    public void setEscalar(final String escalar) {
        this.escalar = escalar;
    }

    public Integer getIdUltimaAprovacao() {
        return idUltimaAprovacao;
    }

    public void setIdUltimaAprovacao(final Integer idUltimaAprovacao) {
        this.idUltimaAprovacao = idUltimaAprovacao;
    }

    public Integer getIdLiberacao() {
        return idLiberacao;
    }

    public void setIdLiberacao(final Integer idLiberacao) {
        this.idLiberacao = idLiberacao;
    }

    /**
     * @return the idTipoAba
     */
    public Integer getIdTipoAba() {
        return idTipoAba;
    }

    /**
     * @param idTipoAba
     *            the idTipoAba to set
     */
    public void setIdTipoAba(final Integer idTipoAba) {
        this.idTipoAba = idTipoAba;
    }

    /**
     * @return the idTipoRequisicao
     */
    public Integer getIdTipoRequisicao() {
        return idTipoRequisicao;
    }

    /**
     * @param idTipoRequisicao
     *            the idTipoRequisicao to set
     */
    public void setIdTipoRequisicao(final Integer idTipoRequisicao) {
        this.idTipoRequisicao = idTipoRequisicao;
    }

    /**
     * @return the usuarioSolicitante
     */
    public String getUsuarioSolicitante() {
        return usuarioSolicitante;
    }

    /**
     * @param usuarioSolicitante
     *            the usuarioSolicitante to set
     */
    public void setUsuarioSolicitante(final String usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }

    /**
     * @return the colMidia
     */
    public Collection<RequisicaoLiberacaoMidiaDTO> getColMidia() {
        return colMidia;
    }

    /**
     * @param colMidia
     *            the colMidia to set
     */
    public void setColMidia(final Collection<RequisicaoLiberacaoMidiaDTO> colMidia) {
        this.colMidia = colMidia;
    }

    /**
     * @return the idFaseAtual
     */
    public Integer getIdFaseAtual() {
        return idFaseAtual;
    }

    /**
     * @param idFaseAtual
     *            the idFaseAtual to set
     */
    public void setIdFaseAtual(final Integer idFaseAtual) {
        this.idFaseAtual = idFaseAtual;
    }

    /**
     * @return the idGrupoAprovador
     */
    public Integer getIdGrupoAprovador() {
        return idGrupoAprovador;
    }

    /**
     * @param idGrupoAprovador
     *            the idGrupoAprovador to set
     */
    public void setIdGrupoAprovador(final Integer idGrupoAprovador) {
        this.idGrupoAprovador = idGrupoAprovador;
    }

    /**
     * @return the nomeGrupoAprovador
     */
    public String getNomeGrupoAprovador() {
        return nomeGrupoAprovador;
    }

    /**
     * @param nomeGrupoAprovador
     *            the nomeGrupoAprovador to set
     */
    public void setNomeGrupoAprovador(final String nomeGrupoAprovador) {
        this.nomeGrupoAprovador = nomeGrupoAprovador;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(final String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public Collection<UploadDTO> getColDocsGerais() {
        return colDocsGerais;
    }

    public void setColDocsGerais(final Collection<UploadDTO> colDocsGerais) {
        this.colDocsGerais = colDocsGerais;
    }

    public Collection<RequisicaoLiberacaoRequisicaoComprasDTO> getColRequisicaoCompras() {
        return colRequisicaoCompras;
    }

    public void setColRequisicaoCompras(final Collection<RequisicaoLiberacaoRequisicaoComprasDTO> colRequisicaoCompras) {
        this.colRequisicaoCompras = colRequisicaoCompras;
    }

    public Integer getIdICMudanca() {
        return idICMudanca;
    }

    public void setIdICMudanca(final Integer idICMudanca) {
        this.idICMudanca = idICMudanca;
    }

    public String getMudancas_serialize() {
        return mudancas_serialize;
    }

    public void setMudancas_serialize(final String mudancas_serialize) {
        this.mudancas_serialize = mudancas_serialize;
    }

    public String getSituacaoLiberacao() {
        return situacaoLiberacao;
    }

    public void setSituacaoLiberacao(final String situacaoLiberacao) {
        this.situacaoLiberacao = situacaoLiberacao;
    }

    public Integer getIdItemConfig() {
        return idItemConfig;
    }

    public void setIdItemConfig(final Integer idItemConfig) {
        this.idItemConfig = idItemConfig;
    }

    public String getNomeItemConfig() {
        return nomeItemConfig;
    }

    public void setNomeItemConfig(final String nomeItemConfig) {
        this.nomeItemConfig = nomeItemConfig;
    }

    public Integer getIdCategoriaSolucao() {
        return idCategoriaSolucao;
    }

    public void setIdCategoriaSolucao(final Integer idCategoriaSolucao) {
        this.idCategoriaSolucao = idCategoriaSolucao;
    }

    public Integer getIdGrupoAtvPeriodica() {
        return idGrupoAtvPeriodica;
    }

    public void setIdGrupoAtvPeriodica(final Integer idGrupoAtvPeriodica) {
        this.idGrupoAtvPeriodica = idGrupoAtvPeriodica;
    }

    public String getResponsavelAtual() {
        return responsavelAtual;
    }

    public void setResponsavelAtual(final String responsavelAtual) {
        this.responsavelAtual = responsavelAtual;
    }

    public Integer getIdMudanca() {
        return idMudanca;
    }

    public void setIdMudanca(final Integer idMudanca) {
        this.idMudanca = idMudanca;
    }

    /**
     * @return the sequenciaLiberacao
     */
    public Integer getSequenciaLiberacao() {
        return sequenciaLiberacao;
    }

    /**
     * @param sequenciaLiberacao
     *            the sequenciaLiberacao to set
     */
    public void setSequenciaLiberacao(final Integer sequenciaLiberacao) {
        this.sequenciaLiberacao = sequenciaLiberacao;
    }

}
