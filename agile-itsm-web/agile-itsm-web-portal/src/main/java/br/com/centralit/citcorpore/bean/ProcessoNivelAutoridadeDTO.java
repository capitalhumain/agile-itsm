package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ProcessoNivelAutoridadeDTO extends BaseEntity {

    private Integer idProcessoNegocio;
    private Integer idNivelAutoridade;
    private String permiteAprovacaoPropria;
    private String permiteSolicitacao;
    private Integer antecedenciaMinimaAprovacao;

    private Integer sequencia;
    private Integer hierarquia;
    private NivelAutoridadeDTO nivelAutoridadeDto;
    private LimiteAprovacaoDTO limiteAprovacaoDto;

    public Integer getIdProcessoNegocio() {
        return idProcessoNegocio;
    }

    public void setIdProcessoNegocio(final Integer parm) {
        idProcessoNegocio = parm;
    }

    public Integer getIdNivelAutoridade() {
        return idNivelAutoridade;
    }

    public void setIdNivelAutoridade(final Integer parm) {
        idNivelAutoridade = parm;
    }

    public String getPermiteAprovacaoPropria() {
        return permiteAprovacaoPropria;
    }

    public void setPermiteAprovacaoPropria(final String parm) {
        permiteAprovacaoPropria = parm;
    }

    public String getPermiteSolicitacao() {
        return permiteSolicitacao;
    }

    public void setPermiteSolicitacao(final String parm) {
        permiteSolicitacao = parm;
    }

    public Integer getAntecedenciaMinimaAprovacao() {
        return antecedenciaMinimaAprovacao;
    }

    public void setAntecedenciaMinimaAprovacao(final Integer parm) {
        antecedenciaMinimaAprovacao = parm;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(final Integer sequencia) {
        this.sequencia = sequencia;
    }

    public Integer getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(final Integer hierarquia) {
        this.hierarquia = hierarquia;
    }

    public NivelAutoridadeDTO getNivelAutoridadeDto() {
        return nivelAutoridadeDto;
    }

    public void setNivelAutoridadeDto(final NivelAutoridadeDTO nivelAutoridadeDto) {
        this.nivelAutoridadeDto = nivelAutoridadeDto;
    }

    public LimiteAprovacaoDTO getLimiteAprovacaoDto() {
        return limiteAprovacaoDto;
    }

    public void setLimiteAprovacaoDto(final LimiteAprovacaoDTO limiteAprovacaoDto) {
        this.limiteAprovacaoDto = limiteAprovacaoDto;
    }

}
