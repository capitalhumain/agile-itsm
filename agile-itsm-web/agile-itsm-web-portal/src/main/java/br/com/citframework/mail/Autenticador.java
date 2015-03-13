package br.com.citframework.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author rogerio
 *
 */
public class Autenticador extends Authenticator {

    public String username = null;
    public String password = null;

    public Autenticador(final String user, final String pwd) {
        username = user;
        password = pwd;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }

}
