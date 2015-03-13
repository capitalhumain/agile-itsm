package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class EmailAlteracaoSenhaDTO extends BaseEntity {

    private static final long serialVersionUID = 5593259062378322864L;

    private String login;

    private String nomeEmpregado;

    private String novaSenha;

    private String link;

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getNomeEmpregado() {
        return nomeEmpregado;
    }

    public void setNomeEmpregado(final String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(final String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public void setLink(final String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

}
