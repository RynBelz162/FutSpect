package com.belzsoftware.futspect.ui.fixtures.info

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belzsoftware.futspect.data.fixtures.FixturesRepository
import com.belzsoftware.futspect.model.fixture.Event
import com.belzsoftware.futspect.model.fixture.FixtureResponse
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.shared.Result
import com.belzsoftware.futspect.util.defaultResult

class FixtureInfoViewModel @ViewModelInject constructor(
    private val fixturesRepository: FixturesRepository
) : ViewModel() {

    private val _fixtureResponse = MutableLiveData<FixtureResponse>()
    val fixtureResponse: LiveData<FixtureResponse> = _fixtureResponse

    private val _fixtureEvents = MediatorLiveData<Result<ApiCall<List<Event>>>>()
    val fixtureEvents: LiveData<Result<ApiCall<List<Event>>>> =
        _fixtureEvents

    fun setFixtureResponse(value: FixtureResponse) {

        // set fixture info
        _fixtureResponse.value = value

        // load fixture events
        _fixtureEvents.addSource(loadEvents()) { events ->
            _fixtureEvents.value = events
        }
    }

    private fun loadEvents(): LiveData<Result<ApiCall<List<Event>>>> {
        val fixtureId = _fixtureResponse.value?.fixture?.id ?: return defaultResult()
        return fixturesRepository.getFixtureEvents(fixtureId)
    }
}