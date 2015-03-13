package br.com.centralit.citcorpore.bpm.negocio;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.bpm.dto.EventoFluxoDTO;
import br.com.centralit.bpm.dto.FluxoDTO;
import br.com.centralit.bpm.dto.ItemTrabalhoFluxoDTO;
import br.com.centralit.bpm.dto.ObjetoNegocioFluxoDTO;
import br.com.centralit.bpm.dto.TarefaFluxoDTO;
import br.com.centralit.bpm.dto.TipoFluxoDTO;
import br.com.centralit.bpm.integracao.FluxoDao;
import br.com.centralit.bpm.integracao.ItemTrabalhoFluxoDao;
import br.com.centralit.bpm.integracao.TipoFluxoDao;
import br.com.centralit.bpm.negocio.ExecucaoFluxo;
import br.com.centralit.bpm.negocio.InstanciaFluxo;
import br.com.centralit.bpm.negocio.ItemTrabalho;
import br.com.centralit.bpm.util.Enumerados;
import br.com.centralit.citcorpore.bean.CalculoJornadaDTO;
import br.com.centralit.citcorpore.bean.EmpregadoDTO;
import br.com.centralit.citcorpore.bean.ExecucaoLiberacaoDTO;
import br.com.centralit.citcorpore.bean.GrupoDTO;
import br.com.centralit.citcorpore.bean.ItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.ModeloEmailDTO;
import br.com.centralit.citcorpore.bean.OcorrenciaLiberacaoDTO;
import br.com.centralit.citcorpore.bean.RequisicaoLiberacaoDTO;
import br.com.centralit.citcorpore.bean.RequisicaoLiberacaoItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.TipoLiberacaoDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.integracao.ExecucaoLiberacaoDao;
import br.com.centralit.citcorpore.integracao.GrupoDao;
import br.com.centralit.citcorpore.integracao.ModeloEmailDao;
import br.com.centralit.citcorpore.integracao.OcorrenciaLiberacaoDao;
import br.com.centralit.citcorpore.integracao.RequisicaoLiberacaoDao;
import br.com.centralit.citcorpore.integracao.TipoLiberacaoDAO;
import br.com.centralit.citcorpore.integracao.UsuarioDao;
import br.com.centralit.citcorpore.mail.MensagemEmail;
import br.com.centralit.citcorpore.negocio.CalendarioServiceEjb;
import br.com.centralit.citcorpore.negocio.EmpregadoService;
import br.com.centralit.citcorpore.negocio.GrupoService;
import br.com.centralit.citcorpore.negocio.ItemConfiguracaoService;
import br.com.centralit.citcorpore.negocio.RequisicaoLiberacaoItemConfiguracaoService;
import br.com.centralit.citcorpore.negocio.RequisicaoLiberacaoServiceEjb;
import br.com.centralit.citcorpore.util.Enumerados.FaseRequisicaoLiberacao;
import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoRequisicaoLiberacao;
import br.com.centralit.citcorpore.util.Enumerados.StatusIC;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.centralit.citcorpore.util.Util;
import br.com.citframework.comparacao.ObjectSimpleComparator;
import br.com.citframework.dto.Usuario;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.integracao.TransactionControlerImpl;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.UtilDatas;

import com.google.gson.Gson;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ExecucaoLiberacao extends ExecucaoFluxo {

    private UsuarioDTO usuarioDto = null;

    public ExecucaoLiberacao(final TransactionControler tc) {
        super(tc);
    }

    public ExecucaoLiberacao() {
        super();
    }

    public ExecucaoLiberacao(final RequisicaoLiberacaoDTO requisicaoLiberacaoDto, final TransactionControler tc) {
        super(requisicaoLiberacaoDto, tc);
    }

    public ExecucaoLiberacao(final RequisicaoLiberacaoDTO requisicaoLiberacaoDto, final TransactionControler tc, final Usuario usuario) {
        super(requisicaoLiberacaoDto, tc, usuario);
    }

    @Override
    public InstanciaFluxo inicia(final String nomeFluxo, final Integer idFase) throws Exception {
        final TipoFluxoDao tipoFluxoDao = new TipoFluxoDao();
        final TipoFluxoDTO tipoFluxoDto = tipoFluxoDao.findByNome(nomeFluxo);
        if (tipoFluxoDto == null) {
            System.out.println("Fluxo " + nomeFluxo + " não existe");
            throw new LogicException(this.i18n_Message("citcorpore.comum.fluxoNaoEncontrado"));
        }
        return this.inicia(new FluxoDao().findByTipoFluxo(tipoFluxoDto.getIdTipoFluxo()), idFase);
    }

    @Override
    public InstanciaFluxo inicia() throws Exception {
        if (this.getRequisicaoLiberacaoDto().getIdTipoLiberacao() == null) {
            throw new LogicException(this.i18n_Message("requisicaoLiberacao.tipoNaoDefinido"));
        }

        InstanciaFluxo result = null;
        final TipoLiberacaoDTO tipoLiberacaoDto = this.recuperaTipoLiberacao();
        if (tipoLiberacaoDto.getIdTipoFluxo() != null) {
            result = this.inicia(new FluxoDao().findByTipoFluxo(tipoLiberacaoDto.getIdTipoFluxo()), null);
        } else {
            final String fluxoPadrao = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.FLUXO_PADRAO_MUDANCAS, null);
            if (fluxoPadrao == null) {
                throw new LogicException(this.i18n_Message("citcorpore.comum.fluxoNaoParametrizado"));
            }
            result = this.inicia(fluxoPadrao, null);
        }

        try {
            final String enviarNotificacao = ParametroUtil.getValor(ParametroSistema.NOTIFICAR_GRUPO_RECEPCAO_SOLICITACAO, this.getTransacao(), "N");
            final String IdModeloEmailGrupoDestinoREquisicaoLiberacao = ParametroUtil.getValorParametroCitSmartHashMap(
                    ParametroSistema.ID_MODELO_EMAIL_GRUPO_DESTINO_REQUISICAOMUDANCA, "30");
            if (enviarNotificacao.equalsIgnoreCase("S") && this.getRequisicaoLiberacaoDto().escalada()) {
                this.enviaEmailGrupo(Integer.parseInt(IdModeloEmailGrupoDestinoREquisicaoLiberacao.trim()), tipoLiberacaoDto.getIdGrupoExecutor());
            }
        } catch (final NumberFormatException e) {
            System.out.println(this.i18n_Message("requisicaoLiberacao.emailNaoDefinido"));
        }

        return result;
    }

    @Override
    public InstanciaFluxo inicia(final FluxoDTO fluxoDto, final Integer idFase) throws Exception {
        if (fluxoDto == null) {
            throw new LogicException(this.i18n_Message("citcorpore.comum.fluxoNaoEncontrado"));
        }

        this.fluxoDto = fluxoDto;

        final HashMap<String, Object> map = new HashMap<>();
        this.mapObjetoNegocio(map);
        final InstanciaFluxo instanciaFluxo = new InstanciaFluxo(this, map);

        final ExecucaoLiberacaoDTO execucaoDto = new ExecucaoLiberacaoDTO();
        execucaoDto.setIdRequisicaoLiberacao(this.getRequisicaoLiberacaoDto().getIdRequisicaoLiberacao());
        execucaoDto.setIdFluxo(instanciaFluxo.getIdFluxo());
        execucaoDto.setIdInstanciaFluxo(instanciaFluxo.getIdInstancia());
        Integer seqReabertura = 0;
        if (this.getRequisicaoLiberacaoDto().getSeqReabertura() != null && this.getRequisicaoLiberacaoDto().getSeqReabertura().intValue() > 0) {
            seqReabertura = this.getRequisicaoLiberacaoDto().getSeqReabertura();
        }
        if (seqReabertura.intValue() > 0) {
            execucaoDto.setSeqReabertura(this.getRequisicaoLiberacaoDto().getSeqReabertura());
        }

        final ExecucaoLiberacaoDao execucaoDao = new ExecucaoLiberacaoDao();
        this.setTransacaoDao(execucaoDao);
        execucaoFluxoDto = (ExecucaoLiberacaoDTO) execucaoDao.create(execucaoDto);

        if (seqReabertura.intValue() == 0 && this.getRequisicaoLiberacaoDto().getEnviaEmailCriacao() != null
                && this.getRequisicaoLiberacaoDto().getEnviaEmailCriacao().equalsIgnoreCase("S")) {
            final TipoLiberacaoDTO tipoLiberacaoDto = this.recuperaTipoLiberacao();
            this.enviaEmail(tipoLiberacaoDto.getIdModeloEmailCriacao());
        }
        return instanciaFluxo;
    }

    @Override
    public void executa(final String loginUsuario, final ObjetoNegocioFluxoDTO objetoNegocioDto, final Integer idItemTrabalho, final String acao,
            final HashMap<String, Object> map) throws Exception {
        if (acao.equals(Enumerados.ACAO_DELEGAR)) {
            return;
        }

        TarefaFluxoDTO tarefaFluxoDto = this.recuperaTarefa(idItemTrabalho);
        if (tarefaFluxoDto == null) {
            return;
        }

        final ExecucaoLiberacaoDao execucaoLiberacaoDao = new ExecucaoLiberacaoDao();
        this.setTransacaoDao(execucaoLiberacaoDao);
        final ExecucaoLiberacaoDTO execucaoLiberacaoDto = execucaoLiberacaoDao.findByIdInstanciaFluxo(tarefaFluxoDto.getIdInstancia());
        if (execucaoLiberacaoDto == null) {
            return;
        }

        this.recuperaFluxo(execucaoLiberacaoDto.getIdFluxo());

        this.objetoNegocioDto = objetoNegocioDto;
        usuarioDto = new UsuarioDao().restoreByLogin(loginUsuario);

        final InstanciaFluxo instanciaFluxo = new InstanciaFluxo(this, tarefaFluxoDto.getIdInstancia());
        this.mapObjetoNegocio(instanciaFluxo.getObjetos(map));

        if (acao.equals(Enumerados.ACAO_INICIAR)) {
            instanciaFluxo.iniciaItemTrabalho(loginUsuario, tarefaFluxoDto.getIdItemTrabalho(), map);
        } else if (acao.equals(Enumerados.ACAO_EXECUTAR)) {
            tarefaFluxoDto = this.recuperaTarefa(idItemTrabalho);
            final OcorrenciaLiberacaoDao ocorrenciaLiberacaoDao = new OcorrenciaLiberacaoDao();
            this.setTransacaoDao(ocorrenciaLiberacaoDao);
            final OcorrenciaLiberacaoDTO ocorrenciaLiberacaoDto = new OcorrenciaLiberacaoDTO();
            ocorrenciaLiberacaoDto.setIdRequisicaoLiberacao(this.getRequisicaoLiberacaoDto().getIdRequisicaoLiberacao());
            ocorrenciaLiberacaoDto.setDataregistro(UtilDatas.getDataAtual());
            ocorrenciaLiberacaoDto.setHoraregistro(UtilDatas.formatHoraFormatadaStr(UtilDatas.getHoraAtual()));
            Long tempo = new Long(0);
            if (tarefaFluxoDto.getDataHoraFinalizacao() != null) {
                tempo = (tarefaFluxoDto.getDataHoraFinalizacao().getTime() - tarefaFluxoDto.getDataHoraCriacao().getTime()) / 1000 / 60;
            }
            ocorrenciaLiberacaoDto.setTempoGasto(tempo.intValue());
            ocorrenciaLiberacaoDto.setDescricao(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Execucao.getDescricao());
            ocorrenciaLiberacaoDto.setDataInicio(UtilDatas.getDataAtual());
            ocorrenciaLiberacaoDto.setDataFim(UtilDatas.getDataAtual());
            ocorrenciaLiberacaoDto.setInformacoesContato("não se aplica");
            ocorrenciaLiberacaoDto.setRegistradopor(loginUsuario);
            ocorrenciaLiberacaoDto.setDadosLiberacao(new Gson().toJson(this.getRequisicaoLiberacaoDto()));
            ocorrenciaLiberacaoDto.setOcorrencia("Execução da tarefa \"" + tarefaFluxoDto.getElementoFluxoDto().getDocumentacao() + "\"");
            ocorrenciaLiberacaoDto.setOrigem(br.com.centralit.citcorpore.util.Enumerados.OrigemOcorrencia.OUTROS.getSigla().toString());
            ocorrenciaLiberacaoDto.setCategoria(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Execucao.getSigla());
            ocorrenciaLiberacaoDto.setIdItemTrabalho(idItemTrabalho);
            ocorrenciaLiberacaoDao.create(ocorrenciaLiberacaoDto);

            instanciaFluxo.executaItemTrabalho(loginUsuario, tarefaFluxoDto.getIdItemTrabalho(), map);

            if (this.getRequisicaoLiberacaoDto().getStatus()
                    .equalsIgnoreCase(br.com.centralit.citcorpore.util.Enumerados.SituacaoRequisicaoLiberacao.NaoResolvida.name())) {
                final GrupoDao grupoDao = new GrupoDao();
                final List<GrupoDTO> listGrupo = (List<GrupoDTO>) grupoDao.getGruposByIdEmpregado(this.getRequisicaoLiberacaoDto().getIdSolicitante());
                listGrupo.get(0);
            }

            if (this.getRequisicaoLiberacaoDto().getFase() != null && this.getRequisicaoLiberacaoDto().getFase().trim().length() > 0) {
                final SituacaoRequisicaoLiberacao novaSituacao = FaseRequisicaoLiberacao.valueOf(this.getRequisicaoLiberacaoDto().getFase()).getSituacao();
                if (novaSituacao != null) {
                    final RequisicaoLiberacaoDao requisicaoDao = new RequisicaoLiberacaoDao();
                    this.setTransacaoDao(requisicaoDao);
                    this.getRequisicaoLiberacaoDto().setStatus(novaSituacao.name());
                    requisicaoDao.updateNotNull(this.getRequisicaoLiberacaoDto());
                }
            }
        }

        if (tarefaFluxoDto.getElementoFluxoDto().getDocumentacao().equals("Liberado")) {
            final RequisicaoLiberacaoDao requisicaoDao = new RequisicaoLiberacaoDao();
            this.setTransacaoDao(requisicaoDao);
            this.getRequisicaoLiberacaoDto().setIdAprovador(usuarioDto.getIdUsuario());
            this.getRequisicaoLiberacaoDto().setDatahoraAprovacao(UtilDatas.getDataHoraAtual());
            requisicaoDao.updateNotNull(this.getRequisicaoLiberacaoDto());
        }

        if (this.getRequisicaoLiberacaoDto().getEnviaEmailAcoes() != null && this.getRequisicaoLiberacaoDto().getEnviaEmailAcoes().equalsIgnoreCase("S")) {
            this.getRequisicaoLiberacaoDto().setNomeTarefa(tarefaFluxoDto.getElementoFluxoDto().getDocumentacao());
            final TipoLiberacaoDTO tipoLiberacaoDto = this.recuperaTipoLiberacao();
            this.enviaEmail(tipoLiberacaoDto.getIdModeloEmailAcoes());
        }

        /*
         * if(getRequisicaoLiberacaoDto().getStatus().equalsIgnoreCase("Concluida")){
         * this.finalizaIC(getRequisicaoLiberacaoDto());
         * }
         */
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

        final OcorrenciaLiberacaoDao ocorrenciaLiberacaoDao = new OcorrenciaLiberacaoDao();
        this.setTransacaoDao(ocorrenciaLiberacaoDao);
        final OcorrenciaLiberacaoDTO ocorrenciaLiberacaoDto = new OcorrenciaLiberacaoDTO();
        ocorrenciaLiberacaoDto.setIdRequisicaoLiberacao(this.getRequisicaoLiberacaoDto().getIdRequisicaoLiberacao());
        ocorrenciaLiberacaoDto.setDataregistro(UtilDatas.getDataAtual());
        ocorrenciaLiberacaoDto.setHoraregistro(UtilDatas.formatHoraFormatadaStr(UtilDatas.getHoraAtual()));
        ocorrenciaLiberacaoDto.setTempoGasto(0);
        ocorrenciaLiberacaoDto.setDescricao(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Compartilhamento.getDescricao());
        ocorrenciaLiberacaoDto.setDataInicio(UtilDatas.getDataAtual());
        ocorrenciaLiberacaoDto.setDataFim(UtilDatas.getDataAtual());
        ocorrenciaLiberacaoDto.setInformacoesContato("não se aplica");
        ocorrenciaLiberacaoDto.setRegistradopor(loginUsuario);
        ocorrenciaLiberacaoDto.setDadosLiberacao(new Gson().toJson(this.getRequisicaoLiberacaoDto()));
        String ocorr = "Compartilhamento da tarefa \"" + tarefaFluxoDto.getElementoFluxoDto().getDocumentacao() + "\"";
        if (usuarioDestino != null) {
            ocorr += " com o usuário " + usuarioDestino;
        }
        if (grupoDestino != null) {
            ocorr += " com o grupo " + grupoDestino;
        }
        ocorrenciaLiberacaoDto.setOcorrencia(ocorr);
        ocorrenciaLiberacaoDto.setOrigem(br.com.centralit.citcorpore.util.Enumerados.OrigemOcorrencia.OUTROS.getSigla().toString());
        ocorrenciaLiberacaoDto.setCategoria(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Compartilhamento.getSigla());
        ocorrenciaLiberacaoDto.setIdItemTrabalho(idItemTrabalho);
        ocorrenciaLiberacaoDao.create(ocorrenciaLiberacaoDto);
    }

    @Override
    public void direcionaAtendimento(final String loginUsuario, final ObjetoNegocioFluxoDTO objetoNegocioDto, final String grupoAtendimento) throws Exception {
        if (this.getRequisicaoLiberacaoDto() == null) {
            return;
        }

        if (grupoAtendimento == null) {
            return;
        }

        final GrupoDao grupoDao = new GrupoDao();
        final GrupoDTO grupoAtendimentoDto = grupoDao.restoreBySigla(grupoAtendimento);
        if (grupoAtendimentoDto == null) {
            return;
        }

        final UsuarioDao usuarioDao = new UsuarioDao();
        this.setTransacaoDao(usuarioDao);
        UsuarioDTO usuarioRespDto = new UsuarioDTO();
        usuarioRespDto.setIdUsuario(this.getRequisicaoLiberacaoDto().getIdResponsavel());
        usuarioRespDto = (UsuarioDTO) usuarioDao.restore(usuarioRespDto);

        this.objetoNegocioDto = objetoNegocioDto;

        final ExecucaoLiberacaoDao execucaoLiberacaoDao = new ExecucaoLiberacaoDao();
        this.setTransacaoDao(execucaoLiberacaoDao);

        final Collection<ExecucaoLiberacaoDTO> colExecucao = execucaoLiberacaoDao.listByIdRequisicaoLiberacao(this.getRequisicaoLiberacaoDto()
                .getIdRequisicaoLiberacao());
        if (colExecucao != null) {
            final ItemTrabalhoFluxoDao itemTrabalhoFluxoDao = new ItemTrabalhoFluxoDao();
            this.setTransacaoDao(itemTrabalhoFluxoDao);
            final OcorrenciaLiberacaoDao ocorrenciaLiberacaoDao = new OcorrenciaLiberacaoDao();
            this.setTransacaoDao(ocorrenciaLiberacaoDao);
            for (final ExecucaoLiberacaoDTO execucaoLiberacaoDto : colExecucao) {
                final InstanciaFluxo instanciaFluxo = new InstanciaFluxo(this, execucaoLiberacaoDto.getIdInstanciaFluxo());
                final Collection<ItemTrabalhoFluxoDTO> colItens = itemTrabalhoFluxoDao.findDisponiveisByIdInstancia(execucaoLiberacaoDto.getIdInstanciaFluxo());
                if (colItens != null) {
                    for (final ItemTrabalhoFluxoDTO itemTrabalhoFluxoDto : colItens) {
                        final ItemTrabalho itemTrabalho = ItemTrabalho.getItemTrabalho(instanciaFluxo, itemTrabalhoFluxoDto.getIdItemTrabalho());
                        itemTrabalho.redireciona(loginUsuario, null, grupoAtendimento);

                        usuarioDto = usuarioDao.restoreByLogin(loginUsuario);

                        /* TarefaFluxoDTO tarefaFluxoDto = recuperaTarefa(atribuicaoFluxoDto.getIdItemTrabalho()); */
                        final OcorrenciaLiberacaoDTO ocorrenciaLiberacaoDto = new OcorrenciaLiberacaoDTO();
                        ocorrenciaLiberacaoDto.setIdRequisicaoLiberacao(this.getRequisicaoLiberacaoDto().getIdRequisicaoLiberacao());
                        ocorrenciaLiberacaoDto.setDataregistro(UtilDatas.getDataAtual());
                        ocorrenciaLiberacaoDto.setHoraregistro(UtilDatas.formatHoraFormatadaStr(UtilDatas.getHoraAtual()));
                        ocorrenciaLiberacaoDto.setTempoGasto(0);
                        ocorrenciaLiberacaoDto.setDescricao(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Direcionamento.getDescricao());
                        ocorrenciaLiberacaoDto.setDataInicio(UtilDatas.getDataAtual());
                        ocorrenciaLiberacaoDto.setDataFim(UtilDatas.getDataAtual());
                        ocorrenciaLiberacaoDto.setInformacoesContato("não se aplica");
                        ocorrenciaLiberacaoDto.setRegistradopor(loginUsuario);
                        ocorrenciaLiberacaoDto.setDadosLiberacao(new Gson().toJson(this.getRequisicaoLiberacaoDto()));
                        String ocorr = "Direcionamento da tarefa \"" + itemTrabalho.getElementoFluxoDto().getDocumentacao() + "\"";
                        ocorr += " para o grupo " + grupoAtendimento;
                        /* ocorr += " para o grupo "+grupoAtendimento; */
                        ocorrenciaLiberacaoDto.setOcorrencia(ocorr);
                        ocorrenciaLiberacaoDto.setOrigem(br.com.centralit.citcorpore.util.Enumerados.OrigemOcorrencia.OUTROS.getSigla().toString());
                        ocorrenciaLiberacaoDto.setCategoria(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Direcionamento.getSigla());
                        /* ocorrenciaLiberacaoDto.setIdItemTrabalho(atribuicaoFluxoDto.getIdItemTrabalho()); */
                        ocorrenciaLiberacaoDao.create(ocorrenciaLiberacaoDto);

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
            System.out.println(this.i18n_Message("requisicaoLiberacao.emailNaoDefinido"));
        }
    }

    /*
     * @Override
     * public void mapObjetoNegocio(Map<String, Object> map) throws Exception {
     * SolicitacaoServicoDTO solicitacaoServicoDto = (SolicitacaoServicoDTO) objetoNegocioDto;
     * SolicitacaoServicoDTO solicitacaoAuxDto = new SolicitacaoServicoServiceEjb().restoreAll(solicitacaoServicoDto.getIdSolicitacaoServico(), getTransacao());
     * if(solicitacaoAuxDto != null){
     * solicitacaoServicoDto.setGrupoAtual(solicitacaoAuxDto.getGrupoAtual());
     * solicitacaoServicoDto.setGrupoNivel1(solicitacaoAuxDto.getGrupoNivel1());
     * }
     * adicionaObjeto("solicitacaoServico", solicitacaoServicoDto, map);
     * if (usuarioDTO != null)
     * adicionaObjeto("usuario", usuarioDTO, map);
     * else if (solicitacaoServicoDto.getUsuarioDto() != null)
     * adicionaObjeto("usuario", solicitacaoServicoDto.getUsuarioDto(), map);
     * adicionaObjeto("execucaoFluxo", this, map);
     * adicionaObjeto("solicitacaoServicoService", new SolicitacaoServicoServiceEjb(), map);
     * }
     */

    @Override
    public void mapObjetoNegocio(final Map<String, Object> map) throws Exception {
        final RequisicaoLiberacaoDTO requisicaoLiberacaoDto = (RequisicaoLiberacaoDTO) objetoNegocioDto;
        final RequisicaoLiberacaoDTO requisicaoAuxDto = new RequisicaoLiberacaoServiceEjb().restoreAll(requisicaoLiberacaoDto.getIdRequisicaoLiberacao(),
                this.getTransacao());
        requisicaoLiberacaoDto.setIdGrupoAtual(requisicaoAuxDto.getIdGrupoAtual());
        requisicaoLiberacaoDto.setIdGrupoNivel1(requisicaoAuxDto.getIdGrupoNivel1());
        requisicaoLiberacaoDto.setNomeGrupoAtual(requisicaoAuxDto.getNomeGrupoAtual());
        requisicaoLiberacaoDto.setNomeGrupoNivel1(requisicaoAuxDto.getNomeGrupoNivel1());
        final UsuarioDTO usuario = this.usuarioSolicitante(requisicaoAuxDto);
        if (usuario != null) {
            requisicaoLiberacaoDto.setUsuarioSolicitante(usuario.getLogin());
        }

        this.adicionaObjeto("requisicaoLiberacao", requisicaoLiberacaoDto, map);
        if (usuarioDto != null) {
            this.adicionaObjeto("usuario", usuarioDto, map);
        } else if (requisicaoLiberacaoDto.getUsuarioDto() != null) {
            this.adicionaObjeto("usuario", requisicaoLiberacaoDto.getUsuarioDto(), map);
        }

        this.adicionaObjeto("execucaoFluxo", this, map);
        this.adicionaObjeto("requisicaoLiberacaoService", new RequisicaoLiberacaoServiceEjb(), map);
    }

    public RequisicaoLiberacaoDTO getRequisicaoLiberacaoDto() {
        return (RequisicaoLiberacaoDTO) objetoNegocioDto;
    }

    @Override
    public List<TarefaFluxoDTO> recuperaTarefas(final String loginUsuario) throws Exception {
        RequisicaoLiberacaoDTO requisicaoLiberacaoDto = new RequisicaoLiberacaoDTO();
        List<TarefaFluxoDTO> result = null;
        final List<TarefaFluxoDTO> listTarefas = super.recuperaTarefas(loginUsuario);
        if (listTarefas != null) {
            result = new ArrayList<>();
            final RequisicaoLiberacaoServiceEjb requisicaoService = new RequisicaoLiberacaoServiceEjb();
            final ExecucaoLiberacaoDao execucaoLiberacaoDao = new ExecucaoLiberacaoDao();
            for (final TarefaFluxoDTO tarefaDto : listTarefas) {
                final ExecucaoLiberacaoDTO execucaoLiberacaoDto = execucaoLiberacaoDao.findByIdInstanciaFluxo(tarefaDto.getIdInstancia());
                if (execucaoLiberacaoDto != null && execucaoLiberacaoDto.getIdRequisicaoLiberacao() != null) {
                    if (execucaoLiberacaoDto.getIdRequisicaoLiberacao() != null) {
                        requisicaoLiberacaoDto = requisicaoService.restoreAll(execucaoLiberacaoDto.getIdRequisicaoLiberacao(), null);
                    }
                    if (requisicaoLiberacaoDto != null) {
                        tarefaDto.setRequisicaoLiberacaoDto(requisicaoLiberacaoDto);
                        result.add(tarefaDto);
                    }
                }
            }
            Collections.sort(result, new ObjectSimpleComparator("getDataHoraInicio", ObjectSimpleComparator.ASC));
        }
        return result;
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

    @Override
    public void enviaEmail(final Integer idModeloEmail) throws Exception {
        if (idModeloEmail == null) {
            return;
        }

        final String enviaEmail = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.EnviaEmailFluxo, "N");
        if (!enviaEmail.equalsIgnoreCase("S")) {
            return;
        }

        final RequisicaoLiberacaoDTO requisicaoAuxDto = new RequisicaoLiberacaoServiceEjb().restoreAll(this.getRequisicaoLiberacaoDto()
                .getIdRequisicaoLiberacao(), this.getTransacao());
        requisicaoAuxDto.setNomeContato(this.getRequisicaoLiberacaoDto().getNomeContato());
        if (requisicaoAuxDto.getEmailSolicitante() == null) {
            return;
        }

        final String remetente = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.RemetenteNotificacoesSolicitacao, null);
        if (remetente == null) {
            throw new LogicException(this.i18n_Message("requisicaoLiberacao.remetenteNaoDefinido"));
        }

        requisicaoAuxDto.setNomeTarefa(this.getRequisicaoLiberacaoDto().getNomeTarefa());

        final MensagemEmail mensagem = new MensagemEmail(idModeloEmail, new BaseEntity[] {requisicaoAuxDto});
        try {
            mensagem.envia(requisicaoAuxDto.getEmailSolicitante(), remetente, remetente);
        } catch (final Exception e) {}
    }

    private TipoLiberacaoDTO recuperaTipoLiberacao() throws Exception {
        final TipoLiberacaoDAO tipoLiberacaoDao = new TipoLiberacaoDAO();
        this.setTransacaoDao(tipoLiberacaoDao);
        TipoLiberacaoDTO tipoLiberacaoDto = new TipoLiberacaoDTO();
        if (this.getRequisicaoLiberacaoDto().getIdTipoLiberacao() != null) {
            tipoLiberacaoDto.setIdTipoLiberacao(this.getRequisicaoLiberacaoDto().getIdTipoLiberacao());
            tipoLiberacaoDto = (TipoLiberacaoDTO) tipoLiberacaoDao.restore(tipoLiberacaoDto);
        }

        if (tipoLiberacaoDto == null) {

            throw new LogicException(this.i18n_Message("requisicaoLiberacao.categoriaLiberacaoNaoLocalizada"));
        }

        return tipoLiberacaoDto;
    }

    @Override
    public void encerra() throws Exception {
        final RequisicaoLiberacaoDTO requisicaoLiberacaoDto = this.getRequisicaoLiberacaoDto();
        if (requisicaoLiberacaoDto == null) {
            throw new LogicException(this.i18n_Message("requisicaoLiberacao.naoEncontrada"));
        }

        if (requisicaoLiberacaoDto.encerrada()) {
            return;
        }

        // if (!requisicaoLiberacaoDto.atendida() && !requisicaoLiberacaoDto.reclassificada())
        // throw new LogicException("Solicitação de serviço não permite encerramento");

        final Collection<ExecucaoLiberacaoDTO> colExecucao = new ExecucaoLiberacaoDao().listByIdRequisicaoLiberacao(this.getRequisicaoLiberacaoDto()
                .getIdRequisicaoLiberacao());
        if (colExecucao != null) {
            for (final ExecucaoLiberacaoDTO execucaoDto : colExecucao) {
                final InstanciaFluxo instanciaFluxo = new InstanciaFluxo(this, execucaoDto.getIdInstanciaFluxo());
                instanciaFluxo.encerra();
            }
        }
        if (!requisicaoLiberacaoDto.getStatus().equalsIgnoreCase(SituacaoRequisicaoLiberacao.Cancelada.name())) {
            requisicaoLiberacaoDto.setStatus(SituacaoRequisicaoLiberacao.Concluida.name());
        }
        requisicaoLiberacaoDto.setDataHoraConclusao(UtilDatas.getDataHoraAtual());
        this.calculaTempoCaptura(requisicaoLiberacaoDto);
        this.calculaTempoAtendimento(requisicaoLiberacaoDto);
        this.calculaTempoAtraso(requisicaoLiberacaoDto);
        final RequisicaoLiberacaoDao requisicaoLiberacaoDao = new RequisicaoLiberacaoDao();
        this.setTransacaoDao(requisicaoLiberacaoDao);
        requisicaoLiberacaoDao.updateNotNull(requisicaoLiberacaoDto);

        final OcorrenciaLiberacaoDao ocorrenciaLiberacaoDao = new OcorrenciaLiberacaoDao();
        this.setTransacaoDao(ocorrenciaLiberacaoDao);
        final OcorrenciaLiberacaoDTO ocorrenciaLiberacaoDto = new OcorrenciaLiberacaoDTO();
        ocorrenciaLiberacaoDto.setIdRequisicaoLiberacao(this.getRequisicaoLiberacaoDto().getIdRequisicaoLiberacao());
        ocorrenciaLiberacaoDto.setDataregistro(UtilDatas.getDataAtual());
        ocorrenciaLiberacaoDto.setHoraregistro(UtilDatas.formatHoraFormatadaStr(UtilDatas.getHoraAtual()));
        ocorrenciaLiberacaoDto.setTempoGasto(0);
        ocorrenciaLiberacaoDto.setDescricao(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Encerramento.getDescricao());
        ocorrenciaLiberacaoDto.setDataInicio(UtilDatas.getDataAtual());
        ocorrenciaLiberacaoDto.setDataFim(UtilDatas.getDataAtual());
        ocorrenciaLiberacaoDto.setRegistradopor("Automático");
        ocorrenciaLiberacaoDto.setDadosLiberacao(new Gson().toJson(this.getRequisicaoLiberacaoDto()));
        ocorrenciaLiberacaoDto.setOrigem(br.com.centralit.citcorpore.util.Enumerados.OrigemOcorrencia.OUTROS.getSigla().toString());
        ocorrenciaLiberacaoDto.setCategoria(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Encerramento.getSigla());
        ocorrenciaLiberacaoDao.create(ocorrenciaLiberacaoDto);

        if (this.getRequisicaoLiberacaoDto().getEnviaEmailFinalizacao() != null
                && this.getRequisicaoLiberacaoDto().getEnviaEmailFinalizacao().equalsIgnoreCase("S")) {
            final TipoLiberacaoDTO tipoLiberacaoDto = this.recuperaTipoLiberacao();
            this.enviaEmail(tipoLiberacaoDto.getIdModeloEmailFinalizacao());
        }
    }

    @Override
    public void reabre(final String loginUsuario) throws Exception {
        final RequisicaoLiberacaoDTO requisicaoLiberacaoDto = this.getRequisicaoLiberacaoDto();
        if (requisicaoLiberacaoDto == null) {
            throw new LogicException(this.i18n_Message("requisicaoLiberacao.naoEncontrada"));
        }

        if (!requisicaoLiberacaoDto.encerrada()) {
            throw new LogicException(this.i18n_Message("requisicaoLiberacao.reabertura"));
        }

        usuarioDto = new UsuarioDao().restoreByLogin(loginUsuario);
        final int seqReabertura = 1;
        /*
         * ReaberturaLiberacaoDao reaberturaLiberacaoDao = new ReaberturaLiberacaoDao();
         * setTransacaoDao(reaberturaLiberacaoDao);
         * Collection colReabertura = reaberturaLiberacaoDao.findByIdRequisicaoLiberacao(requisicaoLiberacaoDto.getIdRequisicaoLiberacao());
         * if (colReabertura != null)
         * seqReabertura = colReabertura.size() + 1;
         * ReaberturaRequisicaoLiberacaoDTO reaberturaLiberacaoDto = new ReaberturaRequisicaoLiberacaoDTO();
         * reaberturaLiberacaoDto.setIdRequisicaoLiberacao(requisicaoLiberacaoDto.getIdRequisicaoLiberacao());
         * reaberturaLiberacaoDto.setSeqReabertura(seqReabertura);
         * reaberturaLiberacaoDto.setIdResponsavel(usuarioDto.getIdUsuario());
         * reaberturaLiberacaoDto.setDataHora(UtilDatas.getDataHoraAtual());
         * reaberturaLiberacaoDao.create(reaberturaLiberacaoDto);
         */

        requisicaoLiberacaoDto.setStatus(SituacaoRequisicaoLiberacao.Reaberta.name());
        requisicaoLiberacaoDto.setSeqReabertura(new Integer(seqReabertura));
        final RequisicaoLiberacaoDao requisicaoDao = new RequisicaoLiberacaoDao();
        this.setTransacaoDao(requisicaoDao);
        requisicaoDao.update(requisicaoLiberacaoDto);

        final OcorrenciaLiberacaoDao ocorrenciaLiberacaoDao = new OcorrenciaLiberacaoDao();
        this.setTransacaoDao(ocorrenciaLiberacaoDao);
        final OcorrenciaLiberacaoDTO ocorrenciaLiberacaoDto = new OcorrenciaLiberacaoDTO();
        ocorrenciaLiberacaoDto.setIdRequisicaoLiberacao(requisicaoLiberacaoDto.getIdRequisicaoLiberacao());
        ocorrenciaLiberacaoDto.setDataregistro(UtilDatas.getDataAtual());
        ocorrenciaLiberacaoDto.setHoraregistro(UtilDatas.formatHoraFormatadaStr(UtilDatas.getHoraAtual()));
        ocorrenciaLiberacaoDto.setTempoGasto(0);
        ocorrenciaLiberacaoDto.setDescricao(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Reabertura.getDescricao());
        ocorrenciaLiberacaoDto.setDataInicio(UtilDatas.getDataAtual());
        ocorrenciaLiberacaoDto.setDataFim(UtilDatas.getDataAtual());
        ocorrenciaLiberacaoDto.setRegistradopor(usuarioDto.getLogin());
        ocorrenciaLiberacaoDto.setDadosLiberacao(new Gson().toJson(this.getRequisicaoLiberacaoDto()));
        ocorrenciaLiberacaoDto.setOrigem(br.com.centralit.citcorpore.util.Enumerados.OrigemOcorrencia.OUTROS.getSigla().toString());
        ocorrenciaLiberacaoDto.setCategoria(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Reabertura.getSigla());
        ocorrenciaLiberacaoDao.create(ocorrenciaLiberacaoDto);

        this.inicia();
    }

    @Override
    public void suspende(final String loginUsuario) throws Exception {
        final RequisicaoLiberacaoDTO requisicaoLiberacaoDto = this.getRequisicaoLiberacaoDto();
        if (requisicaoLiberacaoDto == null) {
            throw new LogicException(this.i18n_Message("requisicaoLiberacao.naoEncontrada"));
        }

        if (!requisicaoLiberacaoDto.emAtendimento() && !requisicaoLiberacaoDto.naoResolvida()) {
            throw new LogicException(this.i18n_Message("requisicaoLiberacao.suspensao"));
        }

        final Timestamp tsAtual = UtilDatas.getDataHoraAtual();
        Timestamp tsInicial = requisicaoLiberacaoDto.getDataHoraInicio();
        if (requisicaoLiberacaoDto.getDataHoraReativacao() != null) {
            tsInicial = requisicaoLiberacaoDto.getDataHoraReativacao();
        }
        final TipoLiberacaoDTO tipoLiberacaoDto = this.recuperaTipoLiberacao();
        if (tipoLiberacaoDto.getIdCalendario() != null) {
            requisicaoLiberacaoDto.setIdCalendario(tipoLiberacaoDto.getIdCalendario());
        }
        CalculoJornadaDTO calculoDto = new CalculoJornadaDTO(requisicaoLiberacaoDto.getIdCalendario(), tsInicial);
        calculoDto = new CalendarioServiceEjb().calculaPrazoDecorrido(calculoDto, tsAtual, null);

        final RequisicaoLiberacaoDao requisicaoDao = new RequisicaoLiberacaoDao();
        this.setTransacaoDao(requisicaoDao);

        requisicaoLiberacaoDto.setStatus(SituacaoRequisicaoLiberacao.Suspensa.name());
        if (requisicaoLiberacaoDto.getTempoDecorridoHH() == null) {
            requisicaoLiberacaoDto.setTempoDecorridoHH(0);
        }
        if (requisicaoLiberacaoDto.getTempoDecorridoMM() == null) {
            requisicaoLiberacaoDto.setTempoDecorridoMM(0);
        }
        requisicaoLiberacaoDto.setTempoDecorridoHH(new Integer(requisicaoLiberacaoDto.getTempoDecorridoHH().intValue()
                + calculoDto.getTempoDecorridoHH().intValue()));
        requisicaoLiberacaoDto.setTempoDecorridoMM(new Integer(requisicaoLiberacaoDto.getTempoDecorridoMM().intValue()
                + calculoDto.getTempoDecorridoMM().intValue()));
        requisicaoLiberacaoDto.setDataHoraSuspensao(tsAtual);
        requisicaoLiberacaoDto.setDataHoraReativacao(null);
        requisicaoDao.update(requisicaoLiberacaoDto);

        final OcorrenciaLiberacaoDao ocorrenciaLiberacaoDao = new OcorrenciaLiberacaoDao();
        this.setTransacaoDao(ocorrenciaLiberacaoDao);
        final OcorrenciaLiberacaoDTO ocorrenciaLiberacaoDto = new OcorrenciaLiberacaoDTO();
        ocorrenciaLiberacaoDto.setIdRequisicaoLiberacao(requisicaoLiberacaoDto.getIdRequisicaoLiberacao());
        ocorrenciaLiberacaoDto.setDataregistro(new Date(tsAtual.getTime()));
        ocorrenciaLiberacaoDto.setHoraregistro(UtilDatas.formatHoraFormatadaStr(tsAtual));
        ocorrenciaLiberacaoDto.setTempoGasto(0);
        ocorrenciaLiberacaoDto.setDescricao(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Suspensao.getDescricao());
        ocorrenciaLiberacaoDto.setDataInicio(UtilDatas.getDataAtual());
        ocorrenciaLiberacaoDto.setDataFim(UtilDatas.getDataAtual());
        ocorrenciaLiberacaoDto.setRegistradopor(loginUsuario);
        ocorrenciaLiberacaoDto.setDadosLiberacao(new Gson().toJson(this.getRequisicaoLiberacaoDto()));
        ocorrenciaLiberacaoDto.setIdJustificativa(requisicaoLiberacaoDto.getIdJustificativa());
        ocorrenciaLiberacaoDto.setComplementoJustificativa(requisicaoLiberacaoDto.getComplementoJustificativa());
        ocorrenciaLiberacaoDto.setOrigem(br.com.centralit.citcorpore.util.Enumerados.OrigemOcorrencia.OUTROS.getSigla().toString());
        ocorrenciaLiberacaoDto.setCategoria(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Suspensao.getSigla());
        ocorrenciaLiberacaoDao.create(ocorrenciaLiberacaoDto);
        if (this.getRequisicaoLiberacaoDto().getEnviaEmailAcoes() != null && this.getRequisicaoLiberacaoDto().getEnviaEmailAcoes().equalsIgnoreCase("S")) {
            this.enviaEmail(tipoLiberacaoDto.getIdModeloEmailAcoes());
        }
    }

    @Override
    public void reativa(final String loginUsuario) throws Exception {
        final RequisicaoLiberacaoDTO requisicaoLiberacaoDto = this.getRequisicaoLiberacaoDto();
        if (requisicaoLiberacaoDto == null) {
            throw new LogicException(this.i18n_Message("requisicaoLiberacao.naoEncontrada"));
        }

        if (!requisicaoLiberacaoDto.suspensa()) {
            throw new LogicException(this.i18n_Message("requisicaoLiberacao.reativacao"));
        }

        final Timestamp tsAtual = UtilDatas.getDataHoraAtual();

        if (requisicaoLiberacaoDto.getPrazoHH() != null && requisicaoLiberacaoDto.getPrazoMM() != null && requisicaoLiberacaoDto.getTempoDecorridoHH() != null
                && requisicaoLiberacaoDto.getTempoDecorridoMM() != null) {
            double prazo = requisicaoLiberacaoDto.getPrazoHH() + new Double(requisicaoLiberacaoDto.getPrazoMM()).doubleValue() / 60;
            final double tempo = requisicaoLiberacaoDto.getTempoDecorridoHH() + new Double(requisicaoLiberacaoDto.getTempoDecorridoMM()).doubleValue() / 60;
            prazo = prazo - tempo;
            if (prazo > 0) {
                CalculoJornadaDTO calculoDto = new CalculoJornadaDTO(requisicaoLiberacaoDto.getIdCalendario(), tsAtual, Util.getHora(prazo),
                        Util.getMinuto(prazo));

                calculoDto = new CalendarioServiceEjb().calculaDataHoraFinal(calculoDto, false, null);
                requisicaoLiberacaoDto.setDataHoraTermino(calculoDto.getDataHoraFinal());
            }

        }

        final RequisicaoLiberacaoDao requisicaoDao = new RequisicaoLiberacaoDao();
        this.setTransacaoDao(requisicaoDao);

        requisicaoLiberacaoDto.setStatus(SituacaoRequisicaoLiberacao.EmExecucao.name());
        requisicaoLiberacaoDto.setDataHoraSuspensao(null);
        requisicaoLiberacaoDto.setDataHoraReativacao(tsAtual);
        requisicaoDao.update(requisicaoLiberacaoDto);

        final OcorrenciaLiberacaoDao ocorrenciaLiberacaoDao = new OcorrenciaLiberacaoDao();
        this.setTransacaoDao(ocorrenciaLiberacaoDao);
        final OcorrenciaLiberacaoDTO ocorrenciaLiberacaoDto = new OcorrenciaLiberacaoDTO();
        ocorrenciaLiberacaoDto.setIdRequisicaoLiberacao(requisicaoLiberacaoDto.getIdRequisicaoLiberacao());
        ocorrenciaLiberacaoDto.setDataregistro(new Date(tsAtual.getTime()));
        ocorrenciaLiberacaoDto.setHoraregistro(UtilDatas.formatHoraFormatadaStr(tsAtual));
        ocorrenciaLiberacaoDto.setTempoGasto(0);
        ocorrenciaLiberacaoDto.setDescricao(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Reativacao.getDescricao());
        ocorrenciaLiberacaoDto.setDataInicio(UtilDatas.getDataAtual());
        ocorrenciaLiberacaoDto.setDataFim(UtilDatas.getDataAtual());
        ocorrenciaLiberacaoDto.setRegistradopor(loginUsuario);
        ocorrenciaLiberacaoDto.setDadosLiberacao(new Gson().toJson(this.getRequisicaoLiberacaoDto()));
        ocorrenciaLiberacaoDto.setOrigem(br.com.centralit.citcorpore.util.Enumerados.OrigemOcorrencia.OUTROS.getSigla().toString());
        ocorrenciaLiberacaoDto.setCategoria(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Reativacao.getSigla());
        ocorrenciaLiberacaoDao.create(ocorrenciaLiberacaoDto);
        if (this.getRequisicaoLiberacaoDto().getEnviaEmailAcoes() != null && this.getRequisicaoLiberacaoDto().getEnviaEmailAcoes().equalsIgnoreCase("S")) {
            final TipoLiberacaoDTO tipoLiberacaoDto = this.recuperaTipoLiberacao();
            this.enviaEmail(tipoLiberacaoDto.getIdModeloEmailAcoes());
        }
    }

    private Integer getIdCalendario(final RequisicaoLiberacaoDTO requisicaoLiberacaoDto) throws Exception {
        Integer idCalendario = requisicaoLiberacaoDto.getIdCalendario();
        if (requisicaoLiberacaoDto.getIdCalendario() == null) {
            final TipoLiberacaoDTO tipoLiberacaoDto = this.recuperaTipoLiberacao();
            idCalendario = tipoLiberacaoDto.getIdCalendario();
        }
        return idCalendario;
    }

    public UsuarioDTO usuarioSolicitante(final RequisicaoLiberacaoDTO requisicaoLiberacaoDto) throws Exception {
        final UsuarioDao usuarioDao = new UsuarioDao();
        final UsuarioDTO usuario = usuarioDao.restoreByIdEmpregado(requisicaoLiberacaoDto.getIdSolicitante());

        return usuario;
    }

    public void calculaTempoAtendimento(final RequisicaoLiberacaoDTO requisicaoLiberacaoDto) throws Exception {
        final Integer idCalendario = this.getIdCalendario(requisicaoLiberacaoDto);

        Timestamp tsAtual = UtilDatas.getDataHoraAtual();
        if (requisicaoLiberacaoDto.getStatus().equalsIgnoreCase(SituacaoRequisicaoLiberacao.Concluida.name())) {
            tsAtual = requisicaoLiberacaoDto.getDataHoraConclusao();
        }

        Timestamp tsInicial = requisicaoLiberacaoDto.getDataHoraInicio();
        if (requisicaoLiberacaoDto.getDataHoraReativacao() != null) {
            tsInicial = requisicaoLiberacaoDto.getDataHoraReativacao();
        }

        CalculoJornadaDTO calculoDto = new CalculoJornadaDTO(idCalendario, tsInicial);
        calculoDto = new CalendarioServiceEjb().calculaPrazoDecorrido(calculoDto, tsAtual, null);

        if (requisicaoLiberacaoDto.getTempoDecorridoHH() == null) {
            requisicaoLiberacaoDto.setTempoDecorridoHH(0);
        }
        if (requisicaoLiberacaoDto.getTempoDecorridoMM() == null) {
            requisicaoLiberacaoDto.setTempoDecorridoMM(0);
        }

        requisicaoLiberacaoDto.setTempoAtendimentoHH(new Integer(requisicaoLiberacaoDto.getTempoDecorridoHH().intValue()
                + calculoDto.getTempoDecorridoHH().intValue()));
        requisicaoLiberacaoDto.setTempoAtendimentoMM(new Integer(requisicaoLiberacaoDto.getTempoDecorridoMM().intValue()
                + calculoDto.getTempoDecorridoMM().intValue()));
    }

    public void calculaTempoCaptura(final RequisicaoLiberacaoDTO requisicaoLiberacaoDto) throws Exception {
        requisicaoLiberacaoDto.setTempoCapturaHH(0);
        requisicaoLiberacaoDto.setTempoCapturaMM(0);

        if (requisicaoLiberacaoDto.getDataHoraCaptura() == null) {
            return;
        }

        final Integer idCalendario = this.getIdCalendario(requisicaoLiberacaoDto);

        CalculoJornadaDTO calculoDto = new CalculoJornadaDTO(idCalendario, requisicaoLiberacaoDto.getDataHoraInicio());
        calculoDto = new CalendarioServiceEjb().calculaPrazoDecorrido(calculoDto, requisicaoLiberacaoDto.getDataHoraCaptura(), null);

        requisicaoLiberacaoDto.setTempoCapturaHH(calculoDto.getTempoDecorridoHH().intValue());
        requisicaoLiberacaoDto.setTempoCapturaMM(calculoDto.getTempoDecorridoMM().intValue());
    }

    public void calculaTempoAtraso(final RequisicaoLiberacaoDTO requisicaoLiberacaoDto) throws Exception {
        requisicaoLiberacaoDto.setTempoAtrasoHH(0);
        requisicaoLiberacaoDto.setTempoAtrasoMM(0);
        if (requisicaoLiberacaoDto.getDataHoraTermino() != null) {
            final Timestamp dataHoraLimite = requisicaoLiberacaoDto.getDataHoraTermino();
            Timestamp dataHoraComparacao = UtilDatas.getDataHoraAtual();
            if (requisicaoLiberacaoDto.encerrada()) {
                dataHoraComparacao = requisicaoLiberacaoDto.getDataHoraConclusao();
            }
            if (dataHoraComparacao.compareTo(dataHoraLimite) > 0) {
                final long atrasoSLA = UtilDatas.calculaDiferencaTempoEmMilisegundos(dataHoraComparacao, dataHoraLimite) / 1000;

                final String hora = Util.getHoraStr(new Double(atrasoSLA) / 3600);
                final int tam = hora.length();
                requisicaoLiberacaoDto.setTempoAtrasoHH(new Integer(hora.substring(0, tam - 2)));
                requisicaoLiberacaoDto.setTempoAtrasoMM(new Integer(hora.substring(tam - 2, tam)));
            }
        }
    }

    @Override
    public void executaEvento(final EventoFluxoDTO eventoFluxoDto) throws Exception {
        final ExecucaoLiberacaoDao execucaoLiberacaoDao = new ExecucaoLiberacaoDao();
        final ExecucaoLiberacaoDTO execucaoLiberacaoDto = execucaoLiberacaoDao.findByIdInstanciaFluxo(eventoFluxoDto.getIdInstancia());
        if (execucaoLiberacaoDto == null) {
            System.out.println("Execução liberação do evento " + eventoFluxoDto.getIdItemTrabalho() + " não encontrada");
            throw new LogicException(this.i18n_Message("requisicaoLiberacao.evento"));
        }
        final RequisicaoLiberacaoDao requisicaoLiberacaoDao = new RequisicaoLiberacaoDao();
        RequisicaoLiberacaoDTO requisicaoLiberacaoDto = new RequisicaoLiberacaoDTO();
        requisicaoLiberacaoDto.setIdRequisicaoLiberacao(execucaoLiberacaoDto.getIdRequisicaoLiberacao());
        requisicaoLiberacaoDto = (RequisicaoLiberacaoDTO) requisicaoLiberacaoDao.restore(requisicaoLiberacaoDto);
        if (requisicaoLiberacaoDto == null) {
            System.out.println("Execução liberação do evento " + eventoFluxoDto.getIdItemTrabalho() + " não encontrada");
            throw new LogicException(this.i18n_Message("requisicaoLiberacao.evento"));
        }
        final TransactionControler tc = new TransactionControlerImpl(execucaoLiberacaoDao.getAliasDB());
        this.setTransacao(tc);
        this.setObjetoNegocioDto(requisicaoLiberacaoDto);
        final InstanciaFluxo instanciaFluxo = new InstanciaFluxo(this, eventoFluxoDto.getIdInstancia());

        final HashMap<String, Object> map = new HashMap<>();
        this.mapObjetoNegocio(instanciaFluxo.getObjetos(map));
        instanciaFluxo.executaEvento(eventoFluxoDto.getIdItemTrabalho(), map);

    }

    @Override
    public void validaEncerramento() throws Exception {

    }

    @Override
    public void enviaEmail(final String identificador, final String[] destinatarios) throws Exception {

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
            throw new LogicException(this.i18n_Message("requisicaoLiberacao.remetenteNaoDefinido"));
        }

        final RequisicaoLiberacaoDTO requisicaoRequisicaoLiberacaoDTO = new RequisicaoLiberacaoServiceEjb().restoreAll(this.getRequisicaoLiberacaoDto()
                .getIdRequisicaoLiberacao(), this.getTransacao());
        if (requisicaoRequisicaoLiberacaoDTO == null) {
            return;
        }
        requisicaoRequisicaoLiberacaoDTO.setNomeTarefa(this.getRequisicaoLiberacaoDto().getNomeTarefa());

        try {
            for (final String email : emails) {
                final int posArroba = email.indexOf("@");
                if (posArroba > 0 && email.substring(posArroba).contains(".")) {
                    try {
                        mensagem = new MensagemEmail(idModeloEmail, new BaseEntity[] {requisicaoRequisicaoLiberacaoDTO});

                        mensagem.envia(email, remetente, remetente);
                    } catch (final Exception e) {
                        // faz nada
                    }
                }
            }
        } catch (final Exception e) {}
    }

    /**
     * Notifica proprietario da Requisição mudança.
     *
     * @param idModeloEmail
     * @throws Exception
     * @author thays.araujo
     */
    public void enviaEmailProprietario(final Integer idModeloEmail) throws Exception {
        MensagemEmail mensagem = null;

        if (idModeloEmail == null) {
            return;
        }

        final EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);

        EmpregadoDTO empregadoDto = new EmpregadoDTO();

        empregadoDto.setIdEmpregado(this.getRequisicaoLiberacaoDto().getUsuarioDto().getIdEmpregado());

        empregadoDto = (EmpregadoDTO) empregadoService.restore(empregadoDto);

        String email = "";

        try {
            email = empregadoDto.getEmail();
        } catch (final Exception e) {
            return;
        }

        if (email == null || email.isEmpty() || email.equalsIgnoreCase("")) {
            return;
        }

        final String remetente = ParametroUtil.getValor(ParametroSistema.RemetenteNotificacoesSolicitacao, this.getTransacao(), null);
        if (remetente == null) {
            throw new LogicException(this.i18n_Message("requisicaoLiberacao.remetenteNaoDefinido"));
        }

        final RequisicaoLiberacaoDTO requisicaoRequisicaoLiberacaoDTO = new RequisicaoLiberacaoServiceEjb().restoreAll(this.getRequisicaoLiberacaoDto()
                .getIdRequisicaoLiberacao(), this.getTransacao());
        if (requisicaoRequisicaoLiberacaoDTO == null) {
            return;
        }
        requisicaoRequisicaoLiberacaoDTO.setNomeTarefa(this.getRequisicaoLiberacaoDto().getNomeTarefa());

        try {
            final int posArroba = email.indexOf("@");
            if (posArroba > 0 && email.substring(posArroba).contains(".")) {
                try {
                    mensagem = new MensagemEmail(idModeloEmail, new BaseEntity[] {requisicaoRequisicaoLiberacaoDTO});
                    mensagem.envia(email, remetente, remetente);
                } catch (final Exception e) {
                    // faz nada
                }
            }
        } catch (final Exception e) {}
    }

    @Override
    public void verificaSLA(final ItemTrabalho itemTrabalho) throws Exception {

    }

    public void finalizaIC(final RequisicaoLiberacaoDTO liberacao) throws ServiceException, Exception {
        final RequisicaoLiberacaoItemConfiguracaoService liberacaoICService = (RequisicaoLiberacaoItemConfiguracaoService) ServiceLocator.getInstance()
                .getService(RequisicaoLiberacaoItemConfiguracaoService.class, null);
        final ItemConfiguracaoService itemService = (ItemConfiguracaoService) ServiceLocator.getInstance().getService(ItemConfiguracaoService.class, null);
        final List<RequisicaoLiberacaoItemConfiguracaoDTO> lista = liberacaoICService.listByIdRequisicaoLiberacao(liberacao.getIdRequisicaoLiberacao());

        if (lista != null && lista.size() > 0) {
            for (final RequisicaoLiberacaoItemConfiguracaoDTO req : lista) {
                itemService.atualizaParaGrupoProducao(req.getIdItemConfiguracao());
                this.atualizaHistoricoIC(req.getIdItemConfiguracao(), liberacao);
            }
        }
    }

    public void atualizaHistoricoIC(final Integer idItemConfiguracao, final RequisicaoLiberacaoDTO liberacao)
            throws br.com.citframework.excecao.ServiceException, Exception {
        final ItemConfiguracaoService itemService = (ItemConfiguracaoService) ServiceLocator.getInstance().getService(ItemConfiguracaoService.class, null);
        ItemConfiguracaoDTO item = new ItemConfiguracaoDTO();
        item.setIdItemConfiguracao(idItemConfiguracao);
        item = (ItemConfiguracaoDTO) itemService.restore(item);

        try {
            itemService.createHistoricoItemComOrigem(item, liberacao, "L");
            itemService.atualizaStatus(item.getIdItemConfiguracao(), StatusIC.VALIDAR.getItem());
        } catch (final Exception e) {

        }
    }

}
