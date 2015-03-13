package br.com.centralit.citcorpore.metainfo.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class GrupoVisaoCamposNegocioDTO extends BaseEntity {

    public static String RELATION_NONE = "N";
    public static String RELATION_SIMPLE = "S";
    public static String RELATION_COMPLEX = "O";
    public static String RELATION_SQL = "Q";
    public static String RELATION_COMBO = "C";

    private Integer idGrupoVisao;
    private Integer idCamposObjetoNegocio;
    private String descricaoNegocio;
    private String tipoNegocio;
    private Integer ordem;
    private String situacao;
    private String obrigatorio;
    private Integer tamanho;
    private Integer decimais;
    private String tipoLigacao;
    private String sql;
    private String filtro;
    private Integer tamanhoParaPesq;
    private String formula;
    private String visivel;
    private String htmlCode;
    private String classeName;
    private String identificador;

    private Integer idObjetoNegocio;
    private Integer idVisao;
    private String descricao;
    private String tipoVisao;
    private String situacaoVisao;

    private Integer numeroEdicao;
    private String ordemCampos;
    private String[] valoresOpcoes;

    private CamposObjetoNegocioDTO camposObjetoNegocioDto;
    private Collection colValores;

    private Integer idObjetoNegocioLigacao;
    private Integer idCamposObjetoNegocioLigacao;
    private Integer idCamposObjetoNegocioLigacaoVinc;
    private Integer idCamposObjetoNegocioLigacaoOrder;

    private Integer idObjetoNegocioMatriz;
    private Integer idCamposObjetoNegocio1;
    private Integer idCamposObjetoNegocio2;
    private Integer idCamposObjetoNegocio3;
    private String descricaoCampo1;
    private String descricaoCampo2;
    private String descricaoCampo3;

    private String numTabs;

    private String descricaoRelacionamento;

    private String nomeCombo;

    private Integer idVisaoRel;
    private Integer seq;

    private String scryptType;
    private String script;

    private String htmlCodeType;

    private String texto;
    private String acao;
    private String hint;
    private String icone;

    private String botaoCadastrado;

    private String ordemBotoes;

    private String nomeDB;
    private String nomeTabelaDB;

    private String nomeTabelaDBLigacao;
    private String nomeDBLigacao;
    private String nomeDBLigacaoVinc;
    private String nomeDBLigacaoOrder;

    public Integer getIdGrupoVisao() {
        return idGrupoVisao;
    }

    public void setIdGrupoVisao(final Integer parm) {
        idGrupoVisao = parm;
    }

    public Integer getIdCamposObjetoNegocio() {
        return idCamposObjetoNegocio;
    }

    public void setIdCamposObjetoNegocio(final Integer parm) {
        idCamposObjetoNegocio = parm;
    }

    public String getDescricaoNegocio() {
        return descricaoNegocio;
    }

    public void setDescricaoNegocio(final String parm) {
        descricaoNegocio = parm;
    }

    public String getTipoNegocio() {
        return tipoNegocio;
    }

    public void setTipoNegocio(final String parm) {
        tipoNegocio = parm;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(final Integer parm) {
        ordem = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public String getObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(final String parm) {
        obrigatorio = parm;
    }

    public CamposObjetoNegocioDTO getCamposObjetoNegocioDto() {
        return camposObjetoNegocioDto;
    }

    public void setCamposObjetoNegocioDto(final CamposObjetoNegocioDTO camposObjetoNegocioDto) {
        this.camposObjetoNegocioDto = camposObjetoNegocioDto;
    }

    public Integer getTamanho() {
        return tamanho;
    }

    public void setTamanho(final Integer tamanho) {
        this.tamanho = tamanho;
    }

    public Integer getDecimais() {
        return decimais;
    }

    public void setDecimais(final Integer decimais) {
        this.decimais = decimais;
    }

    public Collection getColValores() {
        return colValores;
    }

    public void setColValores(final Collection colValores) {
        this.colValores = colValores;
    }

    public String getTipoLigacao() {
        return tipoLigacao;
    }

    public void setTipoLigacao(final String tipoLigacao) {
        this.tipoLigacao = tipoLigacao;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(final String sql) {
        this.sql = sql;
    }

    public Integer getTamanhoParaPesq() {
        return tamanhoParaPesq;
    }

    public void setTamanhoParaPesq(final Integer tamanhoParaPesq) {
        this.tamanhoParaPesq = tamanhoParaPesq;
    }

    public String getFormula() {
        if (formula != null) {
            formula = formula.replaceAll("'", "\"");
        }
        return formula;
    }

    public void setFormula(final String formula) {
        this.formula = formula;
    }

    public String getVisivel() {
        return visivel;
    }

    public void setVisivel(final String visivel) {
        this.visivel = visivel;
    }

    public Integer getIdObjetoNegocio() {
        return idObjetoNegocio;
    }

    public void setIdObjetoNegocio(final Integer idObjetoNegocio) {
        this.idObjetoNegocio = idObjetoNegocio;
    }

    public Integer getIdVisao() {
        return idVisao;
    }

    public void setIdVisao(final Integer idVisao) {
        this.idVisao = idVisao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getTipoVisao() {
        return tipoVisao;
    }

    public void setTipoVisao(final String tipoVisao) {
        this.tipoVisao = tipoVisao;
    }

    public String getSituacaoVisao() {
        return situacaoVisao;
    }

    public void setSituacaoVisao(final String situacaoVisao) {
        this.situacaoVisao = situacaoVisao;
    }

    public Integer getNumeroEdicao() {
        return numeroEdicao;
    }

    public void setNumeroEdicao(final Integer numeroEdicao) {
        this.numeroEdicao = numeroEdicao;
    }

    public String getOrdemCampos() {
        return ordemCampos;
    }

    public void setOrdemCampos(final String ordemCampos) {
        this.ordemCampos = ordemCampos;
    }

    public String[] getValoresOpcoes() {
        return valoresOpcoes;
    }

    public void setValoresOpcoes(final String[] valoresOpcoes) {
        this.valoresOpcoes = valoresOpcoes;
    }

    public Integer getIdObjetoNegocioLigacao() {
        return idObjetoNegocioLigacao;
    }

    public void setIdObjetoNegocioLigacao(final Integer idObjetoNegocioLigacao) {
        this.idObjetoNegocioLigacao = idObjetoNegocioLigacao;
    }

    public Integer getIdCamposObjetoNegocioLigacaoVinc() {
        return idCamposObjetoNegocioLigacaoVinc;
    }

    public void setIdCamposObjetoNegocioLigacaoVinc(final Integer idCamposObjetoNegocioLigacaoVinc) {
        this.idCamposObjetoNegocioLigacaoVinc = idCamposObjetoNegocioLigacaoVinc;
    }

    public Integer getIdCamposObjetoNegocioLigacaoOrder() {
        return idCamposObjetoNegocioLigacaoOrder;
    }

    public void setIdCamposObjetoNegocioLigacaoOrder(final Integer idCamposObjetoNegocioLigacaoOrder) {
        this.idCamposObjetoNegocioLigacaoOrder = idCamposObjetoNegocioLigacaoOrder;
    }

    public Integer getIdCamposObjetoNegocioLigacao() {
        return idCamposObjetoNegocioLigacao;
    }

    public void setIdCamposObjetoNegocioLigacao(final Integer idCamposObjetoNegocioLigacao) {
        this.idCamposObjetoNegocioLigacao = idCamposObjetoNegocioLigacao;
    }

    public String getDescricaoRelacionamento() {
        return descricaoRelacionamento;
    }

    public void setDescricaoRelacionamento(final String descricaoRelacionamento) {
        this.descricaoRelacionamento = descricaoRelacionamento;
    }

    public String getNumTabs() {
        return numTabs;
    }

    public void setNumTabs(final String numTabs) {
        this.numTabs = numTabs;
    }

    public String getNomeCombo() {
        return nomeCombo;
    }

    public void setNomeCombo(final String nomeCombo) {
        this.nomeCombo = nomeCombo;
    }

    public Integer getIdVisaoRel() {
        return idVisaoRel;
    }

    public void setIdVisaoRel(final Integer idVisaoRel) {
        this.idVisaoRel = idVisaoRel;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(final Integer seq) {
        this.seq = seq;
    }

    public String getHtmlCode() {
        return htmlCode;
    }

    public void setHtmlCode(final String htmlCode) {
        this.htmlCode = htmlCode;
    }

    public String getScryptType() {
        return scryptType;
    }

    public void setScryptType(final String scryptType) {
        this.scryptType = scryptType;
    }

    public String getScript() {
        return script;
    }

    public void setScript(final String script) {
        this.script = script;
    }

    public String getClasseName() {
        return classeName;
    }

    public void setClasseName(final String classeName) {
        this.classeName = classeName;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(final String texto) {
        this.texto = texto;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(final String acao) {
        this.acao = acao;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(final String hint) {
        this.hint = hint;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(final String icone) {
        this.icone = icone;
    }

    public String getBotaoCadastrado() {
        return botaoCadastrado;
    }

    public void setBotaoCadastrado(final String botaoCadastrado) {
        this.botaoCadastrado = botaoCadastrado;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(final String identificador) {
        this.identificador = identificador;
    }

    public String getHtmlCodeType() {
        return htmlCodeType;
    }

    public void setHtmlCodeType(final String htmlCodeType) {
        this.htmlCodeType = htmlCodeType;
    }

    public String getOrdemBotoes() {
        return ordemBotoes;
    }

    public void setOrdemBotoes(final String ordemBotoes) {
        this.ordemBotoes = ordemBotoes;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(final String filtro) {
        this.filtro = filtro;
    }

    public String getNomeDB() {
        return nomeDB;
    }

    public void setNomeDB(final String nomeDB) {
        this.nomeDB = nomeDB;
    }

    public String getNomeTabelaDB() {
        return nomeTabelaDB;
    }

    public void setNomeTabelaDB(final String nomeTabelaDB) {
        this.nomeTabelaDB = nomeTabelaDB;
    }

    public String getNomeTabelaDBLigacao() {
        return nomeTabelaDBLigacao;
    }

    public void setNomeTabelaDBLigacao(final String nomeTabelaDBLigacao) {
        this.nomeTabelaDBLigacao = nomeTabelaDBLigacao;
    }

    public String getNomeDBLigacao() {
        return nomeDBLigacao;
    }

    public void setNomeDBLigacao(final String nomeDBLigacao) {
        this.nomeDBLigacao = nomeDBLigacao;
    }

    public String getNomeDBLigacaoVinc() {
        return nomeDBLigacaoVinc;
    }

    public void setNomeDBLigacaoVinc(final String nomeDBLigacaoVinc) {
        this.nomeDBLigacaoVinc = nomeDBLigacaoVinc;
    }

    public String getNomeDBLigacaoOrder() {
        return nomeDBLigacaoOrder;
    }

    public void setNomeDBLigacaoOrder(final String nomeDBLigacaoOrder) {
        this.nomeDBLigacaoOrder = nomeDBLigacaoOrder;
    }

    public Integer getIdObjetoNegocioMatriz() {
        return idObjetoNegocioMatriz;
    }

    public void setIdObjetoNegocioMatriz(final Integer idObjetoNegocioMatriz) {
        this.idObjetoNegocioMatriz = idObjetoNegocioMatriz;
    }

    public Integer getIdCamposObjetoNegocio1() {
        return idCamposObjetoNegocio1;
    }

    public void setIdCamposObjetoNegocio1(final Integer idCamposObjetoNegocio1) {
        this.idCamposObjetoNegocio1 = idCamposObjetoNegocio1;
    }

    public Integer getIdCamposObjetoNegocio2() {
        return idCamposObjetoNegocio2;
    }

    public void setIdCamposObjetoNegocio2(final Integer idCamposObjetoNegocio2) {
        this.idCamposObjetoNegocio2 = idCamposObjetoNegocio2;
    }

    public Integer getIdCamposObjetoNegocio3() {
        return idCamposObjetoNegocio3;
    }

    public void setIdCamposObjetoNegocio3(final Integer idCamposObjetoNegocio3) {
        this.idCamposObjetoNegocio3 = idCamposObjetoNegocio3;
    }

    public String getDescricaoCampo1() {
        return descricaoCampo1;
    }

    public void setDescricaoCampo1(final String descricaoCampo1) {
        this.descricaoCampo1 = descricaoCampo1;
    }

    public String getDescricaoCampo2() {
        return descricaoCampo2;
    }

    public void setDescricaoCampo2(final String descricaoCampo2) {
        this.descricaoCampo2 = descricaoCampo2;
    }

    public String getDescricaoCampo3() {
        return descricaoCampo3;
    }

    public void setDescricaoCampo3(final String descricaoCampo3) {
        this.descricaoCampo3 = descricaoCampo3;
    }

}
