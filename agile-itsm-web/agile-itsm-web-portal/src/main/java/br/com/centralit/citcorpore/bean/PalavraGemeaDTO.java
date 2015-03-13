/**
 * CentalIT - CTISMart
 */
package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Vadoilo Damasceno
 *
 */
public class PalavraGemeaDTO extends BaseEntity {

    private static final long serialVersionUID = 7907826613317683397L;

    private Integer idPalavraGemea;

    private String palavra;

    private String palavraCorrespondente;

    public Integer getIdPalavraGemea() {
        return idPalavraGemea;
    }

    public void setIdPalavraGemea(final Integer idPalavraGemea) {
        this.idPalavraGemea = idPalavraGemea;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(final String palavra) {
        this.palavra = palavra;
    }

    public String getPalavraCorrespondente() {
        return palavraCorrespondente;
    }

    public void setPalavraCorrespondente(final String palavraCorrespondente) {
        this.palavraCorrespondente = palavraCorrespondente;
    }

}
