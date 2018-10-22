package com.chanothai.assignment.presenter.ui.avatarDetail.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanothai.assignment.domain.entity.Resource
import com.chanothai.assignment.domain.entity.ResourceState
import com.chanothai.assignment.domain.input.GetLocationInput
import com.chanothai.assignment.domain.usecase.AvatarService
import com.chanothai.assignment.presenter.model.AvatarDetailModel
import com.chanothai.assignment.presenter.model.AvatarsModel
import java.lang.Exception

class AvatarDetailViewModel(
        private val avatarService: AvatarService
): ViewModel() {

    private val mAvatarDetail = MutableLiveData<Resource<AvatarDetailModel>>()
    val avatarDetailLiveData: LiveData<Resource<AvatarDetailModel>>
    get() = mAvatarDetail

    suspend fun loadLocation(input: GetLocationInput) {
        try {
            val location = avatarService.getLocation(input)

            val avatarDetailModel = AvatarDetailModel(location.name)
            val resource = Resource<AvatarDetailModel>(ResourceState.SUCCESS).apply {
                this.data = avatarDetailModel
            }

            mAvatarDetail.value = resource
        }catch (e: Exception) {
            val resource = Resource<AvatarDetailModel>(ResourceState.FAILED).apply {
                this.errorMessage = e.message
            }

            mAvatarDetail.value = resource
        }
    }
}