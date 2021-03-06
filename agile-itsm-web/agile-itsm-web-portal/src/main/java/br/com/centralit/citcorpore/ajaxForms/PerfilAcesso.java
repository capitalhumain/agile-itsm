package br.com.centralit.citcorpore.ajaxForms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citajax.html.AjaxFormAction;
import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citajax.html.HTMLElement;
import br.com.centralit.citajax.html.HTMLForm;
import br.com.centralit.citcorpore.bean.MenuDTO;
import br.com.centralit.citcorpore.bean.PerfilAcessoDTO;
import br.com.centralit.citcorpore.bean.PerfilAcessoMenuDTO;
import br.com.centralit.citcorpore.bean.PerfilAcessoSituacaoFaturaDTO;
import br.com.centralit.citcorpore.bean.PerfilAcessoSituacaoOSDTO;
import br.com.centralit.citcorpore.negocio.MenuService;
import br.com.centralit.citcorpore.negocio.PerfilAcessoGrupoService;
import br.com.centralit.citcorpore.negocio.PerfilAcessoMenuService;
import br.com.centralit.citcorpore.negocio.PerfilAcessoService;
import br.com.centralit.citcorpore.negocio.PerfilAcessoSituacaoFaturaService;
import br.com.centralit.citcorpore.negocio.PerfilAcessoSituacaoOSService;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.UtilI18N;

@SuppressWarnings({"unchecked", "rawtypes", "unused"})
public class PerfilAcesso extends AjaxFormAction {

    private PerfilAcessoDTO perfilAcessoBean;

    private MenuDTO menuBean;

    private PerfilAcessoMenuDTO acessoMenuBean;

    private static final String INTERROGACAO = "?";

    @Override
    public Class getBeanClass() {
        return PerfilAcessoDTO.class;
    }

    @Override
    public void load(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        this.montarArvoreDeMenus(document, request, response);
        document.focusInFirstActivateField(null);
    }

    /**
     * Inclui Novo Pesfil Acesso e AcessoMenu.
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     * @author thays.araujo
     */
    public void save(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        this.setPerfilAcessoBean((PerfilAcessoDTO) document.getBean());

        final String[] acessoMenuSerializados = this.getPerfilAcessoBean().getAcessoMenuSerializados().split(";");
        final Collection<PerfilAcessoMenuDTO> colPerfilAcessoMenuDTO = new ArrayList<PerfilAcessoMenuDTO>();
        for (final String menus : acessoMenuSerializados) {
            final String[] aux = menus.split("@");
            final PerfilAcessoMenuDTO perfilAcessoMenu = new PerfilAcessoMenuDTO();
            perfilAcessoMenu.setIdMenu(new Integer(aux[0].trim()));
            final String[] tiposAcesso = aux[1].split("-");
            perfilAcessoMenu.setGrava(tiposAcesso[1]);
            perfilAcessoMenu.setPesquisa(tiposAcesso[0]);
            perfilAcessoMenu.setDeleta(tiposAcesso[2]);
            colPerfilAcessoMenuDTO.add(perfilAcessoMenu);
            this.getPerfilAcessoBean().setAcessoMenus(colPerfilAcessoMenuDTO);
        }

        if (this.getPerfilAcessoBean().getIdPerfilAcesso() == null) {
            if (this.getPerfilAcessoService().verificarSePerfilAcessoExiste(this.getPerfilAcessoBean())) {
                document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.registroJaCadastrado"));
            } else {
                this.getPerfilAcessoService().create(this.getPerfilAcessoBean());
                document.alert(UtilI18N.internacionaliza(request, "MSG05"));
            }
        } else {
            if (this.getPerfilAcessoService().verificarSePerfilAcessoExiste(this.getPerfilAcessoBean())) {
                document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.registroJaCadastrado"));
            } else {
                this.getPerfilAcessoService().update(this.getPerfilAcessoBean());
                document.alert(UtilI18N.internacionaliza(request, "MSG06"));
            }
        }
        final HTMLForm form = document.getForm("form");
        form.clear();

        document.executeScript("limpar_LOOKUP_PERFILACESSO()");
    }

    public void restore(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        this.setPerfilAcessoBean((PerfilAcessoDTO) document.getBean());
        this.setPerfilAcessoBean(this.getPerfilAcessoService().restore(this.getPerfilAcessoBean()));
        final HTMLForm form = document.getForm("form");
        form.clear();
        form.setValues(this.getPerfilAcessoBean());

        final PerfilAcessoSituacaoOSService perfilAcessoSituacaoOSService = (PerfilAcessoSituacaoOSService) ServiceLocator.getInstance().getService(
                PerfilAcessoSituacaoOSService.class, null);
        final PerfilAcessoSituacaoFaturaService perfilAcessoSituacaoFaturaService = (PerfilAcessoSituacaoFaturaService) ServiceLocator.getInstance()
                .getService(PerfilAcessoSituacaoFaturaService.class, null);

        final Collection colSituacoesOSPerfil = perfilAcessoSituacaoOSService.findByIdPerfil(this.getPerfilAcessoBean().getIdPerfilAcesso());
        final Collection colSituacoesFaturaPerfil = perfilAcessoSituacaoFaturaService.findByIdPerfil(this.getPerfilAcessoBean().getIdPerfilAcesso());

        String[] valuesOs = null;
        if (colSituacoesOSPerfil != null && colSituacoesOSPerfil.size() > 0) {
            valuesOs = new String[colSituacoesOSPerfil.size()];
            int i = 0;
            for (final Iterator it = colSituacoesOSPerfil.iterator(); it.hasNext();) {
                final PerfilAcessoSituacaoOSDTO perfilAcessoSituacaoOSDTO = (PerfilAcessoSituacaoOSDTO) it.next();
                valuesOs[i] = "" + perfilAcessoSituacaoOSDTO.getSituacaoOs();
                if (valuesOs[i] != null) {
                    document.getCheckboxById("situacaoos" + valuesOs[i].toString() + "").setValue(valuesOs[i]);
                }
                i++;
            }
        }
        /*
         * if (valuesOs != null) { document.getCheckboxById("situacaoos").setValue(valuesOs); }
         */
        String[] valuesFatura = null;
        if (colSituacoesFaturaPerfil != null && colSituacoesFaturaPerfil.size() > 0) {
            valuesFatura = new String[colSituacoesFaturaPerfil.size()];
            int i = 0;
            for (final Iterator it = colSituacoesFaturaPerfil.iterator(); it.hasNext();) {
                final PerfilAcessoSituacaoFaturaDTO perfilAcessoSituacaoFaturaDTO = (PerfilAcessoSituacaoFaturaDTO) it.next();
                valuesFatura[i] = "" + perfilAcessoSituacaoFaturaDTO.getSituacaoFatura();
                if (valuesFatura[i] != null) {
                    document.getCheckboxById("situacaoFatura" + valuesFatura[i].toString() + "").setValue(valuesFatura[i]);
                }

                i++;
            }
        }
        /*
         * if (valuesFatura != null) { document.getCheckboxById("situacaoFatura").setValue(valuesFatura); }
         */

        this.montarArvoreDeMenus(document, request, response);
        document.executeScript("JANELA_AGUARDE_MENU.hide();");
    }

    /**
     * Exclui Perfil Acesso atribuindo sua data fim em Grupo.
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     */
    public void delete(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        this.setPerfilAcessoBean((PerfilAcessoDTO) document.getBean());

        final PerfilAcessoService perfilAcessoService = (PerfilAcessoService) ServiceLocator.getInstance().getService(PerfilAcessoService.class, null);

        if (this.getPerfilAcessoBean().getIdPerfilAcesso() != null) {

            if (perfilAcessoService.excluirPerfilDeAcesso(this.getPerfilAcessoBean())) {
                document.alert(UtilI18N.internacionaliza(request, "MSG07"));
            } else {
                document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.registroNaoPodeSerExcluido"));
            }
        }

        final HTMLForm form = document.getForm("form");
        form.clear();

        document.executeScript("limpar_LOOKUP_PERFILACESSO()");
    }

    /**
     * Monta árvore de Menus.
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     * @author thays.araujo
     */
    String classPai = "";
    String pesq = "", gravar, del = "";
    String aux = "";

    private void montarArvoreDeMenus(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        this.setPerfilAcessoBean((PerfilAcessoDTO) document.getBean());
        final Collection<MenuDTO> listaDeMenus = this.getMenuService().listarMenus();
        final PerfilAcessoMenuService perfilAcessoMenuService = (PerfilAcessoMenuService) ServiceLocator.getInstance().getService(
                PerfilAcessoMenuService.class, null);
        final StringBuilder sb = new StringBuilder();
        sb.append("<div id=\"corpoInf\">");
        sb.append("<br>");
        sb.append("<ul id=\"browser\" class=\"filetree treeview\">");
        Integer idPerfilAcesso = null;

        for (final MenuDTO menu : listaDeMenus) {
            pesq = "";
            del = "";
            gravar = "";
            pesq += "checkPesq" + menu.getIdMenu().toString() + " ";
            gravar += "checkGravar" + menu.getIdMenu().toString() + " ";
            del += "checkDel" + menu.getIdMenu().toString() + " ";

            final String nome = menu.getNome();
            sb.append("<li class=\"closed\">");
            sb.append("<div class=\"hitarea closed-hitarea collapsable-hitarea\">");
            sb.append("</div>");
            sb.append("<span class=\"folder\">");
            sb.append("<div>" + UtilI18N.internacionaliza(request, nome) + "</div>");
            final PerfilAcessoMenuDTO perfilAcessoMenuDTO = new PerfilAcessoMenuDTO();
            perfilAcessoMenuDTO.setIdMenu(menu.getIdMenu());

            if (this.getPerfilAcessoBean() != null) {
                idPerfilAcesso = this.getPerfilAcessoBean().getIdPerfilAcesso();
                perfilAcessoMenuDTO.setIdPerfilAcesso(idPerfilAcesso);
                final Collection<PerfilAcessoMenuDTO> dadosAcessoMenu = perfilAcessoMenuService.restoreMenusAcesso(perfilAcessoMenuDTO);
                if (dadosAcessoMenu != null && dadosAcessoMenu.size() > 0) {
                    for (final PerfilAcessoMenuDTO dto : dadosAcessoMenu) {
                        sb.append("<label class=\"labelCheck pull-left mr10\"><input " + this.trataRetorno(dto.getPesquisa()) + " class=\"checkPesq"
                                + menu.getIdMenu() + "\" type=\"checkbox\" name=\"menu\" id=\"pesq_" + menu.getIdMenu() + "\" value=\"" + menu.getIdMenu()
                                + " " + "\"  onclick=\"marcarTodosCheckbox(this);\" />&nbsp;"
                                + UtilI18N.internacionaliza(request, "citcorpore.comum.pesquisar") + "</label>");
                        sb.append("<label class=\"labelCheck pull-left mr10\"><input " + this.trataRetorno(dto.getGrava()) + " class=\"checkGravar"
                                + menu.getIdMenu() + "\" type=\"checkbox\" name=\"menu\" id=\"gravar_" + menu.getIdMenu() + "\" value=\"" + menu.getIdMenu()
                                + " " + "\" onclick=\"marcarTodosCheckbox(this);\" />&nbsp;" + UtilI18N.internacionaliza(request, "citcorpore.comum.gravar")
                                + "</label>");
                        sb.append("<label class=\"labelCheck\"><input " + this.trataRetorno(dto.getDeleta()) + " class=\"checkDel" + menu.getIdMenu()
                                + "\" type=\"checkbox\" name=\"menu\" id=\"del_" + menu.getIdMenu() + "\" value=\"" + menu.getIdMenu() + " "
                                + "\" onclick=\"marcarTodosCheckbox(this);\" />&nbsp;" + UtilI18N.internacionaliza(request, "citcorpore.comum.deletar")
                                + "</label>");
                    }
                } else {
                    sb.append("<label class=\"labelCheck pull-left mr10\"><input class=\"checkPesq" + menu.getIdMenu()
                            + "\" type=\"checkbox\" name=\"menu\" id=\"pesq_" + menu.getIdMenu() + "\" value=\"" + menu.getIdMenu() + " "
                            + "\"  onclick=\"marcarTodosCheckbox(this);\" />&nbsp;" + UtilI18N.internacionaliza(request, "citcorpore.comum.pesquisar")
                            + "</label>");
                    sb.append("<label class=\"labelCheck pull-left mr10\"><input class=\"checkGravar" + menu.getIdMenu()
                            + "\" type=\"checkbox\" name=\"menu\" id=\"gravar_" + menu.getIdMenu() + "\" value=\"" + menu.getIdMenu() + " "
                            + "\" onclick=\"marcarTodosCheckbox(this);\" />&nbsp;" + UtilI18N.internacionaliza(request, "citcorpore.comum.gravar") + "</label>");
                    sb.append("<label class=\"labelCheck\"><input class=\"checkDel" + menu.getIdMenu() + "\" type=\"checkbox\" name=\"menu\" id=\"del_"
                            + menu.getIdMenu() + "\" value=\"" + menu.getIdMenu() + " " + "\" onclick=\"marcarTodosCheckbox(this);\" />&nbsp;"
                            + UtilI18N.internacionaliza(request, "citcorpore.comum.deletar") + "</label>");
                }
            } else {
                sb.append("<label class=\"labelCheck pull-left mr10\"><input class=\"checkPesq" + menu.getIdMenu()
                        + "\" type=\"checkbox\" name=\"menu\" id=\"pesq_" + menu.getIdMenu() + "\" value=\"" + menu.getIdMenu() + " "
                        + "\"  onclick=\"marcarTodosCheckbox(this);\" />&nbsp;" + UtilI18N.internacionaliza(request, "citcorpore.comum.pesquisar") + "</label>");
                sb.append("<label class=\"labelCheck pull-left mr10\"><input class=\"checkGravar" + menu.getIdMenu()
                        + "\" type=\"checkbox\" name=\"menu\" id=\"gravar_" + menu.getIdMenu() + "\" value=\"" + menu.getIdMenu() + " "
                        + "\" onclick=\"marcarTodosCheckbox(this);\" />&nbsp;" + UtilI18N.internacionaliza(request, "citcorpore.comum.gravar") + "</label>");
                sb.append("<label class=\"labelCheck\"><input class=\"checkDel" + menu.getIdMenu() + "\" type=\"checkbox\" name=\"menu\" id=\"del_"
                        + menu.getIdMenu() + "\" value=\"" + menu.getIdMenu() + " " + "\" onclick=\"marcarTodosCheckbox(this);\" />&nbsp;"
                        + UtilI18N.internacionaliza(request, "citcorpore.comum.deletar") + "</label>");
            }

            sb.append("</span>");
            sb.append("<ul> ");
            final Collection<MenuDTO> listaDeSubMenus = this.getMenuService().listarSubMenus(menu);
            this.gerarMenus(sb, listaDeSubMenus, "0", idPerfilAcesso, request);
            sb.append("</ul>");
            sb.append("</li>");
        }
        sb.append("</ul>");
        sb.append("</div>");

        final HTMLElement divPrincipal = document.getElementById("principalInf");
        divPrincipal.setInnerHTML(sb.toString());

        document.executeScript("tree(\"#browser\");JANELA_AGUARDE_MENU.hide();");

    }

    private void gerarMenus(final StringBuilder sb, final Collection<MenuDTO> listaDeSubMenus, final String nivel, final Integer idPerfilAcesso,
            final HttpServletRequest request) throws ServiceException, Exception {
        final PerfilAcessoMenuService perfilAcessoMenuService = (PerfilAcessoMenuService) ServiceLocator.getInstance().getService(
                PerfilAcessoMenuService.class, null);
        for (final MenuDTO submenu : listaDeSubMenus) {
            final Collection<MenuDTO> novaListaSubMenus = this.getMenuService().listarSubMenus(submenu);
            final String nomeSubmenus = submenu.getNome();
            sb.append("<li class=\"closed\">");
            sb.append("<div class=\"hitarea closed-hitarea collapsable-hitarea\"></div>");
            if (novaListaSubMenus != null && !novaListaSubMenus.isEmpty()) {
                pesq += "checkPesq" + submenu.getIdMenu().toString() + " ";
                gravar += "checkPesq" + submenu.getIdMenu().toString() + " ";
                del += "checkDel" + submenu.getIdMenu().toString() + " ";
                aux = "0";
                sb.append("<span class=\"folder\">");
                sb.append("<div>" + UtilI18N.internacionaliza(request, nomeSubmenus) + "</div>");

            } else {

                sb.append("<span class=\"file\">");
                sb.append("<div>" + UtilI18N.internacionaliza(request, nomeSubmenus) + "</div>");
            }
            final PerfilAcessoMenuDTO perfilAcessoMenuDTO = new PerfilAcessoMenuDTO();
            perfilAcessoMenuDTO.setIdMenu(submenu.getIdMenu());
            if (idPerfilAcesso != null) {
                perfilAcessoMenuDTO.setIdPerfilAcesso(idPerfilAcesso);
                final Collection<PerfilAcessoMenuDTO> dadosAcessoMenu = perfilAcessoMenuService.restoreMenusAcesso(perfilAcessoMenuDTO);
                // sb.append("" + nomeSubmenus + "");
                if (dadosAcessoMenu != null && dadosAcessoMenu.size() > 0) {
                    for (final PerfilAcessoMenuDTO dto : dadosAcessoMenu) {
                        sb.append("<label class='pull-left mr10'><input  type=\"checkbox\" " + this.trataRetorno(dto.getPesquisa()) + " class=\"" + pesq
                                + "\" name=\"menu\" id=\"pesq_" + submenu.getIdMenu() + "\" value=\"" + submenu.getIdMenu() + " "
                                + "\" \"  onclick=\"checkboxPesquisar(" + submenu.getIdMenuPai() + ");\" />&nbsp;"
                                + UtilI18N.internacionaliza(request, "citcorpore.comum.pesquisar") + "</label>");
                        sb.append("<label class='pull-left mr10'><input type=\"checkbox\" " + this.trataRetorno(dto.getGrava()) + " class=\"" + gravar
                                + "\" name=\"menu\" id=\"gravar_" + submenu.getIdMenu() + "\" \" value=\"" + submenu.getIdMenu() + " "
                                + "\" onclick=\"checkboxGravar(" + submenu.getIdMenuPai() + ");\" />&nbsp;"
                                + UtilI18N.internacionaliza(request, "citcorpore.comum.gravar") + "</label>");
                        sb.append("<label><input type=\"checkbox\" " + this.trataRetorno(dto.getDeleta()) + " class=\"" + del + "\" name=\"menu\" id=\"del_"
                                + submenu.getIdMenu() + "\" \" value=\"" + submenu.getIdMenu() + " " + "\" onclick=\"checkboxDeletar(" + submenu.getIdMenuPai()
                                + ");\" />&nbsp;" + UtilI18N.internacionaliza(request, "citcorpore.comum.deletar") + "</label>");
                    }
                } else {
                    sb.append("<label class='pull-left mr10'><input type=\"checkbox\"  class=\"" + pesq + "\" name=\"menu\" id=\"pesq_" + submenu.getIdMenu()
                            + "\" \" value=\"" + submenu.getIdMenu() + " " + "\" onclick=\"checkboxPesquisar(" + submenu.getIdMenuPai() + ");\" />&nbsp;"
                            + UtilI18N.internacionaliza(request, "citcorpore.comum.pesquisar") + "</label>");
                    sb.append("<label class='pull-left mr10'><input type=\"checkbox\"  class=\"" + gravar + "\" name=\"menu\" id=\"gravar_"
                            + submenu.getIdMenu() + "\" \" value=\"" + submenu.getIdMenu() + " " + "\" onclick=\"checkboxGravar(" + submenu.getIdMenuPai()
                            + ");\" />&nbsp;" + UtilI18N.internacionaliza(request, "citcorpore.comum.gravar") + "</label>");
                    sb.append("<label><input type=\"checkbox\"  class=\"" + del + "\" name=\"menu\" id=\"del_" + submenu.getIdMenu() + "\" \" value=\""
                            + submenu.getIdMenu() + " " + "\" onclick=\"checkboxDeletar(" + submenu.getIdMenuPai() + ");\" />&nbsp;"
                            + UtilI18N.internacionaliza(request, "citcorpore.comum.deletar") + "</label>");
                }
            } else {
                sb.append("<label class='pull-left mr10'><input type=\"checkbox\"  class=\"" + pesq + "\" name=\"menu\" id=\"pesq_" + submenu.getIdMenu()
                        + "\" \" value=\"" + submenu.getIdMenu() + " " + "\" onclick=\"checkboxPesquisar(" + submenu.getIdMenuPai() + ");\" />&nbsp;"
                        + UtilI18N.internacionaliza(request, "citcorpore.comum.pesquisar") + "</label>");
                sb.append("<label class='pull-left mr10'><input type=\"checkbox\"  class=\"" + gravar + "\" name=\"menu\" id=\"gravar_" + submenu.getIdMenu()
                        + "\" \" value=\"" + submenu.getIdMenu() + " " + "\" onclick=\"checkboxGravar(" + submenu.getIdMenuPai() + ");\" />&nbsp;"
                        + UtilI18N.internacionaliza(request, "citcorpore.comum.gravar") + "</label>");
                sb.append("<label><input type=\"checkbox\"  class=\"" + del + "\" name=\"menu\" id=\"del_" + submenu.getIdMenu() + "\" \" value=\""
                        + submenu.getIdMenu() + " " + "\" onclick=\"checkboxDeletar(" + submenu.getIdMenuPai() + ");\" />&nbsp;"
                        + UtilI18N.internacionaliza(request, "citcorpore.comum.deletar") + "</label>");
            }
            sb.append("</span>");
            if (novaListaSubMenus != null && !novaListaSubMenus.isEmpty()) {
                sb.append("<ul id=\"subBios\"> ");
                this.gerarMenus(sb, novaListaSubMenus, aux, idPerfilAcesso, request);
                sb.append("</ul> ");
                sb.append("</li>");
            }

        }
    }

    private String trataRetorno(final String tipoAcesso) throws Exception {
        if (tipoAcesso.equalsIgnoreCase("S")) {
            return "checked=\"checked\"";
        } else {
            return "";
        }
    }

    /**
     * Metodo responsavel por verificar se o perfil de acesso está vinculado a algum grupo, se sim exibe a mensagem conforme mapeado na iniciativa 486
     *
     * @param document
     * @param request
     * @param response
     * @throws Exception
     *
     * @author Ezequiel
     */
    public void verificarGruposPerfilAcesso(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        try {

            this.setPerfilAcessoBean((PerfilAcessoDTO) document.getBean());

            final PerfilAcessoGrupoService perfilAcessoGrupoService = (PerfilAcessoGrupoService) ServiceLocator.getInstance().getService(
                    PerfilAcessoGrupoService.class, null);

            final boolean exiteGrupoVinculado = perfilAcessoGrupoService.existeGrupoVinculadoPerfil(this.getPerfilAcessoBean().getIdPerfilAcesso());

            if (exiteGrupoVinculado) {

                String mensagem = UtilI18N.internacionaliza(request, "perfilAcesso.infoGruposVinculado");

                mensagem = mensagem.replace("\\", "");

                document.alert(mensagem);
            }

        } catch (final Exception ex) {

            System.out.println("Não existe id perfil acesso vinculado");
        }
    }

    /**
     * Retorna instância de PerfilAcessoService.
     *
     * @return EmpregadoService
     * @throws ServiceException
     * @throws Exception
     * @author thays.araujo
     */
    public PerfilAcessoService getPerfilAcessoService() throws ServiceException, Exception {
        return (PerfilAcessoService) ServiceLocator.getInstance().getService(PerfilAcessoService.class, null);
    }

    /**
     * Retorna instância de MenuPerfisService.
     *
     * @return EmpregadoService
     * @throws ServiceException
     * @throws Exception
     * @author thays.araujo
     */
    public MenuService getMenuService() throws ServiceException, Exception {
        return (MenuService) ServiceLocator.getInstance().getService(MenuService.class, null);
    }

    /**
     * Atribui valor de PerfilAcessoBean.
     *
     * @param empregado
     * @author thays.araujo
     */
    public void setPerfilAcessoBean(final BaseEntity perfilAcessoBean) {
        this.perfilAcessoBean = (PerfilAcessoDTO) perfilAcessoBean;
    }

    /**
     * Retorna bean de perfilAcessoBean.
     *
     * @return EmpregadoDTO
     * @author thays.araujo
     */
    public PerfilAcessoDTO getPerfilAcessoBean() {
        return perfilAcessoBean;
    }

    /**
     * Atribui valor de MenuPerfisBean.
     *
     * @param empregado
     * @author thays.araujo
     */
    public void setMenuBean(final BaseEntity menuPerfis) {
        menuBean = (MenuDTO) menuPerfis;
    }

    /**
     * Retorna bean de menuPerfis.
     *
     * @return EmpregadoDTO
     * @author thays.araujo
     */
    public MenuDTO getMenuBean() {
        return menuBean;
    }

    /**
     * Atribui valor de AcessoMenu.
     *
     * @param empregado
     * @author thays.araujo
     */
    public void setAcessoMenuBean(final BaseEntity acessoMenu) {
        acessoMenuBean = (PerfilAcessoMenuDTO) acessoMenu;
    }

    /**
     * Retorna bean de AcessoMenu.
     *
     * @return EmpregadoDTO
     * @author thays.araujo
     */
    public PerfilAcessoMenuDTO getAcessoMenuBean() {
        return acessoMenuBean;
    }

}
