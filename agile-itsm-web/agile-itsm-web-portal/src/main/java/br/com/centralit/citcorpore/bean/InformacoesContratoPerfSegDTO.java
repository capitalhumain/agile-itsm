package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class InformacoesContratoPerfSegDTO extends BaseEntity {

    private static final long serialVersionUID = 1037812758934980332L;
    private Integer idInformacoesContratoConfig;
    private Integer idPerfilSeguranca;

    public Integer getIdInformacoesContratoConfig() {
        return idInformacoesContratoConfig;
    }

    public void setIdInformacoesContratoConfig(final Integer idInformacoesContratoConfig) {
        this.idInformacoesContratoConfig = idInformacoesContratoConfig;
    }

    public Integer getIdPerfilSeguranca() {
        return idPerfilSeguranca;
    }

    public void setIdPerfilSeguranca(final Integer idPerfilSeguranca) {
        this.idPerfilSeguranca = idPerfilSeguranca;
    }

}
