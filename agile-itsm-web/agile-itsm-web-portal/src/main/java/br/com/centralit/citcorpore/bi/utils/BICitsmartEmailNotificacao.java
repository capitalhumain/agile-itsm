package br.com.centralit.citcorpore.bi.utils;

import java.util.Map;

import br.com.centralit.citcorpore.mail.MensagemEmail;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.Enumerados.ParametroSistema;
import br.com.centralit.citcorpore.util.ParametroUtil;

/**
 * @author euler.ramos
 *
 */
public class BICitsmartEmailNotificacao {

    String emailGeral;
    String emailConexao;
    String emailFrom;
    Integer idModeloEmail;
    Map<String, String> map;
    boolean notificar;

    public BICitsmartEmailNotificacao() {
        emailGeral = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.BICITSMART_EMAIL_NOTIFICACAO_GERAL, null);
        emailFrom = "citsmart@centralit.com.br";
        notificar = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.BICITSMART_NOTIFICAR_ERRO_IMPORTACAO_POR_EMAIL, "N")
                .equalsIgnoreCase("S");
    }

    private void enviaEmailGeral() {
        try {
            final MensagemEmail mensagemEmail = new MensagemEmail(idModeloEmail, map);
            if (emailGeral != null && emailGeral.length() > 0) {
                mensagemEmail.envia(emailGeral, null, emailFrom);
            }
        } catch (final Exception e) {
            e.printStackTrace();
            System.out.println("CITSMART - Problema no envio de notificação de erro da Importação Automática BICitsmart; e-mail geral: " + emailGeral);
        }
    }

    private void enviaEmailConexao() {
        try {
            final MensagemEmail mensagemEmail = new MensagemEmail(idModeloEmail, map);
            if (emailConexao != null && emailConexao.length() > 0) {
                mensagemEmail.envia(emailConexao, null, emailFrom);
            }
        } catch (final Exception e) {
            e.printStackTrace();
            System.out.println("CITSMART - Problema no envio de notificação de erro da Importação Automática BICitsmart; e-mail conexão: " + emailConexao);
        }
    }

    public void envia() {
        // Se o parâmetro permite a notificação
        if (notificar) {
            if (idModeloEmail != null && idModeloEmail > 0) {
                try {
                    this.enviaEmailGeral();
                    this.enviaEmailConexao();
                } catch (final Exception e) {
                    e.printStackTrace();
                    System.out.println("CITSMART - Problema no envio de notificação de erro da Importação Automática BICitsmart;");
                }
            }
        }
    }

    public void setEmailConexao(final String emailConexao) {
        this.emailConexao = emailConexao;
    }

    public void setModeloEmail(final String modelo) {
        switch (modelo) {
        case "Exceção":
            idModeloEmail = Integer.parseInt(ParametroUtil
                    .getValorParametroCitSmartHashMap(ParametroSistema.BICITSMART_ID_MODELO_EMAIL_ERRO_AGEND_EXCECAO, "0").trim());
            break;
        case "Específico":
            idModeloEmail = Integer.parseInt(ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.BICITSMART_ID_MODELO_EMAIL_ERRO_AGEND_ESPECIFICO,
                    "0").trim());
            break;
        case "Padrão":
            idModeloEmail = Integer.parseInt(ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.BICITSMART_ID_MODELO_EMAIL_ERRO_AGEND_PADRAO, "0")
                    .trim());
            break;
        case "Parâmetro":
            idModeloEmail = Integer.parseInt(ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.BICITSMART_ID_MODELO_EMAIL_ERRO_PARAMETRO, "0")
                    .trim());
            break;
        case "Problema":
            idModeloEmail = Integer.parseInt(ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.BICITSMART_ID_MODELO_EMAIL_ERRO_EXCECUCAO, "0")
                    .trim());
            break;
        default:
            idModeloEmail = 0;
        }
    }

    public void setMap(final Map<String, String> map) {
        this.map = map;
    }

}
