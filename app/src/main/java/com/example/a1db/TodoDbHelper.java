package com.example.a1db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Todo.db";


    private static TodoDbHelper sInstance;

    public static synchronized TodoDbHelper getInstance(Context context)
    {
        if (sInstance == null) {
            sInstance = new TodoDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }



    private TodoDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE Todo (Id TEXT PRIMARY KEY, " +
                "Todo TEXT," +
                "Descrip TEXT," +
                "Priority TEXT," +
                "Time TEXT," +
                "Color INT,"+
                "Date TEXT)";
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Todo");
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }
}
