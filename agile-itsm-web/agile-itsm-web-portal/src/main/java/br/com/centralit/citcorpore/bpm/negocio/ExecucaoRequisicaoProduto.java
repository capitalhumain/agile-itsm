package br.com.centralit.citcorpore.bpm.negocio;

import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.centralit.bpm.dto.AtribuicaoFluxoDTO;
import br.com.centralit.bpm.dto.EventoFluxoDTO;
import br.com.centralit.bpm.dto.FluxoDTO;
import br.com.centralit.bpm.dto.ItemTrabalhoFluxoDTO;
import br.com.centralit.bpm.dto.TarefaFluxoDTO;
import br.com.centralit.bpm.integracao.AtribuicaoFluxoDao;
import br.com.centralit.bpm.integracao.EventoFluxoDao;
import br.com.centralit.bpm.integracao.ItemTrabalhoFluxoDao;
import br.com.centralit.bpm.negocio.InstanciaFluxo;
import br.com.centralit.bpm.negocio.ItemTrabalho;
import br.com.centralit.bpm.negocio.Tarefa;
import br.com.centralit.bpm.util.Enumerados.TipoAtribuicao;
import br.com.centralit.citcorpore.bean.AlcadaDTO;
import br.com.centralit.citcorpore.bean.AlcadaProcessoNegocioDTO;
import br.com.centralit.citcorpore.bean.CentroResultadoDTO;
import br.com.centralit.citcorpore.bean.CotacaoItemRequisicaoDTO;
import br.com.centralit.citcorpore.bean.EmpregadoDTO;
import br.com.centralit.citcorpore.bean.EntregaItemRequisicaoDTO;
import br.com.centralit.citcorpore.bean.ExecucaoSolicitacaoDTO;
import br.com.centralit.citcorpore.bean.ItemRequisicaoProdutoDTO;
import br.com.centralit.citcorpore.bean.ParametroDTO;
import br.com.centralit.citcorpore.bean.ParecerDTO;
import br.com.centralit.citcorpore.bean.RequisicaoProdutoDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.integracao.CentroResultadoDao;
import br.com.centralit.citcorpore.integracao.CotacaoDao;
import br.com.centralit.citcorpore.integracao.CotacaoItemRequisicaoDao;
import br.com.centralit.citcorpore.integracao.EntregaItemRequisicaoDao;
import br.com.centralit.citcorpore.integracao.ExecucaoSolicitacaoDao;
import br.com.centralit.citcorpore.integracao.ItemCotacaoDao;
import br.com.centralit.citcorpore.integracao.ItemRequisicaoProdutoDao;
import br.com.centralit.citcorpore.integracao.ParametroDao;
import br.com.centralit.citcorpore.integracao.ParecerDao;
import br.com.centralit.citcorpore.integracao.RequisicaoProdutoDao;
import br.com.centralit.citcorpore.integracao.UsuarioDao;
import br.com.centralit.citcorpore.negocio.EntregaItemRequisicaoServiceEjb;
import br.com.centralit.citcorpore.negocio.ItemRequisicaoProdutoServiceEjb;
import br.com.centralit.citcorpore.negocio.alcada.AlcadaCompras;
import br.com.centralit.citcorpore.util.Enumerados.AcaoItemRequisicaoProduto;
import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoCotacaoItemRequisicao;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoEntregaItemRequisicao;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoItemRequisicaoProduto;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoSolicitacaoServico;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.util.Reflexao;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilFormatacao;
import br.com.citframework.util.UtilStrings;

@SuppressWarnings("unchecked")
public class ExecucaoRequisicaoProduto extends ExecucaoSolicitacao {

    public ExecucaoRequisicaoProduto() {
        super();
    }

    @Override
    public void validaEncerramento() throws Exception {
        final SolicitacaoServicoDTO solicitacaoServicoDto = this.getSolicitacaoServicoDto();
        if (solicitacaoServicoDto == null) {
            throw new Exception("Solicitação de serviço não encontrada");
        }

        final ItemRequisicaoProdutoDao itemRequisicaoProdutoDao = new ItemRequisicaoProdutoDao();
        this.setTransacaoDao(itemRequisicaoProdutoDao);

        final Collection<ItemRequisicaoProdutoDTO> colItens = itemRequisicaoProdutoDao.findByIdSolicitacaoServico(this.getSolicitacaoServicoDto()
                .getIdSolicitacaoServico());
        if (colItens == null || colItens.isEmpty()) {
            return;
        }

        int qtdeItens = 0;
        boolean bEncerramentoPermitido = true;
        final ItemCotacaoDao itemCotacaoDao = new ItemCotacaoDao();
        final CotacaoDao cotacaoDao = new CotacaoDao();
        this.setTransacaoDao(itemCotacaoDao);
        this.setTransacaoDao(cotacaoDao);

        final EntregaItemRequisicaoDao entregaItemDao = new EntregaItemRequisicaoDao();
        this.setTransacaoDao(entregaItemDao);
        for (final ItemRequisicaoProdutoDTO itemDto : colItens) {
            if (!itemDto.getTipoAtendimento().equalsIgnoreCase("C")) {
                continue;
            }

            if (itemDto.getIdItemCotacao() == null) {
                continue;
            }

            qtdeItens++;
            if (solicitacaoServicoDto.getSituacao().equalsIgnoreCase(SituacaoSolicitacaoServico.Cancelada.name())) {
                bEncerramentoPermitido = false;
                break;
            }

            final Collection<EntregaItemRequisicaoDTO> colEntregas = entregaItemDao.findByIdItemRequisicaoProduto(itemDto.getIdItemRequisicaoProduto());
            if (colEntregas == null || colEntregas.isEmpty()) {
                bEncerramentoPermitido = false;
                break;
            }

            double qtde = 0;
            for (final EntregaItemRequisicaoDTO entregaDto : colEntregas) {
                if (!entregaDto.getSituacao().equals(SituacaoEntregaItemRequisicao.Aprovada.name())
                        && !entregaDto.getSituacao().equals(SituacaoEntregaItemRequisicao.AprovadaPrazo.name())) {
                    bEncerramentoPermitido = false;
                    break;
                }
                qtde += entregaDto.getQuantidadeEntregue().doubleValue();
            }
            if (!bEncerramentoPermitido) {
                break;
            }

            if (itemDto.getQtdeAprovada().doubleValue() != qtde) {
                bEncerramentoPermitido = false;
                break;
            }
        }

        if (bEncerramentoPermitido && solicitacaoServicoDto.atendida() && qtdeItens > 0) {
            bEncerramentoPermitido = false;
        }

        if (!bEncerramentoPermitido) {
            throw new LogicException("Encerramento da requisição " + this.getSolicitacaoServicoDto().getIdSolicitacaoServico()
                    + " não permitido. Existe pelo menos um item em processo de cotação.");
        }

        final ItemRequisicaoProdutoServiceEjb itemRequisicaoService = new ItemRequisicaoProdutoServiceEjb();
        for (final ItemRequisicaoProdutoDTO itemDto : colItens) {
            if (itemDto.getSituacao().equals(SituacaoItemRequisicaoProduto.Cancelado.name())
                    || itemDto.getSituacao().equals(SituacaoItemRequisicaoProduto.Finalizado.name())) {
                continue;
            }
            itemDto.setSituacao(SituacaoItemRequisicaoProduto.Cancelado.name());
            itemRequisicaoProdutoDao.updateNotNull(itemDto);
            itemRequisicaoService.geraHistorico(this.getTransacao(), null, itemDto, AcaoItemRequisicaoProduto.Cancelamento, null,
                    SituacaoItemRequisicaoProduto.Cancelado);
        }
    }

    public void associaItemTrabalhoAprovacao(final Tarefa tarefa) throws Exception {
        final CotacaoItemRequisicaoDao cotacaoItemRequisicaoDao = new CotacaoItemRequisicaoDao();
        this.setTransacaoDao(cotacaoItemRequisicaoDao);
        final Collection<CotacaoItemRequisicaoDTO> colItens = cotacaoItemRequisicaoDao.findDisponiveisAprovacaoByIdSolicitacaoServico(this
                .getSolicitacaoServicoDto().getIdSolicitacaoServico());
        if (colItens != null) {
            for (final CotacaoItemRequisicaoDTO cotacaoItemRequisicaoDto : colItens) {
                cotacaoItemRequisicaoDto.setIdItemTrabalho(tarefa.getIdItemTrabalho());
                cotacaoItemRequisicaoDao.update(cotacaoItemRequisicaoDto);
            }
        }
    }

    public void associaItemTrabalhoInspecao(final Tarefa tarefa) throws Exception {
        final EntregaItemRequisicaoDao entregaItemRequisicaoDao = new EntregaItemRequisicaoDao();
        this.setTransacaoDao(entregaItemRequisicaoDao);
        final Collection<EntregaItemRequisicaoDTO> colItens = entregaItemRequisicaoDao.findDisponiveisInspecaoByIdSolicitacaoServico(this
                .getSolicitacaoServicoDto().getIdSolicitacaoServico());
        if (colItens != null) {
            for (final EntregaItemRequisicaoDTO inspecaoDto : colItens) {
                inspecaoDto.setIdItemTrabalho(tarefa.getIdItemTrabalho());
                entregaItemRequisicaoDao.update(inspecaoDto);
            }
        }
    }

    public void associaItemTrabalhoGarantia(final Tarefa tarefa) throws Exception {
        final EntregaItemRequisicaoDao entregaItemRequisicaoDao = new EntregaItemRequisicaoDao();
        this.setTransacaoDao(entregaItemRequisicaoDao);
        final Collection<EntregaItemRequisicaoDTO> colItens = entregaItemRequisicaoDao.findNaoAprovadasEDisponiveisByIdSolicitacaoServico(this
                .getSolicitacaoServicoDto().getIdSolicitacaoServico());
        if (colItens != null) {
            for (final EntregaItemRequisicaoDTO inspecaoDto : colItens) {
                inspecaoDto.setIdItemTrabalho(tarefa.getIdItemTrabalho());
                entregaItemRequisicaoDao.update(inspecaoDto);
            }
        }
    }

    public ExecucaoRequisicaoProduto(final RequisicaoProdutoDTO requisicaoProdutoDto, final TransactionControler tc) {
        super(requisicaoProdutoDto, tc);
    }

    public ExecucaoRequisicaoProduto(final TransactionControler tc) {
        super(tc);
    }

    public boolean existeAprovacaoPendente() throws Exception {
        final CotacaoItemRequisicaoDao cotacaoItemRequisicaoDao = new CotacaoItemRequisicaoDao();
        this.setTransacaoDao(cotacaoItemRequisicaoDao);
        final Collection<CotacaoItemRequisicaoDTO> colItens = cotacaoItemRequisicaoDao.findDisponiveisAprovacaoByIdSolicitacaoServico(this
                .getSolicitacaoServicoDto().getIdSolicitacaoServico());
        return colItens != null && colItens.size() > 0;
    }

    public boolean existeEntregaPendenteInspecao() throws Exception {
        final EntregaItemRequisicaoDao entregaItemRequisicaoDao = new EntregaItemRequisicaoDao();
        this.setTransacaoDao(entregaItemRequisicaoDao);
        final Collection<EntregaItemRequisicaoDTO> colItens = entregaItemRequisicaoDao.findDisponiveisInspecaoByIdSolicitacaoServico(this
                .getSolicitacaoServicoDto().getIdSolicitacaoServico());
        return colItens != null && colItens.size() > 0;
    }

    public boolean existeEntregaNaoAprovada() throws Exception {
        final EntregaItemRequisicaoDao entregaItemRequisicaoDao = new EntregaItemRequisicaoDao();
        this.setTransacaoDao(entregaItemRequisicaoDao);
        final Collection<EntregaItemRequisicaoDTO> colItens = entregaItemRequisicaoDao.findNaoAprovadasEDisponiveisByIdSolicitacaoServico(this
                .getSolicitacaoServicoDto().getIdSolicitacaoServico());
        return colItens != null && colItens.size() > 0;
    }

    public boolean existeItemPendenteValidacao() throws Exception {
        final ItemRequisicaoProdutoDao itemRequisicaoProdutoDao = new ItemRequisicaoProdutoDao();
        this.setTransacaoDao(itemRequisicaoProdutoDao);

        final Collection<ItemRequisicaoProdutoDTO> colItens = itemRequisicaoProdutoDao.findByIdSolicitacaoServico(this.getSolicitacaoServicoDto()
                .getIdSolicitacaoServico());
        if (colItens == null || colItens.isEmpty()) {
            return false;
        }

        for (final ItemRequisicaoProdutoDTO itemDto : colItens) {
            if (itemDto.getSituacao().equals(SituacaoItemRequisicaoProduto.AguardandoValidacao.name())) {
                return true;
            }
        }
        return false;
    }

    public boolean entregaFinalizada() throws Exception {
        final ItemRequisicaoProdutoDao itemRequisicaoProdutoDao = new ItemRequisicaoProdutoDao();
        this.setTransacaoDao(itemRequisicaoProdutoDao);

        final Collection<ItemRequisicaoProdutoDTO> colItens = itemRequisicaoProdutoDao.findByIdSolicitacaoServico(this.getSolicitacaoServicoDto()
                .getIdSolicitacaoServico());
        if (colItens == null || colItens.isEmpty()) {
            return true;
        }

        for (final ItemRequisicaoProdutoDTO itemDto : colItens) {
            if (!itemDto.getTipoAtendimento().equalsIgnoreCase("C")) {
                continue;
            }
            if (itemDto.getSituacao().equals(SituacaoItemRequisicaoProduto.Inviabilizado.name())) {
                continue;
            }
            if (itemDto.getSituacao().equals(SituacaoItemRequisicaoProduto.Cancelado.name())) {
                continue;
            }
            if (!itemDto.getSituacao().equals(SituacaoItemRequisicaoProduto.Finalizado.name())) {
                // System.out.println("####### " +getSolicitacaoServicoDto().getIdSolicitacaoServico());
                return false;
            }
        }

        final ExecucaoSolicitacaoDao execucaoSolicitacaoDao = new ExecucaoSolicitacaoDao();
        this.setTransacaoDao(execucaoSolicitacaoDao);
        final ExecucaoSolicitacaoDTO execucaoSolicitacaoDto = execucaoSolicitacaoDao.findBySolicitacaoServico(this.getSolicitacaoServicoDto());
        if (execucaoSolicitacaoDto == null) {
            return false;
        }

        final InstanciaFluxo instanciaFluxo = new InstanciaFluxo(this, execucaoSolicitacaoDto.getIdInstanciaFluxo());

        final AtribuicaoFluxoDao atribuicaoFluxoDao = new AtribuicaoFluxoDao();
        this.setTransacaoDao(atribuicaoFluxoDao);
        final Collection<AtribuicaoFluxoDTO> colAtribuicao = atribuicaoFluxoDao.findByDisponiveisByIdInstancia(execucaoSolicitacaoDto.getIdInstanciaFluxo());
        if (colAtribuicao != null) {
            for (final AtribuicaoFluxoDTO atribuicaoFluxoDto : colAtribuicao) {
                final ItemTrabalho itemTrabalho = ItemTrabalho.getItemTrabalho(instanciaFluxo, atribuicaoFluxoDto.getIdItemTrabalho());
                itemTrabalho.cancela(null);
            }
        }

        final EventoFluxoDao eventoFluxoDao = new EventoFluxoDao();
        this.setTransacaoDao(eventoFluxoDao);
        final Collection<EventoFluxoDTO> colEventos = eventoFluxoDao.findDisponiveis(execucaoSolicitacaoDto.getIdInstanciaFluxo());
        if (colEventos != null) {
            for (final EventoFluxoDTO eventoFluxoDto : colEventos) {
                final ItemTrabalho itemTrabalho = ItemTrabalho.getItemTrabalho(instanciaFluxo, eventoFluxoDto.getIdItemTrabalho());
                itemTrabalho.encerra();
            }
        }

        return true;
    }

    public void verificaExpiracao() throws Exception {
        final Date dataAtual = UtilDatas.getDataAtual();
        final ParametroDao parametroDao = new ParametroDao();
        this.setTransacaoDao(parametroDao);

        ParametroDTO parametroDto = parametroDao.getValue("COMPRAS", "TRATA_EXPIRACAO", new Integer(1));
        if (parametroDto == null || parametroDto.getValor() == null && !parametroDto.getValor().equalsIgnoreCase("S")) {
            return;
        }

        if (this.requisicaoRejeitada()) {
            int prazoValidacao = 15;

            parametroDto = parametroDao.getValue("COMPRAS", "PRAZO_EXPIRACAO_VALIDACAO", new Integer(1));
            if (parametroDto != null && parametroDto.getValor() != null && !UtilStrings.apenasNumeros(parametroDto.getValor()).equals("")) {
                prazoValidacao = new Integer(parametroDto.getValor()).intValue();
            }

            final ExecucaoSolicitacaoDao execucaoSolicitacaoDao = new ExecucaoSolicitacaoDao();
            this.setTransacaoDao(execucaoSolicitacaoDao);
            final ExecucaoSolicitacaoDTO execucaoSolicitacaoDto = execucaoSolicitacaoDao.findBySolicitacaoServico(this.getSolicitacaoServicoDto());
            if (execucaoSolicitacaoDto == null) {
                return;
            }

            final InstanciaFluxo instanciaFluxo = new InstanciaFluxo(this, execucaoSolicitacaoDto.getIdInstanciaFluxo());

            ItemTrabalho itemTrabalho = null;
            final AtribuicaoFluxoDao atribuicaoFluxoDao = new AtribuicaoFluxoDao();
            this.setTransacaoDao(atribuicaoFluxoDao);
            final Collection<AtribuicaoFluxoDTO> colAtribuicao = atribuicaoFluxoDao
                    .findByDisponiveisByIdInstancia(execucaoSolicitacaoDto.getIdInstanciaFluxo());
            if (colAtribuicao != null) {
                for (final AtribuicaoFluxoDTO atribuicaoFluxoDto : colAtribuicao) {
                    if (!atribuicaoFluxoDto.getTipo().equals(TipoAtribuicao.Automatica.name())) {
                        continue;
                    }
                    final ItemTrabalho itemTrabalhoAux = ItemTrabalho.getItemTrabalho(instanciaFluxo, atribuicaoFluxoDto.getIdItemTrabalho());
                    if (itemTrabalhoAux != null && itemTrabalhoAux instanceof Tarefa
                            && itemTrabalhoAux.getElementoFluxoDto().getNome().toUpperCase().indexOf("COMPLEMENT") >= 0) {
                        itemTrabalho = itemTrabalhoAux;
                        break;
                    }
                }
            }

            if (itemTrabalho == null) {
                return;
            }

            if (itemTrabalho.getItemTrabalhoDto().getDataHoraCriacao() != null
                    && UtilDatas.getDiasEntreDatas(dataAtual, itemTrabalho.getItemTrabalhoDto().getDataHoraCriacao()) > prazoValidacao) {
                final ItemRequisicaoProdutoDao itemRequisicaoProdutoDao = new ItemRequisicaoProdutoDao();
                this.setTransacaoDao(itemRequisicaoProdutoDao);

                boolean bCancelaTrabalho = true;
                final Collection<ItemRequisicaoProdutoDTO> colItens = itemRequisicaoProdutoDao.findByIdSolicitacaoServico(this.getSolicitacaoServicoDto()
                        .getIdSolicitacaoServico());
                if (colItens != null) {
                    final ItemRequisicaoProdutoServiceEjb itemRequisicaoService = new ItemRequisicaoProdutoServiceEjb();
                    for (final ItemRequisicaoProdutoDTO itemRequisicaoDto : colItens) {
                        if (itemRequisicaoDto.getIdItemCotacao() != null) {
                            bCancelaTrabalho = false;
                            break;
                        }
                    }
                    if (bCancelaTrabalho) {
                        for (final ItemRequisicaoProdutoDTO itemRequisicaoDto : colItens) {
                            itemRequisicaoDto.setSituacao(SituacaoItemRequisicaoProduto.Cancelado.name());
                            itemRequisicaoProdutoDao.update(itemRequisicaoDto);
                            itemRequisicaoService.geraHistorico(this.getTransacao(), null, itemRequisicaoDto, AcaoItemRequisicaoProduto.Alteracao,
                                    "Cancelamento automático por decurso de prazo", SituacaoItemRequisicaoProduto.valueOf(itemRequisicaoDto.getSituacao()));
                        }
                    }
                }
                if (bCancelaTrabalho) {
                    final TarefaFluxoDTO tarefaDto = this.recuperaTarefa(itemTrabalho.getIdItemTrabalho());
                    this.cancelaTarefa(null, this.getSolicitacaoServicoDto(), tarefaDto, "Cancelamento automático por decurso de prazo");
                    this.getSolicitacaoServicoDto().setNomeTarefa(tarefaDto.getElementoFluxoDto().getNome());
                    final String destinatarios[] = new String[] {this.getSolicitacaoServicoDto().getEmailcontato()};
                    this.enviaEmail("ReqProdTarefaCanc", destinatarios);
                }
            }
        } else {
            final EntregaItemRequisicaoDao entregaItemRequisicaoDao = new EntregaItemRequisicaoDao();
            this.setTransacaoDao(entregaItemRequisicaoDao);
            final Collection<EntregaItemRequisicaoDTO> colItens = entregaItemRequisicaoDao.findInspecaoByIdSolicitacaoServico(this.getSolicitacaoServicoDto()
                    .getIdSolicitacaoServico());
            if (colItens != null && colItens.size() > 0) {
                final EntregaItemRequisicaoServiceEjb entregaService = new EntregaItemRequisicaoServiceEjb();
                int prazoInspecao = 7;
                parametroDto = parametroDao.getValue("COMPRAS", "PRAZO_EXPIRACAO_INSPECAO", new Integer(1));
                if (parametroDto != null && parametroDto.getValor() != null && !UtilStrings.apenasNumeros(parametroDto.getValor()).equals("")) {
                    prazoInspecao = new Integer(parametroDto.getValor()).intValue();
                }

                int expirados = 0;
                final HashMap<String, TarefaFluxoDTO> mapItensTrabaho = new HashMap<String, TarefaFluxoDTO>();
                for (final EntregaItemRequisicaoDTO entregaItemDto : colItens) {
                    if (entregaItemDto.getIdItemTrabalho() == null) {
                        continue;
                    }

                    TarefaFluxoDTO tarefaDto = mapItensTrabaho.get("" + entregaItemDto.getIdItemTrabalho());
                    if (tarefaDto == null) {
                        tarefaDto = this.recuperaTarefa(entregaItemDto.getIdItemTrabalho());
                        if (tarefaDto == null) {
                            continue;
                        }
                        mapItensTrabaho.put("" + entregaItemDto.getIdItemTrabalho(), tarefaDto);
                    }

                    if (UtilDatas.getDiasEntreDatas(dataAtual, tarefaDto.getDataHoraCriacao()) > prazoInspecao) {
                        expirados++;
                        entregaService.finalizaDecursoPrazo(entregaItemDto, this.getTransacao());
                    }
                }
                if (expirados == 0) {
                    return;
                }
                for (final TarefaFluxoDTO tarefaDto : mapItensTrabaho.values()) {
                    this.cancelaTarefa(null, this.getSolicitacaoServicoDto(), tarefaDto, "Cancelamento automático por decurso de prazo");
                    this.getSolicitacaoServicoDto().setNomeTarefa(tarefaDto.getElementoFluxoDto().getNome());
                    final String destinatarios[] = new String[] {this.getSolicitacaoServicoDto().getEmailcontato()};
                    this.enviaEmail("ReqProdTarefaCanc", destinatarios);
                }
            }
        }
    }

    public boolean requisicaoRejeitada() throws Exception {
        final RequisicaoProdutoDTO requisicaoDto = this.recuperaRequisicaoProduto();
        return requisicaoDto.getRejeitada() != null && requisicaoDto.getRejeitada().equalsIgnoreCase("S");
    }

    public boolean existeItemAlterado() throws Exception {
        final RequisicaoProdutoDTO requisicaoDto = this.recuperaRequisicaoProduto();
        return requisicaoDto.getItemAlterado() != null && requisicaoDto.getItemAlterado().equalsIgnoreCase("S");
    }

    public boolean exigeAutorizacao() throws Exception {
        final RequisicaoProdutoDTO requisicaoDto = this.recuperaRequisicaoProduto();
        return this.exigeAutorizacao(requisicaoDto);
    }

    public boolean exigeNovaAprovacao() throws Exception {
        final RequisicaoProdutoDTO requisicaoDto = this.recuperaRequisicaoProduto();
        return requisicaoDto.getExigeNovaAprovacao() != null && requisicaoDto.getExigeNovaAprovacao().equalsIgnoreCase("S");
    }

    public boolean exigeAutorizacao(final RequisicaoProdutoDTO requisicaoProdutoDto) throws Exception {
        final RequisicaoProdutoDTO requisicaoAuxDto = this.recuperaRequisicaoProduto();
        final AlcadaDTO alcadaDto = this.recuperaAlcada(requisicaoAuxDto);
        if (alcadaDto != null) {
            boolean result = false;
            if (alcadaDto.getColResponsaveis() != null) {
                result = true;
                for (final EmpregadoDTO empregadoDto : alcadaDto.getColResponsaveis()) {
                    if (this.getSolicitacaoServicoDto().getIdSolicitante().intValue() == empregadoDto.getIdEmpregado().intValue()) {
                        result = false;
                        break;
                    }
                }
            }
            return result;
        } return false;
    }

    public StringBuilder recuperaLoginAutorizadores() throws Exception {
        final RequisicaoProdutoDTO requisicaoDto = this.recuperaRequisicaoProduto();
        return this.recuperaLoginAutorizadores(requisicaoDto);
    }

    public StringBuilder recuperaLoginAutorizadores(final RequisicaoProdutoDTO requisicaoProdutoDto) throws Exception {
        final StringBuilder result = new StringBuilder();
        final AlcadaDTO alcadaDto = this.recuperaAlcada(requisicaoProdutoDto);
        if (alcadaDto != null && alcadaDto.getColResponsaveis() != null) {
            int i = 0;
            final UsuarioDao usuarioDao = new UsuarioDao();
            this.setTransacaoDao(usuarioDao);
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

    public AlcadaDTO recuperaAlcada(final RequisicaoProdutoDTO requisicaoProdutoDto) throws Exception {
        return new AlcadaCompras().determinaAlcada(requisicaoProdutoDto, this.recuperaCentroCusto(requisicaoProdutoDto), this.getTransacao());
    }

    public RequisicaoProdutoDTO recuperaRequisicaoProduto() throws Exception {
        final RequisicaoProdutoDao requisicaoDao = new RequisicaoProdutoDao();
        this.setTransacaoDao(requisicaoDao);
        final SolicitacaoServicoDTO solicitacaoDto = this.getSolicitacaoServicoDto();
        RequisicaoProdutoDTO requisicaoDto = new RequisicaoProdutoDTO();
        requisicaoDto.setIdSolicitacaoServico(solicitacaoDto.getIdSolicitacaoServico());
        requisicaoDto = (RequisicaoProdutoDTO) requisicaoDao.restore(requisicaoDto);
        Reflexao.copyPropertyValues(solicitacaoDto, requisicaoDto);
        return requisicaoDto;
    }

    public CentroResultadoDTO recuperaCentroCusto(final RequisicaoProdutoDTO requisicaoProdutoDto) throws Exception {
        final CentroResultadoDTO centroCustoDto = new CentroResultadoDTO();
        centroCustoDto.setIdCentroResultado(requisicaoProdutoDto.getIdCentroCusto());
        final CentroResultadoDao centroResultadoDao = new CentroResultadoDao();
        this.setTransacaoDao(centroResultadoDao);
        return (CentroResultadoDTO) centroResultadoDao.restore(centroCustoDto);
    }

    @Override
    public InstanciaFluxo inicia() throws Exception {
        return super.inicia();
    }

    @Override
    public InstanciaFluxo inicia(final FluxoDTO fluxoDto, final Integer idFase) throws Exception {
        final String idGrupo = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.ID_GRUPO_PADRAO_REQ_PRODUTOS, null);
        if (idGrupo == null || idGrupo.trim().equals("")) {
            throw new Exception("Grupo padrão de requisição de produtos não parametrizado");
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

        final StringBuilder strItens = new StringBuilder();
        final RequisicaoProdutoDTO requisicaoDto = this.recuperaRequisicaoProduto();
        if (requisicaoDto.getFinalidade().equals("C")) {
            strItens.append("<b>Finalidade: </b>Atendimento ao cliente<br>");
        } else {
            strItens.append("<b>Finalidade: </b>Uso interno<br>");
        }
        strItens.append("<b>Centro de custo: </b>" + requisicaoDto.getCentroCusto() + "<br>");
        strItens.append("<b>Projeto: </b>" + requisicaoDto.getProjeto() + "<br>");
        final ItemRequisicaoProdutoDao itemRequisicaoProdutoDao = new ItemRequisicaoProdutoDao();
        this.setTransacaoDao(itemRequisicaoProdutoDao);
        final Collection<ItemRequisicaoProdutoDTO> colItens = itemRequisicaoProdutoDao.findByIdSolicitacaoServico(solicitacaoServicoDto
                .getIdSolicitacaoServico());
        if (colItens != null) {
            strItens.append("<br>");
            strItens.append("<table width='100%'>");
            strItens.append("   <tr>");
            strItens.append("       <th><b>ITENS</b></th>");
            strItens.append("   </tr>");
            strItens.append("</table>");
            strItens.append("<table width='100%' border='1' style='padding: 5px 5px 5px 5px'>");
            strItens.append("   <tr>");
            strItens.append("       <th width='30%'><b>Descrição</b></th>");
            strItens.append("       <th><b>Especificações</b></th>");
            strItens.append("       <th><b>Qtde</b></th>");
            strItens.append("       <th width='15%'><b>Situação atual</b></th>");
            strItens.append("   </tr>");
            for (final ItemRequisicaoProdutoDTO itemDto : colItens) {
                strItens.append("   <tr>");
                strItens.append("       <td>" + itemDto.getDescricaoItem() + "</td>");
                strItens.append("       <td>" + UtilStrings.nullToVazio(itemDto.getEspecificacoes()) + "</td>");
                strItens.append("       <td>" + UtilFormatacao.formatDouble(itemDto.getQtdeAprovada(), 2) + "</td>");
                strItens.append("       <td>" + itemDto.getDescrSituacao() + "</td>");
                strItens.append("   </tr>");
            }
            strItens.append("</table>");
        }
        solicitacaoServicoDto.setInformacoesComplementaresHTML(strItens.toString());
    }

    @Override
    public boolean permiteAprovacaoAlcada(final AlcadaProcessoNegocioDTO alcadaProcessoNegocioDto, final SolicitacaoServicoDTO solicitacaoServicoDto)
            throws Exception {
        if (alcadaProcessoNegocioDto.getEmpregadoDto().getIdEmpregado().intValue() == solicitacaoServicoDto.getIdSolicitante().intValue()) {
            alcadaProcessoNegocioDto.setComplementoRejeicao("Aprovador não pode ser o solicitante");
            return false;
        }

        final String idGrupoCompras = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.ID_GRUPO_PADRAO_REQ_PRODUTOS, null);
        if (idGrupoCompras == null || idGrupoCompras.trim().equals("")) {
            throw new Exception("Grupo padrão de requisição de produtos não parametrizado");
        }

        if (alcadaProcessoNegocioDto.getMapGruposEmpregado().get(idGrupoCompras.trim()) != null) {
            alcadaProcessoNegocioDto.setComplementoRejeicao("Aprovador não pode pertencer ao grupo responsável por compras");
            return false;
        }

        return true;
    }

    @Override
    public void calculaValorAprovadoAnual(final CentroResultadoDTO centroResultadoDto, final int anoRef, final TransactionControler tc) throws Exception {
        if (tc != null) {
            this.setTransacao(tc);
        }
        valorAnualUsoInterno = 0.0;
        valorAnualAtendCliente = 0.0;
        final CotacaoItemRequisicaoDao cotacaoItemRequisicaoDao = new CotacaoItemRequisicaoDao();
        this.setTransacaoDao(cotacaoItemRequisicaoDao);

        final Collection<CotacaoItemRequisicaoDTO> colItens = cotacaoItemRequisicaoDao.findAprovadasByIdCentroResultado(centroResultadoDto
                .getIdCentroResultado());
        if (colItens != null) {
            final ParecerDao parecerDao = new ParecerDao();
            this.setTransacaoDao(parecerDao);

            final ItemTrabalhoFluxoDao itemTrabalhoFluxoDao = new ItemTrabalhoFluxoDao();
            this.setTransacaoDao(itemTrabalhoFluxoDao);

            for (final CotacaoItemRequisicaoDTO cotacaoItemRequisicaoDto : colItens) {
                double valor = 0;
                if (cotacaoItemRequisicaoDto.getValorTotal() == null) {
                    continue;
                }

                if (cotacaoItemRequisicaoDto.getSituacao().equals(SituacaoCotacaoItemRequisicao.Aprovado.name())
                        && cotacaoItemRequisicaoDto.getIdItemTrabalho() != null) {
                    ItemTrabalhoFluxoDTO itemTrabalhoDto = new ItemTrabalhoFluxoDTO();
                    itemTrabalhoDto.setIdItemTrabalho(cotacaoItemRequisicaoDto.getIdItemTrabalho());
                    itemTrabalhoDto = (ItemTrabalhoFluxoDTO) itemTrabalhoFluxoDao.restore(itemTrabalhoDto);
                    if (itemTrabalhoDto != null && itemTrabalhoDto.getDataHoraFinalizacao() != null) {
                        final Date dataAux = new Date(itemTrabalhoDto.getDataHoraFinalizacao().getTime());
                        final int ano = UtilDatas.getYear(dataAux);
                        if (ano == anoRef) {
                            valor += cotacaoItemRequisicaoDto.getValorTotal().doubleValue();
                        }
                    }
                } else if (cotacaoItemRequisicaoDto.getIdParecerAutorizacao() != null) {
                    ParecerDTO parecerDto = new ParecerDTO();
                    parecerDto.setIdParecer(cotacaoItemRequisicaoDto.getIdParecerAutorizacao());
                    parecerDto = (ParecerDTO) parecerDao.restore(parecerDto);
                    if (parecerDto != null) {
                        final Date dataAux = new Date(parecerDto.getDataHoraParecer().getTime());
                        final int ano = UtilDatas.getYear(dataAux);
                        if (ano == anoRef) {
                            valor += cotacaoItemRequisicaoDto.getValorTotal().doubleValue();
                        }
                    }
                }
                if (valor > 0) {
                    if (cotacaoItemRequisicaoDto.getFinalidade().equalsIgnoreCase("I")) {
                        valorAnualUsoInterno += valor;
                    } else {
                        valorAnualAtendCliente += valor;
                    }
                }
            }
        }
    }

    @Override
    public void calculaValorAprovadoMensal(final CentroResultadoDTO centroResultadoDto, final int mesRef, final int anoRef, final TransactionControler tc)
            throws Exception {
        if (tc != null) {
            this.setTransacao(tc);
        }
        valorMensalUsoInterno = 0.0;
        valorMensalAtendCliente = 0.0;

        final CotacaoItemRequisicaoDao cotacaoItemRequisicaoDao = new CotacaoItemRequisicaoDao();
        this.setTransacaoDao(cotacaoItemRequisicaoDao);

        final Collection<CotacaoItemRequisicaoDTO> colItens = cotacaoItemRequisicaoDao.findAprovadasByIdCentroResultado(centroResultadoDto
                .getIdCentroResultado());
        if (colItens != null) {
            final ParecerDao parecerDao = new ParecerDao();
            this.setTransacaoDao(parecerDao);

            final ItemTrabalhoFluxoDao itemTrabalhoFluxoDao = new ItemTrabalhoFluxoDao();
            this.setTransacaoDao(itemTrabalhoFluxoDao);

            for (final CotacaoItemRequisicaoDTO cotacaoItemRequisicaoDto : colItens) {
                double valor = 0;
                if (cotacaoItemRequisicaoDto.getValorTotal() == null) {
                    continue;
                }

                if (cotacaoItemRequisicaoDto.getSituacao().equals(SituacaoCotacaoItemRequisicao.Aprovado.name())
                        && cotacaoItemRequisicaoDto.getIdItemTrabalho() != null) {
                    ItemTrabalhoFluxoDTO itemTrabalhoDto = new ItemTrabalhoFluxoDTO();
                    itemTrabalhoDto.setIdItemTrabalho(cotacaoItemRequisicaoDto.getIdItemTrabalho());
                    itemTrabalhoDto = (ItemTrabalhoFluxoDTO) itemTrabalhoFluxoDao.restore(itemTrabalhoDto);
                    if (itemTrabalhoDto != null && itemTrabalhoDto.getDataHoraFinalizacao() != null) {
                        final Date dataAux = new Date(itemTrabalhoDto.getDataHoraFinalizacao().getTime());
                        final int mes = UtilDatas.getMonth(dataAux);
                        final int ano = UtilDatas.getYear(dataAux);
                        if (mes == mesRef && ano == anoRef) {
                            valor += cotacaoItemRequisicaoDto.getValorTotal().doubleValue();
                        }
                    }
                } else if (cotacaoItemRequisicaoDto.getIdParecerAutorizacao() != null) {
                    ParecerDTO parecerDto = new ParecerDTO();
                    parecerDto.setIdParecer(cotacaoItemRequisicaoDto.getIdParecerAutorizacao());
                    parecerDto = (ParecerDTO) parecerDao.restore(parecerDto);
                    if (parecerDto != null) {
                        final Date dataAux = new Date(parecerDto.getDataHoraParecer().getTime());
                        final int mes = UtilDatas.getMonth(dataAux);
                        final int ano = UtilDatas.getYear(dataAux);
                        if (mes == mesRef && ano == anoRef) {
                            valor += cotacaoItemRequisicaoDto.getValorTotal().doubleValue();
                        }
                    }
                }
                if (valor > 0) {
                    if (cotacaoItemRequisicaoDto.getFinalidade().equalsIgnoreCase("I")) {
                        valorMensalUsoInterno += valor;
                    } else {
                        valorMensalAtendCliente += valor;
                    }
                }
            }
        }
    }

    @Override
    public double calculaValorAprovado(final SolicitacaoServicoDTO solicitacaoServicoDto, final TransactionControler tc) throws Exception {
        double valor = 0.0;
        this.setObjetoNegocioDto(solicitacaoServicoDto);
        final CotacaoItemRequisicaoDao cotacaoItemRequisicaoDao = new CotacaoItemRequisicaoDao();
        this.setTransacaoDao(cotacaoItemRequisicaoDao);
        final ItemRequisicaoProdutoDao itemRequisicaoDao = new ItemRequisicaoProdutoDao();
        this.setTransacaoDao(itemRequisicaoDao);

        final Collection<CotacaoItemRequisicaoDTO> colItens = cotacaoItemRequisicaoDao.findByIdRequisicaoProduto(solicitacaoServicoDto
                .getIdSolicitacaoServico());
        if (colItens != null) {
            final ItemTrabalhoFluxoDao itemTrabalhoFluxoDao = new ItemTrabalhoFluxoDao();
            this.setTransacaoDao(itemTrabalhoFluxoDao);
            final ParecerDao parecerDao = new ParecerDao();
            this.setTransacaoDao(parecerDao);

            for (final CotacaoItemRequisicaoDTO cotacaoItemRequisicaoDto : colItens) {
                if (!cotacaoItemRequisicaoDto.getSituacao().equals(SituacaoCotacaoItemRequisicao.Aprovado.name())
                        && !cotacaoItemRequisicaoDto.getSituacao().equals(SituacaoCotacaoItemRequisicao.PreAprovado.name())) {
                    continue;
                }
                if (cotacaoItemRequisicaoDto.getValorTotal() == null) {
                    continue;
                }

                ItemRequisicaoProdutoDTO itemDto = new ItemRequisicaoProdutoDTO();
                itemDto.setIdItemRequisicaoProduto(cotacaoItemRequisicaoDto.getIdItemRequisicaoProduto());
                itemDto = (ItemRequisicaoProdutoDTO) itemRequisicaoDao.restore(itemDto);
                if (itemDto == null || itemDto.getSituacao() == null || itemDto.getSituacao().equalsIgnoreCase(SituacaoItemRequisicaoProduto.Cancelado.name())) {
                    continue;
                }

                if (cotacaoItemRequisicaoDto.getSituacao().equals(SituacaoCotacaoItemRequisicao.Aprovado.name())
                        && cotacaoItemRequisicaoDto.getIdItemTrabalho() != null) {
                    ItemTrabalhoFluxoDTO itemTrabalhoDto = new ItemTrabalhoFluxoDTO();
                    itemTrabalhoDto.setIdItemTrabalho(cotacaoItemRequisicaoDto.getIdItemTrabalho());
                    itemTrabalhoDto = (ItemTrabalhoFluxoDTO) itemTrabalhoFluxoDao.restore(itemTrabalhoDto);
                    valor += cotacaoItemRequisicaoDto.getValorTotal().doubleValue();
                } else {
                    if (itemDto.getIdParecerAutorizacao() != null) {
                        ParecerDTO parecerDto = new ParecerDTO();
                        parecerDto.setIdParecer(itemDto.getIdParecerAutorizacao());
                        parecerDto = (ParecerDTO) parecerDao.restore(parecerDto);
                        if (parecerDto != null) {
                            valor += cotacaoItemRequisicaoDto.getValorTotal().doubleValue();
                        }
                    }
                }
            }
        }
        return valor;
    }

    @Override
    public boolean isAtendimentoCliente(final SolicitacaoServicoDTO solicitacaoServicoDto) throws Exception {
        this.setObjetoNegocioDto(solicitacaoServicoDto);
        final RequisicaoProdutoDTO requisicaoProdutoDto = this.recuperaRequisicaoProduto();
        return requisicaoProdutoDto != null && requisicaoProdutoDto.getFinalidade() != null && requisicaoProdutoDto.getFinalidade().trim().equals("C");
    }

}
