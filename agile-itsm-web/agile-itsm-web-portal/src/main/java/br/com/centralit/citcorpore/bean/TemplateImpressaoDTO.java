package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class TemplateImpressaoDTO extends BaseEntity {

    private Integer idTemplateImpressao;
    private String nomeTemplate;
    private String htmlCabecalho;
    private String htmlCorpo;
    private String htmlRodape;
    private Integer idTipoTemplateImp;
    private Integer tamCabecalho;
    private Integer tamRodape;

    public Integer getIdTemplateImpressao() {
        return idTemplateImpressao;
    }

    public void setIdTemplateImpressao(final Integer parm) {
        idTemplateImpressao = parm;
    }

    public String getNomeTemplate() {
        return nomeTemplate;
    }

    public void setNomeTemplate(final String parm) {
        nomeTemplate = parm;
    }

    public String getHtmlCabecalho() {
        return htmlCabecalho;
    }

    public void setHtmlCabecalho(final String parm) {
        htmlCabecalho = parm;
    }

    public String getHtmlCorpo() {
        return htmlCorpo;
    }

    public void setHtmlCorpo(final String parm) {
        htmlCorpo = parm;
    }

    public String getHtmlRodape() {
        return htmlRodape;
    }

    public void setHtmlRodape(final String parm) {
        htmlRodape = parm;
    }

    public Integer getIdTipoTemplateImp() {
        return idTipoTemplateImp;
    }

    public void setIdTipoTemplateImp(final Integer parm) {
        idTipoTemplateImp = parm;
    }

    public Integer getTamCabecalho() {
        return tamCabecalho;
    }

    public void setTamCabecalho(final Integer parm) {
        tamCabecalho = parm;
    }

    public Integer getTamRodape() {
        return tamRodape;
    }

    public void setTamRodape(final Integer parm) {
        tamRodape = parm;
    }

}
