package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.Util;

public class JornadaTrabalhoDTO extends BaseEntity {

    private Integer idJornada;
    private String descricao;
    private String inicio1;
    private String termino1;
    private String inicio2;
    private String termino2;
    private String inicio3;
    private String termino3;
    private String inicio4;
    private String termino4;
    private String inicio5;
    private String termino5;
    private String cargaHoraria;
    private Date dataInicio;
    private Date dataFim;

    private Timestamp dataHoraInicial;

    private double[] inicio;
    private double[] termino;

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(final Integer parm) {
        idJornada = parm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String parm) {
        descricao = parm;
    }

    public String getInicio1() {
        return inicio1;
    }

    public void setInicio1(final String parm) {
        inicio1 = parm;
    }

    public String getTermino1() {
        return termino1;
    }

    public void setTermino1(final String parm) {
        termino1 = parm;
    }

    public String getInicio2() {
        return inicio2;
    }

    public void setInicio2(final String parm) {
        inicio2 = parm;
    }

    public String getTermino2() {
        return termino2;
    }

    public void setTermino2(final String parm) {
        termino2 = parm;
    }

    public String getInicio3() {
        return inicio3;
    }

    public void setInicio3(final String parm) {
        inicio3 = parm;
    }

    public String getTermino3() {
        return termino3;
    }

    public void setTermino3(final String parm) {
        termino3 = parm;
    }

    public String getInicio4() {
        return inicio4;
    }

    public void setInicio4(final String parm) {
        inicio4 = parm;
    }

    public String getTermino4() {
        return termino4;
    }

    public void setTermino4(final String parm) {
        termino4 = parm;
    }

    public String getInicio5() {
        return inicio5;
    }

    public void setInicio5(final String parm) {
        inicio5 = parm;
    }

    public String getTermino5() {
        return termino5;
    }

    public void setTermino5(final String parm) {
        termino5 = parm;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(final String parm) {
        cargaHoraria = parm;
    }

    public String getInicio(final int i) {
        String result = null;
        switch (i) {
        case 1:
            if (this.getInicio1() != null && this.getInicio1().trim().length() > 0) {
                result = this.getInicio1();
            }
            break;
        case 2:
            if (this.getInicio2() != null && this.getInicio2().trim().length() > 0) {
                result = this.getInicio2();
            }
            break;
        case 3:
            if (this.getInicio3() != null && this.getInicio3().trim().length() > 0) {
                result = this.getInicio3();
            }
            break;
        case 4:
            if (this.getInicio4() != null && this.getInicio4().trim().length() > 0) {
                result = this.getInicio4();
            }
            break;
        case 5:
            if (this.getInicio5() != null && this.getInicio5().trim().length() > 0) {
                result = this.getInicio5();
            }
            break;
        }
        return result;
    }

    public String getTermino(final int i) {
        String result = null;
        switch (i) {
        case 1:
            if (this.getTermino1() != null && this.getTermino1().trim().length() > 0) {
                result = this.getTermino1();
            }
            break;
        case 2:
            if (this.getTermino2() != null && this.getTermino2().trim().length() > 0) {
                result = this.getTermino2();
            }
            break;
        case 3:
            if (this.getTermino3() != null && this.getTermino3().trim().length() > 0) {
                result = this.getTermino3();
            }
            break;
        case 4:
            if (this.getTermino4() != null && this.getTermino4().trim().length() > 0) {
                result = this.getTermino4();
            }
            break;
        case 5:
            if (this.getTermino5() != null && this.getTermino5().trim().length() > 0) {
                result = this.getTermino5();
            }
            break;
        }
        return result;
    }

    public double[] getInicio() {
        inicio = new double[] {99, 99, 99, 99, 99, 99};
        for (int i = 1; i <= 5; i++) {
            if (this.getInicio(i) != null) {
                try {
                    inicio[i] = Util.getHoraDbl(this.getInicio(i));
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return inicio;
    }

    public void setInicio(final double[] inicio) {
        this.inicio = inicio;
    }

    public double[] getTermino() {
        termino = new double[] {0, 0, 0, 0, 0, 0};
        for (int i = 1; i <= 5; i++) {
            if (this.getInicio(i) != null) {
                try {
                    termino[i] = Util.getHoraDbl(this.getTermino(i));
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return termino;
    }

    public void setTermino(final double[] termino) {
        this.termino = termino;
    }

    public Timestamp getDataHoraInicial() {
        return dataHoraInicial;
    }

    public void setDataHoraInicial(final Timestamp dataHoraInicial) {
        this.dataHoraInicial = dataHoraInicial;
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

}
