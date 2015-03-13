package br.com.centralit.citcorpore.metainfo.negocio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;

import br.com.centralit.citcorpore.metainfo.bean.CamposObjetoNegocioDTO;
import br.com.centralit.citcorpore.metainfo.bean.GrupoVisaoCamposNegocioDTO;
import br.com.centralit.citcorpore.metainfo.bean.GrupoVisaoCamposNegocioLigacaoDTO;
import br.com.centralit.citcorpore.metainfo.bean.LookupDTO;
import br.com.centralit.citcorpore.metainfo.bean.ObjetoNegocioDTO;
import br.com.centralit.citcorpore.metainfo.bean.ReturnLookupDTO;
import br.com.centralit.citcorpore.metainfo.integracao.CamposObjetoNegocioDao;
import br.com.centralit.citcorpore.metainfo.integracao.GrupoVisaoCamposNegocioDao;
import br.com.centralit.citcorpore.metainfo.integracao.GrupoVisaoCamposNegocioLigacaoDao;
import br.com.centralit.citcorpore.metainfo.integracao.LookupDao;
import br.com.centralit.citcorpore.metainfo.integracao.ObjetoNegocioDao;
import br.com.centralit.citcorpore.metainfo.util.MetaUtil;
import br.com.citframework.service.CrudServiceImpl;

public class LookupServiceEjb extends CrudServiceImpl implements LookupService {

    private LookupDao lookupDao;

    @Override
    protected LookupDao getDao() {
        if (lookupDao == null) {
            lookupDao = new LookupDao();
        }
        return lookupDao;
    }

    @Override
    public Collection findSimple(final LookupDTO parm) throws Exception {
        final GrupoVisaoCamposNegocioDao grupoVisaoCamposNegocioDao = new GrupoVisaoCamposNegocioDao();
        GrupoVisaoCamposNegocioDTO grupoVisaoCamposNegocioDTO = new GrupoVisaoCamposNegocioDTO();

        final GrupoVisaoCamposNegocioLigacaoDao grupoVisaoCamposNegocioLigacaoDao = new GrupoVisaoCamposNegocioLigacaoDao();

        final CamposObjetoNegocioDao camposObjetoNegocioDao = new CamposObjetoNegocioDao();
        CamposObjetoNegocioDTO camposObjetoNegocioDTO = new CamposObjetoNegocioDTO();

        grupoVisaoCamposNegocioDTO.setIdGrupoVisao(parm.getIdGrupoVisao());
        grupoVisaoCamposNegocioDTO.setIdCamposObjetoNegocio(parm.getIdCamposObjetoNegocio());
        grupoVisaoCamposNegocioDTO = (GrupoVisaoCamposNegocioDTO) grupoVisaoCamposNegocioDao.restore(grupoVisaoCamposNegocioDTO);
        if (grupoVisaoCamposNegocioDTO == null) {
            return null;
        }

        if (grupoVisaoCamposNegocioDTO.getTipoLigacao() == null) {
            grupoVisaoCamposNegocioDTO.setTipoLigacao(GrupoVisaoCamposNegocioDTO.RELATION_SIMPLE);
        }
        if (!grupoVisaoCamposNegocioDTO.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioDTO.RELATION_SIMPLE)
                && !grupoVisaoCamposNegocioDTO.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioDTO.RELATION_COMBO)) {
            grupoVisaoCamposNegocioDTO.setTipoLigacao(GrupoVisaoCamposNegocioDTO.RELATION_COMBO);
        }

        final Collection colPresentation = new ArrayList<>();
        final Collection colValue = new ArrayList<>();
        final Collection colOrder = new ArrayList<>();
        final Collection colFilter = new ArrayList<>();

        String sql = "";
        if (grupoVisaoCamposNegocioDTO.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioDTO.RELATION_SIMPLE)
                || grupoVisaoCamposNegocioDTO.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioDTO.RELATION_COMBO)) {
            final Collection colItens = grupoVisaoCamposNegocioLigacaoDao.findByIdGrupoVisaoAndIdCamposObjetoNegocio(parm.getIdGrupoVisao(),
                    parm.getIdCamposObjetoNegocio());
            if (colItens != null) {
                for (final Iterator it = colItens.iterator(); it.hasNext();) {
                    final GrupoVisaoCamposNegocioLigacaoDTO grupoVisaoCamposNegocioLigacaoAux = (GrupoVisaoCamposNegocioLigacaoDTO) it.next();

                    if (grupoVisaoCamposNegocioLigacaoAux.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioLigacaoDTO.PRESENTATION)) {
                        camposObjetoNegocioDTO.setIdCamposObjetoNegocio(grupoVisaoCamposNegocioLigacaoAux.getIdCamposObjetoNegocioLigacao());
                        camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) camposObjetoNegocioDao.restore(camposObjetoNegocioDTO);
                        if (camposObjetoNegocioDTO != null) {
                            camposObjetoNegocioDTO.setDescricao(grupoVisaoCamposNegocioLigacaoAux.getDescricao());
                            colPresentation.add(camposObjetoNegocioDTO);
                        }
                    }
                    if (grupoVisaoCamposNegocioLigacaoAux.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioLigacaoDTO.VALUE)) {
                        camposObjetoNegocioDTO.setIdCamposObjetoNegocio(grupoVisaoCamposNegocioLigacaoAux.getIdCamposObjetoNegocioLigacao());
                        camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) camposObjetoNegocioDao.restore(camposObjetoNegocioDTO);
                        if (camposObjetoNegocioDTO != null) {
                            colValue.add(camposObjetoNegocioDTO);
                        }
                    }
                    if (grupoVisaoCamposNegocioLigacaoAux.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioLigacaoDTO.ORDER)) {
                        camposObjetoNegocioDTO.setIdCamposObjetoNegocio(grupoVisaoCamposNegocioLigacaoAux.getIdCamposObjetoNegocioLigacao());
                        camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) camposObjetoNegocioDao.restore(camposObjetoNegocioDTO);
                        if (camposObjetoNegocioDTO != null) {
                            colOrder.add(camposObjetoNegocioDTO);
                        }
                    }
                    if (grupoVisaoCamposNegocioLigacaoAux.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioLigacaoDTO.FILTER)) {
                        camposObjetoNegocioDTO.setIdCamposObjetoNegocio(grupoVisaoCamposNegocioLigacaoAux.getIdCamposObjetoNegocioLigacao());
                        camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) camposObjetoNegocioDao.restore(camposObjetoNegocioDTO);
                        if (camposObjetoNegocioDTO != null) {
                            camposObjetoNegocioDTO.setFiltro(grupoVisaoCamposNegocioLigacaoAux.getFiltro().replace("\"", "\'"));
                            colFilter.add(camposObjetoNegocioDTO);
                        }
                    }
                }
            }

            sql = this.generateSQL(colPresentation, colValue, colFilter, colOrder, parm.getTermoPesquisa());
        }

        Collection colRetorno = null;
        if (!sql.trim().equalsIgnoreCase("SELECT  FROM")) {
            colRetorno = this.getDao().execSQL(sql, null);
        }
        final Collection colRetornoLookup = new ArrayList<>();
        if (colRetorno != null) {
            final int tamCamposValue = colValue.size();
            for (final Iterator it = colRetorno.iterator(); it.hasNext();) {
                String lineLabel = "";
                String lineValue = "";
                final ReturnLookupDTO returnLookupDTO = new ReturnLookupDTO();
                final Object[] objs = (Object[]) it.next();
                for (int i = 0; i < objs.length; i++) {
                    if (objs[i] != null) {
                        if (i >= tamCamposValue) {
                            if (!lineLabel.equalsIgnoreCase("")) {
                                lineLabel += ", ";
                            }
                            lineLabel += objs[i].toString();
                        } else {
                            if (!lineValue.equalsIgnoreCase("")) {
                                lineValue += "#";
                            }
                            lineValue += objs[i].toString();
                        }
                    }
                }
                returnLookupDTO.setLabel(lineLabel);
                returnLookupDTO.setValue(lineValue);

                colRetornoLookup.add(returnLookupDTO);
            }
        }
        return colRetornoLookup;
    }

    @Override
    public String findSimpleString(final LookupDTO parm) throws Exception {
        final GrupoVisaoCamposNegocioDao grupoVisaoCamposNegocioDao = new GrupoVisaoCamposNegocioDao();
        GrupoVisaoCamposNegocioDTO grupoVisaoCamposNegocioDTO = new GrupoVisaoCamposNegocioDTO();

        final GrupoVisaoCamposNegocioLigacaoDao grupoVisaoCamposNegocioLigacaoDao = new GrupoVisaoCamposNegocioLigacaoDao();
        new GrupoVisaoCamposNegocioLigacaoDTO();

        final CamposObjetoNegocioDao camposObjetoNegocioDao = new CamposObjetoNegocioDao();
        CamposObjetoNegocioDTO camposObjetoNegocioDTO = new CamposObjetoNegocioDTO();

        grupoVisaoCamposNegocioDTO.setIdGrupoVisao(parm.getIdGrupoVisao());
        grupoVisaoCamposNegocioDTO.setIdCamposObjetoNegocio(parm.getIdCamposObjetoNegocio());
        grupoVisaoCamposNegocioDTO = (GrupoVisaoCamposNegocioDTO) grupoVisaoCamposNegocioDao.restore(grupoVisaoCamposNegocioDTO);
        if (grupoVisaoCamposNegocioDTO == null) {
            return null;
        }

        if (grupoVisaoCamposNegocioDTO.getTipoLigacao() == null) {
            grupoVisaoCamposNegocioDTO.setTipoLigacao(GrupoVisaoCamposNegocioDTO.RELATION_SIMPLE);
        }

        final Collection colPresentation = new ArrayList<>();
        final Collection colValue = new ArrayList<>();
        final Collection colOrder = new ArrayList<>();
        final Collection colFilter = new ArrayList<>();

        String sql = "";
        if (grupoVisaoCamposNegocioDTO.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioDTO.RELATION_SIMPLE)
                || grupoVisaoCamposNegocioDTO.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioDTO.RELATION_COMBO)
                || grupoVisaoCamposNegocioDTO.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioDTO.RELATION_NONE)) {
            final Collection colItens = grupoVisaoCamposNegocioLigacaoDao.findByIdGrupoVisaoAndIdCamposObjetoNegocio(parm.getIdGrupoVisao(),
                    parm.getIdCamposObjetoNegocio());
            if (colItens != null) {
                for (final Iterator it = colItens.iterator(); it.hasNext();) {
                    final GrupoVisaoCamposNegocioLigacaoDTO grupoVisaoCamposNegocioLigacaoAux = (GrupoVisaoCamposNegocioLigacaoDTO) it.next();

                    if (grupoVisaoCamposNegocioLigacaoAux.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioLigacaoDTO.PRESENTATION)) {
                        camposObjetoNegocioDTO.setIdCamposObjetoNegocio(grupoVisaoCamposNegocioLigacaoAux.getIdCamposObjetoNegocioLigacao());
                        camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) camposObjetoNegocioDao.restore(camposObjetoNegocioDTO);
                        if (camposObjetoNegocioDTO != null) {
                            camposObjetoNegocioDTO.setDescricao(grupoVisaoCamposNegocioLigacaoAux.getDescricao());
                            colPresentation.add(camposObjetoNegocioDTO);
                        }
                    }
                    if (grupoVisaoCamposNegocioLigacaoAux.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioLigacaoDTO.VALUE)) {
                        camposObjetoNegocioDTO.setIdCamposObjetoNegocio(grupoVisaoCamposNegocioLigacaoAux.getIdCamposObjetoNegocioLigacao());
                        camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) camposObjetoNegocioDao.restore(camposObjetoNegocioDTO);
                        if (camposObjetoNegocioDTO != null) {
                            colValue.add(camposObjetoNegocioDTO);
                        }
                    }
                    if (grupoVisaoCamposNegocioLigacaoAux.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioLigacaoDTO.ORDER)) {
                        camposObjetoNegocioDTO.setIdCamposObjetoNegocio(grupoVisaoCamposNegocioLigacaoAux.getIdCamposObjetoNegocioLigacao());
                        camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) camposObjetoNegocioDao.restore(camposObjetoNegocioDTO);
                        if (camposObjetoNegocioDTO != null) {
                            colOrder.add(camposObjetoNegocioDTO);
                        }
                    }
                    if (grupoVisaoCamposNegocioLigacaoAux.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioLigacaoDTO.FILTER)) {
                        camposObjetoNegocioDTO.setIdCamposObjetoNegocio(grupoVisaoCamposNegocioLigacaoAux.getIdCamposObjetoNegocioLigacao());
                        camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) camposObjetoNegocioDao.restore(camposObjetoNegocioDTO);
                        if (camposObjetoNegocioDTO != null) {
                            camposObjetoNegocioDTO.setFiltro(grupoVisaoCamposNegocioLigacaoAux.getFiltro().replace("\"", "'"));
                            colFilter.add(camposObjetoNegocioDTO);
                        }
                    }
                }
            }

            sql = this.generateSQL(colPresentation, colValue, colFilter, colOrder, parm.getTermoPesquisa());
        }

        final Collection colGeral = new ArrayList<>();
        if (colValue != null) {
            colGeral.addAll(colValue);
        }
        if (colPresentation != null) {
            colGeral.addAll(colPresentation);
        }
        final Collection colRetorno = this.getDao().execSQL(sql, null);
        new ArrayList<>();
        String retorno = "";
        boolean prim = true;
        if (colRetorno != null) {
            colValue.size();
            for (final Iterator it = colRetorno.iterator(); it.hasNext();) {
                final Object[] objs = (Object[]) it.next();
                if (!prim) {
                    retorno += ",";
                }
                retorno += "{";
                final Iterator it2 = colGeral.iterator();

                for (int i = 0; i < objs.length; i++) {
                    if (i > 0) {
                        retorno += ",";
                    }
                    if (it2.hasNext()) {
                        camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) it2.next();
                        retorno += "\"" + camposObjetoNegocioDTO.getNomeDB() + "\":";
                        retorno += "\"" + StringEscapeUtils.escapeJava(objs[i].toString()) + "\"";
                    }
                }
                retorno += "}";
                prim = false;
            }
        }
        return retorno;
    }

    public ReturnLookupDTO restoreSimple(final LookupDTO parm) throws Exception {
        final GrupoVisaoCamposNegocioDao grupoVisaoCamposNegocioDao = new GrupoVisaoCamposNegocioDao();
        GrupoVisaoCamposNegocioDTO grupoVisaoCamposNegocioDTO = new GrupoVisaoCamposNegocioDTO();

        final GrupoVisaoCamposNegocioLigacaoDao grupoVisaoCamposNegocioLigacaoDao = new GrupoVisaoCamposNegocioLigacaoDao();
        new GrupoVisaoCamposNegocioLigacaoDTO();

        final CamposObjetoNegocioDao camposObjetoNegocioDao = new CamposObjetoNegocioDao();
        CamposObjetoNegocioDTO camposObjetoNegocioDTO = new CamposObjetoNegocioDTO();

        grupoVisaoCamposNegocioDTO.setIdGrupoVisao(parm.getIdGrupoVisao());
        grupoVisaoCamposNegocioDTO.setIdCamposObjetoNegocio(parm.getIdCamposObjetoNegocio());
        grupoVisaoCamposNegocioDTO = (GrupoVisaoCamposNegocioDTO) grupoVisaoCamposNegocioDao.restore(grupoVisaoCamposNegocioDTO);
        if (grupoVisaoCamposNegocioDTO == null) {
            return null;
        }

        if (grupoVisaoCamposNegocioDTO.getTipoLigacao() == null) {
            grupoVisaoCamposNegocioDTO.setTipoLigacao(GrupoVisaoCamposNegocioDTO.RELATION_SIMPLE);
        }

        final Collection colPresentation = new ArrayList<>();
        final Collection colValue = new ArrayList<>();
        final Collection colOrder = new ArrayList<>();
        final Collection colFilter = new ArrayList<>();

        String sql = "";
        final Collection colItens = grupoVisaoCamposNegocioLigacaoDao.findByIdGrupoVisaoAndIdCamposObjetoNegocio(parm.getIdGrupoVisao(),
                parm.getIdCamposObjetoNegocio());
        if (colItens != null) {
            for (final Iterator it = colItens.iterator(); it.hasNext();) {
                final GrupoVisaoCamposNegocioLigacaoDTO grupoVisaoCamposNegocioLigacaoAux = (GrupoVisaoCamposNegocioLigacaoDTO) it.next();

                if (grupoVisaoCamposNegocioLigacaoAux.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioLigacaoDTO.PRESENTATION)) {
                    camposObjetoNegocioDTO.setIdCamposObjetoNegocio(grupoVisaoCamposNegocioLigacaoAux.getIdCamposObjetoNegocioLigacao());
                    camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) camposObjetoNegocioDao.restore(camposObjetoNegocioDTO);
                    if (camposObjetoNegocioDTO != null) {
                        camposObjetoNegocioDTO.setDescricao(grupoVisaoCamposNegocioLigacaoAux.getDescricao());
                        colPresentation.add(camposObjetoNegocioDTO);
                    }
                }
                if (grupoVisaoCamposNegocioLigacaoAux.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioLigacaoDTO.VALUE)) {
                    camposObjetoNegocioDTO.setIdCamposObjetoNegocio(grupoVisaoCamposNegocioLigacaoAux.getIdCamposObjetoNegocioLigacao());
                    camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) camposObjetoNegocioDao.restore(camposObjetoNegocioDTO);
                    if (camposObjetoNegocioDTO != null) {
                        camposObjetoNegocioDTO.setFiltro("${TERMO_PESQUISA}");
                        colValue.add(camposObjetoNegocioDTO);
                        colFilter.add(camposObjetoNegocioDTO);
                    }
                }
                if (grupoVisaoCamposNegocioLigacaoAux.getTipoLigacao().equalsIgnoreCase(GrupoVisaoCamposNegocioLigacaoDTO.ORDER)) {
                    camposObjetoNegocioDTO.setIdCamposObjetoNegocio(grupoVisaoCamposNegocioLigacaoAux.getIdCamposObjetoNegocioLigacao());
                    camposObjetoNegocioDTO = (CamposObjetoNegocioDTO) camposObjetoNegocioDao.restore(camposObjetoNegocioDTO);
                    if (camposObjetoNegocioDTO != null) {
                        colOrder.add(camposObjetoNegocioDTO);
                    }
                }
            }
        }

        sql = this.generateSQL(colPresentation, colValue, colFilter, colOrder, parm.getTermoPesquisa());

        final Collection colRetorno = this.getDao().execSQL(sql, null);
        final Collection colRetornoLookup = new ArrayList<>();
        if (colRetorno != null) {
            final int tamCamposValue = colValue.size();
            for (final Iterator it = colRetorno.iterator(); it.hasNext();) {
                String lineLabel = "";
                String lineValue = "";
                final ReturnLookupDTO returnLookupDTO = new ReturnLookupDTO();
                final Object[] objs = (Object[]) it.next();
                for (int i = 0; i < objs.length; i++) {
                    if (i >= tamCamposValue) {
                        if (!lineLabel.equalsIgnoreCase("")) {
                            lineLabel += ", ";
                        }
                        lineLabel += objs[i].toString();
                    } else {
                        if (!lineValue.equalsIgnoreCase("")) {
                            lineValue += "#";
                        }
                        lineValue += objs[i].toString();
                    }
                }
                returnLookupDTO.setLabel(lineLabel);
                returnLookupDTO.setValue(lineValue);

                colRetornoLookup.add(returnLookupDTO);
            }
        }

        if (colRetornoLookup == null) {
            return null;
        }
        if (colRetornoLookup.size() == 0) {
            return null;
        }

        return (ReturnLookupDTO) ((List) colRetornoLookup).get(0);
    }

    private String generateSQL(final Collection colPresentation, final Collection colValue, final Collection colFilter, final Collection colOrder,
            final String termoPesquisa) throws Exception {
        String sql = "SELECT ";

        sql += this.generateFields(colPresentation, colValue);
        sql += " FROM ";
        sql += this.generateFromWithRelatios(colPresentation, colFilter);

        final String strFilter = this.generateFilter(colFilter, termoPesquisa);
        if (!strFilter.equalsIgnoreCase("")) {
            sql += " WHERE " + strFilter;
        }

        final String strOrder = this.generateOrder(colOrder);
        if (!strOrder.equalsIgnoreCase("")) {
            sql += " ORDER BY " + strOrder;
        }

        return sql;
    }

    private String generateFields(final Collection colPresentation, final Collection colValue) throws Exception {
        final ObjetoNegocioDao objetoNegocioDao = new ObjetoNegocioDao();
        String sqlFields = "";
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
                        sqlFilter += " AND ";
                    }
                    String pref = "";
                    String suf = "";
                    String comp = "=";
                    if (MetaUtil.isStringType(camposObjetoNegocioDTO.getTipoDB())) {
                        pref = "'%";
                        suf = "%'";
                        comp = "LIKE";
                    }
                    if (camposObjetoNegocioDTO.getFiltro() != null
                            && (camposObjetoNegocioDTO.getFiltro().equalsIgnoreCase("${TERMO_PESQUISA}") || camposObjetoNegocioDTO.getFiltro()
                                    .equalsIgnoreCase("${termo_pesquisa}"))) {
                        if (termoPesquisa != null && !termoPesquisa.trim().equalsIgnoreCase("")) {
                            sqlFilter += objetoNegocioDTO.getNomeTabelaDB() + "." + camposObjetoNegocioDTO.getNomeDB() + " " + comp + " " + pref
                                    + termoPesquisa.trim() + suf;
                        }
                    } else {
                        if (camposObjetoNegocioDTO.getFiltro() != null) {
                            if (!camposObjetoNegocioDTO.getFiltro().trim().equalsIgnoreCase("")) {
                                sqlFilter += camposObjetoNegocioDTO.getFiltro();
                            }
                            if (termoPesquisa != null && !termoPesquisa.trim().equalsIgnoreCase("")) {
                                String strAnd = "";
                                if (!sqlFilter.trim().equalsIgnoreCase("")) {
                                    strAnd = " AND ";
                                }
                                sqlFilter = sqlFilter.replaceAll("\\$\\{TERMO_PESQUISA\\}", strAnd + objetoNegocioDTO.getNomeTabelaDB() + "."
                                        + camposObjetoNegocioDTO.getNomeDB() + " " + comp + " " + pref + termoPesquisa.trim() + suf);
                                sqlFilter = sqlFilter.replaceAll("\\$\\{termo_pesquisa\\}", strAnd + objetoNegocioDTO.getNomeTabelaDB() + "."
                                        + camposObjetoNegocioDTO.getNomeDB() + " " + comp + " " + pref + termoPesquisa.trim() + suf);
                            }
                        }
                    }
                }
            }
        }
        sqlFilter = sqlFilter.replaceAll("\\$\\{TERMO_PESQUISA\\}", "");
        sqlFilter = sqlFilter.replaceAll("\\$\\{termo_pesquisa\\}", "");
        return sqlFilter;
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
                    sqlOrder += objetoNegocioDTO.getNomeTabelaDB() + "." + camposObjetoNegocioDTO.getNomeDB();
                }
            }
        }
        return sqlOrder;
    }

}
