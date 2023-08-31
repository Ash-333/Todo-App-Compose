package com.ashish.todo.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoItem::class], version = 1, exportSchema = false)
abstract class TodoDataBase :RoomDatabase(){
    abstract fun todoDao():TodoDao
}