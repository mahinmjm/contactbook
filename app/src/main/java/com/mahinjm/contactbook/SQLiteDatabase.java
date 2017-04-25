package com.mahinjm.contactbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

/**
 * Created by mahinjm on 4/10/2016.
 */
public class SQLiteDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contact_book.db";
    private static final String DATABASE_TABLE_NAME = "contact_table";
    private static final String DATABASE_SETTINGS_TABLE = "settings_table";

    private static final String COLUMN1 = "ID";
    private static final String COLUMN2 = "Name";
    private static final String COLUMN3 = "Phone";



    public SQLiteDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        String query, qry;

        query = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE_NAME + " ( " + COLUMN1 + " INTEGER PRIMARY KEY, " + COLUMN2 + " VARCHAR(255), " + COLUMN3 +" VARCHAR(25) )" ;
        db.execSQL(query);

        qry = "CREATE TABLE IF NOT EXISTS " + DATABASE_SETTINGS_TABLE + " ( set_wallpaper BOOLEAN, write_ext_storage BOOLEAN, call_phone BOOLEAN, " +
                "send_sms BOOLEAN, read_contacts BOOLEAN, access_file_loc BOOLEAN )";
        db.execSQL(qry);

    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_SETTINGS_TABLE);
        onCreate(db);
    }


    public boolean insertData(String name, String phone){
        android.database.sqlite.SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN2,name);
        contentValues.put(COLUMN3, phone);

        long checher = sqLiteDatabase.insert(DATABASE_TABLE_NAME,null,contentValues);

        if(checher == -1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor showData(String data){
        android.database.sqlite.SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor result;

        result = sqLiteDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_NAME +" WHERE Name ='" +data+"'", null);

        return result;

    }

    public boolean saveSetting(boolean data1, boolean data2, boolean data3, boolean data4, boolean data5, boolean data6){
        android.database.sqlite.SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("set_wallpaper",data1);
        contentValues.put("write_ext_storage",data2);
        contentValues.put("call_phone",data3);
        contentValues.put("send_sms",data4);
        contentValues.put("read_contacts",data5);
        contentValues.put("access_file_loc",data6);

        long checher = sqLiteDatabase.insert(DATABASE_SETTINGS_TABLE,null,contentValues);

        if(checher == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getSettingData(){
        android.database.sqlite.SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor result;

        result = sqLiteDatabase.rawQuery("SELECT * FROM " + DATABASE_SETTINGS_TABLE , null);

        return result;

    }

/*    public Cursor showData(){
        android.database.sqlite.SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor result;

        result = sqLiteDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE_NAME , null);

        return result;

    }*/




}
