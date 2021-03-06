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
public class NotificacaoUsuarioMonitDTO extends BaseEntity {

    private static final long serialVersionUID = -8918038669670905991L;

    private Integer idNotificacaoUsuarioMonit;

    private Integer idMonitoramentoAtivos;

    private Integer idUsuario;

    private Date dataInicio;

    private Date dataFim;

    public Integer getIdNotificacaoUsuarioMonit() {
        return idNotificacaoUsuarioMonit;
    }

    public void setIdNotificacaoUsuarioMonit(final Integer idNotificacaoUsuarioMonit) {
        this.idNotificacaoUsuarioMonit = idNotificacaoUsuarioMonit;
    }

    public Integer getIdMonitoramentoAtivos() {
        return idMonitoramentoAtivos;
    }

    public void setIdMonitoramentoAtivos(final Integer idMonitoramentoAtivos) {
        this.idMonitoramentoAtivos = idMonitoramentoAtivos;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(final Integer idUsuario) {
        this.idUsuario = idUsuario;
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
