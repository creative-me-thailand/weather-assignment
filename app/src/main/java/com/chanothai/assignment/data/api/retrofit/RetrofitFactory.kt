package com.chanothai.assignment.data.api.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import retrofit2.Retrofit

object RetrofitFactory {
    private const val BASE_URL = "https://rickandmortyapi.comc"

    val retrofitService:() -> RetrofitService = fun(): RetrofitService {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(RetrofitService::class.java)
    }
}