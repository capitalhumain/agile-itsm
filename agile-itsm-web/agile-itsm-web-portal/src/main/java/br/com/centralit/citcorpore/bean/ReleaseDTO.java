package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author valdoilo.damasceno
 *
 */
public class ReleaseDTO extends BaseEntity {

    private static final long serialVersionUID = 3784166762154444481L;

    private String versao;

    private Collection<String> conteudo;

    public String getVersao() {
        return versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }

    public Collection<String> getConteudo() {
        return conteudo;
    }

    public void setConteudo(final Collection<String> conteudo) {
        this.conteudo = conteudo;
    }

}
