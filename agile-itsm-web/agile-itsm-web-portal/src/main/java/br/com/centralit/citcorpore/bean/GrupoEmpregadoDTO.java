package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class GrupoEmpregadoDTO extends BaseEntity {

    private static final long serialVersionUID = -2802341677119032913L;

    private Integer idGrupo;

    private Integer idEmpregado;

    private String sigla;

    private String enviaEmail;

    private String nomeEmpregado;

    public String getEnviaEmail() {
        return enviaEmail;
    }

    public void setEnviaEmail(final String enviaEmail) {
        this.enviaEmail = enviaEmail;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(final Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(final String sigla) {
        this.sigla = sigla;
    }

    public String getNomeEmpregado() {
        return nomeEmpregado;
    }

    public void setNomeEmpregado(final String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (idEmpregado == null ? 0 : idEmpregado.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final GrupoEmpregadoDTO other = (GrupoEmpregadoDTO) obj;
        if (idEmpregado == null) {
            if (other.idEmpregado != null) {
                return false;
            }
        } else if (!idEmpregado.equals(other.idEmpregado)) {
            return false;
        }
        return true;
    }

}
