package br.com.centralit.citcorpore.ajaxForms;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;
import org.apache.commons.lang3.math.NumberUtils;

import br.com.centralit.citajax.html.AjaxFormAction;
import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citajax.html.HTMLSelect;
import br.com.centralit.citcorpore.bean.AssinaturaAprovacaoProjetoDTO;
import br.com.centralit.citcorpore.bean.ClienteDTO;
import br.com.centralit.citcorpore.bean.ContratoDTO;
import br.com.centralit.citcorpore.bean.CronogramaDTO;
import br.com.centralit.citcorpore.bean.EmpregadoDTO;
import br.com.centralit.citcorpore.bean.FornecedorDTO;
import br.com.centralit.citcorpore.bean.LinhaBaseProjetoDTO;
import br.com.centralit.citcorpore.bean.MarcoPagamentoPrjDTO;
import br.com.centralit.citcorpore.bean.OSDTO;
import br.com.centralit.citcorpore.bean.PerfilContratoDTO;
import br.com.centralit.citcorpore.bean.ProdutoContratoDTO;
import br.com.centralit.citcorpore.bean.ProdutoTarefaLinBaseProjDTO;
import br.com.centralit.citcorpore.bean.ProjetoDTO;
import br.com.centralit.citcorpore.bean.RecursoProjetoDTO;
import br.com.centralit.citcorpore.bean.RecursoTarefaLinBaseProjDTO;
import br.com.centralit.citcorpore.bean.ServicoContratoDTO;
import br.com.centralit.citcorpore.bean.ServicoDTO;
import br.com.centralit.citcorpore.bean.TarefaLinhaBaseProjetoDTO;
import br.com.centralit.citcorpore.bean.TemplateImpressaoDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.metainfo.util.JSONUtil;
import br.com.centralit.citcorpore.negocio.AssinaturaAprovacaoProjetoService;
import br.com.centralit.citcorpore.negocio.ClienteService;
import br.com.centralit.citcorpore.negocio.ContratoService;
import br.com.centralit.citcorpore.negocio.EmpregadoService;
import br.com.centralit.citcorpore.negocio.FornecedorService;
import br.com.centralit.citcorpore.negocio.LinhaBaseProjetoService;
import br.com.centralit.citcorpore.negocio.MarcoPagamentoPrjService;
import br.com.centralit.citcorpore.negocio.OSService;
import br.com.centralit.citcorpore.negocio.PerfilContratoService;
import br.com.centralit.citcorpore.negocio.ProdutoContratoService;
import br.com.centralit.citcorpore.negocio.ProdutoTarefaLinBaseProjService;
import br.com.centralit.citcorpore.negocio.ProjetoService;
import br.com.centralit.citcorpore.negocio.RecursoProjetoService;
import br.com.centralit.citcorpore.negocio.RecursoTarefaLinBaseProjService;
import br.com.centralit.citcorpore.negocio.ServicoContratoService;
import br.com.centralit.citcorpore.negocio.ServicoService;
import br.com.centralit.citcorpore.negocio.TarefaLinhaBaseProjetoService;
import br.com.centralit.citcorpore.negocio.TemplateImpressaoService;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.centralit.citcorpore.util.Enumerados.TipoDate;
import br.com.centralit.citcorpore.util.WebUtil;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilFormatacao;
import br.com.citframework.util.UtilI18N;
import br.com.citframework.util.UtilStrings;
import br.com.citframework.util.UtilTratamentoArquivos;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public class Cronograma extends AjaxFormAction {

    @Override
    public void load(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(request);
        if (usuario == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoexpirada"));
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }
        final CronogramaDTO cronogramaDTO = (CronogramaDTO) document.getBean();
        final ProjetoService projetoService = (ProjetoService) ServiceLocator.getInstance().getService(ProjetoService.class, null);
        ServiceLocator.getInstance().getService(LinhaBaseProjetoService.class, null);
        ServiceLocator.getInstance().getService(TarefaLinhaBaseProjetoService.class, null);
        final Collection colProjetos = projetoService.list();

        final HTMLSelect idProjeto = document.getSelectById("idProjetoCombo");
        idProjeto.addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
        if (colProjetos != null) {
            idProjeto.addOptions(colProjetos, "idProjeto", "nomeProjeto", null);
        }
        if (cronogramaDTO.getIdProjeto() != null) {
            abreProjeto(document, request, response);
        }
    }

    @Override
    public Class getBeanClass() {
        return CronogramaDTO.class;
    }

    public void saveMarcosPag(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(request);
        if (usuario == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoexpirada"));
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }
        final CronogramaDTO cronogramaDTO = (CronogramaDTO) document.getBean();
        if (cronogramaDTO.getIdProjeto() == null) {
            document.alert(UtilI18N.internacionaliza(request, "pagamentoProjeto.informeProjeto"));
            return;
        }
        final MarcoPagamentoPrjService marcoPagamentoPrjService = (MarcoPagamentoPrjService) ServiceLocator.getInstance().getService(
                MarcoPagamentoPrjService.class, WebUtil.getUsuarioSistema(request));
        Map<String, Object> map = null;
        try {
            map = JSONUtil.convertJsonToMap(cronogramaDTO.getMarcosProjeto(), true);
            final Collection obj = (Collection) map.get("ARRAY");
            final Collection colMarcos = getMarcosPag(request, obj);
            if (colMarcos != null) {
                for (final Iterator it = colMarcos.iterator(); it.hasNext();) {
                    final MarcoPagamentoPrjDTO marcoPagamentoPrjDTO = (MarcoPagamentoPrjDTO) it.next();
                    marcoPagamentoPrjDTO.setIdProjeto(cronogramaDTO.getIdProjeto());
                    if (marcoPagamentoPrjDTO.getId() != null) {
                        try {
                            marcoPagamentoPrjDTO.setIdMarcoPagamentoPrj(marcoPagamentoPrjDTO.getId());
                        } catch (final Exception e) {}
                    }
                    if (marcoPagamentoPrjDTO.getNomeMarcoPag() == null || marcoPagamentoPrjDTO.getNomeMarcoPag().trim().equalsIgnoreCase("")) {
                        document.alert(UtilI18N.internacionaliza(request, "cronograma.marcoPagamento"));
                        return;
                    }
                    if (marcoPagamentoPrjDTO.getDataPrevisaoPag() == null) {
                        document.alert(UtilI18N.internacionaliza(request, "cronograma.faltaInformarPagamento") + marcoPagamentoPrjDTO.getNomeMarcoPag());
                        return;
                    }
                }
                marcoPagamentoPrjService.saveFromCollection(colMarcos, cronogramaDTO.getIdProjeto());
            }
            document.alert(UtilI18N.internacionaliza(request, "cronograma.cronogramaPagamentoSalvo"));
            document.executeScript("$(\"#__blackpopup__\").trigger(\"close\");");
        } catch (final Exception e) {
            System.out.println("Cronograma.save(): " + cronogramaDTO.getMarcosProjeto());
            e.printStackTrace();
            document.getJanelaPopupById("JANELA_AGUARDE_MENU").hide();
            throw e;
        }
        final Collection colMarcos = marcoPagamentoPrjService.findByIdProjeto(cronogramaDTO.getIdProjeto());
        String strMarcos = "";
        if (colMarcos != null) {
            for (final Iterator it = colMarcos.iterator(); it.hasNext();) {
                final MarcoPagamentoPrjDTO marcoPagamentoPrjDTO = (MarcoPagamentoPrjDTO) it.next();
                String nomeMarco = marcoPagamentoPrjDTO.getNomeMarcoPag();
                nomeMarco = nomeMarco.replaceAll("\'", "");
                nomeMarco = nomeMarco.replaceAll("\"", "");
                final String dataPagStr = UtilDatas.convertDateToString(TipoDate.DATE_DEFAULT, marcoPagamentoPrjDTO.getDataPrevisaoPag(),
                        WebUtil.getLanguage(request));
                if (!strMarcos.trim().equalsIgnoreCase("")) {
                    strMarcos += ",";
                }
                strMarcos += "{id:\"" + marcoPagamentoPrjDTO.getIdMarcoPagamentoPrj() + "\", nomeMarcoPag:\"" + nomeMarco + "\", dataPrevisaoPag:\""
                        + dataPagStr + "\"}";
            }
        }
        document.executeScript("geraMarcos('" + strMarcos + "')");
    }

    public void save(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(request);
        if (usuario == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoexpirada"));
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }
        final CronogramaDTO cronogramaDTO = (CronogramaDTO) document.getBean();
        final LinhaBaseProjetoService linhaBaseProjetoService = (LinhaBaseProjetoService) ServiceLocator.getInstance().getService(
                LinhaBaseProjetoService.class, WebUtil.getUsuarioSistema(request));

        try {
            final Map<String, Object> map = JSONUtil.convertJsonToMap(cronogramaDTO.getTa(), true);
            final Collection obj = (Collection) map.get("TASKS");
            final Collection<TarefaLinhaBaseProjetoDTO> colTarefas = getTarefas(obj, request);

            for (final TarefaLinhaBaseProjetoDTO tarefa : colTarefas) {
                final ArrayList<RecursoTarefaLinBaseProjDTO> recursos = (ArrayList<RecursoTarefaLinBaseProjDTO>) tarefa.getColRecursos();
                for (int i = 0; i < recursos.size(); i++) {
                    if (recursos.get(i).getEsforcoPorOS().equalsIgnoreCase("")) {
                        recursos.get(i).setEsforcoPorOS("0");
                    }
                    if (recursos.get(i).getTempoAloc() != null && recursos.get(i).getTempoAloc().equalsIgnoreCase("00:00")) {
                        if (!recursos.get(i).getEsforcoPorOS().equalsIgnoreCase("0")) {
                            try {
                                Double.parseDouble(recursos.get(i).getEsforcoPorOS());
                            } catch (final Exception e) {
                                document.alert(UtilI18N.internacionaliza(request, "cronograma.erro"));
                                e.printStackTrace();
                                return;
                            }
                            recursos.get(i).setTempoAlocMinutos(Double.parseDouble(recursos.get(i).getEsforcoPorOS()) * 60);
                        }
                    }
                }
            }

            final java.sql.Date data = UtilDatas.getDataAtual();
            final String hora = UtilDatas.getHoraHHMM(UtilDatas.getDataHoraAtual()).replaceAll(":", "");
            final LinhaBaseProjetoDTO linhaBaseProjetoDTO = new LinhaBaseProjetoDTO();
            linhaBaseProjetoDTO.setIdProjeto(cronogramaDTO.getIdProjeto());
            linhaBaseProjetoDTO.setDataLinhaBase(data);
            linhaBaseProjetoDTO.setDataUltAlteracao(data);
            linhaBaseProjetoDTO.setHoraLinhaBase(hora);
            linhaBaseProjetoDTO.setHoraUltAlteracao(hora);
            linhaBaseProjetoDTO.setUsuarioUltAlteracao(usuario.getNomeUsuario());
            linhaBaseProjetoDTO.setSituacao(cronogramaDTO.getSituacaoLinhaBaseProjetoSelecionada());
            if (linhaBaseProjetoDTO.getSituacao() == null) {
                linhaBaseProjetoDTO.setSituacao(LinhaBaseProjetoDTO.ATIVO);
            }
            linhaBaseProjetoDTO.setColTarefas(colTarefas);
            if (cronogramaDTO.getIdLinhaBaseProjeto() != null && cronogramaDTO.getIdLinhaBaseProjeto().intValue() > 0) {
                boolean criacao = false;
                if (UtilStrings.nullToVazio(cronogramaDTO.getNovaLinhaBaseProjeto()).equalsIgnoreCase("S")) {
                    criacao = true;
                } else {
                    LinhaBaseProjetoDTO linhaBaseProjetoAux = new LinhaBaseProjetoDTO();
                    linhaBaseProjetoAux.setIdLinhaBaseProjeto(cronogramaDTO.getIdLinhaBaseProjeto());
                    linhaBaseProjetoAux = (LinhaBaseProjetoDTO) linhaBaseProjetoService.restore(linhaBaseProjetoAux);
                    if (linhaBaseProjetoAux != null) {
                        if (!linhaBaseProjetoAux.getSituacao().equalsIgnoreCase(LinhaBaseProjetoDTO.ATIVO)) { // SE ESTIVER DIFERENTE DE ATIVO, OU SEJA, JA
                            // ESTIVER INICIADO... ENTAO, CRIA.
                            criacao = true;
                        }
                    }
                }
                // -----
                if (criacao) {
                    linhaBaseProjetoDTO.setIdLinhaBaseProjetoUpdate(cronogramaDTO.getIdLinhaBaseProjeto());
                    linhaBaseProjetoService.create(linhaBaseProjetoDTO);
                } else {
                    linhaBaseProjetoDTO.setDataLinhaBase(null); // Deve-se setar nulo neste campo para nao ser atualizado dentro do UPDATE do service
                    linhaBaseProjetoDTO.setHoraLinhaBase(null); // Deve-se setar nulo neste campo para nao ser atualizado dentro do UPDATE do service
                    linhaBaseProjetoDTO.setIdLinhaBaseProjeto(cronogramaDTO.getIdLinhaBaseProjeto());
                    linhaBaseProjetoService.update(linhaBaseProjetoDTO);
                }
            } else {
                linhaBaseProjetoService.create(linhaBaseProjetoDTO);
            }
            document.alert(UtilI18N.internacionaliza(request, "cronograma.cronogramaGravadoSucesso"));
            document.executeScript("$(\"#__blackpopup__\").trigger(\"close\");");
            abreProjeto(document, request, response);
        } catch (final Exception e) {
            System.out.println("Cronograma.save(): " + cronogramaDTO.getTa());
            e.printStackTrace();
            document.getJanelaPopupById("JANELA_AGUARDE_MENU").hide();
            throw e;
        }
        // System.out.println(map);
    }

    public void abreProjeto(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(request);
        if (usuario == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoexpirada"));
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }
        final CronogramaDTO cronogramaDTO = (CronogramaDTO) document.getBean();
        if (cronogramaDTO.getIdProjeto() == null || cronogramaDTO.getIdProjeto().intValue() == 0) {
            return;
        }
        document.executeScript("ge.reset()");
        document.getElementById("podeModificar").setValue("S");

        final LinhaBaseProjetoService linhaBaseProjetoService = (LinhaBaseProjetoService) ServiceLocator.getInstance().getService(
                LinhaBaseProjetoService.class, null);
        final ProjetoService projetoService = (ProjetoService) ServiceLocator.getInstance().getService(ProjetoService.class, null);
        final TarefaLinhaBaseProjetoService tarefaLinhaBaseProjetoService = (TarefaLinhaBaseProjetoService) ServiceLocator.getInstance().getService(
                TarefaLinhaBaseProjetoService.class, null);
        final PerfilContratoService perfilContratoService = (PerfilContratoService) ServiceLocator.getInstance().getService(PerfilContratoService.class, null);
        final RecursoProjetoService recursoProjetoService = (RecursoProjetoService) ServiceLocator.getInstance().getService(RecursoProjetoService.class, null);
        final EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);
        final ProdutoContratoService produtoContratoService = (ProdutoContratoService) ServiceLocator.getInstance().getService(ProdutoContratoService.class,
                null);
        final RecursoTarefaLinBaseProjService recursoTarefaLinBaseProjService = (RecursoTarefaLinBaseProjService) ServiceLocator.getInstance().getService(
                RecursoTarefaLinBaseProjService.class, null);
        final ProdutoTarefaLinBaseProjService produtoTarefaLinBaseProjService = (ProdutoTarefaLinBaseProjService) ServiceLocator.getInstance().getService(
                ProdutoTarefaLinBaseProjService.class, null);
        final MarcoPagamentoPrjService marcoPagamentoPrjService = (MarcoPagamentoPrjService) ServiceLocator.getInstance().getService(
                MarcoPagamentoPrjService.class, WebUtil.getUsuarioSistema(request));

        ProjetoDTO projetoDto = new ProjetoDTO();
        projetoDto.setIdProjeto(cronogramaDTO.getIdProjeto());
        projetoDto = (ProjetoDTO) projetoService.restore(projetoDto);
        String strPerfis = "";
        if (projetoDto != null && projetoDto.getIdContrato() != null) {
            final Collection colPerfis = perfilContratoService.findByIdContrato(projetoDto.getIdContrato());
            if (colPerfis != null) {
                for (final Iterator it = colPerfis.iterator(); it.hasNext();) {
                    final PerfilContratoDTO perfilContratoDTO = (PerfilContratoDTO) it.next();
                    if (perfilContratoDTO.getDeleted() != null && perfilContratoDTO.getDeleted().equalsIgnoreCase("Y")) {
                        continue;
                    }
                    final String nomePerfil = UtilStrings.nullToVazio(perfilContratoDTO.getNomePerfilContrato()).replaceAll("\'", "").replaceAll("\"", "");
                    if (!strPerfis.trim().equalsIgnoreCase("")) {
                        strPerfis += ",";
                    }
                    strPerfis += "{id:\"" + perfilContratoDTO.getIdPerfilContrato() + "\", name:\"" + nomePerfil + "\"}";
                }
            }
        }
        String strProducts = "";
        if (projetoDto != null && projetoDto.getIdContrato() != null) {
            final Collection colProds = produtoContratoService.findByIdContrato(projetoDto.getIdContrato());
            if (colProds != null) {
                for (final Iterator it = colProds.iterator(); it.hasNext();) {
                    final ProdutoContratoDTO produtoContratoDTO = (ProdutoContratoDTO) it.next();
                    if (produtoContratoDTO.getDeleted() != null && produtoContratoDTO.getDeleted().equalsIgnoreCase("Y")) {
                        continue;
                    }
                    final String nomeProduto = UtilStrings.nullToVazio(produtoContratoDTO.getNomeProduto()).replaceAll("\'", "").replaceAll("\"", "");
                    if (!strProducts.trim().equalsIgnoreCase("")) {
                        strProducts += ",";
                    }
                    strProducts += "{id:\"" + produtoContratoDTO.getIdProdutoContrato() + "\", name:\"" + nomeProduto + "\"}";
                }
            }
        }
        // document.executeScript("setPerfis('" + strPerfis + "')");
        final Collection colLinhasBase = linhaBaseProjetoService.findByIdProjeto(cronogramaDTO.getIdProjeto());
        LinhaBaseProjetoDTO linhaBaseProjetoDTO = null;
        if (colLinhasBase != null) {
            for (final Iterator it = colLinhasBase.iterator(); it.hasNext();) {
                linhaBaseProjetoDTO = (LinhaBaseProjetoDTO) it.next();
                break;
            }
        }
        final Collection colRecursos = recursoProjetoService.findByIdProjeto(cronogramaDTO.getIdProjeto());
        String strRecursos = "";
        if (colRecursos != null) {
            for (final Iterator it = colRecursos.iterator(); it.hasNext();) {
                final RecursoProjetoDTO recursoProjetoDTO = (RecursoProjetoDTO) it.next();
                EmpregadoDTO empregadoDTO = new EmpregadoDTO();
                empregadoDTO.setIdEmpregado(recursoProjetoDTO.getIdEmpregado());
                empregadoDTO = (EmpregadoDTO) empregadoService.restore(empregadoDTO);
                if (empregadoDTO != null) {
                    final String nomeAux = UtilStrings.nullToVazio(empregadoDTO.getNome()).replaceAll("\'", "").replaceAll("\"", "");
                    if (!strRecursos.trim().equalsIgnoreCase("")) {
                        strRecursos += ",";
                    }
                    strRecursos += "{id:\"" + recursoProjetoDTO.getIdEmpregado() + "\", name:\"" + nomeAux + "\"}";
                }
            }
        }
        final Collection colMarcos = marcoPagamentoPrjService.findByIdProjeto(cronogramaDTO.getIdProjeto());
        String strMarcos = "";
        if (colMarcos != null) {
            for (final Iterator it = colMarcos.iterator(); it.hasNext();) {
                final MarcoPagamentoPrjDTO marcoPagamentoPrjDTO = (MarcoPagamentoPrjDTO) it.next();
                String nomeMarco = marcoPagamentoPrjDTO.getNomeMarcoPag();
                nomeMarco = nomeMarco.replaceAll("\'", "");
                nomeMarco = nomeMarco.replaceAll("\"", "");
                final String dataPagStr = UtilDatas.convertDateToString(TipoDate.DATE_DEFAULT, marcoPagamentoPrjDTO.getDataPrevisaoPag(),
                        WebUtil.getLanguage(request));
                if (!strMarcos.trim().equalsIgnoreCase("")) {
                    strMarcos += ",";
                }
                strMarcos += "{id:\"0" + marcoPagamentoPrjDTO.getIdMarcoPagamentoPrj() + "\", nomeMarcoPag:\"" + nomeMarco + "\", dataPrevisaoPag:\""
                        + dataPagStr + "\"}";
            }
        }
        document.executeScript("geraMarcos('" + strMarcos + "')");
        document.getTextAreaById("ta").setValue("");

        final StringBuilder strBuffer = new StringBuilder();
        if (linhaBaseProjetoDTO != null) {
            document.getElementById("idLinhaBaseProjeto").setValue("" + linhaBaseProjetoDTO.getIdLinhaBaseProjeto());
            document.getElementById("situacaoLinhaBaseProjeto").setValue("" + linhaBaseProjetoDTO.getSituacao());
            final Collection colTarefasLnBase = tarefaLinhaBaseProjetoService.findByIdLinhaBaseProjeto(linhaBaseProjetoDTO.getIdLinhaBaseProjeto());
            strBuffer.append("{\"tasks\":[");
            if (colTarefasLnBase != null) {
                int i = 0;
                for (final Iterator it = colTarefasLnBase.iterator(); it.hasNext();) {
                    final TarefaLinhaBaseProjetoDTO tarefaLinhaBaseProjetoDTO = (TarefaLinhaBaseProjetoDTO) it.next();
                    String nome = tarefaLinhaBaseProjetoDTO.getNomeTarefa();
                    String idMarcoPagamentoPrjStr = "0";
                    nome = UtilStrings.nullToVazio(nome).replaceAll("\"", "");
                    nome = nome.replaceAll("\'", "");
                    String code = tarefaLinhaBaseProjetoDTO.getCodeTarefa();
                    code = UtilStrings.nullToVazio(code).replaceAll("\"", "");
                    code = code.replaceAll("\'", "");
                    final String description = tarefaLinhaBaseProjetoDTO.getDetalhamentoTarefa();
                    String depends = "";
                    int nivel = 0;
                    long start = 0;
                    long end = 0;
                    double duracao = 0;
                    double progresso = 0;
                    if (tarefaLinhaBaseProjetoDTO.getNivel() != null) {
                        nivel = tarefaLinhaBaseProjetoDTO.getNivel();
                    }
                    if (tarefaLinhaBaseProjetoDTO.getIdMarcoPagamentoPrj() != null) {
                        idMarcoPagamentoPrjStr = "0" + tarefaLinhaBaseProjetoDTO.getIdMarcoPagamentoPrj();
                    }
                    String status = "STATUS_ACTIVE";
                    if (tarefaLinhaBaseProjetoDTO.getSituacao().trim().equalsIgnoreCase(TarefaLinhaBaseProjetoDTO.ATIVO)) {
                        status = "STATUS_ACTIVE";
                    }
                    if (tarefaLinhaBaseProjetoDTO.getSituacao().trim().equalsIgnoreCase(TarefaLinhaBaseProjetoDTO.PRONTO)) {
                        status = "STATUS_DONE";
                    }
                    if (tarefaLinhaBaseProjetoDTO.getSituacao().trim().equalsIgnoreCase(TarefaLinhaBaseProjetoDTO.FALHA)) {
                        status = "STATUS_FAILED";
                    }
                    if (tarefaLinhaBaseProjetoDTO.getSituacao().trim().equalsIgnoreCase(TarefaLinhaBaseProjetoDTO.SUSPENSA)) {
                        status = "STATUS_SUSPENDED";
                    }
                    if (tarefaLinhaBaseProjetoDTO.getSituacao().trim().equalsIgnoreCase(TarefaLinhaBaseProjetoDTO.SEMDEFINICAO)) {
                        status = "STATUS_UNDEFINED";
                    }
                    if (tarefaLinhaBaseProjetoDTO.getDataInicio() != null) {
                        start = tarefaLinhaBaseProjetoDTO.getDataInicio().getTime();
                    }
                    if (tarefaLinhaBaseProjetoDTO.getDataFim() != null) {
                        end = tarefaLinhaBaseProjetoDTO.getDataFim().getTime();
                    }
                    if (tarefaLinhaBaseProjetoDTO.getDuracaoHora() != null) {
                        end = tarefaLinhaBaseProjetoDTO.getDataFim().getTime();
                    }
                    if (tarefaLinhaBaseProjetoDTO.getDuracaoHora() != null) {
                        duracao = tarefaLinhaBaseProjetoDTO.getDuracaoHora();
                    }
                    if (tarefaLinhaBaseProjetoDTO.getProgresso() != null) {
                        progresso = tarefaLinhaBaseProjetoDTO.getProgresso();
                    }
                    if (tarefaLinhaBaseProjetoDTO.getDepends() != null && !tarefaLinhaBaseProjetoDTO.getDepends().equalsIgnoreCase("")) {
                        depends = tarefaLinhaBaseProjetoDTO.getDepends();
                    } else {
                        depends = "0:0";
                    }
                    /*
                     * if(objDepends != null){
                     * depends = objDepends;
                     * }
                     */

                    final Collection colRecsVinc = recursoTarefaLinBaseProjService.findByIdTarefaLinhaBaseProjeto(tarefaLinhaBaseProjetoDTO
                            .getIdTarefaLinhaBaseProjeto());
                    String strRecsVinc = "";
                    if (colRecsVinc != null) {
                        for (final Iterator itRec = colRecsVinc.iterator(); itRec.hasNext();) {
                            final RecursoTarefaLinBaseProjDTO recursoTarefaLinBaseProjDTO = (RecursoTarefaLinBaseProjDTO) itRec.next();
                            if (!strRecsVinc.trim().equalsIgnoreCase("")) {
                                strRecsVinc += ",";
                            }
                            strRecsVinc += "{\"id\":\"" + recursoTarefaLinBaseProjDTO.getIdRecursoTarefaLinBaseProj() + "\",";
                            strRecsVinc += "\"resourceId\":\"" + recursoTarefaLinBaseProjDTO.getIdEmpregado() + "\",";
                            strRecsVinc += "\"roleId\":\"" + recursoTarefaLinBaseProjDTO.getIdPerfilContrato() + "\",";
                            strRecsVinc += "\"effort\":" + geraTempoMiliSegundos(recursoTarefaLinBaseProjDTO.getTempoAloc()) + ",";
                            strRecsVinc += "\"esforcoPorOS\":" + recursoTarefaLinBaseProjDTO.getEsforcoPorOS() + "}";
                        }
                    }
                    final Collection colProdsVinc = produtoTarefaLinBaseProjService.findByIdTarefaLinhaBaseProjeto(tarefaLinhaBaseProjetoDTO
                            .getIdTarefaLinhaBaseProjeto());
                    String strProdsVinc = "";
                    if (colProdsVinc != null) {
                        for (final Iterator itRec = colProdsVinc.iterator(); itRec.hasNext();) {
                            final ProdutoTarefaLinBaseProjDTO produtoTarefaLinBaseProjDTO = (ProdutoTarefaLinBaseProjDTO) itRec.next();
                            if (!strProdsVinc.trim().equalsIgnoreCase("")) {
                                strProdsVinc += ",";
                            }
                            strProdsVinc += "{\"id\":\"" + produtoTarefaLinBaseProjDTO.getIdTarefaLinhaBaseProjeto() + "_"
                                    + produtoTarefaLinBaseProjDTO.getIdProdutoContrato() + "\",\"productId\":\""
                                    + produtoTarefaLinBaseProjDTO.getIdProdutoContrato() + "\"}";
                        }
                    }

                    if (i > 0) {
                        strBuffer.append(",");
                    }

                    strBuffer.append("{\"id\":" + tarefaLinhaBaseProjetoDTO.getIdTarefaLinhaBaseProjeto() + ",\"name\":\"" + nome + "\",\"code\":\"" + code
                            + "\",\"description\":\"" + description + "\",\"level\":" + nivel + "," + "\"status\":\"" + status + "\",\"start\":" + start
                            + ",\"duration\":" + duracao + ",\"progress\":" + progresso + ",\"end\":" + end
                            + ",\"startIsMilestone\":true,\"endIsMilestone\":false," + "\"depends\":\"" + depends + "\",\"idMarcoPagamentoPrj\":\""
                            + idMarcoPagamentoPrjStr + "\"," + "\"assigs\":[" + strRecsVinc + "],\"prods\":[" + strProdsVinc + "]}");
                    i++;
                }
            }
            strBuffer.append("],\"selectedRow\":0,\"deletedTaskIds\":[],\"canWrite\":true,\"canWriteOnParent\":true }");
            document.getTextAreaById("ta").setValue(strBuffer.toString());
        }
        document.executeScript("loadProjectFromTextArea('" + strPerfis + "','" + strRecursos + "','" + strProducts + "')");
        if (linhaBaseProjetoDTO != null) {
            if (linhaBaseProjetoDTO.getSituacao().equalsIgnoreCase("E")) {
                if (linhaBaseProjetoDTO.getJustificativaMudanca() == null || linhaBaseProjetoDTO.getJustificativaMudanca().trim().equalsIgnoreCase("")) {
                    document.getElementById("podeModificar").setValue("N");
                    document.alert(UtilI18N.internacionaliza(request, "cronograma.linhaEmExecucao"));
                }
            }
        }
    }

    private long geraTempoMiliSegundos(final String str) {
        if (str == null) {
            return 0;
        }
        try {
            long l = Long.parseLong(str);
            l = l * 36000;
            return l;
        } catch (final Exception e) {
            return 0;
        }
    }

    public void carregaTemplatesPrintProjeto(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(request);
        if (usuario == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoexpirada"));
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }
        final TemplateImpressaoService templateImpressaoService = (TemplateImpressaoService) ServiceLocator.getInstance().getService(
                TemplateImpressaoService.class, null);
        document.getBean();
        final Collection colTemplates = templateImpressaoService.list();
        final HTMLSelect idTemplate = document.getSelectById("idTemplate");
        if (colTemplates != null) {
            for (final Iterator it = colTemplates.iterator(); it.hasNext();) {
                final TemplateImpressaoDTO templateImpressaoDTO = (TemplateImpressaoDTO) it.next();
                final String nome = UtilStrings.nullToVazio(templateImpressaoDTO.getNomeTemplate()).replaceAll("\'", "");
                idTemplate.addOption("" + templateImpressaoDTO.getIdTemplateImpressao(), nome);
            }
        }
    }

    public void printProjeto(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(request);
        if (usuario == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoexpirada"));
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }
        final CronogramaDTO cronogramaDTO = (CronogramaDTO) document.getBean();
        if (cronogramaDTO.getIdProjeto() == null || cronogramaDTO.getIdProjeto().intValue() == 0) {
            return;
        }
        if (cronogramaDTO.getIdTemplateImpressao() == null || cronogramaDTO.getIdTemplateImpressao().intValue() == 0) {
            document.alert(UtilI18N.internacionaliza(request, "cronograma.informeTemplateImpressao"));
            return;
        }
        final LinhaBaseProjetoService linhaBaseProjetoService = (LinhaBaseProjetoService) ServiceLocator.getInstance().getService(
                LinhaBaseProjetoService.class, null);
        final ProjetoService projetoService = (ProjetoService) ServiceLocator.getInstance().getService(ProjetoService.class, null);
        final TarefaLinhaBaseProjetoService tarefaLinhaBaseProjetoService = (TarefaLinhaBaseProjetoService) ServiceLocator.getInstance().getService(
                TarefaLinhaBaseProjetoService.class, null);
        final PerfilContratoService perfilContratoService = (PerfilContratoService) ServiceLocator.getInstance().getService(PerfilContratoService.class, null);
        final AssinaturaAprovacaoProjetoService assinaturaService = (AssinaturaAprovacaoProjetoService) ServiceLocator.getInstance().getService(
                AssinaturaAprovacaoProjetoService.class, null);
        final RecursoProjetoService recursoProjetoService = (RecursoProjetoService) ServiceLocator.getInstance().getService(RecursoProjetoService.class, null);
        final EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);
        final ProdutoContratoService produtoContratoService = (ProdutoContratoService) ServiceLocator.getInstance().getService(ProdutoContratoService.class,
                null);
        final RecursoTarefaLinBaseProjService recursoTarefaLinBaseProjService = (RecursoTarefaLinBaseProjService) ServiceLocator.getInstance().getService(
                RecursoTarefaLinBaseProjService.class, null);
        final ProdutoTarefaLinBaseProjService produtoTarefaLinBaseProjService = (ProdutoTarefaLinBaseProjService) ServiceLocator.getInstance().getService(
                ProdutoTarefaLinBaseProjService.class, null);
        final OSService osService = (OSService) ServiceLocator.getInstance().getService(OSService.class, null);
        final ContratoService contratoService = (ContratoService) ServiceLocator.getInstance().getService(ContratoService.class, null);
        final FornecedorService fornecedorService = (FornecedorService) ServiceLocator.getInstance().getService(FornecedorService.class, null);
        final ClienteService clienteService = (ClienteService) ServiceLocator.getInstance().getService(ClienteService.class, null);
        final ServicoContratoService servicoContratoService = (ServicoContratoService) ServiceLocator.getInstance().getService(ServicoContratoService.class,
                null);
        final ServicoService servicoService = (ServicoService) ServiceLocator.getInstance().getService(ServicoService.class, null);
        final TemplateImpressaoService templateImpressaoService = (TemplateImpressaoService) ServiceLocator.getInstance().getService(
                TemplateImpressaoService.class, null);
        final MarcoPagamentoPrjService marcoPagamentoPrjService = (MarcoPagamentoPrjService) ServiceLocator.getInstance().getService(
                MarcoPagamentoPrjService.class, null);

        TemplateImpressaoDTO templateImpressaoDTO = new TemplateImpressaoDTO();
        templateImpressaoDTO.setIdTemplateImpressao(cronogramaDTO.getIdTemplateImpressao());
        templateImpressaoDTO = (TemplateImpressaoDTO) templateImpressaoService.restore(templateImpressaoDTO);
        if (templateImpressaoDTO == null) {
            document.alert(UtilI18N.internacionaliza(request, "cronograma.naoEncontrouTemplate"));
            return;
        }

        ProjetoDTO projetoDto = new ProjetoDTO();
        projetoDto.setIdProjeto(cronogramaDTO.getIdProjeto());
        projetoDto = (ProjetoDTO) projetoService.restore(projetoDto);
        OSDTO osDto = null;
        if (projetoDto != null && projetoDto.getIdOs() != null) {
            osDto = new OSDTO();
            osDto.setIdOS(projetoDto.getIdOs());
            osDto = (OSDTO) osService.restore(osDto);
            if (osDto != null) {
                projetoDto.setNomeAreaRequisitante(osDto.getNomeAreaRequisitante());
                projetoDto.setAno(osDto.getAno());
                projetoDto.setNumero(osDto.getNumero());
                projetoDto.setDemanda(osDto.getDemanda());
                projetoDto.setObjetivo(osDto.getObjetivo());
                projetoDto.setDataEmissao(osDto.getDataEmissao());

                ServicoContratoDTO servicoContratoDTO = new ServicoContratoDTO();
                servicoContratoDTO.setIdServicoContrato(osDto.getIdServicoContrato());
                servicoContratoDTO = (ServicoContratoDTO) servicoContratoService.restore(servicoContratoDTO);
                if (servicoContratoDTO != null) {
                    ServicoDTO servicoDTO = new ServicoDTO();
                    servicoDTO.setIdServico(servicoContratoDTO.getIdServico());
                    servicoDTO = (ServicoDTO) servicoService.restore(servicoDTO);
                    if (servicoDTO != null) {
                        projetoDto.setNomeServicoOS(servicoDTO.getNomeServico());
                    }
                }
            }
        }
        if (projetoDto.getAno() == null) {
            projetoDto.setAno(new Integer(0));
        }
        if (projetoDto.getNumero() == null) {
            projetoDto.setNumero("");
        }
        if (projetoDto.getNomeAreaRequisitante() == null) {
            projetoDto.setNomeAreaRequisitante("");
        }
        if (projetoDto.getDemanda() == null) {
            projetoDto.setDemanda("");
        }
        if (projetoDto.getObjetivo() == null) {
            projetoDto.setObjetivo("");
        }
        if (projetoDto.getNomeServicoOS() == null) {
            projetoDto.setNomeServicoOS("");
        }
        ContratoDTO contratoDto = new ContratoDTO();
        FornecedorDTO fornecedorDTO = new FornecedorDTO();
        ClienteDTO clienteDto = new ClienteDTO();
        Collection<PerfilContratoDTO> colPerfis = null;
        if (projetoDto != null && projetoDto.getIdContrato() != null) {
            colPerfis = perfilContratoService.findByIdContrato(projetoDto.getIdContrato());
            contratoDto.setIdContrato(projetoDto.getIdContrato());
            contratoDto = (ContratoDTO) contratoService.restore(contratoDto);
            if (contratoDto != null) {
                fornecedorDTO.setIdFornecedor(contratoDto.getIdFornecedor());
                fornecedorDTO = (FornecedorDTO) fornecedorService.restore(fornecedorDTO);
                //
                clienteDto.setIdCliente(contratoDto.getIdCliente());
                clienteDto = (ClienteDTO) clienteService.restore(clienteDto);
            }
        }

        if (colPerfis == null) {
            document.alert(UtilI18N.internacionaliza(request, "cronograma.definaPerfil"));
            return;
        }

        if (colPerfis != null && colPerfis.isEmpty()) {
            document.alert(UtilI18N.internacionaliza(request, "cronograma.definaPerfil"));
            return;
        }

        Boolean possuiPerfilValido = false;
        for (final PerfilContratoDTO perfil : colPerfis) {
            if (perfil.getDeleted() == null) {
                possuiPerfilValido = true;
            }
        }

        if (!possuiPerfilValido) {
            document.alert(UtilI18N.internacionaliza(request, "cronograma.definaPerfil"));
            return;
        }

        Collection colProds = null;
        if (projetoDto != null && projetoDto.getIdContrato() != null) {
            colProds = produtoContratoService.findByIdContrato(projetoDto.getIdContrato());
        }
        final Collection colLinhasBase = linhaBaseProjetoService.findByIdProjeto(cronogramaDTO.getIdProjeto());
        LinhaBaseProjetoDTO linhaBaseProjetoDTO = null;
        if (colLinhasBase != null) {
            for (final Iterator it = colLinhasBase.iterator(); it.hasNext();) {
                linhaBaseProjetoDTO = (LinhaBaseProjetoDTO) it.next();
                break;
            }
        }
        final Collection colRecursos = recursoProjetoService.findByIdProjeto(cronogramaDTO.getIdProjeto());
        if (colRecursos != null) {
            for (final Iterator it = colRecursos.iterator(); it.hasNext();) {
                final RecursoProjetoDTO recursoProjetoDTO = (RecursoProjetoDTO) it.next();
                EmpregadoDTO empregadoDTO = new EmpregadoDTO();
                empregadoDTO.setIdEmpregado(recursoProjetoDTO.getIdEmpregado());
                empregadoDTO = (EmpregadoDTO) empregadoService.restore(empregadoDTO);
                recursoProjetoDTO.setEmpregadoDTO(empregadoDTO);
            }
        }

        if (linhaBaseProjetoDTO == null) {
            document.alert(UtilI18N.internacionaliza(request, "cronograma.cronogramaVazio"));
            return;
        }

        final Collection<PerfilContratoDTO> colPerfisValoresProjeto = perfilContratoService.getTotaisByIdLinhaBaseProjeto(linhaBaseProjetoDTO
                .getIdLinhaBaseProjeto());

        final Collection<AssinaturaAprovacaoProjetoDTO> colAssinaturas = assinaturaService.findByIdProjeto(cronogramaDTO.getIdProjeto());
        if (colAssinaturas != null) {
            for (final AssinaturaAprovacaoProjetoDTO assinatura : colAssinaturas) {
                EmpregadoDTO empregadoDTO = new EmpregadoDTO();
                empregadoDTO.setIdEmpregado(assinatura.getIdEmpregadoAssinatura());
                empregadoDTO = (EmpregadoDTO) empregadoService.restore(empregadoDTO);
                assinatura.setNome(empregadoDTO.getNome());
                if (empregadoDTO.getNit() != null && !empregadoDTO.getNit().isEmpty()) {
                    assinatura.setMatricula("Matr&iacute;cula: " + empregadoDTO.getNit());
                } else {
                    assinatura.setMatricula(" ");
                }
            }
            Collections.sort((List<AssinaturaAprovacaoProjetoDTO>) colAssinaturas);
        }

        Double totalTempoAloc = new Double(0);
        Collection colMarcosFin = marcoPagamentoPrjService.findByIdProjeto(cronogramaDTO.getIdProjeto());
        if (colMarcosFin != null) {
            for (final Iterator it = colMarcosFin.iterator(); it.hasNext();) {
                final MarcoPagamentoPrjDTO marcoPagamentoPrjDTO = (MarcoPagamentoPrjDTO) it.next();
                marcoPagamentoPrjDTO.setTempoAlocMinutosTotal(new Double(0));
                marcoPagamentoPrjDTO.setCustoPerfil(new Double(0));
                final Collection colPerfisByMarcosFin = perfilContratoService.getTotaisByIdMarcoPagamentoPrj(marcoPagamentoPrjDTO.getIdMarcoPagamentoPrj(),
                        linhaBaseProjetoDTO.getIdLinhaBaseProjeto());
                if (colPerfisByMarcosFin != null) {
                    marcoPagamentoPrjDTO.setColPerfisByMarcosFin(colPerfisByMarcosFin);
                    for (final Iterator itX = colPerfisByMarcosFin.iterator(); itX.hasNext();) {
                        final PerfilContratoDTO perfilContrato = (PerfilContratoDTO) itX.next();
                        if (perfilContrato.getTempoAlocMinutosTotal() != null) {
                            marcoPagamentoPrjDTO.setTempoAlocMinutosTotal(marcoPagamentoPrjDTO.getTempoAlocMinutosTotal().doubleValue()
                                    + perfilContrato.getTempoAlocMinutosTotal().doubleValue());
                            totalTempoAloc = totalTempoAloc + perfilContrato.getTempoAlocMinutosTotal().doubleValue();
                        }
                        if (perfilContrato.getCustoTotalPerfil() != null) {
                            marcoPagamentoPrjDTO.setCustoPerfil(marcoPagamentoPrjDTO.getCustoPerfil().doubleValue()
                                    + perfilContrato.getCustoTotalPerfil().doubleValue());
                        }
                    }
                } else {
                    marcoPagamentoPrjDTO.setColPerfisByMarcosFin(new ArrayList());
                }
                final Collection colProdutosByMarcosFin = produtoContratoService.getProdutosByIdMarcoPagamentoPrj(
                        marcoPagamentoPrjDTO.getIdMarcoPagamentoPrj(), linhaBaseProjetoDTO.getIdLinhaBaseProjeto());
                if (colProdutosByMarcosFin != null) {
                    marcoPagamentoPrjDTO.setColProdutosByMarcosFin(colProdutosByMarcosFin);
                } else {
                    marcoPagamentoPrjDTO.setColProdutosByMarcosFin(new ArrayList());
                }
            }
        }
        final Collection colPerfisByMarcosFinSemMarcos = perfilContratoService.getTotaisSEMIdMarcoPagamentoPrj(linhaBaseProjetoDTO.getIdLinhaBaseProjeto());
        if (colPerfisByMarcosFinSemMarcos != null && colPerfisByMarcosFinSemMarcos.size() > 0) {
            final MarcoPagamentoPrjDTO marcoPagamentoPrjDTO = new MarcoPagamentoPrjDTO();
            marcoPagamentoPrjDTO.setTempoAlocMinutosTotal(new Double(0));
            marcoPagamentoPrjDTO.setCustoPerfil(new Double(0));
            marcoPagamentoPrjDTO.setNomeMarcoPag("--SEM ATRIBUICAO--");
            marcoPagamentoPrjDTO.setDataPrevisaoPag(UtilDatas.getDataAtual());
            marcoPagamentoPrjDTO.setColPerfisByMarcosFin(colPerfisByMarcosFinSemMarcos);
            for (final Iterator itX = colPerfisByMarcosFinSemMarcos.iterator(); itX.hasNext();) {
                final PerfilContratoDTO perfilContrato = (PerfilContratoDTO) itX.next();
                if (perfilContrato.getTempoAlocMinutosTotal() != null) {
                    marcoPagamentoPrjDTO.setTempoAlocMinutosTotal(marcoPagamentoPrjDTO.getTempoAlocMinutosTotal().doubleValue()
                            + perfilContrato.getTempoAlocMinutosTotal().doubleValue());
                    totalTempoAloc = totalTempoAloc + perfilContrato.getTempoAlocMinutosTotal().doubleValue();
                }
                if (perfilContrato.getCustoTotalPerfil() != null) {
                    marcoPagamentoPrjDTO.setCustoPerfil(marcoPagamentoPrjDTO.getCustoPerfil().doubleValue()
                            + perfilContrato.getCustoTotalPerfil().doubleValue());
                }
            }
            marcoPagamentoPrjDTO.setColProdutosByMarcosFin(new ArrayList());
            if (colMarcosFin == null) {
                colMarcosFin = new ArrayList();
            }
            colMarcosFin.add(marcoPagamentoPrjDTO);
        }

        Date dataInicio = null;
        Date dataFim = null;
        double duracaoTotal = 0;
        new StringBuilder();
        if (linhaBaseProjetoDTO != null) {
            final Collection<TarefaLinhaBaseProjetoDTO> colTarefasLnBase = tarefaLinhaBaseProjetoService.findByIdLinhaBaseProjeto(linhaBaseProjetoDTO
                    .getIdLinhaBaseProjeto());
            if (colTarefasLnBase != null) {
                Boolean possuiRecursos = false;
                for (final TarefaLinhaBaseProjetoDTO tarefa : colTarefasLnBase) {
                    if (tarefa.getCusto() != null && tarefa.getCusto() != 0) {
                        possuiRecursos = true;
                    }
                }

                if (!possuiRecursos) {
                    document.alert(UtilI18N.internacionaliza(request, "cronograma.definaRecursos"));
                    return;
                }

                for (final Object element : colTarefasLnBase) {
                    final TarefaLinhaBaseProjetoDTO tarefaLinhaBaseProjetoDTO = (TarefaLinhaBaseProjetoDTO) element;
                    if (dataInicio == null) {
                        dataInicio = tarefaLinhaBaseProjetoDTO.getDataInicio();
                    }
                    if (dataFim == null) {
                        dataFim = tarefaLinhaBaseProjetoDTO.getDataFim();
                    }
                    if (dataInicio.after(tarefaLinhaBaseProjetoDTO.getDataInicio())) {
                        dataInicio = tarefaLinhaBaseProjetoDTO.getDataInicio();
                    }
                    if (dataFim.before(tarefaLinhaBaseProjetoDTO.getDataFim())) {
                        dataFim = tarefaLinhaBaseProjetoDTO.getDataFim();
                    }
                    if (tarefaLinhaBaseProjetoDTO.getNivel() != null && tarefaLinhaBaseProjetoDTO.getNivel().intValue() == 0) {
                        if (tarefaLinhaBaseProjetoDTO.getDuracaoHora() != null) {
                            duracaoTotal = tarefaLinhaBaseProjetoDTO.getDuracaoHora();
                        }
                    }
                    final Collection colPerfisVinc = perfilContratoService.getTotaisByIdTarefaLinhaBaseProjeto(tarefaLinhaBaseProjetoDTO
                            .getIdTarefaLinhaBaseProjeto());
                    tarefaLinhaBaseProjetoDTO.setColPerfis(colPerfisVinc);
                    String strPerfisVinc = "";
                    if (colPerfisVinc != null) {
                        for (final Iterator itRec = colPerfisVinc.iterator(); itRec.hasNext();) {
                            final PerfilContratoDTO perfilContratoDTO = (PerfilContratoDTO) itRec.next();
                            if (!strPerfisVinc.trim().equalsIgnoreCase("")) {
                                strPerfisVinc += ",";
                            }
                            strPerfisVinc += perfilContratoDTO.getNomePerfilContrato();
                        }
                    }
                    tarefaLinhaBaseProjetoDTO.setNomesPerfis(strPerfisVinc);

                    final Collection colRecsVinc = recursoTarefaLinBaseProjService.findByIdTarefaLinhaBaseProjeto(tarefaLinhaBaseProjetoDTO
                            .getIdTarefaLinhaBaseProjeto());
                    tarefaLinhaBaseProjetoDTO.setColRecursos(colRecsVinc);
                    String strRecsVinc = "";
                    if (colRecsVinc != null) {
                        for (final Iterator itRec = colRecsVinc.iterator(); itRec.hasNext();) {
                            final RecursoTarefaLinBaseProjDTO recursoTarefaLinBaseProjDTO = (RecursoTarefaLinBaseProjDTO) itRec.next();
                            EmpregadoDTO empregadoDTO = new EmpregadoDTO();
                            empregadoDTO.setIdEmpregado(recursoTarefaLinBaseProjDTO.getIdEmpregado());
                            empregadoDTO = (EmpregadoDTO) empregadoService.restore(empregadoDTO);
                            recursoTarefaLinBaseProjDTO.setEmpregadoDTO(empregadoDTO);
                            if (empregadoDTO != null) {
                                if (!strRecsVinc.trim().equalsIgnoreCase("")) {
                                    strRecsVinc += ",";
                                }
                                strRecsVinc += empregadoDTO.getNome();
                            }
                        }
                    }
                    tarefaLinhaBaseProjetoDTO.setNomesRecursos(strRecsVinc);
                    // ---
                    final Collection colProdsVinc = produtoTarefaLinBaseProjService.findByIdTarefaLinhaBaseProjeto(tarefaLinhaBaseProjetoDTO
                            .getIdTarefaLinhaBaseProjeto());
                    tarefaLinhaBaseProjetoDTO.setColProdutos(colProdsVinc);
                    String strProdsVinc = "";
                    if (colProdsVinc != null) {
                        for (final Iterator itRec = colProdsVinc.iterator(); itRec.hasNext();) {
                            final ProdutoTarefaLinBaseProjDTO produtoTarefaLinBaseProjDTO = (ProdutoTarefaLinBaseProjDTO) itRec.next();
                            ProdutoContratoDTO produtoContratoDTO = new ProdutoContratoDTO();
                            produtoContratoDTO.setIdProdutoContrato(produtoTarefaLinBaseProjDTO.getIdProdutoContrato());
                            produtoContratoDTO = (ProdutoContratoDTO) produtoContratoService.restore(produtoContratoDTO);
                            if (produtoContratoDTO != null) {
                                if (!strProdsVinc.trim().equalsIgnoreCase("")) {
                                    strProdsVinc += ",";
                                }
                                strProdsVinc += produtoContratoDTO.getNomeProduto();
                            }
                        }
                    }
                    tarefaLinhaBaseProjetoDTO.setNomesProdutos(strProdsVinc);
                }
            }
            if (colTarefasLnBase != null) {
                // Template template = cfg.getTemplate("D:/AmbienteTrabalho/jboss-4.2.3.GA/server/default/deploy/citsmart.war/templateFreeMarker/osMI.ftl");
                final Map<String, Object> data = new HashMap<String, Object>();
                data.put("tasks", colTarefasLnBase);
                data.put("finMilestones", colMarcosFin);
                data.put("rolesValues", colPerfisValoresProjeto);
                data.put("Assinaturas", colAssinaturas);
                data.put("init", dataInicio);
                data.put("end", dataFim);
                data.put("duration", duracaoTotal);
                data.put("products", colProds);
                data.put("roles", colPerfis);
                data.put("resourcesProject", colRecursos);
                data.put("lineBaseDTO", linhaBaseProjetoDTO);
                data.put("contractDto", contratoDto);
                data.put("providerDto", fornecedorDTO);
                data.put("customerDto", clienteDto);
                data.put("projectDTO", projetoDto);
                data.put("osDto", osDto);
                if (totalTempoAloc == null) {
                    totalTempoAloc = new Double(0);
                }
                final double x = totalTempoAloc.doubleValue() / 60;
                data.put("totalTempoAloc", UtilFormatacao.formatDouble(x, 2));

                final File f = new File(CITCorporeUtil.CAMINHO_REAL_APP + "/templateFreeMarker/" + usuario.getIdUsuario());
                f.mkdirs();

                final String strPathTemplateCAB = CITCorporeUtil.CAMINHO_REAL_APP + "/templateFreeMarker/" + usuario.getIdUsuario() + "/"
                        + templateImpressaoDTO.getIdTemplateImpressao() + "_CAB.ftl";
                UtilTratamentoArquivos.geraFileTxtFromString(strPathTemplateCAB, templateImpressaoDTO.getHtmlCabecalho());
                final String strPathTemplateCORPO = CITCorporeUtil.CAMINHO_REAL_APP + "/templateFreeMarker/" + usuario.getIdUsuario() + "/"
                        + templateImpressaoDTO.getIdTemplateImpressao() + "_CORPO.ftl";
                UtilTratamentoArquivos.geraFileTxtFromString(strPathTemplateCORPO, templateImpressaoDTO.getHtmlCorpo());
                final String strPathTemplateROD = CITCorporeUtil.CAMINHO_REAL_APP + "/templateFreeMarker/" + usuario.getIdUsuario() + "/"
                        + templateImpressaoDTO.getIdTemplateImpressao() + "_ROD.ftl";
                UtilTratamentoArquivos.geraFileTxtFromString(strPathTemplateROD, UtilStrings.nullToVazio(templateImpressaoDTO.getHtmlRodape()));

                final String strPathTemplateCAB_HTML = strPathTemplateCAB.replaceAll(".ftl", ".html");
                final String strPathTemplateCORPO_HTML = strPathTemplateCORPO.replaceAll(".ftl", ".html");
                final String strPathTemplateROD_HTML = strPathTemplateROD.replaceAll(".ftl", ".html");

                final String strPathTemplateCORPO_PDF = strPathTemplateCORPO.replaceAll(".ftl", ".pdf");

                final String strPathPDF = Constantes.getValue("CONTEXTO_APLICACAO") + "/templateFreeMarker/" + usuario.getIdUsuario() + "/"
                        + templateImpressaoDTO.getIdTemplateImpressao() + "_CORPO.pdf";

                geraTemplate("templateFreeMarker/" + usuario.getIdUsuario() + "/" + templateImpressaoDTO.getIdTemplateImpressao() + "_CAB.ftl", data,
                        strPathTemplateCAB_HTML);
                geraTemplate("templateFreeMarker/" + usuario.getIdUsuario() + "/" + templateImpressaoDTO.getIdTemplateImpressao() + "_CORPO.ftl", data,
                        strPathTemplateCORPO_HTML);
                geraTemplate("templateFreeMarker/" + usuario.getIdUsuario() + "/" + templateImpressaoDTO.getIdTemplateImpressao() + "_ROD.ftl", data,
                        strPathTemplateROD_HTML);

                final CYaHPConverter converter = new CYaHPConverter();
                final File fout = new File(strPathTemplateCORPO_PDF);
                final FileOutputStream out = new FileOutputStream(fout);
                final List headerFooterList = new ArrayList();

                final Map properties = new HashMap();

                final String strCabecalho = UtilTratamentoArquivos.getStringTextFromFileTxtTemplate(strPathTemplateCAB_HTML);
                final String strRodape = UtilTratamentoArquivos.getStringTextFromFileTxtTemplate(strPathTemplateROD_HTML);
                headerFooterList.add(new IHtmlToPdfTransformer.CHeaderFooter(strCabecalho, IHtmlToPdfTransformer.CHeaderFooter.HEADER));
                headerFooterList.add(new IHtmlToPdfTransformer.CHeaderFooter(strRodape, IHtmlToPdfTransformer.CHeaderFooter.FOOTER));

                int tamCab = 0;
                if (templateImpressaoDTO.getTamCabecalho() != null) {
                    tamCab = templateImpressaoDTO.getTamCabecalho().intValue();
                }
                final IHtmlToPdfTransformer.PageSize pageSize = new IHtmlToPdfTransformer.PageSize(IHtmlToPdfTransformer.A4P.getSize()[0],
                        IHtmlToPdfTransformer.A4P.getSize()[1], IHtmlToPdfTransformer.A4P.getMargin()[0], IHtmlToPdfTransformer.A4P.getMargin()[1],
                        IHtmlToPdfTransformer.A4P.getMargin()[2], IHtmlToPdfTransformer.A4P.getMargin()[3] + tamCab);
                properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS, IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER);
                final String str = UtilTratamentoArquivos.getStringTextFromFileTxtTemplate(strPathTemplateCORPO_HTML);
                converter.convertToPdf(str, pageSize, headerFooterList, "file:///temp/", // root for relative external CSS and IMAGE
                        out, properties);
                /*
                 * converter.convertToPdf(new URL("http://localhost:8080/citsmart/templateFreeMarker/osMI.html"),
                 * IHtmlToPdfTransformer.A4P, headerFooterList, out,
                 * properties);
                 */
                out.flush();
                out.close();

                document.executeScript("window.open('" + strPathPDF + "')");
            }
        }
    }

    private void geraTemplate(final String strPathTemplate, final Map<String, Object> data, final String strPathOutput) throws IOException, TemplateException {
        final Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(CITCorporeUtil.CAMINHO_REAL_APP));
        Template template = null;
        if (System.getProperty("os.name").contains("Windows")) {
            template = cfg.getTemplate(strPathTemplate, "UTF-8");
        } else {
            template = cfg.getTemplate(strPathTemplate, "UTF-8");
        }
        final Writer file = new FileWriter(new File(strPathOutput));
        template.process(data, file);
        file.flush();
        file.close();
    }

    private Collection getTarefas(final Collection col, final HttpServletRequest request) throws LogicException {
        if (col != null) {
            final Collection colRetorno = new ArrayList();
            for (final Iterator it = col.iterator(); it.hasNext();) {
                final HashMap mapItem = (HashMap) it.next();
                final String name = (String) mapItem.get("NAME");
                if (name.equals("")) {
                    throw new LogicException(UtilI18N.internacionaliza(request, "cronograma.informeAtividade"));
                }
                final TarefaLinhaBaseProjetoDTO tarefaLinhaBaseProjetoDTO = generateDTO(mapItem);
                colRetorno.add(tarefaLinhaBaseProjetoDTO);
            }
            return colRetorno;
        }
        return null;
    }

    private Collection getMarcosPag(final HttpServletRequest request, final Collection col) {
        if (col != null) {
            final Collection colRetorno = new ArrayList();
            for (final Iterator it = col.iterator(); it.hasNext();) {
                final HashMap mapItem = (HashMap) it.next();
                final MarcoPagamentoPrjDTO marcoPagamentoPrjDTO = generateMarcoPagDTO(request, mapItem);
                colRetorno.add(marcoPagamentoPrjDTO);
            }
            return colRetorno;
        }
        return null;
    }

    private MarcoPagamentoPrjDTO generateMarcoPagDTO(final HttpServletRequest request, final HashMap mapItem) {
        if (mapItem == null) {
            return null;
        }
        final MarcoPagamentoPrjDTO marcoPagamentoPrjDTO = new MarcoPagamentoPrjDTO();
        marcoPagamentoPrjDTO.setId(NumberUtils.toInt(((String) mapItem.get("ID")).trim(), 0));
        marcoPagamentoPrjDTO.setNomeMarcoPag(UtilStrings.nullToVazio((String) mapItem.get("NOMEMARCOPAG")).trim());
        final String dataStr = UtilStrings.nullToVazio((String) mapItem.get("DATAPREVISAOPAG")).trim();
        if (!dataStr.trim().equalsIgnoreCase("")) {
            try {
                marcoPagamentoPrjDTO.setDataPrevisaoPag(UtilDatas.convertStringToSQLDate(TipoDate.DATE_DEFAULT, dataStr, WebUtil.getLanguage(request)));
            } catch (final Exception e) {}
        }
        return marcoPagamentoPrjDTO;
    }

    private TarefaLinhaBaseProjetoDTO generateDTO(final HashMap mapItem) {
        if (mapItem == null) {
            return null;
        }
        final TarefaLinhaBaseProjetoDTO tarefaLinhaBaseProjetoDTO = new TarefaLinhaBaseProjetoDTO();
        tarefaLinhaBaseProjetoDTO.setProgresso(new Double(0));
        tarefaLinhaBaseProjetoDTO.setNomeTarefa(UtilStrings.nullToVazio((String) mapItem.get("NAME")).trim());

        final String start = UtilStrings.nullToVazio((String) mapItem.get("START")).trim();
        final long startLong = Long.parseLong(start);
        final java.sql.Date dataInicial = new java.sql.Date(startLong);
        tarefaLinhaBaseProjetoDTO.setDataInicio(dataInicial);

        final String end = UtilStrings.nullToVazio((String) mapItem.get("END")).trim();
        final long endLong = Long.parseLong(end);
        final java.sql.Date dataFinal = new java.sql.Date(endLong);
        tarefaLinhaBaseProjetoDTO.setDataFim(dataFinal);

        final String depends = UtilStrings.nullToVazio((String) mapItem.get("DEPENDS")).trim();
        tarefaLinhaBaseProjetoDTO.setDepends(depends);

        try {
            final String level = UtilStrings.nullToVazio((String) mapItem.get("LEVEL")).trim();
            final int levelInt = Integer.parseInt(level);
            tarefaLinhaBaseProjetoDTO.setNivel(levelInt);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        final String id = UtilStrings.nullToVazio((String) mapItem.get("ID")).trim();
        tarefaLinhaBaseProjetoDTO.setIdInternal(id);
        if (id != null && id.indexOf("tmp") < 0) { // se id nao tiver nulo, eh o id original da tarefa.
            Integer idLn = null;
            try {
                idLn = new Integer(id);
                tarefaLinhaBaseProjetoDTO.setIdTarefaLinhaBaseProjeto(idLn);
            } catch (final Exception e) {}
        }

        final String code = UtilStrings.nullToVazio((String) mapItem.get("CODE")).trim();
        tarefaLinhaBaseProjetoDTO.setCodeTarefa(code);

        final String collapsed = UtilStrings.nullToVazio((String) mapItem.get("COLLAPSED")).trim();
        if (collapsed.equalsIgnoreCase("true")) {
            tarefaLinhaBaseProjetoDTO.setCollapsed("S");
        } else {
            tarefaLinhaBaseProjetoDTO.setCollapsed("N");
        }

        try {
            String dur = UtilStrings.nullToVazio((String) mapItem.get("DURATION")).trim();
            dur = dur.replaceAll(",", "");
            final double durDouble = Double.parseDouble(dur);
            tarefaLinhaBaseProjetoDTO.setDuracaoHora(durDouble);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        try {
            String prg = UtilStrings.nullToVazio((String) mapItem.get("PROGRESS")).trim();
            prg = prg.replaceAll(",", "");
            final double prgDouble = Double.parseDouble(prg);
            tarefaLinhaBaseProjetoDTO.setProgresso(prgDouble);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        if (tarefaLinhaBaseProjetoDTO.getProgresso() == null) {
            tarefaLinhaBaseProjetoDTO.setProgresso(new Double(0));
        }

        try {
            String idMarco = UtilStrings.nullToVazio((String) mapItem.get("IDMARCOPAGAMENTOPRJ")).trim();
            idMarco = idMarco.replaceAll(",", "");
            Integer intIdMarco = 0;
            if (!idMarco.equalsIgnoreCase("")) {
                intIdMarco = Integer.parseInt(idMarco);
            }
            if (intIdMarco != null && intIdMarco.longValue() == 0) {
                intIdMarco = null;
            }
            tarefaLinhaBaseProjetoDTO.setIdMarcoPagamentoPrj(intIdMarco);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        final String status = UtilStrings.nullToVazio((String) mapItem.get("STATUS")).trim();
        if (status.equalsIgnoreCase("STATUS_ACTIVE")) {
            tarefaLinhaBaseProjetoDTO.setSituacao(TarefaLinhaBaseProjetoDTO.ATIVO);
        }
        if (status.equalsIgnoreCase("STATUS_DONE")) {
            tarefaLinhaBaseProjetoDTO.setSituacao(TarefaLinhaBaseProjetoDTO.PRONTO);
        }
        if (status.equalsIgnoreCase("STATUS_FAILED")) {
            tarefaLinhaBaseProjetoDTO.setSituacao(TarefaLinhaBaseProjetoDTO.FALHA);
        }
        if (status.equalsIgnoreCase("STATUS_SUSPENDED")) {
            tarefaLinhaBaseProjetoDTO.setSituacao(TarefaLinhaBaseProjetoDTO.SUSPENSA);
        }
        if (status.equalsIgnoreCase("STATUS_UNDEFINED")) {
            tarefaLinhaBaseProjetoDTO.setSituacao(TarefaLinhaBaseProjetoDTO.SEMDEFINICAO);
        }

        tarefaLinhaBaseProjetoDTO.setEstimada("N");

        final Collection colAssigs = (Collection) mapItem.get("ASSIGS");
        final Collection colRecursos = new ArrayList();
        final Collection colProdutos = new ArrayList();
        if (colAssigs != null) {
            for (final Iterator it = colAssigs.iterator(); it.hasNext();) {
                final HashMap mapX = (HashMap) it.next();
                final String idResStr = (String) mapX.get("RESOURCEID");
                final String idPapelStr = (String) mapX.get("ROLEID");
                String effortStr = (String) mapX.get("EFFORT");
                final String esforcoPorOSStr = (String) mapX.get("ESFORCOPOROS");
                // System.out.println("RECURSO >>>>>>> " + idResStr + " >>> " + idPapelStr + " >>> " + effortStr);

                final RecursoTarefaLinBaseProjDTO recursoTarefaLinBaseProjDTO = new RecursoTarefaLinBaseProjDTO();
                try {
                    recursoTarefaLinBaseProjDTO.setIdEmpregado(new Integer(idResStr));
                    recursoTarefaLinBaseProjDTO.setIdPerfilContrato(new Integer(idPapelStr));
                    recursoTarefaLinBaseProjDTO.setEsforcoPorOS(esforcoPorOSStr);
                    if (effortStr != null) {
                        try {
                            double effortAux = Double.parseDouble(effortStr);
                            effortAux = effortAux / 36000;
                            effortStr = UtilFormatacao.formatDouble(effortAux, 0);
                            effortStr = UtilFormatacao.formataHoraHHMM(effortStr);
                        } catch (final Exception e) {
                            effortStr = "";
                        }
                    }
                    recursoTarefaLinBaseProjDTO.setTempoAloc(effortStr);
                    colRecursos.add(recursoTarefaLinBaseProjDTO);
                } catch (final Exception e) {}
            }
        }
        final Collection colProds = (Collection) mapItem.get("PRODS");
        if (colProds != null) {
            for (final Iterator it = colProds.iterator(); it.hasNext();) {
                final HashMap mapX = (HashMap) it.next();
                final String idProdStr = (String) mapX.get("PRODUCTID");
                // System.out.println("PRODUTO >>>>>>> " + idProdStr);

                final ProdutoTarefaLinBaseProjDTO produtoTarefaLinBaseProjDTO = new ProdutoTarefaLinBaseProjDTO();
                try {
                    produtoTarefaLinBaseProjDTO.setIdProdutoContrato(new Integer(idProdStr));
                    colProdutos.add(produtoTarefaLinBaseProjDTO);
                } catch (final Exception e) {}
            }
        }

        final String description = UtilStrings.nullToVazio((String) mapItem.get("DESCRIPTION")).trim();
        tarefaLinhaBaseProjetoDTO.setDetalhamentoTarefa(description);

        tarefaLinhaBaseProjetoDTO.setColRecursos(colRecursos);
        tarefaLinhaBaseProjetoDTO.setColProdutos(colProdutos);

        return tarefaLinhaBaseProjetoDTO;
    }
}
