package com.dwp.mvvmbasicsample.room

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by dwp on 2020-10-30.
 */

@Dao
interface TaskDao {
    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("DELETE FROM task_table")
    fun deleteAllTasks()

    @Query("SELECT * FROM task_table ORDER BY priority DESC")
    fun getAllTasks(): LiveData<MutableList<Task>>
}