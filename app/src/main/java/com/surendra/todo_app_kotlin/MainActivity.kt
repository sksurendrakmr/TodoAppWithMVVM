package com.surendra.todo_app_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.surendra.todo_app_kotlin.data.TodoDatabase
import com.surendra.todo_app_kotlin.data.models.ToDoData
import com.surendra.todo_app_kotlin.data.repository.TodoRepository
import com.surendra.todo_app_kotlin.data.viewmodel.TodoViewModel
import com.surendra.todo_app_kotlin.data.viewmodel.TodoViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: TodoViewModel
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.navHost))

        val repository = TodoRepository(TodoDatabase.getDatabase(this))
        val viewModelFactory = TodoViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TodoViewModel::class.java)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHost)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}