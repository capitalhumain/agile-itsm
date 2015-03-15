package br.com.centralit.citcorpore.negocio;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.htmlparser.jericho.Source;

import org.apache.commons.lang3.StringUtils;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.bpm.dto.FluxoDTO;
import br.com.centralit.bpm.dto.TarefaFluxoDTO;
import br.com.centralit.bpm.dto.TipoFluxoDTO;
import br.com.centralit.bpm.integracao.FluxoDao;
import br.com.centralit.bpm.integracao.TipoFluxoDao;
import br.com.centralit.bpm.negocio.ItemTrabalho;
import br.com.centralit.bpm.negocio.Tarefa;
import br.com.centralit.citcorpore.bean.AcordoNivelServicoDTO;
import br.com.centralit.citcorpore.bean.AcordoServicoContratoDTO;
import br.com.centralit.citcorpore.bean.BaseConhecimentoDTO;
import br.com.centralit.citcorpore.bean.CalculoJornadaDTO;
import br.com.centralit.citcorpore.bean.ConhecimentoSolicitacaoDTO;
import br.com.centralit.citcorpore.bean.ContatoSolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.ContratoDTO;
import br.com.centralit.citcorpore.bean.ControleQuestionariosDTO;
import br.com.centralit.citcorpore.bean.EmpregadoDTO;
import br.com.centralit.citcorpore.bean.ExecucaoSolicitacaoDTO;
import br.com.centralit.citcorpore.bean.FaseServicoDTO;
import br.com.centralit.citcorpore.bean.FluxoServicoDTO;
import br.com.centralit.citcorpore.bean.GerenciamentoServicosDTO;
import br.com.centralit.citcorpore.bean.GrupoDTO;
import br.com.centralit.citcorpore.bean.ItemCfgSolicitacaoServDTO;
import br.com.centralit.citcorpore.bean.ItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.JustificativaSolicitacaoDTO;
import br.com.centralit.citcorpore.bean.ModeloEmailDTO;
import br.com.centralit.citcorpore.bean.PastaDTO;
import br.com.centralit.citcorpore.bean.PesquisaSolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.PrioridadeAcordoNivelServicoDTO;
import br.com.centralit.citcorpore.bean.PrioridadeDTO;
import br.com.centralit.citcorpore.bean.PrioridadeServicoUnidadeDTO;
import br.com.centralit.citcorpore.bean.PrioridadeServicoUsuarioDTO;
import br.com.centralit.citcorpore.bean.ProblemaDTO;
import br.com.centralit.citcorpore.bean.RelatorioCausaSolucaoDTO;
import br.com.centralit.citcorpore.bean.RelatorioDocumentacaoDeFuncionalidadesNovasOuAlteradasNoPeriodoDTO;
import br.com.centralit.citcorpore.bean.RelatorioEficaciaTesteDTO;
import br.com.centralit.citcorpore.bean.RelatorioIncidentesNaoResolvidosDTO;
import br.com.centralit.citcorpore.bean.RelatorioKpiProdutividadeDTO;
import br.com.centralit.citcorpore.bean.RelatorioQuantitativoRetornoDTO;
import br.com.centralit.citcorpore.bean.RelatorioQuantitativoSolicitacaoDTO;
import br.com.centralit.citcorpore.bean.RelatorioQuantitativoSolicitacaoProblemaPorServicoDTO;
import br.com.centralit.citcorpore.bean.RelatorioSolicitacaoPorExecutanteDTO;
import br.com.centralit.citcorpore.bean.RequisicaoMudancaDTO;
import br.com.centralit.citcorpore.bean.ServicoContratoDTO;
import br.com.centralit.citcorpore.bean.ServicoDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoEvtMonDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoMudancaDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoProblemaDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoQuestionarioDTO;
import br.com.centralit.citcorpore.bean.TemplateSolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.TempoAcordoNivelServicoDTO;
import br.com.centralit.citcorpore.bean.TipoDemandaServicoDTO;
import br.com.centralit.citcorpore.bean.UnidadeDTO;
import br.com.centralit.citcorpore.bean.UploadDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.bpm.negocio.ExecucaoSolicitacao;
import br.com.centralit.citcorpore.integracao.AcordoNivelServicoDao;
import br.com.centralit.citcorpore.integracao.AcordoServicoContratoDao;
import br.com.centralit.citcorpore.integracao.BaseConhecimentoDAO;
import br.com.centralit.citcorpore.integracao.ConhecimentoSolicitacaoDao;
import br.com.centralit.citcorpore.integracao.ContatoSolicitacaoServicoDao;
import br.com.centralit.citcorpore.integracao.ContratoDao;
import br.com.centralit.citcorpore.integracao.ControleQuestionariosDao;
import br.com.centralit.citcorpore.integracao.EmpregadoDao;
import br.com.centralit.citcorpore.integracao.ExecucaoSolicitacaoDao;
import br.com.centralit.citcorpore.integracao.FaseServicoDao;
import br.com.centralit.citcorpore.integracao.FluxoServicoDao;
import br.com.centralit.citcorpore.integracao.GrupoDao;
import br.com.centralit.citcorpore.integracao.ItemCfgSolicitacaoServDAO;
import br.com.centralit.citcorpore.integracao.MatrizPrioridadeDAO;
import br.com.centralit.citcorpore.integracao.ModeloEmailDao;
import br.com.centralit.citcorpore.integracao.OcorrenciaSolicitacaoDao;
import br.com.centralit.citcorpore.integracao.PastaDAO;
import br.com.centralit.citcorpore.integracao.PrioridadeAcordoNivelServicoDao;
import br.com.centralit.citcorpore.integracao.PrioridadeServicoUnidadeDao;
import br.com.centralit.citcorpore.integracao.PrioridadeServicoUsuarioDao;
import br.com.centralit.citcorpore.integracao.ServicoContratoDao;
import br.com.centralit.citcorpore.integracao.ServicoDao;
import br.com.centralit.citcorpore.integracao.SolicitacaoServicoDao;
import br.com.centralit.citcorpore.integracao.SolicitacaoServicoEvtMonDao;
import br.com.centralit.citcorpore.integracao.SolicitacaoServicoMudancaDao;
import br.com.centralit.citcorpore.integracao.SolicitacaoServicoProblemaDao;
import br.com.centralit.citcorpore.integracao.SolicitacaoServicoQuestionarioDao;
import br.com.centralit.citcorpore.integracao.TempoAcordoNivelServicoDao;
import br.com.centralit.citcorpore.mail.MensagemEmail;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.centralit.citcorpore.util.CriptoUtils;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.Enumerados.CategoriaOcorrencia;
import br.com.centralit.citcorpore.util.Enumerados.OrigemOcorrencia;
import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoSLA;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoSolicitacaoServico;
import br.com.centralit.citcorpore.util.Enumerados.TipoSolicitacaoServico;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.centralit.citcorpore.util.Util;
import br.com.centralit.citged.bean.ControleGEDDTO;
import br.com.centralit.citged.integracao.ControleGEDDao;
import br.com.centralit.citquestionario.integracao.RespostaItemQuestionarioDao;
import br.com.centralit.citquestionario.negocio.RespostaItemQuestionarioServiceBean;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.integracao.TransactionControlerImpl;
import br.com.citframework.service.CrudServiceImpl;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilFormatacao;
import br.com.citframework.util.UtilI18N;
import br.com.citframework.util.UtilStrings;

import com.google.gson.Gson;

@SuppressWarnings({"unchecked", "rawtypes", "static-access"})
public class SolicitacaoServicoServiceEjb extends CrudServiceImpl implements SolicitacaoServicoService {

    private SolicitacaoServicoDao solicitacaoServicoDao;

    @Override
    public BaseEntity create(BaseEntity model) throws ServiceException, LogicException {
        final TransactionControler tc = new TransactionControlerImpl(this.getDao().getAliasDB());
        try {
            tc.start();
            model = this.create(model, tc, true, true, true);
            if (model == null) {
                throw new Exception();
            }
            tc.commit();
        } catch (final Exception e) {
            this.rollbackTransaction(tc, e);
        } finally {
            tc.closeQuietly();
        }
        return model;
    }

    @Override
    public BaseEntity create(final BaseEntity model, final TransactionControler tc, final boolean determinaPrioridadePrazo, final boolean determinaHoraInicio,
            final boolean determinaDataHoraSolicitacao) throws Exception {
        SolicitacaoServicoDTO solicitacaoServico = (SolicitacaoServicoDTO) model;

        final ExecucaoSolicitacaoServiceEjb execucaoSolicitacaoService = new ExecucaoSolicitacaoServiceEjb();
        final ContatoSolicitacaoServicoDao contatoSolicitacaoServicoDao = new ContatoSolicitacaoServicoDao();
        ContatoSolicitacaoServicoDTO contatoSolicitacaoServico = new ContatoSolicitacaoServicoDTO();
        final SolicitacaoServicoProblemaDao solicitacaoServicoProblemaDao = new SolicitacaoServicoProblemaDao();
        final SolicitacaoServicoMudancaDao solicitacaoServicoMudancaDao = new SolicitacaoServicoMudancaDao();
        final ConhecimentoSolicitacaoDao conhecimentoSolicitacaoDao = new ConhecimentoSolicitacaoDao();
        final ItemCfgSolicitacaoServDAO itemCfgSolicitacaoServDAO = new ItemCfgSolicitacaoServDAO();
        final SolicitacaoServicoEvtMonDao solicitacaoServicoEvtMonDao = new SolicitacaoServicoEvtMonDao();

        final ServicoContratoDao servicoContratoDao = new ServicoContratoDao();
        final GrupoDao grupoDao = new GrupoDao();

        // Faz validacao, caso exista.
        this.validaCreate(model);

        this.getDao().setTransactionControler(tc);
        contatoSolicitacaoServicoDao.setTransactionControler(tc);
        contatoSolicitacaoServico.setNomecontato(solicitacaoServico.getNomecontato());
        contatoSolicitacaoServico.setEmailcontato(solicitacaoServico.getEmailcontato());
        contatoSolicitacaoServico.setTelefonecontato(solicitacaoServico.getTelefonecontato());
        contatoSolicitacaoServico.setObservacao(solicitacaoServico.getObservacao());
        contatoSolicitacaoServico.setRamal(solicitacaoServico.getRamal());
        solicitacaoServicoProblemaDao.setTransactionControler(tc);
        solicitacaoServicoMudancaDao.setTransactionControler(tc);
        conhecimentoSolicitacaoDao.setTransactionControler(tc);
        itemCfgSolicitacaoServDAO.setTransactionControler(tc);

        solicitacaoServicoEvtMonDao.setTransactionControler(tc);
        servicoContratoDao.setTransactionControler(tc);
        grupoDao.setTransactionControler(tc);

        if (solicitacaoServico.getIdLocalidade() != null) {
            contatoSolicitacaoServico.setIdLocalidade(solicitacaoServico.getIdLocalidade());
        }
        contatoSolicitacaoServico = (ContatoSolicitacaoServicoDTO) contatoSolicitacaoServicoDao.create(contatoSolicitacaoServico);

        final ServicoContratoDTO servicoContrato = servicoContratoDao.findByIdContratoAndIdServico(solicitacaoServico.getIdContrato(),
                solicitacaoServico.getIdServico());

        if (servicoContrato == null) {
            throw new LogicException(this.i18nMessage("solicitacaoservico.validacao.servicolocalizado"));
        }

        if (solicitacaoServico.getIdServicoContrato() == null) {
            solicitacaoServico.setIdServicoContrato(servicoContrato.getIdServicoContrato());
        }

        final UsuarioDTO usuario = solicitacaoServico.getUsuarioDto();
        solicitacaoServico.setIdResponsavel(usuario.getIdUsuario());

        solicitacaoServico.setIdCalendario(servicoContrato.getIdCalendario());
        solicitacaoServico.setTempoDecorridoHH(new Integer(0));
        solicitacaoServico.setTempoDecorridoMM(new Integer(0));
        solicitacaoServico.setDataHoraSuspensao(null);
        solicitacaoServico.setDataHoraReativacao(null);
        solicitacaoServico.setDataHoraInicioSLA(null);
        solicitacaoServico.setSituacaoSLA(SituacaoSLA.N.name());

        if (solicitacaoServico.getIdGrupoNivel1() == null || solicitacaoServico.getIdGrupoNivel1().intValue() <= 0) {
            Integer idGrupoNivel1 = null;
            if (servicoContrato.getIdGrupoNivel1() != null && servicoContrato.getIdGrupoNivel1().intValue() > 0) {
                idGrupoNivel1 = servicoContrato.getIdGrupoNivel1();
            } else {
                final String idGrupoN1 = ParametroUtil.getValor(ParametroSistema.ID_GRUPO_PADRAO_NIVEL1, null, null);
                if (idGrupoN1 != null && !idGrupoN1.trim().equalsIgnoreCase("")) {
                    try {
                        idGrupoNivel1 = new Integer(idGrupoN1);
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (idGrupoNivel1 == null || idGrupoNivel1.intValue() <= 0) {
                throw new LogicException(this.i18nMessage("solicitacaoservico.validacao.grupoatendnivel"));
            }
            GrupoDTO grupo = new GrupoDTO();
            grupo.setIdGrupo(idGrupoNivel1);
            grupo = (GrupoDTO) grupoDao.restore(grupo);
            if (grupo == null || grupo.getDataFim() != null) {
                throw new LogicException(this.i18nMessage("solicitacaoservico.validacao.grupoatendnivel"));
            }
            solicitacaoServico.setIdGrupoNivel1(idGrupoNivel1);
        }

        final FluxoServicoDao fluxoServicoDao = new FluxoServicoDao();
        final TipoFluxoDao tipoFluxoDao = new TipoFluxoDao();
        TipoFluxoDTO tipoFluxo = new TipoFluxoDTO();
        final FluxoServicoDTO fluxoServico = fluxoServicoDao.findPrincipalByIdServicoContrato(servicoContrato.getIdServicoContrato());

        int idTipoFluxoSolicitacaoServico = 0;

        // Verifica se há fluxo associado ao serviço contrato
        if (fluxoServico != null && fluxoServico.getIdTipoFluxo() != null) {
            idTipoFluxoSolicitacaoServico = fluxoServico.getIdTipoFluxo();
        } else {
            // Verifica o fluxo padrão para Solicitação Serviço definido em Parâmetro
            final String nomeFluxoPadraoSolicitacaoServico = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.NomeFluxoPadraoServicos,
                    "SolicitacaoServico");

            if (nomeFluxoPadraoSolicitacaoServico != null) {
                tipoFluxo = tipoFluxoDao.findByNome(nomeFluxoPadraoSolicitacaoServico);

                if (tipoFluxo != null && tipoFluxo.getIdTipoFluxo() != null) {
                    idTipoFluxoSolicitacaoServico = tipoFluxo.getIdTipoFluxo();
                }
            }
        }

        int idGrupo = 0;
        if (solicitacaoServico != null && solicitacaoServico.getIdGrupoAtual() != null) {
            idGrupo = solicitacaoServico.getIdGrupoAtual();
            /* Inserido por Carlos Santos em 05/11/2013 -> Antes de testar o nível 1, deve ser testado o grupo executor do contrato */
        } else if (servicoContrato != null && servicoContrato.getIdGrupoExecutor() != null) {
            idGrupo = servicoContrato.getIdGrupoExecutor();
        } else if (solicitacaoServico != null && solicitacaoServico.getIdGrupoNivel1() != null) {
            idGrupo = solicitacaoServico.getIdGrupoNivel1();
        }

        if (idGrupo > 0 && idTipoFluxoSolicitacaoServico > 0) {
            final boolean resultado = this.permissaoGrupoExecutorServico(idGrupo, idTipoFluxoSolicitacaoServico);
            if (!resultado) {
                throw new LogicException(this.i18nMessage("solicitacaoServico.grupoSemPermissao"));
            }
        } else {
            throw new LogicException(this.i18nMessage("fluxo.fluxoserviconaodefinido "));
        }

        solicitacaoServico.setIdContatoSolicitacaoServico(contatoSolicitacaoServico.getIdcontatosolicitacaoservico());
        if (determinaDataHoraSolicitacao) {
            solicitacaoServico.setDataHoraSolicitacao(new Timestamp(new java.util.Date().getTime()));
        }

        if (determinaPrioridadePrazo) {
            this.determinaPrioridadeEPrazo(solicitacaoServico, tc);
        }
        if (determinaHoraInicio) {
            solicitacaoServico.setDataHoraInicio(new Timestamp(new java.util.Date().getTime()));
        }

        solicitacaoServico.setSeqReabertura(new Integer(0));

        if (solicitacaoServico.escalada()) {
            final String tipoCaptura = ParametroUtil.getValor(ParametroSistema.TIPO_CAPTURA_SOLICITACOES, tc, "1");
            if (tipoCaptura.equals("2")) {
                solicitacaoServico.setDataHoraCaptura(solicitacaoServico.getDataHoraInicio());
            }
        }

        solicitacaoServico = (SolicitacaoServicoDTO) solicitacaoServicoDao.create(solicitacaoServico);

        if (solicitacaoServico.getColItensProblema() != null) {
            for (final Iterator it = solicitacaoServico.getColItensProblema().iterator(); it.hasNext();) {
                final ProblemaDTO problemaDTO = (ProblemaDTO) it.next();
                final SolicitacaoServicoProblemaDTO solicitacaoServicoProblemaDTO = new SolicitacaoServicoProblemaDTO();
                solicitacaoServicoProblemaDTO.setIdProblema(problemaDTO.getIdProblema());
                solicitacaoServicoProblemaDTO.setIdSolicitacaoServico(solicitacaoServico.getIdSolicitacaoServico());
                solicitacaoServicoProblemaDao.create(solicitacaoServicoProblemaDTO);
            }
        }

        if (solicitacaoServico.getColItensMudanca() != null) {

            for (final Iterator it = solicitacaoServico.getColItensMudanca().iterator(); it.hasNext();) {
                final RequisicaoMudancaDTO requisicaoMudancaDTO = (RequisicaoMudancaDTO) it.next();

                final SolicitacaoServicoMudancaDTO solicitacaoServicoMudancaDTO = new SolicitacaoServicoMudancaDTO();
                solicitacaoServicoMudancaDTO.setIdRequisicaoMudanca(requisicaoMudancaDTO.getIdRequisicaoMudanca());
                solicitacaoServicoMudancaDTO.setIdSolicitacaoServico(solicitacaoServico.getIdSolicitacaoServico());
                solicitacaoServicoMudancaDao.create(solicitacaoServicoMudancaDTO);
            }
        }

        if (solicitacaoServico.getColItensICSerialize() != null) {
            for (final ItemConfiguracaoDTO bean : solicitacaoServico.getColItensICSerialize()) {
                final ItemCfgSolicitacaoServDTO dto = new ItemCfgSolicitacaoServDTO();
                dto.setIdItemConfiguracao(bean.getIdItemConfiguracao());
                dto.setIdSolicitacaoServico(solicitacaoServico.getIdSolicitacaoServico());
                dto.setDataInicio(Util.getSqlDataAtual());

                itemCfgSolicitacaoServDAO.create(dto);
            }
        }

        if (solicitacaoServico.getColItensBaseConhecimento() != null) {
            for (final Object element : solicitacaoServico.getColItensBaseConhecimento()) {
                final BaseConhecimentoDTO baseConhecimentoDTO = (BaseConhecimentoDTO) element;

                final ConhecimentoSolicitacaoDTO conhecimentoSolicitacaoDTO = new ConhecimentoSolicitacaoDTO();
                conhecimentoSolicitacaoDTO.setIdBaseConhecimento(baseConhecimentoDTO.getIdBaseConhecimento());
                conhecimentoSolicitacaoDTO.setIdSolicitacaoServico(solicitacaoServico.getIdSolicitacaoServico());
                conhecimentoSolicitacaoDao.create(conhecimentoSolicitacaoDTO);
            }
        }

        if (solicitacaoServico.getInformacoesComplementares() != null || solicitacaoServico.getSolicitacaoServicoQuestionarioDTO() != null) {
            final TemplateSolicitacaoServicoDTO template = new TemplateSolicitacaoServicoServiceEjb().recuperaTemplateServico(solicitacaoServico);
            if (template != null) {
                if (template.isQuestionario()) {
                    this.atualizaInformacoesQuestionario(solicitacaoServico, tc);
                } else if (template.getNomeClasseServico() != null) {
                    final ComplemInfSolicitacaoServicoService informacoesComplementaresService = this.getInformacoesComplementaresService(template
                            .getNomeClasseServico());
                    informacoesComplementaresService.create(tc, solicitacaoServico, solicitacaoServico.getInformacoesComplementares());
                }
            }
        }

        try {
            OcorrenciaSolicitacaoServiceEjb.create(solicitacaoServico, null, null, OrigemOcorrencia.OUTROS, CategoriaOcorrencia.Criacao,
                    new Gson().toJson(contatoSolicitacaoServico), CategoriaOcorrencia.Criacao.getDescricao(), usuario, 0, null, tc);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        final ExecucaoSolicitacao execucaoSolicitacao = execucaoSolicitacaoService.registraSolicitacao(solicitacaoServico, tc);
        if (execucaoSolicitacao == null) {
            tc.rollback();
            return null;
        }

        if (solicitacaoServico.getColArquivosUpload() != null && solicitacaoServico.getColArquivosUpload().size() > 0) {
            this.gravaInformacoesGED(solicitacaoServico.getColArquivosUpload(), 1, solicitacaoServico, tc);
        }

        final Source source = new Source(solicitacaoServico.getRegistroexecucao());
        solicitacaoServico.setRegistroexecucao(source.getTextExtractor().toString());

        if (solicitacaoServico.getRegistroexecucao() != null && !solicitacaoServico.getRegistroexecucao().trim().equalsIgnoreCase("")) {
            TarefaFluxoDTO tarefa = null;
            if (solicitacaoServico.getIdTarefa() != null) {
                tarefa = new TarefaFluxoDTO();
                tarefa.setIdItemTrabalho(solicitacaoServico.getIdTarefa());
            }

            OcorrenciaSolicitacaoServiceEjb.create(solicitacaoServico, tarefa, solicitacaoServico.getRegistroexecucao(), OrigemOcorrencia.OUTROS,
                    CategoriaOcorrencia.Execucao, null, CategoriaOcorrencia.Execucao.getDescricao(), usuario, 0, null, tc);
        }

        if (solicitacaoServico.getColSolicitacaoServicoEvtMon() != null) {
            for (final Object element : solicitacaoServico.getColSolicitacaoServicoEvtMon()) {
                final SolicitacaoServicoEvtMonDTO solicitacaoServicoEvtMonDTO = (SolicitacaoServicoEvtMonDTO) element;
                solicitacaoServicoEvtMonDTO.setIdSolicitacaoServico(solicitacaoServico.getIdSolicitacaoServico());
                solicitacaoServicoEvtMonDao.create(solicitacaoServicoEvtMonDTO);
            }
        }

        if (solicitacaoServico != null && solicitacaoServico.getBeanBaseConhecimento() != null
                && solicitacaoServico.getBeanBaseConhecimento().getTitulo() != null && !solicitacaoServico.getBeanBaseConhecimento().getTitulo().isEmpty()) {
            this.InserirNaBaseConhecimento(solicitacaoServico, tc);
        }

        return solicitacaoServico;
    }

    // Criado por Bruno.Aquino
    // Se a Situação estiver como "Resolvida" Capturo: a descricao do problema e a Solução/Resposta
    public void InserirNaBaseConhecimento(final SolicitacaoServicoDTO solicitacaoServicoDTO, final TransactionControler tc) throws ServiceException, Exception {

        final BaseConhecimentoDTO beanBaseConhecimento = solicitacaoServicoDTO.getBeanBaseConhecimento();
        beanBaseConhecimento.setIdSolicitacaoServico(solicitacaoServicoDTO.getIdSolicitacaoServico());

        final BaseConhecimentoDAO baseConhecimentoDAO = new BaseConhecimentoDAO();
        final PastaDAO pastaDao = new PastaDAO();

        pastaDao.setTransactionControler(tc);
        baseConhecimentoDAO.setTransactionControler(tc);

        final BaseConhecimentoDTO baseAux = baseConhecimentoDAO.findByIdSolicitacaoServico(solicitacaoServicoDTO);

        // verifica se já não existe uma registro na base de conhecimento referente a essa solicitação, só pode armazenar um.
        if (baseAux == null) {

            final PastaServiceEjb pastaEjb = new PastaServiceEjb();
            final PastaDTO pastaBean = new PastaDTO();
            int cont = 0;

            // verifica se a pasta existe
            final Collection<PastaDTO> lista = pastaEjb.consultarPastasAtivas();
            for (final PastaDTO p : lista) {
                if (p.getNome() != null) {
                    if (p.getNome().equals(
                            ParametroUtil.getValorParametroCitSmartHashMap(
                                    Enumerados.ParametroSistema.PASTA_SALVA_DESCRICAO_RESPOSTA_DE_SOLICITACAOSERVICO_EM_BASECONHECIMENTO,
                                    "Descrição_Resposta_Para_BaseConhecimento"))) {
                        pastaBean.setId(p.getId());
                        cont++;
                    }
                }
            }

            // se a pasta não existir, pasta vai ser criada pelo parametro ou com o nome do padrão
            if (cont == 0) {
                pastaBean.setNome(ParametroUtil.getValorParametroCitSmartHashMap(
                        Enumerados.ParametroSistema.PASTA_SALVA_DESCRICAO_RESPOSTA_DE_SOLICITACAOSERVICO_EM_BASECONHECIMENTO,
                        "Descrição_Resposta_Para_BaseConhecimento"));
                pastaDao.create(pastaBean);

                final Collection<PastaDTO> lista2 = pastaEjb.consultarPastasAtivas();
                for (final PastaDTO p2 : lista2) {
                    if (p2.getNome() != null) {
                        if (p2.getNome().equals(
                                ParametroUtil.getValorParametroCitSmartHashMap(
                                        Enumerados.ParametroSistema.PASTA_SALVA_DESCRICAO_RESPOSTA_DE_SOLICITACAOSERVICO_EM_BASECONHECIMENTO,
                                        "Descrição_Resposta_Para_BaseConhecimento"))) {
                            pastaBean.setId(p2.getId());
                        }
                    }
                }
            }

            beanBaseConhecimento.setIdPasta(pastaBean.getId());
            baseConhecimentoDAO.create(beanBaseConhecimento);
        }
    }

    public void PersistirItemBaseConhecimento(final SolicitacaoServicoDTO solicitacaoServico, final ConhecimentoSolicitacaoDao conhecimentoSolicitacaoDao)
            throws Exception {
        List<ConhecimentoSolicitacaoDTO> listaTelaConhecimentoDTO = solicitacaoServico.getColConhecimentoSolicitacaoSerialize();
        if (listaTelaConhecimentoDTO != null) {
            ConhecimentoSolicitacaoDTO dto;
            // Inserindo no Banco de Dados os Itens da lista ainda não cadastrados
            for (final ConhecimentoSolicitacaoDTO bean : listaTelaConhecimentoDTO) {
                dto = new ConhecimentoSolicitacaoDTO();
                dto.setIdBaseConhecimento(bean.getIdBaseConhecimento());
                dto.setIdSolicitacaoServico(solicitacaoServico.getIdSolicitacaoServico());
                if (!conhecimentoSolicitacaoDao.seCadastrada(dto)) {
                    conhecimentoSolicitacaoDao.create(dto);
                }
            }
            // Apagando Itens no banco que não estão na lista informada
            Collection<ConhecimentoSolicitacaoDTO> listaDBConhecimentoSolicitacaoDTO = conhecimentoSolicitacaoDao.findByidSolicitacaoServico(solicitacaoServico
                    .getIdSolicitacaoServico());
            Boolean encontrou;
            for (final ConhecimentoSolicitacaoDTO elemento : listaDBConhecimentoSolicitacaoDTO) {
                encontrou = new Boolean("False");
                for (final ConhecimentoSolicitacaoDTO itemBaseConhecimento : listaTelaConhecimentoDTO) {
                    if (elemento.getIdBaseConhecimento().equals(itemBaseConhecimento.getIdBaseConhecimento())) {
                        encontrou = true;
                        break;
                    }
                }
                if (!encontrou) {
                    conhecimentoSolicitacaoDao.delete(elemento);
                }
            }
            // Para o Garbage Collection agir mais rápido
            listaTelaConhecimentoDTO = null;
            dto = null;
            listaDBConhecimentoSolicitacaoDTO = null;
            encontrou = null;
        }
    }

    @Override
    public void deserializaInformacoesComplementares(final SolicitacaoServicoDTO solicitacaoServico, final SolicitacaoServicoQuestionarioDTO solQuestionario)
            throws Exception {
        if (solicitacaoServico.getInformacoesComplementares_serialize() != null) {
            final TemplateSolicitacaoServicoDTO template = new TemplateSolicitacaoServicoServiceEjb().recuperaTemplateServico(solicitacaoServico);
            if (template != null && template.getNomeClasseServico() != null) {
                if (template.isQuestionario()) {
                    solicitacaoServico.setSolicitacaoServicoQuestionarioDTO(solQuestionario);
                } else {
                    final ComplemInfSolicitacaoServicoService informacoesComplementaresService = this.getInformacoesComplementaresService(template
                            .getNomeClasseServico());
                    final BaseEntity informacoesComplementares = informacoesComplementaresService.deserializaObjeto(solicitacaoServico
                            .getInformacoesComplementares_serialize());
                    solicitacaoServico.setInformacoesComplementares(informacoesComplementares);
                }
            }
            solicitacaoServico.setInformacoesComplementares_serialize(null);
        }
    }

    public void determinaPrazoLimiteSolicitacaoACombinarReclassificada(final SolicitacaoServicoDTO solicitacao, final Integer idCalendarioParm,
            final TransactionControler tc) throws Exception {
        new ExecucaoSolicitacaoServiceEjb().determinaPrazoLimiteSolicitacaoACombinarReclassificada(solicitacao, idCalendarioParm, tc);
    }

    public void determinaPrioridadeEPrazo(final SolicitacaoServicoDTO solicitacaoServico, final TransactionControler tc) throws Exception {
        if (solicitacaoServico.getIdSolicitante() != null) {
            final EmpregadoDao empregadoDao = new EmpregadoDao();
            if (tc != null) {
                empregadoDao.setTransactionControler(tc);
            }

            EmpregadoDTO empregadoDTO = null;
            empregadoDTO = empregadoDao.restoreByIdEmpregado(solicitacaoServico.getIdSolicitante());
            if (empregadoDTO != null && empregadoDTO.getIdUnidade() != null) {
                if (solicitacaoServico.getIdUnidade() == null || solicitacaoServico.getIdUnidade().intValue() == 0) {
                    solicitacaoServico.setIdUnidade(empregadoDTO.getIdUnidade());
                }
                final PrioridadeServicoUnidadeDao prioridadeServicoUnidadeDao = new PrioridadeServicoUnidadeDao();
                if (tc != null) {
                    prioridadeServicoUnidadeDao.setTransactionControler(tc);
                }
                final PrioridadeServicoUnidadeDTO prioridadeServicoUnidade = prioridadeServicoUnidadeDao.restore(solicitacaoServico.getIdServicoContrato(),
                        empregadoDTO.getIdUnidade());
                if (prioridadeServicoUnidade != null) {
                    solicitacaoServico.setIdPrioridade(prioridadeServicoUnidade.getIdPrioridade());
                }

            }
        }

        final AcordoNivelServicoDao acordoNivelServicoDao = new AcordoNivelServicoDao();
        if (tc != null) {
            acordoNivelServicoDao.setTransactionControler(tc);
        }

        AcordoNivelServicoDTO acordoNivelServico = acordoNivelServicoDao.findAtivoByIdServicoContrato(solicitacaoServico.getIdServicoContrato(), "T");
        if (acordoNivelServico == null) {
            final AcordoServicoContratoDao acordoServicoContratoDao = new AcordoServicoContratoDao();
            if (tc != null) {
                acordoServicoContratoDao.setTransactionControler(tc);
            }

            // Se nao houver acordo especifico, ou seja, associado direto ao
            // servicocontrato, entao busca um acordo geral que esteja vinculado
            // ao servicocontrato.
            final AcordoServicoContratoDTO acordoServicoContrato = acordoServicoContratoDao.findAtivoByIdServicoContrato(
                    solicitacaoServico.getIdServicoContrato(), "T");
            if (acordoServicoContrato == null) {
                throw new Exception(this.i18nMessage("solicitacaoservico.validacao.tempoacordo"));
            }
            // Apos achar a vinculacao do acordo com o servicocontrato, entao
            // faz um restore do acordo de nivel de servico.
            acordoNivelServico = new AcordoNivelServicoDTO();
            acordoNivelServico.setIdAcordoNivelServico(acordoServicoContrato.getIdAcordoNivelServico());
            acordoNivelServico = (AcordoNivelServicoDTO) acordoNivelServicoDao.restore(acordoNivelServico);
            if (acordoNivelServico == null) {
                // Se nao houver acordo especifico, ou seja, associado direto ao
                // servicocontrato
                throw new Exception(this.i18nMessage("solicitacaoservico.validacao.tempoacordo"));
            }
            solicitacaoServico.setIdAcordoNivelServico(acordoServicoContrato.getIdAcordoNivelServico());

            // Consulta prioridade do usuário de acordo com sla global
            final PrioridadeServicoUsuarioDao prioridadeServicoUsuarioDao = new PrioridadeServicoUsuarioDao();
            if (tc != null) {
                prioridadeServicoUsuarioDao.setTransactionControler(tc);
            }
            final PrioridadeServicoUsuarioDTO prioridadeServicoUsuarioDTO = prioridadeServicoUsuarioDao.findByIdAcordoNivelServicoAndIdUsuario(
                    solicitacaoServico.getIdAcordoNivelServico(), solicitacaoServico.getIdSolicitante());
            if (prioridadeServicoUsuarioDTO != null) {
                solicitacaoServico.setIdPrioridade(prioridadeServicoUsuarioDTO.getIdPrioridade());
            }

            // Consulta prioridade da unidade do usuário de acordo com sla global
            final PrioridadeAcordoNivelServicoDao prioridadeAcordoNivelServicoDao = new PrioridadeAcordoNivelServicoDao();
            if (tc != null) {
                prioridadeAcordoNivelServicoDao.setTransactionControler(tc);
            }
            final PrioridadeAcordoNivelServicoDTO prioridadeAcordoNivelServicoDTO = prioridadeAcordoNivelServicoDao.findByIdAcordoNivelServicoAndIdUnidade(
                    solicitacaoServico.getIdAcordoNivelServico(), solicitacaoServico.getIdUnidade());
            if (prioridadeAcordoNivelServicoDTO != null) {
                solicitacaoServico.setIdPrioridade(prioridadeAcordoNivelServicoDTO.getIdPrioridade());
            }

        } else {
            solicitacaoServico.setIdAcordoNivelServico(acordoNivelServico.getIdAcordoNivelServico());
        }

        if (solicitacaoServico.getUrgencia() == null || solicitacaoServico.getUrgencia().equalsIgnoreCase("")) {
            solicitacaoServico.setUrgencia("B");
        }
        if (solicitacaoServico.getImpacto() == null || solicitacaoServico.getImpacto().equalsIgnoreCase("")) {
            solicitacaoServico.setImpacto("B");
        }

        final String calcularDinamicamente = ParametroUtil.getValorParametroCitSmartHashMap(
                Enumerados.ParametroSistema.CALCULAR_PRIORIDADE_SOLICITACAO_DINAMICAMENTE, "N");

        if (!calcularDinamicamente.trim().equalsIgnoreCase("S")) {
            if (solicitacaoServico.getIdPrioridade() == null) {
                // Aqui determina prazo pela Urgencia e Impacto - Como mando o ITIL.
                if (solicitacaoServico.getUrgencia().equalsIgnoreCase("B") && solicitacaoServico.getImpacto().equalsIgnoreCase("B")) {
                    solicitacaoServico.setIdPrioridade(5);
                } else if (solicitacaoServico.getUrgencia().equalsIgnoreCase("B") && solicitacaoServico.getImpacto().equalsIgnoreCase("M")) {
                    solicitacaoServico.setIdPrioridade(4);
                } else if (solicitacaoServico.getUrgencia().equalsIgnoreCase("B") && solicitacaoServico.getImpacto().equalsIgnoreCase("A")) {
                    solicitacaoServico.setIdPrioridade(3);
                } else if (solicitacaoServico.getUrgencia().equalsIgnoreCase("M") && solicitacaoServico.getImpacto().equalsIgnoreCase("B")) {
                    solicitacaoServico.setIdPrioridade(4);
                } else if (solicitacaoServico.getUrgencia().equalsIgnoreCase("M") && solicitacaoServico.getImpacto().equalsIgnoreCase("M")) {
                    solicitacaoServico.setIdPrioridade(3);
                } else if (solicitacaoServico.getUrgencia().equalsIgnoreCase("M") && solicitacaoServico.getImpacto().equalsIgnoreCase("A")) {
                    solicitacaoServico.setIdPrioridade(2);
                } else if (solicitacaoServico.getUrgencia().equalsIgnoreCase("A") && solicitacaoServico.getImpacto().equalsIgnoreCase("B")) {
                    solicitacaoServico.setIdPrioridade(3);
                } else if (solicitacaoServico.getUrgencia().equalsIgnoreCase("A") && solicitacaoServico.getImpacto().equalsIgnoreCase("M")) {
                    solicitacaoServico.setIdPrioridade(2);
                } else if (solicitacaoServico.getUrgencia().equalsIgnoreCase("A") && solicitacaoServico.getImpacto().equalsIgnoreCase("A")) {
                    solicitacaoServico.setIdPrioridade(1);
                }
            }
        } else {
            final MatrizPrioridadeDAO matrizPrioriDao = new MatrizPrioridadeDAO();
            if (tc != null) {
                matrizPrioriDao.setTransactionControler(tc);
            }

            final String siglaImpacto = solicitacaoServico.getImpacto();
            final String siglaUrgencia = solicitacaoServico.getUrgencia();
            final Integer valorPrioridade = matrizPrioriDao.consultaValorPrioridade(siglaImpacto.trim().toUpperCase(), siglaUrgencia.trim().toUpperCase());
            solicitacaoServico.setIdPrioridade(valorPrioridade);
        }

        if (solicitacaoServico.getIdPrioridade() == null) {
            // Se ainda a prioridade estiver NULA, vai a padrao
            solicitacaoServico.setIdPrioridade(acordoNivelServico.getIdPrioridadePadrao());
        }

        if (solicitacaoServico.getIdPrioridade() == null) {
            throw new Exception(this.i18nMessage("solicitacaoservico.validacao.prioridadesolicitacao"));
        }

        int prazoCapturaHH = 0;
        int prazoCapturaMM = 0;
        int prazoHH = 0;
        int prazoMM = 0;
        final TempoAcordoNivelServicoDao tempoAcordoNivelServicoDao = new TempoAcordoNivelServicoDao();
        if (tc != null) {
            tempoAcordoNivelServicoDao.setTransactionControler(tc);
        }

        final Collection<TempoAcordoNivelServicoDTO> colTempos = tempoAcordoNivelServicoDao.findByIdAcordoAndIdPrioridade(
                acordoNivelServico.getIdAcordoNivelServico(), solicitacaoServico.getIdPrioridade());
        if (colTempos != null) {
            final FluxoServicoDao fluxoServicoDao = new FluxoServicoDao();
            if (tc != null) {
                fluxoServicoDao.setTransactionControler(tc);
            }

            final String idFasePadraoStr = ParametroUtil.getValor(ParametroSistema.IDFaseExecucaoServicos, tc, null);
            if (idFasePadraoStr == null) {
                throw new Exception(this.i18nMessage("solicitacaoservico.validacao.padraoexecucao"));
            }

            final FaseServicoDao faseServicoDao = new FaseServicoDao();
            if (tc != null) {
                faseServicoDao.setTransactionControler(tc);
            }
            final Collection<FaseServicoDTO> colFases = faseServicoDao.list();
            final HashMap<String, FaseServicoDTO> mapFasesCaptura = new HashMap<>();
            if (colFases != null) {
                for (final FaseServicoDTO fase : colFases) {
                    if (fase.getFaseCaptura() != null && fase.getFaseCaptura().equalsIgnoreCase("S")) {
                        mapFasesCaptura.put("" + fase.getIdFase(), fase);
                    }
                }
            }

            Collection colFluxos = fluxoServicoDao.findByIdServicoContrato(solicitacaoServico.getIdServicoContrato());
            final boolean bExisteFluxo = colFluxos != null && !colFluxos.isEmpty();

            for (final TempoAcordoNivelServicoDTO tempoAcordo : colTempos) {
                if (bExisteFluxo) {
                    colFluxos = fluxoServicoDao.findByIdServicoContratoAndIdFase(solicitacaoServico.getIdServicoContrato(), tempoAcordo.getIdFase());
                }

                if (tempoAcordo.getTempoHH() != null) {
                    if (mapFasesCaptura.get("" + tempoAcordo.getIdFase()) != null) {
                        prazoCapturaHH += tempoAcordo.getTempoHH().intValue();
                    }
                    prazoHH += tempoAcordo.getTempoHH().intValue();
                }
                if (tempoAcordo.getTempoMM() != null) {
                    if (mapFasesCaptura.get("" + tempoAcordo.getIdFase()) != null) {
                        prazoCapturaMM += tempoAcordo.getTempoMM().intValue();
                    }
                    prazoMM += tempoAcordo.getTempoMM().intValue();
                }
            }
        }

        if (prazoHH + prazoMM == 0) {
            if (solicitacaoServico.getIdPrioridade() != null && solicitacaoServico.getIdPrioridade().intValue() == 5) {
                solicitacaoServico.setPrazoCapturaHH(0);
                solicitacaoServico.setPrazoCapturaMM(0);
                solicitacaoServico.setPrazoHH(0);
                solicitacaoServico.setPrazoMM(0);
                solicitacaoServico.setSlaACombinar("S");
            } else {
                throw new Exception(this.i18nMessage("solicitacaoservico.prazoacordonivel") + " " + solicitacaoServico.getIdPrioridade());
            }
        }

        while (prazoCapturaMM > 60) {
            prazoCapturaMM = prazoCapturaMM - 60;
            prazoCapturaHH += 1;
        }
        while (prazoMM > 60) {
            prazoMM = prazoMM - 60;
            prazoHH += 1;
        }

        solicitacaoServico.setPrazoCapturaHH(prazoCapturaHH);
        solicitacaoServico.setPrazoCapturaMM(prazoCapturaMM);
        solicitacaoServico.setPrazoHH(prazoHH);
        solicitacaoServico.setPrazoMM(prazoMM);

        // tratamento especial para solicitações a combinar reclassificadas
        SolicitacaoServicoDTO solAux = null;
        if (solicitacaoServico != null && solicitacaoServico.getIdSolicitacaoServico() != null) {
            solAux = (SolicitacaoServicoDTO) this.getDao().restore(solicitacaoServico);
            if (solAux.getDataHoraLimiteStr() != null) {
                if (solAux.getDataHoraLimiteStr().contains("--") && solAux.getSlaACombinar().equalsIgnoreCase("S")) {
                    this.determinaPrazoLimiteSolicitacaoACombinarReclassificada(solicitacaoServico, null, tc);
                    return;
                }
            }

        }

        if (solicitacaoServico.getDataHoraInicio() != null && solicitacaoServico.getDataHoraInicioSLA() == null) {
            solicitacaoServico.setDataHoraInicioSLA(UtilDatas.getDataHoraAtual());
        }

        this.determinaPrazoLimite(solicitacaoServico, null, tc);
    }

    @Override
    public void encerra(final SolicitacaoServicoDTO solicitacaoServico) throws Exception {
        final TransactionControler tc = new TransactionControlerImpl(this.getDao().getAliasDB());
        try {
            tc.start();

            this.encerra(solicitacaoServico, tc);

            tc.commit();

        } catch (final Exception e) {
            this.rollbackTransaction(tc, e);
            throw new ServiceException(e);
        } finally {
            tc.closeQuietly();
        }
    }

    public void encerra(final SolicitacaoServicoDTO solicitacaoServico, final TransactionControler tc) throws Exception {
        final SolicitacaoServicoDTO solicitacaoAuxDto = this.restoreAll(solicitacaoServico.getIdSolicitacaoServico(), tc);
        new ExecucaoSolicitacaoServiceEjb().encerra(solicitacaoAuxDto, tc);
    }

    public void fechaSolicitacaoServicoVinculadaByProblemaOrMudanca(final SolicitacaoServicoDTO solicitacaoServico, final TransactionControler tc)
            throws Exception {
        final SolicitacaoServicoDTO solicitacaoAuxDto = this.restoreAll(solicitacaoServico.getIdSolicitacaoServico(), tc);
        new ExecucaoSolicitacaoServiceEjb().encerra(solicitacaoAuxDto, tc);
        this.getDao().setTransactionControler(tc);
        if (solicitacaoAuxDto != null && solicitacaoAuxDto.getIdSolicitacaoServico() != null) {
            solicitacaoAuxDto.setSituacao("Resolvida");
            this.getDao().updateNotNull(solicitacaoAuxDto);
        }
    }

    @Override
    public Collection findByConhecimento(final BaseConhecimentoDTO baseConhecimento) throws ServiceException, LogicException {
        try {
            return this.getDao().findByConhecimento(baseConhecimento);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Collection findByIdSolictacaoServico(final Integer parm) throws ServiceException, LogicException {
        final SolicitacaoServicoProblemaDao solicitacaoServicoProblemaDao = new SolicitacaoServicoProblemaDao();

        try {
            return solicitacaoServicoProblemaDao.findByIdSolictacaoServico(parm);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByServico(final Integer idServico) throws Exception {
        return this.getDao().findByServico(idServico);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByServico(final Integer idServico, final String nome) throws Exception {
        return this.getDao().findByServico(idServico, nome);
    }

    /**
     * @author breno.guimaraes
     * @return Resumo das solicitaï¿½ï¿½es relacionadas ao clinte passado como argumento.
     */
    @Override
    public ArrayList<SolicitacaoServicoDTO> findSolicitacoesServicosUsuario(final Integer idUsuario, final Integer idItemConfiguracao) {
        Collection<SolicitacaoServicoDTO> solicitacoesSimplificada = null;
        final ArrayList<SolicitacaoServicoDTO> solicitacoesCompleta = new ArrayList<SolicitacaoServicoDTO>();

        final List<Condition> condicoes = new ArrayList<>();
        if (idUsuario != null) {
            condicoes.add(new Condition("idSolicitante", "=", idUsuario.intValue()));
        }

        if (idItemConfiguracao != null) {
            condicoes.add(new Condition("idItemConfiguracao", "=", idItemConfiguracao.intValue()));
        }

        try {
            solicitacoesSimplificada = this.getDao().findByCondition(condicoes, null);
            if (solicitacoesSimplificada != null) {
                for (final SolicitacaoServicoDTO s : solicitacoesSimplificada) {
                    solicitacoesCompleta.add(this.restoreAll(s.getIdSolicitacaoServico()));
                }
            }
        } catch (final ServiceException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return solicitacoesCompleta;
    }

    @Override
    public ArrayList<SolicitacaoServicoDTO> findSolicitacoesServicosUsuario(final Integer idUsuario, String status, final String campoBusca) throws Exception {
        final ArrayList<SolicitacaoServicoDTO> solicitacoesRetorno = new ArrayList<SolicitacaoServicoDTO>();
        if (status == null || status.isEmpty()) {
            status = "EmAndamento";
        }
        final List<SolicitacaoServicoDTO> solicitacoes = this.getSolicitacaoServicoDao().findSolicitacoesServicosUsuario(idUsuario, status, campoBusca);;
        if (solicitacoes != null) {
            for (final SolicitacaoServicoDTO solicitacao : solicitacoes) {
                solicitacoesRetorno.add(this.restoreAll(solicitacao.getIdSolicitacaoServico()));
            }
        }
        return solicitacoesRetorno;
    }

    @Override
    public boolean hasSolicitacoesServicosUsuario(final Integer idUsuario, final String status) throws Exception {
        return this.getSolicitacaoServicoDao().hasSolicitacoesServicosUsuario(idUsuario, status);
    }

    @Override
    protected SolicitacaoServicoDao getDao() {
        if (solicitacaoServicoDao == null) {
            solicitacaoServicoDao = new SolicitacaoServicoDao();
        }
        return solicitacaoServicoDao;
    }

    @Override
    public Collection<SolicitacaoServicoDTO> getHistoricoByIdSolicitacao(final Integer idSolicitacao) throws Exception {
        return this.getSolicitacaoServicoDao().getHistoricoByIdSolicitacao(idSolicitacao);
    }

    @Override
    public ComplemInfSolicitacaoServicoService getInformacoesComplementaresService(final ItemTrabalho itemTrabalho) throws Exception {
        final TemplateSolicitacaoServicoDTO template = new TemplateSolicitacaoServicoServiceEjb().recuperaTemplateServico(itemTrabalho);
        if (template != null) {
            return this.getInformacoesComplementaresService(template.getNomeClasseServico());
        }
        return null;
    }

    public ComplemInfSolicitacaoServicoService getInformacoesComplementaresService(final String nomeClasse) throws Exception {
        final ComplemInfSolicitacaoServicoService informacoesComplementaresService = (ComplemInfSolicitacaoServicoService) Class.forName(nomeClasse)
                .newInstance();
        return informacoesComplementaresService;
    }

    @Override
    public ItemTrabalho getItemTrabalho(final Integer idItemTrabalho) throws Exception {
        return new Tarefa().getItemTrabalho(idItemTrabalho);
    }

    @Override
    public Integer getQuantidadeByIdServico(final int idServico) throws Exception {
        try {
            return this.getDao().getQuantidadeByIdServico(idServico);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer getQuantidadeByIdServicoContrato(final int idServicoContrato) throws Exception {
        try {
            return this.getDao().getQuantidadeByIdServicoContrato(idServicoContrato);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Retorna DAO de solicitacao servico.
     *
     * @return SolicitacaoServicoDao
     * @throws ServiceException
     * @author valdoilo
     */
    public SolicitacaoServicoDao getSolicitacaoServicoDao() throws ServiceException {
        return this.getDao();
    }

    @Override
    public String getUrlInformacoesComplementares(final SolicitacaoServicoDTO solicitacaoServico) throws Exception {
        String url = "";

        final TemplateSolicitacaoServicoDTO templateDto = new TemplateSolicitacaoServicoServiceEjb().recuperaTemplateServico(solicitacaoServico);
        if (templateDto != null) {
            url = Constantes.getValue("CONTEXTO_APLICACAO");
            if (templateDto.isQuestionario()) {
                SolicitacaoServicoQuestionarioDTO solicitacaoServicoQuestionarioDto = null;
                if (solicitacaoServico.getIdSolicitacaoServico() != null) {
                    solicitacaoServicoQuestionarioDto = new SolicitacaoServicoQuestionarioDao().findByIdSolicitacaoServico(solicitacaoServico
                            .getIdSolicitacaoServico());
                }

                url += "/pages/visualizacaoQuestionario/visualizacaoQuestionario.load?tabela100=true";

                if (solicitacaoServicoQuestionarioDto != null && solicitacaoServicoQuestionarioDto.getIdSolicitacaoQuestionario() != null) {
                    url += "&idQuestionario=" + solicitacaoServicoQuestionarioDto.getIdQuestionario();
                    url += "&idIdentificadorResposta=" + solicitacaoServicoQuestionarioDto.getIdSolicitacaoQuestionario();
                    if (solicitacaoServicoQuestionarioDto.getSituacao().equalsIgnoreCase("F")) {
                        url += "&modo=somenteleitura";
                    } else {
                        url += "&modo=edicao";
                    }
                } else {
                    url += "&idQuestionarioOrigem=" + templateDto.getIdQuestionario();
                    url += "&modo=edicao";
                }
                url += "&";
            } else {
                url += templateDto.getUrlRecuperacao();
                url += "?";
            }
            String editar = "S";
            if (solicitacaoServico.getIdSolicitacaoServico() != null && solicitacaoServico.getIdSolicitacaoServico().intValue() > 0) {
                url += "idSolicitacaoServico=" + solicitacaoServico.getIdSolicitacaoServico() + "&";
                if (solicitacaoServico.getIdTarefa() == null) {
                    editar = "N";
                } else {
                    url += "idTarefa=" + solicitacaoServico.getIdTarefa() + "&";
                }
            }
            url += "idServico=2007&idContrato=" + solicitacaoServico.getIdContrato();
            url += "&editar=" + editar;
        }
        return url;
    }

    @Override
    public void gravaInformacoesGED(final Collection colArquivosUpload, final int idEmpresa, final SolicitacaoServicoDTO solicitacaoServico,
            final TransactionControler tc) throws Exception {
        // Setando a transaction no GED
        final ControleGEDDao controleGEDDao = new ControleGEDDao();
        if (tc != null) {
            controleGEDDao.setTransactionControler(tc);
        }

        String PRONTUARIO_GED_DIRETORIO = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.GedDiretorio, "");
        if (PRONTUARIO_GED_DIRETORIO == null || PRONTUARIO_GED_DIRETORIO.trim().equalsIgnoreCase("")) {
            PRONTUARIO_GED_DIRETORIO = "";
        }

        if (PRONTUARIO_GED_DIRETORIO.equalsIgnoreCase("")) {
            PRONTUARIO_GED_DIRETORIO = Constantes.getValue("DIRETORIO_GED");
        }

        if (PRONTUARIO_GED_DIRETORIO == null || PRONTUARIO_GED_DIRETORIO.equalsIgnoreCase("")) {
            PRONTUARIO_GED_DIRETORIO = "/ged";
        }
        String PRONTUARIO_GED_INTERNO = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.GedInterno, "S");
        if (PRONTUARIO_GED_INTERNO == null) {
            PRONTUARIO_GED_INTERNO = "S";
        }
        String prontuarioGedInternoBancoDados = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.GedInternoBD, "N");
        if (!UtilStrings.isNotVazio(prontuarioGedInternoBancoDados)) {
            prontuarioGedInternoBancoDados = "N";
        }
        String pasta = "";
        if (PRONTUARIO_GED_INTERNO.equalsIgnoreCase("S")) {
            pasta = controleGEDDao.getProximaPastaArmazenar();
            File fileDir = new File(PRONTUARIO_GED_DIRETORIO);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            fileDir = new File(PRONTUARIO_GED_DIRETORIO + "/" + idEmpresa);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            fileDir = new File(PRONTUARIO_GED_DIRETORIO + "/" + idEmpresa + "/" + pasta);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
        }

        for (final Iterator it = colArquivosUpload.iterator(); it.hasNext();) {
            final UploadDTO uploadDTO = (UploadDTO) it.next();
            ControleGEDDTO controleGEDDTO = new ControleGEDDTO();
            controleGEDDTO.setIdTabela(ControleGEDDTO.TABELA_SOLICITACAOSERVICO);
            controleGEDDTO.setId(solicitacaoServico.getIdSolicitacaoServico());
            controleGEDDTO.setDataHora(UtilDatas.getDataAtual());
            controleGEDDTO.setDescricaoArquivo(uploadDTO.getDescricao());
            controleGEDDTO.setExtensaoArquivo(Util.getFileExtension(uploadDTO.getNameFile()));
            controleGEDDTO.setPasta(pasta);
            controleGEDDTO.setNomeArquivo(uploadDTO.getNameFile());

            if (!uploadDTO.getTemporario().equalsIgnoreCase("S")) { // Se nao //
                // for //
                // temporario
                continue;
            }

            if (PRONTUARIO_GED_INTERNO.trim().equalsIgnoreCase("S") && "S".equalsIgnoreCase(prontuarioGedInternoBancoDados.trim())) { // Se
                // utiliza
                // GED
                // interno e eh BD
                controleGEDDTO.setPathArquivo(uploadDTO.getPath()); // Isso vai
                // fazer a
                // gravacao
                // no BD.
                // dento do
                // create
                // abaixo.
            } else {
                controleGEDDTO.setPathArquivo(null);
            }
            controleGEDDTO = (ControleGEDDTO) controleGEDDao.create(controleGEDDTO);
            if (controleGEDDTO != null) {
                uploadDTO.setIdControleGED(controleGEDDTO.getIdControleGED());
            }
            if (PRONTUARIO_GED_INTERNO.equalsIgnoreCase("S") && !"S".equalsIgnoreCase(prontuarioGedInternoBancoDados)) { // Se
                // utiliza
                // GED
                // interno e nao eh BD
                if (controleGEDDTO != null) {
                    try {
                        final File arquivo = new File(PRONTUARIO_GED_DIRETORIO + "/" + idEmpresa + "/" + pasta + "/" + controleGEDDTO.getIdControleGED() + "."
                                + Util.getFileExtension(uploadDTO.getNameFile()));
                        CriptoUtils.encryptFile(uploadDTO.getPath(),
                                PRONTUARIO_GED_DIRETORIO + "/" + idEmpresa + "/" + pasta + "/" + controleGEDDTO.getIdControleGED() + ".ged", System
                                .getProperties().get("user.dir") + Constantes.getValue("CAMINHO_CHAVE_PUBLICA"));
                        arquivo.delete();
                    } catch (final Exception e) {

                    }

                }
            } /*
             * else if (!PRONTUARIO_GED_INTERNO.equalsIgnoreCase("S")) { // Se // utiliza // GED // externo // FALTA IMPLEMENTAR!!! }
             */
        }
        final Collection<ControleGEDDTO> colAnexo = controleGEDDao.listByIdTabelaAndIdBaseConhecimento(ControleGEDDTO.TABELA_SOLICITACAOSERVICO,
                solicitacaoServico.getIdSolicitacaoServico());
        if (colAnexo != null) {
            for (final ControleGEDDTO dtoGed : colAnexo) {
                boolean b = false;
                for (final Iterator it = colArquivosUpload.iterator(); it.hasNext();) {
                    final UploadDTO uploadDTO = (UploadDTO) it.next();
                    if (uploadDTO.getIdControleGED() == null) {
                        b = true;
                        break;
                    }
                    if (uploadDTO.getIdControleGED().intValue() == dtoGed.getIdControleGED().intValue()) {
                        b = true;
                    }
                    if (b) {
                        break;
                    }
                }
                if (!b) {
                    controleGEDDao.delete(dtoGed);
                }
            }
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listAll() throws Exception {
        return null;
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listAllIncidentes(final Integer idEmpregado) throws Exception {
        return this.getDao().listAllIncidentes(idEmpregado);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listAllServicos() throws Exception {
        return this.getDao().listAllServicos();
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listAllServicosLikeNomeServico(final String nome) throws Exception {
        return this.getDao().listAllServicosLikeNomeServico(nome);
    }

    @Override
    public Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorFase(final SolicitacaoServicoDTO solicitacaoDto) throws Exception {
        return this.getDao().listaQuantidadeSolicitacaoPorFase(solicitacaoDto);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listarSLA() throws Exception {
        return this.getDao().listarSLA();
    }

    @Override
    public Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorGrupo(final HttpServletRequest request,
            final SolicitacaoServicoDTO solicitacaoDto) throws Exception {

        final Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorGrupo = this.getDao().listaQuantidadeSolicitacaoPorGrupo(
                solicitacaoDto);

        final List<RelatorioQuantitativoSolicitacaoDTO> lista = new ArrayList<RelatorioQuantitativoSolicitacaoDTO>();
        lista.addAll(listaQuantidadeSolicitacaoPorGrupo);

        if (!lista.isEmpty()) {
            String grupoRegistroAnterior = lista.get(0).getGrupo();
            int totalGrupo = 0;
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getGrupo() != null) {
                    if (lista.get(i).getGrupo().equals(grupoRegistroAnterior)) {
                        totalGrupo += lista.get(i).getQuantidadeServico();
                    } else {
                        final RelatorioQuantitativoSolicitacaoDTO relatorioQuantitativoSolicitacaoDTO = new RelatorioQuantitativoSolicitacaoDTO();
                        relatorioQuantitativoSolicitacaoDTO.setServico(UtilI18N.internacionaliza(request, "citcorpore.comum.totalGrupo"));
                        relatorioQuantitativoSolicitacaoDTO.setQuantidadeServico(totalGrupo);
                        relatorioQuantitativoSolicitacaoDTO.setGrupo(grupoRegistroAnterior);
                        lista.add(i++, relatorioQuantitativoSolicitacaoDTO);

                        totalGrupo = lista.get(i).getQuantidadeServico();
                        grupoRegistroAnterior = lista.get(i).getGrupo();
                    }
                }
            }
            final RelatorioQuantitativoSolicitacaoDTO relatorioQuantitativoSolicitacaoDTO = new RelatorioQuantitativoSolicitacaoDTO();
            relatorioQuantitativoSolicitacaoDTO.setServico(UtilI18N.internacionaliza(request, "citcorpore.comum.totalGrupo"));
            relatorioQuantitativoSolicitacaoDTO.setQuantidadeServico(totalGrupo);
            relatorioQuantitativoSolicitacaoDTO.setGrupo(grupoRegistroAnterior);

            lista.add(relatorioQuantitativoSolicitacaoDTO);
        }

        return lista;
    }

    @Override
    public Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorHoraAbertura(final SolicitacaoServicoDTO solicitacaoDto)
            throws Exception {
        return this.getDao().listaQuantidadeSolicitacaoPorHoraAbertura(solicitacaoDto);
    }

    @Override
    public Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorItemConfiguracao(final SolicitacaoServicoDTO solicitacaoDto)
            throws Exception {
        return this.getDao().listaQuantidadeSolicitacaoPorItemConfiguracao(solicitacaoDto);
    }

    @Override
    public Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorOrigem(final SolicitacaoServicoDTO solicitacaoDto) throws Exception {
        return this.getDao().listaQuantidadeSolicitacaoPorOrigem(solicitacaoDto);
    }

    @Override
    public Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorPesquisaSatisfacao(final HttpServletRequest request,
            final SolicitacaoServicoDTO solicitacaoDto) throws Exception {

        final Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorPesquisaSatisfacao = this.getDao()
                .listaQuantidadeSolicitacaoPorPesquisaSatisfacao(solicitacaoDto);

        final List<RelatorioQuantitativoSolicitacaoDTO> lista = new ArrayList<RelatorioQuantitativoSolicitacaoDTO>();
        lista.addAll(listaQuantidadeSolicitacaoPorPesquisaSatisfacao);

        if (!lista.isEmpty()) {
            String grupoRegistroAnterior = lista.get(0).getGrupoPesquisaSatisfacao();
            int totalGrupo = 0;
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getGrupoPesquisaSatisfacao() != null && lista.get(i).getGrupoPesquisaSatisfacao().equals(grupoRegistroAnterior)) {
                    totalGrupo += lista.get(i).getQuantidadePesquisaSatisfacao();
                } else {
                    final RelatorioQuantitativoSolicitacaoDTO relatorioQuantitativoSolicitacaoDTO = new RelatorioQuantitativoSolicitacaoDTO();

                    relatorioQuantitativoSolicitacaoDTO.setServicoPesquisaSatisfacao(UtilI18N.internacionaliza(request, "citcorpore.comum.totalGrupo"));

                    relatorioQuantitativoSolicitacaoDTO.setQuantidadePesquisaSatisfacao(totalGrupo);
                    relatorioQuantitativoSolicitacaoDTO.setGrupoPesquisaSatisfacao(grupoRegistroAnterior);
                    lista.add(i++, relatorioQuantitativoSolicitacaoDTO);

                    totalGrupo = lista.get(i).getQuantidadePesquisaSatisfacao();
                    grupoRegistroAnterior = lista.get(i).getGrupoPesquisaSatisfacao();
                }
            }
            final RelatorioQuantitativoSolicitacaoDTO relatorioQuantitativoSolicitacaoDTO = new RelatorioQuantitativoSolicitacaoDTO();
            relatorioQuantitativoSolicitacaoDTO.setServicoPesquisaSatisfacao(UtilI18N.internacionaliza(request, "citcorpore.comum.totalGrupo"));
            relatorioQuantitativoSolicitacaoDTO.setQuantidadePesquisaSatisfacao(totalGrupo);
            relatorioQuantitativoSolicitacaoDTO.setGrupoPesquisaSatisfacao(grupoRegistroAnterior);

            lista.add(relatorioQuantitativoSolicitacaoDTO);
        }

        return lista;
    }

    @Override
    public Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorPrioridade(final SolicitacaoServicoDTO solicitacaoDto) throws Exception {
        return this.getDao().listaQuantidadeSolicitacaoPorPrioridade(solicitacaoDto);
    }

    @Override
    public Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorResponsavel(final SolicitacaoServicoDTO solicitacaoDto)
            throws Exception {
        return this.getDao().listaQuantidadeSolicitacaoPorResponsavel(solicitacaoDto);
    }

    @Override
    public Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorServico(final SolicitacaoServicoDTO solicitacaoServico)
            throws Exception {
        return this.getDao().listaQuantidadeSolicitacaoPorServico(solicitacaoServico);
    }

    @Override
    public Collection<RelatorioQuantitativoSolicitacaoDTO> listaServicosAbertosAprovados(final SolicitacaoServicoDTO solicitacaoServico) throws Exception {
        return this.getDao().listaServicosAbertosAprovados(solicitacaoServico);
    }

    @Override
    public Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorSituacao(final SolicitacaoServicoDTO solicitacaoDto) throws Exception {

        Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorSituacao = null;
        try {
            listaQuantidadeSolicitacaoPorSituacao = this.getDao().listaQuantidadeSolicitacaoPorSituacao(solicitacaoDto);
            if (listaQuantidadeSolicitacaoPorSituacao != null) {
                for (final RelatorioQuantitativoSolicitacaoDTO relatorioQuantitativoSolicitacaoDTO : listaQuantidadeSolicitacaoPorSituacao) {
                    if (StringUtils.contains(StringUtils.upperCase(relatorioQuantitativoSolicitacaoDTO.getSituacao()), StringUtils.upperCase("EmAndamento"))) {
                        relatorioQuantitativoSolicitacaoDTO.setSituacao("Em Andamento");
                    }
                }
            }
        } catch (final ServiceException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return listaQuantidadeSolicitacaoPorSituacao;
    }

    @Override
    public Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorSituacaoSLA(final HttpServletRequest request,
            final SolicitacaoServicoDTO solicitacaoDto) throws Exception {
        final List<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorSituacaoSLA = this.getDao().listaQuantidadeSolicitacaoPorSituacaoSLA(
                solicitacaoDto);

        // A LISTA DEVE TER APENAS DOIS REGISTROS: QUANTIDADE DE ATRAZOS DE SLA (PRIMEIRO) E QUANTIDADE DE SLA DENTRO DO PRAZO (SEGUNDO)
        if (listaQuantidadeSolicitacaoPorSituacaoSLA != null && listaQuantidadeSolicitacaoPorSituacaoSLA.size() == 2) {
            listaQuantidadeSolicitacaoPorSituacaoSLA.get(0).setSituacaoSLA(UtilI18N.internacionaliza(request, "citcorpore.comum.comAtraso"));
            listaQuantidadeSolicitacaoPorSituacaoSLA.get(1).setSituacaoSLA(UtilI18N.internacionaliza(request, "citcorpore.comum.semAtraso"));
        }

        return listaQuantidadeSolicitacaoPorSituacaoSLA;
    }

    @Override
    public Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorSolicitante(final SolicitacaoServicoDTO solicitacaoDto)
            throws Exception {
        return this.getDao().listaQuantidadeSolicitacaoPorSolicitante(solicitacaoDto);
    }

    @Override
    public Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorTipo(final SolicitacaoServicoDTO solicitacaoDto) throws Exception {
        return this.getDao().listaQuantidadeSolicitacaoPorTipo(solicitacaoDto);
    }

    @Override
    public Collection<RelatorioQuantitativoSolicitacaoDTO> listaQuantidadeSolicitacaoPorTipoServico(final SolicitacaoServicoDTO solicitacaoDto)
            throws Exception {
        return this.getDao().listaQuantidadeSolicitacaoPorTipoServico(solicitacaoDto);
    }

    @Override
    public String listaServico(final Integer idSolicitacaoservico) throws Exception {
        return this.getDao().listaServico(idSolicitacaoservico);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listaSolicitacaoPorBaseConhecimento(final SolicitacaoServicoDTO solicitacao) throws Exception {

        Collection<SolicitacaoServicoDTO> listaSolicitacaoServicoPorBaseConhecimento = null;
        try {
            listaSolicitacaoServicoPorBaseConhecimento = this.getDao().listaSolicitacaoPorBaseConhecimento(solicitacao);
            for (final SolicitacaoServicoDTO solicitacaoServico : listaSolicitacaoServicoPorBaseConhecimento) {
                final Source source = new Source(solicitacaoServico.getDescricao());
                solicitacaoServico.setDescricao(source.getTextExtractor().toString());

                if (StringUtils.contains(StringUtils.upperCase(solicitacaoServico.getSituacao()), StringUtils.upperCase("EmAndamento"))) {
                    solicitacaoServico.setSituacao("Em Andamento");
                }
            }
        } catch (final ServiceException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return listaSolicitacaoServicoPorBaseConhecimento;

    }

    @Override
    public Collection<SolicitacaoServicoDTO> listaSolicitacaoServicoPorCriterios(final PesquisaSolicitacaoServicoDTO pesquisaSolicitacaoServicoDto)
            throws Exception {
        Collection<SolicitacaoServicoDTO> listaSolicitacaoServicoPorCriterios = null;

        try {

            listaSolicitacaoServicoPorCriterios = this.getDao().listaSolicitacaoServicoPorCriterios(pesquisaSolicitacaoServicoDto);
            // Retirando devido ao stress que o mesmo gerava
            /*
             * Timestamp t = UtilDatas.getDataHoraAtual(); for (SolicitacaoServicoDTO solicitacaoServico : listaSolicitacaoServicoPorCriterios) { Source
             * source = new
             * Source(solicitacaoServico.getDescricao()); solicitacaoServico.setDescricao(source.getTextExtractor().toString());
             * if (solicitacaoServico.getSituacao().equalsIgnoreCase("Fechada")) { SolicitacaoServicoDTO solicitacao = new SolicitacaoServicoDTO();
             * solicitacao.setIdSolicitacaoServico(solicitacaoServico.getIdSolicitacaoServico()); solicitacao = (SolicitacaoServicoDTO)
             * this.restore(solicitacao);
             * solicitacaoServico.setTempoAtendimentoHH(solicitacao.getTempoAtendimentoHH());
             * solicitacaoServico.setTempoAtendimentoMM(solicitacao.getTempoAtendimentoMM()); }
             * if (StringUtils.contains(StringUtils.upperCase(solicitacaoServico.getSituacao()), StringUtils.upperCase("EmAndamento"))) {
             * solicitacaoServico.setSituacao("Em Andamento"); }
             * } Timestamp y = UtilDatas.getDataHoraAtual(); System.out.println("Tempo Execução For: " + UtilDatas.calculaDiferencaTempoEmMilisegundos(y, t) );
             */
            // Retirando devido ao stress que o mesmo gerava
            // for (SolicitacaoServicoDTO solicitacaoServico : listaSolicitacaoServicoPorCriterios) {
            // Source source = new Source(solicitacaoServico.getDescricao());
            // solicitacaoServico.setDescricao(source.getTextExtractor().toString());
            /*
             * if (solicitacaoServico.getSituacao().equalsIgnoreCase("Fechada")) { SolicitacaoServicoDTO solicitacao = new SolicitacaoServicoDTO();
             * solicitacao.setIdSolicitacaoServico(solicitacaoServico.getIdSolicitacaoServico()); solicitacao = (SolicitacaoServicoDTO)
             * this.restore(solicitacao);
             * solicitacaoServico.setTempoAtendimentoHH(solicitacao.getTempoAtendimentoHH());
             * solicitacaoServico.setTempoAtendimentoMM(solicitacao.getTempoAtendimentoMM()); }
             * if (StringUtils.contains(StringUtils.upperCase(solicitacaoServico.getSituacao()), StringUtils.upperCase("EmAndamento"))) {
             * solicitacaoServico.setSituacao("Em Andamento"); }
             */

            // }

        } catch (final ServiceException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return listaSolicitacaoServicoPorCriterios;
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listaSolicitacaoServicoPorServicoContrato(final Integer idServicoContratoContabil) throws Exception {
        try {
            return this.getDao().listaSolicitacaoServicoPorServicoContrato(idServicoContratoContabil);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection listaSolicitacoesSemPesquisaSatisfacao() throws Exception {
        return this.getDao().listaSolicitacoesSemPesquisaSatisfacao();
    }

    public Collection<SolicitacaoServicoDTO> listByTarefas(final Collection<TarefaFluxoDTO> listTarefas, final TransactionControler tc) throws Exception {
        if (tc != null) {
            this.getDao().setTransactionControler(tc);
        }

        Collection<SolicitacaoServicoDTO> listSolicitacaoServicoDto = new ArrayList<>();

        listSolicitacaoServicoDto = this.getDao().listByTarefas(listTarefas);

        if (listSolicitacaoServicoDto != null && !listSolicitacaoServicoDto.isEmpty()) {

            for (SolicitacaoServicoDTO solicitacaoDto : listSolicitacaoServicoDto) {

                if (solicitacaoDto != null) {
                    solicitacaoDto.setDataHoraLimiteStr(solicitacaoDto.getDataHoraLimiteStr());
                    solicitacaoDto.setDataHoraInicioSLA(solicitacaoDto.getDataHoraInicioSLA());

                    solicitacaoDto.setNomeServico(solicitacaoDto.getServico());
                    if (solicitacaoDto.getNomeUnidadeSolicitante() != null && !solicitacaoDto.getNomeUnidadeSolicitante().trim().equalsIgnoreCase("")) {
                        solicitacaoDto.setSolicitanteUnidade(solicitacaoDto.getSolicitante() + " (" + solicitacaoDto.getNomeUnidadeSolicitante() + ")");
                    }

                    if (solicitacaoDto.getNomeUnidadeResponsavel() != null && !solicitacaoDto.getNomeUnidadeResponsavel().trim().equalsIgnoreCase("")) {
                        solicitacaoDto.setResponsavel(solicitacaoDto.getResponsavel() + " (" + solicitacaoDto.getNomeUnidadeResponsavel() + ")");
                    }

                    solicitacaoDto = this.verificaSituacaoSLA(solicitacaoDto, tc);
                }
            }
        }
        return listSolicitacaoServicoDto;
    }

    public Collection<SolicitacaoServicoDTO> listByTarefas(final Collection<TarefaFluxoDTO> listTarefas, final TipoSolicitacaoServico[] tiposSolicitacao,
            final TransactionControler tc) throws Exception {
        if (tc != null) {
            this.getDao().setTransactionControler(tc);
        }

        Collection<SolicitacaoServicoDTO> listSolicitacaoServicoDto = new ArrayList<>();

        listSolicitacaoServicoDto = this.getDao().listByTarefas(listTarefas, tiposSolicitacao);

        if (listSolicitacaoServicoDto != null && !listSolicitacaoServicoDto.isEmpty()) {

            for (SolicitacaoServicoDTO solicitacaoDto : listSolicitacaoServicoDto) {

                if (solicitacaoDto != null) {
                    solicitacaoDto.setDataHoraLimiteStr(solicitacaoDto.getDataHoraLimiteStr());
                    solicitacaoDto.setDataHoraInicioSLA(solicitacaoDto.getDataHoraInicioSLA());

                    solicitacaoDto.setNomeServico(solicitacaoDto.getServico());
                    if (solicitacaoDto.getNomeUnidadeSolicitante() != null && !solicitacaoDto.getNomeUnidadeSolicitante().trim().equalsIgnoreCase("")) {
                        solicitacaoDto.setSolicitanteUnidade(solicitacaoDto.getSolicitante() + " (" + solicitacaoDto.getNomeUnidadeSolicitante() + ")");
                    }

                    if (solicitacaoDto.getNomeUnidadeResponsavel() != null && !solicitacaoDto.getNomeUnidadeResponsavel().trim().equalsIgnoreCase("")) {
                        solicitacaoDto.setResponsavel(solicitacaoDto.getResponsavel() + " (" + solicitacaoDto.getNomeUnidadeResponsavel() + ")");
                    }

                    solicitacaoDto = this.verificaSituacaoSLA(solicitacaoDto, tc);
                }
            }
        }
        return listSolicitacaoServicoDto;
    }

    @Override
    public SolicitacaoServicoDTO listIdentificacao(final Integer idItemConfiguracao) throws Exception {

        try {
            return this.getDao().listIdentificacao(idItemConfiguracao);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listIncidentesNaoFinalizados() throws Exception {
        try {
            return this.getSolicitacaoServicoDao().listIncidentesNaoFinalizados();
        } catch (final Exception e) {
            throw e;
        }
    }

    @Override
    public SolicitacaoServicoDTO listInformacaoContato(final String nomeContato) throws Exception {
        try {
            return this.getDao().retornaSolicitacaoServicoComInformacoesDoContato(nomeContato);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Retorna Solicitaçï¿½es de Serviços de acordo com o Tipo de Demanda e Usuário.
     *
     * @param tipoDemandaServico
     * @param grupoSeguranca
     * @param usuario
     * @return
     * @throws Exception
     */
    @Override
    public Collection<SolicitacaoServicoDTO> listSolicitacaoServico(final String tipoDemandaServico, final GrupoDTO grupoSeguranca, final UsuarioDTO usuario,
            final Date dataInicio, final Date dataFim, final String situacao) throws Exception {
        try {
            return this.getSolicitacaoServicoDao().listSolicitacaoServico(tipoDemandaServico, grupoSeguranca, usuario, dataInicio, dataFim, situacao);
        } catch (final Exception e) {
            throw e;
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listSolicitacaoServicoByCriterios(final Collection colCriterios) throws Exception {
        Collection<SolicitacaoServicoDTO> listaSolicitacaoServico = null;

        try {

            listaSolicitacaoServico = this.getDao().listSolicitacaoServicoByCriterios(colCriterios);

            /*
             * for (SolicitacaoServicoDTO solicitacaoServico : listaSolicitacaoServico) { Source source = new Source(solicitacaoServico.getDescricao());
             * solicitacaoServico
             * .setDescricao(source.getTextExtractor().toString());
             * if (solicitacaoServico.getSituacao().equalsIgnoreCase("Fechada")) { SolicitacaoServicoDTO solicitacao = new SolicitacaoServicoDTO();
             * solicitacao.setIdSolicitacaoServico(solicitacaoServico .getIdSolicitacaoServico()); solicitacao = (SolicitacaoServicoDTO)
             * this.restore(solicitacao); solicitacaoServico
             * .setTempoAtendimentoHH(solicitacao.getTempoAtendimentoHH()); solicitacaoServico .setTempoAtendimentoMM(solicitacao.getTempoAtendimentoMM()); }
             * if (StringUtils.contains(StringUtils.upperCase(solicitacaoServico .getSituacao()), StringUtils.upperCase("EmAndamento"))) {
             * solicitacaoServico.setSituacao("Em Andamento"); }
             * }
             */

        } catch (final ServiceException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return listaSolicitacaoServico;
    }

    @Override
    public List<SolicitacaoServicoDTO> listSolicitacaoServicoByItemConfiguracao(final Integer idItemConfiguracao) throws Exception {
        return this.getDao().listSolicitacaoServicoByItemConfiguracao(idItemConfiguracao);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listSolicitacaoServicoEmAndamento(final Integer idSolicitacaoServico) {
        Collection<SolicitacaoServicoDTO> listSolicitacaoServicoEmAndamento = new ArrayList<SolicitacaoServicoDTO>();

        try {

            listSolicitacaoServicoEmAndamento = this.getDao().listSolicitacaoServicoEmAndamento(idSolicitacaoServico);

        } catch (final Exception e) {

            e.printStackTrace();

        }

        return listSolicitacaoServicoEmAndamento;
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listSolicitacaoServicoNaoFinalizadas() throws Exception {
        try {
            return this.getSolicitacaoServicoDao().listSolicitacaoServicoNaoFinalizadas();
        } catch (final Exception e) {
            throw e;
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listSolicitacaoServicoRelacionada(final int idSolicitacaoPai) {

        Collection<SolicitacaoServicoDTO> listSolicitacaoServicoRelacionada = new ArrayList<SolicitacaoServicoDTO>();

        try {

            listSolicitacaoServicoRelacionada = this.getDao().listSolicitacaoServicoRelacionada(idSolicitacaoPai);

        } catch (final Exception e) {

            e.printStackTrace();

        }

        return listSolicitacaoServicoRelacionada;
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listSolicitacaoServicoRelacionadaPai(final int idSolicitacaoPai) {

        Collection<SolicitacaoServicoDTO> listSolicitacaoServicoRelacionada = new ArrayList<SolicitacaoServicoDTO>();

        try {

            listSolicitacaoServicoRelacionada = this.getDao().listSolicitacaoServicoRelacionadaPai(idSolicitacaoPai);

        } catch (final Exception e) {

            e.printStackTrace();

        }

        return listSolicitacaoServicoRelacionada;
    }

    public Collection<SolicitacaoServicoDTO> listSolicitacoesFilhas(final TransactionControler tc) throws Exception {
        if (tc != null) {
            this.getDao().setTransactionControler(tc);
        }
        return this.getDao().listSolicitacoesFilhas();
    }

    @Override
    public SolicitacaoServicoDTO obterQuantidadeSolicitacoesServico(final Integer idServicoContrato, final java.util.Date dataInicio,
            final java.util.Date dataFim) throws Exception {
        try {
            return this.getDao().obterQuantidadeSolicitacoesServico(idServicoContrato, dataInicio, dataFim);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> quantidadeSolicitacaoPorBaseConhecimento(final SolicitacaoServicoDTO solicitacao) throws Exception {
        return this.getDao().quantidadeSolicitacaoPorBaseConhecimento(solicitacao);
    }

    @Override
    public void reabre(final UsuarioDTO usuarioDto, final SolicitacaoServicoDTO solicitacaoServico) throws Exception {
        final TransactionControler tc = new TransactionControlerImpl(this.getDao().getAliasDB());
        try {
            tc.start();

            this.reabre(usuarioDto, solicitacaoServico, tc);

            tc.commit();

        } catch (final Exception e) {
            this.rollbackTransaction(tc, e);
            throw new ServiceException(e);
        } finally {
            tc.closeQuietly();
        }
    }

    public void reabre(final UsuarioDTO usuarioDto, final SolicitacaoServicoDTO solicitacaoServico, final TransactionControler tc) throws Exception {
        final SolicitacaoServicoDTO solicitacaoAuxDto = this.restoreAll(solicitacaoServico.getIdSolicitacaoServico(), tc);
        new ExecucaoSolicitacaoServiceEjb().reabre(usuarioDto, solicitacaoAuxDto, tc);
    }

    @Override
    public void reativa(final UsuarioDTO usuarioDto, final SolicitacaoServicoDTO solicitacaoServico) throws Exception {
        final TransactionControler tc = new TransactionControlerImpl(this.getDao().getAliasDB());
        try {
            tc.start();

            this.reativa(usuarioDto, solicitacaoServico, tc);

            tc.commit();

        } catch (final Exception e) {
            this.rollbackTransaction(tc, e);
            throw new ServiceException(e);
        } finally {
            tc.closeQuietly();
        }
    }

    public void reativa(final UsuarioDTO usuarioDto, final SolicitacaoServicoDTO solicitacaoServico, final TransactionControler tc) throws Exception {
        if (solicitacaoServico.getSituacaoSLA() != null
                && (solicitacaoServico.getSituacaoSLA().equalsIgnoreCase(SituacaoSLA.M.name()) || solicitacaoServico.getSituacaoSLA().equalsIgnoreCase(
                        SituacaoSLA.A.name()))) {
            new ExecucaoSolicitacaoServiceEjb().reativa(usuarioDto, solicitacaoServico, tc);
        } else {
            final SolicitacaoServicoDTO solicitacaoAuxDto = this.restoreAll(solicitacaoServico.getIdSolicitacaoServico(), tc);
            new ExecucaoSolicitacaoServiceEjb().reativa(usuarioDto, solicitacaoAuxDto, tc);
        }

    }

    @Override
    public FluxoDTO recuperaFluxo(final SolicitacaoServicoDTO solicitacaoServico) throws Exception {
        return this.recuperaFluxo(solicitacaoServico, null);
    }

    public FluxoDTO recuperaFluxo(final SolicitacaoServicoDTO solicitacaoServico, final TransactionControler tc) throws Exception {
        if (solicitacaoServico == null || solicitacaoServico.getIdSolicitacaoServico() == null) {
            throw new Exception(this.i18nMessage("solicitacaoservico.validacao.solicitacaoservico"));
        }

        FluxoDTO fluxoDto = null;
        final ExecucaoSolicitacaoDao execucaoSolicitacaoDao = new ExecucaoSolicitacaoDao();
        if (tc != null) {
            execucaoSolicitacaoDao.setTransactionControler(tc);
        }
        final Collection<ExecucaoSolicitacaoDTO> colExecucao = execucaoSolicitacaoDao.listByIdSolicitacao(solicitacaoServico.getIdSolicitacaoServico());
        if (colExecucao != null && !colExecucao.isEmpty()) {
            fluxoDto = new FluxoDTO();
            final ExecucaoSolicitacaoDTO execucaoDto = (ExecucaoSolicitacaoDTO) ((List) colExecucao).get(0);
            fluxoDto.setIdFluxo(execucaoDto.getIdFluxo());
            fluxoDto = (FluxoDTO) new FluxoDao().restore(fluxoDto);
        }

        return fluxoDto;
    }

    @Override
    public Collection<SolicitacaoServicoDTO> relatorioControleSla(final SolicitacaoServicoDTO solicitacao) throws Exception {
        return this.getDao().relatorioControleSla(solicitacao);
    }

    /**
     * @param solicitacaoServicoDTO
     * @return Coleção de Solicitação de serviço com datas horas de sla da solicitacao serviço.
     * @throws Exception
     */
    @Override
    public SolicitacaoServicoDTO relatorioControlePercentualQuantitativoSla(final SolicitacaoServicoDTO solicitacaoServicoDTO) throws Exception {
        final SolicitacaoServicoDTO solicitacaoServicoBean = solicitacaoServicoDTO;

        final List<SolicitacaoServicoDTO> listSolicitacaoservico = (List<SolicitacaoServicoDTO>) this.getDao().relatorioControleSla(solicitacaoServicoDTO);

        /*
         * for (SolicitacaoServicoDTO solicitacaoServicoDTO2 : listSolicitacaoservico) {
         * if(solicitacaoServicoDTO2.getPrazoHH()!= null && solicitacaoServicoDTO2.getPrazoMM()!=null && solicitacaoServicoDTO2.getPrazoHH()==0 &&
         * solicitacaoServicoDTO2.getPrazoMM()==0){
         * solicitacaoServicoDTO2.setAtrasoSLA(0);
         * solicitacaoServicoDTO2.setDataHoraLimiteStr("");
         * solicitacaoServicoDTO2.setDataHoraLimite(null);
         * }
         * }
         */
        this.verificaSituacaoSLA(listSolicitacaoservico);

        solicitacaoServicoBean.setMapPorcentagemSla(this.calculaProcentagemSLAComAtraso(listSolicitacaoservico));

        return solicitacaoServicoBean;
    }

    public Map<String, Object> calculaProcentagemSLAComAtraso(final List<SolicitacaoServicoDTO> listSolicitacaoservico) {

        final int[] totalPrioridade = {0, 0, 0, 0, 0};
        final int[] totalPrioridadeDentroPrazoSla = {0, 0, 0, 0, 0};
        final int[] totalPrioridadeForaPrazoSla = {0, 0, 0, 0, 0};

        double totalPrioridadeDentroPrazo = 0;
        double totalPrioridadeForaDoPrazo = 0;
        double totalPrioridadeGeral = 0;

        double percentualTotalPrioridadeDentroSla = 0;
        double percentualTotalPrioridadeForaSla = 0;

        final Map<String, Object> parametros = new HashMap<String, Object>();

        if (listSolicitacaoservico != null && listSolicitacaoservico.size() > 0) {
            for (final SolicitacaoServicoDTO solicitacaoServicoDTO : listSolicitacaoservico) {

                if (solicitacaoServicoDTO.getIdPrioridade() != null) {
                    switch (solicitacaoServicoDTO.getIdPrioridade().intValue()) {
                    case 1:
                        totalPrioridade[0]++;
                        if (solicitacaoServicoDTO.getAtrasoSLAStr() != null && solicitacaoServicoDTO.getAtrasoSLAStr().equalsIgnoreCase("S")
                                || solicitacaoServicoDTO.getAtrasoSLA() > 0) {
                            totalPrioridadeForaPrazoSla[0]++;
                        } else {
                            totalPrioridadeDentroPrazoSla[0]++;
                        }
                        break;
                    case 2:
                        totalPrioridade[1]++;
                        if (solicitacaoServicoDTO.getAtrasoSLAStr() != null && solicitacaoServicoDTO.getAtrasoSLAStr().equalsIgnoreCase("S")
                                || solicitacaoServicoDTO.getAtrasoSLA() > 0) {
                            totalPrioridadeForaPrazoSla[1]++;
                        } else {
                            totalPrioridadeDentroPrazoSla[1]++;
                        }
                        break;
                    case 3:
                        totalPrioridade[2]++;
                        if (solicitacaoServicoDTO.getAtrasoSLAStr() != null && solicitacaoServicoDTO.getAtrasoSLAStr().equalsIgnoreCase("S")
                                || solicitacaoServicoDTO.getAtrasoSLA() > 0) {
                            totalPrioridadeForaPrazoSla[2]++;
                        } else {
                            totalPrioridadeDentroPrazoSla[2]++;
                        }
                        break;
                    case 4:
                        totalPrioridade[3]++;
                        if (solicitacaoServicoDTO.getAtrasoSLAStr() != null && solicitacaoServicoDTO.getAtrasoSLAStr().equalsIgnoreCase("S")
                                || solicitacaoServicoDTO.getAtrasoSLA() > 0) {
                            totalPrioridadeForaPrazoSla[3]++;
                        } else {
                            totalPrioridadeDentroPrazoSla[3]++;
                        }
                        break;
                    case 5:
                        totalPrioridade[4]++;
                        if (solicitacaoServicoDTO.getAtrasoSLAStr() != null && solicitacaoServicoDTO.getAtrasoSLAStr().equalsIgnoreCase("S")
                                || solicitacaoServicoDTO.getAtrasoSLA() > 0) {
                            totalPrioridadeForaPrazoSla[4]++;
                        } else {
                            totalPrioridadeDentroPrazoSla[4]++;
                        }

                        break;
                    default:
                        break;
                    }
                }
            }

            // Calcula Porcentagem dentro e fora do Prazo individual
            for (int i = 0; totalPrioridade.length > i; i++) {
                double prioridadeDentroPrazoSla = 0;
                double totalPrioridadeSla = 0;
                double prioridadeForaPrazoSla = 0;
                int prioridade = 1;
                prioridade += i;
                if (totalPrioridade[i] > 0) {
                    // Verifica se existe sla dentro do pazo e calcula porcentagem
                    if (totalPrioridadeDentroPrazoSla[i] > 0) {
                        prioridadeDentroPrazoSla = totalPrioridadeDentroPrazoSla[i];
                        totalPrioridadeSla = totalPrioridade[i];
                        parametros
                        .put("percentDentroSlaPrio" + prioridade, UtilFormatacao.formatDouble(prioridadeDentroPrazoSla / totalPrioridadeSla * 100, 2));
                        parametros.put("quantDentroSlaPrio" + prioridade, UtilFormatacao.formatDouble(prioridadeDentroPrazoSla, 0));
                        parametros.put("quantDentroSlaPrio" + prioridade, UtilFormatacao.formatDouble(prioridadeDentroPrazoSla, 0));
                        totalPrioridadeDentroPrazo += prioridadeDentroPrazoSla;
                    } else {
                        parametros.put("percentDentroSlaPrio" + prioridade, "" + 0);
                        parametros.put("quantDentroSlaPrio" + prioridade, UtilFormatacao.formatDouble(prioridadeDentroPrazoSla, 0));
                    }
                    // Verifica se existe sla fora do pazo e calcula porcentagem
                    if (totalPrioridadeForaPrazoSla[i] > 0) {
                        prioridadeForaPrazoSla = totalPrioridadeForaPrazoSla[i];
                        totalPrioridadeSla = totalPrioridade[i];

                        parametros.put("percentForaSlaPrio" + prioridade, UtilFormatacao.formatDouble(prioridadeForaPrazoSla / totalPrioridadeSla * 100, 2));
                        parametros.put("quantForaSlaPrio" + prioridade, UtilFormatacao.formatDouble(prioridadeForaPrazoSla, 0));

                        totalPrioridadeForaDoPrazo += prioridadeForaPrazoSla;
                    } else {
                        parametros.put("percentForaSlaPrio" + prioridade, "" + 0);
                        parametros.put("quantForaSlaPrio" + prioridade, "" + 0);
                    }
                    parametros.put("totalPercentPri" + prioridade, UtilFormatacao.formatDouble(100.00, 0));
                } else {
                    parametros.put("percentDentroSlaPrio" + prioridade, "" + 0);
                    parametros.put("percentForaSlaPrio" + prioridade, "" + 0);
                    parametros.put("quantDentroSlaPrio" + prioridade, "" + 0);
                    parametros.put("quantForaSlaPrio" + prioridade, "" + 0);
                    parametros.put("totalPercentPri" + prioridade, "" + 0);
                }

                parametros.put("totalQuantPri" + prioridade, UtilFormatacao.formatDouble(prioridadeForaPrazoSla + prioridadeDentroPrazoSla, 0));
            }
            // Calcula porcentagem dentro e fora do prazo total
            totalPrioridadeGeral = listSolicitacaoservico.size();
            percentualTotalPrioridadeDentroSla = totalPrioridadeDentroPrazo / totalPrioridadeGeral * 100;
            percentualTotalPrioridadeForaSla = totalPrioridadeForaDoPrazo / totalPrioridadeGeral * 100;

            // Carrega objeto de valores.
            parametros.put("totalPrioridadeDentroPrazo", "" + UtilFormatacao.formatDouble(totalPrioridadeDentroPrazo, 0));
            parametros.put("totalPrioridadeForaDoPrazo", "" + UtilFormatacao.formatDouble(totalPrioridadeForaDoPrazo, 0));
            parametros.put("totalPrioridadeGeral", "" + UtilFormatacao.formatDouble(totalPrioridadeGeral, 0));
            parametros.put("percentualTotalPrioridadeDentroSla", UtilFormatacao.formatDouble(percentualTotalPrioridadeDentroSla, 2));
            parametros.put("percentualTotalPrioridadeForaSla", UtilFormatacao.formatDouble(percentualTotalPrioridadeForaSla, 2));
            parametros.put("percentualTotalPrioridadeSla",
                    UtilFormatacao.formatDouble(percentualTotalPrioridadeForaSla + percentualTotalPrioridadeDentroSla, 2));

        }
        return parametros;
    }

    public Collection<SolicitacaoServicoDTO> listarSLA(final SolicitacaoServicoDTO solicitacao) throws Exception {
        return this.getDao().relatorioControleSla(solicitacao);
    }

    @Override
    public SolicitacaoServicoDTO restoreAll(final Integer idSolicitacaoServico) throws Exception {
        return this.restoreAll(idSolicitacaoServico, null);
    }

    public SolicitacaoServicoDTO restoreAll(final Integer idSolicitacaoServico, final TransactionControler tc) throws Exception {
        if (tc != null) {
            this.getDao().setTransactionControler(tc);
        }
        SolicitacaoServicoDTO solicitacaoDto = null;
        try {
            solicitacaoDto = this.getDao().restoreAll(idSolicitacaoServico);
        } catch (final Exception e) {
            throw new Exception(this.i18nMessage("solicitacaoservico.erro.recuperardadosolicitacao") + " " + idSolicitacaoServico);
        }

        if (solicitacaoDto != null) {
            // Parece estranho, mas isto executa o metodo interno do DTO. Isto
            // eh necessario!!!
            solicitacaoDto.setDataHoraLimiteStr(solicitacaoDto.getDataHoraLimiteStr());

            solicitacaoDto.setNomeServico(solicitacaoDto.getServico());
            if (solicitacaoDto.getNomeUnidadeSolicitante() != null && !solicitacaoDto.getNomeUnidadeSolicitante().trim().equalsIgnoreCase("")) {
                solicitacaoDto.setSolicitanteUnidade(solicitacaoDto.getSolicitante() + " (" + solicitacaoDto.getNomeUnidadeSolicitante() + ")");
            } else {
                solicitacaoDto.setSolicitanteUnidade(solicitacaoDto.getSolicitante());
            }
            if (solicitacaoDto.getNomeUnidadeResponsavel() != null && !solicitacaoDto.getNomeUnidadeResponsavel().trim().equalsIgnoreCase("")) {
                solicitacaoDto.setResponsavel(solicitacaoDto.getResponsavel() + " (" + solicitacaoDto.getNomeUnidadeResponsavel() + ")");
            }
        }
        return this.verificaSituacaoSLA(solicitacaoDto, tc);
    }

    public SolicitacaoServicoDTO restoreByIdInstanciaFluxo(final Integer idInstanciaFluxo, final TransactionControler tc) throws Exception {
        if (tc != null) {
            this.getDao().setTransactionControler(tc);
        }
        SolicitacaoServicoDTO solicitacaoDto = null;
        try {
            solicitacaoDto = this.getDao().restoreByIdInstanciaFluxo(idInstanciaFluxo);
        } catch (final Exception e) {
            System.out.println("CITSMART - Erro na recuperação dos dados da solicitação da instância fluxo" + " " + idInstanciaFluxo);

            e.printStackTrace();
        }

        if (solicitacaoDto != null) {
            // Parece estranho, mas isto executa o metodo interno do DTO. Isto eh necessario!!!
            solicitacaoDto.setDataHoraLimiteStr(solicitacaoDto.getDataHoraLimiteStr());

            solicitacaoDto.setNomeServico(solicitacaoDto.getServico());
            if (solicitacaoDto.getNomeUnidadeSolicitante() != null && !solicitacaoDto.getNomeUnidadeSolicitante().trim().equalsIgnoreCase("")) {
                solicitacaoDto.setSolicitanteUnidade(solicitacaoDto.getSolicitante() + " (" + solicitacaoDto.getNomeUnidadeSolicitante() + ")");
            }

            if (solicitacaoDto.getNomeUnidadeResponsavel() != null && !solicitacaoDto.getNomeUnidadeResponsavel().trim().equalsIgnoreCase("")) {
                solicitacaoDto.setResponsavel(solicitacaoDto.getResponsavel() + " (" + solicitacaoDto.getNomeUnidadeResponsavel() + ")");
            }
        }
        return this.verificaSituacaoSLA(solicitacaoDto, tc);
    }

    @Override
    public SolicitacaoServicoDTO restoreByIdInstanciaFluxo(final Integer idInstanciaFluxo) throws Exception {
        return this.restoreByIdInstanciaFluxo(idInstanciaFluxo, null);
    }

    @Override
    public SolicitacaoServicoDTO retornaSolicitacaoServicoComItemConfiguracaoDoSolicitante(final String login) throws Exception {
        try {
            return this.getDao().retornaSolicitacaoServicoComItemConfiguracaoDoSolicitante(login);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void suspende(final UsuarioDTO usuarioDto, final SolicitacaoServicoDTO solicitacaoServico) throws Exception {
        final TransactionControler tc = new TransactionControlerImpl(this.getDao().getAliasDB());
        try {
            tc.start();
            this.suspende(usuarioDto, solicitacaoServico, tc);
            tc.commit();
        } catch (final Exception e) {
            this.rollbackTransaction(tc, e);
            throw new ServiceException(e);
        } finally {
            tc.closeQuietly();
        }
    }

    public void suspende(final UsuarioDTO usuarioDto, final SolicitacaoServicoDTO solicitacaoServico, final TransactionControler tc) throws Exception {
        if (solicitacaoServico.getSituacaoSLA() != null
                && (solicitacaoServico.getSituacaoSLA().equalsIgnoreCase(SituacaoSLA.M.name()) || solicitacaoServico.getSituacaoSLA().equalsIgnoreCase(
                        SituacaoSLA.A.name()))) {
            new ExecucaoSolicitacaoServiceEjb().suspende(usuarioDto, solicitacaoServico, tc);
        } else {
            final SolicitacaoServicoDTO solicitacaoAuxDto = this.restoreAll(solicitacaoServico.getIdSolicitacaoServico(), tc);
            solicitacaoAuxDto.setIdJustificativa(solicitacaoServico.getIdJustificativa());
            solicitacaoAuxDto.setComplementoJustificativa(solicitacaoServico.getComplementoJustificativa());
        }
    }

    @Override
    public boolean temSolicitacaoServicoAbertaDoEmpregado(final Integer idEmpregado) {
        List retorno = null;
        final ArrayList<Condition> condicoes = new ArrayList<Condition>();
        condicoes.add(new Condition("idSolicitante", "=", idEmpregado));
        try {
            retorno = (List) this.getDao().findByCondition(condicoes, null);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        if (retorno != null && retorno.size() > 0) {
            return retorno.get(0) == null ? false : true;
        } else {
            return false;
        }
    }

    @Override
    public void update(final BaseEntity model) throws LogicException, ServiceException {
        final ExecucaoSolicitacaoServiceEjb execucaoSolicitacaoService = new ExecucaoSolicitacaoServiceEjb();
        final TransactionControler tc = new TransactionControlerImpl(this.getDao().getAliasDB());
        try {
            tc.start();

            // Faz validacao, caso exista.
            this.validaUpdate(model);

            this.getDao().setTransactionControler(tc);
            this.getDao().updateNotNull(model);

            // Executa operacoes pertinentes ao negocio.
            final SolicitacaoServicoDTO solicitacaoServico = (SolicitacaoServicoDTO) model;

            // valida email
            if (solicitacaoServico.getEmailcontato() != null && !Util.isValidEmailAddress(solicitacaoServico.getEmailcontato())) {
                throw new LogicException(this.i18nMessage("citcorpore.validacao.emailInvalido"));
            }

            if (solicitacaoServico.getIdTarefa() != null) {
                execucaoSolicitacaoService.executa(solicitacaoServico, solicitacaoServico.getIdTarefa(), solicitacaoServico.getAcaoFluxo(), tc);
            }

            tc.commit();

        } catch (final Exception e) {
            this.rollbackTransaction(tc, e);
            throw new ServiceException(e);
        } finally {
            tc.closeQuietly();
        }
    }

    @Override
    public BaseEntity updateInfo(final BaseEntity model) throws ServiceException, LogicException {
        final SolicitacaoServicoDTO solicitacaoServico = (SolicitacaoServicoDTO) model;
        final TransactionControler tc = new TransactionControlerImpl(this.getDao().getAliasDB());
        try {
            tc.start();
            this.validaCreate(model);
            this.updateInfo(solicitacaoServico, tc, true);
            tc.commit();
        } catch (final Exception e) {
            this.rollbackTransaction(tc, e);
            throw new ServiceException(e);
        } finally {
            tc.closeQuietly();
        }
        return solicitacaoServico;
    }

    /**
     * Faz a atualizacao de informacoes da solicitacao, mas nao altera as informacoes da classificacao.
     *
     * @param model
     * @return
     * @throws ServiceException
     * @throws LogicException
     */
    public SolicitacaoServicoDTO updateInfo(SolicitacaoServicoDTO solicitacaoServico, final TransactionControler tc, final boolean atualizarRelacionados)
            throws Exception {
        boolean mudancaDescricao = false;
        final ExecucaoSolicitacaoServiceEjb execucaoSolicitacaoService = new ExecucaoSolicitacaoServiceEjb();
        final ContatoSolicitacaoServicoDao contatoSolicitacaoServicoDao = new ContatoSolicitacaoServicoDao();
        final SolicitacaoServicoProblemaDao solicitacaoServicoProblemaDao = new SolicitacaoServicoProblemaDao();
        final SolicitacaoServicoMudancaDao solicitacaoServicoMudancaDao = new SolicitacaoServicoMudancaDao();
        final ConhecimentoSolicitacaoDao conhecimentoSolicitacaoDao = new ConhecimentoSolicitacaoDao();
        final ItemCfgSolicitacaoServDAO itemCfgSolicitacaoServDAO = new ItemCfgSolicitacaoServDAO();
        final ServicoContratoDao servicoContratoDao = new ServicoContratoDao();
        final GrupoDao grupoDao = new GrupoDao();

        ContatoSolicitacaoServicoDTO contatoSolicitacaoServicoDTO = new ContatoSolicitacaoServicoDTO();

        final SolicitacaoServicoDTO dtoAux = this.restoreAll(solicitacaoServico.getIdSolicitacaoServico());

        if (dtoAux != null && solicitacaoServico != null && dtoAux.getDescricao() != null && StringUtils.isNotEmpty(dtoAux.getDescricao())
                && solicitacaoServico.getDescricao() != null && StringUtils.isNotEmpty(solicitacaoServico.getDescricao())) {
            if (solicitacaoServico.getDescricao().hashCode() == dtoAux.getDescricao().hashCode()) {
                mudancaDescricao = true;
            }
        }

        this.getDao().setTransactionControler(tc);
        contatoSolicitacaoServicoDao.setTransactionControler(tc);
        solicitacaoServicoProblemaDao.setTransactionControler(tc);
        itemCfgSolicitacaoServDAO.setTransactionControler(tc);
        solicitacaoServicoMudancaDao.setTransactionControler(tc);
        conhecimentoSolicitacaoDao.setTransactionControler(tc);
        servicoContratoDao.setTransactionControler(tc);
        grupoDao.setTransactionControler(tc);

        // valida email
        if (solicitacaoServico.getEmailcontato() != null && !Util.isValidEmailAddress(solicitacaoServico.getEmailcontato())) {
            throw new LogicException(this.i18nMessage("citcorpore.validacao.emailInvalido"));
        }

        // Executa operacoes pertinentes ao negocio.
        solicitacaoServico.setIdContatoSolicitacaoServico(contatoSolicitacaoServicoDTO.getIdcontatosolicitacaoservico());
        String nomeServicoAnterior = "";
        String nomeContratoAnterior = "";
        String strDescricaoAnterior = "";
        final SolicitacaoServicoDTO solicitacaoServicoAux = (SolicitacaoServicoDTO) this.getDao().restore(solicitacaoServico);
        if (solicitacaoServicoAux != null && solicitacaoServico.getIdGrupoNivel1() == null) {
            solicitacaoServico.setIdGrupoNivel1(solicitacaoServicoAux.getIdGrupoNivel1());
        }

        if (solicitacaoServico.getReclassificar() != null && solicitacaoServico.getReclassificar().equalsIgnoreCase("S")) {
            final ServicoContratoDTO servicoContratoDto = servicoContratoDao.findByIdContratoAndIdServico(solicitacaoServico.getIdContrato(),
                    solicitacaoServico.getIdServico());
            if (servicoContratoDto == null) {
                throw new LogicException(this.i18nMessage("solicitacaoservico.validacao.servicolocalizado"));
            }

            if (solicitacaoServico.getIdServicoContrato() == null) {
                solicitacaoServico.setIdServicoContrato(servicoContratoDto.getIdServicoContrato());
            }

            if (solicitacaoServico.getIdGrupoNivel1() == null || solicitacaoServico.getIdGrupoNivel1().intValue() <= 0) {
                Integer idGrupoNivel1 = null;
                if (servicoContratoDto.getIdGrupoNivel1() != null && servicoContratoDto.getIdGrupoNivel1().intValue() > 0) {
                    idGrupoNivel1 = servicoContratoDto.getIdGrupoNivel1();
                } else {
                    final String idGrupoN1 = ParametroUtil.getValor(ParametroSistema.ID_GRUPO_PADRAO_NIVEL1, tc, null);
                    if (idGrupoN1 != null && !idGrupoN1.trim().equalsIgnoreCase("")) {
                        try {
                            idGrupoNivel1 = new Integer(idGrupoN1);
                        } catch (final Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (idGrupoNivel1 == null || idGrupoNivel1.intValue() <= 0) {
                    throw new LogicException(this.i18nMessage("solicitacaoservico.validacao.grupoatendnivel"));
                }
                GrupoDTO grupoDto = new GrupoDTO();
                grupoDto.setIdGrupo(idGrupoNivel1);
                grupoDto = (GrupoDTO) grupoDao.restore(grupoDto);
                if (grupoDto == null || grupoDto.getDataFim() != null) {
                    throw new LogicException(this.i18nMessage("solicitacaoservico.validacao.grupoatendnivel"));
                }
                solicitacaoServico.setIdGrupoNivel1(idGrupoNivel1);
            }

            if (solicitacaoServicoAux != null) {

                solicitacaoServico.setDataHoraSolicitacao(solicitacaoServicoAux.getDataHoraSolicitacao());
                solicitacaoServico.setDataHoraInicio(solicitacaoServicoAux.getDataHoraInicio());
                solicitacaoServico.setDataHoraCaptura(solicitacaoServicoAux.getDataHoraCaptura());
                strDescricaoAnterior = solicitacaoServicoAux.getDescricao();

                ServicoContratoDTO servicoContratoDTO = new ServicoContratoDTO();
                servicoContratoDTO.setIdServicoContrato(solicitacaoServicoAux.getIdServicoContrato());
                servicoContratoDTO = (ServicoContratoDTO) servicoContratoDao.restore(servicoContratoDTO);
                if (servicoContratoDTO != null) {
                    final ServicoDao servicoDao = new ServicoDao();
                    servicoDao.setTransactionControler(tc);
                    ServicoDTO servicoDto = new ServicoDTO();
                    servicoDto.setIdServico(servicoContratoDTO.getIdServico());
                    servicoDto = (ServicoDTO) servicoDao.restore(servicoDto);
                    if (servicoDto != null) {
                        nomeServicoAnterior = this.i18nMessage("citcorpore.comum.codigo") + ": " + servicoDto.getIdServico() + " - "
                                + this.i18nMessage("citcorpore.comum.nome") + ": " + servicoDto.getNomeServico();
                    }
                    final ContratoDao contratoDao = new ContratoDao();
                    contratoDao.setTransactionControler(tc);
                    ContratoDTO contratoDto = new ContratoDTO();
                    contratoDto.setIdContrato(servicoContratoDTO.getIdContrato());
                    contratoDto = (ContratoDTO) contratoDao.restore(contratoDto);
                    if (contratoDto != null) {
                        nomeContratoAnterior = this.i18nMessage("citcorpore.comum.codigo") + ": " + contratoDto.getIdContrato() + " - "
                                + this.i18nMessage("citcorpore.comum.numero") + ": " + contratoDto.getNumero();
                    }
                }
            }

            if (solicitacaoServico.getIdGrupoNivel1() == null || solicitacaoServico.getIdGrupoNivel1().intValue() <= 0) {
                Integer idGrupoNivel1 = null;
                if (servicoContratoDto.getIdGrupoNivel1() != null) {
                    idGrupoNivel1 = servicoContratoDto.getIdGrupoNivel1();
                } else {
                    final String idGrupoN1 = ParametroUtil.getValor(ParametroSistema.ID_GRUPO_PADRAO_NIVEL1, tc, null);
                    if (idGrupoN1 != null && !idGrupoN1.trim().equalsIgnoreCase("")) {
                        try {
                            idGrupoNivel1 = new Integer(idGrupoN1);
                        } catch (final Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (idGrupoNivel1 == null || idGrupoNivel1.intValue() <= 0) {
                    throw new LogicException(this.i18nMessage("solicitacaoservico.validacao.grupoatendnivel"));
                }
                solicitacaoServico.setIdGrupoNivel1(idGrupoNivel1);
            }

            if (solicitacaoServico.getIdGrupoNivel1() == null || solicitacaoServico.getIdGrupoNivel1().intValue() <= 0) {
                Integer idGrupoNivel1 = null;
                if (servicoContratoDto.getIdGrupoNivel1() != null) {
                    idGrupoNivel1 = servicoContratoDto.getIdGrupoNivel1();
                } else {
                    final String idGrupoN1 = ParametroUtil.getValor(ParametroSistema.ID_GRUPO_PADRAO_NIVEL1, tc, null);
                    if (idGrupoN1 != null && !idGrupoN1.trim().equalsIgnoreCase("")) {
                        try {
                            idGrupoNivel1 = new Integer(idGrupoN1);
                        } catch (final Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (idGrupoNivel1 == null || idGrupoNivel1.intValue() <= 0) {
                    throw new LogicException("Grupo de atendimento nivel 1 não parametrizado");
                }
                solicitacaoServico.setIdGrupoNivel1(idGrupoNivel1);
            }

            this.determinaPrioridadeEPrazo(solicitacaoServico, tc);
        } else {
            solicitacaoServico.setIdServico(null);
            solicitacaoServico.setIdServicoContrato(null);
            solicitacaoServico.setIdPrioridade(null);
            solicitacaoServico.setPrazoCapturaHH(null);
            solicitacaoServico.setPrazoCapturaMM(null);
            solicitacaoServico.setPrazoHH(null);
            solicitacaoServico.setPrazoMM(null);
            solicitacaoServico.setDataHoraInicio(null);
            solicitacaoServico.setDataHoraFim(null);
            solicitacaoServico.setDataHoraLimite(null);
            solicitacaoServico.setDataHoraSolicitacao(null);
            solicitacaoServico.setIdTipoDemandaServico(null);
            solicitacaoServico.setIdFaseAtual(null);
            solicitacaoServico.setSlaACombinar(null);
        }

        if (solicitacaoServicoAux != null && !solicitacaoServicoAux.escalada() && solicitacaoServico.escalada()) {
            final String tipoCaptura = ParametroUtil.getValor(ParametroSistema.TIPO_CAPTURA_SOLICITACOES, tc, "1");
            if (tipoCaptura.equals("2")) {
                solicitacaoServico.setDataHoraCaptura(solicitacaoServico.getDataHoraInicio());
            }
        }

        this.getDao().updateNotNull(solicitacaoServico);

        if (solicitacaoServicoAux != null && solicitacaoServicoAux.getIdTarefaEncerramento() == null && solicitacaoServico.atendida()) {
            if (solicitacaoServico.getIdTarefa() != null
                    && (solicitacaoServico.getAcaoFluxo().equalsIgnoreCase(br.com.centralit.bpm.util.Enumerados.ACAO_INICIAR) || solicitacaoServico
                            .getAcaoFluxo().equalsIgnoreCase(br.com.centralit.bpm.util.Enumerados.ACAO_EXECUTAR))) {
                solicitacaoServico.setIdTarefaEncerramento(solicitacaoServico.getIdTarefa());
                this.getDao().atualizaIdTarefaEncerramento(solicitacaoServico);
            }
        } else if (solicitacaoServicoAux != null && solicitacaoServicoAux.atendida() && !solicitacaoServico.atendida()) {
            solicitacaoServico.setIdTarefaEncerramento(null);
            this.getDao().atualizaIdTarefaEncerramento(solicitacaoServico);
        }

        if (solicitacaoServico.getInformacoesComplementares() != null || solicitacaoServico.getSolicitacaoServicoQuestionarioDTO() != null) {
            final TemplateSolicitacaoServicoDTO templateDto = new TemplateSolicitacaoServicoServiceEjb().recuperaTemplateServico(solicitacaoServico);
            if (templateDto != null) {
                if (templateDto.isQuestionario()) {
                    this.atualizaInformacoesQuestionario(solicitacaoServico, tc);
                } else if (templateDto.getNomeClasseServico() != null) {
                    final ComplemInfSolicitacaoServicoService informacoesComplementaresService = this.getInformacoesComplementaresService(templateDto
                            .getNomeClasseServico());
                    informacoesComplementaresService.update(tc, solicitacaoServico, solicitacaoServico.getInformacoesComplementares());
                }
            }
        }

        final UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin(solicitacaoServico.getRegistradoPor());
        if (solicitacaoServico.getReclassificar() != null && solicitacaoServico.getReclassificar().equalsIgnoreCase("S")) {
            String strRecl = this.i18nMessage("citcorpore.comum.reclassificaosolicitacao");
            strRecl += "\n" + this.i18nMessage("citcorpore.comum.servicoanterior") + ": " + nomeServicoAnterior;
            strRecl += "\n" + this.i18nMessage("citcorpore.comum.contratoanterior") + ": " + nomeContratoAnterior;
            strRecl += "\n" + this.i18nMessage("citcorpore.comum.descricaoanterior") + ": " + strDescricaoAnterior;
            OcorrenciaSolicitacaoServiceEjb.create(solicitacaoServico, null, strRecl, OrigemOcorrencia.OUTROS, CategoriaOcorrencia.Reclassificacao, null,
                    CategoriaOcorrencia.Reclassificacao.getDescricao(), usuarioDTO, 0, null, tc);
        }
        if (solicitacaoServico.getRegistroexecucao() != null && !solicitacaoServico.getRegistroexecucao().trim().equalsIgnoreCase("")) {
            OcorrenciaSolicitacaoServiceEjb.create(solicitacaoServico, null, solicitacaoServico.getRegistroexecucao(), OrigemOcorrencia.OUTROS,
                    CategoriaOcorrencia.Execucao, null, CategoriaOcorrencia.Execucao.getDescricao(), usuarioDTO, 0, null, tc);
        }

        if (atualizarRelacionados) {
            contatoSolicitacaoServicoDTO.setNomecontato(solicitacaoServico.getNomecontato());
            contatoSolicitacaoServicoDTO.setEmailcontato(solicitacaoServico.getEmailcontato());
            contatoSolicitacaoServicoDTO.setTelefonecontato(solicitacaoServico.getTelefonecontato());
            contatoSolicitacaoServicoDTO.setObservacao(solicitacaoServico.getObservacao());
            contatoSolicitacaoServicoDTO.setRamal(solicitacaoServico.getRamal());

            if (CITCorporeUtil.SGBD_PRINCIPAL.equalsIgnoreCase("SQLSERVER")) {
                if (tc != null) {
                    contatoSolicitacaoServicoDao.setTransactionControler(tc);
                }
            }

            if (solicitacaoServico.getIdLocalidade() != null) {
                contatoSolicitacaoServicoDTO.setIdLocalidade(solicitacaoServico.getIdLocalidade());
            }

            if (solicitacaoServico.getIdContatoSolicitacaoServico() != null) {
                contatoSolicitacaoServicoDTO.setIdcontatosolicitacaoservico(solicitacaoServico.getIdContatoSolicitacaoServico());
                contatoSolicitacaoServicoDao.update(contatoSolicitacaoServicoDTO);
            } else {
                contatoSolicitacaoServicoDTO = (ContatoSolicitacaoServicoDTO) contatoSolicitacaoServicoDao.create(contatoSolicitacaoServicoDTO);
            }

            solicitacaoServicoProblemaDao.deleteByIdSolictacaoServico(solicitacaoServico.getIdSolicitacaoServico());
            if (solicitacaoServico.getColItensProblema() != null) {
                solicitacaoServicoProblemaDao.deleteByIdSolictacaoServico(solicitacaoServico.getIdSolicitacaoServico());
                for (final Iterator it = solicitacaoServico.getColItensProblema().iterator(); it.hasNext();) {
                    final ProblemaDTO problemaDTO = (ProblemaDTO) it.next();
                    final SolicitacaoServicoProblemaDTO solicitacaoServicoProblemaDTO = new SolicitacaoServicoProblemaDTO();
                    solicitacaoServicoProblemaDTO.setIdProblema(problemaDTO.getIdProblema());
                    solicitacaoServicoProblemaDTO.setIdSolicitacaoServico(solicitacaoServico.getIdSolicitacaoServico());
                    solicitacaoServicoProblemaDao.create(solicitacaoServicoProblemaDTO);
                }

                // solicitacaoServicoProblemaDao
            }

            solicitacaoServicoMudancaDao.deleteByIdSolictacaoServico(solicitacaoServico.getIdSolicitacaoServico());
            if (solicitacaoServico.getColItensMudanca() != null) {
                for (final Iterator it = solicitacaoServico.getColItensMudanca().iterator(); it.hasNext();) {
                    final RequisicaoMudancaDTO requisicaoMudancaDTO = (RequisicaoMudancaDTO) it.next();

                    final SolicitacaoServicoMudancaDTO solicitacaoServicoMudancaDTO = new SolicitacaoServicoMudancaDTO();
                    solicitacaoServicoMudancaDTO.setIdRequisicaoMudanca(requisicaoMudancaDTO.getIdRequisicaoMudanca());
                    solicitacaoServicoMudancaDTO.setIdSolicitacaoServico(solicitacaoServico.getIdSolicitacaoServico());
                    solicitacaoServicoMudancaDao.create(solicitacaoServicoMudancaDTO);
                }

                // solicitacaoServicoProblemaDao
            }

            conhecimentoSolicitacaoDao.deleteByIdSolictacaoServico(solicitacaoServico.getIdSolicitacaoServico());
            if (solicitacaoServico.getColItensBaseConhecimento() != null) {
                for (final Object element : solicitacaoServico.getColItensBaseConhecimento()) {
                    final BaseConhecimentoDTO baseConhecimentoDTO = (BaseConhecimentoDTO) element;

                    final ConhecimentoSolicitacaoDTO conhecimentoSolicitacaoDTO = new ConhecimentoSolicitacaoDTO();
                    conhecimentoSolicitacaoDTO.setIdBaseConhecimento(baseConhecimentoDTO.getIdBaseConhecimento());
                    conhecimentoSolicitacaoDTO.setIdSolicitacaoServico(solicitacaoServico.getIdSolicitacaoServico());
                    conhecimentoSolicitacaoDao.create(conhecimentoSolicitacaoDTO);
                }

                // solicitacaoServicoProblemaDao
            }

            // PersistirItemBaseConhecimento(solicitacaoServico, conhecimentoSolicitacaoDao);

            itemCfgSolicitacaoServDAO.deleteByIdSolicitacaoServico(solicitacaoServico.getIdSolicitacaoServico());

            if (solicitacaoServico.getColItensICSerialize() != null) {
                for (final ItemConfiguracaoDTO bean : solicitacaoServico.getColItensICSerialize()) {
                    final ItemCfgSolicitacaoServDTO dto = new ItemCfgSolicitacaoServDTO();
                    dto.setIdItemConfiguracao(bean.getIdItemConfiguracao());
                    dto.setIdSolicitacaoServico(solicitacaoServico.getIdSolicitacaoServico());
                    dto.setDataInicio(Util.getSqlDataAtual());

                    itemCfgSolicitacaoServDAO.create(dto);

                    solicitacaoServico = this.verificaSituacaoSLA(solicitacaoServico, tc);
                }
            }

            if (solicitacaoServico.getColArquivosUpload() != null) {
                this.gravaInformacoesGED(solicitacaoServico.getColArquivosUpload(), 1, solicitacaoServico, tc);
            }

            if (solicitacaoServico.getSituacao() != null && solicitacaoServico.getSituacao().equalsIgnoreCase("Resolvida")) {
                if (solicitacaoServico.getBeanBaseConhecimento().getTitulo() != null && !solicitacaoServico.getBeanBaseConhecimento().getTitulo().isEmpty()) {
                    this.InserirNaBaseConhecimento(solicitacaoServico, tc);
                }
            }
        }

        if (solicitacaoServico.getIdTarefa() != null) {
            execucaoSolicitacaoService.executa(solicitacaoServico, solicitacaoServico.getIdTarefa(), solicitacaoServico.getAcaoFluxo(), tc);
        }

        if (solicitacaoServicoAux != null && solicitacaoServico.getIdGrupoAtual() != null
                && UtilStrings.nullToVazio(solicitacaoServico.getAcaoFluxo()).equals(br.com.centralit.bpm.util.Enumerados.ACAO_EXECUTAR)
                && (!solicitacaoServicoAux.escalada() || solicitacaoServicoAux.getIdGrupoAtual().intValue() != solicitacaoServico.getIdGrupoAtual().intValue())) {
            execucaoSolicitacaoService.direcionaAtendimento(solicitacaoServico, tc);
        }

        /*
         * Bruno.aquino 29/05/2014
         * Se ocorrerem alguma alteraçao na solicitação, é enviado um email ao solicitante informando que ocorreu alguma alteração. Se a alteração foi feita na
         * Descrição da solicitação, será enviado
         * por email a descrição também.
         */
        final EmpregadoDao empregadoDao = new EmpregadoDao();
        if (dtoAux != null && dtoAux.getIdTipoDemandaServico().intValue() == 3
                && ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.ATIVAR_ENVIO_EMAIL_UPDATE_INCIDENTE, "N").equalsIgnoreCase("S")) {
            final SolicitacaoServicoDTO aux = solicitacaoServico;
            final EmpregadoDTO empregatoDto = empregadoDao.restoreByIdEmpregado(aux.getIdSolicitante());
            String emailPara = "";
            if (empregatoDto != null) {
                emailPara = empregatoDto.getEmail();
            }

            final String remetente = ParametroUtil.getValor(ParametroSistema.RemetenteNotificacoesSolicitacao);
            if (remetente != null && emailPara != null && StringUtils.isNotEmpty(emailPara) && StringUtils.isNotEmpty(remetente) && empregatoDto != null) {
                if (!mudancaDescricao) {
                    final ModeloEmailDTO modeloEmailDto = new ModeloEmailDao().findByIdentificador("alterSolServDesc");
                    if (modeloEmailDto != null) {
                        final MensagemEmail mensagem = new MensagemEmail(modeloEmailDto.getIdModeloEmail(), new BaseEntity[] {aux});
                        if (mensagem != null) {
                            mensagem.envia(emailPara, null, remetente);
                        }

                    }
                } else {
                    final ModeloEmailDTO modeloEmailDto = new ModeloEmailDao().findByIdentificador("alterSolServico");
                    if (modeloEmailDto != null) {
                        final MensagemEmail mensagem = new MensagemEmail(modeloEmailDto.getIdModeloEmail(), new BaseEntity[] {aux});
                        if (mensagem != null) {
                            mensagem.envia(emailPara, null, remetente);
                        }
                    }

                }
            }
        }

        return solicitacaoServico;
    }

    /**
     * Faz a atualizacao dos anexos da solicitacao, mas nao altera as informacoes da soliciação.
     *
     * @param model
     * @return
     * @throws ServiceException
     * @throws LogicException
     */

    @Override
    public BaseEntity updateInfoCollection(final BaseEntity model) throws ServiceException, LogicException {
        final SolicitacaoServicoDTO solicitacaoServico = (SolicitacaoServicoDTO) model;

        return solicitacaoServico;
    }

    @Override
    public void updateNotNull(final BaseEntity obj) throws Exception {
        this.getDao().updateNotNull(obj);

    }

    /**
     * Faz a mudanca de SLA
     *
     * @param model
     * @return
     * @throws ServiceException
     * @throws LogicException
     */
    @Override
    public void updateSLA(final BaseEntity model) throws ServiceException, LogicException {
        final SolicitacaoServicoDTO solicitacaoServico = (SolicitacaoServicoDTO) model;
        final TransactionControler tc = new TransactionControlerImpl(this.getDao().getAliasDB());
        try {
            tc.start();

            // Faz validacao, caso exista.
            this.validaUpdate(model);

            this.getDao().setTransactionControler(tc);
            solicitacaoServico.setDataHoraInicioSLA(null);
            solicitacaoServico.setDataHoraReativacao(null);

            if (solicitacaoServico.getTempoDecorridoHH() == null) {
                solicitacaoServico.setTempoDecorridoHH(new Integer(0));
            }
            if (solicitacaoServico.getTempoDecorridoMM() == null) {
                solicitacaoServico.setTempoDecorridoMM(new Integer(0));
            }

            if (solicitacaoServico.getSlaACombinar().equalsIgnoreCase("S")) {
                solicitacaoServico.setPrazoCapturaHH(0);
                solicitacaoServico.setPrazoCapturaMM(0);
                solicitacaoServico.setPrazoHH(0);
                solicitacaoServico.setPrazoMM(0);
            } else {
                SolicitacaoServicoDTO solicitacaoAuxDto = new SolicitacaoServicoDTO();
                solicitacaoAuxDto.setIdSolicitacaoServico(solicitacaoServico.getIdSolicitacaoServico());
                solicitacaoAuxDto = (SolicitacaoServicoDTO) solicitacaoServicoDao.restore(solicitacaoAuxDto);
                solicitacaoServico.setDataHoraInicioSLA(solicitacaoAuxDto.getDataHoraInicioSLA());
                solicitacaoServico.setDataHoraReativacaoSLA(solicitacaoAuxDto.getDataHoraReativacaoSLA());
                this.determinaPrazoLimite(solicitacaoServico, solicitacaoServico.getIdCalendario(), tc);
            }

            solicitacaoServicoDao.updateNotNull(model);
            solicitacaoServicoDao.limpaDataReativacao(solicitacaoServico);

            String strOcorr = "\n" + this.i18nMessage("gerenciaservico.mudarsla.tiposla") + ": ";
            if (solicitacaoServico.getSlaACombinar().equalsIgnoreCase("S")) {
                strOcorr += this.i18nMessage("citcorpore.comum.acombinar");
            } else {
                strOcorr += this.i18nMessage("citcorpore.comum.definicaonovotempo");
            }

            // SolicitacaoServicoDTO solicitacaoAuxDto =
            // restoreAll(solicitacaoServico.getIdSolicitacaoServico());
            strOcorr += "\n " + this.i18nMessage("gerenciamento.mudarsla.prazoanterior");
            if (solicitacaoServico.getPrazohhAnterior() != null) {
                strOcorr += solicitacaoServico.getPrazohhAnterior() + "h ";
            }
            if (solicitacaoServico.getPrazommAnterior() != null) {
                strOcorr += solicitacaoServico.getPrazommAnterior() + "m ";
            }

            final JustificativaSolicitacaoDTO justificativaDto = new JustificativaSolicitacaoDTO();
            justificativaDto.setIdJustificativa(solicitacaoServico.getIdJustificativa());
            justificativaDto.setComplementoJustificativa(solicitacaoServico.getComplementoJustificativa());

            final UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setLogin(solicitacaoServico.getRegistradoPor());
            OcorrenciaSolicitacaoServiceEjb.create(solicitacaoServico, null, strOcorr, OrigemOcorrencia.OUTROS, CategoriaOcorrencia.MudancaSLA, null,
                    CategoriaOcorrencia.MudancaSLA.getDescricao(), usuarioDTO, 0, justificativaDto, tc);

            tc.commit();

        } catch (final Exception e) {
            this.rollbackTransaction(tc, e);
            throw new ServiceException(e);
        } finally {
            tc.closeQuietly();
        }
    }

    /**
     *
     * @author breno.guimaraes
     */
    @Override
    public void updateSolicitacaoPai(final int idSolicitacaoPai, final int idSolicitacao) {
        try {
            this.getDao().updateSolicitacaoPai(idSolicitacao, idSolicitacaoPai);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTimeAction(final Integer idGrupoRedirect, final Integer idPrioridadeRedirect, final Integer idSolicitacaoServicoParm,
            TransactionControler tc) throws ServiceException, LogicException {

        final ExecucaoSolicitacaoServiceEjb execucaoSolicitacaoService = new ExecucaoSolicitacaoServiceEjb();
        final OcorrenciaSolicitacaoDao ocorrenciaSolicitacaoDao = new OcorrenciaSolicitacaoDao();

        if (tc == null) {
            tc = new TransactionControlerImpl(this.getDao().getAliasDB());
        }

        try {
            if (!tc.isStarted()) {
                tc.start();
            }

            // Faz validacao, caso exista.

            this.getDao().setTransactionControler(tc);
            ocorrenciaSolicitacaoDao.setTransactionControler(tc);

            List<SolicitacaoServicoDTO> listaSolicitacao = new ArrayList<SolicitacaoServicoDTO>();

            SolicitacaoServicoDTO solicitacaoAuxDto = new SolicitacaoServicoDTO();
            solicitacaoAuxDto.setIdSolicitacaoServico(idSolicitacaoServicoParm);

            listaSolicitacao = (List<SolicitacaoServicoDTO>) this.getDao().find(solicitacaoAuxDto);
            if (listaSolicitacao != null) {
                solicitacaoAuxDto = listaSolicitacao.get(0);
            }
            // if(solicitacaoAuxDto.getIdGrupoAtual() != null){
            // return;
            // }

            final SolicitacaoServicoDTO solicitacaoServico = new SolicitacaoServicoDTO();

            solicitacaoServico.setIdGrupoAtual(idGrupoRedirect);
            solicitacaoServico.setIdPrioridade(idPrioridadeRedirect);
            solicitacaoServico.setIdSolicitacaoServico(idSolicitacaoServicoParm);

            this.getDao().updateNotNull(solicitacaoServico);
            execucaoSolicitacaoService.direcionaAtendimentoAutomatico(solicitacaoServico, tc);
            final String strOcorr = "\nEscalação automática.";

            // SolicitacaoServicoDTO solicitacaoAuxDto =
            // restoreAll(solicitacaoServico.getIdSolicitacaoServico());

            final JustificativaSolicitacaoDTO justificativaDto = new JustificativaSolicitacaoDTO();
            justificativaDto.setIdJustificativa(solicitacaoServico.getIdJustificativa());
            justificativaDto.setComplementoJustificativa(solicitacaoServico.getComplementoJustificativa());

            final UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setLogin("Automático");

            OcorrenciaSolicitacaoServiceEjb.create(solicitacaoServico, null, strOcorr, OrigemOcorrencia.OUTROS, CategoriaOcorrencia.Atualizacao, null,
                    CategoriaOcorrencia.Atualizacao.getDescricao(), usuarioDTO, 0, justificativaDto, tc);

            tc.commit();

        } catch (final Exception e) {
            this.rollbackTransaction(tc, e);
            throw new ServiceException(e);
        } finally {
            tc.closeQuietly();
        }
    }

    @Override
    public boolean verificarExistenciaDeUnidade(final Integer idUnidade) throws Exception {
        return this.getDao().verificarExistenciaDeUnidade(idUnidade);
    }

    @Override
    public boolean verificarExistenciaSolicitacaoFilho(final Integer idSolicitacaoServicoPai) throws Exception {

        Collection<SolicitacaoServicoDTO> listSolicitacaoServicoFilho = new ArrayList<SolicitacaoServicoDTO>();

        listSolicitacaoServicoFilho = this.getDao().findByIdSolicitacaoPai(idSolicitacaoServicoPai);

        if (listSolicitacaoServicoFilho != null && !listSolicitacaoServicoFilho.isEmpty()) {

            return true;

        } else {

            return false;

        }
    }

    @Override
    public SolicitacaoServicoDTO verificaSituacaoSLA(final SolicitacaoServicoDTO solicitacaoDto) throws Exception {
        return this.verificaSituacaoSLA(solicitacaoDto, null);
    }

    public SolicitacaoServicoDTO verificaSituacaoSLA(final SolicitacaoServicoDTO solicitacaoDto, final TransactionControler tc) throws Exception {
        long atrasoSLA = 0;

        if (solicitacaoDto == null) {
            return null;
        }
        /**
         * Não calcula o atraso dos SLAs a combinar
         */
        final boolean slaACombinar = (solicitacaoDto.getPrazoHH() == null || solicitacaoDto.getPrazoHH() == 0)
                && (solicitacaoDto.getPrazoMM() == null || solicitacaoDto.getPrazoMM() == 0);

        if (solicitacaoDto.getDataHoraLimite() == null) {
            this.determinaPrazoLimite(solicitacaoDto, null, tc);
        }

        if (solicitacaoDto.getDataHoraLimite() != null && !slaACombinar) {
            boolean bCalcula = true;
            if (solicitacaoDto.getSituacao().equals(SituacaoSolicitacaoServico.Suspensa.name())) {
                bCalcula = solicitacaoDto.getDataHoraSuspensao().compareTo(solicitacaoDto.getDataHoraLimite()) > 0;
            } else if (solicitacaoDto.getSituacao().equals(SituacaoSolicitacaoServico.Cancelada.name())) {
                bCalcula = false;
            } else if (solicitacaoDto.getDataHoraSuspensaoSLA() != null && solicitacaoDto.getSituacaoSLA() != null
                    && solicitacaoDto.getSituacaoSLA().equalsIgnoreCase("S")) {
                bCalcula = solicitacaoDto.getDataHoraSuspensaoSLA().compareTo(solicitacaoDto.getDataHoraLimite()) > 0;
            }
            if (bCalcula) {
                final Timestamp dataHoraLimite = solicitacaoDto.getDataHoraLimite();
                Timestamp dataHoraComparacao = UtilDatas.getDataHoraAtual();
                if (solicitacaoDto.encerrada()) {
                    dataHoraComparacao = solicitacaoDto.getDataHoraFim();
                }
                if (dataHoraComparacao != null) {
                    if (dataHoraComparacao.compareTo(dataHoraLimite) > 0) {
                        atrasoSLA = UtilDatas.calculaDiferencaTempoEmMilisegundos(dataHoraComparacao, dataHoraLimite) / 1000;
                    }
                }
            }
        }

        solicitacaoDto.setAtrasoSLA(atrasoSLA);
        return solicitacaoDto;
    }

    /**
     * Método que calcula o atraso das SLAs que não estão a combinar e com situação diferentes de suspensa e cancelada
     *
     * @param solicitacaoDto
     * @param tc
     * @return SolicitacaoServicoDTO
     * @throws Exception
     * @author rodrigo.acorse
     */
    @Deprecated
    public SolicitacaoServicoDTO verificaSituacaoSLAsValidas(final SolicitacaoServicoDTO solicitacaoDto, final TransactionControler tc) throws Exception {
        long atrasoSLA = 0;

        if (solicitacaoDto == null) {
            return null;
        }

        final boolean slaACombinar = (solicitacaoDto.getPrazoHH() == null || solicitacaoDto.getPrazoHH() == 0)
                && (solicitacaoDto.getPrazoMM() == null || solicitacaoDto.getPrazoMM() == 0);

        if (solicitacaoDto.getDataHoraLimite() == null) {
            this.determinaPrazoLimite(solicitacaoDto, null, tc);
        }

        if (solicitacaoDto.getDataHoraLimite() != null && !slaACombinar && !solicitacaoDto.getSituacao().equalsIgnoreCase("Suspensa")
                && !solicitacaoDto.getSituacao().equalsIgnoreCase("Cancelada")) {
            boolean bCalcula = true;
            if (solicitacaoDto.getSituacaoSLA() != null && solicitacaoDto.getSituacaoSLA().equalsIgnoreCase("S")) {
                bCalcula = solicitacaoDto.getDataHoraSuspensaoSLA().compareTo(solicitacaoDto.getDataHoraLimite()) > 0;
            }
            if (bCalcula) {
                final Timestamp dataHoraLimite = solicitacaoDto.getDataHoraLimite();
                Timestamp dataHoraComparacao = UtilDatas.getDataHoraAtual();
                if (solicitacaoDto.encerrada()) {
                    dataHoraComparacao = solicitacaoDto.getDataHoraFim();
                }
                if (dataHoraComparacao != null) {
                    if (dataHoraComparacao.compareTo(dataHoraLimite) > 0) {
                        atrasoSLA = UtilDatas.calculaDiferencaTempoEmMilisegundos(dataHoraComparacao, dataHoraLimite) / 1000;
                    }
                }
            }
        }

        solicitacaoDto.setAtrasoSLA(atrasoSLA);
        return solicitacaoDto;
    }

    @Override
    public Collection incidentesPorContrato(final Integer idContrato) throws Exception {
        return this.getDao().incidentesPorContrato(idContrato);

    }

    public void atualizaInformacoesQuestionario(final SolicitacaoServicoDTO solicitacaoServico, final TransactionControler tc) throws Exception {
        final ControleQuestionariosDao controleQuestionariosDao = new ControleQuestionariosDao();
        final SolicitacaoServicoQuestionarioDao solicitacaoServicoQuestionarioDao = new SolicitacaoServicoQuestionarioDao();
        final RespostaItemQuestionarioDao respostaItemDao = new RespostaItemQuestionarioDao();
        final RespostaItemQuestionarioServiceBean respostaItemQuestionarioServiceBean = new RespostaItemQuestionarioServiceBean();

        controleQuestionariosDao.setTransactionControler(tc);
        solicitacaoServicoQuestionarioDao.setTransactionControler(tc);
        respostaItemDao.setTransactionControler(tc);

        final SolicitacaoServicoQuestionarioDTO solicitacaoServicoQuestionarioDto = solicitacaoServico.getSolicitacaoServicoQuestionarioDTO();
        if (solicitacaoServicoQuestionarioDto.getIdSolicitacaoQuestionario() != null
                && solicitacaoServicoQuestionarioDto.getIdSolicitacaoQuestionario().intValue() > 0) {
            solicitacaoServicoQuestionarioDto.setIdSolicitacaoServico(solicitacaoServico.getIdSolicitacaoServico());
            solicitacaoServicoQuestionarioDto.setDataHoraGrav(UtilDatas.getDataHoraAtual());
            solicitacaoServicoQuestionarioDao.updateNotNull(solicitacaoServicoQuestionarioDto);

            respostaItemDao.deleteByIdIdentificadorResposta(solicitacaoServicoQuestionarioDto.getIdSolicitacaoQuestionario());
            respostaItemQuestionarioServiceBean.processCollection(tc, solicitacaoServicoQuestionarioDto.getColValores(),
                    solicitacaoServicoQuestionarioDto.getColAnexos(), solicitacaoServicoQuestionarioDto.getIdSolicitacaoQuestionario(), null);
        } else {
            ControleQuestionariosDTO controleQuestionariosDto = new ControleQuestionariosDTO();
            controleQuestionariosDto = (ControleQuestionariosDTO) controleQuestionariosDao.create(controleQuestionariosDto);

            solicitacaoServicoQuestionarioDto.setIdSolicitacaoServico(solicitacaoServico.getIdSolicitacaoServico());
            solicitacaoServicoQuestionarioDto.setIdResponsavel(solicitacaoServico.getUsuarioDto().getIdEmpregado());
            solicitacaoServicoQuestionarioDto.setIdTarefa(solicitacaoServico.getIdTarefa());
            if (solicitacaoServicoQuestionarioDto.getDataQuestionario() == null) {
                solicitacaoServicoQuestionarioDto.setDataQuestionario(UtilDatas.getDataAtual());
            }
            solicitacaoServicoQuestionarioDto.setSituacao("E");
            solicitacaoServicoQuestionarioDto.setIdSolicitacaoQuestionario(controleQuestionariosDto.getIdControleQuestionario());
            solicitacaoServicoQuestionarioDto.setDataHoraGrav(UtilDatas.getDataHoraAtual());
            final SolicitacaoServicoQuestionarioDTO solQuestionariosDTO = (SolicitacaoServicoQuestionarioDTO) solicitacaoServicoQuestionarioDao
                    .create(solicitacaoServicoQuestionarioDto);

            final Integer idIdentificadorResposta = solQuestionariosDTO.getIdSolicitacaoQuestionario();
            respostaItemQuestionarioServiceBean.processCollection(tc, solQuestionariosDTO.getColValores(), solQuestionariosDTO.getColAnexos(),
                    idIdentificadorResposta, null);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listaSolicitacaoServicoPorCriteriosPaginado(final PesquisaSolicitacaoServicoDTO pesquisaSolicitacaoServicoDto,
            final String paginacao, final Integer pagAtual, final Integer pagAtualAux, final Integer totalPag, final Integer quantidadePaginator,
            final String campoPesquisa) throws Exception {
        try {
            return this.getDao().findByIdContratoPaginada(pesquisaSolicitacaoServicoDto, paginacao, pagAtual, pagAtualAux, totalPag, quantidadePaginator,
                    campoPesquisa);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listaSolicitacaoServicoPorCriteriosPaginado(final PesquisaSolicitacaoServicoDTO pesquisaSolicitacaoServicoDto,
            final String paginacao, final Integer pagAtual, final Integer pagAtualAux, final Integer totalPag, final Integer quantidadePaginator,
            final String campoPesquisa, final Collection<UnidadeDTO> unidadesColaborador) throws Exception {
        try {

            return this.getDao().findByIdContratoPaginada(pesquisaSolicitacaoServicoDto, paginacao, pagAtual, pagAtualAux, totalPag, quantidadePaginator,
                    campoPesquisa, unidadesColaborador);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String calculaSLA(final SolicitacaoServicoDTO solicitacaoServico, final HttpServletRequest request) throws Exception {
        Integer prazoHH = 0;
        Integer prazoMM = 0;

        final TempoAcordoNivelServicoDao tempoAcordoNivelServicoDao = new TempoAcordoNivelServicoDao();
        final AcordoNivelServicoDao acordoNivelServicoDao = new AcordoNivelServicoDao();
        final AcordoServicoContratoDao acordoServicoContratoDao = new AcordoServicoContratoDao();
        final PrioridadeServicoUnidadeDao prioridadeServicoUnidadeDao = new PrioridadeServicoUnidadeDao();
        final EmpregadoDao empregadoDao = new EmpregadoDao();
        final MatrizPrioridadeDAO matrizPrioriDao = new MatrizPrioridadeDAO();
        final ServicoContratoDao servicoContratoDao = new ServicoContratoDao();
        final PrioridadeServicoUsuarioDao prioridadeServicoUsuarioDao = new PrioridadeServicoUsuarioDao();
        final PrioridadeAcordoNivelServicoDao prioridadeAcordoNivelServicoDao = new PrioridadeAcordoNivelServicoDao();

        final TransactionControler tc = new TransactionControlerImpl(this.getDao().getAliasDB());
        if (tc != null) {
            try {
                tc.start();

                tempoAcordoNivelServicoDao.setTransactionControler(tc);
                acordoNivelServicoDao.setTransactionControler(tc);
                acordoServicoContratoDao.setTransactionControler(tc);
                prioridadeServicoUnidadeDao.setTransactionControler(tc);
                empregadoDao.setTransactionControler(tc);
                matrizPrioriDao.setTransactionControler(tc);
                servicoContratoDao.setTransactionControler(tc);
                prioridadeServicoUsuarioDao.setTransactionControler(tc);
                prioridadeAcordoNivelServicoDao.setTransactionControler(tc);

                Integer idPrioridade = null;

                final ServicoContratoDTO servicoContratoDto = servicoContratoDao.findByIdContratoAndIdServico(solicitacaoServico.getIdContrato(),
                        solicitacaoServico.getIdServico());
                if (servicoContratoDto != null) {
                    final Integer idServicoContrato = servicoContratoDto.getIdServicoContrato();

                    if (solicitacaoServico.getIdSolicitante() != null) {
                        EmpregadoDTO empregadoDTO = null;
                        empregadoDTO = empregadoDao.restoreByIdEmpregado(solicitacaoServico.getIdSolicitante());
                        if (empregadoDTO != null && empregadoDTO.getIdUnidade() != null) {
                            final PrioridadeServicoUnidadeDTO prioridadeServicoUnidadeDto = prioridadeServicoUnidadeDao.restore(idServicoContrato,
                                    empregadoDTO.getIdUnidade());
                            if (prioridadeServicoUnidadeDto != null) {
                                idPrioridade = prioridadeServicoUnidadeDto.getIdPrioridade();
                            }
                        }
                    }

                    final String calcularDinamicamente = ParametroUtil.getValorParametroCitSmartHashMap(
                            Enumerados.ParametroSistema.CALCULAR_PRIORIDADE_SOLICITACAO_DINAMICAMENTE, "N");

                    if (!calcularDinamicamente.trim().equalsIgnoreCase("S")) {
                        if (idPrioridade == null) {
                            if (solicitacaoServico.getUrgencia().equalsIgnoreCase("B") && solicitacaoServico.getImpacto().equalsIgnoreCase("B")) {
                                idPrioridade = 5;
                            } else if (solicitacaoServico.getUrgencia().equalsIgnoreCase("B") && solicitacaoServico.getImpacto().equalsIgnoreCase("M")) {
                                idPrioridade = 4;
                            } else if (solicitacaoServico.getUrgencia().equalsIgnoreCase("B") && solicitacaoServico.getImpacto().equalsIgnoreCase("A")) {
                                idPrioridade = 3;
                            } else if (solicitacaoServico.getUrgencia().equalsIgnoreCase("M") && solicitacaoServico.getImpacto().equalsIgnoreCase("B")) {
                                idPrioridade = 4;
                            } else if (solicitacaoServico.getUrgencia().equalsIgnoreCase("M") && solicitacaoServico.getImpacto().equalsIgnoreCase("M")) {
                                idPrioridade = 3;
                            } else if (solicitacaoServico.getUrgencia().equalsIgnoreCase("M") && solicitacaoServico.getImpacto().equalsIgnoreCase("A")) {
                                idPrioridade = 2;
                            } else if (solicitacaoServico.getUrgencia().equalsIgnoreCase("A") && solicitacaoServico.getImpacto().equalsIgnoreCase("B")) {
                                idPrioridade = 3;
                            } else if (solicitacaoServico.getUrgencia().equalsIgnoreCase("A") && solicitacaoServico.getImpacto().equalsIgnoreCase("M")) {
                                idPrioridade = 2;
                            } else if (solicitacaoServico.getUrgencia().equalsIgnoreCase("A") && solicitacaoServico.getImpacto().equalsIgnoreCase("A")) {
                                idPrioridade = 1;
                            }
                        }
                    } else {
                        final String siglaImpacto = solicitacaoServico.getImpacto();
                        final String siglaUrgencia = solicitacaoServico.getUrgencia();
                        final Integer valorPrioridade = matrizPrioriDao.consultaValorPrioridade(siglaImpacto.trim().toUpperCase(), siglaUrgencia.trim()
                                .toUpperCase());
                        idPrioridade = valorPrioridade;
                    }

                    AcordoNivelServicoDTO acordoNivelServicoDto = acordoNivelServicoDao.findAtivoByIdServicoContrato(idServicoContrato, "T");
                    if (acordoNivelServicoDto == null) {
                        acordoNivelServicoDto = new AcordoNivelServicoDTO();
                        final AcordoServicoContratoDTO acordoServicoContratoDTO = acordoServicoContratoDao.findAtivoByIdServicoContrato(idServicoContrato, "T");
                        if (acordoServicoContratoDTO != null) {
                            acordoNivelServicoDto.setIdAcordoNivelServico(acordoServicoContratoDTO.getIdAcordoNivelServico());
                        }
                        // Consulta prioridade do usuário de acordo com sla global
                        final PrioridadeServicoUsuarioDTO prioridadeServicoUsuarioDTO = prioridadeServicoUsuarioDao.findByIdAcordoNivelServicoAndIdUsuario(
                                acordoNivelServicoDto.getIdAcordoNivelServico(), solicitacaoServico.getIdSolicitante());
                        if (prioridadeServicoUsuarioDTO != null && prioridadeServicoUsuarioDTO.getIdPrioridade() != null) {
                            idPrioridade = prioridadeServicoUsuarioDTO.getIdPrioridade();
                        }
                        // Consulta prioridade da unidade do usuário de acordo com sla global
                        final PrioridadeAcordoNivelServicoDTO prioridadeAcordoNivelServicoDTO = prioridadeAcordoNivelServicoDao
                                .findByIdAcordoNivelServicoAndIdUnidade(acordoNivelServicoDto.getIdAcordoNivelServico(), solicitacaoServico.getIdUnidade());
                        if (prioridadeAcordoNivelServicoDTO != null && prioridadeAcordoNivelServicoDTO.getIdPrioridade() != null) {
                            idPrioridade = prioridadeAcordoNivelServicoDTO.getIdPrioridade();
                        }
                    }
                    if (idPrioridade == null) {
                        idPrioridade = acordoNivelServicoDto.getIdPrioridadePadrao();
                    }

                    final Collection<TempoAcordoNivelServicoDTO> colTempos = tempoAcordoNivelServicoDao.findByIdAcordoAndIdPrioridade(
                            acordoNivelServicoDto.getIdAcordoNivelServico(), idPrioridade);
                    if (colTempos != null) {
                        for (final TempoAcordoNivelServicoDTO tempoAcordoDto : colTempos) {
                            if (tempoAcordoDto.getTempoHH() != null) {
                                prazoHH += tempoAcordoDto.getTempoHH().intValue();
                            }
                            if (tempoAcordoDto.getTempoMM() != null) {
                                prazoMM += tempoAcordoDto.getTempoMM().intValue();
                            }
                        }
                        while (prazoMM >= 60) {
                            prazoHH = prazoHH + 1;
                            prazoMM = prazoMM - 60;
                        }
                    }
                    if (prazoHH.equals(0) && prazoMM.equals(0)) {
                        return UtilI18N.internacionaliza(request, "citcorpore.comum.aCombinar");
                    } else {
                        String hh = prazoHH.toString();
                        String mm = prazoMM.toString();

                        if (hh.length() == 1) {
                            hh = "0" + hh;
                        }
                        if (mm.length() == 1) {
                            mm = "0" + mm;
                        }

                        return hh + ":" + mm;
                    }
                }
            } catch (final PersistenceException e) {
                e.printStackTrace();
            } finally {
                tc.closeQuietly();
            }
        }

        return "";
    }

    @Override
    public SolicitacaoServicoDTO findByIdSolicitacaoServico(final Integer idSolicitacaoServico) throws Exception {
        try {
            return this.getDao().findByIdSolicitacaoServico(idSolicitacaoServico);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByIdGrupo(final Integer idGrupo) throws Exception {
        try {
            return this.getDao().findByIdGrupo(idGrupo);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByIdGrupoEDataBaixa(final Integer idGrupo, final Date dataInicio, final Date dataFim) throws Exception {
        try {
            return this.getDao().findByIdGrupoEDataBaixa(idGrupo, dataInicio, dataFim);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByIdGrupoEDataMedia(final Integer idGrupo, final Date dataInicio, final Date dataFim) throws Exception {
        try {
            return this.getDao().findByIdGrupoEDataMedia(idGrupo, dataInicio, dataFim);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByIdGrupoEDataAlta(final Integer idGrupo, final Date dataInicio, final Date dataFim) throws Exception {
        try {
            return this.getDao().findByIdGrupoEDataAlta(idGrupo, dataInicio, dataFim);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByIdGrupoEDataAtendidasBaixa(final Integer idGrupo, final Date dataInicio, final Date dataFim)
            throws Exception {
        try {
            return this.getDao().findByIdGrupoEDataAtendidasBaixa(idGrupo, dataInicio, dataFim);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByIdGrupoEDataAtendidasMedia(final Integer idGrupo, final Date dataInicio, final Date dataFim)
            throws Exception {
        try {
            return this.getDao().findByIdGrupoEDataAtendidasMedia(idGrupo, dataInicio, dataFim);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByIdGrupoEDataAtendidasAlta(final Integer idGrupo, final Date dataInicio, final Date dataFim) throws Exception {
        try {
            return this.getDao().findByIdGrupoEDataAtendidasAlta(idGrupo, dataInicio, dataFim);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByIdGrupoEDataTotal(final Integer idGrupo, final Date dataInicio, final Date dataFim) throws Exception {
        try {
            return this.getDao().findByIdGrupoEDataTotal(idGrupo, dataInicio, dataFim);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByIdGrupoEDataSuspensasTotal(final Integer idGrupo, final Date dataInicio, final Date dataFim)
            throws Exception {
        try {
            return this.getDao().findByIdGrupoEDataSuspensasTotal(idGrupo, dataInicio, dataFim);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByIdPessoaEDataAtendidas(final Integer idGrupo, final String login, final String nome, final Date dataInicio,
            final Date dataFim) throws Exception {
        try {
            return this.getDao().findByIdPessoaEDataAtendidas(idGrupo, login, nome, dataInicio, dataFim);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByIdPessoaEData(final Integer idGrupo, final String login, final String nome, final Date dataInicio,
            final Date dataFim) throws Exception {
        try {
            return this.getDao().findByIdPessoaEData(idGrupo, login, nome, dataInicio, dataFim);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByIdPessoaEDataNaoAtendidas(final Integer idGrupo, final Date dataInicio, final Date dataFim) throws Exception {
        try {
            return this.getDao().findByIdPessoaEDataNaoAtendidas(idGrupo, dataInicio, dataFim);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<RelatorioSolicitacaoPorExecutanteDTO> listaSolicitacaoPorExecutante(
            final RelatorioSolicitacaoPorExecutanteDTO relatorioSolicitacaoPorExecutanteDto) throws Exception {
        return this.getSolicitacaoServicoDao().listaSolicitacaoPorExecutante(relatorioSolicitacaoPorExecutanteDto);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByIdGrupoEDataAtendidasTotal(final Integer idGrupo, final Date dataInicio, final Date dataFim)
            throws Exception {
        try {
            return this.getDao().findByIdGrupoEDataAtendidasTotal(idGrupo, dataInicio, dataFim);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByIdGrupoEDataAtrasadasTotal(final Integer idGrupo, final Date dataInicio, final Date dataFim)
            throws Exception {
        try {
            return this.getDao().findByIdGrupoEDataAtrasadasTotal(idGrupo, dataInicio, dataFim);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<RelatorioQuantitativoSolicitacaoProblemaPorServicoDTO> listaServicoPorSolicitacaoServico(
            final RelatorioQuantitativoSolicitacaoProblemaPorServicoDTO relatorioAnaliseServicoDto) throws Exception {

        final Collection<RelatorioQuantitativoSolicitacaoProblemaPorServicoDTO> listFinal = new ArrayList<RelatorioQuantitativoSolicitacaoProblemaPorServicoDTO>();

        final Collection<RelatorioQuantitativoSolicitacaoProblemaPorServicoDTO> listAux = this.getSolicitacaoServicoDao().listaServicoPorSolicitacaoServico(
                relatorioAnaliseServicoDto);

        Collection<RelatorioQuantitativoSolicitacaoProblemaPorServicoDTO> listaSolicitacaoServicoProblema = new ArrayList<RelatorioQuantitativoSolicitacaoProblemaPorServicoDTO>();

        if (listAux != null) {
            for (final RelatorioQuantitativoSolicitacaoProblemaPorServicoDTO relatorioAnaliseServico : listAux) {
                listaSolicitacaoServicoProblema = this.getSolicitacaoServicoDao().listaSolicitacaoServicoProblemaPorServico(relatorioAnaliseServico);
                relatorioAnaliseServico.setListaSolicitacaoServicoProblema(listaSolicitacaoServicoProblema);
                listFinal.add(relatorioAnaliseServico);
            }
            return listFinal;
        }

        return listAux;
    }

    @Override
    public boolean permissaoGrupoExecutorServico(final int idGrupoExecutor, final int idTipoFluxoSolicitacaoServico) throws Exception {
        final PermissoesFluxoService permissoesFluxoService = (PermissoesFluxoService) ServiceLocator.getInstance().getService(PermissoesFluxoService.class,
                null);
        return permissoesFluxoService.permissaoGrupoExecutorLiberacaoServico(idGrupoExecutor, idTipoFluxoSolicitacaoServico);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listaSolicitacoesPorIdEmpregado(final Integer pgAtual, final Integer qtdPaginacao,
            final GerenciamentoServicosDTO gerenciamentoBean, final Collection<ContratoDTO> listContratoUsuarioLogado) throws Exception {
        try {

            return this.getDao().listaSolicitacoesPorIdEmpregado(pgAtual, qtdPaginacao, gerenciamentoBean, listContratoUsuarioLogado);

        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listaSolicitacoesPorIdEmpregado(final Integer pgAtual, final Integer qtdPaginacao,
            final GerenciamentoServicosDTO gerenciamentoBean, final Collection<ContratoDTO> listContratoUsuarioLogado, final String[] filtro) throws Exception {
        try {

            return this.getDao().listaSolicitacoesPorIdEmpregado(pgAtual, qtdPaginacao, gerenciamentoBean, listContratoUsuarioLogado, filtro);

        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

    public Collection<SolicitacaoServicoDTO> resumoSolicitacoesServico(final Collection<TarefaFluxoDTO> listTarefas, final Integer idTipoDemandaServico,
            final Integer idPrioridade) throws Exception {
        Collection<SolicitacaoServicoDTO> listSolicitacaoServico = new ArrayList<>();
        final HashMap hashMap = new HashMap<>();
        Integer qtdEmAndamento = 0;
        final Integer qtdCancelada = 0;
        Integer qtdSuspensa = 0;
        Integer qtdPrazoVencido = 0;

        listSolicitacaoServico = this.listByTarefas(listTarefas, new TransactionControlerImpl(Constantes.getValue("DATABASE_ALIAS")));

        if (listTarefas != null) {
            for (final SolicitacaoServicoDTO solicitacaoServico : listSolicitacaoServico) {
                if (solicitacaoServico.getSituacao().equalsIgnoreCase("EmAndamento")) {
                    qtdEmAndamento++;
                }
                if (solicitacaoServico.getSituacao().equalsIgnoreCase("Cancelada")) {
                    qtdEmAndamento++;
                }
                if (solicitacaoServico.getSituacao().equalsIgnoreCase("Suspensa")) {
                    qtdSuspensa++;
                }

                solicitacaoServico.setDataHoraLimiteStr(solicitacaoServico.getDataHoraLimiteStr());
                solicitacaoServico.setDataHoraInicioSLA(solicitacaoServico.getDataHoraInicioSLA());

                solicitacaoServico.setNomeServico(solicitacaoServico.getServico());
                if (solicitacaoServico.getNomeUnidadeSolicitante() != null && !solicitacaoServico.getNomeUnidadeSolicitante().trim().equalsIgnoreCase("")) {
                    solicitacaoServico.setSolicitanteUnidade(solicitacaoServico.getSolicitante() + " (" + solicitacaoServico.getNomeUnidadeSolicitante() + ")");
                }

                if (solicitacaoServico.getNomeUnidadeResponsavel() != null && !solicitacaoServico.getNomeUnidadeResponsavel().trim().equalsIgnoreCase("")) {
                    solicitacaoServico.setResponsavel(solicitacaoServico.getResponsavel() + " (" + solicitacaoServico.getNomeUnidadeResponsavel() + ")");
                }
                this.verificaSituacaoSLA(solicitacaoServico);
                if (solicitacaoServico.getAtrasoSLA() > 0 && !solicitacaoServico.getSituacao().equalsIgnoreCase("Cancelada")) {
                    qtdPrazoVencido++;
                }
            }
        }

        hashMap.put("Em Andamento", qtdEmAndamento);
        hashMap.put("Cancelada", qtdCancelada);
        hashMap.put("Suspensa", qtdSuspensa);

        hashMap.put("Prazo Vencido", qtdPrazoVencido);

        return listSolicitacaoServico;
    }

    /*
     * Mário Júnior - 29/10/2013 - 17:00 Modificado para atender o resumo de solicitações
     */
    @Override
    public Collection<TipoDemandaServicoDTO> resumoTipoDemandaServico(final List<TarefaFluxoDTO> listTarefas) throws Exception {
        final Collection<TipoDemandaServicoDTO> resumoTipoDemandaServico = new ArrayList<>();
        final TipoDemandaServicoService tipoDemandaServicoService = (TipoDemandaServicoService) ServiceLocator.getInstance().getService(
                TipoDemandaServicoService.class, null);
        Integer qtdeItens = 0;
        final Collection<TipoDemandaServicoDTO> colTipoDemandaServicos = tipoDemandaServicoService.listSolicitacoes();

        if (listTarefas != null) {
            for (final TipoDemandaServicoDTO tipoDemandaServicoDTO : colTipoDemandaServicos) {
                final TipoDemandaServicoDTO dto = new TipoDemandaServicoDTO();
                for (final TarefaFluxoDTO tarefa : listTarefas) {
                    final SolicitacaoServicoDTO solicitacaoServico = (SolicitacaoServicoDTO) tarefa.getSolicitacaoDto();
                    if (solicitacaoServico.getSituacao().equalsIgnoreCase("EmAndamento") || solicitacaoServico.getSituacao().equalsIgnoreCase("Resolvida")
                            || solicitacaoServico.getSituacao().equalsIgnoreCase("Reaberta") || solicitacaoServico.getSituacao().equalsIgnoreCase("Suspensa")
                            || solicitacaoServico.getSituacao().equalsIgnoreCase("Cancelada")) {
                        if (solicitacaoServico.getIdTipoDemandaServico().intValue() == tipoDemandaServicoDTO.getIdTipoDemandaServico().intValue()) {
                            qtdeItens++;
                        }
                    }
                }
                dto.setNomeTipoDemandaServico(tipoDemandaServicoDTO.getNomeTipoDemandaServico());
                dto.setQuantidade(qtdeItens);
                resumoTipoDemandaServico.add(dto);
                qtdeItens = 0;
            }
        }
        return resumoTipoDemandaServico;
    }

    /*
     * Mário Júnior - 29/10/2013 - 17:00 Modificado para atender o resumo de solicitações
     */
    @Override
    public Collection<PrioridadeDTO> resumoPrioridade(final List<TarefaFluxoDTO> listTarefas) throws Exception {
        final Collection<PrioridadeDTO> colPrioridade = new ArrayList<>();
        final PrioridadeService prioridadeService = (PrioridadeService) ServiceLocator.getInstance().getService(PrioridadeService.class, null);
        Integer qtdeItens = 0;

        final Collection<PrioridadeDTO> colPrioridadeService = prioridadeService.list();

        if (listTarefas != null) {
            for (final PrioridadeDTO prioridade : colPrioridadeService) {
                final PrioridadeDTO dto = new PrioridadeDTO();
                for (final TarefaFluxoDTO tarefasStr : listTarefas) {
                    final SolicitacaoServicoDTO solicitacaoServico = (SolicitacaoServicoDTO) tarefasStr.getSolicitacaoDto();
                    if (solicitacaoServico.getSituacao().equalsIgnoreCase("Reaberta") || solicitacaoServico.getSituacao().equalsIgnoreCase("EmAndamento")
                            || solicitacaoServico.getSituacao().equalsIgnoreCase("Resolvida") || solicitacaoServico.getSituacao().equalsIgnoreCase("Suspensa")
                            || solicitacaoServico.getSituacao().equalsIgnoreCase("Cancelada")) {
                        if (prioridade.getIdPrioridade().intValue() == solicitacaoServico.getIdPrioridade().intValue()) {
                            qtdeItens++;
                        }
                    }
                }
                dto.setNomePrioridade(prioridade.getNomePrioridade());
                dto.setQuantidade(qtdeItens);
                colPrioridade.add(dto);
                qtdeItens = 0;
            }
        }
        return colPrioridade;
    }

    @Override
    public HashMap resumoPorPrazoLimite(final Collection<TarefaFluxoDTO> listTarefas) throws Exception {
        Integer qtdPrazoVencido = 0;
        Integer qtdPrazoAVencer = 0;
        Integer qtdPrazoNormal = 0;
        final HashMap hashMap = new HashMap<>();

        if (listTarefas != null) {
            for (final TarefaFluxoDTO tarefasStr : listTarefas) {
                final SolicitacaoServicoDTO solicitacaoServico = (SolicitacaoServicoDTO) tarefasStr.getSolicitacaoDto();

                solicitacaoServico.setDataHoraLimiteStr(solicitacaoServico.getDataHoraLimiteStr());
                solicitacaoServico.setDataHoraInicioSLA(solicitacaoServico.getDataHoraInicioSLA());

                solicitacaoServico.setNomeServico(solicitacaoServico.getServico());
                if (solicitacaoServico.getNomeUnidadeSolicitante() != null && !solicitacaoServico.getNomeUnidadeSolicitante().trim().equalsIgnoreCase("")) {
                    solicitacaoServico.setSolicitanteUnidade(solicitacaoServico.getSolicitante() + " (" + solicitacaoServico.getNomeUnidadeSolicitante() + ")");
                }

                if (solicitacaoServico.getNomeUnidadeResponsavel() != null && !solicitacaoServico.getNomeUnidadeResponsavel().trim().equalsIgnoreCase("")) {
                    solicitacaoServico.setResponsavel(solicitacaoServico.getResponsavel() + " (" + solicitacaoServico.getNomeUnidadeResponsavel() + ")");
                }

                if (solicitacaoServico.getSituacao().equalsIgnoreCase("Reaberta") || solicitacaoServico.getSituacao().equalsIgnoreCase("EmAndamento")
                        || solicitacaoServico.getSituacao().equalsIgnoreCase("Resolvida") || solicitacaoServico.getSituacao().equalsIgnoreCase("Suspensa")
                        || solicitacaoServico.getSituacao().equalsIgnoreCase("Cancelada")) {
                    if (solicitacaoServico.getAtrasoSLA() > 0 && !solicitacaoServico.getSituacao().equalsIgnoreCase("Cancelada")) {
                        if (solicitacaoServico.getDataHoraLimite() != null) {
                            final Timestamp dataHoraLimite = solicitacaoServico.getDataHoraLimite();
                            Timestamp dataHoraComparacao = UtilDatas.getDataHoraAtual();
                            if (solicitacaoServico.encerrada()) {
                                dataHoraComparacao = solicitacaoServico.getDataHoraFim();
                            }
                            if (dataHoraComparacao != null) {
                                if (dataHoraComparacao.compareTo(dataHoraLimite) > 0) {
                                    qtdPrazoVencido++;
                                }
                            }
                        }
                    } else {
                        if (solicitacaoServico.getFalta1Hora()) {
                            qtdPrazoAVencer++;
                        } else {
                            qtdPrazoNormal++;
                        }
                    }
                }
            }
        }

        hashMap.put("Prazo Normal", qtdPrazoNormal);
        hashMap.put("Prazo a Vencer", qtdPrazoAVencer);
        hashMap.put("Prazo Vencido", qtdPrazoVencido);

        return hashMap;
    }

    @Override
    public Collection<RelatorioQuantitativoRetornoDTO> listaServicosRetorno(final SolicitacaoServicoDTO solicitacaoServicoDTO, final String grupoRetorno)
            throws Exception {
        return this.getSolicitacaoServicoDao().listaServicosRetorno(solicitacaoServicoDTO, grupoRetorno);
    }

    @Override
    public Collection<RelatorioQuantitativoRetornoDTO> listaServicosRetornoNomeResponsavel(final RelatorioQuantitativoRetornoDTO relatorioQuantitativoRetornoDTO)
            throws Exception {
        return this.getSolicitacaoServicoDao().listaServicosRetornoNomeResponsavel(relatorioQuantitativoRetornoDTO);
    }

    @Override
    public SolicitacaoServicoDTO listaIdItemTrabalho(final Integer idInstancia) throws Exception {
        return this.getSolicitacaoServicoDao().listaIdItemTrabalho(idInstancia);
    }

    @Override
    public RelatorioQuantitativoRetornoDTO servicoRetorno(final RelatorioQuantitativoRetornoDTO relatorioQuantitativoRetornoDTO) throws Exception {
        return this.getSolicitacaoServicoDao().servicoRetorno(relatorioQuantitativoRetornoDTO);
    }

    @Override
    public boolean validaQuantidadeRetorno(final RelatorioQuantitativoRetornoDTO relatorioQuantitativoRetornoDTO) throws Exception {
        return this.getSolicitacaoServicoDao().validaQuantidadeRetorno(relatorioQuantitativoRetornoDTO);
    }

    @Override
    public RelatorioQuantitativoRetornoDTO retornarIdEncerramento(final String encerramento,
            final RelatorioQuantitativoRetornoDTO relatorioQuantitativoRetornoDTO) throws Exception {
        return this.getSolicitacaoServicoDao().retornarIdEncerramento(encerramento, relatorioQuantitativoRetornoDTO);
    }

    @Override
    public boolean confirmaEncerramento(final RelatorioQuantitativoRetornoDTO relatorioQuantitativoRetornoDTO, final Integer idElemento) throws Exception {
        return this.getSolicitacaoServicoDao().confirmaEncerramento(relatorioQuantitativoRetornoDTO, idElemento);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findByCodigoExterno(final String codigoExterno) throws Exception {
        return this.getSolicitacaoServicoDao().findByCodigoExterno(codigoExterno);
    }

    public Collection<SolicitacaoServicoDTO> listByTarefas(final Collection<TarefaFluxoDTO> listTarefas, final Integer qtdAtual, final Integer qtdAPaginacao,
            final TransactionControler tc) throws Exception {
        if (tc != null) {
            this.getDao().setTransactionControler(tc);
        }
        Collection<SolicitacaoServicoDTO> listSolicitacaoServico = new ArrayList<>();

        listSolicitacaoServico = this.getDao().listByTarefas(listTarefas, qtdAtual, qtdAPaginacao);

        if (listSolicitacaoServico != null && !listSolicitacaoServico.isEmpty()) {

            for (SolicitacaoServicoDTO solicitacao : listSolicitacaoServico) {

                if (solicitacao != null) {
                    solicitacao.setDataHoraLimiteStr(solicitacao.getDataHoraLimiteStr());
                    solicitacao.setDataHoraInicioSLA(solicitacao.getDataHoraInicioSLA());

                    solicitacao.setNomeServico(solicitacao.getServico());
                    if (solicitacao.getNomeUnidadeSolicitante() != null && !solicitacao.getNomeUnidadeSolicitante().trim().equalsIgnoreCase("")) {
                        solicitacao.setSolicitanteUnidade(solicitacao.getSolicitante() + " (" + solicitacao.getNomeUnidadeSolicitante() + ")");
                    }

                    if (solicitacao.getNomeUnidadeResponsavel() != null && !solicitacao.getNomeUnidadeResponsavel().trim().equalsIgnoreCase("")) {
                        solicitacao.setResponsavel(solicitacao.getResponsavel() + " (" + solicitacao.getNomeUnidadeResponsavel() + ")");
                    }

                    solicitacao = this.verificaSituacaoSLA(solicitacao, tc);
                }
            }
        }
        return listSolicitacaoServico;
    }

    @Override
    public boolean existeSolicitacaoServico(final SolicitacaoServicoDTO solicitacaoservico) throws Exception {
        return this.getDao().existeSolicitacaoServico(solicitacaoservico);
    }

    /**
     * Retorna a Lista de TarefaDTO com SolicitacaoServidoDTO de acordo com o Login, Lista de Contratos do Usuário Logado e os Filtros Selecionados na Tela de
     * Gerenciamento de Serviços.
     *
     * @param pgAtual
     * @param qtdAPaginacao
     * @param login
     * @param gerenciamentoBean
     * @param listContratoUsuarioLogado
     * @return
     * @throws Exception
     * @author valdoilo.damasceno
     * @since 05.11.2013
     */
    public Collection<SolicitacaoServicoDTO> listByTarefas(final Collection<TarefaFluxoDTO> listTarefas, final Integer qtdAtual, final Integer qtdAPaginacao,
            final GerenciamentoServicosDTO gerenciamentoBean, final Collection<ContratoDTO> listContratoUsuarioLogado, final TransactionControler tc)
                    throws Exception {

        if (tc != null) {
            this.getDao().setTransactionControler(tc);
        }

        final Collection<SolicitacaoServicoDTO> listSolicitacaoServico = this.getDao().listByTarefas(listTarefas, qtdAtual, qtdAPaginacao, gerenciamentoBean,
                listContratoUsuarioLogado);

        if (listSolicitacaoServico != null && !listSolicitacaoServico.isEmpty()) {

            for (SolicitacaoServicoDTO solicitacao : listSolicitacaoServico) {

                if (solicitacao != null) {
                    solicitacao.setDataHoraLimiteStr(solicitacao.getDataHoraLimiteStr());
                    solicitacao.setDataHoraInicioSLA(solicitacao.getDataHoraInicioSLA());

                    solicitacao.setNomeServico(solicitacao.getServico());
                    if (solicitacao.getNomeUnidadeSolicitante() != null && !solicitacao.getNomeUnidadeSolicitante().trim().equalsIgnoreCase("")) {
                        solicitacao.setSolicitanteUnidade(solicitacao.getSolicitante() + " (" + solicitacao.getNomeUnidadeSolicitante() + ")");
                    }

                    if (solicitacao.getNomeUnidadeResponsavel() != null && !solicitacao.getNomeUnidadeResponsavel().trim().equalsIgnoreCase("")) {
                        solicitacao.setResponsavel(solicitacao.getResponsavel() + " (" + solicitacao.getNomeUnidadeResponsavel() + ")");
                    }

                    solicitacao = this.verificaSituacaoSLA(solicitacao, tc);
                }
            }
        }

        return listSolicitacaoServico;
    }

    /**
     * Utilizado para a RENDERIZAÇÃO do GRÁFICO, pois no Gráfico não é necessário a utilização de Paginação. Esta consulta considera o Login do Usuário Logado,
     * os Contratos em que está inserido e os
     * Filtros Selecionados na tela de Gerenciamento de Serviços.
     *
     * @param listTarefas
     * @param gerenciamentoBean
     * @return
     * @throws Exception
     * @author valdoilo.damasceno
     * @since 05.11.2013
     */
    public Collection<SolicitacaoServicoDTO> listByTarefas(final Collection<TarefaFluxoDTO> listTarefas, final GerenciamentoServicosDTO gerenciamentoBean,
            final Collection<ContratoDTO> listContratoUsuarioLogado, final TransactionControler tc) throws Exception {

        if (tc != null) {
            this.getDao().setTransactionControler(tc);
        }

        Collection<SolicitacaoServicoDTO> listSolicitacaoServico = new ArrayList<>();

        listSolicitacaoServico = this.getDao().listByTarefas(listTarefas, gerenciamentoBean, listContratoUsuarioLogado);

        if (listSolicitacaoServico != null && !listSolicitacaoServico.isEmpty()) {

            for (SolicitacaoServicoDTO solicitacao : listSolicitacaoServico) {
                if (solicitacao != null) {
                    solicitacao.setDataHoraLimiteStr(solicitacao.getDataHoraLimiteStr());
                    solicitacao.setDataHoraInicioSLA(solicitacao.getDataHoraInicioSLA());

                    solicitacao.setNomeServico(solicitacao.getServico());
                    if (solicitacao.getNomeUnidadeSolicitante() != null && !solicitacao.getNomeUnidadeSolicitante().trim().equalsIgnoreCase("")) {
                        solicitacao.setSolicitanteUnidade(solicitacao.getSolicitante() + " (" + solicitacao.getNomeUnidadeSolicitante() + ")");
                    }

                    if (solicitacao.getNomeUnidadeResponsavel() != null && !solicitacao.getNomeUnidadeResponsavel().trim().equalsIgnoreCase("")) {
                        solicitacao.setResponsavel(solicitacao.getResponsavel() + " (" + solicitacao.getNomeUnidadeResponsavel() + ")");
                    }

                    solicitacao = this.verificaSituacaoSLA(solicitacao, tc);
                }
            }
        }
        return listSolicitacaoServico;
    }

    public SolicitacaoServicoDTO restoreInfoEmails(final Integer idSolicitacaoServico, final TransactionControler tc) throws Exception {
        if (tc != null) {
            this.getDao().setTransactionControler(tc);
        }
        SolicitacaoServicoDTO solicitacaoDto = null;
        try {
            solicitacaoDto = this.getDao().restoreInfoEmails(idSolicitacaoServico);
        } catch (final Exception e) {
            throw new Exception(this.i18nMessage("solicitacaoservico.erro.recuperardadosolicitacao") + " " + idSolicitacaoServico);
        }

        return solicitacaoDto;
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listarSolicitacoesAbertasEmAndamentoPorGrupo(final int idGrupoAtual, final String situacaoSla) throws Exception {
        return this.getDao().listarSolicitacoesAbertasEmAndamentoPorGrupo(idGrupoAtual, situacaoSla);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listarSolicitacoesMultadasSuspensasPorGrupo(final int idGrupoAtual, final String situacaoSla) throws Exception {
        return this.getDao().listarSolicitacoesMultadasSuspensasPorGrupo(idGrupoAtual, situacaoSla);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listaServicosPorResponsavelNoPeriodo(final Date dataIncio, final Date dataFim, final int idFuncionario,
            final boolean mostrarIncidentes, final boolean mostrarRequisicoes, final String situacao) throws Exception {
        return this.getDao().listaServicosPorResponsavelNoPeriodo(dataIncio, dataFim, idFuncionario, mostrarIncidentes, mostrarRequisicoes, situacao);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listaServicosPorSolicitanteNoPeriodoEnviadosAoteste(final Date dataIncio, final Date dataFim,
            final int idFuncionario, final boolean mostrarIncidentes, final boolean mostrarRequisicoes) throws Exception {
        return this.getDao().listaServicosPorSolicitanteNoPeriodoEnviadosAoteste(dataIncio, dataFim, idFuncionario, mostrarIncidentes, mostrarRequisicoes);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listaServicosPorAbertosParaDocumentacao(final Date dataIncio, final Date dataFim, final boolean mostrarIncidentes,
            final boolean mostrarRequisicoes) throws Exception {
        return this.getDao().listaServicosPorAbertosParaDocumentacao(dataIncio, dataFim, mostrarIncidentes, mostrarRequisicoes);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listaServicosPorResponsavelNoPeriodoDocumentacao(final Date dataIncio, final Date dataFim,
            final int idFuncionario, final boolean mostrarIncidentes, final boolean mostrarRequisicoes) throws Exception {
        return this.getDao().listaServicosPorResponsavelNoPeriodoDocumentacao(dataIncio, dataFim, idFuncionario, mostrarIncidentes, mostrarRequisicoes);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listaServicosPorResponsavelNoPeriodoDocumentacaoPorServico(final Date dataIncio, final Date dataFim,
            final int idFuncionario, final boolean mostrarIncidentes, final boolean mostrarRequisicoes, final String listaIdsServicosHomologacaoDocumentacao)
                    throws Exception {
        return this.getDao().listaServicosPorResponsavelNoPeriodoDocumentacaoPorServico(dataIncio, dataFim, idFuncionario, mostrarIncidentes,
                mostrarRequisicoes, listaIdsServicosHomologacaoDocumentacao);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listaServicosPorAbertosPelotesteParaValidacao(final Date dataIncio, final Date dataFim,
            final boolean mostrarIncidentes, final boolean mostrarRequisicoes) throws Exception {
        return this.getDao().listaServicosPorAbertosPelotesteParaValidacao(dataIncio, dataFim, mostrarIncidentes, mostrarRequisicoes);
    }

    public void verificaSituacaoSLA(final List<SolicitacaoServicoDTO> solicitacaoDto) throws Exception {
        for (final SolicitacaoServicoDTO solicitacaoServicoDTO : solicitacaoDto) {
            this.verificaSituacaoSLA(solicitacaoServicoDTO);
        }
    }

    @Override
    public SolicitacaoServicoDTO buscarNumeroItemTrabalhoPorNumeroSolicitacao(final int idSolicitacao) throws Exception {
        return this.getDao().buscarNumeroItemTrabalhoPorNumeroSolicitacao(idSolicitacao);
    }

    @Override
    public Collection<RelatorioEficaciaTesteDTO> listaSolicitacaoPorServicosAbertosNoPerido(final Date dataIncio, final Date dataFim,
            final List<ServicoDTO> listaServicos) throws Exception {
        return this.getDao().listaSolicitacaoPorServicosAbertosNoPerido(dataIncio, dataFim, listaServicos);
    }

    @Override
    public Collection<RelatorioDocumentacaoDeFuncionalidadesNovasOuAlteradasNoPeriodoDTO> listaQtdSolicitacoesCanceladasFinalizadasporServicoNoPeriodo(
            final Date dataIncio, final Date dataFim, final List<ServicoDTO> listaServicos) throws Exception {
        return this.getDao().listaQtdSolicitacoesCanceladasFinalizadasporServicoNoPeriodo(dataIncio, dataFim, listaServicos);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findSolicitacoesNaoResolvidasNoPrazoKPI(
            final RelatorioIncidentesNaoResolvidosDTO relatorioIncidentesNaoResolvidosDTO) throws Exception {
        return this.getDao().findSolicitacoesNaoResolvidasNoPrazoKPI(relatorioIncidentesNaoResolvidosDTO);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> findSolicitacoesNaoResolvidasEntrePrazoKPI(
            final RelatorioIncidentesNaoResolvidosDTO relatorioIncidentesNaoResolvidosDTO) throws Exception {
        return this.getDao().findSolicitacoesNaoResolvidasEntrePrazoKPI(relatorioIncidentesNaoResolvidosDTO);
    }

    @Override
    public Collection<RelatorioKpiProdutividadeDTO> listaQuantitativaEmpregadoSolicitacoesEmcaminhaExito(
            final RelatorioKpiProdutividadeDTO relatorioKpiProdutividadeDto) throws Exception {

        Collection<RelatorioKpiProdutividadeDTO> listaQuantitativaEmpregadoSolicitacoesEmcaminhaExito = new ArrayList<>();

        listaQuantitativaEmpregadoSolicitacoesEmcaminhaExito = this.getDao().listaQuantitativaEmpregadoSolicitacoesEmcaminhaExito(relatorioKpiProdutividadeDto);

        if (listaQuantitativaEmpregadoSolicitacoesEmcaminhaExito != null) {

            for (final RelatorioKpiProdutividadeDTO relatorioKpiProdutividade : listaQuantitativaEmpregadoSolicitacoesEmcaminhaExito) {
                if (relatorioKpiProdutividade.getQtdeencaminhadas() > 0) {
                    Double porcentagemExecutadaExito;
                    porcentagemExecutadaExito = 100 * (relatorioKpiProdutividade.getQtdeexito().doubleValue() / relatorioKpiProdutividade.getQtdeencaminhadas()
                            .doubleValue());
                    relatorioKpiProdutividade.setPorcentagemExecutadaExito(porcentagemExecutadaExito);

                } else {
                    relatorioKpiProdutividade.setQtdeExecutada(0);

                }
            }

            return listaQuantitativaEmpregadoSolicitacoesEmcaminhaExito;
        }

        return null;
    }

    public Collection<SolicitacaoServicoDTO> listSolicitacoesFilhasFiltradas(final GerenciamentoServicosDTO gerenciamentoBean,
            final Collection<ContratoDTO> listContratoUsuarioLogado, final TransactionControler tc) throws Exception {
        final SolicitacaoServicoDao solicitacaoServicoDao = this.getDao();
        if (tc != null) {
            solicitacaoServicoDao.setTransactionControler(tc);
        }
        return solicitacaoServicoDao.listSolicitacoesFilhasFiltradas(gerenciamentoBean, listContratoUsuarioLogado);
    }

    @Override
    public Collection<RelatorioCausaSolucaoDTO> listaCausaSolicitacao(final RelatorioCausaSolucaoDTO relatorioCausaSolicitacao) throws Exception {
        return this.getSolicitacaoServicoDao().listaCausaSolicitacao(relatorioCausaSolicitacao);
    }

    @Override
    public Collection<RelatorioCausaSolucaoDTO> listaSolucaoSolicitacao(final RelatorioCausaSolucaoDTO relatorioCausaSolicitacao) throws Exception {
        return this.getSolicitacaoServicoDao().listaSolucaoSolicitacao(relatorioCausaSolicitacao);
    }

    @Override
    public Collection<RelatorioCausaSolucaoDTO> listaCausaSolucaoAnalitico(final RelatorioCausaSolucaoDTO relatorioCausaSolicitacao) throws Exception {
        return this.getSolicitacaoServicoDao().listaCausaSolucaoAnalitico(relatorioCausaSolicitacao);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listSolicitacoesFilhasFiltradas(final GerenciamentoServicosDTO gerenciamentoBean,
            final Collection<ContratoDTO> listContratoUsuarioLogado) throws Exception {

        return this.listSolicitacoesFilhasFiltradas(gerenciamentoBean, listContratoUsuarioLogado, null);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listSolicitacoesFilhas() throws Exception {
        return this.listSolicitacoesFilhas(null);
    }

    @Override
    public Collection<SolicitacaoServicoDTO> listByTarefas(final Collection<TarefaFluxoDTO> listTarefas, final TipoSolicitacaoServico[] tiposSolicitacao)
            throws Exception {
        return this.listByTarefas(listTarefas, tiposSolicitacao, null);
    }

    public Collection<SolicitacaoServicoDTO> listByTarefas(final Collection<TarefaFluxoDTO> listTarefas, final Integer qtdAtual, final Integer qtdAPaginacao,
            final GerenciamentoServicosDTO gerenciamentoBean, final Collection<ContratoDTO> listContratoUsuarioLogado) throws Exception {
        return this.listByTarefas(listTarefas, qtdAtual, qtdAPaginacao, gerenciamentoBean, listContratoUsuarioLogado, null);
    }

    @Override
    public void determinaPrazoLimite(final SolicitacaoServicoDTO solicitacao, Integer idCalendario, final TransactionControler tc) throws Exception {
        if (solicitacao.getDataHoraInicioSLA() == null) {
            return;
        }

        if (idCalendario == null || idCalendario.intValue() == 0) {
            final ServicoContratoDao servicoContratoDao = new ServicoContratoDao();
            if (tc != null) {
                servicoContratoDao.setTransactionControler(tc);
            }
            final ServicoContratoDTO servicoContrato = servicoContratoDao.findByIdContratoAndIdServico(solicitacao.getIdContrato(), solicitacao.getIdServico());
            if (servicoContrato == null) {
                throw new LogicException(this.i18nMessage("solicitacaoservico.validacao.servicolocalizado"));
            }
            idCalendario = servicoContrato.getIdCalendario();
        }

        if (solicitacao.getPrazoHH() == null) {
            solicitacao.setPrazoHH(0);
        }
        if (solicitacao.getPrazoMM() == null) {
            solicitacao.setPrazoMM(0);
        }

        CalculoJornadaDTO calculo = null;

        if (solicitacao.getHouveMudanca() != null && solicitacao.getHouveMudanca().equalsIgnoreCase("S") && solicitacao.getDataHoraReativacaoSLA() != null
                && solicitacao.getTempoDecorridoHH() != null
                && solicitacao.getPrazoHH() * 100 + solicitacao.getPrazoMM() > solicitacao.getTempoDecorridoHH() * 100 + solicitacao.getTempoDecorridoMM()) {
            final Integer novoPrazoHH = solicitacao.getPrazoHH() - solicitacao.getTempoDecorridoHH();
            final Integer novoPrazoMM = solicitacao.getPrazoMM() - solicitacao.getTempoDecorridoMM();

            calculo = new CalculoJornadaDTO(idCalendario, solicitacao.getDataHoraReativacaoSLA(), novoPrazoHH, novoPrazoMM);
            calculo = new CalendarioServiceEjb().calculaDataHoraFinal(calculo, true, tc);
        } else {
            if (solicitacao.getDataHoraReativacaoSLA() != null && solicitacao.getTempoDecorridoHH() == 0 && solicitacao.getTempoDecorridoMM() == 0) {
                calculo = new CalculoJornadaDTO(idCalendario, solicitacao.getDataHoraReativacaoSLA(), solicitacao.getPrazoHH(), solicitacao.getPrazoMM());
            } else {
                calculo = new CalculoJornadaDTO(idCalendario, solicitacao.getDataHoraInicioSLA(), solicitacao.getPrazoHH(), solicitacao.getPrazoMM());
            }
            calculo = new CalendarioServiceEjb().calculaDataHoraFinal(calculo, true, tc);
        }

        solicitacao.setDataHoraLimite(calculo.getDataHoraFinal());
    }

    @Override
    public Integer numeroSolicitacoesForaPeriodo(final RelatorioIncidentesNaoResolvidosDTO relatorioIncidentesNaoResolvidosDTO) throws PersistenceException,
    ServiceException {
        return this.getSolicitacaoServicoDao().numeroSolicitacoesForaPeriodo(relatorioIncidentesNaoResolvidosDTO);
    }

    /**
     * Novos métodos para paginação
     *
     * @author thyen.chang
     *
     * @param pesquisaSolicitacaoServicoDto
     * @return
     * @throws Exception
     */
    @Override
    public Long listaRelatorioGetQuantidadeRegistros(final PesquisaSolicitacaoServicoDTO pesquisaSolicitacaoServicoDto) throws Exception {
        return this.getSolicitacaoServicoDao().listaRelatorioGetQuantidadeRegistros(pesquisaSolicitacaoServicoDto);
    }

    @Override
    public List<SolicitacaoServicoDTO> listRelatorioGetListaPaginada(final PesquisaSolicitacaoServicoDTO pesquisaSolicitacaoServicoDTO,
            final Integer paginaAtual, final Integer quantidadePorPagina) throws Exception {
        return this.getSolicitacaoServicoDao().listRelatorioGetListaPaginada(pesquisaSolicitacaoServicoDTO, paginaAtual, quantidadePorPagina);
    }

}
