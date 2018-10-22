package com.chanothai.assignment.data.api.retrofit

import com.chanothai.assignment.domain.entity.Avatars
import com.chanothai.assignment.domain.entity.LocationDetail
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("/api/character/")
    fun getAvatars(): Deferred<Response<Avatars>>

    @GET("/api/location/{id}")
    fun getLocation(@Path("id") locationId: String): Deferred<Response<LocationDetail>>
}