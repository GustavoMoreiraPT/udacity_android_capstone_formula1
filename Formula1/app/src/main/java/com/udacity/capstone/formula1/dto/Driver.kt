package com.udacity.capstone.formula1.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Driver(
    val id: Long,
    val driverId: String,
    val code: String,
    val permanentNumber: Int,
    val givenName: String,
    val familyName: String,
    val dateOfBirth: String,
    val nationality: String,
    val url: String) : Parcelable