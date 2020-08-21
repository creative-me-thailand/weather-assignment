package com.chanothai.assignment.data.api.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.chanothai.assignment.data.storage.RoomRepository
import com.chanothai.assignment.domain.entity.database.AvatarEntity
import com.chanothai.assignment.domain.error.NotFoundException
import com.chanothai.assignment.presenter.model.Resource
import com.chanothai.assignment.presenter.model.ResourceState
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterRepoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var characterRepo: CharacterRepo
    private lateinit var roomRepo: RoomRepository

    @Before
    fun setup() {
        roomRepo = RoomRepository(
                InstrumentationRegistry.getTargetContext()
        )

        characterRepo = CharacterRepo(roomRepo)
    }

    @Test
    fun get_Avatars_Success() {
        val avatars = runBlocking { characterRepo.getAll() }
        assertTrue(avatars.results.size > 0)
    }

    @Test
    fun get_Avatar_Success() {
        val id = "1"

        var resource: LiveData<Resource<AvatarEntity>>? = null
        runBlocking {
            resource = characterRepo.get(id)
        }

        var resourceExp: Resource<AvatarEntity>? = null
        val observe = object : Observer<Resource<AvatarEntity>> {
            override fun onChanged(t: Resource<AvatarEntity>?) {
                t?.let {
                    resourceExp = it
                }

                resource?.removeObserver(this)
            }
        }
        resource?.observeForever(observe)

        assertNotNull(resourceExp)
    }

    @Test
    fun get_Avatar_Failed() {
        val id = "0"

        var resource: LiveData<Resource<AvatarEntity>>? = null

        runBlocking {
            resource = characterRepo.get(id)
        }

        var resourceExp: Resource<AvatarEntity>? = null
        val observe = object : Observer<Resource<AvatarEntity>> {
            override fun onChanged(t: Resource<AvatarEntity>?) {
                t?.let {
                    resourceExp = it
                }
                resource?.removeObserver(this)
            }
        }

        resource?.observeForever(observe)

        assertNull(resourceExp?.data)
    }
}