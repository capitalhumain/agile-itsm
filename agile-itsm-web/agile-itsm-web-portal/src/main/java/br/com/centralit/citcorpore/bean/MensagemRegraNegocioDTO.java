package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.Enumerados.ResultadoValidacao;
import br.com.citframework.util.Constantes;

public class MensagemRegraNegocioDTO extends BaseEntity {

    public static final String AVISO = "A";
    public static final String ERRO = "E";

    private String tipo;
    private String mensagem;

    public MensagemRegraNegocioDTO() {
        tipo = "";
        mensagem = "";
    }

    public MensagemRegraNegocioDTO(final String tipo, final String mensagem) {
        this.tipo = tipo;
        this.mensagem = mensagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(final String mensagem) {
        this.mensagem = mensagem;
    }

    public String getImagem() {
        String imagem = "vazio.gif";
        if (tipo != null && !tipo.trim().equals("")) {
            if (tipo.equalsIgnoreCase(ResultadoValidacao.V.name())) {
                imagem = "validado.png";
            } else if (tipo.equalsIgnoreCase(ResultadoValidacao.E.name()) || tipo.equalsIgnoreCase(ResultadoValidacao.I.name())) {
                imagem = "erro.png";
            } else {
                imagem = "excl.gif";
            }
        }
        return Constantes.getValue("CONTEXTO_APLICACAO") + "/imagens/" + imagem;
    }

}
