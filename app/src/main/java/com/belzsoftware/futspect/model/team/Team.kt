package com.belzsoftware.futspect.model.team

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Team(
    val id: Int,
    val name: String,
    val logo: String
) : Parcelable