package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class LigacaoRequisicaoMudancaHistoricoServicoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idligacao_mud_hist_se;
    private Integer idRequisicaoMudanca;
    private Integer idHistoricoMudanca;
    private Integer idrequisicaomudancaservico;

    public Integer getIdligacao_mud_hist_se() {
        return idligacao_mud_hist_se;
    }

    public void setIdligacao_mud_hist_se(final Integer idligacao_mud_hist_se) {
        this.idligacao_mud_hist_se = idligacao_mud_hist_se;
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

    public Integer getIdrequisicaomudancaservico() {
        return idrequisicaomudancaservico;
    }

    public void setIdrequisicaomudancaservico(final Integer idrequisicaomudancaservico) {
        this.idrequisicaomudancaservico = idrequisicaomudancaservico;
    }

}
