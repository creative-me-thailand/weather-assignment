package com.chanothai.assignment.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.chanothai.assignment.data.api.repository.CharacterRepo
import com.chanothai.assignment.domain.entity.api.Avatar
import com.chanothai.assignment.domain.entity.api.Avatars
import com.chanothai.assignment.domain.entity.api.Location
import com.chanothai.assignment.domain.entity.api.Origin
import com.chanothai.assignment.domain.entity.database.AvatarEntity
import com.chanothai.assignment.domain.error.BadRequestException
import com.chanothai.assignment.domain.error.Errors
import com.chanothai.assignment.domain.error.NotFoundException
import com.chanothai.assignment.domain.input.GetAvatarInput
import com.chanothai.assignment.presenter.model.Resource
import com.chanothai.assignment.presenter.model.ResourceState
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.*

class AvatarServiceTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var avatarService: AvatarService
    private lateinit var characterRepo: CharacterRepo
    @Before
    fun setup() {
        characterRepo = mock(CharacterRepo::class.java)
        avatarService = AvatarService(characterRepo)
    }

    @Test
    fun `get avatars success`() {
        val avatarEXP = Avatars(
                mutableListOf(
                        Avatar(
                                1,
                                "Rick Sanchez",
                                "Alive",
                                "Human",
                                "",
                                "Male",
                                Origin(
                                        "Earth (C-137)",
                                        "https://rickandmortyapi.com/api/location/1"
                                ),
                                Location(
                                        "Earth (Replacement Dimension)",
                                        "https://rickandmortyapi.com/api/location/20"
                                ),
                                "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                mutableListOf(
                                        "https://rickandmortyapi.com/api/episode/1",
                                        "https://rickandmortyapi.com/api/episode/2"),
                                "https://rickandmortyapi.com/api/character/1",
                                "2017-11-04T18:48:46.250Z"
                        )
                )
        )

        //block to running on background
        runBlocking {
            //Mock
            `when`(characterRepo.getAll()).thenReturn(avatarEXP)

            val result = avatarService.getAvatars()

            //expected
            assertEquals(avatarEXP, result)

            //Spy
            verify(characterRepo).getAll()

            return@runBlocking
        }
    }

    @Test(expected = NotFoundException::class)
    fun `get avatars Failed`() {

        runBlocking {
            `when`(characterRepo.getAll()).thenThrow(Errors.AvatarNotFound)

            avatarService.getAvatars()

            return@runBlocking
        }
    }

    @Test
    fun `get avatar Success`(){
        val input = GetAvatarInput("1")

        val resource = Resource<AvatarEntity>(ResourceState.SUCCESS).apply {
            this.data = AvatarEntity(1)
        }

        val mResource = MutableLiveData<Resource<AvatarEntity>>()
        mResource.value = resource

        runBlocking {

            `when`(characterRepo.get(input.id)).thenReturn(mResource)

            val result = avatarService.getAvatar(input).value

            assertEquals(resource, result)

            verify(characterRepo).get(input.id)

            return@runBlocking
        }
    }

    @Test
    fun `get avatar Failed`() {
        val input = GetAvatarInput("1")

        val resource = Resource<AvatarEntity>(ResourceState.FAILED).apply {
            this.errorMessage = Errors.AvatarNotFound.message
        }

        runBlocking {
            `when`(characterRepo.get(input.id)).thenThrow(Errors.AvatarNotFound)

            val result = avatarService.getAvatar(input).value

            assertEquals(resource, result)

            verify(characterRepo).get(input.id)

            return@runBlocking
        }
    }

    @Test(expected = BadRequestException::class)
    fun `get avatar when input invalid`() {
        val input = GetAvatarInput("")
        runBlocking {
            avatarService.getAvatar(input)
        }
    }
}