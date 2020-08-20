package com.belzsoftware.futspect.ui.fixtures

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.belzsoftware.futspect.data.fixtures.FixturesRepository
import com.belzsoftware.futspect.model.fixture.FixtureResponse
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.shared.Result

class FixturesViewModel @ViewModelInject constructor(
    fixturesRepository: FixturesRepository
) : ViewModel() {

    val fixtures: LiveData<Result<ApiCall<List<FixtureResponse>>>> =
        fixturesRepository.getFixtures(262)

}