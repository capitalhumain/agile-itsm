package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class FormulaDTO extends BaseEntity {

    public static String FORMULA_INVENTORY_PROCESS_SAVE = "INVENTORY_PROCESS_SAVE";
    public static String FORMULA_INVENTORY_PROCESS_BEFORE_CAPTURE = "INVENTORY_PROCESS_BEFORE_CAPTURE";

    private Integer idFormula;
    private String identificador;
    private String nome;
    private String conteudo;
    private java.sql.Date datacriacao;

    public Integer getIdFormula() {
        return idFormula;
    }

    public void setIdFormula(final Integer parm) {
        idFormula = parm;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(final String parm) {
        identificador = parm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String parm) {
        nome = parm;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(final String parm) {
        conteudo = parm;
    }

    public java.sql.Date getDatacriacao() {
        return datacriacao;
    }

    public void setDatacriacao(final java.sql.Date parm) {
        datacriacao = parm;
    }

}
