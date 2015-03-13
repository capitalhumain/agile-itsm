package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.UtilDatas;

public class AlcadaCentroResultadoDTO extends BaseEntity {

    private static final long serialVersionUID = 4182540164619994646L;

    private Integer idAlcadaCentroResultado;
    private Integer idCentroResultado;
    private Integer idEmpregado;
    private Integer idAlcada;
    private Date dataInicio;
    private Date dataFim;
    private String nomeEmpregado;
    private String nomeCentroResultado;
    private String nomeAlcada;

    private Integer sequencia;
    private String dataInicioStr;
    private String dataFimStr;

    public Integer getIdAlcadaCentroResultado() {
        return idAlcadaCentroResultado;
    }

    public void setIdAlcadaCentroResultado(final Integer idAlcadaCentroResultado) {
        this.idAlcadaCentroResultado = idAlcadaCentroResultado;
    }

    public Integer getIdCentroResultado() {
        return idCentroResultado;
    }

    public void setIdCentroResultado(final Integer idCentroResultado) {
        this.idCentroResultado = idCentroResultado;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    public Integer getIdAlcada() {
        return idAlcada;
    }

    public void setIdAlcada(final Integer idAlcada) {
        this.idAlcada = idAlcada;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
        if (dataInicio != null) {
            dataInicioStr = UtilDatas.dateToSTR(dataInicio);
        }
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
        if (dataFim != null) {
            dataFimStr = UtilDatas.dateToSTR(dataFim);
        }
    }

    public String getNomeEmpregado() {
        return nomeEmpregado;
    }

    public void setNomeEmpregado(final String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

    public String getNomeCentroResultado() {
        return nomeCentroResultado;
    }

    public void setNomeCentroResultado(final String nomeCentroResultado) {
        this.nomeCentroResultado = nomeCentroResultado;
    }

    public String getNomeAlcada() {
        return nomeAlcada;
    }

    public void setNomeAlcada(final String nomeAlcada) {
        this.nomeAlcada = nomeAlcada;
    }

    public String getDataInicioStr() {
        return dataInicioStr;
    }

    public void setDataInicioStr(final String dataInicioStr) {
        this.dataInicioStr = dataInicioStr;
    }

    public String getDataFimStr() {
        return dataFimStr;
    }

    public void setDataFimStr(final String dataFimStr) {
        this.dataFimStr = dataFimStr;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(final Integer sequencia) {
        this.sequencia = sequencia;
    }

}
