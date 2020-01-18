package com.belzsoftware.futspect.ui.fixtures

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belzsoftware.futspect.model.fixture.Fixture
import com.belzsoftware.futspect.network.FootballApiService
import kotlinx.coroutines.launch
import javax.inject.Inject

class FixturesViewModel @Inject constructor(
    private val footballApiService: FootballApiService
) : ViewModel() {

    private val _fixtures = MutableLiveData<List<Fixture>>()
    val fixtures: LiveData<List<Fixture>> = _fixtures

    init {
        loadMatches()
    }

    private fun loadMatches() {
        viewModelScope.launch {
            val result = footballApiService.getFixturesForLeague(524)

            _fixtures.value =
                result.body()?.api?.fixtures
                    ?: emptyList()
        }
    }
}