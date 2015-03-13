package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class SolicitacaoServicoEvtMonDTO extends BaseEntity {

    private Integer idSolicitacaoServico;
    private Integer idEventoMonitoramento;
    private Integer idRecurso;
    private String nomeHost;
    private String nomeService;
    private String infoAdd;

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer parm) {
        idSolicitacaoServico = parm;
    }

    public Integer getIdEventoMonitoramento() {
        return idEventoMonitoramento;
    }

    public void setIdEventoMonitoramento(final Integer parm) {
        idEventoMonitoramento = parm;
    }

    public Integer getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(final Integer parm) {
        idRecurso = parm;
    }

    public String getNomeHost() {
        return nomeHost;
    }

    public void setNomeHost(final String parm) {
        nomeHost = parm;
    }

    public String getNomeService() {
        return nomeService;
    }

    public void setNomeService(final String parm) {
        nomeService = parm;
    }

    public String getInfoAdd() {
        return infoAdd;
    }

    public void setInfoAdd(final String parm) {
        infoAdd = parm;
    }

}
