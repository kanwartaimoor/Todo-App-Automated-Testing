package com.example.a1db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a1db.MainActivity;
import com.example.a1db.MyMenu;
import com.example.a1db.R;

import java.util.ArrayList;

public class note_list_item extends AppCompatActivity {

    ArrayList<com.example.a1db.Todo> arr;
    EditText text;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;

    ListView list;
    com.example.a1db.TodoListAdapter adapter;

    ImageView empty;
    int selectedItem;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.options, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        Intent intent = new Intent(note_list_item.this, MainActivity.class);
        intent.putExtra("backlist", arr);
        startActivity(intent);
        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.a1db.TodoDbHelper helper = com.example.a1db.TodoDbHelper.getInstance(this);

        Intent i=getIntent();
        arr= (ArrayList<com.example.a1db.Todo>) i.getSerializableExtra("list");

        if(arr==null) {
            com.example.a1db.PersistableCollection a=new com.example.a1db.PersistableCollection(null);
            a.load(getApplicationContext());
            arr = a.getArr();
        }


        createView();

    }

    private EditText createText(){
        text = new EditText(this);
        text.setHint("Search");
        text.addTextChangedListener(new TextWatcher(){

            @Override
            public void afterTextChanged(Editable arg0) { }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,int arg2, int arg3) { }

            @Override
            public void onTextChanged(CharSequence text, int start, int before,int count) {
                com.example.a1db.TodoListAdapter.TodoFilter t = (com.example.a1db.TodoListAdapter.TodoFilter) adapter.getFilter();
               t.filter(text.toString());
            }

        });

        return text;
    }


    private ListView createList(){
        list = new ListView(this);

        adapter = new com.example.a1db.TodoListAdapter(this,arr);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position,long id)
            {
                MyMenu newMyMenu =new MyMenu(note_list_item.this,arr,position);
                newMyMenu.show();

            }
        });

        registerForContextMenu(list);

        return list;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void createView(){
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setFocusable(true);
        layout.setFocusableInTouchMode(true);
        layout.addView(createText());
        layout.addView(createList());

        text2=new TextView(this);
        text2.setText(" ");

        text3=new TextView(this);
        text3.setText("Ooops it's empty! ");
        text3.setGravity(Gravity.CENTER );

        text4=new TextView(this);
        text4.setText(" ");

        text5=new TextView(this);
        text5.setText("                                                                                       ");
        text5.setMinHeight(400);
        layout.addView(text2);

        layout.addView(text4);
        layout.addView(text5);

        empty= new ImageView(this);
        empty.setImageDrawable((getResources().getDrawable(R.drawable.empty)));

//        ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(empty.getLayoutParams());
//        marginParams.setMargins(50, 200, 50, 200);
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginParams);
//        empty.setLayoutParams(layoutParams);
        layout.addView(empty);
        layout.addView(text3);
        if(arr!=null)
        {
            text3.setVisibility(View.INVISIBLE);
            empty.setVisibility(View.INVISIBLE);
        }
        setContentView(layout);
    }

    @Override
    public void onBackPressed()
    {
        finishAffinity();
    }




















}


