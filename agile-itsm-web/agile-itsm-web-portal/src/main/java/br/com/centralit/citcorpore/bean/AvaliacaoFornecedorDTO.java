package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class AvaliacaoFornecedorDTO extends BaseEntity {

    private Integer idAvaliacaoFornecedor;

    private Integer idFornecedor;

    private Integer idResponsavel;

    private Date dataAvaliacao;

    private String decisaoQualificacao;

    private String observacoesAvaliacaoFornecedor;

    private Collection<CriterioAvaliacaoFornecedorDTO> listCriterioAvaliacaoFornecedor;

    private Collection<AvaliacaoReferenciaFornecedorDTO> listAvaliacaoReferenciaFornecedor;

    private String nomeResponsavel;

    public Integer getIdAvaliacaoFornecedor() {
        return idAvaliacaoFornecedor;
    }

    public void setIdAvaliacaoFornecedor(final Integer parm) {
        idAvaliacaoFornecedor = parm;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(final Integer parm) {
        idFornecedor = parm;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer parm) {
        idResponsavel = parm;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(final Date parm) {
        dataAvaliacao = parm;
    }

    public String getDecisaoQualificacao() {
        return decisaoQualificacao;
    }

    public void setDecisaoQualificacao(final String parm) {
        decisaoQualificacao = parm;
    }

    /**
     * @return the listCriterioAvaliacaoFornecedor
     */
    public Collection<CriterioAvaliacaoFornecedorDTO> getListCriterioAvaliacaoFornecedor() {
        return listCriterioAvaliacaoFornecedor;
    }

    /**
     * @param listCriterioAvaliacaoFornecedor
     *            the listCriterioAvaliacaoFornecedor to set
     */
    public void setListCriterioAvaliacaoFornecedor(final Collection<CriterioAvaliacaoFornecedorDTO> listCriterioAvaliacaoFornecedor) {
        this.listCriterioAvaliacaoFornecedor = listCriterioAvaliacaoFornecedor;
    }

    /**
     * @return the listAvaliacaoReferenciaFornecedor
     */
    public Collection<AvaliacaoReferenciaFornecedorDTO> getListAvaliacaoReferenciaFornecedor() {
        return listAvaliacaoReferenciaFornecedor;
    }

    /**
     * @param listAvaliacaoReferenciaFornecedor
     *            the listAvaliacaoReferenciaFornecedor to set
     */
    public void setListAvaliacaoReferenciaFornecedor(final Collection<AvaliacaoReferenciaFornecedorDTO> listAvaliacaoReferenciaFornecedor) {
        this.listAvaliacaoReferenciaFornecedor = listAvaliacaoReferenciaFornecedor;
    }

    /**
     * @return the nomeResponsavel
     */
    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    /**
     * @param nomeResponsavel
     *            the nomeResponsavel to set
     */
    public void setNomeResponsavel(final String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    /**
     * @return the observacoesAvaliacaoFornecedor
     */
    public String getObservacoesAvaliacaoFornecedor() {
        return observacoesAvaliacaoFornecedor;
    }

    /**
     * @param observacoesAvaliacaoFornecedor
     *            the observacoesAvaliacaoFornecedor to set
     */
    public void setObservacoesAvaliacaoFornecedor(final String observacoesAvaliacaoFornecedor) {
        this.observacoesAvaliacaoFornecedor = observacoesAvaliacaoFornecedor;
    }

}
