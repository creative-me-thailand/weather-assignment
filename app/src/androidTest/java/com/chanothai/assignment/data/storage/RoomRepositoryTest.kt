package com.chanothai.assignment.data.storage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import com.chanothai.assignment.domain.entity.database.AvatarEntity
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RoomRepositoryTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var avatarDao: AvatarDao
    private lateinit var avatarRoomDatabase: AvatarRoomDatabase

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getTargetContext().applicationContext
        avatarRoomDatabase = Room.inMemoryDatabaseBuilder(context, AvatarRoomDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        avatarDao = avatarRoomDatabase.avatarDao()
    }

    @After
    fun closeDB() {
        avatarRoomDatabase.close()
    }

    @Test
    fun writeCharacterAndReadData() {
        val entity = AvatarEntity(1).apply {
            this.name = "Chicken"
        }

        avatarDao.insert(entity)
        val data = avatarDao.getAvatars()
        assertEquals(data[0].name, entity.name)
    }

    @Test
    fun getAvatarWithID() {
        val entity = AvatarEntity(1).apply {
            this.name = "Chicken"
        }

        avatarDao.insert(entity)

        val avatar = avatarDao.getAvatar(1)

        assertEquals(entity.name, avatar.name)
    }
}