package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class ItemGrupoAssinaturaDTO extends BaseEntity {

    private static final long serialVersionUID = -6639560890633897592L;

    private Integer idItemGrupoAssinatura;
    private Integer idGrupoAssinatura;
    private Integer idAssinatura;
    private Integer ordem;
    private Date dataInicio;
    private Date dataFim;

    // Campos auxiliares para apresentação na tela
    private String nomeResponsavel;
    private String papel;
    private String fase;

    public Integer getIdItemGrupoAssinatura() {
        return idItemGrupoAssinatura;
    }

    public void setIdItemGrupoAssinatura(final Integer idItemGrupoAssinatura) {
        this.idItemGrupoAssinatura = idItemGrupoAssinatura;
    }

    public Integer getIdGrupoAssinatura() {
        return idGrupoAssinatura;
    }

    public void setIdGrupoAssinatura(final Integer idGrupoAssinatura) {
        this.idGrupoAssinatura = idGrupoAssinatura;
    }

    public Integer getIdAssinatura() {
        return idAssinatura;
    }

    public void setIdAssinatura(final Integer idAssinatura) {
        this.idAssinatura = idAssinatura;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(final Integer ordem) {
        this.ordem = ordem;
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

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(final String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(final String papel) {
        this.papel = papel;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(final String fase) {
        this.fase = fase;
    }

}
