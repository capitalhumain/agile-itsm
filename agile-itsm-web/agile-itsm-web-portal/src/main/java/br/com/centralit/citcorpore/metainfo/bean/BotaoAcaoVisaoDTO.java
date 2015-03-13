package br.com.centralit.citcorpore.metainfo.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class BotaoAcaoVisaoDTO extends BaseEntity {

    public static String ACAO_GRAVAR = "1";
    public static String ACAO_LIMPAR = "2";
    public static String ACAO_EXCLUIR = "3";
    public static String ACAO_SCRIPT = "4";

    private Integer idBotaoAcaoVisao;
    private Integer idVisao;
    private String texto;
    private String acao;
    private String script;
    private String hint;
    private String icone;
    private Integer ordem;

    public Integer getIdBotaoAcaoVisao() {
        return idBotaoAcaoVisao;
    }

    public void setIdBotaoAcaoVisao(final Integer parm) {
        idBotaoAcaoVisao = parm;
    }

    public Integer getIdVisao() {
        return idVisao;
    }

    public void setIdVisao(final Integer parm) {
        idVisao = parm;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(final String parm) {
        texto = parm;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(final String parm) {
        acao = parm;
    }

    public String getScript() {
        return script;
    }

    public void setScript(final String parm) {
        script = parm;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(final String parm) {
        hint = parm;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(final String parm) {
        icone = parm;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(final Integer parm) {
        ordem = parm;
    }

}
