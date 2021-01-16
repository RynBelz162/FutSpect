package com.belzsoftware.futspect.model.league

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class League(
    val id: Int = 0,
    val name: String = "",
    val type: String = "",
    val logo: String? = "",
    val country: String = "",
    val flag: String? = "",
    val season: Int = 0,
    val round: String = ""
) : Parcelable