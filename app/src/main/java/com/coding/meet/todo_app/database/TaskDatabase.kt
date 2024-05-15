package com.coding.meet.todo_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.coding.meet.todo_app.converters.TypeConverter
import com.coding.meet.todo_app.dao.TaskDao
import com.coding.meet.todo_app.models.Task

// Database class representing the Room database for tasks
@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)// Use the TypeConverter for Date conversions
abstract class TaskDatabase : RoomDatabase() {

    // Define the abstract method to access the DAO
    abstract val taskDao : TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        // Get an instance of the database (singleton pattern)
        fun getInstance(context: Context): TaskDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_db" // Define the name of the database file
                ).build().also {
                    INSTANCE = it
                }
            }

        }
    }

}