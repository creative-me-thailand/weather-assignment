package com.chanothai.assignment.presenter.ui.avatarDetail.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chanothai.assignment.R
import com.chanothai.assignment.common.BaseActivity
import com.chanothai.assignment.data.api.repository.CharacterRepo
import com.chanothai.assignment.data.storage.RoomRepository
import com.chanothai.assignment.domain.input.GetAvatarInput
import com.chanothai.assignment.domain.usecase.AvatarService
import com.chanothai.assignment.presenter.ui.avatarDetail.viewModel.AvatarDetailViewModel
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class AvatarDetailActivity : BaseActivity() {

    private lateinit var viewModel: AvatarDetailViewModel

    override fun isDisplayHomeEnable(): Boolean? = true

    override fun getToolbarInstance(): Toolbar? = null

    override fun title(): String = "AvatarDetail"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avatar_detail)

        initViewMode()

        if (savedInstanceState == null) {
            getAvatar("1")
        }
    }

    private fun initViewMode() {
        viewModel = ViewModelProviders.of(this,
                createViewModel(
                        AvatarDetailViewModel(AvatarService(CharacterRepo(RoomRepository(this)))
                        ))).get(AvatarDetailViewModel::class.java)

        viewModel.avatarDetailLiveData.observe(this, Observer {resource->
            val data = resource.data

            Toast.makeText(this, data?.name, Toast.LENGTH_LONG).show()
        })
    }

    private fun getAvatar(id: String) {
        val input = GetAvatarInput(id)
        launch(UI) {
            viewModel.getAvatar(input)
        }
    }
}
