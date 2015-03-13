package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.util.UtilFormatacao;
import br.com.citframework.util.UtilHTML;
import br.com.citframework.util.UtilStrings;

public class FornecedorDTO extends BaseEntity {

    private static final long serialVersionUID = -5191625829085954557L;

    private Integer idFornecedor;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String email;
    private String observacao;
    private String deleted;
    private String tipoPessoa;
    private String telefone;
    private String fax;
    private Integer idEndereco;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private Integer idPais;
    private Integer idUf;
    private Integer idCidade;
    private String cep;
    private String nomeContato;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String identificacao;
    private String responsabilidades;
    private Integer idTipoRegistro;
    private Integer idFrequencia;
    private Integer idFormaContato;
    private String ativ_responsabilidades;
    private String gerenciamentodesacordo;

    private String nomeProduto;

    private String qualificado;

    private String marca;

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(final Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getRazaoSocialHTMLEncoded() {
        return UtilHTML.encodeHTML(UtilStrings.nullToVazio(razaoSocial));
    }

    public void setRazaoSocial(final String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(final String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(final String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(final String observacao) {
        this.observacao = observacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(final String fax) {
        this.fax = fax;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(final Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(final String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(final String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(final String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(final String bairro) {
        this.bairro = bairro;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(final Integer idPais) {
        this.idPais = idPais;
    }

    public Integer getIdUf() {
        return idUf;
    }

    public void setIdUf(final Integer idUf) {
        this.idUf = idUf;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(final Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(final String cep) {
        this.cep = cep;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(final String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(final String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(final String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getCnpjFormatado() {
        if (cnpj == null) {
            return "";
        }
        return UtilFormatacao.formataCnpj(cnpj);
    }

    public String getIdentificacao() {
        identificacao = this.getCnpjFormatado();

        if (nomeFantasia != null) {
            if (identificacao.length() > 0) {
                identificacao += " - ";
            }
            identificacao += nomeFantasia;
        }
        return identificacao;
    }

    public void setIdentificacao(final String identificacao) {
        this.identificacao = identificacao;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

    /**
     * @return the nomeProduto
     */
    public String getNomeProduto() {
        return nomeProduto;
    }

    /**
     * @param nomeProduto
     *            the nomeProduto to set
     */
    public void setNomeProduto(final String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca
     *            the marca to set
     */
    public void setMarca(final String marca) {
        this.marca = marca;
    }

    /**
     * @return the tipoPessoa
     */
    public String getTipoPessoa() {
        return tipoPessoa;
    }

    /**
     * @param tipoPessoa
     */
    public void setTipoPessoa(final String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    /**
     * @return the qualificado
     */
    public String getQualificado() {
        return qualificado;
    }

    /**
     * @param qualificado
     */
    public void setQualificado(final String qualificado) {
        this.qualificado = qualificado;
    }

    /**
     * @return the responsabilidades
     */
    public String getResponsabilidades() {
        return responsabilidades;
    }

    /**
     * @param responsabilidades
     */
    public void setResponsabilidades(final String responsabilidades) {
        this.responsabilidades = responsabilidades;
    }

    /**
     * @return the idTipoRegistro
     */
    public Integer getIdTipoRegistro() {
        return idTipoRegistro;
    }

    /**
     * @param idTipoRegistro
     *            the idTipoRegistro to set
     */
    public void setIdTipoRegistro(final Integer idTipoRegistro) {
        this.idTipoRegistro = idTipoRegistro;
    }

    /**
     * @return the idFrequencia
     */
    public Integer getIdFrequencia() {
        return idFrequencia;
    }

    /**
     * @param idFrequencia
     *            the idFrequencia to set
     */
    public void setIdFrequencia(final Integer idFrequencia) {
        this.idFrequencia = idFrequencia;
    }

    /**
     * @return the idFormaContato
     */
    public Integer getIdFormaContato() {
        return idFormaContato;
    }

    /**
     * @param idFormaContato
     *            the idFormaContato to set
     */
    public void setIdFormaContato(final Integer idFormaContato) {
        this.idFormaContato = idFormaContato;
    }

    /**
     * @return the ativ_responsabilidades
     */
    public String getAtiv_responsabilidades() {
        return ativ_responsabilidades;
    }

    /**
     * @param ativ_responsabilidades
     *            the ativ_responsabilidades to set
     */
    public void setAtiv_responsabilidades(final String ativ_responsabilidades) {
        this.ativ_responsabilidades = ativ_responsabilidades;
    }

    /**
     * @return the gerenciamentodesacordo
     */
    public String getGerenciamentodesacordo() {
        return gerenciamentodesacordo;
    }

    /**
     * @param gerenciamentodesacordo
     *            the gerenciamentodesacordo to set
     */
    public void setGerenciamentodesacordo(final String gerenciamentodesacordo) {
        this.gerenciamentodesacordo = gerenciamentodesacordo;
    }

}
