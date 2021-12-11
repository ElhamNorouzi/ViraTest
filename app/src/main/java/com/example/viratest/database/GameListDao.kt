package com.example.viratest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.viratest.api.model.GameBean

@Dao
interface GameListDao {

    @Query("SELECT * FROM gamebean")
    fun getGameList(): List<GameBean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGames(gameBean: List<GameBean>)
}