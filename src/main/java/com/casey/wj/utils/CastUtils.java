package com.casey.wj.utils;
/*
 * @author CaseyL
 * @date 2022/10/14 15:57
 * */

import java.util.ArrayList;
import java.util.List;

public class CastUtils {
    public static <T> List<T> objectConvertToList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?> )obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }
}
