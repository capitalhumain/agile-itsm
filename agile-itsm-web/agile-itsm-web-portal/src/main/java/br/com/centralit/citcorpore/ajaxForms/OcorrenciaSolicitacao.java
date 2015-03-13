/**
 * @author breno.guimaraes
 *
 */

package br.com.centralit.citcorpore.ajaxForms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import br.com.centralit.citajax.html.AjaxFormAction;
import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citajax.html.HTMLForm;
import br.com.centralit.citajax.html.HTMLSelect;
import br.com.centralit.citcorpore.bean.AprovacaoSolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.CategoriaOcorrenciaDTO;
import br.com.centralit.citcorpore.bean.ContatoSolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.DadosEmailRegOcorrenciaDTO;
import br.com.centralit.citcorpore.bean.EmpregadoDTO;
import br.com.centralit.citcorpore.bean.JustificativaSolicitacaoDTO;
import br.com.centralit.citcorpore.bean.OcorrenciaSolicitacaoDTO;
import br.com.centralit.citcorpore.bean.OrigemOcorrenciaDTO;
import br.com.centralit.citcorpore.bean.ServicoContratoDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.integracao.CategoriaOcorrenciaDAO;
import br.com.centralit.citcorpore.integracao.OrigemOcorrenciaDAO;
import br.com.centralit.citcorpore.mail.MensagemEmail;
import br.com.centralit.citcorpore.negocio.AprovacaoSolicitacaoServicoService;
import br.com.centralit.citcorpore.negocio.CategoriaOcorrenciaService;
import br.com.centralit.citcorpore.negocio.EmpregadoService;
import br.com.centralit.citcorpore.negocio.GrupoService;
import br.com.centralit.citcorpore.negocio.JustificativaSolicitacaoService;
import br.com.centralit.citcorpore.negocio.OcorrenciaService;
import br.com.centralit.citcorpore.negocio.OcorrenciaSolicitacaoService;
import br.com.centralit.citcorpore.negocio.OrigemOcorrenciaService;
import br.com.centralit.citcorpore.negocio.ServicoContratoService;
import br.com.centralit.citcorpore.negocio.SolicitacaoServicoService;
import br.com.centralit.citcorpore.negocio.UsuarioService;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;
import br.com.centralit.citcorpore.util.Enumerados.TipoDate;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.centralit.citcorpore.util.WebUtil;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilHTML;
import br.com.citframework.util.UtilI18N;
import br.com.citframework.util.UtilStrings;

import com.google.gson.Gson;

@SuppressWarnings("rawtypes")
public class OcorrenciaSolicitacao extends AjaxFormAction {

    @Override
    public Class<OcorrenciaSolicitacaoDTO> getBeanClass() {
        return OcorrenciaSolicitacaoDTO.class;
    }

    private OcorrenciaSolicitacaoService service;

    private OcorrenciaSolicitacaoService getService() throws ServiceException {
        if (service == null) {
            service = (OcorrenciaSolicitacaoService) ServiceLocator.getInstance().getService(OcorrenciaSolicitacaoService.class, null);
        }
        return service;
    }

    @Override
    public void load(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(request);

        final SolicitacaoServicoService solicitacaoServicoService = (SolicitacaoServicoService) ServiceLocator.getInstance().getService(
                SolicitacaoServicoService.class, null);

        if (usuario == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }
        Integer idSolicitacao = null;
        String apenasVisualizar = "";
        boolean resgistrarOcorrencia = false;
        try {
            idSolicitacao = Integer.parseInt(request.getParameter("idSolicitacaoServico"));
            apenasVisualizar = request.getParameter("visualizar");
            resgistrarOcorrencia = Boolean.valueOf(request.getParameter("resgistrarOcorrencia"));
        } catch (final Exception e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("resgistrarOcorrenciaPortal", resgistrarOcorrencia);

        request.setAttribute("idSolicitacaoServico", idSolicitacao);

        if (apenasVisualizar.equalsIgnoreCase("false")) {
            document.executeScript("$('#tabCadastroOcorrencia').hide();");
        }

        document.executeScript("document.getElementById('divRelacaoOcorrencias').innerHTML = '"
                + UtilI18N.internacionaliza(request, "citcorpore.comum.aguardecarregando") + "'");

        this.listOcorrenciasSituacao(document, request, idSolicitacao);

        this.geraComboCategoria(document, request);

        this.geraComboOrigem(document, request);

        this.configuraCheckNotificaSolicitante(document);

        final SolicitacaoServicoDTO solicitacaoServicoDTO = solicitacaoServicoService.restoreAll(idSolicitacao);

        document.getElementById("registradopor").setValue(usuario.getNomeUsuario());

        this.desabilitarCamposRegistrarOcorrencia(document, resgistrarOcorrencia, solicitacaoServicoDTO, request);

    }

    public void save(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(request);

        if (usuario == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }

        final OcorrenciaSolicitacaoDTO ocorrenciaSolicitacaoDTO = (OcorrenciaSolicitacaoDTO) document.getBean();
        ocorrenciaSolicitacaoDTO.setDataInicio(UtilDatas.getDataAtual());
        ocorrenciaSolicitacaoDTO.setRegistradopor(usuario.getNomeUsuario());
        ocorrenciaSolicitacaoDTO.setDataregistro(UtilDatas.getDataAtual());
        ocorrenciaSolicitacaoDTO.setHoraregistro(UtilDatas.formatHoraFormatadaStr(UtilDatas.getHoraAtual()));
        ocorrenciaSolicitacaoDTO.setIdSolicitacaoServico(ocorrenciaSolicitacaoDTO.getIdSolicitacaoOcorrencia());

        this.getService().create(ocorrenciaSolicitacaoDTO);

        // Realimentando os valores não restaurados
        final CategoriaOcorrenciaService categoriaOcorrenciaService = (CategoriaOcorrenciaService) ServiceLocator.getInstance().getService(
                CategoriaOcorrenciaService.class, null);
        CategoriaOcorrenciaDTO categoriaOcorrenciaDTO = new CategoriaOcorrenciaDTO();
        categoriaOcorrenciaDTO.setIdCategoriaOcorrencia(ocorrenciaSolicitacaoDTO.getIdCategoriaOcorrencia());
        categoriaOcorrenciaDTO = (CategoriaOcorrenciaDTO) categoriaOcorrenciaService.restore(categoriaOcorrenciaDTO);

        final OrigemOcorrenciaService origemOcorrenciaService = (OrigemOcorrenciaService) ServiceLocator.getInstance().getService(
                OrigemOcorrenciaService.class, null);
        OrigemOcorrenciaDTO origemOcorrenciaDTO = new OrigemOcorrenciaDTO();
        origemOcorrenciaDTO.setIdOrigemOcorrencia(ocorrenciaSolicitacaoDTO.getIdOrigemOcorrencia());
        origemOcorrenciaDTO = (OrigemOcorrenciaDTO) origemOcorrenciaService.restore(origemOcorrenciaDTO);

        ocorrenciaSolicitacaoDTO.setCategoria(categoriaOcorrenciaDTO.getNome());
        ocorrenciaSolicitacaoDTO.setOrigem(origemOcorrenciaDTO.getNome());

        // euler.ramos
        // Tratamento para o envio de e-mails notificando o solicitante sobre o lançamento de ocorrências.
        if (ocorrenciaSolicitacaoDTO.getNotificarSolicitante() != null && ocorrenciaSolicitacaoDTO.getNotificarSolicitante().equalsIgnoreCase("S")) {
            try {
                this.enviaEmailSolicitante(this.obterIdModeloEmailNotificacaoSolicitante(), ocorrenciaSolicitacaoDTO, request);
            } catch (final Exception e) {
                System.out.println("Problema no envio do e-mail de notificação de ocorrência ao solicitante.");
            }
        }

        final Integer idEmailRegistroOcorrenciaPortal = this.obterIDModeloEmailNotificacaoResponsavel();

        if (ocorrenciaSolicitacaoDTO.getIsPortal() != null && ocorrenciaSolicitacaoDTO.getIsPortal().equals("true")) {
            if (idEmailRegistroOcorrenciaPortal == null || idEmailRegistroOcorrenciaPortal == 0) {

                document.alert(UtilI18N.internacionaliza(request, "idemailocorrenciaportal.incorreto.vazio"));

                return;
            }
        }

        if ("S".equalsIgnoreCase(ocorrenciaSolicitacaoDTO.getNotificarResponsavel())) {

            this.enviaEmailResponsavel(idEmailRegistroOcorrenciaPortal, ocorrenciaSolicitacaoDTO, request);
        }

        document.alert(UtilI18N.internacionaliza(request, "MSG05"));

        this.listOcorrenciasSituacao(document, request, ocorrenciaSolicitacaoDTO.getIdSolicitacaoOcorrencia());
        document.executeScript("limparCamposOcorrencia()");
        document.executeScript("parent.$('#modal_ocorrencia').modal('hide');");
    }

    /**
     * Metodo para recupera o idsolicitacaoOcorrencia para assim listar as ocorrencias relativas aquela solicitacao.
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     *             \
     * @author thays.araujo
     */
    public void recuperaIdSolicitacaoOcorrencia(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        final OcorrenciaSolicitacaoDTO ocorrenciaSolicitacaoDto = (OcorrenciaSolicitacaoDTO) document.getBean();
        if (ocorrenciaSolicitacaoDto.getIdSolicitacaoOcorrencia() != null) {
            this.listOcorrenciasSituacao(document, request, ocorrenciaSolicitacaoDto.getIdSolicitacaoOcorrencia());
        }
    }

    public void listOcorrenciasSituacao(final DocumentHTML document, final HttpServletRequest request, final Integer idSolicitacaoServico) throws Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(request);

        final boolean registraOcorrenciaPortal = request.getSession().getAttribute("resgistrarOcorrenciaPortal") != null ? (boolean) request.getSession()
                .getAttribute("resgistrarOcorrenciaPortal") : Boolean.FALSE;

                final String language = (String) request.getSession().getAttribute("locale");

                if (usuario == null) {
                    document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
                    document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
                    return;
                }

                final CategoriaOcorrenciaDAO categoriaOcorrenciaDAO = new CategoriaOcorrenciaDAO();
                final OrigemOcorrenciaDAO origemOcorrenciaDAO = new OrigemOcorrenciaDAO();

                CategoriaOcorrenciaDTO categoriaOcorrenciaDTO = (CategoriaOcorrenciaDTO) categoriaOcorrenciaDAO.getBean().newInstance();
                OrigemOcorrenciaDTO origemOcorrenciaDTO = (OrigemOcorrenciaDTO) origemOcorrenciaDAO.getBean().newInstance();

                final OcorrenciaSolicitacaoService ocorrenciaService = this.getService();

                final Collection col = idSolicitacaoServico != null ? ocorrenciaService.findByIdSolicitacaoServico(idSolicitacaoServico) : null;

                final StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append("<table class='dynamicTable table table-striped table-bordered table-condensed dataTable' style='table-layout: auto;'>");
                stringBuilder.append("<tr>");
                stringBuilder.append("<td class=''>");
                stringBuilder.append(UtilI18N.internacionaliza(request, "ocorrenciaSolicitacao.codigoocorrencia"));
                stringBuilder.append("</td>");
                stringBuilder.append("<td class=''>");
                stringBuilder.append(UtilI18N.internacionaliza(request, "ocorrenciaSolicitacao.informacaoocorrencia"));
                stringBuilder.append("</td>");
                stringBuilder.append("<td class=''>");
                stringBuilder.append(UtilI18N.internacionaliza(request, "citcorpore.comum.categoria"));
                stringBuilder.append("</td>");
                stringBuilder.append("<td class=''>");
                stringBuilder.append(UtilI18N.internacionaliza(request, "citcorpore.comum.origem"));
                stringBuilder.append("</td>");
                stringBuilder.append("<td class=''>");
                stringBuilder.append(UtilI18N.internacionaliza(request, "ocorrenciaSolicitacao.tempoGasto"));
                stringBuilder.append("</td>");
                if (!registraOcorrenciaPortal) {
                    stringBuilder.append("<td class=''>");
                    stringBuilder.append(UtilI18N.internacionaliza(request, "ocorrenciaSolicitacao.emailPSolicitante"));
                    stringBuilder.append("</td>");
                } else {
                    stringBuilder.append("<td class=''>");
                    stringBuilder.append(UtilI18N.internacionaliza(request, "ocorrenciaSolicitacao.emailPResponsavel"));
                    stringBuilder.append("</td>");
                }
                stringBuilder.append("</tr>");

                final UsuarioService usuarioService = (UsuarioService) ServiceLocator.getInstance().getService(UsuarioService.class, null);

                if (col != null && col.size() > 0) {
                    final JustificativaSolicitacaoService justificativaService = (JustificativaSolicitacaoService) ServiceLocator.getInstance().getService(
                            JustificativaSolicitacaoService.class, null);
                    final AprovacaoSolicitacaoServicoService aprovacaoService = (AprovacaoSolicitacaoServicoService) ServiceLocator.getInstance().getService(
                            AprovacaoSolicitacaoServicoService.class, null);
                    final EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);

                    for (final Iterator it = col.iterator(); it.hasNext();) {
                        final OcorrenciaSolicitacaoDTO ocorrenciaSolicitacaoAux = (OcorrenciaSolicitacaoDTO) it.next();
                        String ocorrencia = UtilStrings.nullToVazio(ocorrenciaSolicitacaoAux.getOcorrencia());

                        if (ocorrenciaSolicitacaoAux.getIdJustificativa() != null) {
                            JustificativaSolicitacaoDTO justificativaDto = new JustificativaSolicitacaoDTO();
                            justificativaDto.setIdJustificativa(ocorrenciaSolicitacaoAux.getIdJustificativa());
                            justificativaDto = (JustificativaSolicitacaoDTO) justificativaService.restore(justificativaDto);
                            if (justificativaDto != null) {
                                ocorrencia += "<br/>" + UtilI18N.internacionaliza(request, "citcorpore.comum.justificativa") + ": "
                                        + justificativaDto.getDescricaoJustificativa() + "<br/>";
                            }
                        }

                        if (ocorrenciaSolicitacaoAux.getComplementoJustificativa() != null) {
                            ocorrencia += "<br/>" + UtilI18N.internacionaliza(request, "gerenciaservico.mudarsla.complementojustificativa") + ": <b>"
                                    + ocorrenciaSolicitacaoAux.getComplementoJustificativa() + "<br/>";
                        }

                        String dadosSolicitacao = UtilStrings.nullToVazio(ocorrenciaSolicitacaoAux.getDadosSolicitacao());

                        SolicitacaoServicoDTO solicitacaoDto = null;
                        if (dadosSolicitacao.length() > 0) {
                            try {
                                solicitacaoDto = new Gson().fromJson(dadosSolicitacao, SolicitacaoServicoDTO.class);

                                solicitacaoDto.setDataHoraSolicitacaoStr(UtilDatas.convertDateToString(TipoDate.TIMESTAMP_WITH_SECONDS,
                                        solicitacaoDto.getDataHoraSolicitacao(), language));
                                solicitacaoDto.setDataHoraLimiteStr(UtilDatas.convertDateToString(TipoDate.TIMESTAMP_WITH_SECONDS, solicitacaoDto.getDataHoraLimite(),
                                        language));

                                if (solicitacaoDto != null) {
                                    dadosSolicitacao = solicitacaoDto.getDadosStr();
                                }
                            } catch (final Exception e) {
                                dadosSolicitacao = "";
                            }
                        }

                        String descricao = UtilStrings.nullToVazio(ocorrenciaSolicitacaoAux.getDescricao());
                        String informacoesContato = UtilStrings.nullToVazio(ocorrenciaSolicitacaoAux.getInformacoesContato());

                        if (informacoesContato.length() > 0) {
                            try {
                                final ContatoSolicitacaoServicoDTO contatoDto = new Gson().fromJson(informacoesContato, ContatoSolicitacaoServicoDTO.class);
                                if (contatoDto != null) {
                                    informacoesContato = contatoDto.getDadosStr();
                                }
                            } catch (final Exception e) {
                                informacoesContato = "";
                            }
                        }

                        String aprovacao = "";
                        if (solicitacaoDto != null && solicitacaoDto.getIdUltimaAprovacao() != null && ocorrenciaSolicitacaoAux.getIdItemTrabalho() != null) {
                            AprovacaoSolicitacaoServicoDTO aprovacaoDto = new AprovacaoSolicitacaoServicoDTO();
                            aprovacaoDto.setIdAprovacaoSolicitacaoServico(solicitacaoDto.getIdUltimaAprovacao());
                            aprovacaoDto = (AprovacaoSolicitacaoServicoDTO) aprovacaoService.restore(aprovacaoDto);
                            if (aprovacaoDto.getIdTarefa() != null && aprovacaoDto.getIdTarefa().intValue() == ocorrenciaSolicitacaoAux.getIdItemTrabalho().intValue()) {
                                final EmpregadoDTO empregadoDto = empregadoService.restoreByIdEmpregado(aprovacaoDto.getIdResponsavel());
                                if (empregadoDto != null) {
                                    aprovacao += UtilI18N.internacionaliza(request, "citcorpore.comum.aprovador") + ": " + empregadoDto.getNome() + "<br/>";
                                }
                                aprovacao += UtilI18N.internacionaliza(request, "citcorpore.comum.aprovada") + ": ";
                                if (aprovacaoDto.getAprovacao().equalsIgnoreCase("A")) {
                                    aprovacao += "Sim";
                                } else {
                                    aprovacao += "Não";
                                }
                                if (aprovacaoDto.getIdJustificativa() != null) {
                                    JustificativaSolicitacaoDTO justificativaDto = new JustificativaSolicitacaoDTO();
                                    justificativaDto.setIdJustificativa(aprovacaoDto.getIdJustificativa());
                                    justificativaDto = (JustificativaSolicitacaoDTO) justificativaService.restore(justificativaDto);
                                    if (justificativaDto != null) {
                                        aprovacao += "<br/>" + UtilI18N.internacionaliza(request, "citcorpore.comum.justificativa") + ": "
                                                + justificativaDto.getDescricaoJustificativa();
                                    }
                                }
                                if (aprovacaoDto.getComplementoJustificativa() != null && aprovacaoDto.getComplementoJustificativa().trim().length() > 0) {
                                    aprovacao += "<br/>" + UtilI18N.internacionaliza(request, "gerenciaservico.mudarsla.complementojustificativa") + ": "
                                            + aprovacaoDto.getComplementoJustificativa();
                                }
                                if (aprovacaoDto.getObservacoes() != null && aprovacaoDto.getObservacoes().trim().length() > 0) {
                                    aprovacao += "<br/>" + UtilI18N.internacionaliza(request, "citcorpore.comum.observacoes") + ": "
                                            + StringEscapeUtils.unescapeEcmaScript(aprovacaoDto.getObservacoes());
                                }
                            }
                        }

                        ocorrencia = ocorrencia.replaceAll("\"", "");
                        descricao = descricao.replaceAll("\"", "");
                        informacoesContato = informacoesContato.replaceAll("\"", "");
                        ocorrencia = ocorrencia.replaceAll("\n", "<br/>");
                        descricao = descricao.replaceAll("\n", "<br/>");
                        informacoesContato = informacoesContato.replaceAll("\n", "<br/>");
                        dadosSolicitacao = dadosSolicitacao.replaceAll("\n", "<br/>");
                        ocorrencia = UtilHTML.encodeHTML(ocorrencia.replaceAll("\'", ""));
                        descricao = UtilHTML.encodeHTML(descricao.replaceAll("\'", ""));
                        informacoesContato = UtilHTML.encodeHTML(informacoesContato.replaceAll("\'", ""));

                        stringBuilder.append("<tr>");
                        stringBuilder.append("<td rowspan='4'>");
                        stringBuilder.append("<b>" + ocorrenciaSolicitacaoAux.getIdOcorrencia() + "</b>");
                        stringBuilder.append("</td>");
                        stringBuilder.append("<td >");
                        stringBuilder.append("<b>" + UtilDatas.convertDateToString(TipoDate.DATE_DEFAULT, ocorrenciaSolicitacaoAux.getDataregistro(), language) + " - "
                                + ocorrenciaSolicitacaoAux.getHoraregistro());
                        String strRegPor = ocorrenciaSolicitacaoAux.getRegistradopor();
                        try {
                            if (ocorrenciaSolicitacaoAux.getRegistradopor() != null
                                    && !ocorrenciaSolicitacaoAux.getRegistradopor().trim().equalsIgnoreCase("Automático")) {
                                final UsuarioDTO usuarioDto = usuarioService.restoreByLogin(ocorrenciaSolicitacaoAux.getRegistradopor());
                                if (usuarioDto != null) {
                                    final EmpregadoDTO empregadoDto = empregadoService.restoreByIdEmpregado(usuarioDto.getIdEmpregado());
                                    strRegPor = strRegPor + " - " + empregadoDto.getNome();
                                }
                            }
                        } catch (final Exception e) {}
                        if (ocorrenciaSolicitacaoAux.getRegistradopor() != null) {
                            stringBuilder.append(" </b> - " + UtilI18N.internacionaliza(request, "ocorrenciaSolicitacao.registradopor") + ": <br/><b>" + strRegPor
                                    + "</b>");
                        }
                        stringBuilder.append("</td>");

                        // Categoria Ocorrência
                        stringBuilder.append("<td >");
                        if (ocorrenciaSolicitacaoAux.getIdCategoriaOcorrencia() != null && ocorrenciaSolicitacaoAux.getIdCategoriaOcorrencia() != 0) {
                            categoriaOcorrenciaDTO.setIdCategoriaOcorrencia(ocorrenciaSolicitacaoAux.getIdCategoriaOcorrencia());
                            categoriaOcorrenciaDTO = (CategoriaOcorrenciaDTO) categoriaOcorrenciaDAO.restore(categoriaOcorrenciaDTO);
                            stringBuilder.append(UtilI18N.internacionaliza(request, "citcorpore.comum.categoria") + ": <br/><b>" + categoriaOcorrenciaDTO.getNome()
                                    + "</b>");
                        } else {
                            stringBuilder.append(UtilI18N.internacionaliza(request, "citcorpore.comum.categoria") + ": <br/>");
                        }
                        stringBuilder.append("</td>");

                        // Origem Ocorrência
                        stringBuilder.append("<td >");
                        if (ocorrenciaSolicitacaoAux.getIdOrigemOcorrencia() != null && ocorrenciaSolicitacaoAux.getIdOrigemOcorrencia() != 0) {
                            origemOcorrenciaDTO.setIdOrigemOcorrencia(ocorrenciaSolicitacaoAux.getIdOrigemOcorrencia());
                            origemOcorrenciaDTO = (OrigemOcorrenciaDTO) origemOcorrenciaDAO.restore(origemOcorrenciaDTO);
                            stringBuilder
                            .append(UtilI18N.internacionaliza(request, "origemAtendimento.origem") + ": <br/><b>" + origemOcorrenciaDTO.getNome() + "</b>");
                        } else {
                            stringBuilder.append(UtilI18N.internacionaliza(request, "origemAtendimento.origem") + ": <br/>");
                        }
                        stringBuilder.append("</td>");

                        stringBuilder.append("<td >");
                        stringBuilder.append(UtilI18N.internacionaliza(request, "ocorrenciaSolicitacao.tempoGasto") + ": <br/><b>"
                                + (ocorrenciaSolicitacaoAux.getTempoGasto() != null ? "" + ocorrenciaSolicitacaoAux.getTempoGasto() + " min" : "--") + "</b>");
                        stringBuilder.append("</td>");

                        if (!registraOcorrenciaPortal) {
                            stringBuilder.append("<td >");
                            stringBuilder.append(UtilI18N.internacionaliza(request, "ocorrenciaSolicitacao.emailPSolicitante") + ": <br/>");
                            if (ocorrenciaSolicitacaoAux.getNotificarSolicitante() != null && ocorrenciaSolicitacaoAux.getNotificarSolicitante().equalsIgnoreCase("S")) {
                                stringBuilder.append("<b>" + UtilI18N.internacionaliza(request, "citcorpore.comum.enviado") + "</b>");
                            } else {
                                stringBuilder.append("<b>" + UtilI18N.internacionaliza(request, "citcorpore.comum.naoenviado") + "</b>");
                            }
                            stringBuilder.append("</td>");
                        } else {
                            stringBuilder.append("<td >");
                            stringBuilder.append(UtilI18N.internacionaliza(request, "ocorrenciaSolicitacao.emailPResponsavel") + ": <br/>");
                            if (ocorrenciaSolicitacaoAux.getNotificarResponsavel() != null && ocorrenciaSolicitacaoAux.getNotificarResponsavel().equalsIgnoreCase("S")) {
                                stringBuilder.append("<b>" + UtilI18N.internacionaliza(request, "citcorpore.comum.enviado") + "</b>");
                            } else {
                                stringBuilder.append("<b>" + UtilI18N.internacionaliza(request, "citcorpore.comum.naoenviado") + "</b>");
                            }
                            stringBuilder.append("</td>");
                        }

                        stringBuilder.append("</tr>");

                        if (dadosSolicitacao == null || dadosSolicitacao.trim().equalsIgnoreCase("")) {
                            stringBuilder.append("<tr>");
                            stringBuilder.append("<td colspan='5';>");
                            stringBuilder.append(UtilI18N.internacionaliza(request, "citcorpore.comum.descricao") + ": <p class='ocorrenciaSolicitacaoTextoLongo'>"
                                    + StringEscapeUtils.unescapeEcmaScript(descricao) + "</p>");
                            stringBuilder.append("</td>");
                            stringBuilder.append("</tr>");
                        } else {
                            stringBuilder.append("<tr>");
                            stringBuilder.append("<td colspan='5';>");

                            stringBuilder.append(UtilI18N.internacionaliza(request, "citcorpore.comum.descricao") + ": <p class='ocorrenciaSolicitacaoTextoLongo'>"
                                    + StringEscapeUtils.unescapeEcmaScript(descricao) + "</p>");

                            if (dadosSolicitacao != null && !dadosSolicitacao.trim().equalsIgnoreCase("")) {
                                stringBuilder.append(UtilI18N.internacionaliza(request, "ocorrenciaSolicitacao.dadosolicitacao") + ": <b><br/>"
                                        + StringEscapeUtils.unescapeEcmaScript(dadosSolicitacao) + "<br/><br/></b>");
                            }

                            stringBuilder.append("</td>");
                            stringBuilder.append("</tr>");
                        }

                        if (ocorrencia.length() > 0) {
                            stringBuilder.append("<tr>");
                            stringBuilder.append("<td colspan='5';>");
                            stringBuilder.append(UtilI18N.internacionaliza(request, "citcorpore.comum.ocorrencia") + ": <p class='ocorrenciaSolicitacaoTextoLongo'>"
                                    + StringEscapeUtils.unescapeEcmaScript(ocorrencia) + "</p>");
                            if (aprovacao.length() > 0) {
                                stringBuilder.append("<b>" + aprovacao + "<br/><br/></b>");
                            }
                            stringBuilder.append("</td>");
                            stringBuilder.append("</tr>");
                        } else {
                            stringBuilder.append("<tr>");
                            stringBuilder.append("<td colspan='5' style='word-wrap: break-word;overflow:hidden;' >");
                            stringBuilder.append("&nbsp;");
                            stringBuilder.append("</td>");
                            stringBuilder.append("</tr>");
                        }

                        if (informacoesContato == null || informacoesContato.trim().equalsIgnoreCase("")) {
                            stringBuilder.append("<tr>");
                            stringBuilder.append("<td colspan='5' style='word-wrap: break-word;overflow:hidden;' >");
                            if (ocorrenciaSolicitacaoAux.getInformacoesContato() != null && ocorrenciaSolicitacaoAux.getInformacoesContato().length() > 0) {
                                stringBuilder.append(UtilI18N.internacionaliza(request, "citcorpore.comum.informacaoContato") + ": <br/><b>"
                                        + StringEscapeUtils.unescapeEcmaScript(ocorrenciaSolicitacaoAux.getInformacoesContato()) + "<br/><br/></b>");
                            } else {
                                stringBuilder.append("&nbsp;");
                            }
                            stringBuilder.append("</td>");
                            stringBuilder.append("</tr>");
                        } else {
                            stringBuilder.append("<tr>");
                            stringBuilder.append("<td colspan='5' style='word-wrap: break-word;overflow:hidden;' >");
                            if (informacoesContato.length() > 0) {
                                stringBuilder.append(UtilI18N.internacionaliza(request, "citcorpore.comum.informacaoContato") + ": <br/><b>"
                                        + StringEscapeUtils.unescapeEcmaScript(informacoesContato) + "<br/><br/></b>");
                            } else {
                                stringBuilder.append("&nbsp;");
                            }
                            stringBuilder.append("</td>");
                            stringBuilder.append("</tr>");
                        }
                    }
                } else {
                    stringBuilder.append("<table width='100%' class='dynamicTable table table-striped table-bordered table-condensed dataTable' >");
                    stringBuilder.append("<tr>");
                    stringBuilder.append("<td colspan='5' >");
                    stringBuilder.append("<b>" + UtilI18N.internacionaliza(request, "citcorpore.comum.naoinformacao") + "</b>");
                    stringBuilder.append("</td>");
                    stringBuilder.append("</tr>");
                }
                stringBuilder.append("</table>");
                document.getElementById("divRelacaoOcorrencias").setInnerHTML(stringBuilder.toString());
    }

    public void restore(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        OcorrenciaSolicitacaoDTO ocorrencia = (OcorrenciaSolicitacaoDTO) document.getBean();
        ocorrencia = (OcorrenciaSolicitacaoDTO) this.getService().restore(ocorrencia);
        final HTMLForm form = document.getForm("formOcorrenciaSolicitacao");
        form.clear();
        form.setValues(ocorrencia);
        this.configuraCheckNotificaSolicitante(document);
    }

    private void geraComboCategoria(final DocumentHTML document, final HttpServletRequest request) throws Exception {
        final HTMLSelect comboTipoDemanda = document.getSelectById("categoria");
        comboTipoDemanda.removeAllOptions();
        comboTipoDemanda.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));

        for (final Enumerados.CategoriaOcorrencia c : Enumerados.CategoriaOcorrencia.values()) {
            if (!c.getSigla().toString().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.MudancaSLA.getSigla().toString())
                    && !c.getSigla().toString().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.Reclassificacao.getSigla().toString())
                    && !c.getSigla().toString().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.Agendamento.getSigla().toString())
                    && !c.getSigla().toString().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.Suspensao.getSigla().toString())
                    && !c.getSigla().toString().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.Reativacao.getSigla().toString())
                    && !c.getSigla().toString().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.Encerramento.getSigla().toString())
                    && !c.getSigla().toString().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.Reabertura.getSigla().toString())
                    && !c.getSigla().toString().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.Direcionamento.getSigla().toString())
                    && !c.getSigla().toString().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.Compartilhamento.getSigla().toString())
                    && !c.getSigla().toString().equalsIgnoreCase(Enumerados.CategoriaOcorrencia.Criacao.getSigla().toString())) {
                comboTipoDemanda.addOption(c.getSigla().toString(), c.getDescricao());
            }
        }
    }

    private void geraComboOrigem(final DocumentHTML document, final HttpServletRequest request) throws Exception {
        final HTMLSelect comboTipoDemanda = document.getSelectById("origem");
        comboTipoDemanda.removeAllOptions();
        comboTipoDemanda.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));

        for (final Enumerados.OrigemOcorrencia c : Enumerados.OrigemOcorrencia.values()) {
            comboTipoDemanda.addOption(c.getSigla().toString(), c.getDescricao());
        }
    }

    public void enviaEmailSolicitante(final Integer idModeloEmail, final OcorrenciaSolicitacaoDTO ocorrenciaSolicitacaoDTO, final HttpServletRequest request)
            throws Exception {
        MensagemEmail mensagem = null;

        if (idModeloEmail == null || idModeloEmail.intValue() == 0) {
            return;
        }

        // Buscando os dados da Solicitação de Serviço
        final SolicitacaoServicoService solicitacaoServicoService = (SolicitacaoServicoService) ServiceLocator.getInstance().getService(
                SolicitacaoServicoService.class, null);
        final SolicitacaoServicoDTO solicitacaoServicoDTO = solicitacaoServicoService.restoreAll(ocorrenciaSolicitacaoDTO.getIdSolicitacaoServico());
        if (solicitacaoServicoDTO == null) {
            return;
        }

        // Buscando os dados do Solicitante
        final EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);
        EmpregadoDTO empregadoDto = new EmpregadoDTO();
        empregadoDto.setIdEmpregado(solicitacaoServicoDTO.getIdSolicitante());
        empregadoDto = (EmpregadoDTO) empregadoService.restore(empregadoDto);
        if (empregadoDto == null) {
            return;
        }

        // Alimentando os parâmetros possíveis
        final Map<String, String> mapParametros = new HashMap<String, String>();
        mapParametros.put("NOMECONTATO", solicitacaoServicoDTO.getNomecontato());
        mapParametros.put("REGISTRADOPOR", ocorrenciaSolicitacaoDTO.getRegistradopor());
        mapParametros.put("DESCRICAO", ocorrenciaSolicitacaoDTO.getDescricao());
        mapParametros.put("OCORRENCIA", ocorrenciaSolicitacaoDTO.getOcorrencia());
        mapParametros.put("IDSOLICITACAOSERVICO", solicitacaoServicoDTO.getIdSolicitacaoServico().toString());
        mapParametros.put("DEMANDA", solicitacaoServicoDTO.getDemanda());
        mapParametros.put("SERVICO", solicitacaoServicoDTO.getServico());
        mapParametros.put("INFORMACOESCONTATO", ocorrenciaSolicitacaoDTO.getInformacoesContato());
        mapParametros.put("CATEGORIA", ocorrenciaSolicitacaoDTO.getCategoria());
        mapParametros.put("ORIGEM", ocorrenciaSolicitacaoDTO.getOrigem());
        mapParametros.put("TEMPOGASTO",
                ocorrenciaSolicitacaoDTO.getTempoGasto().toString() + " " + UtilI18N.internacionaliza(request, "citcorpore.texto.tempo.minutoS"));

        String email = "";
        try {
            email = empregadoDto.getEmail();
        } catch (final Exception e) {
            return;
        }

        if (email == null || email.isEmpty() || email.equalsIgnoreCase("")) {
            return;
        }

        final String remetente = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.RemetenteNotificacoesSolicitacao, null);
        if (remetente == null) {
            return;
        }

        try {
            try {
                mensagem = new MensagemEmail(idModeloEmail, mapParametros);
                mensagem.envia(email, remetente, remetente);
            } catch (final Exception e) {}
        } catch (final Exception e) {}
    }

    /**
     * @author euler.ramos Obtém o id do modelo de email referente à notificação
     *         de ocorrências ao solicitante
     * @return
     */
    private Integer obterIdModeloEmailNotificacaoSolicitante() {
        Integer idModeloEmail = 0;
        try {
            final String idModeloEmailNotificarSolicitante = ParametroUtil.getValorParametroCitSmartHashMap(
                    ParametroSistema.ID_MODELO_EMAIL_NOTIFICAR_SOLICITANTE, "0");
            idModeloEmail = Integer.parseInt(idModeloEmailNotificarSolicitante.trim());
        } catch (final NumberFormatException e) {
            System.out.println("Modelo de e-mail de notificação de ocorrências não definido.");
            idModeloEmail = 0;
        }
        return idModeloEmail;
    }

    private void configuraCheckNotificaSolicitante(final DocumentHTML document) {
        try {
            if (this.obterIdModeloEmailNotificacaoSolicitante() > 0) {
                document.getCheckboxById("checkNotificarSolicitante").setChecked(true);
                document.getCheckboxById("checkNotificarSolicitante").setDisabled(false);
            } else {
                document.getCheckboxById("checkNotificarSolicitante").setChecked(false);
                document.getCheckboxById("checkNotificarSolicitante").setDisabled(true);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo responsavel por obter o paramentro de envio de email pelo portal obter o parametro de modelo de envio de email da ocorrencias
     * realizadas no portal.
     *
     * @return
     * @author Ezequiel
     */
    private Integer obterIDModeloEmailNotificacaoResponsavel() {

        try {

            return Integer.parseInt(ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.ID_EMAIL_REGISTRO_OCORRENCIA_PELO_PORTAL, "0"));
        } catch (final NumberFormatException e) {

            return null;
        }
    }

    /**
     * Metodo responsavel por enivar o email para o responsavel pela solicitação
     *
     * @param idModeloEmail
     * @param ocorrenciaSolicitacaoDTO
     * @param request
     * @throws Exception
     *
     * @author Ezequiel
     */
    private void enviaEmailResponsavel(final Integer idModeloEmail, final OcorrenciaSolicitacaoDTO ocorrenciaSolicitacaoDTO, final HttpServletRequest request)
            throws Exception {

        MensagemEmail mensagem = null;

        if (idModeloEmail == null || idModeloEmail.intValue() == 0) {
            System.out.println("ID Modelo de Email não configurado");
            return;
        }

        final SolicitacaoServicoService solicitacaoServicoService = (SolicitacaoServicoService) ServiceLocator.getInstance().getService(
                SolicitacaoServicoService.class, null);

        final SolicitacaoServicoDTO solicitacaoServicoDTO = solicitacaoServicoService.restoreAll(ocorrenciaSolicitacaoDTO.getIdSolicitacaoServico());

        if (solicitacaoServicoDTO == null) {
            System.out.println("Solicitação não existe");
            return;
        }

        final Collection<String> emails = this.obteDestinatariosOcorrencia(ocorrenciaSolicitacaoDTO);

        final Map<String, String> mapParametros = new HashMap<String, String>();

        mapParametros.put("IDSOLICITACAOSERVICO", String.valueOf(ocorrenciaSolicitacaoDTO.getIdSolicitacaoServico()));

        mapParametros.put("DATAHORA", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));

        mapParametros.put("REGISTRADOPOR", ocorrenciaSolicitacaoDTO.getRegistradopor());

        mapParametros.put("CATEGORIA", ocorrenciaSolicitacaoDTO.getCategoria());

        mapParametros.put("ORIGEM", ocorrenciaSolicitacaoDTO.getOrigem());

        mapParametros.put("OCORRENCIAS", ocorrenciaSolicitacaoDTO.getOcorrencia());

        ocorrenciaSolicitacaoDTO.setInformacoesContato(ocorrenciaSolicitacaoDTO.getInformacoesContato().replace("\n", "<br>"));

        ocorrenciaSolicitacaoDTO.setInformacoesContato("<br>" + ocorrenciaSolicitacaoDTO.getInformacoesContato());

        mapParametros.put("INFORMACOESCONTATO", ocorrenciaSolicitacaoDTO.getInformacoesContato());

        mapParametros.put("DESCRICAO", ocorrenciaSolicitacaoDTO.getDescricao());

        try {

            for (final String email : emails) {

                final String remetente = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.RemetenteNotificacoesSolicitacao, null);

                if (remetente == null) {
                    System.out.println("Remetente não definido.");
                    return;
                }

                mensagem = new MensagemEmail(idModeloEmail, mapParametros);

                mensagem.envia(email, null, remetente);
            }

        } catch (final Exception e) {

            System.out.println("Erro ao envia o email: ");
            e.printStackTrace();
        }
    }

    /**
     * Metodo responsavel por obter os emails referentes aos destinatarios de uma ocorrencia, implementação referente a inicitiva 481.
     *
     * @param ocorrencia
     * @throws ServiceException
     * @throws Exception
     * @return Collection
     *
     * @author Ezequiel Bispo Nunes
     */
    private Collection<String> obteDestinatariosOcorrencia(final OcorrenciaSolicitacaoDTO ocorrencia) throws ServiceException, Exception {

        final OcorrenciaService ocorrenciaService = (OcorrenciaService) ServiceLocator.getInstance().getService(OcorrenciaService.class, null);

        final GrupoService grupoEmailService = (GrupoService) ServiceLocator.getInstance().getService(GrupoService.class, null);

        final ServicoContratoService servicoContratoService = (ServicoContratoService) ServiceLocator.getInstance().getService(ServicoContratoService.class,
                null);

        final DadosEmailRegOcorrenciaDTO dadosEmail = ocorrenciaService.obterDadosResponsavelEmailRegOcorrencia(ocorrencia.getIdSolicitacaoServico());

        final Collection<String> destinatarios = new ArrayList<String>();

        if (dadosEmail != null) {

            /*
             * Verificar se a solicitação possui "Responsável", se sim, enviar e-mail ao Responsável pela solicitação;
             */
            if (dadosEmail.getIdResponsavelAtual() != null) {

                destinatarios.add(dadosEmail.getEmail());
            }

            /*
             * Verificar se a solicitação foi direcionada para um grupo, se sim, enviar e-mail para o grupo que foi vinculado na solicitação;
             */
            if (dadosEmail.getIdGrupoAtual() != null) {

                final Collection<String> emailsGrupo = grupoEmailService.listarEmailsPorGrupo(dadosEmail.getIdGrupoAtual());

                destinatarios.addAll(emailsGrupo);
            }
        }

        if (destinatarios == null || destinatarios.isEmpty()) {

            final ServicoContratoDTO servicoContratoDTO = servicoContratoService.findByIdSolicitacaoServico(ocorrencia.getIdSolicitacaoServico());

            /*
             * O sistema deverá verificar se para o serviço solicitado possui um "Grupo para escalação do atendimento 1.o Nível" pa-rametrizado, se sim, enviar
             * e-mail ao referido grupo pela solicitação
             */
            if (servicoContratoDTO.getIdGrupoNivel1() != null) {

                final Collection<String> emailNivel1 = grupoEmailService.listarEmailsPorGrupo(servicoContratoDTO.getIdGrupoNivel1());

                destinatarios.clear();

                destinatarios.addAll(emailNivel1);
            }

            /*
             * O sistema deverá verificar se para o serviço solicitado possui um "Grupo Executor" parametrizado, se sim, enviar e-mail ao referido grupo pela
             * solicitação
             */
            if (servicoContratoDTO.getIdGrupoNivel1() == null && servicoContratoDTO.getIdGrupoExecutor() != null) {

                final Collection<String> emailGrupoExecutor = grupoEmailService.listarEmailsPorGrupo(servicoContratoDTO.getIdGrupoExecutor());

                destinatarios.clear();

                destinatarios.addAll(emailGrupoExecutor);
            }

            /*
             * Em último, caso a condição 5 não seja satisfeita, o sistema deverá verificar o parâmetro 9 "ID Grupo Nível 1" e enviar a notificação ao e-mail
             * vinculado à esse grupo definido por parâmetro.
             */
            if (servicoContratoDTO.getIdGrupoNivel1() == null && servicoContratoDTO.getIdGrupoExecutor() == null) {

                final Integer idGrupoPadraoNivel1 = Integer.parseInt(ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.ID_GRUPO_PADRAO_NIVEL1,
                        "0"));

                final Collection<String> emailPadraoNivel1 = grupoEmailService.listarEmailsPorGrupo(idGrupoPadraoNivel1);

                destinatarios.clear();

                destinatarios.addAll(emailPadraoNivel1);
            }

        }

        return destinatarios;

    }

    /**
     * Metodo responsavel por desabilitar os ocultar o campo "CheckNotificarSolicitante" que não faz parte dessa funcionalidade, e deixar os campos
     * registradopor e tempoGasto
     * readOnly.
     * <p>
     * O campo tempoGasto tera o valor default igual a ZERO.
     * <p>
     * Metodo possui as chamdas dos demais metodos de regra de apresentação
     * <p>
     * No fim é executada uma função javascript para quebra as linhas dentro da textarea.
     *
     * @param document
     * @param resgistrarOcorrencia
     * @param solicitacaoServicoDTO
     * @throws Exception
     *
     * @author Ezequiel
     */
    private void desabilitarCamposRegistrarOcorrencia(final DocumentHTML document, final Boolean resgistrarOcorrencia,
            final SolicitacaoServicoDTO solicitacaoServicoDTO, final HttpServletRequest request) throws Exception {

        document.getElementById("divCheckNotificarResponsavel").setVisible(Boolean.FALSE);

        if (resgistrarOcorrencia) {

            document.getElementById("divCheckNotificarSolicitante").setVisible(Boolean.FALSE);

            document.getElementById("divCheckNotificarResponsavel").setVisible(Boolean.TRUE);

            document.getElementById("registradopor").setReadonly(Boolean.TRUE);

            document.getElementById("tempoGasto").setValue("0");

            document.getElementById("tempoGasto").setReadonly(Boolean.TRUE);

            this.preencherCampoInfoContato(document, solicitacaoServicoDTO);

            this.disableCamposCategoria(document);

            this.disableCamposOrigem(document);

            /**
             * Cristian: solicitação 165508
             * O método abaixo não funciona no IE. Então, o que eu fiz foi comentar a linha abaixo e chamar este método antes de abrir a modal.
             */
            // document.executeScript("parent.escapeBrTextArea();");
            document.executeScript("escapeBrTextArea();");
        }

        this.validaParametrosOcorrencia(document);
    }

    /**
     * Metodo responsavel por popular na tela a textarea de informações do contato, valores obtidos através do objeto solicitacaoServicoDTO
     *
     * @param document
     * @param solicitacaoServicoDTO
     * @throws Exception
     * @author Ezequiel
     */
    private void preencherCampoInfoContato(final DocumentHTML document, final SolicitacaoServicoDTO solicitacaoServicoDTO) throws Exception {

        final StringBuilder informacoesContato = new StringBuilder();

        informacoesContato.append("Nome: ").append(solicitacaoServicoDTO.getSolicitante());

        informacoesContato.append("<br>");

        informacoesContato.append("Telefone: ").append(solicitacaoServicoDTO.getTelefonecontato());

        informacoesContato.append("<br>");

        informacoesContato.append("E-mail: ").append(solicitacaoServicoDTO.getEmailResponsavel());

        document.getElementById("informacoesContato").setValue(informacoesContato.toString());
    }

    /**
     * Metodo responsavel por desabilitar os campos vinculados ao campo "CATEGORIA" deixando com um valor default obtido por parametro do sistema
     * ID_CATEGORIA_REGISTRA_OCORRENCIA_PORTAL.
     *
     * @param document
     * @throws Exception
     * @author Ezequiel
     */
    private void disableCamposCategoria(final DocumentHTML document) throws Exception {

        final CategoriaOcorrenciaService categoriaService = (CategoriaOcorrenciaService) ServiceLocator.getInstance().getService(
                CategoriaOcorrenciaService.class, null);

        final Integer idCategoria = Integer.parseInt(ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.ID_CATEGORIA_REGISTRA_OCORRENCIA_PORTAL,
                "0"));

        if (idCategoria != null && idCategoria > 0) {

            final CategoriaOcorrenciaDTO categoriaOcorrenciaDTO = categoriaService.restoreAll(idCategoria);

            if (categoriaOcorrenciaDTO != null && categoriaOcorrenciaDTO.getIdCategoriaOcorrencia() != null) {

                document.getElementById("nomeCategoriaOcorrencia").setValue(categoriaOcorrenciaDTO.getNome());

                document.getElementById("idCategoriaOcorrencia").setValue(String.valueOf(categoriaOcorrenciaDTO.getIdCategoriaOcorrencia()));

                document.getElementById("nomeCategoriaOcorrencia").setReadonly(Boolean.TRUE);

                document.executeScript("disabledBtnsCategoria();");
            }
        }
    }

    /**
     * Metodo responsavel por desabilitar os campos vinculados ao campo "ORIGEM" deixando com um valor default, obtido por parametro do sistema
     * ID_ORIGEM_REGISTRA_OCORRENCIA_PORTAL.
     *
     * @param document
     * @throws Exception
     * @author Ezequiel
     */
    private void disableCamposOrigem(final DocumentHTML document) throws Exception {

        final OrigemOcorrenciaService origemOcorrenciaService = (OrigemOcorrenciaService) ServiceLocator.getInstance().getService(
                OrigemOcorrenciaService.class, null);

        final Integer idOrigem = Integer.parseInt(ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.ID_ORIGEM_REGISTRA_OCORRENCIA_PORTAL, "0"));

        if (idOrigem != null && idOrigem > 0) {

            final OrigemOcorrenciaDTO origemOcorrenciaDTO = origemOcorrenciaService.restoreAll(idOrigem);

            if (origemOcorrenciaDTO != null && origemOcorrenciaDTO.getIdOrigemOcorrencia() != null) {

                document.getElementById("nomeOrigemOcorrencia").setReadonly(Boolean.TRUE);

                document.getElementById("nomeOrigemOcorrencia").setValue(origemOcorrenciaDTO.getNome());

                document.getElementById("idOrigemOcorrencia").setValue(String.valueOf(origemOcorrenciaDTO.getIdOrigemOcorrencia()));

                document.executeScript("disabledBtnsOrigem();");

            }

        }
    }

    /**
     * Valida os parâmetros 262 e 263
     *
     * @param document
     * @author thyen.chang
     * @throws Exception
     */
    public void validaParametrosOcorrencia(final DocumentHTML document) throws Exception {
        final String idCategoria = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.ID_CATEGORIA_REGISTRA_OCORRENCIA_PORTAL, "-1");
        final String idOrigem = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.ID_ORIGEM_REGISTRA_OCORRENCIA_PORTAL, "-1");
        if (!idCategoria.equals("-1")) {
            final CategoriaOcorrenciaService categoriaOcorrenciaService = (CategoriaOcorrenciaService) ServiceLocator.getInstance().getService(
                    CategoriaOcorrenciaService.class, null);
            final CategoriaOcorrenciaDTO categoriaOcorrenciaDTO = categoriaOcorrenciaService.restoreAll(Integer.parseInt(idCategoria));
            if (categoriaOcorrenciaDTO != null) {
                document.executeScript("preencheCampoCategoria('" + categoriaOcorrenciaDTO.getNome() + "', " + idCategoria + ");");
            }

        }
        if (!idOrigem.equals("-1")) {
            ;
        }
        {
            final OrigemOcorrenciaService origemOcorrenciaService = (OrigemOcorrenciaService) ServiceLocator.getInstance().getService(
                    OrigemOcorrenciaService.class, null);
            final OrigemOcorrenciaDTO origemOcorrenciaDTO = origemOcorrenciaService.restoreAll(Integer.parseInt(idOrigem));
            if (origemOcorrenciaDTO != null) {
                document.executeScript("preencheCampoOrigem('" + origemOcorrenciaDTO.getNome() + "', " + idOrigem + ");");
            }
        }
    }

}
