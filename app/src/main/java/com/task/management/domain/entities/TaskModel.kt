package com.task.management.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Task")
data class TaskModel(
    @PrimaryKey var id : Long = 0,
    var taskName: String = "",
    var description: String = "",
    var taskProity : String = "") {}