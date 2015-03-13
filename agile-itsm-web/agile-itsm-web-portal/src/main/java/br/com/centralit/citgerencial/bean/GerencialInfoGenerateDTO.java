package br.com.centralit.citgerencial.bean;

import java.util.HashMap;

import br.com.agileitsm.model.support.BaseEntity;

public class GerencialInfoGenerateDTO extends BaseEntity {

    public static final String TIPO_INFORMACAO_PESSOAS = "PESSOAS";
    public static final String TIPO_INFORMACAO_PROGRAMAS = "PROGRAMAS";

    private static final long serialVersionUID = -6004363026482410376L;
    private String saida;
    private String graphType;

    private String nomeQuestao;

    private HashMap hashParametros;

    private String tipoSaidaApresentada;

    private String caminhoArquivosGraficos;
    private String caminhoArquivosPdfs;

    private String tipoInformacao; // O TipoInformacao indica de onde obter os resultados (PESSOAS, PROGRAMAS).
    private String tipoQuestao;

    public String getSaida() {
        return saida;
    }

    public void setSaida(final String saida) {
        this.saida = saida;
    }

    public String getGraphType() {
        return graphType;
    }

    public void setGraphType(final String graphType) {
        this.graphType = graphType;
    }

    public String getNomeQuestao() {
        return nomeQuestao;
    }

    public void setNomeQuestao(final String nomeQuestao) {
        this.nomeQuestao = nomeQuestao;
    }

    public HashMap getHashParametros() {
        return hashParametros;
    }

    public void setHashParametros(final HashMap hashParametros) {
        this.hashParametros = hashParametros;
    }

    public String getTipoSaidaApresentada() {
        return tipoSaidaApresentada;
    }

    public void setTipoSaidaApresentada(final String tipoSaidaApresentada) {
        this.tipoSaidaApresentada = tipoSaidaApresentada;
    }

    public String getCaminhoArquivosGraficos() {
        return caminhoArquivosGraficos;
    }

    public void setCaminhoArquivosGraficos(final String caminhoArquivosGraficos) {
        this.caminhoArquivosGraficos = caminhoArquivosGraficos;
    }

    public String getCaminhoArquivosPdfs() {
        return caminhoArquivosPdfs;
    }

    public void setCaminhoArquivosPdfs(final String caminhoArquivosPdfs) {
        this.caminhoArquivosPdfs = caminhoArquivosPdfs;
    }

    public String getTipoInformacao() {
        return tipoInformacao;
    }

    public void setTipoInformacao(final String tipoInformacao) {
        this.tipoInformacao = tipoInformacao;
    }

    public String getTipoQuestao() {
        return tipoQuestao;
    }

    public void setTipoQuestao(final String tipoQuestao) {
        this.tipoQuestao = tipoQuestao;
    }

}
