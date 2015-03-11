package br.com.centralit.citcorpore.ajaxForms;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.centralit.citajax.html.AjaxFormAction;
import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citajax.html.HTMLElement;
import br.com.centralit.citajax.html.HTMLForm;
import br.com.centralit.citajax.html.HTMLSelect;
import br.com.centralit.citcorpore.bean.EmpregadoDTO;
import br.com.centralit.citcorpore.bean.GrupoItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.ItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.ItemConfiguracaoTreeDTO;
import br.com.centralit.citcorpore.bean.MidiaSoftwareDTO;
import br.com.centralit.citcorpore.bean.ParametroCorporeDTO;
import br.com.centralit.citcorpore.bean.ProblemaDTO;
import br.com.centralit.citcorpore.bean.RequisicaoLiberacaoDTO;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaDTO;
import br.com.centralit.citcorpore.bean.RiscoDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.TipoItemConfiguracaoDTO;
import br.com.centralit.citcorpore.negocio.ContratoService;
import br.com.centralit.citcorpore.negocio.EmpregadoService;
import br.com.centralit.citcorpore.negocio.GrupoItemConfiguracaoService;
import br.com.centralit.citcorpore.negocio.ItemConfiguracaoService;
import br.com.centralit.citcorpore.negocio.MidiaSoftwareService;
import br.com.centralit.citcorpore.negocio.ParametroCorporeService;
import br.com.centralit.citcorpore.negocio.ProblemaService;
import br.com.centralit.citcorpore.negocio.RequisicaoLiberacaoService;
import br.com.centralit.citcorpore.negocio.RequisicaoMudancaService;
import br.com.centralit.citcorpore.negocio.RiscoService;
import br.com.centralit.citcorpore.negocio.SolicitacaoServicoService;
import br.com.centralit.citcorpore.negocio.TipoItemConfiguracaoService;
import br.com.centralit.citcorpore.negocio.UsuarioService;
import br.com.centralit.citcorpore.negocio.ValorService;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.centralit.citcorpore.util.WebUtil;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilI18N;

public class InformacoesItemConfiguracaoMudanca extends AjaxFormAction {

    @Override
    public void load(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        /* Combo de status */
        final HTMLSelect comboStatus = document.getSelectById("status");
        comboStatus.removeAllOptions();
        for (final Enumerados.StatusIC st : Enumerados.StatusIC.values()) {
            comboStatus.addOption(st.getItem().toString(), UtilI18N.internacionaliza(request, st.getChaveInternacionalizacao()));
        }

        /* Combo de criticidade */
        final HTMLSelect comboCriticiidade = document.getSelectById("criticidade");
        comboCriticiidade.removeAllOptions();
        for (final Enumerados.CriticidadeIC ct : Enumerados.CriticidadeIC.values()) {
            comboCriticiidade.addOption(ct.getItem().toString(), UtilI18N.internacionaliza(request, ct.getDescricao()));
        }

        // Combo Contrato
        document.getSelectById("idContrato").removeAllOptions();
        final ContratoService contratoService = (ContratoService) ServiceLocator.getInstance().getService(ContratoService.class, null);
        final Collection colContrato = contratoService.list();
        document.getSelectById("idContrato").addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
        document.getSelectById("idContrato").addOptions(colContrato, "idContrato", "numero", null);

        final HTMLSelect urgencia = document.getSelectById("urgencia");
        urgencia.removeAllOptions();
        urgencia.addOption("B", UtilI18N.internacionaliza(request, "citcorpore.comum.baixa"));
        urgencia.addOption("M", UtilI18N.internacionaliza(request, "citcorpore.comum.media"));
        urgencia.addOption("A", UtilI18N.internacionaliza(request, "citcorpore.comum.alta"));

        final HTMLSelect impacto = document.getSelectById("impacto");
        impacto.removeAllOptions();
        impacto.addOption("B", UtilI18N.internacionaliza(request, "citcorpore.comum.baixa"));
        impacto.addOption("M", UtilI18N.internacionaliza(request, "citcorpore.comum.media"));
        impacto.addOption("A", UtilI18N.internacionaliza(request, "citcorpore.comum.alta"));

        final ItemConfiguracaoService itemConfiguracaoService = (ItemConfiguracaoService) ServiceLocator.getInstance().getService(
                ItemConfiguracaoService.class, null);
        final ItemConfiguracaoTreeDTO itemConfiguracaoTreeDTO = (ItemConfiguracaoTreeDTO) document.getBean();
        ItemConfiguracaoDTO itemConfiguracaoDTO = new ItemConfiguracaoDTO();
        itemConfiguracaoDTO = itemConfiguracaoService.restoreByIdItemConfiguracao(itemConfiguracaoTreeDTO.getIdItemConfiguracao());
        ServiceLocator.getInstance().getService(UsuarioService.class, null);

        final StringBuilder subDiv = new StringBuilder();

        if (itemConfiguracaoDTO != null) {
            EmpregadoDTO empregadoBean = new EmpregadoDTO();
            final EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);
            if (itemConfiguracaoDTO.getIdProprietario() != null) {
                empregadoBean.setIdEmpregado(itemConfiguracaoDTO.getIdProprietario());
                empregadoBean = (EmpregadoDTO) empregadoService.restore(empregadoBean);
                if (empregadoBean != null) {
                    itemConfiguracaoDTO.setNomeUsuario(empregadoBean.getNome());
                }
            }
            if (itemConfiguracaoDTO.getIdResponsavel() != null) {
                empregadoBean.setIdEmpregado(itemConfiguracaoDTO.getIdResponsavel());
                empregadoBean = (EmpregadoDTO) empregadoService.restore(empregadoBean);
                if (empregadoBean != null) {
                    itemConfiguracaoDTO.setNomeResponsavel(empregadoBean.getNome());
                }
            }

            GrupoItemConfiguracaoDTO grupoItemConfiguracaoDTO = new GrupoItemConfiguracaoDTO();
            ServiceLocator.getInstance().getService(ParametroCorporeService.class, null);
            new ParametroCorporeDTO();

            if (itemConfiguracaoDTO.getIdGrupoItemConfiguracao() != null && itemConfiguracaoDTO.getIdGrupoItemConfiguracao() > 0) {
                grupoItemConfiguracaoDTO.setIdGrupoItemConfiguracao(itemConfiguracaoDTO.getIdGrupoItemConfiguracao());
                final GrupoItemConfiguracaoService grupoItemConfiguracaoService = (GrupoItemConfiguracaoService) ServiceLocator.getInstance().getService(
                        GrupoItemConfiguracaoService.class, null);
                grupoItemConfiguracaoDTO = (GrupoItemConfiguracaoDTO) grupoItemConfiguracaoService.restore(grupoItemConfiguracaoDTO);
                if (grupoItemConfiguracaoDTO != null) {
                    itemConfiguracaoDTO.setIdGrupoItemConfiguracao(grupoItemConfiguracaoDTO.getIdGrupoItemConfiguracao());
                    itemConfiguracaoDTO.setNomeGrupoItemConfiguracao(grupoItemConfiguracaoDTO.getNomeGrupoItemConfiguracao());
                }
            }

            if (itemConfiguracaoDTO.getIdItemConfiguracaoPai() != null) {
                ItemConfiguracaoDTO itemPai = new ItemConfiguracaoDTO();
                itemPai = itemConfiguracaoService.restoreByIdItemConfiguracao(itemConfiguracaoDTO.getIdItemConfiguracaoPai());

                GrupoItemConfiguracaoDTO grupoPai = new GrupoItemConfiguracaoDTO();
                if (itemPai.getIdGrupoItemConfiguracao() != null) {
                    grupoPai.setIdGrupoItemConfiguracao(itemPai.getIdGrupoItemConfiguracao());
                    final GrupoItemConfiguracaoService grupoItemConfiguracaoService = (GrupoItemConfiguracaoService) ServiceLocator.getInstance().getService(
                            GrupoItemConfiguracaoService.class, null);
                    grupoPai = (GrupoItemConfiguracaoDTO) grupoItemConfiguracaoService.restore(grupoPai);
                }

                /* Cabeçalho */
                subDiv.append("<div id='cabecalhoInf'>");
                subDiv.append("<h2>Item de Configura&ccedil;&atilde;o</h2>");
                subDiv.append("<b>" + UtilI18N.internacionaliza(request, "citcorpore.comum.identificacao") + ": </b>" + itemPai.getIdentificacao() + "");
                if (itemPai.getIdGrupoItemConfiguracao() != null && itemPai.getIdGrupoItemConfiguracao() > 0) {
                    if (grupoPai != null) {
                        subDiv.append("<b>" + UtilI18N.internacionaliza(request, "itemConfiguracaoTree.grupo") + ": </b>"
                                + grupoPai.getNomeGrupoItemConfiguracao());
                    }
                } else {

                    if (!ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.NOME_GRUPO_ITEM_CONFIG_NOVOS, " ").trim()
                            .equalsIgnoreCase("")) {
                        subDiv.append("<b>" + UtilI18N.internacionaliza(request, "itemConfiguracaoTree.grupo") + ": </b>"
                                + ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.NOME_GRUPO_ITEM_CONFIG_NOVOS, " "));
                    }
                }
                subDiv.append("</div>");

                // document.executeScript("$('#titleITem').text('"+UtilI18N.internacionaliza(request, "itemConfiguracaoTree.itemConfiguracaoRelacionado")+"')");
                document.executeScript("$('#principalInf').css('display','block')");
                // document.executeScript("$('#itemPai').css('display','block')");

            } else {
                document.executeScript("$('#titleITem').text('" + UtilI18N.internacionaliza(request, "itemConfiguracaoTree.itemConfiguracao") + "')");
                document.executeScript("$('#principalInf').css('display','none')");
                document.executeScript("$('#divGrupoItemConfiguracao').css('display','block')");

            }

            final TipoItemConfiguracaoService tipoItemService = (TipoItemConfiguracaoService) ServiceLocator.getInstance().getService(
                    TipoItemConfiguracaoService.class, null);
            final SolicitacaoServicoService solService = (SolicitacaoServicoService) ServiceLocator.getInstance().getService(SolicitacaoServicoService.class,
                    null);
            final RequisicaoMudancaService reqMudanca = (RequisicaoMudancaService) ServiceLocator.getInstance()
                    .getService(RequisicaoMudancaService.class, null);
            final ProblemaService problemaService = (ProblemaService) ServiceLocator.getInstance().getService(ProblemaService.class, null);
            ServiceLocator.getInstance().getService(ValorService.class, null);
            final MidiaSoftwareService midiaSoftwareService = (MidiaSoftwareService) ServiceLocator.getInstance().getService(MidiaSoftwareService.class, null);
            final RequisicaoLiberacaoService liberacaoService = (RequisicaoLiberacaoService) ServiceLocator.getInstance().getService(
                    RequisicaoLiberacaoService.class, null);
            /* Setando o form do item de configuração pai */
            final HTMLForm form = document.getForm("form");
            form.clear();

            if (itemConfiguracaoDTO.getIdTipoItemConfiguracao() != null && itemConfiguracaoDTO.getIdTipoItemConfiguracao().intValue() > 0) {
                TipoItemConfiguracaoDTO item = new TipoItemConfiguracaoDTO();
                item.setId(itemConfiguracaoDTO.getIdTipoItemConfiguracao());
                item = (TipoItemConfiguracaoDTO) tipoItemService.restore(item);
                if (item != null) {
                    itemConfiguracaoDTO.setNomeTipoItemConfiguracao(item.getNome());
                    // restoreTipoItemConfiguracaoValues(document, request, response);
                }
            }

            if (itemConfiguracaoDTO.getIdIncidente() != null) {
                SolicitacaoServicoDTO ss = new SolicitacaoServicoDTO();
                ss = solService.findByIdSolicitacaoServico(itemConfiguracaoDTO.getIdIncidente());
                if (ss != null) {
                    itemConfiguracaoDTO.setNumeroIncidente(ss.getIdSolicitacaoServico().toString() + " - " + ss.getNomeServico() + " - " + ss.getSituacao());
                }
            }

            if (itemConfiguracaoDTO.getIdMudanca() != null) {
                RequisicaoMudancaDTO rm = new RequisicaoMudancaDTO();
                rm.setIdRequisicaoMudanca(itemConfiguracaoDTO.getIdMudanca());
                rm = (RequisicaoMudancaDTO) reqMudanca.restore(rm);
                if (rm != null) {
                    itemConfiguracaoDTO.setNumeroMudanca(rm.getIdRequisicaoMudanca() + " - " + rm.getTitulo() + " - " + rm.getStatus());
                }
            }

            if (itemConfiguracaoDTO.getIdProblema() != null) {
                ProblemaDTO rm = new ProblemaDTO();
                rm.setIdProblema(itemConfiguracaoDTO.getIdProblema());
                rm = (ProblemaDTO) problemaService.restore(rm);
                if (rm != null) {
                    itemConfiguracaoDTO.setNumeroProblema(rm.getIdProblema() + " - " + rm.getTitulo() + " - " + rm.getStatus());
                }
            }
            if (itemConfiguracaoDTO.getIdMidiaSoftware() != null) {
                MidiaSoftwareDTO rm = new MidiaSoftwareDTO();
                rm.setIdMidiaSoftware(itemConfiguracaoDTO.getIdMidiaSoftware());
                rm = (MidiaSoftwareDTO) midiaSoftwareService.restore(rm);
                if (rm != null) {
                    itemConfiguracaoDTO.setNomeMidia(rm.getNome());
                }
            }
            if (itemConfiguracaoDTO.getIdLiberacao() != null) {
                RequisicaoLiberacaoDTO rm = new RequisicaoLiberacaoDTO();
                rm.setIdRequisicaoLiberacao(itemConfiguracaoDTO.getIdLiberacao());
                rm = (RequisicaoLiberacaoDTO) liberacaoService.restore(rm);
                if (rm != null) {
                    itemConfiguracaoDTO.setTituloLiberacao(rm.getTitulo());
                }
            }

            document.executeScript("event()");

            form.setValues(itemConfiguracaoDTO);
        }

        final HTMLElement divPrincipal = document.getElementById("principalInf");
        divPrincipal.setInnerHTML(subDiv.toString());

    }

    @Override
    public Class<ItemConfiguracaoTreeDTO> getBeanClass() {
        return ItemConfiguracaoTreeDTO.class;
    }

    public void save(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final RiscoDTO risco = (RiscoDTO) document.getBean();
        risco.setDataInicio(UtilDatas.getDataAtual());
        final RiscoService riscoService = (RiscoService) ServiceLocator.getInstance().getService(RiscoService.class, WebUtil.getUsuarioSistema(request));
        if (risco.getIdRisco() == null || risco.getIdRisco().intValue() == 0) {
            riscoService.create(risco);
            document.alert(UtilI18N.internacionaliza(request, "MSG05"));
        } else {
            riscoService.update(risco);
            document.alert(UtilI18N.internacionaliza(request, "MSG06"));
        }
        final HTMLForm form = document.getForm("form");
        form.clear();

        document.executeScript("limpar_LOOKUP_RISCO()");
    }

    public void restore(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        RiscoDTO risco = (RiscoDTO) document.getBean();
        final RiscoService riscoService = (RiscoService) ServiceLocator.getInstance().getService(RiscoService.class, WebUtil.getUsuarioSistema(request));

        risco = (RiscoDTO) riscoService.restore(risco);

        final HTMLForm form = document.getForm("form");
        form.clear();
        form.setValues(risco);

    }

    public void atualizaData(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final RiscoDTO risco = (RiscoDTO) document.getBean();
        final RiscoService riscoService = (RiscoService) ServiceLocator.getInstance().getService(RiscoService.class, null);
        if (risco.getIdRisco().intValue() > 0) {
            risco.setDataFim(UtilDatas.getDataAtual());

            riscoService.update(risco);

        }

        final HTMLForm form = document.getForm("form");
        form.clear();
        document.alert(UtilI18N.internacionaliza(request, "MSG07"));

        document.executeScript("limpar_LOOKUP_RISCO()");
    }

}
