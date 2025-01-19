package com.example.starwarx.ui.pointstable

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarx.databinding.LayoutMatchItemBinding
import com.example.starwarx.databinding.LayoutPlayerScoreItemBinding

/**
 * @author Yogesh Jangir.
 */
class PlayerScoreAdapter(
    private val callback : (Int?) -> Unit
): RecyclerView.Adapter<PlayerScoreViewHolder>() {

    private val data: MutableList<PointItemData> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(matchList: List<PointItemData>){
        data.clear()
        data.addAll(matchList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerScoreViewHolder {
        val binding = LayoutPlayerScoreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerScoreViewHolder(binding, callback)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PlayerScoreViewHolder, position: Int) {
        holder.setData(data[position])
    }
}