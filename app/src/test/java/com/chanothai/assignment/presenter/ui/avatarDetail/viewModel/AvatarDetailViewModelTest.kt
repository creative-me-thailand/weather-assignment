package com.chanothai.assignment.presenter.ui.avatarDetail.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import com.chanothai.assignment.domain.entity.database.AvatarEntity
import com.chanothai.assignment.domain.error.Errors
import com.chanothai.assignment.domain.input.GetAvatarInput
import com.chanothai.assignment.domain.usecase.AvatarService
import com.chanothai.assignment.presenter.model.AvatarDetailModel
import com.chanothai.assignment.presenter.model.Resource
import com.chanothai.assignment.presenter.model.ResourceState
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class AvatarDetailViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Mock private lateinit var avatarService: AvatarService
    private lateinit var viewModel: AvatarDetailViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = AvatarDetailViewModel(avatarService)
    }

    @Test
    fun `load avatar success`() {
        val input = GetAvatarInput("1")

        val rsEntity = Resource<AvatarEntity>(ResourceState.SUCCESS).apply {
            this.data = AvatarEntity(1).apply {
                location = "bangkok"
            }
        }

        val mResource = MutableLiveData<Resource<AvatarEntity>>()
        mResource.postValue(rsEntity)

        runBlocking {
            `when`(avatarService.getAvatar(input)).thenReturn(mResource)

            viewModel.getAvatar(input)

            val result = viewModel.avatarDetailLiveData.value

            val resourceExp = Resource<AvatarDetailModel>(ResourceState.SUCCESS).apply {
                this.data = AvatarDetailModel("bangkok").apply {
                    this.id = "1"
                }
            }

            assertEquals(resourceExp, result)

            verify(avatarService).getAvatar(input)

            return@runBlocking
        }
    }

    @Test
    fun `load avatar failed`() {
        val input = GetAvatarInput("0")

        val rsEntity = Resource<AvatarEntity>(ResourceState.FAILED).apply {
            this.errorMessage = Errors.AvatarNotFound.message
        }

        val mResource = MutableLiveData<Resource<AvatarEntity>>()
        mResource.postValue(rsEntity)

        runBlocking {
            `when`(avatarService.getAvatar(input)).thenReturn(mResource)

            viewModel.getAvatar(input)

            val result = viewModel.avatarDetailLiveData.value

            val resourceExp = Resource<AvatarDetailModel>(ResourceState.FAILED).apply {
                this.errorMessage = rsEntity.errorMessage
            }

            assertEquals(resourceExp, result)

            verify(avatarService).getAvatar(input)

            return@runBlocking
        }
    }

    @Test
    fun `load avatar but input invalid`() {
        val input = GetAvatarInput("")

        runBlocking {
            viewModel.getAvatar(input)

            val result = viewModel.avatarDetailLiveData.value

            val resourceExp = Resource<AvatarDetailModel>(ResourceState.FAILED).apply {
                this.errorMessage = Errors.InvalidInputGetAavatar.message
            }

            assertEquals(resourceExp, result)

            verify(avatarService).getAvatar(input)

            return@runBlocking
        }
    }
}