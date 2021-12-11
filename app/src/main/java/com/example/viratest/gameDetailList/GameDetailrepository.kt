package com.example.viratest.gameDetailList

import android.content.Context
import com.example.viratest.api.model.GameBean
import com.example.viratest.database.AppDataBase

object GameDetailrepository {

    var game = GameBean()
    fun getLocalDetails(context: Context): GameBean{
       game= AppDataBase.getInstance(context).getDetailList().getDetailgames()
        return game
    }
}