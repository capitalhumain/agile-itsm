package br.com.citframework.comparacao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Comparator;

import br.com.citframework.excecao.CompareException;
import br.com.citframework.util.Reflexao;

/**
 * Esta classe serve para fazer comparacao entre objetos em caso de utilizacao de Collections.sort(col, Comparator);
 *
 * <br>
 * Exemplo: <br>
 *
 * <pre>
 * Collections.sort(lst, new ObjectSimpleComparator(&quot;getDataInicio&quot;, ObjectSimpleComparator.ASC)); // ou
 * Collections.sort(lst, new ObjectSimpleComparator(&quot;getDataInicio&quot;, ObjectSimpleComparator.DESC));
 * </pre>
 *
 * onde lst é uma collection de beans: PPPBeanRegistroAmbiental, e getDataInicio pertence ao bean PPPBeanRegistroAmbiental, e este será utilizado para fazer
 * comparacao no momento da ordenacao.
 *
 * @author emauri
 */
public class ObjectSimpleComparator implements Comparator, Serializable {

    private static final long serialVersionUID = -8866183197604977380L;

    public static String ASC = "ASC";
    public static String DESC = "DESC";
    private final String atributo;
    private final String ordem;

    public ObjectSimpleComparator(final String atributoParm, final String ordemParm) throws CompareException {
        if (atributoParm == null) {
            throw new CompareException("Informe o atributo no formato (get+<Xxxxx>) para fazer a comparacao!");
        }
        if (ordemParm == null) {
            throw new CompareException("Informe o ordem para fazer a comparacao (Utilize: ObjectSimpleComparator.ASC ou ObjectSimpleComparator.DESC)!");
        }
        atributo = atributoParm;
        ordem = ordemParm;
    }

    @Override
    public int compare(final Object o1, final Object o2) {
        if (o1 == null) {
            return 0;
        }
        if (o2 == null) {
            return 0;
        }

        final Method m1 = Reflexao.findMethod(atributo, o1);
        if (m1 == null) {
            return 0;
        }

        final Method m2 = Reflexao.findMethod(atributo, o2);
        if (m2 == null) {
            return 0;
        }

        Object valor1 = null;
        try {
            valor1 = m1.invoke(o1, null);
        } catch (final IllegalArgumentException e) {
            System.out.println("            Exception no momento da comparacao (1): " + e.getMessage());
            return 0;
        } catch (final IllegalAccessException e) {
            System.out.println("            Exception no momento da comparacao (1): " + e.getMessage());
            return 0;
        } catch (final InvocationTargetException e) {
            System.out.println("            Exception no momento da comparacao (1): " + e.getMessage());
            return 0;
        }
        Object valor2 = null;
        try {
            valor2 = m2.invoke(o2, null);
        } catch (final IllegalArgumentException e) {
            System.out.println("            Exception no momento da comparacao (2): " + e.getMessage());
            return 0;
        } catch (final IllegalAccessException e) {
            System.out.println("            Exception no momento da comparacao (2): " + e.getMessage());
            return 0;
        } catch (final InvocationTargetException e) {
            System.out.println("            Exception no momento da comparacao (2): " + e.getMessage());
            return 0;
        }

        try {
            if (valor1 == null && valor2 == null) {
                return 0;
            } else if (valor1 == null) {
                return 1;
            } else if (valor2 == null) {
                return -1;
            }
            if (valor1 instanceof java.sql.Date) {
                final java.sql.Date data1 = (java.sql.Date) valor1;
                final java.sql.Date data2 = (java.sql.Date) valor2;
                if (ObjectSimpleComparator.ASC.equalsIgnoreCase(ordem)) {
                    return data1.compareTo(data2);
                } else {
                    return data2.compareTo(data1);
                }
            } else if (valor1 instanceof java.sql.Timestamp) {
                final java.sql.Timestamp ts1 = (java.sql.Timestamp) valor1;
                final java.sql.Timestamp ts2 = (java.sql.Timestamp) valor2;
                if (ObjectSimpleComparator.ASC.equalsIgnoreCase(ordem)) {
                    if (ts2 == null) {
                        return 1;
                    }
                    if (ts1 != null) {
                        return ts1.compareTo(ts2);
                    } else {
                        return 0;
                    }
                } else {
                    if (ts1 == null) {
                        return -1;
                    }
                    if (ts2 != null) {
                        return ts2.compareTo(ts1);
                    } else {
                        return 0;
                    }
                }
            } else if (valor1 instanceof java.util.Date) {
                final java.util.Date data1 = (java.util.Date) valor1;
                final java.util.Date data2 = (java.util.Date) valor2;
                if (ObjectSimpleComparator.ASC.equalsIgnoreCase(ordem)) {
                    if (data2 == null) {
                        return 1;
                    }
                    if (data1 != null) {
                        return data1.compareTo(data2);
                    } else {
                        return 0;
                    }
                } else {
                    if (data1 == null) {
                        return -1;
                    }
                    if (data2 != null) {
                        return data2.compareTo(data1);
                    } else {
                        return 0;
                    }
                }
            } else if (valor1 instanceof Integer) {
                final Integer val1 = (Integer) valor1;
                final Integer val2 = (Integer) valor2;
                if (ObjectSimpleComparator.ASC.equalsIgnoreCase(ordem)) {
                    return val1.compareTo(val2);
                } else {
                    return val2.compareTo(val1);
                }
            } else if (valor1 instanceof Long) {
                final Long val1 = (Long) valor1;
                final Long val2 = (Long) valor2;
                if (ObjectSimpleComparator.ASC.equalsIgnoreCase(ordem)) {
                    return val1.compareTo(val2);
                } else {
                    return val2.compareTo(val1);
                }
            } else if (valor1 instanceof BigDecimal) {
                final BigDecimal val1 = (BigDecimal) valor1;
                final BigDecimal val2 = (BigDecimal) valor2;
                if (ObjectSimpleComparator.ASC.equalsIgnoreCase(ordem)) {
                    return val1.compareTo(val2);
                } else {
                    return val2.compareTo(val1);
                }
            } else if (valor1 instanceof String) {
                final String val1 = (String) valor1;
                final String val2 = (String) valor2;
                if (ObjectSimpleComparator.ASC.equalsIgnoreCase(ordem)) {
                    return val1.compareTo(val2);
                } else {
                    return val2.compareTo(val1);
                }
            }
        } catch (final Exception e) {
            System.out.println("            Exception no momento da comparacao (2): " + e.getMessage());
            return 0;
        }
        return 0;
    }

}
