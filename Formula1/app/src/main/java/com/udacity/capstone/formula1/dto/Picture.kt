package com.udacity.capstone.formula1.dto

import com.squareup.moshi.Json

data class Picture(@Json(name = "media_type") val mediaType: String, val title: String,
                        val url: String){
}