package com.example.a1db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.a1db.R;
import com.example.a1db.Todo;

import java.util.ArrayList;

public class TodoListAdapter extends ArrayAdapter<com.example.a1db.Todo> implements Filterable
{
    private ArrayList<com.example.a1db.Todo> todos;
    private ArrayList<com.example.a1db.Todo> filteredtodos;
    private Filter filter;

    public TodoListAdapter(Context context,ArrayList<com.example.a1db.Todo> notes){
        super(context,0,notes);
        this.todos = notes;
        this.filteredtodos = notes;
    }

    public com.example.a1db.Todo getItem(int position){
        return filteredtodos.get(position);
    }

    public int getCount() {
        return filteredtodos.size();
    }

    public View getView(int position, View convertView,ViewGroup parent)
    {
        com.example.a1db.Todo t= todos.get(position);

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_note_list_item,parent,false);
        }


        convertView.setBackgroundColor(t.getColor());
        TextView name = (TextView) convertView.findViewById(R.id.EventName);
        name.setTextSize(24);
        name.setText(t.getTodo());

        TextView Date = (TextView) convertView.findViewById(R.id.DateTime);
        Date.setTextSize(18);
        Date.setText(t.getDate()+" "+ t.getTime());

        TextView Pri = (TextView) convertView.findViewById(R.id.Priority);
        Pri.setTextSize(18);
        Pri.setText(t.getPriority());



        return convertView;
    }

    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new TodoFilter();
        }
        return filter;
    }

     class TodoFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length() > 0)
            {
                ArrayList<Todo> filteredList = new ArrayList<Todo>();
                for(int i=0; i < todos.size(); i++)
                {
                    com.example.a1db.Todo tem=todos.get(i);
                    if(tem.getTodo().contains(constraint))
                    {
                        filteredList.add(todos.get(i));
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;

            }
            else
                {
                results.count = todos.size();
                results.values = todos;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            filteredtodos = (ArrayList<com.example.a1db.Todo>) results.values;
            notifyDataSetChanged();
        }

    }
}
