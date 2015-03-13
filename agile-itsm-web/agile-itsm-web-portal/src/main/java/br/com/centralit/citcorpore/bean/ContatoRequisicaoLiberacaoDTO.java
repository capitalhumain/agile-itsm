package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ContatoRequisicaoLiberacaoDTO extends BaseEntity {

    private Integer idContatoRequisicaoLiberacao;
    private String nomeContato;
    private String telefoneContato;
    private String emailContato;
    private String observacao;
    private Integer idLocalidade;
    private String ramal;
    private Integer idUnidade;

    public Integer getIdContatoRequisicaoLiberacao() {
        return idContatoRequisicaoLiberacao;
    }

    public void setIdContatoRequisicaoLiberacao(final Integer idContatoRequisicaoLiberacao) {
        this.idContatoRequisicaoLiberacao = idContatoRequisicaoLiberacao;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(final String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(final String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public void setEmailContato(final String emailContato) {
        this.emailContato = emailContato;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(final String observacao) {
        this.observacao = observacao;
    }

    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(final Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(final String ramal) {
        this.ramal = ramal;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(final Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

}
