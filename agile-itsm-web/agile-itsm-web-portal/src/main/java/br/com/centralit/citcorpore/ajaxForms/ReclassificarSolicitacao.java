package br.com.centralit.citcorpore.ajaxForms;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citajax.html.HTMLForm;
import br.com.centralit.citajax.html.HTMLSelect;
import br.com.centralit.citajax.html.HTMLTable;
import br.com.centralit.citcorpore.bean.ADUserDTO;
import br.com.centralit.citcorpore.bean.AcordoNivelServicoDTO;
import br.com.centralit.citcorpore.bean.AcordoServicoContratoDTO;
import br.com.centralit.citcorpore.bean.BaseConhecimentoDTO;
import br.com.centralit.citcorpore.bean.ClienteDTO;
import br.com.centralit.citcorpore.bean.ContratoDTO;
import br.com.centralit.citcorpore.bean.ContratosGruposDTO;
import br.com.centralit.citcorpore.bean.EmpregadoDTO;
import br.com.centralit.citcorpore.bean.FornecedorDTO;
import br.com.centralit.citcorpore.bean.GrupoDTO;
import br.com.centralit.citcorpore.bean.ImpactoDTO;
import br.com.centralit.citcorpore.bean.ItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.ProblemaDTO;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaDTO;
import br.com.centralit.citcorpore.bean.ServicoContratoDTO;
import br.com.centralit.citcorpore.bean.ServicoDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.UnidadeDTO;
import br.com.centralit.citcorpore.bean.UnidadesAccServicosDTO;
import br.com.centralit.citcorpore.bean.UrgenciaDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.integracao.AcordoNivelServicoDao;
import br.com.centralit.citcorpore.integracao.ad.LDAPUtils;
import br.com.centralit.citcorpore.negocio.AcordoNivelServicoService;
import br.com.centralit.citcorpore.negocio.AcordoServicoContratoService;
import br.com.centralit.citcorpore.negocio.BaseConhecimentoService;
import br.com.centralit.citcorpore.negocio.ClienteService;
import br.com.centralit.citcorpore.negocio.ContratoService;
import br.com.centralit.citcorpore.negocio.ContratosGruposService;
import br.com.centralit.citcorpore.negocio.EmpregadoService;
import br.com.centralit.citcorpore.negocio.FornecedorService;
import br.com.centralit.citcorpore.negocio.GrupoService;
import br.com.centralit.citcorpore.negocio.ItemConfiguracaoService;
import br.com.centralit.citcorpore.negocio.OrigemAtendimentoService;
import br.com.centralit.citcorpore.negocio.ProblemaService;
import br.com.centralit.citcorpore.negocio.RequisicaoMudancaService;
import br.com.centralit.citcorpore.negocio.ServicoContratoService;
import br.com.centralit.citcorpore.negocio.ServicoService;
import br.com.centralit.citcorpore.negocio.SolicitacaoServicoService;
import br.com.centralit.citcorpore.negocio.UnidadeService;
import br.com.centralit.citcorpore.negocio.UnidadesAccServicosService;
import br.com.centralit.citcorpore.negocio.UsuarioService;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.Enumerados.TipoDate;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.centralit.citcorpore.util.WebUtil;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilI18N;
import br.com.citframework.util.UtilStrings;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ReclassificarSolicitacao extends SolicitacaoServicoMultiContratos {

    private final ContratoDTO contratoDtoAux = new ContratoDTO();

    private Boolean acao = false;

    @Override
    public boolean validaParametrosUpload() {
        final String PRONTUARIO_GED_DIRETORIO = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.GedDiretorio, "");
        if (PRONTUARIO_GED_DIRETORIO == null || PRONTUARIO_GED_DIRETORIO.trim().equals("")) {
            return false;
        }
        final File pastaGed = new File(PRONTUARIO_GED_DIRETORIO);
        if (!pastaGed.exists()) {
            return false;
        }
        final String DISKFILEUPLOAD_REPOSITORYPATH = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.DISKFILEUPLOAD_REPOSITORYPATH,
                "");
        if (DISKFILEUPLOAD_REPOSITORYPATH == null || DISKFILEUPLOAD_REPOSITORYPATH.trim().equals("")) {
            return false;
        }
        final File pastaUpload = new File(DISKFILEUPLOAD_REPOSITORYPATH);
        if (!pastaUpload.exists()) {
            return false;
        }
        return true;
    }

    @Override
    public void load(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        request.setAttribute("parametrosUploadValidos", this.validaParametrosUpload());
        final UsuarioDTO usuario = WebUtil.getUsuario(request);
        if (usuario == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }
        super.load(document, request, response);

        String VisualizarPasso = request.getParameter("visualizarPasso");
        VisualizarPasso = UtilStrings.nullToVazio(VisualizarPasso);
        if (VisualizarPasso != null && VisualizarPasso.equalsIgnoreCase("C")) {
            // Reclassificar a solicitação
            document.executeScript("visualizaCollapse3()");
        } else {
            document.executeScript("preparaCollapse1()");
        }
        final GrupoService grupoService = (GrupoService) ServiceLocator.getInstance().getService(GrupoService.class, null);

        final HTMLSelect urgencia = document.getSelectById("urgencia");
        urgencia.removeAllOptions();
        urgencia.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
        final HTMLSelect impacto = document.getSelectById("impacto");
        impacto.removeAllOptions();
        impacto.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));

        if (!this.getCalcularDinamicamente().trim().equalsIgnoreCase("S")) {
            urgencia.addOption("B", UtilI18N.internacionaliza(request, "citcorpore.comum.baixa"));
            urgencia.addOption("M", UtilI18N.internacionaliza(request, "citcorpore.comum.media"));
            urgencia.addOption("A", UtilI18N.internacionaliza(request, "citcorpore.comum.alta"));

            impacto.addOption("B", UtilI18N.internacionaliza(request, "citcorpore.comum.baixa"));
            impacto.addOption("M", UtilI18N.internacionaliza(request, "citcorpore.comum.media"));
            impacto.addOption("A", UtilI18N.internacionaliza(request, "citcorpore.comum.alta"));
        } else {
            final Collection<UrgenciaDTO> urgenciaDTO = this.getPrioridadeSolicitacoesService().consultaUrgencia();
            for (final UrgenciaDTO urgenciaTemp : urgenciaDTO) {
                urgencia.addOption(urgenciaTemp.getSiglaUrgencia().toString(), urgenciaTemp.getNivelUrgencia());
            }
            final Collection<ImpactoDTO> impactoDTO = this.getPrioridadeSolicitacoesService().consultaImpacto();
            for (final ImpactoDTO impactoTemp : impactoDTO) {
                impacto.addOption(impactoTemp.getSiglaImpacto().toString(), impactoTemp.getNivelImpacto());
            }
        }

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

        final ContratoService contratoService = (ContratoService) ServiceLocator.getInstance().getService(ContratoService.class, null);
        final ClienteService clienteService = (ClienteService) ServiceLocator.getInstance().getService(ClienteService.class, null);
        final FornecedorService fornecedorService = (FornecedorService) ServiceLocator.getInstance().getService(FornecedorService.class, null);
        final ContratosGruposService contratosGruposService = (ContratosGruposService) ServiceLocator.getInstance().getService(ContratosGruposService.class,
                null);
        final Collection colContratos = contratoService.list();

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
        if (colContratos != null) {
            if (colContratos.size() > 0) {
                document.getSelectById("idContrato").addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
            } else {
                this.acao = true;
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
                    this.contratoDtoAux.setIdContrato(contratoDto.getIdContrato());
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
        SolicitacaoServicoDTO solicitacaoServicoDto = (SolicitacaoServicoDTO) document.getBean();

        if (solicitacaoServicoDto != null && solicitacaoServicoDto.getUrgencia() != null && StringUtils.isNotBlank(solicitacaoServicoDto.getUrgencia())) {
            document.getElementById("urgencia").setValue(solicitacaoServicoDto.getUrgencia().trim());
        }

        if (solicitacaoServicoDto != null && solicitacaoServicoDto.getImpacto() != null && StringUtils.isNotBlank(solicitacaoServicoDto.getImpacto())) {
            document.getElementById("impacto").setValue(solicitacaoServicoDto.getImpacto().trim());
        }

        if (solicitacaoServicoDto != null && solicitacaoServicoDto.getIdContrato() != null) {
            document.getElementById("idContrato").setValue("" + solicitacaoServicoDto.getIdContrato());
        }

        if (request.getParameter("idContrato") != null && !request.getParameter("idContrato").equalsIgnoreCase("")) {
            Integer idContrato = 0;
            idContrato = Integer.parseInt(request.getParameter("idContrato"));

            if (idContrato != null) {
                document.getElementById("idContrato").setValue("" + idContrato);
            }
        }

        document.executeScript("desativarBotaoAvancar1();");
        document.executeScript("desativarBotaoAvancar2();");
        document.executeScript("desativarBotaoAvancar3();");

        if (solicitacaoServicoDto != null) {
            solicitacaoServicoDto.getIdContrato();
        }
        String tarefaAssociada = "N";
        if (solicitacaoServicoDto != null && solicitacaoServicoDto.getIdTarefa() != null) {
            tarefaAssociada = "S";
        }
        request.setAttribute("tarefaAssociada", tarefaAssociada);

        if (solicitacaoServicoDto != null && solicitacaoServicoDto.getIdContrato() != null) {
            this.verificaGrupoExecutor(document, request, response);
            document.getSelectById("idGrupoAtual").setValue("" + solicitacaoServicoDto.getIdGrupoAtual());
        }
        if (this.acao) {
            if (solicitacaoServicoDto.getIdSolicitacaoServico() == null || solicitacaoServicoDto.getIdSolicitacaoServico().intValue() == 0) {
                this.verificaGrupoExecutor(document, request, response);
                this.verificaImpactoUrgencia(document, request, response);
                this.carregaServicosMulti(document, request, response);
                this.carregaUnidade(document, request, response);
            }

        }
        // document.executeScript("$('#loading_overlay').hide();");

        if (solicitacaoServicoDto != null && solicitacaoServicoDto.getIdSolicitacaoServico() != null
                && solicitacaoServicoDto.getIdSolicitacaoServico().intValue() != 0) {
            // document.getElementById("divSLAPrevisto").setVisible(false);
            // document.getElementById("divTipoSolicitacaoServico").setClassName("col_50");
        } else {
            // document.getElementById("divSLAPrevisto").setVisible(true);
            // document.getElementById("divTipoSolicitacaoServico").setClassName("col_30");
        }

        document.executeScript("parent.fecharJanelaAguarde();");
        solicitacaoServicoDto = null;
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

    @Override
    public void sincronizaAD(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        SolicitacaoServicoDTO solicitacaoServicoDto = (SolicitacaoServicoDTO) document.getBean();
        if (solicitacaoServicoDto.getFiltroADPesq() == null) {
            solicitacaoServicoDto.setFiltroADPesq(document.getElementById("filtroADPesq").getValue());
        }

        ContratoDTO contratoDto = new ContratoDTO();

        final ContratoService contratoService = (ContratoService) ServiceLocator.getInstance().getService(ContratoService.class, null);

        contratoDto.setIdContrato(solicitacaoServicoDto.getIdContrato());

        contratoDto = (ContratoDTO) contratoService.restore(contratoDto);

        final Collection<ADUserDTO> listUsuariosADDto = LDAPUtils.consultaEmpregado(solicitacaoServicoDto.getFiltroADPesq(),
                contratoDto.getIdGrupoSolicitante());

        if (listUsuariosADDto != null && !listUsuariosADDto.isEmpty()) {
            document.getElementById("POPUP_SINCRONIZACAO_DETALHE").setInnerHTML(
                    "Sincronização concluída com sucesso. Favor fazer a busca na lookup de colaborador.");
        } else {
            document.getElementById("POPUP_SINCRONIZACAO_DETALHE").setInnerHTML("Nenhum resultado encontrado.");
        }
        document.executeScript("fechar_aguarde();");

        solicitacaoServicoDto = null;

        contratoDto = null;
    }

    public void carregaServicosMulti(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        SolicitacaoServicoDTO solicitacaoServicoDto = (SolicitacaoServicoDTO) document.getBean();
        if (solicitacaoServicoDto.getIdContrato() == null || solicitacaoServicoDto.getIdContrato().intValue() == 0) {
            solicitacaoServicoDto.setIdContrato(this.contratoDtoAux.getIdContrato());
        }
        document.getElementById("divScript").setInnerHTML(UtilI18N.internacionaliza(request, "solicitacaoservico.validacao.scriptservico"));
        final HTMLSelect idServico = document.getSelectById("idServico");
        idServico.removeAllOptions();

        if (solicitacaoServicoDto.getIdTipoDemandaServico() == null) {
            return;
        }
        if (solicitacaoServicoDto.getIdContrato() == null) {
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
            empregadoDto.setIdEmpregado(solicitacaoServicoDto.getIdSolicitante());
            if (solicitacaoServicoDto.getIdSolicitante() != null) {
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
        final Collection col = servicoService.findByIdTipoDemandaAndIdContrato(solicitacaoServicoDto.getIdTipoDemandaServico(),
                solicitacaoServicoDto.getIdContrato(), solicitacaoServicoDto.getIdCategoriaServico());

        int cont = 0;
        Integer idServicoCasoApenas1 = null;
        if (col != null) {
            // this.verificaImpactoUrgencia(document, request, response);
            /* if (col.size() > 1) */

            // idServico.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));

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
                                // idServico.addOptionIfNotExists("" + servicoDTO.getIdServico(), servicoDTO.getNomeServico());
                                idServicoCasoApenas1 = servicoDTO.getIdServico();
                                cont++;
                            }
                        } else {
                            // idServico.addOptionIfNotExists("" + servicoDTO.getIdServico(), servicoDTO.getNomeServico());
                            idServicoCasoApenas1 = servicoDTO.getIdServico();
                            cont++;
                        }
                    }
                }
            }
            // --- RETITRADO POR EMAURI EM 16/07 - TRATAMENTO DE DELETED --> idServico.addOptions(col, "idServico", "nomeServico", null);
        }
        if (cont == 1) { // Se for apenas um servico encontrado, ja executa o carrega contratos.
            solicitacaoServicoDto.setIdServico(idServicoCasoApenas1);
            this.carregaBaseConhecimentoAssoc(document, request, response);
        }
        // document.executeScript("geraAutoCompleteServico()");

        solicitacaoServicoDto = null;
    }

    @Override
    public void carregaUnidade(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final String validarComboUnidade = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.UNIDADE_VINC_CONTRATOS, "N");
        SolicitacaoServicoDTO solicitacaoServicoDto = (SolicitacaoServicoDTO) document.getBean();

        if (solicitacaoServicoDto.getIdSolicitacaoServico() != null && solicitacaoServicoDto.getIdSolicitacaoServico().intValue() > 0) {

            final SolicitacaoServicoService solicitacaoServicoService = (SolicitacaoServicoService) ServiceLocator.getInstance().getService(
                    SolicitacaoServicoService.class, null);

            final ServicoContratoService servicoContratoService = (ServicoContratoService) ServiceLocator.getInstance().getService(
                    ServicoContratoService.class, null);

            solicitacaoServicoDto = (SolicitacaoServicoDTO) solicitacaoServicoService.restore(solicitacaoServicoDto);

            ServicoContratoDTO servicoContratoDTO = new ServicoContratoDTO();
            servicoContratoDTO.setIdServicoContrato(solicitacaoServicoDto.getIdServicoContrato());
            if (solicitacaoServicoDto.getIdServicoContrato() != null) {
                servicoContratoDTO = (ServicoContratoDTO) servicoContratoService.restore(servicoContratoDTO);
            } else {
                servicoContratoDTO = null;
            }
            if (servicoContratoDTO != null) {
                solicitacaoServicoDto.setIdServico(servicoContratoDTO.getIdServico());
                solicitacaoServicoDto.setIdContrato(servicoContratoDTO.getIdContrato());
            }

        }

        if (solicitacaoServicoDto.getIdContrato() == null || solicitacaoServicoDto.getIdContrato().intValue() == 0) {
            solicitacaoServicoDto.setIdContrato(this.contratoDtoAux.getIdContrato());
        }

        final UnidadeService unidadeService = (UnidadeService) ServiceLocator.getInstance().getService(UnidadeService.class, null);
        final HTMLSelect comboUnidadeMultContratos = document.getSelectById("idUnidade");
        this.inicializarCombo(comboUnidadeMultContratos, request);
        if (validarComboUnidade.trim().equalsIgnoreCase("S")) {
            final Integer idContrato = solicitacaoServicoDto.getIdContrato();
            final ArrayList<UnidadeDTO> unidades = (ArrayList) unidadeService.listHierarquiaMultiContratos(idContrato);
            if (unidades != null) {
                for (final UnidadeDTO unidade : unidades) {
                    if (unidade.getDataFim() == null) {
                        comboUnidadeMultContratos.addOption(unidade.getIdUnidade().toString(), unidade.getNomeNivel());
                    }

                }
            }
        } else {
            final ArrayList<UnidadeDTO> unidades = (ArrayList) unidadeService.listHierarquia();
            if (unidades != null) {
                for (final UnidadeDTO unidade : unidades) {
                    if (unidade.getDataFim() == null) {
                        comboUnidadeMultContratos.addOption(unidade.getIdUnidade().toString(), unidade.getNomeNivel());
                    }
                }
            }
        }

        solicitacaoServicoDto = null;

    }

    private void inicializarCombo(final HTMLSelect componenteCombo, final HttpServletRequest request) {
        componenteCombo.removeAllOptions();
        componenteCombo.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
    }

    @Override
    public void carregaServicos(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        this.carregaServicosMulti(document, request, response);
    }

    @Override
    public void verificaImpactoUrgencia(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        SolicitacaoServicoDTO solicitacaoServicoDto = (SolicitacaoServicoDTO) document.getBean();

        if (solicitacaoServicoDto.getIdContrato() == null || solicitacaoServicoDto.getIdContrato().intValue() == 0) {
            solicitacaoServicoDto.setIdContrato(this.contratoDtoAux.getIdContrato());
        }
        document.getSelectById("impacto").setDisabled(false);
        document.getSelectById("urgencia").setDisabled(false);
        if (solicitacaoServicoDto.getIdServico() == null || solicitacaoServicoDto.getIdContrato() == null) {
            return;
        }

        final ServicoContratoService servicoContratoService = (ServicoContratoService) ServiceLocator.getInstance().getService(ServicoContratoService.class,
                null);
        ServicoContratoDTO servicoContratoDto = servicoContratoService.findByIdContratoAndIdServico(solicitacaoServicoDto.getIdContrato(),
                solicitacaoServicoDto.getIdServico());

        if (servicoContratoDto != null) {
            final AcordoNivelServicoService acordoNivelServicoService = (AcordoNivelServicoService) ServiceLocator.getInstance().getService(
                    AcordoNivelServicoService.class, null);
            AcordoNivelServicoDTO acordoNivelServicoDto = acordoNivelServicoService
                    .findAtivoByIdServicoContrato(servicoContratoDto.getIdServicoContrato(), "T");
            if (acordoNivelServicoDto == null) {
                // Se nao houver acordo especifico, ou seja, associado direto ao servicocontrato, entao busca um acordo geral que esteja vinculado ao
                // servicocontrato.
                final AcordoServicoContratoService acordoServicoContratoService = (AcordoServicoContratoService) ServiceLocator.getInstance().getService(
                        AcordoServicoContratoService.class, null);
                final AcordoServicoContratoDTO acordoServicoContratoDTO = acordoServicoContratoService.findAtivoByIdServicoContrato(
                        servicoContratoDto.getIdServicoContrato(), "T");
                if (acordoServicoContratoDTO == null) {
                    document.alert(UtilI18N.internacionaliza(request, "solicitacaoservico.validacao.tempoacordo"));
                    return;
                }
                // Apos achar a vinculacao do acordo com o servicocontrato, entao faz um restore do acordo de nivel de servico.
                acordoNivelServicoDto = new AcordoNivelServicoDTO();
                acordoNivelServicoDto.setIdAcordoNivelServico(acordoServicoContratoDTO.getIdAcordoNivelServico());
                acordoNivelServicoDto = (AcordoNivelServicoDTO) new AcordoNivelServicoDao().restore(acordoNivelServicoDto);
                if (acordoNivelServicoDto == null) {
                    // Se nao houver acordo especifico, ou seja, associado direto ao servicocontrato
                    document.alert(UtilI18N.internacionaliza(request, "solicitacaoservico.validacao.tempoacordo"));
                    return;
                }
            }
            if (acordoNivelServicoDto.getImpacto() != null) {
                document.getSelectById("impacto").setValue("" + acordoNivelServicoDto.getImpacto());
                if (acordoNivelServicoDto.getPermiteMudarImpUrg() != null && acordoNivelServicoDto.getPermiteMudarImpUrg().equalsIgnoreCase("N")) {
                    document.getSelectById("impacto").setDisabled(true);
                }
            } else {
                document.getSelectById("impacto").setValue("B");
            }
            if (acordoNivelServicoDto.getUrgencia() != null) {
                document.getSelectById("urgencia").setValue("" + acordoNivelServicoDto.getUrgencia());
                if (acordoNivelServicoDto.getPermiteMudarImpUrg() != null && acordoNivelServicoDto.getPermiteMudarImpUrg().equalsIgnoreCase("N")) {
                    document.getSelectById("urgencia").setDisabled(true);
                }
            } else {
                document.getSelectById("urgencia").setValue("B");
            }
        } else {
            document.getSelectById("impacto").setValue("B");
            document.getSelectById("urgencia").setValue("B");
        }

        servicoContratoDto = null;

        solicitacaoServicoDto = null;
    }

    @Override
    public void restore(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        super.restore(document, request, response);
        SolicitacaoServicoDTO solicitacaoServicoDto = (SolicitacaoServicoDTO) document.getBean();
        if (solicitacaoServicoDto.getEditar() == null) {
            solicitacaoServicoDto.setEditar("S");
        }
        if (solicitacaoServicoDto.getEditar().equalsIgnoreCase("N")) {
            // document.getElementById("tdListServicos").setVisible(false);
            // document.getElementById("tdLimparServicos").setVisible(false);
            document.getTextBoxById("servicoBusca").setDisabled(true);
        } else {
            if (solicitacaoServicoDto.getReclassificar() == null || solicitacaoServicoDto.getReclassificar().equalsIgnoreCase("N")) {
                // document.getElementById("tdListServicos").setVisible(false);
                // document.getElementById("tdLimparServicos").setVisible(false);
                document.getTextBoxById("servicoBusca").setDisabled(true);
            }
        }

        solicitacaoServicoDto = null;
    }

    @Override
    public void verificaGrupoExecutor(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        SolicitacaoServicoDTO solicitacaoServicoDto = (SolicitacaoServicoDTO) document.getBean();
        if (solicitacaoServicoDto.getIdContrato() == null || solicitacaoServicoDto.getIdContrato().intValue() == 0) {
            solicitacaoServicoDto.setIdContrato(this.contratoDtoAux.getIdContrato());
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
            final Collection colGrupos = grupoSegurancaService.listGruposServiceDeskByIdContrato(solicitacaoServicoDto.getIdContrato());
            if (colGrupos != null) {
                idGrupoAtual.addOptions(colGrupos, "idGrupo", "nome", null);
            }
        }

        this.verificaGrupoExecutorInterno(document, solicitacaoServicoDto);

        solicitacaoServicoDto = null;
    }

    @Override
    public void verificaGrupoExecutorInterno(final DocumentHTML document, final SolicitacaoServicoDTO solicitacaoServicoDto) throws Exception {
        if (solicitacaoServicoDto.getIdServico() == null || solicitacaoServicoDto.getIdContrato() == null) {
            return;
        }

        final ServicoContratoService servicoContratoService = (ServicoContratoService) ServiceLocator.getInstance().getService(ServicoContratoService.class,
                null);
        final ServicoContratoDTO servicoContratoDto = servicoContratoService.findByIdContratoAndIdServico(solicitacaoServicoDto.getIdContrato(),
                solicitacaoServicoDto.getIdServico());
        if (servicoContratoDto != null && servicoContratoDto.getIdGrupoExecutor() != null) {
            document.getElementById("idGrupoAtual").setValue("" + servicoContratoDto.getIdGrupoExecutor());
        } else {
            document.getElementById("idGrupoAtual").setValue("");
        }
    }

    @Override
    public void carregarModalDuplicarSolicitacao(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws ServiceException, Exception {

        SolicitacaoServicoDTO solicitacaoServicoDto = (SolicitacaoServicoDTO) document.getBean();

        final SolicitacaoServicoService solicitacaoServicoService = (SolicitacaoServicoService) ServiceLocator.getInstance().getService(
                SolicitacaoServicoService.class, null);
        final ServicoContratoService servicoContratoService = (ServicoContratoService) ServiceLocator.getInstance().getService(ServicoContratoService.class,
                null);

        solicitacaoServicoDto = (SolicitacaoServicoDTO) solicitacaoServicoService.restore(solicitacaoServicoDto);

        ServicoContratoDTO servicoContratoDto = new ServicoContratoDTO();

        servicoContratoDto.setIdServicoContrato(solicitacaoServicoDto.getIdServicoContrato());

        servicoContratoDto = (ServicoContratoDTO) servicoContratoService.restore(servicoContratoDto);

        solicitacaoServicoDto.setIdContrato(servicoContratoDto.getIdContrato());

        this.carregaComboOrigem(document, request);

        this.carregaUnidade(document, request, response);

        super.preencherComboLocalidade(document, request, response);

        final HTMLForm formSolicitacaoServico = document.getForm("formInformacoesContato");
        formSolicitacaoServico.setValues(solicitacaoServicoDto);

        solicitacaoServicoDto = null;

        servicoContratoDto = null;

    }

    @Override
    public void duplicarSolicitacao(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws ServiceException,
    Exception {

        SolicitacaoServicoDTO novaSolicitacaoServicoDto = (SolicitacaoServicoDTO) document.getBean();

        UsuarioDTO usuarioDto = new UsuarioDTO();

        usuarioDto = WebUtil.getUsuario(request);

        final SolicitacaoServicoService solicitacaoServicoService = (SolicitacaoServicoService) ServiceLocator.getInstance().getService(
                SolicitacaoServicoService.class, null);
        final ServicoContratoService servicoContratoService = (ServicoContratoService) ServiceLocator.getInstance().getService(ServicoContratoService.class,
                null);

        SolicitacaoServicoDTO solicitacaoServicoOrigem = new SolicitacaoServicoDTO();
        ServicoContratoDTO servicoContratoDto = new ServicoContratoDTO();

        solicitacaoServicoOrigem.setIdSolicitacaoServico(novaSolicitacaoServicoDto.getIdSolicitacaoServico());

        solicitacaoServicoOrigem = (SolicitacaoServicoDTO) solicitacaoServicoService.restore(solicitacaoServicoOrigem);

        servicoContratoDto.setIdServicoContrato(solicitacaoServicoOrigem.getIdServicoContrato());

        servicoContratoDto = (ServicoContratoDTO) servicoContratoService.restore(servicoContratoDto);

        novaSolicitacaoServicoDto.setIdSolicitacaoServico(null);
        novaSolicitacaoServicoDto.setIdSolicitacaoPai(solicitacaoServicoOrigem.getIdSolicitacaoServico());
        novaSolicitacaoServicoDto.setIdContatoSolicitacaoServico(null);

        novaSolicitacaoServicoDto.setIdServico(servicoContratoDto.getIdServico());

        novaSolicitacaoServicoDto.setUsuarioDto(usuarioDto);
        novaSolicitacaoServicoDto.setDescricao(solicitacaoServicoOrigem.getDescricao());
        novaSolicitacaoServicoDto.setSituacao(solicitacaoServicoOrigem.getSituacao());
        novaSolicitacaoServicoDto.setRegistroexecucao("");
        novaSolicitacaoServicoDto.setEnviaEmailCriacao("S");

        novaSolicitacaoServicoDto = (SolicitacaoServicoDTO) solicitacaoServicoService.create(novaSolicitacaoServicoDto);

        document.alert(UtilI18N.internacionaliza(request, "gerenciaservico.duplicadacomsucesso"));

        document.executeScript("fecharPopup(\"#formInformacoesContato\")");

        novaSolicitacaoServicoDto = null;
        solicitacaoServicoOrigem = null;
        servicoContratoDto = null;

    }

    @Override
    public void restauraSolicitante(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        SolicitacaoServicoDTO solicitacaoServicoDto = (SolicitacaoServicoDTO) document.getBean();

        final EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);
        final SolicitacaoServicoService solicitacaoServicoService = (SolicitacaoServicoService) ServiceLocator.getInstance().getService(
                SolicitacaoServicoService.class, WebUtil.getUsuarioSistema(request));
        final UsuarioService usuarioService = (UsuarioService) ServiceLocator.getInstance().getService(UsuarioService.class, null);

        EmpregadoDTO empregadoDto = new EmpregadoDTO();
        empregadoDto.setIdEmpregado(solicitacaoServicoDto.getIdSolicitante());
        empregadoDto = (EmpregadoDTO) empregadoService.restore(empregadoDto);

        if (empregadoDto != null) {
            solicitacaoServicoDto.setSolicitante(empregadoDto.getNome());
            solicitacaoServicoDto.setNomecontato(empregadoDto.getNome());
            solicitacaoServicoDto.setTelefonecontato(empregadoDto.getTelefone());
            solicitacaoServicoDto.setEmailcontato(empregadoDto.getEmail());
            solicitacaoServicoDto.setIdUnidade(empregadoDto.getIdUnidade());
            this.preencherComboLocalidade(document, request, response);
        }

        UsuarioDTO usuarioDto = new UsuarioDTO();
        if (empregadoDto != null) {
            usuarioDto = usuarioService.restoreByIdEmpregado(empregadoDto.getIdEmpregado());
        }

        if (usuarioDto != null) {
            final String login = usuarioDto.getLogin();

            final SolicitacaoServicoDTO solicitacaoServicoComItemConfiguracaoDoSolicitante = solicitacaoServicoService
                    .retornaSolicitacaoServicoComItemConfiguracaoDoSolicitante(login);

            if (solicitacaoServicoComItemConfiguracaoDoSolicitante != null) {
                solicitacaoServicoDto.setIdItemConfiguracao(solicitacaoServicoComItemConfiguracaoDoSolicitante.getIdItemConfiguracao());
                solicitacaoServicoDto.setItemConfiguracao(solicitacaoServicoComItemConfiguracaoDoSolicitante.getItemConfiguracao());
                document.executeScript("exibeCampos()");
            }
        }

        final HTMLForm formSolicitacaoServico = document.getForm("formInformacoesContato");
        formSolicitacaoServico.setValues(solicitacaoServicoDto);
        document.executeScript("fecharPopup(\"#POPUP_SOLICITANTE\")");

        solicitacaoServicoDto = null;

    }

    private void carregaComboOrigem(final DocumentHTML document, final HttpServletRequest request) throws ServiceException, Exception, LogicException {
        final OrigemAtendimentoService origemService = (OrigemAtendimentoService) ServiceLocator.getInstance().getService(OrigemAtendimentoService.class, null);

        final HTMLSelect origem = document.getSelectById("idOrigem");

        origem.removeAllOptions();
        this.inicializarCombo(origem, request);

        final Collection listOrigem = origemService.list();

        if (listOrigem != null) {

            origem.addOptions(listOrigem, "idOrigem", "descricao", null);
        }
    }

    @Override
    public void preenchePorEmail(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws ServiceException,
    Exception {

        final EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);

        SolicitacaoServicoDTO solicitacaoServicoDto = (SolicitacaoServicoDTO) document.getBean();
        EmpregadoDTO empregadoDTO = new EmpregadoDTO();
        empregadoDTO = empregadoService.listEmpregadoContrato(solicitacaoServicoDto.getIdContrato(), solicitacaoServicoDto.getEmailcontato());
        if (empregadoDTO != null) {
            document.getElementById("idSolicitante").setValue(empregadoDTO.getIdEmpregado().toString());
            document.getElementById("nomecontato").setValue(empregadoDTO.getNome());
            document.getElementById("telefonecontato").setValue(empregadoDTO.getTelefone());
            document.getElementById("idUnidade").setValue(empregadoDTO.getIdUnidade().toString());
            document.getElementById("solicitante").setValue(empregadoDTO.getNome());
            document.getElementById("idOrigem").setValue("3");
        }

        solicitacaoServicoDto = null;
        empregadoDTO = null;
    }

    @Override
    public void calculaSLA(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        try {
            final SolicitacaoServicoDTO solicitacaoServicoDto = (SolicitacaoServicoDTO) document.getBean();
            document.executeScript("startLoading()");
            if (solicitacaoServicoDto.getIdContrato() == null || solicitacaoServicoDto.getIdContrato().intValue() == 0) {
                throw new Exception("Contrato não encontrado");
            }
            if (solicitacaoServicoDto.getIdServico() == null || solicitacaoServicoDto.getIdServico().intValue() == 0) {
                throw new Exception("Serviço não encontrado");
            }
            if (solicitacaoServicoDto.getUrgencia() == null || solicitacaoServicoDto.getUrgencia().isEmpty()) {
                throw new Exception("Urgência não encontrada");
            }
            if (solicitacaoServicoDto.getImpacto() == null || solicitacaoServicoDto.getImpacto().isEmpty()) {
                throw new Exception("Impacto não encontrado");
            }

            final SolicitacaoServicoService solicitacaoServicoService = (SolicitacaoServicoService) ServiceLocator.getInstance().getService(
                    SolicitacaoServicoService.class, null);
            final String sla = solicitacaoServicoService.calculaSLA(solicitacaoServicoDto, request);
            document.executeScript("document.getElementById('tdResultadoSLAPrevisto').innerHTML = '" + sla + "';");
            document.executeScript("stopLoading()");
            document.executeScript("document.getElementById('tdResultadoSLAPrevisto').style.display='block'");
            document.executeScript("document.getElementById('tdResultadoSLAPrevisto1').style.display='none'");
        } catch (final Exception e) {
            document.executeScript("stopLoading()");
            document.executeScript("document.getElementById('tdResultadoSLAPrevisto').innerHTML = '';");
            document.executeScript("document.getElementById('tdResultadoSLAPrevisto1').style.display='none'");
        }
    }

    @Override
    public void atualizaGridProblema(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        SolicitacaoServicoDTO bean = (SolicitacaoServicoDTO) document.getBean();
        final ProblemaService problemaService = (ProblemaService) ServiceLocator.getInstance().getService(ProblemaService.class, null);
        ProblemaDTO problemaDTO = new ProblemaDTO();

        problemaDTO.setIdProblema(bean.getIdProblema());

        problemaDTO = (ProblemaDTO) problemaService.restore(problemaDTO);
        if (problemaDTO == null) {
            return;
        }
        final HTMLTable tblProblema = document.getTableById("tblProblema");

        if (problemaDTO.getSequenciaProblema() == null) {
            tblProblema.addRow(problemaDTO, new String[] {"", "", "numberAndTitulo", "status"}, new String[] {"idProblema"}, "Problema já cadastrado!!",
                    new String[] {"exibeIconesProblema"}, "buscaProblema", null);

        } else {
            tblProblema.updateRow(problemaDTO, new String[] {"", "", "numberAndTitulo", "status"}, new String[] {"idProblema"}, "Problema já cadastrado!!",
                    new String[] {"exibeIconesProblema"}, "buscaProblema", null, problemaDTO.getSequenciaProblema());
        }
        document.executeScript("HTMLUtils.applyStyleClassInAllCells('tblProblema', 'tblProblema');");
        document.executeScript("fecharModalProblema();");

        bean = null;
    }

    @Override
    public void atualizaGridMudanca(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        SolicitacaoServicoDTO bean = (SolicitacaoServicoDTO) document.getBean();

        final RequisicaoMudancaService requisicaoMudancaService = (RequisicaoMudancaService) ServiceLocator.getInstance().getService(
                RequisicaoMudancaService.class, null);
        RequisicaoMudancaDTO requisicaoMudancaDTO = new RequisicaoMudancaDTO();

        requisicaoMudancaDTO.setIdRequisicaoMudanca(bean.getIdRequisicaoMudanca());

        requisicaoMudancaDTO = (RequisicaoMudancaDTO) requisicaoMudancaService.restore(requisicaoMudancaDTO);

        final HTMLTable tblMudanca = document.getTableById("tblMudanca");

        if (requisicaoMudancaDTO.getSequenciaMudanca() == null) {
            tblMudanca.addRow(null, new String[] {"", "", "numberAndTitulo", "status"}, new String[] {"idRequisicaoMudanca"}, "Mudança já cadastrado!!",
                    new String[] {"exibeIconesMudanca"}, "abreMudanca", null);
        } else {
            tblMudanca.updateRow(requisicaoMudancaDTO, new String[] {"", "", "numberAndTitulo", "status"}, new String[] {"idRequisicaoMudanca"},
                    "Mudança já cadastrado!!", new String[] {"exibeIconesMudanca"}, "abreMudanca", null, requisicaoMudancaDTO.getSequenciaMudanca());
        }
        document.getElementById("teste").setValue(tblMudanca.toString());
        document.executeScript("HTMLUtils.applyStyleClassInAllCells('tblMudanca', 'tblMudanca');");
        document.executeScript("fecharModalMudanca();");

        bean = null;
    }

    @Override
    public void atualizaGridBaseConhecimento(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        final SolicitacaoServicoDTO solicitacaoServicoDTO = (SolicitacaoServicoDTO) document.getBean();

        final BaseConhecimentoService baseConhecimentoService = (BaseConhecimentoService) ServiceLocator.getInstance().getService(
                BaseConhecimentoService.class, null);
        BaseConhecimentoDTO baseConhecimentoDTO = new BaseConhecimentoDTO();

        if (solicitacaoServicoDTO.getIdItemBaseConhecimento() != null) {
            baseConhecimentoDTO.setIdBaseConhecimento(solicitacaoServicoDTO.getIdItemBaseConhecimento());
            baseConhecimentoDTO = (BaseConhecimentoDTO) baseConhecimentoService.restore(baseConhecimentoDTO);

            final HTMLTable tblBaseConhecimento = document.getTableById("tblBaseConhecimento");

            if (baseConhecimentoDTO.getSequenciaBaseConhecimento() == null) {
                tblBaseConhecimento.addRow(baseConhecimentoDTO, new String[] {"", "", "idBaseConhecimento", "titulo"}, new String[] {"idBaseConhecimento"},
                        UtilI18N.internacionaliza(request, "baseConhecimento.baseConhecimentoJaCadastrada"), new String[] {"exibeIconesBaseConhecimento"},
                        null, null);
            } else {
                tblBaseConhecimento.updateRow(baseConhecimentoDTO, new String[] {"", "", "idBaseConhecimento", "titulo"}, new String[] {"idBaseConhecimento"},
                        UtilI18N.internacionaliza(request, "baseConhecimento.baseConhecimentoJaCadastrada"), new String[] {"exibeIconesBaseConhecimento"},
                        null, null, baseConhecimentoDTO.getSequenciaBaseConhecimento());
            }
            document.executeScript("HTMLUtils.applyStyleClassInAllCells('tblBaseConhecimento', 'tblBaseConhecimento');");
            document.executeScript("fecharBaseConhecimento();");
        }
    }

    @Override
    public void atualizaGridItemConfiguracao(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        final SolicitacaoServicoDTO solicitacaoServicoDTO = (SolicitacaoServicoDTO) document.getBean();

        final ItemConfiguracaoService baseConhecimentoService = (ItemConfiguracaoService) ServiceLocator.getInstance().getService(
                ItemConfiguracaoService.class, null);
        ItemConfiguracaoDTO itemConfiguracaoDTO = new ItemConfiguracaoDTO();

        if (solicitacaoServicoDTO.getIdItemBaseConhecimento() != null) {
            itemConfiguracaoDTO.setIdItemConfiguracao(solicitacaoServicoDTO.getIdItemBaseConhecimento());
            itemConfiguracaoDTO = (ItemConfiguracaoDTO) baseConhecimentoService.restore(itemConfiguracaoDTO);

            final HTMLTable tblBaseConhecimento = document.getTableById("tblIC");

            if (itemConfiguracaoDTO.getSequenciaIC() == null) {
                tblBaseConhecimento.addRow(itemConfiguracaoDTO, new String[] {"", "", "idItemConfiguracao", "descricao"}, new String[] {"idItemConfiguracao"},
                        "Item Configuração já cadastrado!!", new String[] {"exibeIconesMudanca"}, "abreItemConfiguracao", null);

            } else {
                tblBaseConhecimento.updateRow(itemConfiguracaoDTO, new String[] {"", "", "idBaseConhecimento", "titulo"}, new String[] {"idBaseConhecimento"},
                        UtilI18N.internacionaliza(request, "baseConhecimento.baseConhecimentoJaCadastrada"), new String[] {"exibeIconesBaseConhecimento"},
                        null, null, itemConfiguracaoDTO.getSequenciaIC());
            }
            document.executeScript("HTMLUtils.applyStyleClassInAllCells('tblIC', 'tblIC');");
            document.executeScript("fecharModalItemConfiguracao();");
        }
    }
}
