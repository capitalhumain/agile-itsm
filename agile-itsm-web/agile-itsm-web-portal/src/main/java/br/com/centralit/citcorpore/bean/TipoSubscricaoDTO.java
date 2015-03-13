package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Pedro
 *
 */
public class TipoSubscricaoDTO extends BaseEntity {

    private Integer idTipoSubscricao;
    private String nomeTipoSubscricao;

    public Integer getIdTipoSubscricao() {
        return idTipoSubscricao;
    }

    public void setIdTipoSubscricao(final Integer idTipoSubscricao) {
        this.idTipoSubscricao = idTipoSubscricao;
    }

    public String getNomeTipoSubscricao() {
        return nomeTipoSubscricao;
    }

    public void setNomeTipoSubscricao(final String nomeTipoSubscricao) {
        this.nomeTipoSubscricao = nomeTipoSubscricao;
    }

}
