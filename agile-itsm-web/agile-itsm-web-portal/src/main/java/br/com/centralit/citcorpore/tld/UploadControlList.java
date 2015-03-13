package br.com.centralit.citcorpore.tld;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class UploadControlList extends BodyTagSupport {

    private static final long serialVersionUID = 4585511707555273663L;

    private String id;
    private String style;
    private String title;
    private String form;
    private String action;
    private String disabled;

    @Override
    public int doStartTag() throws JspException {
        try {
            String urlIframe;
            try {
                urlIframe = br.com.citframework.util.Constantes.getValue("SERVER_ADDRESS") + ((HttpServletRequest) pageContext.getRequest()).getContextPath()
                        + "/pages/refresh" + this.getId() + "List/refresh" + this.getId() + "List.load";
            } catch (final Exception e1) {
                throw new JspException(e1);
            }

            final JspWriter out = pageContext.getOut();
            out.println("<div style='border:1px solid black;' id='divUpload_" + this.getId() + "'>\n");
            out.println("<div style='display:none;background:#E3F0FD;' id='divMostraResultadoUpload_" + this.getId() + "'></div>\n");
            out.println("<iframe name='fraUpload_" + this.getId() + "' id='fraUpload_" + this.getId() + "' style='" + this.getStyle()
                    + ";width:100%; border: none;' src='" + urlIframe + "'></iframe>\n");
            out.println("</div>\n");

        } catch (final IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(final String id) {
        this.id = id;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(final String style) {
        this.style = style;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getForm() {
        return form;
    }

    public void setForm(final String form) {
        this.form = form;
    }

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(final String disabled) {
        this.disabled = disabled;
    }

}
