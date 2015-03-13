package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.Collection;

import org.apache.commons.lang3.StringEscapeUtils;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilHTML;
import br.com.citframework.util.UtilStrings;

public class ProjetoDTO extends BaseEntity {

    private static final long serialVersionUID = -7848776827100833523L;

    private Integer idProjeto;
    private Integer idProjetoAutorizacao;
    private Integer idProjetoPai;
    private Integer idCliente;
    private String nomeProjeto;
    private String detalhamento;
    private String situacao;
    private Double valorEstimado;

    private Integer nivel;
    private Integer idContrato;

    private String vinculoOS;
    private Integer idOs;

    private String sigla;
    private String emergencial;
    private String severidade;
    private String nomeGestor;
    private Integer idRequisicaoMudanca;
    private Integer idLiberacao;

    private Integer idServicoContrato;
    private Integer ano;
    private String numero;
    private String demanda;
    private String objetivo;
    private String nomeAreaRequisitante;
    private String nomeServicoOS;
    private Date dataEmissao;

    private String deleted;

    private Integer idLinhaBaseProjeto;
    private String justificativaMudanca;

    private Collection colRecursos;

    private Collection colAssinaturasAprovacoes;

    public String getDetalhamento() {
        return detalhamento;
    }

    public String getDetalhamentoFmt() {
        if (detalhamento == null) {
            return "";
        }
        return detalhamento;
    }

    public String getDetalhamentoFmtHTMEncoded() {
        if (detalhamento == null) {
            return "";
        }
        return StringEscapeUtils.escapeHtml4(detalhamento);
    }

    public void setDetalhamento(final String detalhamento) {
        this.detalhamento = detalhamento;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(final Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(final Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public String getNomeProjetoHTMLEncoded() {
        return UtilHTML.encodeHTML(nomeProjeto);
    }

    public void setNomeProjeto(final String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public Double getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(final Double valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public Integer getIdProjetoPai() {
        return idProjetoPai;
    }

    public void setIdProjetoPai(final Integer idProjetoPai) {
        this.idProjetoPai = idProjetoPai;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(final Integer nivel) {
        this.nivel = nivel;
    }

    public String getNomeHierarquizado() {

        if (nomeProjeto == null) {
            return "";
        }

        if (idProjeto == null) {
            return "";
        }

        if (this.getNivel() == null) {
            return idProjeto + " " + nomeProjeto;
        }

        String aux = "";

        for (int i = 0; i < this.getNivel().intValue(); i++) {
            aux += ".....";
        }

        return aux + idProjeto + " " + nomeProjeto;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Collection getColRecursos() {
        return colRecursos;
    }

    public void setColRecursos(final Collection colRecursos) {
        this.colRecursos = colRecursos;
    }

    public String getVinculoOS() {
        return vinculoOS;
    }

    public void setVinculoOS(final String vinculoOS) {
        this.vinculoOS = vinculoOS;
    }

    public Integer getIdOs() {
        return idOs;
    }

    public void setIdOs(final Integer idOs) {
        this.idOs = idOs;
    }

    public Integer getIdServicoContrato() {
        return idServicoContrato;
    }

    public void setIdServicoContrato(final Integer idServicoContrato) {
        this.idServicoContrato = idServicoContrato;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(final Integer ano) {
        this.ano = ano;
    }

    public String getNumeroStr() {
        if (numero == null) {
            return "";
        }
        return UtilStrings.nullToVazio(numero);
    }

    public String getNumeroStrHTMLEnconded() {
        if (numero == null) {
            return "";
        }
        return UtilHTML.encodeHTML(UtilStrings.nullToVazio(numero));
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(final String numero) {
        this.numero = numero;
    }

    public String getDemanda() {
        return demanda;
    }

    public void setDemanda(final String demanda) {
        this.demanda = demanda;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(final String objetivo) {
        this.objetivo = objetivo;
    }

    public String getNomeAreaRequisitante() {
        return UtilStrings.nullToVazio(nomeAreaRequisitante);
    }

    public String getNomeAreaRequisitanteHTMLEncoded() {
        return UtilHTML.encodeHTML(UtilStrings.nullToVazio(nomeAreaRequisitante));
    }

    public void setNomeAreaRequisitante(final String nomeAreaRequisitante) {
        this.nomeAreaRequisitante = nomeAreaRequisitante;
    }

    public String getNomeServicoOS() {
        return UtilStrings.nullToVazio(nomeServicoOS);
    }

    public String getNomeServicoOSHTMLEncoded() {
        return UtilHTML.encodeHTML(UtilStrings.nullToVazio(nomeServicoOS));
    }

    public void setNomeServicoOS(final String nomeServicoOS) {
        this.nomeServicoOS = nomeServicoOS;
    }

    public String getSigla() {
        return UtilStrings.nullToVazio(sigla);
    }

    public String getSiglaHTMLEncoded() {
        return UtilHTML.encodeHTML(UtilStrings.nullToVazio(sigla));
    }

    public void setSigla(final String sigla) {
        this.sigla = sigla;
    }

    public String getEmergencialStr() {
        if (emergencial == null) {
            return "";
        }
        if (emergencial.equalsIgnoreCase("S")) {
            return "Sim";
        } else if (emergencial.equalsIgnoreCase("N")) {
            return "Não";
        }
        return "";
    }

    public String getEmergencialStrHTMLEncoded() {
        if (emergencial == null) {
            return "";
        }
        if (emergencial.equalsIgnoreCase("S")) {
            return UtilHTML.encodeHTML("Sim");
        } else if (emergencial.equalsIgnoreCase("N")) {
            return UtilHTML.encodeHTML("Não");
        }
        return "";
    }

    public String getEmergencial() {
        return emergencial;
    }

    public void setEmergencial(final String emergencial) {
        this.emergencial = emergencial;
    }

    public String getSeveridade() {
        return UtilStrings.nullToVazio(severidade);
    }

    public String getSeveridadeHTMLEncoded() {
        return UtilHTML.encodeHTML(UtilStrings.nullToVazio(severidade));
    }

    public void setSeveridade(final String severidade) {
        this.severidade = severidade;
    }

    public String getNomeGestor() {
        return UtilStrings.nullToVazio(nomeGestor);
    }

    public String getNomeGestorHTMLEncoded() {
        return UtilHTML.encodeHTML(UtilStrings.nullToVazio(nomeGestor));
    }

    public void setNomeGestor(final String nomeGestor) {
        this.nomeGestor = nomeGestor;
    }

    public Integer getIdRequisicaoMudanca() {
        return idRequisicaoMudanca;
    }

    public void setIdRequisicaoMudanca(final Integer idRequisicaoMudanca) {
        this.idRequisicaoMudanca = idRequisicaoMudanca;
    }

    public Integer getIdLiberacao() {
        return idLiberacao;
    }

    public void setIdLiberacao(final Integer idLiberacao) {
        this.idLiberacao = idLiberacao;
    }

    public String getDataEmissaoStr() {
        if (dataEmissao == null) {
            return "";
        }
        return UtilDatas.dateToSTR(dataEmissao);
    }

    public String getDataEmissaoStrHTMLEncoded() {
        if (dataEmissao == null) {
            return "";
        }
        return UtilHTML.encodeHTML(UtilDatas.dateToSTR(dataEmissao));
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(final Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Integer getIdLinhaBaseProjeto() {
        return idLinhaBaseProjeto;
    }

    public void setIdLinhaBaseProjeto(final Integer idLinhaBaseProjeto) {
        this.idLinhaBaseProjeto = idLinhaBaseProjeto;
    }

    public String getJustificativaMudanca() {
        return justificativaMudanca;
    }

    public void setJustificativaMudanca(final String justificativaMudanca) {
        this.justificativaMudanca = justificativaMudanca;
    }

    public Integer getIdProjetoAutorizacao() {
        return idProjetoAutorizacao;
    }

    public void setIdProjetoAutorizacao(final Integer idProjetoAutorizacao) {
        this.idProjetoAutorizacao = idProjetoAutorizacao;
    }

    public Collection getColAssinaturasAprovacoes() {
        return colAssinaturasAprovacoes;
    }

    public void setColAssinaturasAprovacoes(final Collection colAssinaturasAprovacoes) {
        this.colAssinaturasAprovacoes = colAssinaturasAprovacoes;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

}
