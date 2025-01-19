package com.example.starwarx.ui.mathesscreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarx.databinding.LayoutMatchItemBinding

/**
 * @author Yogesh Jangir.
 */
class MatchListAdapter: RecyclerView.Adapter<MatchListViewHolder>() {

    private val data: MutableList<MatchItemData> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(matchList: List<MatchItemData>){
        data.clear()
        data.addAll(matchList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchListViewHolder {
        val binding = LayoutMatchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchListViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MatchListViewHolder, position: Int) {
        holder.setData(data[position])
    }
}