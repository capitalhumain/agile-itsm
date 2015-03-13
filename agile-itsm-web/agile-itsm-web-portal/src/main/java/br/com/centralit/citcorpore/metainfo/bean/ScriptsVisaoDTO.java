package br.com.centralit.citcorpore.metainfo.bean;

import java.util.ArrayList;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class ScriptsVisaoDTO extends BaseEntity {

    public static ScriptEventDTO SCRIPT_LOAD = new ScriptEventDTO("LOAD", "visaoAdm.aoCarregarTela");
    public static ScriptEventDTO SCRIPT_VALIDADE = new ScriptEventDTO("VALIDADE", "itemRequisicaoProduto.validacao");
    public static ScriptEventDTO SCRIPT_ONCREATE = new ScriptEventDTO("ONCREATE", "visaoAdm.aoInserir");
    public static ScriptEventDTO SCRIPT_AFTERCREATE = new ScriptEventDTO("AFTERCREATE", "visaoAdm.aposInserir");
    public static ScriptEventDTO SCRIPT_ONUPDATE = new ScriptEventDTO("ONUPDATE", "visaoAdm.aoAtualizar");
    public static ScriptEventDTO SCRIPT_AFTERUPDATE = new ScriptEventDTO("AFTERUPDATE", "visaoAdm.aposAtualizar");
    public static ScriptEventDTO SCRIPT_ONDELETE = new ScriptEventDTO("ONDELETE", "visaoAdm.aoApagar");
    public static ScriptEventDTO SCRIPT_ONRESTORE = new ScriptEventDTO("ONRESTORE", "visaoAdm.aoRecuperar");
    public static ScriptEventDTO SCRIPT_ONSQLWHERESEARCH = new ScriptEventDTO("ONSQLWHERESEARCH", "visaoAdm.aoPrepararWhereBusca");
    public static ScriptEventDTO SCRIPT_ONSQLSEARCH = new ScriptEventDTO("ONSQLSEARCH", "visaoAdm.aoPrepararSQLBusca");

    public static String SCRIPT_EXECUTE_SERVER = "S";
    public static String SCRIPT_EXECUTE_CLIENT = "C";

    public static String JAVASCRIPT = "JAVASCRIPT";

    public static Collection<ScriptEventDTO> colScriptEvents = new ArrayList<ScriptEventDTO>();

    static {
        colScriptEvents.add(SCRIPT_LOAD);
        colScriptEvents.add(SCRIPT_VALIDADE);
        colScriptEvents.add(SCRIPT_ONCREATE);
        colScriptEvents.add(SCRIPT_AFTERCREATE);
        colScriptEvents.add(SCRIPT_ONUPDATE);
        colScriptEvents.add(SCRIPT_AFTERUPDATE);
        colScriptEvents.add(SCRIPT_ONDELETE);
        colScriptEvents.add(SCRIPT_ONRESTORE);
        colScriptEvents.add(SCRIPT_ONSQLWHERESEARCH);
        colScriptEvents.add(SCRIPT_ONSQLSEARCH);
    }

    private Integer idScriptsVisao;
    private Integer idVisao;
    private String typeExecute;
    private String scryptType;
    private String script;
    private String scriptLanguage;

    public Integer getIdScriptsVisao() {
        return idScriptsVisao;
    }

    public void setIdScriptsVisao(final Integer parm) {
        idScriptsVisao = parm;
    }

    public Integer getIdVisao() {
        return idVisao;
    }

    public void setIdVisao(final Integer parm) {
        idVisao = parm;
    }

    public String getTypeExecute() {
        return typeExecute;
    }

    public void setTypeExecute(final String parm) {
        typeExecute = parm;
    }

    public String getScryptType() {
        return scryptType;
    }

    public void setScryptType(final String parm) {
        scryptType = parm;
    }

    public String getScript() {
        return script;
    }

    public void setScript(final String parm) {
        script = parm;
    }

    public String getScriptLanguage() {
        return scriptLanguage;
    }

    public void setScriptLanguage(final String scriptLanguage) {
        this.scriptLanguage = scriptLanguage;
    }

}
