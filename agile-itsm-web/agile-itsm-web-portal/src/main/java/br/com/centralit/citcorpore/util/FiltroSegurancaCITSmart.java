package br.com.centralit.citcorpore.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import br.com.centralit.citcorpore.bean.MenuDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.negocio.MenuService;
import br.com.centralit.citcorpore.negocio.VersaoService;
import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Constantes;

public class FiltroSegurancaCITSmart implements Filter {

    private static final int ACESSO_NEGADO = 403;
    private static final String INTERROGACAO = "?";
    private static Boolean HA_VERSOES_SEM_VALIDACAO = null;

    private static Boolean hasVersaoSemValidacao() {
        try {
            if (HA_VERSOES_SEM_VALIDACAO == null) {
                final VersaoService service = (VersaoService) ServiceLocator.getInstance().getService(VersaoService.class, null);
                HA_VERSOES_SEM_VALIDACAO = service.haVersoesSemValidacao();
            }
            return HA_VERSOES_SEM_VALIDACAO;
        } catch (final Exception e) {
            return true;
        }
    }

    public static void setHaVersoesSemValidacao(final boolean haVersoesSemValidacao) {
        FiltroSegurancaCITSmart.HA_VERSOES_SEM_VALIDACAO = haVersoesSemValidacao;
    }

    @Override
    public void destroy() {}

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain) throws IOException,
            ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = this.getRequestedPath(request);
        final String context = request.getContextPath();

        final UsuarioDTO usuario = WebUtil.getUsuario(request);

        if (this.isFilesLivres(path)) {
            chain.doFilter(request, response);
            return;
        }

        if (this.isPaginaScript(path)) {
            chain.doFilter(request, response);
            return;
        }

        if (path.equals("/pages/portal/portal.load") && usuario == null) {
            request.getSession(true).setAttribute("abrePortal", "S");
        }

        if (this.isRecursoLivre(path) && !hasVersaoSemValidacao()) {
            chain.doFilter(request, response);
            return;
        }

        String CAMINHO_RELATIVO_PAGINA_LOGIN = Constantes.getValue("CAMINHO_RELATIVO_PAGINA_LOGIN");
        if (CAMINHO_RELATIVO_PAGINA_LOGIN == null || CAMINHO_RELATIVO_PAGINA_LOGIN.trim().equalsIgnoreCase("")) {
            CAMINHO_RELATIVO_PAGINA_LOGIN = context + "/login/login.load";
        }

        if (path.equalsIgnoreCase("") || path.equalsIgnoreCase("/")) {
            if (CAMINHO_RELATIVO_PAGINA_LOGIN == null || CAMINHO_RELATIVO_PAGINA_LOGIN.trim().equalsIgnoreCase("")) {
                CAMINHO_RELATIVO_PAGINA_LOGIN = context + "/login/login.load";
            }
            response.sendRedirect(CAMINHO_RELATIVO_PAGINA_LOGIN);
            return;
        }

        if (path.equals("/portal/portal.load") && usuario != null) {
            response.sendRedirect(context + "/pages/portal/portal.load");
            return;
        }

        if (usuario == null) {
            if (CAMINHO_RELATIVO_PAGINA_LOGIN == null || CAMINHO_RELATIVO_PAGINA_LOGIN.trim().equalsIgnoreCase("")) {
                CAMINHO_RELATIVO_PAGINA_LOGIN = context + "/login/login.load";
            }

            // Verifica se eh a pagina de login para nao ficar em redirect infinito.
            if (CAMINHO_RELATIVO_PAGINA_LOGIN.contains(path)) {
                chain.doFilter(request, response);
                return;
            }

            // Deixa passar, se nao nem loga.
            if (path.endsWith("/login.save")) {
                chain.doFilter(request, response);
                return;
            }

            // Deixa passar, se nao nem loga.
            if (path.equalsIgnoreCase("/ActionServletMarcacao")) {
                chain.doFilter(request, response);
                return;
            }
            response.sendRedirect(CAMINHO_RELATIVO_PAGINA_LOGIN);
            return;
        }

        if (!usuario.getLogin().equals("consultor") && !usuario.getLogin().equals("admin")) {
            if ("N".equalsIgnoreCase(usuario.getAcessoCitsmart())) {
                response.sendRedirect(context + "/pages/portal/portal.load");
                return;
            }
        }

        final String idPerfilAcessoAdministrador = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.ID_PERFIL_ACESSO_ADMINISTRADOR, "1");
        final boolean usuarioTemPerfilDeAdministrador = usuario.getIdPerfilAcessoUsuario() != null
                && usuario.getIdPerfilAcessoUsuario().toString().trim().equals(idPerfilAcessoAdministrador.trim());

        // O usuario "admin" ou "admin.centralit" tem acesso a tudo.
        if (usuario.getNomeUsuario().equalsIgnoreCase("consultor") || usuario.getNomeUsuario().equalsIgnoreCase("admin") || usuarioTemPerfilDeAdministrador) {
            if (!hasVersaoSemValidacao()) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(context + "/pages/scripts/scripts.load?upgrade=sim");
            }
            return;
        }

        String SEGURANCA_HABILITADA = Constantes.getValue("SEGURANCA_HABILITADA");
        if (SEGURANCA_HABILITADA == null) {
            SEGURANCA_HABILITADA = "N";
        }

        // Se nao estiver setado como "S", entao esta livre.
        if (!SEGURANCA_HABILITADA.equalsIgnoreCase("S")) {
            chain.doFilter(request, response);
            return;
        }

        try {
            if (this.isRecursoLivre(path)) {
                chain.doFilter(request, response);
                return;
            }
            Collection<String> col = (Collection<String>) request.getSession(true).getAttribute("acessosUsuario");

            // Caso ainda nao tenha carregado a colecao com as autorizações, entao carrega.
            if (col == null) {
                final Collection<String> colPathsAutorizadosUsuario = new ArrayList<>();
                final MenuService menuService = (MenuService) ServiceLocator.getInstance().getService(MenuService.class, null);
                final Collection<MenuDTO> listaPermissao = menuService.listaMenuByUsr(usuario);
                if (listaPermissao != null && listaPermissao.size() > 0) {
                    for (final MenuDTO beanMenu : listaPermissao) {
                        if (!"".equalsIgnoreCase(beanMenu.getLink())) {
                            colPathsAutorizadosUsuario.add("/pages" + beanMenu.getLink());
                        }
                    }
                }
                col = colPathsAutorizadosUsuario;
                request.getSession(true).setAttribute("acessosUsuario", col);
            }
            boolean bAutorizado = false;
            if (col != null) {
                bAutorizado = col.contains(path);
            }
            if (bAutorizado) {
                chain.doFilter(request, response);
                return;
            } else {
                if (path.startsWith("/")) {
                    path = path.substring(1);

                    // Faz mais uma tentativa, mas agora sem a barra que havia.
                    bAutorizado = col.contains(path);
                    if (bAutorizado) {
                        chain.doFilter(request, response);
                        return;
                    }
                }
                request.getSession(true).setAttribute("acessoSolicitado", path);
                this.sendError(ACESSO_NEGADO, "O usuario nao tem acesso ao recurso solicitado.", response);
            }
        } catch (final Exception e) {
            System.out.println("CITSMART - Filtro de Seguranca: Problemas -> " + e.getMessage());
            e.printStackTrace();
            chain.doFilter(request, response);
        }
    }

    private String getRequestedPath(final HttpServletRequest request) {
        String path = request.getRequestURI();
        path = path.substring(request.getContextPath().length());
        final int index = path.indexOf(INTERROGACAO);
        if (index != -1) {
            path = path.substring(0, index);
        }
        return path != null ? path : "";
    }

    @Override
    public void init(final FilterConfig arg0) throws ServletException {
        try {
            final VersaoService service = (VersaoService) ServiceLocator.getInstance().getService(VersaoService.class, null);
            setHaVersoesSemValidacao(service.haVersoesSemValidacao());
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isFilesLivres(String requestedPath) {
        if (requestedPath.endsWith(".old")) {
            return true;
        }
        if (requestedPath.endsWith(".json")) {
            return true;
        }
        if (requestedPath.endsWith(".zip")) {
            return true;
        }
        if (requestedPath.endsWith(".cab")) {
            return true;
        }
        if (requestedPath.endsWith(".smart")) {
            return true;
        }
        if (requestedPath.endsWith(".eot")) {
            return true;
        }
        if (requestedPath.endsWith(".extern")) {
            return true;
        }
        if (requestedPath.endsWith(".getFile")) {
            return true;
        }
        if (requestedPath.endsWith(".jar")) {
            return true;
        }
        if (requestedPath.endsWith(".swf")) {
            return true;
        }
        if (requestedPath.endsWith(".mp3")) {
            return true;
        }
        if (requestedPath.endsWith(".wav")) {
            return true;
        }
        if (requestedPath.endsWith(".woff")) {
            return true;
        }
        if (requestedPath.endsWith(".ico")) {
            return true;
        }
        if (requestedPath.endsWith(".manifest")) {
            return true;
        }
        if (requestedPath.endsWith("svg-editor.jsp")) {
            return true;
        }

        requestedPath = requestedPath.toLowerCase();
        if (requestedPath.endsWith(".js")) {
            return true;
        }
        if (requestedPath.endsWith(".css")) {
            return true;
        }
        if (requestedPath.endsWith(".pdf")) {
            return true;
        }
        if (requestedPath.endsWith(".xml")) {
            return true;
        }
        if (requestedPath.endsWith(".json")) {
            return true;
        }
        if (requestedPath.endsWith(".xls") || requestedPath.endsWith(".xlsx")) {
            return true;
        }
        if (requestedPath.endsWith(".doc") || requestedPath.endsWith(".docx")) {
            return true;
        }
        if (requestedPath.endsWith(".gif") || requestedPath.endsWith(".jpg") || requestedPath.endsWith(".PNG") || requestedPath.endsWith(".bmp")
                || requestedPath.endsWith(".dcm") || requestedPath.endsWith(".dc3") || requestedPath.endsWith(".svg")) {
            return true;
        }

        return false;
    }

    private boolean isPaginaScript(final String requestedPath) {
        return requestedPath.endsWith("/scripts/scripts.load") || requestedPath.endsWith("/scripts/scripts.get")
                || requestedPath.endsWith("/scripts/scripts.event") || requestedPath.contains("/scripts_deploy/") || requestedPath.endsWith("vazio.jsp")
                || requestedPath.endsWith("/start/start.event") || requestedPath.endsWith("/start/start.load") || requestedPath.endsWith("/start/start.get")
                || requestedPath.endsWith("/login/login.load") || requestedPath.endsWith("/login/login.save") || requestedPath.endsWith("/login/login.get")
                || requestedPath.endsWith("/start/termos.html") || requestedPath.endsWith("/start/start.load.event")
                || requestedPath.endsWith("/start/termos_pt_BR.html") || requestedPath.endsWith("/start/termos_en.html")
                || requestedPath.endsWith("/start/termos_es.html");
    }

    private boolean isRecursoLivre(final String requestedPath) {
        if (requestedPath.endsWith("/pesquisaRequisicaoLiberacao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/login.load")) {
            return true;
        }
        if (requestedPath.endsWith("/refreshuploadAnexos.load")) {
            return true;
        }
        if (requestedPath.endsWith("/refreshuploadAnexosList.load")) {
            return true;
        }
        if (requestedPath.endsWith("/refreshuploadAnexosdocsLegais.load")) {
            return true;
        }
        if (requestedPath.endsWith("/uploadExcluirDocsLegais.get")) {
            return true;
        }

        if (requestedPath.endsWith("/refreshuploadAnexosdocsGerais.load")) {
            return true;
        }
        if (requestedPath.endsWith("/uploadExcluirDocsGerais.get")) {
            return true;
        }

        if (requestedPath.endsWith("/ActionServlet")) {
            return true;
        }
        if (requestedPath.endsWith("/CertDisplay")) {
            return true;
        }
        if (requestedPath.endsWith("/CertVerifier")) {
            return true;
        }
        if (requestedPath.indexOf("/applet/") > -1) {
            return true;
        }
        if (requestedPath.indexOf("/services/") > -1) {
            return true;
        }
        if (requestedPath.indexOf("/mobile/") > -1) {
            return true;
        }
        if (requestedPath.indexOf("/certificadodigital/") > -1) {
            return true;
        }
        if (requestedPath.endsWith("/ActionServletLogin")) {
            return true;
        }
        if (requestedPath.endsWith("/dataManagerObjects.load")) {
            return true;
        }
        if (requestedPath.endsWith("/solicitacaoServicoQuestionario.load")) {
            return true;
        }
        if (requestedPath.endsWith("/liberacaoQuestionario.load")) {
            return true;
        }
        if (requestedPath.endsWith("vazio.jsp")) {
            return true;
        }
        if (requestedPath.endsWith("/upload.load")) {
            return true;
        }
        if (requestedPath.endsWith("/uploadList.load")) {
            return true;
        }
        if (requestedPath.endsWith("/uploadDocsLegais.load")) {
            return true;
        }
        if (requestedPath.endsWith("/uploadDocsLegaisList.load")) {
            return true;
        }
        if (requestedPath.endsWith("/uploadDocsGerais.load")) {
            return true;
        }
        if (requestedPath.endsWith("/uploadDocsGeraisList.load")) {
            return true;
        }
        if (requestedPath.contains("cithelp")) {
            return true;
        }
        if (requestedPath.endsWith("/lookup.load")) {
            return true;
        }
        if (requestedPath.endsWith("/tableSearch.load")) {
            return true;
        }
        if (requestedPath.endsWith("/index.load")) {
            return true;
        }

        if (requestedPath.endsWith("/sair.load")) {
            return true;
        }
        if (requestedPath.startsWith("/fckeditor/")) {
            return true;
        }
        if (requestedPath.startsWith("/tempUpload/") || requestedPath.startsWith("tempUpload/")) {
            return true;
        }
        if (requestedPath.endsWith("/topReportControl.jsp")) {
            return true;
        }
        if (requestedPath.endsWith("/eventos.load")) {
            return true;
        }
        if (requestedPath.endsWith("/delegacaoTarefa.load")) {
            return true;
        }
        if (requestedPath.endsWith("/dinamicViews.load")) {
            return true;
        }
        if (requestedPath.endsWith("/solicitacaoServico.load")) {
            return true;
        }
        if (requestedPath.endsWith("/problema.load")) {
            return true;
        }
        if (requestedPath.endsWith("/internacionalizacao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/eventMonitor.load")) {
            return true;
        }
        if (requestedPath.endsWith("/requisicaoMudanca.load")) {
            return true;
        }
        if (requestedPath.endsWith("/requisicaoLiberacao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/planoMelhoriaTreeView.load")) {
            return true;
        }
        if (requestedPath.endsWith("/suspensaoSolicitacao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/modalBaseConhecimento.load")) {
            return true;
        }
        if (requestedPath.endsWith("/osSetSituacao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/mudarSLA.load")) {
            return true;
        }
        if (requestedPath.endsWith("/copiarSLA.load")) {
            return true;
        }
        if (requestedPath.endsWith("/agendarAtividade.load")) {
            return true;
        }
        if (requestedPath.endsWith("/opiniao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/listaServicosContrato.load")) {
            return true;
        }
        if (requestedPath.endsWith("/visualizarNotificacoes.load")) {
            return true;
        }
        if (requestedPath.endsWith("/informacaoItemConfiguracao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/contratosAnexos.load")) {
            return true;
        }
        if (requestedPath.endsWith("/informacoesContratoQuestionario.load")) {
            return true;
        }
        if (requestedPath.endsWith("/informacoesContrato.load")) {
            return true;
        }
        if (requestedPath.endsWith("/listaOSContrato.load")) {
            return true;
        }
        if (requestedPath.endsWith("/respostaPadraoFechar.load")) {
            return true;
        }
        if (requestedPath.endsWith("/respostaPadraoFechar.jsp")) {
            return true;
        }
        if (requestedPath.endsWith("/contratoQuestionarios.load")) {
            return true;
        }
        if (requestedPath.endsWith("/visualizarDesempenhoServicosContrato.load")) {
            return true;
        }
        if (requestedPath.endsWith("/programacaoAtividade.load")) {
            return true;
        }
        if (requestedPath.endsWith("/os.load")) {
            return true;
        }
        if (requestedPath.endsWith("/listaFaturasContrato.load")) {
            return true;
        }
        if (requestedPath.endsWith("/fatura.load")) {
            return true;
        }
        if (requestedPath.endsWith("/tableSearchVinc.load")) {
            return true;
        }
        if (requestedPath.endsWith("/pages/alterarSenha/menu.html")) {
            return true;
        }
        if (requestedPath.endsWith("/alterarSenha.load")) {
            return true;
        }
        if (requestedPath.endsWith("pesquisaSatisfacao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/portal.load")) {
            return true;
        }
        if (requestedPath.endsWith("/categoriaPost.load")) {
            return true;
        }
        if (requestedPath.endsWith("/post.load")) {
            return true;
        }
        if (requestedPath.endsWith("/pesquisa.load")) {
            return true;
        }
        if (requestedPath.endsWith("/visualizarUploadTemp.load")) {
            return true;
        }
        if (requestedPath.endsWith("/cargaParametroCorpore.load")) {
            return true;
        }
        if (requestedPath.endsWith("/cadastroFluxo.load")) {
            return true;
        }
        if (requestedPath.startsWith("/pesquisaSolicitacoesServicos.event") || requestedPath.startsWith("/printPDF/printPDF.jsp")) {
            return true;
        }
        if (requestedPath.startsWith("/pesquisaRequisicaoLiberacao.load") || requestedPath.startsWith("/printPDF/printPDF.jsp")) {
            return true;
        }
        if (requestedPath.startsWith("/pesquisaRequisicaoMudanca.load") || requestedPath.startsWith("/printPDF/printPDF.jsp")) {
            return true;
        }

        if (requestedPath.endsWith("/pesquisaSolicitacoesServicosPortal.load") || requestedPath.startsWith("/printPDF/printPDF.jsp")) {
            return true;
        }

        if (requestedPath.endsWith("/listagemConsultas.load")) {
            return true;
        }
        if (requestedPath.endsWith("/geraInfoPivotTable.load")) {
            return true;
        }
        if (requestedPath.endsWith("/geraTemplateReport.load")) {
            return true;
        }
        if (requestedPath.endsWith("/getFileConfig.load")) {
            return true;
        }
        if (requestedPath.endsWith("/baseConhecimentoView.load")) {
            return true;
        }
        if (requestedPath.endsWith("/agendarAtividadeProblema.load")) {
            return true;
        }
        if (requestedPath.endsWith("/suspensaoProblema.load")) {
            return true;
        }
        if (requestedPath.endsWith("/validacaoRequisicaoProblema.load")) {
            return true;
        }
        if (requestedPath.endsWith("/refreshuploadAnexosDocsGerais.load")) {
            return true;
        }
        if (requestedPath.endsWith("/refreshuploadPlanoDeReversao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/uploadExcluirPlanoDeReversao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/agendarReuniaoRequisicaoMudanca.load")) {
            return true;
        }
        if (requestedPath.endsWith("/delegacaoLiberacao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/agendarAtividadeRequisicaoLiberacao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/suspensaoLiberacao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/agendarAtividadeRequisicaoMudanca.load")) {
            return true;
        }
        if (requestedPath.endsWith("/suspensaoMudanca.load")) {
            return true;
        }
        if (requestedPath.endsWith("/refreshuploadAnexosDocsGeraisList.load")) {
            return true;
        }
        if (requestedPath.endsWith("refreshuploadAnexosdocsLegaisList.load")) {
            return true;
        }
        if (requestedPath.endsWith("/refreshuploadAnexosDocsGeraisList.load")) {
            return true;
        }
        if (requestedPath.endsWith("/refreshuploadAnexosdocsLegaisList.load")) {
            return true;
        }
        if (requestedPath.endsWith("/galeriaImagens.load")) {
            return true;
        }

        if (requestedPath.endsWith("/pages/visualizacaoQuestionario/visualizacaoQuestionario.load")) {
            return true;
        }

        if (requestedPath.endsWith("/pages/uploadArquivoMultimidia/uploadArquivoMultimidia.load")) {
            return true;
        }
        if (requestedPath.endsWith("/pages/uploadPlanoDeReversao/uploadPlanoDeReversao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/pages/refreshuploadPlanoDeReversao/refreshuploadPlanoDeReversao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/pages/uploadPlanoDeReversaoLiberacao/uploadPlanoDeReversaoLiberacao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/pages/uploadPlanoDeReversaoLiberacao/uploadPlanoDeReversaoLiberacao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/pages/uploadPlanoDeReversaoLiberacao/uploadPlanoDeReversaoLiberacao.jsp")) {
            return true;
        }
        if (requestedPath.endsWith("/pages/uploadExcluirPlanoDeReversaoLiberacao/uploadExcluirPlanoDeReversaoLiberacao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/pages/uploadExcluirPlanoDeReversaoLiberacao/uploadExcluirPlanoDeReversaoLiberacao.jsp")) {
            return true;
        }
        if (requestedPath.endsWith("/pages/inventarioNew/inventarioNew.load")) {
            return true;
        }
        if (requestedPath.endsWith("/upload/excluirAnexo.do")) {
            return true;
        }

        if (requestedPath.endsWith("/solicitacaoServicoMultiContratosPortal2.load")) {
            return true;
        }

        if (requestedPath.endsWith("/requisicaoQuestionario/requisicaoQuestionario.load")) {
            return true;
        }
        if (requestedPath.endsWith("/informacaoItemConfiguracao/informacaoItemConfiguracao.load")) {
            return true;
        }

        if (requestedPath.endsWith("/gerenciamentoServicos/gerenciamentoServicos.load")) {
            return true;
        }

        if (requestedPath.endsWith("/solicitacaoServicoMultiContratos/solicitacaoServicoMultiContratos.load")) {
            return true;
        }

        if (StringUtils.containsIgnoreCase(requestedPath, "autoComplete")) {
            return true;
        }

        if (requestedPath.endsWith("/relatorioQuantitativoPorServico/relatorioQuantitativoPorServico.load")) {
            return true;
        }
        if (requestedPath.endsWith("pages/relatorioQuantitativo/relatorioQuantitativo.load")) {
            return true;
        }
        if (requestedPath.endsWith("pages/relatorioServicoAprovar/relatorioServicoAprovar.load")) {
            return true;
        }
        if (requestedPath.endsWith("pages/relatorioCargaHoraria/relatorioCargaHoraria.load")) {
            return true;
        }
        if (requestedPath.endsWith("/pesquisaFaq/pesquisaFaq.load")) {
            return true;
        }

        if (requestedPath.endsWith("/aprovacaoSolicitacaoServico.load")) {
            return true;
        }
        if (requestedPath.endsWith("/atividadesServico/atividadesServico.load")) {
            return true;
        }
        if (requestedPath.endsWith("/solicitacaoServicoMultiContratos/solicitacaoServicoMultiContratos.load")) {
            return true;
        }
        if (requestedPath.endsWith("/patrimonio/patrimonio.load")) {
            return true;
        }
        if (requestedPath.endsWith("pages/valorServicoContrato/valorServicoContrato.load")) {
            return true;
        }
        if (requestedPath.endsWith("/notificacaoServicoContrato/notificacaoServicoContrato.load")) {
            return true;
        }
        if (requestedPath.endsWith("/solicitacaoServicoMultiContratosPortal.load")) {
            return true;
        }
        if (requestedPath.endsWith("/solicitacaoServicoPortal.load")) {
            return true;
        }
        if (requestedPath.endsWith("/refreshuploadAnexosList/refreshuploadAnexosList.load")) {
            return true;
        }
        if (requestedPath.endsWith("/categoriaOcorrencia/categoriaOcorrencia.load")) {
            return true;
        }
        if (requestedPath.endsWith("/origemOcorrencia/origemOcorrencia.load")) {
            return true;
        }
        if (requestedPath.endsWith("/notificacao/notificacao.load")) {
            return true;
        }
        if (requestedPath.endsWith("/cargaUsuarioAd/cargaUsuarioAd.load")) {
            return true;
        }
        if (requestedPath.endsWith("/cargaMensagens/cargaMensagens.load")) {
            return true;
        }
        if (requestedPath.endsWith("/itemConfiguracaoTree/itemConfiguracaoTree.load")) {
            return true;
        }
        if (requestedPath.endsWith("uploadAjax.load")) {
            return true;
        }
        if (requestedPath.endsWith("/uploadExcluir/uploadExcluir.load")) {
            return true;
        }
        if (requestedPath.endsWith("/uploadExcluirDocsLegais.load")) {
            return true;
        }
        if (requestedPath.endsWith("/uploadDocsLegaisExcluir/uploadDocsLegaisExcluir.load")) {
            return true;
        }
        if (requestedPath.endsWith("recuperaFromGed/recuperaFromGed.load")) {
            return true;
        }
        if (requestedPath.endsWith("/uploadExcluirDocsGerais.load")) {
            return true;
        }
        if (requestedPath.endsWith(".save") || requestedPath.endsWith(".find") || requestedPath.endsWith(".get") || requestedPath.endsWith(".restore")
                || requestedPath.endsWith(".event") || requestedPath.endsWith(".complete")) {
            return true;
        }
        if (requestedPath.endsWith("/portal/home.html")) {
            return true;
        }
        if (requestedPath.endsWith("/start/termos.html")) {
            return true;
        }
        if (requestedPath.endsWith("/redefinirSenha/redefinirSenha.jsp")) {
            return true;
        }
        if (requestedPath.endsWith("/pesquisaErroConhecido/pesquisaErroConhecido.load")) {
            return true;
        }
        if (requestedPath.endsWith("/ansServicoContratoRelacionado/ansServicoContratoRelacionado.load")) {
            return true;
        }
        if (requestedPath.endsWith("pages/pesquisaRequisicaoMudanca/pesquisaRequisicaoMudanca.load")) {
            return true;
        }
        if (requestedPath.endsWith("/servicoContrato/servicoContrato.load")) {
            return true;
        }
        if (requestedPath.endsWith("/checklistQuestionario/checklistQuestionario.load")) {
            return true;
        }
        if (requestedPath.endsWith("/situacaoLiberacaoMudanca/situacaoLiberacaoMudanca.load")) {
            return true;
        }
        if (requestedPath.contains("/exportXML/")) {
            return true;
        }
        if (requestedPath.endsWith("pages/refreshuploadPlanoDeReversaoLiberacao/refreshuploadPlanoDeReversaoLiberacao.load")) {
            return true;
        }
        if (requestedPath.endsWith("pages/servicoContratoMulti/servicoContratoMulti.load")) {
            return true;
        }

        if (requestedPath.endsWith("/chatSmart/chatSmart.load")) {
            return true;
        }
        if (requestedPath.endsWith("/chatSmartListaContatos/chatSmartListaContatos.load")) {
            return true;
        }
        if (requestedPath.endsWith("/refreshuploadRequisicaoMudanca/refreshuploadRequisicaoMudanca.load")) {
            return true;
        }

        if (requestedPath.endsWith("/refreshuploadRequisicaoMudanca/refreshuploadRequisicaoMudanca.get")) {
            return true;
        }

        if (requestedPath.endsWith("/uploadRequisicaoMudanca/uploadRequisicaoMudanca.load")) {
            return true;
        }

        if (requestedPath.endsWith("/uploadExcluirRequisicaoMudanca/uploadExcluirRequisicaoMudanca.load")) {
            return true;
        }

        if (requestedPath.endsWith("/motivoSuspensaoAtividade/motivoSuspensaoAtividade.load")) {
            return true;
        }

        if (requestedPath.endsWith("/refreshuploadRequisicaoProblema/refreshuploadRequisicaoProblema.load")) {
            return true;
        }

        if (requestedPath.endsWith("/refreshuploadRequisicaoProblema/refreshuploadRequisicaoProblema.get")) {
            return true;
        }

        if (requestedPath.endsWith("/uploadRequisicaoProblema/uploadRequisicaoProblema.load")) {
            return true;
        }

        if (requestedPath.endsWith("/uploadExcluirRequisicaoProblema/uploadExcluirRequisicaoProblema.load")) {
            return true;
        }

        if (requestedPath.endsWith("/informacoesItemConfiguracaoMudanca/informacoesItemConfiguracaoMudanca.load")) {
            return true;
        }

        if (requestedPath.endsWith("/origemAtendimento/origemAtendimento.load")) {
            return true;
        }

        if (requestedPath.endsWith("/cadastroConexaoBI/cadastroConexaoBI.load")) {
            return true;
        }

        if (requestedPath.endsWith("/agendamentoExecucaoBI/agendamentoExecucaoBI.load")) {
            return true;
        }

        if (requestedPath.endsWith("/importManualBI/importManualBI.load")) {
            return true;
        }

        if (requestedPath.endsWith("/logImportacaoBI/logImportacaoBI.load")) {
            return true;
        }

        if (requestedPath.endsWith("/importarDados/importarDados.load")) {
            return true;
        }

        if (requestedPath.endsWith("/externalConnection/externalConnection.load")) {
            return true;
        }

        if (requestedPath.endsWith("getExportBI.load")) {
            return true;
        }

        if (requestedPath.endsWith("getLogImportacaoBI.load")) {
            return true;
        }

        if (requestedPath.endsWith("suspensaoReativacaoSolicitacao/suspensaoReativacaoSolicitacao.load")) {
            return true;
        }

        if (requestedPath.endsWith("/ocorrenciaSolicitacao/ocorrenciaSolicitacao.load")) {
            return true;
        }

        if (requestedPath.startsWith("/dwr") || requestedPath.endsWith(".dwr")) {
            return true;
        }

        if (requestedPath.endsWith("/relatorioIncidentesNaoResolvidos/relatorioIncidentesNaoResolvidos.load")) {
            return true;
        }

        if (requestedPath.endsWith("/relatorioEficaciaNasEstimativasDasRequisicaoDeServico/relatorioEficaciaNasEstimativasDasRequisicaoDeServico.load")) {
            return true;
        }

        if (requestedPath.endsWith("/relatorioKpiProdutividade/relatorioKpiProdutividade.load")) {
            return true;
        }

        if (requestedPath.endsWith("/relatorioEficaciaTeste/relatorioEficaciaTeste.load")) {
            return true;
        }

        if (requestedPath.endsWith("relatorioDocumentacaoDeFuncionalidadesNovasOuAlteradasNoPeriodo.load")) {
            return true;
        }

        if (requestedPath.endsWith("listagemConsultas.load")) {
            return true;
        }
        if (requestedPath.endsWith("geraInfoPivotTable.load")) {
            return true;
        }
        if (requestedPath.endsWith("geraTemplateReport.load")) {
            return true;
        }
        if (requestedPath.endsWith("geraDataTable.load")) {
            return true;
        }
        if (requestedPath.endsWith("geraGraficoPizzaJS.load")) {
            return true;
        }
        if (requestedPath.endsWith("geraGraficoBarraJS.load")) {
            return true;
        }
        if (requestedPath.endsWith("geraJSP.load")) {
            return true;
        }
        if (requestedPath.endsWith("geraURL.load")) {
            return true;
        }
        if (requestedPath.endsWith("geraXML.load")) {
            return true;
        }
        if (requestedPath.endsWith("geraScript.load")) {
            return true;
        }
        if (requestedPath.endsWith("geraRetornoClasse.load")) {
            return true;
        }
        if (requestedPath.endsWith("listagemConsultasObjects.load")) {
            return true;
        }
        if (requestedPath.endsWith("listagemDashBoardsObjects.load")) {
            return true;
        }
        if (requestedPath.endsWith("topReportControl.jsp")) {
            return true;
        }
        if (requestedPath.endsWith("topDashboardBuilder.jsp")) {
            return true;
        }
        if (requestedPath.endsWith("topDashboarView.jsp")) {
            return true;
        }
        if (requestedPath.endsWith("dashBoardBuilderInternal.load")) {
            return true;
        }
        if (requestedPath.endsWith("dashBoardViewInternal.load")) {
            return true;
        }
        if (requestedPath.endsWith("listagemDashBoardsObjects.load")) {
            return true;
        }
        if (requestedPath.endsWith("listagemDashBoards.load")) {
            return true;
        }
        if (requestedPath.contains("/jspEmbedded/")) {
            return true;
        }

        if (requestedPath.endsWith("/loginCandidato/loginCandidato.load")) {
            return true;
        }

        if (requestedPath.endsWith("/recuperaSenhaCandidato/recuperaSenhaCandidato.load")) {
            return true;
        }

        if (requestedPath.endsWith("/trabalheConosco/trabalheConosco.load") || requestedPath.startsWith("/trabalheConosco/trabalheConosco.load")) {
            return true;
        }

        if (requestedPath.endsWith("/uploadFile/uploadFile.load")) {
            return true;
        }

        return false;
    }

    private void sendError(final int errorCode, final String errorMessage, final HttpServletResponse response) {
        try {
            response.sendError(errorCode, errorMessage);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

}
