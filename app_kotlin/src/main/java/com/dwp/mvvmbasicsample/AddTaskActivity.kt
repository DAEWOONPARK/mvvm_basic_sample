package com.dwp.mvvmbasicsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddTaskActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TASK = "task"
        const val EXTRA_PRIORITY = "priority"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val editTask = findViewById<EditText>(R.id.edit_task)
        val editPriority = findViewById<EditText>(R.id.edit_priority)
        val btSave = findViewById<Button>(R.id.bt_save)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Add Task"
        btSave.setOnClickListener {
            val taskString = editTask.text.toString()
            val priorityString = editPriority.text.toString()

            if(TextUtils.isEmpty(taskString) || TextUtils.isEmpty(priorityString)) {
                Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show()
            }

            val data = Intent()
            data.putExtra(EXTRA_TASK, taskString)
            data.putExtra(EXTRA_PRIORITY, priorityString)
            setResult(RESULT_OK, data)
            finish()
        }

    }
}