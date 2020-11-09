package com.dwp.mvvmbasicsample.room

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Created by dwp on 2020-10-30.
 */

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        var instance: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase {
            if(instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                    TaskDatabase::class.java, "task_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }
            return instance as TaskDatabase
        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance).execute()
            }
        }
    }

    class PopulateDbAsyncTask(db: TaskDatabase?) : AsyncTask<Void, Void, Void>() {
        private val taskDao: TaskDao = db?.taskDao()!!
        override fun doInBackground(vararg p0: Void?): Void? {
            taskDao.insert(Task(task = "Task1", priority = 1))
            taskDao.insert(Task(task = "Task2", priority = 1))
            taskDao.insert(Task(task = "Task3", priority = 1))
            return null
        }
    }


}