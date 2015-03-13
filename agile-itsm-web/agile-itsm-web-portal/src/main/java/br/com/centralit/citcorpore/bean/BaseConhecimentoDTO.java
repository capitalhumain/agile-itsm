/**
 * CentralIT - CITSmart
 */
package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.htmlparser.jericho.Source;

import org.apache.commons.lang3.StringUtils;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * DTO de BaseConhecimento.
 *
 * @author valdoilo.damasceno
 *
 */
public class BaseConhecimentoDTO extends BaseEntity {

    private static final long serialVersionUID = 3913360778332921835L;

    public static Integer CONHECIMENTO = 1;

    public static Integer EVENTO = 2;

    public static Integer MUDANCA = 3;

    public static Integer INCIDENTE = 4;

    public static Integer SERVICO = 5;

    public static Integer PROBLEMA = 6;

    public static String CONFIDENCIAL = "C";

    public static String PUBLICO = "P";

    public static String INTERNO = "I";

    public static String EMDESENHO = "DS";

    public static String EMREVISAO = "ERV";

    public static String REVISADO = "RV";

    public static String EMAVALIACAO = "EAV";

    public static String AVALIADO = "AV";

    private Integer idBaseConhecimento;

    private Integer idPasta;

    private Date dataInicio;

    private Date dataFim;

    private String titulo;

    private String conteudo;

    private String status;

    private Integer idBaseConhecimentoPai;

    private Date dataExpiracao;

    private Integer contadorCliques;

    private String linkDaPastaBaseConhecimento;

    private String palavrasChave;

    private List comentarios;

    private String media;

    private String votos;

    private String termoPesquisa;

    private String versao;

    private String termoPesquisaNota;

    private String nomePasta;

    private String semComentarios;

    private String acessado;

    private String ultimaVersao;

    private Integer idUsuarioAutor;

    private Integer idUsuarioAprovador;

    private String autor;

    private String aprovador;

    private Integer idUsuarioAcesso;

    private String nomeUsuarioAcesso;

    private String fonteReferencia;

    private String ultimoAcesso;

    private Timestamp dataHoraAcesso;

    private Date dataPublicacao;

    private Collection<ImportanciaConhecimentoUsuarioDTO> listImportanciaConhecimentoUsuario;

    private Collection<ImportanciaConhecimentoGrupoDTO> listImportanciaConhecimentoGrupo;

    private Collection<BaseConhecimentoRelacionadoDTO> listBaseConhecimentoRelacionado;

    private String justificativaObservacao;

    private Integer idConhecimentoRelacionado;

    private Integer idNotificacao;

    private String tituloNotificacao;

    private String tipoNotificacao;

    private ArrayList<NotificacaoUsuarioDTO> listaDeUsuarioNotificacao;

    private ArrayList<NotificacaoGrupoDTO> listaDeGrupoNotificacao;

    private String faq;

    private String origem;

    private Integer idUsuarioAutorPesquisa;

    private Integer idUsuarioAprovadorPesquisa;

    private Date dataInicioPesquisa;

    private Date dataPublicacaoPesquisa;

    private Integer grauImportancia;

    private String arquivado;

    private Integer idHistoricoBaseConhecimento;

    private String privacidade;

    private String situacao;

    private String ocultarConteudo;

    private Integer idItemConfiguracao;

    private Integer idProblema;

    private Integer idRequisicaoMudanca;

    private Integer idSolicitacaoServico;

    private Integer sequenciaBaseConhecimento;

    private String iframe;

    private String amDoc;

    private String gerenciamentoDisponibilidade;
    private String direitoAutoral;
    private String legislacao;

    private List<ItemConfiguracaoDTO> colItensICSerialize;
    private List<RequisicaoMudancaDTO> colItensMudanca;
    private List<ProblemaDTO> colItensProblema;
    private List<SolicitacaoServicoDTO> colItensIncidentes;
    private List<RequisicaoLiberacaoDTO> colItensLiberacao;

    private ArrayList<EventoMonitConhecimentoDTO> listEventoMonitoramento;

    private Date dataInicioPublicacao;
    private Date dataFimPublicacao;
    private String conteudoSemFormatacao;

    private Date dataInicioExpiracao;
    private Date dataFimExpiracao;

    private Date dataInicioAcesso;
    private Date dataFimAcesso;

    // Atributos para Relatório
    private Integer qtdPublicados = 0;
    private Integer qtdNaoPublicados = 0;
    private Integer qtdAcessados = 0;
    private Integer qtdAvaliados = 0;
    private Integer qtdExcluidos = 0;
    private Integer qtdArquivados = 0;
    private Integer qtdAtualizados = 0;
    private Integer qtdRestaurados = 0;
    private Integer tipoFaq = 0;
    private Integer qtdDocumentos = 0;
    private Integer qtdErroConhecido = 0;
    private String nomeUsuario = "";
    private Integer qtdConhecimentoPorUsuario = 0;
    private String nomeAprovador = "";
    private Integer qtdConhecimentoPorAprovador = 0;
    private String nomeOrigem = "";
    private Integer qtdConhecimentoPublicadoPorOrigem = 0;
    private Integer qtdConhecimentoNaoPublicadoPorOrigem = 0;
    private String vinculaConhecimentoServico = "";
    private String identificacao = "";
    private Integer idSolicitacaoServicoIncidente = 0;
    private Integer idSolicitacaoServicoRequisicao = 0;

    private List<BaseConhecimentoDTO> listaIncidente;
    private List<BaseConhecimentoDTO> listaRequisitos;
    private List<BaseConhecimentoDTO> listaProblema;
    private List<BaseConhecimentoDTO> listaMudanca;
    private List<BaseConhecimentoDTO> listaIC;
    private List<BaseConhecimentoDTO> listaServico;

    private String erroConhecido;

    // atributos liberação
    private Integer idRequisicaoLiberacao;

    /**
     * @return the colItensICSerialize
     */
    public List<ItemConfiguracaoDTO> getColItensICSerialize() {
        return colItensICSerialize;
    }

    /**
     * @param colItensICSerialize
     *            the colItensICSerialize to set
     */
    public void setColItensICSerialize(final List<ItemConfiguracaoDTO> colItensICSerialize) {
        this.colItensICSerialize = colItensICSerialize;
    }

    /**
     * @return the colItensMudanca
     */
    public List<RequisicaoMudancaDTO> getColItensMudanca() {
        return colItensMudanca;
    }

    /**
     * @param colItensMudanca
     *            the colItensMudanca to set
     */
    public void setColItensMudanca(final List<RequisicaoMudancaDTO> colItensMudanca) {
        this.colItensMudanca = colItensMudanca;
    }

    /**
     * @return the colItensProblema
     */
    public List<ProblemaDTO> getColItensProblema() {
        return colItensProblema;
    }

    /**
     * @param colItensProblema
     *            the colItensProblema to set
     */
    public void setColItensProblema(final List<ProblemaDTO> colItensProblema) {
        this.colItensProblema = colItensProblema;
    }

    /**
     * @return the colItensIncidentes
     */
    public List<SolicitacaoServicoDTO> getColItensIncidentes() {
        return colItensIncidentes;
    }

    /**
     * @param colItensIncidentes
     *            the colItensIncidentes to set
     */
    public void setColItensIncidentes(final List<SolicitacaoServicoDTO> colItensIncidentes) {
        this.colItensIncidentes = colItensIncidentes;
    }

    /**
     * @return the votos
     */
    public String getVotos() {
        return votos;
    }

    /**
     * @param votos
     *            the votos to set
     */
    public void setVotos(final String votos) {
        this.votos = votos;
    }

    /**
     * @return valor do atributo idBaseConhecimento.
     */
    public Integer getIdBaseConhecimento() {
        return idBaseConhecimento;
    }

    /**
     * Define valor do atributo idBaseConhecimento.
     *
     * @param idBaseConhecimento
     */
    public void setIdBaseConhecimento(final Integer idBaseConhecimento) {
        this.idBaseConhecimento = idBaseConhecimento;
    }

    /**
     * @return valor do atributo idPasta.
     */
    public Integer getIdPasta() {
        return idPasta;
    }

    /**
     * Define valor do atributo idPasta.
     *
     * @param idPasta
     */
    public void setIdPasta(final Integer idPasta) {
        this.idPasta = idPasta;
    }

    /**
     * @return valor do atributo dataInicio.
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * Define valor do atributo dataInicio.
     *
     * @param dataInicio
     */
    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return valor do atributo dataFim.
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * Define valor do atributo dataFim.
     *
     * @param dataFim
     */
    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return valor do atributo titulo.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define valor do atributo titulo.
     *
     * @param titulo
     */
    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return valor do atributo conteudo.
     */
    public String getConteudo() {
        return conteudo;
    }

    /**
     * Define valor do atributo conteudo.
     *
     * @param conteudo
     */
    public void setConteudo(final String conteudo) {

        if (conteudo != null && !StringUtils.isBlank(conteudo)) {
            this.setConteudoSemFormatacao(conteudo);
        }

        this.conteudo = conteudo;
    }

    /**
     * @return valor do atributo status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define valor do atributo status.
     *
     * @param status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * @return valor do atributo idBaseConhecimentoPai.
     */
    public Integer getIdBaseConhecimentoPai() {
        return idBaseConhecimentoPai;
    }

    /**
     * Define valor do atributo idBaseConhecimentoPai.
     *
     * @param idBaseConhecimentoPai
     */
    public void setIdBaseConhecimentoPai(final Integer idBaseConhecimentoPai) {
        this.idBaseConhecimentoPai = idBaseConhecimentoPai;
    }

    /**
     * @return valor do atributo dataExpiracao.
     */
    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    /**
     * Define valor do atributo dataExpiracao.
     *
     * @param dataExpiracao
     */
    public void setDataExpiracao(final Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    /**
     * @return valor do atributo contadorCliques.
     */
    public Integer getContadorCliques() {
        return contadorCliques;
    }

    /**
     * Define valor do atributo contadorCliques.
     *
     * @param contadorCliques
     */
    public void setContadorCliques(final Integer contadorCliques) {
        this.contadorCliques = contadorCliques;
    }

    /**
     * @return valor do atributo linkDaPastaBaseConhecimento.
     */
    public String getLinkDaPastaBaseConhecimento() {
        return linkDaPastaBaseConhecimento;
    }

    /**
     * Define valor do atributo linkDaPastaBaseConhecimento.
     *
     * @param linkDaPastaBaseConhecimento
     */
    public void setLinkDaPastaBaseConhecimento(final String linkDaPastaBaseConhecimento) {
        this.linkDaPastaBaseConhecimento = linkDaPastaBaseConhecimento;
    }

    public String getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(final String palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    /**
     * @return valor do atributo comentarios.
     */
    public List getComentarios() {
        return comentarios;
    }

    /**
     * Define valor do atributo comentarios.
     *
     * @param comentarios
     */
    public void setComentarios(final List comentarios) {
        this.comentarios = comentarios;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(final String media) {
        this.media = media;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(final String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }

    public String getTermoPesquisaNota() {
        return termoPesquisaNota;
    }

    public void setTermoPesquisaNota(final String termoPesquisaNota) {
        this.termoPesquisaNota = termoPesquisaNota;
    }

    /**
     * @return the nomePasta
     */
    public String getNomePasta() {
        return nomePasta;
    }

    /**
     * @param nomePasta
     *            the nomePasta to set
     */
    public void setNomePasta(final String nomePasta) {
        this.nomePasta = nomePasta;
    }

    /**
     * @return the semComentarios
     */
    public String getSemComentarios() {
        return semComentarios;
    }

    /**
     * @param semComentarios
     *            the semComentarios to set
     */
    public void setSemComentarios(final String semComentarios) {
        this.semComentarios = semComentarios;
    }

    /**
     * @return the acessado
     */
    public String getAcessado() {
        return acessado;
    }

    /**
     * @param acessado
     *            the acessado to set
     */
    public void setAcessado(final String acessado) {
        this.acessado = acessado;
    }

    /**
     * @return the ultimaVersao
     */
    public String getUltimaVersao() {
        return ultimaVersao;
    }

    /**
     * @param ultimaVersao
     *            the ultimaVersao to set
     */
    public void setUltimaVersao(final String ultimaVersao) {
        this.ultimaVersao = ultimaVersao;
    }

    /**
     * @return the idUsuarioAutor
     */
    public Integer getIdUsuarioAutor() {
        return idUsuarioAutor;
    }

    /**
     * @param idUsuarioAutor
     *            the idUsuarioAutor to set
     */
    public void setIdUsuarioAutor(final Integer idUsuarioAutor) {
        this.idUsuarioAutor = idUsuarioAutor;
    }

    /**
     * @return the idUsuarioAprovador
     */
    public Integer getIdUsuarioAprovador() {
        return idUsuarioAprovador;
    }

    /**
     * @param idUsuarioAprovador
     *            the idUsuarioAprovador to set
     */
    public void setIdUsuarioAprovador(final Integer idUsuarioAprovador) {
        this.idUsuarioAprovador = idUsuarioAprovador;
    }

    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor
     *            the autor to set
     */
    public void setAutor(final String autor) {
        this.autor = autor;
    }

    /**
     * @return the aprovadaPor
     */
    public String getAprovador() {
        return aprovador;
    }

    /**
     * @param aprovador
     *            the aprovadaPor to set
     */
    public void setAprovador(final String aprovador) {
        this.aprovador = aprovador;
    }

    /**
     * @return the idUsuarioAcesso
     */
    public Integer getIdUsuarioAcesso() {
        return idUsuarioAcesso;
    }

    /**
     * @param idUsuarioAcesso
     *            the idUsuarioAcesso to set
     */
    public void setIdUsuarioAcesso(final Integer idUsuarioAcesso) {
        this.idUsuarioAcesso = idUsuarioAcesso;
    }

    /**
     * @return the nomeUsuarioAcesso
     */
    public String getNomeUsuarioAcesso() {
        return nomeUsuarioAcesso;
    }

    /**
     * @param nomeUsuarioAcesso
     *            the nomeUsuarioAcesso to set
     */
    public void setNomeUsuarioAcesso(final String nomeUsuarioAcesso) {
        this.nomeUsuarioAcesso = nomeUsuarioAcesso;
    }

    /**
     * @return the fonteReferencia
     */
    public String getFonteReferencia() {
        return fonteReferencia;
    }

    /**
     * @param fonteReferencia
     *            the fonteReferencia to set
     */
    public void setFonteReferencia(final String fonteReferencia) {
        this.fonteReferencia = fonteReferencia;
    }

    /**
     * @return the ultimoAcesso
     */
    public String getUltimoAcesso() {
        return ultimoAcesso;
    }

    /**
     * @param ultimoAcesso
     *            the ultimoAcesso to set
     */
    public void setUltimoAcesso(final String ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public Timestamp getDataHoraAcesso() {
        return dataHoraAcesso;
    }

    public void setDataHoraAcesso(final Timestamp dataHoraAcesso) {
        this.dataHoraAcesso = dataHoraAcesso;
    }

    /**
     * @return the dataPublicacao
     */
    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    /**
     * @param dataPublicacao
     *            the dataPublicacao to set
     */
    public void setDataPublicacao(final Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    /**
     * @return the idNotificacao
     */
    public Integer getIdNotificacao() {
        return idNotificacao;
    }

    /**
     * @param idNotificacao
     *            the idNotificacao to set
     */
    public void setIdNotificacao(final Integer idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    /**
     * @return the listImportanciaConhecimentoUsuario
     */
    public Collection<ImportanciaConhecimentoUsuarioDTO> getListImportanciaConhecimentoUsuario() {
        return listImportanciaConhecimentoUsuario;
    }

    /**
     * @param listImportanciaConhecimentoUsuario
     *            the listImportanciaConhecimentoUsuario to set
     */
    public void setListImportanciaConhecimentoUsuario(final Collection<ImportanciaConhecimentoUsuarioDTO> listImportanciaConhecimentoUsuario) {
        this.listImportanciaConhecimentoUsuario = listImportanciaConhecimentoUsuario;
    }

    /**
     * @return the listImportanciaConhecimentoGrupo
     */
    public Collection<ImportanciaConhecimentoGrupoDTO> getListImportanciaConhecimentoGrupo() {
        return listImportanciaConhecimentoGrupo;
    }

    /**
     * @param listImportanciaConhecimentoGrupo
     *            the listImportanciaConhecimentoGrupo to set
     */
    public void setListImportanciaConhecimentoGrupo(final Collection<ImportanciaConhecimentoGrupoDTO> listImportanciaConhecimentoGrupo) {
        this.listImportanciaConhecimentoGrupo = listImportanciaConhecimentoGrupo;
    }

    /**
     * @return the justificativaObservacao
     */
    public String getJustificativaObservacao() {
        return justificativaObservacao;
    }

    /**
     * @param justificativaObservacao
     *            the justificativaObservacao to set
     */
    public void setJustificativaObservacao(final String justificativaObservacao) {
        this.justificativaObservacao = justificativaObservacao;
    }

    /**
     * @return the listBaseConhecimentoRelacionado
     */
    public Collection<BaseConhecimentoRelacionadoDTO> getListBaseConhecimentoRelacionado() {
        return listBaseConhecimentoRelacionado;
    }

    /**
     * @param listBaseConhecimentoRelacionado
     *            the listBaseConhecimentoRelacionado to set
     */
    public void setListBaseConhecimentoRelacionado(final Collection<BaseConhecimentoRelacionadoDTO> listBaseConhecimentoRelacionado) {
        this.listBaseConhecimentoRelacionado = listBaseConhecimentoRelacionado;
    }

    /**
     * @return the idConhecimentoRelacionado
     */
    public Integer getIdConhecimentoRelacionado() {
        return idConhecimentoRelacionado;
    }

    /**
     * @param idConhecimentoRelacionado
     *            the idConhecimentoRelacionado to set
     */
    public void setIdConhecimentoRelacionado(final Integer idConhecimentoRelacionado) {
        this.idConhecimentoRelacionado = idConhecimentoRelacionado;
    }

    /**
     * @return the tituloNotificacao
     */
    public String getTituloNotificacao() {
        return tituloNotificacao;
    }

    /**
     * @param tituloNotificacao
     *            the tituloNotificacao to set
     */
    public void setTituloNotificacao(final String tituloNotificacao) {
        this.tituloNotificacao = tituloNotificacao;
    }

    /**
     * @return the tipoNotificacao
     */
    public String getTipoNotificacao() {
        return tipoNotificacao;
    }

    /**
     * @param tipoNotificacao
     *            the tipoNotificacao to set
     */
    public void setTipoNotificacao(final String tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    /**
     * @return the listaDeUsuarioNotificacao
     */
    public ArrayList<NotificacaoUsuarioDTO> getListaDeUsuarioNotificacao() {
        return listaDeUsuarioNotificacao;
    }

    /**
     * @param listaDeUsuarioNotificacao
     *            the listaDeUsuarioNotificacao to set
     */
    public void setListaDeUsuarioNotificacao(final ArrayList<NotificacaoUsuarioDTO> listaDeUsuarioNotificacao) {
        this.listaDeUsuarioNotificacao = listaDeUsuarioNotificacao;
    }

    /**
     * @return the listaDeGrupoNotificacao
     */
    public ArrayList<NotificacaoGrupoDTO> getListaDeGrupoNotificacao() {
        return listaDeGrupoNotificacao;
    }

    /**
     * @param listaDeGrupoNotificacao
     *            the listaDeGrupoNotificacao to set
     */
    public void setListaDeGrupoNotificacao(final ArrayList<NotificacaoGrupoDTO> listaDeGrupoNotificacao) {
        this.listaDeGrupoNotificacao = listaDeGrupoNotificacao;
    }

    /**
     * @return the faq
     */
    public String getFaq() {
        return faq;
    }

    /**
     * @param faq
     *            the faq to set
     */
    public void setFaq(final String faq) {
        this.faq = faq;
    }

    /**
     * @return the origem
     */
    public String getOrigem() {
        return origem;
    }

    /**
     * @param origem
     *            the origem to set
     */
    public void setOrigem(final String origem) {
        this.origem = origem;
    }

    /**
     * @return the idUsuarioAutorPesquisa
     */
    public Integer getIdUsuarioAutorPesquisa() {
        return idUsuarioAutorPesquisa;
    }

    /**
     * @param idUsuarioAutorPesquisa
     *            the idUsuarioAutorPesquisa to set
     */
    public void setIdUsuarioAutorPesquisa(final Integer idUsuarioAutorPesquisa) {
        this.idUsuarioAutorPesquisa = idUsuarioAutorPesquisa;
    }

    /**
     * @return the idUsuarioAprovadorPesquisa
     */
    public Integer getIdUsuarioAprovadorPesquisa() {
        return idUsuarioAprovadorPesquisa;
    }

    /**
     * @param idUsuarioAprovadorPesquisa
     *            the idUsuarioAprovadorPesquisa to set
     */
    public void setIdUsuarioAprovadorPesquisa(final Integer idUsuarioAprovadorPesquisa) {
        this.idUsuarioAprovadorPesquisa = idUsuarioAprovadorPesquisa;
    }

    /**
     * @return the dataInicioPesquisa
     */
    public Date getDataInicioPesquisa() {
        return dataInicioPesquisa;
    }

    /**
     * @param dataInicioPesquisa
     *            the dataInicioPesquisa to set
     */
    public void setDataInicioPesquisa(final Date dataInicioPesquisa) {
        this.dataInicioPesquisa = dataInicioPesquisa;
    }

    /**
     * @return the dataPublicacaoPesquisa
     */
    public Date getDataPublicacaoPesquisa() {
        return dataPublicacaoPesquisa;
    }

    /**
     * @param dataPublicacaoPesquisa
     *            the dataPublicacaoPesquisa to set
     */
    public void setDataPublicacaoPesquisa(final Date dataPublicacaoPesquisa) {
        this.dataPublicacaoPesquisa = dataPublicacaoPesquisa;
    }

    /**
     * @return the arquivado
     */
    public String getArquivado() {
        return arquivado;
    }

    /**
     * @param arquivado
     *            the arquivado to set
     */
    public void setArquivado(final String arquivado) {
        this.arquivado = arquivado;
    }

    /**
     * @return the grauImportancia
     */
    public Integer getGrauImportancia() {
        return grauImportancia;
    }

    /**
     * @param grauImportancia
     *            the grauImportancia to set
     */
    public void setGrauImportancia(final Integer grauImportancia) {
        this.grauImportancia = grauImportancia;
    }

    /**
     * @return the idHistoricoBaseConhecimento
     */
    public Integer getIdHistoricoBaseConhecimento() {
        return idHistoricoBaseConhecimento;
    }

    /**
     * @param idHistoricoBaseConhecimento
     *            the idHistoricoBaseConhecimento to set
     */
    public void setIdHistoricoBaseConhecimento(final Integer idHistoricoBaseConhecimento) {
        this.idHistoricoBaseConhecimento = idHistoricoBaseConhecimento;
    }

    /**
     * @return the privacidade
     */
    public String getPrivacidade() {
        return privacidade;
    }

    /**
     * @param privacidade
     *            the privacidade to set
     */
    public void setPrivacidade(final String privacidade) {
        this.privacidade = privacidade;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao
     *            the situacao to set
     */
    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the ocultarConteudo
     */
    public String getOcultarConteudo() {
        return ocultarConteudo;
    }

    /**
     * @param ocultarConteudo
     *            the ocultarConteudo to set
     */
    public void setOcultarConteudo(final String ocultarConteudo) {
        this.ocultarConteudo = ocultarConteudo;
    }

    /**
     * @return the idItemConfiguracao
     */
    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    /**
     * @param idItemConfiguracao
     *            the idItemConfiguracao to set
     */
    public void setIdItemConfiguracao(final Integer idItemConfiguracao) {
        this.idItemConfiguracao = idItemConfiguracao;
    }

    /**
     * @return the idProblema
     */
    public Integer getIdProblema() {
        return idProblema;
    }

    /**
     * @param idProblema
     *            the idProblema to set
     */
    public void setIdProblema(final Integer idProblema) {
        this.idProblema = idProblema;
    }

    /**
     * @return the idRequisicaoMudanca
     */
    public Integer getIdRequisicaoMudanca() {
        return idRequisicaoMudanca;
    }

    /**
     * @param idRequisicaoMudanca
     *            the idRequisicaoMudanca to set
     */
    public void setIdRequisicaoMudanca(final Integer idRequisicaoMudanca) {
        this.idRequisicaoMudanca = idRequisicaoMudanca;
    }

    /**
     * @return the idSolicitacaoServico
     */
    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    /**
     * @param idSolicitacaoServico
     *            the idSolicitacaoServico to set
     */
    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public Integer getSequenciaBaseConhecimento() {
        return sequenciaBaseConhecimento;
    }

    public void setSequenciaBaseConhecimento(final Integer sequenciaBaseConhecimento) {
        this.sequenciaBaseConhecimento = sequenciaBaseConhecimento;
    }

    /**
     * @return the listEventoMonitoramento
     */
    public ArrayList<EventoMonitConhecimentoDTO> getListEventoMonitoramento() {
        return listEventoMonitoramento;
    }

    /**
     * @param listEventoMonitoramento
     *            the listEventoMonitoramento to set
     */
    public void setListEventoMonitoramento(final ArrayList<EventoMonitConhecimentoDTO> listEventoMonitoramento) {
        this.listEventoMonitoramento = listEventoMonitoramento;
    }

    public String getIframe() {
        return iframe;
    }

    public void setIframe(final String iframe) {
        this.iframe = iframe;
    }

    public String getAmDoc() {
        return amDoc;
    }

    public void setAmDoc(final String amDoc) {
        this.amDoc = amDoc;
    }

    public String getGerenciamentoDisponibilidade() {
        return gerenciamentoDisponibilidade;
    }

    public void setGerenciamentoDisponibilidade(final String gerenciamentoDisponibilidade) {
        this.gerenciamentoDisponibilidade = gerenciamentoDisponibilidade;
    }

    public String getDireitoAutoral() {
        return direitoAutoral;
    }

    public void setDireitoAutoral(final String direitoAutoral) {
        this.direitoAutoral = direitoAutoral;
    }

    public String getLegislacao() {
        return legislacao;
    }

    public void setLegislacao(final String legislacao) {
        this.legislacao = legislacao;
    }

    /**
     * @return the dataInicioPublicacao
     */
    public Date getDataInicioPublicacao() {
        return dataInicioPublicacao;
    }

    /**
     * @param dataInicioPublicacao
     *            the dataInicioPublicacao to set
     */
    public void setDataInicioPublicacao(final Date dataInicioPublicacao) {
        this.dataInicioPublicacao = dataInicioPublicacao;
    }

    /**
     * @return the dataFimPublicacao
     */
    public Date getDataFimPublicacao() {
        return dataFimPublicacao;
    }

    /**
     * @param dataFimPublicacao
     *            the dataFimPublicacao to set
     */
    public void setDataFimPublicacao(final Date dataFimPublicacao) {
        this.dataFimPublicacao = dataFimPublicacao;
    }

    /**
     * @return the conteudoSemFormatacao
     */
    public String getConteudoSemFormatacao() {

        if (conteudoSemFormatacao != null && !StringUtils.isBlank(conteudoSemFormatacao)) {
            final Source source = new Source(conteudoSemFormatacao);
            conteudoSemFormatacao = source.getTextExtractor().toString();
            return conteudoSemFormatacao;
        }

        return conteudoSemFormatacao;
    }

    /**
     * @param conteudoSemFormatacao
     *            the conteudoSemFormatacao to set
     */
    public void setConteudoSemFormatacao(final String conteudoSemFormatacao) {
        this.conteudoSemFormatacao = conteudoSemFormatacao;
    }

    public String getErroConhecido() {
        return erroConhecido;
    }

    public void setErroConhecido(final String erroConhecido) {
        this.erroConhecido = erroConhecido;
    }

    public Integer getQtdPublicados() {
        return qtdPublicados;
    }

    public void setQtdPublicados(final Integer qtdPublicados) {
        this.qtdPublicados = qtdPublicados;
    }

    public Integer getQtdNaoPublicados() {
        return qtdNaoPublicados;
    }

    public void setQtdNaoPublicados(final Integer qtdNaoPublicados) {
        this.qtdNaoPublicados = qtdNaoPublicados;
    }

    public Integer getQtdAcessados() {
        return qtdAcessados;
    }

    public void setQtdAcessados(final Integer qtdAcessados) {
        this.qtdAcessados = qtdAcessados;
    }

    public Integer getQtdAvaliados() {
        return qtdAvaliados;
    }

    public void setQtdAvaliados(final Integer qtdAvaliados) {
        this.qtdAvaliados = qtdAvaliados;
    }

    public Integer getQtdExcluidos() {
        return qtdExcluidos;
    }

    public void setQtdExcluidos(final Integer qtdExcluidos) {
        this.qtdExcluidos = qtdExcluidos;
    }

    public Integer getQtdArquivados() {
        return qtdArquivados;
    }

    public void setQtdArquivados(final Integer qtdArquivados) {
        this.qtdArquivados = qtdArquivados;
    }

    public Integer getQtdAtualizados() {
        return qtdAtualizados;
    }

    public void setQtdAtualizados(final Integer qtdAtualizados) {
        this.qtdAtualizados = qtdAtualizados;
    }

    public Integer getQtdRestaurados() {
        return qtdRestaurados;
    }

    public void setQtdRestaurados(final Integer qtdRestaurados) {
        this.qtdRestaurados = qtdRestaurados;
    }

    public Integer getTipoFaq() {
        return tipoFaq;
    }

    public void setTipoFaq(final Integer tipoFaq) {
        this.tipoFaq = tipoFaq;
    }

    public Integer getQtdDocumentos() {
        return qtdDocumentos;
    }

    public void setQtdDocumentos(final Integer qtdDocumentos) {
        this.qtdDocumentos = qtdDocumentos;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(final String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Integer getQtdConhecimentoPorUsuario() {
        return qtdConhecimentoPorUsuario;
    }

    public void setQtdConhecimentoPorUsuario(final Integer qtdConhecimentoPorUsuario) {
        this.qtdConhecimentoPorUsuario = qtdConhecimentoPorUsuario;
    }

    public Integer getQtdConhecimentoPorAprovador() {
        return qtdConhecimentoPorAprovador;
    }

    public void setQtdConhecimentoPorAprovador(final Integer qtdConhecimentoPorAprovador) {
        this.qtdConhecimentoPorAprovador = qtdConhecimentoPorAprovador;
    }

    public String getNomeAprovador() {
        return nomeAprovador;
    }

    public void setNomeAprovador(final String nomeAprovador) {
        this.nomeAprovador = nomeAprovador;
    }

    public Date getDataInicioExpiracao() {
        return dataInicioExpiracao;
    }

    public void setDataInicioExpiracao(final Date dataInicioExpiracao) {
        this.dataInicioExpiracao = dataInicioExpiracao;
    }

    public Date getDataFimExpiracao() {
        return dataFimExpiracao;
    }

    public void setDataFimExpiracao(final Date dataFimExpiracao) {
        this.dataFimExpiracao = dataFimExpiracao;
    }

    public Integer getQtdConhecimentoPublicadoPorOrigem() {
        return qtdConhecimentoPublicadoPorOrigem;
    }

    public void setQtdConhecimentoPublicadoPorOrigem(final Integer qtdConhecimentoPublicadoPorOrigem) {
        this.qtdConhecimentoPublicadoPorOrigem = qtdConhecimentoPublicadoPorOrigem;
    }

    public Integer getQtdConhecimentoNaoPublicadoPorOrigem() {
        return qtdConhecimentoNaoPublicadoPorOrigem;
    }

    public void setQtdConhecimentoNaoPublicadoPorOrigem(final Integer qtdConhecimentoNaoPublicadoPorOrigem) {
        this.qtdConhecimentoNaoPublicadoPorOrigem = qtdConhecimentoNaoPublicadoPorOrigem;
    }

    public String getNomeOrigem() {
        return nomeOrigem;
    }

    public void setNomeOrigem(final String nomeOrigem) {
        this.nomeOrigem = nomeOrigem;
    }

    public String getVinculaConhecimentoServico() {
        return vinculaConhecimentoServico;
    }

    public void setVinculaConhecimentoServico(final String vinculaConhecimentoServico) {
        this.vinculaConhecimentoServico = vinculaConhecimentoServico;
    }

    public Integer getIdSolicitacaoServicoIncidente() {
        return idSolicitacaoServicoIncidente;
    }

    public void setIdSolicitacaoServicoIncidente(final Integer idSolicitacaoServicoIncidente) {
        this.idSolicitacaoServicoIncidente = idSolicitacaoServicoIncidente;
    }

    public Integer getIdSolicitacaoServicoRequisicao() {
        return idSolicitacaoServicoRequisicao;
    }

    public void setIdSolicitacaoServicoRequisicao(final Integer idSolicitacaoServicoRequisicao) {
        this.idSolicitacaoServicoRequisicao = idSolicitacaoServicoRequisicao;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(final String identificacao) {
        this.identificacao = identificacao;
    }

    public List<BaseConhecimentoDTO> getListaIncidente() {
        return listaIncidente;
    }

    public void setListaIncidente(final List<BaseConhecimentoDTO> listaIncidente) {
        this.listaIncidente = listaIncidente;
    }

    public List<BaseConhecimentoDTO> getListaRequisitos() {
        return listaRequisitos;
    }

    public void setListaRequisitos(final List<BaseConhecimentoDTO> listaRequisitos) {
        this.listaRequisitos = listaRequisitos;
    }

    public List<BaseConhecimentoDTO> getListaProblema() {
        return listaProblema;
    }

    public void setListaProblema(final List<BaseConhecimentoDTO> listaProblema) {
        this.listaProblema = listaProblema;
    }

    public List<BaseConhecimentoDTO> getListaMudanca() {
        return listaMudanca;
    }

    public void setListaMudanca(final List<BaseConhecimentoDTO> listaMudanca) {
        this.listaMudanca = listaMudanca;
    }

    public List<BaseConhecimentoDTO> getListaIC() {
        return listaIC;
    }

    public void setListaIC(final List<BaseConhecimentoDTO> listaIC) {
        this.listaIC = listaIC;
    }

    public List<BaseConhecimentoDTO> getListaServico() {
        return listaServico;
    }

    public void setListaServico(final List<BaseConhecimentoDTO> listaServico) {
        this.listaServico = listaServico;
    }

    public Integer getIdRequisicaoLiberacao() {
        return idRequisicaoLiberacao;
    }

    public void setIdRequisicaoLiberacao(final Integer idRequisicaoLiberacao) {
        this.idRequisicaoLiberacao = idRequisicaoLiberacao;
    }

    public List<RequisicaoLiberacaoDTO> getColItensLiberacao() {
        return colItensLiberacao;
    }

    public void setColItensLiberacao(final List<RequisicaoLiberacaoDTO> colItensLiberacao) {
        this.colItensLiberacao = colItensLiberacao;
    }

    public Integer getQtdErroConhecido() {
        return qtdErroConhecido;
    }

    public void setQtdErroConhecido(final Integer qtdConhecimentoPorErroConhecido) {
        qtdErroConhecido = qtdConhecimentoPorErroConhecido;
    }

    public Date getDataInicioAcesso() {
        return dataInicioAcesso;
    }

    public void setDataInicioAcesso(final Date dataInicioAcesso) {
        this.dataInicioAcesso = dataInicioAcesso;
    }

    public Date getDataFimAcesso() {
        return dataFimAcesso;
    }

    public void setDataFimAcesso(final Date dataFimAcesso) {
        this.dataFimAcesso = dataFimAcesso;
    }

    /**
     * Uma Base de conhecimento ativa é uma base publicada, não arquivada e não excluída.
     *
     * @Author euler.ramos
     */
    public boolean ativa() {
        // Base de conhecimento publicada, não arquivada e não excluída.
        boolean resultado;
        final String publicada = this.getStatus() == null ? "N" : this.getStatus();
        final String arquivada = this.getArquivado() == null ? "N" : this.getArquivado();
        resultado = publicada.equalsIgnoreCase("S") && arquivada.equalsIgnoreCase("N") && this.getDataFim() == null;
        return resultado;
    }

}
