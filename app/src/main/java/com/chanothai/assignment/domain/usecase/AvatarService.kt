package com.chanothai.assignment.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chanothai.assignment.data.api.repository.CharacterRepo
import com.chanothai.assignment.domain.entity.api.Avatars
import com.chanothai.assignment.domain.entity.database.AvatarEntity
import com.chanothai.assignment.domain.error.Errors
import com.chanothai.assignment.domain.error.NotFoundException
import com.chanothai.assignment.domain.input.GetAvatarInput
import com.chanothai.assignment.presenter.model.Resource
import com.chanothai.assignment.presenter.model.ResourceState

class AvatarService(
        private val avatarRepo: CharacterRepo
) {
    suspend fun getAvatars(): Avatars {
        return avatarRepo.getAll()
    }

    suspend fun getAvatar(input: GetAvatarInput): LiveData<Resource<AvatarEntity>> {
        return try {
            if (input.id.isBlank() || input.id.isEmpty()) {
                throw Errors.InvalidInputGetAavatar
            }

            val result = avatarRepo.get(input.id).value

            MutableLiveData<Resource<AvatarEntity>>().apply {
                this.value = result
            }
        } catch (e: NotFoundException) {
            val mAvatar = MutableLiveData<Resource<AvatarEntity>>()
            mAvatar.value = Resource<AvatarEntity>(ResourceState.FAILED).apply {
                this.errorMessage = e.message
            }

            mAvatar
        }
    }
}