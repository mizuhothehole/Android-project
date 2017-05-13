package com.example.ishiiaya.flyingbottle;

/**
 * Created by ishiiaya on 2017/04/29.
 */

public class SchemaProvider {
    public static String DB_NAME = "test";
    public static String TABLE_NAME_MESSAGE_DATA = "Data";
    public static String CLOMUN_NAME_BOTTLE_MESSAGE = "bottleMsg";
    public static String CLOMUN_NAME_BOTTLE_PK = "ID";

    public static String SQL_SELECT_ALL_MESSAGE_DATA = "select * from " + TABLE_NAME_MESSAGE_DATA;

}
