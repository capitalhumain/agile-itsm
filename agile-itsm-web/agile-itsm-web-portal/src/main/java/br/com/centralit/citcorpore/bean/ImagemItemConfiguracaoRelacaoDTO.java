package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ImagemItemConfiguracaoRelacaoDTO extends BaseEntity {

    private static final long serialVersionUID = 12312312345L;

    private Integer idImagemItemConfiguracaoRel;
    private Integer idImagemItemConfiguracao;
    private Integer idImagemItemConfiguracaoPai;

    public Integer getIdImagemItemConfiguracaoRel() {
        return idImagemItemConfiguracaoRel;
    }

    public void setIdImagemItemConfiguracaoRel(final Integer idImagemItemConfiguracaoRel) {
        this.idImagemItemConfiguracaoRel = idImagemItemConfiguracaoRel;
    }

    public Integer getIdImagemItemConfiguracao() {
        return idImagemItemConfiguracao;
    }

    public void setIdImagemItemConfiguracao(final Integer idImagemItemConfiguracao) {
        this.idImagemItemConfiguracao = idImagemItemConfiguracao;
    }

    public Integer getIdImagemItemConfiguracaoPai() {
        return idImagemItemConfiguracaoPai;
    }

    public void setIdImagemItemConfiguracaoPai(final Integer idImagemItemConfiguracaoPai) {
        this.idImagemItemConfiguracaoPai = idImagemItemConfiguracaoPai;
    }

}
