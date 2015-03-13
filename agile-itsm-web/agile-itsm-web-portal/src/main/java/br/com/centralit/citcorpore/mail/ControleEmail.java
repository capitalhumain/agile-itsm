package br.com.centralit.citcorpore.mail;

import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringEscapeUtils;

import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;
import br.com.centralit.citcorpore.util.ParametroUtil;

/**
 * @author rogerio
 *
 */
public class ControleEmail implements Runnable {

    private final String username;
    private final String password;
    private String auth;
    private final String servidorSMTP;
    private final String porta;
    private String starttls;
    private MensagemEmail mensagem;

    public ControleEmail(final MensagemEmail mensagem) throws Exception {
        username = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.EmailUsuario, "");
        password = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.EmailSenha, "");
        if (ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.EmailAutenticacao, "N").equalsIgnoreCase("S")) {
            auth = "true";
        } else {
            auth = "false";
        }
        servidorSMTP = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.EmailSMTP, "");
        porta = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.SMTP_LEITURA_Porta, "587");
        if (ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.EmailStartTLS, "N").equalsIgnoreCase("S")) {
            starttls = "true";
        } else {
            starttls = "false";
        }
        this.mensagem = mensagem;
    }

    public void send(final String to, final String cc, final String bcc, final String from, final String subject, final String text) throws Exception {
        mensagem = new MensagemEmail(to, cc, bcc, from, subject, text);
        this.send();
        return;
    }

    public void send(final String to, final String cc, final String bcc, final String from, final String subject, final String text,
            final boolean confirmarLeituraMail) throws Exception {
        mensagem = new MensagemEmail(to, cc, bcc, from, subject, text);
        mensagem.setConfirmarLeituraMail(confirmarLeituraMail);
        this.send();
        return;
    }

    public void send() throws Exception {
        try {
            final Properties mailProps = new Properties();
            mailProps.put("mail.smtp.auth", auth);
            mailProps.put("mail.smtp.host", servidorSMTP);
            mailProps.put("mail.smtp.port", porta);
            mailProps.put("mail.smtp.starttls.enable", starttls);

            /**
             * Motivo: Alteração para resolução de incidente. Se não exige autenticação pelo parametro então o mesmo não será atribuido
             * Autor: flavio.santana
             * Data/Hora: 02/12/2013
             */

            Session mailSession = null;
            if (ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.EmailAutenticacao, "N").equalsIgnoreCase("S")) {
                mailSession = Session.getInstance(mailProps, new javax.mail.Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
            } else {
                mailSession = Session.getInstance(mailProps);
            }

            mailSession.setDebug(false);
            final Message email = new MimeMessage(mailSession);

            email.setFrom(new InternetAddress(mensagem.getFrom()));
            email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mensagem.getTo()));
            if (mensagem.getCc() != null && mensagem.getCc().trim().length() > 0) {
                email.setRecipients(Message.RecipientType.CC, InternetAddress.parse(mensagem.getCc()));
            }
            if (mensagem.getCco() != null && mensagem.getCco().trim().length() > 0) {
                email.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(mensagem.getCco()));
            }

            email.setSubject(MimeUtility.encodeText(mensagem.getSubject(), "UTF-8", "B"));

            try {
                email.setSentDate(new Date());
            } catch (final Exception e) {
                System.out.println("ERRO AO SETAR A DATA EM Message email = new MimeMessage(mailSession)");
                e.printStackTrace();
            }

            final MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
            mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(mc);

            email.setContent(StringEscapeUtils.unescapeHtml4(mensagem.getText()), "text/html; charset=UTF-8;");

            // Adicionar header para pedir confirmacao de leitura
            if (mensagem.isConfirmarLeituraMail()) {
                email.addHeader("Disposition-Notification-To", mensagem.getFrom());
            }
            Transport.send(email);
        } catch (final Exception e) {
            System.out.println("PROBLEMAS AO ENVIAR EMAIL! ");
            System.out.println("[E]ERROR: " + e);
        }
        return;
    }

    @Override
    public void run() {
        try {
            this.send();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
