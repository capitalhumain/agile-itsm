package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author ronnie.lopes
 *         DTO criado apenas para envio do email Softwares Lista Negra, não possui tabela no banco de dados
 */
public class NotificacaoListaNegraEncontradosDTO extends BaseEntity {

    private String computador;
    private String softwarelistanegra;
    private String softwareencontrado;
    private String dataHora;
    private String tabelaListaNegra;
    private String nomeContato;

    public String getComputador() {
        return computador;
    }

    public void setComputador(final String computador) {
        this.computador = computador;
    }

    public String getSoftwarelistanegra() {
        return softwarelistanegra;
    }

    public void setSoftwarelistanegra(final String softwarelistanegra) {
        this.softwarelistanegra = softwarelistanegra;
    }

    public String getSoftwareencontrado() {
        return softwareencontrado;
    }

    public void setSoftwareencontrado(final String softwareencontrado) {
        this.softwareencontrado = softwareencontrado;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(final String dataHora) {
        this.dataHora = dataHora;
    }

    public String getTabelaListaNegra() {
        return tabelaListaNegra;
    }

    public void setTabelaListaNegra(final String tabelaListaNegra) {
        this.tabelaListaNegra = tabelaListaNegra;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(final String nomeContato) {
        this.nomeContato = nomeContato;
    }

}
