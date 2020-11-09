package com.dwp.mvvmbasicsample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dwp.mvvmbasicsample.room.Task

/**
 * Created by dwp on 2020-11-02.
 */

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TaskRepository(application)
    private val allTasks = repository.getAllTasks()

    fun insert(task: Task) {
        repository.insert(task)
    }

    fun update(task: Task) {
        repository.update(task)
    }

    fun delete(task: Task) {
        repository.delete(task)
    }

    fun deleteAllTasks() {
        repository.deleteAllTasks()
    }

    fun getAllTasks(): LiveData<MutableList<Task>> {
        return allTasks;
    }
}