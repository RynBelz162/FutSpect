package com.belzsoftware.futspect.model.status

data class Score(
    val halfTime: Goals,
    val fullTime: Goals,
    val extraTime: Goals,
    val penalty: Goals
)