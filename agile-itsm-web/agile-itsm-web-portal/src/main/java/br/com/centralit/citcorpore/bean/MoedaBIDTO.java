package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class MoedaBIDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idMoeda;
    private String nomeMoeda;
    private String usarCotacao;
    private Date dataInicio;
    private Date dataFim;
    private Integer idConexaoBI;

    public Integer getIdMoeda() {
        return idMoeda;
    }

    public void setIdMoeda(final Integer idMoeda) {
        this.idMoeda = idMoeda;
    }

    public String getNomeMoeda() {
        return nomeMoeda;
    }

    public void setNomeMoeda(final String nomeMoeda) {
        this.nomeMoeda = nomeMoeda;
    }

    public String getUsarCotacao() {
        return usarCotacao;
    }

    public void setUsarCotacao(final String usarCotacao) {
        this.usarCotacao = usarCotacao;
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

    public Integer getIdConexaoBI() {
        return idConexaoBI;
    }

    public void setIdConexaoBI(final Integer idConexaoBI) {
        this.idConexaoBI = idConexaoBI;
    }

}
