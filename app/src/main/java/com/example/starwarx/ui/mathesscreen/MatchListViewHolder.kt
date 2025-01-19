package com.example.starwarx.ui.mathesscreen

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarx.R
import com.example.starwarx.databinding.LayoutMatchItemBinding

/**
 * @author Yogesh Jangir.
 */

class MatchListViewHolder(private val binding: LayoutMatchItemBinding): RecyclerView.ViewHolder(binding.root) {

    companion object{
        private const val SEPARATOR = " - "
    }

    fun setData(data: MatchItemData){
        setBgColor(data.result)
        binding.player1.text = data.player1
        binding.player2.text = data.player2
        binding.score.text = buildString {
            append(data.score1)
            append(SEPARATOR)
            append(data.score2)
        }
    }

    private fun setBgColor(result: Boolean?) {
        when (result) {
            true -> binding.root.setBackgroundColor(Color.GREEN)
            false -> binding.root.setBackgroundColor(Color.RED)
            else -> binding.root.setBackgroundColor(Color.WHITE)
        }
    }
}