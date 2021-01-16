package com.belzsoftware.futspect.model.fixture

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Venue(
    val id: Int?,
    val name: String?,
    val city: String?
) : Parcelable