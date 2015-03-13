package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class BIConsultaSegurDTO extends BaseEntity {

    private Integer idGrupo;
    private Integer idConsulta;

    private Integer[] perfilSelecionado;

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(final Integer parm) {
        idConsulta = parm;
    }

    public Integer[] getPerfilSelecionado() {
        return perfilSelecionado;
    }

    public void setPerfilSelecionado(final Integer[] perfilSelecionado) {
        this.perfilSelecionado = perfilSelecionado;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(final Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

}
