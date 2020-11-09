package com.dwp.mvvmbasicsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dwp.mvvmbasicsample.R
import com.dwp.mvvmbasicsample.room.Task

/**
 * Created by dwp on 2020-11-02.
 */
class TaskAdapter : RecyclerView.Adapter<TaskViewHolder>() {
    private var allTasks: MutableList<Task> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.txtTask.text = allTasks[position].task
        holder.txtPriority.text = allTasks[position].priority.toString()
    }

    override fun getItemCount(): Int = allTasks.size

    fun setAllTasks(allTasks: MutableList<Task>) {
        this.allTasks = allTasks
        notifyDataSetChanged()
    }

    fun getTask(position: Int): Task = allTasks[position]
}