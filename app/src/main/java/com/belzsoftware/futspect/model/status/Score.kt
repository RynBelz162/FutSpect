package com.belzsoftware.futspect.model.status

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Score(
    val halfTime: Goals?,
    val fullTime: Goals?,
    val extraTime: Goals?,
    val penalty: Goals?
)