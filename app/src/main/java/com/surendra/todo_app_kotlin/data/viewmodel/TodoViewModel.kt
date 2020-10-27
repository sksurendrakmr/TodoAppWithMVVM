package com.surendra.todo_app_kotlin.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surendra.todo_app_kotlin.data.models.ToDoData
import com.surendra.todo_app_kotlin.data.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel(){

     val  getAllTasks: LiveData<List<ToDoData>> = repository.getAllTasks()

    fun insert(toDoData: ToDoData)= viewModelScope.launch(Dispatchers.IO) {
        repository.inertTask(toDoData)
    }

    fun update(toDoData: ToDoData) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateTask(toDoData)
    }

    fun deleteATask(toDoData: ToDoData) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteATask(toDoData)
    }

    fun deleteAllTask()=viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllTask()
    }




}