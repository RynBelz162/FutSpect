package com.belzsoftware.futspect.ui.leagues.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belzsoftware.futspect.data.league.LeaguesRepository
import com.belzsoftware.futspect.entity.league.LeagueFilters
import com.belzsoftware.futspect.util.Event
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class LeagueFilterViewModel @Inject constructor(
    private val leaguesRepository: LeaguesRepository
) : ViewModel() {
    val isLeagueChecked = MutableLiveData<Boolean>()
    val isCupChecked = MutableLiveData<Boolean>()
    val searchTerm = MutableLiveData<String>()

    private val _navigateToLeagues = MutableLiveData<Event<String>>()
    val navigateToLeagues: LiveData<Event<String>>
        get() = _navigateToLeagues

    init {
        viewModelScope.launch {
            val filters = leaguesRepository
                .getFilters()
                .first()

            filters ?: return@launch
            setFilters(filters)
        }
    }

    fun clearFilters() {
        isLeagueChecked.value = false
        isCupChecked.value = false
        searchTerm.value = ""
    }

    fun apply() {
        viewModelScope.launch {

            var finalSearchTerm: String? = searchTerm.value
            if (searchTerm.value.isNullOrBlank() || searchTerm.value.isNullOrEmpty()) {
                finalSearchTerm = null
            }

            val filters = LeagueFilters(
                searchTerm = finalSearchTerm,
                isLeagueChecked = isLeagueChecked.value ?: false,
                isCupChecked = isCupChecked.value ?: false
            )
            leaguesRepository.saveFilters(filters)
        }

        _navigateToLeagues.value = Event("Navigate")
    }

    private fun setFilters(filters: LeagueFilters) {
        isLeagueChecked.value = filters.isLeagueChecked
        isCupChecked.value = filters.isCupChecked
        searchTerm.value = filters.searchTerm
    }
}