package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class GrupoAssinaturaDTO extends BaseEntity {

    private static final long serialVersionUID = -7166007883463244313L;

    private Integer idGrupoAssinatura;
    private String titulo;
    private Date dataInicio;
    private Date dataFim;

    public Integer getIdGrupoAssinatura() {
        return idGrupoAssinatura;
    }

    public void setIdGrupoAssinatura(final Integer idGrupoAssinatura) {
        this.idGrupoAssinatura = idGrupoAssinatura;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
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
