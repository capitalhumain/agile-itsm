package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

/**
 *
 * @author pedro
 *
 */
public class InfoCatalogoServicoDTO extends BaseEntity {

    private Integer idInfoCatalogoServico;
    private Integer idCatalogoServico;
    private String descInfoCatalogoServico;
    private String nomeInfoCatalogoServico;
    private Integer idServicoCatalogo;
    private Integer idContrato;
    private String nomeServicoContrato;
    private String observacaoPortal;
    private String nomeServico;

    private Collection colArquivosUpload;

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public Integer getIdInfoCatalogoServico() {
        return idInfoCatalogoServico;
    }

    public void setIdInfoCatalogoServico(final Integer idInfoCatalogoServico) {
        this.idInfoCatalogoServico = idInfoCatalogoServico;
    }

    public Integer getIdCatalogoServico() {
        return idCatalogoServico;
    }

    public void setIdCatalogoServico(final Integer idCatalogoServico) {
        this.idCatalogoServico = idCatalogoServico;
    }

    public String getDescInfoCatalogoServico() {
        return descInfoCatalogoServico;
    }

    public void setDescInfoCatalogoServico(final String descInfoCatalogoServico) {
        this.descInfoCatalogoServico = descInfoCatalogoServico;
    }

    public String getNomeInfoCatalogoServico() {
        return nomeInfoCatalogoServico;
    }

    public void setNomeInfoCatalogoServico(final String nomeInfoCatalogoServico) {
        this.nomeInfoCatalogoServico = nomeInfoCatalogoServico;
    }

    public String getNomeServicoContrato() {
        return nomeServicoContrato;
    }

    public void setNomeServicoContrato(final String nomeServicoContrato) {
        this.nomeServicoContrato = nomeServicoContrato;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdServicoCatalogo() {
        return idServicoCatalogo;
    }

    public void setIdServicoCatalogo(final Integer idServicoCatalogo) {
        this.idServicoCatalogo = idServicoCatalogo;
    }

    public String getObservacaoPortal() {
        return observacaoPortal;
    }

    public void setObservacaoPortal(final String observacaoPortal) {
        this.observacaoPortal = observacaoPortal;
    }

    public Collection getColArquivosUpload() {
        return colArquivosUpload;
    }

    public void setColArquivosUpload(final Collection colArquivosUpload) {
        this.colArquivosUpload = colArquivosUpload;
    }

}
