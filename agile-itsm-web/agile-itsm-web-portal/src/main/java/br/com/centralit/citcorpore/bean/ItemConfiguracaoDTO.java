package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.citframework.util.DateAdapter;
import br.com.citframework.util.DateTimeAdapter;
import br.com.citframework.util.UtilStrings;

@SuppressWarnings("rawtypes")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ItemConfiguracao")
public class ItemConfiguracaoDTO extends BaseEntity {

    private static final long serialVersionUID = 5116065110323539062L;

    private Integer idItemConfiguracao;
    private String identificacao;
    private String familia;
    private String classe;
    private String localidade;
    private Integer status;
    private String sistemaOperacional;
    private String versao;
    private Integer criticidade;
    private String numeroSerie;
    private Timestamp dataInicioHistorico;
    private Timestamp dataFimHistorico;
    private String grupoTrabalho;
    private String tipoMembroDominio;
    private String usuario;
    private String processador;
    private String softwares;
    private String processar;

    @XmlElement(name = "dataExpiracao")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dataExpiracao;

    private List<ItemConfiguracaoDTO> listItemConfiguracao;
    private Integer idIncidente;
    private Integer idMudanca;
    private Integer idProblema;
    private Integer idProprietario;
    private String opcao;
    private String nomeItemConfiguracaoPai;
    private Integer idItemConfiguracaoPai;
    private Integer idTipoItemConfiguracao;

    @XmlElement(name = "dataInicio")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dataInicio;

    @XmlElement(name = "dataFim")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dataFim;

    private List tipoItemConfiguracao;
    private TipoItemConfiguracaoDTO tipoItemConfiguracaoSerializadas;
    private String nomeTipoItemConfiguracao;
    private String nomeGrupoItemConfiguracao;
    private Integer idGrupoItemConfiguracao;
    private String nomeUsuario;
    private String numeroIncidente;
    private String numeroProblema;
    private String numeroMudanca;
    private String emailGrupoItemConfiguracao;
    private Integer idMidiaSoftware;
    private String nomeMidia;
    private Integer sequenciaIC;
    private String urgencia;
    private String impacto;
    private Integer idBaseConhecimento;
    private Integer quantidade;
    private List<ValorDTO> valores;
    private Integer idContrato;
    private String ativoFixo;
    private Integer idResponsavel;
    private String nomeResponsavel;

    private HistoricoItemConfiguracaoDTO historicoItemConfiguracaoDTO;

    private String tipoVinculo;
    private String identificacaoPai;
    private List<CaracteristicaDTO> listCaracteristicas;
    private String imagem;
    private String midiaSoftwareChavesSerealizadas;
    private List<MidiaSoftwareChaveDTO> midiaSoftwareChaves;
    private String contem;
    private String duplicado;

    private Timestamp datahoradesinstalacao;

    @XmlElement(name = "dtUltimaCaptura")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private Timestamp dtUltimaCaptura;
    private String identificacaoPadrao;

    private Integer idLiberacao;
    private String tituloLiberacao;

    private String impactoUrgencia;

    /**
     * Parâmetros criados para iniciativa 397
     */
    private String nome;
    private String informacoesAdicionais;

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(final String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    /**
     * Variáveis para a iniciativa 396
     *
     * @author thyen.chang
     */
    private Integer idGrupoResponsavel;
    private String tipoResponsavel;

    public String getTipoResponsavel() {
        return tipoResponsavel;
    }

    public void setTipoResponsavel(final String tipoResponsavel) {
        this.tipoResponsavel = tipoResponsavel;
    }

    public Integer getIdGrupoResponsavel() {
        return idGrupoResponsavel;
    }

    public void setIdGrupoResponsavel(final Integer idGrupoResponsavel) {
        this.idGrupoResponsavel = idGrupoResponsavel;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(final String imagem) {
        this.imagem = imagem;
    }

    /**
     * @return Retorna o valor de idItemConfiguracao.
     */
    public Integer getIdItemConfiguracao() {
        return idItemConfiguracao;
    }

    /**
     * @param pIdItemConfiguracao
     *            modifica o atributo idItemConfiguracao.
     */
    public void setIdItemConfiguracao(final Integer pIdItemConfiguracao) {
        idItemConfiguracao = pIdItemConfiguracao;
    }

    /**
     * @return Retorna o valor de opcao.
     */
    public String getOpcao() {
        return opcao;
    }

    /**
     * @param pOpcao
     *            modifica o atributo opcao.
     */
    public void setOpcao(final String pOpcao) {
        opcao = pOpcao;
    }

    public String getIdentificacaoStatus() {
        String str = "";
        if (this.getStatus() == null) {
            str = identificacao + " - " + Enumerados.StatusIC.ATIVADO.getDescricao();
        } else {
            str = identificacao + " - " + Enumerados.StatusIC.getStatus(this.getStatus()).getDescricao();
        }
        // if (this.getImpacto() != null){
        // str += " (Impact: " + this.getImpactoStr() + ")";
        // }
        // if (this.getUrgencia() != null){
        // str += " (Urgency: " + this.getUrgenciaStr() + ")";
        // }
        // return str;
        return str + impactoUrgencia;
    }

    //
    // public String getImpactoStr() {
    // if (this.getImpacto() != null){
    // if ("B".equalsIgnoreCase(this.getImpacto())){
    // return "Low";
    // }
    // if ("M".equalsIgnoreCase(this.getImpacto())){
    // return "Medium";
    // }
    // if ("A".equalsIgnoreCase(this.getImpacto()) || "H".equalsIgnoreCase(this.getImpacto())){
    // return "High";
    // }
    // }
    // return "";
    // }
    // public String getUrgenciaStr() {
    // if (this.getUrgencia() != null){
    // if ("B".equalsIgnoreCase(this.getUrgencia())){
    // return "Low";
    // }
    // if ("M".equalsIgnoreCase(this.getUrgencia())){
    // return "Medium";
    // }
    // if ("A".equalsIgnoreCase(this.getUrgencia()) || "H".equalsIgnoreCase(this.getUrgencia())){
    // return "High";
    // }
    // }
    // return "";
    // }

    /**
     * @return Retorna o valor de identificacao.
     */
    public String getIdentificacao() {
        if (identificacao != null) {
            return identificacao.trim();
        }
        return identificacao;
    }

    /**
     * @param pIdentificacao
     *            modifica o atributo identificacao.
     */
    public void setIdentificacao(final String pIdentificacao) {
        identificacao = UtilStrings.limitarTamanho(pIdentificacao, 400, true);
    }

    /**
     * @return Retorna o valor de idItemConfiguracaoPai.
     */
    public Integer getIdItemConfiguracaoPai() {
        return idItemConfiguracaoPai;
    }

    /**
     * @param pIdItemConfiguracaoPai
     *            modifica o atributo idItemConfiguracaoPai.
     */
    public void setIdItemConfiguracaoPai(final Integer pIdItemConfiguracaoPai) {
        idItemConfiguracaoPai = pIdItemConfiguracaoPai;
    }

    /**
     * @return Retorna o valor de idTipoItemConfiguracao.
     */
    public Integer getIdTipoItemConfiguracao() {
        return idTipoItemConfiguracao;
    }

    /**
     * @param pIdTipoItemConfiguracao
     *            modifica o atributo idTipoItemConfiguracao.
     */
    public void setIdTipoItemConfiguracao(final Integer pIdTipoItemConfiguracao) {
        idTipoItemConfiguracao = pIdTipoItemConfiguracao;
    }

    /**
     * @return Retorna o valor de dataInicio.
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param pDataInicio
     *            modifica o atributo dataInicio.
     */
    public void setDataInicio(final Date pDataInicio) {
        dataInicio = pDataInicio;
    }

    /**
     * @return Retorna o valor de dataFim.
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param pDataFim
     *            modifica o atributo dataFim.
     */
    public void setDataFim(final Date pDataFim) {
        dataFim = pDataFim;
    }

    /**
     * @return Retorna o valor de nomeItemConfiguracaoPai.
     */
    public String getNomeItemConfiguracaoPai() {
        return nomeItemConfiguracaoPai;
    }

    /**
     * @param pNomeItemConfiguracaoPai
     *            modifica o atributo nomeItemConfiguracaoPai.
     */
    public void setNomeItemConfiguracaoPai(final String pNomeItemConfiguracaoPai) {
        nomeItemConfiguracaoPai = pNomeItemConfiguracaoPai;
    }

    /**
     * @return Retorna o valor de tipoItemConfiguracao.
     */
    public List getTipoItemConfiguracao() {
        return tipoItemConfiguracao;
    }

    /**
     * @param pTipoItemConfiguracao
     *            modifica o atributo tipoItemConfiguracao.
     */
    public void setTipoItemConfiguracao(final List pTipoItemConfiguracao) {
        tipoItemConfiguracao = pTipoItemConfiguracao;
    }

    /**
     * @return Retorna o valor de tipoItemConfiguracaoSerializadas.
     */
    public TipoItemConfiguracaoDTO getTipoItemConfiguracaoSerializadas() {
        return tipoItemConfiguracaoSerializadas;
    }

    /**
     * @param pTipoItemConfiguracaoSerializadas
     *            modifica o atributo tipoItemConfiguracaoSerializadas.
     */
    public void setTipoItemConfiguracaoSerializadas(final TipoItemConfiguracaoDTO pTipoItemConfiguracaoSerializadas) {
        tipoItemConfiguracaoSerializadas = pTipoItemConfiguracaoSerializadas;
    }

    /**
     * @return Retorna o valor de nomeTipoItemConfiguracao.
     */
    public String getNomeTipoItemConfiguracao() {
        return nomeTipoItemConfiguracao;
    }

    /**
     * @param pNomeTipoItemConfiguracao
     *            modifica o atributo nomeTipoItemConfiguracao.
     */
    public void setNomeTipoItemConfiguracao(final String pNomeTipoItemConfiguracao) {
        nomeTipoItemConfiguracao = pNomeTipoItemConfiguracao;
    }

    /**
     * @return Retorna o valor de grupoItemConfiguracao.
     */
    public String getNomeGrupoItemConfiguracao() {
        return nomeGrupoItemConfiguracao;
    }

    /**
     * @param pNomeGrupoItemConfiguracao
     *            modifica o atributo nomeGrupoItemConfiguracao.
     */
    public void setNomeGrupoItemConfiguracao(final String pNomeGrupoItemConfiguracao) {
        nomeGrupoItemConfiguracao = pNomeGrupoItemConfiguracao;
    }

    /**
     * @return Retorna o valor de idGrupoItemConfiguracao.
     */
    public Integer getIdGrupoItemConfiguracao() {
        return idGrupoItemConfiguracao;
    }

    /**
     * @param pIdGrupoItemConfiguracao
     *            modifica o atributo idGrupoItemConfiguracao.
     */
    public void setIdGrupoItemConfiguracao(final Integer pIdGrupoItemConfiguracao) {
        idGrupoItemConfiguracao = pIdGrupoItemConfiguracao;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(final String familia) {
        this.familia = familia;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(final String classe) {
        this.classe = classe;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(final String localidade) {
        this.localidade = localidade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(final String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(final String versao) {
        this.versao = versao;
    }

    public Integer getCriticidade() {
        return criticidade;
    }

    public void setCriticidade(final Integer criticidade) {
        this.criticidade = criticidade;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(final Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(final String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public List<ItemConfiguracaoDTO> getListItemConfiguracao() {
        return listItemConfiguracao;
    }

    public void setListItemConfiguracao(final List<ItemConfiguracaoDTO> listItemConfiguracao) {
        this.listItemConfiguracao = listItemConfiguracao;
    }

    public Integer getIdIncidente() {
        return idIncidente;
    }

    public void setIdIncidente(final Integer idIncidente) {
        this.idIncidente = idIncidente;
    }

    public Integer getIdMudanca() {
        return idMudanca;
    }

    public void setIdMudanca(final Integer idMudanca) {
        this.idMudanca = idMudanca;
    }

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(final Integer idProblema) {
        this.idProblema = idProblema;
    }

    public Integer getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(final Integer idProprietario) {
        this.idProprietario = idProprietario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(final String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNumeroIncidente() {
        return numeroIncidente;
    }

    public void setNumeroIncidente(final String numeroIncidente) {
        this.numeroIncidente = numeroIncidente;
    }

    public String getNumeroProblema() {
        return numeroProblema;
    }

    public void setNumeroProblema(final String numeroProblema) {
        this.numeroProblema = numeroProblema;
    }

    public String getNumeroMudanca() {
        return numeroMudanca;
    }

    public void setNumeroMudanca(final String numeroMudanca) {
        this.numeroMudanca = numeroMudanca;
    }

    public List<ValorDTO> getValores() {
        return valores;
    }

    public void setValores(final List<ValorDTO> valores) {
        this.valores = valores;
    }

    public String getEmailGrupoItemConfiguracao() {
        return emailGrupoItemConfiguracao;
    }

    public void setEmailGrupoItemConfiguracao(final String emailGrupoItemConfiguracao) {
        this.emailGrupoItemConfiguracao = emailGrupoItemConfiguracao;
    }

    public Integer getIdMidiaSoftware() {
        return idMidiaSoftware;
    }

    public void setIdMidiaSoftware(final Integer idMidiaSoftware) {
        this.idMidiaSoftware = idMidiaSoftware;
    }

    public String getNomeMidia() {
        return nomeMidia;
    }

    public void setNomeMidia(final String nomeMidia) {
        this.nomeMidia = nomeMidia;
    }

    public String getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(final String urgencia) {
        this.urgencia = urgencia;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(final String impacto) {
        this.impacto = impacto;
    }

    /**
     * @return the sequenciaIC
     */
    public Integer getSequenciaIC() {
        return sequenciaIC;
    }

    /**
     * @param sequenciaIC
     *            the sequenciaIC to set
     */
    public void setSequenciaIC(final Integer sequenciaIC) {
        this.sequenciaIC = sequenciaIC;
    }

    public Integer getIdBaseConhecimento() {
        return idBaseConhecimento;
    }

    public void setIdBaseConhecimento(final Integer idBaseConhecimento) {
        this.idBaseConhecimento = idBaseConhecimento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoVinculo() {
        return tipoVinculo;
    }

    public void setTipoVinculo(final String tipoVinculo) {
        this.tipoVinculo = tipoVinculo;
    }

    public String getIdentificacaoPai() {
        return identificacaoPai;
    }

    public List<CaracteristicaDTO> getListCaracteristicas() {
        return listCaracteristicas;
    }

    public void setIdentificacaoPai(final String identificacaoPai) {
        this.identificacaoPai = identificacaoPai;
    }

    public void setListCaracteristicas(final List<CaracteristicaDTO> listCaracteristicas) {
        this.listCaracteristicas = listCaracteristicas;
    }

    public String getMidiaSoftwareChavesSerealizadas() {
        return midiaSoftwareChavesSerealizadas;
    }

    public void setMidiaSoftwareChavesSerealizadas(final String midiaSoftwareChavesSerealizadas) {
        this.midiaSoftwareChavesSerealizadas = midiaSoftwareChavesSerealizadas;
    }

    public List<MidiaSoftwareChaveDTO> getMidiaSoftwareChaves() {
        return midiaSoftwareChaves;
    }

    public void setMidiaSoftwareChaves(final List<MidiaSoftwareChaveDTO> midiaSoftwareChaves) {
        this.midiaSoftwareChaves = midiaSoftwareChaves;
    }

    public String getContem() {
        return contem;
    }

    public void setContem(final String contem) {
        this.contem = contem;
    }

    public String getDuplicado() {
        return duplicado;
    }

    public void setDuplicado(final String duplicado) {
        this.duplicado = duplicado;
    }

    public Timestamp getDtUltimaCaptura() {
        return dtUltimaCaptura;
    }

    public void setDtUltimaCaptura(final Timestamp dtUltimaCaptura) {
        this.dtUltimaCaptura = dtUltimaCaptura;
    }

    public String getIdentificacaoPadrao() {
        if (identificacaoPadrao != null) {
            return identificacaoPadrao.trim();
        }
        return identificacaoPadrao;
    }

    public void setIdentificacaoPadrao(final String identificacaoPadrao) {
        this.identificacaoPadrao = identificacaoPadrao;
    }

    public Integer getIdLiberacao() {
        return idLiberacao;
    }

    public void setIdLiberacao(final Integer idLiberacao) {
        this.idLiberacao = idLiberacao;
    }

    public String getTituloLiberacao() {
        return tituloLiberacao;
    }

    public void setTituloLiberacao(final String tituloLiberacao) {
        this.tituloLiberacao = tituloLiberacao;
    }

    public String getImpactoUrgencia() {
        return impactoUrgencia;
    }

    public void setImpactoUrgencia(final String impactoUrgencia) {
        this.impactoUrgencia = impactoUrgencia;
    }

    public String getGrupoTrabalho() {
        return grupoTrabalho;
    }

    public void setGrupoTrabalho(final String grupoTrabalho) {
        this.grupoTrabalho = grupoTrabalho;
    }

    public String getTipoMembroDominio() {
        return tipoMembroDominio;
    }

    public void setTipoMembroDominio(final String tipoMembroDominio) {
        this.tipoMembroDominio = tipoMembroDominio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(final String usuario) {
        this.usuario = usuario;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(final String processador) {
        this.processador = processador;
    }

    public String getSoftwares() {
        return softwares;
    }

    public void setSoftwares(final String softwares) {
        this.softwares = softwares;
    }

    public String getProcessar() {
        return processar;
    }

    public void setProcessar(final String processar) {
        this.processar = processar;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getAtivoFixo() {
        return ativoFixo;
    }

    public void setAtivoFixo(final String ativoFixo) {
        this.ativoFixo = ativoFixo;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(final String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public HistoricoItemConfiguracaoDTO getHistoricoItemConfiguracaoDTO() {
        return historicoItemConfiguracaoDTO;
    }

    public void setHistoricoItemConfiguracaoDTO(final HistoricoItemConfiguracaoDTO historicoItemConfiguracaoDTO) {
        this.historicoItemConfiguracaoDTO = historicoItemConfiguracaoDTO;
    }

    public Timestamp getDataInicioHistorico() {
        return dataInicioHistorico;
    }

    public void setDataInicioHistorico(final Timestamp dataInicioHistorico) {
        this.dataInicioHistorico = dataInicioHistorico;
    }

    public Timestamp getDataFimHistorico() {
        return dataFimHistorico;
    }

    public void setDataFimHistorico(final Timestamp dataFimHistorico) {
        this.dataFimHistorico = dataFimHistorico;
    }

    public Timestamp getDatahoradesinstalacao() {
        return datahoradesinstalacao;
    }

    public void setDatahoradesinstalacao(final Timestamp datahoradesinstalacao) {
        this.datahoradesinstalacao = datahoradesinstalacao;
    }

    @Override
    public String toString() {
        return "ItemConfiguracaoDTO [identificacao=" + identificacao + "]";
    }

}
