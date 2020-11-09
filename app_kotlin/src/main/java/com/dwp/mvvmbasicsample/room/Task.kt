package com.dwp.mvvmbasicsample.room

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Created by dwp on 2020-10-30.
 */
@Entity(tableName = "task_table")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val task: String?,
    val priority: Int?
)