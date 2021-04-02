package com.udacity.capstone.formula1.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://ergast.com/api/f1/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

interface F1Gateway {
    @GET("2021/drivers")
    suspend fun getDrivers(): String

    @GET("2021/constructors")
    suspend fun getConstructors(): String
}

object F1GatewayImpl {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val retrofitService: F1Gateway = retrofit.create(F1Gateway::class.java)
}