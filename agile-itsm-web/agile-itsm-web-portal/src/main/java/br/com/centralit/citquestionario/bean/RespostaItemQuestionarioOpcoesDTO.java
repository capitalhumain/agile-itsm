package br.com.centralit.citquestionario.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RespostaItemQuestionarioOpcoesDTO extends BaseEntity {

    private static final long serialVersionUID = 4657090261120793624L;
    private Integer idRespostaItemQuestionario;
    private Integer idOpcaoRespostaQuestionario;
    private String titulo;

    private Integer peso;
    private String valor;
    private String geraAlerta;
    private String exibeComplemento;
    private Integer idQuestaoComplemento;

    public Integer getIdOpcaoRespostaQuestionario() {
        return idOpcaoRespostaQuestionario;
    }

    public void setIdOpcaoRespostaQuestionario(final Integer idOpcaoRespostaQuestionario) {
        this.idOpcaoRespostaQuestionario = idOpcaoRespostaQuestionario;
    }

    public Integer getIdRespostaItemQuestionario() {
        return idRespostaItemQuestionario;
    }

    public void setIdRespostaItemQuestionario(final Integer idRespostaItemQuestionario) {
        this.idRespostaItemQuestionario = idRespostaItemQuestionario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(final Integer peso) {
        this.peso = peso;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(final String valor) {
        this.valor = valor;
    }

    public String getGeraAlerta() {
        return geraAlerta;
    }

    public void setGeraAlerta(final String geraAlerta) {
        this.geraAlerta = geraAlerta;
    }

    public String getExibeComplemento() {
        return exibeComplemento;
    }

    public void setExibeComplemento(final String exibeComplemento) {
        this.exibeComplemento = exibeComplemento;
    }

    public Integer getIdQuestaoComplemento() {
        return idQuestaoComplemento;
    }

    public void setIdQuestaoComplemento(final Integer idQuestaoComplemento) {
        this.idQuestaoComplemento = idQuestaoComplemento;
    }

}
