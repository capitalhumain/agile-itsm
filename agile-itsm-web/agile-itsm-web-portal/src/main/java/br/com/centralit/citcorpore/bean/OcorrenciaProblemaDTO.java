package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;
/**
 * @author geber.costa
 */
import br.com.centralit.citcorpore.util.Enumerados;

public class OcorrenciaProblemaDTO extends BaseEntity {

    private static final long serialVersionUID = -3055161845325828805L;

    private Integer idOcorrencia;
    private Integer idProblema;
    private Date dataInicio;
    private Date dataFim;
    private String categoria;
    private String origem;
    private String descricao;
    private String ocorrencia;
    private String informacoesContato;
    private Integer tempoGasto;
    private Date dataregistro;
    private String horaregistro;
    private String registradopor;
    private Integer idItemTrabalho;
    private String dadosProblema;

    private Integer idJustificativa;
    private String complementoJustificativa;

    private Integer idCategoriaOcorrencia;
    private Integer idOrigemOcorrencia;

    public Integer getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(final Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCategoriaDescricao() {
        if (categoria == null) {
            return "";
        }
        for (final Enumerados.CategoriaOcorrencia c : Enumerados.CategoriaOcorrencia.values()) {
            if (categoria.equalsIgnoreCase(c.getSigla().toString())) {
                return c.getDescricao();
            }
        }
        return "";
    }

    public void setCategoria(final String categoria) {
        this.categoria = categoria;
    }

    public String getOrigem() {
        return origem;
    }

    public String getOrigemDescricao() {
        if (origem == null) {
            return "";
        }
        for (final Enumerados.OrigemOcorrencia o : Enumerados.OrigemOcorrencia.values()) {
            if (origem.equalsIgnoreCase(o.getSigla().toString())) {
                return o.getDescricao();
            }
        }
        return "";
    }

    public void setOrigem(final String origem) {
        this.origem = origem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(final String ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public String getInformacoesContato() {
        return informacoesContato;
    }

    public void setInformacoesContato(final String informacoesContato) {
        this.informacoesContato = informacoesContato;
    }

    public Integer getTempoGasto() {
        return tempoGasto;
    }

    public void setTempoGasto(final Integer tempoGasto) {
        this.tempoGasto = tempoGasto;
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

    public Date getDataregistro() {
        return dataregistro;
    }

    public void setDataregistro(final Date dataregistro) {
        this.dataregistro = dataregistro;
    }

    public String getHoraregistro() {
        return horaregistro;
    }

    public void setHoraregistro(final String horaregistro) {
        this.horaregistro = horaregistro;
    }

    public String getRegistradopor() {
        return registradopor;
    }

    public void setRegistradopor(final String registradopor) {
        this.registradopor = registradopor;
    }

    public Integer getIdItemTrabalho() {
        return idItemTrabalho;
    }

    public void setIdItemTrabalho(final Integer idItemTrabalho) {
        this.idItemTrabalho = idItemTrabalho;
    }

    public Integer getIdJustificativa() {
        return idJustificativa;
    }

    public void setIdJustificativa(final Integer idJustificativa) {
        this.idJustificativa = idJustificativa;
    }

    public String getComplementoJustificativa() {
        return complementoJustificativa;
    }

    public void setComplementoJustificativa(final String complementoJustificativa) {
        this.complementoJustificativa = complementoJustificativa;
    }

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(final Integer idProblema) {
        this.idProblema = idProblema;
    }

    public String getDadosProblema() {
        return dadosProblema;
    }

    public void setDadosProblema(final String dadosProblema) {
        this.dadosProblema = dadosProblema;
    }

    public Integer getIdCategoriaOcorrencia() {
        return idCategoriaOcorrencia;
    }

    public void setIdCategoriaOcorrencia(final Integer idCategoriaOcorrencia) {
        this.idCategoriaOcorrencia = idCategoriaOcorrencia;
    }

    public Integer getIdOrigemOcorrencia() {
        return idOrigemOcorrencia;
    }

    public void setIdOrigemOcorrencia(final Integer idOrigemOcorrencia) {
        this.idOrigemOcorrencia = idOrigemOcorrencia;
    }

}
