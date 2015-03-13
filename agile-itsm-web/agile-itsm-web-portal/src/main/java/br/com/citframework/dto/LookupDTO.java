package br.com.citframework.dto;

import br.com.agileitsm.model.support.BaseEntity;

public class LookupDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String acao;
    private String nomeLookup;
    private String parm1;
    private String parm2;
    private String parm3;
    private String parm4;
    private String parm5;
    private String parm6;
    private String parm7;
    private String parm8;
    private String parm9;
    private String parm10;
    private int parmCount;
    private String paginacao;
    private String checkbox;

    private BaseEntity user;

    public BaseEntity getUser() {
        return user;
    }

    public void setUser(final BaseEntity user) {
        this.user = user;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(final String acao) {
        this.acao = acao;
    }

    public String getNomeLookup() {
        return nomeLookup;
    }

    public void setNomeLookup(final String nomeLookup) {
        this.nomeLookup = nomeLookup;
    }

    public String getParm1() {
        return parm1;
    }

    public void setParm1(final String parm1) {
        this.parm1 = parm1;
    }

    public String getParm10() {
        return parm10;
    }

    public void setParm10(final String parm10) {
        this.parm10 = parm10;
    }

    public String getParm2() {
        return parm2;
    }

    public void setParm2(final String parm2) {
        this.parm2 = parm2;
    }

    public String getParm3() {
        return parm3;
    }

    public void setParm3(final String parm3) {
        this.parm3 = parm3;
    }

    public String getParm4() {
        return parm4;
    }

    public void setParm4(final String parm4) {
        this.parm4 = parm4;
    }

    public String getParm5() {
        return parm5;
    }

    public void setParm5(final String parm5) {
        this.parm5 = parm5;
    }

    public String getParm6() {
        return parm6;
    }

    public void setParm6(final String parm6) {
        this.parm6 = parm6;
    }

    public String getParm7() {
        return parm7;
    }

    public void setParm7(final String parm7) {
        this.parm7 = parm7;
    }

    public String getParm8() {
        return parm8;
    }

    public void setParm8(final String parm8) {
        this.parm8 = parm8;
    }

    public String getParm9() {
        return parm9;
    }

    public void setParm9(final String parm9) {
        this.parm9 = parm9;
    }

    public int getParmCount() {
        return parmCount;
    }

    public void setParmCount(final int parmCount) {
        this.parmCount = parmCount;
    }

    // alterei
    public String getPaginacao() {
        return paginacao;
    }

    public void setPaginacao(final String paginacao) {
        this.paginacao = paginacao;
    }

    // para verificar se é radiobutton ou checkbox
    public String getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(final String checkbox) {
        this.checkbox = checkbox;
    }

}
