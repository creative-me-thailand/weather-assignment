package com.chanothai.assignment.domain.usecase

import com.chanothai.assignment.data.api.repository.CharacterRepo
import com.chanothai.assignment.domain.entity.Avatars
import com.chanothai.assignment.domain.entity.LocationDetail
import com.chanothai.assignment.domain.error.Errors
import com.chanothai.assignment.domain.input.GetLocationInput

class AvatarService(
        private val avatarRepo: CharacterRepo
) {
    suspend fun getAvatars(): Avatars {
        return avatarRepo.getAll()
    }

    suspend fun getLocation(input: GetLocationInput): LocationDetail {
        if (input.id.isBlank() || input.id.isEmpty()) {
            throw Errors.InvalidInputLocation
        }

        return avatarRepo.getLocationDetail(input.id)
    }
}