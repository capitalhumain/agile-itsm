package br.com.centralit.citcorpore.bean;

import java.util.Collection;
import java.util.Iterator;

import br.com.agileitsm.model.support.BaseEntity;

public class ItemRegraNegocioDTO extends BaseEntity {

    private String resultadoValidacao;
    private String mensagensValidacao;
    private boolean ignoraErroImpeditivo;

    private String imagem;
    private String mensagensFmtHTML;

    public String getResultadoValidacao() {
        return resultadoValidacao;
    }

    public void setResultadoValidacao(final String resultadoValidacao) {
        this.resultadoValidacao = resultadoValidacao;
    }

    public String getMensagensValidacao() {
        return mensagensValidacao;
    }

    public void setMensagensValidacao(final String mensagensValidacao) {
        this.mensagensValidacao = mensagensValidacao;
    }

    public String getMensagensFmtHTML() {
        if (mensagensValidacao == null || mensagensValidacao.trim().equals("")) {
            return "";
        }

        Collection colMensagens = null;
        try {
            colMensagens = this.getColMensagensValidacao();
        } catch (final Exception e) {}
        if (colMensagens == null || colMensagens.size() == 0) {
            return "";
        }

        mensagensFmtHTML = "";
        for (final Iterator it = colMensagens.iterator(); it.hasNext();) {
            final MensagemRegraNegocioDTO mensagemDto = (MensagemRegraNegocioDTO) it.next();
            mensagensFmtHTML += "<img src='" + mensagemDto.getImagem() + "'>" + mensagemDto.getMensagem() + "<br>";
        }
        return mensagensFmtHTML;
    }

    public String getImagem() {
        final MensagemRegraNegocioDTO mensagemDto = new MensagemRegraNegocioDTO();
        mensagemDto.setTipo(resultadoValidacao);
        imagem = mensagemDto.getImagem();
        return imagem;
    }

    public Collection getColMensagensValidacao() throws Exception {
        if (this.getMensagensValidacao() == null) {
            return null;
        }
        return br.com.citframework.util.WebUtil.deserializeCollectionFromString(MensagemRegraNegocioDTO.class, this.getMensagensValidacao());
    }

    public void setColMensagensValidacao(final Collection colMensagens) throws Exception {
        if (colMensagens == null) {
            this.setMensagensValidacao(null);
        } else {
            this.setMensagensValidacao(br.com.citframework.util.WebUtil.serializeObjects(colMensagens));
        }

    }

    public boolean isIgnoraErroImpeditivo() {
        return ignoraErroImpeditivo;
    }

    public boolean getIgnoraErroImpeditivo() {
        return ignoraErroImpeditivo;
    }

    public void setIgnoraErroImpeditivo(final boolean ignoraErroImpeditivo) {
        this.ignoraErroImpeditivo = ignoraErroImpeditivo;
    }

    public void setImagem(final String imagem) {
        this.imagem = imagem;
    }

    public void setMensagensFmtHTML(final String mensagensFmtHTML) {
        this.mensagensFmtHTML = mensagensFmtHTML;
    }

}
