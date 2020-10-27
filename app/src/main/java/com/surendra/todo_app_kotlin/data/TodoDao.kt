package com.surendra.todo_app_kotlin.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.surendra.todo_app_kotlin.data.models.ToDoData

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTask() : LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(toDoData: ToDoData)

    @Update
    suspend fun updateTask(toDoData: ToDoData)

    @Delete
    suspend fun deleteATask(toDoData: ToDoData)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTask()
}