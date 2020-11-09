package com.dwp.mvvmbasicsample;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dwp.mvvmbasicsample.adapter.TaskAdapter;
import com.dwp.mvvmbasicsample.room.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TaskAdapter adapter;
    TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivityForResult(intent, 123);
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        registerForContextMenu(recyclerView);

        adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);


        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.setAllTasks(tasks);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123 && resultCode == RESULT_OK) {
            String taskString = data.getStringExtra(AddTaskActivity.EXTRA_TASK);
            String priorityString = data.getStringExtra(AddTaskActivity.EXTRA_PRIORITY);

            Task task = new Task(taskString, Integer.parseInt(priorityString));
            taskViewModel.insert(task);

            Toast.makeText(this, "Task is save!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getTitle().equals("Update")) {
            showUpdateDialog(adapter.getTask(item.getOrder()));
        }
        if(item.getTitle().equals("Delete")) {
            taskViewModel.delete(adapter.getTask(item.getOrder()));
        }
        return super.onContextItemSelected(item);
    }

    public void showUpdateDialog(Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Update Task")
                .setMessage("Please update the fields");

        View view = LayoutInflater.from(this).inflate(R.layout.update_layout, null);
        EditText updateTask = view.findViewById(R.id.update_task);
        EditText updatePriority = view.findViewById(R.id.update_priority);

        updateTask.setText(task.getTask());
        updatePriority.setText(String.valueOf(task.getPriority()));
        builder.setView(view);

        builder.setPositiveButton("Update", (dialogInterface, i) -> {
            if(TextUtils.isEmpty(updateTask.getText()) || TextUtils.isEmpty(updatePriority.getText())) {
                Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
            }
            int id = task.getId();
            String strUpdateTask = updateTask.getText().toString();
            int intUpdatePriority = Integer.parseInt(updatePriority.getText().toString());

            Task task1 = new Task(id, strUpdateTask, intUpdatePriority);
            taskViewModel.update(task1);
        }).setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());

        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_delete_all) {
            taskViewModel.deleteAllTasks();
        }
        return super.onOptionsItemSelected(item);
    }
}