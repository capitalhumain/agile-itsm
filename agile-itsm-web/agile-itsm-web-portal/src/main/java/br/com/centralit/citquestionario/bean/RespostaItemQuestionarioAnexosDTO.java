package br.com.centralit.citquestionario.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RespostaItemQuestionarioAnexosDTO extends BaseEntity {

    private static final long serialVersionUID = 2088779287936858372L;

    private Integer idRespostaItmQuestionarioAnexo;
    private Integer idRespostaItemQuestionario;
    private String caminhoAnexo;
    private String observacao;
    private Integer idQuestaoQuestionario;
    private String nomeArquivo;
    private Integer idControleGED;

    public Integer getIdRespostaItmQuestionarioAnexo() {
        return idRespostaItmQuestionarioAnexo;
    }

    public void setIdRespostaItmQuestionarioAnexo(final Integer idRespostaItmQuestionarioAnexo) {
        this.idRespostaItmQuestionarioAnexo = idRespostaItmQuestionarioAnexo;
    }

    public Integer getIdRespostaItemQuestionario() {
        return idRespostaItemQuestionario;
    }

    public void setIdRespostaItemQuestionario(final Integer idRespostaItemQuestionario) {
        this.idRespostaItemQuestionario = idRespostaItemQuestionario;
    }

    public String getCaminhoAnexo() {
        return caminhoAnexo;
    }

    public void setCaminhoAnexo(final String caminhoAnexo) {
        this.caminhoAnexo = caminhoAnexo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(final String observacao) {
        this.observacao = observacao;
    }

    public Integer getIdQuestaoQuestionario() {
        return idQuestaoQuestionario;
    }

    public void setIdQuestaoQuestionario(final Integer idQuestaoQuestionario) {
        this.idQuestaoQuestionario = idQuestaoQuestionario;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(final String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public Integer getIdControleGED() {
        return idControleGED;
    }

    public void setIdControleGED(final Integer idControleGED) {
        this.idControleGED = idControleGED;
    }

}
