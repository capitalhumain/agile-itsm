package br.com.centralit.citgerencial.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class GerencialPainelDTO extends BaseEntity {

    private static final long serialVersionUID = 8344378952431008361L;

    private String description;
    private String fileName;
    private String fileNameGrupo;
    private String fileNameItem;
    private String fileNameExcel;
    private String fileNameExcelShort;

    private Integer idQuestaoQuestionario;
    private String nomeQuestao;
    private String gerarExcel;

    private String tipoGrafico;

    private Collection listItens;
    private Collection listParameters;

    private String classNameProcessParameters;

    private String parametersPreenchidos;
    private String tipoSaida;
    private String tipoSaidaApresentada;

    private String tipoInformacao;

    private Integer idPainel;
    private Integer[] perfilSelecionado;

    private String campo;

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Collection getListItens() {
        return listItens;
    }

    public void setListItens(final Collection listItens) {
        this.listItens = listItens;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public Integer getIdQuestaoQuestionario() {
        return idQuestaoQuestionario;
    }

    public void setIdQuestaoQuestionario(final Integer idQuestaoQuestionario) {
        this.idQuestaoQuestionario = idQuestaoQuestionario;
    }

    public String getNomeQuestao() {
        return nomeQuestao;
    }

    public void setNomeQuestao(final String nomeQuestao) {
        this.nomeQuestao = nomeQuestao;
    }

    public String getTipoGrafico() {
        return tipoGrafico;
    }

    public void setTipoGrafico(final String tipoGrafico) {
        this.tipoGrafico = tipoGrafico;
    }

    public String getFileNameGrupo() {
        return fileNameGrupo;
    }

    public void setFileNameGrupo(final String fileNameGrupo) {
        this.fileNameGrupo = fileNameGrupo;
    }

    public Collection getListParameters() {
        return listParameters;
    }

    public void setListParameters(final Collection listParameters) {
        this.listParameters = listParameters;
    }

    public boolean isParametersPreenchidos() {
        if (parametersPreenchidos == null || parametersPreenchidos.equalsIgnoreCase("")) {
            return false;
        }
        if (parametersPreenchidos.equalsIgnoreCase("true")) {
            return true;
        } return false;
    }

    public void setParametersPreenchidos(final String parametersPreenchidos) {
        this.parametersPreenchidos = parametersPreenchidos;
    }

    public String getFileNameItem() {
        return fileNameItem;
    }

    public void setFileNameItem(final String fileNameItem) {
        this.fileNameItem = fileNameItem;
    }

    public String getTipoSaida() {
        return tipoSaida;
    }

    public void setTipoSaida(final String tipoSaida) {
        this.tipoSaida = tipoSaida;
    }

    public String getTipoSaidaApresentada() {
        return tipoSaidaApresentada;
    }

    public void setTipoSaidaApresentada(final String tipoSaidaApresentada) {
        this.tipoSaidaApresentada = tipoSaidaApresentada;
    }

    public String getTipoInformacao() {
        return tipoInformacao;
    }

    public void setTipoInformacao(final String tipoInformacao) {
        this.tipoInformacao = tipoInformacao;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(final String campo) {
        this.campo = campo;
    }

    public String getClassNameProcessParameters() {
        return classNameProcessParameters;
    }

    public void setClassNameProcessParameters(final String classNameProcessParameters) {
        this.classNameProcessParameters = classNameProcessParameters;
    }

    public String getGerarExcel() {
        return gerarExcel;
    }

    public void setGerarExcel(final String gerarExcel) {
        this.gerarExcel = gerarExcel;
    }

    public Integer getIdPainel() {
        return idPainel;
    }

    public void setIdPainel(final Integer idPainel) {
        this.idPainel = idPainel;
    }

    public Integer[] getPerfilSelecionado() {
        return perfilSelecionado;
    }

    public void setPerfilSelecionado(final Integer[] perfilSelecionado) {
        this.perfilSelecionado = perfilSelecionado;
    }

    public String getFileNameExcel() {
        return fileNameExcel;
    }

    public void setFileNameExcel(final String fileNameExcel) {
        this.fileNameExcel = fileNameExcel;
    }

    public String getFileNameExcelShort() {
        return fileNameExcelShort;
    }

    public void setFileNameExcelShort(final String fileNameExcelShort) {
        this.fileNameExcelShort = fileNameExcelShort;
    }

}
