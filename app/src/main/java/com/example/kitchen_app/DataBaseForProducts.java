package com.example.kitchen_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseForProducts extends SQLiteOpenHelper {
    private static final String DB_NAME = "Products.db";
    private static final String DB_TABLE = "Products_Basket";
    private static final String DB_TABLE_ONE = "Products_Home";
    private static final String ID = "ID";
    private static final String ID1 = "ID1";
    private static final String NAME = "NAME";
    private static final String QUANTITY = "QUANTITY";
    private static final String NAME1 = "NAME1";
    private static final String DATE = "DATE";
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE +" ("+
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NAME + " TEXT, " +
            QUANTITY + " INTEGER " + ")";
    private static final String CREATE_TABLE1 = "CREATE TABLE " + DB_TABLE_ONE +" ("+
            ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NAME1 + " TEXT, " +
            DATE + " INTEGER " + ")";

    public DataBaseForProducts(Context context){
        super(context,DB_NAME,null,14);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_ONE);
        onCreate(db);
    }
    public boolean insertDataForBasket(String name, int quantity) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(QUANTITY, quantity);
        long result = database.insert(DB_TABLE, null, contentValues);
        return result != -1;
    }
    public boolean insertDataForHome(String name, long date) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME1, name);
        contentValues.put(DATE, date);
        long result = database.insert(DB_TABLE_ONE, null, contentValues);
        return result != -1;
    }
    public Cursor viewDataForBasket(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + DB_TABLE;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public Cursor viewDataForHome() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query1 = "Select * from " + DB_TABLE_ONE;
        Cursor cursor = db.rawQuery(query1, null);
        return cursor;
    }
}
