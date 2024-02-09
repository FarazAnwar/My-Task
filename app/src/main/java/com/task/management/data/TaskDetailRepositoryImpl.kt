package com.task.management.data

import com.task.management.data.db.TaskDetailDao
import com.task.management.domain.entities.TaskModel
import com.task.management.domain.repo.TaskDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskDetailRepositoryImpl @Inject constructor(private val dao: TaskDetailDao) : TaskDetailRepository {

    override suspend fun addTask(taskDataModel: TaskModel) {
        dao.insertTaskDetail(taskDataModel)
    }

    override suspend fun getAllTasks(): Flow<List<TaskModel>> {
        return dao.getAllTask()
    }

    override suspend fun updateTaskDetail(taskDataModel: TaskModel) {
        dao.updateTaskDetail(taskDataModel)
    }

    override suspend fun deleteTask(taskDataModel: TaskModel) {
        dao.deleteTask(taskDataModel)
    }

    override suspend fun getTaskById(id: Long) {

    }
}