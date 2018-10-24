package com.chanothai.assignment.presenter.ui.avatar.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.chanothai.assignment.R
import com.chanothai.assignment.presenter.model.AvatarModel
import com.chanothai.assignment.presenter.ui.avatar.CharacterItemCallback
import kotlinx.android.synthetic.main.character_item_layout.view.*

class CharacterViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
    var avatarImg = itemView.item_avatar_img
    var avatarName = itemView.item_avatar_name

    fun holderClickedCallback(itemCallback: CharacterItemCallback, model: AvatarModel) {
        itemView.setOnClickListener {
            itemCallback.receive(model)
        }
    }

    fun configImage(context: Context):RequestOptions {
//        val radius = context.resources.getDimensionPixelSize(R.dimen.radius_img)

        return RequestOptions().apply {
            this.centerCrop()
//            this.transforms(CenterCrop(), RoundedCorners(radius))
            this.placeholder(R.mipmap.ic_launcher)
        }
    }
}