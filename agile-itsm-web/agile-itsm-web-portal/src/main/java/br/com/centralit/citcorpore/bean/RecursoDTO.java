package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class RecursoDTO extends BaseEntity {

    public static String NAGIOS_NATIVE = "1";
    public static String NAGIOS_CENTREON = "2";

    private Integer idRecurso;
    private Integer idGrupoRecurso;
    private Integer idRecursoPai;
    private String nomeRecurso;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;
    private String tipoAtualizacao;
    private Integer idNagiosConexao;
    private String hostName;
    private String serviceName;
    private String horaInicioFunc;
    private String horaFimFunc;
    private Integer idCalendario;
    private String deleted;

    private String statusAberturaInc;
    private Integer idSolicitante;
    private String emailAberturaInc;
    private String descricaoAbertInc;
    private String impacto;
    private String urgencia;
    private Integer idGrupo;
    private Integer idOrigem;
    private Integer idContrato;
    private Integer idServicoContrato;
    private Integer idEventoMonitoramento;
    private Integer idServico;
    private Integer idItemConfiguracaoPai;
    private Integer idItemConfiguracao;
    private String statusAlerta;
    private String emailsAlerta;
    private String descricaoAlerta;

    private Collection colFaixasValores;

    public Integer getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(final Integer parm) {
        idRecurso = parm;
    }

    public Integer getIdGrupoRecurso() {
        return idGrupoRecurso;
    }

    public void setIdGrupoRecurso(final Integer parm) {
        idGrupoRecurso = parm;
    }

    public Integer getIdRecursoPai() {
        return idRecursoPai;
    }

    public void setIdRecursoPai(final Integer parm) {
        idRecursoPai = parm;
    }

    public String getNomeRecurso() {
        return nomeRecurso;
    }

    public void setNomeRecurso(final String parm) {
        nomeRecurso = parm;
    }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final java.sql.Date parm) {
        dataInicio = parm;
    }

    public java.sql.Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final java.sql.Date parm) {
        dataFim = parm;
    }

    public String getTipoAtualizacao() {
        return tipoAtualizacao;
    }

    public void setTipoAtualizacao(final String parm) {
        tipoAtualizacao = parm;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String parm) {
        deleted = parm;
    }

    public Collection getColFaixasValores() {
        return colFaixasValores;
    }

    public void setColFaixasValores(final Collection colFaixasValores) {
        this.colFaixasValores = colFaixasValores;
    }

    public Integer getIdNagiosConexao() {
        return idNagiosConexao;
    }

    public void setIdNagiosConexao(final Integer idNagiosConexao) {
        this.idNagiosConexao = idNagiosConexao;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(final String hostName) {
        this.hostName = hostName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(final String serviceName) {
        this.serviceName = serviceName;
    }

    public String getHoraInicioFunc() {
        return horaInicioFunc;
    }

    public void setHoraInicioFunc(final String horaInicioFunc) {
        this.horaInicioFunc = horaInicioFunc;
    }

    public String getHoraFimFunc() {
        return horaFimFunc;
    }

    public void setHoraFimFunc(final String horaFimFunc) {
        this.horaFimFunc = horaFimFunc;
    }

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(final Integer idCalendario) {
        this.idCalendario = idCalendario;
    }

    public String getStatusAberturaInc() {
        return statusAberturaInc;
    }

    public void setStatusAberturaInc(final String statusAberturaInc) {
        this.statusAberturaInc = statusAberturaInc;
    }

    public Integer getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(final Integer idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public String getEmailAberturaInc() {
        return emailAberturaInc;
    }

    public void setEmailAberturaInc(final String emailAberturaInc) {
        this.emailAberturaInc = emailAberturaInc;
    }

    public String getDescricaoAbertInc() {
        return descricaoAbertInc;
    }

    public void setDescricaoAbertInc(final String descricaoAbertInc) {
        this.descricaoAbertInc = descricaoAbertInc;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(final String impacto) {
        this.impacto = impacto;
    }

    public String getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(final String urgencia) {
        this.urgencia = urgencia;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(final Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdOrigem() {
        return idOrigem;
    }

    public void setIdOrigem(final Integer idOrigem) {
        this.idOrigem = idOrigem;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdServicoContrato() {
        return idServicoContrato;
    }

    public void setIdServicoContrato(final Integer idServicoContrato) {
        this.idServicoContrato = idServicoContrato;
    }

    public Integer getIdItemConfiguracaoPai() {
        return idItemConfiguracaoPai;
    }

    public void setIdItemConfiguracaoPai(final Integer idItemConfiguracaoPai) {
        this.idItemConfiguracaoPai = idItemConfiguracaoPai;
    }

    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    public void setIdItemConfiguracao(final Integer idItemConfiguracao) {
        this.idItemConfiguracao = idItemConfiguracao;
    }

    public String getStatusAlerta() {
        return statusAlerta;
    }

    public void setStatusAlerta(final String statusAlerta) {
        this.statusAlerta = statusAlerta;
    }

    public String getEmailsAlerta() {
        return emailsAlerta;
    }

    public void setEmailsAlerta(final String emailsAlerta) {
        this.emailsAlerta = emailsAlerta;
    }

    public String getDescricaoAlerta() {
        return descricaoAlerta;
    }

    public void setDescricaoAlerta(final String descricaoAlerta) {
        this.descricaoAlerta = descricaoAlerta;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(final Integer idServico) {
        this.idServico = idServico;
    }

    public Integer getIdEventoMonitoramento() {
        return idEventoMonitoramento;
    }

    public void setIdEventoMonitoramento(final Integer idEventoMonitoramento) {
        this.idEventoMonitoramento = idEventoMonitoramento;
    }

}
