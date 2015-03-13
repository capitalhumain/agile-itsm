package br.com.centralit.citgerencial.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class GerencialItemInformationDTO extends BaseEntity {

    private static final long serialVersionUID = 1996048245518604610L;

    private String type;
    private String description;
    private String hDetailSpacing;
    private String title;
    private boolean report;
    private boolean graph;
    private String reportPageLayout;
    private String defaultVisualization; /* T - Tabela; G:Type - Gráfico + Tipo do Grafico */

    private GerencialSQLDTO gerencialSQL = null;
    private GerencialServiceInformationDTO gerencialService = null;
    private GerencialSummaryInformationDTO gerencialSummary = null;
    private Collection listFields;
    private Collection listGroups;
    private Collection listGraphs;

    private String classExecute;
    private boolean porcentagem;

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public boolean isGraph() {
        return graph;
    }

    public void setGraph(final boolean graph) {
        this.graph = graph;
    }

    public String getHDetailSpacing() {
        return hDetailSpacing;
    }

    public void setHDetailSpacing(final String detailSpacing) {
        hDetailSpacing = detailSpacing;
    }

    public boolean isReport() {
        return report;
    }

    public void setReport(final boolean report) {
        this.report = report;
    }

    public String getReportPageLayout() {
        return reportPageLayout;
    }

    public void setReportPageLayout(final String reportPageLayout) {
        this.reportPageLayout = reportPageLayout;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public GerencialServiceInformationDTO getGerencialService() {
        return gerencialService;
    }

    public void setGerencialService(final GerencialServiceInformationDTO gerencialService) {
        this.gerencialService = gerencialService;
    }

    public GerencialSQLDTO getGerencialSQL() {
        return gerencialSQL;
    }

    public void setGerencialSQL(final GerencialSQLDTO gerencialSQL) {
        this.gerencialSQL = gerencialSQL;
    }

    public Collection getListFields() {
        return listFields;
    }

    public void setListFields(final Collection listFields) {
        this.listFields = listFields;
    }

    public Collection getListGraphs() {
        return listGraphs;
    }

    public void setListGraphs(final Collection listGraphs) {
        this.listGraphs = listGraphs;
    }

    public Collection getListGroups() {
        return listGroups;
    }

    public void setListGroups(final Collection listGroups) {
        this.listGroups = listGroups;
    }

    public String getDefaultVisualization() {
        return defaultVisualization;
    }

    public void setDefaultVisualization(final String defaultVisualization) {
        this.defaultVisualization = defaultVisualization;
    }

    public GerencialSummaryInformationDTO getGerencialSummary() {
        return gerencialSummary;
    }

    public void setGerencialSummary(final GerencialSummaryInformationDTO gerencialSummary) {
        this.gerencialSummary = gerencialSummary;
    }

    public String getClassExecute() {
        return classExecute;
    }

    public void setClassExecute(final String classExecute) {
        this.classExecute = classExecute;
    }

    public boolean isPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(final boolean porcentagem) {
        this.porcentagem = porcentagem;
    }

}
