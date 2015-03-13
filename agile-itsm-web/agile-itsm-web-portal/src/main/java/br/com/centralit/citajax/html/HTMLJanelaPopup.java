package br.com.centralit.citajax.html;

public class HTMLJanelaPopup extends HTMLElement {

    public HTMLJanelaPopup(final String idParm, final DocumentHTML documentParm) {
        super(idParm, documentParm);
    }

    @Override
    public String getType() {
        return JANELAPOPUP;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(final boolean visible) {
        this.visible = visible;
        if (this.visible) {
            this.show();
        } else {
            this.hide();
        }
    }

    public void hide() {
        this.setCommandExecute(id + ".hide()");
    }

    public void show() {
        this.setCommandExecute(id + ".show()");
    }

    public void showInYPosition(final int yPos) {
        this.setCommandExecute(id + ".showInYPosition({top:" + yPos + "})");
    }

}
