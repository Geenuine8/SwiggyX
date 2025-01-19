package com.example.starwarx.ui.mathesscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarx.databinding.LayoutMatchesScreenBinding
import kotlinx.coroutines.DelicateCoroutinesApi

/**
 * @author Yogesh Jangir.
 */
class MatchesScreenFragment: Fragment() {

    private lateinit var binding:LayoutMatchesScreenBinding
    private var data: MatchScreenData? = null

    companion object{
        private const val DATA = "data"

        fun getInstance(data: MatchScreenData? ) = MatchesScreenFragment().apply {
            arguments = bundleOf(DATA to data)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = LayoutMatchesScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        extractData()
        initViews()
        Log.d("G8:MM","onViewCreated")
    }

    private fun extractData() {
        data = arguments?.getParcelable(DATA)
    }

    private fun initViews() {
        binding.heading.text = data?.heading
        binding.matchList.run {
            adapter = MatchListAdapter().apply {
                data?.matchList?.let { setData(it) }
            }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("G8:MM","onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("G8:MM","onDetach")
    }
}