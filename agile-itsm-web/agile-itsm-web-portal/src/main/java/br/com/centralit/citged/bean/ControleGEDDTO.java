package br.com.centralit.citged.bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;

public class ControleGEDDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public static final int TABELA_RESPOSTAITEMQUESTIONARIOANEXOS = 0;
    public static final int TABELA_IMAGEMHISTORICO = 1;
    public static final int TABELA_GALERIA_IMAGENS = 2;
    public static final int TABELA_SOLICITACAOSERVICO = 3;
    public static final int TABELA_BASECONHECIMENTO = 4;
    public static final int TABELA_EXECUCAOATIVIDADE = 5;
    public static final int TABELA_COLETAPRECOS = 9;
    public static final int TABELA_REQUISICAOMUDANCA = 11;
    public static final int TABELA_REQUISICAOLIBERACAO = 12;
    public static final int TABELA_HISTORICO_REQUISICAOLIBERACAO = 13;
    public static final int TABELA_DOCSLEGAIS_REQUISICAOLIBERACAO = 15;
    public static final int TABELA_DOCSGERAIS_REQUISICAOLIBERACAO = 16;
    public static final int TABELA_HISTORICO_DOCSGERAIS_REQUISICAOLIBERACAO = 17;
    public static final int TABELA_HISTORICO_DOCSLEGAIS_REQUISICAOLIBERACAO = 18;
    public static final int TABELA_PLANO_REVERSAO_MUDANCA = 19;
    public static final int TABELA_PROBLEMA = 21;
    public static final int TABELA_IMPORTARDADOS = 22;
    public static final int TABELA_INFO_CATALOGO_SERVICO = 24;

    private Integer idControleGED;
    private Integer idTabela;
    private Integer id;
    private String nomeArquivo;
    private String descricaoArquivo;
    private String extensaoArquivo;
    private Date dataHora;
    private String pasta;
    private String pathArquivo;
    private List pathsAssinaturas;
    private String versao;

    private Integer qtdeObjetos;

    public Integer getIdControleGED() {
        return idControleGED;
    }

    public void setIdControleGED(final Integer idControleGED) {
        this.idControleGED = idControleGED;
    }

    public Integer getIdTabela() {
        return idTabela;
    }

    public void setIdTabela(final Integer idTabela) {
        this.idTabela = idTabela;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(final Integer id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(final String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getDescricaoArquivo() {
        return descricaoArquivo;
    }

    public void setDescricaoArquivo(final String descricaoArquivo) {
        this.descricaoArquivo = descricaoArquivo;
    }

    public String getExtensaoArquivo() {
        return extensaoArquivo;
    }

    public void setExtensaoArquivo(final String extensaoArquivo) {
        this.extensaoArquivo = extensaoArquivo;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(final Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getPasta() {
        return pasta;
    }

    public void setPasta(final String pasta) {
        this.pasta = pasta;
    }

    public Integer getQtdeObjetos() {
        return qtdeObjetos;
    }

    public void setQtdeObjetos(final Integer qtdeObjetos) {
        this.qtdeObjetos = qtdeObjetos;
    }

    public String getPathArquivo() {
        return pathArquivo;
    }

    public void setPathArquivo(final String pathArquivo) {
        this.pathArquivo = pathArquivo;
    }

    public List getPathsAssinaturas() {
        if (pathsAssinaturas == null) {
            pathsAssinaturas = new ArrayList<>();
        }
        return pathsAssinaturas;
    }

    public void setPathsAssinaturas(final List pathsAssinaturas) {
        this.pathsAssinaturas = pathsAssinaturas;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }

}
