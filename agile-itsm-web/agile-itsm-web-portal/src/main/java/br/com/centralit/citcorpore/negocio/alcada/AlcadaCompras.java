package br.com.centralit.citcorpore.negocio.alcada;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AlcadaCentroResultadoDTO;
import br.com.centralit.citcorpore.bean.AlcadaDTO;
import br.com.centralit.citcorpore.bean.CentroResultadoDTO;
import br.com.centralit.citcorpore.bean.EmpregadoDTO;
import br.com.centralit.citcorpore.bean.GrupoDTO;
import br.com.centralit.citcorpore.bean.GrupoEmpregadoDTO;
import br.com.centralit.citcorpore.bean.LimiteAlcadaDTO;
import br.com.centralit.citcorpore.bean.RequisicaoProdutoDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.centralit.citcorpore.integracao.AlcadaCentroResultadoDAO;
import br.com.centralit.citcorpore.integracao.AlcadaDao;
import br.com.centralit.citcorpore.integracao.CentroResultadoDao;
import br.com.centralit.citcorpore.integracao.GrupoDao;
import br.com.centralit.citcorpore.integracao.GrupoEmpregadoDao;
import br.com.centralit.citcorpore.integracao.LimiteAlcadaDao;
import br.com.centralit.citcorpore.negocio.RequisicaoProdutoServiceEjb;
import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;
import br.com.centralit.citcorpore.util.Enumerados.TipoAlcada;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.util.UtilDatas;

public class AlcadaCompras extends AlcadaImpl {

    private Integer idGrupoCompras = null;
    private GrupoEmpregadoDao grupoEmpregadoDao = null;

    @Override
    public AlcadaDTO determinaAlcada(final BaseEntity objetoNegocioDto, final CentroResultadoDTO centroCustoDto, final TransactionControler tc)
            throws Exception {
        final AlcadaDao alcadaDao = new AlcadaDao();
        this.setTransacaoDao(alcadaDao);

        this.objetoNegocioDto = objetoNegocioDto;
        alcadaDto = alcadaDao.findByTipo(TipoAlcada.Compras);
        if (alcadaDto == null) {
            throw new LogicException("Tipo de alçada 'Compras' não encontrada");
        }

        if (isNovaAlcada()) {
            alcadaDto.setColResponsaveis(AlcadaProcessoNegocio.getInstance().getResponsaveis((SolicitacaoServicoDTO) objetoNegocioDto, centroCustoDto, tc));
        } else {
            final LimiteAlcadaDao limiteAlcadaDao = new LimiteAlcadaDao();
            this.setTransacaoDao(limiteAlcadaDao);
            final Collection<LimiteAlcadaDTO> colLimites = limiteAlcadaDao.findByIdAlcada(alcadaDto.getIdAlcada());
            if (colLimites == null || colLimites.isEmpty()) {
                throw new LogicException("Não foram encontrados limites de valores para a alçada '" + alcadaDto.getNomeAlcada() + "'");
            }

            final String idGrupo = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.ID_GRUPO_PADRAO_REQ_PRODUTOS, null);
            if (idGrupo == null || idGrupo.trim().equals("")) {
                throw new Exception("Grupo padrão de requisição de produtos não parametrizado");
            }
            idGrupoCompras = new Integer(idGrupo.trim());

            final AlcadaCentroResultadoDAO alcadaCentroResultadoDao = new AlcadaCentroResultadoDAO();
            this.setTransacaoDao(alcadaCentroResultadoDao);
            centroCustoDto.setColAlcadas(alcadaCentroResultadoDao.findByIdCentroResultadoAndIdAlcada(centroCustoDto.getIdCentroResultado(),
                    alcadaDto.getIdAlcada()));

            final RequisicaoProdutoDTO requisicaoProdutoDto = (RequisicaoProdutoDTO) objetoNegocioDto;
            grupoEmpregadoDao = new GrupoEmpregadoDao();
            this.setTransacaoDao(grupoEmpregadoDao);
            final Collection<GrupoEmpregadoDTO> colGrupos = grupoEmpregadoDao.findAtivosByIdEmpregado(requisicaoProdutoDto.getIdSolicitante());
            final HashMap<String, GrupoEmpregadoDTO> mapGrupos = new HashMap<>();
            if (colGrupos != null) {
                for (final GrupoEmpregadoDTO grupoEmpregadoDto : colGrupos) {
                    mapGrupos.put("" + grupoEmpregadoDto.getIdGrupo(), grupoEmpregadoDto);
                }
            }

            final GrupoDao grupoDao = new GrupoDao();
            this.setTransacaoDao(grupoDao);
            alcadaDto.setColResponsaveis(new ArrayList<EmpregadoDTO>());

            solicitante = this.recuperaEmpregado(requisicaoProdutoDto.getIdSolicitante());

            for (final LimiteAlcadaDTO limiteAlcadaDto : colLimites) {
                if (!limiteAlcadaDto.getTipoLimite().equalsIgnoreCase("Q")) {
                    continue;
                }

                if (!limiteAlcadaDto.getAbrangenciaCentroCusto().equalsIgnoreCase("T")) {
                    continue;
                }

                if (mapGrupos.get("" + limiteAlcadaDto.getIdGrupo()) != null) {
                    final Collection<EmpregadoDTO> colResponsaveis = new ArrayList<>();
                    colResponsaveis.add(solicitante);
                    alcadaDto.setColResponsaveis(colResponsaveis);
                    return alcadaDto;
                }
            }

            final RequisicaoProdutoServiceEjb requisicaoProdutoService = new RequisicaoProdutoServiceEjb();
            final Double valorAprovado = requisicaoProdutoService.calculaValorAprovado(requisicaoProdutoDto, tc);

            final Date dataAux = new Date(requisicaoProdutoDto.getDataHoraSolicitacao().getTime());
            final int mes = UtilDatas.getMonth(dataAux);
            final int ano = UtilDatas.getYear(dataAux);
            final Double valorAprovadoMensal = requisicaoProdutoService.calculaValorAprovadoMensal(centroCustoDto, mes, ano, tc);

            for (final LimiteAlcadaDTO limiteAlcadaDto : colLimites) {
                if (!limiteAlcadaDto.getTipoLimite().equalsIgnoreCase("F")) {
                    continue;
                }

                if (requisicaoProdutoDto.getFinalidade().equalsIgnoreCase("I")) {
                    if (limiteAlcadaDto != null && limiteAlcadaDto.getLimiteItemUsoInterno() != null
                            && limiteAlcadaDto.getLimiteItemUsoInterno().doubleValue() < valorAprovado.doubleValue()) {
                        continue;
                    }
                    if (limiteAlcadaDto != null && limiteAlcadaDto.getLimiteMensalUsoInterno() != null
                            && limiteAlcadaDto.getLimiteMensalUsoInterno().doubleValue() < valorAprovadoMensal.doubleValue()) {
                        continue;
                    }
                } else {
                    if (limiteAlcadaDto != null && limiteAlcadaDto.getLimiteItemAtendCliente() != null
                            && limiteAlcadaDto.getLimiteItemAtendCliente().doubleValue() < valorAprovado.doubleValue()) {
                        continue;
                    }
                    if (limiteAlcadaDto != null && limiteAlcadaDto.getLimiteMensalAtendCliente() != null
                            && limiteAlcadaDto.getLimiteMensalAtendCliente().doubleValue() < valorAprovadoMensal.doubleValue()) {
                        continue;
                    }
                }

                GrupoDTO grupoDto = new GrupoDTO();
                grupoDto.setIdGrupo(limiteAlcadaDto.getIdGrupo());
                grupoDto = (GrupoDTO) grupoDao.restore(grupoDto);
                if (grupoDto == null) {
                    throw new LogicException("Grupo " + limiteAlcadaDto.getIdGrupo() + " não encontrado");
                }

                this.determinaResponsaveis(grupoDto, limiteAlcadaDto.getAbrangenciaCentroCusto(), centroCustoDto);
                // if (alcadaDto.getColResponsaveis().size() > 0)
                // break;
            }

            if (alcadaDto.getColResponsaveis().size() > 0) {
                return alcadaDto;
            }

            for (final LimiteAlcadaDTO limiteAlcadaDto : colLimites) {
                if (!limiteAlcadaDto.getTipoLimite().equalsIgnoreCase("Q")) {
                    continue;
                }

                GrupoDTO grupoDto = new GrupoDTO();
                grupoDto.setIdGrupo(limiteAlcadaDto.getIdGrupo());
                grupoDto = (GrupoDTO) grupoDao.restore(grupoDto);
                if (grupoDto == null) {
                    throw new LogicException("Grupo " + limiteAlcadaDto.getIdGrupo() + " não encontrado");
                }

                this.determinaResponsaveis(grupoDto, limiteAlcadaDto.getAbrangenciaCentroCusto(), centroCustoDto);
                if (alcadaDto.getColResponsaveis().size() > 0) {
                    break;
                }
            }
        }

        return alcadaDto;
    }

    @Override
    public void determinaResponsaveis(final GrupoDTO grupoDto, final String abrangenciaCentroCusto, final CentroResultadoDTO centroCustoDto) throws Exception {
        final Collection<GrupoEmpregadoDTO> colGrupoEmpregado = grupoEmpregadoDao.findByIdGrupo(grupoDto.getIdGrupo());
        if (colGrupoEmpregado == null || colGrupoEmpregado.isEmpty()) {
            return;
        }

        Collection<EmpregadoDTO> colResponsaveis = alcadaDto.getColResponsaveis();
        if (colResponsaveis == null) {
            colResponsaveis = new ArrayList<>();
            alcadaDto.setColResponsaveis(colResponsaveis);
        }
        final HashMap<String, EmpregadoDTO> mapResponsaveis = new HashMap<>();
        for (final EmpregadoDTO empregadoDto : colResponsaveis) {
            mapResponsaveis.put("" + empregadoDto.getIdEmpregado(), empregadoDto);
        }
        for (final GrupoEmpregadoDTO grupoEmpregadoDto : colGrupoEmpregado) {
            if (mapResponsaveis.get("" + grupoEmpregadoDto.getIdEmpregado()) != null) {
                continue;
            }

            if (abrangenciaCentroCusto.equalsIgnoreCase("R")) {
                if (centroCustoDto.getColAlcadas() != null) {
                    for (final AlcadaCentroResultadoDTO alcadaCentroResultadoDto : centroCustoDto.getColAlcadas()) {
                        if (alcadaCentroResultadoDto.getIdEmpregado() != null
                                && alcadaCentroResultadoDto.getIdEmpregado().intValue() == grupoEmpregadoDto.getIdEmpregado().intValue()) {
                            if (!this.permiteResponsavel(grupoEmpregadoDto.getIdEmpregado())) {
                                continue;
                            }
                            final EmpregadoDTO empregadoDto = this.recuperaEmpregado(grupoEmpregadoDto.getIdEmpregado());
                            if (empregadoDto != null) {
                                mapResponsaveis.put("" + empregadoDto.getIdEmpregado(), empregadoDto);
                                colResponsaveis.add(empregadoDto);
                            }
                        }
                    }
                }
            } else if (abrangenciaCentroCusto.equalsIgnoreCase("T")) {
                if (!this.permiteResponsavel(grupoEmpregadoDto.getIdEmpregado())) {
                    continue;
                }
                final EmpregadoDTO empregadoDto = this.recuperaEmpregado(grupoEmpregadoDto.getIdEmpregado());
                if (empregadoDto != null) {
                    mapResponsaveis.put("" + empregadoDto.getIdEmpregado(), empregadoDto);
                    colResponsaveis.add(empregadoDto);
                }
            }
        }
        if (colResponsaveis.size() == 0 && centroCustoDto.getIdCentroResultadoPai() != null) {
            CentroResultadoDTO ccustoPaiDto = new CentroResultadoDTO();
            ccustoPaiDto.setIdCentroResultado(centroCustoDto.getIdCentroResultadoPai());
            final CentroResultadoDao centroResultadoDao = new CentroResultadoDao();
            this.setTransacaoDao(centroResultadoDao);
            ccustoPaiDto = (CentroResultadoDTO) centroResultadoDao.restore(ccustoPaiDto);
            if (ccustoPaiDto != null) {
                this.determinaResponsaveis(grupoDto, abrangenciaCentroCusto, ccustoPaiDto);
            }
        }
    }

    @Override
    public boolean permiteResponsavel(final Integer idEmpregado) throws Exception {
        if (idEmpregado.intValue() == solicitante.getIdEmpregado().intValue()) {
            return false;
        }

        final Collection<GrupoEmpregadoDTO> colGrupos = grupoEmpregadoDao.findAtivosByIdEmpregado(idEmpregado);
        if (colGrupos != null) {
            for (final GrupoEmpregadoDTO grupoEmpregadoDto : colGrupos) {
                if (grupoEmpregadoDto.getIdGrupo().intValue() == idGrupoCompras) {
                    return false;
                }
            }
        }
        return true;
    }

}
