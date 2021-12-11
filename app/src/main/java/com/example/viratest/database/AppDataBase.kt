package com.example.viratest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.viratest.api.model.GameBean

@Database(entities = [GameBean::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun gameListDao(): GameListDao
    abstract fun getDetailList(): GameDetailsDao

    companion object{
      private lateinit var db : AppDataBase
        fun getInstance(context: Context): AppDataBase{
            db =Room.databaseBuilder(context,AppDataBase::class.java,"ViraDataBase")
                .allowMainThreadQueries().build()
            return db
        }
    }

}