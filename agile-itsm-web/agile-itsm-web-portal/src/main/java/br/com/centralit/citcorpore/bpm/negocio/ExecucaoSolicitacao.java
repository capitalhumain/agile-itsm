package br.com.centralit.citcorpore.bpm.negocio;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.bpm.dto.AtribuicaoFluxoDTO;
import br.com.centralit.bpm.dto.ElementoFluxoTarefaDTO;
import br.com.centralit.bpm.dto.EventoFluxoDTO;
import br.com.centralit.bpm.dto.FluxoDTO;
import br.com.centralit.bpm.dto.ItemTrabalhoFluxoDTO;
import br.com.centralit.bpm.dto.ObjetoNegocioFluxoDTO;
import br.com.centralit.bpm.dto.PermissoesFluxoDTO;
import br.com.centralit.bpm.dto.TarefaFluxoDTO;
import br.com.centralit.bpm.dto.TipoFluxoDTO;
import br.com.centralit.bpm.dto.UsuarioBpmDTO;
import br.com.centralit.bpm.integracao.AtribuicaoFluxoDao;
import br.com.centralit.bpm.integracao.ElementoFluxoDao;
import br.com.centralit.bpm.integracao.FluxoDao;
import br.com.centralit.bpm.integracao.ItemTrabalhoFluxoDao;
import br.com.centralit.bpm.integracao.TipoFluxoDao;
import br.com.centralit.bpm.negocio.ExecucaoFluxo;
import br.com.centralit.bpm.negocio.InstanciaFluxo;
import br.com.centralit.bpm.negocio.ItemTrabalho;
import br.com.centralit.bpm.negocio.UsuarioGrupo;
import br.com.centralit.bpm.util.Enumerados;
import br.com.centralit.citcorpore.bean.AcordoNivelServicoDTO;
import br.com.centralit.citcorpore.bean.AcordoServicoContratoDTO;
import br.com.centralit.citcorpore.bean.AlcadaProcessoNegocioDTO;
import br.com.centralit.citcorpore.bean.CalculoJornadaDTO;
import br.com.centralit.citcorpore.bean.CentroResultadoDTO;
import br.com.centralit.citcorpore.bean.ContratoDTO;
import br.com.centralit.citcorpore.bean.EmpregadoDTO;
import br.com.centralit.citcorpore.bean.ExecucaoSolicitacaoDTO;
import br.com.centralit.citcorpore.bean.FluxoServicoDTO;
import br.com.centralit.citcorpore.bean.GerenciamentoServicosDTO;
import br.com.centralit.citcorpore.bean.GrupoDTO;
import br.com.centralit.citcorpore.bean.GrupoEmpregadoDTO;
import br.com.centralit.citcorpore.bean.HistoricoSolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.JustificativaSolicitacaoDTO;
import br.com.centralit.citcorpore.bean.ModeloEmailDTO;
import br.com.centralit.citcorpore.bean.OcorrenciaSolicitacaoDTO;
import br.com.centralit.citcorpore.bean.ParamRecuperacaoTarefasDTO;
import br.com.centralit.citcorpore.bean.ReaberturaSolicitacaoDTO;
import br.com.centralit.citcorpore.bean.ServicoContratoDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.TarefaUsuarioDTO;
import br.com.centralit.citcorpore.bean.TemplateSolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.TempoAcordoNivelServicoDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.integracao.AcordoNivelServicoDao;
import br.com.centralit.citcorpore.integracao.AcordoServicoContratoDao;
import br.com.centralit.citcorpore.integracao.EmpregadoDao;
import br.com.centralit.citcorpore.integracao.ExecucaoSolicitacaoDao;
import br.com.centralit.citcorpore.integracao.FluxoServicoDao;
import br.com.centralit.citcorpore.integracao.GrupoDao;
import br.com.centralit.citcorpore.integracao.GrupoEmpregadoDao;
import br.com.centralit.citcorpore.integracao.ModeloEmailDao;
import br.com.centralit.citcorpore.integracao.OcorrenciaSolicitacaoDao;
import br.com.centralit.citcorpore.integracao.PermissoesFluxoDao;
import br.com.centralit.citcorpore.integracao.ReaberturaSolicitacaoDao;
import br.com.centralit.citcorpore.integracao.ServicoContratoDao;
import br.com.centralit.citcorpore.integracao.SolicitacaoServicoDao;
import br.com.centralit.citcorpore.integracao.TarefaUsuarioDao;
import br.com.centralit.citcorpore.integracao.TemplateSolicitacaoServicoDao;
import br.com.centralit.citcorpore.integracao.TempoAcordoNivelServicoDao;
import br.com.centralit.citcorpore.integracao.UsuarioDao;
import br.com.centralit.citcorpore.mail.MensagemEmail;
import br.com.centralit.citcorpore.negocio.CalendarioServiceEjb;
import br.com.centralit.citcorpore.negocio.GrupoService;
import br.com.centralit.citcorpore.negocio.HistoricoSolicitacaoServicoService;
import br.com.centralit.citcorpore.negocio.OcorrenciaSolicitacaoServiceEjb;
import br.com.centralit.citcorpore.negocio.SolicitacaoServicoService;
import br.com.centralit.citcorpore.negocio.SolicitacaoServicoServiceEjb;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.centralit.citcorpore.util.CriptoUtils;
import br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia;
import br.com.centralit.citcorpore.util.Enumerados.OrigemOcorrencia;
import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoSLA;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoSolicitacaoServico;
import br.com.centralit.citcorpore.util.Enumerados.TipoSolicitacaoServico;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.centralit.citcorpore.util.Util;
import br.com.citframework.comparacao.ObjectSimpleComparator;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.integracao.TransactionControlerImpl;
import br.com.citframework.integracao.core.Page;
import br.com.citframework.integracao.core.PageImpl;
import br.com.citframework.integracao.core.Pageable;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilI18N;
import br.com.citframework.util.UtilStrings;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ExecucaoSolicitacao extends ExecucaoFluxo {

    protected UsuarioDTO usuarioDTO = null;
    protected SolicitacaoServicoService solicitacaoServicoService;
    protected ExecucaoSolicitacaoDTO execucaoSolicitacaoDto;
    private String reabre = "";

    protected Double valorMensalUsoInterno = 0.0;
    protected Double valorAnualUsoInterno = 0.0;
    protected Double valorMensalAtendCliente = 0.0;
    protected Double valorAnualAtendCliente = 0.0;

    public Double getValorMensal() {
        return valorMensalUsoInterno + valorMensalAtendCliente;
    }

    public Double getValorAnual() {
        return valorAnualUsoInterno + valorAnualAtendCliente;
    }

    public Double getValorMensalUsoInterno() {
        return valorMensalUsoInterno;
    }

    public Double getValorAnualUsoInterno() {
        return valorAnualUsoInterno;
    }

    public Double getValorMensalAtendCliente() {
        return valorMensalAtendCliente;
    }

    public Double getValorAnualAtendCliente() {
        return valorAnualAtendCliente;
    }

    public String i18n_Message(final UsuarioDTO usuario, final String key) {
        if (usuario != null) {
            if (UtilI18N.internacionaliza(usuario.getLocale(), key) != null) {
                return UtilI18N.internacionaliza(usuario.getLocale(), key);
            }
            return key;
        }
        return key;
    }

    public ExecucaoSolicitacao(final TransactionControler tc) {
        super(tc);
    }

    public ExecucaoSolicitacao() {
        super();
    }

    public ExecucaoSolicitacao(final SolicitacaoServicoDTO solicitacaoServicoDto, final TransactionControler tc) {
        super(solicitacaoServicoDto, tc);
    }

    @Override
    public InstanciaFluxo inicia(final String nomeFluxo, final Integer idFase) throws Exception {
        final TipoFluxoDao tipoFluxoDao = new TipoFluxoDao();
        final TipoFluxoDTO tipoFluxoDto = tipoFluxoDao.findByNome(nomeFluxo);
        if (tipoFluxoDto == null) {
            throw new Exception("Fluxo " + nomeFluxo + " não existe");
        }

        return this.inicia(new FluxoDao().findByTipoFluxo(tipoFluxoDto.getIdTipoFluxo()), idFase);
    }

    @Override
    public InstanciaFluxo inicia() throws Exception {
        InstanciaFluxo result = null;
        final FluxoServicoDTO fluxoServicoDto = new FluxoServicoDao().findPrincipalByIdServicoContrato(this.getSolicitacaoServicoDto().getIdServicoContrato());
        if (fluxoServicoDto != null) {
            result = this.inicia(new FluxoDao().findByTipoFluxo(fluxoServicoDto.getIdTipoFluxo()), null);
        } else {
            final String fluxoPadrao = ParametroUtil.getValor(ParametroSistema.NomeFluxoPadraoServicos, this.getTransacao(), null);
            if (fluxoPadrao == null) {
                throw new Exception("Fluxo padrão não parametrizado");
            }
            final String idFaseStr = ParametroUtil.getValor(ParametroSistema.IDFaseExecucaoServicos, this.getTransacao(), null);
            if (idFaseStr == null) {
                throw new Exception("Fase padrão de execução não encontrada");
            }
            result = this.inicia(fluxoPadrao, new Integer(idFaseStr));
        }

        try {
            final String enviarNotificacao = ParametroUtil.getValor(ParametroSistema.NOTIFICAR_GRUPO_RECEPCAO_SOLICITACAO, this.getTransacao(), "N");

            final String enviarNotificacaoSolicitacoesVinculadas = ParametroUtil.getValor(
                    ParametroSistema.RECEBER_NOTIFICACAO_ENCERRAR_ESCALONAR_SOLICITACOES_VINCULADAS, this.getTransacao(), "S");

            if (enviarNotificacao.equalsIgnoreCase("S") && this.getSolicitacaoServicoDto().escalada()) {

                if (enviarNotificacaoSolicitacoesVinculadas.equalsIgnoreCase("S")) {
                    if (this.getSolicitacaoServicoDto().getIdSolicitacaoRelacionada() != null) {
                        SolicitacaoServicoDTO solicitacaoServicoRelacionada = new SolicitacaoServicoDTO();
                        solicitacaoServicoRelacionada.setIdSolicitacaoServico(this.getSolicitacaoServicoDto().getIdSolicitacaoRelacionada());
                        solicitacaoServicoRelacionada = (SolicitacaoServicoDTO) this.getSolicitacaoServicoDAO().restore(solicitacaoServicoRelacionada);

                        final String idModeloEmailCriacao = ParametroUtil.getValor(
                                ParametroSistema.ID_MODELO_EMAIL_CRIACAO_GRUPO_EXECUTOR_SOLICITACAO_RELACIONADA, this.getTransacao(), "84");
                        if (idModeloEmailCriacao != null) {
                            this.enviaEmailGrupo(Integer.parseInt(idModeloEmailCriacao), solicitacaoServicoRelacionada.getIdGrupoAtual());
                        } else {
                            throw new Exception("Não há modelo de e-mail setado nos parâmetros.");
                        }
                    }
                }
                this.enviaEmailGrupo(Integer.parseInt(ParametroUtil.getValor(ParametroSistema.ID_MODELO_EMAIL_GRUPO_DESTINO, this.getTransacao(), null)), this
                        .getSolicitacaoServicoDto().getIdGrupoAtual());
            }
        } catch (final NumberFormatException e) {
            System.out.println("Não há modelo de e-mail setado nos parâmetros.");
        }
        return result;
    }

    @Override
    public InstanciaFluxo inicia(final FluxoDTO fluxoDto, final Integer idFase) throws Exception {
        if (fluxoDto == null) {
            throw new Exception("Fluxo não encontrado");
        }

        Integer idFaseFluxo = idFase;
        this.fluxoDto = fluxoDto;

        if (idFaseFluxo == null) {
            FluxoServicoDTO fluxoServicoDto = new FluxoServicoDTO();
            final FluxoServicoDao fluxoServicoDao = new FluxoServicoDao();
            fluxoServicoDto = fluxoServicoDao.findByIdServicoContratoAndIdTipoFluxo(this.getSolicitacaoServicoDto().getIdServicoContrato(),
                    fluxoDto.getIdTipoFluxo());
            if (fluxoServicoDto == null) {
                throw new Exception("Fluxo " + fluxoDto.getNomeFluxo() + " não está associado a este tipo de solicitação");
            }
            idFaseFluxo = fluxoServicoDto.getIdFase();
        }

        this.atualizaFaseSolicitacao(idFaseFluxo);

        final AcordoNivelServicoDao acordoNivelServicoDao = new AcordoNivelServicoDao();
        AcordoNivelServicoDTO acordoNivelServicoDto = new AcordoNivelServicoDTO();
        this.setTransacaoDao(acordoNivelServicoDao);
        acordoNivelServicoDto = acordoNivelServicoDao.findAtivoByIdServicoContrato(this.getSolicitacaoServicoDto().getIdServicoContrato(), "T");
        if (acordoNivelServicoDto == null) {
            if (reabre == "S") {
                final AcordoNivelServicoDTO acordoNivelServicoAux = acordoNivelServicoDao.findByIdAcordoNivelServicoEServicoContrato(this
                        .getSolicitacaoServicoDto().getIdAcordoNivelServico(), this.getSolicitacaoServicoDto().getIdServicoContrato());
                if (acordoNivelServicoAux != null) {
                    acordoNivelServicoDto = acordoNivelServicoAux;
                }
            }
            if (acordoNivelServicoDto == null) {
                // Se nao houver acordo especifico, ou seja, associado direto ao
                // servicocontrato, entao busca um acordo geral que esteja vinculado
                // ao servicocontrato.
                final AcordoServicoContratoDTO acordoServicoContratoDTO = new AcordoServicoContratoDao().findAtivoByIdServicoContrato(this
                        .getSolicitacaoServicoDto().getIdServicoContrato(), "T");
                if (acordoServicoContratoDTO == null) {
                    throw new Exception("solicitacaoservico.validacao.tempoacordo");
                }
                // Apos achar a vinculacao do acordo com o servicocontrato, entao
                // faz um restore do acordo de nivel de servico.
                acordoNivelServicoDto = new AcordoNivelServicoDTO();
                acordoNivelServicoDto.setIdAcordoNivelServico(acordoServicoContratoDTO.getIdAcordoNivelServico());
                acordoNivelServicoDto = (AcordoNivelServicoDTO) acordoNivelServicoDao.restore(acordoNivelServicoDto);
                if (acordoNivelServicoDto == null) {
                    // Se nao houver acordo especifico, ou seja, associado direto ao
                    // servicocontrato
                    throw new Exception("solicitacaoservico.validacao.tempoacordo");
                }
            }
        }

        TempoAcordoNivelServicoDTO tempoDto = new TempoAcordoNivelServicoDTO();
        final TempoAcordoNivelServicoDao tempoDao = new TempoAcordoNivelServicoDao();
        this.setTransacaoDao(tempoDao);
        tempoDto.setIdAcordoNivelServico(acordoNivelServicoDto.getIdAcordoNivelServico());
        tempoDto.setIdPrioridade(this.getSolicitacaoServicoDto().getIdPrioridade());
        tempoDto.setIdFase(this.getSolicitacaoServicoDto().getIdFaseAtual());
        tempoDto = (TempoAcordoNivelServicoDTO) tempoDao.restore(tempoDto);
        if (tempoDto == null) {
            throw new Exception("Não existem prazos de atendimento associados ao serviço/prioridade desta solicitação");
        }

        final HashMap<String, Object> map = new HashMap<>();
        this.mapObjetoNegocio(map);
        final InstanciaFluxo instanciaFluxo = new InstanciaFluxo(this, map);

        final ExecucaoSolicitacaoDTO execucaoDto = new ExecucaoSolicitacaoDTO();
        execucaoDto.setIdSolicitacaoServico(this.getSolicitacaoServicoDto().getIdSolicitacaoServico());
        execucaoDto.setIdFase(this.getSolicitacaoServicoDto().getIdFaseAtual());
        execucaoDto.setIdFluxo(instanciaFluxo.getIdFluxo());
        execucaoDto.setIdInstanciaFluxo(instanciaFluxo.getIdInstancia());
        Integer seqReabertura = 0;
        if (this.getSolicitacaoServicoDto().getSeqReabertura() != null && this.getSolicitacaoServicoDto().getSeqReabertura().intValue() > 0) {
            seqReabertura = this.getSolicitacaoServicoDto().getSeqReabertura();
        }
        if (seqReabertura.intValue() > 0) {
            execucaoDto.setSeqReabertura(this.getSolicitacaoServicoDto().getSeqReabertura());
        }

        execucaoDto.setPrazoHH(tempoDto.getTempoHH());
        execucaoDto.setPrazoMM(tempoDto.getTempoMM());

        final ExecucaoSolicitacaoDao execucaoDao = new ExecucaoSolicitacaoDao();
        this.setTransacaoDao(execucaoDao);
        execucaoFluxoDto = (ExecucaoSolicitacaoDTO) execucaoDao.create(execucaoDto);

        if (seqReabertura.intValue() == 0 && this.getSolicitacaoServicoDto().getEnviaEmailCriacao() != null
                && this.getSolicitacaoServicoDto().getEnviaEmailCriacao().equalsIgnoreCase("S")) {
            final ServicoContratoDTO servicoContratoDto = this.recuperaServicoContrato();
            this.enviaEmail(servicoContratoDto.getIdModeloEmailCriacao());
        }
        reabre = "";
        return instanciaFluxo;
    }

    @Override
    public void executa(final String loginUsuario, final ObjetoNegocioFluxoDTO objetoNegocioDto, final Integer idItemTrabalho, final String acao,
            final HashMap<String, Object> map) throws Exception {
        if (acao.equals(Enumerados.ACAO_DELEGAR)) {
            return;
        }

        final TarefaFluxoDTO tarefaFluxoDto = this.recuperaTarefa(idItemTrabalho);
        if (tarefaFluxoDto == null) {
            return;
        }

        OcorrenciaSolicitacaoDTO ocorrenciaSolicitacao = new OcorrenciaSolicitacaoDTO();
        final ExecucaoSolicitacaoDao execucaoSolicitacaoDao = new ExecucaoSolicitacaoDao();
        this.setTransacaoDao(execucaoSolicitacaoDao);
        final ExecucaoSolicitacaoDTO execucaoSolicitacaoDto = execucaoSolicitacaoDao.findByIdInstanciaFluxo(tarefaFluxoDto.getIdInstancia());
        if (execucaoSolicitacaoDto == null) {
            return;
        }

        this.recuperaFluxo(execucaoSolicitacaoDto.getIdFluxo());

        this.objetoNegocioDto = objetoNegocioDto;
        this.getSolicitacaoServicoDto().setIdTarefa(tarefaFluxoDto.getIdItemTrabalho());
        this.getSolicitacaoServicoDto().setNomeTarefa(tarefaFluxoDto.getElementoFluxoDto().getDocumentacao());

        if (this.getSolicitacaoServicoDto().getIdGrupoNivel1() == null || this.getSolicitacaoServicoDto().getIdGrupoNivel1().intValue() == 0) {
            throw new LogicException("Grupo nível 1 não informado ou erro na recuperação do atributo");
        }

        usuarioDTO = new UsuarioDTO();
        usuarioDTO = new UsuarioDao().restoreByLogin(loginUsuario);

        final InstanciaFluxo instanciaFluxo = new InstanciaFluxo(this, tarefaFluxoDto.getIdInstancia());
        this.mapObjetoNegocio(instanciaFluxo.getObjetos(map));

        if (acao.equals(Enumerados.ACAO_INICIAR)) {
            instanciaFluxo.iniciaItemTrabalho(loginUsuario, tarefaFluxoDto.getIdItemTrabalho(), map);
            ocorrenciaSolicitacao = OcorrenciaSolicitacaoServiceEjb.create(this.getSolicitacaoServicoDto(), tarefaFluxoDto, "Execução da tarefa \""
                    + tarefaFluxoDto.getElementoFluxoDto().getDocumentacao() + "\"", OrigemOcorrencia.OUTROS, CategoriaOcorrencia.Execucao, "não se aplica",
                    CategoriaOcorrencia.Execucao.getDescricao(), usuarioDTO, 0, null, this.getTransacao());
            this.popularHistorico(this.getSolicitacaoServicoDto(), ocorrenciaSolicitacao, "Executa", usuarioDTO);
        } else if (acao.equals(Enumerados.ACAO_EXECUTAR)) {
            instanciaFluxo.executaItemTrabalho(loginUsuario, tarefaFluxoDto.getIdItemTrabalho(), map);

            Integer tempo = 0;
            try {
                final Integer idCalendario = this.getIdCalendario(this.getSolicitacaoServicoDto());
                CalculoJornadaDTO calculoDto = new CalculoJornadaDTO(idCalendario, tarefaFluxoDto.getDataHoraCriacao());
                calculoDto = new CalendarioServiceEjb().calculaPrazoDecorrido(calculoDto, UtilDatas.getDataHoraAtual(), this.getTransacao());
                tempo = calculoDto.getTempoDecorridoHH() * 60 + calculoDto.getTempoDecorridoMM();
            } catch (final Exception e) {
                System.out.println("#### Erro no cálculo do tempo decorrido da tarefa");
                e.printStackTrace();
            }
            ocorrenciaSolicitacao = OcorrenciaSolicitacaoServiceEjb.create(this.getSolicitacaoServicoDto(), tarefaFluxoDto, "Execução da tarefa \""
                    + tarefaFluxoDto.getElementoFluxoDto().getDocumentacao() + "\"", OrigemOcorrencia.OUTROS, CategoriaOcorrencia.Execucao, "não se aplica",
                    CategoriaOcorrencia.Execucao.getDescricao(), usuarioDTO, tempo.intValue(), null, this.getTransacao());
            this.popularHistorico(this.getSolicitacaoServicoDto(), ocorrenciaSolicitacao, "Executa", usuarioDTO);
        }

        if (this.getSolicitacaoServicoDto() != null && this.getSolicitacaoServicoDto().getIdSolicitacaoServico() != null) {
            this.atualizaidResponsalvelAtualSolicitacao();
        }

        if (this.getSolicitacaoServicoDto().getEnviaEmailAcoes() != null && this.getSolicitacaoServicoDto().getEnviaEmailAcoes().equalsIgnoreCase("S")) {
            this.getSolicitacaoServicoDto().setNomeTarefa(tarefaFluxoDto.getElementoFluxoDto().getDocumentacao());
            final ServicoContratoDTO servicoContratoDto = this.recuperaServicoContrato();

            final String enviarNotificacaoSolicitacoesVinculadas = ParametroUtil.getValor(
                    ParametroSistema.RECEBER_NOTIFICACAO_ENCERRAR_ESCALONAR_SOLICITACOES_VINCULADAS, this.getTransacao(), "S");

            if (enviarNotificacaoSolicitacoesVinculadas.equalsIgnoreCase("S")) {
                if (this.getSolicitacaoServicoDto().getIdSolicitacaoRelacionada() != null) {
                    SolicitacaoServicoDTO solicitacaoServicoRelacionada = new SolicitacaoServicoDTO();
                    solicitacaoServicoRelacionada.setIdSolicitacaoServico(this.getSolicitacaoServicoDto().getIdSolicitacaoRelacionada());
                    solicitacaoServicoRelacionada = (SolicitacaoServicoDTO) this.getSolicitacaoServicoDAO().restore(solicitacaoServicoRelacionada);

                    final String idModeloEmailAcoes = ParametroUtil.getValor(ParametroSistema.ID_MODELO_EMAIL_ACOES_GRUPO_EXECUTOR_SOLICITACAO_RELACIONADA,
                            this.getTransacao(), "85");
                    if (idModeloEmailAcoes != null) {
                        this.enviaEmailGrupo(Integer.parseInt(idModeloEmailAcoes), solicitacaoServicoRelacionada.getIdGrupoAtual());
                    } else {
                        throw new Exception("Não há modelo de e-mail setado nos parâmetros.");
                    }

                }
            }

            this.enviaEmail(servicoContratoDto.getIdModeloEmailAcoes());
        }

        if (tarefaFluxoDto.getElementoFluxoDto().getContabilizaSLA() == null || tarefaFluxoDto.getElementoFluxoDto().getContabilizaSLA().equalsIgnoreCase("S")) {
            if (this.getSolicitacaoServicoDto().getDataHoraCaptura() == null) {
                this.getSolicitacaoServicoDto().setDataHoraCaptura(UtilDatas.getDataHoraAtual());
                this.setTransacaoDao(this.getSolicitacaoServicoDAO());
                this.getSolicitacaoServicoDAO().atualizaDataHoraCaptura(this.getSolicitacaoServicoDto());
            }
        }

    }

    @Override
    public void delega(final String loginUsuario, final ObjetoNegocioFluxoDTO objetoNegocioDto, final Integer idItemTrabalho, final String usuarioDestino,
            final String grupoDestino) throws Exception {
        final TarefaFluxoDTO tarefaFluxoDto = this.recuperaTarefa(idItemTrabalho);
        if (tarefaFluxoDto == null) {
            return;
        }

        final InstanciaFluxo instanciaFluxo = new InstanciaFluxo(this, tarefaFluxoDto.getIdInstancia());
        instanciaFluxo.delegaItemTrabalho(loginUsuario, idItemTrabalho, usuarioDestino, grupoDestino);

        this.objetoNegocioDto = objetoNegocioDto;

        usuarioDTO = new UsuarioDTO();
        usuarioDTO = new UsuarioDao().restoreByLogin(loginUsuario);

        final UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
        final UsuarioBpmDTO usuarioBpmDto = usuarioGrupo.recuperaUsuario(usuarioDestino);

        String ocorr = "Compartilhamento da tarefa \"" + tarefaFluxoDto.getElementoFluxoDto().getDocumentacao() + "\"";
        if (usuarioDestino != null) {
            ocorr += " com o usuário " + usuarioBpmDto.getNome();
        }
        if (grupoDestino != null) {
            ocorr += " com o grupo " + grupoDestino;
        }

        OcorrenciaSolicitacaoServiceEjb.create(this.getSolicitacaoServicoDto(), tarefaFluxoDto, ocorr, OrigemOcorrencia.OUTROS,
                CategoriaOcorrencia.Compartilhamento, "não se aplica", CategoriaOcorrencia.Compartilhamento.getDescricao(), usuarioDTO, 0, null,
                this.getTransacao());
    }

    @Override
    public void direcionaAtendimento(final String loginUsuario, final ObjetoNegocioFluxoDTO objetoNegocioDto, final String grupoAtendimento) throws Exception {
        if (this.getSolicitacaoServicoDto() == null) {
            return;
        }

        if (grupoAtendimento == null) {
            return;
        }

        final GrupoDTO grupoAtendimentoDto = new GrupoDao().restoreBySigla(grupoAtendimento);
        if (grupoAtendimentoDto == null) {
            return;
        }

        UsuarioDTO usuarioRespDto = new UsuarioDTO();
        usuarioRespDto.setIdUsuario(this.getSolicitacaoServicoDto().getIdResponsavel());
        usuarioRespDto = (UsuarioDTO) new UsuarioDao().restore(usuarioRespDto);

        this.objetoNegocioDto = objetoNegocioDto;

        final Collection<ExecucaoSolicitacaoDTO> colExecucao = new ExecucaoSolicitacaoDao().listByIdSolicitacao(this.getSolicitacaoServicoDto()
                .getIdSolicitacaoServico());
        if (colExecucao != null) {
            final ItemTrabalhoFluxoDao itemTrabalhoFluxoDao = new ItemTrabalhoFluxoDao();
            this.setTransacaoDao(itemTrabalhoFluxoDao);
            final OcorrenciaSolicitacaoDao ocorrenciaSolicitacaoDao = new OcorrenciaSolicitacaoDao();
            this.setTransacaoDao(ocorrenciaSolicitacaoDao);
            for (final ExecucaoSolicitacaoDTO execucaoSolicitacaoDto : colExecucao) {
                final InstanciaFluxo instanciaFluxo = new InstanciaFluxo(this, execucaoSolicitacaoDto.getIdInstanciaFluxo());
                final Collection<ItemTrabalhoFluxoDTO> colItens = itemTrabalhoFluxoDao.findDisponiveisByIdInstancia(execucaoSolicitacaoDto
                        .getIdInstanciaFluxo());
                if (colItens != null) {
                    for (final ItemTrabalhoFluxoDTO itemTrabalhoFluxoDto : colItens) {
                        final ItemTrabalho itemTrabalho = ItemTrabalho.getItemTrabalho(instanciaFluxo, itemTrabalhoFluxoDto.getIdItemTrabalho());
                        itemTrabalho.redireciona(loginUsuario, null, grupoAtendimento);

                        usuarioDTO = new UsuarioDTO();
                        usuarioDTO = new UsuarioDao().restoreByLogin(loginUsuario);

                        String ocorr = "Direcionamento da tarefa \"" + itemTrabalho.getElementoFluxoDto().getDocumentacao() + "\"";
                        ocorr += " para o grupo " + grupoAtendimento;

                        OcorrenciaSolicitacaoServiceEjb.create(this.getSolicitacaoServicoDto(), itemTrabalhoFluxoDto, ocorr, OrigemOcorrencia.OUTROS,
                                CategoriaOcorrencia.Direcionamento, "não se aplica", CategoriaOcorrencia.Direcionamento.getDescricao(), usuarioDTO, 0, null,
                                this.getTransacao());
                    }
                }
            }
        }

        try {
            final String enviarNotificacao = ParametroUtil.getValor(ParametroSistema.NOTIFICAR_GRUPO_RECEPCAO_SOLICITACAO, this.getTransacao(), "N");
            if (enviarNotificacao.equalsIgnoreCase("S")) {
                this.enviaEmailGrupo(Integer.parseInt(ParametroUtil.getValor(ParametroSistema.ID_MODELO_EMAIL_GRUPO_DESTINO, this.getTransacao(), null)),
                        grupoAtendimentoDto.getIdGrupo());
            }
        } catch (final NumberFormatException e) {
            System.out.println("Não há modelo de e-mail setado nos parâmetros.");
        }
    }

    @Override
    public void mapObjetoNegocio(final Map<String, Object> map) throws Exception {
        final SolicitacaoServicoDTO solicitacaoServicoDto = (SolicitacaoServicoDTO) objetoNegocioDto;
        final SolicitacaoServicoDTO solicitacaoAuxDto = new SolicitacaoServicoServiceEjb().restoreAll(solicitacaoServicoDto.getIdSolicitacaoServico(),
                this.getTransacao());
        if (solicitacaoAuxDto != null) {
            solicitacaoServicoDto.setGrupoAtual(solicitacaoAuxDto.getGrupoAtual());
            solicitacaoServicoDto.setGrupoNivel1(solicitacaoAuxDto.getGrupoNivel1());
        }

        this.adicionaObjeto("solicitacaoServico", solicitacaoServicoDto, map);
        if (usuarioDTO != null) {
            this.adicionaObjeto("usuario", usuarioDTO, map);
        } else if (solicitacaoServicoDto.getUsuarioDto() != null) {
            this.adicionaObjeto("usuario", solicitacaoServicoDto.getUsuarioDto(), map);
        }

        this.adicionaObjeto("execucaoFluxo", this, map);
        this.adicionaObjeto("solicitacaoServicoService", new SolicitacaoServicoServiceEjb(), map);
    }

    public SolicitacaoServicoDTO getSolicitacaoServicoDto() {
        return (SolicitacaoServicoDTO) objetoNegocioDto;
    }

    @Override
    public List<TarefaFluxoDTO> recuperaTarefas(final String loginUsuario, final Integer idTarefa) throws Exception {
        final TransactionControler tc = new TransactionControlerImpl(this.getJdbcAliasBPM());
        try {
            this.setTransacao(tc);
            List<TarefaFluxoDTO> result = null;
            final List<TarefaFluxoDTO> listTarefas = super.recuperaTarefas(loginUsuario, idTarefa);
            if (listTarefas != null) {
                result = new ArrayList<>();
                final SolicitacaoServicoServiceEjb solicitacaoServicoService = new SolicitacaoServicoServiceEjb();

                final Collection<SolicitacaoServicoDTO> listSolicitacaoServicoDto = solicitacaoServicoService.listByTarefas(listTarefas, tc);

                final Collection<SolicitacaoServicoDTO> listSolicitacoesFilhas = solicitacaoServicoService.listSolicitacoesFilhas(tc);

                if (listSolicitacaoServicoDto != null && !listSolicitacaoServicoDto.isEmpty()) {
                    for (final SolicitacaoServicoDTO solicitacaoServicoDto : listSolicitacaoServicoDto) {
                        for (final TarefaFluxoDTO tarefaDto : listTarefas) {
                            if (solicitacaoServicoDto.getIdInstanciaFluxo().equals(tarefaDto.getIdInstancia())) {

                                boolean possuiFilho = false;

                                if (listSolicitacoesFilhas != null && !listSolicitacoesFilhas.isEmpty()) {
                                    for (final SolicitacaoServicoDTO solicitacaoServicoDTO2 : listSolicitacoesFilhas) {
                                        if (solicitacaoServicoDto.getIdSolicitacaoServico().equals(solicitacaoServicoDTO2.getIdSolicitacaoPai())) {
                                            possuiFilho = true;
                                            break;
                                        }
                                    }
                                }

                                solicitacaoServicoDto.setPossuiFilho(possuiFilho);

                                tarefaDto.setSolicitacaoDto(solicitacaoServicoDto);
                                tarefaDto.setDataHoraLimite(solicitacaoServicoDto.getDataHoraLimite());
                                result.add(tarefaDto);
                            }
                        }
                    }
                }
                if (result != null) {
                    Collections.sort(result, new ObjectSimpleComparator("getDataHoraLimite", ObjectSimpleComparator.ASC));
                }
            }
            return result;
        } finally {
            tc.closeQuietly();
        }
    }

    public List<TarefaFluxoDTO> recuperaTarefas(final String loginUsuario, final TipoSolicitacaoServico[] tiposSolicitacao, final String somenteEmAprovacao)
            throws Exception {
        final TransactionControler tc = new TransactionControlerImpl(this.getJdbcAliasBPM());
        try {
            this.setTransacao(tc);
            List<TarefaFluxoDTO> result = null;
            final List<TarefaFluxoDTO> tarefasDoUsuario = super.recuperaTarefas(loginUsuario);
            if (tarefasDoUsuario != null) {
                final TemplateSolicitacaoServicoDao templateSolicitacaoServicoDao = new TemplateSolicitacaoServicoDao();
                this.setTransacaoDao(templateSolicitacaoServicoDao);
                List<TarefaFluxoDTO> listTarefas = new ArrayList<>();
                if (somenteEmAprovacao != null && somenteEmAprovacao.equalsIgnoreCase("S")) {
                    final List<TemplateSolicitacaoServicoDTO> templates = templateSolicitacaoServicoDao.listComAprovacao();
                    final HashMap<String, TemplateSolicitacaoServicoDTO> mapTemplates = new HashMap<String, TemplateSolicitacaoServicoDTO>();
                    if (templates != null) {
                        for (final TemplateSolicitacaoServicoDTO templateDto : templates) {
                            mapTemplates.put(templateDto.getIdentificacao().toUpperCase(), templateDto);
                        }
                    }
                    for (final TarefaFluxoDTO tarefaDto : tarefasDoUsuario) {
                        if (tarefaDto.getElementoFluxoDto().getTemplate() != null && !tarefaDto.getElementoFluxoDto().getTemplate().equals("")) {
                            if (mapTemplates.get(tarefaDto.getElementoFluxoDto().getTemplate().toUpperCase()) != null) {
                                listTarefas.add(tarefaDto);
                            }
                        }
                    }
                } else {
                    listTarefas = tarefasDoUsuario;
                }

                if (listTarefas.size() == 0) {
                    return null;
                }

                result = new ArrayList<>();
                final SolicitacaoServicoServiceEjb solicitacaoServicoService = new SolicitacaoServicoServiceEjb();

                final Collection<SolicitacaoServicoDTO> listSolicitacaoServicoDto = solicitacaoServicoService.listByTarefas(listTarefas, tiposSolicitacao, tc);

                final Collection<SolicitacaoServicoDTO> listSolicitacoesFilhas = solicitacaoServicoService.listSolicitacoesFilhas(tc);

                if (listSolicitacaoServicoDto != null && !listSolicitacaoServicoDto.isEmpty()) {
                    for (final SolicitacaoServicoDTO solicitacaoServicoDto : listSolicitacaoServicoDto) {
                        for (final TarefaFluxoDTO tarefaDto : listTarefas) {
                            if (solicitacaoServicoDto.getIdInstanciaFluxo().equals(tarefaDto.getIdInstancia())) {

                                boolean possuiFilho = false;

                                if (listSolicitacoesFilhas != null && !listSolicitacoesFilhas.isEmpty()) {
                                    for (final SolicitacaoServicoDTO solicitacaoServicoDTO2 : listSolicitacoesFilhas) {
                                        if (solicitacaoServicoDto.getIdSolicitacaoServico().equals(solicitacaoServicoDTO2.getIdSolicitacaoPai())) {
                                            possuiFilho = true;
                                            break;
                                        }
                                    }
                                }

                                solicitacaoServicoDto.setPossuiFilho(possuiFilho);

                                tarefaDto.setSolicitacaoDto(solicitacaoServicoDto);
                                tarefaDto.setDataHoraLimite(solicitacaoServicoDto.getDataHoraLimite());
                                result.add(tarefaDto);
                            }
                        }
                    }
                }
                Collections.sort(result, new ObjectSimpleComparator("getDataHoraLimite", ObjectSimpleComparator.ASC));
            }
            return result;
        } finally {
            tc.closeQuietly();
        }
    }

    public List<TarefaFluxoDTO> recuperaTarefas(final Integer qtdAtual, final Integer qtdAPaginacao, final String login) throws Exception {
        final TransactionControler tc = new TransactionControlerImpl(this.getJdbcAliasBPM());
        try {
            this.setTransacao(tc);
            List<TarefaFluxoDTO> result = null;
            final List<TarefaFluxoDTO> listTarefas = super.recuperaTarefas(login);
            if (listTarefas != null) {
                result = new ArrayList<>();
                final SolicitacaoServicoServiceEjb solicitacaoServicoService = new SolicitacaoServicoServiceEjb();

                final Collection<SolicitacaoServicoDTO> listSolicitacaoServicoDto = solicitacaoServicoService.listByTarefas(listTarefas, qtdAtual,
                        qtdAPaginacao, tc);

                final Collection<SolicitacaoServicoDTO> listSolicitacoesFilhas = solicitacaoServicoService.listSolicitacoesFilhas(tc);

                if (listSolicitacaoServicoDto != null && !listSolicitacaoServicoDto.isEmpty()) {
                    for (final SolicitacaoServicoDTO solicitacaoServicoDto : listSolicitacaoServicoDto) {
                        for (final TarefaFluxoDTO tarefaDto : listTarefas) {
                            if (solicitacaoServicoDto.getIdInstanciaFluxo().equals(tarefaDto.getIdInstancia())) {

                                boolean possuiFilho = false;

                                if (listSolicitacoesFilhas != null && !listSolicitacoesFilhas.isEmpty()) {
                                    for (final SolicitacaoServicoDTO solicitacaoServicoDTO2 : listSolicitacoesFilhas) {
                                        if (solicitacaoServicoDto.getIdSolicitacaoServico().equals(solicitacaoServicoDTO2.getIdSolicitacaoPai())) {
                                            possuiFilho = true;
                                            break;
                                        }
                                    }
                                }

                                solicitacaoServicoDto.setPossuiFilho(possuiFilho);

                                tarefaDto.setSolicitacaoDto(solicitacaoServicoDto);
                                tarefaDto.setDataHoraLimite(solicitacaoServicoDto.getDataHoraLimite());
                                result.add(tarefaDto);
                            }
                        }
                    }
                }
                Collections.sort(result, new ObjectSimpleComparator("getDataHoraLimite", ObjectSimpleComparator.ASC));
            }
            return result;
        } finally {
            tc.closeQuietly();
        }
    }

    public List<TarefaFluxoDTO> recuperaTarefas(final String loginUsuario, final String campoOrdenacao, final Boolean asc) throws Exception {
        final TransactionControler tc = new TransactionControlerImpl(this.getJdbcAliasBPM());
        try {
            this.setTransacao(tc);
            List<TarefaFluxoDTO> result = null;
            final List<TarefaFluxoDTO> listTarefas = super.recuperaTarefas(loginUsuario);
            if (listTarefas != null) {
                result = new ArrayList<>();
                final SolicitacaoServicoServiceEjb solicitacaoServicoService = new SolicitacaoServicoServiceEjb();
                for (final TarefaFluxoDTO tarefaDto : listTarefas) {
                    final SolicitacaoServicoDTO solicitacaoServicoDto = solicitacaoServicoService.restoreByIdInstanciaFluxo(tarefaDto.getIdInstancia(), tc);
                    if (solicitacaoServicoDto != null) {
                        tarefaDto.setSolicitacaoDto(solicitacaoServicoDto);
                        tarefaDto.setDataHoraLimite(solicitacaoServicoDto.getDataHoraLimite());
                        result.add(tarefaDto);
                    }
                }
            }
            return result;
        } finally {
            tc.closeQuietly();
        }
    }

    public Integer totalPaginas(final Integer itensPorPagina, final String loginUsuario) throws Exception {
        final TransactionControler tc = new TransactionControlerImpl(this.getJdbcAliasBPM());
        try {
            this.setTransacao(tc);
            Integer total = 0;
            final List<TarefaFluxoDTO> listTarefas = super.recuperaTarefas(loginUsuario);
            if (listTarefas != null) {
                total = this.getSolicitacaoServicoDAO().totalDePaginas(itensPorPagina, listTarefas);
            }
            return total;
        } finally {
            tc.closeQuietly();
        }
    }

    private void atualizaFaseSolicitacao(final Integer idFase) throws Exception {
        this.setTransacaoDao(this.getSolicitacaoServicoDAO());
        this.getSolicitacaoServicoDto().setIdFaseAtual(idFase);
        this.getSolicitacaoServicoDAO().updateNotNull(this.getSolicitacaoServicoDto());
    }

    private void atualizaidResponsalvelAtualSolicitacao() throws Exception {
        try {
            if (this.getSolicitacaoServicoDto() != null && this.getSolicitacaoServicoDto().getIdSolicitacaoServico() != null) {
                final SolicitacaoServicoDTO solicitacaoServicoDTO = this.getSolicitacaoServicoDto();
                this.getSolicitacaoServicoDAO().setTransactionControler(this.getTransacao());
                final List<SolicitacaoServicoDTO> listSolicitacaoServico = (List<SolicitacaoServicoDTO>) this.getSolicitacaoServicoDAO().findResponsavelAtual(
                        solicitacaoServicoDTO.getIdSolicitacaoServico());

                if (listSolicitacaoServico != null) {
                    solicitacaoServicoDTO.setIdUsuarioResponsavelAtual(listSolicitacaoServico.get(0).getIdUsuarioResponsavelAtual());
                    this.getSolicitacaoServicoDAO().atualizaIdUsuarioResponsavel(solicitacaoServicoDTO);
                } else {
                    this.getSolicitacaoServicoDAO().atualizaIdUsuarioResponsavel(solicitacaoServicoDTO);
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enviaEmail(final String identificador) throws Exception {
        if (identificador == null) {
            return;
        }

        final ModeloEmailDTO modeloEmailDto = new ModeloEmailDao().findByIdentificador(identificador);
        if (modeloEmailDto != null) {
            this.enviaEmail(modeloEmailDto.getIdModeloEmail());
        }
    }

    public boolean isEmailHabilitado() throws Exception {
        final String enviaEmail = ParametroUtil.getValor(ParametroSistema.EnviaEmailFluxo, this.getTransacao(), "N");
        return enviaEmail.equalsIgnoreCase("S");
    }

    public String getRemetenteEmail() throws Exception {
        final String remetente = ParametroUtil.getValor(ParametroSistema.RemetenteNotificacoesSolicitacao, this.getTransacao(), null);
        if (remetente == null) {
            throw new LogicException("Remetente para notificações de solicitação de serviço não foi parametrizado");
        }
        return remetente;
    }

    public void complementaInformacoesEmail(final SolicitacaoServicoDTO solicitacaoServicoDto) throws Exception {
        final String urlSistema = ParametroUtil.getValor(ParametroSistema.URL_Sistema, this.getTransacao(), "");
        if (solicitacaoServicoDto != null) {
            final String idHashValidacao = CriptoUtils.generateHash("CODED" + solicitacaoServicoDto.getIdSolicitacaoServico(), "MD5");
            solicitacaoServicoDto.setHashPesquisaSatisfacao(idHashValidacao);
            solicitacaoServicoDto.setUrlSistema(urlSistema);
            solicitacaoServicoDto.setLinkPesquisaSatisfacao("<a href=\"" + urlSistema
                    + "/pages/pesquisaSatisfacao/pesquisaSatisfacao.load?idSolicitacaoServico=" + solicitacaoServicoDto.getIdSolicitacaoServico() + "&hash="
                    + idHashValidacao + "\">Clique aqui para fazer a avaliação do Atendimento</a>");
        }
    }

    @Override
    public void enviaEmail(final String identificador, final String[] destinatarios) throws Exception {
        if (identificador == null) {
            return;
        }

        if (destinatarios == null || destinatarios.length == 0) {
            return;
        }

        if (!this.isEmailHabilitado()) {
            return;
        }

        final ModeloEmailDTO modeloEmailDto = new ModeloEmailDao().findByIdentificador(identificador);
        if (modeloEmailDto == null) {
            return;
        }

        /*
         * String para = destinatarios[0]; String cc = null; if (destinatarios.length > 1) { cc = ""; for (int i = 1; i < destinatarios.length; i++) { cc +=
         * destinatarios[i] + ";"; } }
         */
        final String remetente = this.getRemetenteEmail();

        final SolicitacaoServicoDTO solicitacaoAuxDto = new SolicitacaoServicoServiceEjb().restoreAll(
                this.getSolicitacaoServicoDto().getIdSolicitacaoServico(), this.getTransacao());
        if (this.getSolicitacaoServicoDto().getNomeTarefa() != null && !this.getSolicitacaoServicoDto().getNomeTarefa().trim().equals("")) {
            solicitacaoAuxDto.setNomeTarefa(this.getSolicitacaoServicoDto().getNomeTarefa());
        } else if (this.getSolicitacaoServicoDto().getIdTarefa() != null) {
            final TarefaFluxoDTO tarefaDto = this.recuperaTarefa(this.getSolicitacaoServicoDto().getIdTarefa());
            if (tarefaDto != null && tarefaDto.getElementoFluxoDto() != null) {
                solicitacaoAuxDto.setNomeTarefa(tarefaDto.getElementoFluxoDto().getDocumentacao());
            }
        }

        this.complementaInformacoesEmail(solicitacaoAuxDto);
        /* Decodifica a mensagem a ser enviada */
        /*
         * comentei a linha abaixo porque estava dando problema no caracter \ e "
         */
        // solicitacaoAuxDto.setDescricao(StringEscapeUtils.unescapeEcmaScript(solicitacaoAuxDto.getDescricao()));
        solicitacaoAuxDto.setResposta(UtilStrings.unescapeJavaString(solicitacaoAuxDto.getResposta()));

        // System.out.println("#################### ENVIANDO EMAIL ####################");
        // System.out.println("### Modelo: " + identificador);
        // System.out.println("### No. solicitação: " + solicitacaoAuxDto.getIdSolicitacaoServico());
        // if (solicitacaoAuxDto.getNomeTarefa() != null)
        // System.out.println("### Tarefa: " + solicitacaoAuxDto.getNomeTarefa());

        final MensagemEmail mensagem = new MensagemEmail(modeloEmailDto.getIdModeloEmail(), new BaseEntity[] {solicitacaoAuxDto});
        try {
            for (final String para : destinatarios) {
                // System.out.println("### Destinatário: " + para);
                mensagem.envia(para, null, remetente);
                Thread.sleep(50);
            }
        } catch (final Exception e) {}
    }

    @Override
    public void enviaEmail(final Integer idModeloEmail) throws Exception {
        if (idModeloEmail == null) {
            return;
        }

        if (!this.isEmailHabilitado()) {
            return;
        }

        final String remetente = this.getRemetenteEmail();

        final SolicitacaoServicoDTO solicitacaoAuxDto = new SolicitacaoServicoServiceEjb().restoreAll(
                this.getSolicitacaoServicoDto().getIdSolicitacaoServico(), this.getTransacao());
        if (solicitacaoAuxDto != null) {
            if (this.getSolicitacaoServicoDto().getNomeTarefa() != null && !this.getSolicitacaoServicoDto().getNomeTarefa().trim().equals("")) {
                solicitacaoAuxDto.setNomeTarefa(this.getSolicitacaoServicoDto().getNomeTarefa());
            } else if (this.getSolicitacaoServicoDto().getIdTarefa() != null) {
                final TarefaFluxoDTO tarefaDto = this.recuperaTarefa(this.getSolicitacaoServicoDto().getIdTarefa());
                if (tarefaDto != null && tarefaDto.getElementoFluxoDto() != null) {
                    solicitacaoAuxDto.setNomeTarefa(tarefaDto.getElementoFluxoDto().getDocumentacao());
                }
            }
        }

        this.complementaInformacoesEmail(solicitacaoAuxDto);
        /* Decodifica a mensagem a ser enviada */
        if (solicitacaoAuxDto != null) {
            /*
             * comentei a linha abaixo porque estava dando problema no caracter \ e "
             */
            // solicitacaoAuxDto.setDescricao(StringEscapeUtils.unescapeEcmaScript(solicitacaoAuxDto.getDescricao()));
            solicitacaoAuxDto.setResposta(UtilStrings.unescapeJavaString(solicitacaoAuxDto.getResposta()));

        }

        SolicitacaoServicoDTO solicitacaoAuxiliarEmail = new SolicitacaoServicoDTO();
        if (solicitacaoAuxDto != null) {
            solicitacaoAuxiliarEmail = new SolicitacaoServicoServiceEjb().restoreInfoEmails(solicitacaoAuxDto.getIdSolicitacaoServico(), this.getTransacao());
        }

        if (solicitacaoAuxiliarEmail != null) {

            if (solicitacaoAuxiliarEmail.getDataRegistroOcorrencia() != null) {
                final SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
                solicitacaoAuxiliarEmail.setDataRegistroOcorrenciaStr(simple.format(solicitacaoAuxiliarEmail.getDataRegistroOcorrencia()));
            }

            solicitacaoAuxDto.setRegistradoPor(solicitacaoAuxiliarEmail.getRegistradoPor());
            solicitacaoAuxDto.setRegistroexecucao(solicitacaoAuxiliarEmail.getRegistroexecucao());
            solicitacaoAuxDto.setDataRegistroOcorrenciaStr(solicitacaoAuxiliarEmail.getDataRegistroOcorrenciaStr());
            solicitacaoAuxDto.setHoraRegistroOcorrencia(solicitacaoAuxiliarEmail.getHoraRegistroOcorrencia());
        }

        if (solicitacaoAuxiliarEmail != null && solicitacaoAuxiliarEmail.getRegistroexecucao() == null) {
            solicitacaoAuxDto.setRegistroexecucao("--");
            solicitacaoAuxDto.setRegistradoPor("--");
            solicitacaoAuxDto.setDataRegistroOcorrenciaStr("");
            solicitacaoAuxDto.setHoraRegistroOcorrencia("--");
        }

        if (solicitacaoAuxiliarEmail != null && solicitacaoAuxiliarEmail.getCategoriaOcorrencia() != null
                && !solicitacaoAuxiliarEmail.getCategoriaOcorrencia().equalsIgnoreCase("Execucao")) {
            solicitacaoAuxDto.setRegistroexecucao("--");
            solicitacaoAuxDto.setRegistradoPor("--");
            solicitacaoAuxDto.setDataRegistroOcorrenciaStr("");
            solicitacaoAuxDto.setHoraRegistroOcorrencia("--");
        }

        // Esse registro de execução na verdade deve ser as informações da ocorrência da solicitação, caso comece com "Execução da Tarefa" quer dizer que foi
        // gerado pelo sistema,
        // logo, não tem importância
        if (solicitacaoAuxiliarEmail != null && solicitacaoAuxiliarEmail.getRegistroexecucao() != null
                && solicitacaoAuxiliarEmail.getRegistroexecucao().startsWith("Execução da tarefa")) {
            solicitacaoAuxDto.setRegistroexecucao("--");
            solicitacaoAuxDto.setRegistradoPor("--");
            solicitacaoAuxDto.setDataRegistroOcorrenciaStr("");
            solicitacaoAuxDto.setHoraRegistroOcorrencia("--");
        }

        // Cálculo do sla para enviar para o e-mail
        if (solicitacaoAuxDto != null && solicitacaoAuxiliarEmail != null) {
            if (solicitacaoAuxDto.getPrazoHH() != null && solicitacaoAuxDto.getPrazoHH() != 0 || solicitacaoAuxDto.getPrazoMM() != null
                    && solicitacaoAuxDto.getPrazoMM() != 0) {
                if (solicitacaoAuxDto.getPrazoHH() == 0) {
                    solicitacaoAuxDto.setSla(solicitacaoAuxDto.getPrazoHH() + "0:" + solicitacaoAuxDto.getPrazoMM());
                } else if (solicitacaoAuxDto.getPrazoMM() == 0) {
                    solicitacaoAuxDto.setSla(solicitacaoAuxDto.getPrazoHH() + ":" + solicitacaoAuxDto.getPrazoMM() + "0");
                } else {
                    solicitacaoAuxDto.setSla(solicitacaoAuxDto.getPrazoHH() + ":" + solicitacaoAuxDto.getPrazoMM());
                }
            } else {
                solicitacaoAuxDto.setSla("Sla à combinar");
            }
        }

        final MensagemEmail mensagem = new MensagemEmail(idModeloEmail, new BaseEntity[] {solicitacaoAuxDto});
        try {
            if (solicitacaoAuxDto != null) {
                mensagem.envia(solicitacaoAuxDto.getEmailcontato(), null, remetente);
            }
        } catch (final Exception e) {}
    }

    /**
     * Notifica todos os Empregados de um grupo.
     *
     * @param idModeloEmail
     * @throws Exception
     */
    public void enviaEmailGrupo(final Integer idModeloEmail, final Integer idGrupoDestino) throws Exception {
        MensagemEmail mensagem = null;

        if (idGrupoDestino == null) {
            return;
        }

        if (idModeloEmail == null) {
            return;
        }

        final GrupoService grupoService = (GrupoService) ServiceLocator.getInstance().getService(GrupoService.class, null);

        List<String> emails = null;

        try {
            emails = (List<String>) grupoService.listarEmailsPorGrupo(idGrupoDestino);
        } catch (final Exception e) {
            return;
        }

        if (emails == null || emails.isEmpty()) {
            return;
        }

        final String remetente = ParametroUtil.getValor(ParametroSistema.RemetenteNotificacoesSolicitacao, this.getTransacao(), null);
        if (remetente == null) {
            throw new LogicException("Remetente para notificações de solicitação de serviço não foi parametrizado");
        }

        final SolicitacaoServicoDTO solicitacaoAuxDto = new SolicitacaoServicoServiceEjb().restoreAll(
                this.getSolicitacaoServicoDto().getIdSolicitacaoServico(), this.getTransacao());
        if (solicitacaoAuxDto == null) {
            return;
        }
        if (this.getSolicitacaoServicoDto().getNomeTarefa() != null && !this.getSolicitacaoServicoDto().getNomeTarefa().trim().equals("")) {
            solicitacaoAuxDto.setNomeTarefa(this.getSolicitacaoServicoDto().getNomeTarefa());
        } else if (this.getSolicitacaoServicoDto().getIdTarefa() != null) {
            final TarefaFluxoDTO tarefaDto = this.recuperaTarefa(this.getSolicitacaoServicoDto().getIdTarefa());
            if (tarefaDto != null && tarefaDto.getElementoFluxoDto() != null) {
                solicitacaoAuxDto.setNomeTarefa(tarefaDto.getElementoFluxoDto().getDocumentacao());
            }
        }

        /* Decodifica a mensagem a ser enviada */
        /*
         * comentei a linha abaixo porque estava dando problema no caracter \ e "
         */
        // solicitacaoAuxDto.setDescricao(StringEscapeUtils.unescapeEcmaScript(solicitacaoAuxDto.getDescricao()));
        solicitacaoAuxDto.setResposta(UtilStrings.unescapeJavaString(solicitacaoAuxDto.getResposta()));

        try {

            // EmpregadoDTO aux = null;
            for (final String email : emails) {
                final int posArroba = email.indexOf("@");
                if (posArroba > 0 && email.substring(posArroba).contains(".")) {
                    try {
                        mensagem = new MensagemEmail(idModeloEmail, new BaseEntity[] {solicitacaoAuxDto});

                        // aux = (EmpregadoDTO)
                        // getEmpregadoService().restore(e);
                        // if(aux != null && aux.getEmail() != null &&
                        // !aux.getEmail().trim().equalsIgnoreCase("") ){
                        mensagem.envia(email, null, remetente);
                        Thread.sleep(50);
                    } catch (final Exception e) {
                        // faz nada
                    }
                }
                // }
            }
        } catch (final Exception e) {}
    }

    private ServicoContratoDTO recuperaServicoContrato() throws Exception {
        final ServicoContratoDao servicoContratoDao = new ServicoContratoDao();
        this.setTransacaoDao(servicoContratoDao);
        ServicoContratoDTO servicoContratoDto = new ServicoContratoDTO();
        if (this.getSolicitacaoServicoDto().getIdServicoContrato() != null) {
            servicoContratoDto.setIdServicoContrato(this.getSolicitacaoServicoDto().getIdServicoContrato());
        } else {
            this.setTransacaoDao(this.getSolicitacaoServicoDAO());
            final SolicitacaoServicoDTO solicitacaoServicoDto = (SolicitacaoServicoDTO) objetoNegocioDto;
            SolicitacaoServicoDTO solicitacaoAuxDto = new SolicitacaoServicoDTO();
            solicitacaoAuxDto.setIdSolicitacaoServico(solicitacaoServicoDto.getIdSolicitacaoServico());
            solicitacaoAuxDto = (SolicitacaoServicoDTO) this.getSolicitacaoServicoDAO().restore(solicitacaoAuxDto);
            servicoContratoDto.setIdServicoContrato(solicitacaoAuxDto.getIdServicoContrato());
        }
        servicoContratoDto = (ServicoContratoDTO) servicoContratoDao.restore(servicoContratoDto);
        if (servicoContratoDto == null) {
            throw new LogicException("Serviço contrato não localizado");
        }
        return servicoContratoDto;
    }

    @Override
    public void encerra() throws Exception {
        final SolicitacaoServicoDTO solicitacaoServicoDto = this.getSolicitacaoServicoDto();
        if (solicitacaoServicoDto == null) {
            throw new Exception("Solicitação de serviço não encontrada");
        }

        if (solicitacaoServicoDto.encerrada()) {
            return;
        }

        this.validaEncerramento();

        // if (!solicitacaoServicoDto.atendida() &&
        // !solicitacaoServicoDto.reclassificada())
        // throw new
        // Exception("Solicitação de serviço não permite encerramento");

        final Collection<ExecucaoSolicitacaoDTO> colExecucao = new ExecucaoSolicitacaoDao().listByIdSolicitacao(this.getSolicitacaoServicoDto()
                .getIdSolicitacaoServico());
        if (colExecucao != null) {
            for (final ExecucaoSolicitacaoDTO execucaoSolicitacaoDto : colExecucao) {
                final InstanciaFluxo instanciaFluxo = new InstanciaFluxo(this, execucaoSolicitacaoDto.getIdInstanciaFluxo());
                instanciaFluxo.encerra();
            }
        }

        if (!solicitacaoServicoDto.getSituacao().equalsIgnoreCase(SituacaoSolicitacaoServico.Cancelada.name())) {
            solicitacaoServicoDto.setSituacao(SituacaoSolicitacaoServico.Fechada.name());
        }
        solicitacaoServicoDto.setDataHoraFim(UtilDatas.getDataHoraAtual());
        this.calculaTempoCaptura(solicitacaoServicoDto);
        this.calculaTempoAtendimento(solicitacaoServicoDto);
        this.calculaTempoAtraso(solicitacaoServicoDto);
        this.setTransacaoDao(this.getSolicitacaoServicoDAO());
        this.getSolicitacaoServicoDAO().updateNotNull(solicitacaoServicoDto);

        OcorrenciaSolicitacaoDTO ocorrenciaSolicitacaoDTO = new OcorrenciaSolicitacaoDTO();
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin("Automático");
        ocorrenciaSolicitacaoDTO = OcorrenciaSolicitacaoServiceEjb.create(this.getSolicitacaoServicoDto(), null, null, OrigemOcorrencia.OUTROS,
                CategoriaOcorrencia.Encerramento, null, CategoriaOcorrencia.Encerramento.getDescricao(), usuarioDTO, 0, null, this.getTransacao());

        this.popularHistorico(solicitacaoServicoDto, ocorrenciaSolicitacaoDTO, "Encerra", usuarioDTO);
        if (this.getSolicitacaoServicoDto().getEnviaEmailFinalizacao() != null
                && this.getSolicitacaoServicoDto().getEnviaEmailFinalizacao().equalsIgnoreCase("S")) {
            final ServicoContratoDTO servicoContratoDto = this.recuperaServicoContrato();

            final String enviarNotificacaoSolicitacoesVinculadas = ParametroUtil.getValor(
                    ParametroSistema.RECEBER_NOTIFICACAO_ENCERRAR_ESCALONAR_SOLICITACOES_VINCULADAS, this.getTransacao(), "S");

            if (enviarNotificacaoSolicitacoesVinculadas.equalsIgnoreCase("S")) {
                if (this.getSolicitacaoServicoDto().getIdSolicitacaoRelacionada() != null) {
                    SolicitacaoServicoDTO solicitacaoServicoRelacionada = new SolicitacaoServicoDTO();
                    solicitacaoServicoRelacionada.setIdSolicitacaoServico(this.getSolicitacaoServicoDto().getIdSolicitacaoRelacionada());
                    solicitacaoServicoRelacionada = (SolicitacaoServicoDTO) this.getSolicitacaoServicoDAO().restore(solicitacaoServicoRelacionada);

                    final String idModeloEmailEncerramento = ParametroUtil.getValor(
                            ParametroSistema.ID_MODELO_EMAIL_ENCERRAMENTO_GRUPO_EXECUTOR_SOLICITACAO_RELACIONADA, this.getTransacao(), "86");
                    if (idModeloEmailEncerramento != null) {
                        this.enviaEmailGrupo(Integer.parseInt(idModeloEmailEncerramento), solicitacaoServicoRelacionada.getIdGrupoAtual());
                    } else {
                        throw new Exception("Não há modelo de e-mail setado nos parâmetros.");
                    }

                }
            }

            this.enviaEmail(servicoContratoDto.getIdModeloEmailFinalizacao());
        }

        final Collection<SolicitacaoServicoDTO> colRelacionados = this.getSolicitacaoServicoDAO().findByIdSolicitacaoPai(
                solicitacaoServicoDto.getIdSolicitacaoServico());
        if (colRelacionados != null) {
            final SolicitacaoServicoServiceEjb solicitacaoService = new SolicitacaoServicoServiceEjb();
            for (final SolicitacaoServicoDTO solicitacaoRelacionadaDto : colRelacionados) {
                if (solicitacaoRelacionadaDto.getIdSolicitacaoServico().intValue() == solicitacaoServicoDto.getIdSolicitacaoServico().intValue()) {
                    continue;
                }
                final SolicitacaoServicoDTO solicitacaoAuxDto = solicitacaoService.restoreAll(solicitacaoRelacionadaDto.getIdSolicitacaoServico(),
                        this.getTransacao());
                solicitacaoAuxDto.setIdCausaIncidente(solicitacaoServicoDto.getIdCausaIncidente());
                solicitacaoAuxDto.setResposta(solicitacaoServicoDto.getResposta());
                final ExecucaoSolicitacao execucaoSolicitacao = new ExecucaoSolicitacao(solicitacaoAuxDto, this.getTransacao());
                execucaoSolicitacao.encerra();
            }
        }
    }

    @Override
    public void reabre(final String loginUsuario) throws Exception {
        final SolicitacaoServicoDTO solicitacaoServicoDto = this.getSolicitacaoServicoDto();
        if (solicitacaoServicoDto == null) {
            throw new Exception("Solicitação de Serviço não encontrada");
        }

        if (!solicitacaoServicoDto.encerrada()) {
            throw new Exception("Solicitação de Serviçoo não permite reabertura");
        }

        usuarioDTO = new UsuarioDTO();
        usuarioDTO = new UsuarioDao().restoreByLogin(loginUsuario);

        int seqReabertura = 1;
        final ReaberturaSolicitacaoDao reaberturaSolicitacaoDao = new ReaberturaSolicitacaoDao();
        this.setTransacaoDao(reaberturaSolicitacaoDao);
        final Collection colReabertura = reaberturaSolicitacaoDao.findByIdSolicitacaoServico(solicitacaoServicoDto.getIdSolicitacaoServico());
        if (colReabertura != null) {
            seqReabertura = colReabertura.size() + 1;
        }

        final ReaberturaSolicitacaoDTO reaberturaSolicitacaoDto = new ReaberturaSolicitacaoDTO();
        reaberturaSolicitacaoDto.setIdSolicitacaoServico(solicitacaoServicoDto.getIdSolicitacaoServico());
        reaberturaSolicitacaoDto.setSeqReabertura(seqReabertura);
        reaberturaSolicitacaoDto.setIdResponsavel(usuarioDTO.getIdUsuario());
        reaberturaSolicitacaoDto.setDataHora(UtilDatas.getDataHoraAtual());
        reaberturaSolicitacaoDao.create(reaberturaSolicitacaoDto);

        solicitacaoServicoDto.setSituacao(SituacaoSolicitacaoServico.Reaberta.name());
        solicitacaoServicoDto.setSeqReabertura(new Integer(seqReabertura));
        this.setTransacaoDao(this.getSolicitacaoServicoDAO());
        this.getSolicitacaoServicoDAO().update(solicitacaoServicoDto);

        OcorrenciaSolicitacaoServiceEjb.create(this.getSolicitacaoServicoDto(), null, null, OrigemOcorrencia.OUTROS, CategoriaOcorrencia.Reabertura, null,
                CategoriaOcorrencia.Reabertura.getDescricao(), usuarioDTO, 0, null, this.getTransacao());
        reabre = "S";

        this.inicia();
    }

    @Override
    public void suspende(final String loginUsuario) throws Exception {
        final SolicitacaoServicoDTO solicitacaoServicoDto = this.getSolicitacaoServicoDto();
        if (solicitacaoServicoDto == null) {
            throw new LogicException("solicitacaoservico.validacao.solicitacaoservico");
        }

        if (!solicitacaoServicoDto.emAtendimento()) {
            throw new LogicException("citcorpore.comum.validarSuspensaoSolicitacao");
        }

        OcorrenciaSolicitacaoDTO ocorrenciaSolicitacao = new OcorrenciaSolicitacaoDTO();

        solicitacaoServicoDto.setDataHoraSuspensao(UtilDatas.getDataHoraAtual());
        solicitacaoServicoDto.setDataHoraReativacao(null);
        this.suspendeSLA(solicitacaoServicoDto);

        final SolicitacaoServicoDao solicitacaoDao = new SolicitacaoServicoDao();
        this.setTransacaoDao(solicitacaoDao);
        solicitacaoServicoDto.setSituacao(SituacaoSolicitacaoServico.Suspensa.name());
        solicitacaoDao.update(solicitacaoServicoDto);

        final JustificativaSolicitacaoDTO justificativaDto = new JustificativaSolicitacaoDTO();
        justificativaDto.setIdJustificativa(solicitacaoServicoDto.getIdJustificativa());
        justificativaDto.setComplementoJustificativa(solicitacaoServicoDto.getComplementoJustificativa());

        usuarioDTO = new UsuarioDTO();
        usuarioDTO = new UsuarioDao().restoreByLogin(loginUsuario);

        ocorrenciaSolicitacao = OcorrenciaSolicitacaoServiceEjb.create(this.getSolicitacaoServicoDto(), null, null, OrigemOcorrencia.OUTROS,
                CategoriaOcorrencia.Suspensao, null, CategoriaOcorrencia.Suspensao.getDescricao(), usuarioDTO, 0, justificativaDto, this.getTransacao());

        this.popularHistorico(this.getSolicitacaoServicoDto(), ocorrenciaSolicitacao, "Suspende", usuarioDTO);
    }

    @Override
    public void reativa(final String loginUsuario) throws Exception {
        final SolicitacaoServicoDTO solicitacaoServicoDto = this.getSolicitacaoServicoDto();
        if (solicitacaoServicoDto.getSituacaoSLA().equals("M")) {
            solicitacaoServicoDto.setSituacaoSLA("A");
        }

        if (!solicitacaoServicoDto.suspensa()) {
            throw new Exception("Solicitação de Serviço não permite reativação");
        }

        this.setTransacaoDao(this.getSolicitacaoServicoDAO());

        final Timestamp tsAtual = UtilDatas.getDataHoraAtual();
        solicitacaoServicoDto.setSituacao(SituacaoSolicitacaoServico.EmAndamento.name());
        solicitacaoServicoDto.setDataHoraSuspensao(null);
        solicitacaoServicoDto.setDataHoraReativacao(tsAtual);
        this.reativaSLA(solicitacaoServicoDto);
        this.getSolicitacaoServicoDAO().update(solicitacaoServicoDto);
        OcorrenciaSolicitacaoDTO ocorrenciaSolicitacaoDTO = new OcorrenciaSolicitacaoDTO();
        usuarioDTO = new UsuarioDTO();
        usuarioDTO = new UsuarioDao().restoreByLogin(loginUsuario);

        ocorrenciaSolicitacaoDTO = OcorrenciaSolicitacaoServiceEjb.create(this.getSolicitacaoServicoDto(), null, null, OrigemOcorrencia.OUTROS,
                CategoriaOcorrencia.Reativacao, null, CategoriaOcorrencia.Reativacao.getDescricao(), usuarioDTO, 0, null, this.getTransacao());

        this.popularHistorico(solicitacaoServicoDto, ocorrenciaSolicitacaoDTO, "Reativa", usuarioDTO);
    }

    private Integer getIdCalendario(final SolicitacaoServicoDTO solicitacaoServicoDto) throws Exception {
        Integer idCalendario = solicitacaoServicoDto.getIdCalendario();
        if (solicitacaoServicoDto.getIdCalendario() == null) {
            final ServicoContratoDTO servicoContratoDto = new ServicoContratoDao().findByIdContratoAndIdServico(solicitacaoServicoDto.getIdContrato(),
                    solicitacaoServicoDto.getIdServico());
            if (servicoContratoDto == null) {
                throw new LogicException("Serviço não localizado para o contrato");
            }
            idCalendario = servicoContratoDto.getIdCalendario();
        }
        return idCalendario;
    }

    public void calculaTempoCaptura(final SolicitacaoServicoDTO solicitacaoServicoDto) throws Exception {
        solicitacaoServicoDto.setTempoCapturaHH(0);
        solicitacaoServicoDto.setTempoCapturaMM(0);

        if (solicitacaoServicoDto.getDataHoraCaptura() == null) {
            return;
        }

        if (solicitacaoServicoDto.getDataHoraInicioSLA() == null) {
            return;
        }

        if (solicitacaoServicoDto.getDataHoraInicioSLA().compareTo(solicitacaoServicoDto.getDataHoraCaptura()) > 0) {
            return;
        }

        final Integer idCalendario = this.getIdCalendario(solicitacaoServicoDto);

        CalculoJornadaDTO calculoDto = new CalculoJornadaDTO(idCalendario, solicitacaoServicoDto.getDataHoraInicioSLA());
        calculoDto = new CalendarioServiceEjb().calculaPrazoDecorrido(calculoDto, solicitacaoServicoDto.getDataHoraCaptura(), this.getTransacao());

        solicitacaoServicoDto.setTempoCapturaHH(calculoDto.getTempoDecorridoHH().intValue());
        solicitacaoServicoDto.setTempoCapturaMM(calculoDto.getTempoDecorridoMM().intValue());
    }

    public void calculaTempoCapturaNovo(final SolicitacaoServicoDTO solicitacaoServicoDto) throws Exception {
        solicitacaoServicoDto.setTempoCapturaHH(0);
        solicitacaoServicoDto.setTempoCapturaMM(0);

        if (solicitacaoServicoDto.getDataHoraCaptura() == null) {
            return;
        }

        if (solicitacaoServicoDto.getDataHoraInicioSLA() == null) {
            return;
        }

        if (solicitacaoServicoDto.getDataHoraInicioSLA().compareTo(solicitacaoServicoDto.getDataHoraCaptura()) > 0) {
            return;
        }

        final Integer idCalendario = this.getIdCalendario(solicitacaoServicoDto);

        CalculoJornadaDTO calculoDto = new CalculoJornadaDTO(idCalendario, solicitacaoServicoDto.getDataHoraInicioSLA());
        calculoDto = new CalendarioServiceEjb().calculaPrazoDecorridoNovo(calculoDto, solicitacaoServicoDto.getDataHoraCaptura(), this.getTransacao());

        solicitacaoServicoDto.setTempoCapturaHH(calculoDto.getTempoDecorridoHH().intValue());
        solicitacaoServicoDto.setTempoCapturaMM(calculoDto.getTempoDecorridoMM().intValue());
        solicitacaoServicoDto.setTempoCapturaSS(calculoDto.getTempoDecorridoSS());
    }

    public void calculaTempoAtendimento(final SolicitacaoServicoDTO solicitacaoServicoDto) throws Exception {
        if (solicitacaoServicoDto.getDataHoraInicioSLA() == null) {
            return;
        }

        final Integer idCalendario = this.getIdCalendario(solicitacaoServicoDto);

        Timestamp tsAtual = UtilDatas.getDataHoraAtual();
        if (solicitacaoServicoDto.getSituacao().equalsIgnoreCase(SituacaoSolicitacaoServico.Fechada.name())) {
            tsAtual = solicitacaoServicoDto.getDataHoraFim();
        }

        Timestamp tsInicial = solicitacaoServicoDto.getDataHoraInicioSLA();
        if (solicitacaoServicoDto.getDataHoraReativacao() != null) {
            tsInicial = solicitacaoServicoDto.getDataHoraReativacao();
        }

        CalculoJornadaDTO calculoDto = new CalculoJornadaDTO(idCalendario, tsInicial);
        calculoDto = new CalendarioServiceEjb().calculaPrazoDecorrido(calculoDto, tsAtual, this.getTransacao());

        if (solicitacaoServicoDto.getTempoDecorridoHH() == null) {
            solicitacaoServicoDto.setTempoDecorridoHH(0);
        }
        if (solicitacaoServicoDto.getTempoDecorridoMM() == null) {
            solicitacaoServicoDto.setTempoDecorridoMM(0);
        }

        // int horasAux = (solicitacaoServicoDto.getTempoDecorridoMM().intValue() + calculoDto.getTempoDecorridoMM().intValue()) / 60;
        // int minAux = (solicitacaoServicoDto.getTempoDecorridoMM().intValue() + calculoDto.getTempoDecorridoMM().intValue()) % 60;

        // Foi verificado que o tempo de atendimento é o tempo decorrido, ele estava calculando duas vezes.
        solicitacaoServicoDto.setTempoAtendimentoHH(new Integer(calculoDto.getTempoDecorridoHH().intValue()));
        solicitacaoServicoDto.setTempoAtendimentoMM(new Integer(calculoDto.getTempoDecorridoMM()));
    }

    public void calculaTempoAtraso(final SolicitacaoServicoDTO solicitacaoServicoDto) throws Exception {
        solicitacaoServicoDto.setTempoAtrasoHH(0);
        solicitacaoServicoDto.setTempoAtrasoMM(0);
        if (solicitacaoServicoDto.getDataHoraLimite() != null) {
            final Timestamp dataHoraLimite = solicitacaoServicoDto.getDataHoraLimite();
            Timestamp dataHoraComparacao = UtilDatas.getDataHoraAtual();
            if (solicitacaoServicoDto.encerrada()) {
                dataHoraComparacao = solicitacaoServicoDto.getDataHoraFim();
            }
            if (dataHoraComparacao.compareTo(dataHoraLimite) > 0) {
                final long atrasoSLA = UtilDatas.calculaDiferencaTempoEmMilisegundos(dataHoraComparacao, dataHoraLimite) / 1000;

                final String hora = Util.getHoraStr(new Double(atrasoSLA) / 3600);
                final int tam = hora.length();
                solicitacaoServicoDto.setTempoAtrasoHH(new Integer(hora.substring(0, tam - 2)));
                solicitacaoServicoDto.setTempoAtrasoMM(new Integer(hora.substring(tam - 2, tam)));
            }
        }
    }

    public EmpregadoDTO recuperaSolicitante(final SolicitacaoServicoDTO solicitacaoServicoDto) throws Exception {
        if (solicitacaoServicoDto == null || solicitacaoServicoDto.getIdSolicitante() == null) {
            return null;
        }

        return new EmpregadoDao().restoreByIdEmpregado(solicitacaoServicoDto.getIdSolicitante());
    }

    public StringBuilder recuperaLoginResponsaveis() throws Exception {
        final StringBuilder result = new StringBuilder();
        final SolicitacaoServicoDTO solicitacaoDto = this.getSolicitacaoServicoDto();
        final UsuarioDao usuarioDao = new UsuarioDao();
        UsuarioDTO usuarioDto = usuarioDao.restoreAtivoByIdEmpregado(solicitacaoDto.getIdSolicitante());
        if (usuarioDto != null) {
            result.append(usuarioDto.getLogin());
        }
        usuarioDto = usuarioDao.restoreAtivoByIdEmpregado(solicitacaoDto.getIdResponsavel());
        if (usuarioDto != null) {
            if (result.length() > 0) {
                result.append(";");
            }
            result.append(usuarioDto.getLogin());
        }
        return result;
    }

    @Override
    public void executaEvento(final EventoFluxoDTO eventoFluxoDto) throws Exception {
        final TransactionControler tc = new TransactionControlerImpl(this.getSolicitacaoServicoDAO().getAliasDB());

        try {
            tc.start();
            this.setTransacao(tc);
            this.setTransacaoDao(this.getSolicitacaoServicoDAO());

            final SolicitacaoServicoDTO solicitacaoServicoDto = this.getSolicitacaoServicoDAO().restoreByIdInstanciaFluxo(eventoFluxoDto.getIdInstancia());
            if (solicitacaoServicoDto == null) {
                throw new LogicException("Solicitação de serviço do evento " + eventoFluxoDto.getIdItemTrabalho() + " não encontrada");
            }

            this.setObjetoNegocioDto(solicitacaoServicoDto);
            final InstanciaFluxo instanciaFluxo = new InstanciaFluxo(this, eventoFluxoDto.getIdInstancia());

            final HashMap<String, Object> map = instanciaFluxo.getMapObj();
            instanciaFluxo.getObjetos(map);
            this.mapObjetoNegocio(map);
            instanciaFluxo.executaEvento(eventoFluxoDto.getIdItemTrabalho(), map);

            tc.commit();

        } catch (final Exception e) {
            e.printStackTrace();
            this.rollbackTransaction(tc, e);
            throw new ServiceException(e);
        } finally {
            tc.closeQuietly();
        }
    }

    public void cancelaTarefa(final String loginUsuario, final SolicitacaoServicoDTO solicitacaoServicoDto, final TarefaFluxoDTO tarefaFluxoDto,
            final String motivo) throws Exception {
        final InstanciaFluxo instanciaFluxo = new InstanciaFluxo(this, tarefaFluxoDto.getIdInstancia());
        instanciaFluxo.cancelaItemTrabalho(loginUsuario, tarefaFluxoDto.getIdItemTrabalho());

        String ocorrencia = "Cancelamento da tarefa \"" + tarefaFluxoDto.getElementoFluxoDto().getDocumentacao() + "\"";
        if (motivo != null && motivo.trim().length() > 0) {
            ocorrencia += ". Motivo: " + motivo;
        }

        Long tempo = new Long(0);
        if (tarefaFluxoDto.getDataHoraFinalizacao() != null) {
            tempo = (tarefaFluxoDto.getDataHoraFinalizacao().getTime() - tarefaFluxoDto.getDataHoraCriacao().getTime()) / 1000 / 60;
        }

        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin("Sistema");
        OcorrenciaSolicitacaoServiceEjb.create(this.getSolicitacaoServicoDto(), tarefaFluxoDto, ocorrencia, OrigemOcorrencia.OUTROS,
                CategoriaOcorrencia.CancelamentoTarefa, "não se aplica", CategoriaOcorrencia.CancelamentoTarefa.getDescricao(), usuarioDTO, tempo.intValue(),
                null, this.getTransacao());
    }

    @Override
    public void validaEncerramento() throws Exception {}

    public String recuperaGrupoAprovador() throws Exception {
        final SolicitacaoServicoDTO solicitacaoServicoDto = this.getSolicitacaoServicoDto();
        if (solicitacaoServicoDto == null) {
            throw new Exception("Solicitação de Serviço não encontrada.");
        }

        final ServicoContratoDTO servicoContratoDto = this.recuperaServicoContrato();
        if (servicoContratoDto.getIdGrupoAprovador() == null) {
            throw new Exception("Grupo aprovador não parametrizado. Verifique o serviço do contrato.");
        }

        final GrupoDao grupoDao = new GrupoDao();
        this.setTransacaoDao(grupoDao);
        GrupoDTO grupoDto = new GrupoDTO();
        grupoDto.setIdGrupo(servicoContratoDto.getIdGrupoAprovador());
        grupoDto = (GrupoDTO) grupoDao.restore(grupoDto);
        if (grupoDto == null) {
            throw new Exception("Grupo aprovador não encontrado.");
        }

        return grupoDto.getSigla();
    }

    @Override
    public void verificaSLA(final ItemTrabalho itemTrabalho) throws Exception {
        final SolicitacaoServicoDTO solicitacaoServicoDto = this.getSolicitacaoServicoDto();
        if (solicitacaoServicoDto == null) {
            throw new Exception("Solicitação de Serviço não encontrada.");
        }

        boolean bContabilizaSLA = true;
        if (itemTrabalho.getContabilizaSLA() != null) {
            bContabilizaSLA = itemTrabalho.getContabilizaSLA().equalsIgnoreCase("S");
        }

        boolean bGravar = false;
        if (bContabilizaSLA) {
            if (solicitacaoServicoDto.getSituacaoSLA().equalsIgnoreCase(SituacaoSLA.N.name())) {
                this.iniciaSLA(solicitacaoServicoDto);
                bGravar = true;
            }
            if (solicitacaoServicoDto.getSituacaoSLA().equalsIgnoreCase(SituacaoSLA.S.name())) {
                this.reativaSLA(solicitacaoServicoDto);
                bGravar = true;
            }
        } else if (solicitacaoServicoDto.getSituacaoSLA().equalsIgnoreCase(SituacaoSLA.A.name())) {
            this.suspendeSLA(solicitacaoServicoDto);
            bGravar = true;
        }
        if (bGravar) {
            this.setTransacaoDao(this.getSolicitacaoServicoDAO());
            this.getSolicitacaoServicoDAO().updateNotNull(solicitacaoServicoDto);
        }
    }

    public void determinaPrazoLimiteSolicitacaoACombinarReclassificada(final SolicitacaoServicoDTO solicitacaoDto, Integer idCalendario) throws Exception {
        final Timestamp tsAtual = UtilDatas.getDataHoraAtual();

        if (idCalendario == null || idCalendario.intValue() == 0) {
            final ServicoContratoDao servicoContratoDao = new ServicoContratoDao();
            this.setTransacaoDao(servicoContratoDao);
            final ServicoContratoDTO servicoContratoDto = servicoContratoDao.findByIdContratoAndIdServico(solicitacaoDto.getIdContrato(),
                    solicitacaoDto.getIdServico());
            if (servicoContratoDto == null) {
                throw new LogicException(this.i18n_Message(solicitacaoDto.getUsuarioDto(), "solicitacaoservico.validacao.servicolocalizado"));
            }
            idCalendario = servicoContratoDto.getIdCalendario();
        }

        if (solicitacaoDto.getPrazoHH() == null) {
            solicitacaoDto.setPrazoHH(0);
        }
        if (solicitacaoDto.getPrazoMM() == null) {
            solicitacaoDto.setPrazoMM(0);
        }

        CalculoJornadaDTO calculoDto = null;

        if (solicitacaoDto.getHouveMudanca() != null
                && solicitacaoDto.getHouveMudanca().equalsIgnoreCase("S")
                && solicitacaoDto.getDataHoraReativacaoSLA() != null
                && solicitacaoDto.getTempoDecorridoHH() != null
                && solicitacaoDto.getPrazoHH() * 100 + solicitacaoDto.getPrazoMM() > solicitacaoDto.getTempoDecorridoHH() * 100
                        + solicitacaoDto.getTempoDecorridoMM()) {
            final Integer novoPrazoHH = solicitacaoDto.getPrazoHH() - solicitacaoDto.getTempoDecorridoHH();
            final Integer novoPrazoMM = solicitacaoDto.getPrazoMM() - solicitacaoDto.getTempoDecorridoMM();

            calculoDto = new CalculoJornadaDTO(idCalendario, solicitacaoDto.getDataHoraReativacaoSLA(), novoPrazoHH, novoPrazoMM);
            calculoDto = new CalendarioServiceEjb().calculaDataHoraFinal(calculoDto, true, this.getTransacao());
        } else {
            if (solicitacaoDto.getDataHoraReativacaoSLA() != null && solicitacaoDto.getTempoDecorridoHH() == 0 && solicitacaoDto.getTempoDecorridoMM() == 0) {
                calculoDto = new CalculoJornadaDTO(idCalendario, solicitacaoDto.getDataHoraReativacaoSLA(), solicitacaoDto.getPrazoHH(),
                        solicitacaoDto.getPrazoMM());
            } else {
                solicitacaoDto.setDataHoraInicioSLA(tsAtual);
                calculoDto = new CalculoJornadaDTO(idCalendario, solicitacaoDto.getDataHoraInicioSLA(), solicitacaoDto.getPrazoHH(),
                        solicitacaoDto.getPrazoMM());
            }
            calculoDto = new CalendarioServiceEjb().calculaDataHoraFinal(calculoDto, true, this.getTransacao());
        }

        solicitacaoDto.setDataHoraLimite(calculoDto.getDataHoraFinal());
    }

    public void iniciaSLA(final SolicitacaoServicoDTO solicitacaoDto) throws Exception {
        if (!solicitacaoDto.getSituacaoSLA().equalsIgnoreCase(SituacaoSLA.N.name())) {
            return;
        }

        solicitacaoDto.setDataHoraInicioSLA(UtilDatas.getDataHoraAtual());
        solicitacaoDto.setSituacaoSLA(SituacaoSLA.A.name());
        new SolicitacaoServicoServiceEjb().determinaPrazoLimite(solicitacaoDto, null, this.getTransacao());

        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin("Automático");

        OcorrenciaSolicitacaoServiceEjb.create(this.getSolicitacaoServicoDto(), null, null, OrigemOcorrencia.OUTROS, CategoriaOcorrencia.InicioSLA, null,
                CategoriaOcorrencia.InicioSLA.getDescricao(), usuarioDTO, 0, null, this.getTransacao());
    }

    public void suspendeSLA(final SolicitacaoServicoDTO solicitacaoDto) throws Exception {
        if (!solicitacaoDto.getSituacaoSLA().equalsIgnoreCase(SituacaoSLA.A.name())) {
            return;
        }

        final Timestamp tsAtual = UtilDatas.getDataHoraAtual();
        Timestamp tsInicial = solicitacaoDto.getDataHoraInicioSLA();
        if (solicitacaoDto.getDataHoraReativacaoSLA() != null) {
            tsInicial = solicitacaoDto.getDataHoraReativacaoSLA();
        }
        CalculoJornadaDTO calculoDto = new CalculoJornadaDTO(solicitacaoDto.getIdCalendario(), tsInicial);
        calculoDto = new CalendarioServiceEjb().calculaPrazoDecorrido(calculoDto, tsAtual, this.getTransacao());

        if (solicitacaoDto.getTempoDecorridoHH() == null) {
            solicitacaoDto.setTempoDecorridoHH(0);
        }
        if (solicitacaoDto.getTempoDecorridoMM() == null) {
            solicitacaoDto.setTempoDecorridoMM(0);
        }

        final int horasAux = (solicitacaoDto.getTempoDecorridoMM().intValue() + calculoDto.getTempoDecorridoMM().intValue()) / 60;
        final int minAux = (solicitacaoDto.getTempoDecorridoMM().intValue() + calculoDto.getTempoDecorridoMM().intValue()) % 60;

        solicitacaoDto.setSituacaoSLA(SituacaoSLA.S.name());
        solicitacaoDto
                .setTempoDecorridoHH(new Integer(solicitacaoDto.getTempoDecorridoHH().intValue() + calculoDto.getTempoDecorridoHH().intValue() + horasAux));
        solicitacaoDto.setTempoDecorridoMM(new Integer(minAux));
        solicitacaoDto.setDataHoraSuspensaoSLA(tsAtual);
        solicitacaoDto.setDataHoraReativacaoSLA(null);

        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin("Automático");

        OcorrenciaSolicitacaoServiceEjb.create(this.getSolicitacaoServicoDto(), null, null, OrigemOcorrencia.OUTROS, CategoriaOcorrencia.SuspensaoSLA, null,
                CategoriaOcorrencia.SuspensaoSLA.getDescricao(), usuarioDTO, 0, null, this.getTransacao());
    }

    public void reativaSLA(final SolicitacaoServicoDTO solicitacaoDto) throws Exception {
        if (!solicitacaoDto.getSituacaoSLA().equalsIgnoreCase(SituacaoSLA.S.name())) {
            return;
        }

        final Timestamp tsAtual = UtilDatas.getDataHoraAtual();
        double prazo = solicitacaoDto.getPrazoHH() + new Double(solicitacaoDto.getPrazoMM()).doubleValue() / 60;
        final double tempo = solicitacaoDto.getTempoDecorridoHH() + new Double(solicitacaoDto.getTempoDecorridoMM()).doubleValue() / 60;
        prazo = prazo - tempo;
        if (prazo > 0) {
            CalculoJornadaDTO calculoDto = new CalculoJornadaDTO(solicitacaoDto.getIdCalendario(), tsAtual, Util.getHora(prazo), Util.getMinuto(prazo));
            calculoDto = new CalendarioServiceEjb().calculaDataHoraFinal(calculoDto, false, this.getTransacao());
            solicitacaoDto.setDataHoraLimite(calculoDto.getDataHoraFinal());
        }

        solicitacaoDto.setSituacaoSLA(SituacaoSLA.A.name());
        solicitacaoDto.setDataHoraSuspensaoSLA(null);
        solicitacaoDto.setDataHoraReativacaoSLA(tsAtual);
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin("Automático");
        OcorrenciaSolicitacaoServiceEjb.create(this.getSolicitacaoServicoDto(), null, null, OrigemOcorrencia.OUTROS, CategoriaOcorrencia.ReativacaoSLA, null,
                CategoriaOcorrencia.ReativacaoSLA.getDescricao(), usuarioDTO, 0, null, this.getTransacao());
    }

    private void popularHistorico(final SolicitacaoServicoDTO solicitacaoServicoDto, final OcorrenciaSolicitacaoDTO ocorrenciaSolicitacaoDTO,
            final String status, final UsuarioDTO usuarioDTO) throws Exception {
        final HistoricoSolicitacaoServicoDTO historicoSolicitacaoServicoDTO = new HistoricoSolicitacaoServicoDTO();
        final HistoricoSolicitacaoServicoService historicoSolicitacaoServicoService = (HistoricoSolicitacaoServicoService) ServiceLocator.getInstance()
                .getService(HistoricoSolicitacaoServicoService.class, null);
        Collection<HistoricoSolicitacaoServicoDTO> listaHistorico = new ArrayList<HistoricoSolicitacaoServicoDTO>();
        Collection<HistoricoSolicitacaoServicoDTO> responsavelAtual = new ArrayList<HistoricoSolicitacaoServicoDTO>();

        historicoSolicitacaoServicoDTO.setIdSolicitacaoServico(ocorrenciaSolicitacaoDTO.getIdSolicitacaoServico());
        historicoSolicitacaoServicoDTO.setIdOcorrencia(ocorrenciaSolicitacaoDTO.getIdOcorrencia());
        historicoSolicitacaoServicoDTO.setDataCriacao(UtilDatas.getDataHoraAtual());

        if (solicitacaoServicoDto.getIdGrupoAtual() != null) {
            historicoSolicitacaoServicoDTO.setIdGrupo(solicitacaoServicoDto.getIdGrupoAtual());
        }

        historicoSolicitacaoServicoDTO.setStatus(status);
        responsavelAtual = historicoSolicitacaoServicoService.findResponsavelAtual(historicoSolicitacaoServicoDTO.getIdSolicitacaoServico());
        if (responsavelAtual != null && !responsavelAtual.isEmpty()) {
            for (final HistoricoSolicitacaoServicoDTO historicoSolicitacaoServicoResponsavel : responsavelAtual) {
                historicoSolicitacaoServicoDTO.setIdResponsavelAtual(historicoSolicitacaoServicoResponsavel.getIdResponsavelAtual());
            }
        } else {
            historicoSolicitacaoServicoDTO.setIdResponsavelAtual(usuarioDTO.getIdUsuario());
        }
        historicoSolicitacaoServicoDTO.setIdServicoContrato(solicitacaoServicoDto.getIdServico());
        historicoSolicitacaoServicoDTO.setIdCalendario(solicitacaoServicoDto.getIdCalendario());
        try {
            if (historicoSolicitacaoServicoService.findHistoricoSolicitacao(ocorrenciaSolicitacaoDTO.getIdSolicitacaoServico())
                    && !status.equalsIgnoreCase("Suspende") && !status.equalsIgnoreCase("Encerra")) {
                historicoSolicitacaoServicoDTO.setHorasTrabalhadas(0.0);
                if (status.equalsIgnoreCase("Executa")) {
                    historicoSolicitacaoServicoDTO.setIdResponsavelAtual(usuarioDTO.getIdUsuario());
                }
                historicoSolicitacaoServicoDTO.setDataFinal(null);
                historicoSolicitacaoServicoService.create(historicoSolicitacaoServicoDTO);
            } else {
                listaHistorico = historicoSolicitacaoServicoService.restoreHistoricoServico(ocorrenciaSolicitacaoDTO.getIdSolicitacaoServico());
                if (listaHistorico != null && !listaHistorico.isEmpty()) {
                    for (final HistoricoSolicitacaoServicoDTO historicoSolicitacaoServicoDTO2 : listaHistorico) {
                        historicoSolicitacaoServicoDTO2.setDataFinal(UtilDatas.getDataHoraAtual());
                        historicoSolicitacaoServicoDTO2.setHorasTrabalhadas(this.calcularHoraTrabalhada(historicoSolicitacaoServicoDTO2));
                        historicoSolicitacaoServicoService.update(historicoSolicitacaoServicoDTO2);
                    }
                }
                if (status.equalsIgnoreCase("Executa")) {
                    historicoSolicitacaoServicoDTO.setHorasTrabalhadas(0.0);
                    if (status.equalsIgnoreCase("Executa")) {
                        historicoSolicitacaoServicoDTO.setIdResponsavelAtual(usuarioDTO.getIdUsuario());
                    }
                    historicoSolicitacaoServicoDTO.setDataFinal(null);
                    historicoSolicitacaoServicoService.create(historicoSolicitacaoServicoDTO);
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private double calcularHoraTrabalhada(final HistoricoSolicitacaoServicoDTO historicoSolicitacaoServicoDTO) throws Exception {
        final Timestamp tsAtual = UtilDatas.getDataHoraAtual();
        final Timestamp tsInicial = historicoSolicitacaoServicoDTO.getDataCriacao();
        CalculoJornadaDTO calculoDto = new CalculoJornadaDTO(historicoSolicitacaoServicoDTO.getIdCalendario(), tsInicial);
        calculoDto = new CalendarioServiceEjb().calculaPrazoDecorrido(calculoDto, tsAtual, this.getTransacao());
        final int horasAux = calculoDto.getTempoDecorridoHH().intValue() * 60;
        final int minAux = calculoDto.getTempoDecorridoMM().intValue() % 60;
        return horasAux + minAux;
    }

    /**
     * Retorna a Lista de TarefaDTO com SolicitacaoServidoDTO de acordo com o Login e a Lista de Contratos do Usuário Logado.
     *
     * @param pgAtual
     * @param qtdAPaginacao
     * @param login
     * @param gerenciamentoBean
     * @param listContratoUsuarioLogado
     * @return
     * @throws Exception
     * @author valdoilo.damasceno
     * @since 05.11.2013
     */
    public List<TarefaFluxoDTO> recuperaTarefas(final Integer qtdAtual, final Integer qtdAPaginacao, final String login,
            final GerenciamentoServicosDTO gerenciamentoBean, final Collection<ContratoDTO> listContratoUsuarioLogado, List<TarefaFluxoDTO> listTarefas)
            throws Exception {
        final TransactionControler tc = new TransactionControlerImpl(this.getJdbcAliasBPM());
        try {
            this.setTransacao(tc);
            final List<TarefaFluxoDTO> result = new ArrayList<>();

            if (listTarefas == null || listTarefas.isEmpty()) {
                // RECUPERA TAREFAS SEM SOLICITACAOSERVICODTO
                listTarefas = super.recuperaTarefas(login);
            }

            if (listTarefas != null) {
                final SolicitacaoServicoServiceEjb solicitacaoServicoServiceEjb = new SolicitacaoServicoServiceEjb();

                final Collection<SolicitacaoServicoDTO> listSolicitacaoServico = solicitacaoServicoServiceEjb.listByTarefas(listTarefas, qtdAtual,
                        qtdAPaginacao, gerenciamentoBean, listContratoUsuarioLogado, tc);

                final Collection<SolicitacaoServicoDTO> listSolicitacoesFilhas = solicitacaoServicoServiceEjb.listSolicitacoesFilhasFiltradas(
                        gerenciamentoBean, listContratoUsuarioLogado, tc);

                if (listSolicitacaoServico != null && !listSolicitacaoServico.isEmpty()) {

                    int cont = 0;

                    for (final SolicitacaoServicoDTO solicitacaoServicoDto : listSolicitacaoServico) {

                        for (final TarefaFluxoDTO tarefaDto : listTarefas) {

                            if (cont == qtdAPaginacao) {
                                break;
                            }

                            if (solicitacaoServicoDto.getIdTarefa().equals(tarefaDto.getIdItemTrabalho())) {

                                boolean possuiFilho = false;

                                if (listSolicitacoesFilhas != null && !listSolicitacoesFilhas.isEmpty()) {
                                    for (final SolicitacaoServicoDTO solicitacaoServicoDTO2 : listSolicitacoesFilhas) {
                                        if (solicitacaoServicoDto.getIdSolicitacaoServico().equals(solicitacaoServicoDTO2.getIdSolicitacaoPai())) {
                                            possuiFilho = true;
                                            break;
                                        }
                                    }
                                }

                                solicitacaoServicoDto.setPossuiFilho(possuiFilho);

                                tarefaDto.setSolicitacaoDto(solicitacaoServicoDto);
                                tarefaDto.setDataHoraLimite(solicitacaoServicoDto.getDataHoraLimite());
                                result.add(tarefaDto);

                                cont++;
                            }
                        }
                    }
                }
            }
            return result;
        } finally {
            tc.closeQuietly();
        }
    }

    /**
     * Utilizado para a RENDERIZAÇÃO do GRÁFICO, pois no Gráfico não é necessário a utilização de Paginação. Esta consulta considera o Login do Usuário Logado,
     * os Contratos em que está inserido e os
     * Filtros Selecionados na tela de Gerenciamento de Serviços.
     *
     * @param loginUsuario
     * @param gerenciamentoBean
     * @return List<TarefaFluxoDTO - Com SolicitacaoServicoDTO recuperados.
     * @throws Exception
     * @author valdoilo.damasceno
     * @since 05.11.2013
     */
    public List<TarefaFluxoDTO> recuperaTarefas(final String loginUsuario, final GerenciamentoServicosDTO gerenciamentoBean,
            final Collection<ContratoDTO> listContratoUsuarioLogado, List<TarefaFluxoDTO> listTarefas) throws Exception {
        final TransactionControler tc = new TransactionControlerImpl(this.getJdbcAliasBPM());
        try {
            this.setTransacao(tc);
            final List<TarefaFluxoDTO> result = new ArrayList<>();

            if (listTarefas == null || listTarefas.isEmpty()) {
                // RECUPERA TAREFAS SEM SOLICITACAOSERVICODTO
                listTarefas = super.recuperaTarefas(loginUsuario);
            }

            if (listTarefas != null) {
                final SolicitacaoServicoServiceEjb solicitacaoServicoServiceEjb = new SolicitacaoServicoServiceEjb();

                final Collection<SolicitacaoServicoDTO> listSolicitacaoServicoDto = solicitacaoServicoServiceEjb.listByTarefas(listTarefas, gerenciamentoBean,
                        listContratoUsuarioLogado, tc);

                final Collection<SolicitacaoServicoDTO> listSolicitacoesFilhas = solicitacaoServicoServiceEjb.listSolicitacoesFilhas(tc);

                if (listSolicitacaoServicoDto != null && !listSolicitacaoServicoDto.isEmpty()) {

                    for (final SolicitacaoServicoDTO solicitacaoServicoDto : listSolicitacaoServicoDto) {

                        for (final TarefaFluxoDTO tarefaDto : listTarefas) {

                            if (solicitacaoServicoDto.getIdTarefa().equals(tarefaDto.getIdItemTrabalho())) {

                                boolean possuiFilho = false;

                                if (listSolicitacoesFilhas != null && !listSolicitacoesFilhas.isEmpty()) {

                                    for (final SolicitacaoServicoDTO solicitacaoServicoDTO2 : listSolicitacoesFilhas) {

                                        if (solicitacaoServicoDto.getIdSolicitacaoServico().equals(solicitacaoServicoDTO2.getIdSolicitacaoPai())) {
                                            possuiFilho = true;
                                            break;
                                        }
                                    }
                                }

                                solicitacaoServicoDto.setPossuiFilho(possuiFilho);

                                tarefaDto.setSolicitacaoDto(solicitacaoServicoDto);
                                tarefaDto.setDataHoraLimite(solicitacaoServicoDto.getDataHoraLimite());
                                result.add(tarefaDto);
                            }
                        }
                    }
                }

                if (result != null) {
                    Collections.sort(result, new ObjectSimpleComparator("getDataHoraLimite", ObjectSimpleComparator.ASC));
                }
            }
            return result;
        } finally {
            tc.closeQuietly();
        }
    }

    public boolean permiteAprovacaoAlcada(final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto, final SolicitacaoServicoDTO solicitacaoServicoDto)
            throws Exception {
        return true;
    }

    public void calculaValorAprovadoAnual(final CentroResultadoDTO centroResultadoDto, final int anoRef, final TransactionControler tc) throws Exception {
        valorAnualAtendCliente = 0.0;
        valorAnualUsoInterno = 0.0;
    }

    public void calculaValorAprovadoMensal(final CentroResultadoDTO centroResultadoDto, final int mesRef, final int anoRef, final TransactionControler tc)
            throws Exception {
        valorMensalAtendCliente = 0.0;
        valorMensalUsoInterno = 0.0;
    }

    public double calculaValorAprovado(final SolicitacaoServicoDTO solicitacaoServicoDto, final TransactionControler tc) throws Exception {
        return 0.0;
    }

    public boolean isAtendimentoCliente(final SolicitacaoServicoDTO solicitacaoServicoDto) throws Exception {
        return false;
    }

    public ExecucaoSolicitacaoDTO getExecucaoSolicitacaoDto() {
        return execucaoSolicitacaoDto;
    }

    public void setExecucaoSolicitacaoDto(final ExecucaoSolicitacaoDTO execucaoSolicitacaoDto) {
        this.execucaoSolicitacaoDto = execucaoSolicitacaoDto;
    }

    public void setValorMensalUsoInterno(final Double valorMensalUsoInterno) {
        this.valorMensalUsoInterno = valorMensalUsoInterno;
    }

    public void setValorAnualUsoInterno(final Double valorAnualUsoInterno) {
        this.valorAnualUsoInterno = valorAnualUsoInterno;
    }

    public void setValorMensalAtendCliente(final Double valorMensalAtendCliente) {
        this.valorMensalAtendCliente = valorMensalAtendCliente;
    }

    public void setValorAnualAtendCliente(final Double valorAnualAtendCliente) {
        this.valorAnualAtendCliente = valorAnualAtendCliente;
    }

    public void cancelarRequisicao() {
        this.getSolicitacaoServicoDto().setSituacao(br.com.centralit.citcorpore.util.Enumerados.SituacaoSolicitacaoServico.Cancelada.getDescricao());
    }

    protected String getJdbcAliasBPM() throws Exception {
        String jdbcAlias = CITCorporeUtil.JDBC_ALIAS_BPM;
        if (jdbcAlias == null || jdbcAlias.trim().equals("")) {
            jdbcAlias = Constantes.getValue("DATABASE_ALIAS");
        }
        return jdbcAlias;
    }

    private SolicitacaoServicoDao solicitacaoServicoDao;

    private SolicitacaoServicoDao getSolicitacaoServicoDAO() {
        if (solicitacaoServicoDao == null) {
            solicitacaoServicoDao = new SolicitacaoServicoDao();
        }
        return solicitacaoServicoDao;
    }

    /**
     * Consulta utilizada para a RENDERIZAÇÃO da LISTAGEM SOLICITACAO SERVIÇO. Retorna a Lista de TarefaDTO com SolicitacaoServidoDTO de acordo com po
     * aramRecuperacaoTarefasDto.
     *
     * @param ParamRecuperacaoTarefasDTO
     *            paramRecuperacaoTarefasDto
     * @return List<TarefaFluxoDTO - Com SolicitacaoServicoDTO recuperados.
     * @throws Exception
     * @author carlos.santos
     * @since 27.01.2015 - Operação Usain Bolt.
     */
    public Page<TarefaFluxoDTO> recuperaTarefas(final ParamRecuperacaoTarefasDTO paramRecuperacaoTarefas, final Pageable pageable) throws Exception {
        final List<TarefaFluxoDTO> tarefas = new ArrayList<>();

        Page<TarefaFluxoDTO> resultPage = new PageImpl<>(tarefas, pageable);

        if (paramRecuperacaoTarefas.getLoginUsuario() == null) {
            return resultPage;
        }

        final TransactionControler tc = new TransactionControlerImpl(this.getJdbcAliasBPM());
        try {
            this.setTransacao(tc);
            final UsuarioDao usuarioDao = new UsuarioDao();
            this.setTransacaoDao(usuarioDao);
            final UsuarioDTO usuarioDto = usuarioDao.restoreByLogin(paramRecuperacaoTarefas.getLoginUsuario());
            if (usuarioDto == null) {
                return resultPage;
            }

            final GrupoEmpregadoDao grupoEmpregadoDao = new GrupoEmpregadoDao();
            this.setTransacaoDao(grupoEmpregadoDao);
            usuarioDto.setColGrupoEmpregado(grupoEmpregadoDao.findByIdEmpregado(usuarioDto.getIdEmpregado()));
            paramRecuperacaoTarefas.setUsuarioDto(usuarioDto);

            final TarefaUsuarioDao tarefaUsuarioDao = new TarefaUsuarioDao();
            this.setTransacaoDao(tarefaUsuarioDao);
            final Page<TarefaUsuarioDTO> pageTarefasUsuario = tarefaUsuarioDao.recuperaTarefas(paramRecuperacaoTarefas, pageable);
            final List<TarefaUsuarioDTO> tarefasUsuario = pageTarefasUsuario.getContent();
            if (tarefasUsuario == null || tarefasUsuario.isEmpty()) {
                resultPage = new PageImpl<>(tarefas, pageable, pageTarefasUsuario.getTotalElements());
                return resultPage;
            }

            final SolicitacaoServicoServiceEjb solicitacaoServicoService = new SolicitacaoServicoServiceEjb();
            for (final TarefaUsuarioDTO tarefaUsuarioDto : tarefasUsuario) {
                final TarefaFluxoDTO tarefaFluxo = tarefaUsuarioDto.converteTarefaFluxoDto();
                SolicitacaoServicoDTO solicitacaoDto = (SolicitacaoServicoDTO) tarefaFluxo.getSolicitacaoDto();
                tarefas.add(tarefaFluxo);

                if (!paramRecuperacaoTarefas.isSomenteTotalizacao()) {
                    solicitacaoDto = solicitacaoServicoService.verificaSituacaoSLA(solicitacaoDto, tc);
                }
            }

            if (!paramRecuperacaoTarefas.isSomenteTotalizacao()) {
                final Map<String, ElementoFluxoTarefaDTO> mapElementos = new HashMap<>();
                final Map<String, PermissoesFluxoDTO> mapPermissoes = new HashMap<>();
                final Map<String, UsuarioDTO> mapUsuarios = new HashMap<>();
                final Map<String, GrupoDTO> mapGrupos = new HashMap<>();

                final PermissoesFluxoDao permissoesFluxoDao = new PermissoesFluxoDao();
                this.setTransacaoDao(permissoesFluxoDao);

                final GrupoDao grupoDao = new GrupoDao();
                this.setTransacaoDao(grupoDao);

                final ElementoFluxoDao elementoFluxoDao = new ElementoFluxoDao();
                this.setTransacaoDao(elementoFluxoDao);

                final AtribuicaoFluxoDao atribuicaoTarefaDao = new AtribuicaoFluxoDao();
                this.setTransacaoDao(atribuicaoTarefaDao);

                for (final TarefaFluxoDTO tarefaFluxo : tarefas) {
                    String executar = "N";
                    String delegar = "N";
                    String suspender = "N";
                    String reativar = "N";
                    String alterarSLA = "N";

                    if (usuarioDto.getColGrupoEmpregado() != null && !tarefaFluxo.isSomenteAcompanhamento()) {
                        PermissoesFluxoDTO permissoesFluxo = null;
                        for (final GrupoEmpregadoDTO grupoEmpregadoDto : usuarioDto.getColGrupoEmpregado()) {
                            final String chave = grupoEmpregadoDto.getIdGrupo() + "|" + tarefaFluxo.getIdTipoFluxo();
                            permissoesFluxo = mapPermissoes.get(chave);
                            if (permissoesFluxo == null) {
                                permissoesFluxo = new PermissoesFluxoDTO();
                                permissoesFluxo.setIdTipoFluxo(tarefaFluxo.getIdTipoFluxo());
                                permissoesFluxo.setIdGrupo(grupoEmpregadoDto.getIdGrupo());
                                permissoesFluxo = (PermissoesFluxoDTO) permissoesFluxoDao.restore(permissoesFluxo);
                                if (permissoesFluxo == null) {
                                    continue;
                                }
                                mapPermissoes.put(chave, permissoesFluxo);
                            }
                            if (!executar.equalsIgnoreCase("S") && permissoesFluxo.getExecutar() != null && permissoesFluxo.getExecutar().equals("S")) {
                                executar = "S";
                            }
                            if (!delegar.equalsIgnoreCase("S") && permissoesFluxo.getDelegar() != null && permissoesFluxo.getDelegar().equals("S")) {
                                delegar = "S";
                            }
                            if (!suspender.equalsIgnoreCase("S") && permissoesFluxo.getSuspender() != null && permissoesFluxo.getSuspender().equals("S")) {
                                suspender = "S";
                            }
                            if (!reativar.equalsIgnoreCase("S") && permissoesFluxo.getReativar() != null && permissoesFluxo.getReativar().equals("S")) {
                                reativar = "S";
                            }
                            if (!alterarSLA.equalsIgnoreCase("S") && permissoesFluxo.getAlterarSLA() != null && permissoesFluxo.getAlterarSLA().equals("S")) {
                                alterarSLA = "S";
                            }
                        }
                    }
                    tarefaFluxo.setExecutar(executar);
                    tarefaFluxo.setDelegar(delegar);
                    tarefaFluxo.setSuspender(suspender);
                    tarefaFluxo.setReativar(reativar);
                    tarefaFluxo.setAlterarSLA(alterarSLA);

                    ElementoFluxoTarefaDTO elementoFluxo = mapElementos.get("" + tarefaFluxo.getIdElemento());
                    if (elementoFluxo == null) {
                        try {
                            elementoFluxo = (ElementoFluxoTarefaDTO) elementoFluxoDao.restore(tarefaFluxo.getIdElemento());
                        } catch (final Exception e) {
                            e.printStackTrace();
                        }
                        if (elementoFluxo == null) {
                            throw new LogicException("Erro na recuperação do elemento de fluxo com ID " + tarefaFluxo.getIdElemento() + " na instância "
                                    + tarefaFluxo.getIdInstancia());
                        }
                        mapElementos.put("" + tarefaFluxo.getIdElemento(), elementoFluxo);
                    }

                    tarefaFluxo.setElementoFluxoDto(elementoFluxo);
                    tarefaFluxo.setCompartilhamento("");
                    final AtribuicaoFluxoDTO atribuicaoDelegacao = this.recuperaDelegacao(tarefaFluxo.getIdItemTrabalho(), atribuicaoTarefaDao);
                    if (atribuicaoDelegacao == null) {
                        continue;
                    }
                    if (atribuicaoDelegacao.getIdUsuario() != null) {
                        if (!atribuicaoDelegacao.getIdUsuario().equals(usuarioDto.getIdUsuario())) {
                            UsuarioDTO usuarioAtualDto = mapUsuarios.get("" + atribuicaoDelegacao.getIdUsuario());
                            if (usuarioAtualDto == null) {
                                usuarioAtualDto = usuarioDao.restoreByID(atribuicaoDelegacao.getIdUsuario());
                                if (usuarioAtualDto == null) {
                                    continue;
                                }
                                mapUsuarios.put("" + atribuicaoDelegacao.getIdUsuario(), usuarioAtualDto);
                            }
                            tarefaFluxo.setCompartilhamento(usuarioAtualDto.getNomeUsuario());
                        }
                    } else {
                        GrupoDTO grupoAtual = mapGrupos.get("" + atribuicaoDelegacao.getIdGrupo());
                        if (grupoAtual == null) {
                            grupoAtual = new GrupoDTO();
                            grupoAtual.setIdGrupo(atribuicaoDelegacao.getIdGrupo());
                            grupoAtual = (GrupoDTO) grupoDao.restore(grupoAtual);
                            if (grupoAtual == null) {
                                continue;
                            }
                            mapGrupos.put("" + atribuicaoDelegacao.getIdGrupo(), grupoAtual);
                        }
                        tarefaFluxo.setCompartilhamento(grupoAtual.getSigla());
                    }
                }
            }
            resultPage = new PageImpl<>(tarefas, pageable);
            return resultPage;
        } finally {
            tc.closeQuietly();
        }
    }

}
