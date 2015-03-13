package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author breno.guimaraes
 *
 */

public class ImagemItemConfiguracaoDTO extends BaseEntity {

    private static final long serialVersionUID = 4649193570630759516L;

    private Integer idImagemItemConfiguracao;
    private Integer idServico;
    private Integer idItemConfiguracao;
    private Integer posx;
    private Integer posy;
    private String descricao;
    private Integer idImagemItemConfiguracaoPai;
    private Collection<ImagemItemConfiguracaoRelacaoDTO> idImagemItemConfiguracaoPaiCol;
    private String idImagemItemConfiguracaoPaiColSerializado;

    private String caminhoImagem;

    private String identificacao;

    /**
     * @return valor do atributo idImagemItemConfiguracao.
     */
    public Integer getIdImagemItemConfiguracao() {
        return idImagemItemConfiguracao;
    }

    /**
     * Define valor do atributo idImagemItemConfiguracao.
     *
     * @param idImagemItemConfiguracao
     */
    public void setIdImagemItemConfiguracao(final Integer idImagemItemConfiguracao) {
        this.idImagemItemConfiguracao = idImagemItemConfiguracao;
    }

    /**
     * @return valor do atributo idServico.
     */
    public Integer getIdServico() {
        return idServico;
    }

    /**
     * Define valor do atributo idServico.
     *
     * @param idServico
     */
    public void setIdServico(final Integer idServico) {
        this.idServico = idServico;
    }

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
     * @return valor do atributo posx.
     */
    public Integer getPosx() {
        return posx;
    }

    /**
     * Define valor do atributo posx.
     *
     * @param posx
     */
    public void setPosx(final Integer posx) {
        this.posx = posx;
    }

    /**
     * @return valor do atributo posy.
     */
    public Integer getPosy() {
        return posy;
    }

    /**
     * Define valor do atributo posy.
     *
     * @param posy
     */
    public void setPosy(final Integer posy) {
        this.posy = posy;
    }

    /**
     * @return valor do atributo descricao.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define valor do atributo descricao.
     *
     * @param descricao
     */
    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return valor do atributo idImagemItemConfiguracaoPai.
     */
    public Integer getIdImagemItemConfiguracaoPai() {
        return idImagemItemConfiguracaoPai;
    }

    public Collection<ImagemItemConfiguracaoRelacaoDTO> getIdImagemItemConfiguracaoPaiCol() {
        return idImagemItemConfiguracaoPaiCol;
    }

    public void setIdImagemItemConfiguracaoPaiCol(final Collection<ImagemItemConfiguracaoRelacaoDTO> idImagemItemConfiguracaoPaiCol) {
        this.idImagemItemConfiguracaoPaiCol = idImagemItemConfiguracaoPaiCol;
    }

    /**
     * Define valor do atributo idImagemItemConfiguracaoPai.
     *
     * @param idImagemItemConfiguracaoPai
     */
    public void setIdImagemItemConfiguracaoPai(final Integer idImagemItemConfiguracaoPai) {
        this.idImagemItemConfiguracaoPai = idImagemItemConfiguracaoPai;
    }

    /**
     * @return valor do atributo caminhoImagem.
     */
    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    /**
     * Define valor do atributo caminhoImagem.
     *
     * @param caminhoImagem
     */
    public void setCaminhoImagem(final String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
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

    public String getIdImagemItemConfiguracaoPaiColSerializado() {
        return idImagemItemConfiguracaoPaiColSerializado;
    }

    public void setIdImagemItemConfiguracaoPaiColSerializado(final String idImagemItemConfiguracaoPaiColSerializado) {
        this.idImagemItemConfiguracaoPaiColSerializado = idImagemItemConfiguracaoPaiColSerializado;
    }

}
