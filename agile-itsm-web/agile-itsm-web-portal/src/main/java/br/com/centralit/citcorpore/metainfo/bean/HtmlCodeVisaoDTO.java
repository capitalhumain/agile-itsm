package br.com.centralit.citcorpore.metainfo.bean;

import java.util.ArrayList;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class HtmlCodeVisaoDTO extends BaseEntity {

    public static HtmlCodePartDTO HTMLCODE_INIT = new HtmlCodePartDTO("INIT", "visaoAdm.inicioAreaCentral");
    public static HtmlCodePartDTO HTMLCODE_END = new HtmlCodePartDTO("END", "visaoAdm.finalAreaCentral");
    public static HtmlCodePartDTO HTMLCODE_INIT_FORM = new HtmlCodePartDTO("INIT_FORM", "visaoAdm.inicioFormulario");
    public static HtmlCodePartDTO HTMLCODE_END_FORM = new HtmlCodePartDTO("END_FORM", "visaoAdm.finalFormulario");
    public static HtmlCodePartDTO HTMLCODE_INIT_BUTTONS = new HtmlCodePartDTO("INIT_BUTTONS", "visaoAdm.inicioAreaBotao");
    public static HtmlCodePartDTO HTMLCODE_END_BUTTONS = new HtmlCodePartDTO("END_BUTTONS", "visaoAdm.finalAreaBotao");
    public static HtmlCodePartDTO HTMLCODE_SUPERIOR = new HtmlCodePartDTO("SUPERIOR", "visaoAdm.areaSuperior");

    private Integer idHtmlCodeVisao;
    private Integer idVisao;
    private String htmlCodeType;
    private String htmlCode;

    public static Collection<HtmlCodePartDTO> colHtmlCodeParts = new ArrayList<HtmlCodePartDTO>();

    static {
        colHtmlCodeParts.add(HTMLCODE_INIT);
        colHtmlCodeParts.add(HTMLCODE_END);
        colHtmlCodeParts.add(HTMLCODE_INIT_FORM);
        colHtmlCodeParts.add(HTMLCODE_END_FORM);
        colHtmlCodeParts.add(HTMLCODE_INIT_BUTTONS);
        colHtmlCodeParts.add(HTMLCODE_END_BUTTONS);
        colHtmlCodeParts.add(HTMLCODE_SUPERIOR);
    }

    public Integer getIdHtmlCodeVisao() {
        return idHtmlCodeVisao;
    }

    public void setIdHtmlCodeVisao(final Integer parm) {
        idHtmlCodeVisao = parm;
    }

    public Integer getIdVisao() {
        return idVisao;
    }

    public void setIdVisao(final Integer parm) {
        idVisao = parm;
    }

    public String getHtmlCodeType() {
        return htmlCodeType;
    }

    public void setHtmlCodeType(final String parm) {
        htmlCodeType = parm;
    }

    public String getHtmlCode() {
        return htmlCode;
    }

    public void setHtmlCode(final String parm) {
        htmlCode = parm;
    }

}
