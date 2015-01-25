package com.example.kelei.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.activeandroid.query.Select;


import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private ArrayList<ToDoItem> todoListItems = new ArrayList<ToDoItem>();
    private Button addButton;
    private EditText textInput;
    private ListView listview;
    private ArrayAdapter adapter;

    private final int EDIT_ITEM_REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupListView();
    }

    private void setupListView() {
        readItems();
        listview = (ListView) findViewById(R.id.todo_list);

        adapter = new ToDoItemAdapter(this, todoListItems);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View child, int position,
                long id) {
                Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                i.putExtra("position", position);
                i.putExtra("todoItem", (Serializable)todoListItems.get(position));
                startActivityForResult(i, EDIT_ITEM_REQUEST_CODE);
            }
        });
        setupAddingToList();
        setupRemovingFromList();
    }

    private void setupAddingToList() {
        addButton = (Button)findViewById(R.id.add_button);
        textInput = (EditText)findViewById(R.id.list_add_text_input);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItemString = textInput.getText().toString();
                ToDoItem item = new ToDoItem(todoItemString);
                item.save();
                todoListItems.add(item);
                adapter.notifyDataSetChanged();
                textInput.setText("");
            }
        });
    }

    private void setupRemovingFromList() {
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View child, int position,
                long id) {
                ToDoItem item = todoListItems.get(position);
                item.delete();
                todoListItems.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == EDIT_ITEM_REQUEST_CODE) {
            String todoName = data.getExtras().getString("todoName");
            int position = data.getExtras().getInt("position");
            ToDoItem item = todoListItems.get(position);
            item.name = todoName;
            item.save();
            adapter.notifyDataSetChanged();
        }
    }

    private void readItems() {
        todoListItems = new Select()
            .from(ToDoItem.class).execute();
    }
}