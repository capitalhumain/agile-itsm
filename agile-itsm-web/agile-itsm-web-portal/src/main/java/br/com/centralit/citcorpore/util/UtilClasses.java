package br.com.centralit.citcorpore.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class UtilClasses {

    /**
     * Retorna os nomes de classes de um determinadao pacote
     *
     * @param packageName
     * @return
     * @throws IOException
     */
    public static ArrayList<String> getClassNamesFromPackage(String packageName) throws IOException {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL packageURL;
        final ArrayList<String> names = new ArrayList<String>();;

        packageName = packageName.replace(".", "/");
        packageURL = classLoader.getResource(packageName);

        if (packageURL.getProtocol().equals("jar")) {
            String jarFileName;
            JarFile jf;
            Enumeration<JarEntry> jarEntries;
            String entryName;

            // build jar file name, then loop through zipped entries
            jarFileName = URLDecoder.decode(packageURL.getFile(), "UTF-8");
            jarFileName = jarFileName.substring(5, jarFileName.indexOf("!"));
            System.out.println(">" + jarFileName);
            jf = new JarFile(jarFileName);
            jarEntries = jf.entries();
            while (jarEntries.hasMoreElements()) {
                entryName = jarEntries.nextElement().getName();
                if (entryName.startsWith(packageName) && entryName.length() > packageName.length() + 5) {
                    entryName = entryName.substring(packageName.length(), entryName.lastIndexOf('.'));
                    names.add(entryName);
                }
            }
            jf.close();
            // loop through files in classpath
        } else {
            final File folder = new File(packageURL.getFile());
            final File[] contenuti = folder.listFiles();
            String entryName;
            for (final File actual : contenuti) {
                entryName = actual.getName();
                entryName = entryName.substring(0, entryName.lastIndexOf('.'));
                names.add(entryName);
            }
        }
        return names;
    }

    /**
     * Verifica se uma classe implementa uma interface (mesmo na hierarquia - heranças)
     *
     * @param classeAnalisar
     * @param nomeCompletoInterfaceComPacote
     * @return
     */
    public static boolean isClassImplInterface(final Class<?> classeAnalisar, final String nomeCompletoInterfaceComPacote) {
        if (classeAnalisar == null) {
            return false;
        }
        final Class<?>[] interfaces = classeAnalisar.getInterfaces();
        if (interfaces != null) {
            for (final Class<?> interfaceAux : interfaces) {
                if (interfaceAux.getName().equalsIgnoreCase(nomeCompletoInterfaceComPacote)) {
                    return true;
                } else {
                    // Se nao for, verifica na hierarquia desta se possui.
                    final boolean bOk = isClassImplInterface(interfaceAux, nomeCompletoInterfaceComPacote);
                    if (bOk) {
                        return true;
                    }
                }
            }
        }
        if (classeAnalisar.getSuperclass() != null) {
            final boolean bOk = isClassImplInterface(classeAnalisar.getSuperclass(), nomeCompletoInterfaceComPacote);
            if (bOk) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna a lista de Classes de um determinado pacote que implementam determinada interface.
     *
     * @param namePackage
     * @param interfaceNameImpl
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     *
     *             Exemplo: getClassesFromPackageImplInterface("br.com.centralit.citcorpore.bean", "br.com.citframework.bean.BaseEntity")
     */
    public static ArrayList<Class<?>> getClassesFromPackageImplInterface(final String namePackage, final String interfaceNameImpl) throws IOException,
            ClassNotFoundException {
        final ArrayList<String> list = UtilClasses.getClassNamesFromPackage(namePackage);
        ArrayList<Class<?>> listReturn = null;
        for (final String className : list) {
            final Class<?> classe = Class.forName(namePackage + "." + className);
            final boolean bOk = UtilClasses.isClassImplInterface(classe, interfaceNameImpl);
            if (bOk) {
                if (listReturn == null) {
                    listReturn = new ArrayList<>();
                }
                listReturn.add(classe);
            }
        }
        return listReturn;
    }

}
