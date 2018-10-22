package com.chanothai.assignment.data.api.repository

import com.chanothai.assignment.domain.entity.Avatars
import com.chanothai.assignment.domain.entity.LocationDetail

interface CharacterAdapter {
    suspend fun getAll(): Avatars
    suspend fun getLocationDetail(id: String): LocationDetail
}