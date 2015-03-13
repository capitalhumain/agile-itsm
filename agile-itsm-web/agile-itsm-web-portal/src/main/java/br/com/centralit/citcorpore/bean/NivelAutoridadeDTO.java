package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.Enumerados.MotivoRejeicaoAlcada;

public class NivelAutoridadeDTO extends BaseEntity {

    private Integer idNivelAutoridade;
    private String nomeNivelAutoridade;
    private Integer hierarquia;
    private String situacao;

    private Collection<GrupoNivelAutoridadeDTO> colGrupos;

    private boolean alcadaRejeitada;
    private MotivoRejeicaoAlcada motivoRejeicao;

    public Integer getIdNivelAutoridade() {
        return idNivelAutoridade;
    }

    public void setIdNivelAutoridade(final Integer parm) {
        idNivelAutoridade = parm;
    }

    public String getNomeNivelAutoridade() {
        return nomeNivelAutoridade;
    }

    public void setNomeNivelAutoridade(final String parm) {
        nomeNivelAutoridade = parm;
    }

    public Integer getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(final Integer parm) {
        hierarquia = parm;
    }

    public Collection<GrupoNivelAutoridadeDTO> getColGrupos() {
        return colGrupos;
    }

    public void setColGrupos(final Collection<GrupoNivelAutoridadeDTO> colGrupos) {
        this.colGrupos = colGrupos;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public boolean isAlcadaRejeitada() {
        return alcadaRejeitada;
    }

    public void setAlcadaRejeitada(final boolean alcadaRejeitada) {
        this.alcadaRejeitada = alcadaRejeitada;
    }

    public MotivoRejeicaoAlcada getMotivoRejeicao() {
        return motivoRejeicao;
    }

    public void setMotivoRejeicao(final MotivoRejeicaoAlcada motivoRejeicao) {
        this.motivoRejeicao = motivoRejeicao;
    }

}
