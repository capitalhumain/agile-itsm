package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioProblemaIncidentesDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idProblema;
    private String tituloProblema;
    private String descricao;
    private Integer idContrato;
    private String contrato;
    private Collection<SolicitacaoServicoDTO> colSolicitacaoServico;

    private Date dataInicio;
    private Date dataFim;
    private String proprietario;
    private String formatoArquivoRelatorio;

    private Integer totalSolicitacaoServicoPorProblema;

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(final Integer idProblema) {
        this.idProblema = idProblema;
    }

    public String getTituloProblema() {
        return tituloProblema;
    }

    public void setTituloProblema(final String tituloProblema) {
        this.tituloProblema = tituloProblema;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(final String contrato) {
        this.contrato = contrato;
    }

    public Collection<SolicitacaoServicoDTO> getColSolicitacaoServico() {
        return colSolicitacaoServico;
    }

    public void setColSolicitacaoServico(final Collection<SolicitacaoServicoDTO> colSolicitacaoServico) {
        this.colSolicitacaoServico = colSolicitacaoServico;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(final String proprietario) {
        this.proprietario = proprietario;
    }

    public String getFormatoArquivoRelatorio() {
        return formatoArquivoRelatorio;
    }

    public void setFormatoArquivoRelatorio(final String formatoArquivoRelatorio) {
        this.formatoArquivoRelatorio = formatoArquivoRelatorio;
    }

    public Integer getTotalSolicitacaoServicoPorProblema() {
        return totalSolicitacaoServicoPorProblema;
    }

    public void setTotalSolicitacaoServicoPorProblema(final Integer totalSolicitacaoServicoPorProblema) {
        this.totalSolicitacaoServicoPorProblema = totalSolicitacaoServicoPorProblema;
    }

}
