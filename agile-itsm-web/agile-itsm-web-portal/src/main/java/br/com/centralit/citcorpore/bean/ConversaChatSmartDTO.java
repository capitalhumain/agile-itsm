package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class ConversaChatSmartDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idConversaChat;
    private Integer idRegistroChat;
    private Integer idUsuario;
    private Timestamp dataHoraConversa;
    private String conversa;

    public Timestamp getDataHoraConversa() {
        return dataHoraConversa;
    }

    public void setDataHoraConversa(final Timestamp dataHoraConversa) {
        this.dataHoraConversa = dataHoraConversa;
    }

    public Integer getIdConversaChat() {
        return idConversaChat;
    }

    public void setIdConversaChat(final Integer idConversaChat) {
        this.idConversaChat = idConversaChat;
    }

    public Integer getIdRegistroChat() {
        return idRegistroChat;
    }

    public void setIdRegistroChat(final Integer idRegistroChat) {
        this.idRegistroChat = idRegistroChat;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(final Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getConversa() {
        return conversa;
    }

    public void setConversa(final String conversa) {
        this.conversa = conversa;
    }

}
