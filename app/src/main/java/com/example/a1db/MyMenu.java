package com.example.a1db;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.a1db.R;

import java.util.ArrayList;
import java.util.Calendar;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MyMenu extends Dialog implements android.view.View.OnClickListener

{
    com.example.a1db.note_list_item obj;
    Button update, delete;
    ArrayList<com.example.a1db.Todo> arr;
    int pos;
    private EditText todo;
    private EditText descrip;
    private Spinner priority;
    private ImageButton Color;
    private EditText date;
    private EditText time;
    private EditText color;
    private TextView prior;
    private ImageButton dButoon;
    private ImageButton cButoon;
    int DefaultColor;


    public MyMenu(com.example.a1db.note_list_item context, ArrayList<com.example.a1db.Todo> a, int pos)
    {
        super(context);
        obj=context;
        arr=a;
        this.pos=pos;
    }



    @Override
    public void onClick(View v)
    {
        if(v.getId()==update.getId())
        {
            arr.get(pos).setTodo(todo.getText().toString());
            arr.get(pos).setDescrip(descrip.getText().toString());
            arr.get(pos).setColor(DefaultColor);
            arr.get(pos).setDate(date.getText().toString());
            arr.get(pos).setPriority(priority.getSelectedItem().toString());
            arr.get(pos).setTime(time.getText().toString());
            obj.createView();
            com.example.a1db.PersistableCollection obj=new com.example.a1db.PersistableCollection(arr);
            obj.save(getContext());
            this.hide();
        }
        else if(v.getId()==delete.getId())
        {
            String id=arr.get(pos).id;
            arr.remove(pos);
            com.example.a1db.PersistableCollection db=new com.example.a1db.PersistableCollection(null);
            int x=db.delete(getContext(),id);
            if(x>0)
                Toast.makeText(getContext(),"Todo Deleted",Toast.LENGTH_SHORT);
            obj.createView();
            this.hide();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        todo=(EditText)findViewById(R.id.todo1);
        descrip=(EditText)findViewById(R.id.descrip1);
        priority=(Spinner) findViewById(R.id.spinner1);
        Color=findViewById(R.id.color2);

        prior=(TextView) findViewById(R.id.Priority1);
        date=(EditText)findViewById(R.id.date1);
        time=(EditText)findViewById(R.id.time1);
        color=(EditText)findViewById(R.id.color1);

        dButoon= findViewById(R.id.calendarbutton1);
        cButoon=findViewById(R.id.clock1);
        this.setSpinnner();

        dButoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday)
                    {
                        selectedmonth = selectedmonth + 1;
                        TextView name = (TextView) findViewById(R.id.date1);
                        name.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });

        cButoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                    {
                        TextView name = (TextView) findViewById(R.id.time1);
                        name.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        Color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenColorPickerDialog(false);
            }
        });

        com.example.a1db.Todo a=arr.get(pos);

        DefaultColor= a.getColor();
        todo.setText(a.getTodo());
        descrip.setText(a.getDescrip());
        prior.setText(a.getPriority());
        date.setText(a.getDate());
        time.setText(a.getTime());
        color.setText(Integer.toString(a.getColor()));
        update.setOnClickListener(this);
        delete.setOnClickListener(this);

    }

    void setSpinnner()
    {


        if(arr.get(pos).priority.equalsIgnoreCase("High"))
        {
            priority.setSelection(0);
        }
        else if(arr.get(pos).priority.equalsIgnoreCase("Medium"))
        {
            priority.setSelection(1);
        }
        else if(arr.get(pos).priority.equalsIgnoreCase("Low"))
        {
            priority.setSelection(2);
        }

    }



    private void OpenColorPickerDialog(boolean AlphaSupport)
    {

        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(getContext(), DefaultColor, AlphaSupport, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog ambilWarnaDialog, int color)
            {
                DefaultColor = color;
            }

            @Override
            public void onCancel(AmbilWarnaDialog ambilWarnaDialog)
            {
                Toast.makeText(getContext(), "Color Picker Closed", Toast.LENGTH_SHORT).show();
            }
        });
        ambilWarnaDialog.show();
    }




}
