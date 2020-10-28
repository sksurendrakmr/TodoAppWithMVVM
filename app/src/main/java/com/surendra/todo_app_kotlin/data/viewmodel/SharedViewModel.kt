package com.surendra.todo_app_kotlin.data.viewmodel

import android.app.Application
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.surendra.todo_app_kotlin.R
import com.surendra.todo_app_kotlin.data.models.Priority
import com.surendra.todo_app_kotlin.data.models.ToDoData

class SharedViewModel(application: Application) :AndroidViewModel(application) {

    val emptyDatabase :MutableLiveData<Boolean> = MutableLiveData(true)



    fun checkIfEmpty(todoData: List<ToDoData>){
        emptyDatabase.value = todoData.isEmpty()
    }

    val listener: AdapterView.OnItemSelectedListener = object :
    AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position) {
                0 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))}
                1 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))}
                2 -> {(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))}
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            TODO("Not yet implemented")
        }
    }

     fun verifyDataFromUser(title:String, description: String) : Boolean {
        return !(title.isEmpty() || description.isEmpty())
    }

     fun parsePriority(priority:String): Priority {
        return when(priority){
            "High Priority" -> {
                Priority.HIGH}
            "Medium Priority" -> {
                Priority.MEDIUM}
            "Low Priority" -> {
                Priority.LOW}
            else -> Priority.LOW
        }
    }

}