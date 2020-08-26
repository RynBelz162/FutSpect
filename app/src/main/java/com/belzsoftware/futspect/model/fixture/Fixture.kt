package com.belzsoftware.futspect.model.fixture

import android.os.Parcelable
import com.belzsoftware.futspect.model.status.Status
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@JsonClass(generateAdapter = true)
data class Fixture(
    val id: Int,
    val venue: Venue,
    val referee: String?,
    val timezone: String,
    val date: Date,
    val timestamp: Long,
    val status: Status
) : Parcelable