package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author mario.haysaki
 *
 */
public class GrupoRequisicaoMudancaDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idGrupoRequisicaoMudanca;
    private Integer idGrupo;
    private Integer idRequisicaoMudanca;
    private String nomeGrupo;
    private Date dataFim;

    public Integer getIdGrupoRequisicaoMudanca() {
        return idGrupoRequisicaoMudanca;
    }

    public void setIdGrupoRequisicaoMudanca(final Integer idGrupoRequisicaoMudanca) {
        this.idGrupoRequisicaoMudanca = idGrupoRequisicaoMudanca;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(final Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdRequisicaoMudanca() {
        return idRequisicaoMudanca;
    }

    public void setIdRequisicaoMudanca(final Integer idRequisicaoMudanca) {
        this.idRequisicaoMudanca = idRequisicaoMudanca;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(final String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

}
