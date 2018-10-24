package com.chanothai.assignment.presenter.ui.avatarDetail.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.chanothai.assignment.R
import com.chanothai.assignment.common.BaseActivity
import com.chanothai.assignment.data.api.repository.CharacterRepo
import com.chanothai.assignment.data.storage.RoomRepository
import com.chanothai.assignment.domain.input.GetAvatarInput
import com.chanothai.assignment.domain.usecase.AvatarService
import com.chanothai.assignment.presenter.model.AvatarDetailModel
import com.chanothai.assignment.presenter.model.AvatarModel
import com.chanothai.assignment.presenter.model.ResourceState
import com.chanothai.assignment.presenter.ui.avatarDetail.adapter.CharacterDetailRecyclerAdapter
import com.chanothai.assignment.presenter.ui.avatarDetail.viewModel.AvatarDetailViewModel
import kotlinx.android.synthetic.main.toolbar_layout_default.*
import kotlinx.android.synthetic.main.toolbar_layout_collapsing.avatar_img_header as avatarIMG
import kotlinx.android.synthetic.main.recycler_view_layout.recycler_view as avatarRecycler
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

fun Context.avatarDetailActivity(avatarModel: AvatarModel): Intent {
    return Intent(this, AvatarDetailActivity::class.java).apply {
        this.putExtra(AVATAR_MODEL, avatarModel)
    }
}

private const val AVATAR_MODEL = "avatar_model"
class AvatarDetailActivity : BaseActivity() {
    private var avatarModel: AvatarModel? = null
    private var avatarAdapter: CharacterDetailRecyclerAdapter? = null

    private lateinit var viewModel: AvatarDetailViewModel

    override fun isDisplayHomeEnable(): Boolean = true
    override fun getToolbarInstance(): Toolbar? = toolbar
    override fun title(): String? = null
    override fun isToolbarCollapsed(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avatar_detail)

        initViewMode()
        bindBundle()
    }

    private fun bindBundle() {
        intent?.let {
            avatarModel = it.getParcelableExtra(AVATAR_MODEL)
            avatarModel
        }?.also { model->
            getAvatar(model.id)
        }
    }

    private fun initViewMode() {
        viewModel = ViewModelProviders.of(this,
                createViewModel(
                        AvatarDetailViewModel(AvatarService(CharacterRepo(RoomRepository(this)))
                        ))).get(AvatarDetailViewModel::class.java)

        avatarDetailObserve()
    }

    private fun avatarDetailObserve() {
        viewModel.avatarDetailLiveData.observe(this, Observer {resource->
            when(resource.status) {
                ResourceState.SUCCESS -> {
                    resource.data?.let {data->
                        setDataCharacterDetail(data)
                    }
                }

                ResourceState.FAILED -> Toast.makeText(this, resource.errorMessage, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setDataCharacterDetail(model: AvatarDetailModel) {
        super.updateTitleToolbar(model.name ?: "")
        Glide.with(this)
                .load(model.image)
                .into(avatarIMG)
        avatarAdapter?.let { adapter->
            adapter.notifyDataSetChanged()
            return
        }

        avatarAdapter = CharacterDetailRecyclerAdapter(this, model)

        with(avatarRecycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = avatarAdapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun getAvatar(id: String) {
        val input = GetAvatarInput(id)
        launch(UI) {
            viewModel.getAvatar(input)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        }else {
            return super.onOptionsItemSelected(item)
        }
    }
}
