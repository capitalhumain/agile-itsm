package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class RoteiroViagemDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idRoteiroViagem;
    private Date dataInicio;
    private Date dataFim;
    private Integer idSolicitacaoServico;
    private Integer idIntegrante;
    private Integer origem;
    private Integer destino;
    private String nomeCidade;
    private Date ida;
    private Date volta;

    public Integer getIdRoteiroViagem() {
        return idRoteiroViagem;
    }

    public void setIdRoteiroViagem(final Integer idRoteiroViagem) {
        this.idRoteiroViagem = idRoteiroViagem;
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

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public Integer getIdIntegrante() {
        return idIntegrante;
    }

    public void setIdIntegrante(final Integer idIntegrante) {
        this.idIntegrante = idIntegrante;
    }

    public Integer getOrigem() {
        return origem;
    }

    public void setOrigem(final Integer origem) {
        this.origem = origem;
    }

    public Integer getDestino() {
        return destino;
    }

    public void setDestino(final Integer destino) {
        this.destino = destino;
    }

    public Date getIda() {
        return ida;
    }

    public void setIda(final Date ida) {
        this.ida = ida;
    }

    public Date getVolta() {
        return volta;
    }

    public void setVolta(final Date volta) {
        this.volta = volta;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(final String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

}
