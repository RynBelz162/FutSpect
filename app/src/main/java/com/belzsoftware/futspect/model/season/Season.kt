package com.belzsoftware.futspect.model.season

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Season(
    val year: Int,
    val start: Date,
    val end: Date,
    val current: Boolean
) : Parcelable