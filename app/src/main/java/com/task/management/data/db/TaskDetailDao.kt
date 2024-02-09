package com.task.management.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.task.management.domain.entities.TaskModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskDetail(taskDetail : TaskModel)

    @Update
    suspend fun updateTaskDetail(taskDetail: TaskModel)

    @Query("select * from `Task`")
    fun getAllTask() : Flow<List<TaskModel>>

    @Query("select * from `Task` where taskName=:taskName")
    fun getUserDetailByName(taskName:String) : List<TaskModel>

    @Query("update `Task` set description=:desc")
    suspend fun updateUserNumber(desc : String)

    @Query("select * from `Task` where id=:taskId")
    suspend fun getUserById(taskId : Long) : TaskModel?

    @Delete
    suspend fun deleteTask(taskDetail: TaskModel)
}