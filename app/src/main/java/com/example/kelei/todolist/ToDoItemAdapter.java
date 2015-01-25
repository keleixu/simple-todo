package com.example.kelei.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by kelei on 1/25/15.
 */
public class ToDoItemAdapter extends ArrayAdapter<ToDoItem> {
    private static class ViewHolder {
        TextView name;
    }

    public ToDoItemAdapter(Context context, ArrayList<ToDoItem> items) {
        super(context, R.layout.todo_list_cell, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ToDoItem item = getItem(position);
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.todo_list_cell, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(item.name);

        return convertView;
    }
}