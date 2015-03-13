package br.com.centralit.citcorpore.bean;

/**
 * Anexo Base de Conhecimento (Referência da tabela AnexoBaseConhecimento)
 *
 * @author VMD
 *
 */
public class AnexoBaseConhecimentoPesquisaDTO {

    private String nome;

    private String descricao;

    private String link;

    private String textoDocumento;

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome
     *            the nome to set
     */
    public void setNome(final String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao
     *            the descricao to set
     */
    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link
     *            the link to set
     */
    public void setLink(final String link) {
        this.link = link;
    }

    /**
     * @return valor do atributo textoDocumento.
     */
    public String getTextoDocumento() {
        return textoDocumento;
    }

    /**
     * Define valor do atributo textoDocumento.
     *
     * @param textoDocumento
     */
    public void setTextoDocumento(final String textoDocumento) {
        this.textoDocumento = textoDocumento;
    }

}
