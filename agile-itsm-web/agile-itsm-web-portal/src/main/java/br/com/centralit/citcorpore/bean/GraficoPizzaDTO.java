/**
 *
 */
package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author breno.guimaraes
 *
 */
public class GraficoPizzaDTO extends BaseEntity {

    private static final long serialVersionUID = 6988415536736065332L;

    private String campo;
    private Double valor;

    public String getCampo() {
        return campo;
    }

    public void setCampo(final String campo) {
        this.campo = campo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(final Double valor) {
        this.valor = valor;
    }

}
