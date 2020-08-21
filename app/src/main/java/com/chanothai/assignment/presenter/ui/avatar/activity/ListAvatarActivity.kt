package com.chanothai.assignment.presenter.ui.avatar.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.chanothai.assignment.R
import com.chanothai.assignment.common.BaseActivity
import com.chanothai.assignment.data.api.repository.CharacterRepo
import com.chanothai.assignment.data.storage.RoomRepository
import com.chanothai.assignment.domain.usecase.AvatarService
import com.chanothai.assignment.presenter.model.AvatarModel
import com.chanothai.assignment.presenter.model.ResourceState
import com.chanothai.assignment.presenter.ui.avatar.CharacterItemCallback
import com.chanothai.assignment.presenter.ui.avatar.adapter.CharacterRecyclerAdapter
import com.chanothai.assignment.presenter.ui.avatar.viewModel.AvatarViewModel
import com.chanothai.assignment.presenter.ui.avatarDetail.activity.avatarDetailActivity
import kotlinx.android.synthetic.main.toolbar_layout_default.*
import kotlinx.android.synthetic.main.recycler_view_layout.recycler_view as listCharacter
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class ListAvatarActivity : BaseActivity(), CharacterItemCallback {

    private lateinit var viewModel: AvatarViewModel
    private var recyclerAdapter: CharacterRecyclerAdapter? = null

    override fun isDisplayHomeEnable(): Boolean = false
    override fun getToolbarInstance(): Toolbar? = toolbar
    override fun title(): String? = "Character"
    override fun isToolbarCollapsed(): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_avatar)

        initViewMode()

        if (savedInstanceState == null) {
            loadCharacters()
        }
    }

    private fun initViewMode() {
        viewModel = ViewModelProviders.of(this,
                createViewModel(
                        AvatarViewModel(AvatarService(CharacterRepo(RoomRepository(this))))
                )).get(AvatarViewModel::class.java)

        avatarObserve()
    }

    private fun avatarObserve() {
        viewModel.avatarLiveData.observe(this, Observer {resource->
            when(resource.status) {
                ResourceState.SUCCESS -> {
                    val avatars = resource.data?.avatars ?: arrayListOf()
                    setDataCharacter(avatars)
                }

                ResourceState.FAILED -> {
                    Toast.makeText(this, resource.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setDataCharacter(avatars: ArrayList<AvatarModel>) {
        recyclerAdapter?.let { adapter->
            adapter.notifyDataSetChanged()
            return
        }

        recyclerAdapter = CharacterRecyclerAdapter(
                this, avatars, this)

        listCharacter.layoutManager = LinearLayoutManager(this)
        listCharacter.hasFixedSize()
        listCharacter.adapter = recyclerAdapter
    }

    private fun loadCharacters() {
        launch(UI) {
            viewModel.loadAvatars()
        }
    }

    override fun receive(avatarModel: AvatarModel) {
        startActivity(avatarDetailActivity(avatarModel))
    }
}
