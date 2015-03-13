package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class LigacaoRequisicaoMudancaHistoricoGrupoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idligacao_mud_his_gru;
    private Integer idRequisicaoMudanca;
    private Integer idHistoricoMudanca;
    private Integer idGrupoRequisicaoMudanca;

    public Integer getIdligacao_mud_his_gru() {
        return idligacao_mud_his_gru;
    }

    public void setIdligacao_mud_his_gru(final Integer idligacao_mud_his_gru) {
        this.idligacao_mud_his_gru = idligacao_mud_his_gru;
    }

    public Integer getIdRequisicaoMudanca() {
        return idRequisicaoMudanca;
    }

    public void setIdRequisicaoMudanca(final Integer idRequisicaoMudanca) {
        this.idRequisicaoMudanca = idRequisicaoMudanca;
    }

    public Integer getIdHistoricoMudanca() {
        return idHistoricoMudanca;
    }

    public void setIdHistoricoMudanca(final Integer idHistoricoMudanca) {
        this.idHistoricoMudanca = idHistoricoMudanca;
    }

    public Integer getIdGrupoRequisicaoMudanca() {
        return idGrupoRequisicaoMudanca;
    }

    public void setIdGrupoRequisicaoMudanca(final Integer idGrupoRequisicaoMudanca) {
        this.idGrupoRequisicaoMudanca = idGrupoRequisicaoMudanca;
    }

}
