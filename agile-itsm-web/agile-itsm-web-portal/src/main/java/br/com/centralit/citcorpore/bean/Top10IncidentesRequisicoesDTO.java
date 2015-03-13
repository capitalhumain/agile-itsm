package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author euler.ramos
 *         Utilizado no relatório Top10 Incidentes Requisições, trará todas as informações requisitadas por este relatório e já totalizadas.
 */
public class Top10IncidentesRequisicoesDTO extends BaseEntity {

    private static final long serialVersionUID = -6857315975603321041L;

    private Integer id;
    private String descricao;
    private Integer idServico;
    private String nomeServico;
    private Integer qtde;
    private Object listaDetalhe;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(final Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(final Integer idServico) {
        this.idServico = idServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(final Integer qtde) {
        this.qtde = qtde;
    }

    public Object getListaDetalhe() {
        return listaDetalhe;
    }

    public void setListaDetalhe(final Object listaDetalhe) {
        this.listaDetalhe = listaDetalhe;
    }

}
