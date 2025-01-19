package com.example.starwarx.ui.starwars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwarx.network.ApiServices

/**
 * @author Yogesh Jangir.
 */
@Suppress("UNCHECKED_CAST")
class StarWarsViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = StarWarsRepository(ApiServices.starWarApi)
        return StarWarsViewModel(repository) as T
    }
}