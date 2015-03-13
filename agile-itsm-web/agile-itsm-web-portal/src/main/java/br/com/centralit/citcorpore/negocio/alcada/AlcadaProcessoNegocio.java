package br.com.centralit.citcorpore.negocio.alcada;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import br.com.centralit.bpm.dto.FluxoDTO;
import br.com.centralit.bpm.dto.TipoFluxoDTO;
import br.com.centralit.bpm.integracao.TipoFluxoDao;
import br.com.centralit.citcorpore.bean.AlcadaProcessoNegocioDTO;
import br.com.centralit.citcorpore.bean.CentroResultadoDTO;
import br.com.centralit.citcorpore.bean.DelegacaoCentroResultadoDTO;
import br.com.centralit.citcorpore.bean.DelegacaoCentroResultadoFluxoDTO;
import br.com.centralit.citcorpore.bean.DelegacaoCentroResultadoProcessoDTO;
import br.com.centralit.citcorpore.bean.EmpregadoDTO;
import br.com.centralit.citcorpore.bean.GrupoEmpregadoDTO;
import br.com.centralit.citcorpore.bean.GrupoNivelAutoridadeDTO;
import br.com.centralit.citcorpore.bean.LimiteAprovacaoAutoridadeDTO;
import br.com.centralit.citcorpore.bean.LimiteAprovacaoDTO;
import br.com.centralit.citcorpore.bean.LimiteAprovacaoProcessoDTO;
import br.com.centralit.citcorpore.bean.NivelAutoridadeDTO;
import br.com.centralit.citcorpore.bean.ParametroDTO;
import br.com.centralit.citcorpore.bean.ProcessoNegocioDTO;
import br.com.centralit.citcorpore.bean.ProcessoNivelAutoridadeDTO;
import br.com.centralit.citcorpore.bean.ResponsavelCentroResultadoDTO;
import br.com.centralit.citcorpore.bean.ResponsavelCentroResultadoProcessoDTO;
import br.com.centralit.citcorpore.bean.SimulacaoAlcadaDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.bean.ValorLimiteAprovacaoDTO;
import br.com.centralit.citcorpore.bpm.negocio.ExecucaoSolicitacao;
import br.com.centralit.citcorpore.integracao.CentroResultadoDao;
import br.com.centralit.citcorpore.integracao.DelegacaoCentroResultadoDao;
import br.com.centralit.citcorpore.integracao.DelegacaoCentroResultadoFluxoDao;
import br.com.centralit.citcorpore.integracao.DelegacaoCentroResultadoProcessoDao;
import br.com.centralit.citcorpore.integracao.EmpregadoDao;
import br.com.centralit.citcorpore.integracao.ExecucaoSolicitacaoDao;
import br.com.centralit.citcorpore.integracao.GrupoEmpregadoDao;
import br.com.centralit.citcorpore.integracao.GrupoNivelAutoridadeDao;
import br.com.centralit.citcorpore.integracao.LimiteAprovacaoAutoridadeDao;
import br.com.centralit.citcorpore.integracao.LimiteAprovacaoDao;
import br.com.centralit.citcorpore.integracao.LimiteAprovacaoProcessoDao;
import br.com.centralit.citcorpore.integracao.NivelAutoridadeDao;
import br.com.centralit.citcorpore.integracao.ParametroDao;
import br.com.centralit.citcorpore.integracao.ProcessoNegocioDao;
import br.com.centralit.citcorpore.integracao.ProcessoNivelAutoridadeDao;
import br.com.centralit.citcorpore.integracao.ResponsavelCentroResultadoDao;
import br.com.centralit.citcorpore.integracao.ResponsavelCentroResultadoProcessoDao;
import br.com.centralit.citcorpore.integracao.UsuarioDao;
import br.com.centralit.citcorpore.integracao.ValorLimiteAprovacaoDao;
import br.com.centralit.citcorpore.negocio.SolicitacaoServicoServiceEjb;
import br.com.centralit.citcorpore.util.Enumerados.MotivoRejeicaoAlcada;
import br.com.citframework.comparacao.ObjectSimpleComparator;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.integracao.CrudDAO;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.util.Reflexao;
import br.com.citframework.util.UtilDatas;

public class AlcadaProcessoNegocio {

    private TransactionControler transacao = null;

    protected CrudDAO atribuiTransacaoDao(final CrudDAO dao) throws Exception {
        if (transacao != null) {
            dao.setTransactionControler(transacao);
        }
        return dao;
    }

    private static String getNomeClasse() throws Exception {
        final ParametroDTO parametroDto = new ParametroDao().getValue("ALCADA", "NOME_CLASSE_ALCADA", new Integer(1));
        if (parametroDto != null && parametroDto.getValor() != null && !parametroDto.getValor().trim().equals("")) {
            return parametroDto.getValor();
        }
        return "br.com.centralit.citcorpore.negocio.alcada.AlcadaProcessoNegocio";
    }

    private HashMap<String, ProcessoNegocioDTO> mapProcessosNegocio = null;
    private HashMap<String, NivelAutoridadeDTO> mapAutoridades = null;

    public static AlcadaProcessoNegocio getInstance() throws Exception {
        return (AlcadaProcessoNegocio) Class.forName(getNomeClasse()).newInstance();
    }

    protected EmpregadoDTO recuperaEmpregado(final Integer idEmpregado) throws Exception {
        final EmpregadoDTO empregadoDto = new EmpregadoDTO();
        empregadoDto.setIdEmpregado(idEmpregado);
        return (EmpregadoDTO) this.atribuiTransacaoDao(new EmpregadoDao()).restore(empregadoDto);
    }

    protected CentroResultadoDTO recuperaCentroResultado(final Integer idCentroResultado) throws Exception {
        final CentroResultadoDTO centroResultadoDto = new CentroResultadoDTO();
        centroResultadoDto.setIdCentroResultado(idCentroResultado);
        return (CentroResultadoDTO) this.atribuiTransacaoDao(new CentroResultadoDao()).restore(centroResultadoDto);
    }

    protected ProcessoNegocioDTO recuperaProcessoNegocio(final Integer idProcessoNegocio) throws Exception {
        final ProcessoNegocioDTO processoNegocioDto = new ProcessoNegocioDTO();
        processoNegocioDto.setIdProcessoNegocio(idProcessoNegocio);
        return (ProcessoNegocioDTO) this.atribuiTransacaoDao(new ProcessoNegocioDao()).restore(processoNegocioDto);
    }

    protected FluxoDTO recuperaFluxo(final SolicitacaoServicoDTO solicitacaoServicoDto) throws Exception {
        return new SolicitacaoServicoServiceEjb().recuperaFluxo(solicitacaoServicoDto);
    }

    protected boolean isSimulacao(final SolicitacaoServicoDTO solicitacaoServicoDto) {
        return SimulacaoAlcadaDTO.class.isInstance(solicitacaoServicoDto);
    }

    public ProcessoNegocioDTO recuperaProcessoNegocio(final FluxoDTO fluxoDto) throws Exception {
        ProcessoNegocioDTO result = null;
        if (fluxoDto != null && fluxoDto.getIdProcessoNegocio() != null) {
            result = this.recuperaProcessoNegocio(fluxoDto.getIdProcessoNegocio());
        }
        if (result != null) {
            this.recuperaAutoridadesProcesso(result, (ProcessoNivelAutoridadeDao) this.atribuiTransacaoDao(new ProcessoNivelAutoridadeDao()));
        }
        fluxoDto.setProcessoNegocioDto(result);
        return result;
    }

    public ExecucaoSolicitacao recuperaExecucaoSolicitacao(final FluxoDTO fluxoDto, final SolicitacaoServicoDTO solicitacaoServicoDto) throws Exception {
        final ExecucaoSolicitacao execucaoSolicitacao = (ExecucaoSolicitacao) Class.forName(fluxoDto.getNomeClasseFluxo()).newInstance();
        execucaoSolicitacao.setTransacao(transacao);
        execucaoSolicitacao.setObjetoNegocioDto(solicitacaoServicoDto);
        final ExecucaoSolicitacaoDao execucaoSolicitacaoDao = new ExecucaoSolicitacaoDao();
        this.atribuiTransacaoDao(execucaoSolicitacaoDao);
        execucaoSolicitacao.setExecucaoSolicitacaoDto(execucaoSolicitacaoDao.findBySolicitacaoServico(solicitacaoServicoDto));
        return execucaoSolicitacao;
    }

    protected void recuperaLimitesAprovacao(final ProcessoNegocioDTO processoNegocioDto) throws Exception {
        final LimiteAprovacaoProcessoDao limiteAprovacaoProcessoDao = new LimiteAprovacaoProcessoDao();
        this.atribuiTransacaoDao(limiteAprovacaoProcessoDao);
        final Collection<LimiteAprovacaoDTO> colLimitesAprovacao = new ArrayList<>();
        // recupera os limites de aprovação associados ao processo
        final Collection<LimiteAprovacaoProcessoDTO> colLimiteAprovacaoProcesso = limiteAprovacaoProcessoDao.findByIdProcessoNegocio(processoNegocioDto
                .getIdProcessoNegocio());
        if (colLimiteAprovacaoProcesso != null) {
            final LimiteAprovacaoDao limiteAprovacaoDao = new LimiteAprovacaoDao();
            this.atribuiTransacaoDao(limiteAprovacaoDao);
            final ValorLimiteAprovacaoDao valorLimiteAprovacaoDao = new ValorLimiteAprovacaoDao();
            this.atribuiTransacaoDao(valorLimiteAprovacaoDao);
            for (final LimiteAprovacaoProcessoDTO limiteAprovacaoProcessoDto : colLimiteAprovacaoProcesso) {
                LimiteAprovacaoDTO limiteAprovacaoDto = new LimiteAprovacaoDTO();
                limiteAprovacaoDto.setIdLimiteAprovacao(limiteAprovacaoProcessoDto.getIdLimiteAprovacao());
                limiteAprovacaoDto = (LimiteAprovacaoDTO) limiteAprovacaoDao.restore(limiteAprovacaoDto);
                // recupera os valores de cada limite
                limiteAprovacaoDto.setColValores(valorLimiteAprovacaoDao.findByIdLimiteAprovacao(limiteAprovacaoProcessoDto.getIdLimiteAprovacao()));
                final Collection<LimiteAprovacaoProcessoDTO> colLimiteAprovacao = limiteAprovacaoProcessoDao.findByIdLimiteAprovacao(limiteAprovacaoProcessoDto
                        .getIdLimiteAprovacao());
                final Collection<ProcessoNegocioDTO> colProcessos = new ArrayList<>();
                colProcessos.add(processoNegocioDto);
                if (colLimiteAprovacao != null) {
                    // recupera os outros processos associados ao limite
                    for (final LimiteAprovacaoProcessoDTO limiteDto : colLimiteAprovacao) {
                        if (limiteDto.getIdProcessoNegocio().intValue() == processoNegocioDto.getIdProcessoNegocio().intValue()) {
                            continue;
                        }
                        final ProcessoNegocioDTO processoDto = this.getHashMapProcessosNegocio().get("" + limiteDto.getIdProcessoNegocio());
                        if (processoDto != null) {
                            colProcessos.add(processoDto);
                        }
                    }
                }
                limiteAprovacaoDto.setColProcessos(colProcessos);
                colLimitesAprovacao.add(limiteAprovacaoDto);
            }
        }
        processoNegocioDto.setColLimitesAprovacao(colLimitesAprovacao);
    }

    protected void associaLimiteAutoridadeEValores(final ProcessoNegocioDTO processoNegocioDto, final ExecucaoSolicitacao execucaoSolicitacao,
            final CentroResultadoDTO centroResultadoDto) throws Exception {
        if (processoNegocioDto.getColAutoridades() == null || processoNegocioDto.getColAutoridades().isEmpty()) {
            return;
        }

        // recupera os limites de aprovação do processo
        // para cada limite, recupera os outros processos associados
        this.recuperaLimitesAprovacao(processoNegocioDto);
        final Collection<LimiteAprovacaoDTO> colLimites = processoNegocioDto.getColLimitesAprovacao();
        if (colLimites == null || colLimites.isEmpty()) {
            return;
        }

        // cria hashMap com os limites
        final HashMap<String, LimiteAprovacaoDTO> mapLimites = new HashMap<>();
        for (final LimiteAprovacaoDTO limiteAprovacaoDto : colLimites) {
            mapLimites.put("" + limiteAprovacaoDto.getIdLimiteAprovacao(), limiteAprovacaoDto);
        }

        final LimiteAprovacaoAutoridadeDao limiteAprovacaoAutoridadeDao = new LimiteAprovacaoAutoridadeDao();
        this.atribuiTransacaoDao(limiteAprovacaoAutoridadeDao);
        for (final ProcessoNivelAutoridadeDTO processoNivelAutoridadeDto : processoNegocioDto.getColAutoridades()) {
            // recupera os limites de aprovação de cada autoridade do processo de negócio
            final Collection<LimiteAprovacaoAutoridadeDTO> colLimiteAprovacaoAutoridade = limiteAprovacaoAutoridadeDao
                    .findByIdNivelAutoridade(processoNivelAutoridadeDto.getIdNivelAutoridade());
            if (colLimiteAprovacaoAutoridade != null) {
                for (final LimiteAprovacaoAutoridadeDTO limiteAprovacaoAutoridadeDto : colLimiteAprovacaoAutoridade) {
                    final LimiteAprovacaoDTO limiteAprovacaoDto = mapLimites.get("" + limiteAprovacaoAutoridadeDto.getIdLimiteAprovacao());
                    if (limiteAprovacaoDto != null) {
                        // associa o limite de aprovação da autoridade, considerando que uma autoridade só pode ter um limite para determinado processo
                        limiteAprovacaoDto.setValido(true);
                        processoNivelAutoridadeDto.setLimiteAprovacaoDto(mapLimites.get("" + limiteAprovacaoAutoridadeDto.getIdLimiteAprovacao()));
                        break;
                    }
                }
            }
        }

        boolean bAtendimentoCliente = false;
        double valorIndividual = 0.0;
        if (this.isSimulacao(execucaoSolicitacao.getSolicitacaoServicoDto())) {
            bAtendimentoCliente = ((SimulacaoAlcadaDTO) execucaoSolicitacao.getSolicitacaoServicoDto()).getFinalidade().equalsIgnoreCase("C");
            valorIndividual = ((SimulacaoAlcadaDTO) execucaoSolicitacao.getSolicitacaoServicoDto()).getValor();
        } else {
            bAtendimentoCliente = execucaoSolicitacao.isAtendimentoCliente(execucaoSolicitacao.getSolicitacaoServicoDto());
            valorIndividual = execucaoSolicitacao.calculaValorAprovado(execucaoSolicitacao.getSolicitacaoServicoDto(), transacao);
        }
        this.recuperaValores(colLimites, execucaoSolicitacao, centroResultadoDto, bAtendimentoCliente);

        for (final ProcessoNivelAutoridadeDTO processoNivelAutoridadeDto : processoNegocioDto.getColAutoridades()) {
            if (processoNivelAutoridadeDto.getNivelAutoridadeDto() == null) {
                continue;
            }
            processoNivelAutoridadeDto.getNivelAutoridadeDto().setAlcadaRejeitada(false);
            final LimiteAprovacaoDTO limiteAprovacaoDto = processoNivelAutoridadeDto.getLimiteAprovacaoDto();

            // ignora os limites que não são por faixa de valor
            if (limiteAprovacaoDto == null || !limiteAprovacaoDto.getTipoLimitePorValor().equalsIgnoreCase("V") || limiteAprovacaoDto.getColValores() == null) {
                continue;
            }

            for (final ValorLimiteAprovacaoDTO valorDto : limiteAprovacaoDto.getColValores()) {
                if (!bAtendimentoCliente && valorDto.getTipoUtilizacao().equalsIgnoreCase("C")) {
                    continue;
                }
                if (bAtendimentoCliente && !valorDto.getTipoUtilizacao().equalsIgnoreCase("C")) {
                    continue;
                }
                double valorRef = 0.0;
                if (valorDto.getTipoLimite().equalsIgnoreCase("I")) {
                    valorRef = valorIndividual;
                } else if (limiteAprovacaoDto.getAbrangenciaCentroResultado().equalsIgnoreCase("R")) {
                    if (valorDto.getTipoLimite().equalsIgnoreCase("M")) {
                        if (bAtendimentoCliente) {
                            valorRef = limiteAprovacaoDto.getValorMensalAtendCliente() - valorIndividual;
                        } else {
                            valorRef = limiteAprovacaoDto.getValorMensalUsoInterno() - valorIndividual;
                        }
                    } else if (valorDto.getTipoLimite().equalsIgnoreCase("A")) {
                        if (bAtendimentoCliente) {
                            valorRef = limiteAprovacaoDto.getValorAnualAtendCliente() - valorIndividual;
                        } else {
                            valorRef = limiteAprovacaoDto.getValorAnualUsoInterno() - valorIndividual;
                        }
                    }
                }
                // invalida autoridades fora do valor limite
                if (valorRef > valorDto.getValorLimite()) {
                    processoNivelAutoridadeDto.getNivelAutoridadeDto().setAlcadaRejeitada(true);
                    processoNivelAutoridadeDto.getNivelAutoridadeDto().setMotivoRejeicao(MotivoRejeicaoAlcada.LimiteValor);
                    break;
                }
            }
        }
    }

    protected void recuperaValores(final Collection<LimiteAprovacaoDTO> colLimites, final ExecucaoSolicitacao execucaoSolicitacaoProcesso,
            final CentroResultadoDTO centroResultadoDto, final boolean bAtendimentoCliente) throws Exception {
        final SolicitacaoServicoDTO solicitacaoServicoDto = execucaoSolicitacaoProcesso.getSolicitacaoServicoDto();
        final Date dataAux = UtilDatas.getDataAtual();
        final int mes = UtilDatas.getMonth(dataAux);
        final int ano = UtilDatas.getYear(dataAux);

        final HashMap<String, ExecucaoSolicitacao> mapExecucaoSolicitacao = new HashMap<>();
        final TipoFluxoDao tipoFluxoDao = new TipoFluxoDao();
        this.atribuiTransacaoDao(tipoFluxoDao);
        for (final LimiteAprovacaoDTO limiteAprovacaoDto : colLimites) {
            // ignora os limites não associados a alguma autoridade
            if (!limiteAprovacaoDto.isValido()) {
                continue;
            }

            double valorMensalUsoInterno = 0.0;
            double valorAnualUsoInterno = 0.0;
            double valorMensalAtendCliente = 0.0;
            double valorAnualAtendCliente = 0.0;
            if (limiteAprovacaoDto.getColProcessos() != null) {
                for (final ProcessoNegocioDTO processoNegocioDto : limiteAprovacaoDto.getColProcessos()) {
                    // recupera os fluxos de cada processo associado ao limite de aprovação
                    final Collection<TipoFluxoDTO> colFluxos = tipoFluxoDao.findByIdProcessoNegocio(processoNegocioDto.getIdProcessoNegocio());
                    if (colFluxos != null) {
                        for (final TipoFluxoDTO tipoFluxoDto : colFluxos) {
                            if (tipoFluxoDto.getNomeClasseFluxo() == null || tipoFluxoDto.getNomeClasseFluxo().trim().equals("")) {
                                tipoFluxoDto.setNomeClasseFluxo(ExecucaoSolicitacao.class.getName());
                            }
                            ExecucaoSolicitacao execucaoSolicitacao = mapExecucaoSolicitacao.get(tipoFluxoDto.getNomeClasseFluxo());
                            if (execucaoSolicitacao == null) {
                                // calcula os valores mensais e anuais
                                try {
                                    execucaoSolicitacao = (ExecucaoSolicitacao) Class.forName(tipoFluxoDto.getNomeClasseFluxo()).newInstance();
                                    execucaoSolicitacao.setObjetoNegocioDto(solicitacaoServicoDto);
                                    mapExecucaoSolicitacao.put(tipoFluxoDto.getNomeClasseFluxo(), execucaoSolicitacao);
                                    if (this.isSimulacao(solicitacaoServicoDto)) {
                                        double valor = 0.0;
                                        if (execucaoSolicitacaoProcesso.getClass().getName().equals(execucaoSolicitacao.getClass().getName())) {
                                            valor = ((SimulacaoAlcadaDTO) solicitacaoServicoDto).getValorMensal();
                                        } else {
                                            valor = ((SimulacaoAlcadaDTO) solicitacaoServicoDto).getValorOutrasAlcadas();
                                        }
                                        if (bAtendimentoCliente) {
                                            execucaoSolicitacao.setValorAnualAtendCliente(valor);
                                            execucaoSolicitacao.setValorMensalAtendCliente(valor);
                                        } else {
                                            execucaoSolicitacao.setValorAnualUsoInterno(valor);
                                            execucaoSolicitacao.setValorMensalUsoInterno(valor);
                                        }
                                    } else {
                                        execucaoSolicitacao.calculaValorAprovadoMensal(centroResultadoDto, mes, ano, transacao);
                                        execucaoSolicitacao.calculaValorAprovadoAnual(centroResultadoDto, mes, transacao);
                                    }
                                } catch (final Exception e) {}
                            }
                            if (execucaoSolicitacao != null) {
                                valorMensalUsoInterno += execucaoSolicitacao.getValorMensalUsoInterno();
                                valorMensalAtendCliente += execucaoSolicitacao.getValorMensalAtendCliente();
                                valorAnualUsoInterno += execucaoSolicitacao.getValorAnualUsoInterno();
                                valorAnualAtendCliente += execucaoSolicitacao.getValorAnualAtendCliente();
                            }
                        }
                    }
                }
            }
            limiteAprovacaoDto.setValorMensalUsoInterno(valorMensalUsoInterno);
            limiteAprovacaoDto.setValorAnualUsoInterno(valorAnualUsoInterno);
            limiteAprovacaoDto.setValorMensalAtendCliente(valorMensalAtendCliente);
            limiteAprovacaoDto.setValorAnualAtendCliente(valorAnualAtendCliente);
        }
    }

    protected void recuperaAutoridadesProcesso(final ProcessoNegocioDTO processoNegocioDto, final ProcessoNivelAutoridadeDao processoNivelAutoridadeDao)
            throws Exception {
        final Collection<ProcessoNivelAutoridadeDTO> colAutoridades = processoNivelAutoridadeDao.findByIdProcessoNegocio(processoNegocioDto
                .getIdProcessoNegocio());
        for (final ProcessoNivelAutoridadeDTO processoNivelAutoridadeDto : colAutoridades) {
            final NivelAutoridadeDTO nivelDto = this.getHashMapNivelAutoridade().get("" + processoNivelAutoridadeDto.getIdNivelAutoridade());
            if (nivelDto != null) {
                processoNivelAutoridadeDto.setNivelAutoridadeDto(nivelDto);
                processoNivelAutoridadeDto.setHierarquia(nivelDto.getHierarquia());
            }
        }
        Collections.sort((List) colAutoridades, new ObjectSimpleComparator("getHierarquia", ObjectSimpleComparator.ASC));
        processoNegocioDto.setColAutoridades(colAutoridades);
    }

    protected HashMap<String, GrupoEmpregadoDTO> getHashMapGruposEmpregado(final EmpregadoDTO empregadoDto) throws Exception {
        final HashMap<String, GrupoEmpregadoDTO> result = new HashMap<>();
        final GrupoEmpregadoDao grupoEmpregadoDao = new GrupoEmpregadoDao();
        this.atribuiTransacaoDao(grupoEmpregadoDao);
        final Collection<GrupoEmpregadoDTO> colGrupos = grupoEmpregadoDao.findAtivosByIdEmpregado(empregadoDto.getIdEmpregado());
        if (colGrupos != null) {
            for (final GrupoEmpregadoDTO grupoEmpregadoDto : colGrupos) {
                result.put("" + grupoEmpregadoDto.getIdGrupo(), grupoEmpregadoDto);
            }
        }
        return result;
    }

    protected AlcadaProcessoNegocioDTO getAlcadaProcessoNegocio(final CentroResultadoDTO centroResultadoDto, final EmpregadoDTO empregadoDto,
            final HashMap<String, GrupoEmpregadoDTO> mapGruposEmpregado, final ResponsavelCentroResultadoProcessoDao responsavelCentroResultadoProcessoDao,
            final ProcessoNegocioDTO processoNegocioRefDto) throws Exception {
        AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto = null;
        final List<ProcessoNegocioDTO> processosNegocio = new ArrayList<>();
        final Collection<ResponsavelCentroResultadoProcessoDTO> colResponsavelProcesso = responsavelCentroResultadoProcessoDao
                .findByIdCentroResultadoAndIdResponsavel(centroResultadoDto.getIdCentroResultado(), empregadoDto.getIdEmpregado());
        if (colResponsavelProcesso != null) {
            for (final ResponsavelCentroResultadoProcessoDTO responsavelCentroResultadoProcessoDto : colResponsavelProcesso) {
                if (processoNegocioRefDto != null && processoNegocioRefDto.getIdProcessoNegocio() != null
                        && processoNegocioRefDto.getIdProcessoNegocio().intValue() != responsavelCentroResultadoProcessoDto.getIdProcessoNegocio().intValue()) {
                    continue;
                }
                final ProcessoNegocioDTO processoNegocioDto = this.getHashMapProcessosNegocio().get(
                        "" + responsavelCentroResultadoProcessoDto.getIdProcessoNegocio());
                if (processoNegocioDto != null && processoNegocioDto.getColAutoridades() != null) {
                    for (final ProcessoNivelAutoridadeDTO processoNivelAutoridadeDto : processoNegocioDto.getColAutoridades()) {
                        if (processoNivelAutoridadeDto.getNivelAutoridadeDto() != null
                                && processoNivelAutoridadeDto.getNivelAutoridadeDto().getColGrupos() != null) {
                            for (final GrupoNivelAutoridadeDTO grupoNivelAutoridadeDto : processoNivelAutoridadeDto.getNivelAutoridadeDto().getColGrupos()) {
                                if (mapGruposEmpregado.get("" + grupoNivelAutoridadeDto.getIdGrupo()) != null) {
                                    final ProcessoNegocioDTO processoDto = new ProcessoNegocioDTO();
                                    Reflexao.copyPropertyValues(processoNegocioDto, processoDto);
                                    processoDto.setNivelAutoridadeDto(this.getHashMapNivelAutoridade().get(
                                            "" + processoNivelAutoridadeDto.getIdNivelAutoridade()));
                                    processosNegocio.add(processoDto);
                                }
                            }
                        }
                    }
                }
            }
            if (processosNegocio.size() > 0) {
                alcadaProcessoNegocioDto = new AlcadaProcessoNegocioDTO();
                alcadaProcessoNegocioDto.setCentroResultadoDto(centroResultadoDto);
                alcadaProcessoNegocioDto.setEmpregadoDto(empregadoDto);
                alcadaProcessoNegocioDto.setProcessosNegocio(processosNegocio);
                alcadaProcessoNegocioDto.setMapGruposEmpregado(mapGruposEmpregado);
            }
        }
        return alcadaProcessoNegocioDto;
    }

    protected HashMap<String, ProcessoNegocioDTO> getHashMapProcessosNegocio() throws Exception {
        if (mapProcessosNegocio == null) {
            mapProcessosNegocio = new HashMap<>();
            final Collection<ProcessoNegocioDTO> colProcessos = this.atribuiTransacaoDao(new ProcessoNegocioDao()).list();
            if (colProcessos != null) {
                final ProcessoNivelAutoridadeDao processoNivelAutoridadeDao = new ProcessoNivelAutoridadeDao();
                this.atribuiTransacaoDao(processoNivelAutoridadeDao);
                for (final ProcessoNegocioDTO processoNegocioDto : colProcessos) {
                    this.recuperaAutoridadesProcesso(processoNegocioDto, processoNivelAutoridadeDao);
                    mapProcessosNegocio.put("" + processoNegocioDto.getIdProcessoNegocio(), processoNegocioDto);
                }
            }
        }
        return mapProcessosNegocio;
    }

    protected HashMap<String, NivelAutoridadeDTO> getHashMapNivelAutoridade() throws Exception {
        if (mapAutoridades == null) {
            mapAutoridades = new HashMap<>();
            final Collection<NivelAutoridadeDTO> colAutoridades = this.atribuiTransacaoDao(new NivelAutoridadeDao()).list();
            if (colAutoridades != null) {
                final GrupoNivelAutoridadeDao grupoNivelAutoridadeDao = new GrupoNivelAutoridadeDao();
                this.atribuiTransacaoDao(grupoNivelAutoridadeDao);
                for (final NivelAutoridadeDTO nivelDto : colAutoridades) {
                    nivelDto.setColGrupos(grupoNivelAutoridadeDao.findByIdNivelAutoridade(nivelDto.getIdNivelAutoridade()));
                    mapAutoridades.put("" + nivelDto.getIdNivelAutoridade(), nivelDto);
                }
            }
        }
        return mapAutoridades;
    }

    public List<AlcadaProcessoNegocioDTO> getAlcadasCentroResultado(final CentroResultadoDTO centroResultadoDto, final ProcessoNegocioDTO processoNegocioDto)
            throws Exception {
        final List<AlcadaProcessoNegocioDTO> result = new ArrayList<>();

        final ResponsavelCentroResultadoDao responsavelCentroResultadoDao = new ResponsavelCentroResultadoDao();
        this.atribuiTransacaoDao(responsavelCentroResultadoDao);
        final Collection<ResponsavelCentroResultadoDTO> colCentrosResultado = responsavelCentroResultadoDao.findByIdCentroResultado(centroResultadoDto
                .getIdCentroResultado());
        if (colCentrosResultado != null) {
            final EmpregadoDao empregadoDao = new EmpregadoDao();
            this.atribuiTransacaoDao(empregadoDao);
            final ResponsavelCentroResultadoProcessoDao responsavelCentroResultadoProcessoDao = new ResponsavelCentroResultadoProcessoDao();

            for (final ResponsavelCentroResultadoDTO responsavelCentroResultadoDto : colCentrosResultado) {
                EmpregadoDTO empregadoDto = new EmpregadoDTO();
                empregadoDto.setIdEmpregado(responsavelCentroResultadoDto.getIdResponsavel());
                empregadoDto = (EmpregadoDTO) empregadoDao.restore(empregadoDto);
                final HashMap<String, GrupoEmpregadoDTO> mapGruposEmpregado = this.getHashMapGruposEmpregado(empregadoDto);

                final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto = this.getAlcadaProcessoNegocio(centroResultadoDto, empregadoDto, mapGruposEmpregado,
                        responsavelCentroResultadoProcessoDao, processoNegocioDto);
                if (alcadaProcessoNegocioDto != null) {
                    result.add(alcadaProcessoNegocioDto);
                }
            }
        }

        return result;
    }

    public List<AlcadaProcessoNegocioDTO> getAlcadasEmpregado(final EmpregadoDTO empregadoDto, final ProcessoNegocioDTO processoNegocioDto) throws Exception {
        final List<AlcadaProcessoNegocioDTO> result = new ArrayList<>();

        final ResponsavelCentroResultadoDao responsavelCentroResultadoDao = new ResponsavelCentroResultadoDao();
        this.atribuiTransacaoDao(responsavelCentroResultadoDao);
        final Collection<ResponsavelCentroResultadoDTO> colCentrosResultado = responsavelCentroResultadoDao.findByIdResponsavel(empregadoDto.getIdEmpregado());
        if (colCentrosResultado != null) {
            final CentroResultadoDao centroResultadoDao = new CentroResultadoDao();
            this.atribuiTransacaoDao(centroResultadoDao);
            final ResponsavelCentroResultadoProcessoDao responsavelCentroResultadoProcessoDao = new ResponsavelCentroResultadoProcessoDao();
            this.atribuiTransacaoDao(responsavelCentroResultadoProcessoDao);
            final HashMap<String, GrupoEmpregadoDTO> mapGruposEmpregado = this.getHashMapGruposEmpregado(empregadoDto);

            for (final ResponsavelCentroResultadoDTO responsavelCentroResultadoDto : colCentrosResultado) {
                CentroResultadoDTO centroResultadoDto = new CentroResultadoDTO();
                centroResultadoDto.setIdCentroResultado(responsavelCentroResultadoDto.getIdCentroResultado());
                centroResultadoDto = (CentroResultadoDTO) centroResultadoDao.restore(centroResultadoDto);

                final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto = this.getAlcadaProcessoNegocio(centroResultadoDto, empregadoDto, mapGruposEmpregado,
                        responsavelCentroResultadoProcessoDao, processoNegocioDto);
                if (alcadaProcessoNegocioDto != null) {
                    result.add(alcadaProcessoNegocioDto);
                }
            }
        }

        return result;
    }

    public List<AlcadaProcessoNegocioDTO> getAlcadasNivelSuperior(final ProcessoNegocioDTO processoNegocioDto, final List<AlcadaProcessoNegocioDTO> colAlcadas)
            throws Exception {
        final List<AlcadaProcessoNegocioDTO> result = new ArrayList<>();
        if (processoNegocioDto.getColLimitesAprovacao() != null) {
            final Collection<NivelAutoridadeDTO> colAutoridades = new ArrayList<>();
            final LimiteAprovacaoAutoridadeDao limiteAprovacaoAutoridadeDao = new LimiteAprovacaoAutoridadeDao();
            this.atribuiTransacaoDao(limiteAprovacaoAutoridadeDao);
            for (final LimiteAprovacaoDTO limiteAprovacaoDto : processoNegocioDto.getColLimitesAprovacao()) {
                if (limiteAprovacaoDto.getAbrangenciaCentroResultado().equalsIgnoreCase("R")) {
                    continue;
                }
                if (limiteAprovacaoDto.getTipoLimitePorValor().equalsIgnoreCase("V")) {
                    continue;
                }
                final Collection<LimiteAprovacaoAutoridadeDTO> colLimiteAprovacaoAutoridade = limiteAprovacaoAutoridadeDao
                        .findByIdLimiteAprovacao(limiteAprovacaoDto.getIdLimiteAprovacao());
                if (colLimiteAprovacaoAutoridade != null) {
                    for (final LimiteAprovacaoAutoridadeDTO limiteAprovacaoAutoridadeDto : colLimiteAprovacaoAutoridade) {
                        colAutoridades.add(this.getHashMapNivelAutoridade().get("" + limiteAprovacaoAutoridadeDto.getIdNivelAutoridade()));
                    }
                }
            }
            final HashMap<String, EmpregadoDTO> mapEmpregados = new HashMap<>();
            if (colAlcadas != null) {
                for (final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto : colAlcadas) {
                    mapEmpregados.put("" + alcadaProcessoNegocioDto.getEmpregadoDto().getIdEmpregado(), alcadaProcessoNegocioDto.getEmpregadoDto());
                }
            }
            final GrupoEmpregadoDao grupoEmpregadoDao = new GrupoEmpregadoDao();
            this.atribuiTransacaoDao(grupoEmpregadoDao);
            for (final NivelAutoridadeDTO nivelAutoridadeDto : colAutoridades) {
                for (final GrupoNivelAutoridadeDTO grupoNivelAutoridadeDto : nivelAutoridadeDto.getColGrupos()) {
                    final Collection<GrupoEmpregadoDTO> colGrupoEmpregado = grupoEmpregadoDao.findByIdGrupo(grupoNivelAutoridadeDto.getIdGrupo());
                    if (colGrupoEmpregado != null) {
                        for (final GrupoEmpregadoDTO grupoEmpregadoDto : colGrupoEmpregado) {
                            if (mapEmpregados.get("" + grupoEmpregadoDto.getIdEmpregado()) != null) {
                                continue;
                            }
                            final EmpregadoDTO empregadoDto = this.recuperaEmpregado(grupoEmpregadoDto.getIdEmpregado());
                            mapEmpregados.put("" + grupoEmpregadoDto.getIdEmpregado(), empregadoDto);

                            final List<ProcessoNegocioDTO> colProcessos = new ArrayList<>();
                            final ProcessoNegocioDTO procDto = new ProcessoNegocioDTO();
                            Reflexao.copyPropertyValues(processoNegocioDto, procDto);
                            procDto.setNivelAutoridadeDto(nivelAutoridadeDto);
                            colProcessos.add(procDto);
                            final AlcadaProcessoNegocioDTO alcadaDto = new AlcadaProcessoNegocioDTO();
                            alcadaDto.setEmpregadoDto(empregadoDto);
                            alcadaDto.setProcessosNegocio(colProcessos);
                            result.add(alcadaDto);
                        }
                    }
                }
            }
        }
        return result;
    }

    public ProcessoNegocioDTO recuperaProcessoNegocio(final SolicitacaoServicoDTO solicitacaoServicoDto) throws Exception {
        ProcessoNegocioDTO result = null;
        final FluxoDTO fluxoDto = new SolicitacaoServicoServiceEjb().recuperaFluxo(solicitacaoServicoDto);
        if (fluxoDto != null && fluxoDto.getIdProcessoNegocio() != null) {
            result = this.recuperaProcessoNegocio(fluxoDto.getIdProcessoNegocio());
        }
        if (result != null) {
            this.recuperaAutoridadesProcesso(result, (ProcessoNivelAutoridadeDao) this.atribuiTransacaoDao(new ProcessoNivelAutoridadeDao()));
        }
        return result;
    }

    protected void validaAlcadas(final FluxoDTO fluxoDto, final ProcessoNegocioDTO processoNegocioDto, final ExecucaoSolicitacao execucaoSolicitacao,
            final SolicitacaoServicoDTO solicitacaoServicoDto, final List<AlcadaProcessoNegocioDTO> colAlcadas) throws Exception {
        this.validaAutoridades(fluxoDto, processoNegocioDto, execucaoSolicitacao, solicitacaoServicoDto, colAlcadas);
        this.validaSolicitante(fluxoDto, processoNegocioDto, execucaoSolicitacao, solicitacaoServicoDto, colAlcadas);
        this.validaAprovador(fluxoDto, processoNegocioDto, execucaoSolicitacao, solicitacaoServicoDto, colAlcadas);
        this.validaHierarquia(fluxoDto, processoNegocioDto, execucaoSolicitacao, solicitacaoServicoDto, colAlcadas);
        this.validaUsuario(fluxoDto, processoNegocioDto, execucaoSolicitacao, solicitacaoServicoDto, colAlcadas);
    }

    protected void validaHierarquia(final FluxoDTO fluxoDto, final ProcessoNegocioDTO processoNegocioDto, final ExecucaoSolicitacao execucaoSolicitacao,
            final SolicitacaoServicoDTO solicitacaoServicoDto, final List<AlcadaProcessoNegocioDTO> colAlcadas) throws Exception {
        if (processoNegocioDto.getPermiteAprovacaoNivelInferior() == null || processoNegocioDto.getPermiteAprovacaoNivelInferior().equalsIgnoreCase("S")) {
            return;
        }
        int hierarquia = 0;
        for (final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto : colAlcadas) {
            if (alcadaProcessoNegocioDto.isAlcadaRejeitada()) {
                continue;
            }
            if (alcadaProcessoNegocioDto.getEmpregadoDto().getIdEmpregado().intValue() != solicitacaoServicoDto.getIdSolicitante().intValue()) {
                continue;
            }
            final NivelAutoridadeDTO nivelAutoridadeDto = alcadaProcessoNegocioDto.getProcessosNegocio().get(0).getNivelAutoridadeDto();
            hierarquia = nivelAutoridadeDto.getHierarquia().intValue();
            break;
        }
        if (hierarquia == 0) {
            return;
        }
        for (final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto : colAlcadas) {
            if (alcadaProcessoNegocioDto.isAlcadaRejeitada()) {
                continue;
            }
            final NivelAutoridadeDTO nivelAutoridadeDto = alcadaProcessoNegocioDto.getProcessosNegocio().get(0).getNivelAutoridadeDto();
            if (nivelAutoridadeDto.getHierarquia().intValue() > hierarquia) {
                alcadaProcessoNegocioDto.setMotivoRejeicao(MotivoRejeicaoAlcada.HierarquiaAutoridade);
                alcadaProcessoNegocioDto.setAlcadaRejeitada(true);
                continue;
            }
        }
    }

    protected void validaSolicitante(final FluxoDTO fluxoDto, final ProcessoNegocioDTO processoNegocioDto, final ExecucaoSolicitacao execucaoSolicitacao,
            final SolicitacaoServicoDTO solicitacaoServicoDto, final List<AlcadaProcessoNegocioDTO> colAlcadas) throws Exception {
        for (final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto : colAlcadas) {
            if (alcadaProcessoNegocioDto.isAlcadaRejeitada()) {
                continue;
            }
            final NivelAutoridadeDTO nivelAutoridadeDto = alcadaProcessoNegocioDto.getProcessosNegocio().get(0).getNivelAutoridadeDto();
            for (final ProcessoNivelAutoridadeDTO processoNivelAutoridadeDto : processoNegocioDto.getColAutoridades()) {
                if (processoNivelAutoridadeDto.getIdNivelAutoridade().intValue() != nivelAutoridadeDto.getIdNivelAutoridade().intValue()) {
                    continue;
                }
                if (processoNivelAutoridadeDto.getPermiteAprovacaoPropria().equals("N")
                        && alcadaProcessoNegocioDto.getEmpregadoDto().getIdEmpregado().intValue() == solicitacaoServicoDto.getIdSolicitante().intValue()) {
                    alcadaProcessoNegocioDto.setMotivoRejeicao(MotivoRejeicaoAlcada.PermissaoAutoridade);
                    alcadaProcessoNegocioDto.setAlcadaRejeitada(true);
                    break;
                }
            }
        }
    }

    protected void validaUsuario(final FluxoDTO fluxoDto, final ProcessoNegocioDTO processoNegocioDto, final ExecucaoSolicitacao execucaoSolicitacao,
            final SolicitacaoServicoDTO solicitacaoServicoDto, final List<AlcadaProcessoNegocioDTO> colAlcadas) throws Exception {
        final UsuarioDao usuarioDao = new UsuarioDao();
        this.atribuiTransacaoDao(usuarioDao);
        for (final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto : colAlcadas) {
            if (alcadaProcessoNegocioDto.isAlcadaRejeitada()) {
                continue;
            }
            final UsuarioDTO usuarioDto = usuarioDao.restoreAtivoByIdEmpregado(alcadaProcessoNegocioDto.getEmpregadoDto().getIdEmpregado());
            if (usuarioDto == null) {
                alcadaProcessoNegocioDto.setAlcadaRejeitada(true);
                alcadaProcessoNegocioDto.setMotivoRejeicao(MotivoRejeicaoAlcada.UsuarioNaoExiste);
                continue;
            }
        }
    }

    protected void validaAutoridades(final FluxoDTO fluxoDto, final ProcessoNegocioDTO processoNegocioDto, final ExecucaoSolicitacao execucaoSolicitacao,
            final SolicitacaoServicoDTO solicitacaoServicoDto, final List<AlcadaProcessoNegocioDTO> colAlcadas) throws Exception {
        for (final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto : colAlcadas) {
            if (alcadaProcessoNegocioDto.isAlcadaRejeitada()) {
                continue;
            }
            final NivelAutoridadeDTO nivelAutoridadeDto = alcadaProcessoNegocioDto.getProcessosNegocio().get(0).getNivelAutoridadeDto();
            for (final ProcessoNivelAutoridadeDTO processoNivelAutoridadeDto : processoNegocioDto.getColAutoridades()) {
                if (processoNivelAutoridadeDto.getIdNivelAutoridade().intValue() != nivelAutoridadeDto.getIdNivelAutoridade().intValue()) {
                    continue;
                }
                if (processoNivelAutoridadeDto.getNivelAutoridadeDto().isAlcadaRejeitada()) {
                    alcadaProcessoNegocioDto.setMotivoRejeicao(processoNivelAutoridadeDto.getNivelAutoridadeDto().getMotivoRejeicao());
                    alcadaProcessoNegocioDto.setAlcadaRejeitada(true);
                    break;
                }
            }
        }
    }

    protected void validaAprovador(final FluxoDTO fluxoDto, final ProcessoNegocioDTO processoNegocioDto, final ExecucaoSolicitacao execucaoSolicitacao,
            final SolicitacaoServicoDTO solicitacaoServicoDto, final List<AlcadaProcessoNegocioDTO> colAlcadas) throws Exception {
        for (final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto : colAlcadas) {
            if (alcadaProcessoNegocioDto.isAlcadaRejeitada()) {
                continue;
            }
            if (!execucaoSolicitacao.permiteAprovacaoAlcada(alcadaProcessoNegocioDto, solicitacaoServicoDto)) {
                alcadaProcessoNegocioDto.setAlcadaRejeitada(true);
                alcadaProcessoNegocioDto.setMotivoRejeicao(MotivoRejeicaoAlcada.RegrasProcesso);
                continue;
            }
        }
    }

    protected Collection<AlcadaProcessoNegocioDTO> recuperaDelegacoes(final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto,
            final ExecucaoSolicitacao execucaoSolicitacao) throws Exception {
        final Collection<AlcadaProcessoNegocioDTO> colAlcadasDelgacoes = new ArrayList<>();
        final DelegacaoCentroResultadoDao delegacaoCentroResultadoDao = new DelegacaoCentroResultadoDao();
        this.atribuiTransacaoDao(delegacaoCentroResultadoDao);
        final Collection<DelegacaoCentroResultadoDTO> colDelegCentroResultado = delegacaoCentroResultadoDao.findByIdResponsavelAndIdCentroResultado(
                alcadaProcessoNegocioDto.getEmpregadoDto().getIdEmpregado(), alcadaProcessoNegocioDto.getCentroResultadoDto().getIdCentroResultado());
        if (colDelegCentroResultado == null) {
            return colAlcadasDelgacoes;
        }
        new ArrayList<>();
        final DelegacaoCentroResultadoProcessoDao delegacaoCentroResultadoProcessoDao = new DelegacaoCentroResultadoProcessoDao();
        this.atribuiTransacaoDao(delegacaoCentroResultadoProcessoDao);
        final DelegacaoCentroResultadoFluxoDao delegacaoCentroResultadoFluxoDao = new DelegacaoCentroResultadoFluxoDao();
        this.atribuiTransacaoDao(delegacaoCentroResultadoFluxoDao);
        for (final DelegacaoCentroResultadoDTO delegacaoCentroResultadoDto : colDelegCentroResultado) {
            if (delegacaoCentroResultadoDto.getDataFim().compareTo(UtilDatas.getDataAtual()) < 0) {
                continue;
            }
            if (delegacaoCentroResultadoDto.getRevogada().equalsIgnoreCase("S")) {
                continue;
            }
            final DelegacaoCentroResultadoProcessoDTO delegacaoCentroResultadoProcessoDto = new DelegacaoCentroResultadoProcessoDTO();
            delegacaoCentroResultadoProcessoDto.setIdDelegacaoCentroResultado(delegacaoCentroResultadoDto.getIdDelegacaoCentroResultado());
            delegacaoCentroResultadoProcessoDto.setIdProcessoNegocio(alcadaProcessoNegocioDto.getProcessosNegocio().get(0).getIdProcessoNegocio());
            delegacaoCentroResultadoProcessoDao.restore(delegacaoCentroResultadoProcessoDto);

            if (delegacaoCentroResultadoDto.getAbrangencia().equalsIgnoreCase(DelegacaoCentroResultadoDTO.ESPECIFICAS)) {
                if (execucaoSolicitacao.getExecucaoSolicitacaoDto() == null) {
                    continue;
                }
                final DelegacaoCentroResultadoFluxoDTO delegacaoCentroResultadoFluxoDto = new DelegacaoCentroResultadoFluxoDTO();
                delegacaoCentroResultadoFluxoDto.setIdDelegacaoCentroResultado(delegacaoCentroResultadoDto.getIdDelegacaoCentroResultado());
                delegacaoCentroResultadoFluxoDto.setIdInstanciaFluxo(execucaoSolicitacao.getExecucaoSolicitacaoDto().getIdInstanciaFluxo());
                delegacaoCentroResultadoFluxoDao.restore(delegacaoCentroResultadoFluxoDto);
            }
            final EmpregadoDTO empregadoDto = this.recuperaEmpregado(delegacaoCentroResultadoDto.getIdEmpregado());
            if (empregadoDto == null) {
                continue;
            }
            final AlcadaProcessoNegocioDTO alcadaDelegDto = new AlcadaProcessoNegocioDTO();
            alcadaDelegDto.setAlcadaOrigemDto(alcadaProcessoNegocioDto);
            alcadaDelegDto.setCentroResultadoDto(alcadaProcessoNegocioDto.getCentroResultadoDto());
            alcadaDelegDto.setDelegacao(true);
            alcadaDelegDto.setEmpregadoDto(empregadoDto);
            alcadaDelegDto.setProcessosNegocio(alcadaProcessoNegocioDto.getProcessosNegocio());
            alcadaDelegDto.setMapGruposEmpregado(this.getHashMapGruposEmpregado(empregadoDto));
            colAlcadasDelgacoes.add(alcadaDelegDto);
        }
        return colAlcadasDelgacoes;
    }

    protected Collection<AlcadaProcessoNegocioDTO> getAlcadasResponsaveis(final SolicitacaoServicoDTO solicitacaoServicoDto,
            final CentroResultadoDTO centroResultadoDto, final FluxoDTO fluxoDto, final TransactionControler tc) throws Exception {
        transacao = tc;

        final ProcessoNegocioDTO processoNegocioDto = this.recuperaProcessoNegocio(fluxoDto);
        if (processoNegocioDto == null) {
            throw new LogicException("Processo de negócio não encontrado");
        }

        final ExecucaoSolicitacao execucaoSolicitacao = this.recuperaExecucaoSolicitacao(fluxoDto, solicitacaoServicoDto);
        if (execucaoSolicitacao == null) {
            throw new LogicException("Instância do fluxo não encontrada");
        }

        List<AlcadaProcessoNegocioDTO> colAlcadas = this.getAlcadasCentroResultado(centroResultadoDto, processoNegocioDto);
        if (colAlcadas == null || colAlcadas.isEmpty()) {
            return null;
        }

        final List<AlcadaProcessoNegocioDTO> colAlcadasDeleg = new ArrayList<>();
        for (final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto : colAlcadas) {
            colAlcadasDeleg.addAll(this.recuperaDelegacoes(alcadaProcessoNegocioDto, execucaoSolicitacao));
        }
        colAlcadas.addAll(colAlcadasDeleg);

        this.associaLimiteAutoridadeEValores(processoNegocioDto, execucaoSolicitacao, centroResultadoDto);
        this.validaAlcadas(fluxoDto, processoNegocioDto, execucaoSolicitacao, solicitacaoServicoDto, colAlcadas);

        int i = 0;
        List<AlcadaProcessoNegocioDTO> result = null;
        final boolean bFiltraHierarquia = fluxoDto.getProcessoNegocioDto().getAlcadaPrimeiroNivel() != null
                && fluxoDto.getProcessoNegocioDto().getAlcadaPrimeiroNivel().equalsIgnoreCase("S");
        if (bFiltraHierarquia) {
            int primeiroNivel = 0;
            for (final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto : colAlcadas) {
                if (alcadaProcessoNegocioDto.isAlcadaRejeitada()) {
                    continue;
                }
                if (primeiroNivel < alcadaProcessoNegocioDto.recuperaHierarquiaNivelAutoridade()) {
                    primeiroNivel = alcadaProcessoNegocioDto.recuperaHierarquiaNivelAutoridade();
                }
            }

            result = new ArrayList<>();
            for (final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto : colAlcadas) {
                if (alcadaProcessoNegocioDto.isAlcadaRejeitada() || alcadaProcessoNegocioDto.recuperaHierarquiaNivelAutoridade() == primeiroNivel) {
                    result.add(alcadaProcessoNegocioDto);
                    if (!alcadaProcessoNegocioDto.isAlcadaRejeitada()) {
                        i++;
                    }
                }
            }
            colAlcadas = result;
        } else {
            result = colAlcadas;
            for (final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto : colAlcadas) {
                if (alcadaProcessoNegocioDto.isAlcadaRejeitada()) {
                    continue;
                }
                i++;
            }
        }

        if (i > 0) {
            return this.ordenaAlcadas(result);
        }

        result.addAll(this.getAlcadasNivelSuperior(processoNegocioDto, result));
        return this.ordenaAlcadas(result);
    }

    private Collection<AlcadaProcessoNegocioDTO> ordenaAlcadas(final List<AlcadaProcessoNegocioDTO> colAlcadas) throws Exception {
        for (final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto : colAlcadas) {
            String chaveOrdenacao = "";
            if (alcadaProcessoNegocioDto.getProcessosNegocio() != null && !alcadaProcessoNegocioDto.getProcessosNegocio().isEmpty()) {
                final ProcessoNegocioDTO processoNegocioDto = alcadaProcessoNegocioDto.getProcessosNegocio().get(0);
                if (processoNegocioDto.getNivelAutoridadeDto() != null) {
                    chaveOrdenacao = "" + processoNegocioDto.getNivelAutoridadeDto().getHierarquia();
                }
            }
            alcadaProcessoNegocioDto.setChaveOrdenacao(chaveOrdenacao);
        }
        Collections.sort(colAlcadas, new ObjectSimpleComparator("getChaveOrdenacao", ObjectSimpleComparator.DESC));
        return colAlcadas;
    }

    public Collection<AlcadaProcessoNegocioDTO> getSimulacaoAlcada(final SimulacaoAlcadaDTO simulacaoAlcadaDto, final CentroResultadoDTO centroResultadoDto,
            final FluxoDTO fluxoDto) throws Exception {
        return this.getAlcadasResponsaveis(simulacaoAlcadaDto, centroResultadoDto, fluxoDto, null);
    }

    public Collection<EmpregadoDTO> getResponsaveis(final SolicitacaoServicoDTO solicitacaoServicoDto, final CentroResultadoDTO centroResultadoDto,
            final TransactionControler tc) throws Exception {
        transacao = tc;

        final FluxoDTO fluxoDto = this.recuperaFluxo(solicitacaoServicoDto);
        if (fluxoDto == null) {
            throw new LogicException("Fluxo não encontrado");
        }

        final Collection<AlcadaProcessoNegocioDTO> colAlcadas = this.getAlcadasResponsaveis(solicitacaoServicoDto, centroResultadoDto, fluxoDto, tc);
        if (colAlcadas == null) {
            return null;
        }

        final Collection<EmpregadoDTO> result = new ArrayList<>();
        for (final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto : colAlcadas) {
            if (alcadaProcessoNegocioDto.isAlcadaRejeitada()) {
                continue;
            }
            result.add(alcadaProcessoNegocioDto.getEmpregadoDto());
        }
        return result;
    }

    public Collection<AlcadaProcessoNegocioDTO> getAlcadasResponsaveis(final SolicitacaoServicoDTO solicitacaoServicoDto,
            final CentroResultadoDTO centroResultadoDto, final TransactionControler tc) throws Exception {
        transacao = tc;

        final FluxoDTO fluxoDto = this.recuperaFluxo(solicitacaoServicoDto);
        if (fluxoDto == null) {
            throw new LogicException("Fluxo não encontrado");
        }

        return this.getAlcadasResponsaveis(solicitacaoServicoDto, centroResultadoDto, fluxoDto, tc);
    }

}
