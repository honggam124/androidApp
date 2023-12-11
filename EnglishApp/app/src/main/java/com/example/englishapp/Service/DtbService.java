package com.example.englishapp.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DtbService extends SQLiteOpenHelper {
    public final static String DBNAME = "App.db";
    public DtbService(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("CREATE TABLE users (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    username TEXT NOT NULL,\n" +
                "    password TEXT NOT NULL,\n" +
                "    email TEXT NOT NULL,\n" +
                "    birth DATE,\n" +
                "    phone TEXT,\n" +
                "    avatar BLOB,\n" +
                "    gender TEXT\n" +
                ");\n");
    }
    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop Table if exists users");
    }
    public boolean insertData(String name, String pass,String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues ctValue = new ContentValues();
        ctValue.put("username", name);
        ctValue.put("email", email);
        ctValue.put("password", pass);
        long result = MyDB.insert("users",null,ctValue);
        if(result==-1)
            return false;
        return true;
    }
    public Boolean checkUser(String email, String pass){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from users where email = ?", new String[] {email});
        if(cursor.getCount()>0);{
            Cursor cursor1 = myDB.rawQuery("Select * from users where password = ?", new String[] {pass});
            if(cursor1.getCount()>0)
                return true;
        }
        return false;
    }
    public Boolean updateUser(String arr[], int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", arr[0]);
        values.put("password", arr[1]);
        values.put("email", arr[2]);
        values.put("birth", arr[3]);
        values.put("avatar", arr[4]);
        values.put("phong", arr[5]);
        values.put("gender", arr[6]);
        int rowsUpdated = db.update("users", values, id + " = ?", new String[]{String.valueOf(id)});
        db.close();
        if (rowsUpdated > 0)
            return true;
        return false;
    }
}
