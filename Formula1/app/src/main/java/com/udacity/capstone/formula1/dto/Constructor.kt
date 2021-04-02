package com.udacity.capstone.formula1.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Constructor(
    val id: Long,
    val name: String,
    val nationality: String,
    val url: String): Parcelable