package br.com.citframework.dto;

import br.com.agileitsm.model.support.BaseEntity;

public class Usuario extends BaseEntity {

    private static final long serialVersionUID = 209957399940789640L;

    private String idUsuario;
    private String ipUsuario;
    private String servidor;
    private Integer idEmpresa;
    private String nomeUsuario;
    private String matricula;
    private String acessos;
    private String locale;

    private String[] grupos;
    private Integer idProfissional;

    private Integer idUsuarioSistema;
    private String nomeEmpresa;

    public Usuario() {}

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(final Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getIpUsuario() {
        return ipUsuario;
    }

    public void setIpUsuario(final String ipUsuario) {
        this.ipUsuario = ipUsuario;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(final String servidor) {
        this.servidor = servidor;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(final String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder();
        final String newLine = "\r\n";
        if (matricula != null) {
            buffer.append("Matricula: ");
            buffer.append(matricula).append(newLine);
        }
        buffer.append("Nome do usuario: ");
        buffer.append(nomeUsuario).append(newLine);
        return buffer.toString();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(final String matricula) {
        this.matricula = matricula;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(final String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAcessos() {
        return acessos;
    }

    public void setAcessos(final String acessos) {
        this.acessos = acessos;
    }

    public String[] getGrupos() {
        return grupos;
    }

    public void setGrupos(final String[] grupos) {
        this.grupos = grupos;
    }

    public Integer getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(final Integer idProfissional) {
        this.idProfissional = idProfissional;
    }

    public Integer getIdUsuarioSistema() {
        return idUsuarioSistema;
    }

    public void setIdUsuarioSistema(final Integer idUsuarioSistema) {
        this.idUsuarioSistema = idUsuarioSistema;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(final String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(final String locale) {
        this.locale = locale;
    }

}
