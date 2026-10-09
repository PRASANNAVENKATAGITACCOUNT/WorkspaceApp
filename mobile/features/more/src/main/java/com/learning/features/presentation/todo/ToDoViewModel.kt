package com.learning.features.presentation.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.features.data.Repository
import com.learning.features.data.local.TaskEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ToDoViewModel constructor(
    val repository: Repository
) : ViewModel() {

    private var _taskEntityList : MutableStateFlow<List<TaskEntity>> = MutableStateFlow(listOf(TaskEntity()))
    val taskList = _taskEntityList.asStateFlow()

    private var _selectedTaskEntity : MutableStateFlow<TaskEntity> = MutableStateFlow(TaskEntity())

    val selectedTask= _selectedTaskEntity.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllTasks()
        }
    }


    suspend fun getAllTasks(){
        repository.getAllTasks().collect { tasks->
            _taskEntityList.value = tasks
        }
    }


    fun setSelectedTask(taskEntity: TaskEntity){
        _selectedTaskEntity.value = taskEntity
    }


    fun insertTask(taskEntity: TaskEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveOrUpdateTask(taskEntity)
        }
    }


    fun deleteTask(taskEntity: TaskEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(taskEntity)
        }
    }



}