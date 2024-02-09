package com.task.management.di

import com.task.management.MyApplication
import com.task.management.data.TaskDetailRepositoryImpl
import com.task.management.data.db.TaskDetailDatabase
import com.task.management.domain.entities.TaskViewModel
import com.task.management.domain.repo.TaskDetailRepository
import com.task.management.domain.usecase.TaskDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class TaskDetailModule {

    @ViewModelScoped
    @Provides
    fun providesUserDetailUseCase(repository: TaskDetailRepository) : TaskDetailUseCase = TaskDetailUseCase(repository)

    @ViewModelScoped
    @Provides
    fun providesUserDetailRepository() : TaskDetailRepository {
        val db = TaskDetailDatabase.getDatabase(MyApplication.instance)
        return  TaskDetailRepositoryImpl(db.userDetail())
    }

    @ViewModelScoped
    @Provides
    fun providesTaskViewModel(useCase: TaskDetailUseCase): TaskViewModel = TaskViewModel(useCase)

}