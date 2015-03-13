package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 *
 * @author pedro
 *
 */
public class ServContratoCatalogoServDTO extends BaseEntity {

    private static final long serialVersionUID = 1582364224581163482L;

    private Integer idCatalogoServico;
    private Integer idServicoContrato;

    private String nomeServico;

    public Integer getIdCatalogoServico() {
        return idCatalogoServico;
    }

    public void setIdCatalogoServico(final Integer idCatalogoServico) {
        this.idCatalogoServico = idCatalogoServico;
    }

    public Integer getIdServicoContrato() {
        return idServicoContrato;
    }

    public void setIdServicoContrato(final Integer idServicoContrato) {
        this.idServicoContrato = idServicoContrato;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

}
