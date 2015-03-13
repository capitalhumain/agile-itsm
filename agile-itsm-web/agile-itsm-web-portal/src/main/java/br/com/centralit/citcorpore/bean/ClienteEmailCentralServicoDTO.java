package br.com.centralit.citcorpore.bean;

import java.util.ArrayList;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author breno.guimaraes
 *
 */
public class ClienteEmailCentralServicoDTO extends BaseEntity {

    private static final long serialVersionUID = 4004251311921076618L;

    private String host;
    private String username;
    private String password;
    private String provider;
    private Integer port;

    private Integer idContrato;
    private String emailOrigem;
    private String emailMessageId;

    private ArrayList<ClienteEmailCentralServicoMessagesDTO> emailMessages;
    private String resultMessage;
    private boolean resultSucess;

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(final String provider) {
        this.provider = provider;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(final Integer port) {
        this.port = port;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getEmailMessageId() {
        return emailMessageId;
    }

    public void setEmailMessageId(final String emailMessageId) {
        this.emailMessageId = emailMessageId;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(final String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public boolean isResultSucess() {
        return resultSucess;
    }

    public void setResultSucess(final boolean resultSucess) {
        this.resultSucess = resultSucess;
    }

    public ArrayList<ClienteEmailCentralServicoMessagesDTO> getEmailMessages() {
        return emailMessages;
    }

    public void setEmailMessages(final ArrayList<ClienteEmailCentralServicoMessagesDTO> emailMessages) {
        this.emailMessages = emailMessages;
    }

    public String getEmailOrigem() {
        return emailOrigem;
    }

    public void setEmailOrigem(final String emailOrigem) {
        this.emailOrigem = emailOrigem;
    }

}
