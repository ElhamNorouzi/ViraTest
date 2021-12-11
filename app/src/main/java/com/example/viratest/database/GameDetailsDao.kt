package com.example.viratest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.viratest.api.model.GameBean

@Dao
interface GameDetailsDao {

    @Query("SELECT * FROM gamebean")
    fun getDetailgames(): GameBean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailGames(vararg gameBean: GameBean)
}