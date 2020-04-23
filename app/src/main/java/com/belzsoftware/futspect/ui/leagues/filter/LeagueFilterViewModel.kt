package com.belzsoftware.futspect.ui.leagues.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belzsoftware.futspect.util.Event
import timber.log.Timber
import javax.inject.Inject

class LeagueFilterViewModel @Inject constructor() : ViewModel() {
    val isLeagueChecked = MutableLiveData(true)
    val isCupChecked = MutableLiveData(true)
    val searchTerm = MutableLiveData("Hello")

    private val _navigateToLeagues = MutableLiveData<Event<String>>()
    val navigateToLeagues: LiveData<Event<String>>
        get() = _navigateToLeagues

    fun clearFilters() {
        isLeagueChecked.value = false
        isCupChecked.value = false
        searchTerm.value = ""
    }

    fun apply() {
        // save selections
        Timber.i("Saved")
        _navigateToLeagues.value = Event("Navigate")
    }
}