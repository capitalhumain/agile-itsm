package br.com.centralit.citcorpore.metainfo.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class VisaoRelacionadaDTO extends BaseEntity {

    public static String VINC_ABA_AO_LADO = "1";
    public static String VINC_ABA_FILHA = "2";

    public static String ACAO_RECUPERAR_PRINCIPAL = "RP";
    public static String ACAO_RECUPERAR_REGISTROS_VINCULADOS = "RV";
    public static String ACAO_SEM_ACAO = "XX";

    public static String PREFIXO_SISTEMA_TABELA_VINCULADA = "TABLE_SEARCH_";

    private Integer idVisaoRelacionada;
    private Integer idVisaoPai;
    private Integer idVisaoFilha;
    private Integer ordem;
    private String titulo;
    private String situacao;
    private String tipoVinculacao;
    private String acaoEmSelecaoPesquisa;
    private Integer idObjetoNegocioNN;

    private VisaoDTO visaoFilhaDto;

    private Collection colVinculosVisao;

    private String identificacaoVisaoFilha;
    private String nomeDBNegocioNN;

    public Integer getIdVisaoRelacionada() {
        return idVisaoRelacionada;
    }

    public void setIdVisaoRelacionada(final Integer parm) {
        idVisaoRelacionada = parm;
    }

    public Integer getIdVisaoPai() {
        return idVisaoPai;
    }

    public void setIdVisaoPai(final Integer parm) {
        idVisaoPai = parm;
    }

    public Integer getIdVisaoFilha() {
        return idVisaoFilha;
    }

    public void setIdVisaoFilha(final Integer parm) {
        idVisaoFilha = parm;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(final Integer parm) {
        ordem = parm;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String parm) {
        titulo = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public String getTipoVinculacao() {
        return tipoVinculacao;
    }

    public void setTipoVinculacao(final String tipoVinculacao) {
        this.tipoVinculacao = tipoVinculacao;
    }

    public VisaoDTO getVisaoFilhaDto() {
        return visaoFilhaDto;
    }

    public void setVisaoFilhaDto(final VisaoDTO visaoFilhaDto) {
        this.visaoFilhaDto = visaoFilhaDto;
    }

    public String getAcaoEmSelecaoPesquisa() {
        return acaoEmSelecaoPesquisa;
    }

    public void setAcaoEmSelecaoPesquisa(final String acaoEmSelecaoPesquisa) {
        this.acaoEmSelecaoPesquisa = acaoEmSelecaoPesquisa;
    }

    public Integer getIdObjetoNegocioNN() {
        return idObjetoNegocioNN;
    }

    public void setIdObjetoNegocioNN(final Integer idObjetoNegocioNN) {
        this.idObjetoNegocioNN = idObjetoNegocioNN;
    }

    public Collection getColVinculosVisao() {
        return colVinculosVisao;
    }

    public void setColVinculosVisao(final Collection colVinculosVisao) {
        this.colVinculosVisao = colVinculosVisao;
    }

    public String getIdentificacaoVisaoFilha() {
        return identificacaoVisaoFilha;
    }

    public void setIdentificacaoVisaoFilha(final String identificacaoVisaoFilha) {
        this.identificacaoVisaoFilha = identificacaoVisaoFilha;
    }

    public String getNomeDBNegocioNN() {
        return nomeDBNegocioNN;
    }

    public void setNomeDBNegocioNN(final String nomeDBNegocioNN) {
        this.nomeDBNegocioNN = nomeDBNegocioNN;
    }

}
