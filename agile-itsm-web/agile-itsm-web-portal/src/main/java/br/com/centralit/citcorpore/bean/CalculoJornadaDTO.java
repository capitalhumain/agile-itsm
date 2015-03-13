package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class CalculoJornadaDTO extends BaseEntity {

    private Integer idCalendario;
    private Timestamp dataHoraInicial;
    private Integer prazoHH;
    private Integer prazoMM;
    private Integer tempoDecorridoHH;
    private Integer tempoDecorridoMM;
    private Integer tempoDecorridoSS;

    private Timestamp dataHoraFinal;

    public CalculoJornadaDTO(final Integer idCalendario, final Timestamp dataHoraInicial, final Integer prazoHH, final Integer prazoMM) {
        this.idCalendario = idCalendario;
        this.dataHoraInicial = dataHoraInicial;
        this.prazoHH = prazoHH;
        this.prazoMM = prazoMM;
    }

    public CalculoJornadaDTO(final Integer idCalendario, final Timestamp dataHoraInicial) {
        this.idCalendario = idCalendario;
        this.dataHoraInicial = dataHoraInicial;
    }

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(final Integer idCalendario) {
        this.idCalendario = idCalendario;
    }

    public Timestamp getDataHoraInicial() {
        return dataHoraInicial;
    }

    public void setDataHoraInicial(final Timestamp dataHoraInicial) {
        this.dataHoraInicial = dataHoraInicial;
    }

    public Integer getPrazoHH() {
        return prazoHH;
    }

    public void setPrazoHH(final Integer prazoHH) {
        this.prazoHH = prazoHH;
    }

    public Integer getPrazoMM() {
        return prazoMM;
    }

    public void setPrazoMM(final Integer prazoMM) {
        this.prazoMM = prazoMM;
    }

    public Timestamp getDataHoraFinal() {
        return dataHoraFinal;
    }

    public void setDataHoraFinal(final Timestamp dataHoraFinal) {
        this.dataHoraFinal = dataHoraFinal;
    }

    public Integer getTempoDecorridoHH() {
        return tempoDecorridoHH;
    }

    public void setTempoDecorridoHH(final Integer tempoDecorridoHH) {
        this.tempoDecorridoHH = tempoDecorridoHH;
    }

    public Integer getTempoDecorridoMM() {
        return tempoDecorridoMM;
    }

    public void setTempoDecorridoMM(final Integer tempoDecorridoMM) {
        this.tempoDecorridoMM = tempoDecorridoMM;
    }

    public Integer getTempoDecorridoSS() {
        return tempoDecorridoSS;
    }

    public void setTempoDecorridoSS(final Integer tempoDecorridoSS) {
        this.tempoDecorridoSS = tempoDecorridoSS;
    }

}
