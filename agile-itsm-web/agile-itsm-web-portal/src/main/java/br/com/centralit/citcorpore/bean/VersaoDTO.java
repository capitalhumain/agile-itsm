package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.CITCorporeUtil;

public class VersaoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idUsuario;
    private Integer idVersao;
    private String nomeVersao;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Integer getIdVersao() {
        return idVersao;
    }

    public String getNomeFisicoArquivo() {
        String nomeFisicoArquivo = new String();
        if (this.getNomeVersao() != null) {
            nomeFisicoArquivo = "deploy_versao_" + this.getNomeVersao() + "_" + CITCorporeUtil.SGBD_PRINCIPAL + ".sql";
        }
        return nomeFisicoArquivo;
    }

    public String getNomeVersao() {
        return nomeVersao;
    }

    public void setIdUsuario(final Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdVersao(final Integer idVersao) {
        this.idVersao = idVersao;
    }

    public void setNomeVersao(final String nomeVersao) {
        this.nomeVersao = nomeVersao;
    }

}
