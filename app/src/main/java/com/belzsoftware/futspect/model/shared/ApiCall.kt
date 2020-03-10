package com.belzsoftware.futspect.model.shared

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiCall<T>(
    val results: Int,
    val response: T
)