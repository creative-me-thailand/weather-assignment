package com.chanothai.assignment.ui.avatar.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanothai.assignment.domain.entity.Avatars

class AvatarViewModel: ViewModel() {

    private val mAvatars = MutableLiveData<Avatars>()
    val avatarLiveData: LiveData<Avatars>
    get() = mAvatars

    fun loadAvatars() {

    }
}