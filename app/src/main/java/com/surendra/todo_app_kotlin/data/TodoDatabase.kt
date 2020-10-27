package com.surendra.todo_app_kotlin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.surendra.todo_app_kotlin.data.models.ToDoData
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database ( entities = [ToDoData::class], version = 1)
@TypeConverters(Converter::class)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun getTodoDao() : TodoDao

    companion object {
        private var INSTANCE:TodoDatabase? = null


        @InternalCoroutinesApi
        fun getDatabase(context: Context) : TodoDatabase {
            val tempInstance= INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}