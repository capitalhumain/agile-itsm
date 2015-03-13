package br.com.centralit.citcorpore.tld;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import br.com.citframework.util.UtilI18N;
import br.com.citframework.util.UtilStrings;

public class UploadControl extends BodyTagSupport {

    private static final long serialVersionUID = -5234599932762700803L;

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
                        + "/pages/refresh" + this.getId() + "/refresh" + this.getId() + ".load";
            } catch (final Exception e1) {
                throw new JspException(e1);
            }

            final JspWriter out = pageContext.getOut();
            out.println("<div style='border:1px solid #ccc;padding:10px;' id='divUpload_" + this.getId() + "'>\n");
            out.println("<div id='divMostraUpload_" + this.getId() + "'>\n");
            if (!UtilStrings.nullToVazio(disabled).equalsIgnoreCase("true")) {
                out.println("<table border='0'>");
                out.println("<tr>");
                out.println("<td>");
                out.println(UtilI18N.internacionaliza((HttpServletRequest) pageContext.getRequest(), "citcorpore.comum.arquivo") + ":");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type='file'  size='50' name='file_" + this.getId() + "' id='file_" + this.getId() + "'/>");
                out.println("</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td >");
                out.println(UtilI18N.internacionaliza((HttpServletRequest) pageContext.getRequest(), "citcorpore.comum.descricao") + ":");
                out.println("</td>");
                out.println("<td>");
                out.println("<input type='text' name='descUploadFile_" + this.getId() + "' size='50' maxlength='70'/>");
                out.println("<input type='hidden' id='upFileNameHidden' name='nameFile_" + this.getId() + "' value='arquivo' />");
                out.println("<input type='button' class='btn btn-icon btn-primary ui-button' name='btnAdd" + this.getId() + "' value='"
                        + UtilI18N.internacionaliza((HttpServletRequest) pageContext.getRequest(), "citcorpore.comum.adicionar") + "' title=' "
                        + UtilI18N.internacionaliza((HttpServletRequest) pageContext.getRequest(), "citcorpore.ged.enviararquivo") + "' onclick='upload_"
                        + this.getId() + "()'>");
                out.println("</td>");
                out.println("</tr>");
                out.println("</table>");
            }
            out.println("</div>\n");
            out.println("<div style='display:none;background:#E3F0FD;' id='divMostraResultadoUpload_" + this.getId() + "'></div>\n");
            out.println("<iframe name='fraUpload_" + this.getId() + "' id='fraUpload_" + this.getId() + "' style='" + this.getStyle()
                    + "; border: none;' src='" + urlIframe + "'></iframe>\n");
            out.println("</div>\n");

            out.println("<script>");
            out.println("function upload_" + this.getId() + "(){");
            String actionAux;
            try {
                actionAux = br.com.citframework.util.Constantes.getValue("SERVER_ADDRESS") + ((HttpServletRequest) pageContext.getRequest()).getContextPath()
                        + this.getAction();
            } catch (final Exception e) {
                throw new JspException(e);
            }
            out.println("uploadFile(" + this.getForm() + ", '" + actionAux + "', 'divMostraResultadoUpload_" + this.getId() + "', '<b><font color=\"red\">"
                    + UtilI18N.internacionaliza((HttpServletRequest) pageContext.getRequest(), "citcorpore.ged.enviandoarquivo")
                    + "...</font></b>', '<b><font color=\"red\">"
                    + UtilI18N.internacionaliza((HttpServletRequest) pageContext.getRequest(), "citcorpore.ged.msg.errocarregar") + "</font></b>', 'fraUpload_"
                    + this.getId() + "', 'file_" + this.getId() + "');");
            out.println("}");

            out.println("function " + this.getId() + "() {}");
            out.println(this.getId() + ".refresh = function(){");
            out.println("document.getElementById('fraUpload_" + this.getId() + "').src = '" + urlIframe + "';");
            out.println("};");

            out.println(this.getId() + ".clear = function(){");
            out.println("try{document.getElementById('file').text = '';}catch(e){}");
            out.println("document.getElementById('fraUpload_" + this.getId() + "').src = 'about:blank';");
            out.println("};");

            out.println("</script>");
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
