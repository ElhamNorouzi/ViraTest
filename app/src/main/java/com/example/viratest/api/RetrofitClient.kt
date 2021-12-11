package com.example.viratest.api

import com.example.viratest.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getService():ApiCall{
        if (retrofit == null ){
            retrofit= Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                        OkHttpClient.Builder()
                            .addInterceptor(HeaderInterceptor())
                            .connectTimeout(Constants.CONNECT_TIME_OUT,TimeUnit.SECONDS)
                            .readTimeout(Constants.READ_TIME_OUT,TimeUnit.SECONDS)
                            .writeTimeout(Constants.WRITE_TIME_OUT,TimeUnit.SECONDS)
                            .build()
                )
                .build()
        }
        return retrofit!!.create(ApiCall::class.java)
    }


}