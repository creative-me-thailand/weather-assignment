package com.chanothai.assignment.presenter.ui.avatar

import com.chanothai.assignment.presenter.model.AvatarModel

interface CharacterItemCallback {
    fun receive(avatarModel: AvatarModel)
}