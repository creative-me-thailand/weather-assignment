package com.chanothai.assignment.presenter.ui.avatar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chanothai.assignment.R
import com.chanothai.assignment.presenter.model.AvatarModel
import com.chanothai.assignment.presenter.ui.avatar.CharacterItemCallback

class CharacterRecyclerAdapter(
        private val context: Context,
        private val avatars: ArrayList<AvatarModel>,
        private val itemCallback: CharacterItemCallback
): RecyclerView.Adapter<CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.character_item_layout, parent, false)

        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return avatars.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.avatarName.text = avatars[position].name

        Glide.with(context)
                .load(avatars[position].image)
                .apply(holder.configImage(context))
                .into(holder.avatarImg)

        holder.holderClickedCallback(itemCallback, avatars[position])
    }
}