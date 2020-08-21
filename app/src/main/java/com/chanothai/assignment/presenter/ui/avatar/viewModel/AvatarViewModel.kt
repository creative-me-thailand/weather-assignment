package com.chanothai.assignment.presenter.ui.avatar.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanothai.assignment.presenter.model.Resource
import com.chanothai.assignment.presenter.model.ResourceState
import com.chanothai.assignment.domain.usecase.AvatarService
import com.chanothai.assignment.presenter.model.AvatarModel
import com.chanothai.assignment.presenter.model.AvatarsModel
import java.lang.Exception

class AvatarViewModel(
        private val avatarService: AvatarService
) : ViewModel() {

    private val mAvatars = MutableLiveData<Resource<AvatarsModel>>()
    val avatarLiveData: LiveData<Resource<AvatarsModel>>
        get() = mAvatars

    suspend fun loadAvatars() {
        try {
            val avatars = avatarService.getAvatars()

            val listAvatar = arrayListOf<AvatarModel>()
            avatars.results.forEachIndexed { index, avatar ->
                val avatarModel = AvatarModel(
                        avatar.id.toString(),
                        avatar.name,
                        avatar.image)

                listAvatar.add(avatarModel)
            }

            val resource = Resource<AvatarsModel>(ResourceState.SUCCESS).apply {
                this.data = AvatarsModel(listAvatar)
            }

            mAvatars.value = resource

        }catch (e: Exception) {
            val resource = Resource<AvatarsModel>(ResourceState.FAILED).apply {
                this.errorMessage = e.message
            }

            mAvatars.value = resource
        }
    }
}