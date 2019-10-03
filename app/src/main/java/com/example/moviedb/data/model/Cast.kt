package com.example.moviedb.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cast(
    val cast_id: String?,
    val character: String?,
    val credit_id: String?,
    val gender: Int?,
    val id: String?,
    val name: String?,
    val order: Int?,
    val profile_path: String?
) : Parcelable