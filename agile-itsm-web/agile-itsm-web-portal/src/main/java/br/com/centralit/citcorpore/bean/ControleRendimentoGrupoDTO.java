package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ControleRendimentoGrupoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idControleRendimentoGrupo;
    private Integer idControleRendimento;
    private Integer idGrupo;

    public Integer getIdControleRendimentoGrupo() {
        return idControleRendimentoGrupo;
    }

    public void setIdControleRendimentoGrupo(final Integer idControleRendimentoGrupo) {
        this.idControleRendimentoGrupo = idControleRendimentoGrupo;
    }

    public Integer getIdControleRendimento() {
        return idControleRendimento;
    }

    public void setIdControleRendimento(final Integer idControleRendimento) {
        this.idControleRendimento = idControleRendimento;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(final Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

}
