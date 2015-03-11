package br.com.citframework.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import br.com.citframework.excecao.LogicException;

/**
 * Classe de utilitarios Geral
 * Caso procure algo especifico, procure as demais classes de utilitarios.
 *
 * @author ney
 */
public class Util {

    /**
     * Retorna uma lista de programções semanais
     *
     * @param hrIncial
     *            String
     * @param hrFinal
     *            String
     * @param intervalo
     *            BigDecimal
     * @return ArrayList
     * @throws LogicException
     * @throws ParseException
     */
    public static ArrayList getHorasProgramacaoSemanal(final String hrIncial, final String hrFinal, final BigDecimal intervalo) throws LogicException {

        Date horaInicial = UtilFormatacao.formataHoraBigDecimalToDate(hrIncial);
        final Date horaFinal = UtilFormatacao.formataHoraBigDecimalToDate(hrFinal);

        final GregorianCalendar calendarHoraInicial = new GregorianCalendar();
        calendarHoraInicial.setTime(horaInicial);

        final GregorianCalendar calendarHoraFinal = new GregorianCalendar();
        final ArrayList horas = new ArrayList();
        final String minuto = String.valueOf(intervalo);

        calendarHoraFinal.setTime(horaFinal);
        while (true) {
            horas.add(horaInicial);
            calendarHoraInicial.add(Calendar.MINUTE, Integer.valueOf(minuto).intValue());
            horaInicial = calendarHoraInicial.getTime();
            if (horaInicial.getTime() >= horaFinal.getTime()) {
                horas.add(horaInicial);
                break;
            }
        }
        return horas;
    }

    public static Object getValorAtributo(final Object obj, final String atrib) {
        Object result = null;
        try {
            result = Reflexao.getPropertyValue(obj, atrib);
        } catch (final Exception e) {
            return result;
        }
        return result;

    }

    public static final void setValorAtributo(final String atributo, final Object obj, Object valor) throws Exception {
        final StringTokenizer str = new StringTokenizer(atributo, ".");
        int elementos = str.countTokens();
        String elemento = "";
        if (valor instanceof BigDecimal) {
            valor = UtilNumbersAndDecimals.changeBigDecimalToDouble((BigDecimal) valor);
        }
        try {

            while (str.hasMoreElements()) {
                elemento = elemento + str.nextElement().toString();
                if (elementos > 1) {

                    // Verificar se ja foi instanciado
                    final Object objAux = Reflexao.getPropertyValue(obj, elemento);
                    if (objAux == null) {
                        Reflexao.setPropertyValue(obj, elemento, Reflexao.getReturnType(obj, elemento).newInstance());
                    }
                    elementos = elementos - 1;
                    elemento = elemento + ".";

                } else {
                    Reflexao.setPropertyValue(obj, elemento, valor);
                }
            }
        } catch (final IllegalArgumentException iaex) {
            throw new Exception("O tipo de dado do atributo " + elemento + " da classe " + obj.getClass().getName()
                    + " Ã© diferente do tipo usado no banco de dados " + valor.getClass().getName());
        }

    }

    public static final Object getObjectValue(final Collection lista, final String atributo, final Object valor) {
        final Iterator it = lista.iterator();
        while (it.hasNext()) {
            final Object obj = it.next();
            final Object val = getValorAtributo(obj, atributo);
            if (val.equals(valor)) {
                return obj;
            }
        }
        return null;
    }

    public static final void geraXml(final String arquivo, final List lista) throws FileNotFoundException {
        final XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(arquivo)));
        e.writeObject(lista);
        e.close();

    }

    public static final List recuperaXML(final String arquivo) throws FileNotFoundException {
        final XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(arquivo)));
        final Object result = d.readObject();
        d.close();
        return (List) result;
    }

    public static List converteExcessao(final Throwable e) {

        final List result = new ArrayList();
        final StackTraceElement[] ste = e.getStackTrace();

        final SimpleDateFormat spd = new SimpleDateFormat("dd/MM/yyyy  hh:mm:ss");
        result.add(e.getClass() + ": " + e.getMessage() + ": " + spd.format(new Date()));
        for (final StackTraceElement element : ste) {
            result.add("	at " + element.getClassName() + "." + element.getMethodName() + "(" + element.getFileName() + ":" + element.getLineNumber() + ')');
        }

        return result;

    }

    public static Collection getResultadoImplosao(final String conta, final String mascara) {
        final String contaMask = setMascaraContabil(conta, mascara);
        final List lista = new ArrayList();
        final StringTokenizer tok = new StringTokenizer(contaMask, ".");
        String contaImplosao = "";
        while (tok.hasMoreTokens()) {
            contaImplosao = contaImplosao + tok.nextToken();
            lista.add(ajustaContaContabil(contaImplosao, mascara));
        }
        return lista;

    }

    public static String setMascaraContabil(String conta, final String mascara) {

        String result = "";
        final StringTokenizer tok = new StringTokenizer(mascara, ".");
        conta = ajustaContaContabil(conta, mascara);
        int anterior = 0;
        while (tok.hasMoreTokens()) {
            final String elemento = tok.nextToken();
            if (anterior != 0) {
                result = result + "." + conta.substring(anterior, anterior + elemento.length());
            } else {
                result = result + conta.substring(anterior, anterior + elemento.length());
            }

            anterior = anterior + elemento.length();

        }

        return result;

    }

    public static String ajustaContaContabil(final String conta, final String mascara) {

        final StringTokenizer tok = new StringTokenizer(mascara, ".");
        String tmp = "";
        String result = conta;
        while (tok.hasMoreTokens()) {
            final String elemento = tok.nextToken();
            tmp = tmp + elemento;
        }

        final int iTamanhoConta = conta.length();
        final int iTamanhoMascara = tmp.length();
        if (iTamanhoConta < iTamanhoMascara) {
            final int resto = iTamanhoMascara - iTamanhoConta;

            for (int i = 0; i < resto; i++) {
                result = result + "0";
            }

        }

        return result;

    }

    public static String getJavaType(final int type) {
        String result = null;
        switch (type) {
        case Types.BIGINT:
            result = "Integer";
            break;
        case Types.DATE:

            result = "java.sql.Date";
            break;
        case Types.BLOB:

            result = "java.sql.Blob";
            break;
        case Types.BOOLEAN:

            result = "Boolean";
            break;
        case Types.CHAR:

            result = "String";
            break;
        case Types.DOUBLE:

            result = "Double";
            break;
        case Types.FLOAT:

            result = "Float";
            break;
        case Types.INTEGER:

            result = "Integer";
            break;
        case Types.JAVA_OBJECT:

            result = "Object";
            break;
        case Types.LONGVARCHAR:

            result = "String";
            break;
        case Types.NUMERIC:

            result = "Double";
            break;
        case Types.REAL:

            result = "Double";
            break;
        case Types.SMALLINT:

            result = "Integer";
            break;
        case Types.TIME:

            result = "java.sql.Timestamp";
            break;

        case Types.TIMESTAMP:

            result = "java.sql.Timestamp";
            break;
        case Types.TINYINT:

            result = "Integer";
            break;

        case Types.VARCHAR:
            result = "String";
            break;
        default:
            result = null;
            break;
        }

        return result;

    }

    public static Integer[] convertStrToArrayInteger(final String strParaConvert, final String token) {
        if (strParaConvert == null) {
            return null;
        }
        if (strParaConvert.equalsIgnoreCase("")) {
            return null;
        }
        final String strAux[] = strParaConvert.split(token);
        if (strAux == null) {
            return null;
        }
        final Integer[] intRetorno = new Integer[strAux.length];

        for (int i = 0; i < strAux.length; i++) {
            try {
                if ("".equalsIgnoreCase(strAux[i].trim())) {
                    intRetorno[i] = new Integer(0);
                } else {
                    intRetorno[i] = new Integer(Integer.parseInt(strAux[i]));
                }
            } catch (final Exception e) {
                intRetorno[i] = null;
            }
        }
        return intRetorno;
    }

    public static BigDecimal[] convertStrToArrayBigDecimal(final String strParaConvert, final String token) {
        if (strParaConvert == null) {
            return null;
        }
        if (strParaConvert.equalsIgnoreCase("")) {
            return null;
        }
        final String strAux[] = strParaConvert.split(token);
        if (strAux == null) {
            return null;
        }
        final BigDecimal[] intRetorno = new BigDecimal[strAux.length];

        for (int i = 0; i < strAux.length; i++) {
            try {
                if ("".equalsIgnoreCase(strAux[i].trim())) {
                    final double x = 0;
                    intRetorno[i] = new BigDecimal(x);
                } else {
                    intRetorno[i] = new BigDecimal(strAux[i]);
                }
            } catch (final Exception e) {
                intRetorno[i] = null;
            }
        }
        return intRetorno;
    }

    public static String getTituloHistoricoExtintor(final Integer id) {
        return getMapHistoricoExtintor().get(id.toString()).toString();
    }

    private static Map getMapHistoricoExtintor() {
        final Map mapHistoricoExtintor = new HashMap();
        mapHistoricoExtintor.put("2", "Inspecionado");
        mapHistoricoExtintor.put("3", "Reparado");
        mapHistoricoExtintor.put("4", "Instrucao");
        mapHistoricoExtintor.put("5", "Incendio");
        mapHistoricoExtintor.put("2", "Inspecionado");
        return mapHistoricoExtintor;
    }

    /**
     * Retorna lista de uma String separada por um token qualquer
     *
     * @param acessos
     * @return
     * @author wagner.filho
     */
    public static List getListaDeToken(final String riscos, final String token) {
        final List listaRetorno = new ArrayList();
        final StringTokenizer st = new StringTokenizer(riscos, token);
        while (st.hasMoreTokens()) {
            listaRetorno.add(st.nextToken());
        }
        return listaRetorno;
    }

    public static String comparacaoSQLString(final String campo, final String comparacao, final String parametro, final List listaParametros) {

        String funcaoUpper = Constantes.getValue("FUNC_UPPER");
        if (funcaoUpper != null && funcaoUpper.trim().length() > 0) {
            funcaoUpper = funcaoUpper.replaceAll("<FIELD>", campo);
            listaParametros.add(parametro.toUpperCase());
            return " " + funcaoUpper + " " + comparacao + " ?";
        } else {
            listaParametros.add(parametro);
            return " " + campo + " " + comparacao + " ?";
        }
    }

    public static String getNomeClasseSemPacote(final Class classe) {

        final String str = classe.getName();
        final StringTokenizer st = new StringTokenizer(str, ".");
        String result = "";
        while (st.hasMoreTokens()) {
            result = st.nextToken();
        }

        return result;

    }

    public static String geraSenhaAleatoria(final int numCaracteres) {
        String result = "";
        for (int i = 1; i <= numCaracteres; i++) {
            result += getCaracterAleatorio();
        }
        return result;
    }

    public static char getCaracterAleatorio() {
        final char[] str = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'w', 'v', 'x', 'y', 'z', '1', '2',
                '3', '4', '5', '6', '7', '8', '9', '0'};
        return str[new Double(62 * Math.random()).intValue()];

    }

    public static int geraNumeroAleatorio(final int limite) {

        return new Double(limite * Math.random()).intValue();

    }

}
