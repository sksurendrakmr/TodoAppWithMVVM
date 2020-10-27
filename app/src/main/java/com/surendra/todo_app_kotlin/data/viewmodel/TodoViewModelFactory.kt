package com.surendra.todo_app_kotlin.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.surendra.todo_app_kotlin.data.repository.TodoRepository

class TodoViewModelFactory(val repository: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodoViewModel(repository) as T
    }

}