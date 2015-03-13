package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author mario.haysaki
 *
 */
public class RelatorioGruposUsuarioDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idGrupo;
    private String nomeGrupo;
    private Integer idColaborador;
    private String nomeColaborador;
    private String formatoArquivoRelatorio;

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(final Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(final Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(final String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public void setNomeColaborador(final String nomeColaborador) {
        this.nomeColaborador = nomeColaborador;
    }

    public String getFormatoArquivoRelatorio() {
        return formatoArquivoRelatorio;
    }

    public void setFormatoArquivoRelatorio(final String formatoArquivoRelatorio) {
        this.formatoArquivoRelatorio = formatoArquivoRelatorio;
    }

}
