package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class LinhaBaseProjetoDTO extends BaseEntity {

    public static final String ATIVO = "A";
    public static final String EMEXECUCAO = "E";
    public static final String INATIVO = "I";

    private Integer idLinhaBaseProjeto;
    private Integer idProjeto;
    private java.sql.Date dataLinhaBase;
    private String horaLinhaBase;
    private String situacao;
    private java.sql.Date dataUltAlteracao;
    private String horaUltAlteracao;
    private String usuarioUltAlteracao;

    private String justificativaMudanca;
    private java.sql.Date dataSolMudanca;
    private String horaSolMudanca;
    private String usuarioSolMudanca;

    private Integer idLinhaBaseProjetoUpdate;

    private Collection colTarefas;

    public Integer getIdLinhaBaseProjeto() {
        return idLinhaBaseProjeto;
    }

    public void setIdLinhaBaseProjeto(final Integer parm) {
        idLinhaBaseProjeto = parm;
    }

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(final Integer parm) {
        idProjeto = parm;
    }

    public java.sql.Date getDataLinhaBase() {
        return dataLinhaBase;
    }

    public void setDataLinhaBase(final java.sql.Date parm) {
        dataLinhaBase = parm;
    }

    public String getHoraLinhaBase() {
        return horaLinhaBase;
    }

    public void setHoraLinhaBase(final String parm) {
        horaLinhaBase = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public java.sql.Date getDataUltAlteracao() {
        return dataUltAlteracao;
    }

    public void setDataUltAlteracao(final java.sql.Date parm) {
        dataUltAlteracao = parm;
    }

    public String getHoraUltAlteracao() {
        return horaUltAlteracao;
    }

    public void setHoraUltAlteracao(final String parm) {
        horaUltAlteracao = parm;
    }

    public String getUsuarioUltAlteracao() {
        return usuarioUltAlteracao;
    }

    public void setUsuarioUltAlteracao(final String parm) {
        usuarioUltAlteracao = parm;
    }

    public Collection getColTarefas() {
        return colTarefas;
    }

    public void setColTarefas(final Collection colTarefas) {
        this.colTarefas = colTarefas;
    }

    public Integer getIdLinhaBaseProjetoUpdate() {
        return idLinhaBaseProjetoUpdate;
    }

    public void setIdLinhaBaseProjetoUpdate(final Integer idLinhaBaseProjetoUpdate) {
        this.idLinhaBaseProjetoUpdate = idLinhaBaseProjetoUpdate;
    }

    public java.sql.Date getDataSolMudanca() {
        return dataSolMudanca;
    }

    public void setDataSolMudanca(final java.sql.Date dataSolMudanca) {
        this.dataSolMudanca = dataSolMudanca;
    }

    public String getHoraSolMudanca() {
        return horaSolMudanca;
    }

    public void setHoraSolMudanca(final String horaSolMudanca) {
        this.horaSolMudanca = horaSolMudanca;
    }

    public String getUsuarioSolMudanca() {
        return usuarioSolMudanca;
    }

    public void setUsuarioSolMudanca(final String usuarioSolMudanca) {
        this.usuarioSolMudanca = usuarioSolMudanca;
    }

    public String getJustificativaMudanca() {
        return justificativaMudanca;
    }

    public void setJustificativaMudanca(final String justificativaMudanca) {
        this.justificativaMudanca = justificativaMudanca;
    }

}
