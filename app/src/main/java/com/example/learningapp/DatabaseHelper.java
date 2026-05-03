package com.example.learningapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "gym.db";
    public static final int DB_VERSION = 1;
    public static final String EXERCISE_TABLE = "exercise";
    public static final String EXERCISE_ID = "id";
    public static final String EXERCISE_NAME = "name";
    public static final String EXERCISE_DESCRIPTION = "description";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + EXERCISE_TABLE + "(" +
                EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                EXERCISE_NAME + " TEXT UNIQUE NOT NULL," +
                EXERCISE_DESCRIPTION + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EXERCISE_TABLE);
        onCreate(db);
    }

    public long insert(String name, String description){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EXERCISE_NAME, name);
        cv.put(EXERCISE_DESCRIPTION, description);

        return db.insertOrThrow(EXERCISE_TABLE, null, cv);
    }

    public int update(long id, String name, String description){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EXERCISE_NAME, name);
        cv.put(EXERCISE_DESCRIPTION, description);

        return db.update(EXERCISE_TABLE, cv, EXERCISE_ID + " = ?", new String[]{ String.valueOf(id) });
    }

    public int delete(long id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(EXERCISE_TABLE, EXERCISE_ID + " = ?", new String[]{ String.valueOf(id) });
    }

    public List<Exercise> getAll(){
        SQLiteDatabase db = getReadableDatabase();
        List<Exercise> result = new ArrayList<>();

        Cursor c = db.query(EXERCISE_TABLE,
                new String[]{ EXERCISE_ID, EXERCISE_NAME, EXERCISE_DESCRIPTION},
                null,null,null,null,
                EXERCISE_ID + " DESC");

        try{
            while (c.moveToNext()){
                Exercise exer = new Exercise();

                exer.setId(c.getLong(0));
                exer.setName(c.getString(c.getColumnIndexOrThrow(EXERCISE_NAME)));
                exer.setDescription(c.getString(2));

                result.add(exer);
            }
        }finally {
            c.close();
        }
        db.close();
        return result;
    }


}
