package br.com.centralit.citcorpore.bean;

import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;

public class MapaDesenhoServicoDTO extends BaseEntity {

    private static final long serialVersionUID = -6598824528431168909L;

    private Integer idMapaDesenhoServico;
    private Integer idServico;
    private Integer idItemConfiguracao;

    private String nomeServico;
    private String identificacao;
    private Integer idImagemItemConfiguracao;

    private String listaItensConfiguracaoSerializada;

    private List listaImagensItensConfiguracao;

    /**
     * @return valor do atributo idMapaDesenhoServico.
     */
    public Integer getIdMapaDesenhoServico() {
        return idMapaDesenhoServico;
    }

    /**
     * Define valor do atributo idMapaDesenhoServico.
     *
     * @param idMapaDesenhoServico
     */
    public void setIdMapaDesenhoServico(final Integer idMapaDesenhoServico) {
        this.idMapaDesenhoServico = idMapaDesenhoServico;
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
     * @return valor do atributo nomeServico.
     */
    public String getNomeServico() {
        return nomeServico;
    }

    /**
     * Define valor do atributo nomeServico.
     *
     * @param nomeServico
     */
    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
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
     * @return valor do atributo listaItensConfiguracaoSerializada.
     */
    public String getListaItensConfiguracaoSerializada() {
        return listaItensConfiguracaoSerializada;
    }

    /**
     * Define valor do atributo listaItensConfiguracaoSerializada.
     *
     * @param listaItensConfiguracaoSerializada
     */
    public void setListaItensConfiguracaoSerializada(final String listaItensConfiguracaoSerializada) {
        this.listaItensConfiguracaoSerializada = listaItensConfiguracaoSerializada;
    }

    /**
     * @return valor do atributo listaImagensItensConfiguracao.
     */
    public List getListaImagensItensConfiguracao() {
        return listaImagensItensConfiguracao;
    }

    /**
     * Define valor do atributo listaImagensItensConfiguracao.
     *
     * @param listaImagensItensConfiguracao
     */
    public void setListaImagensItensConfiguracao(final List listaImagensItensConfiguracao) {
        this.listaImagensItensConfiguracao = listaImagensItensConfiguracao;
    }

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

}
