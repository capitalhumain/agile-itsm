package br.com.centralit.citcorpore.metainfo.util;

import java.util.Map;

public class HashMapUtil {

    public static Map<String, String> map;

    public static String getFieldInHash(final String name) {
        if (map == null || name == null) {
            return null;
        }
        return map.get(name.toUpperCase());
    }

}
