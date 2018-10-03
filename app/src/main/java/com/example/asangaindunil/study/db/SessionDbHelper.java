package com.example.asangaindunil.study.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SessionDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static String DATABASE_NAME = "Session1.db";
    public SessionDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_Create =
                "Create Table "+Constructor.Session.TABLE_NAME
        + "( "+ Constructor.Session._ID+ " INTEGER PRIMARY KEY ,"+
                        Constructor.Session.Col_1+" TEXT , "+
                        Constructor.Session.Col_2+" TEXT , "+
                        Constructor.Session.Col_3+" TEXT , " +
                        Constructor.Session.Col_4+" TEXT , " +
                        Constructor.Session.Col_5+" INTEGER , " +
                        Constructor.Session.Col_6+" TEXT)";
        sqLiteDatabase.execSQL(SQL_Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addInfo(){

    }



}
