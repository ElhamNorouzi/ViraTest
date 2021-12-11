package com.example.viratest.api

import okhttp3.ResponseBody

interface RetrofitObject<T> {
    fun onSuccess(body: T)

    fun onFailure(message: String)
}