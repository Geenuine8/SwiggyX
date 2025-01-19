package com.example.starwarx.ui.pointstable

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author Yogesh Jangir.
 */

@Parcelize
data class PointsTableData(
    val heading: String?,
    val matchList: List<PointItemData>?
) : Parcelable

@Parcelize
data class PointItemData(
    val id: Int?,
    val icon: String?,
    val name: String?,
    var points: Int? = 0,
    var score: Int? = 0
) : Parcelable
