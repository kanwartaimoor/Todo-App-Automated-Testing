package com.example.a1db;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.a1db.R;

import java.util.ArrayList;
import java.util.Calendar;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity
{

    ArrayList<com.example.a1db.Todo> arr;
    private EditText todo;
    private EditText descrip;
    private Spinner priority;
    private EditText date;
    private EditText time;
    private EditText color;
    private ImageButton dButoon;
    private ImageButton cButoon;
    private ImageView empty;
    int DefaultColor ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todo=(EditText)findViewById(R.id.todo);
        descrip=(EditText)findViewById(R.id.descrip);
        priority=(Spinner) findViewById(R.id.spinner);


        date=(EditText)findViewById(R.id.date);
        time=(EditText)findViewById(R.id.time);
        color=(EditText)findViewById(R.id.color);
        cButoon=findViewById(R.id.button);

        date.setEnabled(false);
        time.setEnabled(false);
        DefaultColor=16777215;
        color.setEnabled(false);

        Intent i=getIntent();
        arr= (ArrayList<com.example.a1db.Todo>) i.getSerializableExtra("backlist");

        if(arr==null)
            arr=new ArrayList<>();


    }


    public boolean save()
    {
        if(todo.length()>0)
        {
            com.example.a1db.Todo t = new com.example.a1db.Todo();
            t.setTodo(todo.getText().toString());
            t.setDescrip(descrip.getText().toString());
            if (DefaultColor == 16777215) {
                if (priority.getSelectedItem().toString().equalsIgnoreCase("high")) {
                    t.setColor(Color.RED);
                    DefaultColor = Integer.parseInt("BA0000", 16);
                } else if (priority.getSelectedItem().toString().equalsIgnoreCase("medium")) {
                    t.setColor(Color.YELLOW);
                    DefaultColor = Integer.parseInt("E4E000", 16);
                } else if (priority.getSelectedItem().toString().equalsIgnoreCase("low")) {
                    t.setColor(Color.GREEN);
                    DefaultColor = Integer.parseInt("00E000", 16);
                }
            }
            else
                t.setColor(DefaultColor);

            t.setDate(date.getText().toString());
            t.setPriority(priority.getSelectedItem().toString());
            t.setTime(time.getText().toString());
            arr.add(t);
            return true;
        }
            return false;

    }


    public void DatePicker(View v)
    {

        // TODO Auto-generated method stub
        //To show current date in the datepicker
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker;
        mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday)
            {
                selectedmonth = selectedmonth + 1;
                TextView name = (TextView) findViewById(R.id.date);
                name.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                time.setVisibility(View.VISIBLE);
                cButoon.setVisibility(View.VISIBLE);
            }
        }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Select Date");
        mDatePicker.show();

    }



    public void TimePicker(View v)
    {
        // TODO Auto-generated method stub
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                TextView name = (TextView) findViewById(R.id.time);
                name.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }






    public void Save(View v)
    {
        boolean x = save();
        if (x == true)
        {
            com.example.a1db.PersistableCollection obj = new com.example.a1db.PersistableCollection(arr);
            obj.save(getApplicationContext());

            Intent intent = new Intent(MainActivity.this, com.example.a1db.note_list_item.class);
            intent.putExtra("list", arr);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(MainActivity.this,"Cannot add todo without title",Toast.LENGTH_SHORT).show();
        }
    }

    public void color(View v)
    {
        OpenColorPickerDialog(false);
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity.this, com.example.a1db.note_list_item.class);
        intent.putExtra("list", arr);
        startActivity(intent);
    }


    private void OpenColorPickerDialog(boolean AlphaSupport)
    {

        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(MainActivity.this, DefaultColor, AlphaSupport, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog ambilWarnaDialog, int color)
            {
                DefaultColor = color;
            }

            @Override
            public void onCancel(AmbilWarnaDialog ambilWarnaDialog)
            {
                Toast.makeText(MainActivity.this, "Color Picker Closed", Toast.LENGTH_SHORT).show();
            }
        });
        ambilWarnaDialog.show();
    }

}

