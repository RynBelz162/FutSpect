package com.belzsoftware.futspect.model.status

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Score(
    val halfTime: Goals?,
    val fullTime: Goals?,
    val extraTime: Goals?,
    val penalty: Goals?
) : Parcelable