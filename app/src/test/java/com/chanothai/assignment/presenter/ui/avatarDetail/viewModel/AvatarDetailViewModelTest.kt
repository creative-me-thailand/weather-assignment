package com.chanothai.assignment.presenter.ui.avatarDetail.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chanothai.assignment.domain.entity.LocationDetail
import com.chanothai.assignment.domain.entity.Resource
import com.chanothai.assignment.domain.entity.ResourceState
import com.chanothai.assignment.domain.error.Errors
import com.chanothai.assignment.domain.input.GetLocationInput
import com.chanothai.assignment.domain.usecase.AvatarService
import com.chanothai.assignment.presenter.model.AvatarDetailModel
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.*

class AvatarDetailViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var avatarService: AvatarService
    private lateinit var avatarDetailViewModel: AvatarDetailViewModel

    @Before
    fun setup() {
        avatarService = mock(AvatarService::class.java)
        avatarDetailViewModel = AvatarDetailViewModel(avatarService)
    }

    @Test
    fun loadLocationSuccess() {
        val input = GetLocationInput(
                "1"
        )

        val location = LocationDetail(
                input.id.toInt(),
                "Bangkok"
        )

        val avatarDetailModel = AvatarDetailModel(
                location.name
        )

        val resourceExp = Resource<AvatarDetailModel>(ResourceState.SUCCESS).apply {
            this.data = avatarDetailModel
        }

        runBlocking {
            `when`(avatarService.getLocation(input)).thenReturn(location)

            avatarDetailViewModel.loadLocation(input)

            val result = avatarDetailViewModel.avatarDetailLiveData.value

            assertEquals(resourceExp, result)

            verify(avatarService).getLocation(input)

            return@runBlocking
        }
    }

    @Test
    fun loadLocationFailed() {
        val input = GetLocationInput(
                "1"
        )

        val resourceExp = Resource<AvatarDetailModel>(ResourceState.FAILED).apply {
            this.errorMessage = Errors.LocationNotFound.message
        }

        runBlocking {
            `when`(avatarService.getLocation(input)).thenThrow(Errors.LocationNotFound)

            avatarDetailViewModel.loadLocation(input)

            val result = avatarDetailViewModel.avatarDetailLiveData.value

            assertEquals(resourceExp, result)

            verify(avatarService).getLocation(input)

            return@runBlocking
        }
    }


}