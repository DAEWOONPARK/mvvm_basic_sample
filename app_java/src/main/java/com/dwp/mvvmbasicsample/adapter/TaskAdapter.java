package com.dwp.mvvmbasicsample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dwp.mvvmbasicsample.R;
import com.dwp.mvvmbasicsample.room.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dwp on 2020-11-03.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    private List<Task> allTasks = new ArrayList<>();

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.textTask.setText(allTasks.get(position).getTask());
        holder.textPriority.setText(String.valueOf(allTasks.get(position).getPriority()));
    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }

    public void setAllTasks(List<Task> allTasks) {
        this.allTasks = allTasks;
        notifyDataSetChanged();
    }

    public Task getTask(int position) {
        return allTasks.get(position);
    }
}