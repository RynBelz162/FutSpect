package com.belzsoftware.futspect.model.league

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    val id: Int,
    val name: String,
    val type: String,
    val logo: String
) : Parcelable