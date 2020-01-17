package com.belzsoftware.futspect.ui.fixtures

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
    val fixtures = MutableLiveData<List<Fixture>>()

    init {
        loadMatches()
    }

    private fun loadMatches() {
        viewModelScope.launch {
            val result = footballApiService.getFixturesForLeague(524)

            fixtures.value =
                    result.body()?.api?.fixtures
                        ?: emptyList()
        }
    }
}