package com.example.stockdemo.apiservice

import android.annotation.SuppressLint
import com.example.stockdemo.data.ExchangeReportBWIBBUALL
import com.example.stockdemo.data.ExchangeReportStockDayAll
import com.example.stockdemo.data.ExchangeReportStockDayAvgAll
import com.example.stockdemo.data.StockAll
import com.example.stockdemo.data.StockAvg
import com.example.stockdemo.data.StockBWIBBU
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface OpenApiTaiFexApiService {
    @GET(ExchangeReportBWIBBUALL)
    suspend fun getStockBWIBBU(): Response<List<StockBWIBBU>>

    @GET(ExchangeReportStockDayAvgAll)
    suspend fun getStockAvg(): Response<List<StockAvg>>

    @GET(ExchangeReportStockDayAll)
    suspend fun getStockAll(): Response<List<StockAll>>
}

@SuppressLint("NewApi")
object OpenApiTaiFexAipInstance {
    val api: OpenApiTaiFexApiService by lazy {
        val client: OkHttpClient = OkHttpClient.Builder()
            .connectionPool(ConnectionPool(5, 5, TimeUnit.MINUTES))
            //.dispatcher(Dispatcher())
            .build()
        Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(ApiService.gson))
            .client(client)
            .build()
            .create(OpenApiTaiFexApiService::class.java)
    }
}


fun createRetrofit(): Retrofit {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl("https://openapi.taifex.com.tw/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}