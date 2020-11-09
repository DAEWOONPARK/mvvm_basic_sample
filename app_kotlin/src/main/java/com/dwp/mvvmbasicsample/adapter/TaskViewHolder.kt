package com.dwp.mvvmbasicsample.adapter

import android.view.ContextMenu
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dwp.mvvmbasicsample.R

/**
 * Created by dwp on 2020-11-09.
 */
class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {
    val txtTask: TextView = itemView.findViewById(R.id.txt_task)
    val txtPriority: TextView = itemView.findViewById(R.id.txt_priority)

    init {
        itemView.setOnCreateContextMenuListener(this)
    }


    override fun onCreateContextMenu(contextMenu: ContextMenu, view: View?, contextMenuInfo: ContextMenu.ContextMenuInfo?) {
        contextMenu.setHeaderTitle("Select Menu")
        contextMenu.add(0, 0, adapterPosition, "Update")
        contextMenu.add(0, 1, adapterPosition, "Delete")
    }
}