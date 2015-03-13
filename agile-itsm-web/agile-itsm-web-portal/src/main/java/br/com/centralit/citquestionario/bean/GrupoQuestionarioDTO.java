package br.com.centralit.citquestionario.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class GrupoQuestionarioDTO extends BaseEntity {

    private static final long serialVersionUID = 8473113003183923972L;

    private Integer idGrupoQuestionario;
    private Integer idQuestionario;
    private String nomeGrupoQuestionario;
    private Integer ordem;

    private String infoGrupo;

    private String serializeQuestoesGrupo;
    private Collection colQuestoes;
    private Integer idControleTabela;

    public Integer getIdGrupoQuestionario() {
        return idGrupoQuestionario;
    }

    public void setIdGrupoQuestionario(final Integer idGrupoQuestionario) {
        this.idGrupoQuestionario = idGrupoQuestionario;
    }

    public Integer getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(final Integer idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    public String getNomeGrupoQuestionario() {
        return nomeGrupoQuestionario;
    }

    public void setNomeGrupoQuestionario(final String nomeGrupoQuestionario) {
        this.nomeGrupoQuestionario = nomeGrupoQuestionario;
    }

    public String getInfoGrupo() {
        return infoGrupo;
    }

    public void setInfoGrupo(final String infoGrupo) {
        this.infoGrupo = infoGrupo;
    }

    public String getSerializeQuestoesGrupo() {
        return serializeQuestoesGrupo;
    }

    public void setSerializeQuestoesGrupo(final String serializeQuestoesGrupo) {
        this.serializeQuestoesGrupo = serializeQuestoesGrupo;
    }

    public Collection getColQuestoes() {
        return colQuestoes;
    }

    public void setColQuestoes(final Collection colQuestoes) {
        this.colQuestoes = colQuestoes;
    }

    public Integer getIdControleTabela() {
        return idControleTabela;
    }

    public void setIdControleTabela(final Integer idControleTabela) {
        this.idControleTabela = idControleTabela;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(final Integer ordem) {
        this.ordem = ordem;
    }

}
