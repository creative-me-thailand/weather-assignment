package com.chanothai.assignment.data.api.repository

import androidx.test.runner.AndroidJUnit4
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterRepoTest {

    private lateinit var characterRepo: CharacterRepo
    @Before
    fun setup() {
        characterRepo = CharacterRepo()
    }

    @Test
    fun get_Character_Success() {
        val avatars = runBlocking { characterRepo.getAll() }
        assertNotNull(avatars)
    }

    @Test
    fun get_Location_Sucess() {
        val id = "1"
        val location = runBlocking { characterRepo.getLocationDetail(id) }
        assertNotNull(location)
    }
}