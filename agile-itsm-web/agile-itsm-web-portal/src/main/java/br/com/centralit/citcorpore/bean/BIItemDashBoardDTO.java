package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class BIItemDashBoardDTO extends BaseEntity {

    private Integer idItemDashBoard;
    private Integer idDashBoard;
    private Integer idConsulta;
    private String titulo;
    private Integer posicao;
    private Integer itemTop;
    private Integer itemLeft;
    private Integer itemWidth;
    private Integer itemHeight;
    private String parmsSubst;

    public Integer getIdItemDashBoard() {
        return idItemDashBoard;
    }

    public void setIdItemDashBoard(final Integer parm) {
        idItemDashBoard = parm;
    }

    public Integer getIdDashBoard() {
        return idDashBoard;
    }

    public void setIdDashBoard(final Integer parm) {
        idDashBoard = parm;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(final Integer parm) {
        idConsulta = parm;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String parm) {
        titulo = parm;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(final Integer parm) {
        posicao = parm;
    }

    public Integer getItemTop() {
        return itemTop;
    }

    public void setItemTop(final Integer parm) {
        itemTop = parm;
    }

    public Integer getItemLeft() {
        return itemLeft;
    }

    public void setItemLeft(final Integer parm) {
        itemLeft = parm;
    }

    public Integer getItemWidth() {
        return itemWidth;
    }

    public void setItemWidth(final Integer parm) {
        itemWidth = parm;
    }

    public Integer getItemHeight() {
        return itemHeight;
    }

    public void setItemHeight(final Integer parm) {
        itemHeight = parm;
    }

    public String getParmsSubst() {
        return parmsSubst;
    }

    public void setParmsSubst(final String parmsSubst) {
        this.parmsSubst = parmsSubst;
    }

}
