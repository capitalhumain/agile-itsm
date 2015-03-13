package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class ItemCfgSolicitacaoServDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idSolicitacaoServico;
    private Integer idItemConfiguracao;
    private Integer idItemConfiguracaoPai;
    private String identificacao;
    private Date dataInicio;
    private Date dataFim;

    private String identificacaoStatus;

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    public void setIdItemConfiguracao(final Integer idItemConfiguracao) {
        this.idItemConfiguracao = idItemConfiguracao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the identificacao
     */
    public String getIdentificacao() {
        return identificacao;
    }

    /**
     * @param identificacao
     *            the identificacao to set
     */
    public void setIdentificacao(final String identificacao) {
        this.identificacao = identificacao;
    }

    /**
     * @return the idItemConfiguracaoPai
     */
    public Integer getIdItemConfiguracaoPai() {
        return idItemConfiguracaoPai;
    }

    /**
     * @param idItemConfiguracaoPai
     *            the idItemConfiguracaoPai to set
     */
    public void setIdItemConfiguracaoPai(final Integer idItemConfiguracaoPai) {
        this.idItemConfiguracaoPai = idItemConfiguracaoPai;
    }

    public String getIdentificacaoStatus() {
        return identificacaoStatus;
    }

    public void setIdentificacaoStatus(final String identificacaoStatus) {
        this.identificacaoStatus = identificacaoStatus;
    }

}
