package com.example.a1db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

interface Persistable
{
    public void save( SQLiteDatabase dataStore);
    public void load(Cursor dataStore);
    public String getId();
    public String getType();
}
