package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class AtividadeDTO extends BaseEntity {

    private static final long serialVersionUID = 644345048805944191L;

    private Integer idAtividade;
    private Integer idEtapa;
    private Integer idTipoAtividade;
    private Integer idAtividadeProxima;
    private String nomeAtividade;
    private Integer ordem;
    private String grupoExecutor;

    public String getGrupoExecutor() {
        return grupoExecutor;
    }

    public void setGrupoExecutor(final String grupoExecutor) {
        this.grupoExecutor = grupoExecutor;
    }

    public Integer getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(final Integer idAtividade) {
        this.idAtividade = idAtividade;
    }

    public Integer getIdAtividadeProxima() {
        return idAtividadeProxima;
    }

    public void setIdAtividadeProxima(final Integer idAtividadeProxima) {
        this.idAtividadeProxima = idAtividadeProxima;
    }

    public Integer getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(final Integer idEtapa) {
        this.idEtapa = idEtapa;
    }

    public Integer getIdTipoAtividade() {
        return idTipoAtividade;
    }

    public void setIdTipoAtividade(final Integer idTipoAtividade) {
        this.idTipoAtividade = idTipoAtividade;
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public void setNomeAtividade(final String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(final Integer ordem) {
        this.ordem = ordem;
    }

}
