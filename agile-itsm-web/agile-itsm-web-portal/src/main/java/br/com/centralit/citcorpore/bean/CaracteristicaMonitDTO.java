/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author valdoilo.damasceno
 * @since 13.06.2014
 */
public class CaracteristicaMonitDTO extends BaseEntity {

    private static final long serialVersionUID = 4784276108272700744L;

    private Integer idCaracteristicaMonit;

    private Integer idCaracteristica;

    private Integer idMonitoramentoAtivos;

    private Date dataInicio;

    private Date dataFim;

    public Integer getIdCaracteristicaMonit() {
        return idCaracteristicaMonit;
    }

    public void setIdCaracteristicaMonit(final Integer idCaracteristicaMonit) {
        this.idCaracteristicaMonit = idCaracteristicaMonit;
    }

    public Integer getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(final Integer idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public Integer getIdMonitoramentoAtivos() {
        return idMonitoramentoAtivos;
    }

    public void setIdMonitoramentoAtivos(final Integer idMonitoramentoAtivos) {
        this.idMonitoramentoAtivos = idMonitoramentoAtivos;
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
