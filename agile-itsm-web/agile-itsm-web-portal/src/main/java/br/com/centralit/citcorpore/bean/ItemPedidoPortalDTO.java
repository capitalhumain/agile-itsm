package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ItemPedidoPortalDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idItemPedidoPortal;
    private Integer idPedidoPortal;
    private Integer idSolicitacaoServico;
    private Double valor;

    public Integer getIdItemPedidoPortal() {
        return idItemPedidoPortal;
    }

    public void setIdItemPedidoPortal(final Integer idItemPedidoPortal) {
        this.idItemPedidoPortal = idItemPedidoPortal;
    }

    public Integer getIdPedidoPortal() {
        return idPedidoPortal;
    }

    public void setIdPedidoPortal(final Integer idPedidoPortal) {
        this.idPedidoPortal = idPedidoPortal;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(final Double valor) {
        this.valor = valor;
    }

}
