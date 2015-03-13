/**
 *
 */
package br.com.centralit.citcorpore.ajaxForms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citajax.html.AjaxFormAction;
import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citajax.html.HTMLForm;
import br.com.centralit.citcorpore.bean.CaracteristicaDTO;
import br.com.centralit.citcorpore.bean.EmpregadoDTO;
import br.com.centralit.citcorpore.bean.GrupoItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.ItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.MidiaSoftwareDTO;
import br.com.centralit.citcorpore.bean.TipoItemConfiguracaoDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.negocio.CaracteristicaService;
import br.com.centralit.citcorpore.negocio.EmpregadoService;
import br.com.centralit.citcorpore.negocio.GrupoItemConfiguracaoService;
import br.com.centralit.citcorpore.negocio.ItemConfiguracaoService;
import br.com.centralit.citcorpore.negocio.MidiaSoftwareService;
import br.com.centralit.citcorpore.negocio.TipoItemConfiguracaoService;
import br.com.centralit.citcorpore.negocio.UsuarioService;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.UtilI18N;
import br.com.citframework.util.WebUtil;

/**
 * @author thays.araujo
 *
 */
@SuppressWarnings("rawtypes")
public class ItemConfiguracao extends AjaxFormAction {

    /** Bean de Base Item Configuração. */
    private ItemConfiguracaoDTO itemConfiguracaoBean;

    /** Bean de Tipo Item Configuração. */
    private TipoItemConfiguracaoDTO tipoItemConfiguracaoBean = new TipoItemConfiguracaoDTO();

    /** Bean de Usuario */
    private UsuarioDTO UsuarioDto = new UsuarioDTO();

    /** Bean Empregado. */
    private EmpregadoDTO empregadoDTO = new EmpregadoDTO();

    @Override
    public Class<ItemConfiguracaoDTO> getBeanClass() {
        return ItemConfiguracaoDTO.class;
    }

    /*
     * (non-Javadoc)
     * @see
     * br.com.centralit.citajax.html.AjaxFormAction#load(br.com.centralit.citajax
     * .html.DocumentHTML, javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void load(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usrDto = br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);
        if (usrDto == null) {
            return;
        }
        document.executeScript("ocultarItemConfiguracao()");

    }

    /**
     * Inclui Novo Item de Configuração na Base.
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public boolean salvar(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usrDto = br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);
        if (usrDto == null) {
            return false;
        }
        final MidiaSoftwareService midiaSoftwareService = (MidiaSoftwareService) ServiceLocator.getInstance().getService(MidiaSoftwareService.class, null);

        setItemConfiguracaoBean((BaseEntity) document.getBean());
        /**
         * Checa qual tipo de responsável para gravar no banco o ID correspondente
         *
         * @author thyen.chang
         */
        if (getItemConfiguracaoBean().getTipoResponsavel().equals("U")) {
            getItemConfiguracaoBean().setIdGrupoResponsavel(null);
        } else {
            getItemConfiguracaoBean().setIdGrupoResponsavel(getItemConfiguracaoBean().getIdGrupoResponsavel());
            getItemConfiguracaoBean().setIdResponsavel(null);
        }

        getTipoItemConfiguracaoBean().setCaracteristicas(
                (List) WebUtil.deserializeCollectionFromRequest(CaracteristicaDTO.class, "caracteristicasSerializadas", request));
        getItemConfiguracaoBean().setTipoItemConfiguracaoSerializadas(getTipoItemConfiguracaoBean());
        if (getItemConfiguracaoBean().getIdItemConfiguracaoPai() != null) {
            ItemConfiguracaoDTO i = new ItemConfiguracaoDTO();
            i.setIdItemConfiguracao(getItemConfiguracaoBean().getIdItemConfiguracaoPai());
            i = (ItemConfiguracaoDTO) getItemConfiguracaoService().restore(i);
            getItemConfiguracaoBean().setIdGrupoItemConfiguracao(i.getIdGrupoItemConfiguracao());
        }

        if (getItemConfiguracaoBean().getIdItemConfiguracao() == null) {
            /*
             * Alteracao - Emauri - 27/11/2013
             */
            // ThreadProcessaInventario.performanceDataSemaphoreInventario.acquireUninterruptibly();
            try {
                getItemConfiguracaoService().createItemConfiguracaoAplicacao(getItemConfiguracaoBean(), usrDto);
            } catch (final Exception e) {
                e.printStackTrace();
            } finally {
                // ThreadProcessaInventario.performanceDataSemaphoreInventario.release();
            }
            /*
             * Fim - Alteracao - Emauri - 27/11/2013
             */
            document.alert(UtilI18N.internacionaliza(request, "MSG05"));
            document.executeScript("parent.JANELA_AGUARDE_MENU.hide()");

        } else {
            /* Verificando se as mídias estão esgotadas */
            if (getItemConfiguracaoBean().getIdMidiaSoftware() != null) {
                MidiaSoftwareDTO midia = new MidiaSoftwareDTO();
                midia.setIdMidiaSoftware(getItemConfiguracaoBean().getIdMidiaSoftware());
                midia = (MidiaSoftwareDTO) midiaSoftwareService.restore(midia);

                if (midia.getLicencas() <= getItemConfiguracaoService().quantidadeMidiaSoftware(getItemConfiguracaoBean())) {
                    document.alert(UtilI18N.internacionaliza(request, "itemConfiguracaoTree.licencaEsgotada"));
                    document.executeScript("parent.JANELA_AGUARDE_MENU.hide()");
                    return false;
                } else {
                    /*
                     * Alteracao - Emauri - 27/11/2013
                     */
                    // ThreadProcessaInventario.performanceDataSemaphoreInventario.acquireUninterruptibly();
                    try {
                        getItemConfiguracaoService().updateItemConfiguracao(getItemConfiguracaoBean(), usrDto);
                    } catch (final Exception e) {
                        e.printStackTrace();
                    } finally {
                        // ThreadProcessaInventario.performanceDataSemaphoreInventario.release();
                    }
                    /*
                     * Fim - Alteracao - Emauri - 27/11/2013
                     */
                    document.alert(UtilI18N.internacionaliza(request, "MSG06"));
                    document.executeScript("parent.JANELA_AGUARDE_MENU.hide()");
                }
            } else {
                getItemConfiguracaoService().updateItemConfiguracao(getItemConfiguracaoBean(), usrDto);
                document.alert(UtilI18N.internacionaliza(request, "MSG06"));
                document.executeScript("parent.JANELA_AGUARDE_MENU.hide()");
            }
        }
        if (getItemConfiguracaoBean().getIdItemConfiguracaoPai() == null) {
            document.executeScript("ocultaGrid();reload(" + getItemConfiguracaoBean().getIdItemConfiguracao() + ")");
        } else {
            document.executeScript("ocultaGrid();reload(" + getItemConfiguracaoBean().getIdItemConfiguracaoPai() + ")");
        }
        return true;

    }

    public UsuarioDTO getUsuarioSessao(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);
    }

    /**
     * Restaura Item de Configuracao.
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public void restore(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usrDto = br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);
        if (usrDto == null) {
            return;
        }
        setItemConfiguracaoBean((BaseEntity) document.getBean());
        ItemConfiguracaoDTO itemConfiguracaoPai = new ItemConfiguracaoDTO();

        setItemConfiguracaoBean(getItemConfiguracaoService().restore(getItemConfiguracaoBean()));

        document.executeScript("deleteAllRows()");
        final HTMLForm form = CITCorporeUtil.limparFormulario(document);
        form.setValues(getItemConfiguracaoBean());

        if (getItemConfiguracaoBean().getIdItemConfiguracaoPai() == null) {
            document.executeScript("ocultarItemConfiguracao()");

        } else {
            getTipoItemConfiguracaoBean().setId(getItemConfiguracaoBean().getIdTipoItemConfiguracao());
            getItemConfiguracaoBean().setNomeTipoItemConfiguracao(
                    ((TipoItemConfiguracaoDTO) getTipoItemConfiguracaoService().restore(getTipoItemConfiguracaoBean())).getNome());
            getTipoItemConfiguracaoService().restaurarGridCaracteristicas(
                    document,
                    getCaracteristicaService().consultarCaracteristicasComValoresItemConfiguracao(getItemConfiguracaoBean().getIdTipoItemConfiguracao(),
                            getItemConfiguracaoBean().getIdItemConfiguracao()));
            itemConfiguracaoPai.setIdItemConfiguracao(getItemConfiguracaoBean().getIdItemConfiguracaoPai());
            itemConfiguracaoPai = (ItemConfiguracaoDTO) getItemConfiguracaoService().restore(itemConfiguracaoPai);
            getItemConfiguracaoBean().setNomeItemConfiguracaoPai(itemConfiguracaoPai.getIdentificacao());
            document.executeScript("visualizarItemConfiguracaoPai()");

        }

        if (getItemConfiguracaoBean() != null) {
            if (getItemConfiguracaoBean().getIdGrupoItemConfiguracao() != null && getItemConfiguracaoBean().getIdGrupoItemConfiguracao() > 0) {
                final GrupoItemConfiguracaoService grupoItemConfiguracaoService = (GrupoItemConfiguracaoService) ServiceLocator.getInstance().getService(
                        GrupoItemConfiguracaoService.class, null);
                GrupoItemConfiguracaoDTO grupoItemConfiguracaoDTO = new GrupoItemConfiguracaoDTO();
                grupoItemConfiguracaoDTO.setIdGrupoItemConfiguracao(getItemConfiguracaoBean().getIdGrupoItemConfiguracao());
                grupoItemConfiguracaoDTO = (GrupoItemConfiguracaoDTO) grupoItemConfiguracaoService.restore(grupoItemConfiguracaoDTO);
                getItemConfiguracaoBean().setNomeGrupoItemConfiguracao(grupoItemConfiguracaoDTO.getNomeGrupoItemConfiguracao());
            }
        }

        form.setValues(getItemConfiguracaoBean());
    }

    /**
     * Restaura o Tipo de Item Configuração e carrega a Grid de Características
     * Ativas.
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public void restoreTipoItemConfiguracao(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        restoreTipoItemConfiguracaoValues(document, request, response);
    }

    /**
     * Restaura o Tipo de Item Configuração e carrega a Grid de Características
     * Ativas.
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     * @author flavio.santana
     */
    public void restoreTipoItemConfiguracaoValues(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        final UsuarioDTO usrDto = br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);
        if (usrDto == null) {
            return;
        }

        setItemConfiguracaoBean((BaseEntity) document.getBean());
        if (getItemConfiguracaoBean().getIdTipoItemConfiguracao() == null) {
            setItemConfiguracaoBean(getItemConfiguracaoService().restoreByIdItemConfiguracao(getItemConfiguracaoBean().getIdItemConfiguracao()));
        }
        getTipoItemConfiguracaoBean().setId(getItemConfiguracaoBean().getIdTipoItemConfiguracao());
        setTipoItemConfiguracaoBean(getTipoItemConfiguracaoService().restore(getTipoItemConfiguracaoBean()));
        /**
         * Valida se o campo é diferente de null
         *
         * @author flavio.santana
         *         25/10/2013 11:40
         */
        if (getTipoItemConfiguracaoBean() != null) {
            getItemConfiguracaoBean().setNomeTipoItemConfiguracao(getTipoItemConfiguracaoBean().getNome());
        }

        document.executeScript("deleteAllRows()");
        final HTMLForm form = document.getForm("form");
        form.setValues(getItemConfiguracaoBean());

        if (getTipoItemConfiguracaoBean() != null) {
            getTipoItemConfiguracaoService().restaurarGridCaracteristicas(
                    document,
                    getCaracteristicaService().consultarCaracteristicasComValoresItemConfiguracao(getTipoItemConfiguracaoBean().getId(),
                            getItemConfiguracaoBean().getIdItemConfiguracao()));
        }
    }

    public void restoreUnidade(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usrDto = br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);
        if (usrDto == null) {
            return;
        }
        setItemConfiguracaoBean((BaseEntity) document.getBean());
        setUsuarioDto(getUsuarioService().restore(getUsuarioDto()));
        getEmpregadoDto().setIdEmpregado(getUsuarioDto().getIdEmpregado());
        setEmpregado(getEmpregadoService().restore(getEmpregadoDto()));

        final HTMLForm form = document.getForm("form");
        form.setValues(getItemConfiguracaoBean());
    }

    /**
     * Restaura o Grupo do Item de Configuracao.
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     */
    public void restoreGrupoItemConfiguracao(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        final UsuarioDTO usrDto = br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);
        if (usrDto == null) {
            return;
        }
        final GrupoItemConfiguracaoService grupoItemConfiguracaoService = (GrupoItemConfiguracaoService) ServiceLocator.getInstance().getService(
                GrupoItemConfiguracaoService.class, null);

        final ItemConfiguracaoDTO itemConfiguracaoDTO = (ItemConfiguracaoDTO) (BaseEntity) document.getBean();
        GrupoItemConfiguracaoDTO grupoItemConfiguracaoDTO = new GrupoItemConfiguracaoDTO();

        grupoItemConfiguracaoDTO.setIdGrupoItemConfiguracao(itemConfiguracaoDTO.getIdGrupoItemConfiguracao());

        grupoItemConfiguracaoDTO = (GrupoItemConfiguracaoDTO) grupoItemConfiguracaoService.restore(grupoItemConfiguracaoDTO);

        itemConfiguracaoDTO.setIdGrupoItemConfiguracao(grupoItemConfiguracaoDTO.getIdGrupoItemConfiguracao());
        itemConfiguracaoDTO.setNomeGrupoItemConfiguracao(grupoItemConfiguracaoDTO.getNomeGrupoItemConfiguracao());

        final HTMLForm form = document.getForm("form");
        form.setValues(itemConfiguracaoDTO);

        document.executeScript("fecharPopupGrupo()");
    }

    /**
     * @param document
     * @param request
     * @param response
     * @throws Exception
     *             Metodo colocar status Data fim quando for solicitado a
     *             exclusão do ItemConfiguracao.
     * @author thays.araujo
     */

    public void delete(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usrDto = br.com.centralit.citcorpore.util.WebUtil.getUsuario(request);
        if (usrDto == null) {
            return;
        }
        setItemConfiguracaoBean((BaseEntity) document.getBean());

        if (getItemConfiguracaoBean().getIdItemConfiguracao().intValue() > 0) {
            getItemConfiguracaoService().delete(getItemConfiguracaoBean());
        }

        final HTMLForm form = document.getForm("form");
        document.executeScript("deleteAllRows()");
        form.clear();
        document.alert(UtilI18N.internacionaliza(request, "MSG07"));
    }

    /**
     * Retorna Service de ItemConfiguracao.
     *
     * @return ItemConfiguracaoService
     * @throws ServiceException
     * @throws Exception
     * @author thays.araujo
     */
    private ItemConfiguracaoService getItemConfiguracaoService() throws ServiceException, Exception {
        return (ItemConfiguracaoService) ServiceLocator.getInstance().getService(ItemConfiguracaoService.class, null);
    }

    /**
     * Retorna Service de TipoItemConfiguracao.
     *
     * @return TipoItemConfiguracaoService
     * @throws ServiceException
     * @throws Exception
     * @author thays.araujo
     */
    private TipoItemConfiguracaoService getTipoItemConfiguracaoService() throws ServiceException, Exception {
        return (TipoItemConfiguracaoService) ServiceLocator.getInstance().getService(TipoItemConfiguracaoService.class, null);
    }

    /**
     * Retorna Service de Caracteristica.
     *
     * @return CaracteristicaService
     * @throws ServiceException
     * @throws Exception
     * @author thays.araujo
     */
    private CaracteristicaService getCaracteristicaService() throws ServiceException, Exception {
        return (CaracteristicaService) ServiceLocator.getInstance().getService(CaracteristicaService.class, null);
    }

    /**
     * Retorna Service de Usuario.
     *
     * @return UsuarioService
     * @throws ServiceException
     * @throws Exception
     * @author thays.araujo
     */
    private UsuarioService getUsuarioService() throws ServiceException, Exception {
        return (UsuarioService) ServiceLocator.getInstance().getService(UsuarioService.class, null);
    }

    /**
     * Configura bean ItemConfiguracaoDTO.
     *
     * @param itemConfiguracaoBean
     * @author thays.araujo
     */
    protected void setItemConfiguracaoBean(final BaseEntity itemConfiguracaoBean) {
        this.itemConfiguracaoBean = (ItemConfiguracaoDTO) itemConfiguracaoBean;
    }

    /**
     * Retorna bean de ItemConfiguracaoDTO.
     *
     * @return ItemConfiguracaoDTO
     * @author thays.araujo
     */
    protected ItemConfiguracaoDTO getItemConfiguracaoBean() {
        return itemConfiguracaoBean;
    }

    /**
     * Retorna Bean Tipo Item Configuração.
     *
     * @return TipoItemConfiguracaoDTO
     * @author thays.araujo
     */
    private TipoItemConfiguracaoDTO getTipoItemConfiguracaoBean() {
        return tipoItemConfiguracaoBean;
    }

    /**
     * Retorna Bean Usuario.
     *
     * @return TipoItemConfiguracaoDTO
     * @author thays.araujo
     */
    private UsuarioDTO getUsuarioDto() {
        return UsuarioDto;
    }

    /**
     * Retorna Bean Empregado.
     *
     * @return TipoItemConfiguracaoDTO
     * @author thays.araujo
     */
    private EmpregadoDTO getEmpregadoDto() {
        return empregadoDTO;
    }

    /**
     * Configura Empregado.
     *
     * @param empregadoDto
     * @author thays.araujo
     */
    private void setEmpregado(final BaseEntity empregadoDto) {
        empregadoDTO = (EmpregadoDTO) empregadoDto;
    }

    /**
     * Configura Tipo Item Configuração.
     *
     * @param tipoItemConfiguracao
     * @author thays.araujo
     */
    private void setTipoItemConfiguracaoBean(final BaseEntity tipoItemConfiguracao) {
        tipoItemConfiguracaoBean = (TipoItemConfiguracaoDTO) tipoItemConfiguracao;
    }

    /**
     * Configura Usuario.
     *
     * @param usuarioDto
     * @author thays.araujo
     */
    private void setUsuarioDto(final BaseEntity usuarioDto) {
        UsuarioDto = (UsuarioDTO) usuarioDto;
    }

    /**
     * Retorna Service de Empregado.
     *
     * @return InformacaoItemConfiguracaoService
     * @throws ServiceException
     * @throws Exception
     * @author rosana.godinho
     */
    private EmpregadoService getEmpregadoService() throws ServiceException, Exception {
        return (EmpregadoService) ServiceLocator.getInstance().getService(EmpregadoService.class, null);
    }

}
