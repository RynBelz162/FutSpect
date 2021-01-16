package com.belzsoftware.futspect.model.country

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Country(
    val name: String = "",
    val code: String? = "",
    val flag: String? = ""
) : Parcelable