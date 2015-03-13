package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class TempoAcordoNivelServicoDTO extends BaseEntity {

    private Integer idAcordoNivelServico;
    private Integer idPrioridade;
    private Integer idFase;
    private Integer tempoHH;
    private Integer tempoMM;

    public Integer getIdAcordoNivelServico() {
        return idAcordoNivelServico;
    }

    public void setIdAcordoNivelServico(final Integer parm) {
        idAcordoNivelServico = parm;
    }

    public Integer getIdPrioridade() {
        return idPrioridade;
    }

    public void setIdPrioridade(final Integer parm) {
        idPrioridade = parm;
    }

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(final Integer parm) {
        idFase = parm;
    }

    public Integer getTempoHH() {
        return tempoHH;
    }

    public void setTempoHH(final Integer parm) {
        tempoHH = parm;
    }

    public Integer getTempoMM() {
        return tempoMM;
    }

    public void setTempoMM(final Integer parm) {
        tempoMM = parm;
    }

}
