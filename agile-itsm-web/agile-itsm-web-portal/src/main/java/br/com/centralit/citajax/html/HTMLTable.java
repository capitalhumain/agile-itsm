package br.com.centralit.citajax.html;

import java.util.Collection;

import br.com.centralit.citajax.util.CitAjaxWebUtil;
import br.com.centralit.citajax.util.JavaScriptUtil;

@SuppressWarnings({"rawtypes"})
public class HTMLTable extends HTMLElement {

    public HTMLTable(final String idParm, final DocumentHTML documentParm) {
        super(idParm, documentParm);
    }

    @Override
    public String getType() {
        return TABLE;
    }

    /**
     * Adiciona um linha em uma tabela
     *
     * @param obj
     * @param propertyNamesAddTable
     * @param propertyNamesVerifDuplAddTable
     * @param mensagemDuplicacaoCasoOcorra
     * @param arFuncoesExec
     * @param funcaoClick
     * @param funcaoVerificacao
     * @throws Exception
     */
    public void addRow(final Object obj, final String[] propertyNamesAddTable, final String[] propertyNamesVerifDuplAddTable,
            final String mensagemDuplicacaoCasoOcorra, final String[] arFuncoesExec, final String funcaoClick, final String funcaoVerificacao) throws Exception {
        String strColunas = "";
        if (propertyNamesAddTable != null) {
            for (final String element : propertyNamesAddTable) {
                if (!strColunas.equalsIgnoreCase("")) {
                    strColunas += ",";
                }
                strColunas = strColunas + "'" + element + "'";
            }
        }
        if (strColunas.equalsIgnoreCase("")) {
            strColunas = "null";
        } else {
            strColunas = "[" + strColunas + "]";
        }

        String strColunasVerifDup = "";
        if (propertyNamesVerifDuplAddTable != null) {
            for (final String element : propertyNamesVerifDuplAddTable) {
                if (!strColunasVerifDup.equalsIgnoreCase("")) {
                    strColunasVerifDup += ",";
                }
                strColunasVerifDup = strColunasVerifDup + "'" + element + "'";
            }
        }
        if (strColunasVerifDup.equalsIgnoreCase("")) {
            strColunasVerifDup = "null";
        } else {
            strColunasVerifDup = "[" + strColunasVerifDup + "]";
        }

        String strFuncoesExec = "";
        if (arFuncoesExec != null) {
            for (final String element : arFuncoesExec) {
                if (!strFuncoesExec.equalsIgnoreCase("")) {
                    strFuncoesExec += ",";
                }
                strFuncoesExec = strFuncoesExec + element;
            }
        }
        if (strFuncoesExec.equalsIgnoreCase("")) {
            strFuncoesExec = "null";
        } else {
            strFuncoesExec = "[" + strFuncoesExec + "]";
        }

        String strFuncaoClick = "null";
        if (funcaoClick != null) {
            strFuncaoClick = funcaoClick;
        }

        String strFuncaoVerificacao = "null";
        if (funcaoVerificacao != null) {
            strFuncaoVerificacao = funcaoVerificacao;
        }

        final String objSerializado = CitAjaxWebUtil.serializeObject(obj, false, this.getDocument().getLanguage());

        this.setCommandExecute("HTMLUtils.addRow('" + this.getId() + "', null, null, ObjectUtils.deserializeObject('" + objSerializado + "'), " + strColunas
                + ", " + strColunasVerifDup + ", '" + JavaScriptUtil.escapeJavaScript(mensagemDuplicacaoCasoOcorra) + "', " + strFuncoesExec + ", "
                + strFuncaoClick + ", " + strFuncaoVerificacao + ", false)");
    }

    /**
     * Atualiza uma linha da tabela pelo indice.
     *
     * @param obj
     * @param propertyNamesAddTable
     * @param propertyNamesVerifDuplAddTable
     * @param mensagemDuplicacaoCasoOcorra
     * @param arFuncoesExec
     * @param funcaoClick
     * @param funcaoVerificacao
     * @param indexItem
     * @throws Exception
     */
    public void updateRow(final Object obj, final String[] propertyNamesAddTable, final String[] propertyNamesVerifDuplAddTable,
            final String mensagemDuplicacaoCasoOcorra, final String[] arFuncoesExec, final String funcaoClick, final String funcaoVerificacao,
            final int indexItem) throws Exception {
        String strColunas = "";
        if (propertyNamesAddTable != null) {
            for (final String element : propertyNamesAddTable) {
                if (!strColunas.equalsIgnoreCase("")) {
                    strColunas += ",";
                }
                strColunas = strColunas + "'" + element + "'";
            }
        }
        if (strColunas.equalsIgnoreCase("")) {
            strColunas = "null";
        } else {
            strColunas = "[" + strColunas + "]";
        }

        String strColunasVerifDup = "";
        if (propertyNamesVerifDuplAddTable != null) {
            for (final String element : propertyNamesVerifDuplAddTable) {
                if (!strColunasVerifDup.equalsIgnoreCase("")) {
                    strColunasVerifDup += ",";
                }
                strColunasVerifDup = strColunasVerifDup + "'" + element + "'";
            }
        }
        if (strColunasVerifDup.equalsIgnoreCase("")) {
            strColunasVerifDup = "null";
        } else {
            strColunasVerifDup = "[" + strColunasVerifDup + "]";
        }

        String strFuncoesExec = "";
        if (arFuncoesExec != null) {
            for (final String element : arFuncoesExec) {
                if (!strFuncoesExec.equalsIgnoreCase("")) {
                    strFuncoesExec += ",";
                }
                strFuncoesExec = strFuncoesExec + element;
            }
        }
        if (strFuncoesExec.equalsIgnoreCase("")) {
            strFuncoesExec = "null";
        } else {
            strFuncoesExec = "[" + strFuncoesExec + "]";
        }

        String strFuncaoClick = "null";
        if (funcaoClick != null) {
            strFuncaoClick = funcaoClick;
        }

        String strFuncaoVerificacao = "null";
        if (funcaoVerificacao != null) {
            strFuncaoVerificacao = funcaoVerificacao;
        }

        final String objSerializado = CitAjaxWebUtil.serializeObject(obj, false, this.getDocument().getLanguage());

        this.setCommandExecute("HTMLUtils.updateRow('" + this.getId() + "', null, null, ObjectUtils.deserializeObject('" + objSerializado + "'), " + strColunas
                + ", " + strColunasVerifDup + ", '" + JavaScriptUtil.escapeJavaScript(mensagemDuplicacaoCasoOcorra) + "', " + strFuncoesExec + ", "
                + strFuncaoClick + ", " + strFuncaoVerificacao + ", " + indexItem + ")");
    }

    /**
     * Apaga uma linha da tabela
     *
     * @param indice
     */
    public void deleteRow(final int indice) {
        this.setCommandExecute("HTMLUtils.deleteRow('" + this.getId() + "', " + indice + ")");
    }

    /**
     * Apaga todas linhas da tabela
     *
     */
    public void deleteAllRows() {
        this.setCommandExecute("HTMLUtils.deleteAllRows('" + this.getId() + "')");
    }

    /**
     * Adiciona uma colecao a uma tabela
     *
     * @param col
     * @param propertyNamesAddTable
     * @param propertyNamesVerifDuplAddTable
     * @param mensagemDuplicacaoCasoOcorra
     * @param arFuncoesExec
     * @param funcaoClick
     * @param funcaoVerificacao
     * @throws Exception
     */
    public void addRowsByCollection(final Collection col, final String[] propertyNamesAddTable, final String[] propertyNamesVerifDuplAddTable,
            final String mensagemDuplicacaoCasoOcorra, final String[] arFuncoesExec, final String funcaoClick, final String funcaoVerificacao) throws Exception {
        String strColunas = "";
        if (propertyNamesAddTable != null) {
            for (final String element : propertyNamesAddTable) {
                if (!strColunas.equalsIgnoreCase("")) {
                    strColunas += ",";
                }
                strColunas = strColunas + "'" + element + "'";
            }
        }
        if (strColunas.equalsIgnoreCase("")) {
            strColunas = "null";
        } else {
            strColunas = "[" + strColunas + "]";
        }

        String strColunasVerifDup = "";
        if (propertyNamesVerifDuplAddTable != null) {
            for (final String element : propertyNamesVerifDuplAddTable) {
                if (!strColunasVerifDup.equalsIgnoreCase("")) {
                    strColunasVerifDup += ",";
                }
                strColunasVerifDup = strColunasVerifDup + "'" + element + "'";
            }
        }
        if (strColunasVerifDup.equalsIgnoreCase("")) {
            strColunasVerifDup = "null";
        } else {
            strColunasVerifDup = "[" + strColunasVerifDup + "]";
        }

        String strFuncoesExec = "";
        if (arFuncoesExec != null) {
            for (final String element : arFuncoesExec) {
                if (!strFuncoesExec.equalsIgnoreCase("")) {
                    strFuncoesExec += ",";
                }
                strFuncoesExec = strFuncoesExec + element;
            }
        }
        if (strFuncoesExec.equalsIgnoreCase("")) {
            strFuncoesExec = "null";
        } else {
            strFuncoesExec = "[" + strFuncoesExec + "]";
        }

        String strFuncaoClick = "null";
        if (funcaoClick != null) {
            strFuncaoClick = funcaoClick;
        }

        String strFuncaoVerificacao = "null";
        if (funcaoVerificacao != null) {
            strFuncaoVerificacao = funcaoVerificacao;
        }

        final String strObjsSerializados = CitAjaxWebUtil.serializeObjects(col, false, this.getDocument().getLanguage());

        this.setCommandExecute("HTMLUtils.addRowsByCollection('" + this.getId()
                + "', null, null, ObjectUtils.deserializeCollectionFromString(ObjectUtils.decodificaAspasApostrofe('" + strObjsSerializados + "')), "
                + strColunas + ", " + strColunasVerifDup + ", '" + JavaScriptUtil.escapeJavaScript(mensagemDuplicacaoCasoOcorra) + "', " + strFuncoesExec
                + ", " + strFuncaoClick + ", " + strFuncaoVerificacao + ")");
    }

    public void applyStyleClassInAllCells(final String classNameParm) {
        this.setCommandExecute("HTMLUtils.applyStyleClassInAllCells('" + this.getId() + "','" + classNameParm + "')");
    }

}
