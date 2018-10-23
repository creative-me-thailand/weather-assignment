package com.chanothai.assignment.data.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chanothai.assignment.domain.entity.database.AvatarEntity

@Dao
interface AvatarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(avatarEntity: AvatarEntity)

    @Query("DELETE FROM avatar_table")
    fun deleteAll()

    @Query("SELECT * FROM avatar_table WHERE avatar_id = :id")
    fun getAvatar(id: Int): AvatarEntity

    @Query("SELECT * FROM avatar_table")
    fun getAvatars(): List<AvatarEntity>
}