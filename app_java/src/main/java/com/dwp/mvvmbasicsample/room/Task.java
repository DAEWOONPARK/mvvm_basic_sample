package com.dwp.mvvmbasicsample.room;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by dwp on 2020-11-03.
 */
@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String task;
    private int priority;

    public Task(String task, int priority) {
        this.task = task;
        this.priority = priority;
    }

    @Ignore
    public Task(int id, String task, int priority) {
        this.id = id;
        this.task = task;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
