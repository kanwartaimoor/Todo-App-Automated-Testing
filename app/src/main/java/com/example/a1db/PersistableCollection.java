package com.example.a1db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a1db.Persistable;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;

public class PersistableCollection extends AbstractCollection
{

    private ArrayList<com.example.a1db.Todo> todos;
    public PersistableCollection(ArrayList<com.example.a1db.Todo> arr)
    {

        if(arr==null)
            todos=new ArrayList<com.example.a1db.Todo>();
        else
            todos=arr;
    }

    public void save(Context context)
    {
        com.example.a1db.TodoDbHelper dbHelper =  com.example.a1db.TodoDbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Iterator iterator = todos.iterator();
        while(iterator.hasNext())
        {
            Persistable object = (Persistable) iterator.next();
            object.save(db);
        }
    }
    public void load(Context context)
    {
        com.example.a1db.TodoDbHelper dbHelper =  com.example.a1db.TodoDbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM Todo";
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext())
        {
            com.example.a1db.Todo object = new com.example.a1db.Todo();
            object.load(cursor);
            todos.add(object);
        }
    }

    public int delete(Context context, String id)
    {
        com.example.a1db.TodoDbHelper dbHelper =  com.example.a1db.TodoDbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        return db.delete("Todo",
                "id = ? ",
                new String[]{id});

    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public int size()
    {
        return 0;
    }
    public ArrayList<com.example.a1db.Todo> getArr()
    {
        return todos;
    }

}