package br.com.centralit.citcorpore.ajaxForms;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.centralit.citajax.html.AjaxFormAction;
import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citajax.html.HTMLElement;
import br.com.centralit.citajax.html.HTMLForm;
import br.com.centralit.citcorpore.bean.BICitsmartResultRotinaDTO;
import br.com.centralit.citcorpore.bean.ConexaoBIDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.bi.operation.BICitsmartOperation;
import br.com.centralit.citcorpore.negocio.ConexaoBIService;
import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.centralit.citcorpore.util.Util;
import br.com.centralit.citcorpore.util.WebUtil;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilI18N;

public class ConexaoBI extends AjaxFormAction {

    ConexaoBIDTO conexaoBIDTO = new ConexaoBIDTO();

    @Override
    public void load(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        final UsuarioDTO usuario = WebUtil.getUsuario(request);
        if (usuario == null) {
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }

        // O Load dessa página é renderizado no método iniciar da classe ConexaoBI_Impl.
    }

    public void save(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

    }

    public void restore(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) {

    }

    @Override
    public Class<ConexaoBIDTO> getBeanClass() {
        return ConexaoBIDTO.class;
    }

    public void atualizarLista(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        final UsuarioDTO usuario = WebUtil.getUsuario(request);

        if (usuario == null) {
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }

        setConexaoBIDTO((ConexaoBIDTO) document.getBean());
        final String paginaselecionada = document.getElementById("paginaselecionada").getValue();
        if (paginaselecionada != null) {
            getConexaoBIDTO().setPaginaSelecionada(Integer.parseInt(paginaselecionada));
        }
        recarregarLista(document, request, response);
        /**
         * A linha foi adicionada porque estava fechando o modal e o load antes de recarregar a grid de conexõesbi. assim ele so vai fechar o modal depois que
         * carregar a grid.
         *
         * @author maycon.fernandes
         * @since 25/10/2013 14:35
         */
        document.executeScript("fecharModal()");
    }

    public ConexaoBIDTO getConexaoBIDTO() {
        return conexaoBIDTO;
    }

    public void setConexaoBIDTO(final ConexaoBIDTO conexaoBIDTO) {
        this.conexaoBIDTO = conexaoBIDTO;
    }

    public void recarregarLista(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(request);

        if (usuario == null) {
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }
        final ConexaoBIDTO conexaoBIDTO = (ConexaoBIDTO) document.getBean();

        final StringBuilder sb = new StringBuilder();

        final Integer itensPorPagina = conexaoBIDTO.getItensPorPagina() == null ? Integer.parseInt(ParametroUtil.getValorParametroCitSmartHashMap(
                ParametroSistema.QUANT_RETORNO_PESQUISA, "5")) : conexaoBIDTO.getItensPorPagina();

        Integer paginaSelecionada = conexaoBIDTO.getPaginaSelecionada();

        if (paginaSelecionada == null) {
            paginaSelecionada = 1;
        }

        final Integer tipoLista = conexaoBIDTO.getTipoLista() == null ? 1 : conexaoBIDTO.getTipoLista();

        if (request.getParameter("statusFiltro").equals(" ") || request.getParameter("statusFiltro").equals("T")) {
            renderizarLista(sb, request, itensPorPagina, paginaSelecionada, false, tipoLista);
        } else {
            renderizarListaFiltro(sb, request, itensPorPagina, paginaSelecionada, false, tipoLista);
        }

        final Integer totalPaginasFinal = totalPaginas(request, itensPorPagina, conexaoBIDTO);

        /**
         * Alterado: Mário
         * Motivo: Invertendo as posições para quantificar o total de páginas e definir no objeto de conexaoBI
         * Autor: flavio.santana
         * Data/Hora: 13/11/2013
         */
        paginaSelecionada = totalPaginasFinal == 1 ? 1 : paginaSelecionada;

        final HTMLElement divPrincipal = document.getElementById("esquerda");

        divPrincipal.setInnerHTML(sb.toString());

        carregarItensPaginacao(document, request, totalPaginasFinal);

        final HTMLForm form = document.getForm("formGerenciamento");

        form.setValues(conexaoBIDTO);

        document.executeScript("fechaJanelaAguarde()");
    }

    /**
     * Carrega e atualiza os itens da paginação numerada e informa a quantidade de resultados Ex.: Primeiro « 1 2 3 4 5 » Último 1 De 7 Resultados
     *
     * @param document
     * @param request
     * @param totalPaginasFinal
     */
    public void carregarItensPaginacao(final DocumentHTML document, final HttpServletRequest request, final Integer totalPaginasFinal) throws Exception {
        final StringBuilder sb = new StringBuilder();
        final ConexaoBIDTO conexaoBIDTO = (ConexaoBIDTO) document.getBean();
        Integer paginaSelecionada = conexaoBIDTO.getPaginaSelecionada();
        if (paginaSelecionada == null) {
            paginaSelecionada = 1;
        }
        paginacaoGerenciamento(totalPaginasFinal, sb, paginaSelecionada, request);
        document.executeScript("carregarValorClasse(\"" + sb.toString() + "\", \"paginacaoGerenciamento\")");

        final StringBuilder valores = new StringBuilder();
        carregarValoresPaginacao(totalPaginasFinal, valores, paginaSelecionada, request);
        document.executeScript("carregarValorClasse(\"" + valores.toString() + "\",\"paginacaoGerenciamentoQuantidade\")");
    }

    public void carregarValoresPaginacao(final Integer totalPaginas, final StringBuilder sb, Integer paginaSelecionada, final HttpServletRequest request) {
        if (totalPaginas == 0) {
            paginaSelecionada = 0;
        }
        sb.append(paginaSelecionada + " " + UtilI18N.internacionaliza(request, "citcorpore.comum.de") + " " + totalPaginas + " "
                + UtilI18N.internacionaliza(request, "citcorpore.comum.paginas"));
    }

    /**
     * Realiza a regra de paginação das conexoes Explicação: Se o número de páginas for maior do que cinco, já é possível criar os intervalos. Se a página atual
     * for menor do que cinco (o adjacente
     * esta configurado com 2) é feito um laço. No for, enquanto a variável 'i' for menor do que seis os números são mostrados fazendo uma verificação para
     * saber qual é a página atual que exige uma
     * estilização diferente. Mas se a página atual for maior do que quatro e menor do que a última menos três, é uma página intermediária. Primeiro são
     * anexadas a primeira e última páginas. Depois é
     * feito um laço para definir as adjacentes. A variável 'adjacentes' recebeu neste código o valor dois. Para entender melhor este laço vamos supor que
     * estamos na página seis. A variável 'i' vai
     * receber quatro (atual - adjacentes), enquanto ela for menor do que oito (atual + adjacentes) os números links gerados com uma verificação para saber qual
     * é a página atual. Por fim são anexadas
     * a última e penúltima páginas. O último else é para quando a página atual esta perto do final da numeração. São anexadas a primeira e última páginas além
     * dos três pontos. A variável 'i' recebe o
     * resultado da última página menos oito (4+2*2) enquanto não for menor ou igual a este número, os links são gerados.
     *
     * @param totalPaginas
     * @param sb
     * @param paginaSelecionada
     * @param request
     */
    public void paginacaoGerenciamento(final Integer totalPaginas, final StringBuilder sb, Integer paginaSelecionada, final HttpServletRequest request)
            throws Exception {

        final Integer adjacentes = 2;
        if (paginaSelecionada == null) {
            paginaSelecionada = 1;
        }
        sb.append("	<div id='itenPaginacaoGerenciamento' class='pagination pagination-right margin-none'>");
        sb.append("		<ul>");
        sb.append("			<li " + (totalPaginas == 0 || paginaSelecionada == 1 ? "class='disabled'" : "value='1' onclick='paginarItens(this.value);'") + " ><a>"
                + UtilI18N.internacionaliza(request, "citcorpore.comum.primeiro") + "</a></li>");
        sb.append("			<li "
                + (totalPaginas == 0 || totalPaginas == 1 || paginaSelecionada == 1 ? "class='disabled'" : "value='" + (paginaSelecionada - 1)
                        + "' onclick='paginarItens(this.value);'") + "><a>&laquo;</a></li>");
        if (totalPaginas <= 5) {
            for (int i = 1; i <= totalPaginas; i++) {
                if (i == paginaSelecionada) {
                    sb.append("<li id='" + i + "' value='" + i + "' onclick='paginarItens(this.value);' class='active'><a >" + i + "</a></li> ");
                } else {
                    sb.append("<li id='" + i + "' value='" + i + "' onclick='paginarItens(this.value);'><a >" + i + "</a></li> ");
                }
            }
        } else {
            if (totalPaginas > 5) {
                if (paginaSelecionada < 1 + 2 * adjacentes) {
                    for (int i = 1; i < 2 + 2 * adjacentes; i++) {
                        if (i == paginaSelecionada) {
                            sb.append("<li id='" + i + "' value='" + i + "' onclick='paginarItens(this.value);' class='active'><a >" + i + "</a></li> ");
                        } else {
                            sb.append("<li id='" + i + "' value='" + i + "' onclick='paginarItens(this.value);'><a >" + i + "</a></li> ");
                        }
                    }
                } else if (paginaSelecionada > 2 * adjacentes && paginaSelecionada < totalPaginas - 3) {
                    for (int i = paginaSelecionada - adjacentes; i <= paginaSelecionada + adjacentes; i++) {
                        if (i == paginaSelecionada) {
                            sb.append("<li id='" + i + "' value='" + i + "' onclick='paginarItens(this.value);' class='active'><a >" + i + "</a></li> ");
                        } else {
                            sb.append("<li id='" + i + "' value='" + i + "' onclick='paginarItens(this.value);'><a >" + i + "</a></li> ");
                        }
                    }
                } else {
                    for (int i = totalPaginas - 2 * adjacentes; i <= totalPaginas; i++) {
                        if (i == paginaSelecionada) {
                            sb.append("<li id='" + i + "' value='" + i + "' onclick='paginarItens(this.value);' class='active'><a >" + i + "</a></li> ");
                        } else {
                            sb.append("<li id='" + i + "' value='" + i + "' onclick='paginarItens(this.value);'><a >" + i + "</a></li> ");
                        }
                    }
                }
            }
        }
        sb.append("			<li "
                + (totalPaginas == 0 || totalPaginas == 1 || paginaSelecionada.equals(totalPaginas) ? "class='disabled'" : "value='" + (paginaSelecionada + 1)
                        + "' onclick='paginarItens(this.value);'") + " ><a >&raquo;</a></li>");
        sb.append("			<li "
                + (totalPaginas == 0 || paginaSelecionada.equals(totalPaginas) ? "class='disabled'" : "value='" + totalPaginas
                        + "' onclick='paginarItens(this.value);'") + " ><a >" + UtilI18N.internacionaliza(request, "citcorpore.comum.ultimo") + "</a></li> ");
        sb.append("		</ul>");
        sb.append("	</div>");
    }

    /**
     * Retorna o total de páginas de acordo com o parametro da quantidade de itens a serem listados
     *
     * @param request
     * @param itensPorPagina
     */
    public Integer totalPaginas(final HttpServletRequest request, final Integer itensPorPagina, final ConexaoBIDTO conexaoBIDTO) throws Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(request);

        Integer totalPaginas = 0;

        if (usuario != null) {

            final ConexaoBIService conexaoBIService = (ConexaoBIService) ServiceLocator.getInstance().getService(ConexaoBIService.class, null);

            if (getConexaoBIDTO().getTotalPaginas() != null) {
                /**
                 * Motivo: Alteração para correção dos itens da paginação com responsavel Autor: flavio.santana Data/Hora: 13/11/2013
                 */
                if (getConexaoBIDTO().getTotalPaginas() > 0 && getConexaoBIDTO().getTotalPaginas() < itensPorPagina) {
                    totalPaginas = 1;
                } else {
                    totalPaginas = (int) Math.ceil(new Double(getConexaoBIDTO().getTotalPaginas() / new Double(itensPorPagina)));
                }

            } else {
                totalPaginas = conexaoBIService.obterTotalDePaginas(itensPorPagina, usuario.getLogin(), conexaoBIDTO);
            }
        }
        return totalPaginas;
    }

    /**
     * Responsável por renderizar a listagem de Conexões na tela Painel de Controle.
     *
     * @param sb
     * @param request
     * @param itensPorPagina
     * @param paginaSelecionada
     * @param flag
     * @param tipoLista
     * @throws Exception
     */
    public void renderizarLista(final StringBuilder sb, final HttpServletRequest request, final Integer itensPorPagina, final Integer paginaSelecionada,
            final boolean flag, final Integer tipoLista) throws Exception {
        /**
         * buscando a collection das conexões
         */
        final Collection<ConexaoBIDTO> ColConexao = new HashSet<ConexaoBIDTO>();

        final Collection<ConexaoBIDTO> listConexao = listarConexoesPaginadas(ColConexao, paginaSelecionada, itensPorPagina);
        if (listConexao != null) {
            final int i = 0;
            if (flag) {
                sb.append("<div  id='esquerda' class='innerTB'>");
            }
            sb.append("<!-- Inicio do loop de conexões -->");

            for (final ConexaoBIDTO conexaoBI : listConexao) {
                final String HTMLStatus = setarStatusUltimaIntegracao(conexaoBI, request);

                sb.append("<div class='box-generic content-area " + (i % 2 == 0 ? "alternado" : "") + " listaDetalhada " + (tipoLista.equals(1) ? "ativo" : "")
                        + "'> ");
                sb.append(HTMLStatus);
                sb.append("   <div class='row-fluid'>");
                sb.append("		<div class='span4'>");
                sb.append("			<label class='content-row labelOverflowTresPontinhos'>");
                sb.append("		      <div>" + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.idClienteBI") + "</div>");
                sb.append("		      <span class='verde-negrito' title=''>[" + conexaoBI.getIdConexaoBI() + "]</span>");
                sb.append("		    </label>");
                sb.append("		</div>");
                sb.append("		<div class='span4'>");
                sb.append("			<label class='content-row labelOverflowTresPontinhos'>");
                sb.append("		      <div>" + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.nomeCliente") + "</div>");
                sb.append("		      <span class='servico escuro-negrito' title=''>" + conexaoBI.getNome() + "</span>");
                sb.append("		    </label>");
                sb.append("		</div>");
                sb.append("		<div class='span4'>");
                sb.append("			<label class='content-row labelOverflowTresPontinhos'>");
                sb.append("		      <div>" + UtilI18N.internacionaliza(request, "bi.painelControle.link") + "</div>");
                sb.append("		      <span class='escuro-negrito' >" + conexaoBI.getLink() + "</span>");
                sb.append("		    </label>");
                sb.append("		</div>");
                sb.append("	</div>");
                sb.append(" <div class='row-fluid'>");
                sb.append("		<div class='span4'>");
                sb.append("		    <label class='content-row labelOverflowTresPontinhos'>");
                sb.append("		      <div>" + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.dataHoraUltimaIntegracao") + "</div>");
                sb.append("		      <span class='escuro-negrito'>" + conexaoBI.getDataHoraUltimaImportacaoString() + "</span>");
                sb.append("		    </label>");
                sb.append("		</div>");
                sb.append(" 	<div class='tab-pane span4'>");
                sb.append("		 	<label class='content-row labelOverflowTresPontinhos'>");
                sb.append("		      <div>" + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.tipoIntegracao") + "</div>");
                sb.append("		  	</label>");
                sb.append("			<div class='content-row'>");
                if (conexaoBI.getTipoImportacao() == null || conexaoBI.getTipoImportacao().equalsIgnoreCase("A")) {
                    sb.append("		      <span class='escuro-negrito'> " + UtilI18N.internacionaliza(request, "citcorpore.comum.automatica") + "</span>");
                } else if (conexaoBI.getTipoImportacao().equalsIgnoreCase("M")) {
                    sb.append("		      <span class='escuro-negrito'>" + UtilI18N.internacionaliza(request, "citcorpore.comum.manual") + "</span>");
                }
                sb.append("			</div>");
                sb.append("		</div>");

                if (conexaoBI != null && conexaoBI.getStatus() != null && !conexaoBI.getStatus().equalsIgnoreCase("I")) {
                    final ConexaoBIService conexaoBIService = (ConexaoBIService) ServiceLocator.getInstance().getService(ConexaoBIService.class, null);
                    final Date dtProxExecucao = conexaoBIService.getProxDtExecucao(conexaoBI);

                    if (dtProxExecucao != null) {
                        sb.append("		<div class='span4'>");
                        sb.append("		    <label class='content-row labelOverflowTresPontinhos'>");
                        sb.append("		      <div>" + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.dataHoraProximaExecucao") + "</div>");

                        final String dtProxExecucaoFormatada = Util.formatDateDDMMYYYYHHMMSS(dtProxExecucao);

                        sb.append("		      <span class='escuro-negrito'>" + dtProxExecucaoFormatada + "</span>");
                        sb.append("		    </label>");
                        sb.append("		</div>");
                    }
                }

                sb.append("	</div>");
                sb.append("   <div class='row-fluid'>");
                sb.append("	  	<div class='content-row right'>");
                sb.append("			  	<button type='button' class='btn btn-default maisInfo' onclick='AbrirModalEditarConexaoBI(" + conexaoBI.getIdConexaoBI()
                        + ")'>" + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.editarClienteBI") + "</button>");
                if (conexaoBI.getTipoImportacao() == null || conexaoBI.getTipoImportacao().equalsIgnoreCase("A")
                        && !conexaoBI.getStatus().equalsIgnoreCase("I")) {
                    sb.append("				<button type='button' class='btn btn-default maisInfo' id=''  onclick='executarAgora(" + conexaoBI.getIdConexaoBI() + ")' >"
                            + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.executarAgora") + "</button>");
                }
                sb.append("			    <div id='acoes' class='btn-group btn-block w15 aLeft dropup'>");
                sb.append("					<div class='leadcontainer' data-toggle='dropdown'>");
                sb.append("						<button type='button' class='btn dropdown-lead btn-default'>"
                        + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.maisOpcoes") + "</button>");
                sb.append("					</div>");
                sb.append("						<a class='btn btn-default dropdown-toggle' data-toggle='dropdown' ><span class='caret'></span> </a>");
                sb.append("						<ul class='dropdown-menu pull-right'>");
                if (!conexaoBI.getStatus().equalsIgnoreCase("I")) {
                    sb.append("							<li><a class='importacaoManual' href='#' onclick='return importacaoManual(" + conexaoBI.getIdConexaoBI() + ");'>"
                            + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.importacaoManual") + "</a></li>");
                }
                if (!conexaoBI.getStatus().equalsIgnoreCase("I") && conexaoBI.getLink() != null && !conexaoBI.getLink().equals("")) {
                    sb.append("							<li><a class='testarConexao' href='#' onclick='return testarConexao(" + conexaoBI.getIdConexaoBI() + ");'>"
                            + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.testarConexao") + "</a></li>");
                }
                sb.append("							<li><a class='logExecucao' href='#' onclick='return logExecucao(" + conexaoBI.getIdConexaoBI() + ");'>"
                        + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.logExecucao") + "</a></li>");
                if (conexaoBI.getStatus() != null && !conexaoBI.getStatus().equalsIgnoreCase("I") && conexaoBI.getTipoImportacao() != null
                        && conexaoBI.getTipoImportacao().equalsIgnoreCase("A")) {
                    sb.append("							<li><a class='agenda' href='#' onclick='return agenda(" + conexaoBI.getIdConexaoBI() + ", \"false\");'>"
                            + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.agendamentoEspecifico") + "</a></li>");
                    if (conexaoBI.getStatus().equalsIgnoreCase("F")) {
                        sb.append("							<li><a class='agenda' href='#' onclick='return agenda(" + conexaoBI.getIdConexaoBI() + ", \"true\");'>"
                                + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.agendamentoExcecao") + "</a></li>");
                    }
                }
                if (conexaoBI.getStatus() == null || conexaoBI.getStatus().equalsIgnoreCase(" ") || !conexaoBI.getStatus().equalsIgnoreCase("I")) {
                    sb.append("						<li><a class='desativarCliente' href='#' onclick='return desativarCliente(" + conexaoBI.getIdConexaoBI() + ", \"I\");'>"
                            + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.desativar") + "</a></li>");
                } else {
                    sb.append("						<li><a class='desativarCliente' href='#' onclick='return desativarCliente(" + conexaoBI.getIdConexaoBI() + ", \" \");'>"
                            + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.ativar") + "</a></li>");
                }
                sb.append("						</ul>");
                sb.append("				</div>");
                sb.append("		</div>");
                sb.append("	  </div>");
                sb.append("	</div>");
            }
            sb.append("<!-- Fim do loop de conexões abertas -->");
            if (flag) {
                sb.append("</div>");
            }
        } else {
            sb.append("<div class='content-area'>");
            sb.append("	<div class='row-fluid'>");
            sb.append("	<div class='innerTB'>");
            sb.append("		<h4>" + UtilI18N.internacionaliza(request, "citcorpore.comum.resultado") + "</h4>");
            sb.append("	</div>");
            sb.append("</div>");
        }
    }

    /**
     * Responsável por renderizar a listagem de Conexões na tela Painel de Controle com Filtro.
     *
     * @param sb
     * @param request
     * @param itensPorPagina
     * @param paginaSelecionada
     * @param flag
     * @param tipoLista
     * @throws Exception
     */
    public void renderizarListaFiltro(final StringBuilder sb, final HttpServletRequest request, final Integer itensPorPagina, final Integer paginaSelecionada,
            final boolean flag, final Integer tipoLista) throws Exception {

        /**
         * buscando a collection das conexões
         */
        final ConexaoBIDTO conexaoBIDTO = getConexaoBIDTO();
        final Collection<ConexaoBIDTO> listConexao = listarConexoesPaginadasFiltradas(conexaoBIDTO, paginaSelecionada, itensPorPagina);
        if (listConexao != null) {
            final int i = 0;
            if (flag) {
                sb.append("<div  id='esquerda' class='innerTB'>");
            }
            sb.append("<!-- Inicio do loop de conexões -->");

            for (final ConexaoBIDTO conexaoBI : listConexao) {
                final String HTMLStatus = setarStatusUltimaIntegracao(conexaoBI, request);

                sb.append("<div class='box-generic content-area " + (i % 2 == 0 ? "alternado" : "") + " listaDetalhada " + (tipoLista.equals(1) ? "ativo" : "")
                        + "'> ");
                sb.append(HTMLStatus);
                sb.append("   <div class='row-fluid'>");
                sb.append("		<div class='span4'>");
                sb.append("			<label class='content-row labelOverflowTresPontinhos'>");
                sb.append("		      <div>" + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.idClienteBI") + "</div>");
                sb.append("		      <span class='verde-negrito' title=''>[" + conexaoBI.getIdConexaoBI() + "]</span>");
                sb.append("		    </label>");
                sb.append("		</div>");
                sb.append("		<div class='span4'>");
                sb.append("			<label class='content-row labelOverflowTresPontinhos'>");
                sb.append("		      <div>" + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.nomeCliente") + "</div>");
                sb.append("		      <span class='servico escuro-negrito' title=''>" + conexaoBI.getNome() + "</span>");
                sb.append("		    </label>");
                sb.append("		</div>");
                sb.append("		<div class='span4'>");
                sb.append("			<label class='content-row labelOverflowTresPontinhos'>");
                sb.append("		      <div>" + UtilI18N.internacionaliza(request, "bi.painelControle.link") + "</div>");
                sb.append("		      <span class='escuro-negrito' >" + conexaoBI.getLink() + "</span>");
                sb.append("		    </label>");
                sb.append("		</div>");
                sb.append("	</div>");
                sb.append(" <div class='row-fluid'>");
                sb.append("		<div class='span4'>");
                sb.append("		    <label class='content-row labelOverflowTresPontinhos'>");
                sb.append("		      <div>" + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.dataHoraUltimaIntegracao") + "</div>");
                sb.append("		      <span class='escuro-negrito'>" + conexaoBI.getDataHoraUltimaImportacaoString() + "</span>");
                sb.append("		    </label>");
                sb.append("		</div>");
                sb.append(" 	<div class='tab-pane span4'>");
                sb.append("		 	<label class='content-row labelOverflowTresPontinhos'>");
                sb.append("		      <div>" + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.tipoIntegracao") + "</div>");
                sb.append("		  	</label>");
                sb.append("			<div class='content-row'>");
                if (conexaoBI.getTipoImportacao() == null || conexaoBI.getTipoImportacao().equalsIgnoreCase("A")) {
                    sb.append("		      <span class='escuro-negrito'> " + UtilI18N.internacionaliza(request, "citcorpore.comum.automatica") + "</span>");
                } else if (conexaoBI.getTipoImportacao().equalsIgnoreCase("M")) {
                    sb.append("		      <span class='escuro-negrito'>" + UtilI18N.internacionaliza(request, "citcorpore.comum.manual") + "</span>");
                }
                sb.append("			</div>");
                sb.append("		</div>");

                if (conexaoBI != null && conexaoBI.getStatus() != null && !conexaoBI.getStatus().equalsIgnoreCase("I")) {
                    final ConexaoBIService conexaoBIService = (ConexaoBIService) ServiceLocator.getInstance().getService(ConexaoBIService.class, null);
                    final Date dtProxExecucao = conexaoBIService.getProxDtExecucao(conexaoBI);

                    if (dtProxExecucao != null) {
                        sb.append("		<div class='span4'>");
                        sb.append("		    <label class='content-row labelOverflowTresPontinhos'>");
                        sb.append("		      <div>" + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.dataHoraProximaExecucao") + "</div>");

                        final String dtProxExecucaoFormatada = Util.formatDateDDMMYYYYHHMMSS(dtProxExecucao);

                        sb.append("		      <span class='escuro-negrito'>" + dtProxExecucaoFormatada + "</span>");
                        sb.append("		    </label>");
                        sb.append("		</div>");
                    }
                }

                sb.append("	</div>");
                sb.append("   <div class='row-fluid'>");
                sb.append("	  	<div class='content-row right'>");
                sb.append("			  	<button type='button' class='btn btn-default maisInfo' onclick='AbrirModalEditarConexaoBI(" + conexaoBI.getIdConexaoBI()
                        + ")'>" + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.editarClienteBI") + "</button>");
                if (conexaoBI.getTipoImportacao() == null || conexaoBI.getTipoImportacao().equalsIgnoreCase("A")
                        && !conexaoBI.getStatus().equalsIgnoreCase("I")) {
                    sb.append("				<button type='button' class='btn btn-default maisInfo' id=''  onclick='executarAgora(" + conexaoBI.getIdConexaoBI()
                            + ")' >" + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.executarAgora") + "</button>");
                }
                sb.append("			    <div id='acoes' class='btn-group btn-block w15 aLeft dropup'>");
                sb.append("					<div class='leadcontainer' data-toggle='dropdown'>");
                sb.append("						<button type='button' class='btn dropdown-lead btn-default'>"
                        + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.maisOpcoes") + "</button>");
                sb.append("					</div>");
                sb.append("						<a class='btn btn-default dropdown-toggle' data-toggle='dropdown' ><span class='caret'></span> </a>");
                sb.append("						<ul class='dropdown-menu pull-right'>");
                if (!conexaoBI.getStatus().equalsIgnoreCase("I")) {
                    sb.append("							<li><a class='importacaoManual' href='#' onclick='return importacaoManual(" + conexaoBI.getIdConexaoBI() + ");'>"
                            + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.importacaoManual") + "</a></li>");
                }
                if (!conexaoBI.getStatus().equalsIgnoreCase("I") && conexaoBI.getLink() != null && !conexaoBI.getLink().equals("")) {
                    sb.append("							<li><a class='testarConexao' href='#' onclick='return testarConexao(" + conexaoBI.getIdConexaoBI() + ");'>"
                            + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.testarConexao") + "</a></li>");
                }
                sb.append("							<li><a class='logExecucao' href='#' onclick='return logExecucao(" + conexaoBI.getIdConexaoBI() + ");'>"
                        + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.logExecucao") + "</a></li>");
                if (conexaoBI.getStatus() != null && !conexaoBI.getStatus().equalsIgnoreCase("I") && conexaoBI.getTipoImportacao() != null
                        && conexaoBI.getTipoImportacao().equalsIgnoreCase("A")) {
                    sb.append("							<li><a class='agenda' href='#' onclick='return agenda(" + conexaoBI.getIdConexaoBI() + ", \"false\");'>"
                            + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.agendamentoEspecifico") + "</a></li>");
                    if (conexaoBI.getStatus().equalsIgnoreCase("F")) {
                        sb.append("							<li><a class='agenda' href='#' onclick='return agenda(" + conexaoBI.getIdConexaoBI() + ", \"true\");'>"
                                + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.agendamentoExcecao") + "</a></li>");
                    }
                }
                if (conexaoBI.getStatus() == null || conexaoBI.getStatus().equalsIgnoreCase(" ") || !conexaoBI.getStatus().equalsIgnoreCase("I")) {
                    sb.append("						<li><a class='desativarCliente' href='#' onclick='return desativarCliente(" + conexaoBI.getIdConexaoBI()
                            + ", \"I\");'>" + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.desativar") + "</a></li>");
                } else {
                    sb.append("						<li><a class='desativarCliente' href='#' onclick='return desativarCliente(" + conexaoBI.getIdConexaoBI()
                            + ", \" \");'>" + UtilI18N.internacionaliza(request, "bi.painelControle.conexao.ativar") + "</a></li>");
                }
                sb.append("						</ul>");
                sb.append("				</div>");
                sb.append("		</div>");
                sb.append("	  </div>");
                sb.append("	</div>");
            }

            sb.append("<!-- Fim do loop de conexões abertas -->");
            if (flag) {
                sb.append("</div>");
            }
        } else {
            sb.append("<div class='content-area'>");
            sb.append("	<div class='row-fluid'>");
            sb.append("	<div class='innerTB'>");
            sb.append("		<h4>" + UtilI18N.internacionaliza(request, "citcorpore.comum.resultado") + "</h4>");
            sb.append("	</div>");
            sb.append("</div>");
        }
    }

    /***
     * Realiza o filtro de pesquisa da página de gerenciamento - Seta os itens do filtro no ConexaoBIDTO - Recarrega a lista de conexões de acordo com os
     * filtros informados
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     */
    public void pesquisarItensFiltro(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(request);

        if (usuario == null) {
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }
        setConexaoBIDTO((ConexaoBIDTO) document.getBean());

        recarregarLista(document, request, response);
        document.executeScript("fecharModal()");
    }

    private String setarStatusUltimaIntegracao(final ConexaoBIDTO conexaoBI, final HttpServletRequest request) throws Exception {
        String htmlStatus = "";

        if (conexaoBI.getStatus() == null || conexaoBI.getStatus().equalsIgnoreCase(" ")) {
            htmlStatus = "<div class='ribbon-wrapper small'><div class='ribbon default'>" + UtilI18N.internacionaliza(request, "citcorpore.comum.ativo")
                    + "</div></div>";
        } else if (conexaoBI.getStatus().equalsIgnoreCase("S")) {
            // Sucesso
            htmlStatus = "<div class='ribbon-wrapper small'><div class='ribbon success'>" + UtilI18N.internacionaliza(request, "citcorpore.comum.sucesso")
                    + "</div></div>";
        } else if (conexaoBI.getStatus().equalsIgnoreCase("F")) {
            // Falha
            htmlStatus = "<div class='ribbon-wrapper small'><div class='ribbon danger'>" + UtilI18N.internacionaliza(request, "citcorpore.comum.falha")
                    + "</div></div>";
        } else {
            htmlStatus = "<div class='ribbon-wrapper small'><div class='ribbon'>" + UtilI18N.internacionaliza(request, "citcorpore.comum.inativo")
                    + "</div></div>";
        }

        return htmlStatus;
    }

    /**
     * Retorna a lista de conexões paginada.
     *
     * @param conexaoBIDTO
     *            - tipo da coleção a ser buscado.
     * @param pgAtual
     *            - Página selecionada.
     * @param qtdPaginacao
     *            - quantidade de itens a ser exibido na tela.
     * @return Collection<ConexaoBIDTO>
     * @throws Exception
     * @author thiago.barbosa
     * @since 16.12.2013 - ás 11:00
     */
    @SuppressWarnings("unchecked")
    public Collection<ConexaoBIDTO> listarConexoesPaginadas(final Collection<ConexaoBIDTO> conexaoBIDTO, final Integer pgAtual, final Integer qtdPaginacao)
            throws Exception {
        final ConexaoBIService conexaoBIService = (ConexaoBIService) ServiceLocator.getInstance().getService(ConexaoBIService.class, null);
        return conexaoBIService.listarConexoesPaginadas(conexaoBIDTO, pgAtual, qtdPaginacao);

    }

    /**
     * Retorna a lista de conexões paginada Filtradas.
     *
     * @param conexaoBIDTO
     *            - tipo da coleção a ser buscado.
     * @param pgAtual
     *            - Página selecionada.
     * @param qtdPaginacao
     *            - quantidade de itens a ser exibido na tela.
     * @return Collection<ConexaoBIDTO>
     * @throws Exception
     * @author thiago.barbosa
     * @since 16.12.2013 - ás 11:00
     */
    @SuppressWarnings("unchecked")
    public Collection<ConexaoBIDTO> listarConexoesPaginadasFiltradas(final ConexaoBIDTO conexaoBIDTO, final Integer pgAtual, final Integer qtdPaginacao)
            throws Exception {
        final ConexaoBIService conexaoBIService = (ConexaoBIService) ServiceLocator.getInstance().getService(ConexaoBIService.class, null);
        return conexaoBIService.listarConexoesPaginadasFiltradas(conexaoBIDTO, pgAtual, qtdPaginacao);

    }

    /**
     * Metodo da funcao executar agora, responsavel por chamar a importacao automatica imediata
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     */
    public void executarAgora(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        ConexaoBIDTO conexaoBIDTO = (ConexaoBIDTO) document.getBean();
        final ConexaoBIService conexaoBIService = (ConexaoBIService) ServiceLocator.getInstance().getService(ConexaoBIService.class, null);
        conexaoBIDTO = (ConexaoBIDTO) conexaoBIService.restore(conexaoBIDTO);

        final BICitsmartOperation biCitsmartOperation = new BICitsmartOperation();

        final BICitsmartResultRotinaDTO resultExecucao = biCitsmartOperation.execucaoRotinaAutomatica(conexaoBIDTO, null);
        // document.executeScript("parent.fechaJanelaAguarde();");
        document.executeScript("fechaJanelaAguarde();");

        if (resultExecucao.isResultado()) {
            document.alert(UtilI18N.internacionaliza(request, "bi.painelControle.conexao.importacaoSucesso"));
        } else {
            document.alert(UtilI18N.internacionaliza(request, "bi.painelControle.conexao.importacaoFalha"));
        }

        document.executeScript("document.location.reload()");
    }

    /***
     * Testa a conexão com o cliente
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     * @author rodrigo.acorse
     */
    public void testarConexao(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(request);

        if (usuario == null) {
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }

        ConexaoBIDTO conexaoBIDTO = (ConexaoBIDTO) document.getBean();
        final ConexaoBIService conexaoBIService = (ConexaoBIService) ServiceLocator.getInstance().getService(ConexaoBIService.class, null);
        conexaoBIDTO = (ConexaoBIDTO) conexaoBIService.restore(conexaoBIDTO);

        final BICitsmartOperation biCitsmartOperation = new BICitsmartOperation();
        final BICitsmartResultRotinaDTO responseTesteConexao = biCitsmartOperation.testeConexaoClienteBICitsmart(request, conexaoBIDTO.getIdConexaoBI(),
                conexaoBIDTO.getLink(), conexaoBIDTO.getLogin(), conexaoBIDTO.getSenha());

        document.executeScript("parent.fechaJanelaAguarde();");
        document.alert(responseTesteConexao.getMensagem());
    }

    /**
     * Metodo da funcao alterar status, responsavel por setar o status da conexão como (Ativo) ou (Inativo)
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     */
    public void alterarStatus(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        ConexaoBIDTO conexaoBIDTO = (ConexaoBIDTO) document.getBean();
        String status = "";
        status = conexaoBIDTO.getStatus();
        final ConexaoBIService conexaoBIService = (ConexaoBIService) ServiceLocator.getInstance().getService(ConexaoBIService.class, null);
        if (conexaoBIDTO.getIdConexaoBI() != null) {
            conexaoBIDTO = (ConexaoBIDTO) conexaoBIService.restore(conexaoBIDTO);
            conexaoBIDTO.setStatus(status);
            conexaoBIService.update(conexaoBIDTO);
            document.executeScript("parent.StatusMsg();");
        }
    }

}
