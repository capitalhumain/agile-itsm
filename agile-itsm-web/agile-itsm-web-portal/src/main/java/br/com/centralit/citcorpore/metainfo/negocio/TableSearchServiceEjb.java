package br.com.centralit.citcorpore.metainfo.negocio;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import br.com.centralit.citcorpore.bean.MatrizVisaoDTO;
import br.com.centralit.citcorpore.bean.ValorRecuperadoMatrizDTO;
import br.com.centralit.citcorpore.integracao.MatrizVisaoDao;
import br.com.centralit.citcorpore.metainfo.bean.CamposObjetoNegocioDTO;
import br.com.centralit.citcorpore.metainfo.bean.GrupoVisaoCamposNegocioDTO;
import br.com.centralit.citcorpore.metainfo.bean.GrupoVisaoDTO;
import br.com.centralit.citcorpore.metainfo.bean.LookupDTO;
import br.com.centralit.citcorpore.metainfo.bean.ObjetoNegocioDTO;
import br.com.centralit.citcorpore.metainfo.bean.ReturnLookupDTO;
import br.com.centralit.citcorpore.metainfo.bean.ScriptsVisaoDTO;
import br.com.centralit.citcorpore.metainfo.bean.TableSearchDTO;
import br.com.centralit.citcorpore.metainfo.bean.ValorVisaoCamposNegocioDTO;
import br.com.centralit.citcorpore.metainfo.bean.VinculoVisaoDTO;
import br.com.centralit.citcorpore.metainfo.bean.VisaoDTO;
import br.com.centralit.citcorpore.metainfo.bean.VisaoRelacionadaDTO;
import br.com.centralit.citcorpore.metainfo.integracao.CamposObjetoNegocioDao;
import br.com.centralit.citcorpore.metainfo.integracao.GrupoVisaoCamposNegocioDao;
import br.com.centralit.citcorpore.metainfo.integracao.GrupoVisaoDao;
import br.com.centralit.citcorpore.metainfo.integracao.LookupDao;
import br.com.centralit.citcorpore.metainfo.integracao.ObjetoNegocioDao;
import br.com.centralit.citcorpore.metainfo.integracao.ScriptsVisaoDao;
import br.com.centralit.citcorpore.metainfo.integracao.ValorVisaoCamposNegocioDao;
import br.com.centralit.citcorpore.metainfo.integracao.VinculoVisaoDao;
import br.com.centralit.citcorpore.metainfo.integracao.VisaoRelacionadaDao;
import br.com.centralit.citcorpore.metainfo.script.ScriptRhinoJSExecute;
import br.com.centralit.citcorpore.metainfo.util.MetaUtil;
import br.com.centralit.citcorpore.negocio.MatrizVisaoService;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.centralit.citcorpore.util.Enumerados.TipoDate;
import br.com.centralit.citcorpore.util.WebUtil;
import br.com.citframework.service.CrudServiceImpl;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilFormatacao;
import br.com.citframework.util.UtilI18N;
import br.com.citframework.util.UtilStrings;

/**
 * Classe Ejb responsável por tratar as consultas de DinamicViews.
 *
 */
public class TableSearchServiceEjb extends CrudServiceImpl implements TableSearchService {

    public static String strSGBDPrincipal = null;

    private LookupDao dao;

    @Override
    protected LookupDao getDao() {
        if (dao == null) {
            dao = new LookupDao();
        }
        return dao;
    }

    @Override
    public String findItens(final TableSearchDTO parm, final boolean tableVinc, final Map map, final HttpServletRequest request) throws Exception {
        final GrupoVisaoDao grupoVisaoDao = new GrupoVisaoDao();
        final GrupoVisaoCamposNegocioDao grupoVisaoCamposNegocioDao = new GrupoVisaoCamposNegocioDao();
        final CamposObjetoNegocioDao camposObjetoNegocioDao = new CamposObjetoNegocioDao();
        final LookupServiceEjb lookupService = new LookupServiceEjb();
        final ScriptsVisaoDao scriptsVisaoDao = new ScriptsVisaoDao();

        final Collection colScripts = scriptsVisaoDao.findByIdVisao(parm.getIdVisao());
        final HashMap mapScritps = new HashMap<>();
        if (colScripts != null) {
            for (final Iterator it = colScripts.iterator(); it.hasNext();) {
                final ScriptsVisaoDTO scriptsVisaoDTO = (ScriptsVisaoDTO) it.next();
                mapScritps.put(scriptsVisaoDTO.getTypeExecute() + "#" + scriptsVisaoDTO.getScryptType().trim(), scriptsVisaoDTO.getScript());
            }
        }

        final Collection colPresentation = new ArrayList<>();
        final Collection colCamposApresNeg = new ArrayList<>();
        // Collection colValue = new ArrayList<>();
        final Collection colFilter = new ArrayList<>();
        final Collection colOrder = new ArrayList<>();
        final Collection colGrupos = grupoVisaoDao.findByIdVisaoAtivos(parm.getIdVisao());

        if (colGrupos != null) {
            for (final Iterator it = colGrupos.iterator(); it.hasNext();) {
                final GrupoVisaoDTO grupoVisaoDTO = (GrupoVisaoDTO) it.next();
                grupoVisaoDTO.setColCamposVisao(grupoVisaoCamposNegocioDao.findByIdGrupoVisaoAtivos(grupoVisaoDTO.getIdGrupoVisao()));

                if (grupoVisaoDTO.getColCamposVisao() != null) {
                    for (final Iterator it2 = grupoVisaoDTO.getColCamposVisao().iterator(); it2.hasNext();) {
                        final GrupoVisaoCamposNegocioDTO grupoVisaoCamposNegocioDTO = (GrupoVisaoCamposNegocioDTO) it2.next();

                        CamposObjetoNegocioDTO camposObjetoNegocioDTO = new CamposObjetoNegocioDTO();
                        camposObjetoNegocioDTO.setIdCamposObjetoNegocio(grupoVisaoCamposNegocioDTO.getIdCamposObjetoNegocio());
                        camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) camposObjetoNegocioDao.restore(camposObjetoNegocioDTO);

                        grupoVisaoCamposNegocioDTO.setCamposObjetoNegocioDto(camposObjetoNegocioDTO);
                        if (camposObjetoNegocioDTO != null) {
                            colPresentation.add(camposObjetoNegocioDTO);
                            colCamposApresNeg.add(grupoVisaoCamposNegocioDTO);
                            if (MetaUtil.isStringType(camposObjetoNegocioDTO.getTipoDB())) {
                                colFilter.add(camposObjetoNegocioDTO);
                            }
                            if (parm.getSort() != null) {
                                if (camposObjetoNegocioDTO.getNomeDB().equalsIgnoreCase(parm.getSort())) {
                                    camposObjetoNegocioDTO.setOrder(parm.getOrder());
                                    colOrder.add(camposObjetoNegocioDTO);
                                }
                            }
                        }
                    }
                }
            }
        }

        String sqlAdicionalWhere = "";
        if (tableVinc) {
            sqlAdicionalWhere = this.generateSQLVinculos(parm.getIdVisaoRelacionada(), map);
        }

        String strScript = (String) mapScritps.get(ScriptsVisaoDTO.SCRIPT_EXECUTE_SERVER + "#" + ScriptsVisaoDTO.SCRIPT_ONSQLWHERESEARCH.getName());
        if (strScript != null && !strScript.trim().equalsIgnoreCase("")) {
            final ScriptRhinoJSExecute scriptExecute = new ScriptRhinoJSExecute();
            final Context cx = Context.enter();
            final Scriptable scope = cx.initStandardObjects();
            scope.put("mapFields", scope, map);
            scope.put("sqlWhere", scope, sqlAdicionalWhere);
            scope.put("language", scope, WebUtil.getLanguage(request));
            final Object retorno = scriptExecute.processScript(cx, scope, strScript, TableSearchServiceEjb.class.getName() + "_"
                    + ScriptsVisaoDTO.SCRIPT_ONSQLWHERESEARCH.getName());
            sqlAdicionalWhere = (String) retorno;
        }
        String sql = "";
        sql = this.generateSQL(colPresentation, null, colFilter, colOrder, parm.getSSearch(), sqlAdicionalWhere);
        strScript = (String) mapScritps.get(ScriptsVisaoDTO.SCRIPT_EXECUTE_SERVER + "#" + ScriptsVisaoDTO.SCRIPT_ONSQLSEARCH.getName());
        if (strScript != null && !strScript.trim().equalsIgnoreCase("")) {
            final ScriptRhinoJSExecute scriptExecute = new ScriptRhinoJSExecute();
            final Context cx = Context.enter();
            final Scriptable scope = cx.initStandardObjects();
            scope.put("mapFields", scope, map);
            scope.put("sql", scope, sql);
            scope.put("language", scope, WebUtil.getLanguage(request));
            scriptExecute.processScript(cx, scope, strScript, TableSearchServiceEjb.class.getName() + "_" + ScriptsVisaoDTO.SCRIPT_ONSQLSEARCH.getName());
        }

        int IDISPLAYLENGTH = -1;
        final String IDISPLAYLENGTHStr = (String) map.get("IDISPLAYLENGTH");
        if (IDISPLAYLENGTHStr != null && !IDISPLAYLENGTHStr.trim().equalsIgnoreCase("")) {
            try {
                IDISPLAYLENGTH = Integer.parseInt(IDISPLAYLENGTHStr);
            } catch (final Exception e) {}
        }
        int PAGE = -1;
        final String PAGEStr = (String) map.get("PAGE");
        if (PAGEStr != null && !PAGEStr.trim().equalsIgnoreCase("")) {
            try {
                PAGE = Integer.parseInt(PAGEStr);
            } catch (final Exception e) {}
        }

        if (strSGBDPrincipal.equalsIgnoreCase("ORACLE") || strSGBDPrincipal.equalsIgnoreCase("ORACLE")) {
            // verifica se os parametros já foram alterados na sessão para não alterar várias vezes sem necessidade - melhoria de performace
            final String sql1 = "alter session set nls_comp = linguistic";
            try {
                this.getDao().execSQL(sql1, null);
            } catch (final Exception e) {}
            final String sql2 = "alter session set nls_sort = binary_ai";
            try {
                this.getDao().execSQL(sql2, null);
            } catch (final Exception e) {}
        }

        final Collection colRetorno = this.getDao().execSQL(sql, null);
        String retorno = "";
        boolean prim = true;
        int totalReg = 0;
        if (colRetorno != null && colRetorno.size() > 0) {
            totalReg = colRetorno.size();
            int tamCadaLinha = -1;
            for (final Iterator it = colRetorno.iterator(); it.hasNext();) {
                final Object[] objs = (Object[]) it.next();

                tamCadaLinha = objs.length;
                break;
            }
            if (tableVinc) {
                // Se for tabela vinculada, aumenta uma coluna, pois a "0" é sempre para os dados em Json.
                tamCadaLinha = tamCadaLinha + 2;
            }
            int contador = 0;
            int paginaAux = 1;
            for (final Iterator it = colRetorno.iterator(); it.hasNext();) {
                final Object[] objs = (Object[]) it.next();
                if (IDISPLAYLENGTH > 0) {
                    if (PAGE > 1 && PAGE >= paginaAux + 1) {
                        if (contador >= IDISPLAYLENGTH - 1) {
                            paginaAux++;
                            contador = 0;
                        } else {
                            contador++;
                        }
                        continue;
                    }
                    if (contador >= IDISPLAYLENGTH) {
                        break;
                    }
                }

                final Iterator it2 = colPresentation.iterator();
                if (!prim) {
                    retorno += ",";
                }
                retorno += "{";
                for (int i = 0; i < objs.length; i++) {
                    String valor = "";
                    final GrupoVisaoCamposNegocioDTO grupoVisaoCamposNegocioDTO = (GrupoVisaoCamposNegocioDTO) ((List) colCamposApresNeg).get(i);
                    if (grupoVisaoCamposNegocioDTO.getTipoNegocio().equalsIgnoreCase(MetaUtil.RELATION)) {
                        if (grupoVisaoCamposNegocioDTO.getTipoLigacao() == null) {
                            grupoVisaoCamposNegocioDTO.setTipoLigacao(GrupoVisaoCamposNegocioDTO.RELATION_SIMPLE);
                        }
                        if (grupoVisaoCamposNegocioDTO.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioDTO.RELATION_SIMPLE)) {
                            final LookupDTO lookupDto = new LookupDTO();
                            lookupDto.setTermoPesquisa("");

                            if (objs[i] != null) {
                                lookupDto.setTermoPesquisa(objs[i].toString());
                                lookupDto.setIdGrupoVisao(grupoVisaoCamposNegocioDTO.getIdGrupoVisao());
                                lookupDto.setIdCamposObjetoNegocio(grupoVisaoCamposNegocioDTO.getCamposObjetoNegocioDto().getIdCamposObjetoNegocio());

                                final ReturnLookupDTO returnLookupAux = lookupService.restoreSimple(lookupDto);
                                String value = "";
                                String text = "";
                                if (returnLookupAux != null) {
                                    value = returnLookupAux.getValue();
                                    text = returnLookupAux.getLabel();

                                    valor = text + " [" + value + "] ";
                                } else {
                                    valor = "";
                                    if (objs[i] != null) {
                                        valor = objs[i].toString();
                                    }
                                }
                            } else {
                                valor = "";
                            }
                        } else {
                            if (objs[i] != null) {
                                valor = objs[i].toString();
                            } else {
                                valor = "";
                            }
                        }
                    } else if (grupoVisaoCamposNegocioDTO.getTipoNegocio().equalsIgnoreCase(MetaUtil.RADIO)
                            || grupoVisaoCamposNegocioDTO.getTipoNegocio().equalsIgnoreCase(MetaUtil.SELECT)) {
                        if (objs[i] != null) {
                            final ValorVisaoCamposNegocioDao valorVisaoCamposNegocioDao = new ValorVisaoCamposNegocioDao();
                            final Collection colDominios = valorVisaoCamposNegocioDao.findByIdCamposObjetoNegocio(grupoVisaoCamposNegocioDTO
                                    .getIdCamposObjetoNegocio());
                            if (colDominios != null) {
                                valor = objs[i].toString();
                                for (final Iterator itDominios = colDominios.iterator(); itDominios.hasNext();) {
                                    final ValorVisaoCamposNegocioDTO valorVisaoCamposNegocioDTO = (ValorVisaoCamposNegocioDTO) itDominios.next();
                                    if (objs[i].toString().equalsIgnoreCase(valorVisaoCamposNegocioDTO.getValor())) {
                                        if (valorVisaoCamposNegocioDTO.getDescricao().contains("$")) {
                                            valor = UtilI18N.internacionaliza(request, valorVisaoCamposNegocioDTO.getDescricao().replace("$", "")) + " ["
                                                    + objs[i].toString() + "]";
                                        } else {
                                            valor = valorVisaoCamposNegocioDTO.getDescricao() + " [" + objs[i].toString() + "]";
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        if (objs[i] instanceof java.sql.Date) {
                            valor = UtilDatas.convertDateToString(TipoDate.DATE_DEFAULT, (java.sql.Date) objs[i], WebUtil.getLanguage(request));
                        } else if (objs[i] instanceof java.sql.Timestamp) {
                            valor = UtilDatas.convertDateToString(TipoDate.DATE_DEFAULT, (java.sql.Timestamp) objs[i], WebUtil.getLanguage(request));
                        } else if (Double.class.isInstance(objs[i])) {
                            valor = UtilFormatacao.formatDouble((Double) objs[i], 2);
                            valor = valor.replaceAll(",00", "");
                            valor = valor.replaceAll("\\.", "");
                        } else if (BigDecimal.class.isInstance(objs[i])) {
                            valor = UtilFormatacao.formatBigDecimal((BigDecimal) objs[i], 2);
                            valor = valor.replaceAll(",00", "");
                            valor = valor.replaceAll("\\.", "");
                        } else {
                            if (objs[i] != null) {
                                valor = objs[i].toString();
                            } else {
                                valor = "";
                            }
                        }
                        if (valor != null && valor.indexOf("\n") > -1) { // Se tiver enter, tira o enter da string
                            valor = valor.replaceAll("\n", " ");
                        }
                        if (valor != null && valor.indexOf("\r") > -1) { // Se tiver enter, tira o enter da string
                            valor = valor.replaceAll("\r", " ");
                        }
                        if (valor != null && valor.indexOf("\t") > -1) { // Se tiver tab, tira o tab da string
                            valor = valor.replaceAll("\t", " ");
                        }

                    }
                    if (it2.hasNext()) {
                        if (i > 0) {
                            retorno += ",";
                        }
                        final CamposObjetoNegocioDTO camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) it2.next();
                        retorno += "\"" + camposObjetoNegocioDTO.getNomeDB() + "\":";
                        valor = UtilStrings.replaceInvalid(valor);
                        valor = valor.replaceAll("\"", "");
                        valor = valor.replaceAll("\t", "");
                        valor = valor.trim();
                        retorno += "\"" + valor + "\"";
                    }
                }
                retorno += "}";
                prim = false;

                contador++;
            }
            return "{\"total\": \"" + totalReg + "\",\"rows\":[" + StringEscapeUtils.unescapeEcmaScript(retorno) + "]}";
        }
        return "{\"total\": \"0\",\"rows\":[]}";
    }

    @Override
    public String getInfoMatriz(final TableSearchDTO parm, final boolean tableVinc, final Map map, final HttpServletRequest request) throws Exception {
        final MatrizVisaoService matrizVisaoService = (MatrizVisaoService) ServiceLocator.getInstance().getService(MatrizVisaoService.class, null);
        final ObjetoNegocioService objetoNegocioService = (ObjetoNegocioService) ServiceLocator.getInstance().getService(ObjetoNegocioService.class, null);
        final VisaoService visaoService = (VisaoService) ServiceLocator.getInstance().getService(VisaoService.class, null);
        final GrupoVisaoService grupoVisaoService = (GrupoVisaoService) ServiceLocator.getInstance().getService(GrupoVisaoService.class, null);
        final GrupoVisaoCamposNegocioService grupoVisaoCamposNegocioService = (GrupoVisaoCamposNegocioService) ServiceLocator.getInstance().getService(
                GrupoVisaoCamposNegocioService.class, null);
        final CamposObjetoNegocioService camposObjetoNegocioService = (CamposObjetoNegocioService) ServiceLocator.getInstance().getService(
                CamposObjetoNegocioService.class, null);
        final ValorVisaoCamposNegocioService valorVisaoCamposNegocioService = (ValorVisaoCamposNegocioService) ServiceLocator.getInstance().getService(
                ValorVisaoCamposNegocioService.class, null);
        ServiceLocator.getInstance().getService(VisaoRelacionadaService.class, null);
        ServiceLocator.getInstance().getService(ScriptsVisaoService.class, null);
        ServiceLocator.getInstance().getService(HtmlCodeVisaoService.class, null);

        VisaoDTO visaoDto = new VisaoDTO();
        visaoDto.setIdVisao(parm.getIdVisao());
        visaoDto = (VisaoDTO) visaoService.restore(visaoDto);
        if (visaoDto == null) {
            return null;
        }

        final Collection colGrupos = grupoVisaoService.findByIdVisaoAtivos(parm.getIdVisao());
        if (colGrupos != null) {
            for (final Iterator it = colGrupos.iterator(); it.hasNext();) {
                final GrupoVisaoDTO grupoVisaoDTO = (GrupoVisaoDTO) it.next();
                grupoVisaoDTO.setColCamposVisao(grupoVisaoCamposNegocioService.findByIdGrupoVisaoAtivos(grupoVisaoDTO.getIdGrupoVisao()));

                if (grupoVisaoDTO.getColCamposVisao() != null) {
                    for (final Iterator it2 = grupoVisaoDTO.getColCamposVisao().iterator(); it2.hasNext();) {
                        final GrupoVisaoCamposNegocioDTO grupoVisaoCamposNegocioDTO = (GrupoVisaoCamposNegocioDTO) it2.next();

                        CamposObjetoNegocioDTO camposObjetoNegocioDTO = new CamposObjetoNegocioDTO();
                        camposObjetoNegocioDTO.setIdCamposObjetoNegocio(grupoVisaoCamposNegocioDTO.getIdCamposObjetoNegocio());
                        camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) camposObjetoNegocioService.restore(camposObjetoNegocioDTO);

                        grupoVisaoCamposNegocioDTO.setCamposObjetoNegocioDto(camposObjetoNegocioDTO);

                        final Collection colValores = valorVisaoCamposNegocioService.findByIdGrupoVisaoAndIdCampoObjetoNegocio(grupoVisaoDTO.getIdGrupoVisao(),
                                grupoVisaoCamposNegocioDTO.getIdCamposObjetoNegocio());
                        grupoVisaoCamposNegocioDTO.setColValores(colValores);
                    }
                }
            }
        }
        visaoDto.setColGrupos(colGrupos);
        String retorno = "";
        final Collection colMatriz = matrizVisaoService.findByIdVisao(parm.getIdVisao());
        if (colMatriz != null && colMatriz.size() > 0) {
            final DinamicViewsServiceEjb dinamicViewEjb = new DinamicViewsServiceEjb();
            final Collection colCamposPKPrincipal = new ArrayList<>();
            final Collection colCamposTodosPrincipal = new ArrayList<>();
            dinamicViewEjb.setInfoSave(parm.getIdVisao(), colCamposPKPrincipal, colCamposTodosPrincipal);
            final String tablePrincipal = dinamicViewEjb.generateFrom(colCamposTodosPrincipal);
            final MatrizVisaoDTO matrizVisaoDTO = (MatrizVisaoDTO) colMatriz.iterator().next();
            ObjetoNegocioDTO objetoNegocioDTO = new ObjetoNegocioDTO();
            objetoNegocioDTO.setIdObjetoNegocio(matrizVisaoDTO.getIdObjetoNegocio());
            objetoNegocioDTO = (ObjetoNegocioDTO) objetoNegocioService.restore(objetoNegocioDTO);
            final MatrizVisaoDao matrizVisaoDao = new MatrizVisaoDao();
            final ValorRecuperadoMatrizDTO valorRecuperadoMatrizDTO = matrizVisaoDao.recuperaDadosMatriz(matrizVisaoDTO);
            if (valorRecuperadoMatrizDTO.getColDados() != null) {
                for (final Iterator it = valorRecuperadoMatrizDTO.getColDados().iterator(); it.hasNext();) {
                    boolean prim = true;
                    if (!retorno.trim().equalsIgnoreCase("")) {
                        retorno = retorno + ",";
                    }
                    retorno += "{";
                    final Object[] obj = (Object[]) it.next();
                    for (int i = 0; i < obj.length; i++) {
                        if (!prim) {
                            retorno = retorno + ",";
                        }
                        prim = false;
                        retorno = retorno + "\"fld_" + i + "\":";
                        retorno = retorno + "\"" + obj[i].toString() + "\"";
                    }
                    if (visaoDto.getColGrupos() != null) {
                        for (final Iterator it2 = visaoDto.getColGrupos().iterator(); it2.hasNext();) {
                            final GrupoVisaoDTO grupoVisaoDTO = (GrupoVisaoDTO) it2.next();
                            if (grupoVisaoDTO.getColCamposVisao() != null) {
                                for (final Iterator it3 = grupoVisaoDTO.getColCamposVisao().iterator(); it3.hasNext();) {
                                    final GrupoVisaoCamposNegocioDTO grupoVisaoCamposNegocioDTO = (GrupoVisaoCamposNegocioDTO) it3.next();
                                    if (grupoVisaoCamposNegocioDTO.getTipoNegocio().equalsIgnoreCase(MetaUtil.HIDDEN)) {
                                        continue;
                                    }
                                    String valorApres = "";
                                    if (valorRecuperadoMatrizDTO.getCamposObjetoNegocioApres1DTO() != null) {
                                        if (grupoVisaoCamposNegocioDTO.getCamposObjetoNegocioDto().getNomeDB()
                                                .equalsIgnoreCase(valorRecuperadoMatrizDTO.getCamposObjetoNegocioApres1DTO().getNomeDB())) {
                                            valorApres = obj[1].toString();
                                        }
                                    }
                                    if (valorRecuperadoMatrizDTO.getCamposObjetoNegocioApres2DTO() != null) {
                                        if (grupoVisaoCamposNegocioDTO.getCamposObjetoNegocioDto().getNomeDB()
                                                .equalsIgnoreCase(valorRecuperadoMatrizDTO.getCamposObjetoNegocioApres2DTO().getNomeDB())) {
                                            valorApres = obj[2].toString();
                                        }
                                    }
                                    if (valorApres.trim().equalsIgnoreCase("")) {
                                        // Se ainda estiver vazio, eh que ainda nao conseguiu pegar o valor atual
                                        valorApres = MetaUtil.recuperaValorAtualCampo(colCamposPKPrincipal,
                                                grupoVisaoCamposNegocioDTO.getCamposObjetoNegocioDto(), tablePrincipal, obj, matrizVisaoDTO, map, request);
                                    }
                                    if (!prim) {
                                        retorno = retorno + ",";
                                    }
                                    prim = false;
                                    retorno = retorno + "\"" + grupoVisaoCamposNegocioDTO.getCamposObjetoNegocioDto().getNomeDB() + "\":";
                                    retorno = retorno + "\"" + valorApres + "\"";
                                }
                            }
                        }
                    }
                    retorno += "}";
                }
            }
        }
        return retorno;
    }

    private String generateSQLVinculos(final Integer idVisaoRelacionada, final Map map) throws Exception {
        if (map == null) {
            return "";
        }
        final CamposObjetoNegocioDao camposObjetoNegocioDao = new CamposObjetoNegocioDao();
        final GrupoVisaoCamposNegocioDao grupoVisaoCamposNegocioDao = new GrupoVisaoCamposNegocioDao();
        String sqlAdicional = "";
        if (idVisaoRelacionada != null) {
            final ObjetoNegocioDao objetoNegocioDao = new ObjetoNegocioDao();
            final VisaoRelacionadaDao visaoRelacionadaDao = new VisaoRelacionadaDao();
            VisaoRelacionadaDTO visaoRelacionadaDTO = new VisaoRelacionadaDTO();
            visaoRelacionadaDTO.setIdVisaoRelacionada(idVisaoRelacionada);
            visaoRelacionadaDTO = (VisaoRelacionadaDTO) visaoRelacionadaDao.restore(visaoRelacionadaDTO);

            String tabela = "";
            ObjetoNegocioDTO objetoNegocioDTO = new ObjetoNegocioDTO();
            objetoNegocioDTO.setIdObjetoNegocio(visaoRelacionadaDTO.getIdObjetoNegocioNN());
            if (objetoNegocioDTO.getIdObjetoNegocio() != null) {
                objetoNegocioDTO = (ObjetoNegocioDTO) objetoNegocioDao.restore(objetoNegocioDTO);
                tabela = objetoNegocioDTO.getNomeTabelaDB();
            }

            String complemento = "";
            final VinculoVisaoDao vinculoVisaoDao = new VinculoVisaoDao();
            Collection colVinculos = vinculoVisaoDao.findByIdVisaoRelacionada(idVisaoRelacionada);
            CamposObjetoNegocioDTO camposObjetoNegocioDTOFilho = null;
            CamposObjetoNegocioDTO camposObjetoNegocioDTOFilhoNN = null;
            if (colVinculos == null) {
                colVinculos = new ArrayList<>();
            }
            for (final Iterator itVinculos = colVinculos.iterator(); itVinculos.hasNext();) {
                final VinculoVisaoDTO vinculoVisaoDTO = (VinculoVisaoDTO) itVinculos.next();
                new HashMap<>();

                if (vinculoVisaoDTO.getIdCamposObjetoNegocioFilho() != null) {
                    GrupoVisaoCamposNegocioDTO grupoVisaoCamposNegocioDTO = new GrupoVisaoCamposNegocioDTO();
                    grupoVisaoCamposNegocioDTO.setIdGrupoVisao(vinculoVisaoDTO.getIdGrupoVisaoFilho());
                    grupoVisaoCamposNegocioDTO.setIdCamposObjetoNegocio(vinculoVisaoDTO.getIdCamposObjetoNegocioFilho());
                    grupoVisaoCamposNegocioDTO = (GrupoVisaoCamposNegocioDTO) grupoVisaoCamposNegocioDao.restore(grupoVisaoCamposNegocioDTO);

                    if (grupoVisaoCamposNegocioDTO != null) {
                        camposObjetoNegocioDTOFilho = new CamposObjetoNegocioDTO();
                        camposObjetoNegocioDTOFilho.setIdCamposObjetoNegocio(grupoVisaoCamposNegocioDTO.getIdCamposObjetoNegocio());
                        camposObjetoNegocioDTOFilho = (CamposObjetoNegocioDTO) camposObjetoNegocioDao.restore(camposObjetoNegocioDTOFilho);
                    }
                }

                CamposObjetoNegocioDTO camposObjetoNegocioDTOPai = null;
                if (vinculoVisaoDTO.getIdCamposObjetoNegocioPaiNN() != null) {
                    camposObjetoNegocioDTOPai = new CamposObjetoNegocioDTO();
                    camposObjetoNegocioDTOPai.setIdCamposObjetoNegocio(vinculoVisaoDTO.getIdCamposObjetoNegocioPaiNN());
                    camposObjetoNegocioDTOPai = (CamposObjetoNegocioDTO) camposObjetoNegocioDao.restore(camposObjetoNegocioDTOPai);
                }

                if (vinculoVisaoDTO.getIdCamposObjetoNegocioFilhoNN() != null) {
                    camposObjetoNegocioDTOFilhoNN = new CamposObjetoNegocioDTO();
                    camposObjetoNegocioDTOFilhoNN.setIdCamposObjetoNegocio(vinculoVisaoDTO.getIdCamposObjetoNegocioFilhoNN());
                    camposObjetoNegocioDTOFilhoNN = (CamposObjetoNegocioDTO) camposObjetoNegocioDao.restore(camposObjetoNegocioDTOFilhoNN);
                }

                if (camposObjetoNegocioDTOPai != null) {
                    final String valuePai = (String) map.get(camposObjetoNegocioDTOPai.getNomeDB().trim().toUpperCase());
                    if (valuePai != null && !valuePai.equalsIgnoreCase("")) {
                        if (!complemento.trim().equalsIgnoreCase("")) {
                            complemento += " AND ";
                        }
                        if (MetaUtil.isStringType(camposObjetoNegocioDTOPai.getTipoDB())) {
                            complemento += " " + camposObjetoNegocioDTOPai.getNomeDB().trim().toUpperCase() + " = '" + valuePai + "'";
                        } else {
                            complemento += " " + camposObjetoNegocioDTOPai.getNomeDB().trim().toUpperCase() + " = " + valuePai;
                        }
                    }
                }
                if (vinculoVisaoDTO.getTipoVinculo().equalsIgnoreCase(VinculoVisaoDTO.VINCULO_N_TO_N)) {
                    if (!tabela.equalsIgnoreCase("") && !complemento.equalsIgnoreCase("") && camposObjetoNegocioDTOFilho != null
                            && camposObjetoNegocioDTOFilhoNN != null) {
                        sqlAdicional += " AND ";
                        sqlAdicional += " " + camposObjetoNegocioDTOFilho.getNomeDB() + " IN (SELECT " + camposObjetoNegocioDTOFilhoNN.getNomeDB() + " FROM "
                                + tabela + " WHERE " + complemento + ") ";
                    }
                }
                if (vinculoVisaoDTO.getTipoVinculo().equalsIgnoreCase(VinculoVisaoDTO.VINCULO_1_TO_N)) {
                    if (camposObjetoNegocioDTOPai != null && complemento != null && !complemento.equalsIgnoreCase("")) {
                        sqlAdicional += " AND ";
                        sqlAdicional += complemento;
                    }
                }
            }
        }
        return sqlAdicional;
    }

    /**
     * Gera SQL de consulta das telas que são DinamicViews.
     *
     * @param colPresentation
     * @param colValue
     * @param colFilter
     * @param colOrder
     * @param termoPesquisa
     *            - Termo de pesquisa informado pelo usuário.
     * @param sqlAdicionalWhere
     * @return SQL de Consulta.
     * @throws Exception
     */
    private String generateSQL(final Collection colPresentation, final Collection colValue, final Collection colFilter, final Collection colOrder,
            final String termoPesquisa, String sqlAdicionalWhere) throws Exception {

        if (strSGBDPrincipal == null) {
            strSGBDPrincipal = CITCorporeUtil.SGBD_PRINCIPAL;
            strSGBDPrincipal = UtilStrings.nullToVazio(strSGBDPrincipal).trim();
        }

        String sql = "SELECT ";

        sql += this.generateFields(colPresentation, colValue);
        sql += " FROM ";
        sql += this.generateFromWithRelatios(colPresentation, colFilter);

        final String strFilter = this.generateFilter(colFilter, termoPesquisa);

        sqlAdicionalWhere += " AND (DELETED IS NULL OR DELETED = 'N')";
        if (!strFilter.equalsIgnoreCase("")) {
            sql += " WHERE (" + strFilter + ") ";
            sql += sqlAdicionalWhere;
        } else {
            if (sqlAdicionalWhere != null && !sqlAdicionalWhere.trim().equalsIgnoreCase("")) {
                sql += " WHERE 1 = 1 " + sqlAdicionalWhere;
            }
        }

        final String strOrder = this.generateOrder(colOrder);
        if (!strOrder.equalsIgnoreCase("")) {
            sql += " ORDER BY " + strOrder;
        } else {
            sql += " ORDER BY 2";
        }

        return sql;
    }

    private String generateFields(final Collection colPresentation, Collection colValue) throws Exception {
        final ObjetoNegocioDao objetoNegocioDao = new ObjetoNegocioDao();
        String sqlFields = "";
        if (colValue == null) {
            colValue = new ArrayList<>();
        }
        if (colPresentation != null) {
            int i = 1;
            for (final Iterator it = colValue.iterator(); it.hasNext();) {
                final CamposObjetoNegocioDTO camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) it.next();
                ObjetoNegocioDTO objetoNegocioDTO = new ObjetoNegocioDTO();

                objetoNegocioDTO.setIdObjetoNegocio(camposObjetoNegocioDTO.getIdObjetoNegocio());
                objetoNegocioDTO = (ObjetoNegocioDTO) objetoNegocioDao.restore(objetoNegocioDTO);

                if (objetoNegocioDTO != null) {
                    if (!sqlFields.equalsIgnoreCase("")) {
                        sqlFields += ", ";
                    }
                    sqlFields += objetoNegocioDTO.getNomeTabelaDB() + "." + camposObjetoNegocioDTO.getNomeDB() + " Val_" + i;
                }
                i++;
            }
            i = 1;
            for (final Iterator it = colPresentation.iterator(); it.hasNext();) {
                final CamposObjetoNegocioDTO camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) it.next();
                ObjetoNegocioDTO objetoNegocioDTO = new ObjetoNegocioDTO();

                objetoNegocioDTO.setIdObjetoNegocio(camposObjetoNegocioDTO.getIdObjetoNegocio());
                objetoNegocioDTO = (ObjetoNegocioDTO) objetoNegocioDao.restore(objetoNegocioDTO);

                if (objetoNegocioDTO != null) {
                    if (!sqlFields.equalsIgnoreCase("")) {
                        sqlFields += ", ";
                    }
                    sqlFields += objetoNegocioDTO.getNomeTabelaDB() + "." + camposObjetoNegocioDTO.getNomeDB() + " Fld_" + i;
                }
                i++;
            }
        }
        return sqlFields;
    }

    private String generateFromWithRelatios(final Collection colPresentation, final Collection colFilter) throws Exception {
        final ObjetoNegocioDao objetoNegocioDao = new ObjetoNegocioDao();
        final HashMap map = new HashMap<>();

        final Collection colGeral = new ArrayList<>();
        if (colPresentation != null) {
            colGeral.addAll(colPresentation);
        }
        if (colFilter != null) {
            colGeral.addAll(colFilter);
        }

        if (colGeral != null) {
            for (final Iterator it = colGeral.iterator(); it.hasNext();) {
                final CamposObjetoNegocioDTO camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) it.next();
                ObjetoNegocioDTO objetoNegocioDTO = new ObjetoNegocioDTO();

                objetoNegocioDTO.setIdObjetoNegocio(camposObjetoNegocioDTO.getIdObjetoNegocio());
                objetoNegocioDTO = (ObjetoNegocioDTO) objetoNegocioDao.restore(objetoNegocioDTO);

                if (objetoNegocioDTO != null) {
                    if (!map.containsKey(objetoNegocioDTO.getNomeTabelaDB())) {
                        map.put(objetoNegocioDTO.getNomeTabelaDB(), objetoNegocioDTO.getNomeTabelaDB());
                    }
                }
            }
        }

        final Set set = map.entrySet();
        final Iterator i = set.iterator();

        String fromSql = "";
        while (i.hasNext()) {
            final Map.Entry me = (Map.Entry) i.next();
            if (!fromSql.equalsIgnoreCase("")) {
                fromSql += ",";
            }
            fromSql += me.getKey();
        }

        return fromSql;
    }

    private String generateFilter(final Collection colFilter, final String termoPesquisa) throws Exception {

        final ObjetoNegocioDao objetoNegocioDao = new ObjetoNegocioDao();
        String sqlFilter = "";

        if (colFilter != null) {
            for (final Iterator it = colFilter.iterator(); it.hasNext();) {
                final CamposObjetoNegocioDTO camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) it.next();
                ObjetoNegocioDTO objetoNegocioDTO = new ObjetoNegocioDTO();

                objetoNegocioDTO.setIdObjetoNegocio(camposObjetoNegocioDTO.getIdObjetoNegocio());
                objetoNegocioDTO = (ObjetoNegocioDTO) objetoNegocioDao.restore(objetoNegocioDTO);

                if (objetoNegocioDTO != null) {
                    if (!sqlFilter.equalsIgnoreCase("")) {
                        sqlFilter += " OR ";
                    }
                    String pref = "";
                    String pref2 = "";
                    String suf = "";
                    String suf2 = "";
                    String comp = "=";
                    String termoPesquisaTratado = "";

                    boolean isTextSqlServer = false;

                    if (camposObjetoNegocioDTO.getTipoDB().startsWith("TEXT") && strSGBDPrincipal.equalsIgnoreCase("SQLSERVER")) {

                        isTextSqlServer = true;

                        pref = "'%";
                        suf = "%'";
                        comp = "LIKE";

                    } else {

                        pref2 = "UPPER(";

                        boolean isPostgres = false;
                        if (strSGBDPrincipal.equalsIgnoreCase("POSTGRESQL") || strSGBDPrincipal.equalsIgnoreCase("POSTGRES")) {
                            pref2 = pref2 + "remove_acento(";
                            isPostgres = true;
                        }

                        suf2 = ")";
                        if (isPostgres) {
                            suf2 = "))";
                        }
                        pref = "'%";
                        suf = "%'";
                        comp = "LIKE";

                    }

                    if (termoPesquisa != null && !termoPesquisa.trim().equalsIgnoreCase("")) {

                        if (!isTextSqlServer) {
                            termoPesquisaTratado = this.tratarTermoPesquisa(termoPesquisa);
                        } else {
                            termoPesquisaTratado = termoPesquisa;
                        }

                        if (strSGBDPrincipal.equalsIgnoreCase("SQLSERVER")) {
                            suf = suf + " COLLATE Latin1_General_CI_AI";
                        }

                        sqlFilter += " " + pref2 + objetoNegocioDTO.getNomeTabelaDB() + "." + camposObjetoNegocioDTO.getNomeDB() + suf2 + " " + comp + " "
                                + pref + termoPesquisaTratado.trim() + suf;

                    }
                }
            }
        }

        if (strSGBDPrincipal.equalsIgnoreCase("POSTGRESQL") || strSGBDPrincipal.equalsIgnoreCase("POSTGRES")) {
            sqlFilter = sqlFilter.replaceAll("LIKE", "ILIKE");
        }

        return sqlFilter;
    }

    private String tratarTermoPesquisa(String termoPesquisa) {
        termoPesquisa = termoPesquisa.trim().toUpperCase();
        termoPesquisa = Normalizer.normalize(termoPesquisa, Normalizer.Form.NFD);
        termoPesquisa = termoPesquisa.replaceAll("[^\\p{ASCII}]", "");
        return termoPesquisa;
    }

    private String generateOrder(final Collection colOrder) throws Exception {
        final ObjetoNegocioDao objetoNegocioDao = new ObjetoNegocioDao();
        String sqlOrder = "";
        if (colOrder != null) {
            for (final Iterator it = colOrder.iterator(); it.hasNext();) {
                final CamposObjetoNegocioDTO camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) it.next();
                ObjetoNegocioDTO objetoNegocioDTO = new ObjetoNegocioDTO();

                objetoNegocioDTO.setIdObjetoNegocio(camposObjetoNegocioDTO.getIdObjetoNegocio());
                objetoNegocioDTO = (ObjetoNegocioDTO) objetoNegocioDao.restore(objetoNegocioDTO);

                if (objetoNegocioDTO != null) {
                    if (!sqlOrder.equalsIgnoreCase("")) {
                        sqlOrder += ", ";
                    }
                    sqlOrder += objetoNegocioDTO.getNomeTabelaDB() + "." + camposObjetoNegocioDTO.getNomeDB() + " " + camposObjetoNegocioDTO.getOrder();
                }
            }
        }
        return sqlOrder;
    }

}
