package com.example.finaltest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "UserDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FNAME = "fname";
    private static final String COLUMN_LNAME = "lname";
    private static final String COLUMN_ADDRESS = "haddress";
    private static final String COLUMN_PHONE = "hphone";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME ,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER NOT NULL CONSTRAINT employee_pk PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FNAME + " varchar(200) NOT NULL, " +
                COLUMN_LNAME + " varchar(200) NOT NULL, " +
                COLUMN_ADDRESS + " varchar(200) NOT NULL, " +
                COLUMN_PHONE + " double NOT NULL);";
        db.execSQL(sql);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);

    }

    boolean addUser(String firstname ,String lastname ,String address ,double phoneno){


        SQLiteDatabase sqLiteDatabase = getWritableDatabase();


        ContentValues contentValues = new ContentValues();


        contentValues.put(COLUMN_FNAME,firstname);
        contentValues.put(COLUMN_LNAME,lastname);
        contentValues.put(COLUMN_ADDRESS,address);
        contentValues.put(COLUMN_PHONE,String.valueOf(phoneno));

        return sqLiteDatabase.insert(TABLE_NAME,null,contentValues)!= -1;


    }

   Cursor getAllUsers(){

       SQLiteDatabase sqLiteDatabase = getReadableDatabase();
       return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);


    }

    boolean updateUser( int id, String firstname ,String lastname ,String address ,double phoneno){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_FNAME,firstname);
        contentValues.put(COLUMN_LNAME,lastname);
        contentValues.put(COLUMN_ADDRESS,address);
        contentValues.put(COLUMN_PHONE,String.valueOf(phoneno));

        return sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0;

    }

    boolean deleteUser(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0;


    }



}
