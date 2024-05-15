package com.coding.meet.todo_app.dao

import androidx.room.*
import com.coding.meet.todo_app.models.Task
import kotlinx.coroutines.flow.Flow

// Data Access Object (DAO) interface for Task operations with Room database
@Dao
interface TaskDao {

    // Query to retrieve tasks sorted by task title
    @Query("""SELECT * FROM Task ORDER BY
        CASE WHEN :isAsc = 1 THEN taskTitle END ASC, 
        CASE WHEN :isAsc = 0 THEN taskTitle END DESC""")
    fun getTaskListSortByTaskTitle(isAsc: Boolean) : Flow<List<Task>>

    // Query to retrieve tasks sorted by task date
    @Query("""SELECT * FROM Task ORDER BY
        CASE WHEN :isAsc = 1 THEN date END ASC, 
        CASE WHEN :isAsc = 0 THEN date END DESC""")
    fun getTaskListSortByTaskDate(isAsc: Boolean) : Flow<List<Task>>

    // Insert a new task
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long

    // First way
    // Delete a task by providing the Task object
    @Delete
    suspend fun deleteTask(task: Task) : Int

    // Second Way
    // Delete a task by providing the task ID
    @Query("DELETE FROM Task WHERE taskId == :taskId")
    suspend fun deleteTaskUsingId(taskId: String) : Int

    // Update a task
    @Update
    suspend fun updateTask(task: Task): Int

    // Update specific fields of a task
    @Query("UPDATE Task SET taskTitle=:title, description = :description WHERE taskId = :taskId")
    suspend fun updateTaskPaticularField(taskId:String,title:String,description:String): Int

    // Search for tasks by title
    @Query("SELECT * FROM Task WHERE taskTitle LIKE :query ORDER BY date DESC")
    fun searchTaskList(query: String) : Flow<List<Task>>
}