package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class TemplateSolicitacaoServicoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idTemplate;
    private String nomeTemplate;
    private String identificacao;
    private String nomeClasseAction;
    private String nomeClasseDto;
    private String nomeClasseServico;
    private String urlRecuperacao;
    private String habilitaDirecionamento;
    private String habilitaSituacao;
    private String habilitaSolucao;
    private String habilitaUrgenciaImpacto;
    private String habilitaNotificacaoEmail;
    private String habilitaProblema;
    private String habilitaMudanca;
    private String habilitaItemConfiguracao;
    private String habilitaSolicitacaoRelacionada;
    private String habilitaGravarEContinuar;
    private String scriptAposRecuperacao;
    private Integer alturaDiv;
    private Integer idQuestionario;
    private String aprovacao;

    public Integer getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(final Integer parm) {
        idTemplate = parm;
    }

    public String getNomeTemplate() {
        return nomeTemplate;
    }

    public void setNomeTemplate(final String parm) {
        nomeTemplate = parm;
    }

    public String getNomeClasseServico() {
        return nomeClasseServico;
    }

    public void setNomeClasseServico(final String parm) {
        nomeClasseServico = parm;
    }

    public String getUrlRecuperacao() {
        return urlRecuperacao;
    }

    public void setUrlRecuperacao(final String parm) {
        urlRecuperacao = parm;
    }

    public String getNomeClasseDto() {
        return nomeClasseDto;
    }

    public void setNomeClasseDto(final String nomeClasseDto) {
        this.nomeClasseDto = nomeClasseDto;
    }

    public String getNomeClasseAction() {
        return nomeClasseAction;
    }

    public void setNomeClasseAction(final String nomeClasseAction) {
        this.nomeClasseAction = nomeClasseAction;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(final String identificacao) {
        this.identificacao = identificacao;
    }

    public String getScriptAposRecuperacao() {
        return scriptAposRecuperacao;
    }

    public void setScriptAposRecuperacao(final String scriptAposRecuperacao) {
        this.scriptAposRecuperacao = scriptAposRecuperacao;
    }

    public String getHabilitaDirecionamento() {
        return habilitaDirecionamento;
    }

    public void setHabilitaDirecionamento(final String habilitaDirecionamento) {
        this.habilitaDirecionamento = habilitaDirecionamento;
    }

    public String getHabilitaSituacao() {
        return habilitaSituacao;
    }

    public void setHabilitaSituacao(final String habilitaSituacao) {
        this.habilitaSituacao = habilitaSituacao;
    }

    public String getHabilitaSolucao() {
        return habilitaSolucao;
    }

    public Integer getAlturaDiv() {
        return alturaDiv;
    }

    public void setAlturaDiv(final Integer alturaDiv) {
        this.alturaDiv = alturaDiv;
    }

    public void setHabilitaSolucao(final String habilitaSolucao) {
        this.habilitaSolucao = habilitaSolucao;
    }

    public String getHabilitaUrgenciaImpacto() {
        return habilitaUrgenciaImpacto;
    }

    public void setHabilitaUrgenciaImpacto(final String habilitaUrgenciaImpacto) {
        this.habilitaUrgenciaImpacto = habilitaUrgenciaImpacto;
    }

    public String getHabilitaNotificacaoEmail() {
        return habilitaNotificacaoEmail;
    }

    public void setHabilitaNotificacaoEmail(final String habilitaNotificacaoEmail) {
        this.habilitaNotificacaoEmail = habilitaNotificacaoEmail;
    }

    public String getHabilitaProblema() {
        return habilitaProblema;
    }

    public void setHabilitaProblema(final String habilitaProblema) {
        this.habilitaProblema = habilitaProblema;
    }

    public String getHabilitaMudanca() {
        return habilitaMudanca;
    }

    public void setHabilitaMudanca(final String habilitaMudanca) {
        this.habilitaMudanca = habilitaMudanca;
    }

    public String getHabilitaItemConfiguracao() {
        return habilitaItemConfiguracao;
    }

    public void setHabilitaItemConfiguracao(final String habilitaItemConfiguracao) {
        this.habilitaItemConfiguracao = habilitaItemConfiguracao;
    }

    public String getHabilitaSolicitacaoRelacionada() {
        return habilitaSolicitacaoRelacionada;
    }

    public void setHabilitaSolicitacaoRelacionada(final String habilitaSolicitacaoRelacionada) {
        this.habilitaSolicitacaoRelacionada = habilitaSolicitacaoRelacionada;
    }

    public String getHabilitaGravarEContinuar() {
        return habilitaGravarEContinuar;
    }

    public void setHabilitaGravarEContinuar(final String habilitaGravarEContinuar) {
        this.habilitaGravarEContinuar = habilitaGravarEContinuar;
    }

    public Integer getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(final Integer idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    public boolean isQuestionario() {
        return idQuestionario != null && idQuestionario.intValue() > 0;
    }

    public String getAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(final String aprovacao) {
        this.aprovacao = aprovacao;
    }

}
