package br.com.centralit.citcorpore.bpm.negocio;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.bpm.dto.AtribuicaoFluxoDTO;
import br.com.centralit.bpm.dto.EventoFluxoDTO;
import br.com.centralit.bpm.dto.FluxoDTO;
import br.com.centralit.bpm.dto.TarefaFluxoDTO;
import br.com.centralit.bpm.integracao.AtribuicaoFluxoDao;
import br.com.centralit.bpm.integracao.TarefaFluxoDao;
import br.com.centralit.bpm.negocio.InstanciaFluxo;
import br.com.centralit.bpm.negocio.Tarefa;
import br.com.centralit.citcorpore.bean.AlcadaDTO;
import br.com.centralit.citcorpore.bean.AlcadaProcessoNegocioDTO;
import br.com.centralit.citcorpore.bean.CentroResultadoDTO;
import br.com.centralit.citcorpore.bean.CidadesDTO;
import br.com.centralit.citcorpore.bean.DespesaViagemDTO;
import br.com.centralit.citcorpore.bean.EmpregadoDTO;
import br.com.centralit.citcorpore.bean.IntegranteViagemDTO;
import br.com.centralit.citcorpore.bean.ModeloEmailDTO;
import br.com.centralit.citcorpore.bean.ParecerDTO;
import br.com.centralit.citcorpore.bean.PrestacaoContasViagemDTO;
import br.com.centralit.citcorpore.bean.ProjetoDTO;
import br.com.centralit.citcorpore.bean.RequisicaoViagemDTO;
import br.com.centralit.citcorpore.bean.RoteiroViagemDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.integracao.CentroResultadoDao;
import br.com.centralit.citcorpore.integracao.CidadesDao;
import br.com.centralit.citcorpore.integracao.DespesaViagemDAO;
import br.com.centralit.citcorpore.integracao.EmpregadoDao;
import br.com.centralit.citcorpore.integracao.IntegranteViagemDao;
import br.com.centralit.citcorpore.integracao.ModeloEmailDao;
import br.com.centralit.citcorpore.integracao.ParecerDao;
import br.com.centralit.citcorpore.integracao.PrestacaoContasViagemDao;
import br.com.centralit.citcorpore.integracao.ProjetoDao;
import br.com.centralit.citcorpore.integracao.RequisicaoViagemDAO;
import br.com.centralit.citcorpore.integracao.RoteiroViagemDAO;
import br.com.centralit.citcorpore.integracao.SolicitacaoServicoDao;
import br.com.centralit.citcorpore.integracao.UsuarioDao;
import br.com.centralit.citcorpore.mail.MensagemEmail;
import br.com.centralit.citcorpore.negocio.EmpregadoService;
import br.com.centralit.citcorpore.negocio.IntegranteViagemService;
import br.com.centralit.citcorpore.negocio.RequisicaoViagemService;
import br.com.centralit.citcorpore.negocio.RoteiroViagemService;
import br.com.centralit.citcorpore.negocio.SolicitacaoServicoServiceEjb;
import br.com.centralit.citcorpore.negocio.alcada.AlcadaRequisicaoViagem;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Reflexao;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilI18N;

public class ExecucaoRequisicaoViagem extends ExecucaoSolicitacao {

    public ExecucaoRequisicaoViagem() {
        super();
    }

    @Override
    public String i18n_Message(final UsuarioDTO usuario, final String key) {
        if (usuario != null) {
            if (UtilI18N.internacionaliza(usuario.getLocale(), key) != null) {
                return UtilI18N.internacionaliza(usuario.getLocale(), key);
            }
            return key;
        }
        return key;
    }

    @Override
    public InstanciaFluxo inicia() throws Exception {
        return super.inicia();
    }

    @Override
    public InstanciaFluxo inicia(final FluxoDTO fluxoDto, final Integer idFase) throws Exception {

        final String idGrupo = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.ID_GRUPO_PADRAO_REQ_VIAGEM_EXECUCAO, null);
        if (idGrupo == null || idGrupo.trim().equals("")) {
            throw new Exception(this.i18n_Message("citcorpore.comum.grupoPadraoNaoParametrizado"));
        }
        this.getSolicitacaoServicoDto().setIdGrupoAtual(new Integer(idGrupo.trim()));
        return super.inicia(fluxoDto, idFase);

    }

    @Override
    public void mapObjetoNegocio(final Map<String, Object> map) throws Exception {
        super.mapObjetoNegocio(map);
    }

    @Override
    public void executaEvento(final EventoFluxoDTO eventoFluxoDto) throws Exception {
        super.executaEvento(eventoFluxoDto);
    }

    @Override
    public void complementaInformacoesEmail(final SolicitacaoServicoDTO solicitacaoServicoDto) throws Exception {
        super.complementaInformacoesEmail(solicitacaoServicoDto);

        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        CentroResultadoDTO centroResultado = new CentroResultadoDTO();
        ProjetoDTO projetoDto = new ProjetoDTO();
        CidadesDTO cidade = new CidadesDTO();
        final StringBuilder strItens = new StringBuilder();

        if (requisicaoViagemDto != null) {
            centroResultado = this.recuperaCentroCusto(requisicaoViagemDto);
            projetoDto = this.recuperaProjeto(requisicaoViagemDto);
            strItens.append("<b>Data Inicio: </b>" + UtilDatas.dateToSTR(requisicaoViagemDto.getDataInicioViagem()) + "<br>");
            strItens.append("<b>Data Fim: </b>" + UtilDatas.dateToSTR(requisicaoViagemDto.getDataFimViagem()) + "<br>");
            if (requisicaoViagemDto.getIdCidadeOrigem() != null) {
                cidade = this.recuperaCidade(requisicaoViagemDto.getIdCidadeOrigem());
                strItens.append("<b>Cidade Origem: </b>" + cidade.getNomeCidade() + "<br>");
            }
            if (requisicaoViagemDto.getIdCidadeDestino() != null) {
                cidade = this.recuperaCidade(requisicaoViagemDto.getIdCidadeDestino());
                strItens.append("<b>Cidade Destino: </b>" + cidade.getNomeCidade() + "<br>");
            }
            if (centroResultado != null) {
                strItens.append("<b>Centro de custo: </b>" + centroResultado.getNomeCentroResultado() + "<br>");
            }
            if (projetoDto != null) {
                strItens.append("<b>Projeto: </b>" + projetoDto.getNomeProjeto() + "<br>");
            }
            strItens.append("<b>Motivo: </b><br>");
            strItens.append("" + requisicaoViagemDto.getDescricaoMotivo() + "");
            solicitacaoServicoDto.setInformacoesComplementaresHTML(strItens.toString());

            if (requisicaoViagemDto.getEstado().equalsIgnoreCase(RequisicaoViagemDTO.getAguardandoAprovacao())) {
                solicitacaoServicoDto.setTituloEmail(this.retornaMenorPrazoContacao(solicitacaoServicoDto.getIdSolicitacaoServico()));
            }
        }
    }

    public RequisicaoViagemDTO recuperaRequisicaoViagem() throws Exception {
        final RequisicaoViagemDAO requisicaoViagemDao = new RequisicaoViagemDAO();
        this.setTransacaoDao(requisicaoViagemDao);
        final SolicitacaoServicoDTO solicitacaoDto = this.getSolicitacaoServicoDto();
        RequisicaoViagemDTO requisicaoViagemDto = new RequisicaoViagemDTO();
        requisicaoViagemDto.setIdSolicitacaoServico(solicitacaoDto.getIdSolicitacaoServico());
        requisicaoViagemDto = (RequisicaoViagemDTO) requisicaoViagemDao.restore(requisicaoViagemDto);
        Reflexao.copyPropertyValues(solicitacaoDto, requisicaoViagemDto);
        return requisicaoViagemDto;
    }

    public String retornaMenorPrazoContacao(final Integer idSolicitacaoServico) throws Exception {
        final DespesaViagemDAO despesaViagemDAO = new DespesaViagemDAO();
        final List<DespesaViagemDTO> listaDespesa = (List<DespesaViagemDTO>) despesaViagemDAO.findDespesaViagemByIdSolicitacao(idSolicitacaoServico);
        return UtilDatas.formatTimestamp(listaDespesa.get(0).getValidade());
    }

    public CentroResultadoDTO recuperaCentroCusto(final RequisicaoViagemDTO requisicaoViagemDto) throws Exception {
        final CentroResultadoDTO centroCustoDto = new CentroResultadoDTO();
        centroCustoDto.setIdCentroResultado(requisicaoViagemDto.getIdCentroCusto());
        return (CentroResultadoDTO) new CentroResultadoDao().restore(centroCustoDto);
    }

    public ProjetoDTO recuperaProjeto(final RequisicaoViagemDTO requisicaoViagemDto) throws Exception {
        final ProjetoDTO projetoDto = new ProjetoDTO();
        if (requisicaoViagemDto.getIdProjeto() != null) {
            projetoDto.setIdProjeto(requisicaoViagemDto.getIdProjeto());
            return (ProjetoDTO) new ProjetoDao().restore(projetoDto);

        }
        return null;

    }

    public CidadesDTO recuperaCidade(final Integer idCidade) throws Exception {
        final CidadesDTO cidadeDto = new CidadesDTO();
        if (idCidade != null) {
            cidadeDto.setIdCidade(idCidade);
            return (CidadesDTO) new CidadesDao().restore(cidadeDto);
        }
        return null;
    }

    public AlcadaDTO recuperaAlcada(final RequisicaoViagemDTO requisicaoViagemDto) throws Exception {
        return new AlcadaRequisicaoViagem().determinaAlcada(requisicaoViagemDto, this.recuperaCentroCusto(requisicaoViagemDto), this.getTransacao());
    }

    public StringBuilder recuperaLoginAutorizadores() throws Exception {
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        return this.recuperaLoginAutorizadores(requisicaoViagemDto);
    }

    public StringBuilder recuperaLoginAutorizadores(final RequisicaoViagemDTO requisicaoViagemDto) throws Exception {
        final StringBuilder result = new StringBuilder();
        final AlcadaDTO alcadaDto = this.recuperaAlcada(requisicaoViagemDto);
        if (alcadaDto != null && alcadaDto.getColResponsaveis() != null) {
            int i = 0;
            final UsuarioDao usuarioDao = new UsuarioDao();
            for (final EmpregadoDTO empregadoDto : alcadaDto.getColResponsaveis()) {
                final UsuarioDTO usuarioDto = usuarioDao.restoreAtivoByIdEmpregado(empregadoDto.getIdEmpregado());
                if (usuarioDto != null) {
                    if (i > 0) {
                        result.append(";");
                    }
                    result.append(usuarioDto.getLogin());
                    i++;
                }
            }
        }
        if (result.length() == 0) {
            throw new LogicException("Não foi encontrado nenhum autorizador da requisição");
        }
        return result;
    }

    public boolean exigeAutorizacao() throws Exception {
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        return this.exigeAutorizacao(requisicaoViagemDto);
    }

    public void alteraEstadoCompra() throws Exception {
        RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final RequisicaoViagemDAO requisicaoViagemDAO = new RequisicaoViagemDAO();
        requisicaoViagemDAO.setTransactionControler(this.getTransacao());
        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        integranteViagemDao.setTransactionControler(this.getTransacao());
        final Collection<IntegranteViagemDTO> colIntegrantes = integranteViagemDao.recuperaIntegrantesViagemByIdSolicitacaoEstado(
                requisicaoViagemDto.getIdSolicitacaoServico(), RequisicaoViagemDTO.AGUARDANDO_PLANEJAMENTO);

        if (colIntegrantes != null && colIntegrantes.size() > 0) {
            for (final IntegranteViagemDTO integranteViagemDTO : colIntegrantes) {
                integranteViagemDTO.setEstado(RequisicaoViagemDTO.AGUARDANDO_COMPRAS);
                integranteViagemDao.updateNotNull(integranteViagemDTO);
            }
        }

        requisicaoViagemDto = (RequisicaoViagemDTO) requisicaoViagemDAO.restore(requisicaoViagemDto);
        requisicaoViagemDto.setEstado(RequisicaoViagemDTO.AGUARDANDO_COMPRAS);
        requisicaoViagemDAO.updateNotNull(requisicaoViagemDto);
    }

    /**
     * Metodo que valida a exigencia da autorização da requisição de viagem
     *
     * @param requisicaoViagemDto
     * @return
     * @throws Exception
     */
    public boolean exigeAutorizacao(final RequisicaoViagemDTO requisicaoViagemDto) throws Exception {
        final DespesaViagemDAO despesaViagemDAO = new DespesaViagemDAO();
        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        final RequisicaoViagemDTO requisicaoViagem = this.recuperaRequisicaoViagem();

        final Collection<IntegranteViagemDTO> colIntegrantes = integranteViagemDao.recuperaIntegrantesViagemByIdSolicitacaoEstado(
                requisicaoViagem.getIdSolicitacaoServico(), RequisicaoViagemDTO.AGUARDANDO_PLANEJAMENTO);
        if (colIntegrantes != null && !colIntegrantes.isEmpty()) {
            for (final IntegranteViagemDTO dto : colIntegrantes) {
                if (dto.getRemarcacao() != null && dto.getRemarcacao().equalsIgnoreCase("S")) {
                    final Double vlrTotalNovo = despesaViagemDAO.buscaValorTotalViagem(requisicaoViagem.getIdSolicitacaoServico());

                    final Double vlrTotalAntigo = despesaViagemDAO.buscaValorViagemHistorico(requisicaoViagem.getIdSolicitacaoServico());

                    final String valorAlcadaSemAutorizacao = ParametroUtil.getValorParametroCitSmartHashMap(
                            ParametroSistema.VALOR_ALCADA_SEM_NESSIDADE_AUTORIZACAO, "30");
                    if (valorAlcadaSemAutorizacao == null) {
                        throw new LogicException(this.i18n_Message("requisicaoViagem.percentualDeAceitacaoParaRemarcacaoDeViagem"));
                    }

                    final Double percentualPermissao = new Double(valorAlcadaSemAutorizacao);

                    if (vlrTotalNovo > vlrTotalAntigo) {
                        final Double vlrTotal = vlrTotalNovo - vlrTotalAntigo;
                        final Double percentualRemarcado = vlrTotal * 100 / vlrTotalAntigo;

                        if (percentualRemarcado > percentualPermissao) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }

                }
            }
        }

        final AlcadaDTO alcadaDto = this.recuperaAlcada(requisicaoViagem);
        boolean result = false;

        if (alcadaDto != null) {
            if (alcadaDto.getColResponsaveis() != null) {
                result = true;
                for (final EmpregadoDTO empregadoDto : alcadaDto.getColResponsaveis()) {
                    if (this.getSolicitacaoServicoDto().getIdSolicitante().intValue() == empregadoDto.getIdEmpregado().intValue()) {
                        result = false;
                    }
                }
            }
        }
        return result;
    }

    public ExecucaoRequisicaoViagem(final RequisicaoViagemDTO requisicaoViagemDto, final TransactionControler tc) {
        super(requisicaoViagemDto, tc);
    }

    public ExecucaoRequisicaoViagem(final TransactionControler tc) {
        super(tc);
    }

    public boolean requisicaoAutorizadaSim() throws Exception {
        final RequisicaoViagemDTO requisicaoDto = this.recuperaRequisicaoViagem();
        boolean autorizado = false;

        if (!requisicaoDto.getEstado().equalsIgnoreCase(Enumerados.SituacaoSolicitacaoServico.Cancelada.name())) {
            ParecerDTO parecerDto = new ParecerDTO();

            if (requisicaoDto.getIdAprovacao() != null) {
                parecerDto = this.recuperaParecer(requisicaoDto);
                if (parecerDto != null) {
                    requisicaoDto.setAutorizado(parecerDto.getAprovado());
                    autorizado = requisicaoDto.getAutorizado() != null && requisicaoDto.getAutorizado().equalsIgnoreCase("S");
                }
            }
        }

        return autorizado;
    }

    public boolean requisicaoAutorizadaNao() throws Exception {
        final RequisicaoViagemDTO requisicaoDto = this.recuperaRequisicaoViagem();
        boolean autorizado = false;

        if (!requisicaoDto.getEstado().equalsIgnoreCase(Enumerados.SituacaoSolicitacaoServico.Cancelada.name())) {
            ParecerDTO parecerDto = new ParecerDTO();

            if (requisicaoDto.getIdAprovacao() != null) {
                parecerDto = this.recuperaParecer(requisicaoDto);
                if (parecerDto != null) {
                    requisicaoDto.setAutorizado(parecerDto.getAprovado());
                    autorizado = requisicaoDto.getAutorizado() != null && requisicaoDto.getAutorizado().equalsIgnoreCase("N");
                }
            }
        }

        return autorizado;
    }

    public ParecerDTO recuperaParecer(final RequisicaoViagemDTO requisicaoViagemDto) throws Exception {

        final ParecerDTO parecerDto = new ParecerDTO();
        if (requisicaoViagemDto.getIdAprovacao() != null) {
            parecerDto.setIdParecer(requisicaoViagemDto.getIdAprovacao());

            final ParecerDao parecerDao = new ParecerDao();
            parecerDao.setTransactionControler(this.getTransacao());

            return (ParecerDTO) parecerDao.restore(parecerDto);

        }

        return null;

    }

    public boolean validaPrazoItens() throws Exception {
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        RoteiroViagemDTO roteiroViagemDTO = new RoteiroViagemDTO();

        final Timestamp dataHoraAtual = UtilDatas.getDataHoraAtual();

        final Collection<IntegranteViagemDTO> colIntegrantes = new IntegranteViagemDao().recuperaIntegrantesViagemByIdSolicitacaoEstado(
                requisicaoViagemDto.getIdSolicitacaoServico(), RequisicaoViagemDTO.EM_AUTORIZACAO);
        if (colIntegrantes != null && !colIntegrantes.isEmpty()) {
            for (final IntegranteViagemDTO integranteViagemDto : colIntegrantes) {
                roteiroViagemDTO = new RoteiroViagemDAO().findByIdIntegrante(integranteViagemDto.getIdIntegranteViagem());
                final Collection<DespesaViagemDTO> colDespesa = new DespesaViagemDAO().findDespesasAtivasViagemByIdRoteiro(roteiroViagemDTO
                        .getIdRoteiroViagem());
                if (colDespesa != null && !colDespesa.isEmpty()) {
                    for (final DespesaViagemDTO despesaViagemDTO : colDespesa) {
                        if (despesaViagemDTO.getValidade() != null && !despesaViagemDTO.getValidade().equals("")) {
                            return despesaViagemDTO.getValidade().compareTo(dataHoraAtual) < 0;
                        }
                    }
                }
            }
        }
        return false;
    }

    public StringBuilder recuperaIntegrantesEmPrestacaoContas() throws Exception {
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        return this.recuperaLoginIntegrantesPrestacaoContas(requisicaoViagemDto);
    }

    public StringBuilder recuperaLoginIntegrantesPrestacaoContas(final RequisicaoViagemDTO requisicaoViagemDto) throws Exception {
        final IntegranteViagemDao dao = new IntegranteViagemDao();

        try {
            final Collection<IntegranteViagemDTO> colIntegrantes = dao.recuperaIntegrantesViagemByIdSolicitacaoEstadoPrestConta(
                    requisicaoViagemDto.getIdSolicitacaoServico(), RequisicaoViagemDTO.EM_PRESTACAOCONTAS, "S");
            return this.montarUsuarios(colIntegrantes);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public StringBuilder montarUsuarios(final Collection<IntegranteViagemDTO> colIntegrantes) throws Exception {
        final StringBuilder result = new StringBuilder();
        try {
            if (colIntegrantes != null) {
                int i = 0;
                for (final IntegranteViagemDTO integrantes : colIntegrantes) {
                    final UsuarioDao usuarioDao = new UsuarioDao();

                    final Integer idEmpregado = integrantes.getIdRespPrestacaoContas() == null ? integrantes.getIdEmpregado() : integrantes
                            .getIdRespPrestacaoContas();

                    final UsuarioDTO usuarioDto = usuarioDao.restoreAtivoByIdEmpregado(idEmpregado);
                    if (usuarioDto != null) {
                        if (i > 0) {
                            result.append(";");
                        }
                        result.append(usuarioDto.getLogin());
                        i++;
                    }
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        if (result.length() == 0) {
            throw new LogicException("Não foi encontrado nenhum Integrante da requisição");
        }

        return result;
    }

    public void setaPrestacaoContaSimIntegrante(final Tarefa tarefa) throws Exception {
        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        integranteViagemDao.setTransactionControler(this.getTransacao());
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final List<IntegranteViagemDTO> listIntegranteViagemDTO = (List<IntegranteViagemDTO>) integranteViagemDao
                .recuperaIntegrantesViagemByIdSolicitacaoEstado(requisicaoViagemDto.getIdSolicitacaoServico(), RequisicaoViagemDTO.EM_PRESTACAOCONTAS);
        if (listIntegranteViagemDTO != null && listIntegranteViagemDTO.size() > 0) {
            for (final IntegranteViagemDTO integranteViagemDTO : listIntegranteViagemDTO) {
                integranteViagemDTO.setEmPrestacaoContas("S");
                integranteViagemDao.updateNotNull(integranteViagemDTO);
                break;
            }
        }
    }

    public void setaIdTarefaAoIntegranteViagem(final Tarefa tarefa) throws Exception {

        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        integranteViagemDao.setTransactionControler(this.getTransacao());
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final List<IntegranteViagemDTO> listIntegranteViagemDTO = (List<IntegranteViagemDTO>) integranteViagemDao
                .recuperaIntegrantesViagemByIdSolicitacaoEstado(requisicaoViagemDto.getIdSolicitacaoServico(), RequisicaoViagemDTO.EM_PRESTACAOCONTAS);

        for (final IntegranteViagemDTO integranteViagemDTO : listIntegranteViagemDTO) {
            if (integranteViagemDTO.getIdTarefa() == null || integranteViagemDTO.getIdTarefa().equals("")) {
                integranteViagemDTO.setIdTarefa(tarefa.getIdItemTrabalho());
                integranteViagemDao.update(integranteViagemDTO);
                break;
            }
        }
    }

    public void setaIdTarefaAoIntegranteViagemCorrecao(final Tarefa tarefa) throws Exception {

        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        integranteViagemDao.setTransactionControler(this.getTransacao());
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final List<IntegranteViagemDTO> listIntegranteViagemDTO = (List<IntegranteViagemDTO>) integranteViagemDao
                .recuperaIntegrantesViagemByIdSolicitacaoEstado(requisicaoViagemDto.getIdSolicitacaoServico(), RequisicaoViagemDTO.AGUARDANDO_CORRECAO);

        for (final IntegranteViagemDTO integranteViagemDTO : listIntegranteViagemDTO) {
            if (integranteViagemDTO.getIdTarefa() == null || integranteViagemDTO.getIdTarefa().equals("")) {
                integranteViagemDTO.setIdTarefa(tarefa.getIdItemTrabalho());
                integranteViagemDao.update(integranteViagemDTO);
                break;
            }
        }
    }

    public void setaIdTarefaAoIntegranteConferencia(final Tarefa tarefa) throws Exception {

        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        integranteViagemDao.setTransactionControler(this.getTransacao());
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final List<IntegranteViagemDTO> listIntegranteViagemDTO = (List<IntegranteViagemDTO>) integranteViagemDao
                .recuperaIntegrantesViagemByIdSolicitacaoEstado(requisicaoViagemDto.getIdSolicitacaoServico(), RequisicaoViagemDTO.AGUARDANDO_CONFERENCIA);

        for (final IntegranteViagemDTO integranteViagemDTO : listIntegranteViagemDTO) {
            integranteViagemDTO.setIdTarefa(tarefa.getIdItemTrabalho());
            integranteViagemDTO.setEstado(RequisicaoViagemDTO.EM_CONFERENCIA);
            integranteViagemDao.updateNotNull(integranteViagemDTO);
            break;
        }
    }

    public void setaPrestacaoContaNaoIntegrante(final Tarefa tarefa) throws Exception {
        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        integranteViagemDao.setTransactionControler(this.getTransacao());
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final List<IntegranteViagemDTO> listIntegranteViagemDTO = (List<IntegranteViagemDTO>) integranteViagemDao
                .findAllPrestacaoContasByIdSolicitacao(requisicaoViagemDto.getIdSolicitacaoServico());
        if (listIntegranteViagemDTO != null && listIntegranteViagemDTO.size() > 0) {
            for (final IntegranteViagemDTO integranteViagemDTO : listIntegranteViagemDTO) {
                integranteViagemDTO.setEmPrestacaoContas("N");
                integranteViagemDao.updateNotNull(integranteViagemDTO);
            }
        }
    }

    public void associaItemTrabalhoAoIntegrante(final Tarefa tarefa) throws Exception {
        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        integranteViagemDao.setTransactionControler(this.getTransacao());
        final List<IntegranteViagemDTO> listaIntegrantes = (List<IntegranteViagemDTO>) integranteViagemDao.recuperaIntegrantesViagemByIdSolicitacaoEstado(this
                .getSolicitacaoServicoDto().getIdSolicitacaoServico(), RequisicaoViagemDTO.AGUARDANDO_ADIANTAMENTO);
        if (listaIntegrantes != null && !listaIntegrantes.isEmpty()) {
            for (final IntegranteViagemDTO dto : listaIntegrantes) {
                dto.setIdTarefa(tarefa.getIdItemTrabalho());
                integranteViagemDao.updateNotNull(dto);
            }
        }
    }

    public void associaItemTrabalhoAutorizacaoRequisicao(final Tarefa tarefa) throws Exception {
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final RequisicaoViagemDAO dao = new RequisicaoViagemDAO();
        this.setTransacaoDao(dao);
        if (requisicaoViagemDto != null && requisicaoViagemDto.getIdSolicitacaoServico() != null) {
            requisicaoViagemDto.setIdItemTrabalho(tarefa.getIdItemTrabalho());
            dao.updateNotNull(requisicaoViagemDto);
        }
    }

    public void setEstadoEmAutorizacao() throws Exception {
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        integranteViagemDao.setTransactionControler(this.getTransacao());
        final List<IntegranteViagemDTO> listIntegranteViagemDTO = (List<IntegranteViagemDTO>) integranteViagemDao
                .recuperaIntegrantesViagemByIdSolicitacaoEstado(requisicaoViagemDto.getIdSolicitacaoServico(), RequisicaoViagemDTO.AGUARDANDO_APROVACAO);

        final RequisicaoViagemDAO dao = new RequisicaoViagemDAO();
        this.setTransacaoDao(dao);

        if (requisicaoViagemDto != null && requisicaoViagemDto.getIdSolicitacaoServico() != null) {
            requisicaoViagemDto.setEscalar(RequisicaoViagemDTO.EM_AUTORIZACAO);
            dao.updateNotNull(requisicaoViagemDto);
        }

        if (listIntegranteViagemDTO != null && !listIntegranteViagemDTO.isEmpty()) {
            for (final IntegranteViagemDTO integranteViagemDTO : listIntegranteViagemDTO) {
                integranteViagemDTO.setEstado(RequisicaoViagemDTO.EM_AUTORIZACAO);
                integranteViagemDao.updateNotNull(integranteViagemDTO);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void executaItemTrabalhoPrestacaoConferencia(final Tarefa tarefa) throws Exception {
        final PrestacaoContasViagemDao dao = new PrestacaoContasViagemDao();
        this.setTransacaoDao(dao);
        final List<PrestacaoContasViagemDTO> listaItens = (List<PrestacaoContasViagemDTO>) dao.findByTarefa(tarefa.getIdItemTrabalho());
        if (listaItens != null) {
            final PrestacaoContasViagemDTO prestacaoContas = listaItens.get(0);
            prestacaoContas.setIdItemTrabalho(null);
            dao.update(prestacaoContas);
        }
    }

    @SuppressWarnings("unchecked")
    public void associaItemTrabalhoPrestacaoCorrecao(final Tarefa tarefa) throws Exception {
        final PrestacaoContasViagemDao dao = new PrestacaoContasViagemDao();
        final Integer idSolicitacaoServico = this.getSolicitacaoServicoDto().getIdSolicitacaoServico();
        this.setTransacaoDao(dao);
        final List<PrestacaoContasViagemDTO> listaItens = (List<PrestacaoContasViagemDTO>) dao.findBySituacao(idSolicitacaoServico,
                PrestacaoContasViagemDTO.AGUARDANDO_CORRECAO);
        if (listaItens != null) {
            final PrestacaoContasViagemDTO prestacaoContas = listaItens.get(0);
            prestacaoContas.setIdItemTrabalho(tarefa.getIdItemTrabalho());
            prestacaoContas.setSituacao(PrestacaoContasViagemDTO.EM_CORRECAO);
            this.atribuiUsuarioCorrecao(prestacaoContas, tarefa);
            dao.update(prestacaoContas);
        }
    }

    public void cancelaTarefaPrestacaoContas(final Integer idTarefa, final String login) throws Exception {
        final SolicitacaoServicoDTO solicitacaoDto = this.getSolicitacaoServicoDto();
        final TarefaFluxoDTO tarefaDto = this.recuperaTarefa(idTarefa);
        final String motivo = "Remarcação";
        this.cancelaTarefa(login, solicitacaoDto, tarefaDto, motivo);
    }

    @SuppressWarnings("unchecked")
    public boolean corrigirPrestacaoContas() throws Exception {
        final PrestacaoContasViagemDao dao = new PrestacaoContasViagemDao();
        dao.setTransactionControler(this.getTransacao());
        final Integer idSolicitacao = this.getSolicitacaoServicoDto().getIdSolicitacaoServico();
        final List<PrestacaoContasViagemDTO> listaItens = (List<PrestacaoContasViagemDTO>) dao.findBySituacao(idSolicitacao,
                PrestacaoContasViagemDTO.NAO_APROVADA);
        final boolean isOk = listaItens != null && listaItens.size() > 0;
        if (isOk) {
            final PrestacaoContasViagemDTO prestacaoDto = listaItens.get(0);
            prestacaoDto.setSituacao(PrestacaoContasViagemDTO.AGUARDANDO_CORRECAO);
            dao.update(prestacaoDto);
        }
        return isOk;
    }

    @SuppressWarnings({"unchecked"})
    public boolean isTarefaConferencia() throws Exception {
        final PrestacaoContasViagemDao dao = new PrestacaoContasViagemDao();
        dao.setTransactionControler(this.getTransacao());
        final Integer idSolicitacao = this.getSolicitacaoServicoDto().getIdSolicitacaoServico();
        final List<PrestacaoContasViagemDTO> listaItens = (List<PrestacaoContasViagemDTO>) dao.findBySituacaoAndNull(idSolicitacao,
                PrestacaoContasViagemDTO.AGUARDANDO_CONFERENCIA);
        final boolean isOk = listaItens != null && listaItens.size() > 0;
        if (isOk) {
            final PrestacaoContasViagemDTO dto = listaItens.get(0);
            dto.setSituacao(PrestacaoContasViagemDTO.EM_CONFERENCIA);
            dao.update(dto);
            this.setaInicioTarefa();
        }
        return isOk;
    }

    public boolean isCancelamentoRequisicaoViagem() throws Exception {
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        return requisicaoViagemDto != null && requisicaoViagemDto.getCancelarRequisicao() != null
                && requisicaoViagemDto.getCancelarRequisicao().equalsIgnoreCase("S");
    }

    public boolean isCancelada() throws Exception {
        final RequisicaoViagemDTO requisicaoViagemDTO = this.recuperaRequisicaoViagem();

        if (requisicaoViagemDTO != null && requisicaoViagemDTO.getEstado().equalsIgnoreCase(Enumerados.SituacaoSolicitacaoServico.Cancelada.name())) {
            return true;
        }

        return false;
    }

    public boolean isHouveRemarcacaoViagem() throws Exception {
        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final boolean isOK = integranteViagemDao.isHouveRemarcacaoViagem(requisicaoViagemDto.getIdSolicitacaoServico());

        if (isOK) {
            return true;
        } return false;
    }

    @SuppressWarnings("rawtypes")
    public boolean verificaEtapaDoFluxo(final Integer idSolicitacaoServico, final String template) throws Exception {
        final RequisicaoViagemDAO requisicaoViagemDAO = new RequisicaoViagemDAO();

        final List listaRequisicaoViagem = requisicaoViagemDAO.retornaRequisicaoByTemplateAndIdsolicitacao(idSolicitacaoServico, template);
        if (listaRequisicaoViagem != null && listaRequisicaoViagem.size() > 0) {
            return true;
        } return false;
    }

    public boolean isEstadoPrestacaoContas() throws Exception {
        final PrestacaoContasViagemDao dao = new PrestacaoContasViagemDao();
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final boolean isOk = dao.isEstadoPrestacaoContas(requisicaoViagemDto);
        if (isOk) {
            this.setaInicioTarefa();
        }
        return isOk;
    }

    public boolean isEstadoAutorizacao() throws Exception {
        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        integranteViagemDao.setTransactionControler(this.getTransacao());
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final Collection<IntegranteViagemDTO> colIntegrantes = integranteViagemDao.recuperaIntegrantesViagemByIdSolicitacaoEstado(
                requisicaoViagemDto.getIdSolicitacaoServico(), RequisicaoViagemDTO.AGUARDANDO_APROVACAO);

        if (colIntegrantes != null && colIntegrantes.size() > 0) {
            for (final IntegranteViagemDTO integranteViagemDTO : colIntegrantes) {
                integranteViagemDTO.setEstado(RequisicaoViagemDTO.EM_AUTORIZACAO);
                integranteViagemDao.update(integranteViagemDTO);
            }
            return true;
        } return false;
    }

    public boolean isCotacaoVencida() throws Exception {
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        integranteViagemDao.setTransactionControler(this.getTransacao());
        boolean isOk = false;

        isOk = this.validaPrazoItens();

        if (isOk) {
            this.alteraEstadoRequisicaoViagem(requisicaoViagemDto, RequisicaoViagemDTO.EM_PLANEJAMENTO);
            final Collection<IntegranteViagemDTO> colIntegrantesAux = integranteViagemDao.recuperaIntegrantesViagemByIdSolicitacaoEstado(
                    requisicaoViagemDto.getIdSolicitacaoServico(), RequisicaoViagemDTO.EM_AUTORIZACAO);

            if (colIntegrantesAux != null && colIntegrantesAux.size() > 0) {
                for (final IntegranteViagemDTO integranteViagemDTO : colIntegrantesAux) {
                    integranteViagemDTO.setEstado(RequisicaoViagemDTO.AGUARDANDO_PLANEJAMENTO);
                    integranteViagemDao.updateNotNull(integranteViagemDTO);
                }
            }

            if (requisicaoViagemDto.getIdItemTrabalho() != null) {
                final String motivo = "Data da contação forado prazo de validade.";
                final TarefaFluxoDTO tarefaDto = this.recuperaTarefa(requisicaoViagemDto.getIdItemTrabalho());
                this.cancelaTarefa(null, this.getSolicitacaoServicoDto(), tarefaDto, motivo);
            }
        }

        return isOk;
    }

    public boolean requisicaoViagemFinalizada() throws Exception {
        final IntegranteViagemDao dao = new IntegranteViagemDao();

        final Collection<IntegranteViagemDTO> colIntegrantes = dao.findAllByIdSolicitacao(this.getSolicitacaoServicoDto().getIdSolicitacaoServico());
        if (colIntegrantes != null && !colIntegrantes.isEmpty()) {
            for (final IntegranteViagemDTO dto : colIntegrantes) {
                if (!dto.getEstado().equalsIgnoreCase(RequisicaoViagemDTO.FINALIZADA)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void alteraEstadoRequisicaoViagem(final RequisicaoViagemDTO requisicaoViagemDto, final String estado) throws Exception {
        final RequisicaoViagemDAO reqViagemDao = new RequisicaoViagemDAO();
        this.setTransacaoDao(reqViagemDao);
        requisicaoViagemDto.setEstado(estado);
        requisicaoViagemDto.setTarefaIniciada("N");
        reqViagemDao.updateNotNull(requisicaoViagemDto);
    }

    public void setaInicioTarefa() throws Exception {
        final RequisicaoViagemDAO dao = new RequisicaoViagemDAO();
        this.setTransacaoDao(dao);
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        requisicaoViagemDto.setTarefaIniciada("S");
        dao.update(requisicaoViagemDto);
    }

    public void setaFimTarefa() throws Exception {
        final RequisicaoViagemDAO dao = new RequisicaoViagemDAO();
        this.setTransacaoDao(dao);
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        requisicaoViagemDto.setTarefaIniciada("N");
        dao.update(requisicaoViagemDto);
    }

    public StringBuilder recuperaLoginResponsaveisAdiantamento() throws Exception {
        final StringBuilder result = new StringBuilder();

        final Integer idGrupo = Integer.parseInt(ParametroUtil.getValorParametroCitSmartHashMap(
                ParametroSistema.ID_GRUPO_PADRAO_RESPONSAVEL_ADIANTAMENTO_VIAGEM, this.getSolicitacaoServicoDto().getIdGrupoAtual().toString()));
        final EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);
        final Collection<EmpregadoDTO> colEmpregado = empregadoService.listEmpregadosByIdGrupo(idGrupo);

        if (colEmpregado != null) {
            int i = 0;
            final UsuarioDao usuarioDao = new UsuarioDao();
            for (final EmpregadoDTO empregadoDto : colEmpregado) {
                final UsuarioDTO usuarioDto = usuarioDao.restoreAtivoByIdEmpregado(empregadoDto.getIdEmpregado());
                if (usuarioDto != null) {
                    if (i > 0) {
                        result.append(";");
                    }
                    result.append(usuarioDto.getLogin());
                    i++;
                }
            }
        }
        if (result.length() == 0) {
            throw new LogicException("Não foi encontrado nenhum responsavel para o Adiantamento");
        }

        return result;
    }

    public StringBuilder recuperaLoginResponsaveisConferencia() throws Exception {
        final StringBuilder result = new StringBuilder();

        final Integer idGrupo = Integer.parseInt(ParametroUtil.getValorParametroCitSmartHashMap(
                ParametroSistema.ID_GRUPO_PADRAO_RESPONSAVEL_CONFERENCIA_VIAGEM, this.getSolicitacaoServicoDto().getIdGrupoAtual().toString()));

        final EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);
        final Collection<EmpregadoDTO> colEmpregado = empregadoService.listEmpregadosByIdGrupo(idGrupo);

        if (colEmpregado != null) {
            int i = 0;
            final UsuarioDao usuarioDao = new UsuarioDao();
            for (final EmpregadoDTO empregadoDto : colEmpregado) {
                final UsuarioDTO usuarioDto = usuarioDao.restoreAtivoByIdEmpregado(empregadoDto.getIdEmpregado());
                if (usuarioDto != null) {
                    if (i > 0) {
                        result.append(";");
                    }
                    result.append(usuarioDto.getLogin());
                    i++;
                }
            }
        }
        if (result.length() == 0) {
            throw new LogicException("Não foi encontrado nenhum responsavel para a Conferência");
        }

        return result;
    }

    public StringBuilder recuperaLoginResponsaveisCotacao() throws Exception {
        final StringBuilder result = new StringBuilder();

        final Integer idGrupo = Integer.parseInt(ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.ID_GRUPO_PADRAO_RESPONSAVEL_COTACAO_VIAGEM,
                this.getSolicitacaoServicoDto().getIdGrupoAtual().toString()));
        final EmpregadoService empregadoService = (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);
        final Collection<EmpregadoDTO> colEmpregado = empregadoService.listEmpregadosByIdGrupo(idGrupo);

        if (colEmpregado != null) {
            int i = 0;
            final UsuarioDao usuarioDao = new UsuarioDao();
            for (final EmpregadoDTO empregadoDto : colEmpregado) {
                final UsuarioDTO usuarioDto = usuarioDao.restoreAtivoByIdEmpregado(empregadoDto.getIdEmpregado());
                if (usuarioDto != null) {
                    if (i > 0) {
                        result.append(";");
                    }
                    result.append(usuarioDto.getLogin());
                    i++;
                }
            }
        }
        if (result.length() == 0) {
            throw new LogicException("Não foi encontrado nenhum responsavel pela Cotação");
        }

        return result;
    }

    public StringBuilder recuperaLoginIntegrante() throws Exception {

        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final StringBuilder result = new StringBuilder();
        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        final Collection<IntegranteViagemDTO> colIntegrantes = integranteViagemDao.findAllByIdSolicitacao(requisicaoViagemDto.getIdSolicitacaoServico());
        UsuarioDTO usuarioDto;

        try {

            if (colIntegrantes != null) {
                int i = 0;

                for (final IntegranteViagemDTO integrante : colIntegrantes) {

                    final UsuarioDao usuarioDao = new UsuarioDao();

                    if (integrante.getIdRespPrestacaoContas() != null) {
                        usuarioDto = usuarioDao.restoreAtivoByIdEmpregado(integrante.getIdRespPrestacaoContas());
                    } else {
                        usuarioDto = usuarioDao.restoreAtivoByIdEmpregado(integrante.getIdEmpregado());
                    }
                    if (usuarioDto != null) {
                        if (i > 0) {
                            result.append(";");
                        }
                        result.append(usuarioDto.getLogin());
                        i++;
                    }
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        if (result.length() == 0) {
            throw new LogicException("Não foi encontrado nenhum Integrante da requisição");
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public StringBuilder recuperaLoginIntegranteCorrecao() throws Exception {

        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final StringBuilder result = new StringBuilder();
        final PrestacaoContasViagemDao dao = new PrestacaoContasViagemDao();
        PrestacaoContasViagemDTO prestacaoContasDto = new PrestacaoContasViagemDTO();
        final List<PrestacaoContasViagemDTO> lista = (List<PrestacaoContasViagemDTO>) dao.findBySituacao(requisicaoViagemDto.getIdSolicitacaoServico(),
                PrestacaoContasViagemDTO.AGUARDANDO_CORRECAO);

        if (lista == null) {
            return this.recuperaLoginIntegrante();
        } else {
            try {

                prestacaoContasDto = lista.get(0);

                Integer idEmpregado = prestacaoContasDto.getIdEmpregado();

                final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
                final IntegranteViagemDTO integranteViagemDto = integranteViagemDao.recuperaIntegrante(requisicaoViagemDto.getIdSolicitacaoServico(),
                        idEmpregado);

                if (integranteViagemDto != null) {
                    if (integranteViagemDto.getIdRespPrestacaoContas() != null) {
                        idEmpregado = integranteViagemDto.getIdRespPrestacaoContas();
                    }
                }

                final UsuarioDao usuarioDao = new UsuarioDao();
                final UsuarioDTO usuarioDto = usuarioDao.restoreAtivoByIdEmpregado(prestacaoContasDto.getIdEmpregado());
                if (usuarioDto != null) {
                    result.append(usuarioDto.getLogin());
                    result.append(";");
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
            if (result.length() == 0) {
                throw new LogicException("Não foi encontrado nenhum Integrante da requisição");
            }
            return result;
        }
    }

    @SuppressWarnings("unchecked")
    public StringBuilder recuperaLoginIntegranteCorrecao(final Tarefa tarefa) throws Exception {
        final StringBuilder result = new StringBuilder();

        final PrestacaoContasViagemDao dao = new PrestacaoContasViagemDao();
        final Integer idSolicitacaoServico = this.getSolicitacaoServicoDto().getIdSolicitacaoServico();
        this.setTransacaoDao(dao);
        final List<PrestacaoContasViagemDTO> listaItens = (List<PrestacaoContasViagemDTO>) dao.findBySituacao(idSolicitacaoServico,
                PrestacaoContasViagemDTO.AGUARDANDO_CORRECAO);
        if (listaItens != null) {

            final PrestacaoContasViagemDTO prestacaoContas = listaItens.get(0);
            final UsuarioDao usuarioDao = new UsuarioDao();

            Integer idEmpregado = prestacaoContas.getIdEmpregado();

            final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
            final IntegranteViagemDTO integranteViagemDto = integranteViagemDao.recuperaIntegrante(prestacaoContas.getIdSolicitacaoServico(), idEmpregado);

            if (integranteViagemDto != null && integranteViagemDto.getIdRespPrestacaoContas() != null) {
                idEmpregado = integranteViagemDto.getIdRespPrestacaoContas();
            }

            final UsuarioDTO usuarioDto = usuarioDao.restoreAtivoByIdEmpregado(idEmpregado);
            if (usuarioDto != null) {
                result.append(usuarioDto.getLogin());
                result.append(";");
            }
        }

        if (result.length() == 0) {
            throw new LogicException("Não foi encontrado nenhum Integrante da requisição");
        }

        return result;
    }

    public void enviaEmailNaoAprovado(final Integer idModeloEmail, final RequisicaoViagemDTO requisicaoViagemDto,
            final PrestacaoContasViagemDTO prestacaoContasViagemDto, final TransactionControler tc) throws Exception {
        if (idModeloEmail == null) {
            return;
        }

        if (prestacaoContasViagemDto == null) {
            return;
        }

        EmpregadoDTO empregadoDto = new EmpregadoDTO();
        final EmpregadoDao empregadoDao = new EmpregadoDao();
        empregadoDto.setIdEmpregado(prestacaoContasViagemDto.getIdEmpregado());
        empregadoDto = (EmpregadoDTO) empregadoDao.restore(empregadoDto);

        if (empregadoDto == null) {
            return;
        }

        final String remetente = this.getRemetenteEmail();

        final SolicitacaoServicoDTO solicitacaoAuxDto = new SolicitacaoServicoServiceEjb().restoreAll(requisicaoViagemDto.getIdSolicitacaoServico(), tc);
        if (solicitacaoAuxDto != null) {
            solicitacaoAuxDto.setNomeTarefa(requisicaoViagemDto.getNomeTarefa());
        }

        /* Decodifica a mensagem a ser enviada */
        if (solicitacaoAuxDto != null) {
            solicitacaoAuxDto.setDescricao(StringEscapeUtils.unescapeEcmaScript(solicitacaoAuxDto.getDescricao()));
            solicitacaoAuxDto.setResposta(StringEscapeUtils.unescapeEcmaScript(solicitacaoAuxDto.getResposta()));
            solicitacaoAuxDto.setComplementoJustificativa(prestacaoContasViagemDto.getComplemJustificativaAutorizacao());
            solicitacaoAuxDto.setNomecontato(empregadoDto.getNome());
        }

        final MensagemEmail mensagem = new MensagemEmail(idModeloEmail, new BaseEntity[] {solicitacaoAuxDto});
        try {
            mensagem.envia(empregadoDto.getEmail(), null, remetente);
        } catch (final Exception e) {}
    }

    private void atribuiUsuarioCorrecao(final PrestacaoContasViagemDTO prestacaoContasViagemDto, final Tarefa tarefa) throws Exception {

        final AtribuicaoFluxoDTO atribuicaoFluxoDto = new AtribuicaoFluxoDTO();
        final AtribuicaoFluxoDao atribuicaoFluxoDao = new AtribuicaoFluxoDao();
        atribuicaoFluxoDao.setTransactionControler(this.getTransacao());
        final UsuarioDao usuarioDao = new UsuarioDao();

        Integer idEmpregado = prestacaoContasViagemDto.getIdEmpregado();

        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        final IntegranteViagemDTO integranteViagemDto = integranteViagemDao.recuperaIntegrante(prestacaoContasViagemDto.getIdSolicitacaoServico(), idEmpregado);

        if (integranteViagemDto != null && integranteViagemDto.getIdRespPrestacaoContas() != null) {
            idEmpregado = integranteViagemDto.getIdRespPrestacaoContas();
        }

        final UsuarioDTO usuarioDto = usuarioDao.restoreByIdEmpregado(idEmpregado);

        atribuicaoFluxoDto.setIdItemTrabalho(tarefa.getIdItemTrabalho());
        atribuicaoFluxoDto.setIdUsuario(usuarioDto.getIdUsuario());
        atribuicaoFluxoDto.setTipo("Automatica");
        atribuicaoFluxoDto.setDataHora(UtilDatas.getDataHoraAtual());

        final Collection<AtribuicaoFluxoDTO> itensCadastrados = atribuicaoFluxoDao.findByIdItemTrabalhoAndIdUsuarioAndTipo(
                atribuicaoFluxoDto.getIdItemTrabalho(), atribuicaoFluxoDto.getIdUsuario(), atribuicaoFluxoDto.getTipo());

        if (itensCadastrados == null || itensCadastrados.isEmpty()) {
            atribuicaoFluxoDao.create(atribuicaoFluxoDto);
        }

    }

    @Override
    public boolean permiteAprovacaoAlcada(final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto, final SolicitacaoServicoDTO solicitacaoServicoDto)
            throws Exception {
        final Integer idEmpregado = alcadaProcessoNegocioDto.getEmpregadoDto().getIdEmpregado();

        if (idEmpregado.intValue() == solicitacaoServicoDto.getIdSolicitante().intValue()) {
            alcadaProcessoNegocioDto.setComplementoRejeicao("Aprovador não pode ser o solicitante");
            return false;
        }

        final String idGrupoViagem = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.ID_GRUPO_PADRAO_REQ_VIAGEM_EXECUCAO, null);
        if (idGrupoViagem == null || idGrupoViagem.trim().equals("")) {
            throw new Exception("Grupo padrão de requisição de produtos não parametrizado");
        }

        if (alcadaProcessoNegocioDto.getMapGruposEmpregado().get(idGrupoViagem.trim()) != null) {
            alcadaProcessoNegocioDto.setComplementoRejeicao("Aprovador não pode pertencer ao grupo responsável por viagens");
            return false;
        }

        final Collection<IntegranteViagemDTO> listaIntegratesViagem = new IntegranteViagemDao().findAllByIdSolicitacao(solicitacaoServicoDto
                .getIdSolicitacaoServico());
        if (listaIntegratesViagem != null) {
            for (final IntegranteViagemDTO integranteViagemDto : listaIntegratesViagem) {
                if (integranteViagemDto.getIdEmpregado().intValue() == idEmpregado.intValue()) {
                    alcadaProcessoNegocioDto.setComplementoRejeicao("Aprovador não pode ser um dos integrantes da viagem");
                    return false;
                }
            }
        }
        return true;
    }

    public String recuperaLoginByIdEmpregado(final Integer idEmpregado) throws Exception {
        final UsuarioDao usuarioDao = new UsuarioDao();
        return usuarioDao.restoreAtivoByIdEmpregado(idEmpregado).getLogin();
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

    }

    @Override
    public void calculaValorAprovadoAnual(final CentroResultadoDTO centroResultadoDto, final int anoRef, final TransactionControler tc) throws Exception {
        valorAnualAtendCliente = 0.0;
        valorAnualUsoInterno = 0.0;
        final RequisicaoViagemDAO requisicaoDao = new RequisicaoViagemDAO();
        if (tc != null) {
            requisicaoDao.setTransactionControler(tc);
        }
        final Collection<RequisicaoViagemDTO> colRequisicoes = requisicaoDao.findByIdCentroCusto(centroResultadoDto.getIdCentroResultado());
        if (colRequisicoes != null) {
            final SolicitacaoServicoDao solicitacaoServicoDao = new SolicitacaoServicoDao();
            if (tc != null) {
                solicitacaoServicoDao.setTransactionControler(tc);
            }
            for (final RequisicaoViagemDTO requisicaoViagemDto : colRequisicoes) {
                SolicitacaoServicoDTO solicitacaoServicoDto = new SolicitacaoServicoDTO();
                solicitacaoServicoDto.setIdSolicitacaoServico(requisicaoViagemDto.getIdRequisicaoViagem());
                solicitacaoServicoDto = (SolicitacaoServicoDTO) solicitacaoServicoDao.restore(solicitacaoServicoDto);
                final Date dataAux = new Date(requisicaoViagemDto.getDataHoraSolicitacao().getTime());
                final int ano = UtilDatas.getYear(dataAux);
                if (ano != anoRef) {
                    continue;
                }
                valorAnualUsoInterno += this.calculaValorAprovado(requisicaoViagemDto, tc);
            }
        }
    }

    @Override
    public void calculaValorAprovadoMensal(final CentroResultadoDTO centroResultadoDto, final int mesRef, final int anoRef, final TransactionControler tc)
            throws Exception {
        valorMensalAtendCliente = 0.0;
        valorMensalUsoInterno = 0.0;
        final RequisicaoViagemDAO requisicaoDao = new RequisicaoViagemDAO();
        if (tc != null) {
            requisicaoDao.setTransactionControler(tc);
        }
        final Collection<RequisicaoViagemDTO> colRequisicoes = requisicaoDao.findByIdCentroCusto(centroResultadoDto.getIdCentroResultado());
        if (colRequisicoes != null) {
            final SolicitacaoServicoDao solicitacaoServicoDao = new SolicitacaoServicoDao();
            if (tc != null) {
                solicitacaoServicoDao.setTransactionControler(tc);
            }
            for (final RequisicaoViagemDTO requisicaoViagemDto : colRequisicoes) {
                /*
                 * if (getSolicitacaoServicoDto() != null && getSolicitacaoServicoDto().getIdSolicitacaoServico() != null &&
                 * getSolicitacaoServicoDto().getIdSolicitacaoServico().intValue() == requisicaoViagemDto.getIdSolicitacaoServico().intValue())
                 * continue;
                 */
                SolicitacaoServicoDTO solicitacaoServicoDto = new SolicitacaoServicoDTO();
                solicitacaoServicoDto.setIdSolicitacaoServico(requisicaoViagemDto.getIdRequisicaoViagem());
                solicitacaoServicoDto = (SolicitacaoServicoDTO) solicitacaoServicoDao.restore(solicitacaoServicoDto);
                final Date dataAux = new Date(requisicaoViagemDto.getDataHoraSolicitacao().getTime());
                final int mes = UtilDatas.getMonth(dataAux);
                final int ano = UtilDatas.getYear(dataAux);
                if (ano != anoRef || mes != mesRef) {
                    continue;
                }
                valorMensalUsoInterno += this.calculaValorAprovado(requisicaoViagemDto, tc);
            }
        }
    }

    @Override
    public double calculaValorAprovado(final SolicitacaoServicoDTO solicitacaoServicoDto, final TransactionControler tc) throws Exception {
        final DespesaViagemDAO despesaViagemDAO = new DespesaViagemDAO();

        if (tc != null) {
            despesaViagemDAO.setTransactionControler(tc);
        }

        return despesaViagemDAO.buscaValorTotalViagem(solicitacaoServicoDto.getIdSolicitacaoServico());
    }

    /**
     * Valida se a requisição é uma requisição de viagem remarcado, caso seja,
     * valida se todos os integrantes da viagem já foram remarcadas, caso sim
     * seta que a requisição de viagem não é mais rearcada
     *
     * @throws Exception
     *
     */
    public void verificarRequisicaoRemarcada() throws Exception {
        final RequisicaoViagemDAO dao = new RequisicaoViagemDAO();
        this.setTransacaoDao(dao);
        final RequisicaoViagemDTO requisicaoDto = this.recuperaRequisicaoViagem();

        if (requisicaoDto.getRemarcacao().equals("S")) {

            final RequisicaoViagemService requisicaoViagemService = (RequisicaoViagemService) ServiceLocator.getInstance().getService(
                    RequisicaoViagemService.class, null);
            final RequisicaoViagemDTO requisicao = requisicaoViagemService.recuperaRequisicaoPelaSolicitacao(requisicaoDto.getIdSolicitacaoServico());

            final IntegranteViagemService integranteViagemService = (IntegranteViagemService) ServiceLocator.getInstance().getService(
                    IntegranteViagemService.class, null);
            final Collection<IntegranteViagemDTO> integrantes = integranteViagemService.findAllRemarcacaoByIdSolicitacao(requisicaoDto
                    .getIdSolicitacaoServico());

            if (integrantes == null || integrantes.size() < 1) {
                requisicao.setRemarcacao("N");
                requisicao.setTarefaIniciada("N");
                dao.update(requisicao);
            }

        }
    }

    /**
     * Verifica se a viagem ja aconteceu para avançar o fluxo para prestação de contas
     *
     * @throws Exception
     *
     */
    public boolean viagemOk() throws Exception {
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final IntegranteViagemService integranteViagemService = (IntegranteViagemService) ServiceLocator.getInstance().getService(
                IntegranteViagemService.class, null);
        // IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        // integranteViagemDao.setTransactionControler(getTransacao());
        final RoteiroViagemService roteiroViagemService = (RoteiroViagemService) ServiceLocator.getInstance().getService(RoteiroViagemService.class, null);
        RoteiroViagemDTO roteiroViagemDTO = new RoteiroViagemDTO();
        final Collection<IntegranteViagemDTO> integrantes = integranteViagemService.recuperaIntegrantesViagemByIdSolicitacaoEstado(
                requisicaoViagemDto.getIdSolicitacaoServico(), RequisicaoViagemDTO.AGUARDANDO_PRESTACAOCONTAS);

        final Date dataAtual = UtilDatas.getDataAtual();
        requisicaoViagemDto.getDataFimViagem();

        if (integrantes != null && integrantes.size() > 0) {
            for (IntegranteViagemDTO integranteViagemDTO : integrantes) {
                roteiroViagemDTO = roteiroViagemService.findByIdIntegrante(integranteViagemDTO.getIdIntegranteViagem());

                if (roteiroViagemDTO.getVolta().compareTo(dataAtual) < 0 && integranteViagemDTO.getEstado() != null
                        && integranteViagemDTO.getEstado().equalsIgnoreCase(RequisicaoViagemDTO.AGUARDANDO_PRESTACAOCONTAS)) {
                    integranteViagemDTO = (IntegranteViagemDTO) integranteViagemService.restore(integranteViagemDTO);
                    integranteViagemDTO.setEstado(RequisicaoViagemDTO.EM_PRESTACAOCONTAS);
                    integranteViagemDTO.setEmPrestacaoContas("S");
                    integranteViagemDTO.setRemarcacao("N");
                    integranteViagemDTO.setIdTarefa(null);
                    integranteViagemService.update(integranteViagemDTO);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * cancela intancia se itens com contação vencida
     *
     * @throws Exception
     *
     */
    public void cancelaAutorizacao() throws Exception {
        final SolicitacaoServicoDTO solicitacaoDto = this.getSolicitacaoServicoDto();

        final Collection<TarefaFluxoDTO> colTarefas = new TarefaFluxoDao().findDisponiveisByIdTarefaEstado(solicitacaoDto.getIdSolicitacaoServico(),
                "Autorizar requisição");
        if (colTarefas != null && !colTarefas.isEmpty()) {
            final String motivo = "Requisição com itens vencidos!";
            for (final TarefaFluxoDTO tarefaDto : colTarefas) {
                this.cancelaTarefa(null, solicitacaoDto, tarefaDto, motivo);
            }
        }
    }

    /**
     * Valida se a requisição foi criada para executar planejamento
     *
     */
    public boolean isRequisicaoCriada() throws Exception {
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final RequisicaoViagemDAO requisicaoViagemDAO = new RequisicaoViagemDAO();
        requisicaoViagemDAO.setTransactionControler(this.getTransacao());

        if (requisicaoViagemDto != null && requisicaoViagemDto.getEstado().equalsIgnoreCase("Aguardando Planejamento")
                && requisicaoViagemDto.getTarefaIniciada().equalsIgnoreCase("") || requisicaoViagemDto.getTarefaIniciada() == null) {
            requisicaoViagemDto.setEstado(RequisicaoViagemDTO.EM_PLANEJAMENTO);
            requisicaoViagemDAO.updateNotNull(requisicaoViagemDto);
            return true;
        } return false;
    }

    /**
     * Valida se viagem foi remarcada
     *
     * @return
     * @throws Exception
     */
    public boolean isRemarcada() throws Exception {
        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        integranteViagemDao.setTransactionControler(this.getTransacao());
        final IntegranteViagemService integranteViagemService = (IntegranteViagemService) ServiceLocator.getInstance().getService(
                IntegranteViagemService.class, null);
        Collection<IntegranteViagemDTO> colIntegrantes = new ArrayList<IntegranteViagemDTO>();
        final SolicitacaoServicoDTO solicitacaoDto = this.getSolicitacaoServicoDto();
        Collection<IntegranteViagemDTO> colIntegrantesAux = new ArrayList<IntegranteViagemDTO>();
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final RequisicaoViagemDAO requisicaoViagemDAO = new RequisicaoViagemDAO();
        requisicaoViagemDAO.setTransactionControler(this.getTransacao());
        boolean todosRemarcados = true;
        TarefaFluxoDTO tarefaDto = new TarefaFluxoDTO();

        colIntegrantes = integranteViagemService.recuperaIntegrantesViagemByIdSolicitacaoEstado(requisicaoViagemDto.getIdSolicitacaoServico(),
                RequisicaoViagemDTO.REMARCADO);
        if (colIntegrantes != null && !colIntegrantes.isEmpty()) {
            for (final IntegranteViagemDTO dto : colIntegrantes) {
                dto.setEstado(RequisicaoViagemDTO.AGUARDANDO_PLANEJAMENTO);
                integranteViagemDao.update(dto);
            }

            requisicaoViagemDto.setEstado(RequisicaoViagemDTO.EM_PLANEJAMENTO);
            requisicaoViagemDAO.updateNotNull(requisicaoViagemDto);

            colIntegrantesAux = integranteViagemService.recuperaIntegrantesViagemByIdSolicitacao(requisicaoViagemDto.getIdSolicitacaoServico());
            if (colIntegrantesAux != null && !colIntegrantesAux.isEmpty()) {
                for (final IntegranteViagemDTO dto : colIntegrantesAux) {
                    if (dto.getEstado() != null && dto.getEstado().equalsIgnoreCase(RequisicaoViagemDTO.AGUARDANDO_ADIANTAMENTO)) {
                        todosRemarcados = false;
                    }
                }
            }

            if (todosRemarcados) {
                final String motivo = "Todos integrantes foram remarcados";
                if (colIntegrantesAux != null && !colIntegrantesAux.isEmpty()) {
                    for (final IntegranteViagemDTO dto : colIntegrantesAux) {
                        if (dto.getIdTarefa() != null) {
                            tarefaDto = this.recuperaTarefa(dto.getIdTarefa());
                            if (tarefaDto != null && !tarefaDto.getSituacao().equalsIgnoreCase("Executado")) {
                                this.cancelaTarefa(null, solicitacaoDto, tarefaDto, motivo);
                                break;
                            }
                        }
                    }
                }
            }

            return true;
        } return false;
    }

    @SuppressWarnings({"unchecked"})
    public void setaTarefaConferencia(final Tarefa tarefa) throws Exception {
        final PrestacaoContasViagemDao dao = new PrestacaoContasViagemDao();
        this.setTransacaoDao(dao);
        final List<PrestacaoContasViagemDTO> listaItens = (List<PrestacaoContasViagemDTO>) dao.findBySolicitacaoAndTaferaConferencia(this
                .getSolicitacaoServicoDto().getIdSolicitacaoServico());
        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        integranteViagemDao.setTransactionControler(this.getTransacao());
        final Integer idSolicitacao = this.getSolicitacaoServicoDto().getIdSolicitacaoServico();
        final Collection<IntegranteViagemDTO> colIntegrantes = integranteViagemDao.recuperaIntegrantesViagemByIdSolicitacaoEstado(idSolicitacao,
                RequisicaoViagemDTO.AGUARDANDO_CONFERENCIA);
        final Collection<IntegranteViagemDTO> colIntegrantesAux = integranteViagemDao.recuperaIntegrantesViagemByIdSolicitacaoEstado(idSolicitacao,
                RequisicaoViagemDTO.EM_CONFERENCIA);

        if (listaItens != null) {
            final PrestacaoContasViagemDTO prestacaoContas = listaItens.get(0);
            prestacaoContas.setIdItemTrabalho(tarefa.getIdItemTrabalho());
            prestacaoContas.setSituacao(PrestacaoContasViagemDTO.EM_CONFERENCIA);
            dao.update(prestacaoContas);
            this.setaInicioTarefa();
        }

        if (colIntegrantes != null && colIntegrantes.size() > 0) {
            for (final IntegranteViagemDTO integranteViagemDTO : colIntegrantes) {
                if (integranteViagemDTO.getIdTarefa() == null || integranteViagemDTO.getIdTarefa().equals("")) {
                    integranteViagemDTO.setIdTarefa(tarefa.getIdItemTrabalho());
                    integranteViagemDTO.setEstado(RequisicaoViagemDTO.EM_CONFERENCIA);
                    integranteViagemDao.update(integranteViagemDTO);
                    break;
                }
            }
        }

        if (colIntegrantesAux != null && colIntegrantesAux.size() > 0) {
            for (final IntegranteViagemDTO integranteViagemDTO : colIntegrantesAux) {
                if (integranteViagemDTO.getIdTarefa() == null || integranteViagemDTO.getIdTarefa().equals("")) {
                    integranteViagemDTO.setIdTarefa(tarefa.getIdItemTrabalho());
                    integranteViagemDTO.setEstado(RequisicaoViagemDTO.EM_CONFERENCIA);
                    integranteViagemDao.update(integranteViagemDTO);
                    break;
                }
            }
        }
    }

    public boolean alteraEstadoIntegrantes() throws Exception {
        RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();
        final RequisicaoViagemDAO requisicaoViagemDAO = new RequisicaoViagemDAO();
        requisicaoViagemDAO.setTransactionControler(this.getTransacao());
        final IntegranteViagemDao integranteViagemDao = new IntegranteViagemDao();
        integranteViagemDao.setTransactionControler(this.getTransacao());
        final Collection<IntegranteViagemDTO> colIntegrantes = integranteViagemDao.recuperaIntegrantesViagemByIdSolicitacaoEstado(
                requisicaoViagemDto.getIdSolicitacaoServico(), RequisicaoViagemDTO.AGUARDANDO_PLANEJAMENTO);

        if (colIntegrantes != null && colIntegrantes.size() > 0) {
            for (final IntegranteViagemDTO integranteViagemDTO : colIntegrantes) {
                integranteViagemDTO.setEstado(RequisicaoViagemDTO.AGUARDANDO_APROVACAO);
                integranteViagemDao.updateNotNull(integranteViagemDTO);
            }
        }
        requisicaoViagemDto = (RequisicaoViagemDTO) requisicaoViagemDAO.restore(requisicaoViagemDto);
        requisicaoViagemDto.setEstado(RequisicaoViagemDTO.AGUARDANDO_APROVACAO);
        requisicaoViagemDAO.updateNotNull(requisicaoViagemDto);

        return true;
    }

    public void cancelaTarefasDuplicadas(final Tarefa tarefa) throws Exception {
        final SolicitacaoServicoDTO solicitacaoDto = this.getSolicitacaoServicoDto();

        final Collection<TarefaFluxoDTO> colTarefas = new TarefaFluxoDao().findDisponiveisByIdTarefaEstado(solicitacaoDto.getIdSolicitacaoServico(), tarefa
                .getElementoFluxoDto().getNome());
        final String motivo = "cancela tarefa duplicada na mesma etapa";
        if (colTarefas != null && !colTarefas.isEmpty()) {
            for (TarefaFluxoDTO tarefaDto : colTarefas) {
                if (tarefaDto != null && !tarefaDto.getIdItemTrabalho().equals(tarefa.getIdItemTrabalho())) {
                    tarefaDto = this.recuperaTarefa(tarefaDto.getIdItemTrabalho());
                    this.cancelaTarefa(null, solicitacaoDto, tarefaDto, motivo);
                }
            }
        }
    }

    /**
     * Valida se alguma prestação de contas aguarda conferência
     *
     * @return
     * @throws Exception
     */
    public boolean isConferencia() throws Exception {
        final IntegranteViagemService integranteViagemService = (IntegranteViagemService) ServiceLocator.getInstance().getService(
                IntegranteViagemService.class, null);
        Collection<IntegranteViagemDTO> colIntegrantes = new ArrayList<IntegranteViagemDTO>();
        final RequisicaoViagemDTO requisicaoViagemDto = this.recuperaRequisicaoViagem();

        colIntegrantes = integranteViagemService.recuperaIntegrantesViagemByIdSolicitacaoEstado(requisicaoViagemDto.getIdSolicitacaoServico(),
                RequisicaoViagemDTO.AGUARDANDO_CONFERENCIA);
        if (colIntegrantes != null && !colIntegrantes.isEmpty()) {
            return true;
        } return false;
    }

}
