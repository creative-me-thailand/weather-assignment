package com.chanothai.assignment.data.api.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.chanothai.assignment.data.api.retrofit.RetrofitFactory
import com.chanothai.assignment.data.storage.RoomRepository
import com.chanothai.assignment.domain.entity.api.Avatars
import com.chanothai.assignment.domain.entity.database.AvatarEntity
import com.chanothai.assignment.domain.error.Errors
import com.chanothai.assignment.presenter.model.Resource
import com.chanothai.assignment.presenter.model.ResourceState
import java.lang.Exception

class CharacterRepo(
        private val roomRepo: RoomRepository
) : CharacterAdapter {
    override suspend fun getAll(): Avatars {
        val service = RetrofitFactory.retrofitService()

        val response = service.getAvatars().await()
        if (response.isSuccessful) {

            val avatars = response.body() ?: throw Errors.AvatarNotFound

            //store data in room database
            avatars.results.forEachIndexed { index, avatar ->
                val avatarEntity = AvatarEntity(avatar.id).apply {
                    this.name = avatar.name
                    this.image = avatar.image
                    this.status = avatar.status
                    this.species = avatar.species
                    this.gender = avatar.gender
                    this.origin = avatar.origin.name
                    this.location = avatar.location.name
                    this.created = avatar.created
                }

                roomRepo.insertAvatar(avatarEntity)
            }

            return avatars
        } else {
            throw Exception(response.errorBody().toString())
        }
    }

    override suspend fun get(id: String): LiveData<Resource<AvatarEntity>> {
        try {
            val entity = roomRepo.queryAvatar(id)

            return MutableLiveData<Resource<AvatarEntity>>().apply {
                this.value = Resource<AvatarEntity>(ResourceState.SUCCESS).apply {
                    this.data = entity
                }
            }
        } catch (e: Exception) {
            val mAvatar = MutableLiveData<Resource<AvatarEntity>>()
            mAvatar.value = Resource<AvatarEntity>(ResourceState.FAILED).apply {
                this.errorMessage = e.message
            }

            return mAvatar
        }
    }
}