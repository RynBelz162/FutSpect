package com.belzsoftware.futspect.model.season

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate

@Parcelize
@JsonClass(generateAdapter = true)
data class Season(
    val year: Int,
    val start: LocalDate?,
    val end: LocalDate?,
    val current: Boolean
) : Parcelable