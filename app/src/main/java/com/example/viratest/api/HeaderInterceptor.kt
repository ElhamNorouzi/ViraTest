package com.example.viratest.api

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val user =
            "Android ${Build.MANUFACTURER} ${Build.BRAND} ${Build.MODEL} ${Build.VERSION.SDK_INT}"
        val request = chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("User-agent", user)
            .build()
        return chain.proceed(request)
    }
}