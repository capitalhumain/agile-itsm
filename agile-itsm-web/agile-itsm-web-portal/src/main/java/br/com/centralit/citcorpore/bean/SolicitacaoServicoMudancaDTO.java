package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class SolicitacaoServicoMudancaDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idRequisicaoMudanca;
    private Integer idSolicitacaoServico;
    private Date dataFim;
    private String nomeServico;
    private Integer idHistoricoMudanca;

    /**
     * @return the idSolicitacaoServico
     */
    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    /**
     * @param idSolicitacaoServico
     *            the idSolicitacaoServico to set
     */
    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    /**
     * @return the idRequisicaoMudanca
     */
    public Integer getIdRequisicaoMudanca() {
        return idRequisicaoMudanca;
    }

    /**
     * @param idRequisicaoMudanca
     *            the idRequisicaoMudanca to set
     */
    public void setIdRequisicaoMudanca(final Integer idRequisicaoMudanca) {
        this.idRequisicaoMudanca = idRequisicaoMudanca;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public Integer getIdHistoricoMudanca() {
        return idHistoricoMudanca;
    }

    public void setIdHistoricoMudanca(final Integer idHistoricoMudanca) {
        this.idHistoricoMudanca = idHistoricoMudanca;
    }

}
