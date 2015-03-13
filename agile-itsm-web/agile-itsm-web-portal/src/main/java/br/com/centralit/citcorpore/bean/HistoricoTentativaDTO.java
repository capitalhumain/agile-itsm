package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class HistoricoTentativaDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idHistoricoTentativa;
    private Integer idEvento;
    private Integer idEmpregado;
    private Integer idBaseItemConfiguracao;
    private Integer idItemConfiguracao;
    private String descricao;
    private String hora;
    private Date data;

    public Integer getIdHistoricoTentativa() {
        return idHistoricoTentativa;
    }

    public void setIdHistoricoTentativa(final Integer parm) {
        idHistoricoTentativa = parm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String parm) {
        descricao = parm;
    }

    public Date getData() {
        return data;
    }

    public void setData(final Date parm) {
        data = parm;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(final String parm) {
        hora = parm;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(final Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    public Integer getIdBaseItemConfiguracao() {
        return idBaseItemConfiguracao;
    }

    public void setIdBaseItemConfiguracao(final Integer idBaseItemConfiguracao) {
        this.idBaseItemConfiguracao = idBaseItemConfiguracao;
    }

    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    public void setIdItemConfiguracao(final Integer idItemConfiguracao) {
        this.idItemConfiguracao = idItemConfiguracao;
    }

}
