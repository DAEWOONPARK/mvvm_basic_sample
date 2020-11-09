package com.dwp.mvvmbasicsample

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.dwp.mvvmbasicsample.room.Task
import com.dwp.mvvmbasicsample.room.TaskDao
import com.dwp.mvvmbasicsample.room.TaskDatabase

/**
 * Created by dwp on 2020-10-30.
 */
class TaskRepository(application: Application) {
    private val database = TaskDatabase.getInstance(application)
    private val taskDao = database.taskDao()
    private val allTasks = taskDao.getAllTasks()

    fun insert(task: Task) {
        InsertTaskAsyncTask(taskDao).execute(task)
    }

    fun update(task: Task) {
        UpdateTaskAsyncTask(taskDao).execute(task)
    }

    fun delete(task: Task) {
        DeleteTaskAsyncTask(taskDao).execute(task)
    }

    fun deleteAllTasks() {
        DeleteAllTasksAsyncTask(taskDao).execute()
    }

    fun getAllTasks(): LiveData<MutableList<Task>> {
        return allTasks
    }

    private class InsertTaskAsyncTask(val taskDao: TaskDao) : AsyncTask<Task, Void, Void>() {
        override fun doInBackground(vararg tasks: Task): Void? {
            taskDao.insert(tasks[0])
            return null
        }
    }

    private class UpdateTaskAsyncTask(val taskDao: TaskDao) : AsyncTask<Task, Void, Void>() {
        override fun doInBackground(vararg tasks: Task): Void? {
            taskDao.update(tasks[0])
            return null
        }
    }

    private class DeleteTaskAsyncTask(val taskDao: TaskDao) : AsyncTask<Task, Void, Void>() {
        override fun doInBackground(vararg tasks: Task): Void? {
            taskDao.delete(tasks[0])
            return null
        }
    }

    private class DeleteAllTasksAsyncTask(val taskDao: TaskDao) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg voids: Void): Void? {
            taskDao.deleteAllTasks()
            return null
        }
    }
}