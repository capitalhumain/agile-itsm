package br.com.centralit.citcorpore.bean;

import javax.naming.directory.DirContext;
import javax.naming.ldap.LdapContext;

import br.com.agileitsm.model.support.BaseEntity;

public class ServidorContextoDTO extends BaseEntity {

    private String ldpaFiltro;
    private String ldpaAtributo;
    private String grupoPadrao;
    private String perfilAcesso;
    private String numeroColaboradores;
    private DirContext dirContext;
    private String baseDominio;
    private LdapContext ldapContext;
    private String servidor;
    private boolean ativo;
    private String subDominio;

    public String getLdpaFiltro() {
        return ldpaFiltro;
    }

    public void setLdpaFiltro(final String ldpaFiltro) {
        this.ldpaFiltro = ldpaFiltro;
    }

    public String getLdpaAtributo() {
        return ldpaAtributo;
    }

    public void setLdpaAtributo(final String ldpaAtributo) {
        this.ldpaAtributo = ldpaAtributo;
    }

    public String getGrupoPadrao() {
        return grupoPadrao;
    }

    public void setGrupoPadrao(final String grupoPadrao) {
        this.grupoPadrao = grupoPadrao;
    }

    public String getPerfilAcesso() {
        return perfilAcesso;
    }

    public void setPerfilAcesso(final String perfilAcesso) {
        this.perfilAcesso = perfilAcesso;
    }

    public String getNumeroColaboradores() {
        return numeroColaboradores;
    }

    public void setNumeroColaboradores(final String numeroColaboradores) {
        this.numeroColaboradores = numeroColaboradores;
    }

    public DirContext getDirContext() {
        return dirContext;
    }

    public void setDirContext(final DirContext dirContext) {
        this.dirContext = dirContext;
    }

    public String getBaseDominio() {
        return baseDominio;
    }

    public void setBaseDominio(final String baseDominio) {
        this.baseDominio = baseDominio;
    }

    public LdapContext getLdapContext() {
        return ldapContext;
    }

    public void setLdapContext(final LdapContext ldapContext) {
        this.ldapContext = ldapContext;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(final String servidor) {
        this.servidor = servidor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(final boolean ativo) {
        this.ativo = ativo;
    }

    public String getSubDominio() {
        return subDominio;
    }

    public void setSubDominio(final String subDominio) {
        this.subDominio = subDominio;
    }

}
