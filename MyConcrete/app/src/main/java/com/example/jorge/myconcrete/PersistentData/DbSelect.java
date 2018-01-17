package com.example.jorge.myconcrete.PersistentData;

/**
 * Created by jorge on 16/01/2018.
 * SQL with repositories and Pull Request
 */

public class DbSelect {
    public static String GET_SQL_REPOSITORIES =
            " SELECT "
                    + Field.FIELD_NAME + ","
                    + Field.FIELD_DESCRIPTION + ","
                    + Field.FIELD_OWNER_LOGIN + ","
                    + Field.FIELD_OWNER_AVATAR_URL + ","
                    + Field.FIELD_STARGAZERS_COUNT + ","
                    + Field.FIELD_FORKS_COUNT +
            " FROM " + DbCreate.TABLE_REPOSITORIES ;

    public static String GET_SQL_PULL_REQUEST =
            " SELECT "
                    + Field.FIELD_TITLE + ","
                    + Field.FIELD_HTML_URL + ","
                    + Field.FIELD_CREATED_AT + ","
                    + Field.FIELD_BODY + ","
                    + Field.FIELD_USER_LOGIN + ","
                    + Field.FIELD_USER_AVATAR_URL + ","
                    + Field.FIELD_NAME +
            " FROM " + DbCreate.TABLE_PULL_REQUEST +
            " WHERE " + Field.FIELD_LOGIN + " = ? AND " + Field.FIELD_NAME + " = ? ";



}
