package com.chanothai.assignment.data.api.repository

import com.chanothai.assignment.data.api.retrofit.RetrofitFactory
import com.chanothai.assignment.domain.entity.Avatars
import com.chanothai.assignment.domain.entity.LocationDetail
import com.chanothai.assignment.domain.error.Errors
import java.lang.Exception

class CharacterRepo: CharacterAdapter {
    override suspend fun getAll(): Avatars {
        val service = RetrofitFactory.retrofitService()

        val response = service.getAvatars().await()
        if (response.isSuccessful) {
            return response.body() ?: throw Errors.AvatarNotFound
        }else {
            throw Exception(response.errorBody().toString())
        }
    }

    override suspend fun getLocationDetail(id: String): LocationDetail {
        val service = RetrofitFactory.retrofitService()

        val response = service.getLocation(id).await()
        if (response.isSuccessful) {
            return response.body() ?: throw Errors.LocationNotFound
        }else {
            throw Exception(response.errorBody().toString())
        }
    }
}