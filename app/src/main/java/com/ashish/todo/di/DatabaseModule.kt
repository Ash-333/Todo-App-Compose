package com.ashish.todo.di

import android.app.Application
import androidx.room.Room
import com.ashish.todo.db.TodoDao
import com.ashish.todo.db.TodoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesTodoDao(todoDataBase: TodoDataBase):TodoDao{
        return todoDataBase.todoDao()
    }

    @Provides
    @Singleton
    fun provideTodoDatabase(application:Application):TodoDataBase{
        return Room.databaseBuilder(
            application,
            TodoDataBase::class.java,
            "todo_db"
        ).fallbackToDestructiveMigration().build()
    }

}