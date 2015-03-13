package br.com.centralit.citcorpore.bean;

import java.io.Serializable;

/**
 * @author ezequiel.nunes
 *
 */
public class DadosEmailRegOcorrenciaDTO implements Serializable {

    private static final long serialVersionUID = -2625205721399695112L;

    private Integer idResponsavelAtual;

    private Integer idGrupoAtual;

    private Integer idEmpregado;

    private String email;

    private String nome;

    /**
     * @return the idResponsavelAtual
     */
    public Integer getIdResponsavelAtual() {
        return idResponsavelAtual;
    }

    /**
     * @param idResponsavelAtual
     *            the idResponsavelAtual to set
     */
    public void setIdResponsavelAtual(final Integer idResponsavelAtual) {
        this.idResponsavelAtual = idResponsavelAtual;
    }

    /**
     * @return the idGrupoAtual
     */
    public Integer getIdGrupoAtual() {
        return idGrupoAtual;
    }

    /**
     * @param idGrupoAtual
     *            the idGrupoAtual to set
     */
    public void setIdGrupoAtual(final Integer idGrupoAtual) {
        this.idGrupoAtual = idGrupoAtual;
    }

    /**
     * @return the idEmpregado
     */
    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    /**
     * @param idEmpregado
     *            the idEmpregado to set
     */
    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

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

}
