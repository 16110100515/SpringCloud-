package com.atguigu.scw.utils;

public class StringUtil {
    public static boolean isEmpty(String string){
        return string ==null||"".equals(string);
    }
    public static boolean isNotEmpty(String string){
        return !isEmpty(string);
    }
}
