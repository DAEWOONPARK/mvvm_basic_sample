package com.dwp.mvvmbasicsample.adapter;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dwp.mvvmbasicsample.R;

/**
 * Created by dwp on 2020-11-09.
 */
public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    TextView textTask, textPriority;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        textTask = itemView.findViewById(R.id.txt_task);
        textPriority = itemView.findViewById(R.id.txt_priority);
        itemView.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Select Menu");
        contextMenu.add(0, 0, getAdapterPosition(), "Update");
        contextMenu.add(0, 1, getAdapterPosition(), "Delete");
    }
}