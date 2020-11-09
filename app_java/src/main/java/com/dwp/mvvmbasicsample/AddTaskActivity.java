package com.dwp.mvvmbasicsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    EditText editTask, editPriority;
    Button btSave;
    public static final String EXTRA_TASK = "task";
    public static final String EXTRA_PRIORITY = "priority";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Add Task");

        editTask = findViewById(R.id.edit_task);
        editPriority = findViewById(R.id.edit_priority);
        btSave = findViewById(R.id.bt_save);

        btSave.setOnClickListener(view -> {
            String taskString = editTask.getText().toString();
            String priorityString = editPriority.getText().toString();

            if(TextUtils.isEmpty(taskString) || TextUtils.isEmpty(priorityString)) {
                Toast.makeText(this, "All field must be filled", Toast.LENGTH_SHORT).show();
            }

            Intent data = new Intent();
            data.putExtra(EXTRA_TASK, taskString);
            data.putExtra(EXTRA_PRIORITY, priorityString);
            setResult(RESULT_OK, data);
            finish();
        });

    }
}