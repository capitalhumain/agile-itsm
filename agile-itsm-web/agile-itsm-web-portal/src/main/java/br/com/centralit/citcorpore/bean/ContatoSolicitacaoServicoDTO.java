package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.Util;

public class ContatoSolicitacaoServicoDTO extends BaseEntity {

    private Integer idcontatosolicitacaoservico;
    private String nomecontato;
    private String telefonecontato;
    private String emailcontato;
    private String observacao;
    private Integer idLocalidade;
    private String ramal;

    /**
     * @return the idcontatosolicitacaoservico
     */
    public Integer getIdcontatosolicitacaoservico() {
        return idcontatosolicitacaoservico;
    }

    /**
     * @param idcontatosolicitacaoservico
     *            the idcontatosolicitacaoservico to set
     */
    public void setIdcontatosolicitacaoservico(final Integer idcontatosolicitacaoservico) {
        this.idcontatosolicitacaoservico = idcontatosolicitacaoservico;
    }

    /**
     * @return the nomecontato
     */
    public String getNomecontato() {
        return Util.tratarAspasSimples(nomecontato);
    }

    /**
     * @param nomecontato
     *            the nomecontato to set
     */
    public void setNomecontato(final String nomecontato) {
        this.nomecontato = nomecontato;
    }

    /**
     * @return the telefonecontato
     */
    public String getTelefonecontato() {
        if (telefonecontato == null) {
            telefonecontato = " ";
        }
        return telefonecontato;
    }

    /**
     * @param telefonecontato
     *            the telefonecontato to set
     */
    public void setTelefonecontato(final String telefonecontato) {
        this.telefonecontato = telefonecontato;
    }

    /**
     * @return the emailcontato
     */
    public String getEmailcontato() {
        if (emailcontato == null) {
            emailcontato = " ";
        }
        return Util.tratarAspasSimples(emailcontato);
    }

    /**
     * @param emailcontato
     *            the emailcontato to set
     */
    public void setEmailcontato(final String emailcontato) {
        this.emailcontato = emailcontato;
    }

    /**
     * @return the localizacaofisica
     */
    public String getObservacao() {
        if (observacao == null) {
            observacao = " ";
        }
        return observacao;
    }

    /**
     * @param observacao
     *            the localizacaofisica to set
     */
    public void setObservacao(final String observacao) {
        this.observacao = observacao;
    }

    public String getDadosStr() {
        final StringBuilder str = new StringBuilder();
        if (this.getNomecontato() != null) {
            str.append("Nome: " + this.getNomecontato() + "\n");
            if (this.getTelefonecontato() != null) {
                str.append("Telefone: " + this.getTelefonecontato() + "\n");
            }
            if (this.getEmailcontato() != null) {
                str.append("Email: " + this.getEmailcontato() + "\n");
            }
            if (this.getObservacao() != null && this.getObservacao().length() > 0) {
                str.append("Observação: " + this.getObservacao());
            }
        }
        return str.toString();
    }

    /**
     * @return the idLocalidade
     */
    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    /**
     * @param idLocalidade
     *            the idLocalidade to set
     */
    public void setIdLocalidade(final Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    /**
     * @return the ramal
     */
    public String getRamal() {
        return ramal;
    }

    /**
     * @param ramal
     *            the ramal to set
     */
    public void setRamal(final String ramal) {
        this.ramal = ramal;
    }

}
