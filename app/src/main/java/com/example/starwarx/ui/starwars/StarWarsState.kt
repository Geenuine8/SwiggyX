package com.example.starwarx.ui.starwars

import com.example.starwarx.ui.mathesscreen.MatchScreenData
import com.example.starwarx.ui.pointstable.PointsTableData

/**
 * @author Yogesh Jangir.
 */

sealed class StarWarsState {

    data object ShowLoading : StarWarsState()

    data object HideLoading : StarWarsState()

    data class ShowErrorMessage(val errorMessage: String?) : StarWarsState()

    data class LoadPointsTable(val pointsTableData: PointsTableData) : StarWarsState()

    data class LoadMatchScreenFragment(val matchScreenData: MatchScreenData) : StarWarsState()
}