package com.example.jorge.myconcrete.PersistentData;

/**
 * Created by jorge on 16/01/2018.
 * Sql with create All tables system
 */

public class DbCreate {
    public static final int DB_VERSION = 1;
    public static String DB_NAME = "DB.db";

    public static String TABLE_PULL_REQUEST = "TABLE_PULL_REQUEST";
    public static String CREATE_TABLE_PULL_REQUEST =
            "CREATE TABLE IF NOT EXISTS TABLE_PULL_REQUEST (" +
                    Field.FIELD_TITLE + " VARCHAR(500)," +
                    Field.FIELD_HTML_URL + " VARCHAR(500), " +
                    Field.FIELD_CREATED_AT + " VARCHAR(500), " +
                    Field.FIELD_BODY + " VARCHAR(500), " +
                    Field.FIELD_USER_LOGIN + " VARCHAR(500), " +
                    Field.FIELD_USER_AVATAR_URL + " VARCHAR(500), "  +
                    Field.FIELD_NAME + " VARCHAR(500), " +
                    Field.FIELD_LOGIN + " VARCHAR(500) " +
                    ");";


    public static String TABLE_REPOSITORIES = "TABLE_REPOSITORIES";
    public static String CREATE_TABLE_REPOSITORIES =
            "CREATE TABLE IF NOT EXISTS TABLE_REPOSITORIES (" +
                    Field.FIELD_NAME + " VARCHAR(500)," +
                    Field.FIELD_DESCRIPTION + " VARCHAR(500), " +
                    Field.FIELD_OWNER_LOGIN + " VARCHAR(500), " +
                    Field.FIELD_OWNER_AVATAR_URL + " VARCHAR(500), "  +
                    Field.FIELD_STARGAZERS_COUNT + " INTEGER, " +
                    Field.FIELD_FORKS_COUNT + " INTEGER " +
                    ");";









}
