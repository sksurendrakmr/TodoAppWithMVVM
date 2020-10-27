package com.surendra.todo_app_kotlin.data.repository

import androidx.lifecycle.LiveData
import com.surendra.todo_app_kotlin.data.TodoDatabase
import com.surendra.todo_app_kotlin.data.models.ToDoData

class TodoRepository (private val todoDatabase: TodoDatabase) {

    fun getAllTasks() : LiveData<List<ToDoData>> = todoDatabase.getTodoDao().getAllTask()

    suspend fun inertTask(toDoData: ToDoData) = todoDatabase.getTodoDao().insertTask(toDoData)

    suspend fun updateTask(toDoData: ToDoData) = todoDatabase.getTodoDao().updateTask(toDoData)

    suspend fun deleteATask(toDoData: ToDoData) = todoDatabase.getTodoDao().deleteATask(toDoData)

    suspend fun deleteAllTask() = todoDatabase.getTodoDao().deleteAllTask()
}