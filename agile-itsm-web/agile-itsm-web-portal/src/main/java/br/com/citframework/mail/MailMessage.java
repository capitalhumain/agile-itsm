package br.com.citframework.mail;

/**
 * @author rogerio
 *
 *
 */
public class MailMessage {

    public String to;
    public String cc;
    public String cco;
    public String from;
    public String subject;
    public String text;
    public boolean confirmarLeituraMail;

    public MailMessage(final String to, final String cc, final String cco, final String from, final String subject, final String text) {
        this.setTo(to);
        this.setCc(cc);
        this.setCco(cco);
        this.setFrom(from);
        this.setSubject(subject);
        this.setText(text);
        this.setConfirmarLeituraMail(false);
    }

    /**
     * @return Returns the cc.
     */
    public String getCc() {
        return cc;
    }

    /**
     * @param cc
     *            The cc to set.
     */
    public void setCc(final String cc) {
        this.cc = cc;
    }

    /**
     * @return Returns the cco.
     */
    public String getCco() {
        return cco;
    }

    /**
     * @param cco
     *            The cco to set.
     */
    public void setCco(final String cco) {
        this.cco = cco;
    }

    /**
     * @return Returns the from.
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from
     *            The from to set.
     */
    public void setFrom(final String from) {
        this.from = from;
    }

    /**
     * @return Returns the subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *            The subject to set.
     */
    public void setSubject(final String subject) {
        this.subject = subject;
    }

    /**
     * @return Returns the text.
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            The text to set.
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * @return Returns the to.
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to
     *            The to to set.
     */
    public void setTo(final String to) {
        this.to = to;
    }

    public boolean isConfirmarLeituraMail() {
        return confirmarLeituraMail;
    }

    public void setConfirmarLeituraMail(final boolean confirmarLeituraMail) {
        this.confirmarLeituraMail = confirmarLeituraMail;
    }

}
