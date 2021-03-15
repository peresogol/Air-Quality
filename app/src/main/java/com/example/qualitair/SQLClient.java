package com.example.qualitair;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLClient extends SQLiteOpenHelper {
    // database version = 5
    public static final int DATABASE_VERSION = 5;
    // Database file name
    public static final String  DATABASE_FILE = "villes.db";
    // db creation query
    public static final String SQL_CREATE = "CREATE TABLE Villes (id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, longitude TEXT, latitude TEXT, isFavourite INTEGER);";
    // db deletion query
    public static final String SQL_DELETE = "DROP TABLE IF EXISTS Villes;";

    // Constructeur permettant d'appeler le constructeur de SQLIteOpenHelper
    public SQLClient(Context context) {
        super (context, DATABASE_FILE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLClient.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQLClient.SQL_DELETE);
        this.onCreate(db);
    }

    public boolean insertData(String name, String longitude, String latitude) {
        SQLiteDatabase dbW = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("nom", name);
        contentValues.put("longitude",longitude);
        contentValues.put("latitude",latitude);
        contentValues.put("isFavourite",false);

        long result = dbW.insert("Villes",null, contentValues);
        dbW.close();

        return (result != -1); // -1 = data doesn't insert into db
    }

    public Cursor viewData() {
        SQLiteDatabase dbR = this.getReadableDatabase();
        return dbR.rawQuery("select * from Villes order by id ASC", null);
    }

}