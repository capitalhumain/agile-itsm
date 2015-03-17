package br.com.centralit.citcorpore.tld;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;

import br.com.centralit.citcorpore.bean.MenuDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.negocio.MenuService;
import br.com.centralit.citcorpore.util.WebUtil;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilI18N;

/**
 * Layout do menu de acesso rápido
 *
 * @author flavio.santana
 * @since 28/10/2013
 */
public class MenuRapido extends BodyTagSupport {

    private static final long serialVersionUID = 6792733900026609094L;

    private final String CAMINHO_PAGINAS = Constantes.getValue("CONTEXTO_APLICACAO") + "/pages";

    private static boolean FLAG_MENU_PRINCIPAL = true;
    private static boolean TEM_MENU;

    @Override
    public int doStartTag() throws JspException {
        final UsuarioDTO usrSession = WebUtil.getUsuario((HttpServletRequest) pageContext.getRequest());
        final StringBuilder sbMenu = new StringBuilder();

        try {
            final String menuSessao = this.gerarMenuPrincipal(usrSession);
            sbMenu.append(menuSessao);
            pageContext.getOut().println(sbMenu);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    /***
     * Gera o HTML do menu principal
     *
     * @param usuario
     * @return
     * @throws Exception
     */
    private String gerarMenuPrincipal(final UsuarioDTO usuario) throws Exception {
        StringBuilder html = new StringBuilder();
        boolean flagFinalLinha = false, flagInicioLinha = false;
        TEM_MENU = false;

        final HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        html.append("<div class='widget'>");
        html.append("    <div class='widget-head'>");
        html.append("        <h4 class='heading'>");
        html.append(UtilI18N.internacionaliza(request, "citcorpore.comum.acessoRapido"));
        html.append("        </h4>");
        html.append("    </div>");
        html.append("    <div class='widget-body'>");
        html.append("        <div class='row-fluid'>");

        final List<MenuDTO> listaMenusPai = (List<MenuDTO>) this.getMenuService().listarMenusPorPerfil(usuario, null, false);
        if (listaMenusPai != null) {
            int i = 1;
            final double num = listaMenusPai.size();
            for (final MenuDTO menu : listaMenusPai) {
                final StringBuilder htmlMenu = new StringBuilder();
                FLAG_MENU_PRINCIPAL = false;

                if (flagInicioLinha) {
                    html.append("<div class='row-fluid'>");
                    flagInicioLinha = false;
                }
                htmlMenu.append("<div class='span4'>");
                htmlMenu.append("    <div class='innerAll'>");
                htmlMenu.append("        <div class='glyphicons glyphicon-large " + menu.getImagem() + "'>");
                htmlMenu.append("            <i></i>");
                htmlMenu.append("            <h4>" + UtilI18N.internacionaliza(request, menu.getNome()) + "</h4>");
                this.gerarSubMenu(htmlMenu, menu.getIdMenu(), usuario);
                htmlMenu.append("        </div>");
                htmlMenu.append("    </div>");
                htmlMenu.append("</div>");

                if (FLAG_MENU_PRINCIPAL) {
                    html.append(htmlMenu);
                } else {
                    i--;
                }
                if (i % 3 == 0 || i == num) {
                    flagFinalLinha = flagInicioLinha = true;
                }

                if (flagFinalLinha) {
                    html.append("</div>");
                    flagFinalLinha = false;
                }
                i++;
            }
        }
        html.append("        </div>");
        html.append("    </div>");
        html.append("</div>");

        if (!TEM_MENU) {
            html = new StringBuilder();
        }

        return html.toString();
    }

    /**
     * Gera o HTML dos subMenus
     *
     * @param html
     * @param idMenu
     * @param usuario
     */
    private void gerarSubMenu(final StringBuilder html, final Integer idMenu, final UsuarioDTO usuario) {
        final HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        try {
            final List<MenuDTO> listaSubMenus = (List<MenuDTO>) this.getMenuService().listarMenusPorPerfil(usuario, idMenu, true);
            if (listaSubMenus != null && !listaSubMenus.isEmpty()) {
                for (final MenuDTO submenu : listaSubMenus) {
                    if (submenu.getLink() != null && !submenu.getLink().equals("")) {
                        TEM_MENU = true;
                        FLAG_MENU_PRINCIPAL = true;
                        html.append("<p>");
                        html.append("<a href='");
                        html.append(StringUtils.isBlank(submenu.getLink()) ? "javascript:;" : CAMINHO_PAGINAS + submenu.getLink());
                        html.append("'>");
                        html.append(UtilI18N.internacionaliza(request, submenu.getNome()) + "</a>");
                        html.append("</p>");
                    }
                    this.gerarSubMenu(html, submenu.getIdMenu(), usuario);
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public MenuService getMenuService() throws ServiceException, Exception {
        return (MenuService) ServiceLocator.getInstance().getService(MenuService.class, null);
    }

}
