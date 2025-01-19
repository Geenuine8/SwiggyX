package com.example.starwarx.ui.mathesscreen

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author Yogesh Jangir.
 */

@Parcelize
data class MatchScreenData(
    val heading: String?,
    val matchList: List<MatchItemData>?
) : Parcelable

@Parcelize
data class MatchItemData(
    var player1: String? = null,
    var player2: String? = null,
    var score1: Int? = null,
    var score2: Int? = null,
    var result: Boolean? = null
) : Parcelable


