package com.belzsoftware.futspect.model.standings


sealed class DataItem {
    abstract val id: Int

    data class StandingItem (val standing: Standing): DataItem() {
        override val id = standing.team.id
    }

    object Header : DataItem() {
        override val id = Int.MIN_VALUE
    }
}