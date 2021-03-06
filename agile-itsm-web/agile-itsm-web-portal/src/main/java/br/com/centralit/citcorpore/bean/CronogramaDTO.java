package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class CronogramaDTO extends BaseEntity {

    private String ta;
    private Integer idProjeto;
    private Integer idLinhaBaseProjeto;
    private String novaLinhaBaseProjeto;
    private String situacaoLinhaBaseProjetoSelecionada;
    private Integer idTemplateImpressao;
    private String marcosProjeto;

    public String getTa() {
        return ta;
    }

    public void setTa(final String ta) {
        this.ta = ta;
    }

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(final Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Integer getIdLinhaBaseProjeto() {
        return idLinhaBaseProjeto;
    }

    public void setIdLinhaBaseProjeto(final Integer idLinhaBaseProjeto) {
        this.idLinhaBaseProjeto = idLinhaBaseProjeto;
    }

    public String getNovaLinhaBaseProjeto() {
        return novaLinhaBaseProjeto;
    }

    public void setNovaLinhaBaseProjeto(final String novaLinhaBaseProjeto) {
        this.novaLinhaBaseProjeto = novaLinhaBaseProjeto;
    }

    public String getSituacaoLinhaBaseProjetoSelecionada() {
        return situacaoLinhaBaseProjetoSelecionada;
    }

    public void setSituacaoLinhaBaseProjetoSelecionada(final String situacaoLinhaBaseProjetoSelecionada) {
        this.situacaoLinhaBaseProjetoSelecionada = situacaoLinhaBaseProjetoSelecionada;
    }

    public Integer getIdTemplateImpressao() {
        return idTemplateImpressao;
    }

    public void setIdTemplateImpressao(final Integer idTemplateImpressao) {
        this.idTemplateImpressao = idTemplateImpressao;
    }

    public String getMarcosProjeto() {
        return marcosProjeto;
    }

    public void setMarcosProjeto(final String marcosProjeto) {
        this.marcosProjeto = marcosProjeto;
    }

}
