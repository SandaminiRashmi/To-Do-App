package com.coding.meet.todo_app.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Query
import com.coding.meet.todo_app.models.Task
import com.coding.meet.todo_app.repository.TaskRepository
import com.coding.meet.todo_app.utils.Resource

// ViewModel class responsible for handling UI-related data and operations
class TaskViewModel(application: Application) : AndroidViewModel(application) {

    // Instance of TaskRepository to interact with data
    private val taskRepository = TaskRepository(application)

    // LiveData to observe task list data changes
    val taskStateFlow get() =  taskRepository.taskStateFlow

    // LiveData to observe status updates
    val statusLiveData get() =  taskRepository.statusLiveData

    // LiveData to observe sort order changes
    val sortByLiveData get() =  taskRepository.sortByLiveData

    // Function to update sort order
    fun setSortBy(sort:Pair<String,Boolean>){
        taskRepository.setSortBy(sort)
    }

    // Function to retrieve task list data
    fun getTaskList(isAsc : Boolean, sortByName:String) {
        taskRepository.getTaskList(isAsc, sortByName)
    }

    // Function to insert a task
    fun insertTask(task: Task){
        taskRepository.insertTask(task)
    }

    // Function to delete a task
    fun deleteTask(task: Task) {
        taskRepository.deleteTask(task)
    }

    // Function to delete a task using its ID
    fun deleteTaskUsingId(taskId: String){
        taskRepository.deleteTaskUsingId(taskId)
    }

    // Function to update a task
    fun updateTask(task: Task) {
        taskRepository.updateTask(task)
    }

    // Function to update specific fields of a task
    fun updateTaskPaticularField(taskId: String,title:String,description:String) {
        taskRepository.updateTaskPaticularField(taskId, title, description)
    }

    // Function to search for tasks
    fun searchTaskList(query: String){
        taskRepository.searchTaskList(query)
    }
}