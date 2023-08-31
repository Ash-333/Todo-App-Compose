package com.ashish.todo.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="todo")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    val title:String,
    val description:String,
    val isCompleted:Boolean
)
