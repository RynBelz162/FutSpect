package com.belzsoftware.futspect.ui.fixtures

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belzsoftware.futspect.model.fixture.Fixture
import com.belzsoftware.futspect.network.FootballRetrofitFactory
import kotlinx.coroutines.launch

class FixturesViewModel : ViewModel() {
    val fixtures = MutableLiveData<List<Fixture>>()

    init {
        loadMatches()
    }

    private fun loadMatches() {
        viewModelScope.launch {
            val result = FootballRetrofitFactory
                .createFootballApiService()
                .getMatchesForLeagueAsync(2003, 1)
                .await()

            fixtures.value =
                    result.body()?.fixtures
                        ?: emptyList()
        }
    }
}