package com.belzsoftware.futspect.model.shared

data class ApiCall<T>(
    val results: Int,
    val response: T
)