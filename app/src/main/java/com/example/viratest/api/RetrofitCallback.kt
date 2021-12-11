package com.example.viratest.api

import android.view.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RetrofitCallback {
    fun <T> callRetrofit(call: Call<T>, loading: View?, retrofitObject: RetrofitObject<T>) {

        if (loading != null) loading.visibility = View.VISIBLE
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (loading != null) loading.visibility = View.GONE

                if (response.isSuccessful) {
                    retrofitObject.onSuccess(body = response.body()!!)

                } else {
                    retrofitObject.onFailure(message = response.errorBody()!!.string())
                }
            }


            override fun onFailure(call: Call<T>, t: Throwable) {
                if (loading != null) loading.visibility = View.GONE

                retrofitObject.onFailure(message = t.message.toString())

            }


        })
    }
}