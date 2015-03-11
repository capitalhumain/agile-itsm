package br.com.centralit.citcorpore.ajaxForms;

import java.util.Calendar;
import java.util.Collection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;

import br.com.centralit.citajax.html.AjaxFormAction;
import br.com.centralit.citajax.html.DocumentHTML;
import br.com.centralit.citcorpore.bean.ExportacaoContratosDTO;
import br.com.centralit.citcorpore.bean.GrupoDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.centralit.citcorpore.negocio.ContratoService;
import br.com.centralit.citcorpore.negocio.ExportacaoContratosService;
import br.com.centralit.citcorpore.negocio.GrupoService;
import br.com.centralit.citcorpore.util.WebUtil;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilI18N;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ExportacaoContratos extends AjaxFormAction {

    @Override
    public Class getBeanClass() {
        return ExportacaoContratosDTO.class;
    }

    @Override
    public void load(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UsuarioDTO usuario = WebUtil.getUsuario(request);
        if (usuario == null) {
            document.alert(UtilI18N.internacionaliza(request, "citcorpore.comum.sessaoExpirada"));
            document.executeScript("window.location = '" + Constantes.getValue("SERVER_ADDRESS") + request.getContextPath() + "'");
            return;
        }

        document.getSelectById("idContrato").removeAllOptions();
        final ContratoService contratoService = (ContratoService) ServiceLocator.getInstance().getService(ContratoService.class,
                WebUtil.getUsuarioSistema(request));
        final Collection colContratos = contratoService.listAtivos();
        document.getSelectById("idContrato").addOption("", UtilI18N.internacionaliza(request, "citcorpore.comum.selecione"));
        document.getSelectById("idContrato").addOptions(colContratos, "idContrato", "numero", null);

        final GrupoService grupoService = (GrupoService) ServiceLocator.getInstance().getService(GrupoService.class, WebUtil.getUsuarioSistema(request));
        final Collection<GrupoDTO> colGrupos = grupoService.findGruposAtivos();

        document.executeScript("$('#grupos').html('');");
        document.executeScript("$('#grupos').append('<input type=\"checkbox\" name=\"selectTodosGrupos\" id=\"selectTodosGrupos\" value=\"all\">&nbsp;"
                + UtilI18N.internacionaliza(request, "citcorpore.comum.selecionarTodos") + "<br/><br/>');");
        document.executeScript("$('#grupos').append('<ul></ul>');");
        for (final GrupoDTO grupoDto : colGrupos) {
            document.executeScript("$('#grupos ul').append('<li><input type=\"checkbox\" name=\"idGrupos\" id=\"idGrupos" + grupoDto.getIdGrupo()
                    + "\" value=\"" + grupoDto.getIdGrupo() + "\">&nbsp;" + grupoDto.getNome() + "</li>');");
        }

        final ExportacaoContratosDTO exportacaoContratosDto = (ExportacaoContratosDTO) document.getBean();
        if (exportacaoContratosDto != null && exportacaoContratosDto.getExport() != null && exportacaoContratosDto.getExport().equalsIgnoreCase("y")) {
            downloadArquivo(document, request, response, exportacaoContratosDto);
        }
    }

    public void downloadArquivo(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response,
            final ExportacaoContratosDTO exportacaoContratosDto) throws Exception {
        final ExportacaoContratosService exportacaoContratosService = (ExportacaoContratosService) ServiceLocator.getInstance().getService(
                ExportacaoContratosService.class, WebUtil.getUsuarioSistema(request));

        byte[] buffer;
        final Calendar c = Calendar.getInstance();

        if (exportacaoContratosDto.getExportarCatalogoServico() == null) {
            exportacaoContratosDto.setExportarCatalogoServico("y");
        }

        final String xmlString = StringEscapeUtils.unescapeHtml(exportacaoContratosService.recuperaXmlTabelas(exportacaoContratosDto.getIdContrato(),
                exportacaoContratosDto.getIdGrupos(), exportacaoContratosDto.getExportarUnidades(), exportacaoContratosDto.getExportarAcordoNivelServico(),
                exportacaoContratosDto.getExportarCatalogoServico()));

        if (!xmlString.equals("")) {
            buffer = xmlString.getBytes("ISO-8859-1");
        } else {
            buffer = "<xml></xml>".getBytes();
        }

        response.setContentLength(buffer.length);
        response.setContentType("text/xml");
        response.setHeader("Content-Disposition",
                "attachment; filename=InfoContrato_" + UtilDatas.getDataAtual() + "_" + c.get(Calendar.HOUR_OF_DAY) + "h" + c.get(Calendar.MINUTE) + ".xml");

        final ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(buffer);
        outputStream.flush();
        outputStream.close();
    }

    public void atualizaGrupos(final DocumentHTML document, final HttpServletRequest request, final HttpServletResponse response) throws ServiceException,
    Exception {
        final ExportacaoContratosDTO exportacaoContratosDto = (ExportacaoContratosDTO) document.getBean();

        final GrupoService grupoService = (GrupoService) ServiceLocator.getInstance().getService(GrupoService.class, WebUtil.getUsuarioSistema(request));

        document.executeScript("$('#grupos').html('');");
        document.executeScript("$('#grupos').append('<input type=\"checkbox\" name=\"selectTodosGrupos\" id=\"selectTodosGrupos\" value=\"all\">&nbsp;"
                + UtilI18N.internacionaliza(request, "citcorpore.comum.selecionarTodos") + "<br/><br/>');");
        if (exportacaoContratosDto != null && exportacaoContratosDto.getIdContrato() != null) {
            final Collection<GrupoDTO> colGrupos = grupoService.listGrupoAtivosByIdContrato(exportacaoContratosDto.getIdContrato());
            if (colGrupos != null) {
                document.executeScript("$('#grupos').append('<ul></ul>');");
                for (final GrupoDTO grupoDto : colGrupos) {
                    document.executeScript("$('#grupos ul').append('<li><input type=\"checkbox\" name=\"idGrupos\" id=\"idGrupos" + grupoDto.getIdGrupo()
                            + "\" value=\"" + grupoDto.getIdGrupo() + "\">&nbsp;" + grupoDto.getNome() + "</li>');");
                }
            } else {
                document.executeScript("$('#grupos').html('" + UtilI18N.internacionaliza(request, "exportacaoContratos.nenhumGrupo") + "');");
            }
        } else {
            final Collection<GrupoDTO> colGrupos = grupoService.findGruposAtivos();
            document.executeScript("$('#grupos').append('<ul></ul>');");
            for (final GrupoDTO grupoDto : colGrupos) {
                document.executeScript("$('#grupos ul').append('<li><input type=\"checkbox\" name=\"idGrupos\" id=\"idGrupos" + grupoDto.getIdGrupo()
                        + "\" value=\"" + grupoDto.getIdGrupo() + "\">&nbsp;" + grupoDto.getNome() + "</li>');");
            }
        }
    }

}
