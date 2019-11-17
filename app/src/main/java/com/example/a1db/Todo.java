package com.example.a1db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.UUID;

public class Todo implements Serializable, com.example.a1db.Persistable {


        public String id;
        public String todo;
        public String descrip;
        public String priority;
        public String date;
        public String time;
        public int color;


        Todo()
        {
            init();
        }

        public Todo(String todo, String descrip, String priority, String date, String time, int color) {
            init();
            this.todo = todo;
            this.descrip = descrip;
            this.priority = priority;
            this.date = date;
            this.time = time;
            this.color = color;
        }


        public void setTodo(String todo)
        {
            this.todo=todo;
        }
        public void setDescrip(String descrip)
        {
            this.descrip=descrip;
        }
        public void setPriority(String priority)
        {
            this.priority=priority;
        }
        public void setDate(String date)
        {
            this.date=date;
        }
        public void setTime(String time)
        {
            this.time=time;
        }
        public void setColor(int color)
        {
            this.color=color;
        }

        private void init()
        {
            this.id = UUID.randomUUID().toString();
        }

        public String getPriority() {
            return priority;
        }

        public String getTime() {
            return time;
        }

        public int getColor() {
            return color;
        }

        public String getTodo() {
            return todo;
        }

        public String getDescrip() {
            return descrip;
        }

        public String getDate() {
            return date;
        }

        public String getId()
        {
            return id;
        }

        public String getType()
        {
            return getClass().getName();
        }

        @Override
    public void save(SQLiteDatabase dataStore){

        ContentValues values = new ContentValues();
        values.put("Id",id);
        values.put("Todo",todo);
        values.put("Descrip",descrip);
        values.put("Priority",priority);
        values.put("Date",date);
        values.put("Time",time);
        values.put("Color",color);

        dataStore.insertWithOnConflict("Todo",null,values,SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void load(Cursor dataStore)
    {
        id = dataStore.getString(dataStore.getColumnIndex("Id"));
        todo = dataStore.getString(dataStore.getColumnIndex("Todo"));
        descrip = dataStore.getString(dataStore.getColumnIndex("Descrip"));
        priority = dataStore.getString(dataStore.getColumnIndex("Priority"));
        date = dataStore.getString(dataStore.getColumnIndex("Date"));
        time = dataStore.getString(dataStore.getColumnIndex("Time"));
        color=dataStore.getInt(dataStore.getColumnIndex("Color"));


    }
    }

