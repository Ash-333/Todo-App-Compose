package com.ashish.todo.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo:TodoItem)

    @Update
    suspend fun updateTodo(todo:TodoItem)

    @Query("Select * FROM todo")
    fun getAllTodo(): Flow<List<TodoItem>>

    @Delete
    suspend fun deleteTodo(todo:TodoItem)


}