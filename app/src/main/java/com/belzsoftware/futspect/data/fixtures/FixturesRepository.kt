package com.belzsoftware.futspect.data.fixtures

import com.belzsoftware.futspect.util.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FixturesRepository @Inject constructor(private val dataSource: FixturesRemoteSource) {

    fun getFixtures(leagueId: Int) =
        resultLiveData { dataSource.getFixtures(leagueId) }

    fun getFixtureEvents(fixtureId: Int) =
        resultLiveData { dataSource.getFixtureEvents(fixtureId) }
}