package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class EventosDTO extends BaseEntity {

    private Integer idEvento;
    private Integer idUsuario;
    private Integer idEmpresa;
    private String descricao;
    private String gerarQuando;
    private String ligarCasoDesl;
    private Date dataCriacao;
    private Date dataInicio;
    private Date dataFim;

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(final Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(final Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(final Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getGerarQuando() {
        return gerarQuando;
    }

    public void setGerarQuando(final String gerarQuando) {
        this.gerarQuando = gerarQuando;
    }

    public String getLigarCasoDesl() {
        return ligarCasoDesl;
    }

    public void setLigarCasoDesl(final String ligarCasoDesl) {
        this.ligarCasoDesl = ligarCasoDesl;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(final Date dataCriacao) {
        this.dataCriacao = dataCriacao;
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
