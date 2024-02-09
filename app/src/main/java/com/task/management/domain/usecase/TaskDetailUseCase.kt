package com.task.management.domain.usecase

import com.task.management.domain.entities.TaskModel
import com.task.management.domain.repo.TaskDetailRepository
import kotlinx.coroutines.flow.Flow

class TaskDetailUseCase(private val repository: TaskDetailRepository) {


    // modify response here if you want

    suspend fun getTaskList(): Flow<List<TaskModel>> {
        return repository.getAllTasks()
    }

    suspend fun addTask(taskDataModel: TaskModel) = repository.addTask(taskDataModel)

    suspend fun updateTask(taskDataModel: TaskModel) = repository.updateTaskDetail(taskDataModel)

    suspend fun deleteTask(taskDataModel: TaskModel) = repository.deleteTask(taskDataModel)

    suspend fun geTaskById(id : Long) = repository.getTaskById(id)
}