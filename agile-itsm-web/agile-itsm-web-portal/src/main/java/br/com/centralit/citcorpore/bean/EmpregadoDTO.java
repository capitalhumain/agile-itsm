package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.Util;

/**
 * @author thays.araujo
 *
 */
public class EmpregadoDTO extends BaseEntity {

    private static final long serialVersionUID = 1582364224581163482L;
    private Integer idEmpregado;
    private Integer idGrupo;
    private Integer idCargo;
    private Integer idUnidade;
    private String nome;
    private String nomeProcura;
    private String telefone;
    private Date dataNascimento;
    private String sexo;
    private String cpf;
    private String rg;
    private Date dataEmissaoRG;
    private String orgExpedidor;
    private Integer idUFOrgExpedidor;
    private String pai;
    private String mae;
    private String conjuge;
    private String observacoes;
    private Integer estadoCivil;
    private String email;
    private Date dataCadastro;
    private String fumante;
    private String ctpsNumero;
    private String ctpsSerie;
    private Integer ctpsIdUf;
    private Date ctpsDataEmissao;
    private String nit;
    private Date dataAdmissao;
    private Date dataFim;
    private Integer idSituacaoFuncional;
    private Date dataDemissao;
    private String tipo;
    private Double custoPorHora;
    private Double custoTotalMes;
    private Double valorSalario;
    private Double valorProdutividadeMedia;
    private Double valorPlanoSaudeMedia;
    private Double valorVTraMedia;
    private Double valorVRefMedia;
    private String agencia;
    private String contaSalario;
    private String enviaEmail;
    private String ramal;
    private Integer idCurriculo;
    private String nomeCargo;

    public String getEnviaEmail() {
        return enviaEmail;
    }

    public void setEnviaEmail(final String enviaEmail) {
        this.enviaEmail = enviaEmail;
    }

    private Integer idContrato;
    private String iframe;

    /**
     * @return the custoPorHora
     */
    public Double getCustoPorHora() {
        return custoPorHora;
    }

    /**
     * @param custoPorHora
     *            the custoPorHora to set
     */
    public void setCustoPorHora(final Double custoPorHora) {
        this.custoPorHora = custoPorHora;
    }

    /**
     * @return the custoTotalMes
     */
    public Double getCustoTotalMes() {
        return custoTotalMes;
    }

    /**
     * @param custoTotalMes
     *            the custoTotalMes to set
     */
    public void setCustoTotalMes(final Double custoTotalMes) {
        this.custoTotalMes = custoTotalMes;
    }

    /**
     * @return the valorSalario
     */
    public Double getValorSalario() {
        return valorSalario;
    }

    /**
     * @param valorSalario
     *            the valorSalario to set
     */
    public void setValorSalario(final Double valorSalario) {
        this.valorSalario = valorSalario;
    }

    /**
     * @return the valorProdutividadeMedia
     */
    public Double getValorProdutividadeMedia() {
        return valorProdutividadeMedia;
    }

    /**
     * @param valorProdutividadeMedia
     *            the valorProdutividadeMedia to set
     */
    public void setValorProdutividadeMedia(final Double valorProdutividadeMedia) {
        this.valorProdutividadeMedia = valorProdutividadeMedia;
    }

    /**
     * @return the valorPlanoSaudeMedia
     */
    public Double getValorPlanoSaudeMedia() {
        return valorPlanoSaudeMedia;
    }

    /**
     * @param valorPlanoSaudeMedia
     *            the valorPlanoSaudeMedia to set
     */
    public void setValorPlanoSaudeMedia(final Double valorPlanoSaudeMedia) {
        this.valorPlanoSaudeMedia = valorPlanoSaudeMedia;
    }

    /**
     * @return the valorVTraMedia
     */
    public Double getValorVTraMedia() {
        return valorVTraMedia;
    }

    /**
     * @param valorVTraMedia
     *            the valorVTraMedia to set
     */
    public void setValorVTraMedia(final Double valorVTraMedia) {
        this.valorVTraMedia = valorVTraMedia;
    }

    /**
     * @return the valorVRefMedia
     */
    public Double getValorVRefMedia() {
        return valorVRefMedia;
    }

    /**
     * @param valorVRefMedia
     *            the valorVRefMedia to set
     */
    public void setValorVRefMedia(final Double valorVRefMedia) {
        this.valorVRefMedia = valorVRefMedia;
    }

    /**
     * @return the agencia
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * @return valor do atributo idEmpregado.
     */
    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    /**
     * Define valor do atributo idEmpregado.
     *
     * @param idEmpregado
     */
    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    /**
     * @return valor do atributo idGrupo.
     */
    public Integer getIdGrupo() {
        return idGrupo;
    }

    /**
     * Define valor do atributo idGrupo.
     *
     * @param idGrupo
     */
    public void setIdGrupo(final Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    /**
     * @return valor do atributo idUnidade.
     */
    public Integer getIdUnidade() {
        return idUnidade;
    }

    /**
     * Define valor do atributo idUnidade.
     *
     * @param idUnidade
     */
    public void setIdUnidade(final Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    /**
     * @return valor do atributo nome.
     */
    public String getNome() {
        return Util.tratarAspasSimples(nome);
    }

    /**
     * Define valor do atributo nome.
     *
     * @param nome
     */
    public void setNome(final String nome) {
        this.nome = nome;
    }

    /**
     * @return valor do atributo nomeProcura.
     */
    public String getNomeProcura() {
        return Util.tratarAspasSimples(nomeProcura);
    }

    /**
     * Define valor do atributo nomeProcura.
     *
     * @param nomeProcura
     */
    public void setNomeProcura(final String nomeProcura) {
        this.nomeProcura = nomeProcura;
    }

    /**
     * @return valor do atributo dataNascimento.
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Define valor do atributo dataNascimento.
     *
     * @param dataNascimento
     */
    public void setDataNascimento(final Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return valor do atributo sexo.
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Define valor do atributo sexo.
     *
     * @param sexo
     */
    public void setSexo(final String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return valor do atributo cpf.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define valor do atributo cpf.
     *
     * @param cpf
     */
    public void setCpf(final String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return valor do atributo rg.
     */
    public String getRg() {
        return rg;
    }

    /**
     * Define valor do atributo rg.
     *
     * @param rg
     */
    public void setRg(final String rg) {
        this.rg = rg;
    }

    /**
     * @return valor do atributo dataEmissaoRG.
     */
    public Date getDataEmissaoRG() {
        return dataEmissaoRG;
    }

    /**
     * Define valor do atributo dataEmissaoRG.
     *
     * @param dataEmissaoRG
     */
    public void setDataEmissaoRG(final Date dataEmissaoRG) {
        this.dataEmissaoRG = dataEmissaoRG;
    }

    /**
     * @return valor do atributo orgExpedidor.
     */
    public String getOrgExpedidor() {
        return orgExpedidor;
    }

    /**
     * Define valor do atributo orgExpedidor.
     *
     * @param orgExpedidor
     */
    public void setOrgExpedidor(final String orgExpedidor) {
        this.orgExpedidor = orgExpedidor;
    }

    /**
     * @return valor do atributo idUFOrgExpedidor.
     */
    public Integer getIdUFOrgExpedidor() {
        return idUFOrgExpedidor;
    }

    /**
     * Define valor do atributo idUFOrgExpedidor.
     *
     * @param idUFOrgExpedidor
     */
    public void setIdUFOrgExpedidor(final Integer idUFOrgExpedidor) {
        this.idUFOrgExpedidor = idUFOrgExpedidor;
    }

    /**
     * @return valor do atributo pai.
     */
    public String getPai() {
        return pai;
    }

    /**
     * Define valor do atributo pai.
     *
     * @param pai
     */
    public void setPai(final String pai) {
        this.pai = pai;
    }

    /**
     * @return valor do atributo mae.
     */
    public String getMae() {
        return mae;
    }

    /**
     * Define valor do atributo mae.
     *
     * @param mae
     */
    public void setMae(final String mae) {
        this.mae = mae;
    }

    /**
     * @return valor do atributo conjuge.
     */
    public String getConjuge() {
        return conjuge;
    }

    /**
     * Define valor do atributo conjuge.
     *
     * @param conjuge
     */
    public void setConjuge(final String conjuge) {
        this.conjuge = conjuge;
    }

    /**
     * @return valor do atributo observacoes.
     */
    public String getObservacoes() {
        return Util.tratarAspasSimples(observacoes);
    }

    /**
     * Define valor do atributo observacoes.
     *
     * @param observacoes
     */
    public void setObservacoes(final String observacoes) {
        this.observacoes = observacoes;
    }

    /**
     * @return valor do atributo estadoCivil.
     */
    public Integer getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * Define valor do atributo estadoCivil.
     *
     * @param estadoCivil
     */
    public void setEstadoCivil(final Integer estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    /**
     * @return valor do atributo email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define valor do atributo email.
     *
     * @param email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return valor do atributo dataCadastro.
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }

    /**
     * Define valor do atributo dataCadastro.
     *
     * @param dataCadastro
     */
    public void setDataCadastro(final Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return valor do atributo fumante.
     */
    public String getFumante() {
        return fumante;
    }

    /**
     * Define valor do atributo fumante.
     *
     * @param fumante
     */
    public void setFumante(final String fumante) {
        this.fumante = fumante;
    }

    /**
     * @return valor do atributo ctpsNumero.
     */
    public String getCtpsNumero() {
        return ctpsNumero;
    }

    /**
     * Define valor do atributo ctpsNumero.
     *
     * @param ctpsNumero
     */
    public void setCtpsNumero(final String ctpsNumero) {
        this.ctpsNumero = ctpsNumero;
    }

    /**
     * @return valor do atributo ctpsSerie.
     */
    public String getCtpsSerie() {
        return ctpsSerie;
    }

    /**
     * Define valor do atributo ctpsSerie.
     *
     * @param ctpsSerie
     */
    public void setCtpsSerie(final String ctpsSerie) {
        this.ctpsSerie = ctpsSerie;
    }

    /**
     * @return valor do atributo ctpsIdUf.
     */
    public Integer getCtpsIdUf() {
        return ctpsIdUf;
    }

    /**
     * Define valor do atributo ctpsIdUf.
     *
     * @param ctpsIdUf
     */
    public void setCtpsIdUf(final Integer ctpsIdUf) {
        this.ctpsIdUf = ctpsIdUf;
    }

    /**
     * @return valor do atributo ctpsDataEmissao.
     */
    public Date getCtpsDataEmissao() {
        return ctpsDataEmissao;
    }

    /**
     * Define valor do atributo ctpsDataEmissao.
     *
     * @param ctpsDataEmissao
     */
    public void setCtpsDataEmissao(final Date ctpsDataEmissao) {
        this.ctpsDataEmissao = ctpsDataEmissao;
    }

    /**
     * @return valor do atributo nit.
     */
    public String getNit() {
        return nit;
    }

    /**
     * Define valor do atributo nit.
     *
     * @param nit
     */
    public void setNit(final String nit) {
        this.nit = nit;
    }

    /**
     * @return valor do atributo dataAdmissao.
     */
    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    /**
     * Define valor do atributo dataAdmissao.
     *
     * @param dataAdmissao
     */
    public void setDataAdmissao(final Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    /**
     * @return valor do atributo idSituacaoFuncional.
     */
    public Integer getIdSituacaoFuncional() {
        return idSituacaoFuncional;
    }

    /**
     * Define valor do atributo idSituacaoFuncional.
     *
     * @param idSituacaoFuncional
     */
    public void setIdSituacaoFuncional(final Integer idSituacaoFuncional) {
        this.idSituacaoFuncional = idSituacaoFuncional;
    }

    /**
     * @return valor do atributo dataDemissao.
     */
    public Date getDataDemissao() {
        return dataDemissao;
    }

    /**
     * Define valor do atributo dataDemissao.
     *
     * @param dataDemissao
     */
    public void setDataDemissao(final Date dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    /**
     * @return valor do atributo tipo.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define valor do atributo tipo.
     *
     * @param tipo
     */
    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    /**
     * Define valor do atributo agencia.
     *
     * @param agencia
     */
    public void setAgencia(final String agencia) {
        this.agencia = agencia;
    }

    /**
     * @return valor do atributo contaSalario.
     */
    public String getContaSalario() {
        return contaSalario;
    }

    /**
     * Define valor do atributo contaSalario.
     *
     * @param contaSalario
     */
    public void setContaSalario(final String contaSalario) {
        this.contaSalario = contaSalario;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the idCargo
     */
    public Integer getIdCargo() {
        return idCargo;
    }

    /**
     * @param idCargo
     *            the idCargo to set
     */
    public void setIdCargo(final Integer idCargo) {
        this.idCargo = idCargo;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getIframe() {
        return iframe;
    }

    public void setIframe(final String iframe) {
        this.iframe = iframe;
    }

    /**
     * @return the ramal
     */
    public String getRamal() {
        return ramal;
    }

    /**
     * @param ramal
     *            the ramal to set
     */
    public void setRamal(final String ramal) {
        this.ramal = ramal;
    }

    public Integer getIdCurriculo() {
        return idCurriculo;
    }

    public void setIdCurriculo(final Integer idCurriculo) {
        this.idCurriculo = idCurriculo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (idEmpregado == null ? 0 : idEmpregado.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final EmpregadoDTO other = (EmpregadoDTO) obj;
        if (idEmpregado == null) {
            if (other.idEmpregado != null) {
                return false;
            }
        } else if (!idEmpregado.equals(other.idEmpregado)) {
            return false;
        }
        return true;
    }

    /**
     * @return the nomeCargo
     */
    public String getNomeCargo() {
        return nomeCargo;
    }

    /**
     * @param nomeCargo
     *            the nomeCargo to set
     */
    public void setNomeCargo(final String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

}
