package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author danillo.lisboa
 *
 */
public class HistoricoGEDDTO extends BaseEntity {

    private Integer idLigacaoHistoricoGed;
    private Integer idControleGed;
    private Integer idRequisicaoMudanca;
    private Integer idHistoricoMudanca;
    private Integer idTabela;
    private Date dataFim;

    public Integer getIdLigacaoHistoricoGed() {
        return idLigacaoHistoricoGed;
    }

    public void setIdLigacaoHistoricoGed(final Integer idLigacaoHistoricoGed) {
        this.idLigacaoHistoricoGed = idLigacaoHistoricoGed;
    }

    public Integer getIdControleGed() {
        return idControleGed;
    }

    public void setIdControleGed(final Integer idControleGed) {
        this.idControleGed = idControleGed;
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

    public Integer getIdTabela() {
        return idTabela;
    }

    public void setIdTabela(final Integer idTabela) {
        this.idTabela = idTabela;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

}
