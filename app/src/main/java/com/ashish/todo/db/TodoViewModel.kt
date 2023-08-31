package com.ashish.todo.db

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val todoRepository: TodoRepository): ViewModel() {
    val _todo= MutableStateFlow<List<TodoItem>>(emptyList())

    init {
        getAllTodo()
    }

    fun getAllTodo()=viewModelScope.launch {
        todoRepository.getAllTodo().catch {
            Log.d("Main",it.message.toString())
        }.collect{
            _todo.value=it
        }
    }
    fun insertTodo(todo:TodoItem){
        viewModelScope.launch {
            todoRepository.insertTodo(todo)
        }
    }

    fun deleteTodo(todo: TodoItem){
        viewModelScope.launch {
            todoRepository.deleteTodo(todo)
        }
    }

    fun updateTodo(todo:TodoItem){
        viewModelScope.launch {
            todoRepository.updateTodo(todo)
        }
    }
}