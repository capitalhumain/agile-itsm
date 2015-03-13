package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilFormatacao;
import br.com.citframework.util.UtilHTML;

public class ExecucaoDemandaDTO extends BaseEntity {

    private static final long serialVersionUID = -5575586940240747276L;

    private Integer idExecucao;
    private Integer idDemanda;
    private Integer idAtividade;
    private Integer idEmpregadoExecutor;
    private Integer idEmpregadoReceptor;
    private String relato;
    private Double qtdeHoras;
    private String situacao;
    private String grupoExecutor;
    private String detalhamento;

    private String nomeFluxo;
    private String nomeEtapa;
    private String nomeAtividade;
    private Date expectativaFim;
    private String prioridade;
    private String descricaoTipoAtividade;
    private String nome;
    private Integer idFluxo;
    private Date terminoPrevisto;
    private Integer idEmpregadoLogado;

    private Integer idExecucaoAtribuir;
    private Integer idProjeto;
    private Integer idFluxoSelecionado;
    private String nomeProjeto;
    private Integer idTipoDemanda;

    private Date terminoReal;

    private String numeroOS;
    private Integer anoOS;

    private String objSerializado;

    public String getImagem() {
        String retorno = "";
        if (idTipoDemanda == null) {
            return "";
        }

        retorno = "<img src=demanda.gif title='Outros' border='0' />";

        if (idTipoDemanda.intValue() == 1) {
            retorno = "<img src=esquadro.jpg title='Demanda de Projeto' border='0' />";
        }
        if (idTipoDemanda.intValue() == 2) {
            retorno = "<img src=ferramenta.gif title='Manutenção Evolutiva' border='0' />";
        }
        if (idTipoDemanda.intValue() == 3) {
            retorno = "<img src=joaninha.gif title='Erro' border='0' />";
        }
        if (idTipoDemanda.intValue() == 4) {
            retorno = "<img src=comercial.gif title='Atendimento a área comercial' border='0' />";
        }
        if (idTipoDemanda.intValue() == 5) {
            retorno = "<img src=apresentacao.jpg title='Apresentação de Solução' border='0' />";
        }
        if (idTipoDemanda.intValue() == 6) {
            retorno = "<img src=reuniao.gif title='Reunião com cliente' border='0' />";
        }
        if (idTipoDemanda.intValue() == 7) {
            retorno = "<img src=cliente.gif title='Apoio ao Cliente' border='0' />";
        }
        if (idTipoDemanda.intValue() == 8) {
            retorno = "<img src=design.gif title='Design' border='0' />";
        }
        if (idTipoDemanda.intValue() == 9) {
            retorno = "<img src=ISO.gif title='Demanda ISO 9001' border='0' />";
        }
        if (idTipoDemanda.intValue() == 10) {
            retorno = "<img src=demanda.gif title='Demanda Avulsa' border='0' />";
        }
        if (idTipoDemanda.intValue() == 11) {
            retorno = "<img src=reuniaointerna.gif title='Reunião Interna' border='0' />";
        }

        return retorno;
    }

    public String getGrupoExecutor() {
        return grupoExecutor;
    }

    public void setGrupoExecutor(final String grupoExecutor) {
        this.grupoExecutor = grupoExecutor;
    }

    public Integer getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(final Integer idAtividade) {
        this.idAtividade = idAtividade;
    }

    public Integer getIdDemanda() {
        return idDemanda;
    }

    public void setIdDemanda(final Integer idDemanda) {
        this.idDemanda = idDemanda;
    }

    public Integer getIdEmpregadoExecutor() {
        return idEmpregadoExecutor;
    }

    public void setIdEmpregadoExecutor(final Integer idEmpregadoExecutor) {
        this.idEmpregadoExecutor = idEmpregadoExecutor;
    }

    public Integer getIdEmpregadoReceptor() {
        return idEmpregadoReceptor;
    }

    public void setIdEmpregadoReceptor(final Integer idEmpregadoReceptor) {
        this.idEmpregadoReceptor = idEmpregadoReceptor;
    }

    public Integer getIdExecucao() {
        return idExecucao;
    }

    public void setIdExecucao(final Integer idExecucao) {
        this.idExecucao = idExecucao;
    }

    public String getRelato() {
        return relato;
    }

    public void setRelato(final String relato) {
        this.relato = relato;
    }

    public String getSituacaoDesc() {
        if (situacao == null) {
            return "";
        }
        if (situacao.equalsIgnoreCase("N")) {
            return "Não Iniciada";
        }
        if (situacao.equalsIgnoreCase("I")) {
            return "Em Execução";
        }
        if (situacao.equalsIgnoreCase("F")) {
            return "Finalizada";
        }
        if (situacao.equalsIgnoreCase("C")) {
            return "Paralisada - Aguard. Cliente";
        }
        if (situacao.equalsIgnoreCase("P")) {
            return "Paralisada - Interno";
        }
        if (situacao.equalsIgnoreCase("T")) {
            return "Transferida";
        }
        return situacao;
    }

    public String getSituacaoDescHTML() {
        if (situacao == null) {
            return "";
        }
        if (situacao.equalsIgnoreCase("N")) {
            return UtilHTML.encodeHTML("Não Iniciada");
        }
        if (situacao.equalsIgnoreCase("I")) {
            return UtilHTML.encodeHTML("Em Execução");
        }
        if (situacao.equalsIgnoreCase("F")) {
            return "Finalizada";
        }
        if (situacao.equalsIgnoreCase("C")) {
            return "Paralisada - Aguard. Cliente";
        }
        if (situacao.equalsIgnoreCase("P")) {
            return "Paralisada - Interno";
        }
        if (situacao.equalsIgnoreCase("T")) {
            return "Transferida";
        }
        return UtilHTML.encodeHTML(situacao);
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public String getDescricaoTipoAtividade() {
        return descricaoTipoAtividade;
    }

    public void setDescricaoTipoAtividade(final String descricaoTipoAtividade) {
        this.descricaoTipoAtividade = descricaoTipoAtividade;
    }

    public String getExpectativaFimStr() {
        if (terminoPrevisto != null) {
            return UtilDatas.dateToSTR(terminoPrevisto);
        }
        return UtilDatas.dateToSTR(expectativaFim);
    }

    public Date getExpectativaFim() {
        return expectativaFim;
    }

    public void setExpectativaFim(final Date expectativaFim) {
        this.expectativaFim = expectativaFim;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public String getNomeAtividadeHTML() {
        return UtilHTML.encodeHTML(nomeAtividade);
    }

    public void setNomeAtividade(final String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    public String getNomeEtapa() {
        return nomeEtapa;
    }

    public String getNomeEtapaHTML() {
        return UtilHTML.encodeHTML(nomeEtapa);
    }

    public void setNomeEtapa(final String nomeEtapa) {
        this.nomeEtapa = nomeEtapa;
    }

    public String getNomeFluxo() {
        return nomeFluxo;
    }

    public void setNomeFluxo(final String nomeFluxo) {
        this.nomeFluxo = nomeFluxo;
    }

    public String getPrioridadeDesc() {
        if (prioridade == null) {
            return "";
        }
        if (prioridade.equalsIgnoreCase("E")) {
            return "Emergencial";
        }
        if (prioridade.equalsIgnoreCase("A")) {
            return "Alta";
        }
        if (prioridade.equalsIgnoreCase("M")) {
            return "Média";
        }
        if (prioridade.equalsIgnoreCase("B")) {
            return "Baixa";
        }
        if (prioridade.equalsIgnoreCase("X")) {
            return "Não Definida";
        }
        return prioridade;
    }

    public String getPrioridadeDescHTML() {
        if (prioridade == null) {
            return "";
        }
        if (prioridade.equalsIgnoreCase("E")) {
            return "Emergencial";
        }
        if (prioridade.equalsIgnoreCase("A")) {
            return "Alta";
        }
        if (prioridade.equalsIgnoreCase("M")) {
            return UtilHTML.encodeHTML("Média");
        }
        if (prioridade.equalsIgnoreCase("B")) {
            return "Baixa";
        }
        if (prioridade.equalsIgnoreCase("X")) {
            return UtilHTML.encodeHTML("Não Definida");
        }
        return UtilHTML.encodeHTML(prioridade);
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(final String prioridade) {
        this.prioridade = prioridade;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(final String detalhamento) {
        this.detalhamento = detalhamento;
    }

    public Integer getIdExecucaoAtribuir() {
        return idExecucaoAtribuir;
    }

    public void setIdExecucaoAtribuir(final Integer idExecucaoAtribuir) {
        this.idExecucaoAtribuir = idExecucaoAtribuir;
    }

    public Integer getIdFluxo() {
        return idFluxo;
    }

    public void setIdFluxo(final Integer idFluxo) {
        this.idFluxo = idFluxo;
    }

    public String getTerminoPrevistoStr() {
        return UtilDatas.dateToSTR(terminoPrevisto);
    }

    public Date getTerminoPrevisto() {
        return terminoPrevisto;
    }

    public void setTerminoPrevisto(final Date terminoPrevisto) {
        this.terminoPrevisto = terminoPrevisto;
    }

    public Integer getIdEmpregadoLogado() {
        return idEmpregadoLogado;
    }

    public void setIdEmpregadoLogado(final Integer idEmpregadoLogado) {
        this.idEmpregadoLogado = idEmpregadoLogado;
    }

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(final Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Integer getIdFluxoSelecionado() {
        return idFluxoSelecionado;
    }

    public void setIdFluxoSelecionado(final Integer idFluxoSelecionado) {
        this.idFluxoSelecionado = idFluxoSelecionado;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(final String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public Integer getIdTipoDemanda() {
        return idTipoDemanda;
    }

    public void setIdTipoDemanda(final Integer idTipoDemanda) {
        this.idTipoDemanda = idTipoDemanda;
    }

    public String getQtdeHorasStr() {
        return UtilFormatacao.formatDouble(qtdeHoras, 2);
    }

    public Double getQtdeHoras() {
        return qtdeHoras;
    }

    public void setQtdeHoras(final Double qtdeHoras) {
        this.qtdeHoras = qtdeHoras;
    }

    public Date getTerminoReal() {
        return terminoReal;
    }

    public void setTerminoReal(final Date terminoReal) {
        this.terminoReal = terminoReal;
    }

    public String getObjSerializado() {
        return objSerializado;
    }

    public void setObjSerializado(final String objSerializado) {
        this.objSerializado = objSerializado;
    }

    public String getNumeroOS() {
        return numeroOS;
    }

    public void setNumeroOS(final String numeroOS) {
        this.numeroOS = numeroOS;
    }

    public Integer getAnoOS() {
        return anoOS;
    }

    public void setAnoOS(final Integer anoOS) {
        this.anoOS = anoOS;
    }

}
