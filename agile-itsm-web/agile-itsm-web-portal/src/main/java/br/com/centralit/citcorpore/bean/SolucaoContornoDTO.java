package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import br.com.agileitsm.model.support.BaseEntity;

public class SolucaoContornoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String titulo;
    private String descricao;
    private Timestamp dataHoraCriacao;
    private String dataHoraCriacaoStr;
    private Integer idSolucaoContorno;
    private Integer idProblema;

    public Integer getIdSolucaoContorno() {
        return idSolucaoContorno;
    }

    public void setIdSolucaoContorno(final Integer idSolucaoContorno) {
        this.idSolucaoContorno = idSolucaoContorno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(final Timestamp dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
        if (dataHoraCriacao != null) {
            final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dataHoraCriacaoStr = df.format(dataHoraCriacao);
        }
    }

    public String getDataHoraCriacaoStr() {
        return dataHoraCriacaoStr;
    }

    public void setDataHoraCriacaoStr(final String dataHoraCriacaoStr) {
        this.dataHoraCriacaoStr = dataHoraCriacaoStr;
    }

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(final Integer idProblema) {
        this.idProblema = idProblema;
    }

}
