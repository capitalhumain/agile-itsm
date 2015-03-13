/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Vadoilo Damasceno
 *
 */
public class PatrimonioDTO extends BaseEntity {

    private static final long serialVersionUID = -1730028252715834436L;

    private Integer idItemConfiguracao;

    private Integer idItemConfiguracaoFilho;

    private String identificacao;

    private String nomeItemConfiguracaoPai;

    private Integer idTipoItemConfiguracao;

    private List tipoItemConfiguracao;

    private TipoItemConfiguracaoDTO tipoItemConfiguracaoSerializadas;

    /**
     * @return valor do atributo idItemConfiguracao.
     */
    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    /**
     * Define valor do atributo idItemConfiguracao.
     *
     * @param idItemConfiguracao
     */
    public void setIdItemConfiguracao(final Integer idItemConfiguracao) {
        this.idItemConfiguracao = idItemConfiguracao;
    }

    /**
     * @return valor do atributo identificacao.
     */
    public String getIdentificacao() {
        return identificacao;
    }

    /**
     * Define valor do atributo identificacao.
     *
     * @param identificacao
     */
    public void setIdentificacao(final String identificacao) {
        this.identificacao = identificacao;
    }

    /**
     * @return valor do atributo idTipoItemConfiguracao.
     */
    public Integer getIdTipoItemConfiguracao() {
        return idTipoItemConfiguracao;
    }

    /**
     * Define valor do atributo idTipoItemConfiguracao.
     *
     * @param idTipoItemConfiguracao
     */
    public void setIdTipoItemConfiguracao(final Integer idTipoItemConfiguracao) {
        this.idTipoItemConfiguracao = idTipoItemConfiguracao;
    }

    /**
     * @return the nomeItemConfiguracaoPai
     */
    public String getNomeItemConfiguracaoPai() {
        return nomeItemConfiguracaoPai;
    }

    /**
     * @param nomeItemConfiguracaoPai
     *            the nomeItemConfiguracaoPai to set
     */
    public void setNomeItemConfiguracaoPai(final String nomeItemConfiguracaoPai) {
        this.nomeItemConfiguracaoPai = nomeItemConfiguracaoPai;
    }

    /**
     * @return the tipoItemConfiguracao
     */
    public List getTipoItemConfiguracao() {
        return tipoItemConfiguracao;
    }

    /**
     * @param tipoItemConfiguracao
     *            the tipoItemConfiguracao to set
     */
    public void setTipoItemConfiguracao(final List tipoItemConfiguracao) {
        this.tipoItemConfiguracao = tipoItemConfiguracao;
    }

    /**
     * @return the tipoItemConfiguracaoSerializadas
     */
    public TipoItemConfiguracaoDTO getTipoItemConfiguracaoSerializadas() {
        return tipoItemConfiguracaoSerializadas;
    }

    /**
     * @param tipoItemConfiguracaoSerializadas
     *            the tipoItemConfiguracaoSerializadas to set
     */
    public void setTipoItemConfiguracaoSerializadas(final TipoItemConfiguracaoDTO tipoItemConfiguracaoSerializadas) {
        this.tipoItemConfiguracaoSerializadas = tipoItemConfiguracaoSerializadas;
    }

    public Integer getIdItemConfiguracaoFilho() {
        return idItemConfiguracaoFilho;
    }

    public void setIdItemConfiguracaoFilho(final Integer idItemConfiguracaoFilho) {
        this.idItemConfiguracaoFilho = idItemConfiguracaoFilho;
    }

}
