package com.chanothai.assignment.data.api.repository

import androidx.lifecycle.LiveData
import com.chanothai.assignment.domain.entity.api.Avatars
import com.chanothai.assignment.domain.entity.database.AvatarEntity
import com.chanothai.assignment.presenter.model.Resource

interface CharacterAdapter {
    suspend fun get(id: String): LiveData<Resource<AvatarEntity>>
    suspend fun getAll(): Avatars
}