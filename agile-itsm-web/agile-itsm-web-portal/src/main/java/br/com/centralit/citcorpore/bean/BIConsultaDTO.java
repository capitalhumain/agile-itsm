package br.com.centralit.citcorpore.bean;

import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;

public class BIConsultaDTO extends BaseEntity {

    private Integer idConsulta;
    private String identificacao;
    private String nomeConsulta;
    private String tipoConsulta;
    private String textoSQL;
    private String situacao;
    private String acaoCruzado;
    private String template;
    private String scriptExec;
    private String parametros;
    private Integer idCategoria;
    private String naoAtualizBase;

    private String acao;
    private String conteudo;

    private String parmOK = "false";
    private String dashPart = "N";
    private boolean parametersPreenchidos = false;
    private List listParameters;

    private Collection colColunas;

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(final Integer parm) {
        idConsulta = parm;
    }

    public String getNomeConsulta() {
        return nomeConsulta;
    }

    public void setNomeConsulta(final String parm) {
        nomeConsulta = parm;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(final String parm) {
        tipoConsulta = parm;
    }

    public String getTextoSQL() {
        return textoSQL;
    }

    public void setTextoSQL(final String parm) {
        textoSQL = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public String getAcaoCruzado() {
        return acaoCruzado;
    }

    public void setAcaoCruzado(final String parm) {
        acaoCruzado = parm;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(final String template) {
        this.template = template;
    }

    public String getScriptExec() {
        return scriptExec;
    }

    public void setScriptExec(final String scriptExec) {
        this.scriptExec = scriptExec;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(final String identificacao) {
        this.identificacao = identificacao;
    }

    public String getParametros() {
        return parametros;
    }

    public void setParametros(final String parametros) {
        this.parametros = parametros;
    }

    public boolean isParametersPreenchidos() {
        return parametersPreenchidos;
    }

    public boolean getParametersPreenchidos() {
        return this.isParametersPreenchidos();
    }

    public void setParametersPreenchidos(final boolean parametersPreenchidos) {
        this.parametersPreenchidos = parametersPreenchidos;
    }

    public List getListParameters() {
        return listParameters;
    }

    public void setListParameters(final List listParameters) {
        this.listParameters = listParameters;
    }

    public String getParmOK() {
        return parmOK;
    }

    public void setParmOK(final String parmOK) {
        this.setParametersPreenchidos(false);
        if (parmOK != null) {
            if (parmOK.equalsIgnoreCase("S") || parmOK.equalsIgnoreCase("true")) {
                this.setParametersPreenchidos(true);
            }
        }
        this.parmOK = parmOK;
    }

    public Collection getColColunas() {
        return colColunas;
    }

    public void setColColunas(final Collection colColunas) {
        this.colColunas = colColunas;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(final Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNaoAtualizBase() {
        return naoAtualizBase;
    }

    public void setNaoAtualizBase(final String naoAtualizBase) {
        this.naoAtualizBase = naoAtualizBase;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(final String acao) {
        this.acao = acao;
    }

    public String getDashPart() {
        return dashPart;
    }

    public void setDashPart(final String dashPart) {
        this.dashPart = dashPart;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(final String conteudo) {
        this.conteudo = conteudo;
    }

}
