package br.com.centralit.citcorpore.bean;

/**
 * @author breno.guimaraes
 *         Conteúdo de emails recebidos.
 */
public class Attachment {

    private String contenttype;
    private String filename;
    private byte[] content;
    private String contentid;

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(final String contenttype) {
        this.contenttype = contenttype;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(final String filename) {
        this.filename = filename;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(final byte[] content) {
        this.content = content;
    }

    public String getContentid() {
        return contentid;
    }

    public void setContentid(final String contentid) {
        this.contentid = contentid;
    }

}
