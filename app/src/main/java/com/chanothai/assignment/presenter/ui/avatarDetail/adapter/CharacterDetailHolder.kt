package com.chanothai.assignment.presenter.ui.avatarDetail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.character_detail_item_layout.view.*

class CharacterDetailHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val avatarTitle = itemView.avatar_title_item
    val avatarValue = itemView.avatar_value_item
}