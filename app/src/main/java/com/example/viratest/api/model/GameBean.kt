package com.example.viratest.api.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GameBean(
    @PrimaryKey(autoGenerate = true)val id: Int,
    @ColumnInfo(name = "userId")val userId: Int,
    @ColumnInfo(name = "id")val id: Int,
    @ColumnInfo(name = "rate")val rate: String,
    @ColumnInfo(name = "player_count")@SerializedName("player_count")val playerCont: Int,
    @Embedded val genre: GenreBean,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "video")val video: String
    ){
    constructor(): this(0,"","","",0,GenreBean(),"","")
}