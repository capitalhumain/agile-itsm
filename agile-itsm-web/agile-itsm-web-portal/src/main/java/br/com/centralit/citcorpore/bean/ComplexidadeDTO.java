package br.com.centralit.citcorpore.bean;

import java.math.BigDecimal;

import br.com.agileitsm.model.support.BaseEntity;

/**
 *
 * @author rodrigo.oliveira
 *
 */
public class ComplexidadeDTO extends BaseEntity {

    private static final long serialVersionUID = -4685023069025051625L;

    private Integer idContrato;

    private String complexidade;

    private BigDecimal valorComplexidade;

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(final String complexidade) {
        this.complexidade = complexidade;
    }

    public BigDecimal getValorComplexidade() {
        return valorComplexidade;
    }

    public void setValorComplexidade(final BigDecimal valorComplexidade) {
        this.valorComplexidade = valorComplexidade;
    }

}
