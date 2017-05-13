package com.example.ishiiaya.flyingbottle;

/**
 * Created by ishiiaya on 2017/04/29.
 */

public class StringUtil {

    public static final String EMPTY = "";

    public static final String nullToEmpty(String str) {
        if (null == str || "".equals(str)) {
            return "";
        }
        return str;
    }

    public static final boolean isEmpty(String str) {
        return (null == str || EMPTY.equals(str));
    }
}
