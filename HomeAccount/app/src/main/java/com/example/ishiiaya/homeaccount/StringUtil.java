package com.example.ishiiaya.homeaccount;

/**
 * Created by ishiiaya on 2017/05/04.
 */

public class StringUtil {

    public static String EMPTY = "";
    public static String EQUAL = "=";
    public static String AND = "&";
    public static String DATE_FROM = "dateFrom";
    public static String DATE_TO = "dateTo";
    public static String KIND = "kind";
    public static String FOOD = "food";
    public static String TRAFFIC = "traffic";
    public static String TRAVEL = "travel";

    public static String emptyToString(String str) {
        if (null == str) {
            return EMPTY;
        }
        return str;
    }
}
