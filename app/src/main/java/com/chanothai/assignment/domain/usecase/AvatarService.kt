package com.chanothai.assignment.domain.usecase

import com.chanothai.assignment.data.api.repository.CharacterRepo
import com.chanothai.assignment.domain.entity.Avatars

class AvatarService(
        private val avatarRepo: CharacterRepo
) {
    suspend fun getAvatars(): Avatars {
        return avatarRepo.getAll()
    }
}