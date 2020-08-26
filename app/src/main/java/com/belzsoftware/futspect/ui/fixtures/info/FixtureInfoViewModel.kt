package com.belzsoftware.futspect.ui.fixtures.info

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belzsoftware.futspect.model.fixture.FixtureResponse

class FixtureInfoViewModel @ViewModelInject constructor() : ViewModel() {

    private val _fixtureResponse = MutableLiveData<FixtureResponse>()
    val fixtureResponse: LiveData<FixtureResponse> = _fixtureResponse


    fun setFixtureResponse(value: FixtureResponse) {
        _fixtureResponse.value = value
    }
}