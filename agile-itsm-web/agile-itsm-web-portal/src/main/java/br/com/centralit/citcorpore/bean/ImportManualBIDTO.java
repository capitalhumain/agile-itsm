package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ImportManualBIDTO extends BaseEntity {

    private static final long serialVersionUID = 2458103872245462549L;

    private Integer idConexaoBI;

    public Integer getIdConexaoBI() {
        return idConexaoBI;
    }

    public void setIdConexaoBI(final Integer idConexaoBI) {
        this.idConexaoBI = idConexaoBI;
    }

}
