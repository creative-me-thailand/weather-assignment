package com.chanothai.assignment.data.api.retrofit

import com.chanothai.assignment.domain.entity.api.Avatars
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("/api/character/")
    fun getAvatars(): Deferred<Response<Avatars>>
}