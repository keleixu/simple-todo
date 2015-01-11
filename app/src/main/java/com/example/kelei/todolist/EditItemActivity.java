package com.example.kelei.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditItemActivity extends ActionBarActivity {
    int position;
    String todoName;

    private EditText editTextInput;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        setupEditText();
        setupButtonClick();
    }

    private void setupEditText() {
        Intent i = getIntent();
        if (i != null) {
            editTextInput = (EditText)findViewById(R.id.list_edit_text_input);
            todoName = (String)i.getExtras().get("todoName");
            position = (int)i.getExtras().get("position");
            editTextInput.setText(todoName);
            editTextInput.setSelection(todoName.length());
            editTextInput.requestFocus();
        }
    }

    private void setupButtonClick() {
        saveButton = (Button)findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("todoName", editTextInput.getText().toString());
                data.putExtra("position", position);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
