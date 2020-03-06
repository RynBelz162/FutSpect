package com.belzsoftware.futspect.model.status

import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("long") val longStatus: String,
    @SerializedName("short") val shortStatus: String,
    val elapsed: Int
)