package com.example.starwarx.ui.pointstable

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarx.databinding.LayoutPointsTableBinding
import com.example.starwarx.ui.starwars.StarWarsViewModel
import com.example.starwarx.ui.starwars.StarWarsViewModelFactory

/**
 * @author Yogesh Jangir.
 */

class PointsTableFragment : Fragment() {

    private lateinit var binding: LayoutPointsTableBinding
    private var data: PointsTableData? = null
    private val starViewModel: StarWarsViewModel by activityViewModels {
        StarWarsViewModelFactory()
    }

    companion object {
        private const val DATA = "data"

        fun getInstance(data: PointsTableData?) = PointsTableFragment().apply {
            arguments = bundleOf(DATA to data)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutPointsTableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        extractData()
        initViews()
        Log.d("G8:PointsTableFragment","onViewCreated")
    }

    private fun extractData() {
        data = arguments?.getParcelable(DATA)
    }

    private fun initViews() {
        binding.heading.text = data?.heading
        binding.playerList.run {
            adapter = PlayerScoreAdapter {
                starViewModel.getPlayerMatches(it)
            }.apply {
                data?.matchList?.let { setData(it) }
            }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("G8:PointsTableFragment","onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("G8:PointsTableFragment","onDetach")
    }
}