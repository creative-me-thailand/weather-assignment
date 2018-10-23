package com.chanothai.assignment.presenter.ui.avatar.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chanothai.assignment.R
import com.chanothai.assignment.common.BaseActivity
import com.chanothai.assignment.data.api.repository.CharacterRepo
import com.chanothai.assignment.data.storage.RoomRepository
import com.chanothai.assignment.domain.usecase.AvatarService
import com.chanothai.assignment.presenter.ui.avatar.viewModel.AvatarViewModel
import com.chanothai.assignment.presenter.ui.avatarDetail.activity.AvatarDetailActivity
import kotlinx.android.synthetic.main.activity_list_avatar.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class ListAvatarActivity : BaseActivity() {

    private lateinit var viewModel: AvatarViewModel

    override fun isDisplayHomeEnable(): Boolean? = false

    override fun getToolbarInstance(): Toolbar? = null
    override fun title(): String? = "Avatar"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_avatar)

        initViewMode()
        setClicked()

        if (savedInstanceState == null) {
            loadCharacters()
        }
    }

    private fun initViewMode() {
        viewModel = ViewModelProviders.of(this,
                createViewModel(
                        AvatarViewModel(AvatarService(CharacterRepo(RoomRepository(this))))
                )).get(AvatarViewModel::class.java)

        viewModel.avatarLiveData.observe(this, Observer {resource->
            val avatars = resource.data?.avatars ?: arrayListOf()
            txt_message.text = avatars[0].name
        })
    }

    private fun loadCharacters() {
        launch(UI) {
            viewModel.loadAvatars()
        }
    }

    private fun setClicked() {
        btn_go_avatar_detail.setOnClickListener {
            startActivity(Intent(this, AvatarDetailActivity::class.java))
        }
    }
}
