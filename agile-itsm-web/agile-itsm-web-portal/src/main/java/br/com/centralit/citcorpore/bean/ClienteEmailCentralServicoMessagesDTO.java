package br.com.centralit.citcorpore.bean;

import java.util.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author breno.guimaraes
 *
 */
public class ClienteEmailCentralServicoMessagesDTO extends BaseEntity {

    private static final long serialVersionUID = 4004251311921076618L;

    private String messageId;
    private Integer messageNumber;
    private String messageEmail;
    private String messageSubject;
    private String messageContent;
    private Date messageReceivedDate;
    private boolean seen;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(final String messageId) {
        this.messageId = messageId;
    }

    public Integer getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(final Integer messageNumber) {
        this.messageNumber = messageNumber;
    }

    public String getMessageEmail() {
        return messageEmail;
    }

    public void setMessageEmail(final String messageEmail) {
        this.messageEmail = messageEmail;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(final String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(final String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getMessageReceivedDate() {
        return messageReceivedDate;
    }

    public void setMessageReceivedDate(final Date messageReceivedDate) {
        this.messageReceivedDate = messageReceivedDate;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(final boolean seen) {
        this.seen = seen;
    }

}
