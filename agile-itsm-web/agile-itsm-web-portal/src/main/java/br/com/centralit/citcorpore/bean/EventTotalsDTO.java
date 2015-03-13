package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class EventTotalsDTO extends BaseEntity {

    private int qtdeCritical = 0;
    private int qtdeWarning = 0;
    private int qtdeOk;
    private int qtdeUnknown = 0;

    private int qtdeDown = 0;
    private int qtdeUp = 0;

    public int getQtdeCritical() {
        return qtdeCritical;
    }

    public void setQtdeCritical(final int qtdeCritical) {
        this.qtdeCritical = qtdeCritical;
    }

    public int getQtdeWarning() {
        return qtdeWarning;
    }

    public void setQtdeWarning(final int qtdeWarning) {
        this.qtdeWarning = qtdeWarning;
    }

    public int getQtdeOk() {
        return qtdeOk;
    }

    public void setQtdeOk(final int qtdeOk) {
        this.qtdeOk = qtdeOk;
    }

    public int getQtdeUnknown() {
        return qtdeUnknown;
    }

    public void setQtdeUnknown(final int qtdeUnknown) {
        this.qtdeUnknown = qtdeUnknown;
    }

    public int getQtdeDown() {
        return qtdeDown;
    }

    public void setQtdeDown(final int qtdeDown) {
        this.qtdeDown = qtdeDown;
    }

    public int getQtdeUp() {
        return qtdeUp;
    }

    public void setQtdeUp(final int qtdeUp) {
        this.qtdeUp = qtdeUp;
    }

}
