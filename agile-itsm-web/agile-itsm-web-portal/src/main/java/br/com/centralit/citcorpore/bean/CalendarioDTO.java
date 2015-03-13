package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.util.UtilDatas;

public class CalendarioDTO extends BaseEntity {

    private static final long serialVersionUID = 4675705104260302480L;

    private Integer idCalendario;
    private String descricao;
    private String consideraFeriados;
    private Integer idJornadaSeg;
    private Integer idJornadaTer;
    private Integer idJornadaQua;
    private Integer idJornadaQui;
    private Integer idJornadaSex;
    private Integer idJornadaSab;
    private Integer idJornadaDom;
    private String permiteDataInferiorHoje;

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(final Integer parm) {
        idCalendario = parm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String parm) {
        descricao = parm;
    }

    public String getConsideraFeriados() {
        return consideraFeriados;
    }

    public void setConsideraFeriados(final String parm) {
        consideraFeriados = parm;
    }

    public Integer getIdJornadaSeg() {
        return idJornadaSeg;
    }

    public void setIdJornadaSeg(final Integer parm) {
        if (parm == null || parm == 0) {
            return;
        }
        idJornadaSeg = parm;
    }

    public Integer getIdJornadaTer() {
        return idJornadaTer;
    }

    public void setIdJornadaTer(final Integer parm) {
        if (parm == null || parm == 0) {
            return;
        }
        idJornadaTer = parm;
    }

    public Integer getIdJornadaQua() {
        return idJornadaQua;
    }

    public void setIdJornadaQua(final Integer parm) {
        if (parm == null || parm == 0) {
            return;
        }
        idJornadaQua = parm;
    }

    public Integer getIdJornadaQui() {
        return idJornadaQui;
    }

    public void setIdJornadaQui(final Integer parm) {
        if (parm == null || parm == 0) {
            return;
        }
        idJornadaQui = parm;
    }

    public Integer getIdJornadaSex() {
        return idJornadaSex;
    }

    public void setIdJornadaSex(final Integer parm) {
        if (parm == null || parm == 0) {
            return;
        }
        idJornadaSex = parm;
    }

    public Integer getIdJornadaSab() {
        return idJornadaSab;
    }

    public void setIdJornadaSab(final Integer parm) {
        if (parm == null || parm == 0) {
            return;
        }
        idJornadaSab = parm;
    }

    public Integer getIdJornadaDom() {
        return idJornadaDom;
    }

    public void setIdJornadaDom(final Integer parm) {
        if (parm == null || parm == 0) {
            return;
        }
        idJornadaDom = parm;
    }

    public Integer getIdJornada(final Date dataRef) throws LogicException {
        Integer idJornada = null;
        final int diaSemana = UtilDatas.getDiaSemana(UtilDatas.dateToSTR(dataRef));
        switch (diaSemana) {
        case 1:
            idJornada = this.getIdJornadaDom();
            break;
        case 2:
            idJornada = this.getIdJornadaSeg();
            break;
        case 3:
            idJornada = this.getIdJornadaTer();
            break;
        case 4:
            idJornada = this.getIdJornadaQua();
            break;
        case 5:
            idJornada = this.getIdJornadaQui();
            break;
        case 6:
            idJornada = this.getIdJornadaSex();
            break;
        case 7:
            idJornada = this.getIdJornadaSab();
            break;
        default:
            break;
        }
        return idJornada;
    }

    public String getPermiteDataInferiorHoje() {
        return permiteDataInferiorHoje;
    }

    public void setPermiteDataInferiorHoje(final String permiteDataInferiorHoje) {
        this.permiteDataInferiorHoje = permiteDataInferiorHoje;
    }

}
