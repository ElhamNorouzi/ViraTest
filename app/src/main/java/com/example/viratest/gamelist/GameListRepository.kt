package com.example.viratest.gamelist

import android.content.Context
import com.example.viratest.api.model.GameBean
import com.example.viratest.database.AppDataBase

object GameListRepository {
    val data: MutableList<GameBean> = ArrayList()

    fun getLocalData(context: Context): List<GameBean>{

        data.addAll(AppDataBase.getInstance(context).gameListDao().getGameList())
        return data
    }
}