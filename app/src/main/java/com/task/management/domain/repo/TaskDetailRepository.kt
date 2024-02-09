package com.task.management.domain.repo

import com.task.management.domain.entities.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskDetailRepository {

    suspend fun addTask(taskDataModel: TaskModel)

    suspend fun getAllTasks(): Flow<List<TaskModel>>

    suspend fun updateTaskDetail(taskDataModel: TaskModel)

    suspend fun deleteTask(taskDataModel: TaskModel)

    suspend fun getTaskById(id : Long)
}