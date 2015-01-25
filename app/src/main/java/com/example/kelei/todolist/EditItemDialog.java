package com.example.kelei.todolist;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


import java.io.Serializable;

/**
 * Created by kelei on 1/25/15.
 */
public class EditItemDialog extends DialogFragment {

    private EditText mEditText;
    private Button saveButton;
    private ToDoItem item;
    private int position;

    public interface EditItemDialogListener {
        void onFinishEditDialog(String inputText, int position);
    }

    public EditItemDialog() {
        // Empty constructor required for DialogFragment
    }

    public static EditItemDialog newInstance(ToDoItem item, int position) {
        EditItemDialog frag = new EditItemDialog();
        Bundle args = new Bundle();
        args.putSerializable("todoItem", (Serializable)item);
        args.putInt("position", position);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_item, container);
        setupEditText(view);
        setupButtonClick(view);
        return view;
    }

    private void setupEditText(View view) {
        item = (ToDoItem) getArguments().getSerializable("todoItem");
        position = getArguments().getInt("position");

        getDialog().setTitle(getResources().getString(R.string.title_fragment_edit_item));
        mEditText = (EditText)view.findViewById(R.id.list_edit_text_input);
        mEditText.setText(item.name);
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    private void setupButtonClick(View view) {
        saveButton = (Button)view.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditItemDialogListener listener = (EditItemDialogListener) getActivity();
                listener.onFinishEditDialog(mEditText.getText().toString(), position);
                dismiss();
            }
        });
    }
}
