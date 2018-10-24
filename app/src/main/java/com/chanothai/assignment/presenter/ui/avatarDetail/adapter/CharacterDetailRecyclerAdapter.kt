package com.chanothai.assignment.presenter.ui.avatarDetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chanothai.assignment.R
import com.chanothai.assignment.presenter.model.AvatarDetailModel

class CharacterDetailRecyclerAdapter(
        private val context: Context,
        private val avatarDetail: AvatarDetailModel
): RecyclerView.Adapter<CharacterDetailHolder>() {

    private val titles = listOf("STATUS", "SPECIES", "GENDER", "ORIGIN", "LAST LOCATION")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterDetailHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.character_detail_item_layout, parent, false)
        return CharacterDetailHolder(view)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: CharacterDetailHolder, position: Int) {
        holder.avatarTitle.text = titles[position]

        when(position) {
            0 -> holder.avatarValue.text = avatarDetail.status
            1 -> holder.avatarValue.text = avatarDetail.species
            2 -> holder.avatarValue.text = avatarDetail.gender
            3 -> holder.avatarValue.text = avatarDetail.origin
            4 -> holder.avatarValue.text = avatarDetail.location
        }
    }
}