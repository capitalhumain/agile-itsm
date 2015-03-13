package br.com.centralit.citquestionario.bean;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang3.StringEscapeUtils;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.UtilFormatacao;
import br.com.citframework.util.UtilHTML;
import br.com.citframework.util.WebUtil;

public class QuestaoQuestionarioDTO extends BaseEntity {

    private static final long serialVersionUID = 6249418505804531221L;

    private Integer idQuestaoQuestionario;
    private Integer idGrupoQuestionario;
    private Integer idQuestaoAgrupadora;
    private Integer idQuestaoOrigem;
    private String tituloQuestaoQuestionario;
    private String tipoQuestao;
    private Integer sequenciaQuestao;
    private String valorDefault;
    private String textoInicial;
    private String tipo;
    private String infoResposta;
    private String unidade;
    private String valoresReferencia;
    private String obrigatoria;
    private Integer tamanho;
    private Integer decimais;
    private String ponderada;
    private Integer qtdeLinhas;
    private Integer qtdeColunas;
    private String cabecalhoLinhas;
    private String cabecalhoColunas;
    private String nomeListagem;
    private String ultimoValor;

    private Double valorPermitido1;
    private Double valorPermitido2;

    private Integer idImagem;

    private Collection colOpcoesResposta;
    private String serializeOpcoesResposta;

    private Collection colQuestoesAgrupadas;
    private String serializeQuestoesAgrupadas;
    private Collection colRespostas;
    private String serializeRespostas;

    private Collection colCabecalhosLinha;
    private String serializeCabecalhosLinha;
    private Collection colCabecalhosColuna;
    private String serializeCabecalhosColuna;

    private Integer idRespostaItemQuestionario;

    private RespostaItemQuestionarioDTO respostaItemDto;

    private String idTabela;
    private String idControleQuestao;
    private Integer sequencialResposta;

    private Integer idSubQuestionario;
    private String abaResultSubForm;
    private String sigla;
    private String imprime;
    private String calculada;
    private String editavel;
    private String nomeGrupo;

    public boolean agrupada;

    private Date dataRegistro;

    private Integer idSolicitacaoServico;

    public QuestaoQuestionarioDTO() {
        this.setAgrupada(false);
        this.setSequencialResposta(null);
    }

    public boolean isAgrupada() {
        return agrupada;
    }

    public void setAgrupada(final boolean agrupada) {
        this.agrupada = agrupada;
    }

    public Integer getIdGrupoQuestionario() {
        return idGrupoQuestionario;
    }

    public void setIdGrupoQuestionario(final Integer idGrupoQuestionario) {
        this.idGrupoQuestionario = idGrupoQuestionario;
    }

    public Integer getIdQuestaoQuestionario() {
        return idQuestaoQuestionario;
    }

    public void setIdQuestaoQuestionario(final Integer idQuestaoQuestionario) {
        this.idQuestaoQuestionario = idQuestaoQuestionario;
    }

    public Integer getIdQuestaoAgrupadora() {
        return idQuestaoAgrupadora;
    }

    public void setIdQuestaoAgrupadora(final Integer idQuestaoAgrupadora) {
        this.idQuestaoAgrupadora = idQuestaoAgrupadora;
    }

    public Integer getIdQuestaoOrigem() {
        return idQuestaoOrigem;
    }

    public void setIdQuestaoOrigem(final Integer idQuestaoOrigem) {
        this.idQuestaoOrigem = idQuestaoOrigem;
    }

    public Integer getSequenciaQuestao() {
        return sequenciaQuestao;
    }

    public void setSequenciaQuestao(final Integer sequenciaQuestao) {
        this.sequenciaQuestao = sequenciaQuestao;
    }

    public String getTipoQuestao() {
        return tipoQuestao;
    }

    public void setTipoQuestao(final String tipoQuestao) {
        this.tipoQuestao = tipoQuestao;
    }

    public String getTituloQuestaoQuestionarioNoEnter() {
        if (tituloQuestaoQuestionario == null) {
            return "";
        }
        tituloQuestaoQuestionario = tituloQuestaoQuestionario.replaceAll("\n", "");
        tituloQuestaoQuestionario = tituloQuestaoQuestionario.replaceAll("\r", "");
        tituloQuestaoQuestionario = tituloQuestaoQuestionario.replaceAll("\\\n", "");
        tituloQuestaoQuestionario = tituloQuestaoQuestionario.replaceAll("\\\r", "");
        tituloQuestaoQuestionario = StringEscapeUtils.escapeEcmaScript(tituloQuestaoQuestionario);

        return tituloQuestaoQuestionario;
    }

    public String getTituloQuestaoQuestionario() {
        return tituloQuestaoQuestionario;
    }

    public String getTituloQuestaoQuestionarioSemFmt() {
        if (tituloQuestaoQuestionario == null) {
            return "";
        }

        return UtilHTML.retiraFormatacaoHTML(tituloQuestaoQuestionario);
    }

    public void setTituloQuestaoQuestionario(final String tituloQuestaoQuestionario) {
        this.tituloQuestaoQuestionario = tituloQuestaoQuestionario;
    }

    public String getValorDefault() {
        return valorDefault;
    }

    public void setValorDefault(final String valorDefault) {
        this.valorDefault = valorDefault;
    }

    public Integer getDecimais() {
        return decimais;
    }

    public void setDecimais(final Integer decimais) {
        this.decimais = decimais;
    }

    public Integer getTamanho() {
        return tamanho;
    }

    public void setTamanho(final Integer tamanho) {
        this.tamanho = tamanho;
    }

    public String getTextoInicialNoEnter() {
        if (textoInicial == null) {
            return "";
        }
        textoInicial = textoInicial.replaceAll("\n", "");
        textoInicial = textoInicial.replaceAll("\r", "");
        textoInicial = textoInicial.replaceAll("\\\n", "");
        textoInicial = textoInicial.replaceAll("\\\r", "");
        return textoInicial;
    }

    public String getValorDefaultNoEnter() {
        if (valorDefault == null) {
            return "";
        }
        valorDefault = valorDefault.replaceAll("\n", "");
        valorDefault = valorDefault.replaceAll("\r", "");
        valorDefault = valorDefault.replaceAll("\\\n", "");
        valorDefault = valorDefault.replaceAll("\\\r", "");
        return valorDefault;
    }

    public String getTextoInicial() {
        return textoInicial;
    }

    public void setTextoInicial(final String textoInicial) {
        this.textoInicial = textoInicial;
    }

    public String getSerializeOpcoesResposta() {
        if (serializeOpcoesResposta == null) {
            try {
                serializeOpcoesResposta = WebUtil.serializeObjects(this.getColOpcoesResposta());
            } catch (final Exception e) {
                serializeOpcoesResposta = "";
                e.printStackTrace();
            }
        }
        return serializeOpcoesResposta;
    }

    public void setSerializeOpcoesResposta(final String serializeOpcoesResposta) {
        this.serializeOpcoesResposta = serializeOpcoesResposta;
    }

    public Collection getColOpcoesResposta() {
        return colOpcoesResposta;
    }

    public void setColOpcoesResposta(final Collection colOpcoesResposta) {
        this.colOpcoesResposta = colOpcoesResposta;
    }

    public Collection getColQuestoesAgrupadas() {
        return colQuestoesAgrupadas;
    }

    public void setColQuestoesAgrupadas(final Collection colQuestoesAgrupadas) {
        this.colQuestoesAgrupadas = colQuestoesAgrupadas;
    }

    public String getSerializeQuestoesAgrupadas() {
        if (serializeQuestoesAgrupadas == null) {
            try {
                serializeQuestoesAgrupadas = WebUtil.serializeObjects(this.getColQuestoesAgrupadas());
            } catch (final Exception e) {
                serializeQuestoesAgrupadas = "";
                e.printStackTrace();
            }
        }
        return serializeQuestoesAgrupadas;
    }

    public void setSerializeQuestoesAgrupadas(final String serializeQuestoesAgrupadas) {
        this.serializeQuestoesAgrupadas = serializeQuestoesAgrupadas;
    }

    public Collection getColCabecalhosLinha() {
        return colCabecalhosLinha;
    }

    public Collection getColRespostas() {
        return colRespostas;
    }

    public void setColRespostas(final Collection colRespostas) {
        this.colRespostas = colRespostas;
    }

    public String getSerializeRespostas() {
        return serializeRespostas;
    }

    public void setSerializeRespostas(final String serializeRespostas) {
        this.serializeRespostas = serializeRespostas;
    }

    public void setColCabecalhosLinha(final Collection colCabecalhosLinha) {
        this.colCabecalhosLinha = colCabecalhosLinha;
    }

    public String getSerializeCabecalhosLinha() {
        if (serializeCabecalhosLinha == null) {
            try {
                serializeCabecalhosLinha = WebUtil.serializeObjects(this.getColCabecalhosLinha());
            } catch (final Exception e) {
                serializeCabecalhosLinha = "";
                e.printStackTrace();
            }
        }
        return serializeCabecalhosLinha;
    }

    public void setSerializeCabecalhosLinha(final String serializeCabecalhosLinha) {
        this.serializeCabecalhosLinha = serializeCabecalhosLinha;
    }

    public Collection getColCabecalhosColuna() {
        return colCabecalhosColuna;
    }

    public void setColCabecalhosColuna(final Collection colCabecalhosColuna) {
        this.colCabecalhosColuna = colCabecalhosColuna;
    }

    public String getSerializeCabecalhosColuna() {
        if (serializeCabecalhosColuna == null) {
            try {
                serializeCabecalhosColuna = WebUtil.serializeObjects(this.getColCabecalhosColuna());
            } catch (final Exception e) {
                serializeCabecalhosColuna = "";
                e.printStackTrace();
            }
        }
        return serializeCabecalhosColuna;
    }

    public void setSerializeCabecalhosColuna(final String serializeCabecalhosColuna) {
        this.serializeCabecalhosColuna = serializeCabecalhosColuna;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public String getInfoResposta() {
        return infoResposta;
    }

    public void setInfoResposta(final String infoResposta) {
        this.infoResposta = infoResposta;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(final String unidade) {
        this.unidade = unidade;
    }

    public String getValoresReferencia() {
        return valoresReferencia;
    }

    public void setValoresReferencia(final String valoresReferencia) {
        this.valoresReferencia = valoresReferencia;
    }

    public String getObrigatoria() {
        return obrigatoria;
    }

    public void setObrigatoria(final String obrigatoria) {
        this.obrigatoria = obrigatoria;
    }

    public String getPonderada() {
        return ponderada;
    }

    public void setPonderada(final String ponderada) {
        this.ponderada = ponderada;
    }

    public Integer getQtdeLinhas() {
        return qtdeLinhas;
    }

    public void setQtdeLinhas(final Integer qtdeLinhas) {
        this.qtdeLinhas = qtdeLinhas;
    }

    public Integer getQtdeColunas() {
        return qtdeColunas;
    }

    public void setQtdeColunas(final Integer qtdeColunas) {
        this.qtdeColunas = qtdeColunas;
    }

    public String getCabecalhoLinhas() {
        return cabecalhoLinhas;
    }

    public void setCabecalhoLinhas(final String cabecalhoLinhas) {
        this.cabecalhoLinhas = cabecalhoLinhas;
    }

    public String getCabecalhoColunas() {
        return cabecalhoColunas;
    }

    public void setCabecalhoColunas(final String cabecalhoColunas) {
        this.cabecalhoColunas = cabecalhoColunas;
    }

    public RespostaItemQuestionarioDTO getRespostaItemDto() {
        return respostaItemDto;
    }

    public void setRespostaItemDto(final RespostaItemQuestionarioDTO respostaItemDto) {
        this.respostaItemDto = respostaItemDto;
    }

    public String getIdTabela() {
        return idTabela;
    }

    public void setIdTabela(final String idTabela) {
        this.idTabela = idTabela;
    }

    public String getIdControleQuestao() {
        return idControleQuestao;
    }

    public void setIdControleQuestao(final String idControleQuestao) {
        this.idControleQuestao = idControleQuestao;
    }

    public RespostaItemQuestionarioDTO obtemRespostaItemDto(final Integer sequencialResposta) {
        RespostaItemQuestionarioDTO resp = null;
        if (sequencialResposta == null) {
            resp = respostaItemDto;
        } else {
            if (colRespostas != null && sequencialResposta > 0) {
                for (final Iterator itResp = colRespostas.iterator(); itResp.hasNext();) {
                    final RespostaItemQuestionarioDTO respAux = (RespostaItemQuestionarioDTO) itResp.next();
                    if (respAux.getSequencialResposta() != null && respAux.getSequencialResposta().intValue() == sequencialResposta.intValue()) {
                        resp = respAux;
                        break;
                    }
                }
            }
        }
        return resp;
    }

    public String getNomeListagem() {
        return nomeListagem;
    }

    public void setNomeListagem(final String nomeListagem) {
        this.nomeListagem = nomeListagem;
    }

    public void copiaValoresAtributos(final QuestaoQuestionarioDTO questaoOrigem) throws Exception {
        this.setDecimais(questaoOrigem.getDecimais());
        this.setInfoResposta(questaoOrigem.getInfoResposta());
        this.setObrigatoria(questaoOrigem.getObrigatoria());
        this.setPonderada(questaoOrigem.getPonderada());
        this.setQtdeColunas(questaoOrigem.getQtdeColunas());
        this.setQtdeLinhas(questaoOrigem.getQtdeLinhas());
        this.setTamanho(questaoOrigem.getTamanho());
        this.setTextoInicial(questaoOrigem.getTextoInicial());
        this.setTipoQuestao(questaoOrigem.getTipoQuestao());
        this.setTituloQuestaoQuestionario(questaoOrigem.getTituloQuestaoQuestionarioSemFmt());
        this.setUnidade(questaoOrigem.getUnidade());
        this.setValorDefault(questaoOrigem.getValorDefault());
        this.setValoresReferencia(questaoOrigem.getValoresReferencia());
        this.setCabecalhoColunas(questaoOrigem.getCabecalhoColunas());
        this.setCabecalhoLinhas(questaoOrigem.getCabecalhoLinhas());
        this.setNomeListagem(questaoOrigem.getNomeListagem());
        this.setUltimoValor(questaoOrigem.getUltimoValor());
        this.setIdSubQuestionario(questaoOrigem.getIdSubQuestionario());
        this.setAbaResultSubForm(questaoOrigem.getAbaResultSubForm());
        this.setSigla(questaoOrigem.getSigla());
        this.setImprime(questaoOrigem.getImprime());
        this.setCalculada(questaoOrigem.getCalculada());
    }

    public Integer getSequencialResposta() {
        return sequencialResposta;
    }

    public void setSequencialResposta(final Integer sequencialResposta) {
        this.sequencialResposta = sequencialResposta;
    }

    public String getUltimoValor() {
        return ultimoValor;
    }

    public void setUltimoValor(final String ultimoValor) {
        this.ultimoValor = ultimoValor;
    }

    public Integer getIdSubQuestionario() {
        return idSubQuestionario;
    }

    public void setIdSubQuestionario(final Integer idSubQuestionario) {
        this.idSubQuestionario = idSubQuestionario;
    }

    public String getAbaResultSubForm() {
        return abaResultSubForm;
    }

    public void setAbaResultSubForm(final String abaResultSubForm) {
        this.abaResultSubForm = abaResultSubForm;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(final String sigla) {
        this.sigla = sigla;
    }

    public String getImprime() {
        return imprime;
    }

    public void setImprime(final String imprime) {
        this.imprime = imprime;
    }

    public String getCalculada() {
        return calculada;
    }

    public void setCalculada(final String calculada) {
        this.calculada = calculada;
    }

    private boolean isInCollection(final Integer idValor, final Collection colVerificar) {
        if (colVerificar == null) {
            return false;
        }
        for (final Iterator it = colVerificar.iterator(); it.hasNext();) {
            final RespostaItemQuestionarioOpcoesDTO respItemQuestDto = (RespostaItemQuestionarioOpcoesDTO) it.next();
            if (respItemQuestDto.getIdOpcaoRespostaQuestionario().intValue() == idValor.intValue()) {
                return true;
            }
        }
        return false;
    }

    public String obtemTituloOpcaoResposta(final Integer sequencialResposta) {
        String value = "";
        String sep = "";
        if (this.getColOpcoesResposta() != null) {
            for (final Iterator itOpcResp = this.getColOpcoesResposta().iterator(); itOpcResp.hasNext();) {
                final OpcaoRespostaQuestionarioDTO opcRespDto = (OpcaoRespostaQuestionarioDTO) itOpcResp.next();
                final RespostaItemQuestionarioDTO resposta = this.obtemRespostaItemDto(sequencialResposta);

                if (resposta != null) {
                    if (this.isInCollection(opcRespDto.getIdOpcaoRespostaQuestionario(), resposta.getColOpcoesResposta())) {
                        value += sep + opcRespDto.getTitulo().replaceAll("<br>", "");;
                        sep = ", ";
                    }
                }
            }
        }
        return value;
    }

    public String getEditavel() {
        return editavel;
    }

    public void setEditavel(final String editavel) {
        this.editavel = editavel;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(final String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public Double getValorPermitido1() {
        return valorPermitido1;
    }

    public String getValorPermitido1Str() {
        if (valorPermitido1 == null) {
            return "0,000";
        }
        return UtilFormatacao.formatDouble(valorPermitido1, 3);
    }

    public void setValorPermitido1(final Double valorPermitido1) {
        this.valorPermitido1 = valorPermitido1;
    }

    public Double getValorPermitido2() {
        return valorPermitido2;
    }

    public String getValorPermitido2Str() {
        if (valorPermitido2 == null) {
            return "0,000";
        }
        return UtilFormatacao.formatDouble(valorPermitido2, 3);
    }

    public void setValorPermitido2(final Double valorPermitido2) {
        this.valorPermitido2 = valorPermitido2;
    }

    public Integer getIdRespostaItemQuestionario() {
        return idRespostaItemQuestionario;
    }

    public void setIdRespostaItemQuestionario(final Integer idRespostaItemQuestionario) {
        this.idRespostaItemQuestionario = idRespostaItemQuestionario;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(final Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Integer getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(final Integer idImagem) {
        this.idImagem = idImagem;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

}
