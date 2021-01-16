package com.belzsoftware.futspect.model.status

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Goals(
    val home: Int?,
    val away: Int?
) : Parcelable