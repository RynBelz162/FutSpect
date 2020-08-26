package com.belzsoftware.futspect.model.team

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Teams(
    val home: Team,
    val away: Team
) : Parcelable