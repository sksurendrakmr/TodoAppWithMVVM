package com.surendra.todo_app_kotlin.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "todo_table")
data class ToDoData (
    val title : String,
    val priority: Priority,
    val description : String
):Serializable {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}