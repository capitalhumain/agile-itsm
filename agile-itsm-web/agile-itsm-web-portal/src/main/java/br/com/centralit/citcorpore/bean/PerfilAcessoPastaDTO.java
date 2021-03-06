/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author valdoilo.damasceno
 *
 */
public class PerfilAcessoPastaDTO extends BaseEntity {

    private static final long serialVersionUID = 1017195976624432499L;

    private Integer idPerfilAcesso;

    private Integer idPasta;

    private String nomePasta;

    private Date dataInicio;

    private Date dataFim;

    private String aprovaBaseConhecimento;

    private String permiteLeitura;

    private String permiteLeituraGravacao;

    /**
     * @return valor do atributo idPerfilAcesso.
     */
    public Integer getIdPerfilAcesso() {
        return idPerfilAcesso;
    }

    /**
     * Define valor do atributo idPerfilAcesso.
     *
     * @param idPerfilAcesso
     */
    public void setIdPerfilAcesso(final Integer idPerfilAcesso) {
        this.idPerfilAcesso = idPerfilAcesso;
    }

    /**
     * @return valor do atributo idPasta.
     */
    public Integer getIdPasta() {
        return idPasta;
    }

    /**
     * Define valor do atributo idPasta.
     *
     * @param idPasta
     */
    public void setIdPasta(final Integer idPasta) {
        this.idPasta = idPasta;
    }

    /**
     * @return valor do atributo dataInicio.
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * Define valor do atributo dataInicio.
     *
     * @param dataInicio
     */
    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return valor do atributo dataFim.
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * Define valor do atributo dataFim.
     *
     * @param dataFim
     */
    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return valor do atributo aprovaBaseConhecimento.
     */
    public String getAprovaBaseConhecimento() {
        return aprovaBaseConhecimento;
    }

    /**
     * Define valor do atributo aprovaBaseConhecimento.
     *
     * @param aprovaBaseConhecimento
     */
    public void setAprovaBaseConhecimento(final String aprovaBaseConhecimento) {
        this.aprovaBaseConhecimento = aprovaBaseConhecimento;
    }

    /**
     * @return
     */
    public String getNomePasta() {
        return nomePasta;
    }

    /**
     * Define valor do atributo nomePasta.
     *
     * @param nomePasta
     */
    public void setNomePasta(final String nomePasta) {
        this.nomePasta = nomePasta;
    }

    /**
     * @return the permiteLeitura
     */
    public String getPermiteLeitura() {
        return permiteLeitura;
    }

    /**
     * @param permiteLeitura
     *            the permiteLeitura to set
     */
    public void setPermiteLeitura(final String permiteLeitura) {
        this.permiteLeitura = permiteLeitura;
    }

    /**
     * @return the permiteLeituraGravacao
     */
    public String getPermiteLeituraGravacao() {
        return permiteLeituraGravacao;
    }

    /**
     * @param permiteLeituraGravacao
     *            the permiteLeituraGravacao to set
     */
    public void setPermiteLeituraGravacao(final String permiteLeituraGravacao) {
        this.permiteLeituraGravacao = permiteLeituraGravacao;
    }

}
