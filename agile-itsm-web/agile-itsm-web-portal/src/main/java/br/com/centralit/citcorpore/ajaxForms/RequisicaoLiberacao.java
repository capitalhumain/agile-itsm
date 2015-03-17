package br.com.centralit.citcorpore.ajaxForms;

import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.htmlparser.jericho.Source;

import org.apache.commons.lang3.StringEscapeUtils;

import br.com.centralit.citajax.html.AjaxFormAction;
import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citajax.html.HTMLElement;
import br.com.centralit.citajax.html.HTMLForm;
import br.com.centralit.citajax.html.HTMLSelect;
import br.com.centralit.citajax.html.HTMLTable;
import br.com.centralit.citcorpore.bean.AprovacaoMudancaDTO;
import br.com.centralit.citcorpore.bean.AtividadePeriodicaDTO;
import br.com.centralit.citcorpore.bean.CategoriaOcorrenciaDTO;
import br.com.centralit.citcorpore.bean.ClienteDTO;
import br.com.centralit.citcorpore.bean.ContatoRequisicaoLiberacaoDTO;
import br.com.centralit.citcorpore.bean.ContratoDTO;
import br.com.centralit.citcorpore.bean.ContratosGruposDTO;
import br.com.centralit.citcorpore.bean.EmpregadoDTO;
import br.com.centralit.citcorpore.bean.FornecedorDTO;
import br.com.centralit.citcorpore.bean.GrupoAtvPeriodicaDTO;
import br.com.centralit.citcorpore.bean.GrupoDTO;
import br.com.centralit.citcorpore.bean.HistoricoMudancaDTO;
import br.com.centralit.citcorpore.bean.ItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.JustificativaRequisicaoLiberacaoDTO;
import br.com.centralit.citcorpore.bean.LiberacaoMudancaDTO;
import br.com.centralit.citcorpore.bean.LiberacaoProblemaDTO;
import br.com.centralit.citcorpore.bean.LocalidadeDTO;
import br.com.centralit.citcorpore.bean.LocalidadeUnidadeDTO;
import br.com.centralit.citcorpore.bean.MidiaSoftwareDTO;
import br.com.centralit.citcorpore.bean.OcorrenciaLiberacaoDTO;
import br.com.centralit.citcorpore.bean.OcorrenciaSolicitacaoDTO;
import br.com.centralit.citcorpore.bean.OrigemOcorrenciaDTO;
import br.com.centralit.citcorpore.bean.ProblemaDTO;
import br.com.centralit.citcorpore.bean.ProblemaMudancaDTO;
import br.com.centralit.citcorpore.bean.ProgramacaoAtividadeDTO;
import br.com.centralit.citcorpore.bean.RequisicaoLiberacaoDTO;
import br.com.centralit.citcorpore.bean.RequisicaoLiberacaoItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.RequisicaoLiberacaoMidiaDTO;
import br.com.centralit.citcorpore.bean.RequisicaoLiberacaoResponsavelDTO;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaDTO;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaResponsavelDTO;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaRiscoDTO;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaServicoDTO;
import br.com.centralit.citcorpore.bean.ServicoContratoDTO;
import br.com.centralit.citcorpore.bean.SituacaoLiberacaoMudancaDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoMudancaDTO;
import br.com.centralit.citcorpore.bean.TemplateSolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.TipoLiberacaoDTO;
import br.com.centralit.citcorpore.bean.UnidadeDTO;
import br.com.centralit.citcorpore.bean.UploadDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.integracao.AprovacaoMudancaDao;
import br.com.centralit.citcorpore.integracao.HistoricoMudancaDao;
import br.com.centralit.citcorpore.integracao.LiberacaoMudancaDao;
import br.com.centralit.citcorpore.integracao.ProblemaMudancaDAO;
import br.com.centralit.citcorpore.integracao.RequisicaoMudancaItemConfiguracaoDao;
import br.com.centralit.citcorpore.integracao.RequisicaoMudancaResponsavelDao;
import br.com.centralit.citcorpore.integracao.RequisicaoMudancaRiscoDao;
import br.com.centralit.citcorpore.integracao.RequisicaoMudancaServicoDao;
import br.com.centralit.citcorpore.integracao.SolicitacaoServicoMudancaDao;
import br.com.centralit.citcorpore.negocio.AtividadePeriodicaService;
import br.com.centralit.citcorpore.negocio.CategoriaSolucaoService;
import br.com.centralit.citcorpore.negocio.ClienteService;
import br.com.centralit.citcorpore.negocio.ContatoRequisicaoLiberacaoServiceEjb;
import br.com.centralit.citcorpore.negocio.ContratoService;
import br.com.centralit.citcorpore.negocio.ContratosGruposService;
import br.com.centralit.citcorpore.negocio.EmpregadoService;
import br.com.centralit.citcorpore.negocio.FornecedorService;
import br.com.centralit.citcorpore.negocio.GrupoAtvPeriodicaService;
import br.com.centralit.citcorpore.negocio.GrupoService;
import br.com.centralit.citcorpore.negocio.HistoricoLiberacaoService;
import br.com.centralit.citcorpore.negocio.HistoricoMudancaService;
import br.com.centralit.citcorpore.negocio.ItemConfiguracaoService;
import br.com.centralit.citcorpore.negocio.JustificativaRequisicaoLiberacaoService;
import br.com.centralit.citcorpore.negocio.LiberacaoMudancaService;
import br.com.centralit.citcorpore.negocio.LiberacaoProblemaService;
import br.com.centralit.citcorpore.negocio.LocalidadeService;
import br.com.centralit.citcorpore.negocio.LocalidadeUnidadeService;
import br.com.centralit.citcorpore.negocio.MidiaSoftwareService;
import br.com.centralit.citcorpore.negocio.OcorrenciaLiberacaoService;
import br.com.centralit.citcorpore.negocio.OcorrenciaSolicitacaoService;
import br.com.centralit.citcorpore.negocio.ProblemaService;
import br.com.centralit.citcorpore.negocio.RequisicaoLiberacaoItemConfiguracaoService;
import br.com.centralit.citcorpore.negocio.RequisicaoLiberacaoMidiaService;
import br.com.centralit.citcorpore.negocio.RequisicaoLiberacaoResponsavelService;
import br.com.centralit.citcorpore.negocio.RequisicaoLiberacaoService;
import br.com.centralit.citcorpore.negocio.RequisicaoMudancaItemConfiguracaoService;
import br.com.centralit.citcorpore.negocio.RequisicaoMudancaService;
import br.com.centralit.citcorpore.negocio.ServicoContratoService;
import br.com.centralit.citcorpore.negocio.SituacaoLiberacaoMudancaService;
import br.com.centralit.citcorpore.negocio.TemplateSolicitacaoServicoService;
import br.com.centralit.citcorpore.negocio.TipoLiberacaoService;
import br.com.centralit.citcorpore.negocio.UnidadeService;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.Enumerados.StatusIC;
import br.com.centralit.citcorpore.util.Enumerados.TipoDate;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.centralit.citcorpore.util.Util;
import br.com.centralit.citcorpore.util.WebUtil;
import br.com.centralit.citged.bean.ControleGEDDTO;
import br.com.centralit.citged.integracao.ControleGEDDao;
import br.com.centralit.citged.negocio.ControleGEDService;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.Reflexao;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilHTML;
import br.com.citframework.util.UtilI18N;
import br.com.citframework.util.UtilStrings;

@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public class RequisicaoLiberacao extends AjaxFormAction {

    private RequisicaoLiberacaoDTO requisicaoLiberacaoDTO;
    private RequisicaoLiberacaoDTO requisicaoLiberacaoDTOAux;

    private static HttpServletRequest requestGlobal;
    private static DocumentHTML documentGlobal;

    ContratoDTO contratoDtoAux = new ContratoDTO();
    private Boolean acao = false;

    @Override
    public Class getBeanClass() {
        return RequisicaoLiberacaoDTO.class;
    }

    @Override
    public void load(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        RequisicaoLiberacaoDTO requisicaoLiberacaoDTO = (RequisicaoLiberacaoDTO) document.getBean();
        final UsuarioDTO usuario = WebUtil.getUsuario(request);
        if (usuario == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }

        /**
         * Adicionado para fazer limpeza do upload que está na sessão .
         *
         * @author maycon.fernandes
         * @since 28/10/2013 08:21
         */
        request.getSession(true).setAttribute("colUploadsGED", null);
        /* document.executeScript("$('#requisicaLiberacaoStatus').hide()"); */

        /*
         * request.getSession(true).setAttribute("colUploadsGED", null);
         * request.getSession(true).setAttribute("colUploadsGEDdocsLegais", null);
         */

        if (this.listInfoRegExecucaoRequisicaoLiberacao(this.getRequisicaoLiberacaoDTO(), request) != null) {
            document.getElementById("tblOcorrencias").setInnerHTML(this.listInfoRegExecucaoRequisicaoLiberacao(requisicaoLiberacaoDTO, request));
        }
        this.preencherComboTipoLiberacao(document, request, response);
        this.preencherComboGrupoExecutor(document, request, response);
        this.preencherComboCategoriaSolucao(document, request, response);

        final GrupoService grupoService = (GrupoService) ServiceLocator.getInstance().getService(GrupoService.class, null);

        final Collection<GrupoDTO> lstGrupos = grupoService.getGruposByEmpregado(usuario.getIdEmpregado());

        if (lstGrupos != null) {
            for (final GrupoDTO g : lstGrupos) {
                if (g.getAbertura() != null && g.getAbertura().trim().equals("S")) {
                    document.getElementById("enviaEmailCriacao").setDisabled(true);
                }
                if (g.getEncerramento() != null && g.getEncerramento().trim().equals("S")) {
                    document.getElementById("enviaEmailFinalizacao").setDisabled(true);
                }
                if (g.getAndamento() != null && g.getAndamento().trim().equals("S")) {
                    document.getElementById("enviaEmailAcoes").setDisabled(true);
                }
            }
        }

        final HTMLSelect comboStatus = document.getSelectById("status");
        comboStatus.removeAllOptions();
        comboStatus.addOption("EmExecucao", UtilI18N.internacionaliza(request, "liberacao.emExecucao"));
        comboStatus.addOption("Resolvida", UtilI18N.internacionaliza(request, "citcorpore.comum.resolvida"));
        comboStatus.addOption("NaoResolvida", UtilI18N.internacionaliza(request, "requisicaoLiberacao.naoResolvida"));

        final ContratoService contratoService = (ContratoService) ServiceLocator.getInstance().getService(ContratoService.class, null);
        final ClienteService clienteService = (ClienteService) ServiceLocator.getInstance().getService(ClienteService.class, null);
        final FornecedorService fornecedorService = (FornecedorService) ServiceLocator.getInstance().getService(FornecedorService.class, null);
        final ContratosGruposService contratosGruposService = (ContratosGruposService) ServiceLocator.getInstance().getService(ContratosGruposService.class,
                null);
        final Collection colContratos = contratoService.list();

        this.limpar(document, request, response);

        String COLABORADORES_VINC_CONTRATOS = ParametroUtil.getValorParametroCitSmartHashMap(
                br.com.centralit.citcorpore.util.Enumerados.ParametroSistema.COLABORADORES_VINC_CONTRATOS, "N");
        if (COLABORADORES_VINC_CONTRATOS == null) {
            COLABORADORES_VINC_CONTRATOS = "N";
        }
        Collection colContratosColab = null;
        if (COLABORADORES_VINC_CONTRATOS.equalsIgnoreCase("S")) {
            colContratosColab = contratosGruposService.findByIdEmpregado(usuario.getIdEmpregado());
        }
        final Collection<ContratoDTO> listaContratos = new ArrayList<ContratoDTO>();
        document.getSelectById("idContrato").removeAllOptions();
        if (colContratos != null) {
            if (colContratos.size() > 1) {
                document.getSelectById("idContrato").addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
            } else {
                acao = true;
            }

            for (final Iterator it = colContratos.iterator(); it.hasNext();) {
                final ContratoDTO contratoDto = (ContratoDTO) it.next();
                if (contratoDto.getDeleted() == null || !contratoDto.getDeleted().equalsIgnoreCase("y")) {
                    if (COLABORADORES_VINC_CONTRATOS.equalsIgnoreCase("S")) { // Se parametro de colaboradores por contrato ativo, entao filtra.
                        if (colContratosColab == null) {
                            continue;
                        }
                        if (!this.isContratoInList(contratoDto.getIdContrato(), colContratosColab)) {
                            continue;
                        }
                    }

                    if (requisicaoLiberacaoDTO != null && requisicaoLiberacaoDTO.getIdRequisicaoLiberacao() != null) {
                        this.restore(document, request, response);

                    }
                    // INICIO_LOAD
                    if (requisicaoLiberacaoDTO == null || requisicaoLiberacaoDTO.getIdRequisicaoLiberacao() == null) {
                        document.getElementById("btOcorrencias").setVisible(false);
                        // document.executeScript("bloqueiaMenusDeConsulta();");
                    }

                    String nomeCliente = "";
                    String nomeForn = "";
                    ClienteDTO clienteDto = new ClienteDTO();
                    clienteDto.setIdCliente(contratoDto.getIdCliente());
                    clienteDto = (ClienteDTO) clienteService.restore(clienteDto);
                    if (clienteDto != null) {
                        nomeCliente = clienteDto.getNomeRazaoSocial();
                    }
                    FornecedorDTO fornecedorDto = new FornecedorDTO();
                    fornecedorDto.setIdFornecedor(contratoDto.getIdFornecedor());
                    fornecedorDto = (FornecedorDTO) fornecedorService.restore(fornecedorDto);
                    if (fornecedorDto != null) {
                        nomeForn = fornecedorDto.getRazaoSocial();
                    }
                    contratoDtoAux.setIdContrato(contratoDto.getIdContrato());
                    if (contratoDto.getSituacao().equalsIgnoreCase("A")) {
                        final String nomeContrato = "" + contratoDto.getNumero() + " de "
                                + UtilDatas.convertDateToString(TipoDate.DATE_DEFAULT, contratoDto.getDataContrato(), WebUtil.getLanguage(request)) + " ("
                                + nomeCliente + " - " + nomeForn + ")";
                        document.getSelectById("idContrato").addOption("" + contratoDto.getIdContrato(), nomeContrato);
                        contratoDto.setNome(nomeContrato);
                        listaContratos.add(contratoDto);
                    }
                }
            }
        }

        if (requisicaoLiberacaoDTO != null && requisicaoLiberacaoDTO.getIdRequisicaoLiberacao() != null) {
            this.restore(document, request, response);
        }
        // INICIO_LOAD
        if (requisicaoLiberacaoDTO == null || requisicaoLiberacaoDTO.getIdRequisicaoLiberacao() == null) {
            document.getElementById("btOcorrencias").setVisible(false);
            // document.executeScript("bloqueiaMenusDeConsulta();");
        }

        requisicaoLiberacaoDTO.getIdContrato();
        String tarefaAssociada = "N";
        if (requisicaoLiberacaoDTO.getIdTarefa() != null) {
            tarefaAssociada = "S";
        }
        request.setAttribute("tarefaAssociada", tarefaAssociada);

        if (requisicaoLiberacaoDTO.getIdRequisicaoLiberacao() != null && requisicaoLiberacaoDTO.getIdGrupoAtvPeriodica() == null) {
            final RequisicaoLiberacaoService liberacaoService = (RequisicaoLiberacaoService) ServiceLocator.getInstance().getService(
                    RequisicaoLiberacaoService.class, null);
            requisicaoLiberacaoDTO = (RequisicaoLiberacaoDTO) liberacaoService.restore(requisicaoLiberacaoDTO);
        }
        // carregar o grupo de atividade periódica para agendamento
        final HTMLForm form = document.getForm("form");
        // form.clear();
        if (requisicaoLiberacaoDTO.getIdGrupoAtvPeriodica() != null) {
            document.getSelectById("idGrupoAtvPeriodica").removeAllOptions();
            final GrupoAtvPeriodicaService grupoService2 = (GrupoAtvPeriodicaService) ServiceLocator.getInstance().getService(GrupoAtvPeriodicaService.class,
                    null);

            final ArrayList<GrupoAtvPeriodicaDTO> grupos = (ArrayList) grupoService2.list();
            if (grupos != null) {
                for (final GrupoAtvPeriodicaDTO grupo : grupos) {
                    if (requisicaoLiberacaoDTO.getIdGrupoAtvPeriodica().equals(grupo.getIdGrupoAtvPeriodica())) {
                        document.getSelectById("idGrupoAtvPeriodica").addOption(grupo.getIdGrupoAtvPeriodica().toString(), grupo.getNomeGrupoAtvPeriodica());
                    }
                }
            }
        } else {

            final AtividadePeriodicaDTO atividadePeriodicaDTO = new AtividadePeriodicaDTO();
            atividadePeriodicaDTO.setIdRequisicaoMudanca(requisicaoLiberacaoDTO.getIdLiberacao());
            final HTMLSelect idGrupoAtvPeriodica = document.getSelectById("idGrupoAtvPeriodica");
            idGrupoAtvPeriodica.removeAllOptions();
            final GrupoAtvPeriodicaService grupoAtvPeriodicaService = (GrupoAtvPeriodicaService) ServiceLocator.getInstance().getService(
                    GrupoAtvPeriodicaService.class, null);
            final Collection colGrupos = grupoAtvPeriodicaService.listGrupoAtividadePeriodicaAtiva();
            idGrupoAtvPeriodica.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
            idGrupoAtvPeriodica.addOptions(colGrupos, "idGrupoAtvPeriodica", "nomeGrupoAtvPeriodica", null);

            form.setValues(atividadePeriodicaDTO);
        }

        /*
         * if(requisicaoLiberacaoDTO != null && requisicaoLiberacaoDTO.getIdTarefa() != null ){
         * carregaInformacoesComplementares(document, request, requisicaoLiberacaoDTO);
         * }
         */
        requisicaoLiberacaoDTO = null;

        this.tabelaMudancaSituacaoLiberacao(document, request, response);

        document.getElementById("SGBD").setValue(CITCorporeUtil.SGBD_PRINCIPAL);
        document.executeScript("JANELA_AGUARDE_MENU.hide();$('#loading_overlay').hide();");
    }

    public void restore(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(request);
        if (usuario == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }
        /* document.executeScript("$('#requisicaLiberacaoStatus').show()"); */
        RequisicaoLiberacaoDTO requisicaoLiberacaoDTO = (RequisicaoLiberacaoDTO) document.getBean();
        final RequisicaoLiberacaoService liberacaoService = (RequisicaoLiberacaoService) ServiceLocator.getInstance().getService(
                RequisicaoLiberacaoService.class, WebUtil.getUsuarioSistema(request));

        final Integer idTarefa = requisicaoLiberacaoDTO.getIdTarefa();
        final String acaoFluxo = requisicaoLiberacaoDTO.getAcaoFluxo();
        final String alterarSituacao = requisicaoLiberacaoDTO.getAlterarSituacao();
        final String fase = requisicaoLiberacaoDTO.getFase();
        final String editar = requisicaoLiberacaoDTO.getEditar();
        final String escalar = requisicaoLiberacaoDTO.getEscalar();

        requisicaoLiberacaoDTO = liberacaoService.restoreAll(requisicaoLiberacaoDTO.getIdRequisicaoLiberacao());
        if (requisicaoLiberacaoDTO.getIdContrato() != null) {
            document.getElementById("pesqLockupLOOKUP_EMPREGADO_IDCONTRATO").setValue(requisicaoLiberacaoDTO.getIdContrato().toString());
        }
        if (requisicaoLiberacaoDTO == null) {
            return;
        }

        requisicaoLiberacaoDTO.setIdTarefa(idTarefa);
        requisicaoLiberacaoDTO.setAcaoFluxo(acaoFluxo);
        requisicaoLiberacaoDTO.setAlterarSituacao(alterarSituacao);
        requisicaoLiberacaoDTO.setFase(fase);
        requisicaoLiberacaoDTO.setEscalar(escalar);

        EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class,
                WebUtil.getUsuarioSistema(request));
        EmpregadoDTO empregadoDto = new EmpregadoDTO();
        empregadoDto = empregadoService.restoreByIdEmpregado(requisicaoLiberacaoDTO.getIdSolicitante());
        requisicaoLiberacaoDTO.setNomeSolicitante(empregadoDto.getNome());

        final HTMLTable tblMudancas = document.getTableById("tblMudancas");
        tblMudancas.deleteAllRows();

        final LiberacaoMudancaService liberacaoMudancaService = (LiberacaoMudancaService) ServiceLocator.getInstance().getService(
                LiberacaoMudancaService.class, WebUtil.getUsuarioSistema(request));
        // Collection<LiberacaoMudancaDTO> listaLiberacaoMudancas =
        // liberacaoMudancaService.findByIdLiberacao(requisicaoLiberacaoDTO.getIdRequisicaoLiberacao());
        // Collection<LiberacaoMudancaDTO> listaLiberacaoMudancas =
        // liberacaoMudancaService.findByIdRequisicaoMudanca(requisicaoLiberacaoDTO.getIdRequisicaoLiberacao());
        final Collection<LiberacaoMudancaDTO> listaLiberacaoMudancas = liberacaoMudancaService.findByIdRequisicaoMudanca(
                requisicaoLiberacaoDTO.getIdRequisicaoLiberacao(), null);
        requisicaoLiberacaoDTO.setColMudancas(listaLiberacaoMudancas);

        if (listaLiberacaoMudancas != null) {
            final RequisicaoMudancaService requisicaoMudancaService = (RequisicaoMudancaService) ServiceLocator.getInstance().getService(
                    RequisicaoMudancaService.class, WebUtil.getUsuarioSistema(request));
            ServiceLocator.getInstance().getService(SituacaoLiberacaoMudancaService.class, null);
            for (final LiberacaoMudancaDTO liberacaoMudancaDto : listaLiberacaoMudancas) {
                RequisicaoMudancaDTO requisicaoMudancaDto = new RequisicaoMudancaDTO();
                // requisicaoMudancaDto.setIdRequisicaoMudanca(liberacaoMudancaDto.getIdRequisicaoMudanca());
                requisicaoMudancaDto.setIdRequisicaoMudanca(liberacaoMudancaDto.getIdRequisicaoMudanca());
                requisicaoMudancaDto = (RequisicaoMudancaDTO) requisicaoMudancaService.restore(requisicaoMudancaDto);
                liberacaoMudancaDto.setIdLiberacao(requisicaoLiberacaoDTO.getIdLiberacao());
                liberacaoMudancaDto.setIdHistoricoLiberacao(liberacaoMudancaDto.getIdHistoricoLiberacao());
                liberacaoMudancaDto.setTitulo(requisicaoMudancaDto.getTitulo());
                if (liberacaoMudancaDto.getStatus() == null) {
                    liberacaoMudancaDto.setStatus(requisicaoMudancaDto.getStatus());
                } else {
                    liberacaoMudancaDto.setStatus(liberacaoMudancaDto.getStatus());
                }
                if (liberacaoMudancaDto.getSituacaoLiberacao() == null) {
                    liberacaoMudancaDto.setSituacaoLiberacao(requisicaoLiberacaoDTO.getStatus());
                } else {
                    liberacaoMudancaDto.setSituacaoLiberacao(liberacaoMudancaDto.getSituacaoLiberacao());
                }
            }
            tblMudancas.addRowsByCollection(listaLiberacaoMudancas, new String[] {"", "idRequisicaoMudanca", "titulo", "status", "situacaoLiberacao", "", ""},
                    null, "", new String[] {"gerarImgDel", "gerarImgpopup", "geraImgPlanoReversao"}, "funcaoClickRowMudanca", null);
        }

        final HTMLTable tblProblema = document.getTableById("tblProblema");
        tblProblema.deleteAllRows();

        final LiberacaoProblemaService liberacaoProblemaService = (LiberacaoProblemaService) ServiceLocator.getInstance().getService(
                LiberacaoProblemaService.class, WebUtil.getUsuarioSistema(request));
        final Collection<LiberacaoProblemaDTO> problemas = liberacaoProblemaService.findByIdLiberacao(requisicaoLiberacaoDTO.getIdRequisicaoLiberacao());
        if (problemas != null) {
            final ProblemaService problemaService = (ProblemaService) ServiceLocator.getInstance().getService(ProblemaService.class,
                    WebUtil.getUsuarioSistema(request));
            for (final LiberacaoProblemaDTO liberacaoProblemaDTO : problemas) {
                ProblemaDTO problemaDTO = new ProblemaDTO();
                problemaDTO.setIdProblema(liberacaoProblemaDTO.getIdProblema());
                // problemaDTO= (ProblemaDTO) problemaService.restore(problemaDTO);
                problemaDTO = problemaService.restauraTodos(problemaDTO);
                liberacaoProblemaDTO.setTitulo(problemaDTO.getTitulo());
                liberacaoProblemaDTO.setStatus(problemaDTO.getStatus());
            }

            tblProblema.addRowsByCollection(problemas, new String[] {"", "idProblema", "titulo", "status"}, null, "", new String[] {"gerarImgDelProblema"},
                    null, null);

        }
        // Responsável
        final HTMLTable tblResponsavel = document.getTableById("tblResponsavel");
        tblResponsavel.deleteAllRows();

        final RequisicaoLiberacaoResponsavelService liberacaoResponsavelService = (RequisicaoLiberacaoResponsavelService) ServiceLocator.getInstance()
                .getService(RequisicaoLiberacaoResponsavelService.class, WebUtil.getUsuarioSistema(request));
        final Collection<RequisicaoLiberacaoResponsavelDTO> responsavel = liberacaoResponsavelService.findByIdLiberacaoEDataFim(requisicaoLiberacaoDTO
                .getIdRequisicaoLiberacao());
        tblResponsavel.addRowsByCollection(responsavel, new String[] {"", "idResponsavel", "nomeResponsavel", "nomeCargo", "telResponsavel",
                "emailResponsavel", "papelResponsavel"}, null, "", new String[] {"gerarImgDelResponsavel"}, null, null);

        final HTMLTable tblMidia = document.getTableById("tblMidia");
        tblMidia.deleteAllRows();

        if (requisicaoLiberacaoDTO != null && requisicaoLiberacaoDTO.getIdRequisicaoLiberacao() != null) {
            final RequisicaoLiberacaoMidiaService requisicaoLiberacaoMidiaService = (RequisicaoLiberacaoMidiaService) ServiceLocator.getInstance().getService(
                    RequisicaoLiberacaoMidiaService.class, WebUtil.getUsuarioSistema(request));
            final Collection<RequisicaoLiberacaoMidiaDTO> listRequisicaoLiberacaoMidiaDTO = requisicaoLiberacaoMidiaService
                    .findByIdLiberacaoEDataFim(requisicaoLiberacaoDTO.getIdRequisicaoLiberacao());

            if (listRequisicaoLiberacaoMidiaDTO != null) {

                tblMidia.addRowsByCollection(listRequisicaoLiberacaoMidiaDTO, new String[] {"", "idMidiaSoftware", "nomeMidia"},
                        new String[] {"idMidiaSoftware"}, "Liberação já cadastrado!", new String[] {"exibeIconesMidia"}, null, null);
                document.executeScript("HTMLUtils.applyStyleClassInAllCells('tblMidia', 'tblMidia');");
            }
        }

        this.restaurarAnexos(request, requisicaoLiberacaoDTO);
        this.restaurarAnexosDocsLegais(request, requisicaoLiberacaoDTO);
        this.restaurarAnexosDocsGerais(request, requisicaoLiberacaoDTO);
        if (this.listInfoRegExecucaoRequisicaoLiberacao(this.getRequisicaoLiberacaoDTO(), request) != null) {
            document.getElementById("tblOcorrencias").setInnerHTML(this.listInfoRegExecucaoRequisicaoLiberacao(requisicaoLiberacaoDTO, request));
        }
        empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, WebUtil.getUsuarioSistema(request));

        this.preencherComboSituacao(document, request, requisicaoLiberacaoDTO);

        if (requisicaoLiberacaoDTO.getIdContatoRequisicaoLiberacao() != null) {
            ContatoRequisicaoLiberacaoDTO contatoRequisicaoLiberacaoDTO = new ContatoRequisicaoLiberacaoDTO();
            contatoRequisicaoLiberacaoDTO.setIdContatoRequisicaoLiberacao(requisicaoLiberacaoDTO.getIdContatoRequisicaoLiberacao());
            final ContatoRequisicaoLiberacaoServiceEjb contatoRequisicaoLiberacaoServiceEjb = new ContatoRequisicaoLiberacaoServiceEjb();
            contatoRequisicaoLiberacaoDTO = contatoRequisicaoLiberacaoServiceEjb.restoreContatosById(contatoRequisicaoLiberacaoDTO
                    .getIdContatoRequisicaoLiberacao());

            if (contatoRequisicaoLiberacaoDTO != null && contatoRequisicaoLiberacaoDTO.getIdContatoRequisicaoLiberacao() != null) {
                requisicaoLiberacaoDTO.setIdContatoRequisicaoLiberacao(contatoRequisicaoLiberacaoDTO.getIdContatoRequisicaoLiberacao());
            } else {
                return;
            }
            if (contatoRequisicaoLiberacaoDTO.getNomeContato() != null) {
                requisicaoLiberacaoDTO.setNomeContato2(Util.tratarAspasSimples(contatoRequisicaoLiberacaoDTO.getNomeContato()));
            }
            if (contatoRequisicaoLiberacaoDTO.getTelefoneContato() != null) {
                requisicaoLiberacaoDTO.setTelefoneContato(Util.tratarAspasSimples(contatoRequisicaoLiberacaoDTO.getTelefoneContato()));
            }
            if (contatoRequisicaoLiberacaoDTO.getRamal() != null) {
                requisicaoLiberacaoDTO.setRamal(Util.tratarAspasSimples(contatoRequisicaoLiberacaoDTO.getRamal()));
            }
            if (contatoRequisicaoLiberacaoDTO.getEmailContato() != null) {
                requisicaoLiberacaoDTO.setEmailContato(Util.tratarAspasSimples(contatoRequisicaoLiberacaoDTO.getEmailContato()));
            }
            if (contatoRequisicaoLiberacaoDTO.getIdUnidade() != null) {
                requisicaoLiberacaoDTO.setIdUnidade(contatoRequisicaoLiberacaoDTO.getIdUnidade());
            }
            if (contatoRequisicaoLiberacaoDTO.getIdLocalidade() != null) {
                requisicaoLiberacaoDTO.setIdLocalidade(contatoRequisicaoLiberacaoDTO.getIdLocalidade());
            }
            if (contatoRequisicaoLiberacaoDTO.getObservacao() != null) {
                requisicaoLiberacaoDTO.setObservacao(Util.tratarAspasSimples(contatoRequisicaoLiberacaoDTO.getObservacao()));
            }
        }
        final HTMLForm form = document.getForm("form");
        form.clear();

        if (requisicaoLiberacaoDTO.getIdSolicitante() != null) {

            this.preencherComboGrupoAprovadorById(document, request, response, requisicaoLiberacaoDTO.getIdSolicitante());

        }

        this.carregaUnidadeAux(document, request, response, null, false);
        this.preencherComboLocalidadeComUnidade(document, request, response, requisicaoLiberacaoDTO.getIdUnidade());

        /* atualizaInformacoesRelacionamentos(requisicaoLiberacaoDTO); */
        form.setValues(requisicaoLiberacaoDTO);
        /* document.executeScript("restaurar()"); */
        document.getElementById("numeroLiberacao").setValue(requisicaoLiberacaoDTO.getIdRequisicaoLiberacao().toString());

        if (requisicaoLiberacaoDTO.getIdContrato() != null) {
            document.getSelectById("idContrato").setDisabled(true);
        }
        if (requisicaoLiberacaoDTO.getDataInicial() != null) {
            document.getSelectById("dataInicial").setDisabled(true);
        }

        /* mostraHistoricoLiberacoes(document, request, response, requisicaoLiberacaoDTO); */

        if (editar == null || editar.equalsIgnoreCase("")) {
            requisicaoLiberacaoDTO.setEditar("S");
        } else if (editar.equalsIgnoreCase("N")) {
            document.executeScript("$('#divBarraFerramentas').hide()");
            document.executeScript("$('#divBotoes').hide()");
            document.executeScript("$('#btnGravar').hide()");
            document.executeScript("$('#abas').hide()");
            document.executeScript("$('#divBotaoAddRegExecucao').hide()");
            document.getForm("form").lockForm();

        }

        if (requisicaoLiberacaoDTO.getStatus().equalsIgnoreCase("Resolvida")) {
            document.executeScript("$('#dtLiberacao').addClass('campoObrigatorio')");
        }

        this.restoreTableIcs(document, request, response);
        document.executeScript("informaNumeroSolicitacao('" + requisicaoLiberacaoDTO.getIdRequisicaoLiberacao() + "')");
    }

    /*
     * private void atualizaInformacoesRelacionamentos(RequisicaoLiberacaoDTO requisicaoLiberacaoDTO) throws ServiceException, Exception {
     * // informações dos ics relacionados
     * RequisicaoLiberacaoService requisicaoLiberacaoService = (RequisicaoLiberacaoService)
     * ServiceLocator.getInstance().getService(RequisicaoLiberacaoService.class,null);
     * ArrayList<RequisicaoLiberacaoItemConfiguracaoDTO> listaICsRelacionados =
     * requisicaoLiberacaoService.listItensRelacionadosRequisicaoLiberacao(requisicaoLiberacaoDTO);
     * if(listaICsRelacionados!=null && listaICsRelacionados.size()>0){
     * requisicaoLiberacaoDTO.setItensConfiguracaoRelacionadosSerializado(br.com.citframework.util.WebUtil.serializeObjects(listaICsRelacionados));
     * }
     * }
     */

    public void save(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        requestGlobal = request;
        documentGlobal = document;

        final UsuarioDTO usuario = WebUtil.getUsuario(request);
        if (usuario == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }

        final RequisicaoLiberacaoDTO requisicaoLiberacaoDTO = (RequisicaoLiberacaoDTO) document.getBean();
        RequisicaoLiberacaoDTO requisicaoLiberacaoAuxDto = null;

        if (requisicaoLiberacaoDTO.getIdRequisicaoLiberacao() != null) {
            requisicaoLiberacaoAuxDto = (RequisicaoLiberacaoDTO) this.getRequisicaoLiberacaoService(request).restore(requisicaoLiberacaoDTO);

            if (requisicaoLiberacaoDTO.getDataInicial() == null) {
                requisicaoLiberacaoDTO.setDataInicial(requisicaoLiberacaoAuxDto.getDataInicial());
            }

            if (requisicaoLiberacaoDTO.getIdContrato() == null) {
                requisicaoLiberacaoDTO.setIdContrato(requisicaoLiberacaoAuxDto.getIdContrato());
            }

        }

        if (requisicaoLiberacaoDTO.getIdGrupoAtvPeriodica() == null && requisicaoLiberacaoAuxDto != null) {
            requisicaoLiberacaoDTO.setIdGrupoAtvPeriodica(requisicaoLiberacaoAuxDto.getIdGrupoAtvPeriodica());
        }

        if (requisicaoLiberacaoDTO.getDataInicial().compareTo(requisicaoLiberacaoDTO.getDataFinal()) > 0) {
            document.alert(UtilI18N.internacionaliza(request, "solicitacaoservico.validacao.datainiciomenorfinal"));
            return;
        }

        if (requisicaoLiberacaoDTO.getStatus() != null && requisicaoLiberacaoDTO.getStatus().equals("Resolvida")
                && requisicaoLiberacaoDTO.getDataLiberacao() == null) {
            document.alert(UtilI18N.internacionaliza(request, "liberacao.dataLiberacao") + " "
                    + UtilI18N.internacionaliza(request, "citcorpore.comum.naoInformado"));
            return;
        }

        final RequisicaoLiberacaoService liberacaoService = (RequisicaoLiberacaoService) ServiceLocator.getInstance().getService(
                RequisicaoLiberacaoService.class, WebUtil.getUsuarioSistema(request));

        final Collection<LiberacaoMudancaDTO> colMudancas = br.com.citframework.util.WebUtil.deserializeCollectionFromRequest(LiberacaoMudancaDTO.class,
                "mudancas_serialize", request);
        requisicaoLiberacaoDTO.setColMudancas(colMudancas);

        final Collection<LiberacaoProblemaDTO> colProblemas = br.com.citframework.util.WebUtil.deserializeCollectionFromRequest(LiberacaoProblemaDTO.class,
                "problemas_serialize", request);
        requisicaoLiberacaoDTO.setColProblemas(colProblemas);

        final Collection<RequisicaoLiberacaoResponsavelDTO> colResponsavel = br.com.citframework.util.WebUtil.deserializeCollectionFromRequest(
                RequisicaoLiberacaoResponsavelDTO.class, "responsavel_serialize", request);
        requisicaoLiberacaoDTO.setColResponsaveis(colResponsavel);

        requisicaoLiberacaoDTO.setUsuarioDto(usuario);

        final ArrayList<RequisicaoLiberacaoItemConfiguracaoDTO> listRequisicaoLiberacaoItemConfiguracaoDTO = (ArrayList<RequisicaoLiberacaoItemConfiguracaoDTO>) br.com.citframework.util.WebUtil
                .deserializeCollectionFromRequest(RequisicaoLiberacaoItemConfiguracaoDTO.class, "itensConfiguracaoRelacionadosSerializado", request);
        requisicaoLiberacaoDTO.setListRequisicaoLiberacaoItemConfiguracaoDTO(listRequisicaoLiberacaoItemConfiguracaoDTO);

        final Collection<RequisicaoLiberacaoMidiaDTO> colMidia = br.com.citframework.util.WebUtil.deserializeCollectionFromRequest(
                RequisicaoLiberacaoMidiaDTO.class, "midias_serialize", request);
        requisicaoLiberacaoDTO.setColMidia(colMidia);

        requisicaoLiberacaoDTO.setSituacao("E");

        if (requisicaoLiberacaoDTO != null && requisicaoLiberacaoDTO.getDataFinal() != null) {
            requisicaoLiberacaoDTO.setDataHoraTermino(UtilDatas.strToTimestamp(UtilDatas.convertDateToString(TipoDate.DATE_DEFAULT,
                    requisicaoLiberacaoDTO.getDataFinal(), WebUtil.getLanguage(request))));
        }

        requisicaoLiberacaoDTO.setUsuarioDto(usuario);
        requisicaoLiberacaoDTO.setEnviaEmailCriacao("S");
        requisicaoLiberacaoDTO.setEnviaEmailAcoes("S");
        requisicaoLiberacaoDTO.setEnviaEmailFinalizacao("S");

        // essa linha grava os anexos das requisições no objeto para ser gravado.
        this.gravarAnexoLiberacao(document, request, response, requisicaoLiberacaoDTO);

        final TipoLiberacaoService tipoLiberacaoService = (TipoLiberacaoService) ServiceLocator.getInstance().getService(TipoLiberacaoService.class,
                WebUtil.getUsuarioSistema(request));
        if (requisicaoLiberacaoDTO.getIdRequisicaoLiberacao() != null) {

            final RequisicaoLiberacaoService requisicaoLiberacaoServiceAux = (RequisicaoLiberacaoService) ServiceLocator.getInstance().getService(
                    RequisicaoLiberacaoService.class, WebUtil.getUsuarioSistema(request));

            if (requisicaoLiberacaoDTO.getIdRequisicaoLiberacao() != null) {

                requisicaoLiberacaoAuxDto.setIdRequisicaoLiberacao(requisicaoLiberacaoDTO.getIdRequisicaoLiberacao());
                requisicaoLiberacaoAuxDto = (RequisicaoLiberacaoDTO) requisicaoLiberacaoServiceAux.restore(requisicaoLiberacaoAuxDto);

                if (requisicaoLiberacaoAuxDto.getIdContrato() != null) {

                    requisicaoLiberacaoDTO.setIdContrato(requisicaoLiberacaoAuxDto.getIdContrato());

                }
                if (requisicaoLiberacaoAuxDto.getDataInicial() != null) {
                    requisicaoLiberacaoDTO.setDataInicial(requisicaoLiberacaoAuxDto.getDataInicial());
                }
            }

            requisicaoLiberacaoServiceAux.update(requisicaoLiberacaoDTO);
            document.alert(UtilI18N.internacionaliza(request, "MSG06"));

        } else {

            liberacaoService.create(requisicaoLiberacaoDTO);
            /* document.alert(UtilI18N.internacionaliza(request, "MSG05")); */

            String tipoLiberacao = "";
            if (requisicaoLiberacaoDTO.getIdTipoLiberacao() != null) {
                TipoLiberacaoDTO tipoLiberacaoDTO = new TipoLiberacaoDTO();
                tipoLiberacaoDTO.setIdTipoLiberacao(requisicaoLiberacaoDTO.getIdTipoLiberacao());
                tipoLiberacaoDTO = (TipoLiberacaoDTO) tipoLiberacaoService.restore(tipoLiberacaoDTO);
                if (tipoLiberacaoDTO != null) {
                    tipoLiberacao = tipoLiberacaoDTO.getNomeTipoLiberacao();
                }
            }

            String comando = "mostraMensagemInsercao('" + UtilI18N.internacionaliza(request, "MSG05") + ".<br>"
                    + UtilI18N.internacionaliza(request, "requisicaoLiberacao.requisicaoLiberacao") + " <b><u>"
                    + requisicaoLiberacaoDTO.getIdRequisicaoLiberacao() + "</u></b> " + UtilI18N.internacionaliza(request, "citcorpore.comum.crida")
                    + ".<br><br>" + "Tipo: " + UtilStrings.nullToVazio(tipoLiberacao.toString()) + "<br>";
            comando = comando + "')";

            document.executeScript(comando);
            return;

        }
        this.limpar(document, request, response);
        document.executeScript("limpar();");
        document.executeScript("fechar();");

    }

    public void delete(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(request);
        if (usuario == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }

        final RequisicaoLiberacaoDTO liberacaoDto = (RequisicaoLiberacaoDTO) document.getBean();
        final RequisicaoLiberacaoService liberacaoService = (RequisicaoLiberacaoService) ServiceLocator.getInstance().getService(
                RequisicaoLiberacaoService.class, WebUtil.getUsuarioSistema(request));

        if (liberacaoDto.getIdRequisicaoLiberacao() != null) {
            liberacaoService.delete(liberacaoDto);
            document.alert(UtilI18N.internacionaliza(request, "MSG07"));
            document.executeScript("limpar();");
        }
    }

    /**
     * @param document
     * @param request
     * @param response
     * @throws Exception
     */
    public void preencherComboTipoLiberacao(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        final TipoLiberacaoService tipoLiberacaoService = (TipoLiberacaoService) ServiceLocator.getInstance().getService(TipoLiberacaoService.class, null);
        final HTMLSelect comboTipoLiberacao = document.getSelectById("idTipoLiberacao");
        final ArrayList<TipoLiberacaoDTO> listTipoLiberacao = (ArrayList) tipoLiberacaoService.getAtivos();

        comboTipoLiberacao.removeAllOptions();
        comboTipoLiberacao.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
        if (listTipoLiberacao != null) {
            for (final TipoLiberacaoDTO tipoLiberacaoDTO : listTipoLiberacao) {
                tipoLiberacaoDTO.setNomeTipoLiberacao(StringEscapeUtils.escapeEcmaScript(tipoLiberacaoDTO.getNomeTipoLiberacao()));
                if (tipoLiberacaoDTO.getIdTipoLiberacao() != null || tipoLiberacaoDTO.getIdTipoLiberacao() > 0) {
                    comboTipoLiberacao.addOption(tipoLiberacaoDTO.getIdTipoLiberacao().toString(), tipoLiberacaoDTO.getNomeTipoLiberacao());

                }
            }
        }

    }

    public void preencherComboGrupoAprovadorById(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response,
            final Integer idSolicitante) throws Exception {

        document.getSelectById("idGrupoAprovador").removeAllOptions();

        final GrupoService grupoService = (GrupoService) ServiceLocator.getInstance().getService(GrupoService.class, null);

        final Collection<GrupoDTO> listGrupo = grupoService.getGruposByIdEmpregado(idSolicitante);

        document.getSelectById("idGrupoAprovador").addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));

        document.getSelectById("idGrupoAprovador").addOptions(listGrupo, "idGrupo", "nome", null);

    }

    public void preencherComboGrupoAprovador(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {

        final RequisicaoLiberacaoDTO requisicaoLiberacaoDto = (RequisicaoLiberacaoDTO) document.getBean();

        document.getSelectById("idGrupoAprovador").removeAllOptions();

        final GrupoService grupoService = (GrupoService) ServiceLocator.getInstance().getService(GrupoService.class, null);

        final Collection<GrupoDTO> listGrupo = grupoService.getGruposByIdEmpregado(requisicaoLiberacaoDto.getIdSolicitante());

        document.getSelectById("idGrupoAprovador").addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));

        document.getSelectById("idGrupoAprovador").addOptions(listGrupo, "idGrupo", "nome", null);

    }

    public void preencherComboGrupoExecutor(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        document.getSelectById("idGrupoAtual").removeAllOptions();

        final GrupoService grupoService = (GrupoService) ServiceLocator.getInstance().getService(GrupoService.class, null);

        final Collection<GrupoDTO> listGrupo = grupoService.listGruposNaoComite();

        document.getSelectById("idGrupoAtual").addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));

        document.getSelectById("idGrupoAtual").addOptions(listGrupo, "idGrupo", "nome", null);

    }

    public void carregaDadosContato(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        final RequisicaoLiberacaoDTO requisicaoLiberacaoDto = (RequisicaoLiberacaoDTO) document.getBean();
        final EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);
        final EmpregadoDTO empregadoDTO = empregadoService.restoreByIdEmpregado(requisicaoLiberacaoDto.getIdSolicitante());

        if (empregadoDTO.getNome() != null) {
            final HTMLSelect nomeContato = document.getSelectById("nomeContato2");
            nomeContato.setValue(empregadoDTO.getNome());
        }
        if (empregadoDTO.getEmail() != null) {
            final HTMLSelect emailContato = document.getSelectById("emailContato");
            emailContato.setValue(empregadoDTO.getEmail());
        }
        if (empregadoDTO.getTelefone() != null) {
            final HTMLSelect telefoneContato = document.getSelectById("telefoneContato");
            telefoneContato.setValue(empregadoDTO.getTelefone());
        }
        if (empregadoDTO.getRamal() != null) {
            final HTMLSelect ramal = document.getSelectById("ramal");
            ramal.setValue(empregadoDTO.getRamal());
        }

        if (empregadoDTO.getIdUnidade() != null) {
            this.carregaUnidadeAux(document, request, response, empregadoDTO, true);
        } else {
            this.carregaUnidadeAux(document, request, response, empregadoDTO, false);
        }

        this.preencherComboLocalidadeComUnidade(document, request, response, empregadoDTO.getIdUnidade());

    }

    /**
     * @param document
     * @param request
     * @param response
     * @param empregadoDTO
     * @param setUnidade
     * @throws Exception
     */
    public void carregaUnidadeAux(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response,
            final EmpregadoDTO empregadoDTO, final boolean setUnidade) throws Exception {

        final String validarComboUnidade = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.UNIDADE_VINC_CONTRATOS, "N");
        RequisicaoLiberacaoDTO requisicaoLiberacaoDto = (RequisicaoLiberacaoDTO) document.getBean();

        if (requisicaoLiberacaoDto.getIdRequisicaoLiberacao() != null && requisicaoLiberacaoDto.getIdRequisicaoLiberacao().intValue() > 0) {
            final RequisicaoLiberacaoService requisicaoLiberacaoService = (RequisicaoLiberacaoService) ServiceLocator.getInstance().getService(
                    RequisicaoLiberacaoService.class, null);

            contratoDtoAux.setIdContrato(requisicaoLiberacaoDto.getIdContrato());

            requisicaoLiberacaoDto = (RequisicaoLiberacaoDTO) requisicaoLiberacaoService.restore(requisicaoLiberacaoDto);
        }

        if (requisicaoLiberacaoDto.getIdContrato() == null || requisicaoLiberacaoDto.getIdContrato().intValue() == 0) {
            requisicaoLiberacaoDto.setIdContrato(contratoDtoAux.getIdContrato());
        }
        boolean verifica = false;
        final UnidadeService unidadeService = (UnidadeService) ServiceLocator.getInstance().getService(UnidadeService.class, null);
        final HTMLSelect comboUnidade = document.getSelectById("idUnidade");
        this.inicializarCombo(comboUnidade, request);
        if (validarComboUnidade.trim().equalsIgnoreCase("S")) {
            final Integer idContrato = requisicaoLiberacaoDto.getIdContrato();
            final ArrayList<UnidadeDTO> unidades = (ArrayList) unidadeService.listHierarquiaMultiContratos(idContrato);
            if (unidades != null) {
                for (final UnidadeDTO unidade : unidades) {
                    if (unidade.getDataFim() == null) {
                        comboUnidade.addOption(unidade.getIdUnidade().toString(), StringEscapeUtils.escapeEcmaScript(unidade.getNomeNivel().toString()));
                        if (setUnidade) {
                            if (unidade.getIdUnidade().equals(empregadoDTO.getIdUnidade())) {
                                verifica = true;
                            }
                        }

                        // }
                    }

                }
                if (verifica) {
                    comboUnidade.setValue(empregadoDTO.getIdUnidade().toString());
                }
            }
        } else {
            final ArrayList<UnidadeDTO> unidades = (ArrayList) unidadeService.listHierarquia();
            if (unidades != null) {
                for (final UnidadeDTO unidade : unidades) {
                    if (unidade.getDataFim() == null) {
                        comboUnidade.addOption(unidade.getIdUnidade().toString(), StringEscapeUtils.escapeEcmaScript(unidade.getNomeNivel().toString()));
                    }
                }
            }
            if (setUnidade) {
                comboUnidade.setValue(empregadoDTO.getIdUnidade().toString());
            }
        }

        requisicaoLiberacaoDto = null;

    }

    private void inicializarCombo(final HTMLSelect componenteCombo, final HttpServletRequest request) {
        componenteCombo.removeAllOptions();
        componenteCombo.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
    }

    public void preencherComboLocalidade(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        this.preencherComboLocalidadeComUnidade(document, request, response, null);

    }

    public void preencherComboLocalidadeComUnidade(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response,
            Integer idUnidade) throws Exception {

        final RequisicaoLiberacaoDTO requisicaoLiberacaoDto = (RequisicaoLiberacaoDTO) document.getBean();

        if (idUnidade == null) {
            idUnidade = requisicaoLiberacaoDto.getIdUnidade();
        }

        final LocalidadeUnidadeService localidadeUnidadeService = (LocalidadeUnidadeService) ServiceLocator.getInstance().getService(
                LocalidadeUnidadeService.class, null);

        final LocalidadeService localidadeService = (LocalidadeService) ServiceLocator.getInstance().getService(LocalidadeService.class, null);

        LocalidadeDTO localidadeDto = new LocalidadeDTO();

        Collection<LocalidadeUnidadeDTO> listaIdlocalidadePorUnidade = null;

        Collection<LocalidadeDTO> listaIdlocalidade = null;

        final String TIRAR_VINCULO_LOCALIDADE_UNIDADE = ParametroUtil.getValorParametroCitSmartHashMap(
                Enumerados.ParametroSistema.TIRAR_VINCULO_LOCALIDADE_UNIDADE, "N");

        final HTMLSelect comboLocalidade = document.getSelectById("idLocalidade");
        comboLocalidade.removeAllOptions();
        if (TIRAR_VINCULO_LOCALIDADE_UNIDADE.trim().equalsIgnoreCase("N") || TIRAR_VINCULO_LOCALIDADE_UNIDADE.trim().equalsIgnoreCase("")) {
            if (idUnidade != null) {
                listaIdlocalidadePorUnidade = localidadeUnidadeService.listaIdLocalidades(idUnidade);
            }
            if (listaIdlocalidadePorUnidade != null) {
                this.inicializarComboLocalidade(comboLocalidade, request);
                for (final LocalidadeUnidadeDTO localidadeUnidadeDto : listaIdlocalidadePorUnidade) {
                    localidadeDto.setIdLocalidade(localidadeUnidadeDto.getIdLocalidade());
                    localidadeDto = (LocalidadeDTO) localidadeService.restore(localidadeDto);
                    comboLocalidade.addOption(localidadeDto.getIdLocalidade().toString(), localidadeDto.getNomeLocalidade());
                }

            }
        } else {
            listaIdlocalidade = localidadeService.listLocalidade();
            if (listaIdlocalidade != null) {
                this.inicializarComboLocalidade(comboLocalidade, request);
                for (final LocalidadeDTO localidadeDTO : listaIdlocalidade) {
                    localidadeDto.setIdLocalidade(localidadeDTO.getIdLocalidade());
                    localidadeDto = (LocalidadeDTO) localidadeService.restore(localidadeDto);
                    comboLocalidade.addOption(localidadeDto.getIdLocalidade().toString(), localidadeDto.getNomeLocalidade());
                }
            }

        }
        // requisicaoMudancaDto = null;
    }

    private void inicializarComboLocalidade(final HTMLSelect componenteCombo, final HttpServletRequest request) {
        componenteCombo.removeAllOptions();
        componenteCombo.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
    }

    public RequisicaoLiberacaoDTO getRequisicaoLiberacaoDTO() {
        return requisicaoLiberacaoDTO;
    }

    /**
     * Retorna uma lista de informações da entidade ocorrencia
     *
     * @param requisicaoMudancaDto
     * @param request
     * @return
     * @throws ServiceException
     * @throws Exception
     * @author murilo.pacheco
     */
    public String listInfoRegExecucaoRequisicaoLiberacao(final RequisicaoLiberacaoDTO requisicaoLiberacaoDTO, final HttpServletRequest request)
            throws ServiceException, Exception {
        final JustificativaRequisicaoLiberacaoService justificativaRequisicaoLiberacaoService = (JustificativaRequisicaoLiberacaoService) ServiceLocator
                .getInstance().getService(JustificativaRequisicaoLiberacaoService.class, null);

        final OcorrenciaLiberacaoService ocorrenciaLiberacaoService = (OcorrenciaLiberacaoService) ServiceLocator.getInstance().getService(
                OcorrenciaLiberacaoService.class, null);
        Collection<OcorrenciaLiberacaoDTO> col = null;
        // para teste deixei o valor da requisição fixo 7
        // Collection<OcorrenciaMudancaDTO> col = ocorrenciaMudancaService.findByIdRequisicaoMudanca(requisicaoLiberacaoDTO.getIdRequisicaoLiberacao());
        // Collection<OcorrenciaLiberacaoDTO> col = ocorrenciaLiberacaoService.findByIdRequisicaoLiberacao(7);
        if (requisicaoLiberacaoDTO != null) {
            if (requisicaoLiberacaoDTO.getIdRequisicaoLiberacao() != null) {
                col = ocorrenciaLiberacaoService.findByIdRequisicaoLiberacao(requisicaoLiberacaoDTO.getIdRequisicaoLiberacao());
            }
        }
        // CategoriaOcorrenciaDAO categoriaOcorrenciaDAO = new CategoriaOcorrenciaDAO();
        // OrigemOcorrenciaDAO origemOcorrenciaDAO = new OrigemOcorrenciaDAO();

        CategoriaOcorrenciaDTO categoriaOcorrenciaDTO = new CategoriaOcorrenciaDTO();
        new OrigemOcorrenciaDTO();

        String strBuffer = "<table class='dynamicTable table table-striped table-bordered table-condensed dataTable' style='table-layout: fixed;'>";
        strBuffer += "<thead>";
        strBuffer += "<tr>";
        strBuffer += "<th style='width:20%'>";
        strBuffer += UtilI18N.internacionaliza(request, "citcorpore.comum.datahora");
        strBuffer += "</th>";
        strBuffer += "<th >";
        strBuffer += UtilI18N.internacionaliza(request, "solicitacaoServico.informacaoexecucao");
        strBuffer += "</th>";
        strBuffer += "</tr>";
        strBuffer += "</thead>";

        if (col != null) {

            for (final OcorrenciaLiberacaoDTO ocorrenciaLiberacaoDTO : col) {

                if (ocorrenciaLiberacaoDTO.getOcorrencia() != null) {
                    final Source source = new Source(ocorrenciaLiberacaoDTO.getOcorrencia());
                    ocorrenciaLiberacaoDTO.setOcorrencia(source.getTextExtractor().toString());
                }

                /*
                 * if (categoriaOcorrenciaDTO.getIdCategoriaOcorrencia() != null && categoriaOcorrenciaDTO.getIdCategoriaOcorrencia() != 0) {
                 * categoriaOcorrenciaDTO.setIdCategoriaOcorrencia(ocorrenciaMudancaDto.getIdCategoriaOcorrencia());
                 * categoriaOcorrenciaDAO.restore(categoriaOcorrenciaDTO); }
                 * if (origemOcorrenciaDTO.getIdOrigemOcorrencia() != null && origemOcorrenciaDTO.getIdOrigemOcorrencia() != 0) {
                 * origemOcorrenciaDTO.setIdOrigemOcorrencia(ocorrenciaMudancaDto.getIdOrigemOcorrencia()); origemOcorrenciaDAO.restore(origemOcorrenciaDTO); }
                 */

                String ocorrencia = UtilStrings.nullToVazio(ocorrenciaLiberacaoDTO.getOcorrencia());
                String descricao = UtilStrings.nullToVazio(ocorrenciaLiberacaoDTO.getDescricao());
                String informacoesContato = UtilStrings.nullToVazio(ocorrenciaLiberacaoDTO.getInformacoesContato());
                ocorrencia = ocorrencia.replaceAll("\"", "");
                descricao = descricao.replaceAll("\"", "");
                informacoesContato = informacoesContato.replaceAll("\"", "");
                ocorrencia = ocorrencia.replaceAll("\n", "<br>");
                descricao = descricao.replaceAll("\n", "<br>");
                informacoesContato = informacoesContato.replaceAll("\n", "<br>");
                ocorrencia = UtilHTML.encodeHTML(ocorrencia.replaceAll("\'", ""));
                descricao = UtilHTML.encodeHTML(descricao.replaceAll("\'", ""));
                informacoesContato = UtilHTML.encodeHTML(informacoesContato.replaceAll("\'", ""));
                strBuffer += "<tr>";
                strBuffer += "<td>";
                strBuffer += "<b>"
                        + UtilDatas.convertDateToString(TipoDate.DATE_DEFAULT, ocorrenciaLiberacaoDTO.getDataregistro(), WebUtil.getLanguage(request)) + " - "
                        + ocorrenciaLiberacaoDTO.getHoraregistro();
                strBuffer += " - </b>" + UtilI18N.internacionaliza(request, "ocorrenciaSolicitacao.registradopor") + ": <b>"
                        + ocorrenciaLiberacaoDTO.getRegistradopor() + "</b>";
                strBuffer += "</td>";
                strBuffer += "<td style='word-wrap: break-word;overflow:hidden;'>";
                strBuffer += "<b>" + ocorrenciaLiberacaoDTO.getDescricao() + "<br><br></b>";
                strBuffer += "<b>" + ocorrencia + "<br><br></b>";

                if (ocorrenciaLiberacaoDTO.getCategoria() != null) {
                    if (ocorrenciaLiberacaoDTO.getCategoria().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.Suspensao.toString())
                            || ocorrenciaLiberacaoDTO.getCategoria().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.MudancaSLA.toString())) {
                        JustificativaRequisicaoLiberacaoDTO justificativaRequisicaoLiberacaoDTO = new JustificativaRequisicaoLiberacaoDTO();
                        if (ocorrenciaLiberacaoDTO.getIdJustificativa() != null) {
                            justificativaRequisicaoLiberacaoDTO.setIdJustificativaLiberacao(ocorrenciaLiberacaoDTO.getIdJustificativa());
                            justificativaRequisicaoLiberacaoDTO = (JustificativaRequisicaoLiberacaoDTO) justificativaRequisicaoLiberacaoService
                                    .restore(justificativaRequisicaoLiberacaoDTO);
                            if (justificativaRequisicaoLiberacaoDTO != null) {
                                strBuffer += UtilI18N.internacionaliza(request, "citcorpore.comum.justificativa") + ": <b>"
                                        + justificativaRequisicaoLiberacaoDTO.getDescricaoJustificativa() + "<br><br></b>";
                            }
                        }
                        if (!UtilStrings.nullToVazio(ocorrenciaLiberacaoDTO.getComplementoJustificativa()).trim().equalsIgnoreCase("")) {
                            strBuffer += "<b>" + UtilStrings.nullToVazio(ocorrenciaLiberacaoDTO.getComplementoJustificativa()) + "<br><br></b>";
                        }
                    }
                }

                if (ocorrenciaLiberacaoDTO.getOcorrencia() != null) {
                    if (categoriaOcorrenciaDTO.getNome() != null && !categoriaOcorrenciaDTO.getNome().equals("")) {
                        if (categoriaOcorrenciaDTO.getNome().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.Suspensao.toString())
                                || categoriaOcorrenciaDTO.getNome().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.MudancaSLA.toString())) {
                            JustificativaRequisicaoLiberacaoDTO justificativaRequisicaoLiberacaoDTO = new JustificativaRequisicaoLiberacaoDTO();
                            if (ocorrenciaLiberacaoDTO.getIdJustificativa() != null) {
                                justificativaRequisicaoLiberacaoDTO.setIdJustificativaLiberacao(ocorrenciaLiberacaoDTO.getIdJustificativa());
                                justificativaRequisicaoLiberacaoDTO = (JustificativaRequisicaoLiberacaoDTO) justificativaRequisicaoLiberacaoService
                                        .restore(justificativaRequisicaoLiberacaoDTO);
                                if (justificativaRequisicaoLiberacaoDTO != null) {
                                    strBuffer += UtilI18N.internacionaliza(request, "citcorpore.comum.justificativa") + ": <b>"
                                            + justificativaRequisicaoLiberacaoDTO.getDescricaoJustificativa() + "<br><br></b>";
                                }
                            }
                            if (!UtilStrings.nullToVazio(ocorrenciaLiberacaoDTO.getComplementoJustificativa()).trim().equalsIgnoreCase("")) {
                                strBuffer += "<b>" + UtilStrings.nullToVazio(ocorrenciaLiberacaoDTO.getComplementoJustificativa()) + "<br><br></b>";
                            }
                        }
                    }
                }

                strBuffer += "</td>";
                strBuffer += "</tr>";
            }

        }

        strBuffer += "</table>";

        categoriaOcorrenciaDTO = null;
        return strBuffer;
    }

    public RequisicaoLiberacaoDTO gravarAnexoLiberacao(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response,
            final RequisicaoLiberacaoDTO requisicaoLiberacaoDTO) throws Exception {
        final Collection<UploadDTO> arquivosUpados = (Collection<UploadDTO>) request.getSession(true).getAttribute("colUploadsGED");
        final Collection<UploadDTO> arquivosUpadosDocsLegais = (Collection<UploadDTO>) request.getSession(true).getAttribute("colUploadsGEDdocsLegais");
        final Collection<UploadDTO> arquivosGerais = (Collection<UploadDTO>) request.getSession(true).getAttribute("colUploadGeraisGED");
        requisicaoLiberacaoDTO.setColDocsGerais(arquivosGerais);
        requisicaoLiberacaoDTO.setColArquivosUpload(arquivosUpados);
        requisicaoLiberacaoDTO.setColArquivosUploadDocsLegais(arquivosUpadosDocsLegais);
        // Rotina para gravar no banco
        if (requisicaoLiberacaoDTO.getColArquivosUpload() != null && requisicaoLiberacaoDTO.getColArquivosUpload().size() > 0
                || requisicaoLiberacaoDTO.getColArquivosUploadDocsLegais() != null && requisicaoLiberacaoDTO.getColArquivosUploadDocsLegais().size() > 0) {
            Integer idEmpresa = WebUtil.getIdEmpresa(request);
            if (idEmpresa == null) {
                idEmpresa = 1;
            }
            requisicaoLiberacaoDTO.setIdEmpresa(idEmpresa);
            // requisicaoLiberacaoService.gravaInformacoesGED(requisicaoLiberacaoDTO.getColArquivosUpload(), idEmpresa, requisicaoLiberacaoDTO, null);
            // document.alert(UtilI18N.internacionaliza(request, "MSG06"));
            // document.executeScript("('#POPUP_menuAnexos').dialog('close');");
        }
        if (requisicaoLiberacaoDTO.getColDocsGerais() != null && requisicaoLiberacaoDTO.getColDocsGerais().size() > 0) {
            Integer idEmpresa = WebUtil.getIdEmpresa(request);
            if (idEmpresa == null) {
                idEmpresa = 1;
            }
            requisicaoLiberacaoDTO.setIdEmpresa(idEmpresa);
            // requisicaoLiberacaoService.gravaInformacoesGED(requisicaoLiberacaoDTO.getColArquivosUpload(), idEmpresa, requisicaoLiberacaoDTO, null);
            // document.alert(UtilI18N.internacionaliza(request, "MSG06"));
            // document.executeScript("('#POPUP_menuAnexos').dialog('close');");
        }

        return requisicaoLiberacaoDTO;
    }

    protected void restaurarAnexos(final HttpServletRequest request, final RequisicaoLiberacaoDTO requisicaoLiberacaoDTO) throws ServiceException, Exception {
        final ControleGEDService controleGedService = (ControleGEDService) ServiceLocator.getInstance().getService(ControleGEDService.class, null);
        final Collection colAnexos = controleGedService.listByIdTabelaAndIdLiberacaoAndLigacao(ControleGEDDTO.TABELA_REQUISICAOLIBERACAO,
                requisicaoLiberacaoDTO.getIdRequisicaoLiberacao());
        final Collection<UploadDTO> colAnexosUploadDTO = controleGedService.convertListControleGEDToUploadDTO(colAnexos);

        /**
         * =================================
         * Restaura anexo(s) principal.
         * =================================
         * **/
        if (colAnexosUploadDTO != null) {
            for (final UploadDTO uploadDTO : colAnexosUploadDTO) {
                if (uploadDTO.getDescricao() == null) {
                    uploadDTO.setDescricao("");
                }
            }
        }

        request.getSession(true).setAttribute("colUploadsGED", colAnexosUploadDTO); // Anexo principal

    }

    protected void restaurarAnexosDocsGerais(final HttpServletRequest request, final RequisicaoLiberacaoDTO requisicaoLiberacaoDTO) throws ServiceException,
    Exception {
        final ControleGEDService controleGedService = (ControleGEDService) ServiceLocator.getInstance().getService(ControleGEDService.class, null);

        final Collection colDocsGeraisAnexos = controleGedService.listByIdTabelaAndIdLiberacaoAndLigacao(ControleGEDDTO.TABELA_DOCSGERAIS_REQUISICAOLIBERACAO,
                requisicaoLiberacaoDTO.getIdRequisicaoLiberacao());
        final Collection<UploadDTO> colAnexosDocsGeraisUploadDTO = controleGedService.convertListControleGEDToUploadDTO(colDocsGeraisAnexos);

        /**
         * =================================
         * Restaura anexo(s) Documentos gerais.
         * =================================
         * **/
        if (colAnexosDocsGeraisUploadDTO != null) {
            for (final UploadDTO uploadDTO : colAnexosDocsGeraisUploadDTO) {
                if (uploadDTO.getDescricao() == null) {
                    uploadDTO.setDescricao("");
                }
            }
        }

        request.getSession(true).setAttribute("colUploadGeraisGED", colAnexosDocsGeraisUploadDTO); // Anexo documentos legais

    }

    protected void restaurarAnexosDocsLegais(final HttpServletRequest request, final RequisicaoLiberacaoDTO requisicaoLiberacaoDTO) throws ServiceException,
    Exception {
        final ControleGEDService controleGedService = (ControleGEDService) ServiceLocator.getInstance().getService(ControleGEDService.class, null);

        final Collection colDocsLegaisAnexos = controleGedService.listByIdTabelaAndIdLiberacaoAndLigacao(ControleGEDDTO.TABELA_DOCSLEGAIS_REQUISICAOLIBERACAO,
                requisicaoLiberacaoDTO.getIdRequisicaoLiberacao());
        final Collection<UploadDTO> colAnexosDocsLegaisUploadDTO = controleGedService.convertListControleGEDToUploadDTO(colDocsLegaisAnexos);

        /**
         * =================================
         * Restaura anexo(s) Documentos legais.
         * =================================
         * **/
        if (colAnexosDocsLegaisUploadDTO != null) {
            for (final UploadDTO uploadDTO : colAnexosDocsLegaisUploadDTO) {
                if (uploadDTO.getDescricao() == null) {
                    uploadDTO.setDescricao("");
                }
            }
        }

        request.getSession(true).setAttribute("colUploadsGEDdocsLegais", colAnexosDocsLegaisUploadDTO); // Anexo documentos legais

    }

    public void mostraHistoricoLiberacoes(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response,
            final RequisicaoLiberacaoDTO requisicaoLiberacaoDTO) throws Exception {
        ServiceLocator.getInstance().getService(HistoricoLiberacaoService.class, null);
        final HistoricoMudancaService mudancaService = (HistoricoMudancaService) ServiceLocator.getInstance().getService(HistoricoMudancaService.class, null);
        /* RequisicaoLiberacaoDTO requisicaoLiberacaoDTO = (RequisicaoLiberacaoDTO) document.getBean(); */
        final Collection<String> colbaselines = new ArrayList<>();

        final HTMLElement divPrincipal = document.getElementById("contentBaseline");
        final StringBuilder subDiv = new StringBuilder();
        subDiv.append("" + "<div class='formBody'> " + "	<table id='tblBaselines' class='table table-bordered table-striped'> 	" + "		<thead>" + "			<tr>"
                + "				<th>" + UtilI18N.internacionaliza(request, "liberacao.baseline") + "</th>	" + "				<th>"
                + UtilI18N.internacionaliza(request, "liberacao.versaoHistorico") + "</th>	" + "				<th>"
                + UtilI18N.internacionaliza(request, "liberacao.executorModificacao") + "</th>	" + "				<th>"
                + UtilI18N.internacionaliza(request, "colaborador.colaborador") + "</th>	" + "				<th>"
                + UtilI18N.internacionaliza(request, "liberacao.idRequisicaoMudancaVinculada") + "</th>	" + "				<th>"
                + UtilI18N.internacionaliza(request, "itemConfiguracaoTree.historicoRestaurar") + "</th>	" + "			</tr>" + "		</thead>");
        final List<HistoricoMudancaDTO> listHistoricoMudancas = new ArrayList<HistoricoMudancaDTO>();
        List<HistoricoMudancaDTO> listHistoricoMudancasAux = new ArrayList<HistoricoMudancaDTO>();

        if (requisicaoLiberacaoDTO != null && requisicaoLiberacaoDTO.getColMudancas() != null) {
            for (final LiberacaoMudancaDTO requisicaoMudancaDTO : requisicaoLiberacaoDTO.getColMudancas()) {
                listHistoricoMudancasAux = mudancaService.listHistoricoMudancaByIdRequisicaoMudanca(requisicaoMudancaDTO.getIdRequisicaoMudanca());
                listHistoricoMudancas.addAll(listHistoricoMudancasAux);
                listHistoricoMudancasAux = null;
            }
        }

        if (listHistoricoMudancas != null) {
            int count = 0;
            boolean flag = false;
            document.executeScript("document.form.idHistoricoLiberacao.value = " + requisicaoLiberacaoDTO.getIdRequisicaoLiberacao());
            document.executeScript("countHistorico = 0");
            for (final HistoricoMudancaDTO historicoMudancaDTO : listHistoricoMudancas) {
                flag = historicoMudancaDTO.getBaseLine() != null && historicoMudancaDTO.getBaseLine().equals("SIM") ? true : false;
                String disabled = "";
                count++;
                final DecimalFormat df = new DecimalFormat("0.##");
                String versao = df.format(historicoMudancaDTO.getHistoricoVersao());
                versao = versao.replace(",", ".");
                document.executeScript("seqBaseline = " + count);
                if (flag) {
                    disabled = "disabled='disabled'";
                    colbaselines.add("idHistoricoMudanca" + count);
                }
                subDiv.append("<tbody>" + "	<tr>" + "		<td width='5%'>" + "<input type='checkbox' "
                        + disabled
                        + " id='idHistoricoMudanca"
                        + count
                        + "'"
                        + " name='idHistoricoMudanca"
                        + count
                        + "' value='0"
                        + historicoMudancaDTO.getIdHistoricoMudanca().toString()
                        + "'/></td>"
                        + "		<td>"
                        + versao
                        + " - "
                        + UtilDatas.convertDateToString(TipoDate.TIMESTAMP_WITH_SECONDS, historicoMudancaDTO.getDataHoraModificacao(),
                                WebUtil.getLanguage(request)) + "</td>" + "		<td width='15%'>"
                                + (historicoMudancaDTO.getNomeExecutorModificacao() == null ? "" : historicoMudancaDTO.getNomeExecutorModificacao()) + "</td>"
                                + "		<td>" + (historicoMudancaDTO.getNomeProprietario() == null ? "" : historicoMudancaDTO.getNomeProprietario()) + "</td>" + "		<td>"
                                + (historicoMudancaDTO.getIdRequisicaoMudanca() == null ? "" : historicoMudancaDTO.getIdRequisicaoMudanca()) + "</td>" + "		<td>"
                                + "			<a href='javascript:;' class='even' id='even-" + historicoMudancaDTO.getIdHistoricoMudanca() + "'>"
                                + "		<img src='../../template_new/images/icons/small/grey/documents.png' alt='"
                                + UtilI18N.internacionaliza(request, "itemConfiguracaoTree.historico") + "' " + "title='"
                                + UtilI18N.internacionaliza(request, "itemConfiguracaoTree.historico") + "' /></a>");
                if (flag) {
                    subDiv.append("		<a href='javascript:;' onclick='restaurarHistorico(\"" + historicoMudancaDTO.getIdHistoricoMudanca() + "\")'>"
                            + "			<img src='../../template_new/images/icons/small/grey/refresh_3.png' alt='"
                            + UtilI18N.internacionaliza(request, "itemConfiguracaoTree.restaurar") + "' " + "title='"
                            + UtilI18N.internacionaliza(request, "itemConfiguracaoTree.restaurar") + "' /></a>");
                }

                subDiv.append("		</td>" + "	</tr>" + "	<tr class='sel' id='sel-" + historicoMudancaDTO.getIdHistoricoMudanca() + "'>" + "	</tr>" + "</tbody>");
            }
        }
        subDiv.append("	</table>" + "</div>");
        divPrincipal.setInnerHTML(subDiv.toString());
        document.executeScript("marcarCheckbox(" + colbaselines + ")");
        for (final String str : colbaselines) {
            document.getCheckboxById(str).setChecked(true);
        }
    }

    /*
     * public void saveBaseline(DocumentHTML document, HttpServletRequest request, HttpServletResponse response) throws Exception {
     * UsuarioDTO usrDto = (UsuarioDTO) br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);
     * if(usrDto == null){
     * return;
     * }
     * HistoricoLiberacaoService historicoLiberacaoService = (HistoricoLiberacaoService)
     * ServiceLocator.getInstance().getService(HistoricoLiberacaoService.class, null);
     * List<HistoricoLiberacaoDTO> set = (ArrayList<HistoricoLiberacaoDTO>)
     * br.com.citframework.util.WebUtil.deserializeCollectionFromRequest(HistoricoLiberacaoDTO.class, "baselinesSerializadas" , request);
     * if(set!=null) {
     * for (HistoricoLiberacaoDTO hitoricoliberacaoDTO : set) {
     * HistoricoLiberacaoDTO novo = new HistoricoLiberacaoDTO();
     * novo.setBaseLine("SIM");
     * if(hitoricoliberacaoDTO.getIdHistoricoLiberacao() != null) {
     * novo.setIdHistoricoLiberacao(hitoricoliberacaoDTO.getIdHistoricoLiberacao());
     * novo = (HistoricoLiberacaoDTO) historicoLiberacaoService.restore(novo);
     * if(novo != null){
     * novo.setBaseLine("SIM");
     * historicoLiberacaoService.update(novo);
     * }
     * }
     * }
     * document.alert(UtilI18N.internacionaliza(request, "itemConfiguracaoTree.baselineGravadasSucesso"));
     * load(document, request, response);
     * }
     * }
     */
    public void saveBaseline(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usrDto = br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);
        if (usrDto == null) {
            return;
        }
        ServiceLocator.getInstance().getService(HistoricoMudancaService.class, null);
        final HistoricoMudancaDao historicoMudancaDao = new HistoricoMudancaDao();
        final List<HistoricoMudancaDTO> set = (ArrayList<HistoricoMudancaDTO>) br.com.citframework.util.WebUtil.deserializeCollectionFromRequest(
                HistoricoMudancaDTO.class, "baselinesSerializadas", request);
        if (set != null) {
            for (final HistoricoMudancaDTO historicoMudancaDTO : set) {
                HistoricoMudancaDTO novo = new HistoricoMudancaDTO();
                novo.setBaseLine("SIM");
                if (historicoMudancaDTO.getIdHistoricoMudanca() != null) {
                    novo.setIdHistoricoMudanca(historicoMudancaDTO.getIdHistoricoMudanca());
                    // novo = (HistoricoMudancaDTO) historicoMudancaService.restore(novo);
                    novo = (HistoricoMudancaDTO) historicoMudancaDao.restore(novo);
                    novo.setBaseLine("SIM");
                    historicoMudancaDao.update(novo);
                }
            }
            document.alert(UtilI18N.internacionaliza(request, "itemConfiguracaoTree.baselineGravadasSucesso"));
            this.load(document, request, response);
        }
    }

    /**
     * @param document
     * @param request
     * @param response
     * @throws Exception
     */
    public void carregaUnidade(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        final String validarComboUnidade = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.UNIDADE_VINC_CONTRATOS, "N");
        RequisicaoLiberacaoDTO requisicaoLiberacaoDto = (RequisicaoLiberacaoDTO) document.getBean();

        if (requisicaoLiberacaoDto.getIdRequisicaoLiberacao() != null && requisicaoLiberacaoDto.getIdRequisicaoLiberacao().intValue() > 0) {

            final RequisicaoLiberacaoService requisicaoLiberacaoService = (RequisicaoLiberacaoService) ServiceLocator.getInstance().getService(
                    RequisicaoLiberacaoService.class, null);

            final ServicoContratoService servicoContratoService = (ServicoContratoService) ServiceLocator.getInstance().getService(
                    ServicoContratoService.class, null);
            contratoDtoAux.setIdContrato(requisicaoLiberacaoDto.getIdContrato());

            requisicaoLiberacaoDto = (RequisicaoLiberacaoDTO) requisicaoLiberacaoService.restore(requisicaoLiberacaoDto);

            ServicoContratoDTO servicoContratoDTO = new ServicoContratoDTO();
            servicoContratoDTO.setIdServicoContrato(requisicaoLiberacaoDto.getIdContrato());
            if (requisicaoLiberacaoDto.getIdContrato() != null) {
                servicoContratoDTO = (ServicoContratoDTO) servicoContratoService.restore(servicoContratoDTO);
            } else {
                servicoContratoDTO = null;
            }
            if (servicoContratoDTO != null) {
                requisicaoLiberacaoDto.setIdRequisicaoLiberacao(servicoContratoDTO.getIdServico());
                requisicaoLiberacaoDto.setIdContrato(servicoContratoDTO.getIdContrato());
            }

        }

        if (requisicaoLiberacaoDto.getIdContrato() == null || requisicaoLiberacaoDto.getIdContrato().intValue() == 0) {
            requisicaoLiberacaoDto.setIdContrato(contratoDtoAux.getIdContrato());
        }

        final UnidadeService unidadeService = (UnidadeService) ServiceLocator.getInstance().getService(UnidadeService.class, null);
        final HTMLSelect comboUnidade = document.getSelectById("idUnidade");
        this.inicializarCombo(comboUnidade, request);
        if (validarComboUnidade.trim().equalsIgnoreCase("S")) {
            final Integer idContrato = requisicaoLiberacaoDto.getIdContrato();
            final ArrayList<UnidadeDTO> unidades = (ArrayList) unidadeService.listHierarquiaMultiContratos(idContrato);
            if (unidades != null) {
                for (final UnidadeDTO unidade : unidades) {
                    if (unidade.getDataFim() == null) {
                        comboUnidade.addOption(unidade.getIdUnidade().toString(), unidade.getNomeNivel());
                    }

                }
            }
        } else {
            final ArrayList<UnidadeDTO> unidades = (ArrayList) unidadeService.listHierarquia();
            if (unidades != null) {
                for (final UnidadeDTO unidade : unidades) {
                    if (unidade.getDataFim() == null) {
                        comboUnidade.addOption(unidade.getIdUnidade().toString(), unidade.getNomeNivel());
                    }
                }
            }
        }

        requisicaoLiberacaoDto = null;
    }

    private boolean isContratoInList(final Integer idContrato, final Collection colContratosColab) {
        if (colContratosColab != null) {
            for (final Iterator it = colContratosColab.iterator(); it.hasNext();) {
                final ContratosGruposDTO contratosGruposDTO = (ContratosGruposDTO) it.next();
                if (contratosGruposDTO.getIdContrato().intValue() == idContrato.intValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void verificaGrupoExecutor(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        RequisicaoLiberacaoDTO requisicaoLiberacaoDto = (RequisicaoLiberacaoDTO) document.getBean();
        if (requisicaoLiberacaoDto.getIdContrato() == null || requisicaoLiberacaoDto.getIdContrato().intValue() == 0) {
            requisicaoLiberacaoDto.setIdContrato(contratoDtoAux.getIdContrato());
        }
        String COLABORADORES_VINC_CONTRATOS = ParametroUtil.getValorParametroCitSmartHashMap(
                br.com.centralit.citcorpore.util.Enumerados.ParametroSistema.COLABORADORES_VINC_CONTRATOS, "N");
        if (COLABORADORES_VINC_CONTRATOS == null) {
            COLABORADORES_VINC_CONTRATOS = "N";
        }
        if (COLABORADORES_VINC_CONTRATOS.equalsIgnoreCase("S")) {
            final HTMLSelect idGrupoAtual = document.getSelectById("idGrupoAtual");
            idGrupoAtual.removeAllOptions();
            idGrupoAtual.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
            final GrupoService grupoSegurancaService = (GrupoService) ServiceLocator.getInstance().getService(GrupoService.class, null);
            final Collection colGrupos = grupoSegurancaService.listGruposServiceDeskByIdContrato(requisicaoLiberacaoDto.getIdContrato());
            if (colGrupos != null) {
                idGrupoAtual.addOptions(colGrupos, "idGrupo", "nome", null);
            }
        }

        /*
         * verificaGrupoExecutorInterno(document, requisicaoLiberacaoDto);
         */
        requisicaoLiberacaoDto = null;
    }

    /*
     * public void verificaGrupoExecutorInterno(DocumentHTML document, RequisicaoLiberacaoDTO requisicaoLiberacaoDto) throws Exception {
     * if (requisicaoLiberacaoDto.getIdRequisicaoLiberacao() == null || requisicaoLiberacaoDto.getIdContrato() == null)
     * return;
     * RequisicaoMudancaService servicoContratoService = (RequisicaoMudancaService) ServiceLocator.getInstance().getService(RequisicaoMudancaService.class,
     * null);
     * ServicoContratoDTO servicoContratoDto = servicoContratoService.findByIdContratoAndIdServico(requisicaoLiberacaoDto.getIdContrato(),
     * requisicaoLiberacaoDto.getIdRequisicaoLiberacao());
     * if (servicoContratoDto != null && servicoContratoDto.getIdGrupoExecutor() != null)
     * document.getElementById("idGrupoAtual").setValue("" + servicoContratoDto.getIdGrupoExecutor());
     * else
     * document.getElementById("idGrupoAtual").setValue("");
     * }
     */

    private RequisicaoLiberacaoDTO restauraRequisicaoLiberacaoDTOAux(final HttpServletRequest request) {
        final RequisicaoLiberacaoDTO requisicaoLiberacaoAuxDto = new RequisicaoLiberacaoDTO();
        return requisicaoLiberacaoAuxDto;
    }

    private RequisicaoLiberacaoService getRequisicaoLiberacaoService(final HttpServletRequest request) throws ServiceException, Exception {
        final RequisicaoLiberacaoService requisicaoLiberacaoServiceAux = (RequisicaoLiberacaoService) ServiceLocator.getInstance().getService(
                RequisicaoLiberacaoService.class, WebUtil.getUsuarioSistema(request));
        return requisicaoLiberacaoServiceAux;
    }

    public void verificarParametroAnexos(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        String DISKFILEUPLOAD_REPOSITORYPATH = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.DISKFILEUPLOAD_REPOSITORYPATH, "");
        if (DISKFILEUPLOAD_REPOSITORYPATH == null) {
            DISKFILEUPLOAD_REPOSITORYPATH = "";
        }
        if (DISKFILEUPLOAD_REPOSITORYPATH.equals("")) {
            throw new LogicException(UtilI18N.internacionaliza(request, "citcorpore.comum.anexosUploadSemParametro"));
        }
        final File pasta = new File(DISKFILEUPLOAD_REPOSITORYPATH);
        if (!pasta.exists()) {
            throw new LogicException(UtilI18N.internacionaliza(request, "citcorpore.comum.pastaIndicadaNaoExiste"));
        }
    }

    /*
     * public void restaurarBaseline(DocumentHTML document, HttpServletRequest request, HttpServletResponse response) throws Exception {
     * UsuarioDTO usrDto = (UsuarioDTO) br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);
     * if(usrDto == null){
     * return;
     * }
     * RequisicaoLiberacaoDTO requisicaoLiberacaoDTO = (RequisicaoLiberacaoDTO) document.getBean();
     * RequisicaoLiberacaoDTO requisicaoLiberacaoDTOAux = new RequisicaoLiberacaoDTO();
     * HistoricoLiberacaoDTO historicoLiberacaoDTO = new HistoricoLiberacaoDTO();
     * historicoLiberacaoDTO.setIdHistoricoLiberacao(Integer.parseInt(request.getParameter("idHistoricoLiberacao")));
     * HistoricoLiberacaoService historicoLiberacaoService = (HistoricoLiberacaoService)
     * ServiceLocator.getInstance().getService(HistoricoLiberacaoService.class, null);
     * RequisicaoLiberacaoService requisicaoLiberacaoService = (RequisicaoLiberacaoService)
     * ServiceLocator.getInstance().getService(RequisicaoLiberacaoService.class, null);
     * historicoLiberacaoDTO = (HistoricoLiberacaoDTO) historicoLiberacaoService.restore(historicoLiberacaoDTO);
     * //Realizando a Reflexão de Item de Configuração
     * Reflexao.copyPropertyValues(historicoLiberacaoDTO, requisicaoLiberacaoDTOAux);
     * //popula as collections de problema, mudança e ICs
     * //collection mudanças
     * Collection<LiberacaoMudancaDTO> colMudancas = new ArrayList<LiberacaoMudancaDTO>();
     * LiberacaoMudancaDao liberacaoMudancaDao = new LiberacaoMudancaDao();
     * colMudancas = liberacaoMudancaDao.listByIdHistoricoLiberacao(historicoLiberacaoDTO.getIdHistoricoLiberacao());
     * requisicaoLiberacaoDTOAux.setColMudancas(colMudancas);
     * //fim mudanças
     * //collection problemas
     * Collection<LiberacaoProblemaDTO> colProblemas = new ArrayList<LiberacaoProblemaDTO>();
     * LiberacaoProblemaDao liberacaoProblemaDao = new LiberacaoProblemaDao();
     * colProblemas = liberacaoProblemaDao.listByIdHistorico(historicoLiberacaoDTO.getIdHistoricoLiberacao());
     * requisicaoLiberacaoDTOAux.setColProblemas(colProblemas);
     * // fim problemas
     * //collection ICs
     * ArrayList<RequisicaoLiberacaoItemConfiguracaoDTO> listRequisicaoLiberacaoItemConfiguracaoDTO = new ArrayList<RequisicaoLiberacaoItemConfiguracaoDTO>();
     * RequisicaoLiberacaoItemConfiguracaoDao requisicaoLiberacaoItemConfiguracaoDao = new RequisicaoLiberacaoItemConfiguracaoDao();
     * listRequisicaoLiberacaoItemConfiguracaoDTO =
     * requisicaoLiberacaoItemConfiguracaoDao.findByIdHistoricoLiberacao(historicoLiberacaoDTO.getIdHistoricoLiberacao());
     * requisicaoLiberacaoDTOAux.setListRequisicaoLiberacaoItemConfiguracaoDTO(listRequisicaoLiberacaoItemConfiguracaoDTO);
     * //fim do bloco de população de collections.
     * // esse bloco preenche os anexos do historico
     * Collection<UploadDTO> listuploadDTO = new ArrayList<UploadDTO>();
     * ControleGEDDao controleGEDDao = new ControleGEDDao();
     * listuploadDTO = controleGEDDao.listByIdTabelaAndIdHistorico(ControleGEDDTO.TABELA_REQUISICAOLIBERACAO, historicoLiberacaoDTO.getIdHistoricoLiberacao());
     * requisicaoLiberacaoDTOAux.setColArquivosUpload(listuploadDTO);
     * // esse bloco preenche os anexos de documentos legais do historico
     * Collection<UploadDTO> listuploadDTODocsLegais = new ArrayList<UploadDTO>();
     * listuploadDTODocsLegais = controleGEDDao.listByIdTabelaAndIdHistorico(ControleGEDDTO.TABELA_DOCSLEGAIS_REQUISICAOLIBERACAO,
     * historicoLiberacaoDTO.getIdHistoricoLiberacao());
     * requisicaoLiberacaoDTOAux.setColArquivosUploadDocsLegais(listuploadDTODocsLegais);
     * // esse bloco preenche os anexos de documentos gerais do historico
     * Collection<UploadDTO> listuploadDTODocsGerais = new ArrayList<UploadDTO>();
     * listuploadDTODocsGerais = controleGEDDao.listByIdTabelaAndIdHistorico(ControleGEDDTO.TABELA_DOCSGERAIS_REQUISICAOLIBERACAO,
     * historicoLiberacaoDTO.getIdHistoricoLiberacao());
     * requisicaoLiberacaoDTOAux.setColDocsGerais(listuploadDTODocsGerais);
     * //fim do bloco de anexos
     * //esse bloco restaura a grid de papeis e responsabilidades
     * Collection<RequisicaoLiberacaoResponsavelDTO> colLiberacaoResponsavelDTOs = new ArrayList<RequisicaoLiberacaoResponsavelDTO>();
     * RequisicaoLiberacaoResponsavelDao respDao = new RequisicaoLiberacaoResponsavelDao();
     * colLiberacaoResponsavelDTOs = respDao.listByIdHistoricoLiberacao(historicoLiberacaoDTO.getIdHistoricoLiberacao());
     * requisicaoLiberacaoDTOAux.setColResponsaveis(colLiberacaoResponsavelDTOs);
     * //fim do bloco de responsaveis
     * //esse bloco restaura a grid de solicitacao de compras
     * Collection<RequisicaoLiberacaoRequisicaoComprasDTO> colLiberacaoComprasDTOs = new ArrayList<RequisicaoLiberacaoRequisicaoComprasDTO>();
     * RequisicaoLiberacaoRequisicaoComprasDAO comprasDao = new RequisicaoLiberacaoRequisicaoComprasDAO();
     * colLiberacaoComprasDTOs = comprasDao.findByIdLiberacaoAndIdHistorico(historicoLiberacaoDTO.getIdHistoricoLiberacao());
     * requisicaoLiberacaoDTOAux.setColRequisicaoCompras(colLiberacaoComprasDTOs);
     * //fim do bloco de responsaveis
     * // esse bloco restaura a grid de midias
     * Collection<RequisicaoLiberacaoMidiaDTO> colMidiaDTOs = new ArrayList<RequisicaoLiberacaoMidiaDTO>();
     * RequisicaoLiberacaoMidiaDao midiaDAO = new RequisicaoLiberacaoMidiaDao();
     * colMidiaDTOs = midiaDAO.listByIdHistoricoLiberacao(historicoLiberacaoDTO.getIdHistoricoLiberacao());
     * requisicaoLiberacaoDTOAux.setColMidia(colMidiaDTOs);
     * //fim do bloco de midias
     * UsuarioDTO usr = new UsuarioDTO();
     * usr.setIdUsuario(historicoLiberacaoDTO.getIdProprietario());
     * requisicaoLiberacaoDTOAux.setUsuarioDto(usr);
     * requisicaoLiberacaoService.update(requisicaoLiberacaoDTOAux);
     * document.setBean(requisicaoLiberacaoDTOAux);
     * document.executeScript("uploadAnexos.refresh()");
     * document.executeScript("uploadAnexosdocsLegais.refresh()");
     * document.executeScript("uploadAnexosDocsGerais.refresh()");
     * this.load(document, request, response);
     * String comando = "mostraMensagemRestaurarBaseline('" + UtilI18N.internacionaliza(request, "MSG15") + ".<br>" + UtilI18N.internacionaliza(request,
     * "requisicaoLiberacao.requisicaoLiberacao") + " <b><u>"
     * + requisicaoLiberacaoDTO.getIdRequisicaoLiberacao() + "</u></b> " + UtilI18N.internacionaliza(request, "citcorpore.comum.restaurada") + ".<br><br>"
     * + "Versão: " + UtilStrings.nullToVazio(requisicaoLiberacaoDTOAux.getVersao().toString()) + "<br>";
     * comando = comando + "')";
     * document.executeScript(comando);
     * }
     */

    public void restaurarBaseline(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usrDto = br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);
        final HistoricoMudancaDao historicoMudancaDao = new HistoricoMudancaDao();
        if (usrDto == null) {
            return;
        }
        final RequisicaoMudancaDTO requisicaoMudancaDTO = new RequisicaoMudancaDTO();
        final RequisicaoMudancaDTO requisicaoMudancaDTOAux = new RequisicaoMudancaDTO();
        HistoricoMudancaDTO historicoMudancaDTO = new HistoricoMudancaDTO();
        historicoMudancaDTO.setIdHistoricoMudanca(Integer.parseInt(request.getParameter("idHistoricoMudanca")));

        ServiceLocator.getInstance().getService(HistoricoMudancaService.class, null);
        final RequisicaoMudancaService requisicaoLiberacaoService = (RequisicaoMudancaService) ServiceLocator.getInstance().getService(
                RequisicaoMudancaService.class, null);
        historicoMudancaDTO = (HistoricoMudancaDTO) historicoMudancaDao.restore(historicoMudancaDTO);

        // Realizando a Reflexão de Item de Configuração
        Reflexao.copyPropertyValues(historicoMudancaDTO, requisicaoMudancaDTOAux);

        List<RequisicaoMudancaItemConfiguracaoDTO> colItemconfiguracao = new ArrayList<RequisicaoMudancaItemConfiguracaoDTO>();
        final RequisicaoMudancaItemConfiguracaoDao itemConfiguracaoDao = new RequisicaoMudancaItemConfiguracaoDao();
        colItemconfiguracao = (List<RequisicaoMudancaItemConfiguracaoDTO>) itemConfiguracaoDao.listByIdHistoricoMudanca(historicoMudancaDTO
                .getIdHistoricoMudanca());
        requisicaoMudancaDTOAux.setListRequisicaoMudancaItemConfiguracaoDTO(colItemconfiguracao);

        List<ProblemaMudancaDTO> colProblemas = new ArrayList<ProblemaMudancaDTO>();
        final ProblemaMudancaDAO problemaMudancaDAO = new ProblemaMudancaDAO();
        colProblemas = (List<ProblemaMudancaDTO>) problemaMudancaDAO.listByIdHistoricoMudanca(historicoMudancaDTO.getIdHistoricoMudanca());
        requisicaoMudancaDTOAux.setListProblemaMudancaDTO(colProblemas);

        List<RequisicaoMudancaRiscoDTO> colMudancaRiscoDTOs = new ArrayList<RequisicaoMudancaRiscoDTO>();
        final RequisicaoMudancaRiscoDao requisicaoMudancaRiscoDao = new RequisicaoMudancaRiscoDao();
        colMudancaRiscoDTOs = (List<RequisicaoMudancaRiscoDTO>) requisicaoMudancaRiscoDao.listByIdHistoricoMudanca(historicoMudancaDTO.getIdHistoricoMudanca());
        requisicaoMudancaDTOAux.setListRequisicaoMudancaRiscoDTO(colMudancaRiscoDTOs);

        List<AprovacaoMudancaDTO> aprovacaoMudancaDTOs = new ArrayList<AprovacaoMudancaDTO>();
        final AprovacaoMudancaDao aprovacaoMudancaDao = new AprovacaoMudancaDao();
        aprovacaoMudancaDTOs = (List<AprovacaoMudancaDTO>) aprovacaoMudancaDao.listByIdHistoricoMudanca(historicoMudancaDTO.getIdHistoricoMudanca());
        requisicaoMudancaDTOAux.setListAprovacaoMudancaDTO(aprovacaoMudancaDTOs);

        List<LiberacaoMudancaDTO> liberacaoMudancaDTOs = new ArrayList<LiberacaoMudancaDTO>();
        final LiberacaoMudancaDao liberacaoMudancaDao = new LiberacaoMudancaDao();
        liberacaoMudancaDTOs = (List<LiberacaoMudancaDTO>) liberacaoMudancaDao.listByIdHistoricoMudanca2(historicoMudancaDTO.getIdHistoricoMudanca());
        requisicaoMudancaDTOAux.setListLiberacaoMudancaDTO(liberacaoMudancaDTOs);

        List<RequisicaoMudancaServicoDTO> requisicaoMudancaServicoDTOs = new ArrayList<RequisicaoMudancaServicoDTO>();
        final RequisicaoMudancaServicoDao requisicaoMudancaServicoDao = new RequisicaoMudancaServicoDao();
        requisicaoMudancaServicoDTOs = (List<RequisicaoMudancaServicoDTO>) requisicaoMudancaServicoDao.listByIdHistoricoMudanca(historicoMudancaDTO
                .getIdHistoricoMudanca());
        requisicaoMudancaDTOAux.setListRequisicaoMudancaServicoDTO(requisicaoMudancaServicoDTOs);

        List<SolicitacaoServicoMudancaDTO> solicitacaoServicoMudancaDTOs = new ArrayList<SolicitacaoServicoMudancaDTO>();
        final List<SolicitacaoServicoDTO> solicitacaoServicos = new ArrayList<SolicitacaoServicoDTO>();
        SolicitacaoServicoDTO solicitacaoServicoDTO = new SolicitacaoServicoDTO();
        final SolicitacaoServicoMudancaDao solicitacaoServicoMudancaDao = new SolicitacaoServicoMudancaDao();
        solicitacaoServicoMudancaDTOs = (List<SolicitacaoServicoMudancaDTO>) solicitacaoServicoMudancaDao.listByIdHistoricoMudanca(historicoMudancaDTO
                .getIdHistoricoMudanca());
        for (final SolicitacaoServicoMudancaDTO solicitacaoServicoMudancaDTO : solicitacaoServicoMudancaDTOs) {
            solicitacaoServicoDTO.setIdRequisicaoMudanca(solicitacaoServicoMudancaDTO.getIdRequisicaoMudanca());
            solicitacaoServicoDTO.setIdSolicitacaoServico(solicitacaoServicoMudancaDTO.getIdSolicitacaoServico());
            solicitacaoServicos.add(solicitacaoServicoDTO);
            solicitacaoServicoDTO = new SolicitacaoServicoDTO();
        }
        requisicaoMudancaDTOAux.setListIdSolicitacaoServico(solicitacaoServicos);

        List<RequisicaoMudancaResponsavelDTO> respMudancaDTOs = new ArrayList<RequisicaoMudancaResponsavelDTO>();
        final RequisicaoMudancaResponsavelDao respMudancaDao = new RequisicaoMudancaResponsavelDao();
        respMudancaDTOs = (List<RequisicaoMudancaResponsavelDTO>) respMudancaDao.listByIdHistoricoMudanca(historicoMudancaDTO.getIdHistoricoMudanca());
        requisicaoMudancaDTOAux.setColResponsaveis(respMudancaDTOs);

        // esse bloco preenche os anexos do historico
        Collection<UploadDTO> listuploadDTO = new ArrayList<UploadDTO>();
        final ControleGEDDao controleGEDDao = new ControleGEDDao();
        listuploadDTO = controleGEDDao.listByIdTabelaAndIdHistorico(ControleGEDDTO.TABELA_REQUISICAOLIBERACAO, historicoMudancaDTO.getIdHistoricoMudanca());
        requisicaoMudancaDTOAux.setColArquivosUpload(listuploadDTO);

        final UsuarioDTO usr = new UsuarioDTO();
        usr.setIdUsuario(historicoMudancaDTO.getIdProprietario());
        requisicaoMudancaDTOAux.setUsuarioDto(usr);
        requisicaoLiberacaoService.update(requisicaoMudancaDTOAux, request);

        document.setBean(requisicaoMudancaDTOAux);

        document.executeScript("uploadAnexos.refresh()");
        // this.load(document, request, response);

        String comando = "mostraMensagemRestaurarBaseline('" + UtilI18N.internacionaliza(request, "MSG15") + ".<br>"
                + UtilI18N.internacionaliza(request, "requisicaoLiberacao.requisicaoLiberacao") + " <b><u>" + requisicaoMudancaDTO.getIdRequisicaoMudanca()
                + "</u></b> " + UtilI18N.internacionaliza(request, "citcorpore.comum.restaurada") + ".<br><br>" + "Versão: "
                + UtilStrings.nullToVazio(requisicaoMudancaDTOAux.getIdRequisicaoMudanca().toString()) + "<br>";
        comando = comando + "')";

        document.executeScript(comando);

    }

    public void limpar(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        request.getSession(true).setAttribute("colUploadsGED", null);
        request.getSession(true).setAttribute("colUploadsGEDdocsLegais", null);
        request.getSession(true).setAttribute("colUploadGeraisGED", null);

    }

    // inicio Area Maycon
    public void restaurarMidiaSoftware(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        document.getBean();

        final String idMidia = request.getParameter("midia#idMidiaSoftware");
        MidiaSoftwareDTO midiaSoftwareDTO = new MidiaSoftwareDTO();
        final HTMLTable tblMidia = document.getTableById("tblMidia");

        if (idMidia != null && idMidia.length() > 0) {
            midiaSoftwareDTO.setIdMidiaSoftware(new Integer(idMidia));

            final MidiaSoftwareService midiaService = (MidiaSoftwareService) ServiceLocator.getInstance().getService(MidiaSoftwareService.class,
                    WebUtil.getUsuarioSistema(request));
            midiaSoftwareDTO = (MidiaSoftwareDTO) midiaService.restore(midiaSoftwareDTO);
            if (midiaSoftwareDTO.getSequenciaLiberacao() == null) {
                tblMidia.addRow(midiaSoftwareDTO, new String[] {"", "idMidiaSoftware", "nome", ""}, new String[] {"idMidiaSoftware"},
                        "Liberação já cadastrado!", new String[] {"exibeIconesMidia"}, null, null);
            } else {
                tblMidia.updateRow(midiaSoftwareDTO, new String[] {"", "idMidiaSoftware", "nome", ""}, new String[] {"idMidiaSoftware"},
                        "Liberação já cadastrado!", new String[] {"exibeIconesMidia"}, null, null, midiaSoftwareDTO.getSequenciaLiberacao());
            }
            document.executeScript("HTMLUtils.applyStyleClassInAllCells('tblMidia', 'tblMidia');");
            document.executeScript("fecharMidia();");
        }
    }

    /**
     * Restaura o EmpregadoDTO com o Nome cargo a partir do ID Empregado informado.
     *
     * @param idEmpregado
     * @return Adiciona Responsável a tabela de tblResponsavel
     * @author maycon.fernandes
     *         31/10/2013 18:47
     */
    public void restaurarResponsavel(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        document.getBean();

        final String idResponsavel = request.getParameter("responsavel#idResponsavel");
        final String papelResponsavel = request.getParameter("responsavel#papelResponsavel");

        EmpregadoDTO empregadoDTO = new EmpregadoDTO();
        final HTMLTable tblResponsavel = document.getTableById("tblResponsavel");

        final RequisicaoLiberacaoResponsavelDTO requisicaoLiberacaoResponsavelDTO = new RequisicaoLiberacaoResponsavelDTO();

        if (idResponsavel != null && idResponsavel.length() > 0) {
            empregadoDTO.setIdEmpregado(new Integer(idResponsavel));
            final EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class,
                    WebUtil.getUsuarioSistema(request));
            try {
                empregadoDTO = empregadoService.restoreEmpregadoAndNomeCargoByIdEmpegado(empregadoDTO.getIdEmpregado());
                requisicaoLiberacaoResponsavelDTO.setNomeResponsavel(empregadoDTO.getNome());

                requisicaoLiberacaoResponsavelDTO.setIdResponsavel(empregadoDTO.getIdEmpregado());

                if (papelResponsavel != null && papelResponsavel.length() > 0) {
                    requisicaoLiberacaoResponsavelDTO.setPapelResponsavel(papelResponsavel);
                } else {
                    requisicaoLiberacaoResponsavelDTO.setPapelResponsavel("");
                }

                requisicaoLiberacaoResponsavelDTO.setTelResponsavel(empregadoDTO.getTelefone());
                requisicaoLiberacaoResponsavelDTO.setEmailResponsavel(empregadoDTO.getEmail());
                requisicaoLiberacaoResponsavelDTO.setNomeCargo(empregadoDTO.getNomeCargo());

                if (requisicaoLiberacaoResponsavelDTO.getSequenciaEmpregado() == null) {
                    tblResponsavel.addRow(requisicaoLiberacaoResponsavelDTO, new String[] {"", "idResponsavel", "nomeResponsavel", "nomeCargo",
                            "telResponsavel", "emailResponsavel", "papelResponsavel"}, new String[] {"idResponsavel"}, "Responsavel já cadastrado!",
                            new String[] {"exibeIconesResponsavel"}, null, null);
                } else {
                    tblResponsavel.updateRow(requisicaoLiberacaoResponsavelDTO, new String[] {"", "idResponsavel", "nomeResponsavel", "nomeCargo",
                            "telResponsavel", "emailResponsavel", "papelResponsavel"}, new String[] {"idResponsavel"}, "Responsavel já cadastrado!",
                            new String[] {"exibeIconesResponsavel"}, null, null, requisicaoLiberacaoResponsavelDTO.getSequenciaEmpregado());
                }
                document.executeScript("HTMLUtils.applyStyleClassInAllCells('tblResponsavel', 'tblResponsavel');");
                document.executeScript("fecharResponsavel();");
            } catch (final IndexOutOfBoundsException e) {
                /*
                 * @autor edu.braz
                 * 07/04/2014
                 * tratamento relacionado a colaboradores importados pelo ldap que não possui cargo e deve ser atribuido um cargo a eles
                 */
                document.executeScript("alert(i18n_message('requisicaoLiberacao.cargo'));");
            }
        }
    }

    private void carregaInformacoesComplementares(final DocumentHTML document, final HttpServletRequest request,
            final RequisicaoLiberacaoDTO requisicaoLiberacaoDTO) throws Exception {
        final RequisicaoLiberacaoService requisicaoLiberacaoService = (RequisicaoLiberacaoService) ServiceLocator.getInstance().getService(
                RequisicaoLiberacaoService.class, null);
        final TemplateSolicitacaoServicoService templateService = (TemplateSolicitacaoServicoService) ServiceLocator.getInstance().getService(
                TemplateSolicitacaoServicoService.class, br.com.centralit.citcorpore.util.WebUtil.getUsuarioSistema(request));
        document.executeScript("exibirInformacoesComplementares('" + requisicaoLiberacaoService.getUrlInformacoesComplementares(requisicaoLiberacaoDTO) + "');");
        final TemplateSolicitacaoServicoDTO templateDto = templateService.recuperaTemplateRequisicaoLiberacao(requisicaoLiberacaoDTO);

        if (templateDto != null) {
            if (templateDto.getAlturaDiv() != null) {
                document.executeScript("document.getElementById('divInformacoesComplementares').style.height = '" + templateDto.getAlturaDiv().intValue()
                        + "px';");
            }
        }
        document.executeScript("escondeJanelaAguarde()");
    }

    public void carregaQuestionario(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final RequisicaoLiberacaoDTO requisicaoLiberacaoDTO = (RequisicaoLiberacaoDTO) document.getBean();

        final RequisicaoLiberacaoService requisicaoLiberacaoService = (RequisicaoLiberacaoService) ServiceLocator.getInstance().getService(
                RequisicaoLiberacaoService.class, null);
        final TemplateSolicitacaoServicoService templateService = (TemplateSolicitacaoServicoService) ServiceLocator.getInstance().getService(
                TemplateSolicitacaoServicoService.class, br.com.centralit.citcorpore.util.WebUtil.getUsuarioSistema(request));
        document.executeScript("exibirInformacoesComplementares('" + requisicaoLiberacaoService.getUrlInformacoesQuestionario(requisicaoLiberacaoDTO) + "');");
        final TemplateSolicitacaoServicoDTO templateDto = templateService.recuperaTemplateRequisicaoLiberacao(requisicaoLiberacaoDTO);

        if (templateDto != null) {
            if (templateDto.getAlturaDiv() != null) {
                document.executeScript("document.getElementById('divInformacoesComplementares').style.height = '" + templateDto.getAlturaDiv().intValue()
                        + "px';");
            }

        }
        document.executeScript("escondeJanelaAguarde()");
    }

    /**
     * Adiciona automaticamente o(s) Item(s) de configuração relacionados há uma mudança que esta sendo relacionado a esta liberação
     *
     * @param document
     * @param request
     * @param response
     * @throws br.com.citframework.excecao.ServiceException
     * @throws Exception
     */
    public void relacionaICMudanca(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws br.com.citframework.excecao.ServiceException, Exception {

        final RequisicaoLiberacaoDTO liberacaoDto = (RequisicaoLiberacaoDTO) document.getBean();
        final RequisicaoMudancaItemConfiguracaoService mudancaICService = (RequisicaoMudancaItemConfiguracaoService) ServiceLocator.getInstance().getService(
                RequisicaoMudancaItemConfiguracaoService.class, null);
        ItemConfiguracaoDTO icDto = new ItemConfiguracaoDTO();
        final ItemConfiguracaoService icService = (ItemConfiguracaoService) ServiceLocator.getInstance().getService(ItemConfiguracaoService.class, null);

        final Collection<RequisicaoMudancaItemConfiguracaoDTO> mudancaICs = mudancaICService.listByIdRequisicaoMudanca(liberacaoDto.getIdICMudanca());

        if (mudancaICs != null) {
            for (final RequisicaoMudancaItemConfiguracaoDTO m : mudancaICs) {

                final RequisicaoLiberacaoItemConfiguracaoDTO requisicaoLiberacaoICDTO = new RequisicaoLiberacaoItemConfiguracaoDTO();

                icDto.setIdItemConfiguracao(m.getIdItemConfiguracao());
                icDto = (ItemConfiguracaoDTO) icService.restore(icDto);
                if (icDto != null) {
                    final StatusIC status = StatusIC.getStatus(icDto.getStatus() == null ? 1 : icDto.getStatus());
                    final String nomeStatus = status.getDescricao();
                    requisicaoLiberacaoICDTO.setIdItemConfiguracao(icDto.getIdItemConfiguracao());
                    requisicaoLiberacaoICDTO.setNomeStatusIc(nomeStatus);
                    requisicaoLiberacaoICDTO.setNomeItemConfiguracao(icDto.getIdentificacao());
                    final HTMLTable tblic = document.getTableById("tblICs");

                    tblic.addRow(requisicaoLiberacaoICDTO, new String[] {"", "idItemConfiguracao", "nomeItemConfiguracao", "nomeStatusIc"},
                            new String[] {"idItemConfiguracao"}, UtilI18N.internacionaliza(request, "requisicaoLiberacao.icMudancaJaAdicionado"),
                            new String[] {"gerarImgDelIc"}, null, null);
                }
            }
            document.alert(UtilI18N.internacionaliza(request, "requisicaoLiberacao.icMudancaAdicionado"));
        }
    }

    public void tabelaMudancaSituacaoLiberacao(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        final SituacaoLiberacaoMudancaService situacaoService = (SituacaoLiberacaoMudancaService) ServiceLocator.getInstance().getService(
                SituacaoLiberacaoMudancaService.class, null);
        final HTMLSelect comboSituacaoLiberacao = document.getSelectById("idFechamentoCategoria");
        document.getSelectById("idFechamentoCategoria").addOption("", "-- " + UtilI18N.internacionaliza(request, "citcorpore.comum.todos") + " --");
        final Collection<SituacaoLiberacaoMudancaDTO> listaSituacao = situacaoService.listAll();
        comboSituacaoLiberacao.removeAllOptions();
        comboSituacaoLiberacao.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));

        if (listaSituacao != null) {
            for (final SituacaoLiberacaoMudancaDTO lista : listaSituacao) {
                comboSituacaoLiberacao.addOption(lista.getSituacao(), lista.getSituacao());
            }
        }

    }

    public void addICs(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        final RequisicaoLiberacaoDTO requisicaoLiberacaoDTO = (RequisicaoLiberacaoDTO) document.getBean();

        ItemConfiguracaoDTO icDto = new ItemConfiguracaoDTO();
        final ItemConfiguracaoService icService = (ItemConfiguracaoService) ServiceLocator.getInstance().getService(ItemConfiguracaoService.class, null);

        ServiceLocator.getInstance().getService(RequisicaoLiberacaoItemConfiguracaoService.class, null);
        final RequisicaoLiberacaoItemConfiguracaoDTO requisicaoLiberacaoICDTO = new RequisicaoLiberacaoItemConfiguracaoDTO();
        if (requisicaoLiberacaoDTO != null) {

            icDto.setIdItemConfiguracao(requisicaoLiberacaoDTO.getIdItemConfig());
            if (icDto.getIdItemConfiguracao() != null) {
                icDto = (ItemConfiguracaoDTO) icService.restore(icDto);

                final StatusIC status = StatusIC.getStatus(icDto.getStatus());
                if (status != null) {
                    final String nomeStatus = status.getDescricao();
                    requisicaoLiberacaoICDTO.setNomeStatusIc(nomeStatus);
                }
                requisicaoLiberacaoICDTO.setIdItemConfiguracao(requisicaoLiberacaoDTO.getIdItemConfig());
                requisicaoLiberacaoICDTO.setNomeItemConfiguracao(icDto.getIdentificacao());
            }
            final HTMLTable tblic = document.getTableById("tblICs");

            tblic.addRow(requisicaoLiberacaoICDTO, new String[] {"", "idItemConfiguracao", "nomeItemConfiguracao", "nomeStatusIc", ""},
                    new String[] {"idItemConfiguracao"}, UtilI18N.internacionaliza(request, "citcorpore.comum.itemSelecionado"), new String[] {"gerarImgDelIc",
            "exibeIconesIC"}, null, null);

            document.executeScript("$('#POPUP_PESQUISAITEMCONFIGURACAO').dialog('close')");
        }

    }

    public void restoreTableIcs(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final RequisicaoLiberacaoDTO requisicaoLiberacaoDTO = (RequisicaoLiberacaoDTO) document.getBean();

        ItemConfiguracaoDTO icDto = new ItemConfiguracaoDTO();
        final ItemConfiguracaoService icService = (ItemConfiguracaoService) ServiceLocator.getInstance().getService(ItemConfiguracaoService.class, null);

        final RequisicaoLiberacaoItemConfiguracaoService requisicaoLiberacaoItemConfiguracaoService = (RequisicaoLiberacaoItemConfiguracaoService) ServiceLocator
                .getInstance().getService(RequisicaoLiberacaoItemConfiguracaoService.class, null);
        final RequisicaoLiberacaoItemConfiguracaoDTO requisicaoLiberacaoICDTO = new RequisicaoLiberacaoItemConfiguracaoDTO();
        if (requisicaoLiberacaoDTO != null) {
            requisicaoLiberacaoICDTO.setIdRequisicaoLiberacao(requisicaoLiberacaoDTO.getIdRequisicaoLiberacao());
            final Collection<RequisicaoLiberacaoItemConfiguracaoDTO> col = requisicaoLiberacaoItemConfiguracaoService
                    .findByIdRequisicaoLiberacao(requisicaoLiberacaoICDTO.getIdRequisicaoLiberacao());
            if (col != null) {
                for (final RequisicaoLiberacaoItemConfiguracaoDTO obj : col) {

                    icDto.setIdItemConfiguracao(obj.getIdItemConfiguracao());
                    icDto = (ItemConfiguracaoDTO) icService.restore(icDto);

                    final StatusIC status = StatusIC.getStatus(icDto.getStatus());
                    if (status != null) {
                        final String nomeStatus = status.getDescricao();
                        obj.setNomeStatusIc(nomeStatus);
                    }
                    obj.setNomeItemConfiguracao(icDto.getIdentificacao());
                    final HTMLTable tblic = document.getTableById("tblICs");
                    tblic.deleteAllRows();

                    tblic.addRowsByCollection(col, new String[] {"", "idItemConfiguracao", "nomeItemConfiguracao", "nomeStatusIc", ""}, null, "", new String[] {
                            "gerarImgDelIc", "exibeIconesIC"}, null, null);

                }
            }

        }

    }

    private void preencherComboSituacao(final DocumentHTML document, final HttpServletRequest request, final RequisicaoLiberacaoDTO requisicaoLiberacaoDTO)
            throws Exception {

        final UsuarioDTO usuarioDto = br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);

        final HTMLSelect comboStatus = document.getSelectById("status");
        comboStatus.removeAllOptions();
        comboStatus.addOption("EmExecucao", UtilI18N.internacionaliza(request, "liberacao.emExecucao"));
        comboStatus.addOption("Resolvida", UtilI18N.internacionaliza(request, "citcorpore.comum.resolvida"));
        comboStatus.addOption("NaoResolvida", UtilI18N.internacionaliza(request, "requisicaoLiberacao.naoResolvida"));

        final GrupoService grupoService = (GrupoService) ServiceLocator.getInstance().getService(GrupoService.class, null);
        final Collection<GrupoDTO> lstGrupos = grupoService.getGruposByEmpregado(usuarioDto.getIdEmpregado());

        if (lstGrupos != null) {
            for (final GrupoDTO g : lstGrupos) {
                if (this.getRequisicaoLiberacaoService(request).verificaPermissaoGrupoCancelar(requisicaoLiberacaoDTO.getIdTipoLiberacao(), g.getIdGrupo())) {
                    comboStatus.addOption("Cancelada", UtilI18N.internacionaliza(request, "liberacao.cancelada"));
                    break;
                }
            }
        }

    }

    public void preencherComboCategoriaSolucao(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {

        final CategoriaSolucaoService categoriaSolucaoService = (CategoriaSolucaoService) ServiceLocator.getInstance().getService(
                CategoriaSolucaoService.class, null);
        final Collection colCategSolucao = categoriaSolucaoService.listHierarquia();
        final HTMLSelect idCategoriaSolucao = document.getSelectById("idCategoriaSolucao");
        idCategoriaSolucao.removeAllOptions();
        idCategoriaSolucao.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
        if (colCategSolucao != null) {
            idCategoriaSolucao.addOptions(colCategSolucao, "idCategoriaSolucao", "descricaoCategoriaNivel", null);
        }
    }

    public static Boolean salvaGrupoAtvPeriodicaEAgenda(final RequisicaoLiberacaoDTO requisicaoLiberacaoDto) throws ServiceException, Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(requestGlobal);
        if (usuario == null) {
            documentGlobal.alert(UtilI18N.internacionaliza(requestGlobal, "citcorpore.comum.sessaoExpirada"));
            documentGlobal.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + requestGlobal.getContextPath() + "'");
            return false;
        }

        final AtividadePeriodicaDTO atividadePeriodicaDTO = new AtividadePeriodicaDTO();
        atividadePeriodicaDTO.setIdRequisicaoLiberacao(requisicaoLiberacaoDto.getIdRequisicaoLiberacao());

        atividadePeriodicaDTO.setDuracaoEstimada((int) calculaDuracaoEstimada(requisicaoLiberacaoDto));
        atividadePeriodicaDTO.setIdGrupoAtvPeriodica(requisicaoLiberacaoDto.getIdGrupoAtvPeriodica());
        atividadePeriodicaDTO.setDataInicio(transformaDataStringEmDate(requisicaoLiberacaoDto.getDataInicioStr()));
        atividadePeriodicaDTO.setHoraInicio(requisicaoLiberacaoDto.getHoraAgendamentoInicial());

        if (atividadePeriodicaDTO.getDuracaoEstimada() == null || atividadePeriodicaDTO.getDuracaoEstimada().intValue() == 0) {
            return false;
        } else if (requisicaoLiberacaoDto.getIdGrupoAtvPeriodica() == null) {
            return false;
        }
        ServiceLocator.getInstance().getService(RequisicaoLiberacaoService.class, WebUtil.getUsuarioSistema(requestGlobal));
        final GrupoAtvPeriodicaService grupoAtvPeriodicaService = (GrupoAtvPeriodicaService) ServiceLocator.getInstance().getService(
                GrupoAtvPeriodicaService.class, null);
        // RequisicaoMudancaDTO requisicaoMudancaDto = requisicaoMudancaService.restoreAll(atividadePeriodicaDTO.getIdRequisicaoMudanca());
        String orient = "";
        String ocorr = "";
        if (!ocorr.equalsIgnoreCase("")) {
            ocorr += "\n";
        }
        ocorr += UtilI18N.internacionaliza(requestGlobal, "gerenciaservico.agendaratividade.dataagendamento") + " "
                + UtilDatas.dateToSTR(atividadePeriodicaDTO.getDataInicio());
        if (!ocorr.equalsIgnoreCase("")) {
            ocorr += "\n";
        }
        ocorr += UtilI18N.internacionaliza(requestGlobal, "gerenciaservico.agendaratividade.horaagendamento") + " " + atividadePeriodicaDTO.getHoraInicio();
        if (!ocorr.equalsIgnoreCase("")) {
            ocorr += "\n";
        }
        ocorr += UtilI18N.internacionaliza(requestGlobal, "gerenciaservico.agendaratividade.duracaoestimada") + " "
                + atividadePeriodicaDTO.getDuracaoEstimada();
        GrupoAtvPeriodicaDTO grupoAtvPeriodicaDTO = new GrupoAtvPeriodicaDTO();
        grupoAtvPeriodicaDTO.setIdGrupoAtvPeriodica(atividadePeriodicaDTO.getIdGrupoAtvPeriodica());
        grupoAtvPeriodicaDTO = (GrupoAtvPeriodicaDTO) grupoAtvPeriodicaService.restore(grupoAtvPeriodicaDTO);
        if (grupoAtvPeriodicaDTO != null) {
            ocorr += "\n" + UtilI18N.internacionaliza(requestGlobal, "gerenciaservico.agendaratividade.grupo") + ": "
                    + grupoAtvPeriodicaDTO.getNomeGrupoAtvPeriodica();
        }
        if (atividadePeriodicaDTO.getOrientacaoTecnica() != null) {
            orient = atividadePeriodicaDTO.getOrientacaoTecnica();
            ocorr += "\n" + UtilI18N.internacionaliza(requestGlobal, "gerenciaservico.agendaratividade.orientacaotecnica") + ": \n"
                    + atividadePeriodicaDTO.getOrientacaoTecnica();
        }
        orient += "\n\n" + UtilI18N.internacionaliza(requestGlobal, "requisicaoLiberacao.requisicaoLiberacao") + ": \n" + requisicaoLiberacaoDto.getDescricao();

        atividadePeriodicaDTO.setTituloAtividade(UtilI18N.internacionaliza(requestGlobal, "gerenciaservico.agendaratividade.solicitacaoliberacao") + " "
                + atividadePeriodicaDTO.getIdRequisicaoLiberacao());
        atividadePeriodicaDTO.setDescricao(requisicaoLiberacaoDto.getDescricao());
        atividadePeriodicaDTO.setDataCriacao(UtilDatas.getDataAtual());
        atividadePeriodicaDTO.setCriadoPor(usuario.getNomeUsuario());
        atividadePeriodicaDTO.setIdContrato(requisicaoLiberacaoDto.getIdContrato());
        atividadePeriodicaDTO.setOrientacaoTecnica(orient);

        final Collection colItens = new ArrayList<>();
        final ProgramacaoAtividadeDTO programacaoAtividadeDTO = new ProgramacaoAtividadeDTO();
        programacaoAtividadeDTO.setTipoAgendamento("U");
        programacaoAtividadeDTO.setDataInicio(atividadePeriodicaDTO.getDataInicio());
        programacaoAtividadeDTO.setHoraInicio(atividadePeriodicaDTO.getHoraInicio());
        programacaoAtividadeDTO.setHoraFim("00:00");
        programacaoAtividadeDTO.setDuracaoEstimada(atividadePeriodicaDTO.getDuracaoEstimada());
        programacaoAtividadeDTO.setRepeticao("N");
        colItens.add(programacaoAtividadeDTO);

        final AtividadePeriodicaService atividadePeriodicaService = (AtividadePeriodicaService) ServiceLocator.getInstance().getService(
                AtividadePeriodicaService.class, null);
        atividadePeriodicaDTO.setColItens(colItens);

        // verifica se já não houve agendamento para essa requisição
        final Collection<AtividadePeriodicaDTO> listAtividade = atividadePeriodicaService.findByIdRequisicaoLiberacao(requisicaoLiberacaoDto
                .getIdRequisicaoLiberacao());
        if (listAtividade != null) {
            atividadePeriodicaDTO.setIdAtividadePeriodica(listAtividade.iterator().next().getIdAtividadePeriodica());
            atividadePeriodicaService.update(atividadePeriodicaDTO);
        } else {
            atividadePeriodicaService.create(atividadePeriodicaDTO);
        }

        final OcorrenciaSolicitacaoService ocorrenciaSolicitacaoService = (OcorrenciaSolicitacaoService) ServiceLocator.getInstance().getService(
                OcorrenciaSolicitacaoService.class, null);
        final OcorrenciaSolicitacaoDTO ocorrenciaSolicitacaoDTO = new OcorrenciaSolicitacaoDTO();
        ocorrenciaSolicitacaoDTO.setIdSolicitacaoServico(atividadePeriodicaDTO.getIdSolicitacaoServico());
        ocorrenciaSolicitacaoDTO.setDataregistro(UtilDatas.getDataAtual());
        ocorrenciaSolicitacaoDTO.setHoraregistro(UtilDatas.formatHoraFormatadaStr(UtilDatas.getHoraAtual()));
        ocorrenciaSolicitacaoDTO.setTempoGasto(0);
        ocorrenciaSolicitacaoDTO.setDescricao(Enumerados.CategoriaOcorrencia.Agendamento.getDescricao());
        ocorrenciaSolicitacaoDTO.setDataInicio(UtilDatas.getDataAtual());
        ocorrenciaSolicitacaoDTO.setDataFim(UtilDatas.getDataAtual());
        ocorrenciaSolicitacaoDTO.setInformacoesContato(UtilI18N.internacionaliza(requestGlobal, "MSG013"));
        ocorrenciaSolicitacaoDTO.setRegistradopor(usuario.getNomeUsuario());
        ocorrenciaSolicitacaoDTO.setOcorrencia(ocorr);
        ocorrenciaSolicitacaoDTO.setOrigem(Enumerados.OrigemOcorrencia.OUTROS.getSigla().toString());
        ocorrenciaSolicitacaoDTO.setCategoria(Enumerados.CategoriaOcorrencia.Agendamento.getSigla());
        ocorrenciaSolicitacaoDTO.setIdItemTrabalho(requisicaoLiberacaoDto.getIdTarefa());
        ocorrenciaSolicitacaoService.create(ocorrenciaSolicitacaoDTO);

        return true;
    }

    public static long calculaDuracaoEstimada(final RequisicaoLiberacaoDTO requisicaoLiberacaoDto) {
        final long duracao = requisicaoLiberacaoDto.getDataHoraTerminoAgendada().getTime() - requisicaoLiberacaoDto.getDataHoraInicioAgendada().getTime();
        final long minutos = duracao / (1000 * 60);
        return minutos;
    }

    public static java.sql.Date transformaDataStringEmDate(final String dataSemFormatacao) throws ParseException {
        final DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        final java.sql.Date data = new java.sql.Date(fmt.parse(dataSemFormatacao).getTime());
        return data;

    }

    public void listarPlanos(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws ServiceException,
    Exception {
        final Integer idmudanca = Integer.parseInt(request.getParameter("idMudanca"));
        // document.executeScript("uploadPlanoDeReversaoLiberacao.clear()");
        this.restaurarAnexosPlanoDeReversao(document, request, idmudanca);
        document.executeScript("$('#POPUP_PLANOSREVERSAO').dialog('open')");
    }

    protected void restaurarAnexosPlanoDeReversao(final DocumentHTML document, final HttpServletRequest request, final Integer idRequisicaoMudanca,
            final Collection<UploadDTO> colUploadsGED) throws ServiceException, Exception {
        Collection<UploadDTO> colAnexosUploadDTO = null;
        final ControleGEDService controleGedService = (ControleGEDService) ServiceLocator.getInstance().getService(ControleGEDService.class, null);
        final Collection colAnexos = controleGedService.listByIdTabelaAndIdLiberacaoAndLigacao(ControleGEDDTO.TABELA_PLANO_REVERSAO_MUDANCA,
                idRequisicaoMudanca);
        colAnexosUploadDTO = controleGedService.convertListControleGEDToUploadDTO(colAnexos);

        /**
         * =================================
         * Restaura anexo(s) principal.
         * =================================
         * **/
        if (colAnexosUploadDTO != null) {
            for (final UploadDTO uploadDTO : colAnexosUploadDTO) {
                if (uploadDTO.getDescricao() == null) {
                    uploadDTO.setDescricao("");
                }
                if (uploadDTO.getVersao() == null) {
                    uploadDTO.setVersao("");
                }
            }
        }

        if (colUploadsGED != null) {
            for (final UploadDTO uploadDTO : colUploadsGED) {
                if (uploadDTO.getDescricao() == null) {
                    uploadDTO.setDescricao("");
                }
                if (uploadDTO.getVersao() == null) {
                    uploadDTO.setVersao("");
                }
                if (uploadDTO.getIdMudanca() == null) {
                    uploadDTO.setIdMudanca(idRequisicaoMudanca.toString());
                }
                if (idRequisicaoMudanca.equals(new Integer(uploadDTO.getIdMudanca()))) {
                    colAnexosUploadDTO.add(uploadDTO);
                }
            }
        }
        request.getSession(true).setAttribute("colUploadPlanoDeReversaoLiberacaoGED", colAnexosUploadDTO);
    }

    public void gravarAnexosPlanosReversao(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws ServiceException, Exception {

        final RequisicaoLiberacaoDTO requisicaoLiberacaoDTO = (RequisicaoLiberacaoDTO) document.getBean();
        final RequisicaoMudancaDTO requisicaoMudancaDto = new RequisicaoMudancaDTO();
        requisicaoMudancaDto.setIdRequisicaoMudanca(requisicaoLiberacaoDTO.getIdMudanca());
        final HistoricoMudancaDTO historicoMudancaDTO = new HistoricoMudancaDTO();
        Integer idEmpresa = null;
        final RequisicaoMudancaService requisicaoMudancaService = (RequisicaoMudancaService) ServiceLocator.getInstance().getService(
                RequisicaoMudancaService.class, WebUtil.getUsuarioSistema(request));
        final Collection<UploadDTO> arquivosReversaoUpados = (Collection<UploadDTO>) request.getSession(true).getAttribute(
                "colUploadPlanoDeReversaoLiberacaoGED");
        requisicaoMudancaDto.setColUploadPlanoDeReversaoGED(arquivosReversaoUpados);
        // Rotina para gravar no banco
        if (requisicaoMudancaDto.getColUploadPlanoDeReversaoGED() != null && requisicaoMudancaDto.getColUploadPlanoDeReversaoGED().size() > 0) {
            idEmpresa = WebUtil.getIdEmpresa(request);
            if (idEmpresa == null) {
                idEmpresa = 1;
            }
            /* requisicaoMudancaService.gravaInformacoesGED(requisicaoMudancaDto.getColArquivosUpload(), idEmpresa, requisicaoMudancaDto, null); */
            document.alert(UtilI18N.internacionaliza(request, "MSG06"));
            // document.executeScript("('#POPUP_menuAnexos').dialog('close');");
        }
        requisicaoMudancaDto.setIdEmpresa(idEmpresa);
        requisicaoMudancaService.gravaPlanoDeReversaoGED(requisicaoMudancaDto, null, historicoMudancaDTO);

    }

    protected void restaurarAnexosPlanoDeReversao(final DocumentHTML document, final HttpServletRequest request, final Integer idRequisicaoMudanca)
            throws ServiceException, Exception {
        Collection<UploadDTO> colAnexosUploadDTO = null;
        final ControleGEDService controleGedService = (ControleGEDService) ServiceLocator.getInstance().getService(ControleGEDService.class, null);
        final Collection colAnexos = controleGedService.listByIdTabelaAndIdLiberacaoAndLigacao(ControleGEDDTO.TABELA_PLANO_REVERSAO_MUDANCA,
                idRequisicaoMudanca);
        colAnexosUploadDTO = controleGedService.convertListControleGEDToUploadDTO(colAnexos);
        request.getSession(true).setAttribute("colUploadPlanoDeReversaoLiberacaoGED", colAnexosUploadDTO);
        document.executeScript("uploadPlanoDeReversaoLiberacao.refresh()");
    }
}
// fim area geber

