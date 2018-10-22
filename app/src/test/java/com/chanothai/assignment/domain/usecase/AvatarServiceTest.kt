package com.chanothai.assignment.domain.usecase

import com.chanothai.assignment.data.api.repository.CharacterRepo
import com.chanothai.assignment.domain.entity.*
import com.chanothai.assignment.domain.error.BadRequestException
import com.chanothai.assignment.domain.error.Errors
import com.chanothai.assignment.domain.error.NotFoundException
import com.chanothai.assignment.domain.input.GetLocationInput
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class AvatarServiceTest {

    private lateinit var avatarService: AvatarService
    private lateinit var characterRepo: CharacterRepo
    @Before
    fun setup() {
        characterRepo = mock(CharacterRepo::class.java)
        avatarService = AvatarService(characterRepo)
    }

    @Test
    fun `get all character success`() {
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
    fun `get all avatar Failed`() {

        runBlocking {
            `when`(characterRepo.getAll()).thenThrow(Errors.AvatarNotFound)

            avatarService.getAvatars()

            return@runBlocking
        }
    }


    @Test
    fun `get location success`() {
        val input = GetLocationInput(
                "1"
        )

        val locationExp = LocationDetail(
                1,
                "Abadngo"
        )

        runBlocking {
            `when`(characterRepo.getLocationDetail(input.id)).thenReturn(locationExp)

            val result = avatarService.getLocation(input)

            assertEquals(locationExp, result)
            verify(characterRepo).getLocationDetail(input.id)

            return@runBlocking
        }
    }

    @Test(expected = BadRequestException::class)
    fun `get location then input invalid`() {
        val input = GetLocationInput(
                ""
        )

        runBlocking {
            avatarService.getLocation(input)

            return@runBlocking
        }
    }
}