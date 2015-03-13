package br.com.centralit.citcorpore.bean;

import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;

public class AcordoServicoContratoDTO extends BaseEntity {

    private static final long serialVersionUID = 7878341453446988862L;

    private Integer idAcordoServicoContrato;
    private Integer idAcordoNivelServico;
    private Integer idServicoContrato;
    private java.sql.Date dataCriacao;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;
    private java.sql.Date dataUltAtualiz;
    private Integer idRecurso;
    private String deleted;
    private String habilitado;

    private Integer idContrato;
    private List<ServicoContratoDTO> listaServicoContrato;

    public Integer getIdAcordoServicoContrato() {
        return idAcordoServicoContrato;
    }

    public void setIdAcordoServicoContrato(final Integer parm) {
        idAcordoServicoContrato = parm;
    }

    public Integer getIdAcordoNivelServico() {
        return idAcordoNivelServico;
    }

    public void setIdAcordoNivelServico(final Integer parm) {
        idAcordoNivelServico = parm;
    }

    public Integer getIdServicoContrato() {
        return idServicoContrato;
    }

    public void setIdServicoContrato(final Integer parm) {
        idServicoContrato = parm;
    }

    public java.sql.Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(final java.sql.Date parm) {
        dataCriacao = parm;
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

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String parm) {
        deleted = parm;
    }

    public java.sql.Date getDataUltAtualiz() {
        return dataUltAtualiz;
    }

    public void setDataUltAtualiz(final java.sql.Date dataUltAtualiz) {
        this.dataUltAtualiz = dataUltAtualiz;
    }

    public Integer getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(final Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(final String habilitado) {
        this.habilitado = habilitado;
    }

    public List<ServicoContratoDTO> getListaServicoContrato() {
        return listaServicoContrato;
    }

    public void setListaServicoContrato(final List<ServicoContratoDTO> listaServicoContrato) {
        this.listaServicoContrato = listaServicoContrato;
    }

}
