package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class LinguaDTO extends BaseEntity {

    private Integer idLingua;

    private String nome;

    private String sigla;

    private Date dataInicio;

    private Date dataFim;

    /**
     * @return the idLingua
     */
    public Integer getIdLingua() {
        return idLingua;
    }

    /**
     * @param idLingua
     *            the idLingua to set
     */
    public void setIdLingua(final Integer idLingua) {
        this.idLingua = idLingua;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome
     *            the nome to set
     */
    public void setNome(final String nome) {
        this.nome = nome;
    }

    /**
     * @return the sigla
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * @param sigla
     *            the sigla to set
     */
    public void setSigla(final String sigla) {
        this.sigla = sigla;
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio
     *            the dataInicio to set
     */
    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim
     *            the dataFim to set
     */
    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

}
