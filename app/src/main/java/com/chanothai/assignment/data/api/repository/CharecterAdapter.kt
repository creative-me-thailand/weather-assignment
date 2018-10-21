package com.chanothai.assignment.data.api.repository

import com.chanothai.assignment.domain.entity.Avatars

interface CharecterAdapter {
    suspend fun getAll(): Avatars
}