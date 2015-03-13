package br.com.centralit.citcorpore.ajaxForms;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.htmlparser.jericho.Source;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.bpm.dto.TarefaFluxoDTO;
import br.com.centralit.citajax.html.AjaxFormAction;
import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citajax.html.HTMLCheckbox;
import br.com.centralit.citajax.html.HTMLForm;
import br.com.centralit.citajax.html.HTMLSelect;
import br.com.centralit.citajax.html.HTMLTable;
import br.com.centralit.citcorpore.bean.BaseConhecimentoDTO;
import br.com.centralit.citcorpore.bean.CalendarioDTO;
import br.com.centralit.citcorpore.bean.CategoriaOcorrenciaDTO;
import br.com.centralit.citcorpore.bean.CategoriaProblemaDTO;
import br.com.centralit.citcorpore.bean.CausaIncidenteDTO;
import br.com.centralit.citcorpore.bean.ClienteDTO;
import br.com.centralit.citcorpore.bean.ConhecimentoProblemaDTO;
import br.com.centralit.citcorpore.bean.ContratoDTO;
import br.com.centralit.citcorpore.bean.ContratosGruposDTO;
import br.com.centralit.citcorpore.bean.EmailSolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.EmpregadoDTO;
import br.com.centralit.citcorpore.bean.FornecedorDTO;
import br.com.centralit.citcorpore.bean.GrupoDTO;
import br.com.centralit.citcorpore.bean.ItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.JustificativaProblemaDTO;
import br.com.centralit.citcorpore.bean.LocalidadeDTO;
import br.com.centralit.citcorpore.bean.LocalidadeUnidadeDTO;
import br.com.centralit.citcorpore.bean.OcorrenciaProblemaDTO;
import br.com.centralit.citcorpore.bean.OrigemAtendimentoDTO;
import br.com.centralit.citcorpore.bean.OrigemOcorrenciaDTO;
import br.com.centralit.citcorpore.bean.PastaDTO;
import br.com.centralit.citcorpore.bean.ProblemaDTO;
import br.com.centralit.citcorpore.bean.ProblemaItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.ProblemaMudancaDTO;
import br.com.centralit.citcorpore.bean.ProblemaRelacionadoDTO;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaDTO;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.ServicoContratoDTO;
import br.com.centralit.citcorpore.bean.ServicoDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoProblemaDTO;
import br.com.centralit.citcorpore.bean.SolucaoContornoDTO;
import br.com.centralit.citcorpore.bean.SolucaoDefinitivaDTO;
import br.com.centralit.citcorpore.bean.TemplateSolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.UnidadeDTO;
import br.com.centralit.citcorpore.bean.UnidadesAccServicosDTO;
import br.com.centralit.citcorpore.bean.UploadDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.integracao.OcorrenciaProblemaDAO;
import br.com.centralit.citcorpore.mail.MensagemEmail;
import br.com.centralit.citcorpore.negocio.CalendarioService;
import br.com.centralit.citcorpore.negocio.CategoriaProblemaService;
import br.com.centralit.citcorpore.negocio.CategoriaSolucaoService;
import br.com.centralit.citcorpore.negocio.CausaIncidenteService;
import br.com.centralit.citcorpore.negocio.ClienteService;
import br.com.centralit.citcorpore.negocio.ConhecimentoProblemaService;
import br.com.centralit.citcorpore.negocio.ContatoProblemaService;
import br.com.centralit.citcorpore.negocio.ContratoService;
import br.com.centralit.citcorpore.negocio.ContratosGruposService;
import br.com.centralit.citcorpore.negocio.EmailSolicitacaoServicoService;
import br.com.centralit.citcorpore.negocio.EmpregadoService;
import br.com.centralit.citcorpore.negocio.ExecucaoProblemaService;
import br.com.centralit.citcorpore.negocio.FornecedorService;
import br.com.centralit.citcorpore.negocio.GrupoService;
import br.com.centralit.citcorpore.negocio.ItemConfiguracaoService;
import br.com.centralit.citcorpore.negocio.JustificativaProblemaService;
import br.com.centralit.citcorpore.negocio.LocalidadeService;
import br.com.centralit.citcorpore.negocio.LocalidadeUnidadeService;
import br.com.centralit.citcorpore.negocio.OcorrenciaProblemaService;
import br.com.centralit.citcorpore.negocio.OrigemAtendimentoService;
import br.com.centralit.citcorpore.negocio.PastaService;
import br.com.centralit.citcorpore.negocio.ProblemaItemConfiguracaoService;
import br.com.centralit.citcorpore.negocio.ProblemaMudancaService;
import br.com.centralit.citcorpore.negocio.ProblemaService;
import br.com.centralit.citcorpore.negocio.RequisicaoMudancaService;
import br.com.centralit.citcorpore.negocio.ServicoContratoService;
import br.com.centralit.citcorpore.negocio.ServicoService;
import br.com.centralit.citcorpore.negocio.SolicitacaoServicoProblemaService;
import br.com.centralit.citcorpore.negocio.SolicitacaoServicoService;
import br.com.centralit.citcorpore.negocio.SolucaoContornoService;
import br.com.centralit.citcorpore.negocio.SolucaoDefinitivaService;
import br.com.centralit.citcorpore.negocio.TemplateSolicitacaoServicoService;
import br.com.centralit.citcorpore.negocio.UnidadeService;
import br.com.centralit.citcorpore.negocio.UnidadesAccServicosService;
import br.com.centralit.citcorpore.negocio.UsuarioService;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoRequisicaoProblema;
import br.com.centralit.citcorpore.util.Enumerados.TipoDate;
import br.com.centralit.citcorpore.util.Enumerados.TipoOrigemLeituraEmail;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.centralit.citged.bean.ControleGEDDTO;
import br.com.centralit.citged.negocio.ControleGEDService;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilHTML;
import br.com.citframework.util.UtilI18N;
import br.com.citframework.util.UtilStrings;
import br.com.citframework.util.WebUtil;

/**
 * @author breno.guimaraes
 *
 */
public class Problema extends AjaxFormAction {

    private ProblemaService problemaService;
    private EmpregadoService usuarioService;
    private ProblemaItemConfiguracaoService problemaItemConfiguracaoService;
    private ItemConfiguracaoService itemConfiguracaoService;
    private PastaService pastaService;
    private SolicitacaoServicoProblemaService solicitacaoServicoProblemaService;
    private UsuarioDTO usuario;

    private ProblemaMudancaService problemaMudancaService;
    private RequisicaoMudancaService requisicaoMudancaService;

    ContratoDTO contratoDtoAux = new ContratoDTO();

    private ProblemaDTO problemaDto;
    private Boolean acao = false;

    @Override
    public Class getBeanClass() {
        return ProblemaDTO.class;
    }

    public ProblemaDTO getProblemaDto() {
        return problemaDto;
    }

    public void setProblemaDto(final ProblemaDTO problemaDto) {
        this.problemaDto = problemaDto;
    }

    @Override
    public void load(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        final String UNIDADE_AUTOCOMPLETE = ParametroUtil.getValorParametroCitSmartHashMap(
                br.com.centralit.citcorpore.util.Enumerados.ParametroSistema.UNIDADE_AUTOCOMPLETE, "N");
        StringBuilder objeto;
        if (UNIDADE_AUTOCOMPLETE != null && UNIDADE_AUTOCOMPLETE.equalsIgnoreCase("S")) {
            objeto = new StringBuilder();
            objeto.append("<select  id='idContrato' name='idContrato' class='Valid[Required] Description[\"contrato.contrato\"]' ");
            objeto.append("onchange='setaValorLookup(this);'>");
            objeto.append("</select>");
            document.getElementById("divContrato").setInnerHTML(objeto.toString());

            objeto = new StringBuilder();
            objeto.append("<input type='text' name='unidadeDes' id='unidadeDes' style='width: 100%;' onkeypress='onkeypressUnidadeDes();'>");
            objeto.append("<input type='hidden' name='idUnidade' id='idUnidade' value='0'/>");
            document.getElementById("divUnidade").setInnerHTML(objeto.toString());
            document.executeScript("montaParametrosAutocompleteUnidade()");
        } else {
            objeto = new StringBuilder();
            objeto.append("<select  id='idContrato' name='idContrato' class='Valid[Required] Description[\"contrato.contrato\"]' ");
            objeto.append("onchange='setaValorLookup(this);' onclick= 'document.form.fireEvent(\"carregaUnidade\");'>");
            objeto.append("</select>");
            document.getElementById("divContrato").setInnerHTML(objeto.toString());

            objeto = new StringBuilder();
            objeto.append("<select name='idUnidade' id = 'idUnidade' onchange='document.form.fireEvent(\"preencherComboLocalidade\")' class='Valid[Required] Description[colaborador.cadastroUnidade]'></select>");
            document.getElementById("divUnidade").setInnerHTML(objeto.toString());
        }

        /**
         * Adicionado para fazer limpeza da seção quando for gerenciamento de Serviço
         *
         * @author mario.junior
         * @since 28/10/2013 08:21
         */
        request.getSession(true).setAttribute("colUploadRequisicaoProblemaGED", null);
        problemaDto = (ProblemaDTO) document.getBean();

        document.executeScript("$('#abas').hide()");
        document.executeScript("$('#statusProblema').hide()");
        document.executeScript("$('#divResolvido').hide()");
        document.executeScript("$('#divBotaoFecharFrame').hide()");
        document.executeScript("$('#statusCancelada').hide()");

        // Início do load.
        if (problemaDto == null || problemaDto.getIdProblema() == null) {
            document.getElementById("btOcorrencias").setVisible(false);
        }

        final String descricaoSolicitacao = (String) request.getSession().getAttribute("DescricaoSolicitacao");
        request.getSession().removeAttribute("DescricaoSolicitacao");
        final String iframeSolicitacao = request.getParameter("solicitacaoServico");

        if (descricaoSolicitacao != null && !descricaoSolicitacao.equalsIgnoreCase("")) {
            document.getElementById("DescricaoAuxliar").setInnerHTML(descricaoSolicitacao);
            document.executeScript("setarDescricao()");
        }

        document.getElementById("enviaEmailCriacao").setDisabled(true);

        document.getElementById("enviaEmailFinalizacao").setDisabled(true);

        usuario = br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);

        if (usuario == null) {

            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));

            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");

            return;
        }

        final ContratosGruposService contratosGruposService = (ContratosGruposService) ServiceLocator.getInstance().getService(ContratosGruposService.class,
                null);

        final ContratoService contratoService = (ContratoService) ServiceLocator.getInstance().getService(ContratoService.class, null);

        final ClienteService clienteService = (ClienteService) ServiceLocator.getInstance().getService(ClienteService.class, null);

        final FornecedorService fornecedorService = (FornecedorService) ServiceLocator.getInstance().getService(FornecedorService.class, null);

        alimentaComboPastasBaseConhecimento(request, document);

        final HTMLSelect idGrupoAtual = document.getSelectById("idGrupo");

        idGrupoAtual.removeAllOptions();

        idGrupoAtual.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));

        ServiceLocator.getInstance().getService(GrupoService.class, null);

        final GrupoService grupoSegurancaService = (GrupoService) ServiceLocator.getInstance().getService(GrupoService.class, null);

        final Collection colGrupos = grupoSegurancaService.listaGruposAtivos();

        if (colGrupos != null) {
            idGrupoAtual.addOptions(colGrupos, "idGrupo", "nome", null);
        }

        String COLABORADORES_VINC_CONTRATOS = ParametroUtil.getValorParametroCitSmartHashMap(
                br.com.centralit.citcorpore.util.Enumerados.ParametroSistema.COLABORADORES_VINC_CONTRATOS, "N");

        if (COLABORADORES_VINC_CONTRATOS == null) {
            COLABORADORES_VINC_CONTRATOS = "N";
        }

        Collection colContratosColab = null;

        if (COLABORADORES_VINC_CONTRATOS.equalsIgnoreCase("S")) {
            colContratosColab = contratosGruposService.findByIdEmpregado(usuario.getIdEmpregado());
        }

        final Collection colContratos = contratoService.list();

        document.getSelectById("idContrato").removeAllOptions();

        final Collection<ContratoDTO> listaContratos = new ArrayList<ContratoDTO>();

        if (colContratos != null) {

            if (colContratos.size() > 1) {

                document.getSelectById("idContrato").addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));

            } else {
                acao = true;
            }

            for (final Iterator it = colContratos.iterator(); it.hasNext();) {

                final ContratoDTO contratoDto = (ContratoDTO) it.next();

                if (contratoDto.getDeleted() == null || !contratoDto.getDeleted().equalsIgnoreCase("y")) {

                    // Se parametro de colaboradores por contrato ativo, entao filtra.
                    if (COLABORADORES_VINC_CONTRATOS.equalsIgnoreCase("S")) {

                        if (colContratosColab == null) {
                            continue;
                        }
                        if (!isContratoInList(contratoDto.getIdContrato(), colContratosColab)) {
                            continue;
                        }
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
        if (acao) {
            carregaServicosMulti(document, request, response);
            if (UNIDADE_AUTOCOMPLETE != null && !UNIDADE_AUTOCOMPLETE.equalsIgnoreCase("S")) {
                carregaUnidade(document, request, response);
            }
        }

        alimentaComboCategoriaProblema(request, document);

        preencherComboCausa(document, request, response);

        preencherComboCategoriaSolucao(document, request, response);

        this.preencherComboOrigem(document, request, response);

        if (request.getParameter("chamarTelaProblema") != null && !request.getParameter("chamarTelaProblema").equalsIgnoreCase("")) {
            final Integer tarefa = obterIdTarefa(problemaDto, request);
            if (tarefa > 0) {
                problemaDto.setIdTarefa(tarefa);
            }
        }

        String tarefaAssociada = "N";

        if (problemaDto != null && problemaDto.getIdTarefa() != null) {

            tarefaAssociada = "S";

            request.setAttribute("tarefaAssociada", tarefaAssociada);
        }

        if (problemaDto != null && problemaDto.getIdContrato() != null) {
            document.getElementById("idContrato").setValue("" + problemaDto.getIdContrato());
        }

        if (problemaDto != null && problemaDto.getIdProblema() != null) {
            restore(document, request, response);
        }

        carregarInformacaoProblemaAnaliseTendencia(document, request, response);

        document.getElementById("iframeSolicitacao").setValue(iframeSolicitacao);

        problemaDto = null;
    }

    /**
     * @param document
     * @param request
     * @param response
     * @throws ServiceException
     * @throws Exception
     * @author maycon.silva
     *
     *         O método carrega as informações de um problema caso haja tendencia identifcada no relatorio de Analise de Tendência
     *
     */
    private void carregarInformacaoProblemaAnaliseTendencia(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws ServiceException, Exception {
        if (request.getParameter("tendenciaProblema") != null && request.getParameter("tendenciaProblema").equalsIgnoreCase("S")) {
            final String tipo = request.getParameter("tipo");

            if (request.getParameter("idContrato") != null && !request.getParameter("idContrato").equalsIgnoreCase("")) {
                getProblemaDto().setIdContrato(new Integer(request.getParameter("idContrato")));

                final String UNIDADE_AUTOCOMPLETE = ParametroUtil.getValorParametroCitSmartHashMap(
                        br.com.centralit.citcorpore.util.Enumerados.ParametroSistema.UNIDADE_AUTOCOMPLETE, "N");
                if (UNIDADE_AUTOCOMPLETE != null && !UNIDADE_AUTOCOMPLETE.equalsIgnoreCase("S")) {
                    carregaUnidade(document, request, response);
                }

                document.executeScript("setaValorLookup('" + getProblemaDto().getIdContrato() + "');");
            }

            if (tipo != null && !tipo.trim().equalsIgnoreCase("")) {
                if (tipo.equalsIgnoreCase("servico")) {
                    preencheServicoProblemaByTendenciaProblema(document, request, response);
                }
                if (tipo.equalsIgnoreCase("causa")) {
                    preencheCausaProblemaByTendenciaProblema(document, request, response);
                }

                if (tipo.equalsIgnoreCase("itemConfiguracao")) {
                    preencheItemConfiguracaoProblemaByTendenciaProblema(document, request, response);
                }
            }

            final HTMLForm form = document.getForm("form");
            form.setValues(problemaDto);
        }
    }

    /**
     * Preenche o serviço caso seja uma tendencio do tipo serviço, e seta o serviço na descrição do problema
     *
     * @param document
     * @param request
     * @param response
     * @throws ServiceException
     * @throws Exception
     * @author maycon.silva
     *
     *
     */
    private void preencheServicoProblemaByTendenciaProblema(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws ServiceException, Exception {
        if (request.getParameter("id") != null && !request.getParameter("id").equalsIgnoreCase("")) {
            final ServicoContratoService servicoContratoService = (ServicoContratoService) ServiceLocator.getInstance().getService(
                    ServicoContratoService.class, null);
            ServicoContratoDTO servicoContratoDTO = new ServicoContratoDTO();
            servicoContratoDTO.setIdServicoContrato(new Integer(request.getParameter("id")));
            servicoContratoDTO = servicoContratoService.findByIdServicoContrato(servicoContratoDTO.getIdServicoContrato(), getProblemaDto().getIdContrato());
            getProblemaDto().setDescricao("Serviço do Contrato: " + servicoContratoDTO.getNomeServico());
        }
    }

    /**
     * Preenche a Causa caso seja uma tendencio do tipo Causa, e seta o Causa na descrição do problema
     *
     * @param document
     * @param request
     * @param response
     * @throws ServiceException
     * @throws Exception
     * @author maycon.silva
     *
     *
     */
    private void preencheCausaProblemaByTendenciaProblema(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws ServiceException, Exception {
        if (request.getParameter("id") != null && !request.getParameter("id").equalsIgnoreCase("")) {
            final CausaIncidenteService causaIncidenteService = (CausaIncidenteService) ServiceLocator.getInstance().getService(CausaIncidenteService.class,
                    null);
            CausaIncidenteDTO causaIncidenteDTO = new CausaIncidenteDTO();
            causaIncidenteDTO.setIdCausaIncidente(new Integer(request.getParameter("id")));
            causaIncidenteDTO = (CausaIncidenteDTO) causaIncidenteService.restore(causaIncidenteDTO);
            getProblemaDto().setDescricao("Causa: " + causaIncidenteDTO.getDescricaoCausa());
            getProblemaDto().setIdCausa(new Integer(request.getParameter("id")));
        }
    }

    /**
     * Preenche o Item configuração caso seja uma tendencio do tipo Item configuração, e seta o Item configuração na descrição do problema
     *
     * @param document
     * @param request
     * @param response
     * @throws ServiceException
     * @throws Exception
     * @author maycon.silva
     *
     *
     */
    private void preencheItemConfiguracaoProblemaByTendenciaProblema(final DocumentHTML document, final HttpServletRequest request,
            final HttpServletResponse response) throws ServiceException, Exception {
        if (request.getParameter("id") != null && !request.getParameter("id").equalsIgnoreCase("")) {
            final ItemConfiguracaoService itemConfiguracaoService = (ItemConfiguracaoService) ServiceLocator.getInstance().getService(
                    ItemConfiguracaoService.class, null);
            ItemConfiguracaoDTO itemConfiguracaoDto = new ItemConfiguracaoDTO();
            itemConfiguracaoDto = itemConfiguracaoService.restoreByIdItemConfiguracao(new Integer(request.getParameter("id")));
            getProblemaDto().setDescricao("Item Configuração: " + itemConfiguracaoDto.getIdentificacao());
        }
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

    private void alimentaComboPastasBaseConhecimento(final HttpServletRequest request, final DocumentHTML document) throws Exception {
        final HTMLSelect combo = document.getSelectById("idPastaBaseConhecimento");
        inicializaCombo(request, combo);
        getPastaService().consultarPastasAtivas();
        final ArrayList<PastaDTO> listaPastaAux = (ArrayList<PastaDTO>) getPastaService().listPastasESubpastas(usuario);
        for (final PastaDTO pasta : listaPastaAux) {
            if (pasta.getDataFim() == null) {
                combo.addOption(pasta.getId().toString(), pasta.getNomeNivel());
            }
        }
    }

    /**
     * Carrega combo de Origem do conhecimento
     *
     * @param document
     * @param request
     * @throws Exception
     * @author thays.araujo
     */
    public void preencherComboOrigem(final DocumentHTML document, final HttpServletRequest request) throws Exception {

        final HTMLSelect combo = document.getSelectById("comboOrigem");
        combo.removeAllOptions();

        combo.addOption(BaseConhecimentoDTO.CONHECIMENTO.toString(), UtilI18N.internacionaliza(request, "baseConhecimento.conhecimento"));
        combo.addOption(BaseConhecimentoDTO.EVENTO.toString(), UtilI18N.internacionaliza(request, "justificacaoFalhas.evento"));
        combo.addOption(BaseConhecimentoDTO.MUDANCA.toString(), UtilI18N.internacionaliza(request, "requisicaMudanca.mudanca"));
        combo.addOption(BaseConhecimentoDTO.INCIDENTE.toString(), UtilI18N.internacionaliza(request, "solicitacaoServico.incidente"));
        combo.addOption(BaseConhecimentoDTO.SERVICO.toString(), UtilI18N.internacionaliza(request, "servico.servico"));
        combo.addOption(BaseConhecimentoDTO.PROBLEMA.toString(), UtilI18N.internacionaliza(request, "problema.problema"));
    }

    /**
     * @param document
     * @param request
     * @param response
     * @throws Exception
     * @author breno.guimaraes
     */
    public void restore(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        /**
         * Adicionado para fazer limpeza da seção quando for gerenciamento de Serviço
         *
         * @author mario.junior
         * @since 28/10/2013 08:21
         */
        request.getSession(true).setAttribute("colUploadRequisicaoProblemaGED", null);
        setProblemaDto((ProblemaDTO) document.getBean());

        final UsuarioDTO usuarioDto = br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);

        final ProblemaDTO problemaAuxDto = getProblemaDto();

        if (request.getParameter("chamarTelaProblema") != null && !request.getParameter("chamarTelaProblema").equalsIgnoreCase("")) {
            setProblemaDto(getProblemaService(request).restoreAll(problemaDto));
        } else {
            setProblemaDto(getProblemaService(request).restoreAll(problemaDto.getIdProblema()));
        }

        document.executeScript("$('#abas').show()");

        document.executeScript("$('#statusProblema').show()");
        document.executeScript("$('#divProblemaGrave').show()");
        document.executeScript("$('#divPrecisaMudanca').show()");
        document.executeScript("$('#divPrecisaSolucaoContorno').show()");

        atualizaGridProblema(document, request, response);

        final Integer idTarefa = problemaAuxDto.getIdTarefa();

        final String acaoFluxo = problemaAuxDto.getAcaoFluxo();

        final String escalar = problemaAuxDto.getEscalar();

        final String alterarSituacao = problemaAuxDto.getAlterarSituacao();

        final String fase = problemaAuxDto.getFase();

        final String editar = problemaAuxDto.getEditar();

        ServiceLocator.getInstance().getService(ContatoProblemaService.class, null);

        if (problemaDto.getPrecisaSolucaoContorno() != null) {
            // inicio: thiago.monteiro
            if (problemaDto.getPrecisaSolucaoContorno().equalsIgnoreCase("S")) {

                document.executeScript("$('input[type=radio][name=precisaSolucaoContorno][value=\"S\"]').attr('checked', true)");

            } else {

                document.executeScript("$('input[type=radio][name=precisaSolucaoContorno][value=\"N\"]').attr('checked', true)");

            }
        }

        if (problemaDto.getPrecisaMudanca() != null) {
            if (problemaDto.getPrecisaMudanca().equalsIgnoreCase("S")) {
                document.executeScript("$('#abaMudancas').show()");
                document.executeScript("$('input[type=radio][name=precisaMudanca][value=\"S\"]').attr('checked', true)");

            } else {

                document.executeScript("$('input[type=radio][name=precisaMudanca][value=\"N\"]').attr('checked', true)");

            }
        }

        if (problemaDto.getGrave() != null) {
            if (problemaDto.getGrave().equalsIgnoreCase("S")) {
                document.executeScript("$('#abaRevisaoProblemaGrave').show()");
                document.executeScript("$('input[type=radio][name=grave][value=\"S\"]').attr('checked', true)");

            } else {

                document.executeScript("$('input[type=radio][name=grave][value=\"N\"]').attr('checked', true)");

            }
        }

        if (problemaDto.getPrecisaSolucaoContorno() != null) {
            if (problemaDto.getPrecisaSolucaoContorno().equalsIgnoreCase("S")) {

                // Utilizando o evento click para evitar o erro relacionado ao recarregamento do form.
                document.executeScript("$('input[type=radio][name=precisaSolucaoContorno][value=\"S\"]').click()");

            } else {

                document.executeScript("$('input[type=radio][name=precisaSolucaoContorno][value=\"N\"]').click()");

            }
            // fim: thiago.monteiro
        }

        new HTMLCheckbox("acompanhamento", document);

        final OrigemAtendimentoService origemAtendimentoService = (OrigemAtendimentoService) ServiceLocator.getInstance().getService(
                OrigemAtendimentoService.class, null);

        OrigemAtendimentoDTO origemAtendimentoDto = new OrigemAtendimentoDTO();

        if (problemaDto.getIdOrigemAtendimento() != null && problemaDto.getIdOrigemAtendimento() > 0) {

            origemAtendimentoDto.setIdOrigem(problemaDto.getIdOrigemAtendimento());

            origemAtendimentoDto = (OrigemAtendimentoDTO) origemAtendimentoService.restore(origemAtendimentoDto);

        }

        atribuirNomeProprietarioECriadorParaRequisicaoDto(problemaDto);

        atualizaInformacoesRelacionamentos(document);

        alimentaComboCategoriaProblema(request, document);

        final String UNIDADE_AUTOCOMPLETE = ParametroUtil.getValorParametroCitSmartHashMap(
                br.com.centralit.citcorpore.util.Enumerados.ParametroSistema.UNIDADE_AUTOCOMPLETE, "N");
        if (UNIDADE_AUTOCOMPLETE != null && UNIDADE_AUTOCOMPLETE.equalsIgnoreCase("S")) {
            final UnidadeService unidadeService = (UnidadeService) ServiceLocator.getInstance().getService(UnidadeService.class, null);
            UnidadeDTO unidadeDTO = new UnidadeDTO();
            unidadeDTO.setIdUnidade(problemaDto.getIdUnidade());
            unidadeDTO = (UnidadeDTO) unidadeService.restore(unidadeDTO);
            if (unidadeDTO != null) {
                problemaDto.setUnidadeDes(unidadeDTO.getNome());
            }
        } else {
            restoreComboUnidade(problemaDto, document, request, response);
        }

        restoreComboLocalidade(problemaDto, document, request, response);

        restoreComboOrigemAtendimento(problemaDto, document, request, response);

        preencherComboCausa(document, request, response);

        carregaProblemaRelacionado(request, document, problemaAuxDto);

        /**
         * @author geber.costa verifica se no banco o acompanhamento está setado como 'N' ou 'S'
         * */

        if (problemaDto.getAcompanhamento() == null || problemaDto.getAcompanhamento().equalsIgnoreCase("N")
                || problemaDto.getAcompanhamento().equalsIgnoreCase("")) {

            document.getElementById("acompanhamento").setValue("N");

        } else if (problemaDto.getAcompanhamento().equals("S")) {

            document.getElementById("acompanhamento").setValue("S");

        }

        final SolucaoContornoDTO solucaoContorno = verificaSolucaoContorno(request, response, document, getProblemaDto());
        final SolucaoDefinitivaDTO solucaoDefinitiva = verificaSolucaoDefinitiva(request, response, document, getProblemaDto());

        getProblemaDto().setIdTarefa(idTarefa);

        getProblemaDto().setAcaoFluxo(acaoFluxo);

        getProblemaDto().setEscalar(escalar);

        getProblemaDto().setAlterarSituacao(alterarSituacao);

        getProblemaDto().setFase(fase);

        if (problemaAuxDto.getChamarTelaProblema() != null && problemaAuxDto.getChamarTelaProblema().equalsIgnoreCase("S")) {
            problemaDto.setChamarTelaProblema(problemaAuxDto.getChamarTelaProblema());
            document.executeScript("$('#divBotoes').hide()");
            document.executeScript("$('#divBotaoFecharFrame').show()");
        }

        if (solucaoContorno != null) {
            getProblemaDto().setIdSolucaoContorno(solucaoContorno.getIdSolucaoContorno());
            getProblemaDto().setTituloSolCon(solucaoContorno.getTitulo());
            getProblemaDto().setDescSolCon(solucaoContorno.getDescricao());
        }
        if (solucaoDefinitiva != null) {
            getProblemaDto().setIdSolucaoDefinitiva(solucaoDefinitiva.getIdSolucaoDefinitiva());
            getProblemaDto().setTituloSolDefinitiva(solucaoDefinitiva.getTitulo());
            getProblemaDto().setDescSolDefinitiva(solucaoDefinitiva.getDescricao());
        }

        verificaUltimaAtualizacao(document, request, problemaDto.getIdProblema());
        if (problemaDto.getCausaRaiz() != null && problemaDto.getSolucaoContorno() != null) {
            if (!problemaDto.getCausaRaiz().equalsIgnoreCase("") && !problemaDto.getSolucaoContorno().equalsIgnoreCase("")) {
                document.executeScript("$('#relacionarErrosConhecidos').show()");
            }
        }

        document.setBean(problemaDto);

        final HTMLForm form = document.getForm("form");

        form.clear();

        if (problemaDto.getStatus() != null && problemaDto.getStatus().equalsIgnoreCase(SituacaoRequisicaoProblema.Registrada.getDescricao())) {
            document.executeScript("$('#rotuloCausaRaiz').removeClass('campoObrigatorio')");
            document.executeScript("$('#rotuloSolucaoContorno').removeClass('campoObrigatorio')");
        }

        if (problemaDto.getIdContrato() != null) {
            document.getSelectById("idContrato").setDisabled(true);
        }

        if (problemaDto.getIdCategoriaProblema() != null) {
            document.getSelectById("idCategoriaProblema").setDisabled(true);
        }

        form.setValues(problemaDto);
        String statusSetado = "";
        if (problemaDto.getStatus().equalsIgnoreCase("Registrada") || problemaDto.getStatus().equalsIgnoreCase("Registrado")) {
            statusSetado = "<input type='radio' id='status' name='status' value='" + problemaDto.getStatus() + "' checked='checked' />"
                    + UtilI18N.internacionaliza(request, "citcorpore.comum.registrada") + "";
        } else if (problemaDto.getStatus().equalsIgnoreCase("Resolvido")) {
            statusSetado = "<input type='radio' id='status' name='status' value='" + problemaDto.getStatus() + "' checked='checked' />"
                    + UtilI18N.internacionaliza(request, "problema.resolvido") + "";
        } else if (problemaDto.getStatus().equalsIgnoreCase("Suspensa")) {
            statusSetado = "<input type='radio' id='status' name='status' value='" + problemaDto.getStatus() + "' checked='checked' />"
                    + UtilI18N.internacionaliza(request, "solicitacaoServico.situacao.Suspensa") + "";
        } else if (problemaDto.getStatus().equalsIgnoreCase("Concluída")) {
            statusSetado = "<input type='radio' id='status' name='status' value='" + problemaDto.getStatus() + "' checked='checked' />"
                    + UtilI18N.internacionaliza(request, "citcorpore.comum.concluida") + "";
        } else if (problemaDto.getStatus().equalsIgnoreCase("Cancelada")) {
            statusSetado = "<input type='radio' id='status' name='status' value='" + problemaDto.getStatus() + "' checked='checked' />"
                    + UtilI18N.internacionaliza(request, "citcorpore.comum.cancelada") + "";
        } else if (problemaDto.getStatus().equalsIgnoreCase("Registo de Erro Conhecido")) {
            statusSetado = "<input type='radio' id='status' name='status' value='" + problemaDto.getStatus() + "' checked='checked' />"
                    + UtilI18N.internacionaliza(request, "citcorpore.comum.cancelada") + "";
        } else {
            statusSetado = "<input type='radio' id='status' name='status' value='" + problemaDto.getStatus() + "' checked='checked' />"
                    + problemaDto.getStatus() + "";
        }
        document.getElementById("statusSetado").setInnerHTML(statusSetado);
        document.executeScript("restaurar()");
        document.executeScript("informaNumeroSolicitacao('" + problemaDto.getIdProblema() + "')");

        if (editar == null || editar.equalsIgnoreCase("")) {
            getProblemaDto().setEditar("S");
        } else if (editar.equalsIgnoreCase("N")) {
            document.executeScript("$('#divBarraFerramentas').hide()");
            document.executeScript("$('#divBotoes').hide()");
            document.getForm("form").lockForm();
        }
        /*
         * geber.costa Método listInfoRegExecucaoProblema verifica se o id do problema é válido , caso sim ele traz a lista de ocorrencias de problemas
         */

        if (listInfoRegExecucaoProblema(getProblemaDto(), request) != null) {

            document.getElementById("tblOcorrencias").setInnerHTML(listInfoRegExecucaoProblema(problemaDto, request));

        }

        this.carregaInformacoesComplementares(document, request, problemaDto);

        /**
         * Adicionado para restaurar anexo
         *
         * @author mario.junior
         * @since 31/10/2013 08:21
         */
        final ControleGEDService controleGedService = (ControleGEDService) ServiceLocator.getInstance().getService(ControleGEDService.class, null);
        final Collection colAnexos = controleGedService.listByIdTabelaAndID(ControleGEDDTO.TABELA_PROBLEMA, problemaDto.getIdProblema());
        final Collection colAnexosUploadDTO = controleGedService.convertListControleGEDToUploadDTO(colAnexos);
        request.getSession(true).setAttribute("colUploadRequisicaoProblemaGED", colAnexosUploadDTO);
        request.getSession().setAttribute("colUploadRequisicaoProblemaGED", colAnexosUploadDTO);

        final GrupoService grupoService = (GrupoService) ServiceLocator.getInstance().getService(GrupoService.class, null);
        final Collection<GrupoDTO> lstGrupos = grupoService.getGruposByEmpregado(usuarioDto.getIdEmpregado());

        if (lstGrupos != null) {
            for (final GrupoDTO g : lstGrupos) {
                if (getProblemaService(request).verificaPermissaoGrupoCancelar(problemaDto.getIdCategoriaProblema(), g.getIdGrupo())) {
                    document.executeScript("$('#statusCancelada').show()");
                    break;
                }
            }
        }

    }

    private void carregaProblemaRelacionado(final HttpServletRequest request, final DocumentHTML document, final ProblemaDTO problemaDto) throws Exception {
        final HTMLTable tblProblmea = document.getTableById("tblProblema");
        tblProblmea.deleteAllRows();
        final ProblemaService problemaservice = (ProblemaService) ServiceLocator.getInstance().getService(ProblemaService.class, null);
        if (problemaDto != null) {
            final ProblemaRelacionadoDTO problemaRelacionadoDTO = new ProblemaRelacionadoDTO();
            problemaRelacionadoDTO.setIdProblema(problemaDto.getIdProblema());
            final Collection col = problemaservice.findByProblemaRelacionado(problemaRelacionadoDTO);
            if (col != null) {
                tblProblmea.addRowsByCollection(col, new String[] {"idProblema", "titulo", "status", " "}, new String[] {"idProblema"},
                        "Problema já cadastrado!!", new String[] {"exibeIconesProblema"}, null, null);
                document.executeScript("HTMLUtils.applyStyleClassInAllCells('tblProblema', 'tblProblema');");
            }
        }

    }

    /**
     * Popula combo categoria hierarquicamente.
     *
     * @param document
     * @throws Exception
     */
    private void alimentaComboCategoriaProblema(final HttpServletRequest request, final DocumentHTML document) throws Exception {
        final HTMLSelect combo = document.getSelectById("idCategoriaProblema");
        inicializaCombo(request, combo);

        final CategoriaProblemaService categoriaProblemaService = (CategoriaProblemaService) ServiceLocator.getInstance().getService(
                CategoriaProblemaService.class, null);

        final ArrayList<CategoriaProblemaDTO> listaCategoriaAux = (ArrayList<CategoriaProblemaDTO>) categoriaProblemaService.getAtivos();
        if (listaCategoriaAux != null) {
            for (final CategoriaProblemaDTO categoriaProblemaDto : listaCategoriaAux) {
                combo.addOption(categoriaProblemaDto.getIdCategoriaProblema().toString(),
                        StringEscapeUtils.escapeJavaScript(categoriaProblemaDto.getNomeCategoria()));

            }
        }

    }

    private void inicializaCombo(final HttpServletRequest request, final HTMLSelect componenteCombo) {
        componenteCombo.removeAllOptions();
        componenteCombo.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
    }

    /**
     * Centraliza atualização de informações dos objetos que se relacionam com a mudança.
     *
     * @throws ServiceException
     * @throws Exception
     */
    private void atualizaInformacoesRelacionamentos(final DocumentHTML document) throws ServiceException, Exception {
        // informações dos ics relacionados

        final SolicitacaoServicoService solicitacaoServicoService = (SolicitacaoServicoService) ServiceLocator.getInstance().getService(
                SolicitacaoServicoService.class, null);
        final ArrayList<ProblemaItemConfiguracaoDTO> listaICsRelacionados = (ArrayList<ProblemaItemConfiguracaoDTO>) getProblemaItemConfiguracaoService()
                .findByIdProblema(problemaDto.getIdProblema());
        if (listaICsRelacionados != null) {
            for (final ProblemaItemConfiguracaoDTO problemaItemConfiguracaoDTO : listaICsRelacionados) {
                ItemConfiguracaoDTO itemConfiguracaoDTO = new ItemConfiguracaoDTO();
                itemConfiguracaoDTO = getItemConfiguracaoService().restoreByIdItemConfiguracao(problemaItemConfiguracaoDTO.getIdItemConfiguracao());
                if (itemConfiguracaoDTO != null) {
                    problemaItemConfiguracaoDTO.setNomeItemConfiguracao(itemConfiguracaoDTO.getIdentificacao());
                }

            }
            problemaDto.setItensConfiguracaoRelacionadosSerializado(WebUtil.serializeObjects(listaICsRelacionados, document.getLanguage()));
        }

        final ArrayList<SolicitacaoServicoProblemaDTO> listaSolicitacaoServico = (ArrayList<SolicitacaoServicoProblemaDTO>) getSolicitacaoServicoProblemaService()
                .findByIdProblema(problemaDto.getIdProblema());

        if (listaSolicitacaoServico != null && listaSolicitacaoServico.size() > 0) {
            for (final SolicitacaoServicoProblemaDTO solicitacaoServicoProblemaDto : listaSolicitacaoServico) {
                SolicitacaoServicoDTO solicitacaoServicoDto = new SolicitacaoServicoDTO();
                if (solicitacaoServicoProblemaDto.getIdSolicitacaoServico() != null) {
                    solicitacaoServicoDto = solicitacaoServicoService.restoreAll(solicitacaoServicoProblemaDto.getIdSolicitacaoServico());

                }

                if (solicitacaoServicoDto != null) {
                    solicitacaoServicoProblemaDto.setNomeServico(solicitacaoServicoDto.getNomeServico());
                }

            }

            problemaDto.setSolicitacaoServicoSerializado(WebUtil.serializeObjects(listaSolicitacaoServico, document.getLanguage()));
        }

        final ArrayList<ProblemaMudancaDTO> listaRequisicoesMudancasRelacionadas = (ArrayList<ProblemaMudancaDTO>) getProblemaMudancaService()
                .findByIdProblema(problemaDto.getIdProblema());

        if (listaRequisicoesMudancasRelacionadas != null) {
            for (final ProblemaMudancaDTO problemaMudanca : listaRequisicoesMudancasRelacionadas) {
                RequisicaoMudancaDTO requisicaoMudancaDto = new RequisicaoMudancaDTO();
                if (problemaMudanca.getIdRequisicaoMudanca() != null) {
                    requisicaoMudancaDto = getRequisicaoMudancaService().restoreAll(problemaMudanca.getIdRequisicaoMudanca());
                }

                if (requisicaoMudancaDto != null) {
                    problemaMudanca.setIdRequisicaoMudanca(requisicaoMudancaDto.getIdRequisicaoMudanca());
                    problemaMudanca.setTitulo(requisicaoMudancaDto.getTitulo());
                    problemaMudanca.setStatus(requisicaoMudancaDto.getStatus());
                }
            }
            problemaDto.setRequisicaoMudancaSerializado(WebUtil.serializeObjects(listaRequisicoesMudancasRelacionadas, document.getLanguage()));
        }

    }

    /**
     * @param document
     * @param request
     * @param response
     * @throws Exception
     * @author breno.guimaraes
     */
    public void save(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        document.getJanelaPopupById("JANELA_AGUARDE_MENU").show();

        problemaDto = (ProblemaDTO) document.getBean();

        final UsuarioDTO usuarioDto = br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);

        final ArrayList<ProblemaItemConfiguracaoDTO> ics = (ArrayList<ProblemaItemConfiguracaoDTO>) WebUtil.deserializeCollectionFromRequest(
                ProblemaItemConfiguracaoDTO.class, "itensConfiguracaoRelacionadosSerializado", request);

        problemaDto.setListProblemaItemConfiguracaoDTO(ics);

        final ArrayList<SolicitacaoServicoDTO> listIdSolicitacaoServico = (ArrayList<SolicitacaoServicoDTO>) WebUtil.deserializeCollectionFromRequest(
                SolicitacaoServicoDTO.class, "solicitacaoServicoSerializado", request);

        problemaDto.setListIdSolicitacaoServico(listIdSolicitacaoServico);

        final ArrayList<ProblemaMudancaDTO> rdm = (ArrayList<ProblemaMudancaDTO>) WebUtil.deserializeCollectionFromRequest(ProblemaMudancaDTO.class,
                "RequisicaoMudancaSerializado", request);

        final ArrayList<ProblemaDTO> problemaRelacionados = (ArrayList<ProblemaDTO>) WebUtil.deserializeCollectionFromRequest(ProblemaDTO.class,
                "colItensProblema_Serialize", request);
        if (problemaRelacionados != null) {
            problemaDto.setListProblemaRelacionadoDTO(problemaRelacionados);
        }

        problemaDto.setListProblemaMudancaDTO(rdm);

        problemaDto.setUsuarioDto(usuarioDto);

        problemaDto.setEnviaEmailCriacao("S");

        problemaDto.setEnviaEmailFinalizacao("S");

        problemaDto.setEnviaEmailPrazoSolucionarExpirou("S");

        ProblemaDTO problemaAuxDto = new ProblemaDTO();
        if (problemaDto.getIdProblema() != null) {
            problemaAuxDto.setIdProblema(problemaDto.getIdProblema());
            problemaAuxDto = (ProblemaDTO) getProblemaService(request).restore(problemaAuxDto);
            if (problemaAuxDto.getIdContrato() != null) {
                problemaDto.setIdContrato(problemaAuxDto.getIdContrato());
            }
            if (problemaAuxDto.getIdCategoriaProblema() != null) {
                problemaDto.setIdCategoriaProblema(problemaAuxDto.getIdCategoriaProblema());
            }
        }

        getProblemaService(request).deserializaInformacoesComplementares(problemaDto);

        /**
         * Adicionado para salvar anexos
         *
         * @author mario.junior
         * @since 31/10/2013 08:21
         */
        final Collection<UploadDTO> arquivosUpados = (Collection<UploadDTO>) request.getSession(true).getAttribute("colUploadRequisicaoProblemaGED");
        problemaDto.setColArquivosUpload(arquivosUpados);

        if (problemaDto.getIdProblema() == null) {

            problemaDto = getProblemaService(request).create(problemaDto);

            // Registra o email se tiver sido utilizado
            final EmailSolicitacaoServicoService emailSolicitacaoServicoService = (EmailSolicitacaoServicoService) ServiceLocator.getInstance().getService(
                    EmailSolicitacaoServicoService.class, null);
            if (problemaDto != null && problemaDto.getMessageId() != null && problemaDto.getMessageId().trim().length() > 0) {
                final EmailSolicitacaoServicoDTO emailDto = new EmailSolicitacaoServicoDTO();
                emailDto.setIdSolicitacao(problemaDto.getIdProblema());
                emailDto.setIdMessage(problemaDto.getMessageId());
                emailDto.setOrigem(TipoOrigemLeituraEmail.PROBLEMA.toString());
                emailSolicitacaoServicoService.create(emailDto);
            }

            String comando = "mostraMensagemInsercao('" + UtilI18N.internacionaliza(request, "MSG05") + ".<br>"
                    + UtilI18N.internacionaliza(request, "problema.numero") + " <b><u>" + problemaDto.getIdProblema() + "</u></b> "
                    + UtilI18N.internacionaliza(request, "citcorpore.comum.criado") + ".<br><br>" + UtilI18N.internacionaliza(request, "prioridade.prioridade")
                    + ": " + problemaDto.getPrioridade() + "' ";
            comando = comando + ", " + problemaDto.getIdProblema() + " )";

            document.executeScript("verificaRelacionado(" + problemaDto.getIdProblema() + ")");

            document.executeScript(comando);
            document.getJanelaPopupById("JANELA_AGUARDE_MENU").hide();
            if (problemaDto != null && problemaDto.getIframeSolicitacao().equalsIgnoreCase("true")) {
                document.executeScript("parent.inserirProblemaNalista('" + problemaDto.getIdProblema() + "')");
            }

            return;

        } else {
            new HTMLCheckbox("acompanhamento", document);

            document.getElementById("acompanhamento").getValue();

            if (problemaDto.getAcompanhamento() == null || problemaDto.getAcompanhamento().equals("N")) {

                problemaDto.setAcompanhamento("N");

            } else {

                problemaDto.setAcompanhamento("S");

            }

            getProblemaService(request).update(problemaDto);

            try {
                // Registra o email se tiver sido utilizado
                final EmailSolicitacaoServicoService emailSolicitacaoServicoService = (EmailSolicitacaoServicoService) ServiceLocator.getInstance().getService(
                        EmailSolicitacaoServicoService.class, null);
                if (problemaDto != null && problemaDto.getMessageId() != null && problemaDto.getMessageId().trim().length() > 0) {
                    EmailSolicitacaoServicoDTO emailDto = emailSolicitacaoServicoService.getEmailByIdSolicitacaoAndOrigem(
                            problemaDto.getIdSolicitacaoServico(), TipoOrigemLeituraEmail.PROBLEMA.toString());

                    if (emailDto != null && emailDto.getIdEmail() != null) {
                        emailDto.setIdMessage(problemaDto.getMessageId());
                        emailDto.setOrigem(TipoOrigemLeituraEmail.PROBLEMA.toString());
                        emailSolicitacaoServicoService.update(emailDto);
                    } else {
                        emailDto = new EmailSolicitacaoServicoDTO();
                        emailDto.setIdSolicitacao(problemaDto.getIdProblema());
                        emailDto.setIdMessage(problemaDto.getMessageId());
                        emailDto.setOrigem(TipoOrigemLeituraEmail.PROBLEMA.toString());
                        emailSolicitacaoServicoService.create(emailDto);
                    }
                }

            } catch (final Exception e) {
                e.printStackTrace();
            }

            document.alert(UtilI18N.internacionaliza(request, "MSG06"));
        }

        limparFormularioConsiderandoFCKEditores(document, "form");
        document.getJanelaPopupById("JANELA_AGUARDE_MENU").hide();
        document.executeScript("fechar('" + problemaDto.getIdProblema() + "');");
        problemaDto.getIdProblema();
    }

    /**
     * @param document
     * @param request
     * @param response
     * @throws Exception
     * @author breno.guimaraes
     */
    public void delete(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        problemaDto = (ProblemaDTO) document.getBean();

        getProblemaService(request).deleteAll(problemaDto);

        if (problemaDto.getIdProblema() != null) {
            limparFormularioConsiderandoFCKEditores(document, "form");
            document.alert(UtilI18N.internacionaliza(request, "MSG07"));
        }
    }

    /**
     * Atualiza as informações de nome de proprietario e nome de solicitante em uma requisicaoMudancaDto, caso haja.
     *
     * @param requisicaoMudancaDto
     * @throws ServiceException
     * @throws Exception
     */
    private void atribuirNomeProprietarioECriadorParaRequisicaoDto(final ProblemaDTO problemaDto) throws ServiceException, Exception {
        if (problemaDto == null) {
            return;
        }

        final Integer idProprietario = problemaDto.getIdProprietario();
        final Integer idSolicitante = problemaDto.getIdSolicitante();

        if (idProprietario != null && idSolicitante != null) {
            problemaDto.setSolicitante(getEmpregadoService().restoreByIdEmpregado(idSolicitante).getNome());
        }
    }

    public void carregaServicosMulti(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final ProblemaDTO problemaDto = (ProblemaDTO) document.getBean();
        if (problemaDto.getIdContrato() == null || problemaDto.getIdContrato().intValue() == 0) {
            problemaDto.setIdContrato(contratoDtoAux.getIdContrato());
        }
        if (problemaDto.getIdContrato() == null || problemaDto.getIdContrato().intValue() == 0) {
            return;
        }
        final HTMLSelect idServico = document.getSelectById("idServico");
        idServico.removeAllOptions();

        if (problemaDto.getIdContrato() == null) {
            document.alert(UtilI18N.internacionaliza(request, "solicitacaoservico.validacao.contrato"));
            return;
        }

        String controleAccUnidade = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.CONTROLE_ACC_UNIDADE_INC_SOLIC, "N");

        if (!UtilStrings.isNotVazio(controleAccUnidade)) {
            controleAccUnidade = "N";
        }

        final EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);
        int idUnidade = -999;
        if (controleAccUnidade.trim().equalsIgnoreCase("S")) {
            EmpregadoDTO empregadoDto = new EmpregadoDTO();
            empregadoDto.setIdEmpregado(problemaDto.getIdProprietario());
            if (problemaDto.getIdProprietario() != null) {
                empregadoDto = (EmpregadoDTO) empregadoService.restore(empregadoDto);
                if (empregadoDto != null && empregadoDto.getIdUnidade() != null) {
                    idUnidade = empregadoDto.getIdUnidade().intValue();
                }
            }
        }

        final ServicoService servicoService = (ServicoService) ServiceLocator.getInstance().getService(ServicoService.class, null);
        final UnidadesAccServicosService unidadeAccService = (UnidadesAccServicosService) ServiceLocator.getInstance().getService(
                UnidadesAccServicosService.class, null);
        idServico.removeAllOptions();
        final Collection col = servicoService.findByIdTipoDemandaAndIdContrato(3, problemaDto.getIdContrato(), null);

        int cont = 0;
        Integer idServicoCasoApenas1 = null;
        if (col != null) {

            idServico.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));

            for (final Iterator it = col.iterator(); it.hasNext();) {
                final ServicoDTO servicoDTO = (ServicoDTO) it.next();
                if (servicoDTO.getDeleted() == null || servicoDTO.getDeleted().equalsIgnoreCase("N")) {
                    if (servicoDTO.getIdSituacaoServico().intValue() == 1) { // ATIVO
                        if (controleAccUnidade.trim().equalsIgnoreCase("S")) {
                            UnidadesAccServicosDTO unidadesAccServicosDTO = new UnidadesAccServicosDTO();
                            unidadesAccServicosDTO.setIdServico(servicoDTO.getIdServico());
                            unidadesAccServicosDTO.setIdUnidade(idUnidade);
                            unidadesAccServicosDTO = (UnidadesAccServicosDTO) unidadeAccService.restore(unidadesAccServicosDTO);
                            if (unidadesAccServicosDTO != null) {// Se existe acesso
                                idServico.addOptionIfNotExists("" + servicoDTO.getIdServico(), servicoDTO.getNomeServico());
                                idServicoCasoApenas1 = servicoDTO.getIdServico();
                                cont++;
                            }
                        } else {
                            idServico.addOptionIfNotExists("" + servicoDTO.getIdServico(), servicoDTO.getNomeServico());
                            idServicoCasoApenas1 = servicoDTO.getIdServico();
                            cont++;
                        }
                    }
                }
            }
        }
        if (cont == 1) { // Se for apenas um servico encontrado, ja executa o carrega contratos.
            problemaDto.setIdServico(idServicoCasoApenas1);
        }
    }

    /**
     * form.clear não funciona para os FCKEditores. Esta função garante que todo o formulário será limpado.
     *
     * @param document
     * @param nomeFormulario
     * @author breno.guimaraes
     */
    private void limparFormularioConsiderandoFCKEditores(final DocumentHTML document, final String nomeFormulario) {
        document.executeScript("limpar(document." + nomeFormulario + ")");
    }

    /**
     * @throws ServiceException
     * @throws Exception
     * @author breno.guimaraes
     */
    private ProblemaService getProblemaService(final HttpServletRequest request) throws ServiceException, Exception {

        if (problemaService == null) {
            problemaService = (ProblemaService) ServiceLocator.getInstance().getService(ProblemaService.class,
                    br.com.centralit.citcorpore.util.WebUtil.getUsuarioSistema(request));
        }
        return problemaService;
    }

    /**
     * @throws ServiceException
     * @throws Exception
     * @author thays.araujo
     */
    private SolicitacaoServicoProblemaService getSolicitacaoServicoProblemaService() throws ServiceException, Exception {
        if (solicitacaoServicoProblemaService == null) {
            solicitacaoServicoProblemaService = (SolicitacaoServicoProblemaService) ServiceLocator.getInstance().getService(
                    SolicitacaoServicoProblemaService.class, null);
        }
        return solicitacaoServicoProblemaService;
    }

    private EmpregadoService getEmpregadoService() throws ServiceException, Exception {
        if (usuarioService == null) {
            usuarioService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);
        }
        return usuarioService;
    }

    private ProblemaItemConfiguracaoService getProblemaItemConfiguracaoService() throws ServiceException, Exception {
        if (problemaItemConfiguracaoService == null) {
            problemaItemConfiguracaoService = (ProblemaItemConfiguracaoService) ServiceLocator.getInstance().getService(ProblemaItemConfiguracaoService.class,
                    null);
        }
        return problemaItemConfiguracaoService;
    }

    private ProblemaMudancaService getProblemaMudancaService() throws ServiceException, Exception {
        if (problemaMudancaService == null) {
            problemaMudancaService = (ProblemaMudancaService) ServiceLocator.getInstance().getService(ProblemaMudancaService.class, null);
        }
        return problemaMudancaService;
    }

    private ItemConfiguracaoService getItemConfiguracaoService() throws ServiceException, Exception {
        if (itemConfiguracaoService == null) {
            itemConfiguracaoService = (ItemConfiguracaoService) ServiceLocator.getInstance().getService(ItemConfiguracaoService.class, null);
        }
        return itemConfiguracaoService;
    }

    private RequisicaoMudancaService getRequisicaoMudancaService() throws ServiceException, Exception {
        if (requisicaoMudancaService == null) {
            requisicaoMudancaService = (RequisicaoMudancaService) ServiceLocator.getInstance().getService(RequisicaoMudancaService.class, null);
        }
        return requisicaoMudancaService;
    }

    private PastaService getPastaService() throws ServiceException, Exception {
        if (pastaService == null) {
            pastaService = (PastaService) ServiceLocator.getInstance().getService(PastaService.class, null);
        }
        return pastaService;
    }

    public void preencherComboCausa(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        final CausaIncidenteService causaIncidenteService = (CausaIncidenteService) ServiceLocator.getInstance().getService(CausaIncidenteService.class, null);
        final Collection colCausas = causaIncidenteService.listHierarquia();
        final HTMLSelect comboCausa = document.getSelectById("idCausa");

        comboCausa.removeAllOptions();
        comboCausa.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
        if (colCausas != null) {
            comboCausa.addOptions(colCausas, "idCausaIncidente", "descricaoCausaNivel", null);
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

    public void restoreColaboradorSolicitante(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        ProblemaDTO problemaDto = (ProblemaDTO) document.getBean();
        final EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);

        EmpregadoDTO empregadoDto = new EmpregadoDTO();
        if (problemaDto.getIdSolicitante() != null) {
            empregadoDto.setIdEmpregado(problemaDto.getIdSolicitante());
            empregadoDto = (EmpregadoDTO) empregadoService.restore(empregadoDto);

            problemaDto.setSolicitante(empregadoDto.getNome());
            problemaDto.setNomeContato(empregadoDto.getNome());
            problemaDto.setTelefoneContato(empregadoDto.getTelefone());
            problemaDto.setRamal(empregadoDto.getRamal());
            problemaDto.setEmailContato(empregadoDto.getEmail().trim());
            problemaDto.setObservacao(empregadoDto.getObservacoes());
            problemaDto.setIdUnidade(empregadoDto.getIdUnidade());
            preencherComboLocalidade(document, request, response);
        }

        document.executeScript("$('#POPUP_SOLICITANTE').dialog('close')");

        final HTMLForm form = document.getForm("form");
        form.setValues(problemaDto);
        form.setValueText("ramal", null, problemaDto.getRamal());
        form.setValueText("telefoneContato", null, problemaDto.getTelefoneContato());
        document.executeScript("fecharPopup(\"#POPUP_EMPREGADO\")");

        problemaDto = null;
    }

    public void preencherComboLocalidade(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final ProblemaDTO problemaDto = (ProblemaDTO) document.getBean();

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
            if (problemaDto.getIdUnidade() != null) {
                listaIdlocalidadePorUnidade = localidadeUnidadeService.listaIdLocalidades(problemaDto.getIdUnidade());
            }
            if (listaIdlocalidadePorUnidade != null) {
                inicializarComboLocalidade(comboLocalidade, request);
                for (final LocalidadeUnidadeDTO localidadeUnidadeDto : listaIdlocalidadePorUnidade) {
                    localidadeDto.setIdLocalidade(localidadeUnidadeDto.getIdLocalidade());
                    localidadeDto = (LocalidadeDTO) localidadeService.restore(localidadeDto);
                    comboLocalidade.addOption(localidadeDto.getIdLocalidade().toString(), localidadeDto.getNomeLocalidade());
                }

            }
        } else {
            listaIdlocalidade = localidadeService.listLocalidade();
            if (listaIdlocalidade != null) {
                inicializarComboLocalidade(comboLocalidade, request);
                for (final LocalidadeDTO localidadeDTO : listaIdlocalidade) {
                    localidadeDto.setIdLocalidade(localidadeDTO.getIdLocalidade());
                    localidadeDto = (LocalidadeDTO) localidadeService.restore(localidadeDto);
                    comboLocalidade.addOption(localidadeDto.getIdLocalidade().toString(), localidadeDto.getNomeLocalidade());
                }
            }

        }

    }

    private void inicializarComboLocalidade(final HTMLSelect componenteCombo, final HttpServletRequest request) {
        componenteCombo.removeAllOptions();
        componenteCombo.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
    }

    public void carregaUnidade(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        ProblemaDTO problemaDto = (ProblemaDTO) document.getBean();
        final String validarComboUnidade = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.UNIDADE_VINC_CONTRATOS, "N");

        if (problemaDto.getIdProblema() != null && problemaDto.getIdProblema().intValue() > 0) {
            final ProblemaService problemaService = (ProblemaService) ServiceLocator.getInstance().getService(ProblemaService.class, null);

            contratoDtoAux.setIdContrato(problemaDto.getIdContrato());

            problemaDto = (ProblemaDTO) problemaService.restore(problemaDto);
        }

        if (problemaDto.getIdContrato() == null || problemaDto.getIdContrato().intValue() == 0) {
            problemaDto.setIdContrato(contratoDtoAux.getIdContrato());
        }

        final UnidadeService unidadeService = (UnidadeService) ServiceLocator.getInstance().getService(UnidadeService.class, null);
        final HTMLSelect comboUnidade = document.getSelectById("idUnidade");
        inicializarCombo(comboUnidade, request);
        if (validarComboUnidade.trim().equalsIgnoreCase("S")) {
            final Integer idContrato = problemaDto.getIdContrato();
            final ArrayList<UnidadeDTO> unidades = (ArrayList) unidadeService.listHierarquiaMultiContratos(idContrato);
            if (unidades != null) {
                for (final UnidadeDTO unidade : unidades) {
                    if (unidade.getDataFim() == null) {
                        comboUnidade.addOption(unidade.getIdUnidade().toString(), StringEscapeUtils.escapeJavaScript(unidade.getNomeNivel()));
                    }

                }
            }
        } else {
            final ArrayList<UnidadeDTO> unidades = (ArrayList) unidadeService.listHierarquia();
            if (unidades != null) {
                for (final UnidadeDTO unidade : unidades) {
                    if (unidade.getDataFim() == null) {
                        comboUnidade.addOption(unidade.getIdUnidade().toString(), StringEscapeUtils.escapeJavaScript(unidade.getNomeNivel()));
                    }
                }
            }
        }

        problemaDto = null;

    }

    private void inicializarCombo(final HTMLSelect componenteCombo, final HttpServletRequest request) {
        componenteCombo.removeAllOptions();
        componenteCombo.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
    }

    public void preencherComboGrupoExecutor(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        document.getSelectById("idGrupo").removeAllOptions();

        final GrupoService grupoService = (GrupoService) ServiceLocator.getInstance().getService(GrupoService.class, null);

        final Collection<GrupoDTO> listGrupo = grupoService.listaGruposAtivos();

        document.getSelectById("idGrupo").addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));

        document.getSelectById("idGrupo").addOptions(listGrupo, "idGrupo", "nome", null);

    }

    public void preencherComboOrigem(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        final OrigemAtendimentoService origemService = (OrigemAtendimentoService) ServiceLocator.getInstance().getService(OrigemAtendimentoService.class, null);
        final HTMLSelect idOrigem = document.getSelectById("idOrigemAtendimento");
        idOrigem.removeAllOptions();
        document.getSelectById("idOrigemAtendimento").addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));

        final Collection col = origemService.recuperaAtivos();
        final String origemPadrao = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.ORIGEM_PADRAO_SOLICITACAO, "").trim();
        if (col != null) {
            if (origemPadrao != null && !origemPadrao.equals("")) {
                idOrigem.addOptions(col, "idOrigem", "descricao", origemPadrao);
            } else {
                idOrigem.addOptions(col, "idOrigem", "descricao", null);
            }
        }
    }

    /**
     *
     * @param problemaDto
     * @param request
     * @return
     * @throws ServiceException
     * @throws Exception
     * @author geber.costa retorna uma lista com os registros de execução de um problema
     */
    public String listInfoRegExecucaoProblema(final ProblemaDTO problemaDto, final HttpServletRequest request) throws ServiceException, Exception {

        final JustificativaProblemaService justificativaProblemaService = (JustificativaProblemaService) ServiceLocator.getInstance().getService(
                JustificativaProblemaService.class, null);

        final OcorrenciaProblemaService ocorrenciaProblemaService = (OcorrenciaProblemaService) ServiceLocator.getInstance().getService(
                OcorrenciaProblemaService.class, null);

        final Collection<OcorrenciaProblemaDTO> col = ocorrenciaProblemaService.findByIdProblema(problemaDto.getIdProblema());

        CategoriaOcorrenciaDTO categoriaOcorrenciaDTO = new CategoriaOcorrenciaDTO();
        new OrigemOcorrenciaDTO();

        String strBuffer = "<table class='dynamicTable table table-striped table-bordered table-condensed dataTable' style='table-layout: fixed;'>";
        strBuffer += "<tr>";
        strBuffer += "<th style='width:20%'>";
        strBuffer += UtilI18N.internacionaliza(request, "citcorpore.comum.datahora");
        strBuffer += "</th>";
        strBuffer += "<th>";
        strBuffer += UtilI18N.internacionaliza(request, "solicitacaoServico.informacaoexecucao");
        strBuffer += "</th>";
        strBuffer += "</tr>";

        if (col != null) {

            for (final OcorrenciaProblemaDTO ocorrenciaProblemaDto : col) {

                if (ocorrenciaProblemaDto.getOcorrencia() != null) {
                    final Source source = new Source(ocorrenciaProblemaDto.getOcorrencia());
                    ocorrenciaProblemaDto.setOcorrencia(source.getTextExtractor().toString());
                }

                String ocorrencia = UtilStrings.nullToVazio(ocorrenciaProblemaDto.getOcorrencia());
                String descricao = UtilStrings.nullToVazio(ocorrenciaProblemaDto.getDescricao());
                String informacoesContato = UtilStrings.nullToVazio(ocorrenciaProblemaDto.getInformacoesContato());
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
                strBuffer += "<td >";
                strBuffer += "<b>"
                        + UtilDatas.convertDateToString(TipoDate.DATE_DEFAULT, ocorrenciaProblemaDto.getDataregistro(), WebUtil.getLanguage(request)) + " - "
                        + ocorrenciaProblemaDto.getHoraregistro();
                strBuffer += " - </b>" + UtilI18N.internacionaliza(request, "ocorrenciaSolicitacao.registradopor") + ": <b>"
                        + ocorrenciaProblemaDto.getRegistradopor() + "</b>";
                strBuffer += "</td>";
                strBuffer += "<td style='word-wrap: break-word;overflow:hidden;'>";
                strBuffer += "<b>" + ocorrenciaProblemaDto.getDescricao() + "<br><br></b>";
                strBuffer += "<b>" + ocorrencia + "<br><br></b>";

                if (ocorrenciaProblemaDto.getCategoria() != null) {
                    if (ocorrenciaProblemaDto.getCategoria().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.Suspensao.toString())
                            || ocorrenciaProblemaDto.getCategoria().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.MudancaSLA.toString())) {
                        JustificativaProblemaDTO justificativaSolicitacaoDTO = new JustificativaProblemaDTO();
                        if (ocorrenciaProblemaDto.getIdJustificativa() != null) {
                            justificativaSolicitacaoDTO.setIdJustificativaProblema(ocorrenciaProblemaDto.getIdJustificativa());
                            justificativaSolicitacaoDTO = (JustificativaProblemaDTO) justificativaProblemaService.restore(justificativaSolicitacaoDTO);
                            if (justificativaSolicitacaoDTO != null) {

                                strBuffer += UtilI18N.internacionaliza(request, "citcorpore.comum.justificativa") + ": <b>"
                                        + justificativaSolicitacaoDTO.getDescricaoProblema() + "<br><br></b>";
                            }
                        }
                        if (!UtilStrings.nullToVazio(ocorrenciaProblemaDto.getComplementoJustificativa()).trim().equalsIgnoreCase("")) {
                            strBuffer += "<b>" + UtilStrings.nullToVazio(ocorrenciaProblemaDto.getComplementoJustificativa()) + "<br><br></b>";
                        }
                    }

                }

                if (ocorrenciaProblemaDto.getOcorrencia() != null) {
                    if (categoriaOcorrenciaDTO.getNome() != null && !categoriaOcorrenciaDTO.getNome().equals("")) {
                        if (categoriaOcorrenciaDTO.getNome().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.Suspensao.toString())
                                || categoriaOcorrenciaDTO.getNome().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.MudancaSLA.toString())) {
                            JustificativaProblemaDTO justificativaSolicitacaoDTO = new JustificativaProblemaDTO();
                            if (ocorrenciaProblemaDto.getIdJustificativa() != null) {

                                justificativaSolicitacaoDTO.setIdJustificativaProblema(ocorrenciaProblemaDto.getIdJustificativa());
                                justificativaSolicitacaoDTO = (JustificativaProblemaDTO) justificativaProblemaService.restore(justificativaSolicitacaoDTO);
                                if (justificativaSolicitacaoDTO != null) {
                                    strBuffer += UtilI18N.internacionaliza(request, "citcorpore.comum.justificativa") + ": <b>"
                                            + justificativaSolicitacaoDTO.getDescricaoProblema() + "<br><br></b>";
                                }
                            }
                            if (!UtilStrings.nullToVazio(ocorrenciaProblemaDto.getComplementoJustificativa()).trim().equalsIgnoreCase("")) {
                                strBuffer += "<b>" + UtilStrings.nullToVazio(ocorrenciaProblemaDto.getComplementoJustificativa()) + "<br><br></b>";
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

    public void restoreComboUnidade(ProblemaDTO problemaDto, final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        final UnidadeService unidadeService = (UnidadeService) ServiceLocator.getInstance().getService(UnidadeService.class, null);
        if (problemaDto.getIdProblema() != null && problemaDto.getIdProblema().intValue() > 0) {
            final String validarComboUnidade = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.UNIDADE_VINC_CONTRATOS, "N");

            if (request.getParameter("chamarTelaProblema") == null || request.getParameter("chamarTelaProblema").equalsIgnoreCase("")) {
                problemaDto = (ProblemaDTO) problemaService.restore(problemaDto);
            }

            if (problemaDto.getIdContrato() == null || problemaDto.getIdContrato().intValue() == 0) {
                problemaDto.setIdContrato(contratoDtoAux.getIdContrato());
            }

            final String UNIDADE_AUTOCOMPLETE = ParametroUtil.getValorParametroCitSmartHashMap(
                    br.com.centralit.citcorpore.util.Enumerados.ParametroSistema.UNIDADE_AUTOCOMPLETE, "N");
            if (UNIDADE_AUTOCOMPLETE != null && !UNIDADE_AUTOCOMPLETE.equalsIgnoreCase("S")) {
                if (problemaDto.getIdUnidade() != null) {
                    final HTMLSelect comboUnidade = document.getSelectById("idUnidade");
                    inicializarCombo(comboUnidade, request);
                    if (validarComboUnidade.trim().equalsIgnoreCase("S")) {
                        final Integer idContrato = problemaDto.getIdContrato();
                        final ArrayList<UnidadeDTO> unidades = (ArrayList) unidadeService.listHierarquiaMultiContratos(idContrato);
                        if (unidades != null) {
                            for (final UnidadeDTO unidade : unidades) {
                                if (unidade.getDataFim() == null) {
                                    comboUnidade.addOption(unidade.getIdUnidade().toString(), StringEscapeUtils.escapeJavaScript(unidade.getNomeNivel()));
                                }

                            }
                        }
                    } else {
                        final ArrayList<UnidadeDTO> unidades = (ArrayList) unidadeService.listHierarquia();
                        if (unidades != null) {
                            for (final UnidadeDTO unidade : unidades) {
                                if (unidade.getDataFim() == null) {
                                    comboUnidade.addOption(unidade.getIdUnidade().toString(), StringEscapeUtils.escapeJavaScript(unidade.getNomeNivel()));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void restoreComboLocalidade(final ProblemaDTO ProblemaDto, final DocumentHTML document, final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {

        if (ProblemaDto.getIdProblema() != null && ProblemaDto.getIdProblema().intValue() > 0) {

            final String TIRAR_VINCULO_LOCALIDADE_UNIDADE = ParametroUtil.getValorParametroCitSmartHashMap(
                    Enumerados.ParametroSistema.TIRAR_VINCULO_LOCALIDADE_UNIDADE, "N");

            if (ProblemaDto.getIdContrato() == null || ProblemaDto.getIdContrato().intValue() == 0) {
                ProblemaDto.setIdContrato(contratoDtoAux.getIdContrato());
            }

            if (ProblemaDto.getIdLocalidade() != null) {

                final LocalidadeUnidadeService localidadeUnidadeService = (LocalidadeUnidadeService) ServiceLocator.getInstance().getService(
                        LocalidadeUnidadeService.class, null);
                final LocalidadeService localidadeService = (LocalidadeService) ServiceLocator.getInstance().getService(LocalidadeService.class, null);
                LocalidadeDTO localidadeDto = new LocalidadeDTO();
                Collection<LocalidadeUnidadeDTO> listaIdlocalidadePorUnidade = null;
                Collection<LocalidadeDTO> listaIdlocalidade = null;

                final HTMLSelect comboLocalidade = document.getSelectById("idLocalidade");
                comboLocalidade.removeAllOptions();
                if (TIRAR_VINCULO_LOCALIDADE_UNIDADE.trim().equalsIgnoreCase("N") || TIRAR_VINCULO_LOCALIDADE_UNIDADE.trim().equalsIgnoreCase("")) {
                    if (ProblemaDto.getIdUnidade() != null) {
                        listaIdlocalidadePorUnidade = localidadeUnidadeService.listaIdLocalidades(ProblemaDto.getIdUnidade());
                    }
                    if (listaIdlocalidadePorUnidade != null) {
                        inicializarComboLocalidade(comboLocalidade, request);
                        for (final LocalidadeUnidadeDTO localidadeUnidadeDto : listaIdlocalidadePorUnidade) {
                            localidadeDto.setIdLocalidade(localidadeUnidadeDto.getIdLocalidade());
                            localidadeDto = (LocalidadeDTO) localidadeService.restore(localidadeDto);
                            comboLocalidade.addOption(localidadeDto.getIdLocalidade().toString(), localidadeDto.getNomeLocalidade());
                        }

                    }
                } else {
                    listaIdlocalidade = localidadeService.listLocalidade();
                    if (listaIdlocalidade != null) {
                        inicializarComboLocalidade(comboLocalidade, request);
                        for (final LocalidadeDTO localidadeDTO : listaIdlocalidade) {
                            localidadeDto.setIdLocalidade(localidadeDTO.getIdLocalidade());
                            localidadeDto = (LocalidadeDTO) localidadeService.restore(localidadeDto);
                            comboLocalidade.addOption(localidadeDto.getIdLocalidade().toString(), localidadeDto.getNomeLocalidade());
                        }
                    }
                }
            }
        }
    }

    public void preencherComboOrigemAtendimento(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {

        final HTMLSelect comboOrigem = document.getSelectById("idOrigemAtendimento");

        final OrigemAtendimentoService origemAtendimentoService = (OrigemAtendimentoService) ServiceLocator.getInstance().getService(
                OrigemAtendimentoService.class, null);
        final Collection<OrigemAtendimentoDTO> listOrigem = origemAtendimentoService.list();

        comboOrigem.removeAllOptions();
        comboOrigem.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
        if (listOrigem != null) {
            for (final OrigemAtendimentoDTO origemDTO : listOrigem) {
                if (origemDTO.getIdOrigem() != null || origemDTO.getIdOrigem() > 0) {
                    comboOrigem.addOption(origemDTO.getIdOrigem().toString(), StringEscapeUtils.escapeJavaScript(origemDTO.getDescricao()));
                }
            }
        }

    }

    public void restoreComboOrigemAtendimento(final ProblemaDTO problemaDto, final DocumentHTML document, final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {
        if (problemaDto.getIdProblema() != null && problemaDto.getIdProblema().intValue() > 0) {
            if (problemaDto.getIdOrigemAtendimento() != null && problemaDto.getIdOrigemAtendimento().intValue() > 0) {
                this.preencherComboOrigem(document, request, response);
            }
        }
    }

    /**
     * Restaura Problemas na Grid.
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     * @author thays.araujo
     */
    public void atualizaGridProblema(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        ProblemaDTO problemaDTO = (ProblemaDTO) document.getBean();
        final ProblemaService problemaService = (ProblemaService) ServiceLocator.getInstance().getService(ProblemaService.class, null);
        final ConhecimentoProblemaService conhecimentoProblemaService = (ConhecimentoProblemaService) ServiceLocator.getInstance().getService(
                ConhecimentoProblemaService.class, null);

        ConhecimentoProblemaDTO erroConhecido = null;

        if (problemaDTO.getIdProblema() != null) {
            erroConhecido = conhecimentoProblemaService.getErroConhecidoByProblema(problemaDTO.getIdProblema());
            problemaDTO = (ProblemaDTO) problemaService.restore(problemaDTO);
        }

        final HTMLTable tblErrosConhecidos = document.getTableById("tblErrosConhecidos");
        tblErrosConhecidos.deleteAllRows();

        if (erroConhecido != null) {
            tblErrosConhecidos.addRow(erroConhecido, new String[] {"", "titulo", "status", ""}, null, null, new String[] {"exibeIconesEditarBaseConhecimento"},
                    null, null);
            document.executeScript("$('#divBaseConhecimento').hide()");
        } else {
            document.executeScript("$('#divBaseConhecimento').show()");
        }

    }

    public void atualizaGridProblemaRelacionados(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        ProblemaDTO bean = (ProblemaDTO) document.getBean();
        final ProblemaService problemaService = (ProblemaService) ServiceLocator.getInstance().getService(ProblemaService.class, null);
        ProblemaDTO problemaDTO = new ProblemaDTO();

        problemaDTO.setIdProblema(bean.getIdProblemaRelacionado());

        problemaDTO = (ProblemaDTO) problemaService.restore(problemaDTO);
        if (problemaDTO == null) {
            return;
        }
        final HTMLTable tblProblema = document.getTableById("tblProblema");

        if (problemaDTO.getSequenciaProblema() == null) {
            tblProblema.addRow(problemaDTO, new String[] {"idProblema", "titulo", "status", " "}, new String[] {"idProblema"}, "Problema já cadastrado!!",
                    new String[] {"exibeIconesProblema"}, null, null);
        } else {
            tblProblema.updateRow(problemaDTO, new String[] {"idProblema", "titulo", "status", " "}, new String[] {"idProblema"}, "Problema já cadastrado!!",
                    new String[] {"exibeIconesProblema"}, "", null, problemaDTO.getSequenciaProblema());
        }
        document.executeScript("HTMLUtils.applyStyleClassInAllCells('tblProblema', 'tblProblema');");
        document.executeScript("fecharModalProblema();");

        bean = null;
    }

    public void limparTabelas(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final HTMLTable tblICs = document.getTableById("tblICs");
        tblICs.deleteAllRows();
        final HTMLTable tblRDM = document.getTableById("tblRDM");
        tblRDM.deleteAllRows();
        final HTMLTable tblSolicitacaoServico = document.getTableById("tblSolicitacaoServico");
        tblSolicitacaoServico.deleteAllRows();
    }

    /**
     * preencher comobo de calendario
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     * @author geber.costa
     */
    public void preencherComboCalendario(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final CalendarioService calendarioService = (CalendarioService) ServiceLocator.getInstance().getService(CalendarioService.class, null);
        final HTMLSelect comboCalendario = document.getSelectById("idCalendario");
        final Collection<CalendarioDTO> calendarioDTO = calendarioService.list();

        comboCalendario.removeAllOptions();

        if (calendarioDTO != null) {
            for (final CalendarioDTO calendario : calendarioDTO) {

                comboCalendario.addOption(calendario.getIdCalendario().toString(), StringEscapeUtils.escapeJavaScript(calendario.getDescricao()));
            }
        }

    }

    /**
     * Método responsável pela validação do avanço do fluxo. O fluxo só será válido e portanto só poderá avançar caso o problema em questão esteja associado a
     * uma base de conhecimento.
     *
     * @autor thiago.monterio
     * @param document
     * @param request
     * @param response
     * @throws Exception
     */
    public void validacaoAvancaFluxo(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        final ProblemaDTO problemaDTO = (ProblemaDTO) document.getBean();

        final ProblemaService problemaService = (ProblemaService) ServiceLocator.getInstance().getService(ProblemaService.class, null);

        if (problemaDTO != null && problemaService != null) {
            if (problemaDTO.getStatus() != null && problemaDTO.getStatus().equalsIgnoreCase(SituacaoRequisicaoProblema.RegistroErroConhecido.getDescricao())) {
                final boolean avancarFluxo = problemaService.validacaoAvancaFluxo(problemaDTO, null);

                if (!avancarFluxo) {

                    document.alert(UtilI18N.internacionaliza(request, "problema.mensagem.necessarioExistirErroConhecido"));

                } else {

                    document.executeScript("gravar('form')");

                }
            } else {
                document.executeScript("gravar('form')");
            }

        }
    }

    /**
     * Método responsável por enviar mensagens de notificação no e-mail do responsável (proprietário) e de cada um dos usuários que fazem parte do grupo
     * executor na resolução de um problema.
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     * @autor thiago.monteiro / thays.araujo
     */
    public void notificarPrazoSolucionarProblemaExpirou(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response,
            final UsuarioDTO usuario) throws Exception {

        EmpregadoDTO empregadoDto = new EmpregadoDTO();

        final ProblemaService problemaService = (ProblemaService) ServiceLocator.getInstance().getService(ProblemaService.class, null);

        final UsuarioService usuarioService = (UsuarioService) ServiceLocator.getInstance().getService(UsuarioService.class, null);

        final EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);

        final GrupoService grupoService = (GrupoService) ServiceLocator.getInstance().getService(GrupoService.class, null);

        final String enviarNotificacao = ParametroUtil.getValorParametroCitSmartHashMap(
                ParametroSistema.NOTIFICAR_RESPONSAVEL_GRUPO_PRAZO_SOLUCAO_CONTORNO_PROBLEMA_EXPIRADO, "S");

        if (enviarNotificacao.equalsIgnoreCase("S")) {

            final Collection<ProblemaDTO> listaProblemasComPrazoLimiteContornoExpirado = problemaService.listaProblemasComPrazoLimiteContornoExpirado(usuario);

            if (usuario != null) {
                if (usuario.getIdEmpregado() != null) {
                    empregadoDto.setIdEmpregado(usuario.getIdEmpregado());
                    empregadoDto = (EmpregadoDTO) empregadoService.restore(empregadoDto);
                }
            }

            if (problemaService != null && usuarioService != null) {

                if (listaProblemasComPrazoLimiteContornoExpirado != null) {

                    final String ID_MODELO_EMAIL_PRAZO_SOLUCAO_CONTORNO_PROBLEMA_EXPIRADO = ParametroUtil.getValorParametroCitSmartHashMap(
                            ParametroSistema.ID_MODELO_EMAIL_PRAZO_SOLUCAO_CONTORNO_PROBLEMA_EXPIRADO, "38");

                    final Set emailsIntegrantesGrupoExecutor = new HashSet();

                    Collection<String> emailsPorGrupo = null;

                    for (final ProblemaDTO problemaDto : listaProblemasComPrazoLimiteContornoExpirado) {

                        if (problemaDto.getIdGrupo() != null) {

                            emailsPorGrupo = grupoService.listarEmailsPorGrupo(problemaDto.getIdGrupo());

                            if (emailsPorGrupo != null && !emailsPorGrupo.isEmpty()) {

                                for (final String email : emailsPorGrupo) {
                                    emailsIntegrantesGrupoExecutor.add(email);
                                }

                            }

                        }
                    }

                    final MensagemEmail mensagemEmail = new MensagemEmail(Integer.parseInt(ID_MODELO_EMAIL_PRAZO_SOLUCAO_CONTORNO_PROBLEMA_EXPIRADO.trim()),
                            new BaseEntity[] {problemaDto});

                    mensagemEmail.envia(empregadoDto.getEmail(), StringUtils.remove(StringUtils.remove(emailsIntegrantesGrupoExecutor.toString(), "["), "]"),
                            ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.RemetenteNotificacoesSolicitacao, "10"));
                }
            }
        }
    }

    public void gravarSolContorno(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final ProblemaDTO problemaDto = (ProblemaDTO) document.getBean();
        SolucaoContornoDTO solucaoContornoDto = new SolucaoContornoDTO();
        final SolucaoContornoService solucaoContornoService = (SolucaoContornoService) ServiceLocator.getInstance().getService(SolucaoContornoService.class,
                null);
        final ProblemaService problemaService = (ProblemaService) ServiceLocator.getInstance().getService(ProblemaService.class, null);

        if (problemaDto.getIdProblema() != null) {
            solucaoContornoDto.setIdProblema(problemaDto.getIdProblema());

            if (problemaDto.getIdSolucaoContorno() != null && problemaDto.getIdSolucaoContorno().intValue() > 0) {

                solucaoContornoDto.setIdSolucaoContorno(problemaDto.getIdSolucaoContorno());
                solucaoContornoDto = (SolucaoContornoDTO) solucaoContornoService.restore(solucaoContornoDto);

                if (problemaDto.getSolucaoContorno() != null && !problemaDto.getSolucaoContorno().equals("")) {
                    solucaoContornoDto.setDescricao(problemaDto.getSolucaoContorno());
                } else {
                    document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.descricaoObrigatorio"));
                    return;
                }

                if (problemaDto.getTituloSolucaoContorno() != null && !problemaDto.getTituloSolucaoContorno().equals("")) {
                    solucaoContornoDto.setTitulo(problemaDto.getTituloSolucaoContorno());
                } else {
                    document.alert(UtilI18N.internacionaliza(request, "baseConhecimento.tituloObrigatorio"));
                    return;
                }

                solucaoContornoService.update(solucaoContornoDto);
                document.alert(UtilI18N.internacionaliza(request, "MSG06"));

            } else {

                if (problemaDto.getSolucaoContorno() != null && !problemaDto.getSolucaoContorno().equals("")) {
                    solucaoContornoDto.setDescricao(problemaDto.getSolucaoContorno());
                } else {
                    document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.descricaoObrigatorio"));
                    return;
                }

                if (problemaDto.getTituloSolucaoContorno() != null && !problemaDto.getTituloSolucaoContorno().equals("")) {
                    solucaoContornoDto.setTitulo(problemaDto.getTituloSolucaoContorno());
                } else {
                    document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.tituloObrigatorio"));
                    return;
                }
                solucaoContornoDto.setDataHoraCriacao(UtilDatas.getDataHoraAtual());
                solucaoContornoDto = solucaoContornoService.create(solucaoContornoDto);
                document.alert(UtilI18N.internacionaliza(request, "MSG05"));

            }
            problemaService.updateNotNull(problemaDto);
        } else {
            return;
        }
        this.montarTabela(solucaoContornoDto, request, response, document);
        document.executeScript("fecharSolContorno()");

    }

    public SolucaoContornoDTO verificaSolucaoContorno(final HttpServletRequest request, final HttpServletResponse response, final DocumentHTML document,
            final ProblemaDTO problema) throws ServiceException, Exception {
        SolucaoContornoDTO solucaoContorno = new SolucaoContornoDTO();
        final SolucaoContornoService solucaoContornoService = (SolucaoContornoService) ServiceLocator.getInstance().getService(SolucaoContornoService.class,
                null);

        solucaoContorno.setIdProblema(problema.getIdProblema());
        solucaoContorno = solucaoContornoService.findByIdProblema(solucaoContorno);

        if (solucaoContorno != null) {
            this.montarTabela(solucaoContorno, request, response, document);
        }

        return solucaoContorno;
    }

    public void montarTabela(final SolucaoContornoDTO solucaoContornoDto, final HttpServletRequest request, final HttpServletResponse response,
            final DocumentHTML document) throws Exception {
        final HTMLTable tblSolContorno = document.getTableById("tblSolContorno");
        tblSolContorno.deleteAllRows();
        if (solucaoContornoDto.getIdSolucaoContorno() != null) {
            solucaoContornoDto.setDataHoraCriacaoStr(UtilDatas.convertDateToString(TipoDate.TIMESTAMP_WITH_SECONDS, solucaoContornoDto.getDataHoraCriacao(),
                    WebUtil.getLanguage(request)));
            tblSolContorno.addRow(solucaoContornoDto, new String[] {"titulo", "dataHoraCriacaoStr", "descricao"}, null, null, null, null, null);
        }
    }

    public void gravarSolDefinitiva(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final ProblemaDTO problemaDto = (ProblemaDTO) document.getBean();
        SolucaoDefinitivaDTO solucaoDefinitivaDto = new SolucaoDefinitivaDTO();
        final SolucaoDefinitivaService solucaoDefinitivaService = (SolucaoDefinitivaService) ServiceLocator.getInstance().getService(
                SolucaoDefinitivaService.class, null);

        if (problemaDto.getIdProblema() != null) {
            solucaoDefinitivaDto.setIdProblema(problemaDto.getIdProblema());

            if (problemaDto.getIdSolucaoDefinitiva() != null && problemaDto.getIdSolucaoDefinitiva().intValue() > 0) {

                solucaoDefinitivaDto.setIdSolucaoDefinitiva(problemaDto.getIdSolucaoDefinitiva());
                solucaoDefinitivaDto = (SolucaoDefinitivaDTO) solucaoDefinitivaService.restore(solucaoDefinitivaDto);

                if (problemaDto.getSolucaoDefinitiva() != null && !problemaDto.getSolucaoDefinitiva().equals("")) {
                    solucaoDefinitivaDto.setDescricao(problemaDto.getSolucaoDefinitiva());
                } else {
                    document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.descricaoObrigatorio"));
                    return;
                }

                if (problemaDto.getTituloSolucaoDefinitiva() != null && !problemaDto.getTituloSolucaoDefinitiva().equals("")) {
                    solucaoDefinitivaDto.setTitulo(problemaDto.getTituloSolucaoDefinitiva());
                } else {
                    document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.tituloObrigatorio"));
                    return;
                }

                solucaoDefinitivaService.update(solucaoDefinitivaDto);
                document.alert(UtilI18N.internacionaliza(request, "MSG06"));

            } else {

                if (problemaDto.getSolucaoDefinitiva() != null && !problemaDto.getSolucaoDefinitiva().equals("")) {
                    solucaoDefinitivaDto.setDescricao(problemaDto.getSolucaoDefinitiva());
                } else {
                    document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.descricaoObrigatorio"));
                    return;
                }

                if (problemaDto.getTituloSolucaoDefinitiva() != null && !problemaDto.getTituloSolucaoDefinitiva().equals("")) {
                    solucaoDefinitivaDto.setTitulo(problemaDto.getTituloSolucaoDefinitiva());
                } else {
                    document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.tituloObrigatorio"));
                    return;
                }
                solucaoDefinitivaDto.setDataHoraCriacao(UtilDatas.getDataHoraAtual());
                solucaoDefinitivaDto = solucaoDefinitivaService.create(solucaoDefinitivaDto);
                document.alert(UtilI18N.internacionaliza(request, "MSG05"));

            }
        } else {
            return;
        }

        this.montarTabela(solucaoDefinitivaDto, request, response, document);
        document.executeScript("fecharSolDefinitiva()");

    }

    public SolucaoDefinitivaDTO verificaSolucaoDefinitiva(final HttpServletRequest request, final HttpServletResponse response, final DocumentHTML document,
            final ProblemaDTO problema) throws ServiceException, Exception {
        SolucaoDefinitivaDTO solucaoDefinitiva = new SolucaoDefinitivaDTO();
        final SolucaoDefinitivaService solucaoDefinitivaService = (SolucaoDefinitivaService) ServiceLocator.getInstance().getService(
                SolucaoDefinitivaService.class, null);

        solucaoDefinitiva.setIdProblema(problema.getIdProblema());
        solucaoDefinitiva = solucaoDefinitivaService.findByIdProblema(solucaoDefinitiva);

        if (solucaoDefinitiva != null) {
            this.montarTabela(solucaoDefinitiva, request, response, document);
        }

        return solucaoDefinitiva;
    }

    public void montarTabela(final SolucaoDefinitivaDTO solucaoDefinitivaDto, final HttpServletRequest request, final HttpServletResponse response,
            final DocumentHTML document) throws Exception {
        final HTMLTable tblSolDefinitiva = document.getTableById("tblSolDefinitiva");
        tblSolDefinitiva.deleteAllRows();
        if (solucaoDefinitivaDto.getIdSolucaoDefinitiva() != null) {
            solucaoDefinitivaDto.setDataHoraCriacaoStr(UtilDatas.convertDateToString(TipoDate.TIMESTAMP_WITH_SECONDS,
                    solucaoDefinitivaDto.getDataHoraCriacao(), WebUtil.getLanguage(request)));
            tblSolDefinitiva.addRow(solucaoDefinitivaDto, new String[] {"titulo", "dataHoraCriacaoStr", "descricao"}, null, null, null, null, null);
        }
    }

    /**
     * @author geber.costa
     * @param document
     * @param idProblema
     * @throws Exception
     *             Traz uma lista para verificação de ultima data e hora da ocorrência, esses dados serão retornados na página do cliente.
     */
    public void verificaUltimaAtualizacao(final DocumentHTML document, final HttpServletRequest request, final int idProblema) throws Exception {

        final OcorrenciaProblemaDAO ocorrenciaDao = new OcorrenciaProblemaDAO();
        final List<OcorrenciaProblemaDTO> lista = (List<OcorrenciaProblemaDTO>) ocorrenciaDao.listByUltimaDataEHora(idProblema);

        Date data = null;
        String hora = "";
        String registradoPor = "";

        if (lista != null) {

            for (final OcorrenciaProblemaDTO l : lista) {

                /**
                 *
                 * valida para pegar a ultima data de registro e a ultima hora, porém ele irá pegar a partiro do momento que a ocorrencia não for nula e vazia
                 * ou se ela tiver alguma das descrições
                 * setadas
                 */

                if (l.getOcorrencia() != null && !l.getOcorrencia().equalsIgnoreCase("") || l.getDescricao().equalsIgnoreCase("Encerramento da Solicitação")
                        || l.getDescricao().equalsIgnoreCase("Suspensão da Solicitação") || l.getDescricao().equalsIgnoreCase("Reativação da Solicitação")
                        || l.getDescricao().equalsIgnoreCase("Agendamento da Atividade") || l.getDescricao().equalsIgnoreCase("Registro de Execução")) {

                    if (l.getDataregistro() != null) {
                        data = l.getDataregistro();
                    }

                    if (!l.getHoraregistro().equalsIgnoreCase("")) {
                        hora = l.getHoraregistro();
                    }
                    registradoPor = l.getRegistradopor();

                }

            }

            final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            final String date = sdf.format(data);

            document.getElementById("dataHoraUltimaAtualizacao").setInnerHTML(
                    "<br/>" + UtilI18N.internacionaliza(request, "carteiraTrabalho.data") + ":<b>&nbsp;" + date + "&nbsp;&nbsp;&nbsp;</b>"
                            + UtilI18N.internacionaliza(request, "carteiraTrabalho.hora") + ":<b>&nbsp;" + hora + "&nbsp;&nbsp;&nbsp;</b>"
                            + UtilI18N.internacionaliza(request, "ocorrenciaProblema.registradopor") + ":<b>&nbsp;" + registradoPor + "&nbsp;&nbsp;</b>");
        }
    }

    public Integer obterIdTarefa(final ProblemaDTO problema, final HttpServletRequest request) throws ServiceException, Exception {
        int res = 0;
        final ExecucaoProblemaService execucaoProblemaService = (ExecucaoProblemaService) ServiceLocator.getInstance().getService(
                ExecucaoProblemaService.class, null);
        usuario = br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);
        final TarefaFluxoDTO tarefaFluxo = execucaoProblemaService.recuperaTarefa(usuario.getLogin(), problema);
        if (tarefaFluxo != null) {
            res = tarefaFluxo.getIdItemTrabalho();
        }
        return res;
    }

    public void carregaInformacoesComplementares(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        ProblemaDTO problemaDto = (ProblemaDTO) document.getBean();
        carregaInformacoesComplementares(document, request, problemaDto);
        problemaDto = null;
    }

    private void carregaInformacoesComplementares(final DocumentHTML document, final HttpServletRequest request, final ProblemaDTO problemaDto)
            throws Exception {
        final TemplateSolicitacaoServicoService templateService = (TemplateSolicitacaoServicoService) ServiceLocator.getInstance().getService(
                TemplateSolicitacaoServicoService.class, br.com.centralit.citcorpore.util.WebUtil.getUsuarioSistema(request));

        document.executeScript("exibirInformacoesComplementares('" + getProblemaService(request).getUrlInformacoesComplementares(problemaDto) + "');");
        final TemplateSolicitacaoServicoDTO templateDto = templateService.recuperaTemplateProblema(problemaDto);

        if (templateDto != null) {
            if (templateDto.getAlturaDiv() != null) {
                document.executeScript("document.getElementById('divInformacoesComplementares').style.height = '" + templateDto.getAlturaDiv().intValue()
                        + "px';");

            }

        }
        document.executeScript("escondeJanelaAguarde()");
    }

    public void restoreImpactoUrgenciaPorCategoriaProblema(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {

        final ProblemaDTO problemaDto = (ProblemaDTO) document.getBean();

        CategoriaProblemaDTO categoriaProblemaDto = new CategoriaProblemaDTO();

        final CategoriaProblemaService categoriaProblemaService = (CategoriaProblemaService) ServiceLocator.getInstance().getService(
                CategoriaProblemaService.class, null);

        if (problemaDto.getIdCategoriaProblema() != null) {
            categoriaProblemaDto.setIdCategoriaProblema(problemaDto.getIdCategoriaProblema());
            categoriaProblemaDto = (CategoriaProblemaDTO) categoriaProblemaService.restore(categoriaProblemaDto);
        }

        if (categoriaProblemaDto != null) {
            problemaDto.setImpacto(categoriaProblemaDto.getImpacto());
            problemaDto.setUrgencia(categoriaProblemaDto.getUrgencia());

        }

        final HTMLForm form = document.getForm("form");
        form.setValues(problemaDto);
        document.executeScript("atualizaPrioridade()");
    }

    public void verificarItensRelacionados(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws ServiceException, Exception {
        document.getBean();

        final ArrayList<SolicitacaoServicoDTO> listIdSolicitacaoServico = (ArrayList<SolicitacaoServicoDTO>) br.com.citframework.util.WebUtil
                .deserializeCollectionFromRequest(SolicitacaoServicoDTO.class, "solicitacaoServicoSerializado", request);
        final ArrayList<RequisicaoMudancaItemConfiguracaoDTO> listRequisicaoMudancaItemConfiguracaoDTO = (ArrayList<RequisicaoMudancaItemConfiguracaoDTO>) br.com.citframework.util.WebUtil
                .deserializeCollectionFromRequest(RequisicaoMudancaItemConfiguracaoDTO.class, "itensConfiguracaoRelacionadosSerializado", request);

        boolean existeItensRelaiconados = false;

        if (listIdSolicitacaoServico != null && listIdSolicitacaoServico.size() > 0) {
            existeItensRelaiconados = true;
        } else if (listRequisicaoMudancaItemConfiguracaoDTO != null && listRequisicaoMudancaItemConfiguracaoDTO.size() > 0) {
            existeItensRelaiconados = true;
        }

        if (existeItensRelaiconados) {
            document.executeScript("verificarItensRelacionados(false)");
        } else {
            validacaoAvancaFluxo(document, request, response);
        }
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

    public void restaurarItemConfiguracao(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) {
        problemaDto = (ProblemaDTO) document.getBean();
        ItemConfiguracaoDTO itemConfiguracaoDTO = new ItemConfiguracaoDTO();
        try {

            final ItemConfiguracaoService itemConfiguracaoService = (ItemConfiguracaoService) ServiceLocator.getInstance().getService(
                    ItemConfiguracaoService.class, null);
            problemaDto.getHiddenIdItemConfiguracao();
            if (problemaDto != null && problemaDto.getHiddenIdItemConfiguracao() != null && Integer.SIZE > 0) {
                itemConfiguracaoDTO.setIdItemConfiguracao(problemaDto.getHiddenIdItemConfiguracao());
                itemConfiguracaoDTO = (ItemConfiguracaoDTO) itemConfiguracaoService.restore(itemConfiguracaoDTO);
                document.getElementById("hiddenIdItemConfiguracao").setValue(itemConfiguracaoDTO.getIdItemConfiguracao().toString());

                document.executeScript("atualizarTabelaICs('" + itemConfiguracaoDTO.getIdItemConfiguracao() + "','" + itemConfiguracaoDTO.getIdentificacao()
                        + "')");
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Método necessário para os casos de PoppupManager, pois há uma função que quando a popup é fechada, é chamado um fireevent para carregar a combo, porém
     * não tinha como acessar o metodo
     * alimentaComboCategoriaProblema por ser private e era necessario ter um metodo que recebia os parametro (DocumentHTML, HttpServletRequest,
     * HttpServletResponse)
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     */
    public void alimentaComboCatProblemaAposCadastro(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        alimentaComboCategoriaProblema(request, document);
    }

}
