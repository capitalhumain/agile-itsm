package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class BIDashBoardSegurDTO extends BaseEntity {

    private Integer idGrupo;
    private Integer idDashBoard;

    private Integer[] perfilSelecionado;

    public Integer getIdDashBoard() {
        return idDashBoard;
    }

    public void setIdDashBoard(final Integer parm) {
        idDashBoard = parm;
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
