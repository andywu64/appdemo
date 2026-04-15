package com.example.stockdemo.apiservice

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder

object ApiService {
    const val BASE_URL = "https://openapi.twse.com.tw/"//"https://openapi.taifex.com.tw/"

    @RequiresApi(Build.VERSION_CODES.O)
    val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

}