package com.example.starwarx.ui.starwars

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * @author Yogesh Jangir.
 */

class StarWarsViewModel(private val repository: StarWarsRepository) : ViewModel() {

    private val starWarsState: MutableLiveData<StarWarsState> = MutableLiveData()

    fun fetchData() {
        starWarsState.value = StarWarsState.ShowLoading
        viewModelScope.launch {
            repository.fetchData()
                .catch { onError(it) }
                .collect { onSuccess() }
        }
    }

    private fun onError(throwable: Throwable) {
        starWarsState.value = StarWarsState.HideLoading
        starWarsState.value = StarWarsState.ShowErrorMessage(throwable.stackTraceToString())
    }

    private fun onSuccess() {
        starWarsState.value = StarWarsState.HideLoading
        starWarsState.value = StarWarsState.LoadPointsTable(repository.getPointTableData())
    }

    fun getPlayerMatches(id: Int?) {
        starWarsState.value = StarWarsState.LoadMatchScreenFragment(repository.getPlayerMatches(id))
    }

    fun getStarWarsState() = starWarsState as LiveData<StarWarsState>
}
