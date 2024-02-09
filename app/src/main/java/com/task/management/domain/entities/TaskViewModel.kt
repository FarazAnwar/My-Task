package com.task.management.domain.entities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.management.domain.usecase.TaskDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel class TaskViewModel @Inject constructor(private val useCase: TaskDetailUseCase) : ViewModel() {

    private var _userList = MutableLiveData<List<TaskModel>>(emptyList())
    var userList: LiveData<List<TaskModel>> = _userList

    init {
        getTaskList()
    }

    fun addTask(taskDataModel: TaskModel) {
        viewModelScope.launch {
            useCase.addTask(taskDataModel)
            // refresh list
            getTaskList()
        }
    }

    private fun getTaskList() {
        viewModelScope.launch {
            useCase.getTaskList().collect {
                // it will show recently added item on top
                if (it.isNotEmpty()) {
                    if (it.size > 1) _userList.value = it.reversed()
                    else _userList.value = it
                }else _userList.value = emptyList()
            }
        }
    }

    fun updateTaskDetail(taskDataModel: TaskModel) {
        viewModelScope.launch {
            useCase.updateTask(taskDataModel)
            // refresh list
            getTaskList()
        }
    }


    fun deleteTask(taskDataModel: TaskModel) {
        viewModelScope.launch {
            useCase.deleteTask(taskDataModel)
            // refresh list
            getTaskList()
        }
    }

}