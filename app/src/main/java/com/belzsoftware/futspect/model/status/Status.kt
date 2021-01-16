package com.belzsoftware.futspect.model.status

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Status(
    @Json(name = "long") val longStatus: String,
    @Json(name = "short") val shortStatus: String,
    val elapsed: Int?
) : Parcelable