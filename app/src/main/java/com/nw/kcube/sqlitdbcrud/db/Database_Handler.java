package com.nw.kcube.sqlitdbcrud.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nw.kcube.sqlitdbcrud.model.movie;

import java.util.ArrayList;

public class Database_Handler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movie_test";
    SQLiteDatabase db = Database_Handler.this.getWritableDatabase();

    private static final String MOVIE_TABLE = "movies",
            MOVIE_ID = "id",
            MOVIE_NAME = "name",
            MOVIE_TYPE = "type",
            MOVIE_CINEMA = "cinema",
            MOVIE_DATE = "date",
            MOVIE_STATUS = "status",
            MOVIE_REVIEW = "review";

    public Database_Handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LESSON_TABLE = "CREATE TABLE " + MOVIE_TABLE + "("
                + MOVIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + MOVIE_NAME + " TEXT ,"
                + MOVIE_TYPE + " TEXT,"
                + MOVIE_CINEMA + " TEXT,"
                + MOVIE_DATE + " TEXT,"
                + MOVIE_STATUS + " TEXT,"
                + MOVIE_REVIEW + " TEXT )";
        db.execSQL(CREATE_LESSON_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MOVIE_TABLE);
        onCreate(db);

    }

    public ArrayList<movie> getMovieList(String query) {
        ArrayList<movie> userlist = new ArrayList<>();
        Cursor usercursor = db.rawQuery(query, null);
        usercursor.moveToFirst();
        while (!usercursor.isAfterLast()) {
            String id = usercursor.getInt(0) + "";
            String name = usercursor.getString(1);
            String type = usercursor.getString(2);
            String cinema = usercursor.getString(3);
            String date = usercursor.getString(4);
            String status = usercursor.getString(5);
            String review = usercursor.getString(6);
            userlist.add(new movie(id, name, type, cinema, date, status, review));
            usercursor.moveToNext();
        }
        return userlist;
    }

    public void insertMovie(String name, String type, String cinema, String date, String status, String review) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("type", type);
        contentValues.put("cinema", cinema);
        contentValues.put("date", date);
        contentValues.put("status", status);
        contentValues.put("review", review);
        db.insert("movies", null, contentValues);
    }


    public void updateMovie(int id, String name, String type, String cinema, String date, String status, String review) {
        db = this.getReadableDatabase();
        String updatequery = "update " + MOVIE_TABLE +
                " set name = '" + name + "' , type ='" + type +
                "' , cinema ='" + cinema +
                "' , date ='" + date +
                "' , status ='" + status +
                "' , review ='" + review + "'"
                + " where id = '" + id + "'";
        db.execSQL(updatequery);
        Log.e("executed", updatequery);
    }

    public void upgradeFav(String table, int id, String star) {
        db = this.getReadableDatabase();
//        ContentValues c = new ContentValues();
//        c.put("fav", star);
//        db.update(table, c, "id = '" + id + "'", null);
        String updatequery = "update " + table + " set fav = '" + star + "'" + " where id = '" + id + "'";
        db.execSQL(updatequery);
        Log.e("executed", updatequery);
    }

//    public int update_content(String content, int id) {
//        Log.d("DATA", content + " and " + id);
//        SQLiteDatabase db = this.getReadableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_CONTENT, content);
//        return db.update(KEY_USER_TABLE, values, KEY_ID + "='" + id + "'", null);
//
//    }
}
