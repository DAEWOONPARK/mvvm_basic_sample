package com.dwp.mvvmbasicsample

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dwp.mvvmbasicsample.adapter.TaskAdapter
import com.dwp.mvvmbasicsample.room.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private val adapter = TaskAdapter()
    private val taskViewModel: TaskViewModel by lazy {
        ViewModelProvider(this).get(TaskViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val floatingActionButton = findViewById<FloatingActionButton>(R.id.fab)
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intent, 123)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        taskViewModel.getAllTasks().observe(this, {
            adapter.setAllTasks(it)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 123 && resultCode == RESULT_OK) {
            val taskString = data?.getStringExtra(AddTaskActivity.EXTRA_TASK)
            val priorityString = data?.getStringExtra(AddTaskActivity.EXTRA_PRIORITY)

            val task = Task(task = taskString, priority = priorityString?.toInt())

            taskViewModel.insert(task)

            Toast.makeText(this, "Task is saved!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.title) {
            "Update" -> showUpdateDialog(adapter.getTask(item.order))
            "Delete" -> taskViewModel.delete(adapter.getTask(item.order))
            else -> Log.e(tag, "Wrong context item type")
        }
        return super.onContextItemSelected(item)
    }

    private fun showUpdateDialog(task: Task) {
        val view = LayoutInflater.from(this).inflate(R.layout.update_layout, null)
        val updateTask = view.findViewById<EditText>(R.id.update_task)
        val updatePriority = view.findViewById<EditText>(R.id.update_priority)

        updateTask.setText(task.task)
        updatePriority.setText(task.priority.toString())

        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle("Update Task")
            setMessage("Please update the fields")
            setView(view)
            setPositiveButton("Update") {_, _ ->
                if(TextUtils.isEmpty(updateTask.text) || TextUtils.isEmpty(updatePriority.text)) {
                    Toast.makeText(this@MainActivity, "Field are empty", Toast.LENGTH_SHORT).show()
                }
                taskViewModel.update(Task(task.id, updateTask.text.toString(), updatePriority.text.toString().toInt()))
            }
            setNegativeButton("Cancel") {dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_delete_all -> taskViewModel.deleteAllTasks()
            else -> Log.e(tag, "Wrong item ID")
        }
        return super.onOptionsItemSelected(item)
    }
}