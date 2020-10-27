package com.surendra.todo_app_kotlin.data

import androidx.room.TypeConverter
import com.surendra.todo_app_kotlin.data.models.Priority

class Converter  {

    @TypeConverter
    fun fromPriority(priority: Priority) :String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority:String): Priority {
        return Priority.valueOf(priority)
    }
}