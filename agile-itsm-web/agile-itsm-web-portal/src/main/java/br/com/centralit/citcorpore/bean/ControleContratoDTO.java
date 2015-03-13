package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;

public class ControleContratoDTO extends BaseEntity {

    private static final long serialVersionUID = 1582364224581163482L;

    private Integer idControleContrato;
    private Integer idContrato;
    private String nomeContrato;

    private String cliente;
    private String numeroSubscricao;
    private String email;

    private String endereco;
    private String contato;
    private String telefone1;
    private String telefone2;
    private Integer tipoSubscricao;
    private String url;
    private String login;
    private String senha;
    private Date dataInicio;

    private Date dataFim;

    private String moduloAtivo;

    private Integer idCcVersao;
    private String nomeCcVersao;

    // pagamento
    private Date dataCcPagamento;
    private Integer parcelaCcPagamento;
    private Date dataAtrasoCcPagamento;
    // treinamento
    private Date dataCcTreinamento;
    private String nomeCcTreinamento;
    private Integer idEmpregadoTreinamento;
    private String nomeInstrutorCcTreinamento;

    private String pagamentoSerialize;
    private String treinamentoSerialize;
    private String ocorrenciaSerialize;
    private String versaoSerialize;
    // ocorrencia
    private Date dataCcOcorrencia;
    private String assuntoCcOcorrencia;
    private Integer idUsuarioOcorrencia;
    private String usuarioOcorrencia;
    private String descOcorrencia;
    private String empregadoCcOcorrencia;

    private List lstPagamento;
    private List lstTreinamento;
    private List lstOcorrencia;

    private List lstVersao;
    private String lstModulos;
    private List lstModulosAtivos;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getLstModulos() {
        return lstModulos;
    }

    public void setLstModulos(final String lstModulos) {
        this.lstModulos = lstModulos;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getIdEmpregadoTreinamento() {
        return idEmpregadoTreinamento;
    }

    public void setIdEmpregadoTreinamento(final Integer idEmpregadoTreinamento) {
        this.idEmpregadoTreinamento = idEmpregadoTreinamento;
    }

    public Date getDataCcOcorrencia() {
        return dataCcOcorrencia;
    }

    public void setDataCcOcorrencia(final Date dataCcOcorrencia) {
        this.dataCcOcorrencia = dataCcOcorrencia;
    }

    public String getAssuntoCcOcorrencia() {
        return assuntoCcOcorrencia;
    }

    public void setAssuntoCcOcorrencia(final String assuntoCcOcorrencia) {
        this.assuntoCcOcorrencia = assuntoCcOcorrencia;
    }

    public Integer getIdUsuarioOcorrencia() {
        return idUsuarioOcorrencia;
    }

    public void setIdUsuarioOcorrencia(final Integer idUsuarioOcorrencia) {
        this.idUsuarioOcorrencia = idUsuarioOcorrencia;
    }

    public String getUsuarioOcorrencia() {
        return usuarioOcorrencia;
    }

    public void setUsuarioOcorrencia(final String usuarioOcorrencia) {
        this.usuarioOcorrencia = usuarioOcorrencia;
    }

    public String getDescOcorrencia() {
        return descOcorrencia;
    }

    public void setDescOcorrencia(final String descOcorrencia) {
        this.descOcorrencia = descOcorrencia;
    }

    public Integer getIdControleContrato() {
        return idControleContrato;
    }

    public void setIdControleContrato(final Integer idControleContrato) {
        this.idControleContrato = idControleContrato;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(final String cliente) {
        this.cliente = cliente;
    }

    public String getNumeroSubscricao() {
        return numeroSubscricao;
    }

    public void setNumeroSubscricao(final String numeroSubscricao) {
        this.numeroSubscricao = numeroSubscricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(final String endereco) {
        this.endereco = endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(final String contato) {
        this.contato = contato;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(final String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(final String telefone2) {
        this.telefone2 = telefone2;
    }

    public Integer getTipoSubscricao() {
        return tipoSubscricao;
    }

    public void setTipoSubscricao(final Integer tipoSubscricao) {
        this.tipoSubscricao = tipoSubscricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(final String senha) {
        this.senha = senha;
    }

    public String getModuloAtivo() {
        return moduloAtivo;
    }

    public void setModuloAtivo(final String moduloAtivo) {
        this.moduloAtivo = moduloAtivo;
    }

    public List getLstPagamento() {
        return lstPagamento;
    }

    public void setLstPagamento(final List lstPagamento) {
        this.lstPagamento = lstPagamento;
    }

    public List getLstTreinamento() {
        return lstTreinamento;
    }

    public void setLstTreinamento(final List lstTreinamento) {
        this.lstTreinamento = lstTreinamento;
    }

    public List getLstOcorrencia() {
        return lstOcorrencia;
    }

    public void setLstOcorrencia(final List lstOcorrencia) {
        this.lstOcorrencia = lstOcorrencia;
    }

    public List getLstVersao() {
        return lstVersao;
    }

    public void setLstVersao(final List lstVersao) {
        this.lstVersao = lstVersao;
    }

    public String getPagamentoSerialize() {
        return pagamentoSerialize;
    }

    public void setPagamentoSerialize(final String pagamentoSerialize) {
        this.pagamentoSerialize = pagamentoSerialize;
    }

    public String getTreinamentoSerialize() {
        return treinamentoSerialize;
    }

    public void setTreinamentoSerialize(final String treinamentoSerialize) {
        this.treinamentoSerialize = treinamentoSerialize;
    }

    public String getOcorrenciaSerialize() {
        return ocorrenciaSerialize;
    }

    public void setOcorrenciaSerialize(final String ocorrenciaSerialize) {
        this.ocorrenciaSerialize = ocorrenciaSerialize;
    }

    public String getVersaoSerialize() {
        return versaoSerialize;
    }

    public void setVersaoSerialize(final String versaoSerialize) {
        this.versaoSerialize = versaoSerialize;
    }

    public List getLstModulosAtivos() {
        return lstModulosAtivos;
    }

    public void setLstModulosAtivos(final List lstModulosAtivos) {
        this.lstModulosAtivos = lstModulosAtivos;
    }

    public String getNomeContrato() {
        return nomeContrato;
    }

    public void setNomeContrato(final String nomeContrato) {
        this.nomeContrato = nomeContrato;
    }

    public Date getDataCcPagamento() {
        return dataCcPagamento;
    }

    public void setDataCcPagamento(final Date dataCcPagamento) {
        this.dataCcPagamento = dataCcPagamento;
    }

    public Integer getParcelaCcPagamento() {
        return parcelaCcPagamento;
    }

    public void setParcelaCcPagamento(final Integer parcelaCcPagamento) {
        this.parcelaCcPagamento = parcelaCcPagamento;
    }

    public Date getDataAtrasoCcPagamento() {
        return dataAtrasoCcPagamento;
    }

    public void setDataAtrasoCcPagamento(final Date dataAtrasoCcPagamento) {
        this.dataAtrasoCcPagamento = dataAtrasoCcPagamento;
    }

    public Date getDataCcTreinamento() {
        return dataCcTreinamento;
    }

    public void setDataCcTreinamento(final Date dataCcTreinamento) {
        this.dataCcTreinamento = dataCcTreinamento;
    }

    public String getNomeCcTreinamento() {
        return nomeCcTreinamento;
    }

    public void setNomeCcTreinamento(final String nomeCcTreinamento) {
        this.nomeCcTreinamento = nomeCcTreinamento;
    }

    public String getNomeInstrutorCcTreinamento() {
        return nomeInstrutorCcTreinamento;
    }

    public void setNomeInstrutorCcTreinamento(final String nomeInstrutorCcTreinamento) {
        this.nomeInstrutorCcTreinamento = nomeInstrutorCcTreinamento;
    }

    public Integer getIdCcVersao() {
        return idCcVersao;
    }

    public void setIdCcVersao(final Integer idCcVersao) {
        this.idCcVersao = idCcVersao;
    }

    public String getNomeCcVersao() {
        return nomeCcVersao;
    }

    public void setNomeCcVersao(final String nomeCcVersao) {
        this.nomeCcVersao = nomeCcVersao;
    }

    public String getEmpregadoCcOcorrencia() {
        return empregadoCcOcorrencia;
    }

    public void setEmpregadoCcOcorrencia(final String empregadoCcOcorrencia) {
        this.empregadoCcOcorrencia = empregadoCcOcorrencia;
    }

}
