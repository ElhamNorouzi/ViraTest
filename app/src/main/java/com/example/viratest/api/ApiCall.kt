package com.example.viratest.api

import com.example.viratest.api.model.GameBean
import retrofit2.Call
import retrofit2.http.GET

interface ApiCall {

    @GET("pnfbu")
    fun getGames(): Call<List<GameBean>>

    @GET("1bjyoa")
    fun getGameDetails(): Call<GameBean>
}