package com.example.starwarx.ui.starwars

import android.app.IntentService
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.starwarx.databinding.ActivityStarWarsBinding
import com.example.starwarx.ui.mathesscreen.MatchScreenData
import com.example.starwarx.ui.mathesscreen.MatchesScreenFragment
import com.example.starwarx.ui.pointstable.PointsTableData
import com.example.starwarx.ui.pointstable.PointsTableFragment

/**
 * @author Yogesh Jangir.
 */

class StarWarsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityStarWarsBinding.inflate(layoutInflater) }
    private val viewModel: StarWarsViewModel by viewModels { StarWarsViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.run {
            title = "Star Wars"
        }
        observeStates()
        viewModel.fetchData()
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()
        Log.d("G8","onCreate")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
        Log.d("G8","onResume")

    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
        Log.d("G8","onStart")
        Thread.sleep(10000)
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()
        Log.d("G8","onPause")
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
        Log.d("G8","onStop")
    }

    private fun addPointsTableFragment(pointsTableData: PointsTableData) {
        supportFragmentManager.commit {
            val instance = PointsTableFragment.getInstance(pointsTableData)
            add(binding.fragmentContainer.id, instance)
            addToBackStack("..")
        }
    }

    private fun addMatchesScreenFragment(matchScreenData: MatchScreenData) {
        supportFragmentManager.commit {
            val instance = MatchesScreenFragment.getInstance(matchScreenData)
            add(binding.fragmentContainer.id, instance)
        }
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
        }

    }

    private fun observeStates() {
        viewModel.getStarWarsState().observe(this) { state->
            when(state){
                is StarWarsState.LoadPointsTable -> {
                    addPointsTableFragment(state.pointsTableData)
                }
                is StarWarsState.LoadMatchScreenFragment -> {
                    addMatchesScreenFragment(state.matchScreenData)
                }
                is StarWarsState.ShowLoading -> {
                    binding.loadingText.visibility = View.VISIBLE
                }
                is StarWarsState.HideLoading -> {
                    binding.loadingText.visibility = View.GONE
                }
                is  StarWarsState.ShowErrorMessage -> {
                    Toast.makeText(this, state.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
        }
        return super.onSupportNavigateUp()
    }
}