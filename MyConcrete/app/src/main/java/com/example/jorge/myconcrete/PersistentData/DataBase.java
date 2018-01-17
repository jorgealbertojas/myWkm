package com.example.jorge.myconcrete.PersistentData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jorge.myconcrete.model.Owner;
import com.example.jorge.myconcrete.model.PullRequest;
import com.example.jorge.myconcrete.model.Repositories;
import com.example.jorge.myconcrete.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorge on 16/01/2018.
 */

public class DataBase extends SQLiteOpenHelper {

    private SQLiteDatabase mDb;
    private Context context;



    public DataBase(Context context){
        super(context,DbCreate.DB_NAME,null,DbCreate.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {

    };

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Drop in tablet PullRequest e User
        db.execSQL(" DROP TABLE IF EXISTS " + DbCreate.TABLE_PULL_REQUEST );

        // Drop in tablet Repositories Owner
        db.execSQL(" DROP TABLE IF EXISTS " + DbCreate.TABLE_REPOSITORIES );

        db.execSQL(DbCreate.CREATE_TABLE_PULL_REQUEST);
        db.execSQL(DbCreate.CREATE_TABLE_REPOSITORIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase ndb, int oldVersion, int newVersion) {

    }

    public void insertPullRequest(List<PullRequest> listPullRequest, String name, String login){


        for (int i = 0; i < listPullRequest.size() ; i++) {
            // Insert PullRequest
            ContentValues obj = new ContentValues();
            obj.put(Field.FIELD_TITLE, listPullRequest.get(i).getTitle());
            obj.put(Field.FIELD_HTML_URL, listPullRequest.get(i).getHtml_url());
            obj.put(Field.FIELD_CREATED_AT, listPullRequest.get(i).getCreated_at());
            obj.put(Field.FIELD_BODY, listPullRequest.get(i).getBody());
            obj.put(Field.FIELD_USER_LOGIN, listPullRequest.get(i).getUser().getLogin());
            obj.put(Field.FIELD_USER_AVATAR_URL, listPullRequest.get(i).getUser().getAvatar_url());
            obj.put(Field.FIELD_NAME, name);
            obj.put(Field.FIELD_LOGIN, login);
            this.onInsert(context,obj, DbCreate.TABLE_PULL_REQUEST);

        }
    }

    public void insertRepositories(List<Repositories> repositories){


        for (int i = 0; i < repositories.size() ; i++) {
            // Insert PullRequest
            ContentValues obj = new ContentValues();
            obj.put(Field.FIELD_NAME, repositories.get(i).getName());
            obj.put(Field.FIELD_DESCRIPTION, repositories.get(i).getDescription());
            obj.put(Field.FIELD_OWNER_LOGIN, repositories.get(i).getOwner().getLogin());
            obj.put(Field.FIELD_OWNER_AVATAR_URL, repositories.get(i).getOwner().getAvatar_url());
            obj.put(Field.FIELD_STARGAZERS_COUNT, repositories.get(i).getStargazers_count());
            obj.put(Field.FIELD_FORKS_COUNT, repositories.get(i).getForks_count());

            this.onInsert(context,obj, DbCreate.TABLE_REPOSITORIES);

        }
    }

    public List<Repositories> getListRepositories() {

        List<Repositories> listRepositories = new ArrayList<Repositories>();

        mDb = this.getWritableDatabase();

        Cursor cursor = mDb.rawQuery(DbSelect.GET_SQL_REPOSITORIES,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast() ){
            Repositories repositories = new Repositories();
            Owner owner = new Owner();
            try {
                repositories.setName(cursor.getString(cursor.getColumnIndex(Field.FIELD_NAME)));
                repositories.setDescription(cursor.getString(cursor.getColumnIndex(Field.FIELD_DESCRIPTION)));

                owner.setAvatar_url(cursor.getString(cursor.getColumnIndex(Field.FIELD_OWNER_AVATAR_URL)));
                owner.setLogin(cursor.getString(cursor.getColumnIndex(Field.FIELD_OWNER_LOGIN)));

                repositories.setOwner(owner);

                repositories.setStargazers_count(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Field.FIELD_STARGAZERS_COUNT))));
                repositories.setStargazers_count(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Field.FIELD_FORKS_COUNT))));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            listRepositories.add(repositories);
            cursor.moveToNext();
        }
        cursor.close();
        return listRepositories;

    }

    public List<PullRequest> getListPullRequest(String login, String name) {

        List<PullRequest> listPullRequest = new ArrayList<PullRequest>();

        mDb = this.getWritableDatabase();

        Cursor cursor = mDb.rawQuery(DbSelect.GET_SQL_PULL_REQUEST,new String[]{login,name});

        cursor.moveToFirst();
        while(!cursor.isAfterLast() ){
            PullRequest pullRequest = new PullRequest();
            User user = new User();
            try {
                pullRequest.setTitle(cursor.getString(cursor.getColumnIndex(Field.FIELD_TITLE)));
                pullRequest.setHtml_url(cursor.getString(cursor.getColumnIndex(Field.FIELD_HTML_URL)));

                user.setAvatar_url(cursor.getString(cursor.getColumnIndex(Field.FIELD_USER_AVATAR_URL)));
                user.setLogin(cursor.getString(cursor.getColumnIndex(Field.FIELD_USER_LOGIN)));

                pullRequest.setUser(user);

                pullRequest.setCreated_at(cursor.getString(cursor.getColumnIndex(Field.FIELD_CREATED_AT)));
                pullRequest.setBody(cursor.getString(cursor.getColumnIndex(Field.FIELD_BODY)));

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            listPullRequest.add(pullRequest);
            cursor.moveToNext();
        }
        cursor.close();
        return listPullRequest;

    }

    private void onInsert(Context context, ContentValues obj, String nTabela) {
        try{
            DbInstance.getInstance( context ).insert( nTabela, obj );
        }
        catch (Throwable ex){

        }

    }

    public long insert(String table,ContentValues values){

        mDb = this.getWritableDatabase();

        long row = mDb.insert(table, null, values);
        mDb.close();

        return row;
    }










}
