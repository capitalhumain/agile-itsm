package br.com.centralit.citcorpore.bpm.negocio;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.bpm.dto.AtribuicaoFluxoDTO;
import br.com.centralit.bpm.dto.EventoFluxoDTO;
import br.com.centralit.bpm.dto.FluxoDTO;
import br.com.centralit.bpm.dto.ObjetoNegocioFluxoDTO;
import br.com.centralit.bpm.dto.TarefaFluxoDTO;
import br.com.centralit.bpm.dto.TipoFluxoDTO;
import br.com.centralit.bpm.integracao.AtribuicaoFluxoDao;
import br.com.centralit.bpm.integracao.FluxoDao;
import br.com.centralit.bpm.integracao.TipoFluxoDao;
import br.com.centralit.bpm.negocio.ExecucaoFluxo;
import br.com.centralit.bpm.negocio.InstanciaFluxo;
import br.com.centralit.bpm.negocio.ItemTrabalho;
import br.com.centralit.bpm.util.Enumerados;
import br.com.centralit.bpm.util.Enumerados.TipoAtribuicao;
import br.com.centralit.citcorpore.bean.CalculoJornadaDTO;
import br.com.centralit.citcorpore.bean.EmpregadoDTO;
import br.com.centralit.citcorpore.bean.ExecucaoMudancaDTO;
import br.com.centralit.citcorpore.bean.GrupoDTO;
import br.com.centralit.citcorpore.bean.ModeloEmailDTO;
import br.com.centralit.citcorpore.bean.OcorrenciaMudancaDTO;
import br.com.centralit.citcorpore.bean.ReaberturaMudancaDTO;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaDTO;
import br.com.centralit.citcorpore.bean.TipoMudancaDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.integracao.ExecucaoMudancaDao;
import br.com.centralit.citcorpore.integracao.GrupoDao;
import br.com.centralit.citcorpore.integracao.ModeloEmailDao;
import br.com.centralit.citcorpore.integracao.OcorrenciaMudancaDao;
import br.com.centralit.citcorpore.integracao.ReaberturaMudancaDao;
import br.com.centralit.citcorpore.integracao.RequisicaoMudancaDao;
import br.com.centralit.citcorpore.integracao.TipoMudancaDAO;
import br.com.centralit.citcorpore.integracao.UsuarioDao;
import br.com.centralit.citcorpore.mail.MensagemEmail;
import br.com.centralit.citcorpore.negocio.CalendarioServiceEjb;
import br.com.centralit.citcorpore.negocio.EmpregadoService;
import br.com.centralit.citcorpore.negocio.GrupoEmailService;
import br.com.centralit.citcorpore.negocio.GrupoService;
import br.com.centralit.citcorpore.negocio.RequisicaoMudancaServiceEjb;
import br.com.centralit.citcorpore.util.Enumerados.FaseRequisicaoMudanca;
import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoRequisicaoMudanca;
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
public class ExecucaoMudanca extends ExecucaoFluxo {

    private UsuarioDTO usuarioDto = null;

    public ExecucaoMudanca(final TransactionControler tc) {
        super(tc);
    }

    public ExecucaoMudanca() {
        super();
    }

    public ExecucaoMudanca(final RequisicaoMudancaDTO requisicaoMudancaDto, final TransactionControler tc, final Usuario usuario) {
        super(requisicaoMudancaDto, tc, usuario);
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
        if (this.getRequisicaoMudancaDto().getIdTipoMudanca() == null) {
            throw new LogicException(this.i18n_Message("requisicaoMudanca.tipoNaoDefinido"));
        }

        InstanciaFluxo result = null;
        final TipoMudancaDTO tipoMudancaDto = this.recuperaTipoMudanca();
        if (tipoMudancaDto.getIdTipoFluxo() != null) {
            result = this.inicia(new FluxoDao().findByTipoFluxo(tipoMudancaDto.getIdTipoFluxo()), null);
        } else {
            final String fluxoPadrao = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.FLUXO_PADRAO_MUDANCAS, null);
            if (fluxoPadrao == null) {
                throw new LogicException(this.i18n_Message("citcorpore.comum.fluxoNaoParametrizado"));
            }
            result = this.inicia(fluxoPadrao, null);
        }

        try {
            final String enviarNotificacao = ParametroUtil.getValor(ParametroSistema.NOTIFICAR_GRUPO_RECEPCAO_SOLICITACAO, this.getTransacao(), "N");
            final String IdModeloEmailGrupoDestinoREquisicaoMudanca = ParametroUtil.getValorParametroCitSmartHashMap(
                    ParametroSistema.ID_MODELO_EMAIL_GRUPO_DESTINO_REQUISICAOMUDANCA, "30");
            if (enviarNotificacao.equalsIgnoreCase("S") && this.getRequisicaoMudancaDto().escalada()) {
                this.enviaEmailGrupo(Integer.parseInt(IdModeloEmailGrupoDestinoREquisicaoMudanca.trim()), tipoMudancaDto.getIdGrupoExecutor());
            }
        } catch (final NumberFormatException e) {
            System.out.println("Não há modelo de e-mail setado nos parâmetros.");
        }

        try {

            final String IdModeloEmailGrupoComiteREquisicaoMudanca = ParametroUtil.getValorParametroCitSmartHashMap(
                    ParametroSistema.ID_MODELO_EMAIL_GRUPO_COMITE_REQUISICAOMUDANCA, "29");
            if (this.getRequisicaoMudancaDto().getEnviaEmailGrupoComite() != null
                    && this.getRequisicaoMudancaDto().getEnviaEmailGrupoComite().equalsIgnoreCase("S")) {
                this.enviaEmailGrupo(Integer.parseInt(IdModeloEmailGrupoComiteREquisicaoMudanca), this.getRequisicaoMudancaDto().getIdGrupoComite());
                this.enviaEmailProprietario(Integer.parseInt(IdModeloEmailGrupoComiteREquisicaoMudanca.trim()));
            }
        } catch (final NumberFormatException e) {
            System.out.println("Não há modelo de e-mail setado nos parâmetros.");
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

        final ExecucaoMudancaDTO execucaoDto = new ExecucaoMudancaDTO();
        execucaoDto.setIdRequisicaoMudanca(this.getRequisicaoMudancaDto().getIdRequisicaoMudanca());
        execucaoDto.setIdFluxo(instanciaFluxo.getIdFluxo());
        execucaoDto.setIdInstanciaFluxo(instanciaFluxo.getIdInstancia());
        Integer seqReabertura = 0;
        if (this.getRequisicaoMudancaDto().getSeqReabertura() != null && this.getRequisicaoMudancaDto().getSeqReabertura().intValue() > 0) {
            seqReabertura = this.getRequisicaoMudancaDto().getSeqReabertura();
        }
        if (seqReabertura.intValue() > 0) {
            execucaoDto.setSeqReabertura(this.getRequisicaoMudancaDto().getSeqReabertura());
        }

        final ExecucaoMudancaDao execucaoDao = new ExecucaoMudancaDao();
        this.setTransacaoDao(execucaoDao);
        execucaoFluxoDto = (ExecucaoMudancaDTO) execucaoDao.create(execucaoDto);

        if (seqReabertura.intValue() == 0 && this.getRequisicaoMudancaDto().getEnviaEmailCriacao() != null
                && this.getRequisicaoMudancaDto().getEnviaEmailCriacao().equalsIgnoreCase("S")) {
            final TipoMudancaDTO tipoMudancaDto = this.recuperaTipoMudanca();
            this.enviaEmail(tipoMudancaDto.getIdModeloEmailCriacao());
        }
        return instanciaFluxo;
    }

    public void finalizaItemRelacionadoMudanca(final RequisicaoMudancaDTO requisicaoMudancaDTO) throws ServiceException, Exception {
        new RequisicaoMudancaServiceEjb().fechaRelacionamentoMudanca(super.getTransacao(), requisicaoMudancaDTO);
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

        final ExecucaoMudancaDao execucaoMudancaDao = new ExecucaoMudancaDao();
        this.setTransacaoDao(execucaoMudancaDao);
        final ExecucaoMudancaDTO execucaoMudancaDto = execucaoMudancaDao.findByIdInstanciaFluxo(tarefaFluxoDto.getIdInstancia());
        if (execucaoMudancaDto == null) {
            return;
        }

        this.recuperaFluxo(execucaoMudancaDto.getIdFluxo());

        this.objetoNegocioDto = objetoNegocioDto;
        usuarioDto = new UsuarioDao().restoreByLogin(loginUsuario);

        final InstanciaFluxo instanciaFluxo = new InstanciaFluxo(this, tarefaFluxoDto.getIdInstancia());
        this.mapObjetoNegocio(instanciaFluxo.getObjetos(map));
        if (acao.equals(Enumerados.ACAO_INICIAR)) {
            instanciaFluxo.iniciaItemTrabalho(loginUsuario, tarefaFluxoDto.getIdItemTrabalho(), map);
        } else if (acao.equals(Enumerados.ACAO_EXECUTAR)) {
            tarefaFluxoDto = this.recuperaTarefa(idItemTrabalho);
            final OcorrenciaMudancaDao ocorrenciaMudancaDao = new OcorrenciaMudancaDao();
            this.setTransacaoDao(ocorrenciaMudancaDao);
            final OcorrenciaMudancaDTO ocorrenciaMudancaDto = new OcorrenciaMudancaDTO();
            ocorrenciaMudancaDto.setIdRequisicaoMudanca(this.getRequisicaoMudancaDto().getIdRequisicaoMudanca());
            ocorrenciaMudancaDto.setDataregistro(UtilDatas.getDataAtual());
            ocorrenciaMudancaDto.setHoraregistro(UtilDatas.formatHoraFormatadaStr(UtilDatas.getHoraAtual()));
            Long tempo = new Long(0);
            if (tarefaFluxoDto.getDataHoraFinalizacao() != null) {
                tempo = (tarefaFluxoDto.getDataHoraFinalizacao().getTime() - tarefaFluxoDto.getDataHoraCriacao().getTime()) / 1000 / 60;
            }
            ocorrenciaMudancaDto.setTempoGasto(tempo.intValue());
            ocorrenciaMudancaDto.setDescricao(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Execucao.getDescricao());
            ocorrenciaMudancaDto.setDataInicio(UtilDatas.getDataAtual());
            ocorrenciaMudancaDto.setDataFim(UtilDatas.getDataAtual());
            ocorrenciaMudancaDto.setInformacoesContato("não se aplica");
            ocorrenciaMudancaDto.setRegistradopor(loginUsuario);
            ocorrenciaMudancaDto.setDadosMudanca(new Gson().toJson(this.getRequisicaoMudancaDto()));
            ocorrenciaMudancaDto.setOcorrencia("Execução da tarefa \"" + tarefaFluxoDto.getElementoFluxoDto().getDocumentacao() + "\"");
            ocorrenciaMudancaDto.setOrigem(br.com.centralit.citcorpore.util.Enumerados.OrigemOcorrencia.OUTROS.getSigla().toString());
            ocorrenciaMudancaDto.setCategoria(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Execucao.getSigla());
            ocorrenciaMudancaDto.setIdItemTrabalho(idItemTrabalho);
            ocorrenciaMudancaDao.create(ocorrenciaMudancaDto);

            instanciaFluxo.executaItemTrabalho(loginUsuario, tarefaFluxoDto.getIdItemTrabalho(), map);

            if (this.getRequisicaoMudancaDto().getFase() != null && this.getRequisicaoMudancaDto().getFase().trim().length() > 0) {
                final SituacaoRequisicaoMudanca novaSituacao = FaseRequisicaoMudanca.valueOf(this.getRequisicaoMudancaDto().getFase()).getSituacao();
                if (novaSituacao != null) {
                    final RequisicaoMudancaDao requisicaoDao = new RequisicaoMudancaDao();
                    this.setTransacaoDao(requisicaoDao);
                    this.getRequisicaoMudancaDto().setStatus(novaSituacao.name());
                    requisicaoDao.updateNotNull(this.getRequisicaoMudancaDto());
                }
            }
        }

        if (this.getRequisicaoMudancaDto().getEnviaEmailAcoes() != null && this.getRequisicaoMudancaDto().getEnviaEmailAcoes().equalsIgnoreCase("S")) {
            this.getRequisicaoMudancaDto().setNomeTarefa(tarefaFluxoDto.getElementoFluxoDto().getDocumentacao());
            final TipoMudancaDTO tipoMudancaDto = this.recuperaTipoMudanca();
            this.enviaEmail(tipoMudancaDto.getIdModeloEmailAcoes());
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

        final OcorrenciaMudancaDao ocorrenciaMudancaDao = new OcorrenciaMudancaDao();
        this.setTransacaoDao(ocorrenciaMudancaDao);
        final OcorrenciaMudancaDTO ocorrenciaMudancaDto = new OcorrenciaMudancaDTO();
        ocorrenciaMudancaDto.setIdRequisicaoMudanca(this.getRequisicaoMudancaDto().getIdRequisicaoMudanca());
        ocorrenciaMudancaDto.setDataregistro(UtilDatas.getDataAtual());
        ocorrenciaMudancaDto.setHoraregistro(UtilDatas.formatHoraFormatadaStr(UtilDatas.getHoraAtual()));
        ocorrenciaMudancaDto.setTempoGasto(0);
        ocorrenciaMudancaDto.setDescricao(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Compartilhamento.getDescricao());
        ocorrenciaMudancaDto.setDataInicio(UtilDatas.getDataAtual());
        ocorrenciaMudancaDto.setDataFim(UtilDatas.getDataAtual());
        ocorrenciaMudancaDto.setInformacoesContato("não se aplica");
        ocorrenciaMudancaDto.setRegistradopor(loginUsuario);
        ocorrenciaMudancaDto.setDadosMudanca(new Gson().toJson(this.getRequisicaoMudancaDto()));
        String ocorr = "Compartilhamento da tarefa \"" + tarefaFluxoDto.getElementoFluxoDto().getDocumentacao() + "\"";
        if (usuarioDestino != null) {
            ocorr += " com o usuário " + usuarioDestino;
        }
        if (grupoDestino != null) {
            ocorr += " com o grupo " + grupoDestino;
        }
        ocorrenciaMudancaDto.setOcorrencia(ocorr);
        ocorrenciaMudancaDto.setOrigem(br.com.centralit.citcorpore.util.Enumerados.OrigemOcorrencia.OUTROS.getSigla().toString());
        ocorrenciaMudancaDto.setCategoria(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Compartilhamento.getSigla());
        ocorrenciaMudancaDto.setIdItemTrabalho(idItemTrabalho);
        ocorrenciaMudancaDao.create(ocorrenciaMudancaDto);
    }

    @Override
    public void direcionaAtendimento(final String loginUsuario, final ObjetoNegocioFluxoDTO objetoNegocioDto, final String grupoAtendimento) throws Exception {
        if (this.getRequisicaoMudancaDto() == null) {
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
        usuarioRespDto.setIdUsuario(this.getRequisicaoMudancaDto().getIdResponsavel());
        usuarioRespDto = (UsuarioDTO) new UsuarioDao().restore(usuarioRespDto);

        this.objetoNegocioDto = objetoNegocioDto;

        final Collection<ExecucaoMudancaDTO> colExecucao = new ExecucaoMudancaDao().listByIdRequisicaoMudanca(this.getRequisicaoMudancaDto()
                .getIdRequisicaoMudanca());
        if (colExecucao != null) {
            final AtribuicaoFluxoDao atribuicaoFluxoDao = new AtribuicaoFluxoDao();
            this.setTransacaoDao(atribuicaoFluxoDao);
            final OcorrenciaMudancaDao ocorrenciaMudancaDao = new OcorrenciaMudancaDao();
            this.setTransacaoDao(ocorrenciaMudancaDao);
            for (final ExecucaoMudancaDTO execucaoSolicitacaoDto : colExecucao) {
                final Collection<AtribuicaoFluxoDTO> colAtribuicao = atribuicaoFluxoDao.findByDisponiveisByIdInstancia(execucaoSolicitacaoDto
                        .getIdInstanciaFluxo());
                if (colAtribuicao == null) {
                    continue;
                }

                for (final AtribuicaoFluxoDTO atribuicaoFluxoDto : colAtribuicao) {
                    if (!atribuicaoFluxoDto.getTipo().equals(TipoAtribuicao.Automatica.name())) {
                        continue;
                    }

                    if (atribuicaoFluxoDto.getIdGrupo() != null && atribuicaoFluxoDto.getIdGrupo().intValue() == grupoAtendimentoDto.getIdGrupo().intValue()) {
                        continue;
                    }

                    if (atribuicaoFluxoDto.getIdUsuario() != null && atribuicaoFluxoDto.getIdUsuario().intValue() == usuarioRespDto.getIdUsuario().intValue()) {
                        this.delega(loginUsuario, objetoNegocioDto, atribuicaoFluxoDto.getIdItemTrabalho(), null, grupoAtendimento);
                    } else {
                        atribuicaoFluxoDto.setIdUsuario(null);
                        atribuicaoFluxoDto.setIdGrupo(grupoAtendimentoDto.getIdGrupo());
                        atribuicaoFluxoDao.update(atribuicaoFluxoDto);
                    }

                    final TarefaFluxoDTO tarefaFluxoDto = this.recuperaTarefa(atribuicaoFluxoDto.getIdItemTrabalho());
                    final OcorrenciaMudancaDTO ocorrenciaMudancaDto = new OcorrenciaMudancaDTO();
                    ocorrenciaMudancaDto.setIdRequisicaoMudanca(this.getRequisicaoMudancaDto().getIdRequisicaoMudanca());
                    ocorrenciaMudancaDto.setDataregistro(UtilDatas.getDataAtual());
                    ocorrenciaMudancaDto.setHoraregistro(UtilDatas.formatHoraFormatadaStr(UtilDatas.getHoraAtual()));
                    ocorrenciaMudancaDto.setTempoGasto(0);
                    ocorrenciaMudancaDto.setDescricao(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Direcionamento.getDescricao());
                    ocorrenciaMudancaDto.setDataInicio(UtilDatas.getDataAtual());
                    ocorrenciaMudancaDto.setDataFim(UtilDatas.getDataAtual());
                    ocorrenciaMudancaDto.setInformacoesContato("não se aplica");
                    ocorrenciaMudancaDto.setRegistradopor(loginUsuario);
                    ocorrenciaMudancaDto.setDadosMudanca(new Gson().toJson(this.getRequisicaoMudancaDto()));
                    String ocorr = "Direcionamento da tarefa \"" + tarefaFluxoDto.getElementoFluxoDto().getDocumentacao() + "\"";
                    ocorr += " para o grupo " + grupoAtendimento;
                    ocorrenciaMudancaDto.setOcorrencia(ocorr);
                    ocorrenciaMudancaDto.setOrigem(br.com.centralit.citcorpore.util.Enumerados.OrigemOcorrencia.OUTROS.getSigla().toString());
                    ocorrenciaMudancaDto.setCategoria(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Direcionamento.getSigla());
                    ocorrenciaMudancaDto.setIdItemTrabalho(atribuicaoFluxoDto.getIdItemTrabalho());
                    ocorrenciaMudancaDao.create(ocorrenciaMudancaDto);
                }
            }
        }
    }

    @Override
    public void mapObjetoNegocio(final Map<String, Object> map) throws Exception {
        final RequisicaoMudancaDTO requisicaoMudancaDto = (RequisicaoMudancaDTO) objetoNegocioDto;
        final RequisicaoMudancaDTO requisicaoAuxDto = new RequisicaoMudancaServiceEjb().restoreAll(requisicaoMudancaDto.getIdRequisicaoMudanca(),
                this.getTransacao());
        requisicaoMudancaDto.setNomeGrupoAtual(requisicaoAuxDto.getNomeGrupoAtual());
        requisicaoMudancaDto.setNomeGrupoNivel1(requisicaoAuxDto.getNomeGrupoNivel1());

        this.adicionaObjeto("requisicaoMudanca", requisicaoMudancaDto, map);
        if (usuarioDto != null) {
            this.adicionaObjeto("usuario", usuarioDto, map);
        } else if (requisicaoMudancaDto.getUsuarioDto() != null) {
            this.adicionaObjeto("usuario", requisicaoMudancaDto.getUsuarioDto(), map);
        }

        this.adicionaObjeto("execucaoFluxo", this, map);
        this.adicionaObjeto("requisicaoMudancaService", new RequisicaoMudancaServiceEjb(), map);
    }

    public RequisicaoMudancaDTO getRequisicaoMudancaDto() {
        return (RequisicaoMudancaDTO) objetoNegocioDto;
    }

    @Override
    public List<TarefaFluxoDTO> recuperaTarefas(final String loginUsuario) throws Exception {
        List<TarefaFluxoDTO> result = null;
        final List<TarefaFluxoDTO> listTarefas = super.recuperaTarefas(loginUsuario);
        if (listTarefas != null) {
            result = new ArrayList<>();
            final RequisicaoMudancaServiceEjb requisicaoService = new RequisicaoMudancaServiceEjb();
            final ExecucaoMudancaDao execucaoMudancaDao = new ExecucaoMudancaDao();
            for (final TarefaFluxoDTO tarefaDto : listTarefas) {
                final ExecucaoMudancaDTO execucaoMudancaDto = execucaoMudancaDao.findByIdInstanciaFluxo(tarefaDto.getIdInstancia());
                if (execucaoMudancaDto != null && execucaoMudancaDto.getIdRequisicaoMudanca() != null) {
                    final RequisicaoMudancaDTO requisicaoMudancaDto = requisicaoService.restoreAll(execucaoMudancaDto.getIdRequisicaoMudanca(), null);
                    if (requisicaoMudancaDto != null) {
                        tarefaDto.setRequisicaoMudancaDto(requisicaoMudancaDto);
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

        final RequisicaoMudancaDTO requisicaoAuxDto = new RequisicaoMudancaServiceEjb().restoreAll(this.getRequisicaoMudancaDto().getIdRequisicaoMudanca(),
                this.getTransacao());
        requisicaoAuxDto.setNomeContato(this.getRequisicaoMudancaDto().getNomeContato());
        if (requisicaoAuxDto.getEmailSolicitante() == null) {
            return;
        }

        final String remetente = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.RemetenteNotificacoesSolicitacao, null);
        if (remetente == null) {
            throw new LogicException(this.i18n_Message("citcorpore.comum.notficacaoEmailParametrizado"));
        }

        requisicaoAuxDto.setNomeTarefa(this.getRequisicaoMudancaDto().getNomeTarefa());

        final MensagemEmail mensagem = new MensagemEmail(idModeloEmail, new BaseEntity[] {requisicaoAuxDto});
        try {
            mensagem.envia(requisicaoAuxDto.getEmailSolicitante(), remetente, remetente);
        } catch (final Exception e) {}
    }

    private TipoMudancaDTO recuperaTipoMudanca() throws Exception {
        final TipoMudancaDAO tipoMudancaDao = new TipoMudancaDAO();
        this.setTransacaoDao(tipoMudancaDao);
        TipoMudancaDTO tipoMudancaDto = new TipoMudancaDTO();
        if (this.getRequisicaoMudancaDto().getIdTipoMudanca() != null) {
            tipoMudancaDto.setIdTipoMudanca(this.getRequisicaoMudancaDto().getIdTipoMudanca());
            tipoMudancaDto = (TipoMudancaDTO) tipoMudancaDao.restore(tipoMudancaDto);
        }

        if (tipoMudancaDto == null) {

            throw new LogicException(this.i18n_Message("requisicaoMudanca.categoriaMudancaNaoLocalizada"));
        }

        return tipoMudancaDto;
    }

    @Override
    public void encerra() throws Exception {
        final RequisicaoMudancaDTO requisicaoMudancaDto = this.getRequisicaoMudancaDto();
        if (requisicaoMudancaDto == null) {
            throw new LogicException(this.i18n_Message("requisicaoMudanca.naoEncontrada"));
        }

        if (requisicaoMudancaDto.encerrada()) {
            return;
        }

        // if (!requisicaoMudancaDto.atendida() && !requisicaoMudancaDto.reclassificada())
        // throw new Exception("Solicitação de serviço não permite encerramento");

        final Collection<ExecucaoMudancaDTO> colExecucao = new ExecucaoMudancaDao().listByIdRequisicaoMudanca(this.getRequisicaoMudancaDto()
                .getIdRequisicaoMudanca());
        if (colExecucao != null) {
            for (final ExecucaoMudancaDTO execucaoDto : colExecucao) {
                final InstanciaFluxo instanciaFluxo = new InstanciaFluxo(this, execucaoDto.getIdInstanciaFluxo());
                instanciaFluxo.encerra();
            }
        }
        if (!requisicaoMudancaDto.getStatus().equalsIgnoreCase(SituacaoRequisicaoMudanca.Cancelada.name())
                && !requisicaoMudancaDto.getStatus().equalsIgnoreCase(SituacaoRequisicaoMudanca.Rejeitada.name())) {
            requisicaoMudancaDto.setStatus(SituacaoRequisicaoMudanca.Concluida.name());
        }
        requisicaoMudancaDto.setDataHoraConclusao(UtilDatas.getDataHoraAtual());
        // calculaTempoCaptura(requisicaoMudancaDto);
        // calculaTempoAtendimento(requisicaoMudancaDto);
        // calculaTempoAtraso(requisicaoMudancaDto);
        final RequisicaoMudancaDao requisicaoDao = new RequisicaoMudancaDao();
        this.setTransacaoDao(requisicaoDao);
        requisicaoDao.updateNotNull(requisicaoMudancaDto);

        final OcorrenciaMudancaDao ocorrenciaMudancaDao = new OcorrenciaMudancaDao();
        this.setTransacaoDao(ocorrenciaMudancaDao);
        final OcorrenciaMudancaDTO ocorrenciaMudancaDto = new OcorrenciaMudancaDTO();
        ocorrenciaMudancaDto.setIdRequisicaoMudanca(this.getRequisicaoMudancaDto().getIdRequisicaoMudanca());
        ocorrenciaMudancaDto.setDataregistro(UtilDatas.getDataAtual());
        ocorrenciaMudancaDto.setHoraregistro(UtilDatas.formatHoraFormatadaStr(UtilDatas.getHoraAtual()));
        ocorrenciaMudancaDto.setTempoGasto(0);
        ocorrenciaMudancaDto.setDescricao(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Encerramento.getDescricao());
        ocorrenciaMudancaDto.setDataInicio(UtilDatas.getDataAtual());
        ocorrenciaMudancaDto.setDataFim(UtilDatas.getDataAtual());
        ocorrenciaMudancaDto.setRegistradopor("Automático");
        ocorrenciaMudancaDto.setDadosMudanca(new Gson().toJson(this.getRequisicaoMudancaDto()));
        ocorrenciaMudancaDto.setOrigem(br.com.centralit.citcorpore.util.Enumerados.OrigemOcorrencia.OUTROS.getSigla().toString());
        ocorrenciaMudancaDto.setCategoria(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Encerramento.getSigla());
        ocorrenciaMudancaDao.create(ocorrenciaMudancaDto);

        if (this.getRequisicaoMudancaDto().getEnviaEmailFinalizacao() != null
                && this.getRequisicaoMudancaDto().getEnviaEmailFinalizacao().equalsIgnoreCase("S")) {
            final TipoMudancaDTO tipoMudancaDto = this.recuperaTipoMudanca();
            this.enviaEmail(tipoMudancaDto.getIdModeloEmailFinalizacao());
        }
    }

    @Override
    public void reabre(final String loginUsuario) throws Exception {
        final RequisicaoMudancaDTO requisicaoMudancaDto = this.getRequisicaoMudancaDto();
        if (requisicaoMudancaDto == null) {
            throw new LogicException(this.i18n_Message("requisicaoMudanca.naoEncontrada"));
        }

        if (!requisicaoMudancaDto.encerrada()) {
            throw new LogicException(this.i18n_Message("requisicaoMudanca.naoPermiteReabertura"));
        }

        usuarioDto = new UsuarioDao().restoreByLogin(loginUsuario);
        int seqReabertura = 1;
        final ReaberturaMudancaDao reaberturaMudancaDao = new ReaberturaMudancaDao();
        this.setTransacaoDao(reaberturaMudancaDao);
        final Collection colReabertura = reaberturaMudancaDao.findByIdRequisicaoMudanca(requisicaoMudancaDto.getIdRequisicaoMudanca());
        if (colReabertura != null) {
            seqReabertura = colReabertura.size() + 1;
        }

        final ReaberturaMudancaDTO reaberturaMudancaDto = new ReaberturaMudancaDTO();
        reaberturaMudancaDto.setIdRequisicaoMudanca(requisicaoMudancaDto.getIdRequisicaoMudanca());
        reaberturaMudancaDto.setSeqReabertura(seqReabertura);
        reaberturaMudancaDto.setIdResponsavel(usuarioDto.getIdUsuario());
        reaberturaMudancaDto.setDataHora(UtilDatas.getDataHoraAtual());
        reaberturaMudancaDao.create(reaberturaMudancaDto);

        requisicaoMudancaDto.setStatus(SituacaoRequisicaoMudanca.Reaberta.name());
        requisicaoMudancaDto.setSeqReabertura(new Integer(seqReabertura));
        final RequisicaoMudancaDao requisicaoDao = new RequisicaoMudancaDao();
        this.setTransacaoDao(requisicaoDao);
        requisicaoDao.update(requisicaoMudancaDto);

        final OcorrenciaMudancaDao ocorrenciaMudancaDao = new OcorrenciaMudancaDao();
        this.setTransacaoDao(ocorrenciaMudancaDao);
        final OcorrenciaMudancaDTO ocorrenciaMudancaDto = new OcorrenciaMudancaDTO();
        ocorrenciaMudancaDto.setIdRequisicaoMudanca(requisicaoMudancaDto.getIdRequisicaoMudanca());
        ocorrenciaMudancaDto.setDataregistro(UtilDatas.getDataAtual());
        ocorrenciaMudancaDto.setHoraregistro(UtilDatas.formatHoraFormatadaStr(UtilDatas.getHoraAtual()));
        ocorrenciaMudancaDto.setTempoGasto(0);
        ocorrenciaMudancaDto.setDescricao(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Reabertura.getDescricao());
        ocorrenciaMudancaDto.setDataInicio(UtilDatas.getDataAtual());
        ocorrenciaMudancaDto.setDataFim(UtilDatas.getDataAtual());
        ocorrenciaMudancaDto.setRegistradopor(usuarioDto.getLogin());
        ocorrenciaMudancaDto.setDadosMudanca(new Gson().toJson(this.getRequisicaoMudancaDto()));
        ocorrenciaMudancaDto.setOrigem(br.com.centralit.citcorpore.util.Enumerados.OrigemOcorrencia.OUTROS.getSigla().toString());
        ocorrenciaMudancaDto.setCategoria(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Reabertura.getSigla());
        ocorrenciaMudancaDao.create(ocorrenciaMudancaDto);

        this.inicia();
    }

    @Override
    public void suspende(final String loginUsuario) throws Exception {
        final RequisicaoMudancaDTO requisicaoMudancaDto = this.getRequisicaoMudancaDto();
        if (requisicaoMudancaDto == null) {
            throw new LogicException(this.i18n_Message("requisicaoMudanca.naoEncontrada"));
        }

        if (!requisicaoMudancaDto.emAtendimento()) {
            throw new LogicException(this.i18n_Message("requisicaoMudanca.naoPermiteSuspensao"));
        }

        final Timestamp tsAtual = UtilDatas.getDataHoraAtual();
        Timestamp tsInicial = requisicaoMudancaDto.getDataHoraInicio();
        if (requisicaoMudancaDto.getDataHoraReativacao() != null) {
            tsInicial = requisicaoMudancaDto.getDataHoraReativacao();
        }
        final TipoMudancaDTO tipoMudancaDto = this.recuperaTipoMudanca();
        if (tipoMudancaDto.getIdCalendario() != null) {
            requisicaoMudancaDto.setIdCalendario(tipoMudancaDto.getIdCalendario());
        }
        CalculoJornadaDTO calculoDto = new CalculoJornadaDTO(requisicaoMudancaDto.getIdCalendario(), tsInicial);
        calculoDto = new CalendarioServiceEjb().calculaPrazoDecorrido(calculoDto, tsAtual, null);

        final RequisicaoMudancaDao requisicaoDao = new RequisicaoMudancaDao();
        this.setTransacaoDao(requisicaoDao);

        requisicaoMudancaDto.setStatus(SituacaoRequisicaoMudanca.Suspensa.name());
        if (requisicaoMudancaDto.getTempoDecorridoHH() == null) {
            requisicaoMudancaDto.setTempoDecorridoHH(0);
        }
        if (requisicaoMudancaDto.getTempoDecorridoMM() == null) {
            requisicaoMudancaDto.setTempoDecorridoMM(0);
        }
        requisicaoMudancaDto.setTempoDecorridoHH(new Integer(requisicaoMudancaDto.getTempoDecorridoHH().intValue()
                + calculoDto.getTempoDecorridoHH().intValue()));
        requisicaoMudancaDto.setTempoDecorridoMM(new Integer(requisicaoMudancaDto.getTempoDecorridoMM().intValue()
                + calculoDto.getTempoDecorridoMM().intValue()));
        requisicaoMudancaDto.setDataHoraSuspensao(tsAtual);
        requisicaoMudancaDto.setDataHoraReativacao(null);
        requisicaoDao.update(requisicaoMudancaDto);

        final OcorrenciaMudancaDao ocorrenciaMudancaDao = new OcorrenciaMudancaDao();
        this.setTransacaoDao(ocorrenciaMudancaDao);
        final OcorrenciaMudancaDTO ocorrenciaMudancaDto = new OcorrenciaMudancaDTO();
        ocorrenciaMudancaDto.setIdRequisicaoMudanca(requisicaoMudancaDto.getIdRequisicaoMudanca());
        ocorrenciaMudancaDto.setDataregistro(new Date(tsAtual.getTime()));
        ocorrenciaMudancaDto.setHoraregistro(UtilDatas.formatHoraFormatadaStr(tsAtual));
        ocorrenciaMudancaDto.setTempoGasto(0);
        ocorrenciaMudancaDto.setDescricao(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Suspensao.getDescricao());
        ocorrenciaMudancaDto.setDataInicio(UtilDatas.getDataAtual());
        ocorrenciaMudancaDto.setDataFim(UtilDatas.getDataAtual());
        ocorrenciaMudancaDto.setRegistradopor(loginUsuario);
        ocorrenciaMudancaDto.setDadosMudanca(new Gson().toJson(this.getRequisicaoMudancaDto()));
        ocorrenciaMudancaDto.setIdJustificativa(requisicaoMudancaDto.getIdJustificativa());
        ocorrenciaMudancaDto.setComplementoJustificativa(requisicaoMudancaDto.getComplementoJustificativa());
        ocorrenciaMudancaDto.setOrigem(br.com.centralit.citcorpore.util.Enumerados.OrigemOcorrencia.OUTROS.getSigla().toString());
        ocorrenciaMudancaDto.setCategoria(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Suspensao.getSigla());
        ocorrenciaMudancaDao.create(ocorrenciaMudancaDto);
    }

    @Override
    public void reativa(final String loginUsuario) throws Exception {
        final RequisicaoMudancaDTO requisicaoMudancaDto = this.getRequisicaoMudancaDto();
        if (requisicaoMudancaDto == null) {
            throw new LogicException(this.i18n_Message("requisicaoMudanca.naoEncontrada"));
        }

        if (!requisicaoMudancaDto.suspensa()) {
            throw new LogicException(this.i18n_Message("requisicaoMudanca.naoPermiteReativacao"));
        }

        final Timestamp tsAtual = UtilDatas.getDataHoraAtual();
        double prazo = requisicaoMudancaDto.getPrazoHH() + new Double(requisicaoMudancaDto.getPrazoMM()).doubleValue() / 60;
        final double tempo = requisicaoMudancaDto.getTempoDecorridoHH() + new Double(requisicaoMudancaDto.getTempoDecorridoMM()).doubleValue() / 60;
        prazo = prazo - tempo;
        if (prazo > 0) {
            CalculoJornadaDTO calculoDto = new CalculoJornadaDTO(requisicaoMudancaDto.getIdCalendario(), tsAtual, Util.getHora(prazo), Util.getMinuto(prazo));

            calculoDto = new CalendarioServiceEjb().calculaDataHoraFinal(calculoDto, false, null);
            requisicaoMudancaDto.setDataHoraTermino(calculoDto.getDataHoraFinal());
        }
        final RequisicaoMudancaDao requisicaoDao = new RequisicaoMudancaDao();
        this.setTransacaoDao(requisicaoDao);

        requisicaoMudancaDto.setStatus(SituacaoRequisicaoMudanca.EmExecucao.name());
        requisicaoMudancaDto.setDataHoraSuspensao(null);
        requisicaoMudancaDto.setDataHoraReativacao(tsAtual);
        requisicaoDao.update(requisicaoMudancaDto);

        final OcorrenciaMudancaDao ocorrenciaMudancaDao = new OcorrenciaMudancaDao();
        this.setTransacaoDao(ocorrenciaMudancaDao);
        final OcorrenciaMudancaDTO ocorrenciaMudancaDto = new OcorrenciaMudancaDTO();
        ocorrenciaMudancaDto.setIdRequisicaoMudanca(requisicaoMudancaDto.getIdRequisicaoMudanca());
        ocorrenciaMudancaDto.setDataregistro(new Date(tsAtual.getTime()));
        ocorrenciaMudancaDto.setHoraregistro(UtilDatas.formatHoraFormatadaStr(tsAtual));
        ocorrenciaMudancaDto.setTempoGasto(0);
        ocorrenciaMudancaDto.setDescricao(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Reativacao.getDescricao());
        ocorrenciaMudancaDto.setDataInicio(UtilDatas.getDataAtual());
        ocorrenciaMudancaDto.setDataFim(UtilDatas.getDataAtual());
        ocorrenciaMudancaDto.setRegistradopor(loginUsuario);
        ocorrenciaMudancaDto.setDadosMudanca(new Gson().toJson(this.getRequisicaoMudancaDto()));
        ocorrenciaMudancaDto.setOrigem(br.com.centralit.citcorpore.util.Enumerados.OrigemOcorrencia.OUTROS.getSigla().toString());
        ocorrenciaMudancaDto.setCategoria(br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia.Reativacao.getSigla());
        ocorrenciaMudancaDao.create(ocorrenciaMudancaDto);
    }

    private Integer getIdCalendario(final RequisicaoMudancaDTO requisicaoMudancaDto) throws Exception {
        Integer idCalendario = requisicaoMudancaDto.getIdCalendario();
        if (requisicaoMudancaDto.getIdCalendario() == null) {
            final TipoMudancaDTO tipoMudancaDto = this.recuperaTipoMudanca();
            idCalendario = tipoMudancaDto.getIdCalendario();
        }
        return idCalendario;
    }

    public void calculaTempoAtendimento(final RequisicaoMudancaDTO requisicaoMudancaDto) throws Exception {
        final Integer idCalendario = this.getIdCalendario(requisicaoMudancaDto);

        Timestamp tsAtual = UtilDatas.getDataHoraAtual();
        if (requisicaoMudancaDto.getStatus().equalsIgnoreCase(SituacaoRequisicaoMudanca.Concluida.name())) {
            tsAtual = requisicaoMudancaDto.getDataHoraConclusao();
        }

        Timestamp tsInicial = requisicaoMudancaDto.getDataHoraInicio();
        if (requisicaoMudancaDto.getDataHoraReativacao() != null) {
            tsInicial = requisicaoMudancaDto.getDataHoraReativacao();
        }

        CalculoJornadaDTO calculoDto = new CalculoJornadaDTO(idCalendario, tsInicial);
        calculoDto = new CalendarioServiceEjb().calculaPrazoDecorrido(calculoDto, tsAtual, null);

        if (requisicaoMudancaDto.getTempoDecorridoHH() == null) {
            requisicaoMudancaDto.setTempoDecorridoHH(0);
        }
        if (requisicaoMudancaDto.getTempoDecorridoMM() == null) {
            requisicaoMudancaDto.setTempoDecorridoMM(0);
        }

        requisicaoMudancaDto.setTempoAtendimentoHH(new Integer(requisicaoMudancaDto.getTempoDecorridoHH().intValue()
                + calculoDto.getTempoDecorridoHH().intValue()));
        requisicaoMudancaDto.setTempoAtendimentoMM(new Integer(requisicaoMudancaDto.getTempoDecorridoMM().intValue()
                + calculoDto.getTempoDecorridoMM().intValue()));
    }

    public void calculaTempoCaptura(final RequisicaoMudancaDTO requisicaoMudancaDto) throws Exception {
        requisicaoMudancaDto.setTempoCapturaHH(0);
        requisicaoMudancaDto.setTempoCapturaMM(0);

        if (requisicaoMudancaDto.getDataHoraCaptura() == null) {
            return;
        }

        final Integer idCalendario = this.getIdCalendario(requisicaoMudancaDto);

        CalculoJornadaDTO calculoDto = new CalculoJornadaDTO(idCalendario, requisicaoMudancaDto.getDataHoraInicio());
        calculoDto = new CalendarioServiceEjb().calculaPrazoDecorrido(calculoDto, requisicaoMudancaDto.getDataHoraCaptura(), null);

        requisicaoMudancaDto.setTempoCapturaHH(calculoDto.getTempoDecorridoHH().intValue());
        requisicaoMudancaDto.setTempoCapturaMM(calculoDto.getTempoDecorridoMM().intValue());
    }

    public void calculaTempoAtraso(final RequisicaoMudancaDTO requisicaoMudancaDto) throws Exception {
        requisicaoMudancaDto.setTempoAtrasoHH(0);
        requisicaoMudancaDto.setTempoAtrasoMM(0);
        if (requisicaoMudancaDto.getDataHoraTermino() != null) {
            final Timestamp dataHoraLimite = requisicaoMudancaDto.getDataHoraTermino();
            Timestamp dataHoraComparacao = UtilDatas.getDataHoraAtual();
            if (requisicaoMudancaDto.encerrada()) {
                dataHoraComparacao = requisicaoMudancaDto.getDataHoraConclusao();
            }
            if (dataHoraComparacao.compareTo(dataHoraLimite) > 0) {
                final long atrasoSLA = UtilDatas.calculaDiferencaTempoEmMilisegundos(dataHoraComparacao, dataHoraLimite) / 1000;

                final String hora = Util.getHoraStr(new Double(atrasoSLA) / 3600);
                final int tam = hora.length();
                requisicaoMudancaDto.setTempoAtrasoHH(new Integer(hora.substring(0, tam - 2)));
                requisicaoMudancaDto.setTempoAtrasoMM(new Integer(hora.substring(tam - 2, tam)));
            }
        }
    }

    @Override
    public void executaEvento(final EventoFluxoDTO eventoFluxoDto) throws Exception {
        final ExecucaoMudancaDao execucaoMudancaDao = new ExecucaoMudancaDao();
        final ExecucaoMudancaDTO execucaoMudancaDto = execucaoMudancaDao.findByIdInstanciaFluxo(eventoFluxoDto.getIdInstancia());
        if (execucaoMudancaDto == null) {
            System.out.println("Execução mudança do evento " + eventoFluxoDto.getIdItemTrabalho() + " não encontrada");
            throw new LogicException(this.i18n_Message("requisicaoMudanca.eventoNaoEncontrado"));
        }
        final RequisicaoMudancaDao requisicaoMudancaDao = new RequisicaoMudancaDao();
        RequisicaoMudancaDTO requisicaoMudancaDto = new RequisicaoMudancaDTO();
        requisicaoMudancaDto.setIdRequisicaoMudanca(execucaoMudancaDto.getIdRequisicaoMudanca());
        requisicaoMudancaDto = (RequisicaoMudancaDTO) requisicaoMudancaDao.restore(requisicaoMudancaDto);
        if (requisicaoMudancaDto == null) {
            System.out.println("Execução mudança do evento " + eventoFluxoDto.getIdItemTrabalho() + " não encontrada");
            throw new LogicException(this.i18n_Message("requisicaoMudanca.eventoNaoEncontrado"));
        }

        final TransactionControler tc = new TransactionControlerImpl(execucaoMudancaDao.getAliasDB());
        this.setTransacao(tc);
        this.setObjetoNegocioDto(requisicaoMudancaDto);
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
        ServiceLocator.getInstance().getService(GrupoEmailService.class, null);

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
            throw new LogicException(this.i18n_Message("requisicaoMudanca.remetenteNaoParametrizado"));
        }

        final RequisicaoMudancaDTO requisicaoMudancaDTO = new RequisicaoMudancaServiceEjb().restoreAll(this.getRequisicaoMudancaDto().getIdRequisicaoMudanca(),
                this.getTransacao());
        if (requisicaoMudancaDTO == null) {
            return;
        }
        requisicaoMudancaDTO.setNomeTarefa(this.getRequisicaoMudancaDto().getNomeTarefa());

        try {

            for (final String email : emails) {
                final int posArroba = email.indexOf("@");
                if (posArroba > 0 && email.substring(posArroba).contains(".")) {
                    try {
                        mensagem = new MensagemEmail(idModeloEmail, new BaseEntity[] {requisicaoMudancaDTO});
                        mensagem.envia(email, remetente, remetente);
                    } catch (final Exception e) {}
                }
            }

        } catch (final Exception e) {}
    }

    // envia e-mail para todos do grupo em um agendamento de reunião
    public void enviaEmailReuniaoGrupo(final Integer idModeloEmail, final Integer idGrupoDestino, final Integer idRequisicaoMudanca,
            final Integer idReuniaoRequisicaoMudanca) throws Exception {
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
            emails = (List<String>) grupoService.listarPessoasEmailPorGrupo(idGrupoDestino);
        } catch (final Exception e) {
            return;
        }

        if (emails == null || emails.isEmpty()) {
            return;
        }

        final String remetente = ParametroUtil.getValor(ParametroSistema.RemetenteNotificacoesSolicitacao, this.getTransacao(), null);
        if (remetente == null) {
            throw new LogicException(this.i18n_Message("requisicaoMudanca.remetenteNaoParametrizado"));
        }

        final RequisicaoMudancaDTO requisicaoMudancaDTO = new RequisicaoMudancaServiceEjb().restoreAllReuniao(idRequisicaoMudanca, idReuniaoRequisicaoMudanca,
                this.getTransacao());
        if (requisicaoMudancaDTO == null) {
            return;
        }

        final Object[] emailsArray = new Object[emails.size() / 2];
        int j = 0;
        for (int i = 0; i < emails.size(); i++) {
            if (emails.get(i).contains("@")) {
                continue;
            } else {
                emailsArray[j] = emails.get(i);
                j++;
            }
        }

        try {
            int i = 0;
            for (final String email : emails) {
                final int posArroba = email.indexOf("@");
                if (posArroba > 0 && email.substring(posArroba).contains(".")) {
                    try {
                        if (StringUtils.isNotBlank(emailsArray[i].toString())) {
                            final String nomeContato = emailsArray[i].toString();
                            requisicaoMudancaDTO.setNomeContato(nomeContato);
                        }
                        mensagem = new MensagemEmail(idModeloEmail, new BaseEntity[] {requisicaoMudancaDTO});
                        mensagem.envia(email, remetente, remetente);
                        i++;
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

        empregadoDto.setIdEmpregado(this.getRequisicaoMudancaDto().getUsuarioDto().getIdEmpregado());

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
            throw new LogicException(this.i18n_Message("requisicaoMudanca.remetenteNaoParametrizado"));
        }

        final RequisicaoMudancaDTO requisicaoMudancaDTO = new RequisicaoMudancaServiceEjb().restoreAll(this.getRequisicaoMudancaDto().getIdRequisicaoMudanca(),
                this.getTransacao());
        if (requisicaoMudancaDTO == null) {
            return;
        }
        requisicaoMudancaDTO.setNomeTarefa(this.getRequisicaoMudancaDto().getNomeTarefa());

        try {
            final int posArroba = email.indexOf("@");
            if (posArroba > 0 && email.substring(posArroba).contains(".")) {
                try {
                    requisicaoMudancaDTO.setNomeContato(empregadoDto.getNome());
                    mensagem = new MensagemEmail(idModeloEmail, new BaseEntity[] {requisicaoMudancaDTO});

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

}
