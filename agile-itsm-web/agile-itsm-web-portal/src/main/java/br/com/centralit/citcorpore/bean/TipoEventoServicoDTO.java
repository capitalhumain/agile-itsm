package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class TipoEventoServicoDTO extends BaseEntity {

    private Integer idTipoEventoServico;
    private String nomeTipoEventoServico;

    public Integer getIdTipoEventoServico() {
        return idTipoEventoServico;
    }

    public void setIdTipoEventoServico(final Integer parm) {
        idTipoEventoServico = parm;
    }

    public String getNomeTipoEventoServico() {
        return nomeTipoEventoServico;
    }

    public void setNomeTipoEventoServico(final String parm) {
        nomeTipoEventoServico = parm;
    }

}
