package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ToolDataBaseDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String tabela;
    private String tipoAcao;
    private String strExec;
    private String quantRows;

    public String getTabela() {
        return tabela;
    }

    public void setTabela(final String tabela) {
        this.tabela = tabela;
    }

    public String getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(final String tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public String getStrExec() {
        return strExec;
    }

    public void setStrExec(final String strExec) {
        this.strExec = strExec;
    }

    public String getQuantRows() {
        return quantRows;
    }

    public void setQuantRows(final String quantRows) {
        this.quantRows = quantRows;
    }

}
