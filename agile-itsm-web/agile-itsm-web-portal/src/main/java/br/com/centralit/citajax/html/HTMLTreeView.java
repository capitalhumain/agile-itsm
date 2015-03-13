package br.com.centralit.citajax.html;

import br.com.citframework.util.UtilStrings;

/**
 * Esta classe faz uso do JavaScript: dtree.js
 * 
 * @author emauri
 *
 */
public class HTMLTreeView extends HTMLElement {

    private boolean generateHeader = false;
    private boolean generateIcons = false;

    private Boolean useIcons = new Boolean(false);

    private String iconRoot = "../../imagens/predio.gif";
    private String iconJoinBottom = "../../imagens/joinBottom.gif";
    private String iconMinusBottom = "../../imagens/minusBottom.gif";
    private String iconPlusBottom = "../../imagens/plusBottom.gif";
    private String iconFolderOpen = "../../imagens/folderOpen.gif";
    private String iconJoin = "../../imagens/join.gif";
    private String iconEmpty = "../../imagens/empty.gif";

    public HTMLTreeView(final String idParm, final DocumentHTML documentParm) {
        super(idParm, documentParm);
    }

    @Override
    public String getType() {
        return TREEVIEW;
    }

    public void add(final String idNode, final String idNodePai, final String name, final String url, final String title, final String target,
            final String icon, final String iconOpen, final Boolean open, final String functionOnClick) {
        if (!generateHeader) {
            this.genHeader();
            generateHeader = true;
        }
        if (!generateIcons) {
            this.genIcons();
            generateIcons = true;
        }
        this.setCommandExecute("document.trvV" + this.getId() + ".add(" + idNode + "," + idNodePai + ",'" + name + "','" + UtilStrings.nullToVazio(url) + "','"
                + UtilStrings.nullToVazio(title) + "','" + UtilStrings.nullToVazio(target) + "','" + UtilStrings.nullToVazio(icon) + "','"
                + UtilStrings.nullToVazio(iconOpen) + "', " + open.toString() + ", '" + UtilStrings.nullToVazio(functionOnClick) + "')");
    }

    public void renderTreeView() {
        if (!generateHeader) {
            this.genHeader();
            generateHeader = true;
        }
        this.setCommandExecute("document.getElementById('" + this.getId() + "').innerHTML = " + "document.trvV" + this.getId() + ".toString()");
    }

    private void genHeader() {
        this.setCommandExecute("document.trvV" + this.getId() + " = new dTree('" + "trvV" + this.getId() + "')");
        this.setCommandExecute("document.trvV" + this.getId() + ".config.useIcons = " + this.getUseIcons().toString());
    }

    private void genIcons() {
        this.setCommandExecute("document.trvV" + this.getId() + ".icon.root = '" + this.getIconRoot() + "'");
        this.setCommandExecute("document.trvV" + this.getId() + ".icon.joinBottom = '" + this.getIconJoinBottom() + "'");
        this.setCommandExecute("document.trvV" + this.getId() + ".icon.minusBottom = '" + this.getIconMinusBottom() + "'");
        this.setCommandExecute("document.trvV" + this.getId() + ".icon.plusBottom = '" + this.getIconPlusBottom() + "'");
        this.setCommandExecute("document.trvV" + this.getId() + ".icon.folderOpen = '" + this.getIconFolderOpen() + "'");
        this.setCommandExecute("document.trvV" + this.getId() + ".icon.join = '" + this.getIconJoin() + "'");
        this.setCommandExecute("document.trvV" + this.getId() + ".icon.empty = '" + this.getIconEmpty() + "'");
    }

    public Boolean getUseIcons() {
        return useIcons;
    }

    public void setUseIcons(final Boolean useIcons) {
        this.useIcons = useIcons;
    }

    public String getIconFolderOpen() {
        return iconFolderOpen;
    }

    public void setIconFolderOpen(final String iconFolderOpen) {
        this.iconFolderOpen = iconFolderOpen;
        generateIcons = false;
    }

    public String getIconJoinBottom() {
        return iconJoinBottom;
    }

    public void setIconJoinBottom(final String iconJoinBottom) {
        this.iconJoinBottom = iconJoinBottom;
        generateIcons = false;
    }

    public String getIconMinusBottom() {
        return iconMinusBottom;
    }

    public void setIconMinusBottom(final String iconMinusBottom) {
        this.iconMinusBottom = iconMinusBottom;
        generateIcons = false;
    }

    public String getIconPlusBottom() {
        return iconPlusBottom;
    }

    public void setIconPlusBottom(final String iconPlusBottom) {
        this.iconPlusBottom = iconPlusBottom;
        generateIcons = false;
    }

    public String getIconRoot() {
        return iconRoot;
    }

    public void setIconRoot(final String iconRoot) {
        this.iconRoot = iconRoot;
        generateIcons = false;
    }

    public String getIconJoin() {
        return iconJoin;
    }

    public void setIconJoin(final String iconJoin) {
        this.iconJoin = iconJoin;
    }

    public String getIconEmpty() {
        return iconEmpty;
    }

    public void setIconEmpty(final String iconEmpty) {
        this.iconEmpty = iconEmpty;
    }

}
