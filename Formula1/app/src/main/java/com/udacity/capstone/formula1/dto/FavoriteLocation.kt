package com.udacity.capstone.formula1.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteLocation(
    val id: Long,
    val latitude: Double,
    val longitude: Double
): Parcelable