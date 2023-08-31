package com.ashish.todo.db

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepository @Inject constructor(private val todoDao:TodoDao) {


    suspend fun insertTodo(todo:TodoItem){
        todoDao.insertTodo(todo)
    }

    suspend fun updateTodo(todo:TodoItem){
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo:TodoItem){
        todoDao.deleteTodo(todo)
    }
    fun getAllTodo():Flow<List<TodoItem>>{
        return todoDao.getAllTodo()
    }
}