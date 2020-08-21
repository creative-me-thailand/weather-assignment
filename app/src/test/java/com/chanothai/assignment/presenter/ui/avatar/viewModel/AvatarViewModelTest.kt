package com.chanothai.assignment.presenter.ui.avatar.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chanothai.assignment.domain.entity.api.Avatar
import com.chanothai.assignment.domain.entity.api.Avatars
import com.chanothai.assignment.domain.entity.api.Location
import com.chanothai.assignment.domain.entity.api.Origin
import com.chanothai.assignment.domain.error.Errors
import com.chanothai.assignment.domain.usecase.AvatarService
import com.chanothai.assignment.presenter.model.AvatarModel
import com.chanothai.assignment.presenter.model.AvatarsModel
import com.chanothai.assignment.presenter.model.Resource
import com.chanothai.assignment.presenter.model.ResourceState
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.*

class AvatarViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var avatarService: AvatarService
    private lateinit var avatarViewModel: AvatarViewModel

    @Before
    fun setup() {
        avatarService = mock(AvatarService::class.java)
        avatarViewModel = AvatarViewModel(avatarService)
    }

    @Test
    fun loadAvatarSuccess() {
        val avatars = Avatars(
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

        val avatarModel = AvatarModel(
                avatars.results[0].id.toString(),
                avatars.results[0].name,
                avatars.results[0].image)

        val avatarsModel = AvatarsModel(arrayListOf(avatarModel))
        val resourceExp = Resource<AvatarsModel>(ResourceState.SUCCESS).apply {
            this.data = avatarsModel
        }

        runBlocking {
            `when`(avatarService.getAvatars()).thenReturn(avatars)

            avatarViewModel.loadAvatars()

            val result = avatarViewModel.avatarLiveData.value

            assertEquals(resourceExp, result)

            verify(avatarService).getAvatars()

            return@runBlocking
        }
    }

    @Test
    fun loadAvatarFailed() {
        val resourceExp = Resource<AvatarsModel>(ResourceState.FAILED).apply {
            this.errorMessage = Errors.AvatarNotFound.message
        }

        runBlocking {
            `when`(avatarService.getAvatars()).thenThrow(Errors.AvatarNotFound)

            avatarViewModel.loadAvatars()

            val result = avatarViewModel.avatarLiveData.value

            assertEquals(resourceExp, result)

            verify(avatarService).getAvatars()

            return@runBlocking
        }
    }
}