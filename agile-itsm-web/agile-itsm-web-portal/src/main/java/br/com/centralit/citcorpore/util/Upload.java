/*
 * Created on 15/07/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.com.centralit.citcorpore.util;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author CentralIT
 */
public class Upload {

    public void doUpload(final HttpServletRequest request, final Collection colFilesUpload) throws Exception {
        final FileItemFactory factory = new DiskFileItemFactory();
        final ServletFileUpload fu = new ServletFileUpload(factory);
        fu.setSizeMax(-1);
        fu.setFileSizeMax(-1);

        final List fileItems = fu.parseRequest(request);
        final Iterator i = fileItems.iterator();
        FileItem fi;
        UploadItem upIt;
        File arquivo;
        final Iterator itAux = colFilesUpload.iterator();
        while (itAux.hasNext()) {
            upIt = (UploadItem) itAux.next();
            while (i.hasNext()) {
                fi = (FileItem) i.next();
                if (upIt.getNomeArquivo().toUpperCase().trim().equals(fi.getName().toUpperCase().trim())) {
                    arquivo = new File(upIt.getPathArquivo() + "\\" + upIt.getNomeArquivo());
                    fi.write(arquivo);
                }
            }
        }
    }

    /**
     * Modificando a forma de anexar, foi mudado para um método não depreciado.
     * 
     * @param request
     * @return
     * @throws Exception
     * @author mario.haysaki
     */
    public HashMap[] doUploadAll(final HttpServletRequest request) throws Exception {
        final HashMap[] hshRetorno = new HashMap[2];
        final DiskFileItemFactory fact = new DiskFileItemFactory();

        String DISKFILEUPLOAD_REPOSITORYPATH = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.DISKFILEUPLOAD_REPOSITORYPATH, "");
        if (DISKFILEUPLOAD_REPOSITORYPATH == null) {
            DISKFILEUPLOAD_REPOSITORYPATH = "";
        }
        final File repositoryPath = new File(DISKFILEUPLOAD_REPOSITORYPATH);
        fact.setRepository(repositoryPath);

        final ServletFileUpload fu = new ServletFileUpload(fact);

        try {
            /**
             * @author pedro.lino, Danilo.Lisboa
             *         Necessário especificar o encoding, pois quando existe dois ou mais uploads na mesma tela estava vindo com caracteres especiais;
             *         NÃO RETIRAR O TRATAMENTO DE ENCODING.
             * **/
            fu.setHeaderEncoding("UTF-8");
            fu.setSizeMax(-1);

            hshRetorno[0] = new HashMap<>(); // Retorna os campos de formulário
            hshRetorno[1] = new HashMap<>(); // Retorna os nomes de arquivos

            final List fileItems = fu.parseRequest(request);
            final Iterator i = fileItems.iterator();
            FileItem fi;
            while (i.hasNext()) {
                fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    hshRetorno[1].put(CITCorporeUtil.getNameFile(fi.getName()), fi);
                    hshRetorno[0].put(fi.getFieldName().toUpperCase(), CITCorporeUtil.getNameFile(fi.getName()));
                    request.setAttribute(fi.getFieldName(), CITCorporeUtil.getNameFile(fi.getName()));
                } else {
                    // System.err.println(fi.getFieldName().toUpperCase() + ": " + fi.getString());
                    hshRetorno[0].put(fi.getFieldName().toUpperCase(), fi.getString());
                    request.setAttribute(fi.getFieldName(), fi.getString());
                }
            }
        } catch (final Exception e) {
            // TODO: handle exception
        }

        return hshRetorno;
    }

}
