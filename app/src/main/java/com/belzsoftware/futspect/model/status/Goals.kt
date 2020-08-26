package com.belzsoftware.futspect.model.status

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Goals(
    val home: Int? = 0,
    val away: Int? = 0
) : Parcelable