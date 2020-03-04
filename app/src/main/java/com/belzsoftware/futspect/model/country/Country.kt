package com.belzsoftware.futspect.model.country

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    val name: String,
    val code: String,
    val flag: String
) : Parcelable