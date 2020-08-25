package com.belzsoftware.futspect.ui.fixtures.info

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FixtureInfoViewModel @ViewModelInject constructor() : ViewModel() {

    private val fixtureId = MutableLiveData<Int>()

    fun setFixtureId(id: Int) {
        fixtureId.value = id
    }
}