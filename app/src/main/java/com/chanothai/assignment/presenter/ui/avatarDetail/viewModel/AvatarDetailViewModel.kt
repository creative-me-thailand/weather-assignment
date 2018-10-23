package com.chanothai.assignment.presenter.ui.avatarDetail.viewModel

import androidx.lifecycle.*
import com.chanothai.assignment.presenter.model.Resource
import com.chanothai.assignment.presenter.model.ResourceState
import com.chanothai.assignment.domain.input.GetAvatarInput
import com.chanothai.assignment.domain.usecase.AvatarService
import com.chanothai.assignment.presenter.model.AvatarDetailModel
import java.lang.Exception

class AvatarDetailViewModel(
        private val avatarService: AvatarService
) : ViewModel() {

    private var mResource = MutableLiveData<Resource<AvatarDetailModel>>()
    val avatarDetailLiveData: LiveData<Resource<AvatarDetailModel>>
    get() = mResource

    suspend fun getAvatar(input: GetAvatarInput) {
        try {
            val resourceRoom = avatarService.getAvatar(input).value
            when(resourceRoom?.status) {
                ResourceState.SUCCESS -> {
                    resourceRoom.data?.let {
                        return@let Resource<AvatarDetailModel>(ResourceState.SUCCESS).apply {
                            this.data = AvatarDetailModel(it.location ?: "").apply {
                                this.id = it.id.toString()
                                this.name = it.name
                                this.image = it.image
                                this.status = it.status
                                this.species = it.species
                                this.gender = it.gender
                                this.origin = it.origin
                                this.created = it.created
                            }
                        }


                    }?.also {resourceAvatar->
                        mResource.value = resourceAvatar
                    }
                }

                ResourceState.FAILED -> {
                    val resource = Resource<AvatarDetailModel>(ResourceState.FAILED).apply {
                        this.errorMessage = resourceRoom.errorMessage
                    }

                    mResource.value = resource
                }
            }
        }catch (e: Exception) {
            val resource = Resource<AvatarDetailModel>(ResourceState.FAILED).apply {
                this.errorMessage = e.message
            }

            mResource.value = resource
        }
    }
}