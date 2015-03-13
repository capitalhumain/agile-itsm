package br.com.centralit.citcorpore.util;

import br.com.centralit.citcorpore.bean.Attachment;

/**
 * @author breno.guimaraes
 * @author Dj
 *         Interface para tratamento de conteúdo de email com JavaMail API.
 */
public interface Renderable {

    Attachment getAttachment(final int i);

    int getAttachmentCount();

    String getBodytext();

    String getSubject();

}
