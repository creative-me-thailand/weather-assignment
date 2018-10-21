package com.chanothai.assignment.data.api.repository

import com.chanothai.assignment.data.api.retrofit.RetrofitFactory
import com.chanothai.assignment.domain.entity.Avatars
import java.lang.Exception

class CharacterRepo: CharecterAdapter {
    override suspend fun getAll(): Avatars {
        val service = RetrofitFactory.retrofitService()

        val response = service.getAvatars().await()
        if (response.isSuccessful) {
            return response.body()!!
        }else {
            throw Exception(response.errorBody().toString())
        }
    }
}