package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author ygor.magalhaes
 *
 */
public class ComandoSistemaOperacionalDTO extends BaseEntity {

    private static final long serialVersionUID = -6847753633682691321L;

    private Integer id;

    private Integer idComando;

    private Integer idSistemaOperacional;

    private String comando;

    /**
     * @return valor do atributo idComando.
     */
    public Integer getIdComando() {
        return idComando;
    }

    /**
     * Define valor do atributo idComando.
     *
     * @param idComando
     */
    public void setIdComando(final Integer idComando) {
        this.idComando = idComando;
    }

    /**
     * @return valor do atributo idSistemaOperacional.
     */
    public Integer getIdSistemaOperacional() {
        return idSistemaOperacional;
    }

    /**
     * Define valor do atributo idSistemaOperacional.
     *
     * @param idSistemaOperacional
     */
    public void setIdSistemaOperacional(final Integer idSistemaOperacional) {
        this.idSistemaOperacional = idSistemaOperacional;
    }

    /**
     * @return valor do atributo comando.
     */
    public String getComando() {
        return comando;
    }

    /**
     * Define valor do atributo comando.
     *
     * @param comando
     */
    public void setComando(final String comando) {
        this.comando = comando;
    }

    /**
     * @return valor do atributo id.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Define valor do atributo id.
     *
     * @param id
     */
    @Override
    public void setId(final Integer id) {
        this.id = id;
    }

}
