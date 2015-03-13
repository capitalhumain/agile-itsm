package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioQuantitativoRetornoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idOcorrencia;
    private Integer idInstancia;
    private Integer idSolicitacaoServico;
    private Date dataRegistro;
    private String horaRegistro;
    private String nome;
    private String ocorrencia;
    private String dataCompleta;
    private Integer idUsuario;
    private Integer idItemTrabalho;
    private Integer idElemento;
    private Integer idFluxo;

    public Integer getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(final Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public Integer getIdInstancia() {
        return idInstancia;
    }

    public void setIdInstancia(final Integer idInstancia) {
        this.idInstancia = idInstancia;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(final Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getHoraRegistro() {
        return horaRegistro;
    }

    public void setHoraRegistro(final String horaRegistro) {
        this.horaRegistro = horaRegistro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(final String ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public String getDataCompleta() {
        return dataCompleta;
    }

    public void setDataCompleta(final String dataCompleta) {
        this.dataCompleta = dataCompleta;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(final Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdItemTrabalho() {
        return idItemTrabalho;
    }

    public void setIdItemTrabalho(final Integer idItemTrabalho) {
        this.idItemTrabalho = idItemTrabalho;
    }

    public Integer getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(final Integer idElemento) {
        this.idElemento = idElemento;
    }

    public Integer getIdFluxo() {
        return idFluxo;
    }

    public void setIdFluxo(final Integer idFluxo) {
        this.idFluxo = idFluxo;
    }

}
