package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author ronnie.lopes
 *
 */
public class SoftwaresListaNegraDTO extends BaseEntity {

    private Integer idSoftwaresListaNegra;
    private String nomeSoftwaresListaNegra;

    public Integer getIdSoftwaresListaNegra() {
        return idSoftwaresListaNegra;
    }

    public void setIdSoftwaresListaNegra(final Integer idSoftwaresListaNegra) {
        this.idSoftwaresListaNegra = idSoftwaresListaNegra;
    }

    public String getNomeSoftwaresListaNegra() {
        return nomeSoftwaresListaNegra;
    }

    public void setNomeSoftwaresListaNegra(final String nomeSoftwaresListaNegra) {
        this.nomeSoftwaresListaNegra = nomeSoftwaresListaNegra;
    }

}
