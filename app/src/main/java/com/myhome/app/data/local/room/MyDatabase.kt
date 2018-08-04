package com.myhome.app.data.local.room


import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.myhome.app.data.model.Article


@Database(entities = [(Article::class)], version = 1, exportSchema = false)
@TypeConverters(MediaTypeConverter::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun taskDao(): DataDao

}