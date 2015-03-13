package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.UtilFormatacao;
import br.com.citframework.util.UtilHTML;
import br.com.citframework.util.UtilStrings;

public class TarefaLinhaBaseProjetoDTO extends BaseEntity {

    public static final String ATIVO = "A";
    public static final String PRONTO = "P";
    public static final String FALHA = "F";
    public static final String SUSPENSA = "S";
    public static final String SEMDEFINICAO = "?";

    private Integer idTarefaLinhaBaseProjeto;
    private Integer idLinhaBaseProjeto;
    private Integer sequencia;
    private Integer idCalendario;
    private Integer idTarefaLinhaBaseProjetoVinc;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;
    private String codeTarefa;
    private String nomeTarefa;
    private String detalhamentoTarefa;
    private Double progresso;
    private Double duracaoHora;
    private Integer nivel;
    private String idInternal;
    private String collapsed;
    private Double custo;
    private Double custoPerfil;
    private String situacao;
    private String estimada;
    private Double trabalho;
    private java.sql.Date dataInicioReal;
    private java.sql.Date dataFimReal;
    private Double duracaoHoraReal;
    private Double custoReal;
    private Double custoRealPerfil;
    private Integer idTarefaLinhaBaseProjetoMigr;
    private Integer idTarefaLinhaBaseProjetoPai;
    private Double tempoTotAlocMinutos;
    private Integer idPagamentoProjeto;
    private Integer idMarcoPagamentoPrj;
    private String depends;

    public String getDepends() {
        return depends;
    }

    public void setDepends(final String depends) {
        this.depends = depends;
    }

    private Integer idRecursoTarefaLinBaseProj;
    private Integer idPerfilContrato;
    private Integer idEmpregado;
    private Double percentualAloc;
    private String tempoAloc;
    private Double percentualExec;

    private Collection colRecursos;
    private Collection colProdutos;
    private Collection colPerfis;

    private String nomesRecursos;
    private String nomesProdutos;
    private String nomesPerfis;
    private String esforcoPorOS;

    public Integer getIdTarefaLinhaBaseProjeto() {
        return idTarefaLinhaBaseProjeto;
    }

    public void setIdTarefaLinhaBaseProjeto(final Integer parm) {
        idTarefaLinhaBaseProjeto = parm;
    }

    public Integer getIdLinhaBaseProjeto() {
        return idLinhaBaseProjeto;
    }

    public void setIdLinhaBaseProjeto(final Integer parm) {
        idLinhaBaseProjeto = parm;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(final Integer parm) {
        sequencia = parm;
    }

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(final Integer parm) {
        idCalendario = parm;
    }

    public Integer getIdTarefaLinhaBaseProjetoVinc() {
        return idTarefaLinhaBaseProjetoVinc;
    }

    public void setIdTarefaLinhaBaseProjetoVinc(final Integer parm) {
        idTarefaLinhaBaseProjetoVinc = parm;
    }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final java.sql.Date parm) {
        dataInicio = parm;
    }

    public java.sql.Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final java.sql.Date parm) {
        dataFim = parm;
    }

    public String getCodeTarefa() {
        return codeTarefa;
    }

    public void setCodeTarefa(final String parm) {
        codeTarefa = parm;
    }

    public String getNomeTarefaNivel() {
        if (this.getNivel() == null) {
            return nomeTarefa;
        }
        String str = "";
        for (int i = 0; i < this.getNivel(); i++) {
            str += "   ";
        }
        return str + nomeTarefa;
    }

    public String getNomeTarefaNivelHTML() {
        String ret = this.getNomeTarefaNivel();
        if (ret != null) {
            ret = ret.replaceAll(" ", "&nbsp;");
            return ret;
        }
        return ret;
    }

    public String getNomeTarefaNivelPonto() {
        String ret = this.getNomeTarefaNivel();
        if (ret != null) {
            ret = ret.replaceAll(" ", ".");
            return ret;
        }
        return ret;
    }

    public String getNomeTarefaNivelPontoHTMLEncoded() {
        String ret = this.getNomeTarefaNivel();
        if (ret != null) {
            ret = ret.replaceAll(" ", ".");
            return UtilHTML.encodeHTML(UtilStrings.nullToVazio(ret));
        }
        return UtilHTML.encodeHTML(UtilStrings.nullToVazio(ret));
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(final String parm) {
        nomeTarefa = parm;
    }

    public String getDetalhamentoTarefa() {
        return detalhamentoTarefa;
    }

    public void setDetalhamentoTarefa(final String parm) {
        detalhamentoTarefa = parm;
    }

    public Double getProgresso() {
        return progresso;
    }

    public void setProgresso(final Double parm) {
        progresso = parm;
    }

    public Double getDuracaoHora() {
        return duracaoHora;
    }

    public void setDuracaoHora(final Double parm) {
        duracaoHora = parm;
    }

    public boolean isTarefaFase() {
        int niv = 0;
        if (nivel != null) {
            niv = nivel.intValue();
        }
        if (niv <= 1) {
            if (this.getColProdutos() != null && this.getColProdutos().size() > 0) {
                return false;
            }
            if (this.getColRecursos() != null && this.getColRecursos().size() > 0) {
                return false;
            }
            return true;
        }
        return false;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(final Integer parm) {
        nivel = parm;
    }

    public String getIdInternal() {
        return idInternal;
    }

    public void setIdInternal(final String parm) {
        idInternal = parm;
    }

    public String getCollapsed() {
        return collapsed;
    }

    public void setCollapsed(final String parm) {
        collapsed = parm;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(final Double parm) {
        custo = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public String getEstimada() {
        return estimada;
    }

    public void setEstimada(final String parm) {
        estimada = parm;
    }

    public Double getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(final Double parm) {
        trabalho = parm;
    }

    public java.sql.Date getDataInicioReal() {
        return dataInicioReal;
    }

    public void setDataInicioReal(final java.sql.Date parm) {
        dataInicioReal = parm;
    }

    public java.sql.Date getDataFimReal() {
        return dataFimReal;
    }

    public void setDataFimReal(final java.sql.Date parm) {
        dataFimReal = parm;
    }

    public Double getDuracaoHoraReal() {
        return duracaoHoraReal;
    }

    public void setDuracaoHoraReal(final Double parm) {
        duracaoHoraReal = parm;
    }

    public Double getCustoReal() {
        return custoReal;
    }

    public void setCustoReal(final Double parm) {
        custoReal = parm;
    }

    public Collection getColRecursos() {
        return colRecursos;
    }

    public void setColRecursos(final Collection colRecursos) {
        this.colRecursos = colRecursos;
    }

    public String getProdutos() {
        return "------ XXXXX-----";
    }

    public Collection getColProdutos() {
        return colProdutos;
    }

    public void setColProdutos(final Collection colProdutos) {
        this.colProdutos = colProdutos;
    }

    public Integer getIdRecursoTarefaLinBaseProj() {
        return idRecursoTarefaLinBaseProj;
    }

    public void setIdRecursoTarefaLinBaseProj(final Integer idRecursoTarefaLinBaseProj) {
        this.idRecursoTarefaLinBaseProj = idRecursoTarefaLinBaseProj;
    }

    public Integer getIdPerfilContrato() {
        return idPerfilContrato;
    }

    public void setIdPerfilContrato(final Integer idPerfilContrato) {
        this.idPerfilContrato = idPerfilContrato;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    public Double getPercentualAloc() {
        return percentualAloc;
    }

    public void setPercentualAloc(final Double percentualAloc) {
        this.percentualAloc = percentualAloc;
    }

    public String getTempoAloc() {
        return tempoAloc;
    }

    public void setTempoAloc(final String tempoAloc) {
        this.tempoAloc = tempoAloc;
    }

    public Double getPercentualExec() {
        return percentualExec;
    }

    public void setPercentualExec(final Double percentualExec) {
        this.percentualExec = percentualExec;
    }

    public Integer getIdTarefaLinhaBaseProjetoMigr() {
        return idTarefaLinhaBaseProjetoMigr;
    }

    public void setIdTarefaLinhaBaseProjetoMigr(final Integer idTarefaLinhaBaseProjetoMigr) {
        this.idTarefaLinhaBaseProjetoMigr = idTarefaLinhaBaseProjetoMigr;
    }

    public String getNomesRecursos() {
        return nomesRecursos;
    }

    public void setNomesRecursos(final String nomesRecursos) {
        this.nomesRecursos = nomesRecursos;
    }

    public boolean isTemProdutos() {
        if (nomesProdutos == null) {
            return false;
        }
        if (nomesProdutos.trim().equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

    public String getNomesProdutos() {
        return nomesProdutos;
    }

    public String getNomesProdutosHTMLEncoded() {
        return UtilHTML.encodeHTML(UtilStrings.nullToVazio(nomesProdutos));
    }

    public void setNomesProdutos(final String nomesProdutos) {
        this.nomesProdutos = nomesProdutos;
    }

    public Double getCustoPerfil() {
        return custoPerfil;
    }

    public String getCustoPerfilStr() {
        if (custoPerfil == null) {
            return "";
        }
        return UtilFormatacao.formatDouble(custoPerfil, 2);
    }

    public String getCustoPerfilStrHTMLEncoded() {
        if (custoPerfil == null) {
            return "";
        }
        return UtilHTML.encodeHTML(UtilStrings.nullToVazio(UtilFormatacao.formatDouble(custoPerfil, 2)));
    }

    public void setCustoPerfil(final Double custoPerfil) {
        this.custoPerfil = custoPerfil;
    }

    public Double getCustoRealPerfil() {
        return custoRealPerfil;
    }

    public void setCustoRealPerfil(final Double custoRealPerfil) {
        this.custoRealPerfil = custoRealPerfil;
    }

    public Integer getIdTarefaLinhaBaseProjetoPai() {
        return idTarefaLinhaBaseProjetoPai;
    }

    public void setIdTarefaLinhaBaseProjetoPai(final Integer idTarefaLinhaBaseProjetoPai) {
        this.idTarefaLinhaBaseProjetoPai = idTarefaLinhaBaseProjetoPai;
    }

    public Collection getColPerfis() {
        return colPerfis;
    }

    public void setColPerfis(final Collection colPerfis) {
        this.colPerfis = colPerfis;
    }

    public String getNomesPerfis() {
        return nomesPerfis;
    }

    public void setNomesPerfis(final String nomesPerfis) {
        this.nomesPerfis = nomesPerfis;
    }

    public Double getTempoTotAlocMinutos() {
        return tempoTotAlocMinutos;
    }

    public void setTempoTotAlocMinutos(final Double tempoTotAlocMinutos) {
        this.tempoTotAlocMinutos = tempoTotAlocMinutos;
    }

    public Integer getIdPagamentoProjeto() {
        return idPagamentoProjeto;
    }

    public void setIdPagamentoProjeto(final Integer idPagamentoProjeto) {
        this.idPagamentoProjeto = idPagamentoProjeto;
    }

    public Integer getIdMarcoPagamentoPrj() {
        return idMarcoPagamentoPrj;
    }

    public void setIdMarcoPagamentoPrj(final Integer idMarcoPagamentoPrj) {
        this.idMarcoPagamentoPrj = idMarcoPagamentoPrj;
    }

    public String getEsforcoPorOS() {
        return esforcoPorOS;
    }

    public void setEsforcoPorOS(final String esforcoPorOS) {
        this.esforcoPorOS = esforcoPorOS;
    }

}
