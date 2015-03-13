package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author thays.araujo
 *
 */
public class ImportanciaNegocioDTO extends BaseEntity {

    private static final long serialVersionUID = -7848776827100833523L;

    private Integer idImportanciaNegocio;
    private Integer idEmpresa;
    private String nomeImportanciaNegocio;
    private String situacao;

    public Integer getIdImportanciaNegocio() {
        return idImportanciaNegocio;
    }

    public void setIdImportanciaNegocio(final Integer idImportanciaNegocio) {
        this.idImportanciaNegocio = idImportanciaNegocio;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(final Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNomeImportanciaNegocio() {
        return nomeImportanciaNegocio;
    }

    public void setNomeImportanciaNegocio(final String nomeImportanciaNegocio) {
        this.nomeImportanciaNegocio = nomeImportanciaNegocio;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

}
