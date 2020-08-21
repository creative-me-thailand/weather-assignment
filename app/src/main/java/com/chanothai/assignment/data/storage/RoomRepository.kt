package com.chanothai.assignment.data.storage

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.chanothai.assignment.domain.entity.database.AvatarEntity
import com.chanothai.assignment.domain.error.Errors
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

class RoomRepository(context: Context) {

    private var avatarDao: AvatarDao? = null

    var avatarLiveData: AvatarEntity? = null
    get() {
        return field?.let {
            it
        }
    }
    set(value) {
        value?.let {
            field = it
        }
    }

    init {
        val roomDB = AvatarRoomDatabase.getDatabase(context)
        avatarDao = roomDB.avatarDao()
    }

    suspend fun insertAvatar(avatarEntity: AvatarEntity) {
        async(CommonPool) {
            avatarDao?.insert(avatarEntity)
            return@async
        }.await()
    }

    suspend fun queryAvatar(id: String): AvatarEntity {
        avatarLiveData = async(CommonPool) { avatarDao?.getAvatar(id.toInt()) }.await()

        return avatarLiveData ?: throw Errors.AvatarNotFound
    }

    fun queryAvatars(): List<AvatarEntity> {
        return avatarDao?.getAvatars() ?: throw Errors.AvatarNotFound

    }
}