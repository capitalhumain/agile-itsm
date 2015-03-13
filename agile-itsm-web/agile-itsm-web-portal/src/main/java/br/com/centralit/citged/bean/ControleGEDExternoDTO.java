package br.com.centralit.citged.bean;

import java.sql.Timestamp;
import java.util.HashMap;

public class ControleGEDExternoDTO extends ControleGEDDTO {

    private HashMap mapDemaisDados;
    private String caminhoCompletoDocumento;
    private String caminhoCompletoAssinaturaDigital;
    private String caminhoCompletoCarimboTempo;
    private byte[] conteudoDocumento;
    private byte[] conteudoAssinaturaDigital;
    private byte[] conteudoCarimboTempo;
    private Timestamp dataGeracaoConteudo;

    private String numeroRetorno;

    public HashMap getMapDemaisDados() {
        return mapDemaisDados;
    }

    public void setMapDemaisDados(final HashMap mapDemaisDados) {
        this.mapDemaisDados = mapDemaisDados;
    }

    public byte[] getConteudoDocumento() {
        return conteudoDocumento;
    }

    public void setConteudoDocumento(final byte[] conteudoDocumento) {
        this.conteudoDocumento = conteudoDocumento;
    }

    public byte[] getConteudoAssinaturaDigital() {
        return conteudoAssinaturaDigital;
    }

    public void setConteudoAssinaturaDigital(final byte[] conteudoAssinaturaDigital) {
        this.conteudoAssinaturaDigital = conteudoAssinaturaDigital;
    }

    public byte[] getConteudoCarimboTempo() {
        return conteudoCarimboTempo;
    }

    public void setConteudoCarimboTempo(final byte[] conteudoCarimboTempo) {
        this.conteudoCarimboTempo = conteudoCarimboTempo;
    }

    public String getCaminhoCompletoDocumento() {
        return caminhoCompletoDocumento;
    }

    public void setCaminhoCompletoDocumento(final String caminhoCompletoDocumento) {
        this.caminhoCompletoDocumento = caminhoCompletoDocumento;
    }

    public String getCaminhoCompletoAssinaturaDigital() {
        return caminhoCompletoAssinaturaDigital;
    }

    public void setCaminhoCompletoAssinaturaDigital(final String caminhoCompletoAssinaturaDigital) {
        this.caminhoCompletoAssinaturaDigital = caminhoCompletoAssinaturaDigital;
    }

    public String getCaminhoCompletoCarimboTempo() {
        return caminhoCompletoCarimboTempo;
    }

    public void setCaminhoCompletoCarimboTempo(final String caminhoCompletoCarimboTempo) {
        this.caminhoCompletoCarimboTempo = caminhoCompletoCarimboTempo;
    }

    public Timestamp getDataGeracaoConteudo() {
        return dataGeracaoConteudo;
    }

    public void setDataGeracaoConteudo(final Timestamp dataGeracaoConteudo) {
        this.dataGeracaoConteudo = dataGeracaoConteudo;
    }

    public String getNumeroRetorno() {
        return numeroRetorno;
    }

    public void setNumeroRetorno(final String numeroRetorno) {
        this.numeroRetorno = numeroRetorno;
    }

}
