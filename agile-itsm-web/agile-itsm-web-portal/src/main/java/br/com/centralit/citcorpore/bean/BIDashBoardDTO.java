package br.com.centralit.citcorpore.bean;

import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;

public class BIDashBoardDTO extends BaseEntity {

    private Integer idDashBoard;
    private String nomeDashBoard;
    private String tipo;
    private String identificacao;
    private String situacao;
    private Integer idUsuario;
    private String parametros;
    private String naoAtualizBase;
    private Integer tempoRefresh;

    private Collection colItens;
    private List listParameters;

    private String parmOK = "false";
    private boolean parametersPreenchidos = false;

    public Integer getIdDashBoard() {
        return idDashBoard;
    }

    public void setIdDashBoard(final Integer parm) {
        idDashBoard = parm;
    }

    public String getNomeDashBoard() {
        return nomeDashBoard;
    }

    public void setNomeDashBoard(final String parm) {
        nomeDashBoard = parm;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String parm) {
        tipo = parm;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(final String parm) {
        identificacao = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(final Integer parm) {
        idUsuario = parm;
    }

    public Collection getColItens() {
        return colItens;
    }

    public void setColItens(final Collection colItens) {
        this.colItens = colItens;
    }

    public String getParametros() {
        return parametros;
    }

    public void setParametros(final String parametros) {
        this.parametros = parametros;
    }

    public String getNaoAtualizBase() {
        return naoAtualizBase;
    }

    public void setNaoAtualizBase(final String naoAtualizBase) {
        this.naoAtualizBase = naoAtualizBase;
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

    public boolean isParametersPreenchidos() {
        return parametersPreenchidos;
    }

    public boolean getParametersPreenchidos() {
        return parametersPreenchidos;
    }

    public void setParametersPreenchidos(final boolean parametersPreenchidos) {
        this.parametersPreenchidos = parametersPreenchidos;
    }

    public Integer getTempoRefresh() {
        return tempoRefresh;
    }

    public void setTempoRefresh(final Integer tempoRefresh) {
        this.tempoRefresh = tempoRefresh;
    }

}
