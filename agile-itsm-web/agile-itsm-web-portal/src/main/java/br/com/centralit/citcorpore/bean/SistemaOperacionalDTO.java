package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author ygor.magalhaes
 *
 */
public class SistemaOperacionalDTO extends BaseEntity {

    private Integer id;
    private String nome;

    /**
     * @return valor do atributo id.
     */
    public Integer getId() {
	return id;
    }

    /**
     * Define valor do atributo id.
     * 
     * @param id
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * @return valor do atributo nome.
     */
    public String getNome() {
	return nome;
    }

    /**
     * Define valor do atributo nome.
     * 
     * @param nome
     */
    public void setNome(String nome) {
	this.nome = nome;
    }

}