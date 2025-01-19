package com.example.starwarx.ui.pointstable

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.starwarx.databinding.LayoutPlayerScoreItemBinding

/**
 * @author Yogesh Jangir.
 */

class PlayerScoreViewHolder(
    private val binding: LayoutPlayerScoreItemBinding,
    private val callback : (Int?) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun setData(data: PointItemData) {
        Glide.with(binding.playerIcon)
            .load(data.icon)
            .into(binding.playerIcon)
        binding.playerName.text = data.name
        binding.playerScore.text = data.points.toString()
        binding.root.setOnClickListener {
            callback(data.id)
        }

    }
}