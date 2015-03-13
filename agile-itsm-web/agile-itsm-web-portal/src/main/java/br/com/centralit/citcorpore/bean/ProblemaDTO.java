package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.bpm.dto.ObjetoNegocioFluxoDTO;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.Util;

public class ProblemaDTO extends ObjetoNegocioFluxoDTO {

    private static final long serialVersionUID = 1L;

    private Integer idProblema;
    private String status;
    private Integer prioridade;
    private Integer idCriador;
    private Integer idProprietario;
    private String titulo;
    private String descricao;
    private Integer idCategoriaProblema;
    private String impacto;
    private String urgencia;
    private String severidade;
    private String proativoReativo;
    private String msgErroAssociada;
    private Integer idProblemaItemConfiguracao;
    private Integer idErrosConhecidos;
    private Integer idProblemaMudanca;
    private Integer idProblemaIncidente;
    private Date dataHoraLimiteSolucionar;
    private Timestamp dataHoraInicio;
    private Timestamp dataHoraFim;
    private String solucaoDefinitiva;
    private String fecharItensRelacionados;
    private Integer idProblemaRelacionado;
    private String messageId;

    private Integer idSolicitacaoServico;

    private Integer idServico;
    private Integer idContrato;
    private Integer idServicoContrato;

    private Integer idGrupo;

    private String servico;

    private String nomeGrupoAtual;
    private String contrato;

    // base conhecimento
    private String causaRaiz;
    private String solucaoContorno;
    private String adicionarBDCE;

    // propriedades que não estão no banco
    private String nomeCriador;
    private String nomeProprietario;
    private String itensConfiguracaoRelacionadosSerializado;
    private String solicitacaoServicoSerializado;
    private String nomeServico;
    private List<SolicitacaoServicoDTO> listIdSolicitacaoServico;

    // propriedades da baseConhecimento
    private Integer idBaseConhecimento;
    private String statusBaseConhecimento;
    private Integer idPastaBaseConhecimento;
    private Integer sequenciaProblema;
    private Integer quantidade;

    private Integer prazoHH;
    private Integer prazoMM;
    private String slaACombinar;

    private Timestamp dataHoraLimite;
    private Integer idPrioridade;
    private Timestamp dataHoraSolicitacao;

    private String dataHoraLimiteStr;

    private String requisicaoMudancaSerializado;

    private List<ProblemaMudancaDTO> listProblemaMudancaDTO;
    private List<ProblemaDTO> listProblemaRelacionadoDTO;

    private String origem;

    // adicionado por david.junior
    // para a execucao dos fluxos de problema
    private String nomeUnidadeSolicitante;
    private String nomeUnidadeResponsavel;
    private String solicitante;
    private String solicitanteUnidade;
    private String responsavel;
    private Timestamp dataHoraInicioSLA;
    private String dataHoraInicioSLAStr;
    private UsuarioDTO usuarioDto;
    private double atrasoSLA;
    private String atrasoSLAStr;
    private String acaoFluxo;
    private Integer idFaseAtual;
    private String linkPesquisaSatisfacao;
    private Integer seqReabertura;
    private String enviaEmailCriacao;
    private String enviaEmailFinalizacao;
    private String enviaEmailAcoes;
    private String nomeTarefa;
    private Timestamp dataHoraCaptura;
    private Integer idInstanciaFluxo;
    private Integer idProblemaPai;
    private Boolean possuiFilho;

    private String emailContato;
    private Integer tempoCapturaHH;
    private Integer tempoCapturaMM;
    private Integer idCalendario;
    private Timestamp dataHoraReativacao;
    private Integer tempoDecorridoHH;
    private Integer tempoDecorridoMM;
    private Integer tempoAtendimentoHH;
    private Integer tempoAtendimentoMM;
    private Integer tempoAtrasoHH;
    private Integer tempoAtrasoMM;
    private Integer idCausaIncidente;
    private String resposta;
    private Timestamp dataHoraSuspensao;
    private String situacaoSLA;
    private Timestamp dataHoraSuspensaoSLA;
    private Timestamp dataHoraReativacaoSLA;
    private Integer idSolicitante;

    private String nomeContato;
    private String telefoneContato;
    private String ramal;
    private String observacao;
    private Integer idUnidade;
    private Integer idJustificativaProblema;
    private String complementoJustificativa;
    private Integer idTarefa;
    private Integer idOrigemAtendimento;
    private String diagnostico;
    private String dataHoraCapturaStr;
    private Integer idContatoProblema;
    private Integer idLocalidade;

    // Adicionado por geber.costa
    // Atributos para Revisão de Problemas graves

    private String acoesCorretas;
    private String acoesIncorretas;
    private String melhoriasFuturas;
    private String recorrenciaProblema;
    private String responsabilidadeTerceiros;
    private String acompanhamento;

    // Adicionado por riubbe.oliveira
    // Atributos para Categoria Problema
    private String confirmaSolucaoContorno;
    private Integer idCategoriaSolucao;
    private String fechamento;

    private List<ProblemaItemConfiguracaoDTO> listProblemaItemConfiguracaoDTO;

    private String registroexecucao;

    // add by david.junior para problema.java
    //
    private String escalar;
    private String alterarSituacao;
    private String fase;
    private String editar;

    private String grave;
    private String precisaMudanca;
    private String precisaSolucaoContorno;

    private String resolvido;
    private String tituloSolucaoContorno;
    private Integer idSolucaoContorno;

    // Flag (S/N) que indica se deve ser enviado um e-mail
    // quando o prazo para solucionar um problema expirou.
    // por padrão é S no banco de dados.
    private String enviaEmailPrazoSolucionarExpirou;

    private Integer idCausa;

    private String tituloSolCon;
    private String descSolCon;

    private String tituloSolucaoDefinitiva;
    private Integer idSolucaoDefinitiva;
    private String tituloSolDefinitiva;

    private String iframeSolicitacao;

    private String descSolDefinitiva;
    private String unidadeDes;

    public String getIframeSolicitacao() {
        return iframeSolicitacao;
    }

    public void setIframeSolicitacao(final String iframeSolicitacao) {
        this.iframeSolicitacao = iframeSolicitacao;
    }

    private String chamarTelaProblema;

    // Serialização das informações complementares
    private String informacoesComplementares_serialize;
    private BaseEntity informacoesComplementares;

    private Integer hiddenIdItemConfiguracao;

    // Mário Júnior - Anexo
    private Collection colArquivosUpload;

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(final Integer idProblema) {
        this.idProblema = idProblema;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(final Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Integer getIdCriador() {
        return idCriador;
    }

    public void setIdCriador(final Integer idCriador) {
        this.idCriador = idCriador;
    }

    public Integer getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(final Integer idProprietario) {
        this.idProprietario = idProprietario;
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

    public Integer getIdCategoriaProblema() {
        return idCategoriaProblema;
    }

    public void setIdCategoriaProblema(final Integer idCategoriaProblema) {
        this.idCategoriaProblema = idCategoriaProblema;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(final String impacto) {
        this.impacto = impacto;
    }

    public String getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(final String urgencia) {
        this.urgencia = urgencia;
    }

    public String getSeveridade() {
        return severidade;
    }

    public void setSeveridade(final String severidade) {
        this.severidade = severidade;
    }

    public String getProativoReativo() {
        return proativoReativo;
    }

    public void setProativoReativo(final String proativoReativo) {
        this.proativoReativo = proativoReativo;
    }

    public String getMsgErroAssociada() {
        return msgErroAssociada;
    }

    public void setMsgErroAssociada(final String msgErroAssociada) {
        this.msgErroAssociada = msgErroAssociada;
    }

    public Integer getIdProblemaItemConfiguracao() {
        return idProblemaItemConfiguracao;
    }

    public void setIdProblemaItemConfiguracao(final Integer idProblemaItemConfiguracao) {
        this.idProblemaItemConfiguracao = idProblemaItemConfiguracao;
    }

    public Integer getIdErrosConhecidos() {
        return idErrosConhecidos;
    }

    public void setIdErrosConhecidos(final Integer idErrosConhecidos) {
        this.idErrosConhecidos = idErrosConhecidos;
    }

    public Integer getIdProblemaMudanca() {
        return idProblemaMudanca;
    }

    public void setIdProblemaMudanca(final Integer idProblemaMudanca) {
        this.idProblemaMudanca = idProblemaMudanca;
    }

    public Integer getIdProblemaIncidente() {
        return idProblemaIncidente;
    }

    public void setIdProblemaIncidente(final Integer idProblemaIncidente) {
        this.idProblemaIncidente = idProblemaIncidente;
    }

    public Date getDataHoraLimiteSolucionar() {
        return dataHoraLimiteSolucionar;
    }

    public void setDataHoraLimiteSolucionar(final Date dataHoraLimiteSolucionar) {
        this.dataHoraLimiteSolucionar = dataHoraLimiteSolucionar;
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

    public String getSolucaoDefinitiva() {
        return solucaoDefinitiva;
    }

    public void setSolucaoDefinitiva(final String solucaoDefinitiva) {
        this.solucaoDefinitiva = solucaoDefinitiva;
    }

    public String getNomeCriador() {
        return nomeCriador;
    }

    public void setNomeCriador(final String nomeCriador) {
        this.nomeCriador = nomeCriador;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(final String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getItensConfiguracaoRelacionadosSerializado() {
        return itensConfiguracaoRelacionadosSerializado;
    }

    public void setItensConfiguracaoRelacionadosSerializado(final String itensConfiguracaoRelacionadosSerializado) {
        this.itensConfiguracaoRelacionadosSerializado = itensConfiguracaoRelacionadosSerializado;
    }

    public String getCausaRaiz() {
        return causaRaiz;
    }

    public void setCausaRaiz(final String causaRaiz) {
        this.causaRaiz = causaRaiz;
    }

    public String getSolucaoContorno() {
        return solucaoContorno;
    }

    public void setSolucaoContorno(final String solucaoContorno) {
        this.solucaoContorno = solucaoContorno;
    }

    public String getAdicionarBDCE() {
        return adicionarBDCE;
    }

    public void setAdicionarBDCE(final String adicionarBDCE) {
        this.adicionarBDCE = adicionarBDCE;
    }

    public Integer getIdBaseConhecimento() {
        return idBaseConhecimento;
    }

    public void setIdBaseConhecimento(final Integer idBaseConhecimento) {
        this.idBaseConhecimento = idBaseConhecimento;
    }

    public String getStatusBaseConhecimento() {
        return statusBaseConhecimento;
    }

    public void setStatusBaseConhecimento(final String statusBaseConhecimento) {
        this.statusBaseConhecimento = statusBaseConhecimento;
    }

    public Integer getIdPastaBaseConhecimento() {
        return idPastaBaseConhecimento;
    }

    public void setIdPastaBaseConhecimento(final Integer idPastaBaseConhecimento) {
        this.idPastaBaseConhecimento = idPastaBaseConhecimento;
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(final Integer idServico) {
        this.idServico = idServico;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(final Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(final String servico) {
        this.servico = servico;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(final String contrato) {
        this.contrato = contrato;
    }

    public Integer getIdServicoContrato() {
        return idServicoContrato;
    }

    public void setIdServicoContrato(final Integer idServicoContrato) {
        this.idServicoContrato = idServicoContrato;
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

    public String getSlaACombinar() {
        return slaACombinar;
    }

    public void setSlaACombinar(final String slaACombinar) {
        this.slaACombinar = slaACombinar;
    }

    public Timestamp getDataHoraLimite() {
        return dataHoraLimite;
    }

    public void setDataHoraLimite(final Timestamp dataHoraLimite) {
        this.dataHoraLimite = dataHoraLimite;
        if (dataHoraLimite != null) {
            final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dataHoraLimiteStr = format.format(dataHoraLimite);
        }
    }

    public String getDataHoraLimiteStr() {
        if (slaACombinar == null || slaACombinar.equalsIgnoreCase("N")) {
            return dataHoraLimiteStr;
        } else {
            return "--";
        }
    }

    public void setDataHoraLimiteStr(final String dataHoraLimiteStr) {
        if (slaACombinar == null || slaACombinar.equalsIgnoreCase("N")) {
            this.dataHoraLimiteStr = dataHoraLimiteStr;
        } else {
            this.dataHoraLimiteStr = "--";
        }
    }

    public Integer getIdPrioridade() {
        return idPrioridade;
    }

    public void setIdPrioridade(final Integer idPrioridade) {
        this.idPrioridade = idPrioridade;
    }

    public Timestamp getDataHoraSolicitacao() {
        return dataHoraSolicitacao;
    }

    public void setDataHoraSolicitacao(final Timestamp dataHoraSolicitacao) {
        this.dataHoraSolicitacao = dataHoraSolicitacao;
    }

    public String getRequisicaoMudancaSerializado() {
        return requisicaoMudancaSerializado;
    }

    public void setRequisicaoMudancaSerializado(final String requisicaoMudancaSerializado) {
        this.requisicaoMudancaSerializado = requisicaoMudancaSerializado;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(final String origem) {
        this.origem = origem;
    }

    public List<ProblemaMudancaDTO> getListProblemaMudancaDTO() {
        return listProblemaMudancaDTO;
    }

    public void setListProblemaMudancaDTO(final List<ProblemaMudancaDTO> listProblemaMudancaDTO) {
        this.listProblemaMudancaDTO = listProblemaMudancaDTO;
    }

    public String getNomeUnidadeSolicitante() {
        return nomeUnidadeSolicitante;
    }

    public void setNomeUnidadeSolicitante(final String nomeUnidadeSolicitante) {
        this.nomeUnidadeSolicitante = nomeUnidadeSolicitante;
    }

    public String getNomeUnidadeResponsavel() {
        return nomeUnidadeResponsavel;
    }

    public void setNomeUnidadeResponsavel(final String nomeUnidadeResponsavel) {
        this.nomeUnidadeResponsavel = nomeUnidadeResponsavel;
    }

    public String getSolicitante() {
        if (solicitante == null) {
            return null;
        }
        return solicitante.replaceAll("\"", " ");
    }

    public void setSolicitante(final String solicitante) {
        if (solicitante == null) {
            this.solicitante = null;
            return;
        }
        this.solicitante = solicitante.replaceAll("\"", " ");
    }

    public String getSolicitanteUnidade() {
        return solicitanteUnidade;
    }

    public void setSolicitanteUnidade(final String solicitanteUnidade) {
        this.solicitanteUnidade = solicitanteUnidade;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(final String responsavel) {
        this.responsavel = responsavel;
    }

    public Timestamp getDataHoraInicioSLA() {
        return dataHoraInicioSLA;
    }

    public void setDataHoraInicioSLA(final Timestamp dataHoraInicioSLA) {
        this.dataHoraInicioSLA = dataHoraInicioSLA;
        if (dataHoraInicioSLA != null) {
            final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            this.setDataHoraInicioSLAStr(format.format(dataHoraInicioSLA));
        }
    }

    public String getDataHoraInicioSLAStr() {
        return dataHoraInicioSLAStr;
    }

    public void setDataHoraInicioSLAStr(final String dataHoraInicioSLAStr) {
        this.dataHoraInicioSLAStr = dataHoraInicioSLAStr;
    }

    public UsuarioDTO getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(final UsuarioDTO usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public boolean atendida() {
        return status != null
                && (status.equalsIgnoreCase(Enumerados.SituacaoProblema.Resolvida.name())
                        || status.equalsIgnoreCase(Enumerados.SituacaoProblema.Cancelada.name()) || status.equalsIgnoreCase(Enumerados.SituacaoProblema.Fechada
                        .name()));
    }

    public boolean finalizada() {
        return status != null
                && (status.equalsIgnoreCase(Enumerados.SituacaoProblema.Resolvida.name())
                        || status.equalsIgnoreCase(Enumerados.SituacaoProblema.Cancelada.name()) || status.equalsIgnoreCase(Enumerados.SituacaoProblema.Fechada
                        .name()));
    }

    public boolean encerrada() {
        return status != null && status.equalsIgnoreCase(Enumerados.SituacaoProblema.Fechada.name());
    }

    public boolean emAtendimento() {
        return status != null
                && (status.equalsIgnoreCase(Enumerados.SituacaoProblema.EmAndamento.name()) || status.equalsIgnoreCase(Enumerados.SituacaoProblema.Reaberta
                        .name()));
    }

    public boolean reclassificada() {
        return status != null && status.equalsIgnoreCase(Enumerados.SituacaoProblema.ReClassificada.name());
    }

    public boolean escalada() {
        return this.getIdGrupo() != null;
    }

    public boolean suspensa() {
        return status != null && status.equalsIgnoreCase(Enumerados.SituacaoProblema.Suspensa.name());
    }

    public double getAtrasoSLA() {
        return atrasoSLA;
    }

    public void setAtrasoSLA(final double atrasoSLA) {
        if (slaACombinar == null || slaACombinar.equalsIgnoreCase("N")) {
            this.atrasoSLA = atrasoSLA;
            atrasoSLAStr = Util.getHoraFmtStr(atrasoSLA / 3600);
        } else {
            this.atrasoSLA = 0;
            atrasoSLAStr = "--";
        }
    }

    public String getAtrasoSLAStr() {
        if (slaACombinar == null || slaACombinar.equalsIgnoreCase("N")) {
            return atrasoSLAStr;
        } else {
            return "--";
        }
    }

    public void setAtrasoSLAStr(final String atrasoSLAStr) {
        if (slaACombinar == null || slaACombinar.equalsIgnoreCase("N")) {
            this.atrasoSLAStr = atrasoSLAStr;
        } else {
            this.atrasoSLAStr = "--";
        }
    }

    public String getAcaoFluxo() {
        return acaoFluxo;
    }

    public void setAcaoFluxo(final String acaoFluxo) {
        this.acaoFluxo = acaoFluxo;
    }

    public Integer getIdFaseAtual() {
        return idFaseAtual;
    }

    public void setIdFaseAtual(final Integer idFaseAtual) {
        this.idFaseAtual = idFaseAtual;
    }

    public String getLinkPesquisaSatisfacao() {
        return linkPesquisaSatisfacao;
    }

    public void setLinkPesquisaSatisfacao(final String linkPesquisaSatisfacao) {
        this.linkPesquisaSatisfacao = linkPesquisaSatisfacao;
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

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(final String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public Timestamp getDataHoraCaptura() {
        return dataHoraCaptura;
    }

    public void setDataHoraCaptura(final Timestamp dataHoraCaptura) {
        this.dataHoraCaptura = dataHoraCaptura;
        if (dataHoraCaptura != null) {
            final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dataHoraCapturaStr = format.format(dataHoraCaptura);
        }
    }

    public Integer getIdInstanciaFluxo() {
        return idInstanciaFluxo;
    }

    public void setIdInstanciaFluxo(final Integer idInstanciaFluxo) {
        this.idInstanciaFluxo = idInstanciaFluxo;
    }

    public Integer getIdProblemaPai() {
        return idProblemaPai;
    }

    public void setIdProblemaPai(final Integer idProblemaPai) {
        this.idProblemaPai = idProblemaPai;
    }

    public Boolean getPossuiFilho() {
        return possuiFilho;
    }

    public void setPossuiFilho(final Boolean possuiFilho) {
        this.possuiFilho = possuiFilho;
    }

    public String getEmailContato() {
        return Util.tratarAspasSimples(emailContato);
    }

    public void setEmailContato(final String emailContato) {
        this.emailContato = this.tratarCaracteresEspeciais(emailContato);
    }

    private String tratarCaracteresEspeciais(final String valor) {
        if (valor != null && !StringUtils.isEmpty(valor)) {
            if (StringUtils.contains(valor, "'")) {
                return StringUtils.replace(valor, "'", "");
            } else {
                if (StringUtils.contains(valor, "\"")) {
                    return StringUtils.replace(valor, "\"", "");
                } else {
                    return valor;
                }
            }

        } else {

            return valor;
        }
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

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(final Integer idCalendario) {
        this.idCalendario = idCalendario;
    }

    public Timestamp getDataHoraReativacao() {
        return dataHoraReativacao;
    }

    public void setDataHoraReativacao(final Timestamp dataHoraReativacao) {
        this.dataHoraReativacao = dataHoraReativacao;
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

    public Integer getTempoAtrasoMM() {
        return tempoAtrasoMM;
    }

    public void setTempoAtrasoMM(final Integer tempoAtrasoMM) {
        this.tempoAtrasoMM = tempoAtrasoMM;
    }

    public Integer getTempoAtrasoHH() {
        return tempoAtrasoHH;
    }

    public void setTempoAtrasoHH(final Integer tempoAtrasoHH) {
        this.tempoAtrasoHH = tempoAtrasoHH;
    }

    public Integer getIdCausaIncidente() {
        return idCausaIncidente;
    }

    public void setIdCausaIncidente(final Integer idCausaIncidente) {
        this.idCausaIncidente = idCausaIncidente;
    }

    public String getResposta() {
        return Util.tratarAspasSimples(resposta);
    }

    public void setResposta(final String parm) {
        resposta = parm;
    }

    public Timestamp getDataHoraSuspensao() {
        return dataHoraSuspensao;
    }

    public void setDataHoraSuspensao(final Timestamp dataHoraSuspensao) {
        this.dataHoraSuspensao = dataHoraSuspensao;
    }

    public String getSituacaoSLA() {
        return situacaoSLA;
    }

    public void setSituacaoSLA(final String situacaoSLA) {
        this.situacaoSLA = situacaoSLA;
    }

    public Timestamp getDataHoraSuspensaoSLA() {
        return dataHoraSuspensaoSLA;
    }

    public void setDataHoraSuspensaoSLA(final Timestamp dataHoraSuspensaoSLA) {
        this.dataHoraSuspensaoSLA = dataHoraSuspensaoSLA;
    }

    public Timestamp getDataHoraReativacaoSLA() {
        return dataHoraReativacaoSLA;
    }

    public void setDataHoraReativacaoSLA(final Timestamp dataHoraReativacaoSLA) {
        this.dataHoraReativacaoSLA = dataHoraReativacaoSLA;
    }

    public Integer getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(final Integer idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(final String nomeContato) {
        this.nomeContato = nomeContato;
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

    public String getComplementoJustificativa() {
        return complementoJustificativa;
    }

    public void setComplementoJustificativa(final String complementoJustificativa) {
        this.complementoJustificativa = complementoJustificativa;
    }

    /**
     *
     * @author geber.costa
     */
    public String getDadosStr() {
        final StringBuilder str = new StringBuilder();
        if (this.getIdProblema() != null) {
            str.append("Número do problema: " + this.getIdProblema() + "\n");
            if (this.getDataHoraInicio() != null) {
                str.append("Criada em: " + this.getDataHoraInicio() + "\n");
            }
            if (this.getDescricao() != null) {
                str.append("Situação: " + this.getDescricao() + "\n");
            }
            // if (!suspensa()) {
            if (this.getPrazoHH() != null) {
                str.append("Prazo atual: " + this.getPrazoHH() + "\n");
            }
            if (this.getDataHoraLimiteStr() != null) {
                str.append("Data hora limite: " + this.getDataHoraLimiteStr() + "\n");
            }
            if (this.getNomeGrupoAtual() != null) {
                str.append("Grupo atual: " + this.getNomeGrupoAtual() + "\n");
            }
            // } else {
            // str.append("Tempo decorrido: " + getTempoCapturaMM() + "\n");
            // }
            if (this.getImpacto() != null) {
                String imp = "";
                if (this.getImpacto().equalsIgnoreCase("B")) {
                    imp = "Baixo";
                }
                if (this.getImpacto().equalsIgnoreCase("M")) {
                    imp = "Médio";
                }
                if (this.getImpacto().equalsIgnoreCase("A")) {
                    imp = "Alto";
                }
                str.append("Impacto: " + imp + "\n");
            }
            if (this.getUrgencia() != null) {
                String imp = "";
                if (this.getUrgencia().equalsIgnoreCase("B")) {
                    imp = "Baixa";
                }
                if (this.getUrgencia().equalsIgnoreCase("M")) {
                    imp = "Média";
                }
                if (this.getUrgencia().equalsIgnoreCase("A")) {
                    imp = "Alta";
                }
                str.append("Urgência: " + imp + "\n");
            }
            if (this.getPrioridade() != null) {
                str.append("Prioridade (Código): " + this.getPrioridade() + "\n");
            }
        }
        return str.toString();
    }

    public Integer getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(final Integer idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(final String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Integer getIdOrigemAtendimento() {
        return idOrigemAtendimento;
    }

    public void setIdOrigemAtendimento(final Integer idOrigemAtendimento) {
        this.idOrigemAtendimento = idOrigemAtendimento;
    }

    public String getDataHoraCapturaStr() {
        if (slaACombinar == null || slaACombinar.equalsIgnoreCase("N")) {
            return dataHoraCapturaStr;
        } else {
            return "--";
        }
    }

    public void setDataHoraCapturaStr(final String dataHoraCapturaStr) {
        if (slaACombinar == null || slaACombinar.equalsIgnoreCase("N")) {
            this.dataHoraCapturaStr = dataHoraCapturaStr;
        } else {
            this.dataHoraCapturaStr = "--";
        }
    }

    public Integer getIdContatoProblema() {
        return idContatoProblema;
    }

    public void setIdContatoProblema(final Integer idContatoProblema) {
        this.idContatoProblema = idContatoProblema;
    }

    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(final Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    public String getAcoesCorretas() {
        return acoesCorretas;
    }

    public void setAcoesCorretas(final String acoesCorretas) {
        this.acoesCorretas = acoesCorretas;
    }

    public String getAcoesIncorretas() {
        return acoesIncorretas;
    }

    public void setAcoesIncorretas(final String acoesIncorretas) {
        this.acoesIncorretas = acoesIncorretas;
    }

    public String getMelhoriasFuturas() {
        return melhoriasFuturas;
    }

    public void setMelhoriasFuturas(final String melhoriasFuturas) {
        this.melhoriasFuturas = melhoriasFuturas;
    }

    public String getRecorrenciaProblema() {
        return recorrenciaProblema;
    }

    public void setRecorrenciaProblema(final String recorrenciaProblema) {
        this.recorrenciaProblema = recorrenciaProblema;
    }

    public String getResponsabilidadeTerceiros() {
        return responsabilidadeTerceiros;
    }

    public void setResponsabilidadeTerceiros(final String responsabilidadeTerceiros) {
        this.responsabilidadeTerceiros = responsabilidadeTerceiros;
    }

    public String getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(final String acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public Integer getIdCategoriaSolucao() {
        return idCategoriaSolucao;
    }

    public void setIdCategoriaSolucao(final Integer idCategoriaSolucao) {
        this.idCategoriaSolucao = idCategoriaSolucao;
    }

    public String getConfirmaSolucaoContorno() {
        return confirmaSolucaoContorno;
    }

    public void setConfirmaSolucaoContorno(final String confirmaSolucaoContorno) {
        this.confirmaSolucaoContorno = confirmaSolucaoContorno;
    }

    public String getFechamento() {
        return fechamento;
    }

    public void setFechamento(final String fechamento) {
        this.fechamento = fechamento;
    }

    public String getNomeGrupoAtual() {
        return nomeGrupoAtual;
    }

    public void setNomeGrupoAtual(final String nomeGrupoAtual) {
        this.nomeGrupoAtual = nomeGrupoAtual;
    }

    public List<ProblemaItemConfiguracaoDTO> getListProblemaItemConfiguracaoDTO() {
        return listProblemaItemConfiguracaoDTO;
    }

    public void setListProblemaItemConfiguracaoDTO(final List<ProblemaItemConfiguracaoDTO> listProblemaItemConfiguracaoDTO) {
        this.listProblemaItemConfiguracaoDTO = listProblemaItemConfiguracaoDTO;
    }

    public String getRegistroexecucao() {
        return registroexecucao;
    }

    public void setRegistroexecucao(final String registroexecucao) {
        this.registroexecucao = registroexecucao;
    }

    public Integer getIdJustificativaProblema() {
        return idJustificativaProblema;
    }

    public void setIdJustificativaProblema(final Integer idJustificativaProblema) {
        this.idJustificativaProblema = idJustificativaProblema;
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

    public String getFase() {
        return fase;
    }

    public void setFase(final String fase) {
        this.fase = fase;
    }

    public String getEditar() {
        return editar;
    }

    public void setEditar(final String editar) {
        this.editar = editar;
    }

    public String getGrave() {
        return grave;
    }

    public void setGrave(final String grave) {
        this.grave = grave;
    }

    public String getPrecisaMudanca() {
        return precisaMudanca;
    }

    public void setPrecisaMudanca(final String precisaMudanca) {
        this.precisaMudanca = precisaMudanca;
    }

    public String getPrecisaSolucaoContorno() {
        return precisaSolucaoContorno;
    }

    public void setPrecisaSolucaoContorno(final String precisaSolucaoContorno) {
        this.precisaSolucaoContorno = precisaSolucaoContorno;
    }

    public String getResolvido() {
        return resolvido;
    }

    public void setResolvido(final String resolvido) {
        this.resolvido = resolvido;
    }

    public String getEnviaEmailPrazoSolucionarExpirou() {
        return enviaEmailPrazoSolucionarExpirou;
    }

    public void setEnviaEmailPrazoSolucionarExpirou(final String enviaEmailPrazoSolucionarExpirou) {
        this.enviaEmailPrazoSolucionarExpirou = enviaEmailPrazoSolucionarExpirou;
    }

    public Integer getIdCausa() {
        return idCausa;
    }

    public void setIdCausa(final Integer idCausa) {
        this.idCausa = idCausa;
    }

    public String getTituloSolucaoContorno() {
        return tituloSolucaoContorno;
    }

    public void setTituloSolucaoContorno(final String tituloSolucaoContorno) {
        this.tituloSolucaoContorno = tituloSolucaoContorno;
    }

    public Integer getIdSolucaoContorno() {
        return idSolucaoContorno;
    }

    public void setIdSolucaoContorno(final Integer idSolucaoContorno) {
        this.idSolucaoContorno = idSolucaoContorno;
    }

    public String getTituloSolCon() {
        return tituloSolCon;
    }

    public void setTituloSolCon(final String tituloSolCon) {
        this.tituloSolCon = tituloSolCon;
    }

    public String getDescSolCon() {
        return descSolCon;
    }

    public void setDescSolCon(final String descSolCon) {
        this.descSolCon = descSolCon;
    }

    public String getTituloSolucaoDefinitiva() {
        return tituloSolucaoDefinitiva;
    }

    public void setTituloSolucaoDefinitiva(final String tituloSolucaoDefinitiva) {
        this.tituloSolucaoDefinitiva = tituloSolucaoDefinitiva;
    }

    public Integer getIdSolucaoDefinitiva() {
        return idSolucaoDefinitiva;
    }

    public void setIdSolucaoDefinitiva(final Integer idSolucaoDefinitiva) {
        this.idSolucaoDefinitiva = idSolucaoDefinitiva;
    }

    public String getTituloSolDefinitiva() {
        return tituloSolDefinitiva;
    }

    public void setTituloSolDefinitiva(final String tituloSolDefinitiva) {
        this.tituloSolDefinitiva = tituloSolDefinitiva;
    }

    public String getDescSolDefinitiva() {
        return descSolDefinitiva;
    }

    public void setDescSolDefinitiva(final String descSolDefinitiva) {
        this.descSolDefinitiva = descSolDefinitiva;
    }

    public String getChamarTelaProblema() {
        return chamarTelaProblema;
    }

    public void setChamarTelaProblema(final String chamarTelaProblema) {
        this.chamarTelaProblema = chamarTelaProblema;
    }

    public String getInformacoesComplementares_serialize() {
        return informacoesComplementares_serialize;
    }

    public void setInformacoesComplementares_serialize(final String informacoesComplementares_serialize) {
        this.informacoesComplementares_serialize = informacoesComplementares_serialize;
    }

    public BaseEntity getInformacoesComplementares() {
        return informacoesComplementares;
    }

    public void setInformacoesComplementares(final BaseEntity informacoesComplementares) {
        this.informacoesComplementares = informacoesComplementares;
    }

    public String getFecharItensRelacionados() {
        return fecharItensRelacionados;
    }

    public void setFecharItensRelacionados(final String fecharItensRelacionados) {
        this.fecharItensRelacionados = fecharItensRelacionados;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(final String messageId) {
        this.messageId = messageId;
    }

    public Collection getColArquivosUpload() {
        return colArquivosUpload;
    }

    public void setColArquivosUpload(final Collection colArquivosUpload) {
        this.colArquivosUpload = colArquivosUpload;
    }

    public Integer getHiddenIdItemConfiguracao() {
        return hiddenIdItemConfiguracao;
    }

    public void setHiddenIdItemConfiguracao(final Integer hiddenIdItemConfiguracao) {
        this.hiddenIdItemConfiguracao = hiddenIdItemConfiguracao;
    }

    /**
     * @return the idProblemaRelacionado
     */
    public Integer getIdProblemaRelacionado() {
        return idProblemaRelacionado;
    }

    /**
     * @param idProblemaRelacionado
     *            the idProblemaRelacionado to set
     */
    public void setIdProblemaRelacionado(final Integer idProblemaRelacionado) {
        this.idProblemaRelacionado = idProblemaRelacionado;
    }

    /**
     * @return the listProblemaRelacionadoDTO
     */
    public List<ProblemaDTO> getListProblemaRelacionadoDTO() {
        return listProblemaRelacionadoDTO;
    }

    /**
     * @param listProblemaRelacionadoDTO
     *            the listProblemaRelacionadoDTO to set
     */
    public void setListProblemaRelacionadoDTO(final List<ProblemaDTO> listProblemaRelacionadoDTO) {
        this.listProblemaRelacionadoDTO = listProblemaRelacionadoDTO;
    }

    public String getUnidadeDes() {
        return unidadeDes;
    }

    public void setUnidadeDes(final String unidadeDes) {
        this.unidadeDes = unidadeDes;
    }

}
