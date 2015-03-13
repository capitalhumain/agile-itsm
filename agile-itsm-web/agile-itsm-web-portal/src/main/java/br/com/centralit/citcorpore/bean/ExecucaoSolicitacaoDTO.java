package br.com.centralit.citcorpore.bean;

import br.com.centralit.bpm.dto.ExecucaoFluxoDTO;

public class ExecucaoSolicitacaoDTO extends ExecucaoFluxoDTO {

    private Integer idSolicitacaoServico;
    private Integer idFase;
    private Integer prazoHH;
    private Integer prazoMM;
    private Integer seqReabertura;

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer parm) {
        idSolicitacaoServico = parm;
    }

    public Integer getIdFase() {
        return idFase;
    }

    public void setIdFase(final Integer parm) {
        idFase = parm;
    }

    public Integer getPrazoHH() {
        return prazoHH;
    }

    public void setPrazoHH(final Integer parm) {
        prazoHH = parm;
    }

    public Integer getPrazoMM() {
        return prazoMM;
    }

    public void setPrazoMM(final Integer parm) {
        prazoMM = parm;
    }

    public Integer getSeqReabertura() {
        return seqReabertura;
    }

    public void setSeqReabertura(final Integer seqReabertura) {
        this.seqReabertura = seqReabertura;
    }

}
