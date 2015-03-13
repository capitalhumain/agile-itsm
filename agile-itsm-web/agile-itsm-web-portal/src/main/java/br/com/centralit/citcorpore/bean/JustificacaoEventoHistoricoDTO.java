package br.com.centralit.citcorpore.bean;

/**
 * @author breno.guimaraes Entidade de relacionamento entre as tabelas
 *         JustificacaoFalhas, Evento e HistoricoTentativas.
 */
public class JustificacaoEventoHistoricoDTO {

    private String ip;
    private Integer idItemConfiguracao;
    private Integer idBaseItemConfiguracao;
    private String identificacaoItemConfiguracao;
    private String descricaoTentativa;
    private Integer idEvento;
    private String nomeGrupo;
    private String nomeUnidade;
    private Integer idEmpregado;
    private Integer idHistoricoTentativa;

    public String getIp() {
        return ip;
    }

    public void setIp(final String ip) {
        this.ip = ip;
    }

    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    public void setIdItemConfiguracao(final Integer idItemConfiguracao) {
        this.idItemConfiguracao = idItemConfiguracao;
    }

    public Integer getIdBaseItemConfiguracao() {
        return idBaseItemConfiguracao;
    }

    public void setIdBaseItemConfiguracao(final Integer idBaseItemConfiguracao) {
        this.idBaseItemConfiguracao = idBaseItemConfiguracao;
    }

    public String getIdentificacaoItemConfiguracao() {
        return identificacaoItemConfiguracao;
    }

    public void setIdentificacaoItemConfiguracao(final String identificacaoItemConfiguracao) {
        this.identificacaoItemConfiguracao = identificacaoItemConfiguracao;
    }

    public String getDescricaoTentativa() {
        return descricaoTentativa;
    }

    public void setDescricaoTentativa(final String descricaoTentativa) {
        this.descricaoTentativa = descricaoTentativa;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(final Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(final String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(final String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    public Integer getIdHistoricoTentativa() {
        return idHistoricoTentativa;
    }

    public void setIdHistoricoTentativa(final Integer idHistoricoTentativa) {
        this.idHistoricoTentativa = idHistoricoTentativa;
    }

}
