package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ExportacaoContratosDTO extends BaseEntity {

    private static final long serialVersionUID = -7252057961936714136L;

    private Integer idContrato;
    private String export;
    private Integer[] idGrupos;
    private String exportarUnidades;
    private String exportarAcordoNivelServico;
    private String exportarCatalogoServico;

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getExport() {
        return export;
    }

    public void setExport(final String export) {
        this.export = export;
    }

    public Integer[] getIdGrupos() {
        return idGrupos;
    }

    public void setIdGrupos(final Integer[] idGrupos) {
        this.idGrupos = idGrupos;
    }

    public String getExportarUnidades() {
        return exportarUnidades;
    }

    public void setExportarUnidades(final String exportarUnidades) {
        this.exportarUnidades = exportarUnidades;
    }

    public String getExportarAcordoNivelServico() {
        return exportarAcordoNivelServico;
    }

    public void setExportarAcordoNivelServico(final String exportarAcordoNivelServico) {
        this.exportarAcordoNivelServico = exportarAcordoNivelServico;
    }

    public String getExportarCatalogoServico() {
        return exportarCatalogoServico;
    }

    public void setExportarCatalogoServico(final String exportarCatalogoServico) {
        this.exportarCatalogoServico = exportarCatalogoServico;
    }

}
