package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class PrioridadeDTO extends BaseEntity {

    private static final long serialVersionUID = -7848776827100833523L;

    private Integer idPrioridade;
    private Integer idEmpresa;
    private String nomePrioridade;
    private String situacao;
    private String grupoPrioridade;
    private Integer quantidade;

    public String getGrupoPrioridade() {
        if (grupoPrioridade != null) {
            return grupoPrioridade.trim();
        }

        return grupoPrioridade;
    }

    public void setGrupoPrioridade(final String grupoPrioridade) {
        this.grupoPrioridade = grupoPrioridade;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getNomePrioridade() {
        return nomePrioridade;
    }

    public void setNomePrioridade(final String nomePrioridade) {
        this.nomePrioridade = nomePrioridade;
    }

    public Integer getIdPrioridade() {
        return idPrioridade;
    }

    public void setIdPrioridade(final Integer idPrioridade) {
        this.idPrioridade = idPrioridade;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(final Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final Integer quantidade) {
        this.quantidade = quantidade;
    }

}
